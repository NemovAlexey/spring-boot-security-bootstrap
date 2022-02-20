package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String index(Model model, Principal principal) {
        User loggedUser = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", roleService.findAll());
        return "admin";
    }

    @PostMapping()
    public String saveUser(User user, @RequestParam("roleNames") String[] roleNames) {
        userService.save(user, roleNames);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String updateUser(User user, @RequestParam("roleNames") String[] roleNames) {
        userService.save(user, roleNames);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
