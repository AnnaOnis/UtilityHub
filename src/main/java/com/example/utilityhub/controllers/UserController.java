package com.example.utilityhub.controllers;



import com.example.utilityhub.dao.NotificationService;
import com.example.utilityhub.dao.PaymentService;
import com.example.utilityhub.dao.RequestService;
import com.example.utilityhub.dao.UserService;
import com.example.utilityhub.dto.UserUpdateDTO;
import com.example.utilityhub.models.Notification;
import com.example.utilityhub.models.Payment;
import com.example.utilityhub.models.Request;
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
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RequestService requestService;
    private final NotificationService notificationService;
    private final PasswordEncoder passwordEncoder;
    private final PaymentService paymentService;

    @GetMapping("/dashboard")
    public String getUserDashboard(Model model, Authentication auth) {

        if(auth != null) {
            model.addAttribute("lk", true);
            Optional<User> user = userService.findByUsername(auth.getName());
            if (user.isPresent()) {
                model.addAttribute("user", new UserUpdateDTO(
                        user.get().getUsername(),
                        user.get().getPassword(),
                        user.get().getFullName(),
                        user.get().getEmail(),
                        user.get().getAddress()));
                model.addAttribute("userId", user.get().getId());

            List<Payment> payments = paymentService.getPaymentsByUserId(user.get().getId());
            model.addAttribute("payments", payments);

            List<Request> requests = requestService.getRequestsByUserId(user.get().getId());
            model.addAttribute("requests", requests);

            List<Notification> notifications = notificationService.getNotificationsByUserId(user.get().getId());
            model.addAttribute("notifications", notifications);

                return "pages/user/user-dashboard";
            }
        }

        return "redirect:/login";
    }

    @PostMapping("/update/{id}")
    public  String updateUserInformation(Model model, @PathVariable Long id, @ModelAttribute UserUpdateDTO userUpdateDTO){
        model.addAttribute("lk", true);
        Optional<User> existingUser = userService.findById(id);
        if (existingUser.isPresent()) {
            User updateUser = existingUser.get();
            updateUser.setUsername(userUpdateDTO.getUsername());
            updateUser.setFullName(userUpdateDTO.getFullName());
            updateUser.setEmail(userUpdateDTO.getEmail());
            updateUser.setAddress(userUpdateDTO.getAddress());
            if (!userUpdateDTO.getPassword().isEmpty()) {
                updateUser.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
            }

            userService.update(updateUser);
            return "redirect:/user/dashboard";
        } else {
            return "redirect:/login";
        }
    }

}
