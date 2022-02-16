package api;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class DWGTest {

    @Test
    void get_add_Node() {
        DWG G = new DWG();
        Node_data n = new Node_data(1,1,1,0);
        G.addNode(n);
        assertTrue(G.getNode(0)==n);
    }

    @Test
    void get_add_Edge() {
        DWG G = new DWG();

        G.addNode(new Node_data(1,1,1,0));
        G.addNode(new Node_data(1,1,1,1));
        G.connect(0,1,0.0);
        assertTrue((G.getEdge(0,1)).getSrc() == 0);
        assertTrue((G.getEdge(0,1)).getDest() == 1);

    }


    @Test
    void nodeIter() {
        DWG G = new DWG();
        Node_data n = new Node_data(1,1,1,0);
        G.addNode(n);
        Iterator<NodeData> it = G.nodeIter();
        assertSame(it.next(), n);
    }

    @Test
    void edgeIter() {
        DWG G = new DWG();
        G.addNode(new Node_data(1,1,1,0));
        G.addNode(new Node_data(1,1,1,1));
        G.connect(0,1,0.0);
        Iterator<EdgeData> it = G.edgeIter(0);
        Edge_data a = (Edge_data) it.next();
        assertEquals(0, a.getSrc());
        assertEquals(1, a.getDest());
    }
    @Test
    void removeNode() {
        DWG G = new DWG();
        Node_data n = new Node_data(1,1,1,0);
        G.addNode(n);
        G.removeNode(0);
        assertEquals(0, G.node_num);
    }

    @Test
    void removeEdge() {
        DWG G = new DWG();
        G.addNode(new Node_data(1,1,1,0));
        G.addNode(new Node_data(1,1,1,1));
        G.connect(0,1,0.0);
        G.removeEdge(0,1);
        assertEquals(0, G.edge_num);
    }

    @Test
    void nodeSize() {
        DWG G = new DWG();
        Node_data n = new Node_data(1,1,1,0);
        G.addNode(n);
        assertEquals(1, G.node_num);
    }

    @Test
    void edgeSize() {
        DWG G = new DWG();
        G.addNode(new Node_data(1,1,1,0));
        G.addNode(new Node_data(1,1,1,1));
        G.connect(0,1,0.0);
        assertEquals(1, G.edgeSize());
    }

    @Test
    void getMC() {
        DWG G = new DWG();
        G.addNode(new Node_data(1,1,1,0));
        G.addNode(new Node_data(1,1,1,1));
        G.connect(0,1,0.0);
        assertEquals(3, G.getMC());
    }
}