package gr.athenstech.dissertation.decisionsupportsystem.services;

import java.util.List;
import java.util.Optional;

import gr.athenstech.dissertation.decisionsupportsystem.model.Graphic;


public interface GraphicsService {
	 public List<Graphic> getAllGraphics();
	 
	 public Optional<Graphic> getGraphic(Long id);
	 
	 public Boolean mapExists();

	 public void saveGraphics(List<Graphic> graphics);
	 
	 public Graphic saveGraphic(Graphic graphic);
	 
	 public void deleteGraphics();
	 
	 public void deleteGraphic(Long id);
}
