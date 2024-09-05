package com.somos_sansa.sansa.services;

import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.somos_sansa.sansa.exception.SanSaException;
import com.somos_sansa.sansa.models.entities.Comment;
import com.somos_sansa.sansa.models.entities.Topic;
import com.somos_sansa.sansa.models.entities.User;
import com.somos_sansa.sansa.repositories.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final TopicService topicService;

    public CommentService (CommentRepository commentRepository, UserService userService, TopicService topicService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.topicService = topicService;
    }

    public List<Comment> getAllComments() throws SanSaException {
        List<Comment> commentList = commentRepository.findAll();
        if (commentList == null || commentList.size() == 0) {
            throw new SanSaException("Todavía no hay ningún commentario creado");
        }
        return commentList;
    }

    public List<Comment> getCommentsByTopicId(int topicId) throws SanSaException {
        List<Comment> commentList = commentRepository.findByTopicId(topicId);
        if (commentList == null || commentList.size() == 0) {
            throw new SanSaException("Todavía no hay ningún commentario creado en ese tema", HttpStatus.NOT_FOUND);
        }
        return commentList;
    }

    public void addNewComment(Comment comment) throws SanSaException {
        if (comment.getText() == null || comment.getText().isEmpty()) {
            throw new SanSaException("El texto del comentario no puede estar vacío.", HttpStatus.BAD_REQUEST);
        }

        if (comment.getCreatedAt() == null) {
            throw new SanSaException("La hora de creación del comentario no puede estar vacía.", HttpStatus.BAD_REQUEST);
        }

        User user = comment.getUser();
        if (user == null || !user.isIdSet()) {
            throw new SanSaException("Usuario no especificado.", HttpStatus.BAD_REQUEST);
        }

        User existingUser = userService.getUserById(user.getId());
        if (existingUser == null) {
            throw new SanSaException("Usuario no encontrado.", HttpStatus.NOT_FOUND);
        }
        comment.setUser(existingUser);

        Topic topic = comment.getTopic();
        if (topic == null) {
            throw new SanSaException("Tema no especificada.", HttpStatus.BAD_REQUEST);
        }

        Topic existingTopic = topicService.getTopicById(topic.getId());
        if (existingTopic == null) {
            throw new SanSaException("Tema no encontrado.", HttpStatus.NOT_FOUND);
        }
        comment.setTopic(existingTopic);

        commentRepository.save(comment);
    }

    public void updateComment(Comment comment) throws SanSaException {

        try {
            Comment existingComment = getCommentById(comment.getId());

            existingComment.setText(comment.getText());
        
            commentRepository.save(existingComment);
        
        } catch (IllegalArgumentException | OptimisticLockingFailureException | JpaObjectRetrievalFailureException exc) {
            throw new SanSaException(exc.getMessage());
        }
    }

    public Comment getCommentById(int commentId) throws SanSaException {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null) {
            throw new SanSaException("El comentario con ID" + commentId + "no encontrado.", HttpStatus.NOT_FOUND);
        }
        return comment;
    }

    public ResponseEntity<Object> deleteComment(int commentId) throws SanSaException {
        try {
            getCommentById(commentId);
            commentRepository.deleteById(commentId);
            return new ResponseEntity<>("El comentario ha sido eliminado con exito!", HttpStatus.OK);
        
        } catch (IllegalArgumentException | OptimisticLockingFailureException | JpaObjectRetrievalFailureException exc) {
            throw new SanSaException(exc.getMessage());
        }
        
    }

}
