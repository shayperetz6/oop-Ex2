package api;


import java.util.Arrays;
import java.util.HashMap;

/**
 * This class represents the set of operations applicable on a
 * node (vertex) in a (directional) weighted graph.
 */

public class Node_data implements NodeData{
    HashMap<Integer, EdgeData> edges;
    HashMap<Integer, EdgeData> edges_in;
    int edge_num = 0;
    int edgeIn_num = 0;
    static int amount = 0;
    int key = 0;
    GeoLocation point;
    double weight = 0;
    String info = "";
    int tag = 0;

    public Node_data(double x,double y, double z, int id){
        amount++;
        this.point = new Geo_location(x,y,z);
        this.edges = new HashMap<>();
        this.edges_in = new HashMap<>();
        this.key = id;
    }
    /**
     * data constructor creates a node from the given data
     * @param p is a geolocation point
     * @param w is the weight of the node
     * @param i is the info
     * @param t is the tag
     */
    public Node_data(GeoLocation p, double w, String i, int t){
        amount++;
        this.key = amount;
        this.point = p;// may need to make a new point
        this.weight = w;
        this.info = i;
        this.tag = t;
        this.edges = new HashMap<>();
        this.edges_in = new HashMap<>();
    }
    public int getEdge_num(){
        return this.edge_num;
    }
    public int getEdgeIn_num(){
        return this.edgeIn_num;
    }
    public void addEdge(EdgeData e){
        this.edge_num ++;
        this.edges.put(e.getDest(),e);
    }
    public void addEdgeIn(EdgeData e){
        this.edgeIn_num ++;
        this.edges_in.put(e.getSrc(),e);
    }
    /**
     * a copy constructor
     * @param node_to_copy is the node we need to copy
     */
    public Node_data(NodeData node_to_copy) {
        Node_data n = (Node_data) node_to_copy;
        amount++;
        this.key = amount;
        this.point = new Geo_location(n.getLocation());// may need to make a new point
        this.weight = n.getWeight();
        this.info = n.getInfo();
        this.tag = n.getTag();

        for(int key : n.edges.keySet()){
            this.edges.put(key,new Edge_data(n.edges.get(key)));
        }

        for(int key : n.edges_in.keySet()){
            this.edges_in.put(key,new Edge_data(n.edges_in.get(key)));
        }

        this.edgeIn_num = n.getEdgeIn_num();
        this.edge_num = n.getEdge_num();
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public GeoLocation getLocation() {
        return this.point;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.point = p;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    @Override
    public String toString() {
        return "Node_data{key=" + this.key +
                ", point=" + point +
                Arrays.asList(this.edges) +" , in- "+ Arrays.asList(this.edges_in) +
                '}';
    }
}
