package com.tips.qbatch1.step;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tips.qbatch1.model.TableOne;
import com.tips.qbatch1.model.json.ReaderItemJson;
import com.tips.qbatch1.repository.TableOneRepository;
import com.tips.qbatch1.service.TableOneService;

import lombok.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

@Configuration
@StepScope
public class ReaderLocalApi implements ItemReader<List<ReaderItemJson>>
{
    private static final Logger logger = LoggerFactory.getLogger(ReaderLocalApi.class);
    
    List<ReaderItemJson> readerItemJsonList = new ArrayList<ReaderItemJson>();
    
    private RestTemplate restTemplate;

    private int nextAddressSiGuIndex;
    
    public ReaderLocalApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        nextAddressSiGuIndex = 0;
    }
    
    @Override
    public List<ReaderItemJson> read() throws Exception
    {
    	List<ReaderItemJson> readerItemJsonList = abcd();
    	
    	return readerItemJsonList;
    }
    
    private List<ReaderItemJson> abcd() throws IOException
    {
    	List<ReaderItemJson> readerItemJsonList = new ArrayList<ReaderItemJson>();
    	
    	JsonNode item = null;
    	
        try
        {
//            StringBuilder urlBuilder = new StringBuilder("http://localhost:8081/list");
//            
//            URL url = new URL(urlBuilder.toString());
//            
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Content-type", "application/json");
//            
//            BufferedReader rd;
//            
//            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300)
//            {
//                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            }
//            else
//            {
//                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//            }
//            
//            ObjectMapper mapper = new ObjectMapper();
//            
//            JsonNode root = mapper.readTree(rd);
//            
//            logger.error("[root] : " + root.toString());
//            
//            JsonNode item = null;
//            
//            item = root.path("result").get("list");
//            
//            logger.error("[item] : " + item.toString());
//            
//            readerItemJsonList = new ObjectMapper().readerFor(new TypeReference<ArrayList<ReaderItemJson>>() {}).readValue(item);
//            
//            logger.error("[readerItemJsonList] : " + readerItemJsonList.toString());
//            
//            rd.close();
//            
//            conn.disconnect();
            
        	URI uri = new URI("http://localhost:8081/list");
        	
        	ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        	
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            
            item = root.path("result").get("list");
            
        	readerItemJsonList = new ObjectMapper().readerFor(new TypeReference<ArrayList<ReaderItemJson>>() {}).readValue(item);


            
            // Write
//            List<TableOne> tableOneList = new ArrayList<TableOne>();
//            
//            for (ReaderItemJson readerItemJson : readerItemJsonList)
//            {
//            	TableOne tableOne = new TableOne();
//            	
//            	tableOne.setId            (readerItemJson.getId         ());
//            	tableOne.setReturntype    (readerItemJson.getDatatime   ());
//            	tableOne.setCograde       (readerItemJson.getCograde    ());
//            	tableOne.setCovalue       (readerItemJson.getCovalue    ());
//            	tableOne.setDataterm      (readerItemJson.getDataterm   ());
//            	tableOne.setDatatime      (readerItemJson.getDatatime   ());
//            	tableOne.setKhaigrade     (readerItemJson.getKhaigrade  ());
//            	tableOne.setKhaivalue     (readerItemJson.getKhaivalue  ());
//            	tableOne.setMangname      (readerItemJson.getMangname   ());
//            	tableOne.setNo2grade      (readerItemJson.getNo2grade   ());
//            	tableOne.setNo2value      (readerItemJson.getNo2value   ());
//            	tableOne.setNumofrows     (readerItemJson.getNumofrows  ());
//            	tableOne.setO3grade       (readerItemJson.getO3grade    ());
//                tableOne.setO3value       (readerItemJson.getO3value    ());
//                tableOne.setPageno        (readerItemJson.getPageno     ());
//                tableOne.setPm10grade     (readerItemJson.getPm10grade  ());
//                tableOne.setPm10grade1h   (readerItemJson.getPm10grade1h());
//                tableOne.setPm10value     (readerItemJson.getPm10value  ());
//                tableOne.setPm10value24   (readerItemJson.getPm10value24());
//                tableOne.setPm25grade     (readerItemJson.getPm25grade  ());
//                tableOne.setPm25grade1h   (readerItemJson.getPm25grade1h());
//                tableOne.setPm25value     (readerItemJson.getPm25value  ());
//                tableOne.setPm25value24   (readerItemJson.getPm25value24());
//                tableOne.setResultcode    (readerItemJson.getResultcode ());
//                tableOne.setResultmsg     (readerItemJson.getResultmsg  ());
//                tableOne.setRnum          (readerItemJson.getRnum       ());
//                tableOne.setServicekey    (readerItemJson.getServicekey ());
//                tableOne.setSidoname      (readerItemJson.getSidoname   ());
//                tableOne.setSo2grade      (readerItemJson.getSo2grade   ());
//                tableOne.setSo2value      (readerItemJson.getSo2value   ());
//                tableOne.setStationcode   (readerItemJson.getStationcode());
//                tableOne.setStationname   (readerItemJson.getStationname());
//                tableOne.setTotalcount    (readerItemJson.getTotalcount ());
//                tableOne.setVer           (readerItemJson.getVer        ());
//            	
//                tableOneList.add(tableOne);
//            	
//            	logger.error("[tableOneList] : " + tableOneList.toString());
//            }
            
            //tableOneService.saveTableOneAllList(tableOneList);
            
          return readerItemJsonList;
          //return null;
        }
        catch(Exception e)
        {
            logger.error("Error...");
            
            e.printStackTrace();

            return null;
        }
    }
}
