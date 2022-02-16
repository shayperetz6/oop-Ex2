package api;

public class Edge_data implements EdgeData{

    int src;
    int dest;
    double weight;
    int tag;
    String info ="";

    public Edge_data(int src, int dest, double weight, int tag) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
        this.tag = tag;
    }
    public Edge_data(EdgeData edge_to_copy){
        Edge_data e = (Edge_data) edge_to_copy;
        this.src = e.getSrc();
        this.dest = e.getDest();
        this.weight = e.getWeight();
        this.tag = e.getTag();
        this.info = e.getInfo();
    }

    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getWeight() {
        return this.weight;
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
        return "Edge_data{" +
                "src=" + src +
                ", dest=" + dest +
                ", weight=" + weight +
                '}';
    }
}
