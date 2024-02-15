package edu.scranton.cs.se518.nile.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnUnauthorizedWhenPostingOrderWithoutUser() throws Exception {
        mockMvc.perform(post("/orders")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails("courtney")
    void shouldReturnOkWhenPostingOrderWithUser() throws Exception {
        mockMvc.perform(post("/orders")).andExpect(status().isOk());
    }

    @Test
    void shouldReturnUnauthorizedWhenGettingOrderWithoutUser() throws Exception {
        mockMvc.perform(post("/orders")).andExpect(status().isUnauthorized());
    }

    @Test  //  test failed
    @WithUserDetails("lisa")
    void shouldReturnForbiddenWhenGettingOrderAsWrongUser() throws Exception {
        mockMvc.perform(get("/orders/2")).andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("courtney")
    void shouldReturnOkWhenGettingOrderAsCorrectUser() throws Exception {
        mockMvc.perform(get("/orders/2")).andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("emily")
    void shouldReturnOkWhenGettingOrderWithAuthority() throws Exception {
        mockMvc.perform(get("/orders/2")).andExpect(status().isOk());
    }

    @Test // test failed
    @WithUserDetails("courtney") 
    void shouldReturnForbiddenWhenDeletingOrderWithoutAuthority() throws Exception {
        mockMvc.perform(delete("/orders/2")).andExpect(status().isForbidden());
    }

    @Test // test failed
    @WithUserDetails("emily")
    void shouldReturnForbiddenWhenDeletingOrderWithWrongAuthority() throws Exception {
        mockMvc.perform(delete("/orders/2")).andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("manny")
    void shouldReturnNoContentWhenDeletingOrderWithAuthority() throws Exception {
        mockMvc.perform(delete("/orders/2")).andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails("adam")
    void shouldReturnNoContentWhenDeletingOrderAsAdministrator() throws Exception {
        mockMvc.perform(delete("/orders/2")).andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnUnauthorizedForPathsUnderSpecificOrder() throws Exception {
        mockMvc.perform(get("/orders/1/secret")).andExpect(status().isUnauthorized());
    }
}
