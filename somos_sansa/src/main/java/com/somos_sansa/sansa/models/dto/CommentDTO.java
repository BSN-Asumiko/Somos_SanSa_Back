package com.somos_sansa.sansa.models.dto;

import java.time.LocalDateTime;

public class CommentDTO {
    private int id;
    private String text;
    private LocalDateTime createdAt;
    private TopicDTO topicDTO;
    private UserDTO userDTO;

    public CommentDTO(int id, String text, LocalDateTime createdAt, TopicDTO topicDTO, UserDTO userDTO) {
        this.id=id;
        this.text=text;
        this.createdAt=createdAt;
        this.topicDTO=topicDTO;
        this.userDTO=userDTO;
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
    public UserDTO getUserDTO() {
        return userDTO;
    }
    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    
    
}
