package gr.athenstech.dissertation.decisionsupportsystem.services;

import java.util.List;

import gr.athenstech.dissertation.decisionsupportsystem.model.Graphic;


public interface GraphicsService {
	 public List<Graphic> getAllGraphics();
	 
	 public void saveGraphics(List<Graphic> graphics);
	 
	 public void saveGraphic(Graphic graphic);
	 
	 public void deleteGraphics();
	 
	 public void deleteGraphic(Long id);
}
