package com.jt.idea.manage.mapper;

import com.jt.idea.common.po.StudentBook;
import com.jt.idea.common.po.StudentBookExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentBookMapper {
    int countByExample(StudentBookExample example);

    int deleteByExample(StudentBookExample example);

    int deleteByPrimaryKey(Integer bookId);

    int insert(StudentBook record);

    int insertSelective(StudentBook record);

    List<StudentBook> selectByExample(StudentBookExample example);

    StudentBook selectByPrimaryKey(Integer bookId);

    int updateByExampleSelective(@Param("record") StudentBook record, @Param("example") StudentBookExample example);

    int updateByExample(@Param("record") StudentBook record, @Param("example") StudentBookExample example);

    int updateByPrimaryKeySelective(StudentBook record);

    int updateByPrimaryKey(StudentBook record);
}