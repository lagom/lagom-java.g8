package $package$.impl;

import akka.Done;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import com.lightbend.lagom.javadsl.testkit.PersistentEntityTestDriver;
import com.lightbend.lagom.javadsl.testkit.PersistentEntityTestDriver.Outcome;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;

import $package$.impl.$name;format="Camel"$Command.Hello;
import $package$.impl.$name;format="Camel"$Command.UseGreetingMessage;
import $package$.impl.$name;format="Camel"$Event.GreetingMessageChanged;

import static org.junit.Assert.assertEquals;

public class $name;format="Camel"$EntityTest {
    private static ActorSystem system;

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create("$name;format="Camel"$EntityTest");
    }

    @AfterClass
    public static void teardown() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    @Test
    public void test$name;format="Camel"$Entity() {
        PersistentEntityTestDriver<$name;format="Camel"$Command, $name;format="Camel"$Event, $name;format="Camel"$State> driver = new PersistentEntityTestDriver<>(system,
                new $name;format="Camel"$Entity(), "world-1");

        Outcome<$name;format="Camel"$Event, $name;format="Camel"$State> outcome1 = driver.run(new Hello("Alice"));
        assertEquals("Hello, Alice!", outcome1.getReplies().get(0));
        assertEquals(Collections.emptyList(), outcome1.issues());

        Outcome<$name;format="Camel"$Event, $name;format="Camel"$State> outcome2 = driver.run(new UseGreetingMessage("Hi"),
                new Hello("Bob"));
        assertEquals(1, outcome2.events().size());
        assertEquals(new GreetingMessageChanged("world-1", "Hi"), outcome2.events().get(0));
        assertEquals("Hi", outcome2.state().message);
        assertEquals(Done.getInstance(), outcome2.getReplies().get(0));
        assertEquals("Hi, Bob!", outcome2.getReplies().get(1));
        assertEquals(2, outcome2.getReplies().size());
        assertEquals(Collections.emptyList(), outcome2.issues());
    }
}
