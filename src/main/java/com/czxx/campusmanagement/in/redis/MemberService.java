package com.czxx.campusmanagement.in.redis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.czxx.campusmanagement.entity.redis.Member;

@Repository(value="memberService")
public interface MemberService{

    
    /**
     * 添加对象
     */

    public boolean add(final Member member);
       

    /**
     * 添加集合
     */
    public boolean add(final List<Member> list);
    
    /**
     * 删除对象 ,依赖key
     */
    public void delete(String key);
  
    /**
     * 删除集合 ,依赖key集合
     */
    public void delete(List<String> keys);
    
    /**
     * 修改对象 
     */
    public boolean update(final Member member);
    
    /**
     * 根据key获取对象
     */
    public Member get(final String keyId);

}