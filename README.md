# RobotWorlds_CPT19

## Project description

--
RobotWorlds is a Server/Client program written in java. Players can connect to a server which hosts a virtual world for their robots to join. The program allows clients to interact with one another by providing features such as launching different models of robots into the world, moving around, firing weapons and defending with shields. The server allows concurrent connection for multiple clients, using a multi-threaded approach, making sure each client gets their appropriate information.
--

## Features

--
1. Movement - Clients can send requests for movement in a 400x200 sized world (configurable), using commands such as 'forward 10'; 'back 5' or 'turn left'. The world also comes with pre-configured obstacles, which prevent players from moving past them.

2. Defend - Each robot comes configured with shields based on their type. For eg. The Sniper robot comes with 1 shield, but as a benefit it gains more shots than other robots. To defend your robot, you can make use of obstacles to hide behind or *repair* your shields. **Note** while repairing, the robot is unable to move.

3. Attack - Each robot comes configured with a specified number of shots, as well as a firing range. Use the *fire* command to shoot an enemy. You can restore your shots with the *reload* command. **Note** while reloading, the robot is unable to move.

4. Choose a model - Clients are able to choose different models of robots, each with their own pre-configured stats. **Note** This feature has not been fully implemented, so for now, only two robots can be chosen. To choose a specific model of robot, use the *launch* command. For eg. type > launch sniper hal. It will launch a robot of type sniper into the world, with the name hal.

5. List robots - On the Server side, you can run a command to list all robots currently in the world along with their state. Use command *robots*.

6. List server/world information - On the server side, you can use the *dump* command to print out all objects in the world.

7. Quit - Shuts down the server. Command = *quit*.

--

## Installation

--
To run and install this project, you can follow these instructions:

### Step 1
Copy this : **git@gitlab.wethinkco.de:enisaac022/robotworlds_cpt19.git**. 
In your terminal type, git clone <'paste-it-here'> and hit enter.

### Step 2
To run the server, first find the directory in which you downloaded the project.
Once in the project directory, type `cd robotworld/out/artifacts/`.
Now type, `java -jar robotworld.jar` and the server will start.


### Step 3
Run the client. Once again, find the project directory.
Once in the project directory, type `cd robotworld/src/main/java/za/co/wethinkcode/client`.
Now choose the `runClient.java` file and run it.
**Note** : the client program must be run in an IDE (Intergrated Development Environment).

After doing this, you will have a running server with a client that can connect to it.
--

## Configuration

--
You can configure world settings by finding the `world_configs.json` file.
In the project directory, navigate to the server/world package and open the config file.
**Note**: The configuration for the server has bugs, so the configs can't be changed at the moment.

For the client-side, you are able to run it with command-line arguments. For example, if using the IntelliJ IDEA IDE, once in the client file - choose run with parameters. A window will open, in which you can find a section saying `Program arguments`. In the text-box, insert the *PORT* number of the server as well as the *SERVER_IP_ADRESS*. Apply and run the program.

--

## Contributors

--
`duvanhe022@student.wethinkcode.co.za`
`enisaac022@student.wethinkcode.co.za`
`lumgiji022@student.wethinkcode.co.za`
`onmaenz022@student.wethinkcode.co.za`
--


*Thank you*
