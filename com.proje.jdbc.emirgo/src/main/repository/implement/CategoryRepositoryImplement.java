package com.proje.repository.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.proje.model.Category;
import com.proje.repository.CategoryRepository;

public class CategoryRepositoryImplement implements CategoryRepository{

	
	private final Logger log=(Logger) LogManager.getLogger();
	private Connection con;
	private PreparedStatement pS;
	private ResultSet rS;
	
	@Override
	public Category findCategoryById(int id) {
		
		return null;
	}

	@Override
	public List<Category> findCategories() {
		// TODO Auto-generated method stub
		return null;
	}

}
