// File: com/example/evmanagement/dao/EnergyLogDAO.java
package com.example.evmanagement.dao;

import com.example.evmanagement.model.EnergyLog;
import com.example.evmanagement.util.FileUtil;

import java.io.IOException;
import java.util.List;

public class EnergyLogDAO {
    private static final String FILE_PATH = "data/energyLogs.dat";

    public void saveEnergyLogs(List<EnergyLog> logs) throws IOException {
        FileUtil.writeObjectToFile(logs, FILE_PATH);
    }

    @SuppressWarnings("unchecked")
    public List<EnergyLog> loadEnergyLogs() throws IOException, ClassNotFoundException {
        return (List<EnergyLog>) FileUtil.readObjectFromFile(FILE_PATH);
    }
}
