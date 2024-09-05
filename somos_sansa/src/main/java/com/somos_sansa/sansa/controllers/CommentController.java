package com.somos_sansa.sansa.controllers;
import static com.somos_sansa.sansa.config.security.ConstantsSecurity.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.somos_sansa.sansa.exception.SanSaException;
import com.somos_sansa.sansa.mapper.EntityToDTOMapper;
import com.somos_sansa.sansa.models.dto.CommentDTO;
import com.somos_sansa.sansa.models.entities.Comment;
import com.somos_sansa.sansa.services.CommentService;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService=commentService;
    }

    @GetMapping(GET_COMMENTS_BY_TOPIC)
    public List<CommentDTO> getCommentsByTopic (@PathVariable int topicId) throws SanSaException {
        List<Comment> comments = commentService.getCommentsByTopicId(topicId);
        return comments.stream()
                .map(EntityToDTOMapper::convertToCommentDTO)
                .collect(Collectors.toList());
    }

    @PostMapping(ADD_NEW_COMMENT)
    public ResponseEntity<CommentDTO> addNeWComment(@RequestBody Comment comment)
            throws SanSaException {
        commentService.addNewComment(comment);
        CommentDTO resultDTO = EntityToDTOMapper.convertToCommentDTO(comment);
        return new ResponseEntity<>(resultDTO, HttpStatus.CREATED);
    }




    
}
