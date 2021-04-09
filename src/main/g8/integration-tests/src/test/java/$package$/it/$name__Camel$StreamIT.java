package $package$.it;

import akka.actor.ActorSystem;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import com.lightbend.lagom.javadsl.client.integration.LagomClientFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import $package$stream.api.$name;format="Camel"$StreamService;
import $package$.api.GreetingMessage;
import $package$.api.$name;format="Camel"$Service;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class $name;format="Camel"$StreamIT {

    private static final String SERVICE_LOCATOR_URI = "http://localhost:9008";

    private static LagomClientFactory clientFactory;
    private static $name;format="Camel"$Service $name;format="camel"$Service;
    private static $name;format="Camel"$StreamService $name;format="camel"$streamService;
    private static ActorSystem system;

    @BeforeClass
    public static void setup() {
        clientFactory = LagomClientFactory.create("integration-test", $name;format="Camel"$StreamIT.class.getClassLoader());
        // One of the clients can use the service locator, the other can use the service gateway, to test them both.
        $name;format="camel"$Service = clientFactory.createDevClient($name;format="Camel"$Service.class, URI.create(SERVICE_LOCATOR_URI));
        $name;format="camel"$streamService = clientFactory.createDevClient($name;format="Camel"$StreamService.class, URI.create(SERVICE_LOCATOR_URI));

        system = ActorSystem.create();
    }

    @Test
    public void helloWorld() throws Exception {
        String answer = await($name;format="camel"$Service.hello("foo").invoke());
        assertEquals("Hello, foo!", answer);
        await($name;format="camel"$Service.useGreeting("bar").invoke(new GreetingMessage("Hi")));
        String answer2 = await($name;format="camel"$Service.hello("bar").invoke());
        assertEquals("Hi, bar!", answer2);
    }

    @Test
    public void helloStream() throws Exception {
        // Important to concat our source with a maybe, this ensures the connection doesn't get closed once we've
        // finished feeding our elements in, and then also to take 3 from the response stream, this ensures our
        // connection does get closed once we've received the 3 elements.
        Source<String, ?> response = await($name;format="camel"$streamService.directStream().invoke(
                Source.from(Arrays.asList("a", "b", "c"))
                        .concat(Source.maybe())));
        List<String> messages = await(response.take(3).runWith(Sink.seq(), system));
        assertEquals(Arrays.asList("Hello, a!", "Hello, b!", "Hello, c!"), messages);
    }

    private <T> T await(CompletionStage<T> future) throws Exception {
        return future.toCompletableFuture().get(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void tearDown() {
        if (clientFactory != null) {
            clientFactory.close();
        }
        if (system != null) {
            system.terminate();
        }
    }
}
