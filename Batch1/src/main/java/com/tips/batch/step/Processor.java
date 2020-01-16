package com.tips.batch.step;

import org.springframework.batch.item.ItemProcessor;

public class Processor implements ItemProcessor<String, String>
{
    @Override
    public String process(String data) throws Exception
    {
        return "[ItemProcessor] " + data.toUpperCase();
    }
}