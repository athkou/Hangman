Thank you for using Hangman. The README file contains information about the project.

======== CONTENTS ========
#1.INTRO                 #
#2.SYSTEM REQUIREMENTS   #
#3.HOW TO RUN THE GAME   #
#4.HOW TO PLAY           #
==========================

###############1. INTRO ###############
Hangman is implemented in Java and the first version will be a console application. In the near and distant future I am
planning to do a GUI version and an android application.

###############2. SYSTEM REQUIREMENTS ###############
The hangman project was implemented using Java 7+. It must be installed on your computer to compile and run the game.

###############4. HOW TO RUN THE GAME ###############
You compile and run the program using the commands:
javac Hangman.java && java Hangman

###############4. HOW TO PLAY ###############
Every time you run the program you are supposed to either guess the entire word or a letter one by one.
Only the letters [a-zA-Z] are valid. Non-word letters and digits are not allowed. You have 7 tries and with a wrong guess
your tries are decreased by one. The game stops i the user was successful or if he doesn't have any remaining tries left.
Either way the game ends with an appropriate message.