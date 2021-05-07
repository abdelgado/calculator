package com.cdm.extrasbackend.api;

import com.cdm.extrasbackend.model.Proyecto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoDaoAPI extends CrudRepository <Proyecto, String> {
}
