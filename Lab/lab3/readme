                                 CS 61B Lab 3
                              February 6-7, 2014

Goal:  This lab will give you practice with linked lists.

Copy the Lab 3 directory by starting from your home directory and typing:
  cp -r ~cs61b/lab/lab3 .
  cd lab3

Getting Started
---------------
Please make sure you have a partner for this lab.

The files in the directory lab3/ contain the classes SList and SListNode, which
implement a singly-linked list.  Compile SList.java with "javac -g SList.java".
Run the test code with

    java SList

The main() method of SList includes test code, which can be used to help debug
the list code before SLists are used in other programs.

Read SList.java to find out what methods are available to help you modify
SLists.  Items in our SLists are indexed starting from 1, unlike Java arrays.

Part I:  Using SLists (1 point)
-------------------------------
In the main() method, construct a list that looks like:
    [ 6 9 12 ] 
and print the resulting list.

Add more lines to change this list to:
    [ 3 6 9 12 15 ] 
and print the resulting list.

Part II:  Adding to the End of a SList (3 points)
--------------------------------------------------
A method called insertEnd() exists, but it runs in linear time, because every
time it is called, it walks down the list to find the end.  Without changing
the meaning of this method or any other, modify the representation of a SList
and whatever methods are necessary to make insertEnd() run in constant time.
Your SList class will need to continually maintain a record of the last (tail)
SListNode in an SList, and all SList's methods will have to ensure that this
record stays current.

Check-off
---------
Show your TA or Lab Assistant your main() and insertEnd() methods and run the
program.

1 point:   Show your main() method, and show that it is printing the proper
           output for Part I.
3 points:  Show your insertEnd() method, and explain how you got it to work in
           constant time.  Show that your program still prints the right
           output.  Which other methods had to be modified?
