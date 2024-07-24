package org.mb.microblog.service;

import org.mb.microblog.dto.PostDto;
import org.mb.microblog.model.Post;

public interface IPostService {
    PostDto postFindById(Long id);
    PostDto postCreate(Post post, Long userId);
}
