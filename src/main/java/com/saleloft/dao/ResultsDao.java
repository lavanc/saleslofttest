package com.saleloft.dao;

import com.saleloft.util.PersistUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lchandra on 3/14/2017.
 */
@Component
public class ResultsDao {
    private static final Logger logger = LoggerFactory.getLogger(ResultsDao.class);
    public void save(Map<Integer,String[][]> result) {
        //This method in would persist to a database , but for the interview i have a hashmap
        // that gets saved to the file
        PersistUtil.WriteToFile(result,logger);
    }

    public Map<Integer,String[][]> retrieve(){
       return PersistUtil.readFromFile(logger);
    }

}
