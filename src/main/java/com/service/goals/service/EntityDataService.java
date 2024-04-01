package com.service.goals.service;

import com.service.goals.dto.EntityDataNodeDTO;
import com.service.goals.model.EntityDataNode;

import java.util.List;
import java.util.Map;

public interface EntityDataService {

    boolean saveEntityData(EntityDataNodeDTO entityDataNodeDTO);

    boolean updateEntityData(Long id, EntityDataNodeDTO entityDataNodeDTO);

    List<EntityDataNode> getEntityDataNodesByAttributes(Map<String, Object> attributes);

    List<EntityDataNodeDTO> getAllEntityDataNodes();

    EntityDataNodeDTO getEntityNodesById(Long id);

    List<EntityDataNodeDTO> filterSearch(EntityDataNodeDTO entityDataNodeDTO);

    boolean deleteEntityNode(Long id);
}
