package edu.aydin.db.hibotou;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.aydin.db.hibotou.entity.Student;
import edu.aydin.db.hibotou.util.HibernateUtil;
import edu.aydin.db.hibotou.entity.Phone;

public class App 
{
    public static void main( String[] args )
    {
        Student student1 = new Student("Ceren", "Kartal", "cantekinkaya@mail.com", new Phone("555-1111122"));
        Student student2 = new Student("Burak", "Akgul", "email@mail.com", new Phone("555-1122334"));
        
        Transaction transaction = null;
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
       
        session.save(student1);
        session.save(student2);
        
        session.save(new Phone("555-1113333"));
        transaction.commit();
        
        session = HibernateUtil.getSessionFactory().openSession();
        /*List<Student> students = session.createQuery("from Student", Student.class).list();
        for(Student stud: students)
        	System.out.println(stud.getPhone().getNumber());
        */
        List<Phone> phones = session.createQuery("from Phone", Phone.class).list();
        for(Phone phone: phones)
        	System.out.println(phone.getNumber());
    }
}
