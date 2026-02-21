package com.venkat.residency.repository;

import com.venkat.residency.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByGuestEmail(String guestEmail);
    List<Booking> findByStatus(String status);
    
    @Query("SELECT b FROM Booking b WHERE b.checkInDate <= :date AND b.checkOutDate >= :date AND b.status = 'CHECKED_IN'")
    List<Booking> findActiveBookingsOnDate(@Param("date") LocalDate date);
    
    List<Booking> findByCheckInDateBetween(LocalDate start, LocalDate end);
    
    // FIXED: Corrected the SQL query syntax
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.room.id = :roomId " +
           "AND b.status IN ('CONFIRMED', 'CHECKED_IN') " +
           "AND ((:checkInDate BETWEEN b.checkInDate AND b.checkOutDate) " +
           "OR (:checkOutDate BETWEEN b.checkInDate AND b.checkOutDate) " +
           "OR (b.checkInDate BETWEEN :checkInDate AND :checkOutDate))")
    Long countConflictingBookings(@Param("roomId") Long roomId, 
                                 @Param("checkInDate") LocalDate checkInDate, 
                                 @Param("checkOutDate") LocalDate checkOutDate);
}