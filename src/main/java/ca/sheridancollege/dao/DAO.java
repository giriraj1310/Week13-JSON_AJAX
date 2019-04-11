package ca.sheridancollege.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.fluttercode.datafactory.impl.DataFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ca.sheridancollege.beans.Person;
import lombok.*;

@Data
public class DAO {

	SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	public void addPerson(Person person) {
		// Open a new session and begin a transaction.
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(person);

		// Commit the transaction and close the session.
		session.getTransaction().commit();
		session.close();
	}

	public List<Person> getPerson() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("from Person");
		List<Person> personList = (List<Person>) query.getResultList();

		session.getTransaction().commit();
		session.close();

		return personList;
	}
	
	public Person queryByID(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("from Person where id=:id");
		query.setParameter("id", id);
		List<Person> personList = (List<Person>) query.getResultList();

		session.getTransaction().commit();
		session.close();

		Person person = new Person();
		if (!personList.isEmpty()) {
			person = personList.get(0);
		}
		return person;
	}

	public void generateRandom() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		DataFactory df = new DataFactory();

		for (int i = 1; i < 10; i++) {
			Person person = new Person(df.getName(), df.getNumberText(10), df.getAddress(), df.getEmailAddress());
			session.save(person);

		}

		// ending of the method
		session.getTransaction().commit();
		session.close();

	}

	public void deletePerson(int index) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Person person = (Person) session.get(Person.class, index);
		session.delete(person);

		session.getTransaction().commit();
		session.close();
	}

	public List<Person> queryByName(String name) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("from Person where name=:name");
		query.setParameter("name", name);
		List<Person> persons = (List<Person>) query.getResultList();

		session.getTransaction().commit();
		session.close();

		return persons;
	}
	
	public List<Person> queryByNumber(String phone) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("from Person where phone=:phone");
		query.setParameter("phone", phone);
		List<Person> persons = (List<Person>) query.getResultList();

		session.getTransaction().commit();
		session.close();

		return persons;
	}
	
	public List<Person> queryByAddress(String address) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("from Person where address=:address");
		query.setParameter("address", address);
		List<Person> persons = (List<Person>) query.getResultList();

		session.getTransaction().commit();
		session.close();

		return persons;
	}
	
	public List<Person> queryByEmail(String email) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("from Person where email=:email");
		query.setParameter("email", email);
		List<Person> persons = (List<Person>) query.getResultList();

		session.getTransaction().commit();
		session.close();

		return persons;
	}

}
