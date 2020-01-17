package com.tips.batch.step;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tips.batch.model.json.ReaderItemJson;

import lombok.Data;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class ReaderPublicApi implements ItemReader<List<ReaderItemJson>>
{
    private static final Logger logger = LoggerFactory.getLogger(ReaderPublicApi.class);
    List<ReaderItemJson> fineDust = new ArrayList<ReaderItemJson>();
    
    @Override
    public List<ReaderItemJson> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException
    {
        try
        {
            // 미세먼지 공공 REST API
            String serviceId     = "ArpltnInforInqireSvc"    ;  // 대기오염정보 조회 서비스
            String operationName = "getCtprvnRltmMesureDnsty";  // 시도별 실시간 측정정보 조회
            String serviceKey    = "bg9choiwFZX5JYcIIF76jFiVYe0VwiWdxdpCUldbALWxzJLNZA4Ipq2Z1SVqkZyWSW88og%2Bt8EiOCX9J%2BB3ZUw%3D%3D";
            String numOfRows     = "1"   ;
            String pageNo        = "1"   ;
            String sidoName      = "서울" ;
            String version       = "1.3" ;
            String returnType    = "json";
            
            StringBuilder urlBuilder = new StringBuilder("http://openapi.airkorea.or.kr/openapi/services/rest/");
            
            urlBuilder.append(serviceId
                          + "/"
                          + operationName
                          + "?" + "serviceKey="   + serviceKey
                          + "&" + "numOfRows="    + numOfRows
                          + "&" + "pageNo="       + pageNo
                          + "&" + "sidoName="     + sidoName
                          + "&" + "ver="          + version
                          + "&" +  "_returnType=" + returnType);
            
            URL url = new URL(urlBuilder.toString());
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            
            BufferedReader rd;
            
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300)
            {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }
            else
            {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(rd);
           
            JsonNode item = null;
          //item = root.path("response").path("body").path("items").get("item");
            item = root.path("list");
            
            fineDust = new ObjectMapper().readerFor(new TypeReference<ArrayList<ReaderItemJson>>() {}).readValue(item);
            
            rd.close();
            
            conn.disconnect();
            
            return fineDust;
        }
        catch(Exception e)
        {
            logger.error("Error//");
            
            e.printStackTrace();

            return null;
        }
    }
}
