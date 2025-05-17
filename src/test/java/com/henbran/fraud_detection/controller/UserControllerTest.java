package com.henbran.fraud_detection.controller;

import java.util.List;

import javax.print.attribute.standard.Media;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.henbran.fraud_detection.dto.UserDTO;
import com.henbran.fraud_detection.dto.UserRegistrationDTO;
import com.henbran.fraud_detection.entity.User;
import com.henbran.fraud_detection.mapper.UserMapper;
import com.henbran.fraud_detection.service.UserService;
import com.henbran.fraud_detection.utils.Constants;


@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void shouldReturnOkWhenGetAllUsers() throws Exception{
        // Cria lista de usuário para simular o repositório
        List<User> userList = Arrays.asList(
            new User("Bruno", "Andrade", "bhmandrade@gmail.com", "819934994032", "Rua lá de casa.", "Vice city", "2423423423"),
            new User("Naira", "Andrade", "bhmandrade@gmail.com", "819934994032", "Rua lá de casa.", "Vice city", "2423423423"),
            new User("Ana", "Andrade", "bhmandrade@gmail.com", "819934994032", "Rua lá de casa.", "Vice city", "2423423423"),
            new User("Tony", "Andrade", "bhmandrade@gmail.com", "819934994032", "Rua lá de casa.", "Vice city", "2423423423")
        );
        // Configura o mock para retornar a lista de usuários mockados.
        when(userService.getAllusers()).thenReturn(userList);
        // Realiza uma requisição para /users
        mockMvc.perform(get("/users"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(greaterThan(1))))
            .andExpect(jsonPath("$[0].firstName").value("Bruno"));
    }

    @Test
    public void shouldReturnOkWhenSaveNewUser() throws Exception {
        // Cria um userRegDTO para salvar no banco de dados
        UserRegistrationDTO userRegDTO = new UserRegistrationDTO(0L, "Bruno", "Andrade", "email@email.com", "2334436546", "rua das ruas", "Cajamaribe", "bubu", "sjfhgskjlhgk", true);
        User newUser = new User("Bruno", "Andrade", "email@email.com", "2334436546", "rua das ruas", "Cajamaribe", "sjfhgskjlhgk");
        newUser.setUsername(userRegDTO.username());

        // Configura o mock para validar o usuário
        when(userService.isUserValid(any(User.class))).thenReturn(true);

        // Configura o mock para retornar o userDTO
        when(userService.saveUser(any(User.class))).thenReturn(newUser);

        // Converter o userDTO para Json
        ObjectMapper objMapper = new ObjectMapper();
        String userRegDTOJson = objMapper.writeValueAsString(userRegDTO);

        mockMvc.perform(post("/users/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userRegDTOJson))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.firstName").value("Bruno"))
            .andExpect(jsonPath("$.firstName").exists())
            .andExpect(jsonPath("$.lastName").exists())
            .andExpect(jsonPath("$.email").exists())
            .andExpect(jsonPath("$.phoneNumber").exists())
            .andExpect(jsonPath("$.address").exists())
            .andExpect(jsonPath("$.city").exists())
            .andExpect(jsonPath("$.username").exists());
    }
    
    @Test
    public void shouldReturnOkWhenGetUserById() throws Exception {
        Long userId = 1L;

        User user = new User("Bruno", "Andrade", "email@email.com", "2334436546", "rua das ruas", "Cajamaribe", "sjfhgskjlhgk");
        user.setId(userId);
        user.setUsername("bubu");

        // Mock do retorno do service gerUserById
        when(userService.getUserById(userId)).thenReturn(user);

        mockMvc.perform(get("/users/" + userId))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.firstName").exists())
            .andExpect(jsonPath("$.lastName").exists())
            .andExpect(jsonPath("$.email").exists())
            .andExpect(jsonPath("$.phoneNumber").exists())
            .andExpect(jsonPath("$.address").exists())
            .andExpect(jsonPath("$.city").exists())
            .andExpect(jsonPath("$.username").exists());

    }

    @Test
    public void shouldReturnNotFoundWhenUserDoesNotExists() throws Exception {
        Long nonExistentUserId = 999L;
        // Mockar o retorno do service getUserById para null
        when(userService.getUserById(nonExistentUserId)).thenReturn(null);

        mockMvc.perform(get("/users/"+nonExistentUserId))
            .andExpect(status().isNotFound());

    }


    @Test
    public void shouldReturnOkWhenDeleteUser() throws Exception {
        Long userId = 1L;

        // Mock retorno do service deleteUser
        when(userService.deleteUser(userId)).thenReturn(Constants.USER_DELETED_SUCCESSFULY_STRING);

        mockMvc.perform(delete("/users/"+userId))
            .andExpect(status().isOk())
            .andExpect(content().string(Constants.USER_DELETED_SUCCESSFULY_STRING));
    }

    @Test
    public void shouldReturnBadRequestWhenUserDoesNotExists() throws Exception {
        Long nonExistentUserId = 0L;

        // Mockar retorno do service deleteUser
        when(userService.deleteUser(nonExistentUserId)).thenThrow(new IllegalArgumentException(Constants.USER_NOT_FOUND_STRING));

        mockMvc.perform(delete("/users/"+nonExistentUserId))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(Constants.USER_NOT_FOUND_STRING));
    }
}
