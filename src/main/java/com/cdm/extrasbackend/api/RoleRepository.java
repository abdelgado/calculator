package com.cdm.extrasbackend.api;

import com.cdm.extrasbackend.model.ERole;
import com.cdm.extrasbackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
