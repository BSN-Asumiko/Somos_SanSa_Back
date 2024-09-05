package com.somos_sansa.sansa.mapper;

import com.somos_sansa.sansa.models.dto.BranchDTO;
import com.somos_sansa.sansa.models.dto.CommentDTO;
import com.somos_sansa.sansa.models.dto.TopicDTO;
import com.somos_sansa.sansa.models.dto.UserDTO;
import com.somos_sansa.sansa.models.entities.Branch;
import com.somos_sansa.sansa.models.entities.Comment;
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

    public static CommentDTO convertToCommentDTO (Comment comment) {
        UserDTO userDTO = convertToUserDTO(comment.getUser());
        TopicDTO topicDTO = convertToTopicDTO(comment.getTopic());
        return new CommentDTO (
            comment.getId(),
            comment.getText(),
            comment.getCreatedAt(),
            topicDTO,
            userDTO
        );
    }
}
