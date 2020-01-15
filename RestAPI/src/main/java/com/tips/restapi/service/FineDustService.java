package com.tips.restapi.service;

import java.security.InvalidParameterException;

import com.tips.restapi.model.json.FineDustList;

public interface FineDustService
{
    public FineDustList findFineDustList() throws InvalidParameterException;
}
