package com.like.file.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import com.like.board.service.BoardCommandService;
import com.like.file.domain.model.FileInfo;
import com.like.file.dto.FileResponseDTO;
import com.like.file.infra.file.LocalFileRepository;
import com.like.file.service.FileService;

@Controller
public class FileController {
	
	private BoardCommandService boardCommandService;
		
	private FileService fileService;	
			
	public FileController(BoardCommandService boardCommandService
						 ,FileService fileService) {
		this.boardCommandService = boardCommandService;
		this.fileService = fileService;
	}
	
	@RequestMapping(value={"/common/file/{id}"}, method=RequestMethod.GET) 
	public HttpServletResponse fileDownLoad(HttpServletResponse response,
			@PathVariable(value="id") String id) throws Exception {
								
		FileInfo fileInfo = fileService.getFileInfo(id);
		
		response = this.setResponse(response, fileInfo.getSize(), fileInfo.getFileName());
		
		fileService.downloadFile(fileInfo, response.getOutputStream());		
		
		return response;
	}
	
	@RequestMapping(value={"/common/fileimage/{id}"}, method=RequestMethod.GET) 
	public HttpServletResponse fileImageDownLoad(HttpServletResponse response,
			@PathVariable(value="id") String id) throws Exception {
								
		FileInfo fileInfo = fileService.getFileInfo(id);
					
		// set content attributes for the response
		response.setContentType(fileInfo.getContentType());
		response.setContentLengthLong(fileInfo.getSize());
		response.setCharacterEncoding("UTF-8");
									
		fileService.downloadFile(fileInfo, response.getOutputStream());		
		
		return response;
	}
	
	@RequestMapping(value={"/common/file"}, method=RequestMethod.POST) 
	public ResponseEntity<?> fileUpload(final MultipartHttpServletRequest request,
			@RequestParam(value="pgmId", required=false) String pgmId ) throws Exception {
						
		List<FileInfo> list = new ArrayList<FileInfo>();
		final Map<String, MultipartFile> files = request.getFileMap();
		Iterator<Entry<String,MultipartFile>> itr = files.entrySet().iterator();		
				
		while ( itr.hasNext() ) {			
			Entry<String,MultipartFile> entry = itr.next(); 			
			MultipartFile file = entry.getValue();					
			list.add(fileService.uploadFile(file, "kbm", pgmId));																
		}
		
								
		List<FileResponseDTO> fileList = new ArrayList<>();
		
		for (FileInfo info : list) {
			Map<String, String> res = new HashMap<>();
			res.put("status", "success");
			
			Map<String, String> link = new HashMap<>();
			link.put("download", LocalFileRepository.fileDownLoadUrl+info.getPkFile());
			
			FileResponseDTO response = FileResponseDTO.builder()
														.uid(info.getPkFile())
														.name(info.getFileName())
														.status("done")
														.response("success")
														//.linkProps("http://localhost:8090/common/file/"+info.getPkFile())
														.url(LocalFileRepository.fileDownLoadUrl+info.getPkFile())
														//.thumbUrl("http://localhost:8090/common/file/"+info.getPkFile())
														.build();
			fileList.add(response);
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
						 					
		return new ResponseEntity<List<FileResponseDTO>>(fileList, responseHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value={"/common/file2"}, method=RequestMethod.POST) 
	public void handleFileUpload(@RequestParam("file") MultipartFile file) throws Exception {

		fileService.uploadFile(file, "kbm", "test");
	}
				
		
		
	private HttpServletResponse setResponse(HttpServletResponse response, long fileSize, String fileName) throws Exception {
		
		// get MIME type of the file
		String mimeType= null;
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";	         
		}
		
		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLengthLong(fileSize);
		response.setCharacterEncoding("UTF-8");
		
		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment;filename=\"%s\"", new String(fileName.getBytes("EUC-KR"), "8859_1"));
		
		response.setHeader(headerKey, headerValue);
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");	
		
		return response;
	}
			
}