//package com.demo;
//
//import com.demo.bean.MyBeanPostProcessor;
//import com.demo.bean.MyInstantiationAwareBeanPostProcessor;
//import com.demo.entity.User;
//import com.demo.util.RedisUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.BeanFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.support.DefaultListableBeanFactory;
//import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.ApplicationContext;
//import org.springframework.core.io.ClassPathResource;
//
//import javax.annotation.Resource;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@SpringBootTest
//@Slf4j
//class DemoApplicationTests {
//
//
//
//    @Test
//    void contextLoads() {
//        User user = new User();
//        user.setId(1l);
//        user.setTime(LocalDateTime.now());
//        user.setPassword("xxx");
//        redisUtils.lSet("test",user);
//        redisUtils.set("user",user);
//        log.info(redisUtils.get("user").toString());
//        List<Object> list = redisUtils.lGet("test",0L,-1L);
//        list.forEach(System.out::print);
//    }
//    @Resource
//    private ApplicationContext context;
//
//    @Resource
//    private BeanFactory beanFactory;
//    @Test
//    void beanFactory(){
//        org.springframework.core.io.Resource resource =  new ClassPathResource("Test.xml");
//        BeanFactory beanFactory = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader((DefaultListableBeanFactory) beanFactory);
//        reader.loadBeanDefinitions(resource);
//
//        //向容器中注册后处理器 MyBeanPostProcessor
//        ((DefaultListableBeanFactory) beanFactory).addBeanPostProcessor(new MyBeanPostProcessor());
//
//        //向容器中注册后处理器 MyInstantiationAwareBeanPostProcessor
//        //注意：后处理器调用顺序和注册顺序无关。在处理多个后处理器的情况下，必需通过实现Ordered接口来确定顺序
//        ((DefaultListableBeanFactory) beanFactory).addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
//
//        com.demo.bean.Test test = (com.demo.bean.Test) beanFactory.getBean("test");
//        ((DefaultListableBeanFactory) beanFactory).destroySingletons();
//    }
//
//    @Resource(name="redisUtils")
//    private RedisUtils redisUtils;
//    @Autowired
//    private RedisUtils redisUtils2;
//    @Test
//    void aaa(){
//        System.out.println(redisUtils);
//        System.out.println(redisUtils);
//        System.out.println(context.getBeanNamesForType(RedisUtils.class)[0]);
//    }
//}
