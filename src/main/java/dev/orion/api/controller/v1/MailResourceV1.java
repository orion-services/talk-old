package dev.orion.api.controller.v1;

import io.smallrye.common.annotation.Blocking;

import javax.ws.rs.Produces;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dev.orion.api.dto.MailRequestDTO;
import dev.orion.services.interfaces.MailService;
import dev.orion.services.interfaces.UserService;

@Path("/mail")
public class MailResourceV1 {

    @Inject
    UserService userService;

    @Inject
    MailService mailService;

    @POST
    @Blocking
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendEmail(MailRequestDTO mailRequestDTO) {

        var sendToAll = mailRequestDTO.sendToAll;

        var users = userService.getUsers(mailRequestDTO.usersIds);

        var containsUserNull = mailService.containsUserNull(users);

        var responses = mailService.getResponses(users);

        if (containsUserNull.equals(Boolean.TRUE) && sendToAll.equals(Boolean.FALSE)) {

            return Response.status(403).build();

        } else {

            try {
                mailService.sendMails(responses, mailRequestDTO.templateID);

            } catch (IllegalArgumentException i) {

                return Response.status(400, "invalid uuid format").build();

            } catch (NotFoundException i) {

                return Response.status(404, "template not found").build();
            }
            return Response.ok(responses).build();
        }

    }

}