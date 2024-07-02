package com.example.utilityhub.controllers;

import com.example.utilityhub.services.interfaces.UtilityServiceService;
import com.example.utilityhub.models.entities.UtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/utility_services")
@RequiredArgsConstructor
public class UtilityServiceController {

    private final UtilityServiceService utilityServiceService;

    @GetMapping
    public String getUtilityServices(Model model,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "3") int size,
                                     @RequestParam(defaultValue = "id") String sortField,
                                     @RequestParam(defaultValue = "asc") String sortDir)
    {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<UtilityService> utilityServices = utilityServiceService.getAllUtilityServicesPageable(pageable);
        model.addAttribute("utilityServices", utilityServices);
        model.addAttribute("page", utilityServices.getNumber());
        model.addAttribute("totalPages", utilityServices.getTotalPages());
        model.addAttribute("size", utilityServices.getSize());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);

        UtilityService utilityService = new UtilityService(null, "", null, null);
        model.addAttribute("utilityService", utilityService);

        return "pages/admin/utilityServices";
    }

    @GetMapping("/{id}")
    public String getUtilityServiceById(Model model,
                                        @PathVariable Long id,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "3") int size,
                                        @RequestParam(defaultValue = "id") String sortField,
                                        @RequestParam(defaultValue = "asc") String sortDir)
    {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<UtilityService> utilityServices = utilityServiceService.getAllUtilityServicesPageable(pageable);
        model.addAttribute("utilityServices", utilityServices);
        model.addAttribute("page", utilityServices.getNumber());
        model.addAttribute("totalPages", utilityServices.getTotalPages());
        model.addAttribute("size",utilityServices.getSize());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);

        UtilityService utilityService = utilityServiceService.findById(id).get();
        model.addAttribute("utilityService", utilityService);

        return "pages/admin/utilityServices";
    }

    @PostMapping ("/delete/{id}")
    public String deleteUtilityService(@PathVariable Long id){
        utilityServiceService.delete(id);
        return "redirect:/admin/utility_services";
    }

    @PostMapping
    public String saveUtilityService(@ModelAttribute UtilityService utilityService){
        utilityServiceService.save(utilityService);
        return "redirect:/admin/utility_services";
    }

    @PostMapping("/{id}")
    public String updateUtilityService(@ModelAttribute UtilityService utilityService){
        utilityServiceService.update(utilityService);
        return "redirect:/admin/utility_services";
    }

}
