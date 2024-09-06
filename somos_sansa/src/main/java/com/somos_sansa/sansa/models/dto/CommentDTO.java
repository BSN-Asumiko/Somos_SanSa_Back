package com.somos_sansa.sansa.models.dto;

import java.time.LocalDateTime;

public class CommentDTO {
    private int id;
    private String text;
    private LocalDateTime createdAt;
    private TopicDTO topicDTO;
    private UserDTO userCommentDTO;

    public CommentDTO(int id, String text, LocalDateTime createdAt, TopicDTO topicDTO, UserDTO userCommentDTO) {
        this.id=id;
        this.text=text;
        this.createdAt=createdAt;
        this.topicDTO=topicDTO;
        this.userCommentDTO=userCommentDTO;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public TopicDTO getTopicDTO() {
        return topicDTO;
    }
    public void setTopicDTO(TopicDTO topicDTO) {
        this.topicDTO = topicDTO;
    }
    public UserDTO getUserCommentDTO() {
        return userCommentDTO;
    }
    public void setUserCommentDTO(UserDTO userCommentDTO) {
        this.userCommentDTO = userCommentDTO;
    }

    
    
}
