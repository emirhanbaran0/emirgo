package com.proje.repository.implement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.proje.connection.DBConnection;
import com.proje.model.Brand;
import com.proje.model.Category;
import com.proje.model.Product;
import com.proje.model.User;
import com.proje.model.queries.UserQuery;
import com.proje.repository.UserRepository;

public class UserRepositoryImplement implements UserRepository {

	private final Logger log=(Logger) LogManager.getLogger();
	private Connection con;
	private PreparedStatement pS;
	private ResultSet rS;
	
	@Override
	public User saveUser(User user) {
		
		con=DBConnection.getConnection();
		
		try {
			pS=con.prepareStatement(UserQuery.saveUserQuery);
			pS.setInt(1, user.getUserId());
			pS.setString(2, user.getFirstName());
			pS.setString(3, user.getLastName());
			pS.setDate(4, user.getBirthDate());
			pS.setString(5, user.getUserName());
			
			pS.executeUpdate();
		} catch (SQLException e) {
			log.warn(user.getUserId() +"'li user Kaydedilirken Hata meydana geldi! Hata: "+e);
			e.printStackTrace();
		}finally {
			DBConnection.closeResultSet(rS);
			DBConnection.closeConnection(con);
		}
		
		return user;
	}

	@Override
	public boolean saveUserProduct(int userId, int productId) {
		
			con=DBConnection.getConnection();
		
		try {
			pS=con.prepareStatement(UserQuery.saveUser_ProductQuery);
			pS.setInt(1, userId);
			pS.setInt(2,productId);
			pS.executeUpdate();
		} catch (SQLException e) {
			log.warn("USER_PRODUCT  Kaydedilirken Hata meydana geldi! Hata: "+e);
			e.printStackTrace();
		}finally {
			DBConnection.closeResultSet(rS);
			DBConnection.closeConnection(con);
		}
		
		return true;
	}

	@Override
	public User updateUser(User user) {
		
		con=DBConnection.getConnection();
		
		try {
			pS=con.prepareStatement(UserQuery.updateUserQuery);
			pS.setString(1, user.getFirstName());
			pS.setString(2,user.getLastName());
			pS.setDate(3, user.getBirthDate());
			pS.setString(4, user.getUserName());
			pS.setInt(5, user.getUserId());
			pS.executeUpdate();
		} catch (SQLException e) {
			log.warn(user.getUserId()+"'li user  Güncellenirken Hata meydana geldi! Hata: "+e);
			e.printStackTrace();
		}finally {
			DBConnection.closeResultSet(rS);
			DBConnection.closeConnection(con);
		}
		return null;
	}

	@Override
	public boolean deleteUser(int id) {
		
		con=DBConnection.getConnection();
		
		try {
			pS=con.prepareStatement(UserQuery.deleteUser_ProductQuery);
			pS.setInt(1, id);
			pS.executeUpdate();
		} catch (SQLException e) {
			log.warn(id+"'li User  Silinirken Hata meydana geldi! Hata: "+e);
			e.printStackTrace();
		}finally {
			DBConnection.closePreparedStatement(pS);
			DBConnection.closeConnection(con);
		}
		
		return true;
	}

	@Override
	public User findUserById(int id) {
		
		con=DBConnection.getConnection();
		User user=null;
		try {
			pS=con.prepareStatement(UserQuery.findUserByIdQuery);
			pS.setInt(1, id);
			rS=pS.executeQuery();
			if(rS.next())
			{
				user=new User(rS.getInt("UserId"),rS.getString("Ad"),rS.getString("Soyad"),rS.getDate("Birtday"),rS.getString("Username"));
			}
		} catch (SQLException e) {
			log.warn(id+"'li user bulunurken Hata meydana geldi! Hata: "+e);
			e.printStackTrace();
		}finally {
			DBConnection.closeResultSet(rS);
			DBConnection.closePreparedStatement(pS);
			DBConnection.closeConnection(con);
		}
		
		return user;
	}

	@Override
	public User findUserProductsById(int id) {
		
		con=DBConnection.getConnection();
		User user=null;
		
		try {
			pS=con.prepareStatement(UserQuery.findUserProductsQuery );
			pS.setInt(1, id);
			rS=pS.executeQuery();
			boolean status=true;
			ArrayList<Product> products=new ArrayList<>();
			
			while(rS.next())
			{
				if(status)
				{
					user=new User(rS.getInt("UserId"),rS.getString("Ad"),rS.getString("Soyad"),rS.getDate("Birtday"),rS.getString("Username"));
					status=false;
				}	
				
				int productId=rS.getInt("PrductId");
				String productName=rS.getString("ProductName");
				Double productPrice=rS.getDouble("ProductPrice");
				int stokNumber=rS.getInt("StokNumber");
				Date addDate=rS.getDate("AddDate");
				Date updateDate=rS.getDate("UpdateDate");
				
				int categoryId=rS.getInt("CategoryId");
				String categoryName=rS.getString("CategoryName");
				
				int brandId=rS.getInt("BrandId");
				String brandName=rS.getString("BrandName");
				
				Category category=new Category(categoryId,categoryName);
				Brand brand=new Brand(brandId,brandName);
				Product product=new Product(productId,productName,productPrice,stokNumber,addDate,updateDate,category,brand);
				
				products.add(product);
			}
			
			user.setProducts(products);
			
		} catch (SQLException e) {
			log.warn("Kullanıcı urunleri bulunurken  Hata meydana geldi! Hata: "+e);
			e.printStackTrace();
		}finally {
			DBConnection.closeResultSet(rS);
			DBConnection.closePreparedStatement(pS);
			DBConnection.closeConnection(con);	
		}
		
		return user;
	}

	@Override
	public List<User> findUsers() {
		con=DBConnection.getConnection();
		ArrayList<User> users=new ArrayList<>();
		try {
			pS=con.prepareStatement(UserQuery.findUserQuery);
			rS=pS.executeQuery();
			while(rS.next())
			{
				User user=new User(rS.getInt("UserId"),rS.getString("Ad"),rS.getString("Soyad"),rS.getDate("Birtday"),rS.getString("Username"));
				users.add(user);
			}
			
			
		} catch (SQLException e) {
			log.warn(" User listesi alınırken Hata meydana geldi! Hata: "+e);
			e.printStackTrace();
		}finally {
			DBConnection.closeResultSet(rS);
			DBConnection.closePreparedStatement(pS);
			DBConnection.closeConnection(con);
		}
		
		return users;
	}

}
