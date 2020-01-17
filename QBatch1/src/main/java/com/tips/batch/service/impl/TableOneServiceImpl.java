package com.tips.batch.service.impl;

import java.util.List;

import com.tips.batch.model.TableOne;
import com.tips.batch.repository.TableOneRepository;
import com.tips.batch.service.TableOneService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableOneServiceImpl implements TableOneService {
    @Autowired
    TableOneRepository tableOneRepository;

    @Override
    public void saveTableOneAllList(List<TableOne> tableOneList)
    {
    	tableOneRepository.saveAll(tableOneList);
    }
}