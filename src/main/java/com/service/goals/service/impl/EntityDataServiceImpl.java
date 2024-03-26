package com.service.goals.service.impl;


import com.service.goals.model.EntityDataNode;
import com.service.goals.service.EntityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EntityDataServiceImpl implements EntityDataService {


//    @Autowired
//    public EntityDataServiceImpl(EntityDataNodeRepository entityDataNodeRepository,EntityManager entityManager){
//        this.entityDataNodeRepository = entityDataNodeRepository;
//        this.entityManager = entityManager;
//    }

    @Override
    public boolean saveEntityData(EntityDataNode entityDataNode){
        try {
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<EntityDataNode> getEntityDataNodesByAttributes(Map<String, Object> attributes) {
        try {
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<EntityDataNode> getAllEntityDataNodes(){
        try {
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public boolean deleteEntityNode(Long id) {
        try {
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
