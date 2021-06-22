package Ven;

import java.sql.Connection;
import java.sql.PreparedStatement;
<<<<<<< HEAD

public class ProductListDao {
	
	private ProductListDao() {
	}
	
	static private ProductListDao dao = new ProductListDao();
	
	public static ProductListDao getInstance() {
		return dao;
	}
	
	
	//2.저장
	int insertProductList(Connection conn, ProductList pList) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "insert into ProductList values (ProductList_icode_seq.nextval, ?, ?, ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, ProductList.getName());
		
		
		
		
		
		
		
		return 0;
		
	}
	
	
=======
import java.sql.SQLException;


public class ProductListDao {
	
	// 3. ProductList 테이블의 데이터 수정 메소드
		// 반영된 행의 개수 반환
		// 사용자로부터 데이터를 받아서 처리 -> ProductList 객체
		int editProductList(Connection conn, ProductList ProductList) {
			
			int result = 0;

			// 전달받은 ProductList 객체의 데이터로 ProductList 테이블에 저장 -> 결과 값을 반환
			PreparedStatement pstmt = null;

			try {
				String sql = "update ProductList set name=?, price=?, itemQty=?, where itemcode=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, ProductList.getName());
				pstmt.setInt(2, ProductList.getPrice());
				pstmt.setInt (3, ProductList.getDrinkStock());
				
				result = pstmt.executeUpdate();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			return result;
		}



>>>>>>> a30ae92877f77540e3cc419a0a96faa2b0d9bba6
}
