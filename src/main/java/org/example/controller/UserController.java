package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.dto.CreateUser;
import org.example.dto.UpdateUser;
import org.example.entity.User;
import org.example.repository.UserRepository;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final UserRepository repository = new UserRepository();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String create(String userCreateDto) throws JsonProcessingException {

        CreateUser dto = mapper.readValue(userCreateDto, CreateUser.class);

        User user = repository.create(dto);

        return mapper.writeValueAsString(user);
    }

    @GET
    public String getAll() throws JsonProcessingException {

        List<User> users = repository.getAll();

        return mapper.writeValueAsString(users);

    }

    @GET
    @Path("/{id}")
    public String getById(@PathParam("id") Long id) throws JsonProcessingException {

        User user = repository.getById(id);

        return mapper.writeValueAsString(user);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {

        repository.delete(id);
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") Long id, String updateUserDto) throws JsonProcessingException {

        UpdateUser dto = mapper.readValue(updateUserDto, UpdateUser.class);
        repository.update(id, dto);

    }

}
