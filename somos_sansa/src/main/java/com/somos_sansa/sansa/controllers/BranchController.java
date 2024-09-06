package com.somos_sansa.sansa.controllers;
import static com.somos_sansa.sansa.config.security.ConstantsSecurity.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.somos_sansa.sansa.exception.SanSaException;
import com.somos_sansa.sansa.mapper.EntityToDTOMapper;
import com.somos_sansa.sansa.models.dto.BranchDTO;
import com.somos_sansa.sansa.models.entities.Branch;
import com.somos_sansa.sansa.services.BranchService;

@RestController
public class BranchController {
    private final BranchService branchService;

    public BranchController (BranchService branchService) {
        this.branchService=branchService;
    }

    @GetMapping(GET_ALL_BRANCHES_URL)
    public List<BranchDTO> getAllBranches () throws SanSaException {
        List<Branch> branches = branchService.getAllBranches();
        List<BranchDTO> branchesDTO = branches.stream().map(EntityToDTOMapper::convertToBranchDTO)
                .collect(Collectors.toList());
        return branchesDTO;
    }
    
}
