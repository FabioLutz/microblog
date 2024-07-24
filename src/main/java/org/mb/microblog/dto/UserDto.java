package org.mb.microblog.dto;

import java.util.List;

public class UserDto {
    private Long id;
    private String name;
    private List<PostDto> postDtos;

    public UserDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PostDto> getPostDtos() {
        return postDtos;
    }

    public void setPostDtos(List<PostDto> postDtos) {
        this.postDtos = postDtos;
    }
}
