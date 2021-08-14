package dev.orion;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.smallrye.common.annotation.Blocking;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.UUID;

import dev.orion.api.dto.MailRequestDTO;
import dev.orion.services.UserService;

@Path("/mail")                                                          
public class MailResource {

    @Inject Mailer mailer;  
    
    @GET                                                                
    @Blocking
    @Consumes(MediaType.APPLICATION_JSON)                                                           
    public Response sendEmail(MailRequestDTO mailRequestDTO) {

        UserService userService = new UserService();

        Set<String> usersIds = mailRequestDTO.usersIds;

        Set<String> emails = userService.getEmails(usersIds);

        //aqui chamaria a interface

        emails.forEach(email ->{
        
        var email1 = Mail.withText(email,
        "Ahoy from Quarkus",
        "A simple email sent from a Quarkus application.");

            mailer.send(
                    email1
            );
        });
        
        return Response.ok().build();
    }

}