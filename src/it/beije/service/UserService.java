package it.beije.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.model.Users;
import it.beije.repository.UsersRepository;
@Service
public class UserService {
	@Autowired
	private UsersRepository usersRepository;

	public List<Users> findAll(){

		return   usersRepository.findAll();
	}


	public Users findByID(Integer id) {
		return usersRepository.findById(id).get();
	}
	public Users findByEmail(String email) {
		return usersRepository.findByEmail(email);
	}
	public boolean addUser(String email, String name, String surname, String password)
	{
		if(findByEmail(email)==null) {
			Users c= new Users();
			c.setEmail(email);
			c.setFirstNname(name);
			c.setSecondName(surname);
			c.setPassword(password);
			c=usersRepository.save(c);
			return c.getId()!=0;
		} return false;

	}
	public boolean esiste(Integer id)
	{
		return usersRepository.existsById(id);
	}
	public Users save(Users u)
	{
		return usersRepository.save(u);

	}

}
