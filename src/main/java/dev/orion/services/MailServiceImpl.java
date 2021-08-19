package dev.orion.services;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import java.util.UUID;
import dev.orion.api.dto.MailResponseDTO;
import dev.orion.api.dto.TemplateDTO;
import dev.orion.api.dto.UserClientDTO;
import dev.orion.api.entity.Template;
import dev.orion.services.interfaces.MailService;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;

@ApplicationScoped
public class MailServiceImpl implements MailService {

    @Inject
    Mailer mailer;

    @Inject
    Template template;

    @Override
    public TemplateDTO getTemplete(String templateID) {

        if (templateID != null) {

            try {
                Optional<Template> templateFound = Template.findByIdOptional(UUID.fromString(templateID));
                if (templateFound.isPresent()) {

                    TemplateDTO templateDTO = new TemplateDTO();
                    templateDTO.msg = templateFound.get().msg;
                    templateDTO.title = templateFound.get().title;

                    return templateDTO;

                } else {

                    throw new NotFoundException("template not found");
                }
            } catch (IllegalArgumentException i) {
                throw new IllegalArgumentException("invalid uuid format");
            }

        }

        return null;
    }

    @Override
    public void sendMails(Set<MailResponseDTO> responses, String templateID) {

        var returnedTemplate = getTemplete(templateID);

        if (returnedTemplate != null) {

            responses.forEach(response -> {

                if (response.email != null) {
                    var email1 = Mail.withText(response.email, returnedTemplate.title, returnedTemplate.msg);

                    mailer.send(email1);
                    response.isSended = true;
                }
            });

        } else {

            responses.forEach(response -> {

                if (response.email != null) {
                    var email1 = Mail.withText(response.email, "Ahoy from Quarkus", "default message");

                    mailer.send(email1);
                    response.isSended = true;
                }
            });
        }

    }

    @Override
    public Boolean containsUserNull(Map<String, UserClientDTO> returnedUsers) {

        return returnedUsers.values().contains(null);
    }

    @Override
    public Set<MailResponseDTO> getResponses(Map<String, UserClientDTO> users) {

        Set<MailResponseDTO> responses = new HashSet<>();

        users.forEach((key, user) -> {

            MailResponseDTO mailResponseDTO = new MailResponseDTO();

            mailResponseDTO.uuid = key;
            mailResponseDTO.isUserNull = user == null;
            mailResponseDTO.email = user != null ? user.email : null;
            mailResponseDTO.isSended = false;

            responses.add(mailResponseDTO);

        });

        return responses;
    }
}
