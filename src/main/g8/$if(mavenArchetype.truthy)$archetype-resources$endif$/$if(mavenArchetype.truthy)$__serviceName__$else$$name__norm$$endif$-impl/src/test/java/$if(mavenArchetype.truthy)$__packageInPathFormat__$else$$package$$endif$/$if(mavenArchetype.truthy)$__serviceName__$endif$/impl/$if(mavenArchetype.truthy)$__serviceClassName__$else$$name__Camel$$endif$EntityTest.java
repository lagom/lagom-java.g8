package $if(mavenArchetype.truthy)$\${package}.\${serviceName}$else$$package$$endif$.impl;

import akka.Done;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import com.lightbend.lagom.javadsl.testkit.PersistentEntityTestDriver;
import com.lightbend.lagom.javadsl.testkit.PersistentEntityTestDriver.Outcome;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;

import $if(mavenArchetype.truthy)$\${package}.\${serviceName}$else$$package$$endif$.impl.$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Command.Hello;
import $if(mavenArchetype.truthy)$\${package}.\${serviceName}$else$$package$$endif$.impl.$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Command.UseGreetingMessage;
import $if(mavenArchetype.truthy)$\${package}.\${serviceName}$else$$package$$endif$.impl.$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Event.GreetingMessageChanged;

import static org.junit.Assert.assertEquals;

public class $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$EntityTest {
    private static ActorSystem system;

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create("$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$EntityTest");
    }

    @AfterClass
    public static void teardown() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    @Test
    public void test$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Entity() {
        PersistentEntityTestDriver<$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Command, $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Event, $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$State> driver = new PersistentEntityTestDriver<>(system,
                new $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Entity(), "world-1");

        Outcome<$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Event, $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$State> outcome1 = driver.run(new Hello("Alice"));
        assertEquals("Hello, Alice!", outcome1.getReplies().get(0));
        assertEquals(Collections.emptyList(), outcome1.issues());

        Outcome<$if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$Event, $if(mavenArchetype.truthy)$\${serviceClassName}$else$$name;format="Camel"$$endif$State> outcome2 = driver.run(new UseGreetingMessage("Hi"),
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
