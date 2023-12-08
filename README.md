# CS 245 (Fall 2023) - Assignment 3 - IRoadTrip


<h3>Description</h3>
This Java program is designed to plan and operate a road trip based on geographical data. The program utilizes a custom undirected weighted graph to model the relationships between countries and their states. It considers various factors such as borders, distances, and geographical data to provide information about routes between locations.

<h3>Usage of the 3 Files</h3>
This program takes in 3 files, 'borders.txt', 'capdist.csv', and 'state_name.tsv', which all contain various information regarding the countries. The 'borders.txt' file contains information about bordering countries, which is parsed through in order to obtain a list of hashmaps, that contain a country as the key from the 'state_name.tsv' file, and the neighboring countries as the values, along with their distances, which comes from the 'capdist.csv'. 

<h3>Classes Implemented</h3>
In this program, I implemented 4 classes to make the simulation of a road trip smooth, and seamless. The first class was 'IRoadTrip', which serves as the main class for the road trip program. It orchestrates the entire program, from reading input files, to creating the graph to provide interactive functionality for users. The second class is the 'Graph' class, which represents a custom undirected weighted graph. It includes methods for adding nodes, edges, finding paths, getting distances, and implementing Dijkstra's algorithm. The third class is the 'Node' class, which represents a node in the graph, that corresponds to a state in a country. It includes information about the state, country, and neighboring nodes. The last class is the 'Edge' class represents an edge in the graph, that connects two nodes. It includes information about the destination node and the weight (distance) of the edge.

<h3>How It Works</h3>
First, the program will prompt the users to input a source and destination country. The program will then readv the input files in order to create nodes, and establish connections between the user's input (2 countries). Then, a graph is constructed based on the loaded data, considering bordering countries and distances between them. The graph then utilizes Dijkstra's algorithm in order to find the shortest path between two countries, taking into account the graph's weighted edges. After, it should show the user the shortest path between their source and destination countries.