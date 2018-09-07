package com.czxx.campusmanagement.dao;

import com.czxx.campusmanagement.entity.CallTheRollDetail;
import com.czxx.campusmanagement.entity.CallTheRollDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CallTheRollDetailMapper {
    long countByExample(CallTheRollDetailExample example);

    int deleteByExample(CallTheRollDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CallTheRollDetail record);

    int insertSelective(CallTheRollDetail record);

    List<CallTheRollDetail> selectByExample(CallTheRollDetailExample example);

    CallTheRollDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CallTheRollDetail record, @Param("example") CallTheRollDetailExample example);

    int updateByExample(@Param("record") CallTheRollDetail record, @Param("example") CallTheRollDetailExample example);

    int updateByPrimaryKeySelective(CallTheRollDetail record);

    int updateByPrimaryKey(CallTheRollDetail record);
    
    //查询详情
    List<CallTheRollDetail> selectDetailWithRelation(Integer callTheRollId);
}