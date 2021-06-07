      ____                 _                      ____            _           _   
     / ___| __ _ _ __ ___ | |__   ___   __ _     |  _ \ _ __ ___ (_) ___  ___| |_ 
    | |  _ / _` | '_ ` _ \| '_ \ / _ \ / _` |    | |_) | '__/ _ \| |/ _ \/ __| __|
    | |_| | (_| | | | | | | |_) | (_) | (_| |    |  __/| | | (_) | |  __/ (__| |_ 
     \____|\__,_|_| |_| |_|_.__/ \___/ \__,_|    |_|   |_|  \___// |\___|\___|\__|
                                                               |__/               
What is it?
=======
It is a project structure for rapid web application development based on Scala, Apache Wicket, and your flavor of base framework: Java EE with JPA or Spring with MongoDB. It also comes with Velocity for email templates processing.

It has a clean, tuned directory structure that fits the common scenario where designers and developers work together to build a web application, but want clear separation of design/layout and programming code.

How to create a new project?
-------
Gamboa is based on Apache Maven and has archetypes. Previous knowledge on Maven is required.
To start your project, follow these steps:

1. Install Apache Maven 3.0 (go to http://maven.apache.org)
2. Clone the Gamboa project locally using git

    $ git clone git@github.com:brunoborges/gamboa-project.git
3. Go to the 'gamboa-project' folder and install the Maven artifacts

    $ mvn install
4. Go to your workspace, choose your archetype (gamboa-jee-archetype or gamboa-mongodb-archetype) and create your project

    $ cd ~/workspace

    $ mvn archetype:generate -DarchetypeGroupId=org.gamboa-project -DarchetypeArtifactId=gamboa-jee-archetype -DarchetypeVersion=1.0-SNAPSHOT -DgroupId=com.mycompany -DartifactId=myproject -DinteractiveMode=false

5. Run it (it will download and start GlassFish Embedded)

    $ cd myproject

    $ mvn install

6. Now fire your browser at http://localhost:8080

7. To have access, just fill the login form with some valid email and a password,  remember it. Create an account and then login.

Project structure
-------
Here is the project structure Gamboa suggests for rapid development:

    myproject $> tree
    .
    |-- src                  --> .scala files
    |   |-- code
    |       |-- data         --> your JPA / POJO entities for JEE or MongoDB
    |       |-- services     --> your EJB3 or Spring services
    |       |  `-- email     --> your email template processing classes
    |       `-- webapp       --> your base classes for the Web application
    |           `-- pages    --> your Wicket pages using the Scala DSL
    |
    |-- config               --> configuration files (.xml and some .properties)
    |   `-- templates        --> Velocity templates for email delivery
    |
    |-- layout               --> .html files and presentation (.css, .js, images)
    |   |-- css              
    |   `-- js               --> your custom JS scripts
    |       `-- vendor       --> js libs like jQuery, modernizr and respond
    |
    |-- maven-eclipse.xml    
    |-- pom.xml

### Project structure dismistified
Gamboa does not follow Maven's convention because it believes the convention is not suited for rapid web application development. Having to define a Java package and all those folders under src/ is not straightforward for developers who don't know Maven. Even for those who know, it still is not something simple. Gamboa suggests a different approach, taking advantage of Maven's build process and Scala capabilities. 

All HTML files must go into the `layout/` folder. For each HTML page, there must be a Scala class under `code/pages`. This is the Wicket convention. Other Web resources (CSS, JavaScript and images) will go to the context root of the web application. This way, the prototype will be functional and during runtime, everything will work as expected, thanks to Wicket. There's no need to programmatically reference resources.

Logical services are EJB3 or Spring-based POJOs and should go to `code/services` as this is the convention for Annotations scan defined in the `applicationContext.xml` if you are using the Spring-based archetype. If you want to change this, modify that XML as you wish.

The `code/services/email` package has some useful classes for sending emails. Templates should go to `config/templates` and can be Velocity templates in HTML. All configuration like SMTP properties are located in the `emailContext.xml` for Spring-based. The JEE archetype requires a JNDI name for the MailSession resource.

#### Want a clean structure?
If you want a clean project structure, without any code, just create a project based on `gamboa-jee-archetype` and delete everything from `code/webapp/pages` and `layout`. Then drop your functional prototype to `layout` and start coding from scratch. Remember to follow Wicket's convention (one WebPage/Panel/Border class for each HTML file).

IDE Support
-------
Projects created with Gamboa Archetype already have Eclipse files configured. They were generated for Eclipse Helios 3.6. Also, it is better to have the Scala IDE installed to have better support at editing and hot deployment during development. Another great tool is the Aptana Studio for HTML, CSS and JavaScript edition.

### Scala IDE - Update site (for Scala 2.9.z)

     http://download.scala-ide.org/releases-29/stable/site

Debugging
-------
It is possible to debug by simply running the debug.sh script that will start the JVM with a debug server at port 9009. Then on Eclipse, launch a Remote Application debug agent and attach to that port.

Frameworks and Technologies
-------
Here's the list of all Gamboa integrated frameworks and technologies:

1. Server-side libraries

* Scala 2.9.2
* Apache Wicket 6.0
* Velocity 1.7
* JUnit 4.10
* SLF4J 1.7.1
* Logback

2. Client-side libraries

* jQuery 1.6.1
* Modernizr 1.7
* HTML5 Initializr (http://initializr.com/)

Roadmap
-------
The Gamboa project aims to be not only a Maven archetype, but also a solid quickstart for Java and Scala developers who want to quickly build web applications that already have a functional prototype, following the top-down design strategy.

Contributing
-------
As any other GitHub project: fork it and contribute. If you want to share your changes, request a pull.
