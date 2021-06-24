package Ven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductListDao {

	// 싱글톤 패턴
	private ProductListDao() {
	}

	// 클래스 내부에 인스턴스
	static private ProductListDao dao = new ProductListDao();

	// 메소드를 통해 반환
	public static ProductListDao getInstance() {
		return dao;
	}

	// 전체 타입 검색기능
	// 반환타입 List<productList>
	// 매개변수 - Connection 객체 : Statement
	ArrayList<ProductList> getProductList(Connection conn) {

		ArrayList<ProductList> list = null;

		// 데이터 베이스의 ProductList 테이블 이용 select 결과물 -> list 에 저장
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			String sql = "select * from PRODUCTINFO order by itemcode";

			// 결과 받아오기
			rs = stmt.executeQuery(sql);

			list = new ArrayList<>();

			// 데이터를 ProductList 객체로 생성 -> list에 저장

			while (rs.next()) {
				ProductList PL = new ProductList(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));

				list.add(PL);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
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

	
	int updateProductList(Connection conn, ProductList ProductList) {
        
        int result = 0;
        PreparedStatement pstmt = null;

        try {
           String sql = "update PRODUCTINFO set name=?, price= ?, itemQty=? where itemcode=?";
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, ProductList.getName());
           pstmt.setInt(2, ProductList.getPrice());
           pstmt.setInt(3, ProductList.getItemQty());
           pstmt.setInt (4, ProductList.getItemcode());
           
           
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
	
	// 2. 저장
	int insertProductList(Connection conn, ProductList pList) {

		int result = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = "INSERT INTO PRODUCTINFO VALUES (PINFO_ICODE_SEQ.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pList.getName());
			pstmt.setInt(2, pList.getPrice());
			pstmt.setInt(3, pList.getItemQty());

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

	// 3. 수정
	int editProductList(Connection conn, ProductList ProductList) {

		int result = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = "update PRODUCTINFO set itemQty=itemQty+? where itemcode=?";
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

	// 삭제 Manager
	int deleteProduct(Connection conn, int itemcode) {

		int result = 0;

		// 데이터 베이스 처리 sql
		PreparedStatement pstmt = null;
		String sql = "delete from PRODUCTINFO where ITEMCODE=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemcode);

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

	// 전체 타입 검색기능
	// 반환타입 List<productList>
	// 매개변수 - Connection 객체 : Statement
	ArrayList<ProductList> getBuylist(Connection conn, ProductList PList) {

		ArrayList<ProductList> list = null;

		// 데이터 베이스의 ProductList 테이블 이용 select 결과물 -> list 에 저장

		ResultSet rs = null;
		PreparedStatement ps = null;
		try {

			String sql = "SELECT name,price FROM productinfo where itemcode = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, PList.getItemcode());

			// 결과 받아오기

			rs = ps.executeQuery();
			list = new ArrayList<>();

			// 데이터를 ProductList 객체로 생성 -> list에 저장
			while (rs.next()) {
				ProductList PL = new ProductList(rs.getString(1), rs.getInt(2));

				list.add(PL);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return list;

	}
}
