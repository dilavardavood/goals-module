package com.service.goals.controller;

import com.service.goals.model.DataNode;
import com.service.goals.service.DataService;
import com.service.goals.utils.Constants;
import com.service.goals.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(Constants.DATANODE_ENDPOINT)
public class DataController {

    @Autowired
    DataService dataService;

    @PostMapping(Constants.CREATE_NODE)
    public ResponseEntity<Response<Boolean>> createDataNode(@RequestBody Map<String,Object> request) {
        try {
            boolean result = dataService.saveDataNode(request);
            Response<Boolean> response = new Response<>(Constants.SUCCESS, null, HttpStatus.CREATED.value(), result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Response<Boolean> errorResponse = new Response<>(Constants.ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    @PatchMapping(Constants.UPDATE_NODE)
    public ResponseEntity<Response<Boolean>> updateRecord(@RequestBody Map<String,Object> request) {
        try {
            boolean result = dataService.updateRecord(request);
            Response<Boolean> response = new Response<>(Constants.SUCCESS, null, HttpStatus.OK.value(), result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Response<Boolean> errorResponse = new Response<>(Constants.ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    @GetMapping("/get")
    public ResponseEntity<Response<Map<String,Object>>> getRecord(@RequestParam String code) {
        try {
            Map<String,Object> result = dataService.getRecord(code);
            Response<Map<String,Object> > response = new Response<>(Constants.SUCCESS, null, HttpStatus.OK.value(), result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Response<Map<String,Object>> errorResponse = new Response<>(Constants.ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    @GetMapping(Constants.GET_ALL_NODE)
    public ResponseEntity<Response<List<DataNode>>> getAllDataNodes() {
//        try {
//            List<DataNode> dataNodes = dataService.getAllDataNodes();
//            Response<List<DataNode>> response = new Response<>(Constants.SUCCESS, null, HttpStatus.OK.value(), dataNodes);
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            Response<List<DataNode>> errorResponse = new Response<>(Constants.ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//        }
        return null;
    }
    @GetMapping(Constants.FILTER_BY)
    public ResponseEntity<Response<List<DataNode>>> getDataNodesByAttributes(@RequestParam Map<String, Object> attributes) {
        List<DataNode> dataNodeList = null;
//        try {
//            dataNodeList = dataService.getDataNodesByAttributes(attributes);
//            Response<List<DataNode>> response = new Response<>(Constants.SUCCESS, null, 200, dataNodeList);
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            Response<List<DataNode>> errorResponse = new Response<>(Constants.ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//        }
        return null;
    }

    @DeleteMapping("/delete/")
    public ResponseEntity<Response<Boolean>> deleteRecord(@RequestParam Long id){
        try {
            boolean result = dataService.deleteRecord(id);
            Response response = new Response(Constants.SUCCESS,null,200,result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Response response = new Response(Constants.ERROR, e.getMessage(), 500,null);
            return ResponseEntity.ok(response);
        }
    }

}
