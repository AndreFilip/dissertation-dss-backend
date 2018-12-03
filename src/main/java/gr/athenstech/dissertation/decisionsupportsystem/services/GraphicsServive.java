package gr.athenstech.dissertation.decisionsupportsystem.services;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.athenstech.dissertation.decisionsupportsystem.model.Graphic;
import gr.athenstech.dissertation.decisionsupportsystem.repositories.GraphicsRepository;

@Service
public class GraphicsServive {
	
    private static final Logger log = LoggerFactory.getLogger(GraphicsServive.class);
//	 log.info("Log!!!: " + (pointRepository.findAll().isEmpty()));	 

	 @Autowired
	 private GraphicsRepository graphicsRepository; 
	
	 public List<Graphic> getAllGraphics() {
		 return graphicsRepository.findAll();
	 } 
	 
	 public List<Graphic> saveGraphics(List<Graphic> graphics) {
		 
//		 if (graphicsRepository.findAll().isEmpty()) {
//			 for (Graphic graphic: graphics) {
//				 graphicsRepository.save(graphic);
//			 }		 
//		   return graphicsRepository.findAll();
//		 } else {
//			 List<Graphic> graphicsDB = graphicsRepository.findAll();
//			 for (Graphic graphicFromFrontend: graphics) {
//				 for (Graphic graphicDB: graphicsDB) {
//					 if (graphicFromFrontend.equals(graphicDB)) {
////						 if (graphicFromFrontend.getType().equals("point")) {
////							 if (graphicFromFrontend.getColor().equals(graphicDB.getColor())) {
////								 graphicFromFrontend.getColor().setId(graphicDB.getColor().getId());
////								 graphicsRepository.save(graphicFromFrontend);
////							 } else {
////								 graphicsRepository.save(graphicFromFrontend);
////							 }
////						 }
////						
////						 if (graphicFromFrontend.getType().equals("polyline")) {
////							 if (graphicFromFrontend.getPaths().equals(graphicDB.getPaths())) {
////								 graphicFromFrontend.getColor().setId(graphicDB.getColor().getId());
////								 graphicsRepository.save(graphicFromFrontend);
////							 } else {
////								 graphicsRepository.save(graphicFromFrontend);
////							 }
////						 }
////						 
////						 if (graphicFromFrontend.getType().equals("polygon")) {
////							 if (graphicFromFrontend.getPaths().equals(graphicDB.getPaths())) {
////								 graphicFromFrontend.getColor().setId(graphicDB.getColor().getId());
////								 graphicsRepository.save(graphicFromFrontend);
////							 } else {
////								 graphicsRepository.save(graphicFromFrontend);
////							 }
////						 }
//						 graphicsRepository.save(graphicFromFrontend);
//						 
//						 break;
//					 }
//				 }
//				 graphicsRepository.save(graphicFromFrontend);
//			 }	
//		 }
		 
		 deleteGraphics();
		 for (Graphic graphic: graphics) {
			 graphicsRepository.save(graphic);
		 }	
		 
		 return graphicsRepository.findAll();
	 }
	 
	 public void deleteGraphics() {
		 for (Graphic graphic: graphicsRepository.findAll()) {
			 graphicsRepository.delete(graphic);
		 }		 
	 }
	 
	 public void deleteGraphic(Long id) {
		 if (graphicsRepository.existsById(id)) {			 
			 graphicsRepository.deleteById(id);
		 }
		 	 
	 }
	 

}
