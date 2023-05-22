Robot Client
​
This is a Java client application for interacting with a server that controls a robot. The client allows users to send commands to the server and receive responses.
Requirements
​
    Java Development Kit (JDK) 8 or above
    Gson library (included in the project)
​
Usage
​
    Clone the repository or download the source code.
    git@gitlab.wethinkco.de:enisaac022/robotworlds_cpt19.git
    Open the project in your preferred Java development environment.
    Build the project to compile the Java classes.
​
Running the Server
​
    Locate the Play class in the za.co.wethinkcode.server package.
    Run the Play class to start the server. The server will listen on the specified port (default is 8000).
​
Running the Client
​
    Locate the runClient class in the za.co.wethinkcode.client package.
    Run the runClient class to start the client application.
    The client will prompt you to enter the server IP address and port number. Provide the required information to establish a connection with the server.
​
Once connected, you can send commands to the server by typing them in the console. The commands include:
​
    look: Retrieve information about the robot's state and surroundings.
    repair: Repair the robot.
    fire: Fire a shot.
    reload: Reload the robot's ammunition.
    turn <direction>: Turn the robot in the specified direction (left or right).
    launch <kind>: Launch a new robot of the specified kind (sniper, basic, tank).
    Any other command: The client will send the command as-is to the server.
​
To quit the client application, simply type quit in the console.
Contributors
​
    Enrique Isaacs
    Duncan Van Heerden
    Lungisile Mgijima
    Onesmus Maenzanise
​
License
​
This project is licensed under the MIT License.
Acknowledgements
​
This project uses the Gson library for JSON serialization and deserialization. More information about Gson can be found here.
Contributing
​
Contributions to this project are welcome. Feel free to submit bug reports, feature requests, or pull requests.
​
Feel free to customize the README file according to your project's specific details and requirements.
Coll
