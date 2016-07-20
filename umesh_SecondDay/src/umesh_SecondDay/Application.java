package umesh_SecondDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Menu. 1.Insert\t\t 2.update\t\t 3.delete \t\t4.display \t\t5.Exit");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int choice=0;
		do{
		try {
			System.out.println("Enter Choice:");
			choice=Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DAOPerson daoObject=new DAOPerson();
		daoObject.performOperation(choice);
		}while(choice!=5);
	}

}
