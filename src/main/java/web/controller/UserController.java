package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUser(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "user")
    public String allUsers(ModelMap model) {
        model.addAttribute("user", userService.getAllUsers());
        return "user";
    }

    @GetMapping(value = "user/add")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "addUser";
    }

    @PostMapping(value = "user/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/user";
    }

    @GetMapping(value = "user/edit/{id}")
    public String editUser(ModelMap model, @PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping(value = "user/edit")
    public String edit(@ModelAttribute("user") User user) {
        userService.editUser(user);
        return "redirect:/user";
    }

    @GetMapping("user/delete")
    public String deleteUserById(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/user";
    }

    @GetMapping("user/{id}")
    public String showUser(@PathVariable("id") Long id, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.getUserById(id));
        return "showUser";
    }
}
