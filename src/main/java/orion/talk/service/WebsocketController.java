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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import orion.talk.data.DAOChannel;
import orion.talk.data.DAOTextMessage;

import orion.talk.model.Channel;
import orion.talk.model.TextMessage;

/**
 * Web Socket for Orion Talk Service
 * 
 * @author Rodrigo Prestes Machado
 */
@Stateless
@ServerEndpoint(value = "/talkws/{token}")
public class WebsocketController {

    // private Tracer tracer;

    @Inject
    private DAOTextMessage daoTextMessage;
    @Inject
    private DAOChannel daoTalk;

    private final Map<String, List<Session>> sessions = Collections
            .synchronizedMap(new HashMap<String, List<Session>>());

    @OnOpen
    public void onOpen(final Session session, @PathParam("token") final String token) {
        System.out.println("onOpen: " + token);

        // finding the sessions for the hash
        final List<Session> talkSessions = this.sessions.get(token);

        // Create new talk with the first session
        if (talkSessions == null) {
            final List<Session> newTalkSessions = new ArrayList<Session>();
            newTalkSessions.add(session);
            this.sessions.put(token, newTalkSessions);
        } else
            // Add a new session in a current talk
            talkSessions.add(session);

        // Send to all the hash
        this.sendToAll(token, token);
    }

    @OnMessage
    public void onMessage(final String message, @PathParam("token") final String token) {
        // Span span = this.tracer.buildSpan("talk-onMessage").start();
        System.out.println("onMessage: " + message);
        final Channel channel = daoTalk.find("token", token);
        if (channel != null) {
            final TextMessage textMessage = new TextMessage();
            textMessage.setChannel(channel);
            textMessage.setText(message);
            this.daoTextMessage.create(textMessage);
        }
        this.sendToAll(message, token);
    }

    @OnClose
    public void onClose(final Session session, @PathParam("talkHash") final String talkHash) {
        System.out.println("onClose");
        final List<Session> talkSessions = this.sessions.get(talkHash);
        if (talkSessions != null) {
            talkSessions.remove(session);
        }
    }

    /**
     * Sends a message to all connections of a channel identifying by a token
     * 
     * @param message The text message
     * @param token   The token of a channel
     */
    private void sendToAll(String message, final String token) {
        try {
            final List<Session> talkSessions = this.sessions.get(token);
            for (Session session : talkSessions) {
                session.getBasicRemote().sendText(message);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @OnError
    public void error(final Throwable t) {
        // . . .
    }
}