package com.tips.restapi.model.json;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FineDustJson
{
    private String datatime;
    private String stationname;
    private String mangname;
    private String so2value;
    private String covalue;
    private String o3value;
    private String no2value;
    private String pm10value;
    
    @JsonProperty("fineDustList")
    public ArrayList<FineDustJson> FineDustList;
}
