package com.example.socialmediaplanning.service;

import com.example.socialmediaplanning.dao.AppointmentDao;
import com.example.socialmediaplanning.dao.CompanyDao;
import com.example.socialmediaplanning.dao.EmployeeDao;
import com.example.socialmediaplanning.entity.Appointment;
import com.example.socialmediaplanning.entity.Company;
import com.example.socialmediaplanning.entity.Employee;
import com.example.socialmediaplanning.entity.Role;
import com.example.socialmediaplanning.exceptions.CompanyNotFoundException;
import com.example.socialmediaplanning.exceptions.EmployeeNotFoundException;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private AppointmentDao appointmentDao;


    public Employee addNewEmployee(Employee employee) {
        return employeeDao.save(employee);
    }

    public Optional<Employee> findEmployee(String id) {
        return employeeDao.findById(id);
    }

    @Transactional
    public Employee updateEmployee(Employee employee) {
        return employeeDao.save(employee);
    }

    public Set<Employee> findEmployeesByCompany(String id) {
        Optional<Company> optionalCompany = companyDao.findById(id);
        if(optionalCompany.isEmpty()) {
            throw new CompanyNotFoundException("Id not found");
        }
        Company company = optionalCompany.get();

        return employeeDao.findByCompany(company);
    }

    @Transactional
    public void hireEmployee(String idCompany, String idEmployee){
        Optional<Company> isCompany = companyDao.findById(idCompany);
        isCompany.ifPresent(company -> {
            Optional<Employee> isEmployee = employeeDao.findById(idEmployee);
            isEmployee.ifPresent(employee -> {
                employee.setCompany(company);

                System.out.println(employee.getEmployeeName());
                companyDao.save(company);
                employeeDao.save(employee);
            });
        });
    }

    @Transactional
    public void makeUserAdmin(String id) {
        Optional<Employee> optionalEmployee = employeeDao.findById(id);
        optionalEmployee.ifPresent(employee -> {
            employee.setRole(Role.ADMIN);
            employeeDao.save(employee);
        });
    }
    @Transactional
    public void deleteEmployee(String idEmployee) {
        Employee employee = employeeDao.getReferenceById(idEmployee);
        Set<Appointment> appointments = appointmentDao.findByEmployee(employee);
        appointmentDao.deleteAll(appointments);
        employeeDao.deleteById(idEmployee);
    }

}
