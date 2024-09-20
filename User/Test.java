package User;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Model.Admin;
import Service.Administrator;
import Service.ConnectionClass;
import Service.Login;
import Service.Regs;


public class Test {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in); 
		Session ss = ConnectionClass.getcon();
		int choice=0;
		System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_ WELCOME IN BOOK MY SHOW-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_ ");
		do{
			System.out.println("1. Administrater\n2. User\n3. Exit\n\nEnter your choice : ");
			choice=sc.nextInt();
			switch(choice) {
			case 1:
				
				System.out.println("Enter Username : ");
				String user = sc.next();
				boolean flag=false;
				Query<Admin> q = ss.createQuery("from Admin");
				List<Admin> log = q.getResultList();
				for(Admin l : log) {
					if(user.equals(l.getUser())) {
						System.out.println("Enter Password : ");
						String pass = sc.next();
						flag=true;
						if(pass.equals(l.getPass())) {
							System.out.println("Login Succesfully....");
							Administrator a = new Administrator();
							a.admin();
						}else
							System.out.println("Incorrect Password.....");					
					}	
				}
				if(flag==false)
				System.out.println("Incorrect Username.....");
				
				break;
				
			case 2:	
				try {
					int choice1;
					do {
						System.out.println("1. Registration\n2. Login\n3. Exit\nEnter your Choice : ");
						choice1 = sc.nextInt();
						switch (choice1) {
						case 1:
							Regs r = new Regs();
							r.accCreate();
							break;
						case 2:
							Login l = new Login();
							l.login();
							break;
						}
					} while (choice1 != 3);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}while(choice!=3);
		
		sc.close();		
	}

}
