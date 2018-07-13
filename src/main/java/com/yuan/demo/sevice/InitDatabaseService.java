package com.yuan.demo.sevice;

import com.yuan.demo.mapper.InitDatabaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitDatabaseService {
    @Autowired
    private InitDatabaseMapper initDatabaseMapper;

    public void reCreateTableTest(){
        initDatabaseMapper.reCreateTableTest();
    }
}
