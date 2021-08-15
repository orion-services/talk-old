package dev.orion.services;

import java.util.List;

import dev.orion.api.dto.UserClientDTO;

public interface MailService {

    public Boolean isSendToAllStrictly(List<UserClientDTO> returnedUsers, Boolean sendToAll);

}
