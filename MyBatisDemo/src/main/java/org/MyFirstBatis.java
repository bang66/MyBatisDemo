package org;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.mapper.IPersonMapper;
import org.pojo.Person;

import java.io.IOException;
import java.io.InputStream;

public class MyFirstBatis {
    Logger logger=Logger.getLogger(MyFirstBatis.class);

    //插入单测
    @org.junit.Test
    public void testInsertPerson() throws IOException {
        //获取mybatis全局配置文件
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //读取配置文件的配置信息，利用SqlSessionFactoryBuilder创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //利用sqlSessionFactory打开与数据库的会话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            IPersonMapper personMapper = sqlSession.getMapper(IPersonMapper.class);//通过sqlSession得到mapper

            Person person = new Person();
            person.setAge(3);
            person.setId(28);
            person.setMobilePhone("33333333");
            person.setUserName("333");

            personMapper.insertPerson(person);//调用mapper的方法
            sqlSession.commit();//若是 增、删、改操作，不添加此语句则数据库不更改
            logger.info("commit succ----!");
        } finally {
            sqlSession.close();
        }
    }


    //查找单测
    @org.junit.Test
    public void getPersonById() throws IOException {
        //获取mybatis全局配置文件
        String resouces = "mybatis-config.xml";
        InputStream in = Resources.getResourceAsStream(resouces);
        //读取配置文件的配置信息，利用SqlSessionFactoryBuilder创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        //利用sqlSessionFactory打开与数据库的会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            IPersonMapper personMapper = sqlSession.getMapper(IPersonMapper.class);//通过sqlSession得到mapper
            Person person = personMapper.getPersonById(25);//调用mapper的方法
            //只读操作不需要sqlSession.commit()
            System.out.println(person);
            logger.info(person);
        } finally {
            sqlSession.close();
        }

    }

    //修改单测
    @org.junit.Test
    public void updatePerson() throws IOException {
        //获取mybatis全局配置文件
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //读取配置文件的配置信息，利用SqlSessionFactoryBuilder创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //利用sqlSessionFactory打开与数据库的会话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            IPersonMapper personMapper = sqlSession.getMapper(IPersonMapper.class);//通过sqlSession得到mapper

            Person person = new Person();
            person.setAge(23);
            person.setId(25);
            person.setMobilePhone("5858888888");
            person.setUserName("xxx");

            personMapper.updatePerson(person);//调用mapper的方法
            sqlSession.commit();//若是 增、删、改操作，不添加此语句则数据库不更改
            logger.info("commit succ----!");
        } finally {
            sqlSession.close();
        }
    }

}
