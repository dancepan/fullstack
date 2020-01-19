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
public class WriterJpaImpl implements ItemWriter<ProcessorReceiveDTO>
{
	private static final Logger log = LoggerFactory.getLogger(WriterJpaImpl.class);
	
	private JdbcBatchItemWriter<BatchTarget> delegate;
	

    @Autowired
    public SimpleDriverDataSource dataSource;
    
	
	private static final String sql = "insert into batch_target (column1, column2, column3, column4, column5) values (:column1, :column2, :column3, :column4, :column5)";
	
	public WriterJpaImpl() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriver(new  org.postgresql.Driver());
        dataSource.setUrl("jdbc:postgresql://localhost:5432/tipsdb");
        dataSource.setUsername("tipsuser");
        dataSource.setPassword("tipsuser");
        
        this.dataSource = dataSource;
	}

    public WriterJpaImpl(DataSource dataSource) {
        //this.dataSource = dataSource;
    }
	
    @BeforeStep
    public void prepareForWriter() {
        this.delegate = new JdbcBatchItemWriter<BatchTarget>();
        this.delegate.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<BatchTarget>());
        this.delegate.setDataSource(dataSource);
        this.delegate.setJdbcTemplate(new NamedParameterJdbcTemplate(dataSource));
        this.delegate.setSql(sql);
        this.delegate.afterPropertiesSet();

    }
    
    @Override
    public void write(List<? extends ProcessorReceiveDTO> trades) throws Exception
    {
    	log.info("[WriterImplJpa] write() trades : " + trades.toString());
    	
    	//JdbcBatchItemWriter<BatchTarget> batchTargetWriter = new JdbcBatchItemWriter<>();
    	
    	//batchTargetWriter.setAssertUpdates(false);
    	//batchTargetWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
    	//batchTargetWriter.setSql("INSERT INTO public.batch_target (column1) values ('111111111')");
    	
    	ArrayList <BatchTarget> batchTargetList = new ArrayList<BatchTarget>();
    	BatchTarget batchTarget = new BatchTarget();
    	
    	batchTarget.setColumn1("1111111111");
    	batchTarget.setColumn2("1111111111");
    	batchTarget.setColumn3("1111111111");
    	batchTarget.setColumn4("1111111111");
    	batchTarget.setColumn5("1111111111");
    	
    	batchTargetList.add(batchTarget);
    	
    	//batchTargetWriter.write(batchTargetList);

    	this.delegate.write(batchTargetList);
    	
    	return;
    }
}
