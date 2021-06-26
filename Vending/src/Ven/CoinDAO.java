
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

			String sql = "select mkey,mname,mcount ,(mvalue*mcount)\r\n" + "FROM money";

			String sql2 = "SELECT sum(money.mvalue * money.mcount) FROM MONEY";

			rs = stmt.executeQuery(sql);
			re2 = stmt2.executeQuery(sql2);

			list = new ArrayList<>();

			while (re2.next()) {
				System.out.printf("======== 총 잔액 : %d ======== \n", re2.getInt(1));
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

	int UpCoin(Connection conn, CoinList coinlist) {

		int result = 0;

		PreparedStatement pstmt = null;

		try {
			String sql = "update MONEY set mcount=mcount+? where mvalue=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, coinlist.getMoneyCount());
			pstmt.setInt(2, coinlist.getMoenyKey()); //

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