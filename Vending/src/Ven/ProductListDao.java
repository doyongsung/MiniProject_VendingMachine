package Ven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class ProductListDao {

	// 싱글톤 패턴 : 여러개의 인스턴스를 생 성하지 못하도록 하는 디자인 패턴
	// 1. 외부 클래스 또는 인스턴스에서 해당 클래스로 인스턴스를 생성하지 못하도록 처리
	private ProductListDao(){
	}
	
	// 2. 클래스 내부에서 인스턴스를 만들고 
	static private ProductListDao dao = new ProductListDao(); 
	
	// 3. 메소드를 통해서 반환 하도록 처리
	public static ProductListDao getInstance() {
		return dao;
	}
	
	
	// 1. 전체 데이터 검색 기능
	// 반환 타입 List<Dept>
	// 매개변수 - Connection 객체 : Statement
	ArrayList<ProductList> getProductList(Connection conn) {

		ArrayList<ProductList> list = null;

		// 데이터 베이스의 ITEMINFO 테이블 이용 select 결과를 -> list 저장
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			String sql = "select * from iteminfo order by itemcode";

			// 결과 받아오기
			rs = stmt.executeQuery(sql);

			list = new ArrayList<>();

			// 데이터를 ITEMINFO 객체로 생성 -> list에 저장
			while (rs.next()) {
				
				ProductList d = new ProductList(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
				
				list.add(d);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return list;
	}
}
	
	