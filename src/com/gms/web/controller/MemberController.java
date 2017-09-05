package com.gms.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gms.web.command.Command;
import com.gms.web.constant.Action;
import com.gms.web.domain.MajorBean;
import com.gms.web.domain.MemberBean;
import com.gms.web.domain.StudentBean;
import com.gms.web.proxy.BlockHandler;
import com.gms.web.proxy.PageHandler;
import com.gms.web.proxy.PageProxy;
import com.gms.web.proxy.Proxy;
import com.gms.web.service.MemberService;
import com.gms.web.service.MemberServiceImpl;
import com.gms.web.util.DispatcherServlet;
import com.gms.web.util.ParamsIterator;
import com.gms.web.util.Separator;

@WebServlet("/member.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberController 진입");
		Separator.init(request);
		MemberBean member=new MemberBean();
		MemberService service=MemberServiceImpl.getInstance();
		Map<?,?> map=new HashMap<>();
		PageProxy pxy=new PageProxy(request);
		Command cmd=new Command();
		pxy.setPageSize(5);
		pxy.setBlockSize(5);
		switch (request.getParameter("action")) {
		case Action.MOVE:DispatcherServlet.send(request, response);break;
		case Action.JOIN: 
			System.out.println("=== join 진입 ===");   
	         map=ParamsIterator.execute(request);
	         member.setId((String)map.get("id"));
	         member.setPassword((String)map.get("password"));
	         member.setName((String)map.get("name"));
	         member.setBirthday((String)map.get("birthday"));
	         member.setGender((String)map.get("gender"));
	         member.setEmail((String)map.get("email"));
	         member.setPhone((String)map.get("phone"));
	         member.setMajor((String)map.get("major"));
	         member.setProfile("defaultimg.jpg");
	         String[] subjects=((String)map.get("subject")).split(",");
	         List<MajorBean> list=new ArrayList<>();
	         MajorBean major=null;
	         for(int i=0;i<subjects.length;i++){
	            major=new MajorBean();
	            major.setMajorId(String.valueOf((int)((Math.random() * 9999) + 1000)));
	            major.setId((String)map.get("id"));
	            major.setTitle((String)map.get("major"));
	            major.setSubjId(subjects[i]);
	            list.add(major);
	         }
	         Map<String,Object>tempMap=new HashMap<>();
	         tempMap.put("member", member);
	         tempMap.put("major", list);
	         String rs=service.add(tempMap);
	         Separator.cmd.setDir("common");
	         Separator.cmd.process();
	         System.out.println("컨트롤러 인서트 결과:"+rs);
	         DispatcherServlet.send(request, response);
	         break;
		case Action.LIST:
			System.out.println("Member List Enter");
			pxy.setTheNumberOfRows(Integer.parseInt(service.count(cmd)));
			pxy.setPageNumber(Integer.parseInt(
					request.getParameter("pageNumber")));
			pxy.execute(BlockHandler.attr(pxy),
					service.list(PageHandler.attr(pxy)));
			DispatcherServlet.send(request, response);
			break; 
		case Action.SEARCH:
			System.out.println("서치진입:  "+map.get("search"));
			map=ParamsIterator.execute(request);
			cmd=PageHandler.attr(pxy);
			
			cmd.setColumn("name");
			cmd.setSearch(String.valueOf(map.get("search")));
			cmd.setPageNumber(
					request.getParameter("pageNumber"));
			cmd.setStartRow(PageHandler.attr(pxy).getStartRow());
			cmd.setEndRow(PageHandler.attr(pxy).getEndRow());
			pxy.setTheNumberOfRows(
					Integer.parseInt(service.count(cmd)));
			pxy.setPageNumber(
					Integer.parseInt(
					cmd.getPageNumber()));
			pxy.execute(BlockHandler.attr(pxy),
					service.findByName(cmd));
			DispatcherServlet.send(request, response);
			break; 
		case Action.UPDATE: 
			map=ParamsIterator.execute(request);
			System.out.println("수정할 ID: "+map.get("id"));
			System.out.println("수정할 Email: "+map.get("email"));
			//cmd.setSearch(request.getParameter("id"));
			//service.modify(service.findById(cmd));
			DispatcherServlet.send(request, response);
			break;
		case Action.DELETE: 
			//service.remove(request.getParameter("id"));
			response.sendRedirect(request.getContextPath()
					+"/member.do?action=list&page=member_list&pageNumber=1");
			break;
		case Action.DETAIL: 
			cmd.setSearch(request.getParameter("id"));
			System.out.println("###"+service.findById(cmd).getName());
			request.setAttribute("student", service.findById(cmd));
			DispatcherServlet.send(request, response);
			break;
		default:System.out.println("FAIL..");break;
		}
	}
}







