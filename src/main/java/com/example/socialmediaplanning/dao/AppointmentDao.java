package com.example.socialmediaplanning.dao;

import com.example.socialmediaplanning.entity.Appointment;
import com.example.socialmediaplanning.entity.Company;
import com.example.socialmediaplanning.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AppointmentDao extends JpaRepository<Appointment, String> {
    Set<Appointment> findByCompany(Company company);
    Set<Appointment> findByEmployee(Employee employee);
}
