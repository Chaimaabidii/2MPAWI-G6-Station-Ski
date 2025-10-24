package com.example.gestionstationskii;

import com.example.gestionstationskii.controllers.RegistrationRestController;
import com.example.gestionstationskii.entities.Registration;
import com.example.gestionstationskii.entities.Support;
import com.example.gestionstationskii.services.IRegistrationServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RegistrationRestControllerMockitoTest {

    private MockMvc mockMvc;

    @Mock
    private IRegistrationServices registrationServices;

    @InjectMocks
    private RegistrationRestController registrationRestController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(registrationRestController).build();
    }

    @Test
    void testAddAndAssignToSkier() throws Exception {
        Registration reg = new Registration();
        reg.setNumRegistration(1L);

        when(registrationServices.addRegistrationAndAssignToSkier(any(Registration.class), eq(1L)))
                .thenReturn(reg);

        mockMvc.perform(put("/registration/addAndAssignToSkier/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reg)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numRegistration").value(1));

        verify(registrationServices, times(1))
                .addRegistrationAndAssignToSkier(any(Registration.class), eq(1L));
    }

    @Test
    void testAssignToCourse() throws Exception {
        Registration reg = new Registration();
        reg.setNumRegistration(2L);

        when(registrationServices.assignRegistrationToCourse(2L, 3L)).thenReturn(reg);

        mockMvc.perform(put("/registration/assignToCourse/2/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numRegistration").value(2));

        verify(registrationServices, times(1))
                .assignRegistrationToCourse(2L, 3L);
    }

    @Test
    void testAddAndAssignToSkierAndCourse() throws Exception {
        Registration reg = new Registration();
        reg.setNumRegistration(5L);

        when(registrationServices.addRegistrationAndAssignToSkierAndCourse(any(Registration.class), eq(1L), eq(2L)))
                .thenReturn(reg);

        mockMvc.perform(put("/registration/addAndAssignToSkierAndCourse/1/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reg)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numRegistration").value(5));

        verify(registrationServices, times(1))
                .addRegistrationAndAssignToSkierAndCourse(any(Registration.class), eq(1L), eq(2L));
    }

    @Test
    void testNumWeeksCourseOfInstructorBySupport() throws Exception {
        List<Integer> weeks = Arrays.asList(1, 2, 3);

        when(registrationServices.numWeeksCourseOfInstructorBySupport(1L, Support.SKI))
                .thenReturn(weeks);

        mockMvc.perform(get("/registration/numWeeks/1/SKI"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value(1))
                .andExpect(jsonPath("$[1]").value(2))
                .andExpect(jsonPath("$[2]").value(3));

        verify(registrationServices, times(1))
                .numWeeksCourseOfInstructorBySupport(1L, Support.SKI);
    }
}
