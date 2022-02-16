package api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Geo_locationTest {

    @Test
    void copy_constractor(){
        Geo_location p = new Geo_location(1,1,1);
        Geo_location q  = new Geo_location(p);
        assertTrue(p != q);
        assertEquals(1,q.x);
        assertEquals(1,q.y);
        assertEquals(1,q.z);
    }

    @Test
    void x() {
        Geo_location p = new Geo_location(1,1,1);
        p.x = 2;
        assertEquals(2,p.x());
    }

    @Test
    void y() {
        Geo_location p = new Geo_location(1,1,1);
        p.y = 2;
        assertEquals(2,p.y());
    }

    @Test
    void z() {
        Geo_location p = new Geo_location(1,1,1);
        p.z = 2;
        assertEquals(2,p.z());
    }

    @Test
    void distance() {
        Geo_location p = new Geo_location(1,1,1);
        Geo_location q = new Geo_location(1,2,1);
        assertEquals(1, p.distance(q));
    }
}