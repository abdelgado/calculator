package com.cdm.extrasbackend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "persona")
@Data
public class Persona {
    @Id
    private String cedula;
    @Column
    private String nombre;
    @Column
    private Double salario;
    @Column
    private Boolean activo;

    @PrePersist
    public void setTrueActivo(){
        this.activo = true;
    }
}
