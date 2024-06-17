package com.example.utilityhub.controllers;



import com.example.utilityhub.dao.UserService;
import com.example.utilityhub.dto.UserUpdateDTO;
import com.example.utilityhub.models.Payment;
import com.example.utilityhub.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/dashboard")
    public String getUserDashboard(Model model, Authentication auth) {

        if(auth != null) {
            Optional<User> user = userService.findByUsername(auth.getName());
            if (user.isPresent()) {
                model.addAttribute("user", new UserUpdateDTO(user.get().getUsername(),
                        user.get().getFullName(),
                        user.get().getEmail(),
                        user.get().getPassword()));
                model.addAttribute("userId", user.get().getId());
//            List<Payment> payments = paymentService.getAllPaymentsByUserId(userId);
//            model.addAttribute("payments", payments);
//
//            List<Request> requests = requestService.getAllRequestsByUserId(userId);
//            model.addAttribute("requests", requests);
//
//            List<Notification> notifications = notificationService.getAllNotificationsByUserId(userId);
//            model.addAttribute("notifications", notifications);

                return "user-dashboard";  // Личный кабинет пользователя
            } else {
                return "redirect:/login";  // Если пользователь не найден, перенаправляем на страницу входа
            }
        } else {
            return "redirect:/login";  // Если пользователь не найден, перенаправляем на страницу входа
        }
    }

    @PostMapping("/update/{id}")
    public  String updateUserInformation(@PathVariable Long id, @ModelAttribute UserUpdateDTO userUpdateDTO){
        Optional<User> existingUser = userService.findById(id);
        if (existingUser.isPresent()) {
            User updateUser = existingUser.get();
            updateUser.setUsername(userUpdateDTO.getUsername());
            updateUser.setFullName(userUpdateDTO.getFullName());
            updateUser.setEmail(userUpdateDTO.getEmail());
            if (!userUpdateDTO.getPassword().isEmpty()) {
                updateUser.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
            }

            userService.update(updateUser);
            return "redirect:/users/dashboard?userId=" + id;
        } else {
            return "redirect:/login";
        }
    }

}
