package api;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import org.w3c.dom.Node;

public class algo_dwg implements DirectedWeightedGraphAlgorithms{

    DWG G = null;
    private static final int visited = 1;
    private static final int not_visited = 0;
    Stack<Integer> dfs_stack = null;

    //constructor that create a new algo graph
    public algo_dwg(){
    }

    /**
     * set the graph for the algorithems
     * @param g
     */
    @Override
    public void init(DirectedWeightedGraph g) {
        this.G = (DWG)g;
    }

    /**
     * gets the graph
     * @return the DWG graph
     */
    @Override
    public DirectedWeightedGraph getGraph() {
        return this.G;
    }

    /**
     *
     * @return a full deep copy of the graph form nodes to edges to geolocations
     */
    @Override
    public DirectedWeightedGraph copy() {
        DWG new_g = new DWG();
        for(int key: this.G.G.keySet()){
            new_g.addNode(new Node_data(this.G.G.get(key)));
        }
        return new_g;
    }

    /**
     * a dfs travel on the graph
     * @param index
     */
    private void DFS(int index){
        Stack<Integer> stacky = new Stack<>();
        stacky.add(index);
        while (!stacky.isEmpty()){
            int i = stacky.pop();
            dfs_stack.push(i);
            Node_data curr = (Node_data) this.G.getNode(i);
            curr.setTag(visited);
            for(int key: curr.edges.keySet()){
                if(this.G.getNode(key).getTag() != visited){
                    stacky.push(key);
                }
            }
        }
    }
    /**
     * a dfs travel on the graph but all the edges are reversed
     * @param index
     */
    private void DFS_reverse(int index){
        Stack<Integer> stacky = new Stack<>();
        stacky.add(index);
        while (!stacky.isEmpty()){
            int i = stacky.pop();
            dfs_stack.push(i);
            Node_data curr = (Node_data) this.G.getNode(i);
            curr.setTag(visited);
            for(int key: curr.edges_in.keySet()){
                if(this.G.getNode(key).getTag() != visited){
                    stacky.push(key);
                }
            }
        }
    }

    /**
     * set all the nodes tag to a certin int
     * @param t
     */
    private void set_all_nodes_tag(int t){
        Iterator<NodeData> iter = this.G.nodeIter();
        while(iter.hasNext()){
            iter.next().setTag(t);
        }
    }

    /**using Kosaraju's Algorithm for Strongly Connected Components
     * to check if the graph is connceted
     * if only one componnent than the graph is conncted
     * https://en.wikipedia.org/wiki/Kosaraju%27s_algorithm
     * @return
     */
    @Override
    public boolean isConnected() {
        this.dfs_stack = new Stack<>();
        this.set_all_nodes_tag(0);
        for(int key: this.G.G.keySet()){
            if(this.G.G.get(key).getTag() != visited) {
                this.DFS(key);
            }
        }

        this.set_all_nodes_tag(0);
        int connetcted_parts_sum =0;
        while(!dfs_stack.isEmpty()){
            int i = this.dfs_stack.pop();
            if(this.G.G.get(i).getTag() != visited) {
                connetcted_parts_sum++;
                this.DFS_reverse(i);
            }
        }
        this.set_all_nodes_tag(0);
        return connetcted_parts_sum == 1;
    }

    /**
     * returns the distance of the shortest path from one node to anouter
     * @param src - start node
     * @param dest - end (target) node
     * @return -1 if no path find
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        HashMap<Integer, Double[]> paths = new HashMap<>();
        int done = 0;
        for(int key: this.G.G.keySet()){
            paths.put(key,new Double[]{-1.0,-1.0});
        }
        paths.put(src,new Double[]{0.0,src+0.0});
        int min = src;
        double min_weight = -1;
        Boolean[] done_nodes = new Boolean[Node_data.amount+1];
        Arrays.fill(done_nodes, false);
        done_nodes[src] = true;
        while(done<=this.G.node_num) {

            done++;
            Node_data curr = ((Node_data) this.G.getNode(min));
            done_nodes[min] = true;
            if(curr != null) {
                for (int key : curr.edges.keySet()) {
                    if (paths.get(key)[0] == -1) {
                        paths.put(key, new Double[]{curr.edges.get(key).getWeight() + paths.get(curr.getKey())[0], curr.getKey() + 0.0});
                    } else if (paths.get(key)[0] > paths.get(curr.getKey())[0] + curr.edges.get(key).getWeight()) {
                        paths.put(key, new Double[]{paths.get(curr.getKey())[0] + curr.edges.get(key).getWeight(), curr.getKey() + 0.0});
                    }
                }
            }
            min_weight = -1;
            for (int key : paths.keySet()){
                if(!done_nodes[key]){
                    if(paths.get(key)[0] != -1){
                        if(min_weight == -1){
                            min = key;
                            min_weight = paths.get(key)[0];
                        }
                        else if (paths.get(key)[0]<min_weight){
                            min = key;
                            min_weight = paths.get(key)[0];
                        }
                    }
                }
            }
        }
        return paths.get(dest)[0];
    }

    /**
     * returns the actoual path from one node to anouter
     * @param src - start node
     * @param dest - end (target) node
     * @return the path of nodes null if no path exist
     */
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        HashMap<Integer, Double[]> paths = new HashMap<>();
        int done = 0;
        for(int key: this.G.G.keySet()){
            paths.put(key,new Double[]{-1.0,-1.0});
        }
        paths.put(src,new Double[]{0.0,src+0.0});
        int min = src;
        double min_weight = -1;
        Boolean[] done_nodes = new Boolean[this.G.node_num_max];
        Arrays.fill(done_nodes, false);
        done_nodes[src] = true;
        while(done<=this.G.node_num) {
            done++;
            Node_data curr = ((Node_data) this.G.getNode(min));
            done_nodes[min] = true;
            for (int key : curr.edges.keySet()) {
                if (paths.get(key)[0] == -1) {
                    paths.put(key, new Double[]{curr.edges.get(key).getWeight(), curr.getKey() + 0.0});
                } else if (paths.get(key)[0] > paths.get(curr.getKey())[0] + curr.edges.get(key).getWeight()) {
                    paths.put(key, new Double[]{paths.get(curr.getKey())[0] + curr.edges.get(key).getWeight(), curr.getKey() + 0.0});
                }
            }
            min_weight = -1;
            for (int key : paths.keySet()){
                if(!done_nodes[key]){
                    if(paths.get(key)[0] != -1){
                        if(min_weight == -1){
                            min = key;
                            min_weight = paths.get(key)[0];
                        }
                        else if (paths.get(key)[0]<min_weight){
                            min = key;
                            min_weight = paths.get(key)[0];
                        }
                    }
                }
            }

        }
        if(paths.get(dest)[0]!=-1) {
            int curr_id = dest;
            List<NodeData> p = new LinkedList<>();
            while (curr_id != src) {
                p.add(this.G.getNode(curr_id));
                double new_curr = paths.get(curr_id)[1];
                curr_id = (int) new_curr;
            }
            p.add(this.G.getNode(src));
            return p;
        }
        return null;
    }

    /**
     * retunrs the center of a grph
     * @return
     */
    @Override
    public NodeData center() {
        Double[][] distances = new Double[this.G.node_num][this.G.node_num];
        HashMap<Integer,Integer> trans = new HashMap<>();
        int runner = 0;
        for(int key:this.G.G.keySet()){
            trans.put(runner,key);
            runner++;
        }
        for(int i =0; i < this.G.node_num;i++){
            for(int j =0; j < this.G.node_num;j++){
                if(i==j){
                    distances[i][j] = 0.0;
                }
                else {
                    distances[i][j] = Double.MAX_VALUE;
                }
            }
        }
        for(int i =0; i < this.G.node_num;i++){
            for(int j =0; j < this.G.node_num;j++){
                if(i==j){
                    distances[i][j] = 0.0;
                }
                else {

                    EdgeData dist = this.G.getEdge(trans.get(i),trans.get(j));
                    if(dist == null) {
                        distances[i][j] = Double.MAX_VALUE;
                    }
                    else{

                        distances[i][j] = dist.getWeight();
                    }
                }
            }
        }
        for(int k=0; k<this.G.node_num;k++){
            for(int i =0; i <this.G.node_num;i++){
                for(int j =0; j <this.G.node_num;j++){
                    if (distances[i][j] > distances[i][k] + distances[k][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                    }
                }

            }

        }
        double[] max_dist = new double[this.G.node_num];
        for(int i =0; i <this.G.node_num;i++){
            max_dist[i] = 0;
        }
        for(int i =0; i <this.G.node_num;i++){
            for(int j =0; j <this.G.node_num;j++){
                if(max_dist[i]<distances[i][j]){
                    max_dist[i] = distances[i][j];
                }
            }

        }

        int center = 0;
        for(int i =0; i <this.G.node_num;i++){

            if(max_dist[center]>=max_dist[i]){

                center = i;
            }
        }
        return this.G.getNode(trans.get(center));
    }

    /**
     * the traveling sales man problrm
     * find a path the go through all the nodes in the list
     * and return a list of the  nodes we go throgh
     * @param cities
     * @return null if no path exist
     */
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        if(cities.isEmpty()){
            return null;
        }
        double total = 0;
        List<NodeData> path = new LinkedList<>();
        int next = 0;
        path.add(cities.get(next));
        while(!cities.isEmpty()){
            Node_data curr = (Node_data)cities.remove(next);
            HashMap<Integer, Double> min_map = new HashMap<>();
            for(NodeData n: cities){
                if(n != null) {
                    min_map.put(n.getKey(), this.shortestPathDist(curr.getKey(), n.getKey()));
                }
            }
            int min_key = -1;
            for(int key: min_map.keySet()){
                if(min_key == -1){
                    min_key = key;
                }
                else if(min_map.get(key)<min_map.get(min_key) && min_map.get(key) != -1){
                    min_key = key;
                }
            }
            path.add(this.G.getNode(min_key));
            next = cities.indexOf(this.G.getNode(min_key));
        }
        List<NodeData> full_path = new LinkedList<>();
        full_path.add(path.get(0));
        for(int i = 0; i<path.size()-2;i++){
            full_path.addAll(this.shortestPath(path.get(i).getKey(),path.get(i+1).getKey()));

        }
        return full_path;
    }

    /**
     * save a json file represent your graph onto the comuter
     * @param file - the file name (may include a relative path).
     * @return
     */
    @Override
    public boolean save(String file) {
        try {
            String path = file;
            JsonWriter writer = new JsonWriter(new FileWriter(path));
            writer.beginObject();
            writer.name("Edges");
            writer.beginArray();
            Iterator<EdgeData> edges = this.G.edgeIter();
            while(edges.hasNext()){
                Edge_data edge = (Edge_data) edges.next();
                writer.beginObject();
                writer.name("src").value(edge.getSrc());
                writer.name("w").value(edge.getWeight());
                writer.name("dest").value(edge.getDest());
                writer.endObject();
            }
            writer.endArray();
            writer.name("Nodes");
            writer.beginArray();
            Iterator<NodeData> nodes = this.G.nodeIter();
            while(nodes.hasNext()){
                Node_data node = (Node_data) nodes.next();
                writer.beginObject();
                writer.name("pos").value(node.getLocation().x()+","+node.getLocation().y()+","+node.getLocation().z());
                writer.name("id").value(node.getKey());
                writer.endObject();
            }
            writer.endArray();
            writer.endObject();
            writer.close();
            Type collectionType = new TypeToken< HashMap<String, ArrayList<HashMap<String, String>>>>(){}.getType();
            return true;
        }
        catch (FileNotFoundException e){
            return false;
        }
        catch (IOException e) {
            return false;
        }
    }

    /**
     * loads a json file from the computer
     * @param file - file name of JSON file
     * @return
     */
    @Override
    public boolean load(String file) {
        try {
            String path = file;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            Gson gson = new Gson();

            Type collectionType = new TypeToken< HashMap<String, ArrayList<HashMap<String, String>>>>(){}.getType();
            HashMap<String, ArrayList<HashMap<String, String>>> json_g = gson.fromJson(bufferedReader, collectionType);

            ArrayList<HashMap<String, String>> e = json_g.get("Edges");
            ArrayList<HashMap<String, String>> n = json_g.get("Nodes");

            this.G = new DWG();
            Iterator<HashMap<String, String>> iter = n.iterator();
            while(iter.hasNext()){
                HashMap<String, String> curr = iter.next();
                String[] pos = curr.get("pos").split(",");
                int id =  Integer.parseInt(curr.get("id"));
                double x = Float.parseFloat(pos[0]);
                double y = Float.parseFloat(pos[1]);
                double z = Float.parseFloat(pos[2]);
                Node_data new_node = new Node_data(x,y,z,id);
                this.G.addNode(new_node);
            }
            iter = e.iterator();
            while(iter.hasNext()){
                HashMap<String, String> curr = iter.next();
                int src = Integer.parseInt(curr.get("src"));
                double w = Float.parseFloat(curr.get("w"));
                int dest = Integer.parseInt(curr.get("dest"));
                this.G.connect(src,dest,w);
            }
            return true;
        }
        catch (FileNotFoundException e){
            return false;
        }

    }

}
