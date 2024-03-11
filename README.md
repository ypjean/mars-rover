Mars Rover

You are tasked to implement Mars rover as a program.

The Mars rover is controlled by sending it a stream of command and it will react according to the received commands in sequence. One command is represented by 1 character. Stream of command is presented by array of character.

Table of commands:

f – Move forward 1 coordinate in the current direction

b – Move backward 1 coordinate in the current direction

r – Rotate 90 degree clock-wise from current direction

l – Rotate 90 degree anti clock-wise from current direction

 

When the rover is sent from Earth and lands on Mars, assume that the initial coordinate position (X, Y) and direction (East/West/North/South) are known. Subsequently, a stream of command is sent to the rover and the rover moves according to the commands. Refer to the picture below.

Assume that the rover landed at coordinate (3, 4) and it is facing North; when we send a stream of command of ‘f’, ‘f’, ‘r’, ‘f’, ‘f’ then the final position is (5, 6) facing East.

Example of interaction with the command through parameters:

java –cp target com.test.MarsRoverApp “3,4,N” “f,f,r,f,f”

Output on the Console: 

Final Coordinate: 5, 6

Final Direction: East


Please implement the program according to object-oriented design and best practices.


Bonus

Extend your application with the following:

A command CLI to initialize multiple rovers (you can make your own assumptions and design your own CLI interface) with its own position and how to move a specific rover.
Rovers must not collide.
They will stop at the position before collision and all commands after that are cancelled.

Requirements:

Implement the solution as a Spring Boot Microservice (Java 8+ as backend services). The data shall be in-memory.
Implement the above use case considering object oriented principles and development best practices. The implementation should be a tested working executable.
