package com.basicJWTProjectBack.Controlls;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.basicJWTProjectBack.Models.ERole;
import com.basicJWTProjectBack.Models.Role;
import com.basicJWTProjectBack.Models.User;
import com.basicJWTProjectBack.Repositories.RoleRepo;
import com.basicJWTProjectBack.Repositories.UserRepo;



@RestController
@CrossOrigin(origins = "*")
public class UserCont {

	@Autowired
	UserRepo userRepo;
	@Autowired
	RoleRepo roleRepo ;
    @GetMapping("/test/admin")
	@PreAuthorize("hasRole('ADMIN')")
    public String testAdmin()
    {
    	return "It works";
    }
    

    @GetMapping("/test/all")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String testAll()
    {
    	return "It works";
    }
    
    @GetMapping("/test/user")
	@PreAuthorize("hasRole('USER')")
    public String testUser()
    {
    	return "It works";
    }

    
    @GetMapping("/user/get/username/{username}")  
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public User getUserByUsername(@PathVariable("username") String username)
    {
    	Optional<User> u = userRepo.findByUsername(username);
    	if(u.isPresent())
    	{
    		return u.get();
    	}
    	return null;
    }
    
    @GetMapping("/user/get/all")  
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<User> getAllUsers()
    {
    	List<User> u = userRepo.findAll();

    		return u;

    }
    
    @PostMapping("/user/add")
    public ResponseEntity<Object> addUser(/*@Valid*/ @RequestBody User reqUser) throws SQLIntegrityConstraintViolationException
    {
    	//System.out.println(">>> Create new User: " + reqUser);
    	if (reqUser.getUsername().isEmpty())
			return  new ResponseEntity<>("Email Obligatory", HttpStatus.BAD_REQUEST);
    	Role role = new  Role(ERole.ROLE_USER);
		roleRepo.save(role);
		HashSet<Role> roles = new HashSet<Role>();
		roles.add(role);
		reqUser.setRoles(roles);
		userRepo.save(reqUser);
		return ResponseEntity.ok(reqUser);
    	
    }
	@DeleteMapping("/user/delete")
	@PreAuthorize("hasRole('ADMIN')")
	ResponseEntity<Object> deleteUser(@PathVariable int id) {
		Optional<User> u = userRepo.findById(id);
		if(u.isPresent()) {
			userRepo.deleteById(id);
			return ResponseEntity.ok(u);
		}
		return new ResponseEntity<>("user not found",HttpStatus.NOT_FOUND);
	}
	@PutMapping("/user/put/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	ResponseEntity<?> replaceIndividual(@RequestBody User newUser, @PathVariable String id)  {
		if(newUser instanceof User) {
			Optional<User> user = userRepo.findById(Integer.parseInt(id));
			if(!user.isPresent())
				  return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
			//newUser.setUserId(id);
			userRepo.save(newUser);
			return ResponseEntity.ok(newUser);
		}
	  return new ResponseEntity<>("Accept only User entity",HttpStatus.BAD_REQUEST);
	}
}
