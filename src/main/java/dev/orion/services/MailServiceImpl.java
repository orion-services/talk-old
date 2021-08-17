package dev.orion.services;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import dev.orion.api.dto.ResponseDTO;
import dev.orion.api.dto.UserClientDTO;
import dev.orion.services.interfaces.MailService;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;

@ApplicationScoped
public class MailServiceImpl implements MailService {
    
    @Inject
    Mailer mailer;
    @Override
    public void sendMails(Set<ResponseDTO> responses) {
        responses.forEach(response ->{
            if(response.email != null){
                var email1 = Mail.withText(response.email, "Ahoy from Quarkus",
                "A simple email sent from a Quarkus application.");

                mailer.send(email1);
                response.isSended = true;
            }    
        });
    }

    @Override
    public Boolean containsUserNull(Map<String,UserClientDTO> returnedUsers) {

        return returnedUsers.values().contains(null);
    }

    @Override
    public Set<ResponseDTO> getResponses(Map<String, UserClientDTO> users) {

        Set<ResponseDTO> responses = new HashSet<>();

        users.forEach( (key, user) -> {

            ResponseDTO responseDTO = new ResponseDTO();

            responseDTO.uuid = key;         
            responseDTO.isUserNull = user == null;
            responseDTO.email = user != null ? user.email : null;
            responseDTO.isSended = false;

            responses.add(responseDTO);
            
        });

        return responses;
    }
}
