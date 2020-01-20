package com.example.quartz2.bean.processor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import com.example.quartz2.model.ProcessorReceiveDTO;
import com.example.quartz2.model.ReaderReturnDTO;

public class ProcessorImpl implements ItemProcessor<List<ReaderReturnDTO>, List<ProcessorReceiveDTO>>
{
    private static final Logger log = LoggerFactory.getLogger(ProcessorImpl.class);

    List<ProcessorReceiveDTO> processorReceiveDTO = new ArrayList<ProcessorReceiveDTO>();
    
    @Override
    public List<ProcessorReceiveDTO> process(final List<ReaderReturnDTO> items) throws Exception
    {
        for (ReaderReturnDTO record : items)
        {
            ProcessorReceiveDTO ProcessorReceiveDTOObj = new ProcessorReceiveDTO();
            
            ProcessorReceiveDTOObj.setColumn1(record.getColumn1());
            ProcessorReceiveDTOObj.setColumn2(record.getColumn2());
            ProcessorReceiveDTOObj.setColumn3(record.getColumn3());
            ProcessorReceiveDTOObj.setColumn4(record.getColumn4());
            ProcessorReceiveDTOObj.setColumn5(record.getColumn5());
            
            processorReceiveDTO.add(ProcessorReceiveDTOObj);
        }
        
        return processorReceiveDTO;
    }
}
