```
   ____                _
  / ___| __ _ _ __ ___ | |__   ___   __ _
 | |  _ / _` | '_ ` _ \| '_ \ / _ \ / _` |
 | |_| | (_| | | | | | | |_) | (_) | (_| |
  \____|\__,_|_| |_| |_|_.__/ \___/ \__,_|   ** for Spring / MongoDB **
```

# Gamboa MongoDB Archetype

`org.gamboa-project:gamboa-mongodb-archetype` &mdash; a Maven **archetype** that
generates a ready-to-run **Scala + Apache Wicket** web application backed by
**Spring 6** and **MongoDB**.

## What you get

A generated project comes wired up with:

* **Scala 2.13** sources compiled by `scala-maven-plugin`.
* **Apache Wicket 10** UI, using **wicketstuff-scala** for an idiomatic Scala
  component DSL and **Wicket Spring** for dependency injection.
* **Spring Framework 6** and **Spring Security 6**.
* **MongoDB** access through the official **mongo-scala-driver**.
* **Bootstrap 5.3** for the client-side layout (vendored locally, no CDN).
* **Jakarta Mail (Angus)** for email and **Logback** for logging.

It inherits dependency management from
[`gamboa-spring-parent`](../gamboa-spring-parent).

## Generate a project

Install the archetypes locally first (from the repository root):

```bash
mvn install
```

Then generate a project from the archetype:

```bash
mvn archetype:generate \
  -DarchetypeGroupId=org.gamboa-project \
  -DarchetypeArtifactId=gamboa-mongodb-archetype \
  -DarchetypeVersion=1.0-SNAPSHOT \
  -DgroupId=com.example \
  -DartifactId=my-mongo-app
```

## Run the generated project

Make sure a **MongoDB** instance is reachable (see the generated Spring config),
then start the app on the embedded **Jetty 12** server:

```bash
cd my-mongo-app
mvn jetty:run
```

The app is served at <http://localhost:8080/>. To produce a deployable WAR
instead, run `mvn package` and deploy to any Jakarta Servlet 6 container.

## Generated project layout

```
my-mongo-app/
├── pom.xml                 # parent: gamboa-spring-parent
├── config/
│   └── templates/          # Velocity email templates
└── src/code/
    ├── common/             # shared helpers
    ├── db/                 # MongoDB access (mongo-scala-driver)
    ├── services/
    │   └── email/          # mail service
    └── pages/              # Wicket pages (.scala + .html)
```

## Tooling

Develop with **IntelliJ IDEA** (Scala plugin) or **VS Code + Metals**. Any
editor works &mdash; the build is plain Maven.
