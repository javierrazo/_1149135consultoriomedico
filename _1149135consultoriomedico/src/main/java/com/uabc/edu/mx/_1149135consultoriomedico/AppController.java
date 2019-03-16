package com.uabc.edu.mx._1149135consultoriomedico;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/consultorio")
public class AppController {
    private List<Cita> citas = new ArrayList<>();
    private List<Doctor> doctores;
    private int contCita =1;
    SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @GetMapping(value = "/inicio")
    public String inicio (Model model){
        popularDoctores();
        popularCitas();

        model.addAttribute("doctores", doctores);
        model.addAttribute("citas", doctores);

        return "inicio";
    }

    @GetMapping(value = "/consultarCitas")
    public String consultarCitas (Model model){
        popularDoctores();
       // popularCitas();

        //Las listas las paso a json
        String someObjectAsJson = new Gson().toJson(citas);
        String docsAsJson = new Gson().toJson(doctores);

        //Agrego los objetos a model
        model.addAttribute("objtJson", someObjectAsJson);
        model.addAttribute("docJson", docsAsJson);
        return "consultarCitas";
    }

    @GetMapping(value = "/agendarCita")
    public String agendarCita (Model model){
        //Creo la lista de doctores
        popularDoctores();

        //Añado los doctores al model
        model.addAttribute("doctores", doctores);
        return "agendarCita";
    }

    @PostMapping("/addCita")
    public String addCita(HttpServletRequest request,
                          @RequestParam(value = "nombre", required = false) String nombre,
                          @RequestParam(value = "apellidoP", required = false) String apellidoP,
                          @RequestParam(value = "apellidoM", required = false) String apellidoM,
                          @RequestParam(value = "asunto", required = false) String asunto,
                          @RequestParam(value = "fecha", required = false) String fecha,
                          @RequestParam(value = "hora", required = false) String hora,
                          @RequestParam(value = "docId", required = false) int idDoctor) {
        //Recibo paramtros del html
        //Quito los espacios en blanco en asunto
        asunto=asunto.trim();
        try {
            //Creo una fecha auxiliar con un formato con los valores recibidos
            Date fechaAux = formatter.parse(fecha+ " " + hora );
            //Instancio y agrego cita
            citas.add(new Cita(contCita, nombre, apellidoP, apellidoM, asunto, fechaAux, idDoctor));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        contCita++;
        return "redirect:/consultorio/agendarCita";
    }


    public void popularDoctores(){
        //Metedo para crear doctores
        doctores = new ArrayList<>();
        doctores.add(new Doctor(22, "Victor", "Gonzalez", "Romero"));
        doctores.add(new Doctor(38, "Jose", "Ramirez", "Bagarin"));
        doctores.add(new Doctor(15, "Patricio", "Arellano", "Vega"));
    }

    public void popularCitas(){
        citas = new ArrayList<>();
        //Metodo para crear citas
        try {
            Date fechaAux;
            fechaAux = formatter.parse("2019-03-13"+ " " + "10:20" );
            citas.add(new Cita(contCita, "juan", "perez", "lopez", "cancer", fechaAux, 22));
            contCita++;
            fechaAux = formatter.parse("2019-03-14"+ " " + "10:20" );
            citas.add(new Cita(contCita, "ramon", "alvarez", "villa", "muerte", fechaAux, 38));
            contCita++;
            fechaAux = formatter.parse("2019-03-15"+ " " + "10:20" );
            citas.add(new Cita(contCita, "pedro", "padilla", "salazar", "hambre", fechaAux, 15));
            contCita++;
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
