package project.goodreads.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import project.goodreads.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
