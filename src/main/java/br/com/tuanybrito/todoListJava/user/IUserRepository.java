package br.com.tuanybrito.todoListJava.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByUserName(String username);
}
