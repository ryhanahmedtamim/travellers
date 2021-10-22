package com.ryhanahmedtamim.travellersservice.cotroller;

import com.ryhanahmedtamim.travellersservice.model.Location;
import com.ryhanahmedtamim.travellersservice.model.Status;
import com.ryhanahmedtamim.travellersservice.service.LocationService;
import com.ryhanahmedtamim.travellersservice.service.StatusPosterService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class StatusPosterController {

    LocationService locationService;
    StatusPosterService statusPosterService;

    public StatusPosterController(LocationService locationService, StatusPosterService statusPosterService){
        this.locationService = locationService;
        this.statusPosterService = statusPosterService;
    }


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

        if(status.getPrivacy() == 0){
            return "redirect:/my-status";
        }
        return "redirect:/";
    }

    @GetMapping("my-status")
    public String getAllMyPost(Model model){
        List<Status> statusList = statusPosterService.getAllStatusByUserId();
        model.addAttribute("statusList",statusList);
        List<Location> locations = locationService.getAllLocations();
        Status status = new Status();
        model.addAttribute("status", status);
        model.addAttribute("locations", locations);
        return "my-status";
    }

    @PostMapping("update-a-status")
    public String updateMyPost(Model model, Status status){
        String flag = statusPosterService.editStatus(status);
        log.debug(flag);
        return "redirect:/my-status";
    }
}
