package com.example.app;

import com.example.app.model.Plane;
import com.example.app.repository.PlaneRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Duration;
import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class PlaneTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PlaneRepository repository;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void createPlaneTest() throws Exception {
        Plane plane = new Plane("Ivan", 5, LocalDate.of(2022, 1, 2), Duration.ofHours(2), "Uralsk", "Moscow", false);
        mockMvc.perform(post("/planes")
                .content(objectMapper.writeValueAsString(plane))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Ivan"))
                .andExpect(jsonPath("$.places").value(5))
                .andExpect(jsonPath("$.departureTime", is(LocalDate.of(2022, 1, 2).toString())))
                .andExpect(jsonPath("$.duration", is(Duration.ofHours(2).toString())))
                .andExpect(jsonPath("$.departurePlace").value("Uralsk"))
                .andExpect(jsonPath("$.destination").value("Moscow"))
                .andExpect(jsonPath("$.deleted").value(false))
        ;
    }

    private Plane createTestPlane(String name, Integer places,
                                  LocalDate departureTime,
                                  Duration duration,
                                  String departurePlace,
                                  String destination,
                                  boolean deleted) {
        Plane plane = new Plane(name, places, departureTime, duration, departurePlace, destination, deleted);
        return repository.save(plane);
    }

    @Test
    public void getPlaneByPlaneIdTest() throws Exception {
        long id = createTestPlane("Ivan", 5, LocalDate.of(2022, 1, 2), Duration.ofHours(2), "Uralsk", "Moscow", false).getId();
        mockMvc.perform(
                get("/planes/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Ivan"))
                .andExpect(jsonPath("$.places").value(5))
                .andExpect(jsonPath("$.departureTime").value(LocalDate.of(2022, 1, 2).toString()))
                .andExpect(jsonPath("$.duration").value(Duration.ofHours(2).toString()))
                .andExpect(jsonPath("$.departurePlace").value("Uralsk"))
                .andExpect(jsonPath("$.destination").value("Moscow"))
                .andExpect(jsonPath("$.deleted").value(false));
    }
}
