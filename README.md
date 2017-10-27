This project connects an android device to a raspberry pi through ssh and sends commands to activate/deactivate a light. For a full tutorial go to this link: 


There are four files contained, aside from the readme. 

Led.py - Quick python file that turns ON the lights via gpios pins on the raspberry pi. 

Led2.py - Quick python file that turns OFF the lights via gpios pins on the raspberry pi.

MainActivity.java - Java file that listens for a button click/toggle and connects to the raspberry pi. It then executes one of the above python files depending on the toggle status. 

jsch-0.1.54.jar - This is the latest jsch library used for ssh connections in java. Link for that library is here: http://www.jcraft.com/jsch/





