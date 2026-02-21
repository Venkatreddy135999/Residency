package com.venkat.residency.repository;

import com.venkat.residency.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByAvailableTrue();
    List<Room> findByHostelTypeAndAvailableTrue(String hostelType);
    List<Room> findByRoomTypeAndAvailableTrue(String roomType);
    
    @Query("SELECT r FROM Room r WHERE r.available = true AND r.id NOT IN " +
           "(SELECT b.room.id FROM Booking b WHERE " +
           "b.status IN ('CONFIRMED', 'CHECKED_IN') AND " +
           "((:checkInDate BETWEEN b.checkInDate AND b.checkOutDate) OR " +
           "(:checkOutDate BETWEEN b.checkInDate AND b.checkOutDate) OR " +
           "(b.checkInDate BETWEEN :checkInDate AND :checkOutDate)))")
    List<Room> findAvailableRooms(@Param("checkInDate") LocalDate checkInDate, 
                                 @Param("checkOutDate") LocalDate checkOutDate);
}