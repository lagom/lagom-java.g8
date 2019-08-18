#!/usr/bin/env bash

rm -rf maven maven-hello
sbt new file://$(pwd) --mavenArchetype=yes --name=maven
cp src/main/pom.xml maven
mkdir -p maven/src/main/resources
sed -i'' -r 's|%LAGOM-VERSION%|1.6.0-M4|g' maven/archetype-resources/pom.xml
mv maven/archetype-resources/api-pom.xml maven/archetype-resources/__serviceName__-api/pom.xml
mv maven/archetype-resources/impl-pom.xml maven/archetype-resources/__serviceName__-impl/pom.xml
mv maven/archetype-resources/stream-api-pom.xml maven/archetype-resources/__serviceName__-stream-api/pom.xml
mv maven/archetype-resources/stream-impl-pom.xml maven/archetype-resources/__serviceName__-stream-impl/pom.xml
mv maven/archetype-resources maven/src/main/resources
mv maven/META-INF maven/src/main/resources
(cd maven && mvn install)
mvn archetype:generate -B -DarchetypeGroupId=com.lightbend.lagom -DarchetypeArtifactId=maven-archetype-lagom-java -DgroupId=com.example -DartifactId=maven-hello -DarchetypeVersion=1.0-SNAPSHOT
(cd maven-hello && mvn verify)
