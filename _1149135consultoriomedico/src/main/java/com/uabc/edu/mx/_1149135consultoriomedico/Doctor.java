package com.uabc.edu.mx._1149135consultoriomedico;

import lombok.Data;
import sun.swing.BakedArrayList;

import java.util.ArrayList;
import java.util.List;

@Data
public class Doctor {
    private int idDoctor;
    private String nombre;
    private String apellidoP;
    private String apellidoM;

    public Doctor(int idDoctor, String nombre, String apellidoP, String apellidoM) {
        this.idDoctor = idDoctor;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
    }
}
