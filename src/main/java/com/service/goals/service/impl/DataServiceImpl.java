package com.service.goals.service.impl;

import com.service.goals.repository.DataNodeJDBC;
import com.service.goals.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class DataServiceImpl implements DataService {

    private final DataNodeJDBC dataNodeJDBC;

    @Autowired
    public DataServiceImpl(DataNodeJDBC dataNodeJDBC) {
        this.dataNodeJDBC = dataNodeJDBC;
    }

    @Override
    public boolean deleteRecord(Long id){
        dataNodeJDBC.deleteRecord(id);
        return true;
    }
    @Override
    public boolean saveDataNode(Map<String,Object> request) {
        try {
            if(request.containsKey("dataNode")){
                dataNodeJDBC.insertRecord(request);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Map<String,Object> getRecord(String code){
        try {
            return dataNodeJDBC.getDataNodeDetails(code);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean updateRecord(Map<String, Object> request){
        try {
            return dataNodeJDBC.updateRecord(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
