package com.basicJWTProjectBack.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basicJWTProjectBack.Models.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
