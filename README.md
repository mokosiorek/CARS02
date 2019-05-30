PROJECT CARS 02

This project was created with IntelliJ IDEA IDE. And can be run via this IDE.

To run the project manually with maven and java compiler follow these steps.

Steps:

Build the project using "mvn install" command - this should generate seperate jars from modules as result.
To combine them use command: "mvn clean compile assembly:single" in terminal in directory of pom.xml from menu module.
In target directory there should be a jar file generated which combines all modules' jars.
To run the generated jar use command: "java --enable-preview -cp <jar_file_name> kosiorek.michal.menu.App". The files cars02car1.json, cars02car2.json, cars02car3.json, cars02car4.json must be in the same directory as the generated jar.
