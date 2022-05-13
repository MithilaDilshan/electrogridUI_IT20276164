package com.BillManage;
import java.sql.*;

public class Bill {



private Connection connect() {
Connection con = null;
try {
Class.forName("com.mysql.cj.jdbc.Driver");



// Provide the correct details: DBServer/DBName, username, password
con = DriverManager.getConnection(
"jdbc:mysql://localhost:3306/pafelectricity?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
"root", "");
} catch (Exception e) {
e.printStackTrace();
}
return con;
}
	
public String insertBill(String bill_AccNo, String bill_Date, String bill_UnitA, String bill_Unitprice, String bill_Total)
		{
			String output = "";
			try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into billing1 (`biil_ID`,`bill_AccNo`,`bill_Date`,`bill_UnitA`,`bill_Unitprice`,`bill_Total`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, bill_AccNo);
			preparedStmt.setString(3, bill_Date);
			preparedStmt.setString(4, bill_UnitA);
			preparedStmt.setString(5, bill_Unitprice);
			preparedStmt.setString(6, bill_Total);


			// execute the statement
			preparedStmt.execute();
			con.close();
			String newBill = readBill();
			output = "{\"status\":\"success\", \"data\": \"" +newBill + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the billing1.\"}";
			System.err.println(e.getMessage());
		}
			
		return output;
	}

public String readBill()
{
		String output = "";
		try
		{
				Connection con = connect();
					if (con == null)
					{
						return "Error while connecting to the database for reading.";
					}

					//Prepare the HTML table to be displayed
					output = "<table border='3'>"
							+ "<tr><th>Bill AccNO</th>"
							+"<th>Bill Date</th>"
							+ "<th>Bill UnitAmount</th>"
							+ "<th>Bill Price</th>"
							+ "<th>Bill total</th>"
							+ "<th>Update</th><th>Remove</th></tr>";
					
					String query = "select * from billing1";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);

					// iterate through the rows in the result set
					while (rs.next())
					{
						String biil_ID  = Integer.toString(rs.getInt("biil_ID"));
						String bill_AccNo  = rs.getString("bill_AccNo");
						String bill_Date  = rs.getString("bill_Date");
						String bill_UnitA =rs.getString("bill_UnitA");
						String bill_Unitprice  = rs.getString("bill_Unitprice");
						String bill_Total = rs.getString("bill_Total");



						// Add a row into the html table
						output += "<tr><td><input id='hidBillIDUpdate'name='hidBillIDUpdate'type='hidden' value='" + biil_ID  + "'>"+ bill_AccNo  + "</td>";
						output += "<td>" + bill_Date + "</td>";
						output += "<td>" + bill_UnitA + "</td>";
						output += "<td>" + bill_Unitprice + "</td>";
						output += "<td>" + bill_Total + "</td>";


						// buttons
						output += "<td><input name='btnUpdate' type='button' value='Update' "
								+ "class='btnUpdate btn btn-secondary' data-billid='" + biil_ID + "'></td>"
								+"<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-billid='" + biil_ID + "'></td></tr>"; 
					}
					con.close();


					// Complete the html table
					output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the billing1.";
				System.err.println(e.getMessage());
			}
			return output;
		}
	public String deleteBill(String biil_ID)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for deleting.";
			}


			// create a prepared statement
			String query = "delete from billing1 where biil_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(biil_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			//output = "Deleted successfully";
			String newBill = readBill();
			output = "{\"status\":\"success\", \"data\": \"" +newBill + "\"}";
			}
		catch (Exception e)
		{
			//output = "Error while deleting the Bill.";
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the billing1.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

	//method to update bill details in DB
	public String updateBill(String biil_ID, String bill_AccNo, String bill_Date,String bill_UnitA,String bill_Unitprice,String bill_Total)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for updating.";
			}
				
			// create a prepared statement
			String query = "UPDATE billing1 SET bill_AccNo=?,bill_Date=?,bill_UnitA=?,bill_Unitprice=?,bill_Total=? WHERE biil_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, bill_AccNo);
			preparedStmt.setString(2, bill_Date);
			preparedStmt.setString(3, bill_UnitA);
			preparedStmt.setString(4, bill_Unitprice);
			preparedStmt.setString(5, bill_Total);
			preparedStmt.setInt(6, Integer.parseInt(biil_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Updated bill details successfully";
			String newBill = readBill();
			output = "{\"status\":\"success\", \"data\": \"" +newBill + "\"}"; }
		catch (Exception e)
		{
			//output = "Error while updating the Customer Details.";
			output = "{\"status\":\"error\", \"data\":\"Error while updating the billing1.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}


}

