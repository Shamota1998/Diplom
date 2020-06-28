package com.vshamota.demo;

import com.vshamota.demo.repository.CategoryRepo;
import com.vshamota.demo.repository.DeviceRepo;
import com.vshamota.demo.repository.ProducerRepo;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
//@WebMvcTest
@AutoConfigureMockMvc
public class SecuredControllerSpringBootIntegrationTest {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MockMvc mvc;
    @MockBean
    private CategoryRepo categoryRepo;
    @MockBean
    private ProducerRepo producerRepo;
    @MockBean
    private DeviceRepo deviceRepo;



    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @SneakyThrows
    @WithMockUser(username = "admin", password = "qwerty", roles = {"ADMIN"})
    @Test
    public void shouldReturnStatusOk() {
        when(categoryRepo.findAll()).thenReturn(new ArrayList<>());
        when(producerRepo.findAll()).thenReturn(new ArrayList<>());
        when(deviceRepo.findAll()).thenReturn(new ArrayList<>());
        mvc.perform(get("/products/list")).andExpect(status().isOk());
    }

    @SneakyThrows
//    @WithMockUser(username = "admin", password = "qwerty", roles = {"ADMIN"})
    @Test
    public void shouldRedirect() {
        when(categoryRepo.findAll()).thenReturn(new ArrayList<>());
        when(producerRepo.findAll()).thenReturn(new ArrayList<>());
        when(deviceRepo.findAll()).thenReturn(new ArrayList<>());
        mvc.perform(get("/products/list")).andExpect(status().is3xxRedirection());
    }

    @SneakyThrows
    @Test
    @WithMockUser(username = "admin", password = "qwerty", roles = {"ADMIN"})
    public void loginUser() {
        mvc.perform(post("/login")
                .param("username", "admin")
                .param("password", "qwerty"))
                .andExpect(authenticated());
    }


}