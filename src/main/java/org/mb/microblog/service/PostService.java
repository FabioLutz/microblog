package org.mb.microblog.service;

import org.mb.microblog.dto.PostDto;
import org.mb.microblog.dto.UserDto;
import org.mb.microblog.model.Post;
import org.mb.microblog.model.PostRepository;
import org.mb.microblog.model.User;
import org.mb.microblog.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class PostService implements IPostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    private static PostDto convertToDto(Post post) {
        PostDto postDto = new PostDto(
                post.getId(),
                post.getTitle(),
                post.getContent()
        );
        UserDto userDto = new UserDto(
                post.getUser().getId(),
                post.getUser().getName()
        );
        postDto.setUserDto(userDto);
        return postDto;
    }

    @Override
    public PostDto postFindById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Post not found"));
        return convertToDto(post);
    }

    @Override
    @Transactional
    public PostDto postCreate(Post post, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Post not found"));
        post.setUser(user);
        Post postCreated = postRepository.save(post);
        return postFindById(postCreated.getId());
    }
}
