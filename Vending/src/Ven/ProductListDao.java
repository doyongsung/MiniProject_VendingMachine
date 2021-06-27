
package Ven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductListDao {

	private ProductListDao() {}
	static private ProductListDao dao = new ProductListDao();
	public static ProductListDao getInstance() {
		return dao;
	}



	//음료리스트
	ArrayList<ProductList> getProductList(Connection conn) {

		ArrayList<ProductList> list = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			String sql = "select * from PRODUCTINFO order by itemcode";
			rs = stmt.executeQuery(sql);
			list = new ArrayList<>();
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

	
	
	//음료정보수정
	int updateProductList(Connection conn, ProductList ProductList) {

		int result = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = "update PRODUCTINFO set name=?, price= ?, itemQty=? where itemcode=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ProductList.getName());
			pstmt.setInt(2, ProductList.getPrice());
			pstmt.setInt(3, ProductList.getItemQty());
			pstmt.setInt(4, ProductList.getItemcode());

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

	
	
	//새로운 음료 추가
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

	
	
	//발주> 재고 추가
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

	
	
	//음료 삭제
	int deleteProduct(Connection conn, int itemcode) {
		int result = 0;
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

	
	
	//구매> 음료이름, 가격 출력
	ArrayList<ProductList> getBuylist(Connection conn, ProductList PList) {
		ArrayList<ProductList> list = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			String sql = "SELECT name,price FROM productinfo where itemcode = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, PList.getItemcode());

			rs = ps.executeQuery();
			list = new ArrayList<>();

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
	
	
	
	//구매> 재고수량 수정
	int subtractProductList(Connection conn, ProductList ProductList) {
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

	
	
	//구매> 구매내역 저장
	int insertBuyInfo(Connection conn, BuyList bList) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "INSERT INTO BUYINFO VALUES (BINFO_BCODE_SEQ.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bList.getBuyQty());
			pstmt.setInt(2, bList.getTotalPrice());
			pstmt.setInt(3, bList.getItemCode());
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

	
	
	//구매> 재고수량 체크
	int getItemQty(Connection conn, ProductList pList) {

		int itemQty = 0;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			String sql = "SELECT itemQty FROM productinfo where itemcode = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pList.getItemcode());
			rs = ps.executeQuery();

			if (rs.next()) {
				itemQty = rs.getInt(1);
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
		return itemQty;
	}

}
