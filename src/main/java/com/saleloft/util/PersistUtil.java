package com.saleloft.util;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.slf4j.Logger;

import java.io.*;
import java.util.Map;

/**
 * Created by lchandra on 3/15/2017.
 */
public class PersistUtil {
    private PersistUtil(){}
    private static Gson gson = new Gson();

    public static Map<Integer,String[][]> readFromFile(Logger logger) {
        File file = new File(System.getProperty("user.dir"));
        Map<Integer,String[][]> results=null;
        if (!file.exists())
            logger.info("File doesn't exist");

        InputStreamReader isReader;
        try {
            isReader = new InputStreamReader(new FileInputStream(file), "UTF-8");

            JsonReader myReader = new JsonReader(isReader);
           results = gson.fromJson(myReader, Map.class);



        } catch (Exception e) {
            logger.error("error load cache from file " + e.toString());
        }

       return results;
    }

    public static void WriteToFile(Map<Integer,String[][]> myData,Logger logger) {
        File file = new File(System.getProperty("user.dir"));
        if (!file.exists()) {
            try {
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            } catch (IOException e) {
                logger.error("Excepton Occured: " + e.toString());
            }
        }

        try {
            // Convenience class for writing character files
            FileWriter writer;
            writer = new FileWriter(file.getAbsoluteFile(), true);

            // Writes text to a character-output stream
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(gson.toJson(myData));
            bufferWriter.close();

            logger.info("Company data saved at file location: " + System.getProperty("user.dir") + " Data: " + myData + "\n");
        } catch (IOException e) {
            logger.error("Hmm.. Got an error while saving Company data to file " + e.toString());
        }
    }
}
