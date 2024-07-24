package org.mb.microblog.service;

import org.mb.microblog.dto.UserDto;
import org.mb.microblog.model.User;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    UserDto userFindById(Long id);
    UserDto createUser(User user);
}
