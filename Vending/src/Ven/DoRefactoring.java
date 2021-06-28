package Ven;

import java.sql.*;
import java.util.Scanner;

public class DoRefactoring {
	public static void close(Statement statement){
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet resultSet){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection connecton){
        if (connecton != null){
            try {
            	connecton.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	public static int getInputInteger(String s) {
		System.out.print(s);
		Scanner sc = new Scanner(System.in);
		
		return Integer.parseInt(sc.nextLine());
	}
	
    public static String getInputString(String s){
    	System.out.print(s);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static int getInputInteger(){
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }
    public static String getInputString() {
    	 Scanner sc = new Scanner(System.in);
         return sc.nextLine();
    }




}
