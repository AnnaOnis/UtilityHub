package com.example.utilityhub.controllers;

import com.example.utilityhub.dao.RequestService;
import com.example.utilityhub.dao.UserService;
import com.example.utilityhub.dto.RequestDTO;
import com.example.utilityhub.models.Request;
import com.example.utilityhub.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestController {
    private final UserService userService;
    private final RequestService requestService;

    @GetMapping
    public String getUsersRequests(Model model, Authentication auth){
        if(auth != null) {

            Optional<User> user = userService.findByUsername(auth.getName());
            if (user.isPresent()){
                Long userId = user.get().getId();

                List<Request> requests = requestService.getRequestsByUserId(userId);
                model.addAttribute("requests", requests);
                model.addAttribute("userId", userId);
                model.addAttribute("newRequest", new RequestDTO());

                List<String> requestTypes = new ArrayList<>(3);
                requestTypes.add("MAINTENANCE");
                requestTypes.add("COMPLAINT");
                requestTypes.add("OTHER");
                model.addAttribute("requestTypes", requestTypes);

                return "pages/user/requests";
            }
        }
        return"redirect:/login";
    }

    @PostMapping("/add")
    public String addNewRequest(@ModelAttribute RequestDTO requestDTO, @RequestParam Long userId){
        Request newRequest = new Request(requestDTO.getTitle(), requestDTO.getDescription(), requestDTO.getType());
        Optional<User> optionalUser = userService.findById(userId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            newRequest.setUser(user);
            user.getRequests().add(newRequest);
            userService.update(user);
        }

        return "redirect:/requests";
    }
}
