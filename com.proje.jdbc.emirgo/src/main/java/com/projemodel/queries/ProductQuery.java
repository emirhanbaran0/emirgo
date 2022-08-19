package com.proje.model.queries;

public class ProductQuery {

	public static final String saveProductQuery="INSERT INTO product(ProductId,ProductName,ProductPrice,StokNumber,AddDate,UpdateDate,CategoryId,BrandId) Values (?,?,?,?,?,?,?,?)";
	
	public static final String updateProductQuery="UPDATE product SET ProductName=?,ProductPrice=?,StokNumber=? ,UpdateDate=?,CategoryId=?,BrandId=? WHERE ProductId=? ";
	
	public static final String deleteUser_ProductQuery="DELETE FROM user_product WHERE ProductId=?";
	public static final String deleteProductQuery="DELETE FROM product WHERE ProductId=?";
	
	public static final String findProductByIdQuery="SELECT * FROM product WHERE ProductId=?";
	
	public static final String findProductQuery="SELECT * FROM product";

}
