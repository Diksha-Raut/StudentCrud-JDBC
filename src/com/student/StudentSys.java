package com.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentSys {
	public static Connection getConnection() {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/student";
		String user = "root";
		String pass = "tiger";

		Connection conn = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("connected");

		} catch (Exception e) {
			System.out.println("Error!!!" + e.getMessage());
		}
		return conn;
	}

	public static void create() {
		Connection conn = getConnection();
		try {
			Statement smt = conn.createStatement();

			// Create table if not exists
			String createTableQuery = "CREATE TABLE IF NOT EXISTS stud (id INT PRIMARY KEY, name VARCHAR(20),phone VARCHAR(15), address VARCHAR(100))";
			smt.executeUpdate(createTableQuery);
			System.out.println("Table created successfully!");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("not created");
		}

	}

	public static void insert(int i, String name, int phone, String address) {
		Connection conn = getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("insert into stud values (?,?,?,?)");
			pst.setInt(1, i);
			pst.setString(2, name);
			pst.setInt(3, phone);
			pst.setString(4, address);

			pst.executeUpdate();
			System.out.println("Table inserted successfully!");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Table not inserted");
		}
	}

	// select
	public static void select(int i) {
		Connection conn = getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("Select * from stud where id= ?");
			pst.setInt(1, i);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				System.out.println("ID:" + rs.getInt("id"));
				System.out.println("Name:" + rs.getString("name"));
				System.out.println("Phone:" + rs.getInt("phone"));
				System.out.println("Add:" + rs.getString("address"));

			} else {
				System.out.println("Student not found");
			}
		} catch (Exception e) {
			System.out.println("Error" + e.getMessage());
		}
	}

	// update

	public static void update(int i, String name, int phone, String address) {
		Connection conn = getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("Update stud set name =?, phone=?, address=? where id=?");

			pst.setString(1, name);
			pst.setInt(2, phone);
			pst.setString(3, address);
			pst.setInt(4, i);

			pst.executeUpdate();
			System.out.println("STUDENT UPDATED");

		} catch (Exception e) {
			System.out.println("Error.. " + e.getMessage());
		}

	}

	// Delete

	public static void delete(int i) {
		Connection conn = getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("Delete from stud where id=?");

			pst.setInt(1, i);
			System.out.println("STUDENT Deleted");
			pst.executeUpdate();

		} catch (Exception e) {
			System.out.println("Error.. " + e.getMessage());
		}

	}

	public static void main(String[] args) {

//		insert(1, "diksha",4386, "dmaial");
		select(1);
//		update(1,"Diksha", 9999, "map" );
//		delete(1);
//		StudentSys.create();

	}

}
