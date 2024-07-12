package com.tap.dao;

import java.util.List;

import com.tap.model.Students;

public interface StudentInf {
	public void add(Students students);
	public List<Students> getThroughId(int id);
	public List<Students> getThroughName(String name);
	public List<Students> getThoughEmail(String email);
	public List<Students> getThroughAge(int age);
	public void delete(int id);
	public void update(Students s);
	public List<Students> getAll();

}
