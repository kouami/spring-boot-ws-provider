package com.spring.ws.springws.repository;

import com.samples.spring.xpadro.employees.Employee;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDao {

    private static final List<Employee> employeeList = new ArrayList<>();


    @PostConstruct
    public void populateData() {

        Employee em = new Employee();
        em.setLastName("Akolly");
        em.setFirstName("Emmanuel");
        em.setGender("M");
        em.setEmpId(new BigInteger("12345"));



        Employee alex = new Employee();
        alex.setLastName("Akolly");
        alex.setFirstName("Alexander");
        alex.setGender("M");
        alex.setEmpId(new BigInteger("6789"));



        Employee chris = new Employee();
        chris.setLastName("Akolly");
        chris.setFirstName("Christina");
        chris.setGender("F");
        chris.setEmpId(new BigInteger("7484"));


        Employee lyd = new Employee();
        lyd.setLastName("Akolly");
        lyd.setFirstName("Lydia");
        lyd.setGender("F");
        lyd.setEmpId(new BigInteger("3434"));


        Employee claire = new Employee();
        claire.setLastName("Akolly");
        claire.setFirstName("Claire");
        claire.setGender("G");
        claire.setEmpId(new BigInteger("9870"));


        employeeList.add(em);
        employeeList.add(alex);
        employeeList.add(chris);
        employeeList.add(lyd);
        employeeList.add(claire);
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }
}
