// CoinDao 
package Ven;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CoinDAO {
   private CoinDAO() {
      // TODO Auto-generated constructor stub
   }

   // 클래스 내부에 인스턴스
   static private CoinDAO dao = new CoinDAO();

   // 메소드를 통해 반환
   public static CoinDAO getInstance() {
      return dao;
   }

   // 전체 타입 검색기능
   // 반환타입 List<productList>
   // 매개변수 - Connection 객체 : Statement
   ArrayList<CoinList> getCoinLists(Connection conn) {

      ArrayList<CoinList> list = null;

      // 데이터 베이스의 ProductList 테이블 이용 select 결과물 -> list 에 저장
      Statement stmt = null;
      Statement stmt2 = null;
      ResultSet rs = null;
      ResultSet re2 = null;

      try {
         stmt = conn.createStatement();
         stmt2 = conn.createStatement();

         String sql = "select mkey,mname,mcount ,(mvalue*mcount)\r\n" + "FROM money";

         String sql2 = "SELECT sum(money.mvalue * money.mcount) FROM MONEY";

         // 결과 받아오기
         rs = stmt.executeQuery(sql);
         re2 = stmt2.executeQuery(sql2);

         list = new ArrayList<>();

         // 데이터를 ProductList 객체로 생성 -> list에 저장

         while (re2.next()) {
            System.out.printf("======== 총 잔액 : %d ======== \n", re2.getInt(1));
         }

         while (rs.next()) {
            CoinList CL = new CoinList(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
            list.add(CL);
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

   // 3. DEPT 테이블의 데이터 수정 메소드
   // 반영된 행의 개수 반환
   // 사용자로부터 데이터를 받아서 처리 -> Dept 객체
   int editDept(Connection conn, CoinList coinlist) {

      int result = 0;

      // 전달받은 Dept 객체의 데이터로 Dept 테이블에 저장 -> 결과 값을 반환
      PreparedStatement pstmt = null;

      try {
         String sql = "update MONEY set mcount=? where mkey=?";

         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, coinlist.getMoneyCount());
         pstmt.setInt(2, coinlist.getMoenyKey());
          
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

   int UpCoin(Connection conn, CoinList coinlist) {

      int result = 0;

      // 전달받은 Dept 객체의 데이터로 Dept 테이블에 저장 -> 결과 값을 반환
      PreparedStatement pstmt = null;

      try {
         String sql = "update MONEY set mcount=mcount+? where mvalue=?";
                                                        //1             = 100
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, coinlist.getMoneyCount()); // 2  1 
         pstmt.setInt(2, coinlist.getMoenyKey()); // 100 500 
         // key 100, count 1
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