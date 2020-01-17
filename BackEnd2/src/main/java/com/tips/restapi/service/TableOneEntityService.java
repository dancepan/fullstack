package com.tips.restapi.service;

import java.security.InvalidParameterException;

import com.tips.restapi.model.json.TableOneJson;

public interface TableOneEntityService
{
    public TableOneJson findTableOneEntity() throws InvalidParameterException;
}
