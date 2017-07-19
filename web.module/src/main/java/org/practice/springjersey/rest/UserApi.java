package org.practice.springjersey.rest;

import lombok.extern.slf4j.Slf4j;
import org.practice.springjersey.domain.User;
import org.practice.springjersey.service.UserService;
import org.practice.springjersey.util.ApiLinkBuilder;
import org.practice.springjersey.validation.UserValidation;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/users")
@Slf4j
public class UserApi {

    @Inject
    private UserService userService;

    @Inject
    private ApiLinkBuilder apiLinkBuilder;

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(
            @Context UriInfo uriInfo,
            @Valid @UserValidation User user){

        log.debug("create request for {}", user);

        user = userService.createUser(user);
        URI uri = apiLinkBuilder.createObjectLink(uriInfo, user.getId().toString());

        return Response
                .created(uri)
                .build();
    }


    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers(){

        List<User> userList = userService.findAllUsers();

        return Response
                .ok()
                .entity(userList)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id){

        User user = userService.findUserById(id);

        return Response
                .ok()
                .entity(user)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id")Long id, @Valid @UserValidation User user){

        userService.updateUser(id, user);

        return Response
                .noContent()
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id){

        userService.deleteUser(id);

        return Response
                .noContent()
                .build();
    }

}
