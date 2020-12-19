package application.controller.web;


import application.data.service.UserService;
import application.model.viewmodel.common.ProductVM;
import application.model.viewmodel.user.UserDetailVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping(path ="/user-detail")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String cart(Model model,
                       @Valid @ModelAttribute("username") UserDetailVM userName,
                       HttpServletResponse response,
                       HttpServletRequest request,
                       final Principal principal){

        return "user-detail";
    }
}
