package Service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.config.HibernateUtil;

public class ConnectionClass {
	
	public final class login {

	}

	public static Session getcon(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session ss = sf.openSession();
		return ss;
	}
}
