package Service;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Model.Meal;
import Model.Movie;

public class Administrator {
	public void admin(){
		int choice=0;
		Scanner sc = new Scanner(System.in);
		Session ss = ConnectionClass.getcon();
		Transaction tx ;
		do {
			System.out.println("1. Add Movie\n2. Add Snacks\n3. Update Movie\n4. Update Snacks\n5. Delete Movie\n6. Delete Snacks\n7. Exit\n\nEnter your Choice : ");
			choice = sc.nextInt();
			switch(choice) {
			case 1: 
				tx = ss.beginTransaction();
				Movie m = new Movie();
				System.out.println("Enter Id : ");
				m.setId(sc.nextInt());
				System.out.println("Enter name : ");
				m.setName(sc.next());
				System.out.println("Enter Price : ");
				m.setPrice(sc.nextInt());
				System.out.println("Enter Rating : ");
				m.setRating(sc.nextFloat());
				System.out.println("Enter Gener : ");
				m.setGener(sc.next());
				ss.persist(m);
				tx.commit();
				break;
				
			case 2:
				tx = ss.beginTransaction();
				Meal me = new Meal();
				System.out.println("Enter Id : ");
				me.setId(sc.nextInt());
				System.out.println("Enter name : ");
				sc.nextLine();
				me.setName(sc.nextLine());
				System.out.println("Enter Description : ");
				me.setDescp(sc.nextLine());
				System.out.println("Enter Price : ");
				me.setPrice(sc.nextInt());
				ss.persist(me);
				tx.commit();
				break;
				
			case 3:
				tx = ss.beginTransaction();
				System.out.println("Enter id that movie can Update you : ");
				Movie mo = ss.get(Movie.class, sc.nextInt());
				System.out.println("Enter name : ");
				mo.setName(sc.next());
				System.out.println("Enter Price : ");
				mo.setPrice(sc.nextInt());
				System.out.println("Enter Rating : ");
				mo.setRating(sc.nextFloat());
				System.out.println("Enter Gener : ");
				mo.setGener(sc.next());
				ss.update(mo);
				tx.commit();
				System.out.println("Succesfully Done.....");
				break;
				
			case 4:
				tx = ss.beginTransaction();	
				System.out.println("Enter id that Snack can Update you : ");
				Meal meal = ss.get(Meal.class,sc.nextInt());
				System.out.println("Enter name : ");
				sc.nextLine();
				meal.setName(sc.nextLine());
				System.out.println("Enter Description : ");
				meal.setDescp(sc.nextLine());
				System.out.println("Enter Price : ");
				meal.setPrice(sc.nextInt());
				ss.update(meal);
				tx.commit();
				System.out.println("Succesfully Done.....");
				break;
				
			case 5:
				tx = ss.beginTransaction();	
				Query<Movie> q =ss.createQuery("delete from Movie where id=?1");
				System.out.println("Enter id to delete Movie : ");
				q.setParameter(1, sc.nextInt());
				q.executeUpdate();
				System.out.println("Succesfully Done.....");
				tx.commit();
				break;
				
			case 6:
				tx = ss.beginTransaction();	
				Query<Movie> q1 =ss.createQuery("delete from Meal where id=?1");
				System.out.println("Enter id to delete Meal : ");
				q1.setParameter(1, sc.nextInt());
				q1.executeUpdate();
				System.out.println("Succesfully Done.....");
				tx.commit();
				break;
				
				
			}
		}while(choice!=7);
		
	}
}
