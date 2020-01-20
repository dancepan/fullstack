package com.tips.backend1.service;

import java.security.InvalidParameterException;

import com.tips.backend1.model.json.FineDustJson;

public interface FineDustService
{
    public FineDustJson findFineDustJson() throws InvalidParameterException;
}
