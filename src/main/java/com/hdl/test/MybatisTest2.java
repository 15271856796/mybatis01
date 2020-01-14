package com.hdl.test;

import com.hdl.bean.Employee;
import com.hdl.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * mybatis实现方式二,常用
 * mybatis配置过程:
 *      1 先导入必要的pom.xml坐标
 *      2 创建一个全局的配置文件(mybatis-config.xml),有数据源一些运行环境信息
 *      3 操作数据库的接口类
 *      4 sql配置文件(EmployeeMapper.xm);配置了每一个sql,以及sql结果的封装规则等.
 *      5 实现接口类与sql配置文件的动态绑定,也就是使sql配置文件(EmployeeMapper2.xm) 中的命名空间以及标签中的id属性值与接口的全限定类名以及方法名保持一致
 *      6 将sql配置文件注册在全局配置文件中,即在mybatis-config.xml中加一句  <mapper resource="EmployeeMapper2.xml"></mapper>
 *  配置完成后,具体使用过程:
 *      1 读取全局配置文件信息(mybatis-config.xml),创建一个SqlSessionFactory对象
 *      2 使用SqlSessionFactory对象获取一个SqlSession对象,用他来执行增删改查的操作
 *        一个SqlSession就代表与数据库的一次会话,用完之后需要关掉,使用的是sql的唯一标识(命名空间.id)来告诉mybatis要执行哪个sql
 *        sql都是保存在sql映射文件中(比如EmployeeMapper2.xml)
 *      3 获取接口对象,执行接口方法
 */

public class MybatisTest2 {
    @Test
    public void test() throws IOException {
        //1 获取SqlSessionFactory对象,和方式一一样
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2 获取SqlSession对象,和方式一一样
        SqlSession openSession = sqlSessionFactory.openSession();

        //3 获取接口对象,虽然接口没有实现类,也没有关系,会直接执行绑定上的sql配置文件中的内容
        //只要接口和sql配置文件绑定好了之后,就会为接口创建一个代理对象,代理对象回去执行增删改查
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getempbyId(11);
            System.out.println(employee);  //Employee{id=11, lastname='tom', gender='0', email='15271856796@163.com'}
        } finally {
            openSession.close();
        }

    }
}
