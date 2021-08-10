package com.yangy.web.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@SuppressWarnings("all")
public class TestController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    
    
    

    //测试简单的数据传输
    @RequestMapping("/t1")
    public String test1(Model model){
        model.addAttribute("msg","daiaoqi");
        return "test";
    }

    //简单测试redis
    @RequestMapping("/testRedis")
    public String redis(){
        redisTemplate.opsForValue().set("k2","wt");
        String str = redisTemplate.opsForValue().get("k2");
        return str;
    }

    //测试thymeleaf遍历集合
    @RequestMapping("/t2")
    public String test2(Map<String,Object> map){
        //存入数据
        map.put("msg","<h1>Hello</h1>");
        map.put("users", Arrays.asList("daiaoqi","wutong"));
        return "test2";
    }

   

    //测试swagger的实体类接口
    @ApiOperation("测试实体类接口")
    @PostMapping("/yangy")
    @ResponseBody
    public String kuang(@ApiParam("这个名字会被返回")String username){
        return username;
    }

    //测试JSR303数据校验
    @PostMapping("/tsetValidator")
    public Object validatorObject(BindingResult br){
        Map<String,Object> errorMap = new HashMap<String,Object>();
        if(br.hasErrors()){
            //对错误集合进行遍历,有的话,直接放入map集合中
            br.getFieldErrors().forEach(p->{
                errorMap.put(p.getField(), p.getDefaultMessage());
            });
        }
        //返回错误信息
        return errorMap;
    }

     public static void main(String[] args) {
        Person p1=new Person("海绵宝宝",18);
        Person p2=new Person("派大星",19);
        Person p3=new Person("蟹老板",20);
        Person p4=new Person("海绵宝宝",18);
        
        Set<Person> p=new HashSet<Person>();
        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.add(p4);     
        
        //先不重写Person的equals和hashCode方法，看下HashSet存储情况
        for (Person ps:p) {
            System.out.println(ps);
        }
    }
    
    static class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {          //打印时输出内容，否则输出地址
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return age == person.age &&
                    Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
        
    }
    
}