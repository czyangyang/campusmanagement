package com.czxx.campusmanagement.dao;

import com.czxx.campusmanagement.entity.SchoolClass;
import com.czxx.campusmanagement.entity.SchoolClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchoolClassMapper {
    long countByExample(SchoolClassExample example);

    int deleteByExample(SchoolClassExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchoolClass record);

    int insertSelective(SchoolClass record);

    List<SchoolClass> selectByExample(SchoolClassExample example);

    SchoolClass selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchoolClass record, @Param("example") SchoolClassExample example);

    int updateByExample(@Param("record") SchoolClass record, @Param("example") SchoolClassExample example);

    int updateByPrimaryKeySelective(SchoolClass record);

    int updateByPrimaryKey(SchoolClass record);
}