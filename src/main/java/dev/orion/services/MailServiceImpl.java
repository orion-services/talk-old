package dev.orion.services;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import dev.orion.api.dto.ResponseDTO;
import dev.orion.api.dto.UserClientDTO;
import dev.orion.services.interfaces.MailService;




@ApplicationScoped
public class MailServiceImpl implements MailService {

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
