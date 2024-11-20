package org.example.taskify.backend.appData;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DataReader<T> {

    private final Class<T> type;

    private final String dataFileName;

    private final String path = "src/main/resources/data/";

    private final ObjectMapper mapper = new ObjectMapper();

    public DataReader(Class<T> dataType,String dataFileName) {
        this.dataFileName = dataFileName;
        this.type = dataType;
    }

    public void writeData(Record record){
        try {
            mapper.writeValue(new File(path + dataFileName), record);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Record readData(){
        try {
            return (Record) mapper.readValue(new File(path + dataFileName), type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
