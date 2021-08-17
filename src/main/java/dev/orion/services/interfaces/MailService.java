package dev.orion.services.interfaces;

import java.util.Map;
import java.util.Set;

import dev.orion.api.dto.ResponseDTO;
import dev.orion.api.dto.UserClientDTO;

public interface MailService {

    public Boolean containsUserNull(Map<String,UserClientDTO> returnedUsers);
    public Set<ResponseDTO> getResponses(Map<String,UserClientDTO> users);
    public void sendMails(Set<ResponseDTO> responses);

}
