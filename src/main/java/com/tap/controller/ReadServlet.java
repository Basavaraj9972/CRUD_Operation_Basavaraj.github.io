package com.tap.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoImp.StudentsImp;
import com.tap.model.Students;

@WebServlet("/ReadServlet")
public class ReadServlet extends HttpServlet  {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StudentsImp studentsImp = new StudentsImp();
		HttpSession session = req.getSession();
		String Column_data = null;
		Column_data = req.getParameter("Column_data");
		String studentInformation = req.getParameter("studentInformation");
		if(Column_data != null && Column_data.equals("id")) {
			int id = Integer.parseInt(studentInformation);
			List<Students> throughId = studentsImp.getThroughId(id);
			if(throughId != null && !throughId.isEmpty()) {
				session.setAttribute("allStudents", throughId);
			}
			else if(throughId.isEmpty()){
				session.setAttribute("msg","Invalid Student Id");
			}
		}
		else if(Column_data != null && Column_data.equals("name")) {
			List<Students> throughName = studentsImp.getThroughName(studentInformation);
			if(throughName != null && !throughName.isEmpty()) {
				session.setAttribute("allStudents", throughName);
				System.out.println(throughName+"true Student");
			}
			else if(throughName.isEmpty()) {
				System.out.println(throughName+" false Student");
				session.setAttribute("msg","Invalid Student Name");
			}
		}
		else if(Column_data != null && Column_data.equals("email")) {
			List<Students> thoughEmail = studentsImp.getThoughEmail(studentInformation);
			if(thoughEmail != null && !thoughEmail.isEmpty() ) {
				session.setAttribute("allStudents", thoughEmail);
			}
			else if(thoughEmail.isEmpty()){
				session.setAttribute("msg","Invalid Student Email");
			}
		}
		else if(Column_data != null && Column_data.equals("age")) {
			int age = Integer.parseInt(studentInformation);
			List<Students> throughAge = studentsImp.getThroughAge(age);
			if(throughAge != null && !throughAge.isEmpty()) {
				session.setAttribute("allStudents", throughAge);
			}
			else if(throughAge.isEmpty()){
				session.setAttribute("msg","Invalid Student Age");
			}
		}
		else if(Column_data == null) {
			List<Students> allStudents = studentsImp.getAll();
			session.setAttribute("allStudents",allStudents);
			System.out.println(allStudents);
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("list.jsp");
		rd.forward(req, resp);
	}
}
