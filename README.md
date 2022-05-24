# Practical Implementation of Mending in Graph Theory #

## Motivation ##

The modern era has seen significant progress in distributed technologies like the block-chain, 
peer to peer software and service platforms running on user hardware.

When companies grow in size their respective networks gain so many users that it becomes impractical to re-run a distributed algorithm on the whole network to obtain a valid labeling for the new devices. 
A clever and convenient solution to this consists in running a mending algorithm locally to incorporate the new devices into the network in a consistent manner.

To achieve this, mending algorithms are implemented by considering the network of devices as a graph where each device is a node/vertex and each connection is an edge. 
As many real world networks have some form of periodicity (are made of patterns that repeat themselves) or symmetry, they are easy to represent as grids. 

## Description ##

This project provides solutions for two very common practical problems.

1)	Node coloring with as few colors as possible such that 2 adjacent nodes always have different colors.
2)	Edge orientation: orient the edges such that every node has a number of inbound edges included in a predefined set of numbers.

Each of those problems is solved on 3 different grids:

1.	Coloring in 2D-triangular grids with less than Δ+1 colors. 
2.	Coloring in 2D-hexagonal grids with less than Δ+1 colors. 
3.	Coloring in 2D-octogonal grids with less than Δ+1 colors. 
4.	Orientation problem in 2D-triangular grids.
5.	Orientation problem in 2D-hexagonal grids.
6.	Orientation problem in 2D-octogonal grids.

Where Δ refers to the maximal degree of a vertex.

These implementations can also be used as inspiration to solve similar problems on other grids.

## Installation ##

To install this, you will need to have Java installed. You will then be able to download the code and run it directly on your machine.

## Use ##

To run the coloring algorithms, you can run the code in src/Coloring/Main.java.
To run the orienting algorithms, you can run the code in src/Orienting/Main.java.

To run only a specific algorithm, comment the other function calls in the corresponding Main class.
For example, if you only wish to run the Hexagonal Grid Orienting algorithm, 
Comment the other 2 methods invoked in the main method of src/Orienting/Main.java.

To get the solution in a file, uncomment the last line of the selected method in the Main.java file.
For example, if you only wish to write the output of the Triangular Grid Coloring algorithm, 
Uncomment the last line of solve_Triangular_Grid_Coloring in src/Coloring/Main.java.

Please notice that some algorithm have a very large output size and they should be run in a distributed manner to compute specifically the solution for their local neighborhood. 

## Acknowledgments and other Contributors ##

Many thanks to Prof. Jukka Suomela at Aalto University for his help as supervisor of this project.