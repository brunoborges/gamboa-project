      ____                 _                      ____            _           _   
     / ___| __ _ _ __ ___ | |__   ___   __ _     |  _ \ _ __ ___ (_) ___  ___| |_ 
    | |  _ / _` | '_ ` _ \| '_ \ / _ \ / _` |    | |_) | '__/ _ \| |/ _ \/ __| __|
    | |_| | (_| | | | | | | |_) | (_) | (_| |    |  __/| | | (_) | |  __/ (__| |_ 
     \____|\__,_|_| |_| |_|_.__/ \___/ \__,_|    |_|   |_|  \___// |\___|\___|\__|
                                                               |__/               
<<<<<<< HEAD
** for Java EE **

What is it?
=======
Is a project structure for rapid web application development based on Scala, Apache Wicket, Java EE through GlassFish and relational database. It also comes with Velocity for email templates processing.

It has a clean directory structure that fits the common scenario where designers and developers work together to build a web application, but want clear separation of design/layout and programming code.

How to create a new project?
-------
Gamboa is based on Apache Maven and has an Archetype. Previous knowledge on Maven is required. 
To start your project, follow these steps:

1. Install Apache Maven 3.0
2. Clone these 3 Gamboa projects locally using git

    $ git clone git@github.com:gamboa/gamboa-parent.git
    $ git clone git@github.com:gamboa/gamboa-dsl.git
    $ git clone git@github.com:gamboa/wicket-cdi.git
    $ git clone git@github.com:gamboa/jee6.git
3. Go to each folder (gamboa-parent, gamboa-dsl, wicket-cdi and jee6) and install the Maven artifacts

    $ mvn install
4. Create your project

    $ mvn archetype:generate -DarchetypeGroupId=org.gamboa-project -DarchetypeArtifactId=gamboa-jee6-archetype -DarchetypeVersion=1.0-SNAPSHOT -DgroupId=com.mycompany -DartifactId=myproject -DinteractiveMode=false

5. Run it (it will download and start GlassFish Embedded)

    $ cd myproject
    
    $ mvn install

Project structure
-------
Here is the project structure Gamboa suggests for rapid development:

    myproject $> tree
    .
    |-- src              --> .scala files
    |   |-- code
    |       |-- pages
    |           `-- services
    |           `-- email
    |-- config           --> configuration files (.xml and some .properties)
    |   `-- templates
    |-- layout           --> .html files and presentation (.css, .js, images)
    |   |-- apple-touch
    |   |-- css
    |   `-- js
    |       `-- libs
    |-- maven-eclipse.xml
    |-- pom.xml

### Project structure dismistified
Gamboa does not follow Maven's convention because it believes the convention is not suited for rapid web application development. Having to define a Java package and all those folders under src/ is not straightforward for developers who don't know Maven. Even for those who know, it still is not something simple. Gamboa suggests a different approach, taking advantage of Maven's build process and Scala capabilities. 

All HTML files must go into the `layout/` folder. For each HTML page, there must be a Scala class under `code/pages`. This is the Wicket convention. Other Web resources (CSS, JavaScript and images) will go to the context root of the web application. This way, the prototype will be functional and during runtime, everything will work as expected, thanks to Wicket. There's no need to programmatically reference resources.

Logical services are Spring-based POJOs and must go to `code/services` as this is the convention for Annotations scan defined in the `applicationContext.xml`. If you feel you must change this, modify that XML as you wish.

The `code/services/email` package has some useful classes to sending emails. Templates must go to `config/templates` and can be HTMLs. All configuration like SMTP properties are located in the `emailContext.xml`.

### Want a clean structure?
If you want a clean project structure, without any code, you have two options:

1. Just create a project based on `gamboa-archetype` and delete everything from `code/pages` and `layout`. Then drop your functional prototype to `layout` and start coding from scratch. Remember to follow Wicket's convention (one WebPage/Panel/Border class for each HTML file).

### This is just a skeleton
This is a skeleton for Web 2.0 projects that don't require transactional/relational databases. MongoDB is great and if you know how to use it, you can achieve the same requirements as if with relational databases like Oracle, MySQL or PostgreSQL.

IDE Support
-------
Projects created with Gamboa Archetype already have Eclipse files configured. They were generated for Eclipse Helios 3.6. Also, it is better to have the Scala IDE installed to have better support at editing and hot deployment during development. Another great tool is the Aptana Studio for HTML, CSS and JavaScript edition.

### Scala IDE - Update site (for Scala 2.9.z)

     http://download.scala-ide.org/releases-29/stable/site

### Aptana Studio - Update site (3.1.2)

     http://download.aptana.com/studio3/plugin/install

Debugging
-------
It is possible to debug by simply running the debug.sh script that will start the JVM with a debug server at port 9009. Then on Eclipse, launch a Remote Application debug agent and attach to that port.

Frameworks and Technologies
-------
Here's the list of all Gamboa integrated frameworks and technologies:

1. Server-side libraries

* Scala 2.9.2
* Apache Wicket 1.5.6
* Velocity 1.7
* JUnit 4.8.1
* Log4J 1.2.16
* SLF4J 1.6.1

2. Client-side libraries

* jQuery 1.6.1
* Modernizr 1.7
* HTML5 Initializr (http://initializr.com/)

Roadmap
-------
The Gamboa project aims to be not only a Maven archetype, but also a solid base for developers who want to quickly build web applications that already

Contributing
-------
As any other GitHub project: fork it and contribute. If you want to share your changes, request a pull.
=======
PROJECT MOVED
=============
This project has moved to http://gamboa-project.org (still at github).
>>>>>>> e83a36986f931e9467bbb33c1d0c9a2d182283f4
