package com.tips.batch.service;

import java.util.List;


import com.tips.batch.entity.FineDustStageMock;
import com.tips.batch.repository.FineDustStageMokeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FineDustStageMockServiceImpl implements FineDustStageMockService {
    @Autowired
    FineDustStageMokeRepository fineDustStageMokeRepository;

//    @Autowired
//    EstateRealTrxHouseRepository estateRealTrxHouseRepository;
//
//    @Autowired
//    EstateRealTrxMansionRepository estateRealTrxMansionRepository;
//
//    @Override
//    public void saveFineDustMockAll(List<FineDustStageMock> fineDustStageMock) {
//        fineDustStageMokeRepository.saveAll(fineDustStageMock);
//    }

    @Override
    public void saveFineDustMockAll(List<FineDustStageMock> fineDustStageMock)
    {
        fineDustStageMokeRepository.saveAll(fineDustStageMock);
        
    }

//    @Override
//    public void saveAptTrade(Estate_Real_Apt_Trx estate_Real_Trx) {
//        estateRealTrxAptRepository.save(estate_Real_Trx);
//    }
//
//    @Override
//    public void saveHouseTradeAll(List<Estate_Real_House_Trx> estate_Real_Trx_list) {
//        estateRealTrxHouseRepository.saveAll(estate_Real_Trx_list);
//    }
//
//    @Override
//    public void saveHouseTrade(Estate_Real_House_Trx estate_Real_Trx) {
//        estateRealTrxHouseRepository.save(estate_Real_Trx);
//    }
//
//    @Override
//    public void saveMansionTradeAll(List<Estate_Real_Mansion_Trx> estate_Real_Trx_list) {
//        estateRealTrxMansionRepository.saveAll(estate_Real_Trx_list);
//    }
//
//    @Override
//    public void saveMansionTrade(Estate_Real_Mansion_Trx estate_Real_Trx) {
//        estateRealTrxMansionRepository.save(estate_Real_Trx);
//    }
 }