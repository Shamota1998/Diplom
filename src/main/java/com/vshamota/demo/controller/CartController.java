package com.vshamota.demo.controller;

import com.vshamota.demo.DTO.OrderDTO;
import com.vshamota.demo.domain.Cart;
import com.vshamota.demo.domain.User;
import com.vshamota.demo.repository.CartRepo;
import com.vshamota.demo.repository.DeviceRepo;
import com.vshamota.demo.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Controller
public class CartController {
    private final UserRepo userRepo;
    private final CartRepo cartRepo;
    private final DeviceRepo deviceRepo;

    @GetMapping("/myCart")
    public String getCartPage(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("devicesOnCart", cartRepo.findCartByUser(user).getDevices());
        model.addAttribute("OrderList", new OrderDTO());
        return "products/cart";
    }

    @GetMapping("/deleteFormCart/{id}")
    public String delete(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        Cart cart = cartRepo.findCartByUser(user);
        cart.deleteProduct(deviceRepo.findById(id).orElseThrow(NoSuchElementException::new));
        cartRepo.save(cart);
        return "redirect:/myCart";
    }
}
