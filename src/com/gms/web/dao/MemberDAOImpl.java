package com.gms.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gms.web.command.Command;
import com.gms.web.constant.DB;
import com.gms.web.constant.SQL;
import com.gms.web.constant.Vendor;
import com.gms.web.domain.MajorBean;
import com.gms.web.domain.MemberBean;
import com.gms.web.domain.StudentBean;
import com.gms.web.factory.DatabaseFactory;
public class MemberDAOImpl implements MemberDAO{
	Connection conn;
	public static MemberDAOImpl getInstance() {return new MemberDAOImpl();}
	private MemberDAOImpl(){
		conn=null;
	}
	@Override
	public String insert(Map<?,?>map) {
		String rs="";
		MemberBean bean=(MemberBean)map.get("member");
		@SuppressWarnings("unchecked")
		List<MajorBean> list=(List<MajorBean>) map.get("major");
		PreparedStatement pstmt=null;
		try {
			conn=DatabaseFactory
					.createDatabase(Vendor.MARIADB, DB.USERNAME, DB.PASSWORD)
					.getConnection(); 
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement(SQL.MEMBER_INSERT);
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getPassword());
			pstmt.setString(4, bean.getBirthday());
			pstmt.setString(5, bean.getPhone());
			pstmt.setString(6, bean.getEmail());
			pstmt.setString(7, bean.getProfile());
			System.out.println("***"+SQL.MEMBER_INSERT);
			pstmt.executeUpdate();
			for(int i=0;i<list.size();i++){
				pstmt=conn.prepareStatement(SQL.MAJOR_INSERT);
				pstmt.setString(1, list.get(i).getMajorId());
				pstmt.setString(2, list.get(i).getTitle());
				pstmt.setString(3, list.get(i).getId());
				pstmt.setString(4, list.get(i).getSubjId());
				rs=String.valueOf(pstmt.executeUpdate());
				System.out.println("***"+SQL.MAJOR_INSERT);
				
				System.out.println("******"+rs);
			}
			
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(conn !=null){
				try{
					conn.rollback();
				}catch(SQLException ex){
					e.printStackTrace();
				}
			}
			
		}
		try {
			conn.setAutoCommit(true);
			System.out.println("**setAutoCommit(true)***");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("##### "+rs);
		return rs;
	}

	@Override
	public List<?> selectAll(Command cmd) {
		List<StudentBean>list=new ArrayList<>();
		try {
			conn=DatabaseFactory.createDatabase(Vendor.MARIADB, DB.USERNAME, DB.PASSWORD).getConnection();
			PreparedStatement pstmt=conn.prepareStatement(SQL.STUDENT_LIST);
			pstmt.setString(1, cmd.getStartRow());
			pstmt.setString(2, cmd.getEndRow());
			ResultSet rs=pstmt.executeQuery();
			StudentBean member=null;
			while(rs.next()){
				member=new StudentBean();
				member.setNum(String.valueOf(rs.getInt(DB.NUM)));
				member.setId(rs.getString(DB.ID));
				member.setName(rs.getString(DB.NAME));
				member.setEmail(rs.getString(DB.EMAIL));
				member.setPhone(rs.getString(DB.PHONE));
				member.setRegdate(rs.getString(DB.REGDATE));
				member.setSsn(rs.getString(DB.SSN));
				member.setSubjects(rs.getString(DB.SUBJECTS));
				list.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}

	@Override
	public List<StudentBean> selectByName(Command cmd) {
		System.out.println("selectByName("+cmd.getSearch()+")");
		System.out.println("selectByName("+cmd.getColumn()+")");
		List<StudentBean>list=new ArrayList<>();
		try {
			PreparedStatement pstmt=DatabaseFactory
					.createDatabase(Vendor.MARIADB, DB.USERNAME, DB.PASSWORD)
					.getConnection().prepareStatement(SQL.STUDENT_FINDBYNAME);
			if (cmd.getSearch()==null) {
				pstmt.setString(1, "%");	
			}
			else{
				pstmt.setString(1, "%"+cmd.getSearch()+"%");
			}
			
			ResultSet rs=pstmt.executeQuery();
			StudentBean member=null;
			while(rs.next()){
				member=new StudentBean();
				
				
				member.setNum(String.valueOf(rs.getInt(DB.NUM)));
				member.setId(rs.getString(DB.ID));
				member.setName(rs.getString(DB.NAME));
				member.setEmail(rs.getString(DB.EMAIL));
				member.setPhone(rs.getString(DB.PHONE));
				member.setRegdate(rs.getString(DB.REGDATE));
				member.setSsn(rs.getString(DB.SSN));
				member.setSubjects((rs.getString(DB.SUBJECTS)));
				list.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	@Override
	public StudentBean selectById(Command cmd) {
		StudentBean member=null;
		System.out.println("$ ID"+cmd.getSearch());
		try {
			PreparedStatement pstmt=DatabaseFactory.createDatabase(Vendor.MARIADB, DB.USERNAME,DB.PASSWORD)
					.getConnection().prepareStatement(SQL.STUDENT_FINDBYID);
			pstmt.setString(1, cmd.getSearch());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				member=new StudentBean();
				member.setId(rs.getString(DB.ID));
				member.setName(rs.getString(DB.NAME));
				member.setEmail(rs.getString(DB.EMAIL));
				member.setPhone(rs.getString(DB.PHONE));
				member.setRegdate(rs.getString(DB.REGDATE));
				member.setSsn(rs.getString(DB.SSN));
				member.setSubjects(rs.getString(DB.SUBJECTS));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}
	@Override
	public String count(Command cmd) {
		System.out.println("count("+cmd.getSearch()+")");
		System.out.println("count("+cmd.getColumn()+")");
		String count="";
		try {
			conn=DatabaseFactory
					.createDatabase(Vendor.MARIADB, DB.USERNAME,DB.PASSWORD)
					.getConnection();
			PreparedStatement pstmt=null;
			if(cmd.getSearch()==null){
				System.out.println("cmd.getSearch() is null");
				pstmt=conn.prepareStatement(SQL.STUDENT_COUNT);
				pstmt.setString(1, "%");
			}else{
				System.out.println("cmd.getSearch() is not null");
				pstmt=conn.prepareStatement(SQL.STUDENT_COUNT);
				pstmt.setString(1, "%"+cmd.getSearch()+"%");
			}
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				count=rs.getString("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("DAO count: "+count);
		return count;
	}

	@Override
	public String update(MemberBean bean) {
		String rs="";
		try {
			PreparedStatement pstmt=DatabaseFactory.createDatabase(Vendor.MARIADB, DB.USERNAME,DB.PASSWORD).getConnection().prepareStatement(SQL.MEMBER_UPDATE);
			pstmt.setString(1, bean.getPassword());
			pstmt.setString(2, bean.getId());
			rs=String.valueOf(pstmt.executeUpdate());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}

	@Override
	public String delete(Command cmd) {
		String rs="";
		try {
			PreparedStatement pstmt=DatabaseFactory.createDatabase(Vendor.MARIADB, DB.USERNAME,DB.PASSWORD).getConnection().prepareStatement(SQL.MEMBER_DELETE);
			pstmt.setString(1, cmd.getSearch());
			rs=String.valueOf(pstmt.executeUpdate());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	@Override
	public MemberBean login(Command cmd) {
		MemberBean member=null;
		System.out.println("$ ID"+cmd.getSearch());
		try {
			PreparedStatement pstmt=DatabaseFactory.createDatabase(Vendor.MARIADB, DB.USERNAME,DB.PASSWORD).getConnection().prepareStatement("select * from member where member_id like ?");
			pstmt.setString(1, cmd.getSearch());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				member=new MemberBean();
				member.setId(rs.getString(DB.MEMBER_ID));
				member.setName(rs.getString(DB.NAME));
				member.setPassword(rs.getString(DB.PASS));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}
}