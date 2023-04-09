package com.example.socialmediaplanning.controller;

import com.example.socialmediaplanning.entity.Company;
import com.example.socialmediaplanning.entity.Employee;
import com.example.socialmediaplanning.exceptions.CompanyNotFoundException;
import com.example.socialmediaplanning.exceptions.EmployeeNotFoundException;
import com.example.socialmediaplanning.service.CompanyService;
import com.example.socialmediaplanning.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping({"/addNewCompany"})
    public Company addNewCompany(@RequestBody Company company) {
        return companyService.addCompany(company);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCompany(@PathVariable String id) {
        Optional<Company> company = companyService.findCompany(id);
        if(company.isEmpty()) {
            throw new CompanyNotFoundException("Id not found");
        }
        return new ResponseEntity<>(companyService.findCompany(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable String id, @RequestBody   Company updateCompany) {
        Optional<Company> company = companyService.findCompany(id);
        if(company.isEmpty()) {
            throw new CompanyNotFoundException("Id not found");
        }
        Company companyToUpdate = company.get();
        companyToUpdate.setCompanyName(updateCompany.getCompanyName());
        Company savedCompany = companyService.updateCompany(companyToUpdate);
        return new ResponseEntity<>(savedCompany, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Company> deleteCompany(@PathVariable String id) {
        Optional<Company> company = companyService.findCompany(id);
        if(company.isEmpty()) {
            throw new CompanyNotFoundException("Id not found");
        }
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/employees/{employeeName}")
    public ResponseEntity<?> hireEmployee(@PathVariable String id, @PathVariable String employeeName) {
        try {
            employeeService.hireEmployee(id, employeeName);
            System.out.println("no");
            return ResponseEntity.ok().build();
        } catch (CompanyNotFoundException e) {
            System.out.println("Hi");
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/promote")
    public void makeAdmin(@PathVariable String id) {

        employeeService.makeUserAdmin(id);
    }

}
