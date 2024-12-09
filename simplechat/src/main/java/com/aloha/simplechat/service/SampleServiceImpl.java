package com.aloha.simplechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.simplechat.domain.Sample;
import com.aloha.simplechat.mapper.SampleMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SampleServiceImpl implements SampleService  {

    @Autowired private SampleMapper sampleMapper;

    @Override
    public boolean insert(Sample sample) {
        log.info("insert: " + sample);
        return sampleMapper.insert(sample) > 0;
    }

    @Override
    public Sample select(String id) {
        log.info("select: " + id);
        return sampleMapper.select(id);
    }

    @Override
    public boolean update(Sample sample) {
        log.info("update: " + sample);
        return sampleMapper.update(sample) > 0;
    }

    @Override
    public boolean delete(String id) {
        log.info("delete: " + id);
        return sampleMapper.delete(id) > 0;
    }

    
}
