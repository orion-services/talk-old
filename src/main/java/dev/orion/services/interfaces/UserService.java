package dev.orion.services.interfaces;

import java.util.Map;
import java.util.Set;

import dev.orion.api.dto.UserClientDTO;

public interface UserService {

    public Set<String> getEmails(Map<String, UserClientDTO> users);

    public Map<String, UserClientDTO> getUsers(Set<String> userID);

    public Boolean validateService(String serviceId);

}
