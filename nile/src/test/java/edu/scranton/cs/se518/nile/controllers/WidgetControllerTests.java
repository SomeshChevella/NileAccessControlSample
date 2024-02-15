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
public class WidgetControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test 
    void shouldReturnOkWhenGettingWidgetsWithoutUser() throws Exception {
        mockMvc.perform(get("/widgets")).andExpect(status().isOk());
    }

    @Test  
    void shouldReturnOkWhenGettingWidgetWithoutUser() throws Exception {
        mockMvc.perform(get("/widgets/1")).andExpect(status().isOk());
    }

    @Test
    void shouldReturnUnauthorizedWhenPostingWidgetWithoutUser() throws Exception {
        mockMvc.perform(post("/widgets")).andExpect(status().isUnauthorized());
    }

    @Test 
    @WithUserDetails("courtney")
    void shouldReturnForbiddenWhenPostingWidgetWithoutAuthority() throws Exception {
        mockMvc.perform(post("/widgets")).andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("lisa")
    void shouldReturnOkWhenPostingWidgetWithAuthority() throws Exception {
        mockMvc.perform(post("/widgets")).andExpect(status().isOk());
    }

    @Test 
    @WithUserDetails("lisa")
    void shouldReturnForbiddenWhenDeletingWidgetWithoutAuthority() throws Exception {
        mockMvc.perform(delete("/widgets/1")).andExpect(status().isForbidden());
    }

    @Test  
    @WithUserDetails("lisa")
    void shouldReturnForbiddenWhenDeletingOtherWidgetWithoutAuthority() throws Exception {
        mockMvc.perform(delete("/widgets/2")).andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("adam")
    void shouldReturnNoContentWhenDeletingWidgetAsAdministrator() throws Exception {
        mockMvc.perform(delete("/widgets/1")).andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnUnauthorizedForPathsUnderSpecificWidget() throws Exception {
        mockMvc.perform(get("/widgets/1/secret")).andExpect(status().isUnauthorized());
    }
}
