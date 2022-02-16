package api;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.Random.*;

public class testdwg {
    public static void main(String[] args) throws FileNotFoundException {
        algo_dwg d = new algo_dwg();
        DWG G = new DWG();
        for(int i =0;i<1000000;i++){
            Node_data n = new Node_data(new Geo_location(1,1,1),0,"",0);
            G.addNode(n);
        }
        for(int i =0;i<1000000;i++){
            for(int j =0 ; j<10; j++){
                Random r = new Random();
                int a = r.nextInt(100000);
                double w =(double) r.nextInt(10)+1;
                G.connect(i,a,w);
            }
        }
        d.init(G);
        Instant start = Instant.now();
        System.out.println(d.isConnected());
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println(timeElapsed.getNano());





    }
}
