package com.henbran.fraud_detection.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.blankOrNullString;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
    
    // Injeção do MockMvc para simular requisições HTTP
    @Autowired
    private MockMvc mockMvc;

    @Test
    void loginShouldReturnToken() throws Exception {
        // Neste teste iremos chamar a rota /auth/login, passando um username e password.
        // O endpoint deverá retornar um token JWT (uma string não vazia).
        String username = "henbran";
        String password = "12345678";

        mockMvc.perform(post("/auth/login")
                .param("username", username)
                .param("password", password)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
            .andExpect(status().isOk())
            .andExpect(content().string(not(blankOrNullString())));
    }

    @Test
    void loginShouldReturn401WhenUsernameIsMissing() throws Exception {
        // Neste teste iremos chamar a rota /auth/login, sem passar um username.
        // O endpoint deverá retornar 401 (UNAUTHORIZED).
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
            .andExpect(status().isUnauthorized())
            .andExpect(content().string("O parâmetro 'username' é obrigatório."));
    }
}
