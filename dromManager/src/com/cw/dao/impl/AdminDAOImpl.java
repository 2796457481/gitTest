package com.cw.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.cw.core.DBUtils;
import com.cw.dao.AdminDAO;
import com.cw.entity.Admin;

public class AdminDAOImpl implements AdminDAO {

    // 获取所有的管理员用户
    public List<Admin> getAdminList() {

	String sql = "SELECT * FROM t_dormmanager  d  ,t_dormbuild  dd WHERE d.`dormBuildId`=dd.`dormBuildId`";

	Connection conn = DBUtils.getConn();

	List<Admin> admins = new ArrayList<Admin>();
	try {
	    PreparedStatement ps = conn.prepareStatement(sql);

	    ResultSet rs = ps.executeQuery();

	    while (rs.next()) {

		int id = rs.getInt("dormManId");
		String name = rs.getString("name");
		String userName = rs.getString("username");
		String sex = rs.getString("sex");
		String tel = rs.getString("tel");
		String pwd = rs.getString("password");
		// 获取宿舍楼信息
		String dromBuilderInfo = rs.getString("dormBuildName");

		Admin admin = new Admin();

		// 设置宿舍楼信息

		admin.setDromBuilderInfo(dromBuilderInfo);

		admin.setId(id);

		admin.setName(name);

		admin.setPassword(pwd);

		admin.setSex(sex);

		admin.setTel(tel);

		admin.setUserName(userName);

		admins.add(admin);

	    }

	    DBUtils.closeResouce(conn, ps, rs);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return admins;
    }

    public static void main(String[] args) {
	AdminDAO admin = new AdminDAOImpl();

	List<Admin> admins = admin.getAdminList();

	for (Admin a : admins) {

	    System.out.println("admin、 ID=" + a.getId());
	    System.out.println(a.getDromBuilderInfo());
	}
    }

    @Override
    public boolean login(Admin admin) {
	// TODO Auto-generated method stub
	String sql = "select * from t_admin where userName= '" + admin.getUserName() + "' and password= '"
		+ admin.getPassword() + "' ";
	System.out.println("sql>>>>>" + sql);
	Connection conn = DBUtils.getConn();

	PreparedStatement pre = null;

	ResultSet result = null;

	boolean flag = false;

	try {

	    pre = conn.prepareStatement(sql);

	    result = pre.executeQuery();

	    // 得到数据库的查询结果,一个数据集
	    if (result.next()) {
		flag = true;
	    }

	} catch (SQLException e) {
	    // TODO: handle exception
	    e.printStackTrace();

	} finally {
	    DBUtils.closeResouce(conn, pre, result);
	}

	return flag;

    }

    @Override
    public boolean update(Admin admin) {
	// TODO Auto-generated method stub
	Connection conn = DBUtils.getConn();
	PreparedStatement pre = null;
	String sql = "update t_admin set  password='" + admin.getPassword() + "' where adminId=" + admin.getId();
	boolean falg = false;
	try {
	    System.out.println("修改" + sql);
	    pre = conn.prepareStatement(sql);

	    falg = pre.executeUpdate() > 0;

	} catch (SQLException e) {

	    e.printStackTrace();

	} finally {
	    DBUtils.closeResouce(conn, pre, null);

	}

	return falg;

    }

    @Override
    public int querypwd(Admin admin) {
	// TODO Auto-generated method stub
	String sql = "select adminId from t_admin where userName='" + admin.getUserName() + "' and password= '"
		+ admin.getPassword() + "'";
	System.out.println("sql>>>>>" + sql);
	Connection conn = DBUtils.getConn();

	PreparedStatement pre = null;

	ResultSet result = null;

	int adminid = 0;

	try {

	    pre = conn.prepareStatement(sql);

	    result = pre.executeQuery();

	    // 得到数据库的查询结果,一个数据集
	    while (result.next()) {
		adminid = result.getInt("adminId");
	    }

	} catch (SQLException e) {
	    // TODO: handle exception
	    e.printStackTrace();

	} finally {
	    DBUtils.closeResouce(conn, pre, result);
	}
	return adminid;

    }

}
