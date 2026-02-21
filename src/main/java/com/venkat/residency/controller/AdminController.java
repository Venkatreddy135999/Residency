package com.venkat.residency.controller;

import com.venkat.residency.dto.RoomForm;
import com.venkat.residency.entity.Room;
import com.venkat.residency.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Room> allRooms = roomService.getAllRooms();
        long availableRooms = allRooms.stream().filter(r -> Boolean.TRUE.equals(r.getAvailable())).count();

        model.addAttribute("totalRooms", allRooms.size());
        model.addAttribute("availableRooms", availableRooms);
        // For now, keep bookings stats as 0; can be wired later
        model.addAttribute("totalBookings", 0);
        model.addAttribute("pendingBookings", 0);

        return "admin/dashboard";
    }

    @GetMapping("/rooms")
    public String manageRooms(Model model) {
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);
        return "admin/rooms";
    }

    @GetMapping("/rooms/new")
    public String showCreateRoomForm(Model model) {
        RoomForm form = new RoomForm();
        model.addAttribute("roomForm", form);
        model.addAttribute("roomTypes", List.of("SINGLE", "DOUBLE", "FAMILY", "DORM"));
        model.addAttribute("hostelTypes", List.of("BACHELOR", "FAMILY", "MIXED"));
        return "admin/room-form";
    }

    @PostMapping("/rooms")
    public String createRoom(@Valid @ModelAttribute("roomForm") RoomForm form,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("roomTypes", List.of("SINGLE", "DOUBLE", "FAMILY", "DORM"));
            model.addAttribute("hostelTypes", List.of("BACHELOR", "FAMILY", "MIXED"));
            return "admin/room-form";
        }
        Room room = new Room();
        applyFormToRoom(form, room);
        roomService.saveRoom(room);
        return "redirect:/admin/rooms";
    }

    @GetMapping("/rooms/edit/{id}")
    public String showEditRoomForm(@PathVariable Long id, Model model) {
        Optional<Room> roomOpt = roomService.getRoomById(id);
        if (roomOpt.isEmpty()) {
            return "redirect:/admin/rooms";
        }
        Room room = roomOpt.get();
        RoomForm form = new RoomForm();
        form.setId(room.getId());
        form.setRoomNumber(room.getRoomNumber());
        form.setRoomType(room.getRoomType());
        form.setHostelType(room.getHostelType());
        form.setPricePerNight(room.getPricePerNight());
        form.setDescription(room.getDescription());
        form.setCapacity(room.getCapacity());
        form.setAvailable(room.getAvailable());
        form.setAttachedBathroom(room.getAttachedBathroom());
        form.setAcAvailable(room.getAcAvailable());
        form.setWifiAvailable(room.getWifiAvailable());
        form.setMealsIncluded(room.getMealsIncluded());
        form.setLaundryService(room.getLaundryService());
        form.setSecurityDeposit(room.getSecurityDeposit());
        form.setDepositAmount(room.getDepositAmount());

        model.addAttribute("roomForm", form);
        model.addAttribute("roomTypes", List.of("SINGLE", "DOUBLE", "FAMILY", "DORM"));
        model.addAttribute("hostelTypes", List.of("BACHELOR", "FAMILY", "MIXED"));
        return "admin/room-form";
    }

    @PostMapping("/rooms/{id}")
    public String updateRoom(@PathVariable Long id,
                             @Valid @ModelAttribute("roomForm") RoomForm form,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("roomTypes", List.of("SINGLE", "DOUBLE", "FAMILY", "DORM"));
            model.addAttribute("hostelTypes", List.of("BACHELOR", "FAMILY", "MIXED"));
            return "admin/room-form";
        }
        Optional<Room> roomOpt = roomService.getRoomById(id);
        if (roomOpt.isEmpty()) {
            return "redirect:/admin/rooms";
        }
        Room room = roomOpt.get();
        applyFormToRoom(form, room);
        roomService.saveRoom(room);
        return "redirect:/admin/rooms";
    }

    @PostMapping("/rooms/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return "redirect:/admin/rooms";
    }

    private void applyFormToRoom(RoomForm form, Room room) {
        room.setRoomNumber(form.getRoomNumber());
        room.setRoomType(form.getRoomType());
        room.setHostelType(form.getHostelType());
        room.setPricePerNight(form.getPricePerNight());
        room.setDescription(form.getDescription());
        room.setCapacity(form.getCapacity());
        room.setAvailable(form.getAvailable());
        room.setAttachedBathroom(form.getAttachedBathroom());
        room.setAcAvailable(form.getAcAvailable());
        room.setWifiAvailable(form.getWifiAvailable());
        room.setMealsIncluded(form.getMealsIncluded());
        room.setLaundryService(form.getLaundryService());
        room.setSecurityDeposit(form.getSecurityDeposit());
        room.setDepositAmount(form.getDepositAmount());
    }

    @GetMapping("/bookings")
    public String manageBookings(Model model) {
        return "admin/bookings";
    }
}