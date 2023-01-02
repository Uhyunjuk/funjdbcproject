package com.kh.jdbc.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTemplate {

	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER = "STUDENT";
	private final String PASSWORD = "STUDENT";

	// jdbc싱글턴패턴
	// 자바의 모든 객체들을 싱글턴패턴을 적용해서
	// 메모리효율을 높일수 있게 해야한다 << 아 그렇습니까?
	private static JDBCTemplate instance;

	// 생성자만들어주기
	public JDBCTemplate() {
		try {
			Class.forName(DRIVER_NAME); // 반드시 해줘야하는 것
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static JDBCTemplate getDriverLoad() {
		if (instance == null) {
			instance = new JDBCTemplate();
		}
		return instance;
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn.setAutoCommit(false); //false로 두면 오토커밋해제임! 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	public void commit(Connection conn) {
		try {
			if(conn !=null && !conn.isClosed())
				conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rollback(Connection conn) {
		try {
			if(conn !=null && !conn.isClosed())
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
