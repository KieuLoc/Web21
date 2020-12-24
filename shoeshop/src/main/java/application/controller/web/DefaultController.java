package application.controller.web;

import application.data.model.User;
import application.data.service.UserService;
import application.model.viewmodel.home.HomeLandingVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class DefaultController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

    @GetMapping(path="/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        HomeLandingVM vm = new HomeLandingVM();
        return "/register";
    }
    @GetMapping(path="/forgot")
    public String forgot(Model model) {
        model.addAttribute("user", new User());
        return "/forgot";
    }


    @RequestMapping(path="/register-user", method = RequestMethod.POST)
    public String registerNewUser(@Valid @ModelAttribute("user") User user){
        userService.registerNewUser(user);
        return "redirect:/login";
    }


}
