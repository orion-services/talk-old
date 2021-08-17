package dev.orion.services.interfaces;

import java.util.Map;
import java.util.Set;

import dev.orion.api.dto.MailResponseDTO;
import dev.orion.api.dto.UserClientDTO;

public interface MailService {

    public Boolean containsUserNull(Map<String,UserClientDTO> returnedUsers);
    public Set<MailResponseDTO> getResponses(Map<String,UserClientDTO> users);
    public void sendMails(Set<MailResponseDTO> responses);

}
