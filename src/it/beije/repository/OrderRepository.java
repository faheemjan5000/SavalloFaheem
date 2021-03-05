package it.beije.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.beije.model.*;


public interface OrderRepository extends JpaRepository<Order, Integer> {

	public Order findByUserIDAndState(Integer userID, String state);
	public List<Order> findByUserID(Integer userID);
}
