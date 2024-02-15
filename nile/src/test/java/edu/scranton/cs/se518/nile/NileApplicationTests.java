package edu.scranton.cs.se518.nile;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NileApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldReturnUnauthorizedByDefault() throws Exception {
        mockMvc.perform(get("/missed")).andExpect(status().isUnauthorized());
    }

    @Test // test failed
    @WithUserDetails("adam")
    void shouldReturnForbiddenByDefaultEvenIfUserIsAuthenticated() throws Exception {
        mockMvc.perform(get("/missed")).andExpect(status().isForbidden());
    }
}
