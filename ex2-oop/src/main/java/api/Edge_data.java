package api;

public class Edge_data implements EdgeData{

    int src;
    int dest;
    double weight;
    int tag;
    String info ="";

    /**a constrctor for creating a neew edge
     *
     * @param src
     * @param dest
     * @param weight
     * @param tag
     */
    public Edge_data(int src, int dest, double weight, int tag) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
        this.tag = tag;
    }

    /**
     * a copy constructor
     * @param edge_to_copy
     */
    public Edge_data(EdgeData edge_to_copy){
        Edge_data e = (Edge_data) edge_to_copy;
        this.src = e.getSrc();
        this.dest = e.getDest();
        this.weight = e.getWeight();
        this.tag = e.getTag();
        this.info = e.getInfo();
    }

    /**
     * retruns the src id
     * @return
     */
    @Override
    public int getSrc() {
        return this.src;
    }
    /**
     * retruns the dst id
     * @return
     */
    @Override
    public int getDest() {
        return this.dest;
    }

    /**
     *
     * @return the wegiht of the edge
     */
    @Override
    public double getWeight() {
        return this.weight;
    }

    /**
     *
     * @return the ino of the eedge
     */
    @Override
    public String getInfo() {
        return this.info;
    }

    /**
     * set the info of the edge
     * @param s is the new info
     */
    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    /**
     * returns tha tag of the edge
     * @return
     */
    @Override
    public int getTag() {
        return this.tag;
    }

    /**
     * set a new tag
     * @param t - the new value of the tag
     */
    @Override
    public void setTag(int t) {
        this.tag = t;
    }
//a simple to string method
    @Override
    public String toString() {
        return "Edge_data{" +
                "src=" + src +
                ", dest=" + dest +
                ", weight=" + weight +
                '}';
    }
}
