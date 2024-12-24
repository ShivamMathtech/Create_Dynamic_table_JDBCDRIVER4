package com.driverType4.Oracle;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Test {
  public static void main(String[] args) throws Exception{
	Class.forName("oracle.jdbc.OracleDriver");
	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Shivam");
	//Create Statement Object
	Statement st = con.createStatement();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Table Name            :");
	String tName= br.readLine();
	int primaryKeyColumnCount=0;
	String primaryKeyColumns = "";
	String query = "create table "+tName+"(";
	while(true) {
		System.out.print("Column Name            :");
		String colName = br.readLine();
		System.out.print("Column Data Type       :");
		String colDataType = br.readLine();
		System.out.print("Column Size            :");
		int colSize =Integer.parseInt(br.readLine());
		System.out.print("Is Primary Key[yes/no] :");
		String primarykeyOption = br.readLine(); 
		if(primarykeyOption.equalsIgnoreCase("yes")) {
			if(primaryKeyColumnCount ==0) {
				primaryKeyColumns =primaryKeyColumns+colName;
				primaryKeyColumnCount=primaryKeyColumnCount+1;
			}else {
				primaryKeyColumns =primaryKeyColumns+","+colName;
			}
			
		}
		query =query+colName+ " "+ colDataType+ "(" +colSize + "),";
		System.out.print("One More Column [Yes/no] :");
		String columnOption= br.readLine();
		if(columnOption.equalsIgnoreCase("yes")) {
			continue;
		}else {
			query = query+"primary key(" +primaryKeyColumns +"))";
			break;
		}
	}
	st.executeUpdate(query);
	System.out.println("Table "+tName +"Created Successfully");
  br.close();
  st.close();
  con.close();
  }
}
