package dev.orion;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.smallrye.common.annotation.Blocking;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/mail")                                                          
public class MailResource {

    @Inject Mailer mailer;  
    
    

    @GET                                                                
    @Blocking                                                           
    public void sendEmail() {
        
        var email1 = Mail.withText("example@example.com",
        "Ahoy from Quarkus",
        "A simple email sent from a Quarkus application.");
        var email2 = Mail.withText("example@example.com",
        "Ahoy from Quarkus",
        "A simple email sent from a Quarkus application.");

        mailer.send(
                email1,email2
        );
    }

}