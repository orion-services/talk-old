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

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * Represents a space of a talk among users
 */
@Data
@Entity
public class Channel {

    /** primary key */
    @Id
    @GeneratedValue
    private long id;

    /** indicates when the channel was created */
    private Timestamp creationDate;

    /** the token used to represent the channel */
    private String token;

    /** the text messages of a channel */
    @OneToMany(mappedBy = "channel", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<TextMessage> messages;

    // ** constructor */
    public Channel() {
        // generates the tocen
        this.messages = new HashSet<TextMessage>();
        this.token = this.generateToken();

        // store when the channel was created
        Calendar calendar = Calendar.getInstance();
        this.creationDate = new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * Generates a token to represent a channel
     */
    public String generateToken() {
        return UUID.randomUUID().toString();
    }
}