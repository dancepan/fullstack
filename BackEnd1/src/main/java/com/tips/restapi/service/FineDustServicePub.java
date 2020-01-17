package com.tips.restapi.service;

import java.security.InvalidParameterException;

import com.tips.restapi.model.json.FineDustJson;
import com.tips.restapi.model.json.FineDustStageJson;

public interface FineDustServicePub
{
    public FineDustStageJson findFineDustStageJson() throws InvalidParameterException;;
}
