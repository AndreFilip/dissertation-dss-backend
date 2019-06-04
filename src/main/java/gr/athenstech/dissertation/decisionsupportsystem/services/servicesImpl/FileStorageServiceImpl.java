package gr.athenstech.dissertation.decisionsupportsystem.services.servicesImpl;

import gr.athenstech.dissertation.decisionsupportsystem.utils.exceptions.FileStorageException;
import gr.athenstech.dissertation.decisionsupportsystem.utils.exceptions.MyFileNotFoundException;
import gr.athenstech.dissertation.decisionsupportsystem.configurations.properties.FileStorageProperties;
import gr.athenstech.dissertation.decisionsupportsystem.configurations.security.SecurityUtils;
import gr.athenstech.dissertation.decisionsupportsystem.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileStorageServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(FileStorageServiceImpl.class);

    private final Path fileStorageLocation;   
    
    @Autowired
    private SecurityUtils securityUtils;
    
    @Autowired
    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUpload())
                .toAbsolutePath().normalize();
        
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
    
    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            if(!fileName.endsWith("kml")) {
                throw new FileStorageException("Sorry! Filename does not end with .kml " + fileName);
            }
            // Copy file to the target location (Replacing existing file with the same name)
            String username = securityUtils.getCurrentUser().getUsername();
            Path usersDirectoryPath = Paths.get(this.fileStorageLocation.toString(), username)
                    .toAbsolutePath().normalize();
            if (Files.notExists(usersDirectoryPath)) {
            	try {
                    Files.createDirectories(usersDirectoryPath);
                } catch (Exception ex) {
                	logger.info("Could not create the user's directory where the uploaded files will be stored: {}", username);
                    throw new FileStorageException("Could not create the directory where the uploaded files will be stored. ", ex);
                }
            }
            Files.list(usersDirectoryPath).forEach(p -> {
				try {
					Files.delete(p);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
                	logger.info("Could not delete: {}", p.toString());
				}
			});
            
            Path usersFilenamePath = Paths.get(this.fileStorageLocation.toString(),username).toAbsolutePath().normalize().resolve(fileName); 
            logger.info("targetLocation, {} " , usersFilenamePath.toString() );
            Files.copy(file.getInputStream(), usersFilenamePath, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    
    public void deleteFile () throws IOException {
    	String username = securityUtils.getCurrentUser().getUsername();
        Path usersDirectoryPath = Paths.get(this.fileStorageLocation.toString(), username)
                .toAbsolutePath().normalize();
        if (Files.exists(usersDirectoryPath)) {
	        	Files.list(usersDirectoryPath).forEach(p -> {
	    			try {
	    				Files.delete(p);
	    			} catch (IOException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	                	logger.info("Could not delete: {}", p.toString());
	    			}
	    		});        	
        }
        
    }
    
    public String getFile () throws IOException {
    	String username = securityUtils.getCurrentUser().getUsername();
        Path usersDirectoryPath = Paths.get(this.fileStorageLocation.toString(), username)
                .toAbsolutePath().normalize();
        if (Files.exists(usersDirectoryPath)) {
	        	List<Path> filepaths = Files.list(usersDirectoryPath).collect(Collectors.toList());	        	
	        	if (filepaths != null && filepaths.get(0) != null) {	        		
		             return filepaths.get(0).getFileName().toString();		           
	        	}          
	        	 
        }
        return null;
        
    }
    
    public Resource loadFileAsResource(String fileName, String username) {
        logger.info("loadFileAsResource fileName, {} " , fileName);

    	Path usersPath = null;
        try {        	
            usersPath = Paths.get(this.fileStorageLocation.toString(), username).toAbsolutePath().normalize().resolve(fileName); 
            Resource resource = new UrlResource(usersPath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                logger.info("!resource.exists(), {} " , usersPath.toString());
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            logger.info("malformed, {} " , usersPath.toString());
            throw new MyFileNotFoundException("File was malformed and not found " + fileName, ex);
        }
    }

}
