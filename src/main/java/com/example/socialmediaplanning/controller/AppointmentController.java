package com.example.socialmediaplanning.controller;

import com.example.socialmediaplanning.dao.AppointmentDao;
import com.example.socialmediaplanning.dao.EmployeeDao;
import com.example.socialmediaplanning.entity.Appointment;
import com.example.socialmediaplanning.entity.Company;
import com.example.socialmediaplanning.entity.Employee;
import com.example.socialmediaplanning.exceptions.AppointmentNotFoundException;
import com.example.socialmediaplanning.exceptions.CompanyNotFoundException;
import com.example.socialmediaplanning.service.AppointmentService;
import com.example.socialmediaplanning.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@RestController
//@Validated
@RequestMapping("appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;


    @GetMapping("/{id}")
    public ResponseEntity<?> findAppointment(@PathVariable String id) {
        Optional<Appointment> appointment = appointmentService.findAppointment(id);
        if(appointment.isEmpty()) {
            throw new AppointmentNotFoundException("Id not found");
        }
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }


    @ResponseBody
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable String id, @RequestBody Appointment updatedAppointment) {
        Optional<Appointment> appointment = appointmentService.findAppointment(id);
        if(appointment.isEmpty()) {
            throw new AppointmentNotFoundException("Id not found");
        }
        return new ResponseEntity<>(appointmentService.updateAppointment(id, updatedAppointment), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Appointment> deleteAppointment(@PathVariable String id) {
        Optional<Appointment> appointment = appointmentService.findAppointment(id);
        if(appointment.isEmpty()) {
            throw new AppointmentNotFoundException("Id not found");
        }
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();

    }


    @PostMapping( value = "/{idEmployee}/assignAppointment")
    public ResponseEntity<?> createAppointment(@PathVariable String idEmployee, @RequestBody Appointment appointment) {
        LocalDate currentDate = LocalDate.now();
        if (appointment.getMyDate().isBefore(currentDate)) {
            return ResponseEntity.badRequest().build();
        }
        try {
            appointmentService.addAppointment(idEmployee, appointment);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/company/{id}/aprove")
    public void aproveAppointment(@PathVariable String id) {
        Optional<Appointment> appointment = appointmentService.findAppointment(id);
        if(appointment.isEmpty()) {
            throw new AppointmentNotFoundException("Id not found");
        }
        appointmentService.aproveAppointment(id);
    }

    @GetMapping("/company/{id}/schedule")
    public Set<Appointment> Schedule(@PathVariable String id) {
        return appointmentService.findAppointmentByCompany(id);
    }
}



