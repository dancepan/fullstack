package com.tips.backend1.service;

import java.security.InvalidParameterException;

import com.tips.backend1.model.json.FineDustJson;
import com.tips.backend1.model.json.FineDustStageJson;

public interface FineDustServicePub
{
    public FineDustStageJson findFineDustStageJson() throws InvalidParameterException;;
}
