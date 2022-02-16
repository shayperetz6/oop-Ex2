package ex2;

import api.*;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class my_panel extends JPanel {
    private BufferedImage img;
    private Graphics2D g2;
    private DirectedWeightedGraph graph;

    /**
     * create a panel the will be used for grafics
     * @param G
     */
    public my_panel(DirectedWeightedGraph G){
        img = new BufferedImage(500, 500,BufferedImage.TYPE_INT_RGB);
        g2 = (Graphics2D) img.getGraphics();
        this.graph = G;
        this.draw();
    }

    /**
     * set the graph to show
     * @param G
     */
    public void set_graph(DirectedWeightedGraph G){
        this.graph = G;
    }

    /**
     * draw a white color board
     */
    public void draw() {
        g2.setColor(Color.WHITE);
        g2.fillRect(0,0,500,500);
    }

    /**
     * same as draw but with repaint
     */
    public void clear() {
        g2.setColor(Color.WHITE);
        g2.fillRect(0,0,500,500);
        // repaint
        this.repaint();
    }

    /**
     * create a red colored text on the screen
     */
    public void fillred(){
        g2.setColor(Color.RED);
        g2.setFont(new Font("Calibri", Font.BOLD, 20));
        g2.drawString("the graph is not conncted",0,30);
        g2.setFont(new Font("Calibri", Font.BOLD, 15));
        this.repaint();
    }
    /**
     * create a green colored text on the screen
     */
    public void fillgreen(){
        g2.setColor(Color.GREEN);
        g2.setFont(new Font("Calibri", Font.BOLD, 20));
        g2.drawString("the graph is conncted",0,30);
        g2.setFont(new Font("Calibri", Font.BOLD, 15));
        this.repaint();
    }

    /**
     * changes the tag of the edges between thos nodes
     * @param change
     */
    public void change_edges( List<NodeData> change){
        int len=change.size();
        for(int i =0;i<len-1;i++){
            Iterator<EdgeData> ei = graph.edgeIter(change.get(i).getKey());
            while (ei.hasNext()){
                Edge_data e = (Edge_data) ei.next();
                if(e.getDest() == change.get(i+1).getKey()){
                    e.setTag(1);
                }
            }
        }
    }

    /**
     * set the tag of those nodes edges to 0
     */
    public void zero_edges(){
            Iterator<EdgeData> ei = graph.edgeIter();
            while (ei.hasNext()){
                Edge_data e = (Edge_data) ei.next();
                e.setTag(0);
            }
    }

    /**
     * update the grafics to a new graph
     * drawing each part of the graph from the start
     */
    public void update(){
        //call drawing method

        g2.setStroke(new BasicStroke(3));
        DWG G = (DWG)this.graph;
        Iterator<NodeData> iter1 = G.nodeIter();
        double max_x = 0;
        double max_y = 0;
        double min_x = Double.MAX_VALUE;
        double min_y = Double.MAX_VALUE;
        while (iter1.hasNext()){
            Node_data curr = (Node_data)iter1.next();
            if(curr.getLocation().x()>max_x){
                max_x = (curr.getLocation().x());
            }
            else if(curr.getLocation().x()<min_x){
                min_x = curr.getLocation().x();
            }
            if(curr.getLocation().y()>max_y){
                max_y = curr.getLocation().y();
            }

            else if(curr.getLocation().y()<min_y){
                min_y = curr.getLocation().y();
            }
        }
        double abs_x = Math.abs(max_x-min_x);
        double abs_y = Math.abs(max_y-min_y);
        double scale_x =380/abs_x;
        double scale_y =380/abs_y;
        if(G.nodeSize() == 1){
            scale_y = 1;
            scale_x =1;
        }

        Iterator<EdgeData> iter2 = G.edgeIter();
        while (iter2.hasNext()){
            Edge_data curr = (Edge_data) iter2.next();
            Node_data src = (Node_data) G.getNode(curr.getSrc());
            Node_data dst = (Node_data) G.getNode(curr.getDest());
            g2.setColor(Color.BLACK);
            double src_x = Math.abs(src.getLocation().x()-min_x)*scale_x;
            double src_y = Math.abs(src.getLocation().y()-min_y)*scale_y;

            double dst_x = Math.abs(dst.getLocation().x()-min_x)*scale_x;
            double dst_y = Math.abs(dst.getLocation().y()-min_y)*scale_y;
            if(curr.getTag() != 0){
                g2.setColor(Color.BLUE);
            }
            g2.drawLine((int)(src_x)+40,(int)(src_y)+40,
                    (int)(dst_x)+30,(int)(dst_y)+30);

        }
        // repaint
        Iterator<NodeData> iter = G.nodeIter();
        while (iter.hasNext()){
            Node_data curr = (Node_data)iter.next();
            g2.setColor(Color.RED);
            if(curr.getTag() == 1){
                g2.setColor(Color.BLUE);
            }
            double this_x = Math.abs(curr.getLocation().x()-min_x)*scale_x;
            double this_y = Math.abs(curr.getLocation().y()-min_y)*scale_y;

            g2.drawString(""+curr.getKey(),(int)(this_x)+30,(int)(this_y)+30);
            g2.drawOval((int)((this_x))+30,(int)((this_y))+30,20,20);
        }

        this.repaint();
    }

    /**
     * the paint component of this panel
     * @param g
     */
    public void paintComponent(Graphics g){
        g.drawImage(img,0,0,null);

    }
}
