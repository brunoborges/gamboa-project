      ____                 _                      ____            _           _   
     / ___| __ _ _ __ ___ | |__   ___   __ _     |  _ \ _ __ ___ (_) ___  ___| |_ 
    | |  _ / _` | '_ ` _ \| '_ \ / _ \ / _` |    | |_) | '__/ _ \| |/ _ \/ __| __|
    | |_| | (_| | | | | | | |_) | (_) | (_| |    |  __/| | | (_) | |  __/ (__| |_ 
     \____|\__,_|_| |_| |_|_.__/ \___/ \__,_|    |_|   |_|  \___// |\___|\___|\__|
                                                               |__/               
What is it?
=======
Is a project structure for rapid web application development based on Scala, Apache Wicket, Spring Framework and MongoDB. It also comes with Velocity for email templates processing.

It has a clean directory structure that fits the common scenario where designers and developers work together to build a web application, but want clear separation of design/layout and programming code.

How to create a project?
-------
Gamboa is based on Apache Maven and has an Archetype. Previous knowledge on Maven is required. To start your project, follow these steps:

1. Install Apache Maven 3.0
2. Install MongoDB
3. Clone the Gamboa Project repository locally

    $ git clone git@github.com:brunoborges/gamboa-project.git
3. Go to the gamboa-archetype folder and install it

    $ mvn install
4. Create your project

    $ mvn archetype:generate -DarchetypeGroupId=org.gamboa-project -DarchetypeArtifactId=gamboa-archetype -DarchetypeVersion=1.0-SNAPSHOT -DgroupId=com.mycompany -DartifactId=myproject -DinteractiveMode=false

5. Run it

    $ cd myproject
    $ mvn jetty:run

Project structure
-------
Here is the project structure Gamboa suggests for rapid development:

    myproject $> tree
    .
    |-- code
    |   |-- db
    |   |-- pages
    |   `-- services
    |       `-- email
    |-- config
    |   `-- templates
    |-- layout
    |   |-- apple-touch
    |   |-- css
    |   `-- js
    |       `-- libs
    |-- maven-eclipse.xml
    |-- pom.xml

IDE Support
-------
Projects created with Gamboa Archetype already have Eclipse files configured. They were generated for Eclipse Helios 3.6. Also, it is better to have the Scala IDE installed to have better support at editing and hot deployment during development. Another great tool is the Aptana Studio for HTML, CSS and JavaScript edition.

### Scala IDE - Update site (2.9 trunk)

     http://download.scala-ide.org/nightly-update-wip-experiment-trunk

### Aptana Studio - Update site (3.0.1)

     http://download.aptana.com/studio3/plugin/install

Debugging
-------
It is possible to debug by simply running the Jetty plugin with Xdebug options through the MAVEN_OPTS environment variable. Then on Eclipse, launch a Remote Application debug agent.

Follow these instructions documented at the JETTY Wiki

     http://docs.codehaus.org/display/JETTY/Debugging+with+the+Maven+Jetty+Plugin+inside+Eclipse

Frameworks and Technologies
-------
Here's the list of all Gamboa integrated frameworks and technologies:

* Scala 2.9.0-1
* Apache Wicket 1.5.0-RC4.2
* MongoDB driver 2.6.1
* Casbah 2.1.5.0
* Velocity 1.7
* Spring 3.0.5
* JUnit 4.8.1
* Log4J 1.2.16
* SLF4J 1.6.1
* Jetty Plugin 8.0.0.M3

Roadmap
-------
The Gamboa project aims to be not only a Maven archetype, but also a solid base for developers who want to quickly build web applications that already

Contributing
-------
As any other GitHub project: fork it and contribute. If you want to share your changes, request a pull.
