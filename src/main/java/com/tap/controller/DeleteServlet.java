package com.tap.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.daoImp.StudentsImp;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		StudentsImp studentsImp = new StudentsImp();
		studentsImp.delete(id);
		RequestDispatcher rd = req.getRequestDispatcher("ReadServlet");
		rd.forward(req, resp);
	
	}

}
