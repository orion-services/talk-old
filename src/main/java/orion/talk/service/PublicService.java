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
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import orion.talk.data.DAOChannel;
import orion.talk.model.Channel;

@RequestScoped
@Path("/api/v1/")
public class PublicService {

    @Inject
    private DAOChannel daoChannel;

    /**
     * Creates a new Channel
     * 
     * @return A Channel object
     */
    @GET
    @Path("/createChannel")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Channel createChannel() {
        String errorMessage = null;
        try {
            return daoChannel.create(new Channel());
        } catch (Exception e) {
            errorMessage = "Was not possible to create a channel";
            throw new WebApplicationException(errorMessage, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

}
