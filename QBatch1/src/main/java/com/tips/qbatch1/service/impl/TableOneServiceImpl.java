package com.tips.qbatch1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tips.qbatch1.model.TableOne;
import com.tips.qbatch1.repository.TableOneRepository;
import com.tips.qbatch1.service.TableOneService;


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