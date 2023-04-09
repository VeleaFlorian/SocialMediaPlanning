package com.example.socialmediaplanning.controller;

import com.example.socialmediaplanning.entity.Employee;
import com.example.socialmediaplanning.exceptions.EmployeeNotFoundException;
import com.example.socialmediaplanning.service.EmployeeService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping({"/addNewEmployee"})
    public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee employee) {
        employeeService.addNewEmployee(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findEmployee(@PathVariable String id) {
        Optional<Employee> employee = employeeService.findEmployee(id);
        if(employee.isEmpty()) {
            throw new EmployeeNotFoundException("Id not found");
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable String id, @RequestBody Employee updateEmployee) {
        Optional<Employee> employee = employeeService.findEmployee(id);
        if(employee.isEmpty()) {
            throw new EmployeeNotFoundException("Id not found.");
        }
        Employee employeeToUpdate = employee.get();
        employeeToUpdate.setEmail(updateEmployee.getEmail());
        employeeToUpdate.setAge(updateEmployee.getAge());
        employeeToUpdate.setRole(updateEmployee.getRole());
        employeeToUpdate.setEmployeeName(updateEmployee.getEmployeeName());
        Employee savedEmployee = employeeService.updateEmployee(employeeToUpdate);
        return new ResponseEntity<>(savedEmployee, HttpStatus.OK);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String id) {
        Optional<Employee> employee = employeeService.findEmployee(id);
        if(employee.isEmpty()) {
            throw new EmployeeNotFoundException("Id not found.");
        }
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<?> getEmployeesByCompany(@PathVariable String id) {
        Set<Employee>  employees =  employeeService.findEmployeesByCompany(id);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }



}
