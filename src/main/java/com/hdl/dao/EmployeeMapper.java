package com.hdl.dao;

import com.hdl.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * MybatisTest中是mybatis提供给我们第一种方法,可以看到很麻烦
 * mybatis提供给我们第二种方法,创建一个如下接口,这个接口想实现的功能就是查询数据库,并将结果进行封装,这和sql配置文件的功能是一样的
 * 所以mybatis提供给我们将接口与sql配置文件动态绑定的方法,不用我们再去写实现类,然后使用方式一去使用mybatis
 * 动态绑定的过程:
 *      1 之前使用方法一的时候,sql配置文件中的命名空间可以随意的起名,但是现在为了实现动态的绑定,sql配置文件中的命名空间名称必须是接口的全限定类名
 *      2  select标签中的id之前也是随意的起名字,但是现在需要与我们接口中的方法进行绑定,也就是id等于方法名
 */
public interface EmployeeMapper {
    //查询
    public Employee getempbyId(Integer id);

    //演示多个参数的查询
    public Employee getempbyIdandLastname(@Param("id") Integer id, @Param("lastname") String lastname);

    //演示多个参数的查询(方法中传入map,那么sql在接参的时候直接#{key}就可以取值)
    public Employee getempbymap(Map<String,Object> map);

    //添加
    public void addEmployee(Employee emp);

    //删除
    public void  deleteEmployee(Integer id);

    //修改
    public void updateEmployee(Employee emp);

    //mybatis允许增删改直接定义一下类型返回值,不需要像查询一样在sql配置文件中还声明返回值类型,直接如下
        //Integer,Long,Boolean(当改动为0行时,直接返回false,否则为true)
    public Integer  deleteEmployee1(Integer id);
    public Boolean  deleteEmployee2(Integer id);

    //获取添加的记录的自动生成的主键
    public void addEmployee1(Employee emp);



}
