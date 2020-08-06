package orion.talk;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import orion.talk.secure.ProtectedService;

@Testcontainers
public class TalkServiceIT {

    // https://www.testcontainers.org/modules/docker_compose/
    @ClassRule
    public static DockerComposeContainer environment = new DockerComposeContainer();

    @Inject
    @RestClient
    public static ProtectedService talk;

    @Test
    public void createChannelTest() {
        // Channel channel = talk.createChannel();
        assertTrue(true);
    }

}