package $package$stream.impl;

import akka.Done;
import com.lightbend.lagom.javadsl.persistence.cassandra.CassandraSession;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Singleton
public class $name;format="Camel"$StreamRepository {
    private final CassandraSession uninitializedSession;

    // Will return the session when the Cassandra tables have been successfully created
    private volatile CompletableFuture<CassandraSession> initializedSession;

    @Inject
    public $name;format="Camel"$StreamRepository(CassandraSession uninitializedSession) {
        this.uninitializedSession = uninitializedSession;
        // Eagerly create the session
        session();
    }

    private CompletionStage<CassandraSession> session() {
        // If there's no initialized session, or if the initialized session future completed
        // with an exception, then reinitialize the session and attempt to create the tables
        if (initializedSession == null || initializedSession.isCompletedExceptionally()) {
            initializedSession = uninitializedSession.executeCreateTable(
                    "CREATE TABLE IF NOT EXISTS greeting_message (name text PRIMARY KEY, message text)"
            ).thenApply(done -> uninitializedSession).toCompletableFuture();
        }
        return initializedSession;
    }

    public CompletionStage<Done> updateMessage(String name, String message) {
        return session().thenCompose(session ->
                session.executeWrite("INSERT INTO greeting_message (name, message) VALUES (?, ?)",
                        name, message)
        );
    }

    public CompletionStage<Optional<String>> getMessage(String name) {
        return session().thenCompose(session ->
                session.selectOne("SELECT message FROM greeting_message WHERE name = ?", name)
        ).thenApply(maybeRow -> maybeRow.map(row -> row.getString("message")));
    }
}
