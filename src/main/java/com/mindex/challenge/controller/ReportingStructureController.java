package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.data.ReportingStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Bobby Marco
 * REST controller class that manages the ReportingStructure endpoint for GET requests
 */
@RestController
public class ReportingStructureController {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructure.class);

    @Autowired
    private EmployeeService employeeService;

    // receives the GET request, generates the reporting structure, and returns it
    @GetMapping("/reportingStructure/{id}")
    public ReportingStructure getReportingStructure(@PathVariable String id) {
        LOG.debug("Received reporting structure request for id  [{}]", id);
        // fetch employee information based on id
        Employee employee = employeeService.read(id);
        int numberOfReports = calcNumberOfReports(employee);

        return new ReportingStructure(employee, numberOfReports);
    }

    // Helper function to recursively count the reports under a given employee
    private int calcNumberOfReports(Employee employee) {
        // base case
        if (employee.getDirectReports() == null || employee.getDirectReports().isEmpty()) {
            return 0;
        }

        int reportCount = 0;
        // loop through direct reports
        for (Employee directEmp : employee.getDirectReports()) {
            reportCount += 1 + calcNumberOfReports(employeeService.read(directEmp.getEmployeeId()));
        }

        return reportCount;
    }
}
