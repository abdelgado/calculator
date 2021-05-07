package com.cdm.extrasbackend.services;

import com.cdm.extrasbackend.model.Persona;
import com.cdm.extrasbackend.utils.commons.GenericServiceAPI;

import java.util.List;

public interface PersonaServiceAPI extends GenericServiceAPI <Persona, String> {
    List<Persona> findAllPersonas();
}
