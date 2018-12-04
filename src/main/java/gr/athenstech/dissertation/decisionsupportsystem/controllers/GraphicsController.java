//package gr.athenstech.dissertation.decisionsupportsystem.controllers;
//
//import gr.athenstech.dissertation.decisionsupportsystem.model.Graphic;
//import gr.athenstech.dissertation.decisionsupportsystem.services.GraphicsServive;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@CrossOrigin
//@RestController
//@RequestMapping("/graphics")
//public class GraphicsController {
//	
//	@Autowired
//    private GraphicsServive graphicsServive;
//		
//	 @GetMapping
//	 public List<Graphic> getAllGraphics() {
//		 return graphicsServive.getAllGraphics();
//	 } 
//	 
//	 @PostMapping
//	 public List<Graphic> saveGraphics(@RequestBody List<Graphic> graphics) {			 
//	   return graphicsServive.saveGraphics(graphics);
//	 }
//	 
//	 @DeleteMapping
//	 public void deleteGraphics() {
//		 graphicsServive.deleteGraphics();		 
//	 }
//	 
//	 @DeleteMapping("/{id}")
//	 public void deleteGraphic(@PathVariable Long id) {
//		 graphicsServive.deleteGraphic(id);		 
//	 }
//	 
////	 @GetMapping(value = "/{id}")
////	 public Graphics getGraphicById(@PathVariable("id") ObjectId id) {
////	   return graphicsRepository.findBy_id(id);
////	 }
//	 
////	 @PutMapping(value = "/{id}")
////	 public Point modifyGraphicById(@PathVariable("id") ObjectId id, @Valid @RequestBody Point graphic) { 
////		 graphic.set_id(id);	   
////		 graphicsRepository.save(graphic);
////		 return graphic;
////	 }
////	 
////	 @DeleteMapping(value = "/{id}")
////	 public void deleteGraphic(@PathVariable ObjectId id) {
////		 graphicsRepository.delete(graphicsRepository.findBy_id(id));
////	 }
////	 
////	 @GetMapping(value = "/getgraphicbyname/{name}")
////	 public List<Point> getGraphicByName(@PathVariable("name") String name) {
////	   return graphicsRepository.findByName(name);
////	 }
//	 
//
//	 
//}
//
