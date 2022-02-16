package api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class algo_dwgTest {
    static algo_dwg alg = new algo_dwg();
    @BeforeAll
    static void intgraph(){
        DWG G = new DWG();
        Node_data n1 = new Node_data(new Geo_location(1,1,1),2.2,"",0);
        Node_data n2 = new Node_data(new Geo_location(2,1,1),2.2,"",0);
        Node_data n3 = new Node_data(new Geo_location(3,1,1),2.2,"",0);
        G.addNode(n1);
        G.addNode(n2);
        G.addNode(n3);
        G.connect(n1.getKey(), n2.getKey(), 5);
        G.connect(n3.getKey(), n2.getKey(), 5);
        G.connect(n2.getKey(), n1.getKey(), 1);
        G.connect(n2.getKey(), n3.getKey(), 1);
        alg.init(G);
        System.out.println("build");
    }

    @Test

    void isConnected() {
        assertTrue(alg.isConnected());
    }
    @Test

    void shortestPathDist() {
        assertEquals(alg.shortestPathDist(0,2),6);
    }
    @Test

    void shortestPath() {
        assertTrue(alg.shortestPath(0,2)!=null);
    }
    @Test

    void center() {
        assertEquals(alg.center().getKey(),1);
    }
    @Test

    void tsp() {
        assertTrue(alg.tsp(alg.shortestPath(0,2))!=null);
    }
}