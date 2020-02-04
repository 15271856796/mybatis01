package com.hdl.dao;

import com.hdl.bean.Employee;
import com.hdl.bean.Employee_Dept;

public interface Employee_DeptMapper {
    public Employee_Dept getEmployee_DeptbyId(Integer id);
    public Employee_Dept getEmployee_DeptbyId2(Integer id);
}
