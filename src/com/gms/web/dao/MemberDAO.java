package com.gms.web.dao;

import java.util.List;
import java.util.Map;

import com.gms.web.command.Command;
import com.gms.web.domain.MemberBean;
import com.gms.web.domain.StudentBean;

public interface MemberDAO {
	public String insert(Map<?,?>map);
	public List<?> selectAll(Command cmd);
	public List<?> selectByName(Command cmd);
	public StudentBean selectById(Command cmd);
	public String count(Command cmd);
	public String update(MemberBean bean);
	public String delete(Command cmd);
	public MemberBean login(Command cmd);
}
