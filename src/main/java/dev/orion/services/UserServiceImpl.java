package dev.orion.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import dev.orion.api.dto.UserClientDTO;
import dev.orion.services.interfaces.UserService;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Override
    public Boolean validateService(String serviceID) {
        
        return mockValidateService(serviceID).equals(false);

    }

    @Override
    public Set<String> getEmails(Map<String, UserClientDTO> users) {

        Set<String> emails = new HashSet<>();

        users.forEach((key, user) -> {

            if (user != null) {

                emails.add(user.email);

            }
        });

        return emails;
    }

    @Override
    public Map<String, UserClientDTO> getUsers(Set<String> usersIDs) {

        return mockUserCreation(usersIDs);

    }

    public Map<String, UserClientDTO> mockUserCreation(Set<String> usersIDs) {

        Map<String, UserClientDTO> users = new HashMap<>();

        List<String> userIDsList = new ArrayList<>(usersIDs);

        // mock valids user and a null user

        var userDTOOne = new UserClientDTO();
        userDTOOne.name = "teste";
        userDTOOne.cpf = "00000000";
        userDTOOne.email = "example@example.com";
        userDTOOne.uuid = userIDsList.get(0);

        var userDTOTwo = new UserClientDTO();
        userDTOTwo.name = "teste";
        userDTOTwo.cpf = "00000000";
        userDTOTwo.email = "example2@example.com";
        userDTOTwo.uuid = userIDsList.get(1);

        users.put(userIDsList.get(0), userDTOOne);
        users.put(userIDsList.get(1), userDTOTwo);
        users.put(userIDsList.get(2), null);

        return users;

    }

    public Boolean mockValidateService(String serviceID) {

       return serviceID.equals("123");

    }
}
