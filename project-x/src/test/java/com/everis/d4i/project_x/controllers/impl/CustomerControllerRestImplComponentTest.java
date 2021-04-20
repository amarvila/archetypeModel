package com.everis.d4i.project_x.controllers.impl;

import com.everis.d4i.project_x.controller.rest.impl.CustomerControllerRestImpl;
import com.everis.d4i.project_x.service.CustomerService;
import com.everis.d4i.project_x.util.constant.RestConstantsUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerControllerRestImpl.class)
//@SpringBootTest
//@AutoConfigureMockMvc
@Ignore
public class CustomerControllerRestImplComponentTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void getAllCustomersReturnsA200Code() throws Exception {
        //when and then
        mockMvc.perform(
                get(RestConstantsUtils.APPLICATION_NAME
                            + RestConstantsUtils.API_VERSION_1
                            + RestConstantsUtils.RESOURCE_CUSTOMER
                )
                        .header("Origin", "*"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}
