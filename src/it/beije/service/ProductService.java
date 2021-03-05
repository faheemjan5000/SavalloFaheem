package it.beije.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.model.Product;
import it.beije.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public Product returnProduct(Integer id) {
	Optional<Product> c=	 productRepository.findById(id);
		return c.get();
	
	}
	public List<Product> returnProducts() {
			return productRepository.findAll();
		
		}
}
