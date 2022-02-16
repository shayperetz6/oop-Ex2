package ex2;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.algo_dwg;

import javax.swing.*;
import java.awt.*;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        algo_dwg d = new algo_dwg();
        d.load(json_file);
        DirectedWeightedGraph ans = d.getGraph();
        return ans;
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = new algo_dwg();
        ans.load(json_file);
        return ans;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {

        myframe frame = new myframe();
        if(json_file != ""){
            DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
            frame.my_algo = (algo_dwg) alg;
            System.out.println(alg.getGraph().nodeSize());
            frame.graph_panel.set_graph(alg.getGraph());
            frame.graph_panel.update();
        }
        JPanel graph_panel = new JPanel();
        graph_panel.setBackground(Color.GRAY);
        graph_panel.setBounds(0,0,500,500);
        JPanel actions_panel = new JPanel();
        actions_panel.setBackground(Color.black);
        actions_panel.setBounds(500,0,300,500);
        Label  label = new Label("hi");
        label.setForeground(Color.WHITE);
        actions_panel.add(label);
        frame.add(graph_panel);
        frame.add(actions_panel);
    }

    /**
     * geting the graph name from the user or creating a plane graph(empty)
     * @param args
     */
    public static void main(String args[]){
        if(args.length >= 1) {
            System.out.println("the file is " + args[0]);
            runGUI(args[0]);

        }
        else{
            runGUI("");
        }
    }
}