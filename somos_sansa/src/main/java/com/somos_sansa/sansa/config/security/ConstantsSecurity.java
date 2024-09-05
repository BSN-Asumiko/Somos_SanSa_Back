package com.somos_sansa.sansa.config.security;

public class ConstantsSecurity {

    //Spring Security
    public static final String GET_ALL_BRANCHES = "/auth/branches";
    public static final String GET_TOPICS_BY_BRANCH = "/auth/branches/{branchId}/topics";
    public static final String ADD_NEW_TOPIC = "/api/topic/add";
    public static final String GET_COMMENTS_BY_TOPIC = "/auth/topics/{topicId}/comments";
    public static final String ADD_NEW_COMMENT = "/api/comments/add";
    public static final String UPDATE_COMMENT = "/api/comments/update/{id}";
    public static final String DELETE_COMMENT = "/api/comments/delete/{id}";
    
}
