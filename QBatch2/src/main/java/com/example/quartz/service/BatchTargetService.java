package com.example.quartz.service;

import java.security.InvalidParameterException;
import java.util.List;

import com.example.quartz.model.entity.BatchTarget;

public interface BatchTargetService
{
	List<BatchTarget> findAll() throws InvalidParameterException;
	void              saveAll(List<BatchTarget> batchTargetList) throws InvalidParameterException;
}
