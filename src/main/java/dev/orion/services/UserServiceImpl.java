package dev.orion.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import dev.orion.api.dto.UserClientDTO;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Override
    public Set<String> getEmails(List<UserClientDTO> users) {

        Set<String> emails = new HashSet<>();

        users.forEach(user ->{
            emails.add(user.email);
        });
     
        return emails;
    }
    
    @Override
    public List<UserClientDTO> getUsers(Set<String> usersIDs) {

        return mockUserCreation(usersIDs);

    }

    public List<UserClientDTO> mockUserCreation(Set<String> usersIDs){

        ArrayList<UserClientDTO> users = new ArrayList<>();

        usersIDs.forEach(userId -> {
            var userDTO = new UserClientDTO();
            userDTO.name = "teste";
            userDTO.cpf = "00000000";
            userDTO.email = "example@example.com";
            userDTO.uuid = userId;

            users.add(userDTO);
        });

        return users;

    }
}
