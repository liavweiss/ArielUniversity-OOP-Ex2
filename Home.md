![](https://github.com/ItaiLash/ex2/blob/main/docs/pokemon%20logo.png)



* A general explanation of the Pokemon game
* How to start playing?
  * start the game via command line
  * start the game via login menu
* Our way of playing the game
* Our score table


## A general explanation of the Pokemon game
This game is a very fun way to illustrate the algorithms that implemented in the first part of the project via a game.\
The game is very simple and consists of 24 different stages (0-23).\
At each stage a different graph is loaded with "Pokemon" (orange and green dots) and "Agents" (red dots) on it.\
The graphical interface allows the Pokemon and agents to be displayed on the graph.\
The purpose of the game is to use the algorithms from the first part of the project in order to eat as many Pokemon as possible in the allotted time for each stage.
\
The Pokemon are divided into two groups:\
Orange Pokemon - are on a descending side (aka 8 -> 4).\
Green Pokemon - are on a rising side (aka 2 -> 7).\

![](https://github.com/ItaiLash/ex2/blob/main/docs/Screen%20Shot%202020-12-20%20at%2020.49.48.png)

## How to start playing?
To start playing perform a clone to our repository.
Once our repository is on your computer there are two options to start the game:
### start the game via command line
![](https://github.com/ItaiLash/ex2/blob/main/docs/Screen%20Shot%202020-12-20%20at%2021.09.47.png)
### start the game via login menu 
Double-click the Ex2.jar file and an opening window will appear:\
![](https://github.com/ItaiLash/ex2/blob/main/docs/Screen%20Shot%202020-12-20%20at%2021.14.47.png)

## Our way of playing the game
### Summary of our course of action:
First, we decided to place the agents in the most lucrative places on the graph.\
In some cases the agents will be placed next to the Pokemon with the highest values.\
And in other cases the agents will be located in areas where there is a high amount of Pokemon.\
During the game our department holds HashMap whose keys are the agents' numbers and in the entries are the Pokemon that the agents marked for eating.\
If the agent marks a Pokemon for eating he will move on a rib that will advance him towards the Pokemon.\
Because every time a Pokemon is eaten a new Pokemon appears on the graph, each time an agent reaches the apex, it will check if there is another Pokemon now that it is better for it to mark for eating (closer to the one marked) and move towards it, otherwise it will continue to move towards the same Pokemon.\
If the agent has not marked a Pokemon to eat he will select the closest Pokemon to its current location that has not been marked by another agent.\
If the agent does not have a Pokemon available for eating (since all other Pokemon have been marked by other agents) he will move on the side he is on and wait for a new Pokemon to appear in his area.\
### The algorithms we use:
We used a number of methods that aim to eat a maximum of Pokemons in less than 10 moves per second.
The main methods are:\
`Ex2(long id, int scenario)` : A constructor who receives the player's ID number and the number of levle in which the player will want to play. The constructor allows the game to be loaded via the command line. The user can choose whether to enter the ID number and level number within the command line or through the graphical window.\
`run()` : Loading the selected level from the server and running the game algorithmically and graphically.\
`init(game_service game)` : Gets the desired level from the server as a JSON file, reads the file and loads it into a graph. Positions the Pokemon on the graph as called from the server. Positions the agents correctly and cleverly on the graph - each agent will be placed close to a different Pokemon and in different locations on the graph.\
`openGUI(directed_weighted_graph gg, String gamePokemons)` : Takes care of the updated graphic display of the game.\
`moveAgents(game_service game, directed_weighted_graph gg)` : Moves the agents on the edges, if the agent has reached to a node the method calls for an auxiliary method that wisely selects the next edge on which the agent will advance.\
`isAlive(List<CL_Agent> agents, List<CL_Pokemon> pokemons)` : Checks whether a Pokemon that marked by an agent is still alive or has been eaten by another agent.\
`nextNode(directed_weighted_graph g, int src, CL_Agent agent)` : The method calls auxiliary functions according to the status of the agent (its location, whether he chose a Pokemon he would like to eat, etc.) and finally returns the edge that the agent needs to advance on.\
`nextPokemonByDis(dw_graph_algorithms g, int src, CL_Agent agent)` : The method selects for the agent the Pokemon that will best pay for him to eat.\
`continueToPok(dw_graph_algorithms g, int src, CL_Agent agent, CL_Pokemon myPokemon)` : If an agent chooses a Pokemon that he wants to eat, the method will check whether it pays to continue advancing towards the chosen Pokemon or to choose another Pokemon (when Pokemon is eaten a new Pokemon is created in a random place on the graph so it may be better to choose another Pokemon to advance towards).\
`mostValuePok(ArrayList<CL_Pokemon> poks)` : this method is used in init method. The method returns a priority queue of Pokemon sorted by their values.\
`goodList(List<CL_Pokemon> currentList, dw_graph_algorithms g, CL_Agent currentAgent)` : A private method that gets a list of all the Pokemon on the graph. The method filters Pokemon that are closer to other agents and returns a shortlist of Pokemon available for eating for the current agent.\
`choosePokemon(List<CL_Pokemon> pokList, dw_graph_algorithms g)` : The method returns the most lucrative Pokemon to start the game next to. The method checks which Pokemon has the most Pokemons closest to it and so the agent will start the game in the best position for him.\
`getRatio(edge_data e, directed_weighted_graph g, Point3D point)` : Returns the relative position of a point on an edge.

### Our score table:
| **Level** | **Score**      |    **Number of moves**        |
|-----------------|-----------------------|-----------------------|
| 0 | 147 | 290 |
| 1 | 526 | 580 |
| 2 | 327 | 290 |
| 3 | 932 |  581 |
| 4 | 356 |  289 |
| 5 | 726 |  580 |
| 6 | 85 | 290 |
| 7 | 396 | 581 |
| 8 | 130 | 289 |
| 9 | 556 | 580 |
| 10 | 222 | 290 |
| 11 | 1910 | 579 |
| 12 | 79 | 290 |
| 13 | 465 | 580 |
| 14 | 239 | 290 |
| 15 | 310 | 579 |
| 16 | 249 | 289 |
| 17 | 1194 | 579 |
| 18 | 40 | 290 |
| 19 | 320 | 580 |
| 20 | 204 | 288 |
| 21 | 248 | 594 |
| 22 | 305 | 288 |
| 23 | 613 | 583 |

