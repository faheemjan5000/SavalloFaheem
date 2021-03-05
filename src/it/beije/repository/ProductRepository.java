package it.beije.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.beije.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {


//	
//	//SELECT * FROM rubrica WHERE surname LIKE '...%'
//	@Query(nativeQuery = true, value ="SELECT * FROM contatti WHERE surname LIKE :letters%")
//	public List<Contatto> searchByFirstLettersOfSurname(@Param("letters") String letters);

}
