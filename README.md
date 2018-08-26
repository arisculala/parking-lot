The problem solution is written in Java and maven to compile the project into a set of executable jar file
	Application Build Requirements
		OSX or Linux machine
		Latest version of Java JDK
		Latest version of maven


## Setup and Build
	```
	$ cd parking_lot
	$ cd parking-lot #this is the solution directory
	```

	Run the following command to build the project using maven
	(you can add option -DskipTests on the command line below if you do not want maven to run the existing junit test)
	The command below will generate the `target` directory, build classes and the jar file parking-lot/target/parking-lot-0.0.1-SNAPSHOT.jar
	On the command line maven will also show you the result of running the existing junit test and if the build is success or failed.
	```
	parking-lot $ mvn clean install
	```


## Usage
	After doing the project build using mvn clean install you can now launch the application following the instructions below:

	You can run below command to launch the interactive parking lot application in command line
	```
	parking-lot $ java -jar parking-lot/target/parking-lot-0.0.1-SNAPSHOT.jar
	```

	You can run below command to launch parking lot application and read the command in a given text file
	```
	parking-lot $ java -jar parking-lot/target/parking-lot-0.0.1-SNAPSHOT.jar file_input.txt
	```

	You can also run the parking lot application using the configured bash script
	```
	parking-lot $ cd ..
	parking_lot $ bin/setup #to make sure before running the application that we build the project using maven
	```
	To run the interactive command line
	```
	parking_lot $ bin/parking_lot
	```
	To run the application to accept file input
	```
	parking_lot $ bin/parking_lot file_input.txt
	```


## These are common Parking Lot System commands used in various situations:
create_parking_lot <total number of parking slots to be created>
	- This will initialize/reinitialize the total number of parking slot spaces available

park <registration number> <car colour>
	- Assigned the specified car(registration number, colour) to the nearest available slot near the entry point

leave <parking slot number>
	- Remove the car details from the specified parking slot number

status
	- Display the status of all parking slot

registration_numbers_for_cars_with_colour <car colour>
	- Get list of car's registration number matching the parameter car colour

slot_numbers_for_cars_with_colour <car colour>
	- Get list of slot numbers matching the parameter car colour

slot_number_for_registration_number <registration number>
	- Get the slot number for the given parameter car registration number

parking_lot_help
	- Show list of available parking lot command

exit
	- Exit the application

