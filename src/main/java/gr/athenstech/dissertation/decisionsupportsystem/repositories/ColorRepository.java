package gr.athenstech.dissertation.decisionsupportsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gr.athenstech.dissertation.decisionsupportsystem.model.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

}
