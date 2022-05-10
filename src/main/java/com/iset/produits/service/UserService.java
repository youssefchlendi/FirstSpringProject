package com.iset.produits.service;

import java.util.Objects;
import java.util.Set;

import javax.validation.Valid;

import java.util.HashSet;

import com.iset.produits.dao.RoleRepository;
import com.iset.produits.dao.UserRepository;
import com.iset.produits.entities.Role;
import com.iset.produits.entities.User;
import com.iset.produits.entities.UserForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import groovy.util.logging.Slf4j;

@Controller
@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Objects.requireNonNull(username);
        User user = userRepository.findUserWithName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }

    @GetMapping("/register")
    public String showRegister(ModelMap modelMap) {
        modelMap.addAttribute("userForm", new UserForm());
        return "register";
    }
    @PostMapping("/register")
    public String register(@Valid UserForm userForm, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            System.out.println(userForm.toString());
            modelMap.addAttribute("userForm", userForm);
            return "register";
        }
        this.saveUser(userForm.getUsername(), userForm.getPassword(), userForm.getConfirmedPassword());
        return "redirect:/login";
    }


    public User saveUser(String username, String password, String confirmedPassword) {
        User appUser = new User();
        if (userRepository.findUserWithName(username).isPresent() == true)
            throw new RuntimeException("User already exists");
        if (!password.equals(confirmedPassword))
            throw new RuntimeException("Please confirm your password");
        appUser.setUsername(username);
        Set<Role> roles = new HashSet<Role>();
        Role r = new Role("ROLE_USER");
        roleRepository.save(r);
        roles.add(r);
        appUser.setRoles(roles);
        appUser.setPassword(password);
        userRepository.save(appUser);
        return appUser;
    }

}
