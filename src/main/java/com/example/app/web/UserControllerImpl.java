package com.example.app.web;

import com.example.app.model.User;
import com.example.app.model.UserDto;
import com.example.app.service.UserService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;
    final MapperFacade mapperFacade;

    public List<UserDto> findAllUsers() {
        return mapperFacade.mapAsList(userService.findAllUsers(), UserDto.class);
    }

    public UserDto findUserById(@PathVariable Long userId) {
        return mapperFacade.map(userService.findUserById(userId), UserDto.class);
    }

    public UserDto createUser(@RequestBody User user) {
        if (user.isDeleted()) {
            return null;
        }
        return mapperFacade.map(userService.createUser(user), UserDto.class);
    }

    public void updateUser(@PathVariable Long userId, @RequestBody User user) {
        user.setId(userId);
        userService.updateUser(user);
    }

     public void patchUser(@PathVariable Long userId) {
        User patchedAsDeleted = userService.findUserById(userId);
        patchedAsDeleted.setDeleted(true);
        userService.createUser(patchedAsDeleted);
    }
}
