package com.example.quartz2.bean.reader;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.quartz2.model.ReaderReturnDTO;
import com.example.quartz2.model.reader.ReaderSourceDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.batch.item.ItemReader;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;

public class ReaderRestApiImpl implements ItemReader<List<ReaderReturnDTO>>
{
    private static final Logger log = LoggerFactory.getLogger(ReaderRestApiImpl.class);
    
    public ReaderRestApiImpl() {};
    
    private static int runningCount = 0;
    
    List<ReaderSourceDTO> readerSourceDTOList = new ArrayList<ReaderSourceDTO>();  // REST API 리턴 형식
    List<ReaderReturnDTO> readerReturnDTOList = new ArrayList<ReaderReturnDTO>();  // Processor 로 넘길 형식
    
    // Get Rest Api Data
    public List<ReaderReturnDTO> getResource(int runningCount)
    {
        log.info("[ReaderRestApiImpl] getResource() runningCount : " + runningCount);
        
        try
        {
            String uri = "http://localhost:8081/list";
            
            RestTemplate restTemplate = new RestTemplate();
            
            String response = restTemplate.getForObject(uri, String.class);
        
            ObjectMapper mapper = new ObjectMapper();
            
            JsonNode list = mapper.readTree(response).path("result").get("list");
            
            readerSourceDTOList = new ObjectMapper().readerFor(new TypeReference<ArrayList<ReaderSourceDTO>>() {}).readValue(list);
            
            for (ReaderSourceDTO readerSourceDTO : readerSourceDTOList)
            {
                ReaderReturnDTO readerReturnObj = new ReaderReturnDTO();
                
                readerReturnObj.setColumn1(readerSourceDTO.getDatatime   ());
                readerReturnObj.setColumn2(readerSourceDTO.getStationname());
                readerReturnObj.setColumn3(readerSourceDTO.getMangname   ());
                readerReturnObj.setColumn4(readerSourceDTO.getPm10grade  ());
                readerReturnObj.setColumn5(readerSourceDTO.getPm10value  ());
                
                readerReturnDTOList.add(readerReturnObj);
            }

            return readerReturnDTOList;
        }
        catch(Exception e)
        {
            log.info("[ReaderRestApiImpl]");
            
            e.printStackTrace();

            return null;
        }
    }
    
    @Override
    public List<ReaderReturnDTO> read()
    {
        List<ReaderReturnDTO> readerReturnDTOList = null;
        
        if (runningCount < 1)
        {
            readerReturnDTOList = this.getResource(runningCount);
            
            runningCount++;
        }

        return readerReturnDTOList;
    }
}
