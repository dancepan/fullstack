package com.tips.qbatch1.step;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

import com.tips.qbatch1.model.TableOne;
import com.tips.qbatch1.model.json.ReaderItemJson;

@Configuration
@StepScope
public class Processor implements ItemProcessor<List<ReaderItemJson>, List<TableOne>>
{
	private static final Logger logger = LoggerFactory.getLogger(Processor.class);
	
    @Override
    public List<TableOne> process(List<ReaderItemJson> list) throws Exception
    {
         List<TableOne> tableOneList = new ArrayList<TableOne>();
         
         for (ReaderItemJson readerItemJson : list)
         {
         	TableOne tableOne = new TableOne();
         	
         	tableOne.setId            (readerItemJson.getId         ());
         	tableOne.setReturntype    (readerItemJson.getDatatime   ());
         	tableOne.setCograde       (readerItemJson.getCograde    ());
         	tableOne.setCovalue       (readerItemJson.getCovalue    ());
         	tableOne.setDataterm      (readerItemJson.getDataterm   ());
         	tableOne.setDatatime      (readerItemJson.getDatatime   ());
         	tableOne.setKhaigrade     (readerItemJson.getKhaigrade  ());
         	tableOne.setKhaivalue     (readerItemJson.getKhaivalue  ());
         	tableOne.setMangname      (readerItemJson.getMangname   ());
         	tableOne.setNo2grade      (readerItemJson.getNo2grade   ());
         	tableOne.setNo2value      (readerItemJson.getNo2value   ());
         	tableOne.setNumofrows     (readerItemJson.getNumofrows  ());
         	tableOne.setO3grade       (readerItemJson.getO3grade    ());
             tableOne.setO3value       (readerItemJson.getO3value    ());
             tableOne.setPageno        (readerItemJson.getPageno     ());
             tableOne.setPm10grade     (readerItemJson.getPm10grade  ());
             tableOne.setPm10grade1h   (readerItemJson.getPm10grade1h());
             tableOne.setPm10value     (readerItemJson.getPm10value  ());
             tableOne.setPm10value24   (readerItemJson.getPm10value24());
             tableOne.setPm25grade     (readerItemJson.getPm25grade  ());
             tableOne.setPm25grade1h   (readerItemJson.getPm25grade1h());
             tableOne.setPm25value     (readerItemJson.getPm25value  ());
             tableOne.setPm25value24   (readerItemJson.getPm25value24());
             tableOne.setResultcode    (readerItemJson.getResultcode ());
             tableOne.setResultmsg     (readerItemJson.getResultmsg  ());
             tableOne.setRnum          (readerItemJson.getRnum       ());
             tableOne.setServicekey    (readerItemJson.getServicekey ());
             tableOne.setSidoname      (readerItemJson.getSidoname   ());
             tableOne.setSo2grade      (readerItemJson.getSo2grade   ());
             tableOne.setSo2value      (readerItemJson.getSo2value   ());
             tableOne.setStationcode   (readerItemJson.getStationcode());
             tableOne.setStationname   (readerItemJson.getStationname());
             tableOne.setTotalcount    (readerItemJson.getTotalcount ());
             tableOne.setVer           (readerItemJson.getVer        ());
         	
             tableOneList.add(tableOne);
         	
         	logger.error("[tableOneList] : " + tableOneList.toString());
         }
         
        return tableOneList;
    }
}
