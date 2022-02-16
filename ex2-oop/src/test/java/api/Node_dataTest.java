package api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Node_dataTest {

    @Test
    void getKey() {
        GeoLocation p = new Geo_location(1,1,1);
        Node_data n1 = new Node_data(p, 20,"hi",0);
        GeoLocation q = new Geo_location(2,2,2);
        Node_data n2 = new Node_data(q, 40,"hi2",10);

        assertTrue(n1.getKey()+1 == n2.getKey());
    }

    @Test
    void get_set_Location() {
        GeoLocation p = new Geo_location(1, 1, 1);
        Node_data n1 = new Node_data(null, 20, "hi", 0);
        GeoLocation q = new Geo_location(1, 1, 1);
        n1.setLocation(p);
        assertEquals(0, q.distance(n1.getLocation()));
    }

    @Test
    void get_set_Weight() {
        GeoLocation p = new Geo_location(1,1,1);
        Node_data n1 = new Node_data(p, 20,"hi",0);
        assertEquals(20,n1.getWeight());
        n1.setWeight(40);
        assertEquals(40,n1.getWeight());
    }

    @Test
    void get_set_Info() {
        GeoLocation p = new Geo_location(1,1,1);
        Node_data n1 = new Node_data(p, 20,"hi",0);
        assertTrue("hi".equals(n1.getInfo()));
        n1.setInfo("hello");
        assertTrue("hello".equals(n1.getInfo()));
    }

    @Test
    void get_set_Tag() {
        GeoLocation p = new Geo_location(1,1,1);
        Node_data n1 = new Node_data(p, 20,"hi",0);
        assertEquals(0,n1.getTag());
        n1.setTag(4);
        assertEquals(4,n1.getTag());
    }
}