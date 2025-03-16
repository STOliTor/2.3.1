package ru.repository.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.repository.dao.UserDao;
import ru.repository.model.User;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping(value = "/users")
    public String viewAllUsers(Model model) {
        List<User> users = userDao.viewAll();
        for (User user : users) {
            System.out.println(user.toString());
        }
        model.addAttribute("users",users);
    return "users";
    }

    @GetMapping("/new")
    public String newOrEditForm(Model model) {
        model.addAttribute("user", new User());
        return "userForm";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        userDao.save(user);
        System.out.println(user.toString());
        return "redirect:/users/users";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        User user = userDao.findById(id);
        model.addAttribute("user", user);
        return "userForm";
    }


    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        userDao.redact(user);
        return "redirect:/users/users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userDao.delete(id);
        return "redirect:/users/users";
    }
}
