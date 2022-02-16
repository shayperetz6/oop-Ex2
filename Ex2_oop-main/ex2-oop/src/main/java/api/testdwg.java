package api;
import java.io.FileNotFoundException;
import java.util.Iterator;

public class testdwg {
    public static void main(String[] args) throws FileNotFoundException {
        algo_dwg d = new algo_dwg();
        d.load("G2.json");
        d.getGraph().nodeSize();
        Iterator<NodeData> iter = d.getGraph().nodeIter();
        d.getGraph().connect(0,11,0.7);
        while (iter.hasNext()){
            Node_data n = (Node_data) iter.next();
        }
        d.save("try.json");
    }
}
