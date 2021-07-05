

<h3 align="center">WirDelta Backend Solution (BE+FE)</h3>

  <p align="center">
     Backend Position Sample Project
    <br/>
  </p>




<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Run</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

The Project is consist of both of backend and frontend. Basic functionalities  of project are 

* Allow Login to  user who has Admin role and restrict others users to access admin panel. 
* List down the Job proposals and allow the admin user to accept and reject the job proposals.
* Actions should be token based and token must have expiry.
* For demo purpose project is using in memory database and data seeder encapsulated inside project.
* After Login a JWT token issued for the user which has expiry time and is required for further API Call.
* Application Dashboard can be access by only admin users.



### Built With
Following frameworks and ORMS and database has been used for the project.
* [Java 11]()
* [Spring-boot (Back-End Framework)]()
* [Spring-data-jpa (ORM)]()
* [H2-database (In Memeory Database)]()
* [Angular Js(Front-End Framework)]()
* [Gradle (Build Tool)]()


<!-- GETTING STARTED -->
## Getting Started

project is build using spring-boot framework for backend and AngularJS for frontend. In order 
to build the project following prerequisites required.

### Prerequisites

* Java SDK 11
* NodeJs 
* npm
* gradle (optional)


### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/leo4amit/WireDelta-Backend
   ```

### Run

1. In main project directory enter following command
   ```sh
   ./gradlew bootRun
   ```
2. Open Browser with 
   ```JS
   http://localhost:8080/
   ```

3. Admin User Details
   ```JS
   username : amit
   password : amit123
   ```

4. Normal User Details (Restricted)
   ```JS
   username : Taniya
   password : Taniya123
   ```


<!-- CONTACT -->
## Contact

Your Name - [amit sharma]() - leo4amit@gmail.com

