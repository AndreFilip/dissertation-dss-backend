package gr.athenstech.dissertation.decisionsupportsystem.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gr.athenstech.dissertation.decisionsupportsystem.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findOptionalByUsername(String username);
    Boolean existsByUsername(String username);
}
