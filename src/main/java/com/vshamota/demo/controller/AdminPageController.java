package com.vshamota.demo.controller;

import com.vshamota.demo.DTO.NewDeviceFormDTO;
import com.vshamota.demo.domain.Device;
import com.vshamota.demo.repository.CategoryRepo;
import com.vshamota.demo.repository.DeviceRepo;
import com.vshamota.demo.repository.ProducerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Controller
public class AdminPageController {
    private final DeviceRepo deviceRepo;
    private final ProducerRepo producerRepo;
    private final CategoryRepo categoryRepo;

    @GetMapping(value = "admin/newDevices")
    public String addOrEditDevice(Model model,  @RequestParam(defaultValue = "0") int page){
        model.addAttribute("all", deviceRepo.findAll(PageRequest.of(page, 5)));
        model.addAttribute("newDevice", new NewDeviceFormDTO());
        model.addAttribute("listOfCategories", categoryRepo.findAll());
        model.addAttribute("listOfProducers", producerRepo.findAll());
        return "admin/newDevice";
    }

    @Transactional
    @GetMapping("admin/delete/{id}")
    public String delete(@PathVariable Integer id) {
        deviceRepo.deleteById(id);
        return "redirect:/admin/newDevices";
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
