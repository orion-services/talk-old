package dev.orion.api.dto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserClientDTO {

    @NotBlank(message = "uuid must not be empty")
    @NotNull
    public String uuid;

    @NotBlank(message = "Name must not be empty")
    @NotNull
    public String name;

    @NotBlank(message = "Email must not be empty")
    @NotNull
    public String email;

    @NotBlank(message = "Cpf must not be empty")
    @NotNull
    public String cpf;
        
}
