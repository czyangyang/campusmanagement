package com.czxx.campusmanagement.entity.redis;

import java.io.Serializable;

public class Member implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -1959528436584592183L;
    
    
    private String id;
    private String nickname;
    
    public Member(){}
    
    public Member(String id, String nickname){
        this.setId(id);
        this.setNickname(nickname);
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
}