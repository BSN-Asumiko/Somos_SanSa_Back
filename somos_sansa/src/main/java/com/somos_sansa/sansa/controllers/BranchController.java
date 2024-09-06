package com.somos_sansa.sansa.controllers;
import static com.somos_sansa.sansa.config.security.ConstantsSecurity.*;

import java.util.List;
import java.util.stream.Collectors;

//FOR ADDING A NEW BRANCH
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;

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

    //FOR FUTURE DEVELOPMENT - ONLY ADMIN CAN ADD 
    /* @PostMapping(ADD_NEW_BRANCH_URL)
    public ResponseEntity<BranchDTO> addNeWBranch(@RequestBody Branch branch)
            throws SanSaException {
        branchService.addNewBranch(branch);
        BranchDTO resultDTO = EntityToDTOMapper.convertToBranchDTO(branch);
        return new ResponseEntity<>(resultDTO, HttpStatus.CREATED);
    } */

    
}
