package com.aloha.simplechat.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.aloha.simplechat.domain.Sample;


@Mapper
public interface SampleMapper {

    public int insert(Sample sample);

    public Sample select(@Param("id") String id);

    public int update(Sample sample);

    public int delete(@Param("id") String id);

}
