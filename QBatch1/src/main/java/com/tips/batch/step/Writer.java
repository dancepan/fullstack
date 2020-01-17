package com.tips.batch.step;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;

import com.tips.batch.entity.FineDust;

public class Writer implements ItemWriter<List<FineDust>>
{
    @Override
    public void write(List<? extends List<FineDust>> lists) throws Exception
    {
        //for (String msg : messages)
        //{
        //    System.out.println("[ItemWriter] " + msg);
        //}
        
        System.out.println("------------ writer----------------");
        
        
        return;
    }

}
