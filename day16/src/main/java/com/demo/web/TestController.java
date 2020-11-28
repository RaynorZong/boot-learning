package com.demo.web;

import com.demo.impl.HtxxServiceImpl;
import com.demo.model.node.Htxx;
import com.demo.util.Neo4jUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private Neo4jUtil neo4jUtil;

    @GetMapping("/get")
    public Map<String, Object> get(@RequestParam  String name){
        Map<String, Object> retMap = new HashMap<>();
        String cql = "match (m:Htxx) where m.name='"+name+"' return m ";
        Set<Map<String ,Object>> nodeList = new HashSet<>();
        neo4jUtil.getList(cql,nodeList);
        retMap.put("nodeList",nodeList);
        return retMap;
    }
    @GetMapping("/get2")
    public Map<String, Object> get2(@RequestParam(required = false) String name){
        Map<String, Object> retMap = new HashMap<>();
        //cql语句  ID()可以获取节点自动生成的id
        String cql = "match l=(m)-[]-(n)  return l limit 200";
        //待返回的值，与cql return后的值顺序对应
        Set<Map<String ,Object>> nodeList = new HashSet<>();
        Set<Map<String ,Object>> edgeList = new HashSet<>();
        neo4jUtil.getPathList(cql,nodeList,edgeList);
        retMap.put("nodeList",nodeList);
        retMap.put("edgeList",edgeList);



        return retMap;
    }
    @GetMapping("/d3")
    public Map<String,Object> get3(@RequestParam(required = false) String name){
        //cql语句  ID()可以获取节点自动生成的id
        String cql = "match l=(m)-[]-(n)  return l limit 1";
        //待返回的值，与cql return后的值顺序对应
        Set<Map<String ,Object>> nodeList = new HashSet<>();
        Set<Map<String ,Object>> edgeList = new HashSet<>();
        neo4jUtil.getPathList(cql,nodeList,edgeList);

        List<Map> edgeListRes = new ArrayList<>();
        for(Map map: edgeList){
            Map<String,String> map1 = new HashMap<>();
            map1.put("source",map.get("fromNodeId").toString());
            map1.put("target",map.get("toNodeId").toString());
            map1.put("rela",map.get("label").toString());
            map1.put("type",map.get("id").toString());
            edgeListRes.add(map1);
        }
        Map<String,Object> nodeMap = new HashMap<>();
        for(Map map: nodeList){
            Map<String,String> map1 = new HashMap<>();
            map1.put("'name'",map.get("label").toString());
            map1.put("'type'",map.get("label").toString());
            nodeMap.put(map.get("nodeId").toString(),map1);
        }
        Map<String,Object> res = new HashMap<>();
        res.put("nodeMap",nodeMap);
        res.put("edgeList",edgeListRes);
        return res;
    }
    @GetMapping("add")
    public void add(){
        //创建单个节点
        //String cql = "create (:Person{name:\"康康\"})";
        //创建多个节点
        //String cql = "create (:Person{name:\"李雷\"}) create (:Person{name:\"小明\"})";
        //根据已有节点创建关系
        //String cql = "match (n:Person{name:\"李雷\"}),(m:Person{name:\"小明\"}) create (n)-[r:friendRelation]->(m)";
        //同时创建节点和关系
        String cql = "create (:Person{name:\"张三\"})-[r:friendRelation]->(:Person{name:\"王五\"})";
        neo4jUtil.add(cql);
    }
}
