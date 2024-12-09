package com.aloha.simplechat.service;

import com.aloha.simplechat.domain.Sample;

public interface SampleService  {
    
    public boolean insert(Sample sample);
    public Sample select(String id);
    public boolean update(Sample sample);
    public boolean delete(String id);
    
}
