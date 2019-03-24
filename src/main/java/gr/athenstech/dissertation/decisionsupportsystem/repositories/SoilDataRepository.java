package gr.athenstech.dissertation.decisionsupportsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gr.athenstech.dissertation.decisionsupportsystem.model.SoilData;

@Repository
public interface SoilDataRepository extends JpaRepository<SoilData, Integer> {
	
	SoilData findByTextureClass(String textureClass);
}
