package gr.athenstech.dissertation.decisionsupportsystem.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import gr.athenstech.dissertation.decisionsupportsystem.configurations.security.SecurityUtils;
import gr.athenstech.dissertation.decisionsupportsystem.dto.UploadFileResponse;
import gr.athenstech.dissertation.decisionsupportsystem.model.User;
import gr.athenstech.dissertation.decisionsupportsystem.services.servicesImpl.FileStorageServiceImpl;
import gr.athenstech.dissertation.decisionsupportsystem.utils.exceptions.FileStorageException;
import gr.athenstech.dissertation.decisionsupportsystem.utils.exceptions.MyFileNotFoundException;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@CrossOrigin
@RestController
@RequestMapping("/files")
public class FileController {

   private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
   @Autowired
   private FileStorageServiceImpl fileStorageServiceImpl;
   
   @Autowired
   private SecurityUtils securityUtils;
	 
   @PostMapping("/uploadKml")
   public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
       String fileName;
		try {
			fileName = fileStorageServiceImpl.storeFile(file);
		} catch (FileStorageException e) {
			e.printStackTrace();
		    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
       
       String username = securityUtils.getCurrentUser().getUsername();

       String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
               .path("/files/downloadFile/")               
               .path(username)
               .path("/")
               .path(fileName)
               .toUriString();

       return new ResponseEntity<UploadFileResponse>(new UploadFileResponse(fileName, fileDownloadUri,file.getContentType(), file.getSize()), HttpStatus.OK);
   }
   
   @DeleteMapping("/deleteKml")
   public ResponseEntity<?> deleteFile() {
		try {
			fileStorageServiceImpl.deleteFile();
		} catch (IOException e) {
			e.printStackTrace();
		    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}       

       return new ResponseEntity<>(HttpStatus.OK);
   }
   
   @GetMapping("/downloadFile/{username}/{fileName:.+}")
   public ResponseEntity<Resource> downloadFile( @PathVariable String username, @PathVariable String fileName, HttpServletRequest request) {
       logger.info("downloadFile fileName, {} " , fileName);

//       String usernameNow = securityUtils.getCurrentUser().getUsername();
//       if(!usernameNow.equals(username)) {
//		    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//       }
       // Load file as Resource
       Resource resource;
		try {
			resource = fileStorageServiceImpl.loadFileAsResource(fileName, username);
		} catch (MyFileNotFoundException e) {
			e.printStackTrace();
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

       // Try to determine file's content type
       String contentType = null;
       try {
           contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
       } catch (IOException ex) {
           logger.info("Could not determine file type.");
       }

       // Fallback to the default content type if type could not be determined
       if(contentType == null) {
           contentType = "application/octet-stream";
       }

       return ResponseEntity.ok()
               .contentType(MediaType.parseMediaType(contentType))
               .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
               .body(resource);
   }
   
   @GetMapping("/downloadFile/getIfExists")
   public ResponseEntity<String> getFile (HttpServletRequest request) {
       // Load file as Resource
       Resource resource;
		try {
			resource = fileStorageServiceImpl.getFile();
		} catch (IOException e) {
			e.printStackTrace();
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if(resource != null) {
			// Try to determine file's content type
		       String contentType = null;
		       try {
		           contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		       } catch (IOException ex) {
		           logger.info("Could not determine file type.");
		       }

		       // Fallback to the default content type if type could not be determined
		       if(contentType == null) {
		           contentType = "application/octet-stream";
		       }
	       return ResponseEntity.ok().body(resource.toString());

		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       
       
   }

}
