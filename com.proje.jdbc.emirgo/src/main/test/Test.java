package com.proje.test;

import java.util.List;

import com.proje.model.Brand;
import com.proje.repository.implement.CategoryRepositoryImplement;
import com.proje.repository.implement.ProductRepositoryImplement;
import com.proje.repository.implement.UserRepositoryImplement;
import com.repository.service.BrandService;
import com.repository.service.CategoryService;
import com.repository.service.ProductService;
import com.repository.service.UserService;
import com.repository.service.implement.BrandServiceImplement;

public class Test {

	public static void main(String[] args) {
		
		BrandService bS=new BrandServiceImplement();
		
		CategoryRepositoryImplement cS= new CategoryRepositoryImplement();
		
		ProductRepositoryImplement pS=new ProductRepositoryImplement();
		
		UserRepositoryImplement  uS=new UserRepositoryImplement();
	
		Brand brand=bS.findBrandById(1);
		
		
			System.out.println("Brand ID: "+brand.getBrandId()+ " Brand Name: "+brand.getBrandName());
		
	}

}
