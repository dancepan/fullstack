package com.tips.batch.step;

import java.text.ParseException;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.web.client.RestTemplate;

import lombok.Data;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Reader implements ItemReader<String>
{
    private String[] messages = { "Welcome to Spring Batch Example", "We use H2 Database for this example" };

    private int count = 0;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException
    {
//        if (count < messages.length)
//        {
//            return "[ItemReader] " + messages[count++];
//        }
//        else
//        {
//            count = 0;
//        }

        StringBuffer result = new StringBuffer();
        
        try
        {
            String serviceId     = "ArpltnInforInqireSvc"    ;  // 대기오염정보 조회 서비스
            String operationName = "getCtprvnRltmMesureDnsty";  // 시도별 실시간 측정정보 조회
            String serviceKey    = "bg9choiwFZX5JYcIIF76jFiVYe0VwiWdxdpCUldbALWxzJLNZA4Ipq2Z1SVqkZyWSW88og%2Bt8EiOCX9J%2BB3ZUw%3D%3D";
            String numOfRows     = "1" ;
            String pageNo        = "1"   ;
            String sidoName      = "서울" ;
            String version       = "1.3" ;
            String returnType    = "json";
            
            String strUrl = "http://openapi.airkorea.or.kr/openapi/services/rest/"
                          + serviceId
                          + "/"
                          + operationName
                          + "?" + "serviceKey="   + serviceKey
                          + "&" + "numOfRows="    + numOfRows
                          + "&" + "pageNo="       + pageNo
                          + "&" + "sidoName="     + sidoName
                          + "&" + "ver="          + version
                          + "&" +  "_returnType=" + returnType;
            
            URL url = new URL(strUrl);
            
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
            
            urlconnection.setRequestMethod("GET");
                
            BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
            
            String returnLine;
            
            result.append("<xmp>");
            
            while((returnLine = br.readLine()) != null)
            {
                result.append(returnLine + "\n");
            }
                
            urlconnection.disconnect();
        }
        catch(Exception e)
        {
        }
            
        return result + "</xmp>";
    }
}
