package com.venkat.residency.controller;

import com.venkat.residency.dto.BookingRequest;
import com.venkat.residency.entity.Booking;
import com.venkat.residency.entity.Room;
import com.venkat.residency.service.BookingService;
import com.venkat.residency.service.EmailService;
import com.venkat.residency.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bookings")
public class BookingController {
    
    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private RoomService roomService;
    
    @Autowired
    private EmailService emailService;

    @GetMapping
    public String getAllBookings(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "bookings/list";
    }

    @GetMapping("/new")
    public String showBookingForm(@RequestParam Long roomId, Model model) {
        Optional<Room> room = roomService.getRoomById(roomId);
        if (room.isPresent()) {
            BookingRequest bookingRequest = new BookingRequest();
            bookingRequest.setRoomId(roomId);
            model.addAttribute("bookingRequest", bookingRequest);
            model.addAttribute("room", room.get());
            model.addAttribute("idProofTypes", List.of("AADHAR", "PASSPORT", "DRIVING_LICENSE", "VOTER_ID"));
            model.addAttribute("purposeTypes", List.of("BUSINESS", "VACATION", "EDUCATION", "OTHER"));
            return "bookings/form";
        }
        return "redirect:/rooms";
    }

    @PostMapping
    public String createBooking(@Valid @ModelAttribute BookingRequest bookingRequest, 
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            Optional<Room> room = roomService.getRoomById(bookingRequest.getRoomId());
            if (room.isPresent()) {
                model.addAttribute("room", room.get());
                model.addAttribute("idProofTypes", List.of("AADHAR", "PASSPORT", "DRIVING_LICENSE", "VOTER_ID"));
                model.addAttribute("purposeTypes", List.of("BUSINESS", "VACATION", "EDUCATION", "OTHER"));
            }
            return "bookings/form";
        }
        
        try {
            Booking booking = new Booking();
            booking.setGuestName(bookingRequest.getGuestName());
            booking.setGuestEmail(bookingRequest.getGuestEmail());
            booking.setGuestPhone(bookingRequest.getGuestPhone());
            booking.setIdProof(bookingRequest.getIdProof());
            booking.setIdProofNumber(bookingRequest.getIdProofNumber());
            booking.setCheckInDate(bookingRequest.getCheckInDate());
            booking.setCheckOutDate(bookingRequest.getCheckOutDate());
            booking.setNumberOfGuests(bookingRequest.getNumberOfGuests());
            booking.setSpecialRequests(bookingRequest.getSpecialRequests());
            booking.setEmergencyContact(bookingRequest.getEmergencyContact());
            booking.setEmergencyPhone(bookingRequest.getEmergencyPhone());
            booking.setPurposeOfStay(bookingRequest.getPurposeOfStay());
            
            Booking savedBooking = bookingService.createBooking(booking, bookingRequest.getRoomId());
            
            // Send confirmation email
            emailService.sendBookingConfirmation(
                savedBooking.getGuestEmail(),
                savedBooking.getGuestName(),
                savedBooking.getId().toString()
            );
            
            return "redirect:/payments/" + savedBooking.getId();
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            Optional<Room> room = roomService.getRoomById(bookingRequest.getRoomId());
            if (room.isPresent()) {
                model.addAttribute("room", room.get());
                model.addAttribute("idProofTypes", List.of("AADHAR", "PASSPORT", "DRIVING_LICENSE", "VOTER_ID"));
                model.addAttribute("purposeTypes", List.of("BUSINESS", "VACATION", "EDUCATION", "OTHER"));
            }
            return "bookings/form";
        }
    }

    @GetMapping("/success")
    public String bookingSuccess(@RequestParam Long bookingId, Model model) {
        Optional<Booking> booking = bookingService.getBookingById(bookingId);
        if (booking.isPresent()) {
            model.addAttribute("booking", booking.get());
            return "bookings/success";
        }
        return "redirect:/rooms";
    }

    @GetMapping("/{id}")
    public String getBookingDetails(@PathVariable Long id, Model model) {
        Optional<Booking> booking = bookingService.getBookingById(id);
        if (booking.isPresent()) {
            model.addAttribute("booking", booking.get());
            return "bookings/details";
        }
        return "redirect:/bookings";
    }

    @PostMapping("/{id}/cancel")
    public String cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return "redirect:/bookings";
    }

    @GetMapping("/search")
    public String searchBookings(@RequestParam String email, Model model) {
        List<Booking> bookings = bookingService.getBookingsByEmail(email);
        model.addAttribute("bookings", bookings);
        return "bookings/list";
    }
}