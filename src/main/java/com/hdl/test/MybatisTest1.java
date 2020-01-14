package com.hdl.test;

import com.hdl.bean.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;


public class MybatisTest1 {

    /**
     * mybatis实现方式一,不常用,常用的是方式二 详细看MybatisTest2
     * mybatis配置过程:
     *      1 先导入必要的pom.xml坐标
     *      2 创建一个全局的配置文件(mybatis-config.xml),有数据源一些运行环境信息
     *      3 sql配置文件(EmployeeMapper1.xm);配置了每一个sql,以及sql结果的封装规则等.
     *      4 将sql配置文件注册在全局配置文件中,即在mybatis-config.xml中加一句  <mapper resource="EmployeeMapper1.xml"></mapper>
     *
     * 配置完成后,具体使用过程:
     *      1 读取全局配置文件信息(mybatis-config.xml),创建一个SqlSessionFactory对象
     *      2 使用SqlSessionFactory对象获取一个SqlSession对象,用他来执行增删改查的操作
     *        一个SqlSession就代表与数据库的一次会话,用完之后需要关掉,使用的是sql的唯一标识(命名空间.id)来告诉mybatis要执行哪个sql
     *        sql都是保存在sql映射文件中(比如EmployeeMapper1.xml)
     *
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        //1 获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2 获取SqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            //selectOne 的第一个参数是指定用哪个sql 通过命名空间+标签id来唯一指定,后面的都是sql语句中的参数
            Employee employee= (Employee) openSession.selectOne("com.hdl.bean.EmployeeMapper1.selectEmployee", 11);
            System.out.println(employee);  //Employee{id=11, lastname='null', gender='0', email='15271856796@163.com'}

            Employee employee2= (Employee) openSession.selectOne("com.hdl.bean.EmployeeMapper1.selectEmployee2", 11);
            System.out.println(employee2);//Employee{id=11, lastname='tom', gender='0', email='15271856796@163.com'}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            openSession.close();
        }

    }

}
