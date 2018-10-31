package com.cw.dao;

import java.util.List;

import com.cw.entity.Admin;

public interface AdminDAO {
    public boolean login(Admin admin);// 登录方法

    public List<Admin> getAdminList();

    public boolean update(Admin admin);// 修改密码

    public int querypwd(Admin admin);// 修改密码方法

    
}
