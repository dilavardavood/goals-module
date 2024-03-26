package com.service.goals.service;

import com.service.goals.dto.DataNodeDTO;
import com.service.goals.model.EntityDataNode;

import java.util.List;
import java.util.Map;

public interface EntityDataService {
    boolean saveEntityData(EntityDataNode entityDataNode);

    List<EntityDataNode> getEntityDataNodesByAttributes(Map<String, Object> attributes);

    List<EntityDataNode> getAllEntityDataNodes();

    boolean deleteEntityNode(Long id);
}
