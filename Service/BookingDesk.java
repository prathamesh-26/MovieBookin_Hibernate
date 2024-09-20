package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.config.HibernateUtil;

import Model.Details;
import Model.Login_Entity;
import Model.Meal;
import Model.Movie;


public class BookingDesk {
	static Scanner sc = new Scanner(System.in);
		Session ss = ConnectionClass.getcon();
		Transaction tx = ss.beginTransaction();
	public void bookTicket() {
		
		System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_ Today's Movie -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_\n");
		Query<Movie> q = ss.createQuery("from Movie");
		List<Movie> m = q.getResultList();
		boolean flag = false;
		for(Movie m1 : m) {
			System.out.println(m1);
		}		
		Details d = new Details();
		System.out.println("Enter Id to Book tickets : ");
		int id = sc.nextInt();
		for(Movie m1 : m) {
		if(id==m1.getId()) {
			d.setMovieId(id);
			System.out.println("Enter how many Tickets you want book : ");
			d.setSeats(sc.nextInt());
			System.out.println("\nMovie Ticket Booked Succesfully.....");
			ss.save(d);
			tx.commit();
			flag=true;
		} 
		}
		if(flag==false){
			System.out.println("Movie not Found.....");
		}
		
		
			}

	public void addMeal() {
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_ Today's Snacks -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_\n");
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session ss = sf.openSession();
        Transaction tx = null;
        
            Query<Meal> query = ss.createQuery("from Meal");
            List<Meal> meals = query.list();
            for (Meal meal : meals) {
                System.out.println(meal);
            }
        
        while (true) {
        	 
        		 Boolean flag = false;
            System.out.println("Enter Id to book Snacks (0 to Exit): ");
            int snackId = sc.nextInt();
            if (snackId == 0) {
                break;
            }
            for (Meal meal : meals) {  
            if(snackId==meal.getId()) {
                tx = ss.beginTransaction();
                Details details = new Details();
                details.setMealId(snackId);
                ss.save(details);
                tx.commit();
                flag = true;
                System.out.println("Snack booked successfully.");
        } 
        	 }
            if(flag==false) {
            	System.out.println("Snack not found.....");
            }
        }
    }

	
	public void printDetails(){
	    System.out.println("\n-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_ Ticket Bill -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_\n");
	    int total=0;
	    Query<Movie> q1 = ss.createQuery("from Movie");
		List<Movie> m = q1.getResultList();
		Query<Details> q2 = ss.createQuery("from Details");
		List<Details> d = q2.getResultList();
		Query<Meal> q3 = ss.createQuery("from Meal");
		List<Meal> meal = q3.getResultList();
		
		for(Details d1 : d) {
				for(Movie m1 : m) {
					if(m1.getId()== d1.getMovieId()) {
						total =d1.getSeats()*m1.getPrice();
					System.out.println("Movie : "+m1.getName()+"\tPrice : "+m1.getPrice());
				}
			}
				for(Meal m1 : meal) {
					if(m1.getId()== d1.getMealId()) {
					total +=m1.getPrice();
					System.out.println("Meal : "+m1.getName()+"\tPrice : "+m1.getPrice());
				}
			}
				
		}
		System.out.println("Total Bill = "+total);
		}

	public void logOut() {
		System.out.println(
				"-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_ Logged out succefully -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
		tx = ss.beginTransaction();
		Query<Login_Entity> q = ss.createQuery("delete from Details");
		int i = q.executeUpdate();
		tx.commit();
		
	}

	
}
