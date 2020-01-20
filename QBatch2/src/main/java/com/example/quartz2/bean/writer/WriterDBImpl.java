package com.example.quartz2.bean.writer;

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

import com.example.quartz2.model.BizVO;
import com.example.quartz2.model.FileWriteDTO;
import com.example.quartz2.model.ProcessorReceiveDTO;
import com.example.quartz2.model.entity.BatchTarget;
import com.example.quartz2.repository.BatchTargetRepository;
import com.example.quartz2.service.BatchTargetService;
import com.example.quartz2.service.impl.BatchTargetServiceImpl;

@StepScope
public class WriterDBImpl implements ItemWriter<List<ProcessorReceiveDTO>>
{
    private static final Logger log = LoggerFactory.getLogger(WriterDBImpl.class);
    
    private JdbcBatchItemWriter<BatchTarget> batchTargetWriter;
    
    BatchTargetServiceImpl batchTargetServiceImpl;
    
    SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

    private static final String sql = "insert into batch_target (column1, column2, column3, column4, column5) "
                                    + "values "
                                    + "(:column1, :column2, :column3, :column4, :column5)";
    
    public WriterDBImpl()
    {
        dataSource.setDriver  (new org.postgresql.Driver());
        dataSource.setUrl     ("jdbc:postgresql://localhost:5432/tipsdb");
        dataSource.setUsername("tipsuser");
        dataSource.setPassword("tipsuser");
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
    public void write(List<? extends List<ProcessorReceiveDTO>> items) throws Exception
    {
        log.info("[WriterImplJpa] write() trades : " + items.toString());
        
        ArrayList <BatchTarget> batchTargetList = new ArrayList<BatchTarget>();
        
        for (List<ProcessorReceiveDTO> list : items)
        {
            list.forEach(recored ->
            {
                BatchTarget batchTarget = new BatchTarget();
                
                batchTarget.setColumn1(recored.getColumn1());
                batchTarget.setColumn2(recored.getColumn2());
                batchTarget.setColumn3(recored.getColumn3());
                batchTarget.setColumn4(recored.getColumn4());
                batchTarget.setColumn5(recored.getColumn5());
                
                batchTargetList.add(batchTarget);
            });
            
            this.batchTargetWriter.write(batchTargetList);
        }
    }
}