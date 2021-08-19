package dev.orion.api.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MailRequestDTO {

  @NotBlank(message = "Name must not be empty")
  @NotNull
  public Set<String> usersIds = new HashSet<>();

  @NotBlank(message = "sendToAll must not be empty")
  @NotNull
  public Boolean sendToAll;

  public String templateID;

}
