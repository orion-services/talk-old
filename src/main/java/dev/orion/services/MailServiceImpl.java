package dev.orion.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import dev.orion.api.dto.UserClientDTO;


@ApplicationScoped
public class MailServiceImpl implements MailService {

    @Override
    public Boolean isSendToAllStrictly(List<UserClientDTO> returnedUsers, Boolean sendToAll) {
    
        return returnedUsers.contains(null)  && 
        sendToAll.equals(Boolean.TRUE);
    }
    
}
