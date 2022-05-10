package com.iset.produits.security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecurityController {
    @GetMapping("/accessDenied")
    public String geterror() {
        return "accessDenied";
    }

    @PostMapping("/accessDenied")
    public String posterror() {
        return "accessDenied";
    }

    @GetMapping("/login")
    public String login(ModelMap modelMap,@RequestParam(name = "error", defaultValue = "0") int error) {
        if (error == 1) {
            modelMap.addAttribute("error", "Veuillez vérifier vos identifiants");
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
