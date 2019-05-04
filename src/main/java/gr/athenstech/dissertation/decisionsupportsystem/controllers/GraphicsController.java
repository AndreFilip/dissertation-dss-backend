package gr.athenstech.dissertation.decisionsupportsystem.controllers;

import gr.athenstech.dissertation.decisionsupportsystem.services.GraphicsService;
import gr.athenstech.dissertation.decisionsupportsystem.model.Graphic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/graphics")
public class GraphicsController {
	private static final Logger logger = LoggerFactory.getLogger(GraphicsController.class);

	@Autowired
    private GraphicsService graphicsServiceImpl;
	
	
	 @GetMapping("/mapExists")
	 public ResponseEntity<Map<String, Boolean>> mapExists() {
		 Boolean result = Boolean.valueOf(false);
		 Map <String,Boolean> response = new HashMap<>();
		 try {
			result = graphicsServiceImpl.mapExists();
			response.put("myresponse", result);
			return new ResponseEntity<Map<String, Boolean>>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("myerror", result);
			return new ResponseEntity<Map<String, Boolean>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 }
		
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
	 
	 @GetMapping("/{id}")
	 public ResponseEntity<Optional<Graphic>> getGraphic(@PathVariable Long id) {
		Optional<Graphic> graphic = null;
		 try {
			graphic = graphicsServiceImpl.getGraphic(id);
			return new ResponseEntity<Optional<Graphic>>(graphic, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Optional<Graphic>>(graphic, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 } 
	 
	 @PostMapping
	 public ResponseEntity<Map<String,String>> saveGraphics(@RequestBody List<Graphic> graphics) {	
		 logger.info("List<Graphic> graphics received: {}", graphics.toString());
		 Map <String,String> response = new HashMap<>();
		try {
			graphicsServiceImpl.saveGraphics(graphics);
			response.put("myresponse", "Successfully saved graphics.");
			return new ResponseEntity<Map<String,String>>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("myerror", "Error saving graphics.");
			return new ResponseEntity<Map<String,String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	 }
	 
	 @DeleteMapping
	 public ResponseEntity<Map<String,String>> deleteGraphics() {
		 Map <String,String> response = new HashMap<>();
		 try {
			graphicsServiceImpl.deleteGraphics();
			response.put("myresponse", "Successfully deleted graphics.");
			return new ResponseEntity<Map<String,String>>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("myerror", "Error deleting graphics.");
			return new ResponseEntity<Map<String,String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	 }
	 
	 @DeleteMapping("/{id}")
	 public ResponseEntity<Map<String,String>> deleteGraphic(@PathVariable Long id) {
		 Map <String,String> response = new HashMap<>();
		 try {
			graphicsServiceImpl.deleteGraphic(id);
			response.put("myresponse", "Successfully deleted 1 graphic.");
			return new ResponseEntity<Map<String,String>>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.put("myerror", "Error deleting 1 graphic.");
			return new ResponseEntity<Map<String,String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	 }
	 
	 @PostMapping("/saveGraphic")
	 public ResponseEntity<Object> saveGraphic(@RequestBody Graphic graphic) {		
//		 Map <String,String> response = new HashMap<>();
//		 logger.debug("Graphic received: " + graphic.toString());
		try {
			graphic = graphicsServiceImpl.saveGraphic(graphic);
//			response.put("myresponse", "Successfully saved 1 graphic.");
			return new ResponseEntity<Object>(graphic, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
//			response.put("myerror", "Error in saving 1 graphic");
			return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	 }


}

