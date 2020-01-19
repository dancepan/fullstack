package com.example.quartz.service.impl;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.quartz.model.ProcessorReceiveDTO;
import com.example.quartz.model.entity.BatchTarget;
import com.example.quartz.repository.BatchTargetRepository;
import com.example.quartz.service.BatchTargetService;



@Transactional
@Service
public class BatchTargetServiceImpl implements BatchTargetService
{
    @Autowired
    BatchTargetRepository batchTargetRepository;

    public List<BatchTarget> findAll() throws InvalidParameterException 
    {
        List<BatchTarget> batchTargetList = this.batchTargetRepository.findAll();

        return batchTargetList;
    }
    
    public void saveAll(List<BatchTarget> batchTargetList) throws InvalidParameterException 
    {
        this.batchTargetRepository.saveAll(batchTargetList);
    }
}
