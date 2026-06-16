```
   ____                _
  / ___| __ _ _ __ ___ | |__   ___   __ _
 | |  _ / _` | '_ ` _ \| '_ \ / _ \ / _` |
 | |_| | (_| | | | | | | |_) | (_) | (_| |
  \____|\__,_|_| |_| |_|_.__/ \___/ \__,_|   ** for Java EE **
```

# Gamboa JEE Archetype

`org.gamboa-project:gamboa-jee-archetype` &mdash; a Maven **archetype** that
generates a ready-to-run **Scala + Apache Wicket** web application targeting
**Jakarta EE 10**.

## What you get

A generated project comes wired up with:

* **Scala 2.13** sources compiled by `scala-maven-plugin`.
* **Apache Wicket 10** UI, using **wicketstuff-scala** for an idiomatic Scala
  component DSL and **Wicket CDI** for dependency injection.
* **Jakarta EE 10** &mdash; JPA persistence, CDI, Bean Validation, and Jakarta
  Mail, all under the `jakarta.*` namespace.
* **Bootstrap 5.3** for the client-side layout (vendored locally, no CDN).
* **Logback** via SLF4J for logging.

It inherits dependency management from
[`gamboa-jee-parent`](../gamboa-jee-parent).

## Generate a project

Install the archetypes locally first (from the repository root):

```bash
mvn install
```

Then generate a project from the archetype:

```bash
mvn archetype:generate \
  -DarchetypeGroupId=org.gamboa-project \
  -DarchetypeArtifactId=gamboa-jee-archetype \
  -DarchetypeVersion=1.0-SNAPSHOT \
  -DgroupId=com.example \
  -DartifactId=my-jee-app
```

## Build & deploy the generated project

```bash
cd my-jee-app
mvn package
```

This produces a WAR under `target/`. Deploy it to any **Jakarta EE 10**
application server, for example **GlassFish 7+**, **Payara 6**, or
**WildFly 30+**.

## Generated project layout

```
my-jee-app/
├── pom.xml                         # parent: gamboa-jee-parent
├── config/
│   ├── beans.xml                   # CDI
│   ├── META-INF/                   # persistence.xml (JPA)
│   └── templates/                  # Velocity email templates
└── src/code/
    ├── data/                       # JPA entities
    ├── services/
    │   └── email/                  # mail service + settings
    └── webapp/
        ├── initializers/           # Wicket application bootstrap
        └── pages/                  # Wicket pages (.scala + .html)
```

## Tooling

Develop with **IntelliJ IDEA** (Scala plugin) or **VS Code + Metals**. Any
editor works &mdash; the build is plain Maven.
