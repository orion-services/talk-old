package dev.orion.services;

import java.util.HashSet;
import java.util.Set;

public class UserService {

    public Set<String> getEmails(Set<String> userID){

        Set<String> emails = new HashSet<>();

        String[][] users;

        //integracao fake com servico de usuario

        UserServiceFake userServiceFake = new UserServiceFake();

        userServiceFake.returnUsers(userID);

        users = userServiceFake.returnUsers(userID);

        for(int i=0;i<users.length;i++){
           emails.add(users[i][2]);
        }
    
        return emails;
    }
}
