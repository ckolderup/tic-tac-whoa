## Tic-Tac-Whoa

This is an attempt to create a server for Ultimate Tic-Tac-Toe, a variation on Tic-Tac-Toe that 
contains a 3x3 grid of 3x3 grids. The rules are as follows:

1) The first player can place their mark in any location.
2) Every turn after that, the player whose turn it is must place a mark in the grid whose 
overall board position corresponds to the square in which the previous player placed their mark during their turn.
3) When an individual board has been "won" by the conventional rules of tic-tac-toe, that square is considered as being "won" for that player on the overall board.
4) When a player is sent to a board on their turn that has already been "won", they can choose to play on any board for their turn.
5) A win is declared when a player has won by conventional tic-tac-toe rules on the overall board.

tic-tac-whoa is the "server" component of a server-client relationship for Ultimate Tic Tac Toe implementations. You communicate by fetching a game by its UUID via the HTTP API and then performing actions on that game, passing in the UUID for all operations.

For the first pass, all games are kept in memory, not stored in any kind of database. When the server goes down, any existing games are "lost" by the server.

The hope is to get the server portion up and running and then build the first client in a separate app/framework.
