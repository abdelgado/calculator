package com.cdm.extrasbackend.api;

import com.cdm.extrasbackend.model.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaDaoAPI extends CrudRepository<Persona, String> {

    @Query("select p from Persona p where p.activo = true")
    List<Persona> findAllPersonas();
}
