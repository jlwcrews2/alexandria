package no.jlwcrews.alexandria.location;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LocationController.class)
class LocationControllerTest {

    static List<Location> locations = new ArrayList<>();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    LocationService service;

    @BeforeAll
    static void setUpBeforeClass() {
        for (int i = 0; i < 10; i++) {
            locations.add(new Location("Location " + i));
        }
    }

    @Test
    void getLocations() throws Exception {
        when(service.getLocations()).thenReturn(locations);
        this.mockMvc.perform(get("/api/location"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getLocationById() throws Exception {
        when(service.getLocationById(1)).thenReturn(locations.get(1));
        System.out.println(new ObjectMapper().writeValueAsString(service.getLocationById(1)));
        this.mockMvc.perform(
                        get("/api/location/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location").value("Location 1"));

    }

    @Test
    void saveLocation() throws Exception {
        when(service.saveLocation(new Location())).thenReturn(locations.get(1));
        this.mockMvc.perform(
                post("/api/location")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(locations.get(1)))
        ).andExpect(status().isOk());
    }
}