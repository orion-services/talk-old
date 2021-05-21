/**
 * @License
 * Copyright 2020 Orion Services
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package orion.talk.secure;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import orion.talk.data.DAOChannel;
import orion.talk.data.DAOTextMessage;
import orion.talk.model.Channel;
import orion.talk.model.TextMessage;

@Path("/api/v1/")
@RequestScoped
public class ProtectedService {

    @Inject
    private DAOTextMessage daoTextMessage;

    @Inject
    private DAOChannel daoChannel;

    /**
     * Sends a text message to a Channel asynchronously
     * 
     * @param token   The token of a channel
     * @param message The message
     * 
     * @return A TextMessage object
     */
    @POST
    @Path("/send")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public TextMessage sendTextMessage(@FormParam("token") final String token,
            @FormParam("message") final String message) throws WebApplicationException {

        String errorMessage = null;

        TextMessage textMessage = new TextMessage();

        if (!message.equals("")) {
            try {
                Channel channel = daoChannel.find("token", token);

                textMessage = new TextMessage();
                textMessage.setMessage(message);
                textMessage.setChannel(channel);
                daoTextMessage.create(textMessage);

            } catch (NoResultException e) {
                errorMessage = "Channel not found";
                throw new WebApplicationException(errorMessage, Response.Status.NOT_FOUND);
            }
        } else {
            errorMessage = "The message is empty";
            throw new WebApplicationException(errorMessage, Response.Status.NOT_FOUND);
        }

        return textMessage;
    }

    /**
     * Returns a channel with all text messages
     * 
     * @param token The token of a channel
     * @return A Channel object
     */
    @GET
    @Path("/load/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Channel loadChannel(@PathParam("token") final String token) throws WebApplicationException {
        Channel channel = null;
        try {
            channel = daoChannel.find("token", token);
        } catch (Exception e) {
            throw new WebApplicationException("Channel not found", Response.Status.NOT_FOUND);
        }
        return channel;
    }
}
