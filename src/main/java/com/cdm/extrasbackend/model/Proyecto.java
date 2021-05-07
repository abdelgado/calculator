package com.cdm.extrasbackend.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "proyecto")
@Data
public class Proyecto {
    @Id
    private String idProyecto;
    @Column
    private String nombre;


}
