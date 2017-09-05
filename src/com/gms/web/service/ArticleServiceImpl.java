package com.gms.web.service;

import java.util.List;

import com.gms.web.dao.ArticleDAO;
import com.gms.web.dao.ArticleDAOImpl;
import com.gms.web.domain.ArticleBean;

public class ArticleServiceImpl implements ArticleService {
private ArticleDAO dao =ArticleDAOImpl.getInstance(); 
public static ArticleServiceImpl getInstance(){
	return new ArticleServiceImpl();
}
private ArticleServiceImpl() {
	// TODO Auto-generated constructor stub
}	
@Override
	public String write(ArticleBean bean) {
		String result=ArticleDAOImpl.getInstance().insert(bean);
		System.out.println("**"+result);
		return result.equals("1")?"성공":"실패";
	}

	@Override
	public List<ArticleBean> list() {
		// TODO Auto-generated method stub
		return ArticleDAOImpl.getInstance().selectAll();
	}

	@Override
	public List<ArticleBean> findById(String id) {
		// TODO Auto-generated method stub
		return ArticleDAOImpl.getInstance().selectById(id);
	}

	@Override
	public ArticleBean findBySeq(String seq) {
		ArticleBean result = ArticleDAOImpl.getInstance().selectBySeq(seq);
		System.out.println(result.toString());
		return result;
	}

	@Override
	public String count() {
		return dao.count();
	}

	@Override
	public String modify(ArticleBean bean) {
		return ArticleDAOImpl.getInstance().update(bean);
	}

	@Override
	public String remove(String seq) {
		// TODO Auto-generated method stub
		return ArticleDAOImpl.getInstance().delete(seq).equals("1") ? "delete":"fail";
	}

}
