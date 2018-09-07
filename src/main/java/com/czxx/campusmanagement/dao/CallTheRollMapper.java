package com.czxx.campusmanagement.dao;

import com.czxx.campusmanagement.entity.CallTheRoll;
import com.czxx.campusmanagement.entity.CallTheRollDetail;
import com.czxx.campusmanagement.entity.CallTheRollExample;
import com.czxx.campusmanagement.entity.Student;
import com.czxx.campusmanagement.entity.StudentExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CallTheRollMapper {
    long countByExample(CallTheRollExample example);

    int deleteByExample(CallTheRollExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CallTheRoll record);

    int insertSelective(CallTheRoll record);

    List<CallTheRoll> selectByExample(CallTheRollExample example);

    CallTheRoll selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CallTheRoll record, @Param("example") CallTheRollExample example);

    int updateByExample(@Param("record") CallTheRoll record, @Param("example") CallTheRollExample example);

    int updateByPrimaryKeySelective(CallTheRoll record);

    int updateByPrimaryKey(CallTheRoll record);
    
    //关联查询
    List<CallTheRoll> selectByExampleWithRelation(CallTheRollExample example);
    
    long countByExampleWithRelation(CallTheRollExample example);
}