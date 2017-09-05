package com.gms.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gms.web.command.Command;
import com.gms.web.dao.MemberDAO;
import com.gms.web.dao.MemberDAOImpl;
import com.gms.web.domain.MajorBean;
import com.gms.web.domain.MemberBean;
import com.gms.web.domain.StudentBean;

public class MemberServiceImpl implements MemberService{
	public static MemberServiceImpl getInstance() {
		return new MemberServiceImpl();
	}
	private MemberServiceImpl(){}
	@Override
	public String add(Map<String,Object> map) {
		System.out.println("member service 진입");
		MemberBean m=(MemberBean)map.get("member");
		System.out.println("넘어온 회원 정보:"+m.toString());
		@SuppressWarnings("unchecked")
		List<MajorBean>list=(List<MajorBean>)map.get("major");
		System.out.println("넘어온 수강과목:"+list);
		String rs=MemberDAOImpl.getInstance().insert(map);
		System.out.println("서비스 RS :"+rs);
		return rs;
	}
	@Override
	public List<?> list(Command cmd) {
		return MemberDAOImpl.getInstance().selectAll(cmd);
	}
	@Override
	public List<?> findByName(Command cmd) {
		System.out.println("findByName("+cmd.getSearch()+")");
		return MemberDAOImpl.getInstance().selectByName(cmd);
	}

	@Override
	public StudentBean findById(Command cmd) {
		return MemberDAOImpl.getInstance().selectById(cmd);
	}

	@Override
	public String count(Command cmd) {
		
		return MemberDAOImpl.getInstance().count(cmd);
	}

	@Override
	public String modify(MemberBean bean) {		
		String msg="";
		return msg;
	}

	@Override
	public String remove(Command cmd) {
		String msg="";
		
		return msg;
	}
	@Override
	public Map<String,Object> login(MemberBean bean) {
		Map<String,Object> map=new HashMap<>();
		Command cmd=new Command();
		cmd.setSearch(bean.getId());
		MemberBean m=MemberDAOImpl.getInstance().login(cmd);
		String page=
		 (m!=null)?
				(bean.getPassword().equals(m.getPassword()))?
						"main":"login_fail":"join";
		map.put("page", page);
		map.put("user", m);
		return map;
		
	}
}











