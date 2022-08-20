package com.proje.repository.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.proje.connection.DBConnection;
import com.proje.model.Brand;
import com.proje.model.Category;
import com.proje.model.Product;
import com.proje.model.queries.ProductQuery;
import com.proje.repository.ProductRepository;

public class ProductRepositoryImplement implements ProductRepository {

	private final Logger log=(Logger) LogManager.getLogger();
	private Connection con;
	private PreparedStatement pS;
	private ResultSet rS;
	
	
	@Override
	public Product saveProduct(Product product) {
		
		con=DBConnection.getConnection();
		
		
		try {
			
			LocalDateTime ldt=LocalDateTime.now();
			pS=con.prepareStatement(ProductQuery.saveProductQuery);
			//INSERT INTO product(ProductId,ProductName,ProductPrice,StokNumber,AddDate,UpdateDate,CategoryId,BrandId) Values (?,?,?,?,?,?,?,?)
			pS.setInt(1, product.getProductId());
			pS.setString(2, product.getProductName());
			pS.setDouble(3, product.getProductPrice());
			pS.setInt(4, product.getStokNumber());
			pS.setTimestamp(5, Timestamp.valueOf(ldt));
			pS.setDate(6, null);
			pS.setInt(7, product.getCategory().getCategoryId());
			pS.setInt(8, product.getBrand().getBrandId());
			
			pS.executeUpdate();
		} catch (SQLException e) {
			log.warn(product.getProductId()+"'li ürün eklenirken hata meydana geldi! Hata"+e);
			//e.printStackTrace();
		}finally {
			DBConnection.closePreparedStatement(pS);
			DBConnection.closeConnection(con);
		}
		
		return product;
	}

	@Override
	public boolean saveBatchProduct(List<Product> products) {
		
		con=DBConnection.getConnection();
		
		
		try {
			
			LocalDateTime ldt=LocalDateTime.now();
			pS=con.prepareStatement(ProductQuery.saveProductQuery);
			//INSERT INTO product(ProductId,ProductName,ProductPrice,StokNumber,AddDate,UpdateDate,CategoryId,BrandId) Values (?,?,?,?,?,?,?,?)
			for(Product product : products)
			
			{
				pS.setInt(1, product.getProductId());
				pS.setString(2, product.getProductName());
				pS.setDouble(3, product.getProductPrice());
				pS.setInt(4, product.getStokNumber());
				pS.setTimestamp(5, Timestamp.valueOf(ldt));
				pS.setDate(6, null);
				pS.setInt(7, product.getCategory().getCategoryId());
				pS.setInt(8, product.getBrand().getBrandId());
				
				
			}
				pS.executeBatch();
		} catch (SQLException e) {
			log.warn(" Ürün listesi eklenirken hata meydana geldi! Hata"+e);
			//e.printStackTrace();
			return false;
		}finally {
			DBConnection.closePreparedStatement(pS);
			DBConnection.closeConnection(con);
		}
			return true;
	}

	@Override
	public Product updateProduct(Product product) {
		
		LocalDateTime ldt=LocalDateTime.now();
		
		try {
				pS=con.prepareStatement(ProductQuery.updateProductQuery);
				
				
				pS.setString(1, product.getProductName());
				pS.setDouble(2, product.getProductPrice());
				pS.setInt(3, product.getStokNumber());
				pS.setTimestamp(4, Timestamp.valueOf(ldt));
				pS.setInt(5, product.getCategory().getCategoryId());
				pS.setInt(6, product.getBrand().getBrandId());
				pS.setInt(7, product.getProductId());
				
		}catch(SQLException e)
		{
			log.warn("Ürün güncellenirken hata meydana geldi! Hata:"+e);
		}finally {
			DBConnection.closePreparedStatement(pS);
			DBConnection.closeConnection(con);
		}
		
		return product;
	}

	@Override
	public boolean deleteProduct(int id) {
		
		con=DBConnection.getConnection();
		
		try {
			pS=con.prepareStatement(ProductQuery.deleteUser_ProductQuery);
			pS.setInt(1, id);
			pS.executeUpdate();
			
			pS=con.prepareStatement(ProductQuery.deleteProductQuery);
			pS.setInt(1, id);
			pS.executeUpdate();
			
		} catch (SQLException e) {
			log.warn(id+"'li Product Silinirken Hata meydana geldi! Hata: "+e);
			return false;
		}finally {
			DBConnection.closePreparedStatement(pS);
			DBConnection.closeConnection(con);
		}
		
		return true;
	}

	@Override
	public Product findProductById(int id) {
		
		con=DBConnection.getConnection();
		Product product=null;
		try {
			pS=con.prepareStatement(ProductQuery.findProductByIdQuery);
			pS.setInt(1, id);
			rS=pS.executeQuery();
			if(rS.next())
			{
				int categoryId=rS.getInt("CategoryId");
				String categoryName=rS.getString("CategoryName");
				
				int brandId=rS.getInt("BrandId");
				String brandName=rS.getString("BrandName");
				
				Brand brand=new Brand(brandId,brandName);
				Category category=new Category(categoryId,categoryName);
				
				product=new Product(rS.getInt("ProductId"),rS.getString("ProductName"),rS.getDouble("ProductPrice"),rS.getInt("StokNumber"),rS.getDate("AddDate"),rS.getDate("UpdateDate"),category,brand);
			}
		} catch (SQLException e) {
			log.warn(id+"'li Ürün bulunurken Hata meydana geldi! Hata: "+e);
			e.printStackTrace();
		}finally {
			DBConnection.closeResultSet(rS);
			DBConnection.closePreparedStatement(pS);
			DBConnection.closeConnection(con);
		}
		
		return product;
	}

	@Override
	public List<Product> findProducts() {
		
		con=DBConnection.getConnection();
		ArrayList<Product> products=new ArrayList<>();
		
		
		try {
			pS=con.prepareStatement(ProductQuery.findProductQuery);
			rS=pS.executeQuery();
			while(rS.next())
			{
				
				int categoryId=rS.getInt("CategoryId");
				String categoryName=rS.getString("CategoryName");
				
				int brandId=rS.getInt("BrandId");
				String brandName=rS.getString("BrandName");
				
				Brand brand=new Brand(brandId,brandName);
				Category category=new Category(categoryId,categoryName);
				
				Product product=new Product(rS.getInt("ProductId"),rS.getString("ProductName"),rS.getDouble("ProductPrice"),rS.getInt("StokNumber"),rS.getDate("AddDate"),rS.getDate("UpdateDate"),category,brand);
				products.add(product);
			}
		} catch (SQLException e) {
			log.warn("Ürünler  bulunurken Hata meydana geldi! Hata: "+e);
			e.printStackTrace();
		}finally {
			DBConnection.closeResultSet(rS);
			DBConnection.closePreparedStatement(pS);
			DBConnection.closeConnection(con);
		}
		
		
		return products;
	}

}
