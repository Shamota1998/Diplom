package com.vshamota.demo.controller;

import com.vshamota.demo.DTO.FinalOrderDTO;
import com.vshamota.demo.DTO.OrderDTO;
import com.vshamota.demo.domain.*;
import com.vshamota.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Controller
public class OrderController {
    private final UserRepo userRepo;
    private final CartRepo cartRepo;
    private final DeviceRepo deviceRepo;
    private final OrderRepo orderRepo;
    private final Order_device orderDeviceRepo;

    List<FinalOrderDTO> finalList;
    OrderDTO order;

    @PostMapping("/makeOrder")
    public String makeOrderPage(Model model, OrderDTO order) {

        Float finalPrice = 0f;
        for (int i = 0; i < order.getDev().size(); i++) {
            finalPrice += order.getQty().get(i) * order.getDev().get(i).getPrice();
        }
        order.setTotalPrice(finalPrice);
        finalList = new ArrayList<>();
        for (int i = 0; i < order.getDev().size(); i++) {
            FinalOrderDTO tmp = new FinalOrderDTO();
            tmp.setDevice(order.getDev().get(i));
            tmp.setQty(order.getQty().get(i));
            tmp.setTotalPrice(finalPrice);
            finalList.add(tmp);
        }

//        for (int i = 0; i < order.size(); i++) {
//            finalPrice += order.get(i).getQty() * order.get(i).getDev().getPrice();
//        }
//        order.get(0).setTotalPrice(finalPrice);
//        finalList = new ArrayList<>();
//        for (int i = 0; i < order.size(); i++) {
//            FinalOrderDTO tmp = new FinalOrderDTO();
//            tmp.setDevice(order.get(i).getDev());
//            tmp.setQty(order.get(i).getQty());
//            tmp.setTotalPrice(finalPrice);
//            finalList.add(tmp);
//        }
        model.addAttribute("finalOrderList", finalList);

        return "products/order";
    }

    @PostMapping("/submitOrder")
    public String submitOrder(Model model, @AuthenticationPrincipal User user) {
        Orders newOrder = new Orders();
        List<OrderDevice> orderDeviceList = new ArrayList<>();
        newOrder.setOrderDevices(orderDeviceList);
        newOrder.setDate(LocalDate.now());
        newOrder.setUser(user);
        orderRepo.save(newOrder);
        for (int i = 0; i < finalList.size(); i++) {
            Device device = finalList.get(i).getDevice();
            device.setQTY(device.getQTY() - finalList.get(i).getQty());
            OrderDevice od = new OrderDevice();
            od.setDevice(device);
            od.setQty(finalList.get(i).getQty());
            od.setOrder(newOrder);
            orderDeviceRepo.save(od);
            orderDeviceList.add(od);
        }
        Cart cart = cartRepo.findCartByUser(user);
        for (FinalOrderDTO finalOrder : finalList
        ) {
            cart.deleteProduct(deviceRepo.findById(finalOrder.getDevice().getDev_id())
                    .orElseThrow(NoSuchElementException::new));
        }
        cartRepo.save(cart);
        model.addAttribute("submitMessage", "Order was submitted!");

        return "products/order";
    }
}

