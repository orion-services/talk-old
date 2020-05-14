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
package orion.talk.service;

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
import javax.ws.rs.core.MediaType;

import orion.talk.data.DAOChannel;
import orion.talk.data.DAOTextMessage;
import orion.talk.model.Channel;
import orion.talk.model.TextMessage;

@RequestScoped
@Path("/api/v1.0/")
public class ServiceController {

    @Inject
    private DAOTextMessage daoTextMessage;
    @Inject
    private DAOChannel daoChannel;

    /**
     * Creates a new Channel
     * 
     * @return A Channel object
     */
    @GET
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Channel createChannel() {
        final Channel channel = new Channel();
        return daoChannel.create(channel);
    }

    /**
     * Creates a text message in a Channel asynchronously
     * 
     * @param token   The token of a channel
     * @param message The message
     * @return Returns a TextMessage object
     */
    @POST
    @Path("/send")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public TextMessage createTextMessage(@FormParam("token") final String token,
            @FormParam("message") final String message) {

        TextMessage textMessage = new TextMessage();
        try {
            Channel channel = daoChannel.find("token", token);

            textMessage = new TextMessage();
            textMessage.setMessage(message);
            textMessage.setChannel(channel);
            daoTextMessage.create(textMessage);

        } catch (NoResultException e) {
            System.out.println(e);
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
    public Channel loadChannel(@PathParam("token") final String token) {
        Channel channel = null;
        try {
            channel = daoChannel.find("token", token);
        } catch (NoResultException e) {
            System.out.println("Channel object not found with token: " + token);
        } catch (Exception e) {
            System.out.println(e);
        }
        return channel;
    }

}
