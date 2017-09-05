package com.gms.web.proxy;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gms.web.service.MemberService;
import com.gms.web.service.MemberServiceImpl;

import lombok.Getter;
import lombok.Setter;

public class PageProxy extends Proxy{
	
	@Getter @Setter protected int 
		pageSize,blockSize,theNumberOfRows,pageNumber;
	
	public PageProxy(HttpServletRequest request) {
		super(request);
	}
	
	public void execute(int[] arr,List<?>list) {
		request.setAttribute("pageNumber", arr[0]);
		request.setAttribute("theNumberOfPages", arr[1]);
		request.setAttribute("startPage",arr[2]);
		request.setAttribute("endPage",arr[3]);
		request.setAttribute("prevBlock", arr[4]);
		request.setAttribute("nextBlock", arr[5]);
		request.setAttribute("list", list);
	}
}








