package umesh_SecondDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DAOPerson {
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	Operations opObject=new Operations();
	Person p=new Person();
void performOperation(int choice) {
		// TODO Auto-generated method stub
		switch(choice){
		case 1:
			try {
				System.out.println("Enter First Name:");
				p.setFirstName(br.readLine());
				System.out.println("Enter Last Name:");
				p.setLastName(br.readLine());
				opObject.insert(p);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			Person p1=new Person();
			try {
				System.out.println("Enter First Name:");
				p.setFirstName(br.readLine());
				System.out.println("Enter Last Name:");
				p.setLastName(br.readLine());
				System.out.println("Enter First Name to be Updated:");
				p1.setFirstName(br.readLine());
				System.out.println("Enter Last Name to be Updated:");
				p1.setLastName(br.readLine());
				opObject.upadte(p, p1);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			try {
				System.out.println("Enter First Name of a person to be deleted:");
				p.setFirstName(br.readLine());
				System.out.println("Enter Last Name of a person to be deleted:");
				p.setLastName(br.readLine());
				opObject.delete(p);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 4:
			ArrayList <Person> li=null;
			li=opObject.displayRecord();
			for(int i=0;i<li.size();i++){
				Person obj=li.get(i);
				System.out.println("FirstName is:"+obj.getFirstName());
				System.out.println("LastName is:"+obj.getLastName());
			}
		}
	}
	
}
