This is the game linear domination. This is a two player game where players will try to possess the most squares by the end of the game. Players play a turn by specifying a start and end point for a line. The coordinates of any point have to be less than or equal to N, because the size of the board is N x N. There are two rules for a line being played to be valid. It cannot have the same midpoint or angle as a line already played. Though, if it has been K turns since a line has been played, players are allowed to play a line with a repeat midpoint or angle. The game can go on for as long as players like, unless a very large K value was decided. If this is the case then at a certain point too many midpoints and angles will be remembered, and no line can be played.

The input file is of the format

-----------------

N K

x1 y1 x2 y2

...
...

x1 y1 x2 y2

-----------------

N specifies the size of the gameboard (N X N).

K specifies the number of turns that the game will remember a line's midpoint.

Each line specifies a player's line being played. The game starts with player 1, and alternates every line.
