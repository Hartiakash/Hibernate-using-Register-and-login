package checklist.dao;

import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import checklist.dto.User;

public class UserLogic {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("project1");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();

	public void addUser(User user) {
		transaction.begin();
		manager.persist(user);
		transaction.commit();
	}

	public User fetchByEmail(String email) {
		Query q = manager.createQuery("select a from User a");
		List<User> l = q.getResultList();
		for (User r : l) {
			if (r.getEmail().equals(email)) {
				return r;
			}
		}
		return null;
	}
}
