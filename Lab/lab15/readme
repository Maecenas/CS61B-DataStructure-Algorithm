                                 CS 61B Lab 15
                                 May 8-9, 2014
                                 Sorting Games

Goal:  To help you practice sorting algorithms for the final exam.

You MUST have a partner for this lab, unless there's an odd number of people
and you are the odd one out.  You'll also need lots of paper.

Copy the Lab 15 directory by doing the following, starting from your home
directory.

  cp -r ~cs61b/lab/lab15 .

In this lab, you and your partner will help each other practice the in-place
array versions of heapsort, quicksort, and quickselect.  Then you will compete
against another team for the fastest sorting time.

Hour 1:
-------
The lab directory includes a program called GimmeYoNumber, which prints two
lists of 13 random numbers in the range 0...99.  Although the numbers for
"Opponent 1" and "Opponent 2" are different, the two lists have been scrambled
exactly the same way, and will require exactly the same sequence of swaps to
sort them.

For now, take just the first list of numbers and write them in a row on a sheet
of paper.  One of you should then sort the numbers with in-place heapsort as
described in the Lecture 30 notes, while your partner watches, catches your
mistakes, and helps if you're uncertain of the details of the algorithm.

To prepare for competition, you should diagram the sorting process in a
particular way:  each swap of two keys is written on a separate line, under the
previous swap.  For example, here's an illustration of selection sort.  (Lined
paper helps.)

                            83 18 72 21 93 44  7 21
                            -----------------------
                             7                83
                            -----------------------
                                  21 72
                            -----------------------
                                     21          72
                            -----------------------
                                        44 93
                            -----------------------
                                           72    93

You must write down EVERY swap of two keys (unlike in the Lecture 30 notes,
where I did entire removeMin() operations in one step).  If you swap two
identical keys (as often happens in quicksort, and can happen in heapsort), you
must illustrate that too.  You do not have to note when a key is swapped with
itself; for instance, in the selection sort above, the 18 is swapped with
itself, but I haven't written it down.  In each row, you are allowed to write
down keys that haven't moved, but you don't have to.  (I've omitted them all in
the example above.)  This same format will be accepted on the final exam.

When you're done, run GimmeYoNumber again and have your partner practice
in-place heapsort.  When you're confident that both you and your partner
understand heapsort, repeat the process with in-place quicksort (exactly as
described in the Lecture 31 notes) and in-place quickselect.  Always choose the
second key from the left to be the pivot.  When you practice quickselect, your
goal is to find the median key (seventh out of thirteen).

When you think you're ready to compete, or one hour has passed (whichever comes
first), it's time to find another team to vanquish...

Hour 2:
-------
Find another team to compete against.  (If you're the odd team out, wait until
a game completes; the losers are required to play a second game.)  You will
play a round of heapsort and a round of quicksort.  If each team wins one
round, you'll have a quickselect tiebreaker.

During each sort, either you or your partner will be the _lead_, and the other
will be the assistant.  Only the lead is allowed to write.  The assistant is
allowed to speak, point out mistakes, etc., but not to hold a writing
instrument.  The lead is allowed to write any diagrams that will help with
sorting (e.g. heaps in tree form), in addition to the official answer.

Designate one of yourselves to be the heapsort lead, and the other to be the
quicksort lead.  (Either of you can lead the quickselect tiebreaker; if one of
you is clearly faster, that one should get the job.)

Decide which team is Opponent 1 and which is Opponent 2.  To start a round of
sorting, run GimmeYoNumber, which will generate one list for each team.  Each
team writes down their complete list on a sheet of paper.  Then, it's up to you
whether you want to be in eyeshot of the opposing team or not.  Perform the
sort.  When you are done, yell "Sorted!".

The team that declared themselves sorted first is the tentative winner.
After making this statement, you must immediately hand your solution to the
other team; you are not allowed to write on that paper again.  The other team
may finish their solution at their leisure, then hand it to you.

The round ends with each team checking the other team's sort for errors.
A team that has made an error (e.g. omitting a swap of two equal keys) loses
the round unless both teams made an error, in which case the round must be
repeated.  Disputes on whether something is an error shall be adjudicated by
the TA or a lab assistant.  If neither team made an error, the team that
finished first wins the round.

The first team to win two rounds (including the quickselect tiebreaker if
necessary) wins the game.  If you lose the game, you must play a second game
with a different team to get full marks for the lab.  Please give preference to
a team that hasn't had a chance to play yet.

To get full marks for the lab, you must either play two games or win one.  You
are welcome to play additional games, though.

Bonus
-----
One in ten billion players will be the lucky winner of a FRIENDSHIP WITH
BRITNEY SPEARS!  To find out if you've won, enter the first ten digits printed
by GimmeYoNumber into your cell phone.

Check-off
---------
2 points:  For playing one game against another team and losing.
4 points:  For playing a game against another team and winning, or for
           playing games against two different teams.
