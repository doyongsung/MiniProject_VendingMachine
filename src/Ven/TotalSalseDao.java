package Ven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TotalSalseDao {

	private TotalSalseDao() {
	}

	static private TotalSalseDao dao = new TotalSalseDao();

	public static TotalSalseDao getInstance() {
		return dao;
	}

	// 음료 판매량 조회
	ArrayList<TotalList> gettTotalList(Connection conn) {

		// 데이터 베이스의 BuyInfo테이블 이용 select 결과를 -> list 저장
		ArrayList<TotalList> list = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			String sql = "select b.itemcode, p.name, sum(b.buyqty), sum(b.totalprice) total "
					+ "from PRODUCTINFO p , BUYINFO b " + "where b.itemcode = p.itemcode "
					+ "group by b.itemcode, p.name order by total desc";

			// 결과 받아오기
			rs = stmt.executeQuery(sql);
			list = new ArrayList<>();

			// 데이터를 TotalList 객체로 생성 -> list에 저장
			while (rs.next()) {
				TotalList TL = new TotalList(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				list.add(TL);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
}