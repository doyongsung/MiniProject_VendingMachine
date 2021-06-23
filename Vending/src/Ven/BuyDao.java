package Ven;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BuyDao {
	
	private BuyDao() {
	}
	
	static private BuyDao bDao = new BuyDao();
	public static BuyDao getInstance() {
		return bDao;
	}
	
	//출력
//    ArrayList<ProductList> getProductList(Connection conn){
//        
//        ArrayList<ProductList> list = null;
//       
//        // 데이터 베이스의 ProductList 테이블 이용 select 결과물 -> list 에 저장
//        Statement stmt = null;
//        ResultSet rs = null;
//        
//        try {
//         stmt = conn.createStatement();
//         String sql = "select * from PRODUCTINFO order by itemcode";
//         
//         //결과 받아오기
//         rs = stmt.executeQuery(sql);
//		
//		
//		
//	return 0;
//	}
	
	
	
	
	
	
	
	//구매이력 저장
	int insertBuyList(Connection conn, BuyList bList) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = 
					"INSERT INTO BUYINFO VALUES (BINFO_BCODE_SEQ.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bList.getBuyQty());	//구매한 음료 갯수
			pstmt.setInt(2, bList.getTotalPrice()); //구매 총액
			pstmt.setInt(3, bList.getItemCode()); //음료코드
			
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
	
	
	
	//재고수량 업데이트
	int editProductList(Connection conn, ProductList ProductList) {

		int result = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = "update PRODUCTINFO set itemQty=itemQty-? where itemcode=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ProductList.getItemQty());
			pstmt.setInt(2, ProductList.getItemcode());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
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
	
	
	
}