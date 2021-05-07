package com.cdm.extrasbackend.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "registro")
@Data
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDate fecha;
    @Column
    private LocalTime hora_entrada;
    @Column
    private LocalTime hora_salida;
    @Column
    private Double hora_ordinaria;
    @Column
    private Double recargo_nocturno;
    @Column
    private Double hora_extra;
    @Column
    private Double hora_extra_nocturna;
    @Column
    private Double hora_extra_festiva;
    @Column
    private Double hora_extra_festiva_nocturna;
    @OneToOne
    @JoinColumn(name = "cedula")
    private Persona persona;
    @OneToOne
    @JoinColumn(name = "id_proyecto")
    private Proyecto proyecto;
    @Column
    private boolean festivo;
    @Column
    private Double salario_sin_prestaciones;
    @Column
    private Double salario_con_prestaciones;
    @Column
    private String actividad;
    @Column
    private String users;


}
