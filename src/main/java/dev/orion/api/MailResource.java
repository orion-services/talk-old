package dev.orion.api;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.smallrye.common.annotation.Blocking;

import javax.ws.rs.Produces;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dev.orion.api.dto.MailRequestDTO;
import dev.orion.services.interfaces.MailService;
import dev.orion.services.interfaces.UserService;

@Path("/mail")
public class MailResource {

    @Inject
    Mailer mailer;

    @Inject
    UserService userService;

    @Inject
    MailService mailService;

    @POST
    @Blocking
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendEmail(MailRequestDTO mailRequestDTO) {

        var usersIds = mailRequestDTO.usersIds;

        var sendToAll = mailRequestDTO.sendToAll;

        var users = userService.getUsers(usersIds);

        var containsUserNull = mailService.containsUserNull(users);

        var responses = mailService.getResponses(users);

        if (containsUserNull.equals(Boolean.TRUE) && sendToAll.equals(Boolean.FALSE)) {

            return Response.status(502).build();

        } else {

            responses.forEach(response ->{
                if(response.email != null){
                    var email1 = Mail.withText(response.email, "Ahoy from Quarkus",
                    "A simple email sent from a Quarkus application.");

                    mailer.send(email1);
                    response.isSended = true;
                }    
            });

            return Response.ok(responses).build();
        }

    }

}