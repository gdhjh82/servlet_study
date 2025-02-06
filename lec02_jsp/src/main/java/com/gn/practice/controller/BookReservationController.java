package com.gn.practice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bookOrder")
public class BookReservationController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public BookReservationController() {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String userName = req.getParameter("user_name");
		String userPhone = req.getParameter("user_phone");
		String userEmail = req.getParameter("user_email");
		String[] book = req.getParameterValues("user_book");
		String time = req.getParameter("date_time");
		
		int price = 0;
	
		for(String a : book) {
			switch(a) {
			case "1" : price = 1500; break;
			case "2" : price = 1800; break;
			case "3" : price = 2000; break;
			}
		}
		int date = 500;
		if(time.equals("1")){
			price += 0;
		}else {
			 int a = Integer.parseInt(time);
			for(int i = 0 ; i < a-1 ; i++){
				price += date;
			}
	}

		

		
		
		
		RequestDispatcher view = req.getRequestDispatcher("views/bookConfirmation.jsp");
		req.setAttribute("name", userName);
		req.setAttribute("phone", userPhone);
		req.setAttribute("email", userEmail);
		req.setAttribute("book", book);
		req.setAttribute("price", price);
		req.setAttribute("date", time);
		view.forward(req, resp);
		
		System.out.println("이름 :"+userName);
		System.out.println("전화번호 :"+userPhone);
		System.out.println("이메일 :"+userEmail);
		System.out.println("빌린 도서"+book);
		System.out.println("가격"+price);
		System.out.println("대여 기간"+date);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

}
