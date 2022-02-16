package api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Edge_dataTest {

    @Test
    void get_Src_dst_weight() {
        EdgeData e = new Edge_data(1,2,20,0);
        assertEquals(e.getDest(), 2);
        assertEquals(e.getSrc(), 1);
        assertEquals(e.getWeight(), 20);
    }

    @Test
    void get_set_tag() {
        EdgeData e = new Edge_data(1,2,20,0);
        assertEquals(e.getTag(), 0);
        e.setTag(2);
        assertEquals(e.getTag(),2);
    }

    @Test
    void get_set_Info() {
        EdgeData e = new Edge_data(1,2,20,0);
        e.setInfo("hi");
        assertTrue("hi".equals(e.getInfo()));
    }
}