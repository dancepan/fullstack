package com.example.quartz.step;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

import com.example.quartz.etlprocess.FxMarketEvent;

/**
 * The Class FxMarketEventReader.
 *
 * @author ashraf
 */
public class ReaderImpl extends FlatFileItemReader<FxMarketEvent>
{
    public ReaderImpl()
    {
        // Set input file
        this.setResource(new ClassPathResource("trades_source.csv"));
        
        // Skip the file header line
        this.setLinesToSkip(1);
        
        // Line is mapped to item (FxMarketEvent) using setLineMapper(LineMapper)
        this.setLineMapper(new DefaultLineMapper<FxMarketEvent>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    { 
                        setNames(new String[] { "stock", "time", "price", "shares" });
                    }
                });
                
                setFieldSetMapper(new BeanWrapperFieldSetMapper<FxMarketEvent>() {
                    {
                        setTargetType(FxMarketEvent.class);
                    }
                });
            }
        });
    }
}
