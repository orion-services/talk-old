package dev.orion.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TemplateDTO {

    @NotBlank(message = "serviceID must not be empty")
    @NotNull
    public String serviceID;

    @NotBlank(message = "Title must not be empty")
    @NotNull
    public String title;

    @NotBlank(message = "Msg must not be empty")
    @NotNull
    public String msg;
}
