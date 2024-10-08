package com.somos_sansa.sansa.models.dto;

import java.time.LocalDateTime;

public class TopicDTO {
    private int id;
    private String title;
    private LocalDateTime createdAt;
    private BranchDTO branchDTO;
    private UserDTO userTopicDTO;

    public TopicDTO(int id, String title, LocalDateTime createdAt, BranchDTO branchDTO, UserDTO userTopicDTO) {
        this.id=id;
        this.title=title;
        this.createdAt=createdAt;
        this.branchDTO=branchDTO;
        this.userTopicDTO=userTopicDTO;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public BranchDTO getBranchDTO() {
        return branchDTO;
    }
    public void setBranchDTO(BranchDTO branchDTO) {
        this.branchDTO = branchDTO;
    }
    public UserDTO getUserTopicDTO() {
        return userTopicDTO;
    }
    public void setUserTopicDTO(UserDTO userTopicDTO) {
        this.userTopicDTO = userTopicDTO;
    }

    
}
