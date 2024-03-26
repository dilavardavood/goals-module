package com.service.goals.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DataNodeJDBC {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DataNodeJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void deleteRecord(Long id) {
        String sql = "DELETE FROM data_node WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    public Boolean insertRecord(Map<String,Object> request) {
        try {
            Map<String, Object> dataNode = (Map<String, Object>) request.get("dataNode");
            // Insert into DataNodes table
            String dataNodeQuery = "INSERT INTO data_node (name, description, code, node_type, created_on, created_by, updated_on, updated_by) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP, ?)";
            jdbcTemplate.update(dataNodeQuery, dataNode.get("name"), dataNode.get("description"), dataNode.get("code"), dataNode.get("nodeType"), "admin", "admin");

            // Retrieve the ID of the newly inserted row in DataNodes table
            Long dataNodeId = jdbcTemplate.queryForObject("SELECT currval('datanodes_id_seq')", Long.class);

            // Insert into EDNProperty table
            String ednPropertyQuery = "INSERT INTO edn_property (entity_data_node_id, property_name, property_value) VALUES (?, ?, ?)";
            for (Map.Entry<String, Object> entry : ((Map<String, Object>) request.get("moreProperties")).entrySet()) {
                jdbcTemplate.update(ednPropertyQuery, dataNodeId, entry.getKey(), entry.getValue());
            }

            // Insert into DataNode_Relations table
            String dataNodeRelationsQuery = "INSERT INTO dn_relations (parentID, childID) VALUES (?, ?)";
            List<String> children = (List<String>) request.get("children");
            for (String child : children) {
                jdbcTemplate.update(dataNodeRelationsQuery, dataNode.get("code"), child);
            }

            return true;
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public Map<String, Object> getDataNodeDetails(String code) {
        Map<String, Object> result = new HashMap<>();
        try {
            // Query to retrieve data from DataNodes table
            String dataNodeQuery = "SELECT * FROM data_node WHERE code = ?";
            Map<String, Object> dataNode = jdbcTemplate.queryForMap(dataNodeQuery, code);
//            Long dataNodeId = (Long) dataNode.get("id");

            // Query to retrieve more properties from EDNProperty table
            String ednPropertyQuery = "SELECT * FROM edn_property WHERE entity_data_node_id = CAST(? AS VARCHAR)";
            List<Map<String, Object>> ednProperties = jdbcTemplate.queryForList(ednPropertyQuery,dataNode.get("id") );
            dataNode.put("moreProperties", ednProperties);

            // Query to retrieve children from DataNode_Relations table
            String dataNodeRelationsQuery = "SELECT childid FROM dn_relations WHERE parentid = ?";
            List<Map<String, Object>> children = jdbcTemplate.queryForList(dataNodeRelationsQuery, code);
            dataNode.put("children", children);

            result.put("dataNode", dataNode);
            return result;
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public Boolean updateRecord(Map<String,Object> request) {
        try {
            Map<String, Object> dataNode = (Map<String, Object>) request.get("dataNode");
            String code = (String) dataNode.get("code");

            // Check if the record with the provided code exists
            String checkExistingQuery = "SELECT COUNT(*) FROM data_node WHERE code = ?";
            int count = jdbcTemplate.queryForObject(checkExistingQuery, Integer.class, code);

            if (count == 0) {
                throw new RuntimeException("Record with code " + code + " does not exist. Update aborted.");
            }

            // Retrieve existing data node details
            String retrieveExistingQuery = "SELECT * FROM data_node WHERE code = ?";
            Map<String, Object> existingDataNode = jdbcTemplate.queryForMap(retrieveExistingQuery, code);

            // Prepare update query and parameters
            StringBuilder updateQuery = new StringBuilder("UPDATE data_node SET ");
            List<Object> params = new ArrayList<>();
            if (dataNode.containsKey("name") && dataNode.get("name") != null) {
                updateQuery.append("name = ?, ");
                params.add(dataNode.get("name"));
            } else {
                params.add(existingDataNode.get("name"));
            }
            if (dataNode.containsKey("description") && dataNode.get("description") != null) {
                updateQuery.append("description = ?, ");
                params.add(dataNode.get("description"));
            } else {
                params.add(existingDataNode.get("description"));
            }
            if (dataNode.containsKey("nodeType") && dataNode.get("nodeType") != null) {
                updateQuery.append("node_type = ?, ");
                params.add(dataNode.get("nodeType"));
            } else {
                params.add(existingDataNode.get("node_type"));
            }
            updateQuery.append("updated_on = CURRENT_TIMESTAMP, updated_by = ? WHERE code = ?");
            params.add("admin");
            params.add(code);

            // Execute update query
            jdbcTemplate.update(updateQuery.toString(), params.toArray());

            // Retrieve the ID of the data node
            Long dataNodeId = jdbcTemplate.queryForObject("SELECT id FROM data_node WHERE code = ?", Long.class, code);

            // Update EDNProperty
            Map<String, Object> moreProperties = (Map<String, Object>) request.get("moreProperties");
            if (moreProperties != null) {
                for (Map.Entry<String, Object> entry : moreProperties.entrySet()) {
                    String propertyName = entry.getKey();
                    String propertyValue = (String) entry.getValue();
                    String propertyQuery = "UPDATE edn_property SET property_value = ? WHERE entity_data_node_id = CAST(? AS VARCHAR) AND property_name = ?";
                    jdbcTemplate.update(propertyQuery, propertyValue, String.valueOf(dataNodeId), propertyName);
                }
            }

            // Update DataNode_Relations table (if needed)
            List<String> children = (List<String>) request.get("children");
            if (children != null) {
                // Delete existing relations for the data node
                String deleteQuery = "DELETE FROM dn_relations WHERE parentID = ?";
                jdbcTemplate.update(deleteQuery, code);
                // Insert new relations
                String insertQuery = "INSERT INTO dn_relations (parentID, childID) VALUES (?, ?)";
                for (String child : children) {
                    jdbcTemplate.update(insertQuery, code, child);
                }
            }

            return true;
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
