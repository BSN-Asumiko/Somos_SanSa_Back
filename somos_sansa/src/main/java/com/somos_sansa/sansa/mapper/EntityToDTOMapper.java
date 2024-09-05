package com.somos_sansa.sansa.mapper;

import com.somos_sansa.sansa.models.dto.BranchDTO;
import com.somos_sansa.sansa.models.dto.TopicDTO;
import com.somos_sansa.sansa.models.dto.UserDTO;
import com.somos_sansa.sansa.models.entities.Branch;
import com.somos_sansa.sansa.models.entities.Topic;
import com.somos_sansa.sansa.models.entities.User;

public class EntityToDTOMapper {
    public static UserDTO convertToUserDTO(User user) {
        return new UserDTO(
            user.getId(),
            user.getNickame(),
            user.getAvatarUrl(),
            user.getDistrict()
            );
    }

    public static BranchDTO convertToBranchDTO(Branch branch) {
        return new BranchDTO(
            branch.getId(),
            branch.getCategory(),
            branch.getDescription()
        );
    }

    public static TopicDTO convertToTopicDTO(Topic topic) {
        BranchDTO branchDTO = convertToBranchDTO(topic.getBranch());
        UserDTO userDTO = convertToUserDTO(topic.getUser());
        return new TopicDTO(
            topic.getId(),
            topic.getTitle(),
            topic.getCreatedAt(),
            branchDTO,
            userDTO
        );
    }
}
