package gr.athenstech.dissertation.decisionsupportsystem.services.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.athenstech.dissertation.decisionsupportsystem.services.GraphicsService;
import gr.athenstech.dissertation.decisionsupportsystem.configurations.security.SecurityUtils;
import gr.athenstech.dissertation.decisionsupportsystem.model.Graphic;
import gr.athenstech.dissertation.decisionsupportsystem.model.User;
import gr.athenstech.dissertation.decisionsupportsystem.repositories.GraphicsRepository;

@Service
public class GraphicsServiceImpl implements GraphicsService{
	
    private static final Logger logger = LoggerFactory.getLogger(GraphicsServiceImpl.class);

	 @Autowired
	 private GraphicsRepository graphicsRepository; 
	
	 @Autowired
	 private SecurityUtils securityUtils; 
	 
	 @Override
	 @Transactional
	 public List<Graphic> getAllGraphics() {
		 User user = securityUtils.getCurrentUser();
		 logger.info("User, getAllGraphics(): {}", user.toString());
		 return graphicsRepository.findByUser(user);
	 } 
	 
	 @Override
	 @Transactional
	 public Optional<Graphic> getGraphic(Long id) {
		 return graphicsRepository.findById(id);
	 }

	 
	 @Override
	 @Transactional
	 public void saveGraphics(List<Graphic> graphics) {	
		 User user = securityUtils.getCurrentUser();		 
		 logger.info("User, saveGraphics(): {}", user.toString());
		 for (Graphic graphic: graphics) {
			 graphic.setUser(user);
			 graphicsRepository.save(graphic);
		 }			 
	 }
	 
	 @Override
	 @Transactional
	 public Graphic saveGraphic(Graphic graphic) {	
		 User user = securityUtils.getCurrentUser();		 
		 logger.info("User, saveGraphic(): {}", user.toString());
		 graphic.setUser(user);		 
	     return graphicsRepository.save(graphic);
	 }
	 
	 @Override
	 @Transactional
	 public void deleteGraphics() {
		 User user = securityUtils.getCurrentUser();		 
		 logger.info("User, deleteGraphics(): {}", user.toString());
		 for (Graphic graphic: graphicsRepository.findByUser(user)) {
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

	@Override
	public Boolean mapExists() {
		List<Graphic> graphics = new ArrayList<>();		
		User user = securityUtils.getCurrentUser();		 
		logger.info("User, mapExists(): {}", user.toString());
		graphics = graphicsRepository.findByUser(user);		
		return Boolean.valueOf(!graphics.isEmpty());
	}
	 

}
