package com.venkat.residency.controller;

import com.venkat.residency.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @Autowired
    private RoomService roomService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("featuredRooms", roomService.getAvailableRooms().subList(0, 
            Math.min(3, roomService.getAvailableRooms().size())));
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/services")
    public String services() {
        return "services";
    }

    @GetMapping("/gallery")
    public String gallery() {
        return "gallery";
    }

    @GetMapping("/faq")
    public String faq() {
        return "faq";
    }
}