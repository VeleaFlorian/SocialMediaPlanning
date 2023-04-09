package com.example.socialmediaplanning.service;

import com.example.socialmediaplanning.dao.CompanyDao;
import com.example.socialmediaplanning.dao.EmployeeDao;
import com.example.socialmediaplanning.entity.Company;
import com.example.socialmediaplanning.entity.Employee;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CompanyService {

    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private EmployeeDao employeeDao;


    public CompanyService(CompanyDao companyDao, EmployeeDao employeeDao) {
        this.companyDao = companyDao;
        this.employeeDao = employeeDao;
    }

    public CompanyDao getCompanyDao() {
        return companyDao;
    }

    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public Company addCompany(Company company) {
        return companyDao.save(company);
    }

    public Optional<Company> findCompany(String id){
        return companyDao.findById(id);
    }

    public Company updateCompany(Company company) {
        return companyDao.save(company);
    }


    @Transactional
    public void deleteCompany(String idCompany) {
        Company company = companyDao.getReferenceById(idCompany);
        Set<Employee> employees = employeeDao.findByCompany(company);
        employeeDao.deleteAll(employees);
        companyDao.deleteById(idCompany);
    }
}
