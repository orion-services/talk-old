package dev.orion.services;

import java.util.List;
import java.util.Set;

import dev.orion.api.dto.UserClientDTO;

public interface UserService {

    public Set<String> getEmails(List<UserClientDTO> users);
    public List<UserClientDTO> getUsers(Set<String> userID);
        
}
