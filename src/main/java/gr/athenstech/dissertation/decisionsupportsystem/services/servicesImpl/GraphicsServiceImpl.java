package gr.athenstech.dissertation.decisionsupportsystem.services.servicesImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.athenstech.dissertation.decisionsupportsystem.services.GraphicsService;
import gr.athenstech.dissertation.decisionsupportsystem.model.Graphic;
import gr.athenstech.dissertation.decisionsupportsystem.repositories.GraphicsRepository;

@Service
public class GraphicsServiceImpl implements GraphicsService{
	
    private static final Logger logger = LoggerFactory.getLogger(GraphicsServiceImpl.class);

	 @Autowired
	 private GraphicsRepository graphicsRepository; 
	
	 @Override
	 @Transactional
	 public List<Graphic> getAllGraphics() {
		 return graphicsRepository.findAll();
	 } 
	 
	 @Override
	 @Transactional
	 public void saveGraphics(List<Graphic> graphics) {	
		 for (Graphic graphic: graphics) {
			 graphicsRepository.save(graphic);
		 }			 
	 }
	 
	 @Override
	 @Transactional
	 public void saveGraphic(Graphic graphic) {	
			 graphicsRepository.save(graphic);
	 }
	 
	 @Override
	 @Transactional
	 public void deleteGraphics() {
		 for (Graphic graphic: graphicsRepository.findAll()) {
			 graphicsRepository.delete(graphic);
		 }		 
	 }
	 
	 @Override
	 @Transactional
	 public void deleteGraphic(Long id) {
		 if (graphicsRepository.existsById(id)) {			 
			 graphicsRepository.deleteById(id);
		 }		 	 
	 }
	 

}
