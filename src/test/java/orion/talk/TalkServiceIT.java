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

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({ DockerCompose.class })
public class TalkServiceIT {

    @Test
    public void foo() {

        String host = DockerCompose.talk.getContainerIpAddress();
        Integer port = DockerCompose.talk.getFirstMappedPort();

        System.out.println(">>>>>>>>>>>>>>>>>>" + host);
        System.out.println(">>>>>>>>>>>>>>>>>>" + port);

        CloseableHttpResponse response;
        try {

            String url = "http://"+ host + ":" + port + "/orion-talk-service/talk/api/v1/createChannel";

            System.out.println(">>>>>>>>>>>>>>>>>>" + url);

            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet get = new HttpGet(url);
            response = client.execute(get);
            System.out.println(response.toString());
            assertEquals(response.getStatusLine().getStatusCode(), 200);
            client.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}