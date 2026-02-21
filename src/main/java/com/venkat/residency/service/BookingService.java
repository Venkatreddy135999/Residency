package com.venkat.residency.service;

import com.venkat.residency.entity.Booking;
import com.venkat.residency.entity.Room;
import com.venkat.residency.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private RoomService roomService;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> getBookingsByEmail(String email) {
        return bookingRepository.findByGuestEmail(email);
    }

    public List<Booking> getBookingsByStatus(String status) {
        return bookingRepository.findByStatus(status);
    }

    public Booking createBooking(Booking booking, Long roomId) {
        Optional<Room> roomOpt = roomService.getRoomById(roomId);
        if (roomOpt.isPresent() && roomOpt.get().getAvailable()) {
            Room room = roomOpt.get();
            
            // Validate dates
            if (booking.getCheckInDate() == null || booking.getCheckOutDate() == null) {
                throw new IllegalArgumentException("Check-in and check-out dates are required");
            }
            if (booking.getCheckInDate().isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("Check-in date must be today or in the future");
            }
            if (!booking.getCheckOutDate().isAfter(booking.getCheckInDate())) {
                throw new IllegalArgumentException("Check-out date must be after check-in date");
            }
            
            // Check room availability
            Long conflictingBookings = bookingRepository.countConflictingBookings(
                roomId, booking.getCheckInDate(), booking.getCheckOutDate());
            
            if (conflictingBookings > 0) {
                throw new IllegalStateException("Room not available for selected dates");
            }
            
            booking.setRoom(room);
            booking.setTotalAmount(booking.calculateTotalAmount());
            
            return bookingRepository.save(booking);
        }
        throw new IllegalArgumentException("Room not found or not available");
    }

    public Booking updateBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBookingStatus(Long id, String status) {
        Optional<Booking> bookingOpt = bookingRepository.findById(id);
        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            booking.setStatus(status);
            return bookingRepository.save(booking);
        }
        return null;
    }

    public void cancelBooking(Long id) {
        Optional<Booking> bookingOpt = bookingRepository.findById(id);
        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            booking.setStatus("CANCELLED");
            bookingRepository.save(booking);
        }
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}