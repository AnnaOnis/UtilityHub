package com.example.utilityhub.controllers;

import com.example.utilityhub.dao.NotificationService;
import com.example.utilityhub.dao.PaymentService;
import com.example.utilityhub.dao.RequestService;
import com.example.utilityhub.dao.UserService;
import com.example.utilityhub.models.Notification;
import com.example.utilityhub.models.Payment;
import com.example.utilityhub.models.Request;
import com.example.utilityhub.models.User;
import com.example.utilityhub.models.enums.PaymentStatus;
import com.example.utilityhub.models.enums.RequestStatus;
import com.example.utilityhub.models.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final RequestService requestService;
    private final PaymentService paymentService;
    private final NotificationService notificationService;

    @GetMapping("/dashboard")
    public String showAdminDashboard() {
        return "pages/admin/admin-dashboard";
    }

//User`s requests
    @GetMapping("/users")
    public String getUsers(Model model,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size,
                           @RequestParam(defaultValue = "id") String sortField,
                           @RequestParam(defaultValue = "asc") String sortDir)
    {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<User> users = userService.getAllUsersPageable(pageable);
        model.addAttribute("users", users);
        model.addAttribute("page", users.getNumber());
        model.addAttribute("totalPages", users.getTotalPages());
        model.addAttribute("size", users.getSize());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        return "pages/admin/users";
    }

    @PostMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }

//Request`s requests
    @GetMapping("/requests")
    public String getRequests(Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(defaultValue = "id") String sortField,
                              @RequestParam(defaultValue = "asc") String sortDir)
    {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Request> requests = requestService.getAllRequestsPageable(pageable);
        model.addAttribute("requests", requests);
        model.addAttribute("page", requests.getNumber());
        model.addAttribute("totalPages", requests.getTotalPages());
        model.addAttribute("size", requests.getSize());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);

        return "pages/admin/requests";
    }

    @PostMapping("/update-request-status/{id}")
    public String updateRequestStatus(@PathVariable Long id, @RequestParam String status) {
        Optional<Request> optionalRequest = requestService.findById(id);
        if (optionalRequest.isPresent()) {
            Request request = optionalRequest.get();
            request.setStatus(RequestStatus.valueOf(status));
            requestService.update(request);
        }
        return "redirect:/admin/requests";
    }

//Payment`s requests

    @GetMapping("/payments")
    public String getPayments(Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(defaultValue = "id") String sortField,
                              @RequestParam(defaultValue = "asc") String sortDir)
    {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Payment> payments = paymentService.getAllPaymentsPageable(pageable);
        model.addAttribute("payments", payments);
        model.addAttribute("page", payments.getNumber());
        model.addAttribute("totalPages", payments.getTotalPages());
        model.addAttribute("size", payments.getSize());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);

        return "pages/admin/payments";
    }

    @PostMapping("/update-payment-status/{id}")
    public String updatePaymentStatus(@PathVariable Long id, @RequestParam String status) {
        Optional<Payment> optionalPayment = paymentService.findById(id);
        if (optionalPayment != null) {
            Payment payment = optionalPayment.get();
            payment.setStatus(PaymentStatus.valueOf(status));
            paymentService.update(payment);
        }
        return "redirect:/admin/payments";
    }

//Notification`s requests
    @GetMapping("/notifications")
    public String getNotifications(Model model,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String sortField,
                                   @RequestParam(defaultValue = "asc") String sortDir)
    {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Notification> notifications = notificationService.getAllNotificationsPageable(pageable);
        model.addAttribute("notifications", notifications);
        model.addAttribute("page", notifications.getNumber());
        model.addAttribute("totalPages", notifications.getTotalPages());
        model.addAttribute("size", notifications.getSize());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);

        return "pages/admin/notifications";
    }
    @PostMapping("/send-notification")
    public String sendNotification(@RequestParam(required = false) Long userId, @RequestParam String message) {

        if (userId == null) {
            // Отправка уведомления всем пользователям
            List<User> users = userService.getAll();
            for (User user : users) {
                if(user.getRole() == Role.USER){
                    user.addNotification(message);
                    userService.update(user);
                }
            }
        } else {
            // Отправка уведомления конкретному пользователю
            Optional<User> userOpt = userService.findById(userId);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                user.addNotification(message);
                userService.update(user);
            }
        }
        return "redirect:/admin/dashboard";
    }

//    private void addNotificationToUser(User user, String message){
//        Notification notification = new Notification();
//        notification.setUser(user);
//        notification.setMessage(message);
//        notification.setIsRead(false);
//
//        user.getNotifications().add(notification);
//        userService.update(user);
//    }
}
