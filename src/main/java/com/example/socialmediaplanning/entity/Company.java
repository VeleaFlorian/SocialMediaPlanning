package com.example.socialmediaplanning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String companyName;
//    @OneToMany(targetEntity = Appointment.class, cascade = CascadeType.ALL)
//    @JoinColumn(name="ca_fk", referencedColumnName = "ID")
//    private Set<Appointment> appointments;

    public String getID() {
        return id;
    }



    public void setID(String ID) {
        this.id = ID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

