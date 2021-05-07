package com.cdm.extrasbackend.controller;

import com.cdm.extrasbackend.model.Persona;
import com.cdm.extrasbackend.model.Proyecto;
import com.cdm.extrasbackend.model.Registro;
import com.cdm.extrasbackend.services.PersonaServiceAPI;
import com.cdm.extrasbackend.services.ProyectoServiceAPI;
import com.cdm.extrasbackend.services.RegistroServiceAPI;
import com.cdm.extrasbackend.utils.classes.CalculoHoras;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registro")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class RegistroRestController {

    @Autowired
    private RegistroServiceAPI registroServiceAPI;

    @Autowired
    private PersonaServiceAPI personaServiceAPI;

    @Autowired
    private ProyectoServiceAPI proyectoServiceAPI;

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Registro> getAll(){
        return registroServiceAPI.getAll();
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Registro find (@PathVariable Long id){
        return registroServiceAPI.get(id);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Registro> save (@RequestBody Registro registro){
        CalculoHoras calculoHoras = new CalculoHoras();
        calculoHoras.calcularHoras(registro.getHora_entrada(),registro.getHora_salida(),registro.isFestivo());
        registro.setHora_ordinaria(calculoHoras.getHorasordinarias());
        registro.setRecargo_nocturno(calculoHoras.getRecargosnocturos());
        registro.setHora_extra(calculoHoras.getHorasExtrasOrdinarias());
        registro.setHora_extra_nocturna(calculoHoras.getHorasExtrasNocturnas());
        registro.setHora_extra_festiva(calculoHoras.getHorasExtrasOrdinariasFestivas());
        registro.setHora_extra_festiva_nocturna(calculoHoras.getHorasExtrasNocturnasFestivas());
        Persona persona = personaServiceAPI.get(registro.getPersona().getCedula());
        registro.setPersona(persona);
        calculoHoras.calcularSueldo(persona.getSalario());
        registro.setSalario_sin_prestaciones(calculoHoras.getSalarioSinPrestaciones());
        registro.setSalario_con_prestaciones(calculoHoras.getSalarioConPrestaciones());
        Proyecto proyecto = proyectoServiceAPI.get(registro.getProyecto().getIdProyecto());
        registro.setProyecto(proyecto);
        Registro obj = registroServiceAPI.save(registro);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PostMapping("/segundo/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Registro> segundoRegistro (@PathVariable Long id, @RequestBody Registro registro){
        CalculoHoras calculoHoras = new CalculoHoras();
        Registro primerRegistro = registroServiceAPI.get(id);
        calculoHoras.calcularHorasSegundoRegistro(registro.getHora_entrada(),registro.getHora_salida(),registro.isFestivo(),primerRegistro);
        registro.setHora_ordinaria(calculoHoras.getHorasordinarias());
        registro.setRecargo_nocturno(calculoHoras.getRecargosnocturos());
        registro.setHora_extra(calculoHoras.getHorasExtrasOrdinarias());
        registro.setHora_extra_nocturna(calculoHoras.getHorasExtrasNocturnas());
        registro.setHora_extra_festiva(calculoHoras.getHorasExtrasOrdinariasFestivas());
        registro.setHora_extra_festiva_nocturna(calculoHoras.getHorasExtrasNocturnasFestivas());
        Persona persona = personaServiceAPI.get(registro.getPersona().getCedula());
        registro.setPersona(persona);
        calculoHoras.calcularSueldo(persona.getSalario());
        registro.setSalario_sin_prestaciones(calculoHoras.getSalarioSinPrestaciones());
        registro.setSalario_con_prestaciones(calculoHoras.getSalarioConPrestaciones());
        Proyecto proyecto = proyectoServiceAPI.get(registro.getProyecto().getIdProyecto());
        registro.setProyecto(proyecto);
        Registro obj = registroServiceAPI.save(registro);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity <Registro> delete (@PathVariable Long id){
        Registro registro = registroServiceAPI.get(id);
        if (registro != null){
            registroServiceAPI.delete(id);
        }else {return  new ResponseEntity<>(registro, HttpStatus.INTERNAL_SERVER_ERROR);}
        return new ResponseEntity<>(registro, HttpStatus.OK);
    }
}
