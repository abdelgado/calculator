package com.cdm.extrasbackend.services;

import com.cdm.extrasbackend.api.ProyectoDaoAPI;
import com.cdm.extrasbackend.model.Proyecto;
import com.cdm.extrasbackend.utils.commons.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ProyectoServiceImpl extends GenericServiceImpl <Proyecto, String> implements ProyectoServiceAPI {

    @Autowired
    private ProyectoDaoAPI proyectoDaoAPI;

    @Override
    public CrudRepository<Proyecto, String> getDAO() {
        return proyectoDaoAPI;
    }
}
