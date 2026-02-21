package com.venkat.residency.service;

import com.venkat.residency.entity.Room;
import com.venkat.residency.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    
    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public List<Room> getAvailableRooms() {
        return roomRepository.findByAvailableTrue();
    }

    public List<Room> getAvailableRoomsByDates(LocalDate checkIn, LocalDate checkOut) {
        return roomRepository.findAvailableRooms(checkIn, checkOut);
    }

    public List<Room> getRoomsByHostelType(String hostelType) {
        return roomRepository.findByHostelTypeAndAvailableTrue(hostelType);
    }

    public List<Room> getRoomsByRoomType(String roomType) {
        return roomRepository.findByRoomTypeAndAvailableTrue(roomType);
    }

    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    public Room updateRoomAvailability(Long id, Boolean available) {
        Optional<Room> roomOpt = roomRepository.findById(id);
        if (roomOpt.isPresent()) {
            Room room = roomOpt.get();
            room.setAvailable(available);
            return roomRepository.save(room);
        }
        return null;
    }
}