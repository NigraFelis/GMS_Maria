package com.gms.web.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gms.web.constant.DB;
import com.gms.web.constant.SQL;
import com.gms.web.constant.Vendor;
import com.gms.web.domain.ArticleBean;
import com.gms.web.domain.MemberBean;
import com.gms.web.factory.DatabaseFactory;

public class ArticleDAOImpl implements ArticleDAO{
public static ArticleDAOImpl instance = new ArticleDAOImpl();
	public static ArticleDAOImpl getInstance() {
	return instance;
}
private ArticleDAOImpl() {
}
	@Override
	public String insert(ArticleBean bean) {
		String rs="";
		try {PreparedStatement pstmt= DatabaseFactory.createDatabase(Vendor.ORACLE, DB.USERNAME, DB.PASSWORD).getConnection().prepareStatement(SQL.ARTICLE_INSERT);
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getTitle());
			pstmt.setString(3, bean.getContent());
			rs =String.valueOf(pstmt.executeUpdate());
		} catch (Exception e) {
			e.printStackTrace();
		}
			return rs;
	}
	
	@Override
	public List<ArticleBean> selectAll() {
		ArticleBean bean=null;
		List<ArticleBean> temp=new ArrayList<>();
		try {
			PreparedStatement stmt=DatabaseFactory.createDatabase(Vendor.ORACLE,DB.USERNAME,DB.PASSWORD).getConnection()
			.prepareStatement(SQL.ARTICLE_LIST);
			ResultSet rs=stmt.executeQuery();
		while(rs.next()){
			bean=new ArticleBean();
			bean.setArticleSeq(rs.getInt("article_seq"));
			bean.setId(rs.getString("id"));
			bean.setTitle(rs.getString("title"));
			bean.setHitcount(rs.getInt("hitcount"));
			bean.setRegdate(rs.getString("regdate"));
			temp.add(bean);
			
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return temp;
	}

	@Override
	public List<ArticleBean> selectById(String id) {
		// TODO Auto-generated method stub
		List<ArticleBean> list=new ArrayList<>();
		ArticleBean bean=null;
		try {
			PreparedStatement pstmt=DatabaseFactory.createDatabase(Vendor.ORACLE, DB.USERNAME, DB.PASSWORD).getConnection().prepareStatement(SQL.ARTICLE_FINDBYID);
			pstmt.setString(1, id);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				bean=new ArticleBean();
				bean.setArticleSeq(rs.getInt(DB.ARTICLE_SEQ));
				bean.setId(rs.getString(DB.ID));
				bean.setTitle(rs.getString(DB.TITLE));
				bean.setContent(rs.getString(DB.CONTENT));
				bean.setHitcount(rs.getInt(DB.HITCOUNT));
				bean.setRegdate(rs.getString(DB.REGDATE));
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArticleBean selectBySeq(String seq) {
		ArticleBean bean = new ArticleBean();
		try {
			PreparedStatement pstmt = DatabaseFactory.createDatabase(Vendor.ORACLE, DB.USERNAME, DB.PASSWORD).getConnection().prepareStatement(SQL.ARTICLE_FINDBYSEQ);
			pstmt.setInt(1, Integer.parseInt(seq));
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				bean.setArticleSeq(rs.getInt(DB.ARTICLE_SEQ));
				bean.setContent(rs.getString(DB.CONTENT));
				bean.setHitcount(rs.getInt(DB.HITCOUNT));
				bean.setId(rs.getString(DB.ID));
				bean.setRegdate(rs.getString(DB.REGDATE));
				bean.setTitle(rs.getString(DB.TITLE));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	@Override
	public String count() {
		String count="";
		try {
			PreparedStatement stmt = DatabaseFactory.createDatabase(Vendor.ORACLE, DB.USERNAME, DB.USERNAME).getConnection().prepareStatement(SQL.ARTICLE_COUNT);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				count=rs.getString("count");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public String update(ArticleBean bean) {
		ArticleBean temp=selectBySeq(String.valueOf(bean.getArticleSeq()));
		String title=(bean.getTitle().equals(""))? temp.getTitle(): bean.getTitle();
		String content=(bean.getContent().equals(""))? temp.getContent(): bean.getContent();
		String rs="";
		try {
			PreparedStatement pstmt = DatabaseFactory.createDatabase(Vendor.ORACLE, DB.USERNAME, DB.PASSWORD).getConnection().prepareStatement(SQL.BOARD_UPDATE);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, bean.getArticleSeq());
			rs =String.valueOf(pstmt.executeUpdate());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public String delete(String seq) {
		// TODO Auto-generated method stub
		String rs="";
		try {
			PreparedStatement pstmt=DatabaseFactory.createDatabase(Vendor.ORACLE, DB.USERNAME, DB.PASSWORD).getConnection().prepareStatement(SQL.BOARD_DELETE);
			pstmt.setInt(1,Integer.parseInt(seq));
			rs=String.valueOf(pstmt.executeUpdate());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

}
