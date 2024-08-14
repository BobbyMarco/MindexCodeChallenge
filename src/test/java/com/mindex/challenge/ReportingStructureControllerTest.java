package com.mindex.challenge;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import com.mindex.challenge.controller.ReportingStructureController;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ReportingStructureController.class)
public class ReportingStructureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @InjectMocks
    private ReportingStructureController reportingStructureController;

    private Employee employee;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // Setup mock Employee with a simple reporting structure
        Employee directReport1 = new Employee();
        directReport1.setEmployeeId("2");

        Employee directReport2 = new Employee();
        directReport2.setEmployeeId("3");

        employee = new Employee();
        employee.setEmployeeId("1");
        employee.setDirectReports(Arrays.asList(directReport1, directReport2));

        // Mocking the employeeService.read method
        when(employeeService.read("1")).thenReturn(employee);
        when(employeeService.read("2")).thenReturn(new Employee()); // Mocking direct report employee
        when(employeeService.read("3")).thenReturn(new Employee()); // Mocking direct report employee
    }

    @Test
    public void testGetReportingStructure() throws Exception {
        mockMvc.perform(get("/reportingStructure/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employee.employeeId").value("1"))
                .andExpect(jsonPath("$.numberOfReports").value(2));
    }
}