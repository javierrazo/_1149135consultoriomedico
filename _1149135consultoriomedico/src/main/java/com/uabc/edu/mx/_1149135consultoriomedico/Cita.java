package com.uabc.edu.mx._1149135consultoriomedico;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Cita implements Serializable {
    private int idCita;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String asunto;
    private Date fecha;
    private int idDoctor;

    public Cita(int idCita, String nombre, String apellidoP, String apellidoM, String asunto, Date fecha, int idDoctor) {
        this.idCita = idCita;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.asunto = asunto;
        this.fecha = fecha;
        this.idDoctor = idDoctor;
    }


}
