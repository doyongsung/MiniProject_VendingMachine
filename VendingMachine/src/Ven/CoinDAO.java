
package Ven;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CoinDAO {
	private CoinDAO() {

	}

	static private CoinDAO dao = new CoinDAO();

	public static CoinDAO getInstance() {
		return dao;
	}

	ArrayList<CoinList> getCoinLists(Connection conn) {

		ArrayList<CoinList> list = null;

		Statement stmt = null;
		Statement stmt2 = null;
		ResultSet rs = null;
		ResultSet re2 = null;

		try {
			stmt = conn.createStatement();
			stmt2 = conn.createStatement();

			//
			String sql = "select mkey,mname,mcount ,(mvalue*mcount)\r\n" + "FROM money";

			// 음료 총 판매량
			String sql2 = "SELECT sum(money.mvalue * money.mcount) FROM MONEY";

			rs = stmt.executeQuery(sql);
			re2 = stmt2.executeQuery(sql2);

			list = new ArrayList<>();

			while (re2.next()) {
				System.out.printf("————————————————— 총 잔액  %d —————————————————", re2.getInt(1));
				System.out.println();

			}

			while (rs.next()) {
				CoinList CL = new CoinList(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				list.add(CL);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

		}

		return list;

	}

	
	// i번째 배열과, 나눈 값을 coinlist 매개변수로 받는다.
	int UpCoin(Connection conn, CoinList coinlist) {

		int result = 0;

		PreparedStatement pstmt = null;

		try {
			
			// i번째 배열과 동일한 mvalue(= 동전의 가치)와 일치하는 칼럼에 있는 
			// mcount(= 동전의 개수) 에 나눈 값을 더한다.
			String sql = "update MONEY set mcount=mcount+? where mvalue=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, coinlist.getMoneyCount());
			pstmt.setInt(2, coinlist.getMoenyKey()); //

			// update한 결과물 반환
			result = pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}

		return result;
	}

	int editCoin(Connection conn, CoinList coinlist) {

		int result = 0;

		PreparedStatement pstmt = null;

		try {
			String sql = "update MONEY set mcount=? where mkey=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, coinlist.getMoneyCount());
			pstmt.setInt(2, coinlist.getMoenyKey());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}

		return result;
	}
}