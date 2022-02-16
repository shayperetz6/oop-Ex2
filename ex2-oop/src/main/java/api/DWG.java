package api;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class DWG implements DirectedWeightedGraph{
    HashMap<Integer, NodeData> G;
    int node_num = 0;
    int node_num_max=0;
    int edge_num = 0;
    int MC = 0;
    //intilaze a new hashmap to contain all the nodes
    public DWG(){
        this.G = new HashMap<>();
    }

    /**
     *
     * @param key - the node_id
     * @return the nodedata with the id=key
     */
    @Override
    public NodeData getNode(int key) {
        if(this.G.containsKey(key)){
            return G.get(key);
        }
        return null;
    }

    /**
     *
     * @param src
     * @param dest
     * @return the edge data that starts with the src and ends in dest
     */
    @Override
    public EdgeData getEdge(int src, int dest) {
        if(this.G.containsKey(src)){
            if(((Node_data) this.G.get(src)).edges.containsKey(dest)){
                return ((Node_data) this.G.get(src)).edges.get(dest);
            }
            return null;
        }
        return null;
    }

    /**
     * adding a new nodedata to the graph
     * @param n a nodedata
     */
    @Override
    public void addNode(NodeData n) {
        this.node_num ++;
        if(node_num_max<node_num){
            node_num_max++;
        }
        this.edge_num += ((Node_data) n).getEdge_num();
        this.MC ++;
        this.G.put(n.getKey(), n);
    }

    /**
     * create a new edge between the src and dest
     * @param src - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
     */
    @Override
    public void connect(int src, int dest, double w) {
        if(this.G.containsKey(src)){

            this.MC ++;
            EdgeData new_e = new Edge_data(src,dest,w,0);
            ((Node_data) this.G.get(src)).addEdge(new_e);
            ((Node_data) this.G.get(dest)).addEdgeIn(new_e);
            this.edge_num++;
        }
    }

    /**
     * returns a node iterator
     * @return
     */
    @Override
    public Iterator<NodeData> nodeIter() {
        int MC_start = this.MC;
        Iterator<NodeData> iter = this.G.values().iterator();
        if(MC_start!=this.MC){
            throw new RuntimeException("changes have been done");
        }
        return iter;
    }
    /**
     * returns a edge iterator  =of all the edges in the graph
     * @return
     */
    @Override
    public Iterator<EdgeData> edgeIter() {
        int MC_start = this.MC;
        LinkedList<EdgeData> edge_list = new LinkedList<>();
        Iterator<NodeData> iter =  this.G.values().iterator();
        while (iter.hasNext()){
            Iterator<EdgeData> iter2 =  ((Node_data) iter.next()).edges.values().iterator();
            while(iter2.hasNext()){
                Edge_data e = (Edge_data) iter2.next();
                edge_list.add(e);
            }
        }
        Iterator<EdgeData> e_iter = edge_list.iterator();
        if(MC_start!=this.MC){
            throw new RuntimeException("changes have been done");
        }
        return e_iter;
    }
    /**
     * returns a edge iteratorof all the edges in a node
     * @return
     */
    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        int MC_start = this.MC;
        Iterator<EdgeData> e_iter = ((Node_data)G.get(node_id)).edges.values().iterator();
        if(MC_start!=this.MC){
            throw new RuntimeException("changes have been done");
        }
        return e_iter;
    }

    /**
     * removing a node and all of the edges conncted to her(in and out)
     * @param key is the id
     * @return the nodedata we removed
     */
    @Override
    public NodeData removeNode(int key) {
        if(this.G.containsKey(key)){
            this.MC ++;
            this.node_num --;

            this.edge_num -= ((Node_data)G.get(key)).getEdge_num();
            this.MC += ((Node_data)G.get(key)).getEdge_num();

            this.edge_num -= ((Node_data)G.get(key)).getEdgeIn_num();
            this.MC += ((Node_data)G.get(key)).getEdgeIn_num();

            for(int keys : ((Node_data)G.get(key)).edges_in.keySet()){
                ((Node_data)G.get(keys)).edges.remove(key);

            }
            ((Node_data)G.get(key)).edges_in.clear();
            ((Node_data)G.get(key)).edgeIn_num = 0;

            for(int keys : ((Node_data)G.get(key)).edges.keySet()){
                ((Node_data)G.get(keys)).edges_in.remove(key);
            }
            ((Node_data)G.get(key)).edges.clear();
            ((Node_data)G.get(key)).edge_num = 0;

            return this.G.remove(key);
        }
        return null;
    }

    /**
     * disconnect the edge between src and dest
     * @param src
     * @param dest
     * @return the edgedat a we removed
     */
    @Override
    public EdgeData removeEdge(int src, int dest) {
        if(this.G.containsKey(src)){
            Node_data n = (Node_data) G.get(src);
            if (n.edges.containsKey(dest)){
                this.edge_num--;
                n.edge_num--;
                Node_data n2 = (Node_data) G.get(dest);
                n2.edgeIn_num--;
                n2.edges_in.remove(src);
                return n.edges.remove(dest);
            }
            return null;
        }
        return null;
    }

    /**
     * returns the amount of nodes in the graph
     * @return
     */
    @Override
    public int nodeSize() {
        return this.node_num;
    }

    /**
     * returns the amount of edges in the graph
     * @return
     */
    @Override
    public int edgeSize() {
        return this.edge_num;
    }

    /**
     * returns the amount of modifictions the graph had
     * @return
     */
    @Override
    public int getMC() {
        return this.MC;
    }
}
