package com.example.utilityhub.controllers;

import com.example.utilityhub.dao.PaymentService;
import com.example.utilityhub.dao.UserService;
import com.example.utilityhub.dao.UtilityServiceService;
import com.example.utilityhub.dto.PaymentDTO;
import com.example.utilityhub.models.Notification;
import com.example.utilityhub.models.Payment;
import com.example.utilityhub.models.User;
import com.example.utilityhub.models.UtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final UserService userService;
    private final PaymentService paymentService;
    private final UtilityServiceService utilityServiceService;

    @GetMapping
    public String getUsersPayment(Model model, Authentication auth){
        if(auth != null) {
            Optional<User> user = userService.findByUsername(auth.getName());
            if (user.isPresent()){
                Long userId = user.get().getId();

                List<Payment> payments = paymentService.getPaymentsByUserId(userId);
                model.addAttribute("payments", payments);

                List<UtilityService> services = utilityServiceService.getAll();
                model.addAttribute("services", services);

                model.addAttribute("paymentDTO", new PaymentDTO());
                model.addAttribute("userId", userId);

                return "pages/user/payments";
            }
        }
        return"redirect:/login";
    }

    @PostMapping
    public String createNewPayment(@ModelAttribute PaymentDTO paymentDTO){
        LocalDate paymentDate = LocalDateTime.now().toLocalDate();
        UtilityService utilityService = utilityServiceService.findById(paymentDTO.getUtilityServiceId()).get();
        User user = userService.findById(paymentDTO.getUserId()).get();
        Payment newPayment = new Payment(paymentDate, paymentDTO.getAmount(), utilityService, user);

        user.getPayments().add(newPayment);
        userService.update(user);

        return "redirect:/payments";
    }
}
