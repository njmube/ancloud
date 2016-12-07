package org.ancloud.fs.controllers;

import org.ancloud.fs.StorageFileNotFoundException;
import org.ancloud.fs.StorageService;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FileUploadController {

	private final StorageService storageService;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {

		model.addAttribute("files",
				storageService
						.loadAll()
						.map(path -> MvcUriComponentsBuilder
								.fromMethodName(FileUploadController.class,
										"serveFile",
										path.getFileName().toString()).build()
								.toString()).collect(Collectors.toList()));

		return "uploadForm";
	}
	
	@GetMapping("/player")
	public String player(Model model) throws IOException {

		model.addAttribute("files",
				storageService
						.loadAll()
						.map(path -> MvcUriComponentsBuilder
								.fromMethodName(FileUploadController.class,
										"serveFile",
										path.getFileName().toString()).build()
								.toString()).collect(Collectors.toList()));

		return "player";
	}
	
	

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping("/")
	public String handleFileUpload(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart){
			ServletFileUpload upload = new ServletFileUpload();
			FileItemIterator iterator;
			try {
				iterator = upload.getItemIterator(request);
				while (iterator.hasNext()) {
					FileItemStream fileItem = iterator.next();
					String name = fileItem.getFieldName();
					if(!fileItem.isFormField()){
						storageService.store(fileItem.openStream(),fileItem.getName());
					}
				}
			} catch (FileUploadException | IOException e) {
				e.printStackTrace();
			}
		}
		redirectAttributes.addFlashAttribute("message", "You successfully uploaded "
//						+ file.getOriginalFilename() 
						+ "!");

		return "redirect:/";
	}
	
	@GetMapping("/async-upload")
	public String asyncUpload(Model model) throws IOException {
		return "uploadForm2";
	}
	
	@PostMapping("/async-upload")
	public ResponseEntity<?> handleAjaxFileUpload(HttpServletRequest request) {
		boolean isSuccess = true;
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart){
			ServletFileUpload upload = new ServletFileUpload();
			FileItemIterator iterator;
			try {
				iterator = upload.getItemIterator(request);
				while (iterator.hasNext()) {
					FileItemStream fileItem = iterator.next();
					String name = fileItem.getFieldName();
					if(!fileItem.isFormField()){
						storageService.store(fileItem.openStream(),fileItem.getName());
					}
				}
			} catch (FileUploadException | IOException e) {
				isSuccess = false;
			}
		}
		return new ResponseEntity<>(new EmptyJsonEntity(),HttpStatus.OK);
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity handleStorageFileNotFound(
			StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}
