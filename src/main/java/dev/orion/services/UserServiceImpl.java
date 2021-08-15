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

        users.forEach(user -> {
            if(user != null){
                
                emails.add(user.email);

            }
        });

     
        return emails;
    }
    
    @Override
    public List<UserClientDTO> getUsers(Set<String> usersIDs) {

        return mockUserCreation(usersIDs);

    }

    public List<UserClientDTO> mockUserCreation(Set<String> usersIDs){

        ArrayList<UserClientDTO> users = new ArrayList<>();

        List<String> userIDsList = new ArrayList<>(usersIDs);

        //mock valids user and a null user
        
        var userDTOOne = new UserClientDTO();
        userDTOOne.name = "teste";
        userDTOOne.cpf = "00000000";
        userDTOOne.email = "example1@example.com";
        userDTOOne.uuid = userIDsList.get(0); 

        var userDTOTwo = new UserClientDTO();
        userDTOTwo.name = "teste";
        userDTOTwo.cpf = "00000000";
        userDTOTwo.email = "example2@example.com";
        userDTOTwo.uuid = userIDsList.get(1); 

        users.add(userDTOOne);
        users.add(userDTOTwo);
        users.add(null);

        return users;

    }
}
