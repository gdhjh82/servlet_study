package com.gn.controller.guestbook;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;


@WebServlet("/guestBookEnd")
public class GuestBookEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GuestBookEndServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String guestName = request.getParameter("guestName");
		String guestMsg = request.getParameter("guestMsg");
		System.out.println(guestName);
		
		JSONObject obj = new JSONObject();
		obj.put("name",guestName);
		obj.put("msg",guestMsg);
			
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(obj);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
