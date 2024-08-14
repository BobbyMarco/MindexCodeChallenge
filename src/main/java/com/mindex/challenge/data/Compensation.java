package com.mindex.challenge.data;

/**
 * @author Bobby Marco
 * Compensation object class
 * contains monetary information and the id of the employee associated with it
 */
public class Compensation {

    private String employeeId;
    private String salary;
    private String effectiveDate;


    public Compensation(){
    }

    public void setEmployeeId(String employeeId){this.employeeId = employeeId;}

    public String getEmployeeId(){return employeeId;}

    public void setSalary(String salary){this.salary=salary;}

    public String getSalary(){return salary;}

    public void setEffectiveDate(String effectiveDate){this.effectiveDate=effectiveDate;}

    public String getEffectiveDate(){return effectiveDate;}
}
