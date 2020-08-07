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
package orion.talk;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.microshed.testing.SharedContainerConfig;
import org.microshed.testing.jaxrs.RESTClient;
import org.microshed.testing.jupiter.MicroShedTest;
import org.testcontainers.containers.MySQLContainer;

import orion.talk.model.Channel;
import orion.talk.service.PublicService;

@MicroShedTest
@SharedContainerConfig(AppContainerConfig.class)
public class TalkServiceIT {

    @RESTClient
    public static PublicService client;

    @Test
    public void createChannelTest() {
        try (MySQLContainer<?> mysql = new MySQLContainer<>("mysql:latest").withDatabaseName("orion-talk-service")
                .withUsername("orion-talk-service").withPassword("orion-talk-service")
                .withNetworkAliases("talk_default").withExposedPorts(3307)) {
            mysql.start();

            Channel channel = client.createChannel();
            assertNotNull(channel);
        }

    }

    @Test
    public void foo() {
        assertTrue(true);
    }

}