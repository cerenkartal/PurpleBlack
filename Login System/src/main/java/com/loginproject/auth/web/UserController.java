package com.loginproject.auth.web;

import com.loginproject.auth.model.User;
import com.loginproject.auth.service.SecurityService;
import com.loginproject.auth.service.UserService;
import com.loginproject.auth.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/index/" + userForm.getUsername();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "Logged out successfully.");
        
        return "login";
    }

    @RequestMapping(value="/index/{username}", method = RequestMethod.GET)
    public String index(@PathVariable("username") String username, Model model) {
    	User user = userService.findByUsername(username);
    	
    	model.addAttribute("name", user.getName());
    	model.addAttribute("surname", user.getSurname());
    	model.addAttribute("age", user.getAge() + "");
    	
        return "index";
    }
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {
    	User user = userService.findByUsername(request.getUserPrincipal().getName());
    	
    	model.addAttribute("name", user.getName());
    	model.addAttribute("surname", user.getSurname());
    	model.addAttribute("age", user.getAge() + "");
    	
        return "index";
    }
}
