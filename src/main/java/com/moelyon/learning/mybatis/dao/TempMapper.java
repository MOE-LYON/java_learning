package com.moelyon.learning.mybatis.dao;

import com.moelyon.learning.mybatis.entity.TempEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author moelyon
 */
@Mapper
public interface TempMapper {

    TempEntity findById(@Param("id") Integer id);

    List<TempEntity> findAll();

}
