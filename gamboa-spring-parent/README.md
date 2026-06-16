# Gamboa Spring Parent

`org.gamboa-project:gamboa-spring-parent` &mdash; packaging: `pom`

The parent POM for the **Spring + MongoDB** flavor of Gamboa. It extends
[`gamboa-parent`](../gamboa-parent) with the Spring stack and is the parent that
generated MongoDB projects inherit from.

## What it provides

On top of the shared Gamboa stack, this module manages:

* **Spring Framework 6** (context, web, `spring-context-support`) and
  **Spring Security 6**.
* **Apache Wicket Spring integration** (`wicket-spring`) for injecting Spring
  beans into Wicket components.
* **Jakarta Servlet API** (`provided`).
* **Email** via Jakarta Mail and the Angus Mail implementation
  (`org.eclipse.angus:angus-mail`).
* The **Jetty 12 (ee10) Maven plugin** for running generated projects locally.

## Position in the reactor

```
gamboa-parent
└── gamboa-spring-parent    ← you are here
    └── (generated Spring/MongoDB project)
```

* **Parent:** [`gamboa-parent`](../gamboa-parent).
* **Used by:** [`gamboa-mongodb-archetype`](../gamboa-mongodb-archetype) &mdash;
  the POM of every generated MongoDB project declares `gamboa-spring-parent` as
  its parent.

## Notes on modernization

* Upgraded from Spring 4 / Spring Security 3 to **Spring 6 / Spring Security 6**.
* Servlet container moved to **Jetty 12 (Jakarta `ee10`)**; run a generated
  project with `mvn jetty:run`.
* Mail moved from `javax.mail` to **Jakarta Mail (Angus)**.

## Build

Built as part of the reactor from the repository root:

```bash
mvn install
```
