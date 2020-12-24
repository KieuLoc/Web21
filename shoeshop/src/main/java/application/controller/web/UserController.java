package application.controller.web;


import application.data.model.User;
import application.data.service.UserService;
import application.model.viewmodel.common.HeaderMenuVM;
import application.model.viewmodel.common.LayoutHeaderVM;
import application.model.viewmodel.common.ProductVM;
import application.model.viewmodel.home.HomeLandingVM;
import application.model.viewmodel.order.CheckoutVM;
import application.model.viewmodel.order.OrderVM;
import application.model.viewmodel.user.ChangePasswordVM;
import application.model.viewmodel.user.UserDetailVM;
import application.model.viewmodel.user.UserVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path ="/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/detail")
    public String getDetail(Model model,
                            @Valid @ModelAttribute("productname")ProductVM productName){

        UserDetailVM vm=new UserDetailVM();

        UserVM userVM=new UserVM();

        String username= SecurityContextHolder.getContext().getAuthentication().getName();

        User userEntity =userService.findUserByUsername(username);

        if (userEntity != null) {
            userVM.setAddress(userEntity.getAddress());
            userVM.setEmail(userEntity.getEmail());
            userVM.setAvatar(userEntity.getAvatar());
            userVM.setGender(userEntity.getGender());
            userVM.setPhoneNumber(userEntity.getPhoneNumber());
            userVM.setName(userEntity.getName());
        }

        vm.setLayoutHeaderVM(this.getLayoutHeaderVM());

        model.addAttribute("user",userVM);
        model.addAttribute("vm",vm);
        return "/userdetail";

    }
    @GetMapping("/change-password")
    public String changePassword(@Valid @ModelAttribute("productname")ProductVM productName,Model model){
        ChangePasswordVM changePasswordVM=new ChangePasswordVM();

        changePasswordVM.setLayoutHeaderVM(this.getLayoutHeaderVM());

        model.addAttribute("changePassword",changePasswordVM);
        return "/change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(@Valid @ModelAttribute("changePassword")ChangePasswordVM password,Model model){

        String userName=SecurityContextHolder.getContext().getAuthentication().getName();
        User userEntity=userService.findUserByUsername(userName);

        if(password.getCurrentPassword() == null ||
                password.getNewPassword() == null ||
                password.getConfirmPassword() == null||
                ! passwordEncoder.matches(password.getCurrentPassword(),userEntity.getPasswordHash())||
                ! password.getNewPassword().equals(password.getConfirmPassword())){
            return "redirect:/user/change-password?fail";
        }
        userEntity.setPassword(password.getNewPassword());
        userEntity.setPasswordHash(passwordEncoder.encode(userEntity.getPassword()));
        userService.updateUser(userEntity);

        return "redirect:/";
    }
    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("user") UserVM userVM){
        try{
            String username=SecurityContextHolder.getContext().getAuthentication().getName();
            User users = userService.findUserByUsername(username);
            users.setAvatar(userVM.getAvatar());
            users.setAddress(userVM.getAddress());
            users.setName(userVM.getName());
            users.setEmail(userVM.getEmail());
            users.setGender(userVM.getGender());
            users.setPhoneNumber(userVM.getPhoneNumber());

            userService.updateUser(users);
            System.out.println("update Success");
            return "redirect:/user/detail?updateSuccess";
        }catch(Exception e){
            System.out.println("update Fail!!!");
        }

        return "redirect:/user/detail?updateFalse";
    }
}
