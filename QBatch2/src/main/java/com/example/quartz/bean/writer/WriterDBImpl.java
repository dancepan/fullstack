package com.example.quartz.bean.writer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.example.quartz.model.ProcessorReceiveDTO;
import com.example.quartz.model.entity.BatchTarget;
import com.example.quartz.repository.BatchTargetRepository;
import com.example.quartz.service.BatchTargetService;
import com.example.quartz.model.BizVO;
import com.example.quartz.model.FileWriteDTO;

/**
 * The Class StockPriceAggregator.
 * 
 * @author ashraf
 */
@StepScope
public class WriterDBImpl implements ItemWriter<ProcessorReceiveDTO>
{
	private static final Logger log = LoggerFactory.getLogger(WriterDBImpl.class);
	
	private JdbcBatchItemWriter<BatchTarget> batchTargetWriter;

    @Autowired
    public SimpleDriverDataSource dataSource;
	
	private static final String sql = "insert into batch_target (column1, column2, column3, column4, column5) "
			                        + "values "
			                        + "(:column1, :column2, :column3, :column4, :column5)";
	
	public WriterDBImpl()
	{
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		
        dataSource.setDriver  (new org.postgresql.Driver());
        dataSource.setUrl     ("jdbc:postgresql://localhost:5432/tipsdb");
        dataSource.setUsername("tipsuser");
        dataSource.setPassword("tipsuser");
        
        this.dataSource = dataSource;
	}

    @BeforeStep
    public void prepareForWriter()
    {
        this.batchTargetWriter = new JdbcBatchItemWriter<BatchTarget>();
        this.batchTargetWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<BatchTarget>());
        this.batchTargetWriter.setDataSource(dataSource);
        this.batchTargetWriter.setJdbcTemplate(new NamedParameterJdbcTemplate(dataSource));
        this.batchTargetWriter.setSql(sql);
        this.batchTargetWriter.afterPropertiesSet();
    }
    
    @Override
    public void write(List<? extends ProcessorReceiveDTO> trades) throws Exception
    {
    	log.info("[WriterImplJpa] write() trades : " + trades.toString());
    	
    	ArrayList <BatchTarget> batchTargetList = new ArrayList<BatchTarget>();
    	
    	trades.forEach(record -> 
    	{
    		BatchTarget batchTarget = new BatchTarget();
    		
    		batchTarget.setColumn1(record.getId    ());
    		batchTarget.setColumn2(record.getPrice ());
    		batchTarget.setColumn3(record.getTime  ());
    		batchTarget.setColumn4(record.getShares());
    		batchTarget.setColumn5(record.getStock ());
    		
    		batchTargetList.add(batchTarget);
    	});
    	
    	this.batchTargetWriter.write(batchTargetList);
    }
}
