package gr.athenstech.dissertation.decisionsupportsystem.repositories;

import gr.athenstech.dissertation.decisionsupportsystem.model.Graphic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphicsRepository extends JpaRepository<Graphic,  Long> {

}