package com.ryujm.memo.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ryujm.memo.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/post")
public class PostRestController {

	public final PostService postService;
	
	public PostRestController(PostService postService) {
		this.postService = postService;
	}
	
	// 메모입력 API
	@PostMapping("/create")
	public Map<String, String> createMemo(@RequestParam String title
					,@RequestParam String contents
					, @RequestParam(required=false) MultipartFile imageFile
					, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(postService.addPost(userId, title, contents, imageFile)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
		
	}
	
	// 메모 수정 API
	@PutMapping("/update")
	public Map<String, String> updatePost(
				@RequestParam int userId
				,@RequestParam String title
				,@RequestParam String contents) {
		
		Map<String, String>resultMap = new HashMap<>();
		if(postService.updatePost(userId, title, contents)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	@DeleteMapping("/delete")
	public Map<String, String> deletePost(@RequestParam int id) {
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(postService.deletePost(id)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	
	
	
}
