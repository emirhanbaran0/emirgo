package com.proje.model.queries;

public class UserQuery {

	public static final String saveUserQuery="INSERT INTO user(UserId,Ad,Soyad,Birthday,Username) Values (?,?,?,?,?)";
	
	public static final String saveUser_ProductQuery="INSERT INTO user_product(UserId,ProductId) Values(?,?) ";
	
	public static final String updateUserQuery="UPDATE user SET Ad=?,Soyad=?,Birthday=? ,Username=? WHERE UserId=? ";
	
	public static final String deleteUser_ProductQuery="DELETE FROM user_product WHERE UserId=?";
	
	public static final String deleteUserByIdQuery="DELETE FROM user WHERE UserId=?";
	
	public static final String findUserByIdQuery="SELECT * FROM user WHERE UserId=?";
	
	public static final String findUserQuery="SELECT * FROM user";
	
}
