package dev.orion.services;

import java.util.Set;

public class UserServiceFake {

    public String[][] returnUsers(Set<String> usersIDs){

        String[] user1 = {"uuid1","name", "example@outlook.com", "cpf"};  
        String[] user2 = {"uudi2","name", "example@gmail.com", "cpf"};  
        
        String[][] usersBank = {user1,user2};

        return usersBank;
    }
}