package com.somos_sansa.sansa.config.security;

public class ConstantsSecurity {

    //ENDPOINTS

    //front-end part
    public static final String LOCALHOST_FRONT_URL = "http://localhost:5173";

    //users
    public static final String LOGIN_URL = "/auth/log_in";
    public static final String SIGNIN_URL = "/auth/sign_in";
    public static final String UPDATE_PROFILE_URL = "/api/profile/update/{userId}";
    public static final String GET_PROFILE_DETAILS_URL = "/api/profile/{id}";
    
    //branches
    public static final String GET_ALL_BRANCHES_URL = "/auth/branches";
    //public static final String ADD_NEW_BRANCH_URL = "/auth/branches/add"; for future development - this endpoint should be changed for only admin can use

    //topics
    public static final String GET_TOPICS_BY_BRANCH_URL = "/auth/branches/{branchId}/topics";
    public static final String ADD_NEW_TOPIC_URL = "/api/topics/add";

    //comments
    public static final String GET_COMMENTS_BY_TOPIC_URL = "/auth/topics/{topicId}/comments";
    public static final String ADD_NEW_COMMENT_URL = "/api/comments/add";
    public static final String UPDATE_COMMENT_URL = "/api/comments/update/{id}";
    public static final String DELETE_COMMENT_URL = "/api/comments/delete/{id}";
    public static final String GET_COMMENT_BY_ID_URL = "/api/comments/{id}";
    

    //Spring Security
    public static final String HEADER_AUTHORIZATION_KEY = "Authorization";
    public static final String TOKEN_BEARER_PREFIX = "Bearer ";
    

     // JWT 
    public static final String SECRET_KEY = System.getProperty("JWT_SECRET_KEY");
    public static final long TOKEN_EXPIRATION_TIME = 259_200_000;
}
