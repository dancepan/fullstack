package com.tips.qbatch5.oracle.adw.service.jpa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tips.qbatch5.oracle.adw.repository.entity.Korea_Address_Sigu;
import com.tips.qbatch5.oracle.adw.repository.jpa.AddressSiGuJpaRepository;
import com.tips.qbatch5.oracle.adw.service.jpa.AddressSiGuJpaService;

@Service
 public class AddressSiGuJpaServiceImpl implements AddressSiGuJpaService {
    @Autowired
    AddressSiGuJpaRepository addressSiGuJpaRepository;

    //@Autowired
    //public void setDummyRepository(DummyRepository dummyRepository) {
    //    this.dummyRepository = dummyRepository;
    //}

    @Override
    public Iterable<Korea_Address_Sigu> getAllAddressSiGu() {
        return addressSiGuJpaRepository.findAll();
    }
 }