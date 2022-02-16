package GrapGui;
import api.*;
import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

public class Panel extends JPanel  {

    private DWG graph;
    private algo_dwg ag;
    private double minX;
    private double minY;
    private double maxX;
    private double maxY;
    private double X;
    private double Y;
    private Dimension screenSize;
    public Panel( DWG g)
    {
        super();
        graph= g;
        ag.init(g);
        this.setBackground(new Color(11, 148, 56, 255));
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setLimits();
        X = screenWidth / Math.abs(maxX - minX) * 0.9;
        Y = screenHeight / Math.abs(maxY - minY) * 0.9;
        repaint();

    }
    void setLimits(){
        Iterator<NodeData> xit = ag.getGraph().nodeIter();
        while (xit.hasNext()) {
            NodeData n = xit.next();
            if (n.getLocation().x() < minX) {
                minX = n.getLocation().x();
            }
        }
        Iterator<NodeData> yit = ag.getGraph().nodeIter();
        while (yit.hasNext()) {
            NodeData m = yit.next();
            if (m.getLocation().y() < minY) {
                minY = m.getLocation().y();
            }
        }
        xit = ag.getGraph().nodeIter();
        while (xit.hasNext()) {
            NodeData n = xit.next();
            if (n.getLocation().x() > maxX) {
                maxX = n.getLocation().x();
            }
        }
        yit = ag.getGraph().nodeIter();
        while (yit.hasNext()) {
            NodeData m = yit.next();
            if (m.getLocation().y() > maxY) {
                maxY = m.getLocation().y();
            }
        }
    }

    public void paintComponent (Graphics g){
        super.paintComponent(g);
        Iterator<NodeData> it = ag.getGraph().nodeIter();
        NodeData current;
        while(it.hasNext()) // Draw all the nodes
        {
            current=it.next();
            int x = (int) ((current.getLocation().x() - minX) * X);
            int y = (int) ((current.getLocation().y() - minY) * Y);
            g.setColor(Color.white);
            g.fillOval(x, y, 18, 18);
            g.setColor(Color.black);
            g.setFont(new Font("MV Boli",Font.PLAIN,25));
            g.drawString("" + current.getKey(), x + 10, y + 15);

        }
        Iterator<EdgeData> itr=ag.getGraph().edgeIter();
        EdgeData edge;
        while (itr.hasNext())
        {
            edge= itr.next();
            double srcX = this.graph.getNode(edge.getSrc()).getLocation().x();
            srcX = ((srcX - minX) * X) + 15;
            double srcY = this.graph.getNode(edge.getSrc()).getLocation().y();
            srcY = ((srcY - minY) * Y) + 15;

            double destX = this.graph.getNode(edge.getDest()).getLocation().x();
            destX = ((destX - minX) * X) + 15;
            double destY = this.graph.getNode(edge.getDest()).getLocation().y();
            destY = ((destY - minY) * Y) + 15;
            g.setColor(new Color(74, 7, 7, 255));
            g.drawLine( (int) srcX, (int) srcY, (int) destX, (int) destY);
            drawArrowLine(g, (int) srcX, (int) srcY, (int) destX, (int) destY, 15, 10);
            g.drawString(edge.getWeight() + "", ((int)srcX + (int)destX) / 2, ((int) srcY + (int)destY / 2));
        }

    }
    /*public void shortPath(List<NodeData> lst, DWG g)
    {
        NodeData current;
        NodeData next;
        int i=0;
        while (i < lst.size()) {
            current=lst.get(i);
            double srcX = current.getLocation().x();
            srcX = ((srcX - minX) * X) + 15;
            double srcY = current.getLocation().y();
            srcY = ((srcY - minY) * Y) + 15;
            i++;
            next=lst.get(i);
            double destX = next.getLocation().x();
            destX = ((destX - minX) * X) + 15;
            double destY = next.getLocation().y();
            destY = ((destY - minY) * Y) + 15;
            g.setColor(new Color(74, 7, 7, 255));
            g.drawLine( (int) srcX, (int) srcY, (int) destX, (int) destY);
            drawArrowLine(g, (int) srcX, (int) srcY, (int) destX, (int) destY, 15, 10);
        }*/

    //this function is taken from https://stackoverflow.com/questions/2027613/how-to-draw-a-directed-arrow-line-in-java
    /**
     * Draw an arrow line between two points.
     * @param g the graphics component.
     * @param x1 x-position of first point.
     * @param y1 y-position of first point.
     * @param x2 x-position of second point.
     * @param y2 y-position of second point.
     * @param d  the width of the arrow.
     * @param h  the height of the arrow.
     */
    private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
        int dx = x2 - x1, dy = y2 - y1;
        double distance = Math.sqrt(dx * dx + dy * dy);
        double xm = distance - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / distance, cos = dx / distance;

        x = xm * cos - ym * sin + x1;
        ym = xm * sin + ym * cos + y1;
        xm = x;

        x = xn * cos - yn * sin + x1;
        yn = xn * sin + yn * cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 3);
    }


}
