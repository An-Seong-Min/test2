package com.hello.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBBean {
	
	// 딱 하나만 만들기 위해 private static 사용
	private static DBBean instance = new DBBean();
	//new 연산을 막는다 -> 생성자를 private 로 막는다
	private DBBean() {}
	// get 함수 를 만들어준다.
	public static DBBean getInstance() {
		return instance;
	}
	//static 함수나 변수를 부를때 클래스이름. 라고 쓴다.
	
	
//	private Connection getConnection() throws Exception{
//		Context initCtx = new InitialContext();
//		Context envCtx = (Context) initCtx.lookup("java:comp/env");
//		DataSource ds = (DataSource)envCtx.lookup("jdbc/jsp");
//		return ds.getConnection();
//	}
	
	   private Connection getConnection() {
		      Context context;
		      DataSource datasource = null;
		      Connection conn = null;
		      
		      try {
		         context = new InitialContext();
		         datasource = (DataSource)context.lookup("java:comp/env/jdbc/jsp");
		         conn = datasource.getConnection();
		      } catch(NamingException | SQLException e) {
		         e.printStackTrace();
		         return null;
		      }
		      return conn;
		   }
	
	public ArrayList<TodoBean> select() {
	      
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      ArrayList<TodoBean> arr = new ArrayList<TodoBean>();
	      
	      try {
	         conn = getConnection();
	         String sql = "select * from todo";
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
	            TodoBean bean = new TodoBean();
	            bean.setId(rs.getInt("id"));
	            bean.setJob(rs.getString("job"));
	            bean.setDone(rs.getBoolean("done"));
	            
	            arr.add(bean);
	         }
	      } catch (Exception e) {
	         // TODO Auto-generated catch block
	         System.out.println("select error");
	      }finally {
	         try {rs.close();} catch (SQLException e) {}
	         try {pstmt.close();} catch (SQLException e) {}
	         try {conn.close();} catch (SQLException e) {}         
	      }
	      
	      return arr;
	   }
	
	public int insert(String job) {
		Connection conn = null;
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();	
			String sql = "insert into todo (job) values(?)";
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, job);
			result = pstmt.executeUpdate();	
			
			// 1인지 체크하고 키를 받은 뒤 리턴
			if(result == 1) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
					result = rs.getInt(1);
				}
			}
		}	catch(Exception ex) {
			System.out.println("할 일 추가 실패");
		}  finally {
			if(pstmt != null) try { pstmt.close();} catch(SQLException ex) {}
			if(conn != null) try { conn.close();} catch(SQLException ex) {}
		}
		return result;
	}
	
		public int update(int id, boolean done) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			int x = 0;
			try {
				conn = getConnection();
				
				String sql = "update todo set done=? where id = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setBoolean(1, done);
				pstmt.setInt(2, id);
				x = pstmt.executeUpdate();		
		} catch(Exception ex) {
				System.out.println("업데이트 실패");
			} finally {
				if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
				if(conn != null) try { conn.close(); } catch(SQLException ex) {}
				}
			return x;
			}			
}
