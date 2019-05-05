package gr.athenstech.dissertation.decisionsupportsystem.repositories;

import gr.athenstech.dissertation.decisionsupportsystem.model.Graphic;
import gr.athenstech.dissertation.decisionsupportsystem.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphicsRepository extends JpaRepository<Graphic,  Long> {
	
	List<Graphic> findByUser(User user);

}