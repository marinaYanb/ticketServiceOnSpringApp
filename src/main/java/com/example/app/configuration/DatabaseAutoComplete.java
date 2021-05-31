package com.example.app.configuration;

import com.example.app.model.Plane;
import com.example.app.model.User;
import com.example.app.repository.PlaneRepository;
import com.example.app.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;

@Component
public class DatabaseAutoComplete implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PlaneRepository planeRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        User[] users = mapper.readValue(new File("src/main/resources/user.json"), User[].class);
        Arrays.stream(users).forEach(userRepository::save);
        Plane[] planes = mapper.readValue(new File("src/main/resources/plane.json"), Plane[].class);
        Arrays.stream(planes).forEach(planeRepository::save);
    }
}
