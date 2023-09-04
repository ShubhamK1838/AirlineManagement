package code;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class Database {
	

	public static Connection getConnection() throws Exception {

		String url;
		url = "jdbc:mysql://localhost:3306/codeclause";

		Connection con = DriverManager.getConnection(url, "root", "jadoo");

		return con;
	}
	
	public static int cancelTicket(String id) {
		int result=0;
		try {
			Connection con = getConnection();
			PreparedStatement prst = con.prepareStatement("delete from tickets where psid=? ");
			prst.setString(1, id);

			result=prst.executeUpdate();
			con.close();

		} catch (Exception e) {
		}
		return result;
		
	}
	

	public static void DataForTickets(DefaultTableModel model) {
		try {

			String url;
			url = "jdbc:mysql://localhost:3306/codeclause";

			Connection con = DriverManager.getConnection(url, "root", "jadoo");

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("select * from tickets" );
			while (rs.next()) {
				String row[] = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) };
				model.addRow(row);
			}

			con.close();

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}
	
	public static void  insertTicketDetail(String ele[])
	{
		try
		{
			Connection con =getConnection();; 
			PreparedStatement  prst ;
			prst=con.prepareStatement("insert into tickets values(?,?,?,?,?,?,?)");
			int cnt =1; 
			for(String st: ele) {
				prst.setString(cnt++, st);
			}
			
			prst.executeUpdate(); 
			
			con.close(); 
		}
		catch(Exception  e)
		{
			System.out.println(e); 
		}
		
	}

	
	public static Object[] getFlightCode() {
		Object[] ele = null;
		try {

			Connection con = getConnection();

			ArrayList list = new ArrayList();

			Statement st = con.createStatement();
			ResultSet result = st.executeQuery("select flight_code from flights");

			while (result.next()) {
				list.add(result.getString(1));

			}
			ele = list.toArray();

		} catch (Exception e) {
			System.out.println(e);
		}
		return ele;
	}

	public static void delete(String flightcode) {
		try {
			Connection con = getConnection();
			PreparedStatement prst = con.prepareStatement("delete from flights where flight_code=? ");
			prst.setString(1, flightcode);
			prst.executeUpdate();

			con.close();

		} catch (Exception e) {
//			System.out.println(e);
		}
	}

	public static void deletePas(String pnumber) {
		try {
			Connection con = getConnection();
			PreparedStatement prst = con.prepareStatement("delete from passanger where pnumber=? ");
			prst.setString(1, pnumber);
			prst.executeUpdate();

			con.close();

		} catch (Exception e) {
//			System.out.println(e);
		}
	}

	public static void dataForFlightUpdate(String s1, String s2, String s3, String s4, String s5) {
		Connection con = null;
		try {
			String url;
			url = "jdbc:mysql://localhost:3306/codeclause";

			con = DriverManager.getConnection(url, "root", "jadoo");
			Statement st = con.createStatement();
			int n = st.executeUpdate("update flights set source='" + s2 + "',destination='" + s3 + "',take_of='" + s4
					+ "',no_of_seats='" + s5 + "'  where flight_code='" + s1 + "'");

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void dataForpasUpdate(String s1, String s2, String s3, String s4, String s5) {
		Connection con = null;
		try {
			String url;
			url = "jdbc:mysql://localhost:3306/codeclause";

			con = DriverManager.getConnection(url, "root", "jadoo");
			Statement st = con.createStatement();
			int n = st.executeUpdate("update  passanger set pname='" + s1 + "',gender='" + s2 + "',nationility='" + s3
					+ "',phone='" + s5 + "'  where pnumber='" + s4 + "'");

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void DataForFlight(DefaultTableModel model, String table) {
		try {

			String url;
			url = "jdbc:mysql://localhost:3306/codeclause";

			Connection con = DriverManager.getConnection(url, "root", "jadoo");

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("select * from " + table);
			while (rs.next()) {
				String row[] = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5) };
				model.addRow(row);
			}

			con.close();

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	public static void insert(String table, String flightcode, String source, String dest, String takeof,
			String noseat) {
		Connection con;
		try {
			String url;
			url = "jdbc:mysql://localhost:3306/codeclause";

			con = DriverManager.getConnection(url, "root", "jadoo");

			String query = "insert into " + table + " values(?,?,?,?,?)";
			PreparedStatement prst = con.prepareStatement(query);
//			prst.setString(1, table);
			prst.setString(1, flightcode);
			prst.setString(2, source);
			prst.setString(3, dest);
			prst.setString(4, takeof);
			prst.setString(5, noseat);

			prst.executeUpdate();

			con.close();
		} catch (Exception e) {
			{

			}

		}
	}

	
	public static boolean authenticate(String user, String pass) throws SQLException {
		Connection con = null;
		boolean say=false;
		try {
			String url;
			url = "jdbc:mysql://localhost:3306/codeclause";

			con = DriverManager.getConnection(url, "root", "jadoo");

			PreparedStatement prst = con.prepareStatement("select auth from admins where user=? and pass=?");
			prst.setString(1, user);
			prst.setString(2, pass);
			ResultSet result = prst.executeQuery();
			result.next();
			if (result.getInt(1) == 1)
				return true;
			con.close();

		} catch (Exception e) {

		}

		return say;
	}

}
