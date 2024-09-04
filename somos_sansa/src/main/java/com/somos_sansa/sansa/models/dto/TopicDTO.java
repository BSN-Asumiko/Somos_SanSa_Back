package com.somos_sansa.sansa.models.dto;

import java.time.LocalDateTime;

public class TopicDTO {
    private int id;
    private String title;
    private LocalDateTime createdAt;
    private BranchDTO branchDTO;
    private UserDTO userDTO;

    public TopicDTO(int id, String title, LocalDateTime createdAt, BranchDTO branchDTO, UserDTO userDTO) {
        this.id=id;
        this.title=title;
        this.createdAt=createdAt;
        this.branchDTO=branchDTO;
        this.userDTO=userDTO;
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
    public UserDTO getUserDTO() {
        return userDTO;
    }
    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    
}
