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
package orion.talk.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
public class User {

    /** primary key */
    @Id
    @GeneratedValue
    private long id;

    /** user name */
    private String name;

    /** user email */
    private String email;

    /** the channels that a user participates */
    @ManyToMany(mappedBy = "users")
    private Set<Channel> channels;

    /**
     * Adds a channel to a user
     * 
     * @param channel A channel
     */
    public void addChannel(Channel channel) {
        this.channels.add(channel);
    }

}