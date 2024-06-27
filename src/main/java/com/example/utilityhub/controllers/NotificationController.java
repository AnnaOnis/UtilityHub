package com.example.utilityhub.controllers;

import com.example.utilityhub.dao.NotificationService;
import com.example.utilityhub.dao.UserService;
import com.example.utilityhub.models.Notification;
import com.example.utilityhub.models.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final UserService userService;
    private  final NotificationService notificationService;
    @GetMapping
    public String getUsersNotification(Model model, Authentication auth){
        if(auth != null) {
            Optional<User> user = userService.findByUsername(auth.getName());
            if (user.isPresent()){
                Long userId = user.get().getId();

                List<Notification> notifications = notificationService.getNotificationsByUserId(userId);
                model.addAttribute("notifications", notifications);

                model.addAttribute("userId", userId);
            }
            return "pages/user/notifications";
        }
        return"redirect:/login";
    }

    @GetMapping("/read/{id}")
    public String readUsersNotification(@PathVariable Long id){

        Optional<Notification> optionalNot = notificationService.findById(id);
        if(optionalNot.isPresent()){
            Notification not = optionalNot.get();
            not.setIsRead(true);
            notificationService.update(not);
        }
        return "redirect:/notifications";
    }


    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteNotification(@PathVariable Long id,
                                     HttpServletRequest request){

        Optional<Notification> noti = notificationService.findById(id);
        if(noti.isPresent()){
            notificationService.delete(id);
        }

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
