package com.vshamota.demo.controller;

import com.vshamota.demo.DTO.UserRegistrationDTO;
import com.vshamota.demo.domain.*;
import com.vshamota.demo.enums.Role;
import com.vshamota.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;

@RequiredArgsConstructor
@Controller
public class RegistrationController {

    private final CityRepo cityRepo;
    private final UserRepo userRepo;
    private final AddressRepo addressRepo;
    private final UserDetailsRepo userDetailsRepo;
    private final CartRepo cartRepo;

    @GetMapping("user/registrationPage")
    public String registration(Model model) {
        model.addAttribute("newUser", new UserRegistrationDTO());
        model.addAttribute("cities", cityRepo.findAll());
        return "user/registration";
    }

    @PostMapping("user/registration")
    public String addUser(@ModelAttribute("newUser") @Valid UserRegistrationDTO newUser,
                          BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("cities", cityRepo.findAll());
            return "user/registration";
        }
        //...Some actions
        User addUser = new User();
        addUser.setLogin(newUser.getLogin());
        addUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));
        addUser.setRoles(Collections.singleton(Role.USER));
        addUser.setActive(true);
        userRepo.save(addUser);
        City selectedCity = cityRepo.findById(newUser.getCity().getCityID()).orElse(null);
        Address address = new Address();
        address.setAddressDesc(newUser.getAddress());
        address.setCity(selectedCity);
        addressRepo.save(address);
        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName(newUser.getFirstName());
        userDetails.setLastName(newUser.getLastName());
        userDetails.setUser(addUser);
        userDetails.setAddress(address);
        userDetailsRepo.save(userDetails);
//        -------
        Cart cart = new Cart();
        cart.setUser(addUser);
        cartRepo.save(cart);
        return "redirect:/user/main";
    }

    @GetMapping("user/main")
    public String getMainPage() {
        return "user/main";
    }
}
