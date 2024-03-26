package com.service.goals.utils;

public class Constants {

    public static final String OK = "Ok";
    public static final String ERROR = "Error";
    public static final String SUCCESS = "Success";
    public static final String CREATE_NODE = "/create";
    public static final String UPDATE_NODE = "/update";
    public static final String GET_ALL_NODE = "/get/all";
    public static final String FILTER_BY = "/filter";
    public static final String DATANODE_ENDPOINT = "data/node";

    public interface DataNodes {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String NODE_TYPE = "node_type";
        public static final String CODE = "code";
        public static final String CREATED_ON = "created_on";
        public static final String CREATED_BY = "created_by";
        public static final String UPDATED_ON = "updated_on";
        public static final String UPDATED_BY = "updated_by";

    }
    public interface EntityDataNode {
        public static final String ID = "id";
        public static final String DATANODE_ID = "datanode_id";
        public static final String ENTITY_ID = "entity_id";
        public static final String NODE_TYPE = "node_type";
        public static final String ENTITY_TYPE = "entity_type";
        public static final String CREATED_ON = "created_on";
        public static final String CREATED_BY = "created_by";
        public static final String UPDATED_ON = "updated_on";
        public static final String UPDATED_BY = "updated_by";

    }
    public interface EDNProps {
        public static final String ID = "id";
        public static final String EDN_ID = "entity_data_node_id";
        public static final String PROPERTY_NAME = "property_name";
        public static final String PROPERTY_VALUE = "property_value";

    }

}
