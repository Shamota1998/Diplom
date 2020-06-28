package com.vshamota.demo;


import com.vshamota.demo.DTO.UserRegistrationDTO;
import com.vshamota.demo.config.WebSecurityConfig;
import com.vshamota.demo.repository.*;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(WebSecurityConfig.class)
@RunWith(SpringRunner.class)
@ActiveProfiles(value = "test")
@SpringBootTest
@AutoConfigureMockMvc
@Sql(value = {"CreateAndInitializeDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"cleanUp.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CityRepo cityRepo;
    @Autowired
     UserRepo userRepo;
    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private UserDetailsRepo userDetailsRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    CountryRepo countryRepo;


    @SneakyThrows
    @Test
    public void shouldPutCityListInModelAndRedirectOnRegistration() {
        mockMvc.perform(get("/user/registrationPage"))
                .andExpect(ResultMatcher.matchAll(
                        status().isOk(),
                        model().attributeExists("newUser"),
                        model().attributeExists("cities"),
                        model().size(2)
                ));
    }

    @SneakyThrows
    @Test
    public void shouldAddUserCredentialsInDB() {
        UserRegistrationDTO userRegDTO = new UserRegistrationDTO(
                "SomeName",
                "Lastname",
                "someLogin",
                "some20200",
                "some20200",
                "Grenible 22/1",
                countryRepo.findById(1).orElseThrow(NoSuchElementException::new),
                cityRepo.findById(1).orElseThrow(NoSuchElementException::new)
        );

        mockMvc.perform(post("/user/registration").flashAttr("newUser", userRegDTO))
                .andExpect(status().isOk());

        assertThat(userDetailsRepo.findById(1)).isNotNull();
    }

}
