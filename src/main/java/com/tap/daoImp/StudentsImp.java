package com.tap.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.StudentInf;
import com.tap.model.Students;

public class StudentsImp implements StudentInf {
	
	Connection con = null;
	String INSERT_QUERY = "insert into `students`(`name`, `email`, `age`) values(?,?,?)";
	String SELECT_QUERY_id = "select * from `students` where id =?";
	String SELECT_QUERY_name = "select * from `students` where name =?";
	String SELECT_QUERY_email = "select * from `students` where email =?";
	String SELECT_QUERY_age = "select * from `students` where age =?";
	String DELETE_QUERY = "delete from `students` where id=?";
	String UPDATE_QUERY = "update `students` set `name`=?, `email`=?, `age`=? where `id`=? ";
	String SELECT_ALL_QUERY = "select * from `students`";
	
	public StudentsImp() {
		String url = "jdbc:mysql://localhost:3306/student_db";
		String username ="root";
		String password ="root";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.print("Class loaded successfully");
				con = DriverManager.getConnection(url,username,password);
				System.out.println("Database is connection is Established Successfully");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void add(Students students) {
		try {
			PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY);
			pstmt.setString(1, students.getName());
			pstmt.setString(2, students.getEmail());
			pstmt.setInt(3, students.getAge());
			int i = pstmt.executeUpdate();
			System.out.print(i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	
	@Override
	public void delete(int id) {
		try {
			PreparedStatement pstmt = con.prepareStatement(DELETE_QUERY);
			pstmt.setInt(1, id);
			int i = pstmt.executeUpdate();
			System.out.println(i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Students s) {
		try {
			PreparedStatement pstmt = con.prepareStatement(UPDATE_QUERY);
			pstmt.setString(1,s.getName());
			pstmt.setString(2,s.getEmail());
			pstmt.setInt(3,s.getAge());
			pstmt.setInt(4,s.getId());
			int i = pstmt.executeUpdate();
			System.out.print(i);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Students> getAll() {
		ArrayList<Students> list = new ArrayList<Students>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL_QUERY);
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				int age = rs.getInt("age");
				Students s = new Students(id,name,email,age);
				list.add(s);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public List<Students> getThroughId(int id) {
		Students s =null;
		ArrayList<Students> list = new ArrayList<Students>();
		try {
			PreparedStatement pstmt = con.prepareStatement(SELECT_QUERY_id);
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				int age = rs.getInt("age");
				s = new Students(id,name,email,age);
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public List<Students> getThroughName(String name) {
		Students s =null;
		ArrayList<Students> list = new ArrayList<Students>();
		try {
			PreparedStatement pstmt = con.prepareStatement(SELECT_QUERY_name);
			pstmt.setString(1,name);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String email = rs.getString("email");
				int age = rs.getInt("age");
				s = new Students(id,name,email,age);
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Students> getThoughEmail(String email) {
		Students s =null;
		ArrayList<Students> list = new ArrayList<Students>();
		try {
			PreparedStatement pstmt = con.prepareStatement(SELECT_QUERY_email);
			pstmt.setString(1,email);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				s = new Students(id,name,email,age);
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Students> getThroughAge(int age) {
		Students s =null;
		ArrayList<Students> list = new ArrayList<Students>();
		try {
			PreparedStatement pstmt = con.prepareStatement(SELECT_QUERY_age);
			pstmt.setInt(1,age);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				s = new Students(id,name,email,age);
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
