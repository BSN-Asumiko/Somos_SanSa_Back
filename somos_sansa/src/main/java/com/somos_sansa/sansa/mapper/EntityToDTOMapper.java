package com.somos_sansa.sansa.mapper;

import com.somos_sansa.sansa.models.dto.BranchDTO;
import com.somos_sansa.sansa.models.entities.Branch;

public class EntityToDTOMapper {
    public static BranchDTO convertToBranchDTO(Branch branch) {
        return new BranchDTO(
            branch.getId(),
            branch.getCategory(),
            branch.getDescription()
        );
    }
}
