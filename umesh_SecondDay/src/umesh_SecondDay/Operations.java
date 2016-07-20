package umesh_SecondDay;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Operations {
	Connection con=ConnectionManagement.getCon();
	 void insert(Person p) {
		// TODO Auto-generated method stub
		//getting connection
		
		try {
			PreparedStatement pstmt=con.prepareStatement("insert into person(firstName,lastName) values('"+p.getFirstName()+"','"+p.getLastName()+"')");
			
			pstmt.executeUpdate();
			System.out.println("Data Inserted");
			
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	void upadte(Person p,Person p1) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pstmt=con.prepareStatement("update person set firstName='"+p.getFirstName()+"',lastName='"+p.getLastName()+"' where firstName='"+p1.getFirstName()+"' AND lastName='"+p1.getLastName()+"'");
	
			pstmt.executeUpdate();
			System.out.println("Values Updated");
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void delete(Person p) {
		// TODO Auto-generated method stub
			try {
				PreparedStatement pstmt=con.prepareStatement("delete from person where firstName='"+p.getFirstName()+"' AND lastName='"+p.getLastName()+"'");
				pstmt.executeUpdate();
				System.out.println("Deleted Successfully..!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	ArrayList displayRecord() {
		// TODO Auto-generated method stub
		ArrayList<Person> li=new ArrayList<Person>();
		try {
			PreparedStatement pstmt=con.prepareStatement("select firstName,lastName from person");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				Person p=new Person();
				p.setFirstName(rs.getString("firstName"));
				p.setLastName(rs.getString("lastName"));
				li.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return li;
	}
}
