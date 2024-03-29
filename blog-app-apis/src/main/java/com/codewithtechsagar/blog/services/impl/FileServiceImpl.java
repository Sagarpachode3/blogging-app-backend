package com.codewithtechsagar.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.codewithtechsagar.blog.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		// File name
		String name = file.getOriginalFilename();
		// abc.png

		// Validate file type
		if (!isValidImageType(file.getContentType(), name)) {
			throw new IllegalArgumentException("Invalid image type. Allowed types are PNG, JPEG, or JPG.");
		}

		// random name generate file
		String randomId = UUID.randomUUID().toString();
		String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));

		// Fullpath
		String filePath = path + File.separator + fileName1;

		// create folder if not created
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		// file Copy
		Files.copy(file.getInputStream(), Paths.get(filePath));

		return fileName1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath = path + File.separator + fileName;
		InputStream is = new FileInputStream(fullPath);

		return is;
	}

	private boolean isValidImageType(String contentType, String fileName) {

		// Check if the content type or file extension indicates an image of type PNG,
		// JPEG, or JPG

		return contentType != null
				&& (contentType.startsWith("image/png") || contentType.startsWith("image/jpeg")
						|| contentType.startsWith("image/jpg"))
				&& StringUtils.getFilenameExtension(fileName).matches("(?i)(png|jpe?g)");

		// OR

		/*
		 * private boolean isValidImageType(String fileName) { 
		 * String extension = StringUtils.getFilenameExtension(fileName);
		 * 
		 * // Check if the extension indicates an image of type PNG, JPEG, or JPG
		 * 
		 * return extension != null && extension.matches("(?i)(png|jpe?g)"); }
		 OR
		 * return extension != null && fileName.matches("(?i).+\\.(png|jpeg|jpg)");
		 * 
		 * (?i): Case-insensitive matching. 
		 * .+:: Matches one or more characters (any character) before the dot (.) in the file extension. 
		 * \\.: Escaped dot (.) to match the actual dot in the file extension.
		 *  (png|jpeg|jpg): Alternation to match either "png", "jpeg", or "jpg".
		 * 
		 */
	}

}
