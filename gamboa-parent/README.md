# Gamboa Parent

`org.gamboa-project:gamboa-parent` &mdash; packaging: `pom`

The shared parent POM for the whole Gamboa project. It centralizes the
dependency and plugin versions so every archetype stays on a single, consistent
technology stack. Bump a version here and all archetypes pick it up.

## What it provides

* **Dependency management** for the stack shared by all archetypes: Scala,
  Apache Wicket, wicketstuff-scala (the Scala DSL), Apache Velocity,
  SLF4J / Logback, and JUnit.
* **Plugin management** &mdash; most notably the
  [`scala-maven-plugin`](https://github.com/davidB/scala-maven-plugin) that
  compiles the Scala sources, plus the WAR plugin used by generated projects.
* **Build properties** defining the target toolchain (`maven.compiler.release`,
  Scala version, library versions, etc.).

## Key versions

| Component | Version |
| --- | --- |
| Java (`maven.compiler.release`) | 17 |
| Scala | 2.13.x |
| Apache Wicket | 10.5.x |
| wicketstuff-scala | 10.5.x |
| Velocity (`velocity-engine-core`) | 2.4.x |
| SLF4J / Logback | 2.0.x / 1.5.x |

See [`pom.xml`](pom.xml) for the authoritative values.

## Position in the reactor

```
gamboa (root)
└── gamboa-parent          ← you are here
    ├── gamboa-jee-parent
    └── gamboa-spring-parent
```

* **Parent:** `org.gamboa-project:gamboa` (the reactor root).
* **Inherited by:** [`gamboa-jee-parent`](../gamboa-jee-parent) and
  [`gamboa-spring-parent`](../gamboa-spring-parent).

## Build

Built as part of the reactor from the repository root:

```bash
mvn install
```
