package com.spring.ws.springws.endpoint;


import com.samples.spring.xpadro.employees.Employee;
import com.samples.spring.xpadro.employees.Employees;
import com.samples.spring.xpadro.employees.GetEmployeeRequest;
import com.samples.spring.xpadro.employees.GetEmployeeResponse;
import com.spring.ws.springws.repository.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Endpoint
public class EmployeeEndpoint {

    private static final String NAMESPACE_URI = "http://www.xpadro.spring.samples.com/employees";

    @Autowired
    private EmployeeDao dao;


    /**
     * @param request
     * @return
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetEmployeeRequest")
    public @ResponsePayload
    GetEmployeeResponse getEmployeeResponse(@RequestPayload GetEmployeeRequest request) {
        GetEmployeeResponse response = new GetEmployeeResponse();
        Employees employees = new Employees();
        employees.getEmployee().addAll(getEmployees(request.getId(), request.getSearchCriteria()));
        response.setEmployees(employees);
        return response;
    }

    /**
     * @param id
     * @param criteria
     * @return
     */
    private List<Employee> getEmployees(BigInteger id, String criteria) {

        List<Employee> employees = new ArrayList<>();
        if (id != null) {
            Employee emp = getEmployeesById(id);
            employees.add(emp);
        } else if (criteria != null) {
            employees = getEmployeesByFirstOrLastName(criteria);
        }
        return employees;
    }

    /**
     * @param id
     * @return
     */
    private Employee getEmployeesById(BigInteger id) {
        
        return dao.getEmployeeList().stream().filter(emp -> emp.getEmpId().equals(id)).findFirst().get();
    }

    /**
     * @param criteria
     * @return
     */
    private List<Employee> getEmployeesByFirstOrLastName(String criteria) {

        return dao.getEmployeeList().stream().filter(
                e -> e.getFirstName().equals(criteria) || e.getLastName().equals(criteria)
                || e.getGender().equals(criteria) || e.getEmpId().equals(criteria)
        ).collect(Collectors.toList());

    }
}
