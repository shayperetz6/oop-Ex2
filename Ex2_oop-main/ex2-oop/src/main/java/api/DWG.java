package api;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class DWG implements DirectedWeightedGraph{
    HashMap<Integer, NodeData> G;
    int node_num = 0;
    int edge_num = 0;
    int MC = 0;
    public DWG(){
        this.G = new HashMap<>();
    }

    @Override
    public NodeData getNode(int key) {
        if(this.G.containsKey(key)){
            return G.get(key);
        }
        return null;
    }

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

    @Override
    public void addNode(NodeData n) {
        this.node_num ++;
        this.edge_num += ((Node_data) n).getEdge_num();
        this.MC ++;
        this.G.put(n.getKey(), n);
    }

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

    @Override
    public Iterator<NodeData> nodeIter() {
        int MC_start = this.MC;
        Iterator<NodeData> iter = this.G.values().iterator();
        if(MC_start!=this.MC){
            throw new RuntimeException("changes have been done");
        }
        return iter;
    }

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

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        int MC_start = this.MC;
        Iterator<EdgeData> e_iter = ((Node_data)G.get(node_id)).edges.values().iterator();
        if(MC_start!=this.MC){
            throw new RuntimeException("changes have been done");
        }
        return e_iter;
    }

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

    @Override
    public int nodeSize() {
        return this.node_num;
    }

    @Override
    public int edgeSize() {
        return this.edge_num;
    }

    @Override
    public int getMC() {
        return this.MC;
    }
}
