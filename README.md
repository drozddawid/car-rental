# Car Rental
Simple CRUD database application for managing cars in car rental company. The application was developed during the course "Databases 2" conducted at Wrocław University of Science and Technology.

# Repository structure
The application is built in a three-tier architecture, so we created separate branches for every tier of the application:
 * [`database`](../../tree/database) - data tier,
 * [`car-rental-server`](../../tree/car-rental-server) - application tier,
 * [`car-rental-app`](../../tree/car-rental.app) - presentation tier.
You can also find [documentation](documentation.pdf) in branch main.

# Prerequisites
To build and run this application, you are going to need:
* MySQL server,
* JDK 17,
* Gradle,
* Node.js with serve

# Things you need to do before first start of the applcation
## Install MySQL server and initialize database
Download [MySQL](https://dev.mysql.com/downloads/mysql/)

1. Extract downloaded zip to `C:/mysql`

2. Add `C:/mysql/bin` to PATH environment variable

3. Initialize data directory.
* create C:/mysql/data directory
* create C:/mysql/my.ini file and write the following:

<pre>
[mysqld]
basedir=C:/mysql
datadir=C:/mysql/data
</pre>

* run `mysqld --defaults-file=C:/mysql/my.ini --initialize --console`<br>

You will get an output like this:
<pre>2022-05-21T10:05:22.172958Z 6 [Note] [MY-010454] [Server] A temporary password is generated for root@localhost: nd_XlulsN1jP</pre>

Copy credentials and save it somewhere. <br>
In our case: <br>
<pre>
login: root@localhost
password: nd_XlulsN1jP
</pre>
4. Start the server by running `mysqld --console`

5. In another terminal connect to the server as root using `mysql -u root -p`<br>
You will be prompted for password, enter previously saved password.

6. Change root user password
After logging in using `mysql`, use statement below to change random generated password<br>
`ALTER USER 'root'@'localhost' IDENTIFIED BY 'new-password';`<br>

7. Initialize database by running script from file [`database/car_rental_script.sql`](../database/car_rental_script.sql)<br>
This script creates database and puts some example data in it. If you don't want to insert example data, use [`car_rental_script_no_example_data.sql`](../database/car_rental_script_no_example_data.sql)<br>
To run the script, enter:
`source <path_to_script_file>`<br>
or<br>
`\. <path_to_script_file>`
in the same terminal you used to change the account password.

8. From now, if you want to start MySQL server, run `mysqld --console`

## Install JDK 17 with Gradle and build the car-rental-server project
[JDK 17](https://www.oracle.com/java/technologies/downloads/#jdk17-windows)<br>
[Gradle](https://gradle.org/install/)
1. Set database server credentials set in point 6. of previous section. So as to do it, you need to modify [`src/main/resources/application.properties`](../car-rental-server/src/main/resources/application.properties) file. Set the username with `spring.datasource.username` and the password with `spring.datasource.password`. If you connect with your database remotely, you will also need to change `spring.datasource.url`.
<pre>
spring.datasource.url=jdbc:mysql://localhost:3306/car_rental
spring.datasource.username=root
spring.datasource.password=admin
</pre>
2. Build project<br>
run `gradle bootJar`<br>
This will build the project into executable jar `car-rental-0.0.1-SNAPSHOT.jar` which will be stored in `build/libs` directory.
3. Run the application server.<br>
run `java -jar car-rental-0.0.1-SNAPSHOT.jar`

## Install Node.js and build the car-rental-app project
You can download Node.js [HERE](https://nodejs.org/en/download/).<br>

1. Install serve after installing Node.js<br>
run `npm install -g serve`
2. Install dependencies.<br>
run `npm install` from car-rental-app project root directory<br>
This will download all project dependencies into `node_modules` directory.
3. Build project<br>
run `npm run build` from car-rental-app project root directory<br>
This will build the project into `build` directory.
4. From now, you can serve the application using:<br>
`serve -s build` from car-rental-app project root directory

# Running the application
1. `mysqld --console` - starts MySQL database server.
2. `java -jar build/libs/car-rental-0.0.1-SNAPSHOT.jar` from car-rental-server project root directory - starts appliaction server.
3. `serve -s build` from car-rental-app project root directory - serves the web application.

To log into the application, open [`localhost:3000/login`](http://localhost:3000/login) to log in as customer or [`localhost:3000/admin`](http://localhost:3000/admin) to log in as employee. Application creates default user with login `admin@example.com` and password `admin`. You can use these credentials to log into both panels. You can create new account using [`login`](http://localhost:3000/login) panel, but by default created user will have group id 0, which means that it's customer account and it has only access to [`login`](http://localhost:3000/login) panel. To give user access to [`admin`](http://localhost:3000/admin) panel, you need to do it manually in database:
1. Start database server<br>
`mysqld --console`
2. Connect to database<br>
`mysql -u root -p`
3. Set user group id.
* `USE car_rental;` - selects our application database,
* `SELECT * FROM UZYTKOWNICY;` - shows all the users registred,
* `UPDATE uzytkownicy SET user_group_id = <user group id> WHERE login = "<user login>"`;
  where :
  * `<user group id>` 0 means customer, and 1 means employee (priviledge mode).
  * `<user login>` is user login from row LOGIN.
  You can do for example:<br>
  * `UPDATE uzytkownicy SET user_group_id = 1 WHERE login = "admin@example.com";` - to give user `admin@example.com` access for both customer and employee panel,
  * `UPDATE uzytkownicy SET user_group_id = 0 WHERE login = "admin@example.com";` - to give user `admin@example.com` access only for customer panel.

# Authors
[Władysław Nowak](https://github.com/ULTUX)<br>
[Dawid Drozd](https://github.com/drozddawid)
