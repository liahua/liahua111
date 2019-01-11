package com.jt.idea.manage.mapper;

import com.jt.idea.common.po.StudentSection;
import com.jt.idea.common.po.StudentSectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentSectionMapper {
    int countByExample(StudentSectionExample example);

    int deleteByExample(StudentSectionExample example);

    int deleteByPrimaryKey(Integer sectionId);

    int insert(StudentSection record);

    int insertSelective(StudentSection record);

    List<StudentSection> selectByExample(StudentSectionExample example);

    StudentSection selectByPrimaryKey(Integer sectionId);

    int updateByExampleSelective(@Param("record") StudentSection record, @Param("example") StudentSectionExample example);

    int updateByExample(@Param("record") StudentSection record, @Param("example") StudentSectionExample example);

    int updateByPrimaryKeySelective(StudentSection record);

    int updateByPrimaryKey(StudentSection record);
}