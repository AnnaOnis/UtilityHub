package com.example.utilityhub.controllers;

import com.example.utilityhub.dao.UserService;
import com.example.utilityhub.dto.UserRegisterDTO;
import com.example.utilityhub.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@ControllerAdvice
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegisterDTO());
        return "register";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("user") UserRegisterDTO userDTO, Model model) {
        if (!userDTO.getPassword().equals(userDTO.getPassRepeat())) {
            model.addAttribute("error", true);
            model.addAttribute("type", "password");
            return "register";
        }

        if (userService.findByUsername(userDTO.getUsername()).isPresent()) {
            model.addAttribute("error", true);
            model.addAttribute("type", "username");
            return "register";
        }

        if (userService.findByEmail(userDTO.getEmail()).isPresent()) {
            model.addAttribute("error", true);
            model.addAttribute("type", "email");
            return "register";
        }

        User newUser = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
        User registeredUser = userService.save(newUser);

        if(registeredUser == null){
            model.addAttribute("error", true);
            model.addAttribute("type", "unknown");
            return "register";
        }

        model.addAttribute("error", false);
        return "register";
    }
}
