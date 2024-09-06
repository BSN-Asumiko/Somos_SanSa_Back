package com.somos_sansa.sansa.models.dto;

public class UserDTO {
    private int id;
    private String nickname;
    private String avatarUrl;
    private String district;

    public UserDTO(int id, String nickname, String avatarUrl, String district) {
        this.id=id;
        this.nickname=nickname;
        this.avatarUrl=avatarUrl;
        this.district=district;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    

    
}
