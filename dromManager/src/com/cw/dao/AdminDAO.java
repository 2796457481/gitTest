package com.cw.dao;

import java.util.List;

import com.cw.entity.Admin;

public interface AdminDAO {
    public boolean login(Admin admin);// ��¼����

    public List<Admin> getAdminList();

    public boolean update(Admin admin);// �޸�����

    public int querypwd(Admin admin);// �޸����뷽��

    
}
