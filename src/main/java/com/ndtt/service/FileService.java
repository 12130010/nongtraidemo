package com.ndtt.service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	@Value("${spring.http.multipart.location}")
	private String pathString;

	public String save(MultipartFile file) throws URISyntaxException, IOException {
		String fileName = generateFileName(file);
		Path path = Paths.get(pathString, fileName);
		Files.write(path, file.getBytes());
		return fileName;
	}

	public List<String> save(List<MultipartFile> files) throws URISyntaxException, IOException {
		String[] fileNames = new String[files.size()];
		int index = 0;
		for (MultipartFile file : files) {
			fileNames[index++] = save(file);
		}
		return Arrays.asList(fileNames);

	}

	String generateFileName(MultipartFile file) {
		long name = System.currentTimeMillis();
		String originalName = file.getOriginalFilename();
		int extensionIndex = originalName.lastIndexOf('.');
		return Long.toString(name).concat(originalName.substring(extensionIndex));
	}

	public boolean delete(String fileName) {
		File file = new File(pathString, fileName);
		return file.delete();
	}

	public boolean delete(List<String> fileNames) throws URISyntaxException, IOException {
		for (String fileName : fileNames) {
			if (!delete(fileName)) {
				return false;
			}
		}
		return true;
	}
}
