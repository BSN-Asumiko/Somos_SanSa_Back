package com.somos_sansa.sansa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.somos_sansa.sansa.models.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository <Role, Integer> {
    Optional<Role> findByName(String name);
}
