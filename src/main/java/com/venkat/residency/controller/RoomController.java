package com.venkat.residency.controller;

import com.venkat.residency.entity.Room;
import com.venkat.residency.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    
    @Autowired
    private RoomService roomService;

    @GetMapping
    public String getAllRooms(@RequestParam(required = false) String type, Model model) {
        List<Room> rooms;
        if (type != null && !type.isEmpty()) {
            rooms = roomService.getRoomsByHostelType(type.toUpperCase());
            model.addAttribute("selectedType", type.toUpperCase());
        } else {
            rooms = roomService.getAllRooms();
        }
        model.addAttribute("rooms", rooms);
        return "rooms/list";
    }

    @GetMapping("/available")
    public String getAvailableRooms(Model model) {
        List<Room> rooms = roomService.getAvailableRooms();
        model.addAttribute("rooms", rooms);
        return "rooms/available";
    }

    @GetMapping("/search")
    public String searchRooms(@RequestParam(required = false) LocalDate checkIn,
                             @RequestParam(required = false) LocalDate checkOut,
                             @RequestParam(required = false) String hostelType,
                             Model model) {
        if (checkIn != null && checkOut != null) {
            List<Room> availableRooms = roomService.getAvailableRoomsByDates(checkIn, checkOut);
            if (hostelType != null && !hostelType.isEmpty()) {
                availableRooms = availableRooms.stream()
                    .filter(room -> room.getHostelType().equalsIgnoreCase(hostelType))
                    .toList();
            }
            model.addAttribute("rooms", availableRooms);
            model.addAttribute("checkIn", checkIn);
            model.addAttribute("checkOut", checkOut);
            model.addAttribute("selectedHostelType", hostelType);
        } else {
            model.addAttribute("rooms", roomService.getAvailableRooms());
        }
        return "rooms/search";
    }

    @GetMapping("/bachelor")
    public String getBachelorRooms(Model model) {
        List<Room> rooms = roomService.getRoomsByHostelType("BACHELOR");
        model.addAttribute("rooms", rooms);
        model.addAttribute("hostelType", "Bachelor Hostel");
        return "rooms/types/bachelor";
    }

    @GetMapping("/family")
    public String getFamilyRooms(Model model) {
        List<Room> rooms = roomService.getRoomsByHostelType("FAMILY");
        model.addAttribute("rooms", rooms);
        model.addAttribute("hostelType", "Family Hostel");
        return "rooms/types/family";
    }

    @GetMapping("/mixed")
    public String getMixedRooms(Model model) {
        List<Room> rooms = roomService.getRoomsByHostelType("MIXED");
        model.addAttribute("rooms", rooms);
        model.addAttribute("hostelType", "Mixed Hostel");
        return "rooms/types/mixed";
    }

    @GetMapping("/{id}")
    public String getRoomDetails(@PathVariable Long id, Model model) {
        Optional<Room> room = roomService.getRoomById(id);
        if (room.isPresent()) {
            model.addAttribute("room", room.get());
            return "rooms/details";
        }
        return "redirect:/rooms";
    }
}