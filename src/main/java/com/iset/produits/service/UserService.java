package com.iset.produits.service;

import java.util.Objects;
import java.util.Set;

import javax.validation.Valid;

import java.util.HashSet;
import java.util.List;

import com.iset.produits.dao.RoleRepository;
import com.iset.produits.dao.UserRepository;
import com.iset.produits.entities.MyUserDetail;
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

        return new MyUserDetail(user);
    }

    @GetMapping("/register")
    public String showRegister(ModelMap modelMap) {
        List<Role> roles = roleRepository.findAll();
        modelMap.addAttribute("roles", roles);
        modelMap.addAttribute("userForm", new UserForm());
        modelMap.addAttribute("reg", true);

        return "auth";
    }

    @PostMapping("/register")
    public String register(@Valid UserForm userForm, BindingResult bindingResult,Long role, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            List<Role> roles = roleRepository.findAll();
            System.out.println(roles);
            modelMap.addAttribute("roles", roles);
            modelMap.addAttribute("reg", true);
            modelMap.addAttribute("userForm", userForm);
            return "auth";
        }
        this.saveUser(userForm.getUsername(), userForm.getPassword(), userForm.getConfirmedPassword(), role);

        return "redirect:/login";
    }

    public User saveUser(String username, String password, String confirmedPassword, Long role) {
        User appUser = new User();
        if (userRepository.findUserWithName(username).isPresent() == true)
            throw new RuntimeException("User already exists");
        if (!password.equals(confirmedPassword))
            throw new RuntimeException("Please confirm your password");
        appUser.setUsername(username);
        Set<Role> roles = new HashSet<Role>();

        Role r = roleRepository.findById(role).get();
        roleRepository.save(r);
        roles.add(r);
        appUser.setRoles(roles);
        appUser.setPassword(password);
        userRepository.save(appUser);
        return appUser;
    }

}
