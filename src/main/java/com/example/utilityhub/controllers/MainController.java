package com.example.utilityhub.controllers;

import com.example.utilityhub.dao.UserService;
import com.example.utilityhub.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping
    public String getHomePageWithAuth(Model model, Authentication auth) {
        model.addAttribute("home", true);

        if (auth != null) {
            Optional<User> user = userService.findByUsername(auth.getName());
            if(user.isPresent()){
                Long userId = user.get().getId();
                model.addAttribute("userId", userId);
                return "pages/home";
            }
        }
        return "pages/home";
    }
}
