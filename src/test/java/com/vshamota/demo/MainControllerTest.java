package com.vshamota.demo;


import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class MainControllerTest {

    @Autowired
    MockMvc mockMvc;

    @WithMockUser
    @SneakyThrows
    @Test
    public void shouldReturnMainPage(){
        mockMvc.perform(get("/mainpage"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/main"));
    }

    @WithMockUser
    @SneakyThrows
    @Test
    public void shouldReturnLoginPage(){
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/login"));
    }

    @WithMockUser
    @SneakyThrows
    @Test
    public void shouldReturnAboutPage(){
        mockMvc.perform(get("/main/aboutUs"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/aboutUs"));
    }

    @SneakyThrows
    @Test
    public void shouldRedirectOnLoginPage(){
        mockMvc.perform(get("/main/aboutUs"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
}
