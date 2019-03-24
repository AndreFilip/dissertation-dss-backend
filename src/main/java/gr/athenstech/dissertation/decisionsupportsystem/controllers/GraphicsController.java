package gr.athenstech.dissertation.decisionsupportsystem.controllers;

import gr.athenstech.dissertation.decisionsupportsystem.services.GraphicsService;
import gr.athenstech.dissertation.decisionsupportsystem.model.Graphic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/graphics")
public class GraphicsController {
	private static final Logger logger = LoggerFactory.getLogger(GraphicsController.class);

	@Autowired
    private GraphicsService graphicsServiceImpl;
		
	 @GetMapping
	 public ResponseEntity<List<Graphic>> getAllGraphics() {
		 List<Graphic> graphics = null;
		 try {
			graphics = graphicsServiceImpl.getAllGraphics();
			return new ResponseEntity<List<Graphic>>(graphics, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Graphic>>(graphics, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 } 
	 
	 @PostMapping
	 public ResponseEntity<String> saveGraphics(@RequestBody List<Graphic> graphics) {	
		 logger.info("List<Graphic> graphics received: {}", graphics.toString());

		try {
//			graphicsServiceImpl.saveGraphics(graphics);
			return new ResponseEntity<String>("Successfully saved.", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error saving graphics.", HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	 }
	 
	 @DeleteMapping
	 public ResponseEntity<String> deleteGraphics() {
		 try {
			graphicsServiceImpl.deleteGraphics();
			return new ResponseEntity<String>("Successfully deleted graphics.", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error deleting graphics.", HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	 }
	 
	 @DeleteMapping("/{id}")
	 public ResponseEntity<String> deleteGraphic(@PathVariable Long id) {
		 try {
			graphicsServiceImpl.deleteGraphic(id);
			return new ResponseEntity<String>("Successfully deleted.", HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>("Error deleting graphics.", HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	 }
	 
	 @PostMapping("/{id}")
	 public ResponseEntity<String> saveGraphic(@RequestBody Graphic graphic) {		 
		try {
			graphicsServiceImpl.saveGraphic(graphic);
			return new ResponseEntity<String>("Successfully saved.", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error saving graphic.", HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	 }


}

