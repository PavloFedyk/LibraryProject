package com.library.controller;

import com.library.entity.RentInfo;
import com.library.entity.RentStatus;
import com.library.entity.User;
import com.library.service.book.BookRetrieverService;
import com.library.service.rent_info.RentInfoDeleterService;
import com.library.service.rent_info.RentInfoRetrieverService;
import com.library.service.rent_info.RentInfoSaverService;
import com.library.service.user.UserRetrieverService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;


@Controller
@RequestMapping("/rent-info")
@RequiredArgsConstructor
public class RentInfoController {

    private final BookRetrieverService bookRetrieverService;
    private final RentInfoRetrieverService rentInfoRetrieverService;
    private final RentInfoDeleterService rentInfoDeleterService;
    private final RentInfoSaverService rentInfoSaverService;
    private final UserRetrieverService userRetrieverService;


    @GetMapping("/request/{id}/{user_id}")
    public String create(@PathVariable Long id, @PathVariable("user_id") Long userId) {
        RentInfo rentInfo = new RentInfo();

        rentInfo.setUser(userRetrieverService.findById(userId));
        rentInfo.setBook(bookRetrieverService.findById(id));
        rentInfo.setRentStatus(RentStatus.ASKED);
        rentInfo.setRentDate(LocalDateTime.now(ZoneId.of("Europe/Kiev")));
        rentInfo.setRequiredReturnDate(LocalDateTime.now(ZoneId.of("Europe/Kiev")).plusDays(30));
        rentInfoSaverService.save(rentInfo);
        return "redirect:/";
    }

    @GetMapping("/return/{id}")
    public String returnBook(@PathVariable Long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        RentInfo rentInfo = rentInfoRetrieverService.findById(id);
        rentInfo.setReturnDate(LocalDateTime.now(ZoneId.of("Europe/Kiev")));
        rentInfo.setRentStatus(RentStatus.RETURNED);
        rentInfoRetrieverService.update(rentInfo);
        return "redirect:/user/" + user.getId();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("/give/{id}")
    public String giv(@PathVariable Long id) {
        RentInfo rentInfo = rentInfoRetrieverService.findById(id);
        rentInfoRetrieverService.update(rentInfo);
        return "redirect:/rent-info";
    }
    @GetMapping("/cancel/{id}")
    public String cancel(@PathVariable Long id){
        RentInfo rentInfo = rentInfoRetrieverService.findById(id);
        rentInfoRetrieverService.update(rentInfo);
        return "redirect:/rent-info";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("/status/{rent-status}")
    public String getAllByRentStatus(@PathVariable(name = "rent-status") String rentStatus, Model model) {
        List<RentInfo> rentInfoList =
                rentInfoRetrieverService.findByRentStatus(RentStatus.valueOf(RentStatus.class, rentStatus.toUpperCase()));
        model.addAttribute("rentInfoList", rentInfoList);
        return "rent-list";
    }


    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        rentInfoDeleterService.remove(id);
        return "redirect:/rent-info";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping
    public String getAll(Model model){
        List<RentInfo> rentInfos = rentInfoRetrieverService.findAll();
        model.addAttribute("rentInfos", rentInfos);
        return "rent-list";
    }


}
