package com.service.goals.service;

import com.service.goals.dto.DataNodeDTO;
import com.service.goals.model.DataNode;

import java.util.List;
import java.util.Map;

public interface DataService {
    boolean deleteRecord(Long id);

    boolean saveDataNode(Map<String,Object> request);

    Map<String,Object> getRecord(String code);

    Boolean updateRecord(Map<String, Object> request);

//    boolean saveDataNode(DataNode dataNode);

//    boolean updateDataNode(Long id, DataNode updatedDataNode);

//    List<DataNode> getAllDataNodes(String type);

//    List<DataNode> getAllDataNodes();

//    List<DataNode> getDataNodesByAttributes(Map<String, Object> attributes);

//    boolean deleteNode(Long id);
    // Add other methods as needed
}
