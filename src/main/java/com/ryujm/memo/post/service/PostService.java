package com.ryujm.memo.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ryujm.memo.post.domain.Post;
import com.ryujm.memo.post.repository.PostRepository;

import jakarta.persistence.PersistenceException;

@Service
public class PostService {

	public final PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	
	public boolean addPost(int userId, String title, String contents) {
		
		Post post = Post.builder()
				.userId(userId)
				.title(title)
				.contents(contents)
				.build();
		
		try {
			
			postRepository.save(post);
			
		} catch(PersistenceException e) {
			return false;
		}
		
		return true;
		
	}
	
	public List<Post> getPostList(int userId) {
		return postRepository.findByUserIdOrderByIdDesc(userId);
	}
	
	public Post getPost(int id) {
		
		Optional<Post> optionalPost = postRepository.findById(id);
		
		return optionalPost.orElse(null);
		
	}
	
	
}
