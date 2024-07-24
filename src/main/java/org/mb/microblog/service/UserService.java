package org.mb.microblog.service;

import org.mb.microblog.dto.PostDto;
import org.mb.microblog.dto.UserDto;
import org.mb.microblog.model.Post;
import org.mb.microblog.model.User;
import org.mb.microblog.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    private static UserDto convertToDto(User user) {
        UserDto userDto = new UserDto(
                user.getId(), user.getName()
        );
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : user.getPosts()) {
            PostDto postDto = new PostDto(
                    post.getId(),
                    post.getTitle(),
                    post.getContent()
            );
            postDtos.add(postDto);
        }
        userDto.setPostDtos(postDtos);
        return userDto;
    }

    @Override
    public UserDto userFindById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found")
                );
        return convertToDto(user);
    }


    @Override
    public UserDto createUser(User user) {
        User userCreated = userRepository.save(user);
        return userFindById(userCreated.getId());
    }
}
