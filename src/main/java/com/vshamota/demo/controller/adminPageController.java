package com.vshamota.demo.controller;

import com.vshamota.demo.DTO.NewDeviceFormDTO;
import com.vshamota.demo.domain.Device;
import com.vshamota.demo.repository.CategoryRepo;
import com.vshamota.demo.repository.DeviceRepo;
import com.vshamota.demo.repository.ProducerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class adminPageController {
    private final DeviceRepo deviceRepo;
    private final ProducerRepo producerRepo;
    private final CategoryRepo categoryRepo;

    @GetMapping("admin/adminPage")
    public String getAdminPage(){
        return "admin/adminPage";
    }

    @GetMapping("admin/listOfDevices")
    public String getList(Model model){
        model.addAttribute("listOfDevices", deviceRepo.findAll());
        return "admin/listOfDevices";
    }

    @GetMapping(value = "admin/newDevices")
    public String addOrEditDevice(Model model){
        model.addAttribute("newDevice", new NewDeviceFormDTO());
        model.addAttribute("listOfCategories", categoryRepo.findAll());
        model.addAttribute("listOfProducers", producerRepo.findAll());
        return "admin/newDevice";
    }


    @PostMapping(value = "admin/newDeviceForm")
    public String newDeviceForm(@ModelAttribute(name="newDevice") NewDeviceFormDTO formObj){
        Device newDevice = new Device();
        newDevice.setDevice_desc(formObj.getDeviceDesc());
        newDevice.setPrice(formObj.getPrice());
        newDevice.setQTY(formObj.getQTY());
        newDevice.setCategory(formObj.getCategory());
        newDevice.setProducer(formObj.getProducer());
        deviceRepo.save(newDevice);
        return "redirect:/admin/newDevices";
    }
}
