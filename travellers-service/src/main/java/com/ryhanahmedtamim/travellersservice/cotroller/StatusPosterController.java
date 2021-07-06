package com.ryhanahmedtamim.travellersservice.cotroller;

import com.ryhanahmedtamim.travellersservice.model.Location;
import com.ryhanahmedtamim.travellersservice.model.Status;
import com.ryhanahmedtamim.travellersservice.service.LocationService;
import com.ryhanahmedtamim.travellersservice.service.StatusPosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StatusPosterController {

    @Autowired
    LocationService locationService;

    @Autowired
    StatusPosterService statusPosterService;

    @GetMapping("post-a-status")
    public String getPostForm(Model model){
        List<Location> locations = locationService.getAllLocations();
        Status status = new Status();
        model.addAttribute("status", status);
        model.addAttribute("locations", locations);
        return "/status-form";
    }

    @PostMapping("post-a-status")
    public String postStatus( Model model, Status status){
        String flag = statusPosterService.postStatus(status);
        return "redirect:/";
    }
}
