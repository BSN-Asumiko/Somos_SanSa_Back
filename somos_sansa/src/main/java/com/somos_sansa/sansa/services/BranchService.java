package com.somos_sansa.sansa.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.somos_sansa.sansa.exception.SanSaException;
import com.somos_sansa.sansa.models.entities.Branch;
import com.somos_sansa.sansa.repositories.BranchRepository;

@Service
public class BranchService {
    private final BranchRepository branchRepository;

    public BranchService (BranchRepository branchRepository) {
        this.branchRepository=branchRepository;
    }
    
    public List<Branch> getAllBranches() throws SanSaException {
        List<Branch> branchList = branchRepository.findAll();
        if (branchList == null || branchList.size() == 0) {
            throw new SanSaException("La base de datos está vacia" );
        }
        return branchList;
    }
}
