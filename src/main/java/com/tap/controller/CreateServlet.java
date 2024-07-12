package com.tap.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.daoImp.StudentsImp;
import com.tap.model.Students;

@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		int age = Integer.parseInt(req.getParameter("age"));
		StudentsImp studentsImp = new StudentsImp();
		Students student = new Students(name,email,age);
		studentsImp.add(student);
		System.out.print("name : "+name);
		System.out.print("name : "+email);
		System.out.print("name : "+age);
		RequestDispatcher rd = req.getRequestDispatcher("ReadServlet");
		rd.forward(req, resp);
		
		
		
	}

}
