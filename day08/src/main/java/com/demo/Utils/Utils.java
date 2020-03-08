package com.demo.Utils;

import com.alibaba.fastjson.JSON;
import com.demo.entity.User;
import com.demo.entity.UserVO;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class Utils {

    public static void record(Object  old, Object  now) {
        Field[] fields = old.getClass().getDeclaredFields();
        Class<?> oldClass = old.getClass();
        StringBuilder sb = new StringBuilder("{");
        for (Field field : fields) {
            try {
                String key = field.getName();
                if (Objects.equals("id", key) || Objects.equals("records", key)) {
                    continue;
                }
                String getMethodName = "get" + key.substring(0, 1).toUpperCase() + key.substring(1);
                Method getMethod = oldClass.getDeclaredMethod(getMethodName);
                Object oldValue = getMethod.invoke(old);
                Object nowValue = getMethod.invoke(now);
                if (!Objects.equals(oldValue, nowValue)) {
                    sb.append("\"" + key + "_old\":" + "\"" + oldValue + "\",");
                    sb.append("\"" + key + "_now\":" + "\"" + nowValue + "\",");
                }
            } catch (Exception e) {
                continue;
            }
        }
        if(sb.length()>1){
            sb.deleteCharAt(sb.length()-1);
        }
        sb.append("}");
        try {
            Method setMethod = oldClass.getDeclaredMethod("setRecords",String.class);
            setMethod.invoke(now, sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void showRecord(Object now,Object vo){
        Class<?> nowClass = now.getClass();
        Class<?> voClass = vo.getClass();
        Field[] fields = now.getClass().getDeclaredFields();// 根据now的字段去复制
        try {
            Method getRecordsMethod = nowClass.getDeclaredMethod("getRecords");
            String records =   getRecordsMethod.invoke(now).toString();
            Map<String,Object> map = (Map) JSON.parseObject(records);
            for (Field field : fields) {
                try {
                    String key = field.getName();
                    String getMethodName = "get"+key.substring(0,1).toUpperCase()+key.substring(1);
                    String setMethodName = "set"+key.substring(0,1).toUpperCase()+key.substring(1);
                    Method getMethod =  nowClass.getDeclaredMethod(getMethodName);
                    if(map.containsKey(key+"_old")){
                        Method setMethod = voClass.getDeclaredMethod(setMethodName,String.class);
                        setMethod.invoke(vo,map.get(key+"_old")+"---->"+map.get(key+"_now"));
                    }else{
                        Method setMethod = voClass.getDeclaredMethod(setMethodName, field.getType());
                        Object value = getMethod.invoke(now);
                        if(value != null){
                            setMethod.invoke(vo,value);
                        }
                    }
                } catch (Exception e) {
                    log.info(e.getMessage());
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        LocalDateTime time = LocalDateTime.now();
        User old = new User();
        old.setId(1L);
        old.setName("Eric");
        old.setPassword("456");
        old.setRecords("111");
        old.setTime(time);
        User now = new User();
        now.setId(1L);
        now.setName("Eric1");
        now.setPassword("4561");
        now.setTime(LocalDateTime.now());
        Utils.record(old,now);//添加修改记录
        log.info(now.getRecords());
        UserVO vo = new UserVO();
        Utils.showRecord(now,vo);//vo中对修改记录处理
        log.info(vo.toString()+"++++++ vo");

    }
}
