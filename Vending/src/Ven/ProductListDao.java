package Ven;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
	
	
}
