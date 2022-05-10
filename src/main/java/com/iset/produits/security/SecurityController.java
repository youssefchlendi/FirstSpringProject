package com.iset.produits.security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.iset.produits.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecurityController {
    @Autowired
    public UserService userDetailsService;

    @GetMapping("/accessDenied")
    public String geterror() {
        return "accessDenied";
    }

    @PostMapping("/accessDenied")
    public String posterror() {
        return "accessDenied";
    }

    @GetMapping("/login")
    public String login(ModelMap modelMap, 
    @RequestParam(name = "error", defaultValue = "0") int error,
    @RequestParam(name = "logout", defaultValue = "0") int logout) {
        if (error == 1) {
            modelMap.addAttribute("error", "Veuillez vérifier vos identifiants");
            return "auth";
        }
        if (logout == 1) {
            modelMap.addAttribute("error", "Vous avez été déconnecté");
            return "auth";
        }
        return "auth";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        // redirect to login page
        return "redirect:/login";
    }

    @GetMapping("/loggedout")
    public String loggedout() {
        return "loggedout";
    }
}
