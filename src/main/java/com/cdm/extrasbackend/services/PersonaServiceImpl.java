package com.cdm.extrasbackend.services;

import com.cdm.extrasbackend.api.PersonaDaoAPI;
import com.cdm.extrasbackend.model.Persona;
import com.cdm.extrasbackend.utils.commons.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class PersonaServiceImpl extends GenericServiceImpl <Persona, String> implements PersonaServiceAPI {

    @Autowired
    private PersonaDaoAPI personaDaoAPI;

    @Override
    public CrudRepository<Persona, String> getDAO() {
        return personaDaoAPI;
    }

    @NotNull
    @Transactional
    @Override
    public List<Persona> findAllPersonas() {
        return personaDaoAPI.findAllPersonas();
    }
}
