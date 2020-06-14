package com.basicJWTProjectBack;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.basicJWTProjectBack.Models.ERole;
import com.basicJWTProjectBack.Models.Role;
import com.basicJWTProjectBack.Models.User;
import com.basicJWTProjectBack.Repositories.RoleRepo;
import com.basicJWTProjectBack.Repositories.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

@SpringBootApplication
public class BasicJWTProjectBack implements CommandLineRunner{

	@Autowired
	UserRepo userRepo;
	@Autowired
	RoleRepo roleRepo;
	public static void main(String[] args) {
		SpringApplication.run(BasicJWTProjectBack.class, args);
	}
	
	@Bean
	public ObjectMapper serializingObjectMapper() {
	    ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	    objectMapper.registerModule(new JavaTimeModule());
	    return objectMapper;
	}
	
	@Override
	public void run(String... args) throws Exception {

		Role role = new Role(ERole.ROLE_ADMIN);
		Set<Role> setRole = new HashSet<Role>();
		setRole.add(role);
		roleRepo.save(role);
		User firstAdmin = new User();
		firstAdmin.setPassword("123");
		firstAdmin.setUsername("aa@gmail.com");
		firstAdmin.setFirstName("root");
		firstAdmin.setLastName("root");
		firstAdmin.setRoles(setRole);
		firstAdmin.setAddress("69 rue de la République");
		firstAdmin.setBirtheDate( LocalDate.of(1993, 11, 01));
		firstAdmin.setCity("Montreuil");
		firstAdmin.setIsFamele(false);
		firstAdmin.setPhone("0618803007");
		firstAdmin.setZipCode("93100");
		userRepo.save(firstAdmin);
		
		Role role2 = new Role(ERole.ROLE_USER);
		Set<Role> setRole2 = new HashSet<Role>();
		setRole2.add(role2);
		roleRepo.save(role2);
		
		User firstUser = new User();
		firstUser.setPassword("123");
		firstUser.setUsername("user@user.com");
		firstUser.setFirstName("user");
		firstUser.setLastName("user");
		firstUser.setRoles(setRole2);
		userRepo.save(firstUser);
	}

}
