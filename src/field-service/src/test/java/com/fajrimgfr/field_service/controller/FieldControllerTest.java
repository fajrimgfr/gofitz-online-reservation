package com.fajrimgfr.field_service.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureWebMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fajrimgfr.field_service.service.FieldService;

@SpringBootTest
@AutoConfigureWebMvc
public class FieldControllerTest {
    private static final String ENDPOINT_URL = "/field";

    @InjectMocks
    private FieldController fieldController;

    @MockitoBean
    private FieldService fieldService;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.fieldController).build();
    }

    // @Test


//     public void createNewProductTest() throws Exception {
//         Product demoProduct = new Product(1, "demo", "desc", "type",1,1000,"SUP","SUP01");
//         when(productRepository.save(any())).thenReturn(demoProduct);
//         mockMvc.perform(MockMvcRequestBuilders
//                         .post(ENDPOINT_URL)
//                         .content(Objects.requireNonNull(ValueMapper.jsonAsString(demoProduct)))
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .accept(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isCreated())
//                 .andExpect(MockMvcResultMatchers.jsonPath("$.results.id").exists());
//     }

//     @Test
//     public void shouldReturnAllProductsFromDB() throws Exception {
//         when(productRepository.findAll()).thenReturn(Arrays.asList(
//                 new Product(1, "demo1", "desc1", "type1",1,1000,"SUP1","SUP01"),
//                 new Product(2, "demo2", "desc2", "type2",2,2000,"SUP2","SUP02")
//         ));
//         mockMvc.perform(MockMvcRequestBuilders
//                         .get(ENDPOINT_URL)
//                         .accept(MediaType.APPLICATION_JSON))
//                 .andDo(print())
//                 .andExpect(status().isOk())
//                 .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
//                 .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].id").value(1));
//     }
}
