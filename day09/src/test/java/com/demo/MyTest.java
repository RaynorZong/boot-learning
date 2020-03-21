package com.demo;


import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Lock;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class MyTest {

    @Test
    public void test1(){
        Integer [] n = {-1,0,1,-2,-4};
        Set<Integer> set = Stream.of(n).collect(Collectors.toSet());
        Set<Integer> set1 = new HashSet<>();
        List<String> list = new ArrayList<>();
        for(int i=0;i<5;i++){
            for(int j=i+1;j<5;j++){
                if(set.contains((n[i]+n[j])*(-1))&& n[i]!=n[j]){
                    list.add(n[i]+","+n[j]+","+(-1)*(n[i]+n[j]));
                }

            }
        }
        list.forEach(System.out::println);
    }
    @Test
    public void test2(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

    }
}
