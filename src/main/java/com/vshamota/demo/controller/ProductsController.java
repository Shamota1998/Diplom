package com.vshamota.demo.controller;

import com.vshamota.demo.DTO.FilterTmpDTO;
import com.vshamota.demo.domain.Cart;
import com.vshamota.demo.domain.Device;
import com.vshamota.demo.domain.User;
import com.vshamota.demo.repository.*;
import com.vshamota.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller(value = "/products")
public class ProductsController {

    private final CategoryRepo categoryRepo;
    private final ProducerRepo producerRepo;
    private final DeviceRepo deviceRepo;
    private final CartRepo cartRepo;
    private boolean rights;
    private FilterTmpDTO tmp;

    @GetMapping(value = "/list")
    public String getListOfProducts(Model model, @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(required = false) Boolean filtered,
                                    @AuthenticationPrincipal User user) {
        rights = UserService.checkUserPrivileges(user);
        model.addAttribute("rights", rights);
        if (filtered == null || !filtered) {
            model.addAttribute("categoryList", categoryRepo.findAll());
            model.addAttribute("producerList", producerRepo.findAll());
            model.addAttribute("filterObj", new FilterTmpDTO());
            model.addAttribute("allProducts", deviceRepo.findAll(PageRequest.of(page, 6)));
            model.addAttribute("filtered", false);
        }
        return "products/showProducts";
    }

    @PostMapping(value = "/filter")
    public String getFilterObj(@ModelAttribute("filterObj") FilterTmpDTO filterObj,
                               @AuthenticationPrincipal User user,
                               Model model, @RequestParam(defaultValue = "0") int page) {
        rights = UserService.checkUserPrivileges(user);

        tmp = filterObj;
        model.addAttribute("rights", rights);
        model.addAttribute("categoryList", categoryRepo.findAll());
        model.addAttribute("producerList", producerRepo.findAll());
        model.addAttribute("filtered", true);

        if (filterObj.getSort().equals("ASC") && filterObj.getProducer().equals("ANY")) {

            model.addAttribute("filtredProducts",
                    deviceRepo.findAllByCategoryInAndPriceBetweenOrderByPriceAsc
                            (
                                    filterObj.getCategory(),
                                    filterObj.getMinPrice(),
                                    filterObj.getMaxPrice(),
                                    PageRequest.of(page, 6)
                            ));

            return "products/showProducts";
        }

        if (filterObj.getSort().equals("DESC") && filterObj.getProducer().equals("ANY")) {
            model.addAttribute("filtredProducts",
                    deviceRepo.findAllByCategoryInAndPriceBetweenOrderByPriceDesc
                            (
                                    filterObj.getCategory(),
                                    filterObj.getMinPrice(),
                                    filterObj.getMaxPrice(),
                                    PageRequest.of(page, 6)
                            ));
            return "products/showProducts";
        }

        if (filterObj.getSort().equals("ASC")) {
            model.addAttribute("filtredProducts",
                    deviceRepo.findAllByCategoryInAndPriceBetweenAndProducerEqualsOrderByPriceAsc
                            (
                                    filterObj.getCategory(),
                                    filterObj.getMinPrice(),
                                    filterObj.getMaxPrice(),
                                    producerRepo.findByFirmDesc(filterObj.getProducer())
                            ));
            return "products/showProducts";
        }

        model.addAttribute("filtredProducts",
                deviceRepo.findAllByCategoryInAndPriceBetweenAndProducerEqualsOrderByPriceDesc
                        (
                                filterObj.getCategory(),
                                filterObj.getMinPrice(),
                                filterObj.getMaxPrice(),
                                producerRepo.findByFirmDesc(filterObj.getProducer())
                        ));
        return "products/showProducts";
    }

    @GetMapping("/page")
    public String getNewPage(@RequestParam(name = "page") int page, Model model) {
        model.addAttribute("categoryList", categoryRepo.findAll());
        model.addAttribute("producerList", producerRepo.findAll());
        model.addAttribute("filterObj", new FilterTmpDTO());
        model.addAttribute("filtered", true);
        if (tmp.getSort().equals("ASC") && tmp.getProducer().equals("ANY")) {
            model.addAttribute("filtredProducts",
                    deviceRepo.findAllByCategoryInAndPriceBetweenOrderByPriceAsc
                            (
                                    tmp.getCategory(),
                                    tmp.getMinPrice(),
                                    tmp.getMaxPrice(),
                                    PageRequest.of(page, 6)
//                                    filterObj.getProducer()
                            ));
            return "products/showProducts";
        }
        if (tmp.getSort().equals("DESC") && tmp.getProducer().equals("ANY")) {
            model.addAttribute("filtredProducts",
                    deviceRepo.findAllByCategoryInAndPriceBetweenOrderByPriceDesc
                            (
                                    tmp.getCategory(),
                                    tmp.getMinPrice(),
                                    tmp.getMaxPrice(),
                                    PageRequest.of(page, 6)
//                                    filterObj.getProducer()
                            ));
            return "products/showProducts";
        }
        return "products/showProducts";
    }

    @GetMapping(value = "/addToCart/{id}")
    public String addToCart(@PathVariable Integer id, @AuthenticationPrincipal User user) {

        Device addDevice = deviceRepo.findById(id).orElse(null);
        Cart cartByUser = cartRepo.findCartByUser(user);
        cartByUser.addDevice(addDevice);
        cartRepo.save(cartByUser);

        return "redirect:/list";
    }
}
