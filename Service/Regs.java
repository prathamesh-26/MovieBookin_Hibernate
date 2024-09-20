package Service;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Login_Entity;

public class Regs {
	
	Scanner sc = new Scanner(System.in);
	public void accCreate() {
		System.out.println("Enter Name : ");
		String nm = sc.next();
		System.out.println("Enter Email : ");
		String mail= sc.next();
		String username = nm+"@JustTicket";
		String pass = Regs.passGen() ;
		Login_Entity u1 = new Login_Entity();
		u1.setName(nm);
		u1.setMail(mail);
		u1.setUsername(username);
		u1.setPassword(pass);
		System.out.println("Username : "+u1.getUsername());
		System.out.println("PassWord : "+u1.getPassword());
		Session ss = ConnectionClass.getcon();
		Transaction tx = ss.beginTransaction();
		ss.save(u1);
		tx.commit();
	}
	
	public static String passGen() {
		SecureRandom sr = new SecureRandom();
		String lc = "abcdefghijklmnopqrstuvwxyz";
		String uc = lc.toUpperCase();
		String sc = "!@#$%^&*+><";
		String nm = "0123456789";
		String ac = lc+uc+sc+nm;
		StringBuffer sf = new StringBuffer(8);
		for(int i=1;i<=8;i++) {
			sf.append(ac.charAt(sr.nextInt(ac.length())));
		}
		String pass = sf.toString();
		return pass;
		
	}
		
	}
	

