package com.hdl.test;

import com.hdl.bean.Employee;
import com.hdl.bean.Employee_Dept;
import com.hdl.dao.EmployeeMapper;
import com.hdl.dao.Employee_DeptMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest3 {
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
            Employee_DeptMapper mapper = openSession.getMapper(Employee_DeptMapper.class);
            //测试查询
            Employee_Dept employee_dept = mapper.getEmployee_DeptbyId(16);
            System.out.println(employee_dept);  //Employee{id=11, lastname='tom', gender='0', email='15271856796@163.com'}

            //手动提交
            openSession.commit();

        } finally {

            openSession.close();
        }

    }
}
