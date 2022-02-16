package api;
import static java.lang.Math.sqrt;

public class Geo_location implements GeoLocation{
    double x;
    double y;
    double z;

    /**create a geolocation by seting an x y z value
     *
     * @param x x val
     * @param y y val
     * @param z z val
     */
    public Geo_location(double x , double y , double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     *  a copy constructor
     * @param point
     */
    public Geo_location(GeoLocation point){
        this.x = point.x();
        this.y = point.y();
        this.z = point.z();
    }

    //returns the x value
    @Override
    public double x() {
        return this.x;
    }
    //returns the y value
    @Override
    public double y() {
        return this.y;
    }
    //returns the z value
    @Override
    public double z() {
        return this.z;
    }

    /**returns the distance to anouter point
     *
     * @param g
     * @return
     */
    @Override
    public double distance(GeoLocation g) {
        double sum1 = (this.x - g.x());
        double sum2 = (this.y - g.y());
        double sum3 = (this.z - g.z());
        double sum = sum1*sum1 + sum2*sum2 + sum3*sum3;
        return sqrt(sum);
    }

    //to string method
    @Override
    public String toString() {
        return "Geo_location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
