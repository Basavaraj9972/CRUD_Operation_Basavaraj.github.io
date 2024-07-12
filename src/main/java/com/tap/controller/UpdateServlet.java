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

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String age_s = req.getParameter("age");
		String id_s = req.getParameter("id");
		int age = Integer.parseInt(age_s);
		int id = Integer.parseInt(id_s);
		StudentsImp studentsImp = new StudentsImp();
		Students student = new Students(id,name,email,age);
		studentsImp.update(student);
		System.out.println(name+" "+email+" "+age+" "+id);
		RequestDispatcher rd = req.getRequestDispatcher("ReadServlet");
		rd.forward(req, resp);
		
	}
}
