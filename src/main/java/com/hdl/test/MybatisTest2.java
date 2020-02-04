package com.hdl.test;

import com.hdl.bean.Employee;
import com.hdl.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import javax.xml.soap.SOAPPart;
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
        //2 获取SqlSession对象,和方式一一样,这种方式的openSession不会自动提交,所以需要我们手动设置自动提交,
        // 也可以SqlSession openSession = sqlSessionFactory.openSession(true);这样的话可以自动提交
        SqlSession openSession = sqlSessionFactory.openSession();

        //3 获取接口对象,虽然接口没有实现类,也没有关系,会直接执行绑定上的sql配置文件中的内容
        //只要接口和sql配置文件绑定好了之后,就会为接口创建一个代理对象,代理对象回去执行增删改查
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            //测试查询
            Employee employee = mapper.getempbyId(11);
            System.out.println(employee);  //Employee{id=11, lastname='tom', gender='0', email='15271856796@163.com'}
            //测试添加
            Employee employee1=new Employee(null,"hr","1","15732632513@163.com");
            mapper.addEmployee(employee1);
            //测试删除
            mapper.deleteEmployee(11);
            //测试修改
            Employee employee2=new Employee(16,"hdl","0","15732632513@163.com");
            mapper.updateEmployee(employee2);
            //测试返回值为Interger的删除
            int a=mapper.deleteEmployee1(11);//由于表里已经没有id为11的用户,所以改动值为0,也就是返回值为0
            //测试返回值为Boolean的删除
            boolean b=mapper.deleteEmployee2(11);   //返回值为false
            System.out.println(a);
            System.out.println(b);
            //测试获取添加的记录的自增主键
            Employee employee3=new Employee(null,"hr","1","15732632513@163.com");
            mapper.addEmployee1(employee3);
            System.out.println(employee3.getId()); //由于你在sql配置文件中已经设置过,所以这个时候再获取的时候id就不是null了.而是返回自动生成的主键


            //手动提交
            openSession.commit();

        } finally {

            openSession.close();
        }

    }
}
