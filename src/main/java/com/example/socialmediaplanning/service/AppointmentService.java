package com.example.socialmediaplanning.service;

import com.example.socialmediaplanning.dao.AppointmentDao;
import com.example.socialmediaplanning.dao.CompanyDao;
import com.example.socialmediaplanning.dao.EmployeeDao;
import com.example.socialmediaplanning.entity.Appointment;
import com.example.socialmediaplanning.entity.Company;
import com.example.socialmediaplanning.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AppointmentService {
    @Autowired
    AppointmentDao appointmentDao;

    @Autowired
    CompanyDao companyDao;

    @Autowired
    EmployeeDao employeeDao;

    public AppointmentService(AppointmentDao appointmentDao, CompanyDao companyDao, EmployeeDao employeeDao) {
        this.appointmentDao = appointmentDao;
        this.companyDao = companyDao;
        this.employeeDao = employeeDao;
    }
    public Optional<Appointment> findAppointment(String id) {
        return appointmentDao.findById(id);
    }
    @Transactional
    public ResponseEntity<Appointment> updateAppointment(String id, Appointment updatedAppointment)
    {
        Optional<Appointment> appointment = findAppointment(id);
        if(appointment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        LocalDate currentDate = LocalDate.now();
        if (updatedAppointment.getMyDate().isBefore(currentDate)) {
            return ResponseEntity.badRequest().build();
        }
        Appointment appointmentToUpdate = appointment.get();
        appointmentToUpdate.setMyDate(updatedAppointment.getMyDate());
        appointmentToUpdate.setMessage(updatedAppointment.getMessage());
        Appointment savedAppointment = appointmentDao.save(appointmentToUpdate);
        return ResponseEntity.ok().body(savedAppointment);
    }

    public void deleteAppointment(String id) {
        appointmentDao.deleteById(id);
    }

    public Set<Appointment> findAppointmentByCompany(String id) {
        Optional<Company> optionalCompany = companyDao.findById(id);
        if(optionalCompany.isEmpty()) {
            return null;
        }
        Company company = optionalCompany.get();

        return appointmentDao.findByCompany(company);
    }

    @Transactional
    public void addAppointment(String idEmployee, Appointment appointment){
        Optional<Employee> optionalEmployee = employeeDao.findById(idEmployee);
        optionalEmployee.ifPresent(employee -> {
            appointment.setEmployee(employee);
            appointmentDao.save(appointment);
        });
    }



    public void aproveAppointment(String ID) {
        Optional<Appointment> isAppointment = appointmentDao.findById(ID);

        isAppointment.ifPresent(appointment -> {
            appointment.setCompany(appointment.getEmployee().getCompany());
            appointmentDao.save(appointment);
        });
    }

    public Set<Appointment> Schedule(String ID) {
        Optional<Company> isCompany = companyDao.findById(ID);
        if(isCompany.isEmpty()) {
            return null;
        }
        Company company = isCompany.get();
        return appointmentDao.findByCompany(company);
    }

}
