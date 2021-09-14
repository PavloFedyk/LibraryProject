package com.library.controller;

import com.library.entity.RentInfo;
import com.library.entity.RentStatus;
import com.library.entity.User;
import com.library.service.rent_info.RentInfoDeleterService;
import com.library.service.rent_info.RentInfoRetrieverService;
import com.library.service.rent_info.RentInfoSaverService;
import com.library.service.role.RoleRetrieverService;
import com.library.service.user.UserDeleterService;
import com.library.service.user.UserRetrieverService;
import com.library.service.user.UserSaverService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserRetrieverService userRetrieverService;
    private final UserSaverService userSaverService;
    private final UserDeleterService userDeleterService;
    private final RoleRetrieverService roleRetrieverService;
    private final RentInfoSaverService rentInfoSaverService;
    private final RentInfoDeleterService rentInfoDeleterService;
    private final RentInfoRetrieverService rentInfoRetrieverService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/create")
    public String create(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-form";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("user") User User, BindingResult result) {
        if (result.hasErrors()) return "user-form";

        User.setPassword(passwordEncoder.encode(User.getPassword()));
        User.setRole(roleRetrieverService.findById(2L));
        User.setCreationDate(LocalDateTime.now(ZoneId.of("Europe/Kiev")));
        userSaverService.save(User);
        return "redirect:/user/" + User.getId();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER') or hasAuthority('READER') and authentication.principal.id == #id")
    @GetMapping("/{id}")
    public String read(@PathVariable Long id, Model model) {
        User user = userRetrieverService.findById(id);
        Integer amountReadBooks = userRetrieverService.amountReadBooks(id);
        Optional<Double> averageReadingTimeReturnedBooks = userRetrieverService.readingTimeOfBooks(RentStatus.RETURNED, id);
        Optional<Double> averageReadingTimeCurrentBooks = userRetrieverService.readingTimeOfBooks(RentStatus.ACTIVE, id);
        Integer daysClient = userRetrieverService.daysOurClient(id);
        List<RentInfo> rentInfos = rentInfoRetrieverService.findByUserId(id);

        averageReadingTimeCurrentBooks
                .ifPresent(t -> model.addAttribute("averageReadingTimeCurrentBooks", t));
        averageReadingTimeReturnedBooks
                .ifPresent(t -> model.addAttribute("averageReadingTimeReturnedBooks", t));

        model.addAttribute("user", user);
        model.addAttribute("amountReadBooks", amountReadBooks);
        model.addAttribute("daysClient", daysClient);
        model.addAttribute("rentInfos", rentInfos);
        return "user-info";
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAnyAuthority('READER', 'MANAGER') and authentication.principal.id == #id")
    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        User user = userRetrieverService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleRetrieverService.findAll());
        return "user-update";
    }

    @PostMapping("/update")
    public String update(@Validated @ModelAttribute("user") User user,
                         BindingResult result, Model model,
                         @RequestParam("oldPassword") String oldPassword,
                         @RequestParam(value = "roleId", required = false, defaultValue = "1") long roleId) {
        User oldUser = userRetrieverService.findById(user.getId());
        if (result.hasErrors()) {
            user.setRole(oldUser.getRole());
            model.addAttribute("roles", roleRetrieverService.findAll());
            return "user-update";
        }
        if (!passwordEncoder.matches(oldPassword, oldUser.getPassword())) {
            result.addError(new FieldError("user", "password", "Invalid old password"));
            user.setRole(oldUser.getRole());
            model.addAttribute("roles", roleRetrieverService.findAll());
            return "user-update";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreationDate(oldUser.getCreationDate());
        user.setRole(roleRetrieverService.findById(roleId));
        userSaverService.save(user);
        return "redirect:/user/";
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAnyAuthority('READER', 'MANAGER') and authentication.principal.id == #id")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        if (Objects.equals(user.getId(), id)) {
            userDeleterService.remove(id);
            SecurityContextHolder.clearContext();
            return "redirect:/login";
        }
        userDeleterService.remove(id);
        return "redirect:/user";
    }

    @GetMapping("/cancel/{id}")
    public String cancel(@PathVariable Long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        RentInfo rentInfo = rentInfoRetrieverService.findById(id);
        rentInfo.setRentStatus(RentStatus.REJECTED);
        rentInfoRetrieverService.update(rentInfo);
        return "redirect:/user/" + user.getId();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("/debtor")
    public String getAllWithExpiredStatus(Model model) {
        List<User> users = userRetrieverService.findAllWithExpiredStatus();
        model.addAttribute("users", users);
        return "debtor-list";
    }

    @GetMapping("/debtor/update")
    public String updateDebtors() {
        rentInfoRetrieverService.updateDebtors();
        return "redirect:/user/debtor";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping
    public String getAll(Model model) {
        List<User> users = userRetrieverService.findAll();
        Integer averageAge = userRetrieverService.averageAge();
        Integer averageTimeWorkingWithLibrary = userRetrieverService.averageTimeWorkingWithLibrary();

        model.addAttribute("users", users);
        model.addAttribute("avgAge", averageAge);
        model.addAttribute("avgTime", averageTimeWorkingWithLibrary);
        return "user-list";
    }
}


