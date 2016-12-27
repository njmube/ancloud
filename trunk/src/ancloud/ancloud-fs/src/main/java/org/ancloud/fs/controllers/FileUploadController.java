package org.ancloud.fs.controllers;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.ancloud.fs.StorageFileNotFoundException;
import org.ancloud.fs.StorageService;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileUploadController {

	private Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	private final StorageService storageService;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {

		model.addAttribute("files",
					storageService.loadAll().map(new Function<Path,String>(){
													@Override
													public String apply(Path path) {
														return MvcUriComponentsBuilder.fromMethodName(
																	FileUploadController.class,
																	"serveFile",
																	path.getFileName().toString()
																).build().toString();
													}
												})
											.collect(Collectors.toList())
		);

		return "uploadForm";
	}
	
	@GetMapping("/player")
	public String player(Model model) throws IOException {

		model.addAttribute("files",
				storageService.loadAll().map(new Function<Path,String>(){
												@Override
												public String apply(Path path) {
													return MvcUriComponentsBuilder.fromMethodName(
																FileUploadController.class,
																"serveFile",
																path.getFileName().toString()
															).build().toString();
												}
											})
										.collect(Collectors.toList())
		);

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
			} catch (Exception ex) {
				logger.error(ExceptionUtils.getStackTrace(ex));
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
					if(!fileItem.isFormField()){
						storageService.store(fileItem.openStream(),fileItem.getName());
					}
				}
			} catch (Exception ex) {
				isSuccess = false;
			} 
		}
		return new ResponseEntity<Object>(new EmptyJsonEntity(),HttpStatus.OK);
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}
