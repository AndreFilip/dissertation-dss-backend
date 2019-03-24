package gr.athenstech.dissertation.decisionsupportsystem.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import gr.athenstech.dissertation.decisionsupportsystem.dto.UploadFileResponse;
import gr.athenstech.dissertation.decisionsupportsystem.services.servicesImpl.FileStorageServiceImpl;

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
   
   @PostMapping("/uploadKml")
   public ResponseEntity<UploadFileResponse> uploadFile(@RequestParam("file") MultipartFile file) {
       String fileName = fileStorageServiceImpl.storeFile(file);

       String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
               .path("/downloadFile/")
               .path(fileName)
               .toUriString();

       return new ResponseEntity<UploadFileResponse>(new UploadFileResponse(fileName, fileDownloadUri,file.getContentType(), file.getSize()), HttpStatus.OK);
   }
   
   @GetMapping("/downloadFile/{fileName:.+}")
   public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
       // Load file as Resource
       Resource resource = fileStorageServiceImpl.loadFileAsResource(fileName);

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

}
