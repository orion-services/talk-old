package orion.talk;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.Duration;

import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.microshed.testing.jaxrs.RESTClient;
import org.microshed.testing.jupiter.MicroShedTest;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.containers.wait.strategy.WaitStrategy;
import org.testcontainers.junit.jupiter.Container;

import orion.talk.secure.ProtectedService;

@MicroShedTest
public class TalkServiceIT {

    @Container
    public static ApplicationContainer talk = new ApplicationContainer().withAppContextRoot("/orion-talk-service")
            .withExposedPorts(9081).waitingFor(Wait.forHttp("/"));

    @RESTClient
    public static ProtectedService client;

    @Test
    public void createChannelTest() {
        // Channel channel = talk.createChannel();
        assertTrue(true);

    }

}