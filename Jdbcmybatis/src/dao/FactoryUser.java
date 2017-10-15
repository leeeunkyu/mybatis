package dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * dao ��������ȭ�� ���� factory���� Ŭ����
 * @author kosta
 *
 */
public class FactoryUser {
	private static FactoryUser instance = new FactoryUser();
	/**DB ����̹� ����*/
	private String driverinfo;
	/**DB URL ���*/
	private String url;
	/**DB ���� ID*/
	private String driver_user;
	/**DB ���� PWD*/
	private String driver_pwd;
	private ResourceBundle bundle;
	public static SqlSessionFactory factory;

	public FactoryUser() {
		if (instance != null) {
			throw new AssertionError();
		}
	}
	
	public static FactoryUser getInstance() {
		return instance;
	}
	
	static {
		String resource = "resources/mybaties-config.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
			factory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			System.out.println("Error Message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch(IOException e) {
				System.out.println("Error Message : " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 
	 * @param url
	 * @param user
	 * @param password
	 * @return Connection
	 * DB�� ����
	 */
	public Connection getConnection() {
		try {
			return DriverManager.getConnection(this.url, this.driver_user, this.driver_pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * CUD �ڿ����� �޼ҵ�
	 * @param conn
	 * @param stmt
	 */
	public void close(Connection conn,Statement stmt) {
		close(conn,stmt,null);
	}
	
	/**
	 * R �ڿ����� �޼ҵ�
	 * @param conn
	 * @param stmt
	 * @param rs
	 */
	public void close(Connection conn,Statement stmt,ResultSet rs) {
		try {
			if(stmt!=null) {stmt.close();}
			if(conn!=null) {conn.close();}
			if(rs!=null) {rs.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
