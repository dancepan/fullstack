package com.tips.restapi.service;

import java.security.InvalidParameterException;

import com.tips.restapi.model.json.FineDustJson;

public interface FineDustService
{
    public FineDustJson fineDustJson() throws InvalidParameterException;
}
