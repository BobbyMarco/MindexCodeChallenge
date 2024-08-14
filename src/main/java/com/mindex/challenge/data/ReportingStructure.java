package com.mindex.challenge.data;

/**
 * @author Bobby Marco
 * ReportingStructure object class
 * contains an employee and the number of reports they manage
 */
public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    public ReportingStructure(Employee employee, int numberOfReports){
        this.employee = employee;
        this.numberOfReports = numberOfReports;
    }

    public Employee getEmployee(){
        return employee;
    }

    public void setEmployee(Employee employee){
        this.employee = employee;
    }

    public int getNumberOfReports(){
        return numberOfReports;
    }

    public void setNumberOfReports(int numberOfReports){
        this.numberOfReports = numberOfReports;
    }

}
