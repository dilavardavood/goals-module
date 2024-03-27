package com.service.goals.service.impl;

import com.service.goals.dto.DataNodeDTO;
import com.service.goals.repository.DataNodeJDBC;
import com.service.goals.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class DataServiceImpl implements DataService {

    private final DataNodeJDBC dataNodeJDBC;

    @Autowired
    public DataServiceImpl(DataNodeJDBC dataNodeJDBC) {
        this.dataNodeJDBC = dataNodeJDBC;
    }

    @Override
    public boolean retireNode(Long id){
        dataNodeJDBC.deleteRecord(id);
        return true;
    }
    @Override
    public boolean createNode(DataNodeDTO dataNodeDTO) {
        try {
                dataNodeJDBC.insertRecord(dataNodeDTO);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Map<String, Object>> readAllNodes(){
        try {
            return dataNodeJDBC.gelAllDetails();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Map<String,Object> readNodeById(Long id){
        try {
            return dataNodeJDBC.getDataNodeDetails(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean updateNode(Long id, DataNodeDTO dataNodeDTO){
        try {
            return dataNodeJDBC.updateRecord(id, dataNodeDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Map<String,Object>> filterSearch(Map<String, Object> filter){
        try {
            return dataNodeJDBC.getFilteredDetails(filter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
