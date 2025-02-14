package com.gn.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.gn.member.service.MemberService;
import com.gn.member.vo.Member;

@WebServlet(name="memberupdateendservlet", urlPatterns="/memberUpdateEnd")
public class MemberUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberUpdateEndServlet() {
        super();

    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pw = request.getParameter("member_pw");
		String name = request.getParameter("member_name");
		String no = request.getParameter("member_no");
		
		int result = new MemberService().updateMember(pw,name,no);
				
		JSONObject obj = new JSONObject();
		obj.put("res_cods", "500");
		obj.put("res_msg", "오류");
		
		if(result > 0) {
			obj.put("res_cods", "200");
			obj.put("res_msg", "수정됨");
		}
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(obj);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
