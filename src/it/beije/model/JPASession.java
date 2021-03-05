//package it.beije.model;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//public class JPASession {
//
//private JPASession() {}
//
//private static EntityManagerFactory entityManagerFactory;
//
//public static EntityManager getEntityManager() {
//	if (entityManagerFactory == null || !entityManagerFactory.isOpen()) {
//		entityManagerFactory = Persistence.createEntityManagerFactory("web");
//	}
//	
//	return entityManagerFactory.createEntityManager();
//}
//
//
//}
