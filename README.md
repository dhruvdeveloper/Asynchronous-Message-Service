# Asynchronous-Message-Service

• Implemented an asynchronous message service consisting of a server process and three clients

• Each client process will connect to the server over a socket. The server is able to handle all three clients concurrently

• Clients will prompt the user for a username. When a client connects to the server, its username would be displayed by the server in real-time. Two or more clients would not use the same username simultaneously

• The server will keep a cumulative log of previously used usernames and display those names on its GUI

• When the server encounters a new username, a unique message queue would be created for that username. This queue has been able to contain an arbitrary number of messages. Queues would be persistent

• Clients can select 3 options for message communication. One to one, multicasting, and broadcasting

• Clients would have one check message button. Clients can check messages while online to the server or even after some times as messages would be stored in queues

• Technology and concepts: Java, JavaFX, Multithreading, asynchronous, queues.

-	Queue is implemented as a file. 

Steps to execute:  

❖	Open on IntelliJ IDE or other IDE

❖	Set up JDK 11

❖	Run server: Go to the server folder, find server.java file and run it

❖	It will open server UI

❖	Click on connect to start the server

❖	Client: Open 3 of the client folder on 3 different IntelliJ windows one after one and enter the username and produce the output as describe in requirements. 

❖	Find Main.java file in each client’s windows and run it. 

❖	Enter your previously used or new username 

❖	Select your type of communication 

❖	Select user

❖	Send the Message

❖	Check Message: To check the messages, click on the check message button. It will retrieve you the messages sent to you while you are online or offline. 

References:
https://www.geeksforgeeks.org/socket-programming-in-java/

https://way2java.com/networking/chat-program-two-way-communication/

https://www.callicoder.com/javafx-registration-form-gui-tutorial/

https://www.geeksforgeeks.org/multi-threaded-chat-application-set-1/

https://www.javatpoint.com/javafx-checkbox

https://www.javatpoint.com/how-to-create-a-file-in-java

Bugs: 
-	Client need to connect to the server again to get updated client list from server. Because it is getting data of all connected and offline client while it connect to the server. 

