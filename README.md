# Web and Mobile application development
## Project Member : 

>*Dhayananth Dharmalingam*


## About the Project

This report provides information about developed mobile and web server application in order to manage heater and window IoT devices remotely. Different web technologies and mobile application development technologies were used in order to implement this project. 
This report feature explanation of what has been done in the project and how far the features are functional. 


# Application overview 
 This project consist of two main applications.  
  
> ## *SERVER-APPLICATION*
>
> found in `spring-rest` repository 
>   
>  This application is a Java Spring application. This application has different services and controllers to open different end points. Each controller service helps to manage particular entity : Ex : Building, Windows etc
>   
   
. The detail overview and the implementation of this server-application is described in the report.
  
> ## *MOBILE-APPLICATION*
>
> found in `android-app` repository 
>   
>  This application is an Android application which is developed using KOTLIN in order to act as an user interface. The Android app connects the backend. A user can able to perform certain functionalities with regards to building management.

## How to get started with the Project
 
 1. To run the program Java 11 or greater version must be installed in the computer
 2. Download the Application file from Git repositories (`android-app` & `spring-rest` repositories need to be downloaded) (Here is the link : [click-here](https://github.com/ujm-projects))

 3. Import the project to IntelliJ IDE as Gradle Project. (Here is the link to donwload IntelliJ : [click-here](https://www.jetbrains.com/idea/))
 4. Run the java application (./gradlew --continuous bootRun` )
 5. open the android application from Android Studio (Here is the link to donwload Android Studio : [click-here](https://developer.android.com/studio/))
 6. run the android app
 7. In the User interface there are Specific buttons  which allows to see what the application is performing. 

## Testing configuration 
The API application can be tested using INSOMNIA tool, that helps to test API Application. The configuration file will be given along with the project files, which you can able to simply import to your INSOMNIA tool, and instantly start testing the end points with just clicking the buttons. Because each end points are pre-configured with required input-args. 

* Step 1: import the ‘Insomnia_2021-01-13.json’ file to your insomnia workspace (right top corner)

* Step 2: select ‘remote’ in the environment tab. 

> You can download the tool here :[click-here](https://insomnia.rest/) 

> You can download the workspace configuration here :
[click-here](https://github.com/ujm-projects/spring-rest/blob/master/Insomnia_2021-01-13.json) 


> Atonally, you can test the application using SWAGGER API document in the following link. [click-here](https://rest-api.cleverapps.io/swagger-ui/index.html ) 

## Links to the repo
GitHub organization : https://github.com/ujm-projects 

Repository-Spring rest application : https://github.com/ujm-projects/spring-rest 

Repository-Android application : https://github.com/ujm-projects/android-app

<!-- #### NOTE!!!
> Please refer `report.pdf` for more information.  -->

## Stack
* Java Spring boot
* Java 11
* Android
* JPA

## Reference 

*Code reference* `[dev-mind]`: https://dev-mind.fr/formations.html

*Spring boot* : https://spring.io/projects/spring-boot


# SPRING SPECIFIC HELP

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.0/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.0/gradle-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/#using-boot-devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)



