package api;
import static java.lang.Math.sqrt;

public class Geo_location implements GeoLocation{
    double x;
    double y;
    double z;

    public Geo_location(double x , double y , double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Geo_location(GeoLocation point){
        this.x = point.x();
        this.y = point.y();
        this.z = point.z();
    }


    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public double distance(GeoLocation g) {
        double sum1 = (this.x - g.x());
        double sum2 = (this.y - g.y());
        double sum3 = (this.z - g.z());
        double sum = sum1*sum1 + sum2*sum2 + sum3*sum3;
        return sqrt(sum);
    }

    @Override
    public String toString() {
        return "Geo_location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
