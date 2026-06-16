# Gamboa JEE Parent

`org.gamboa-project:gamboa-jee-parent` &mdash; packaging: `pom`

The parent POM for the **Jakarta EE** flavor of Gamboa. It extends
[`gamboa-parent`](../gamboa-parent) with the dependencies a Jakarta EE web
application needs, and is the parent that generated JEE projects inherit from.

## What it provides

On top of the shared Gamboa stack, this module manages:

* **Jakarta EE 10 API** (`jakarta.platform:jakarta.jakartaee-api`, `provided`)
  &mdash; CDI, EJB, JPA, Bean Validation, Servlet, Mail, etc.
* **Apache Wicket CDI integration** (`wicket-cdi`) so Wicket components can be
  injected with CDI beans.
* **JDBC driver** for relational persistence (`com.mysql:mysql-connector-j`).

## Position in the reactor

```
gamboa-parent
└── gamboa-jee-parent       ← you are here
    └── (generated JEE project)
```

* **Parent:** [`gamboa-parent`](../gamboa-parent).
* **Used by:** [`gamboa-jee-archetype`](../gamboa-jee-archetype) &mdash; the POM
  of every generated JEE project declares `gamboa-jee-parent` as its parent.

## Notes on modernization

* Migrated from Java EE 6 (`javax.*`) to **Jakarta EE 10 (`jakarta.*`)**.
* The legacy embedded-GlassFish plugin was removed (no Jakarta-compatible
  successor). Generated projects now build a plain WAR you deploy to a
  Jakarta EE 10 server such as **GlassFish 7+, Payara 6, or WildFly 30+**.

## Build

Built as part of the reactor from the repository root:

```bash
mvn install
```
