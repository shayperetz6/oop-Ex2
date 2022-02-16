@auther Ofek Alon, Shay Peretz
___________________________________________________________________________________________
About the project:

In this project we implement a directed weighted graph. In addition, we run on the graph the flowing Algorithms: 
center,tsp, short path, save and load from json and to json file, init the graph and copy.
And build GUI to visualize the graph and the graph algorithm.
___________________________________________________________________________________________
The main classes in the project:
__________________________________________________________________________________________
Node_date :
This class represents the set of operations applicable on a
node (vertex) in a (directional) weighted graph.
variables:
HashMap<Integer, EdgeData> edges // hashmap contains all the edges the node is their source
HashMap<Integer, EdgeData> edges_in// hashmap contains all the edges the node is there destination
methods:
public int getKey() 
public GeoLocation getLocation() 
public void setLocation(GeoLocation p)
public double getWeight()
public void setWeight(double w)
public String getInfo()
public void setInfo(String s)
public int getTag()
public void setTag(int t) 
___________________________________________________________________________________________

Geo_location:
represents a geo location <x,y,z>, (aka Point3D data)
variables:
x,y,z the coordination of the node on the graph
methods:
public double x()
public double y()
public double z()
public double distance(GeoLocation g)-return distance between two points

__________________________________________________________________________________________
Edge_data
represents the set of operations applicable on a
directional edge(src,dest) in a directional weighted graph.

variables:
src
dest
weight
tag
methods:
public int getSrc()
public int getDest()
public double getWeight()
public String getInfo()
public void setInfo(String s)
public int getTag() 
public void setTag(int t) 
___________________________________________________________________________________________
DWG
represents a Directional Weighted Graph



