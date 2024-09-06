package com.somos_sansa.sansa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.somos_sansa.sansa.models.entities.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer>{
    
}
