package com.proje.repository.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.proje.connection.DBConnection;
import com.proje.model.Brand;
import com.proje.model.queries.BrandQuery;
import com.proje.repository.BrandRepository;

public class BrandRepositoryImplement implements BrandRepository {

	private final Logger log=(Logger) LogManager.getLogger();
	private Connection con;
	private PreparedStatement pS;
	private ResultSet rS;
	
	@Override
	public Brand findBrandById(int id) {
		
		con=DBConnection.getConnection();
		Brand brand=null;
		
		try {
			pS=con.prepareStatement(BrandQuery.findBranByIdQuery);
			pS.setInt(1, id);
			rS=pS.executeQuery();
			
			if(rS.next())
			{
				brand=new Brand(rS.getInt("BrandId"),rS.getString("BrandName"));
			}
			
		}catch(SQLException e)
		{
			log.warn("Marka bulunurken hata meydana geldi. Hata: "+e);
		}finally {
			DBConnection.closeResultSet(rS);
			DBConnection.closePreparedStatement(pS);
			DBConnection.closeConnection(con);
		}
		
		return brand;
	}

	@Override
	public List<Brand> findBrands() {
		ArrayList<Brand> brands=new ArrayList<>();
		
		try {
			pS=con.prepareStatement(BrandQuery.findBrandsQuery);
			rS=pS.executeQuery();
			
			while(rS.next())
			{
				Brand brand=new Brand(rS.getInt("BrandId"),rS.getString("BrandName"));
				brands.add(brand);
			}
			
		}catch(SQLException e)
		{
			log.warn("Marka Listesi alinirken hata meydana geldi. Hata: "+e);
		}finally {
			DBConnection.closeResultSet(rS);
			DBConnection.closePreparedStatement(pS);
			DBConnection.closeConnection(con);
		}
		
		return brands;
	}

	
	
}
