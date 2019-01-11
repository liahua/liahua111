package com.jt.idea.manage.mapper;

import com.jt.idea.common.po.IndexBook;
import com.jt.idea.common.po.IndexBookExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IndexBookMapper {
    int countByExample(IndexBookExample example);

    int deleteByExample(IndexBookExample example);

    int deleteByPrimaryKey(Long id);

    int insert(IndexBook record);

    int insertSelective(IndexBook record);

    List<IndexBook> selectByExample(IndexBookExample example);

    IndexBook selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") IndexBook record, @Param("example") IndexBookExample example);

    int updateByExample(@Param("record") IndexBook record, @Param("example") IndexBookExample example);

    int updateByPrimaryKeySelective(IndexBook record);

    int updateByPrimaryKey(IndexBook record);
}