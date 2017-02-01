# Sprint1
First Sprint Iteration of OOP Turn-Based Strategy Game

#Compile and run GUI:
>> git clone https://github.com/COP4331-Team7/Sprint1

>> cd Sprint1/Sprint1/src/GUI_src/

>> javac MVC.java && java MVC

#Design of Game
##Theme
Futuristic/Space

##Map(s)
In the first iteration, there is one map that is 20x20 tiles. The map is described by 4 different terrains: 
 1. FlatLand - regular movement 
 2. Desert - slowed down movement by -1 tiles 
 3. Hills - slowed down movement by -2 tiles 
 4. Mountains - impassible by any of the Units in this first iteration
 
##Player Stats
The game presents 3 different stats, on a range from 0 - 100, to enhance gameplay:
 1. Money - assists in upkeep of units/structures
 2. Construction - assists in building new structures
 3. Technology - assists in *nothing*

##Resources
A resource is automatically picked up when a unit enters a tile:
 1. Money Bag - increases the Money stat by a random integer between 20 and 40 
 2. Moon Rocks - increases the Construction stat by a random integer between 20 and 40 
 3. Hieroglyphic Books - increases the Technology stat by a random integer between 20 and 40 

##Area Effects
An area effect is a process that is automatically triggered when a unit enters a place:
 1. Storm - takes damage to a unit at a random integer between -30 and -10 
 2. Elixir Shower - heals damage to a unit at a random integer between 20 and 30 
 3. Volcanic Vent - instantly kills a unit 
