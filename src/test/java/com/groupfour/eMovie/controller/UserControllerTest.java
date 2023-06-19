package com.groupfour.eMovie.controller;

import com.groupfour.eMovie.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@Transactional
class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    // get方法的测试样例，请仿照着写
    @Test
    public void getUserByUsername() throws Exception {
        RequestBuilder request;
        request = get("/users/{username}", "lixueqi");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        RequestBuilder request1;
        request1 = get("/users/{username}", "asfwsdfa");
        mockMvc.perform(request1)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }



    @Test
    public void getRatingRecord() throws Exception {
        RequestBuilder request;
        request = get("/users/ratingrecord/{uid}", 1);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }


    // post方法的测试样例，请仿照着写
    @Test
    void registerUser() throws Exception {
        RequestBuilder request;

        request = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"1\", \"password\": \"d307d18355d3652b0f574f141d5da601\"}");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andDo(result -> {
                    // 在第一个请求完成后执行第二个请求
                    mockMvc.perform(request)
                            .andExpect(status().isOk())
                            .andDo(MockMvcResultHandlers.print());
                });

        RequestBuilder request1;
        request1 = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"a\", \"password\": \"\"}");
        mockMvc.perform(request1)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        RequestBuilder request2;
        request2 = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"\", \"password\": \"d307d18355d3652b0f574f141d5da601\"}");
        mockMvc.perform(request2)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());


        RequestBuilder request3;
        request3 = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"000\", \"password\": \"000\"}");
        mockMvc.perform(request3)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        RequestBuilder request4;
        request4 = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"sdadsdsaaaaaa\", \"password\": \"aaaaa\"}");
        mockMvc.perform(request4)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void login() throws Exception {
        RequestBuilder request;
        request = post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"lixueqi\", \"password\": \"d307d18355d3652b0f574f141d5da601\"}");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        RequestBuilder request1;
        request1 = post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"\", \"password\": \"d307d18355d3652b0f574f141d5da601\"}");
        mockMvc.perform(request1)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        RequestBuilder request2;
        request2 = post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"lixueqi\", \"password\": \"\"}");
        mockMvc.perform(request2)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        RequestBuilder request3;
        request3 = post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"000jkhjkhjkhjk\", \"password\": \"000\"}");
        mockMvc.perform(request3)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void changePassword() throws Exception {

        RequestBuilder request = patch("/users/password/{username}", "lixueqi")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.valueOf("application/json-patch+json"))
                .content("{\"oldPassword\": \"md5\", \"newPassword\": \"md5\"}");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());


        RequestBuilder request1 = patch("/users/password/{username}", "000")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.valueOf("application/json-patch+json"))
                .content("{\"oldPassword\": \"000\", \"newPassword\": \"009\"}");
        mockMvc.perform(request1)
                .andExpect(status().is(200))
                .andDo(MockMvcResultHandlers.print());

        RequestBuilder request2 = patch("/users/password/{username}", "000")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.valueOf("application/json-patch+json"))
                .content("{\"oldPassword\": \"\", \"newPassword\": \"\"}");
        mockMvc.perform(request2)
                .andExpect(status().is(200))
                .andDo(MockMvcResultHandlers.print());

        RequestBuilder request3 = patch("/users/password/{username}", "adasdasdsada")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.valueOf("application/json-patch+json"))
                .content("{\"oldPassword\": \"\", \"newPassword\": null}");
        mockMvc.perform(request3)
                .andExpect(status().is(200))
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void rateMovie() throws Exception {
        RequestBuilder request;
        request = post("/users/rating")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"uid\": \"000\", \"movieid\": \"2\", \"rating\": \"5.0\"}");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
}
