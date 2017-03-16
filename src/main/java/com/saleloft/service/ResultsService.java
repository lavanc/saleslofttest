package com.saleloft.service;

import com.saleloft.dao.ResultsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lchandra on 3/14/2017.
 */
@Service
public class ResultsService {
    private final AtomicInteger count = new AtomicInteger(0);
    private Map<Integer, String[][]> STORAGE = new HashMap<>();
    @Autowired
    ResultsDao dao;

    public void saveResults(String[][] result){
        STORAGE.put(count.incrementAndGet(), result);
        //call persistResults to sve in file
        persistResults();
    }

    public void persistResults(){
        dao.save(STORAGE);
    }
}
