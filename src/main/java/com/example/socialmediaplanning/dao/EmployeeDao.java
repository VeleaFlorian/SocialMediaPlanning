package com.example.socialmediaplanning.dao;

import com.example.socialmediaplanning.entity.Company;
import com.example.socialmediaplanning.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, String> {

    Set<Employee> findByCompany(Company company);
}
