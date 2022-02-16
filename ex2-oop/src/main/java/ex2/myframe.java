package ex2;

import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class myframe extends JFrame implements ActionListener {
    JButton addNode;
    JTextField nodedata;
    JButton addEdge;
    JTextField edgedata;
    JButton delNode;
    JTextField delnodedata;
    JButton delEdge;
    JTextField deledgedata;

    JButton start_sp;
    JTextField sp_data;
    JButton start_spd;
    JTextField spd_data;
    JButton start_tsp;
    JTextField tsp_data;

    JMenuItem load;
    JMenuItem save;
    JMenuItem algo_connected;
    JMenuItem algo_center;
    JMenuItem show;
    algo_dwg my_algo = new algo_dwg();

    my_panel graph_panel;

    /**starting the gui
     * creates the menu , panels , labels and more
     * alse set action lisetners
     */
    public myframe(){
        DWG G = new DWG();
        my_algo.init(G);

        this.setTitle("graph gui");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1100,500);
        this.setLayout(null);
        this.setResizable(false);

        JMenuBar menu = new JMenuBar();

        JMenu files = new JMenu("files");
        JMenu algo = new JMenu("algorithms");

        load = new JMenuItem("load json graph");
        save = new JMenuItem("save json graph");
        algo_connected = new JMenuItem("run - connected");
        algo_center = new JMenuItem("run - center");
        show = new JMenuItem("show graph");

        load.addActionListener(this);
        save.addActionListener(this);
        algo_center.addActionListener(this);
        algo_connected.addActionListener(this);
        show.addActionListener(this);
        files.add(load);
        files.add(save);
        algo.add(algo_center);
        algo.add(algo_connected);
        algo.add(show);
        menu.add(files);
        menu.add(algo);
        this.setJMenuBar(menu);


        graph_panel = new my_panel(my_algo.getGraph());
        graph_panel.setBounds(0,0,500,500);

        JPanel actions_panel = new JPanel();
        actions_panel.setLayout(null);
        actions_panel.setBackground(Color.black);
        actions_panel.setBounds(500,0,600,500);

        this.show_algo_staf(actions_panel);
        this.show_nodes_staf(actions_panel);

        this.add(graph_panel);
        this.add(actions_panel);
        this.setVisible(true);

    }

    /**
     * drawing to the screen the actions of algortihems
     * shotrest path/dist and tsp
     * @param actions_panel
     */
    public void show_algo_staf(JPanel actions_panel){
        //set algorithemm things
        JLabel  label5 = new JLabel("Short P:");
        label5.setFont(new Font("Calibri", Font.BOLD, 20));
        label5.setForeground(Color.WHITE);
        label5.setBounds(310,0,70,50);

        sp_data = new JTextField("int src,int dst");
        sp_data.setBounds(390,10,150,30);

        start_sp = new JButton();
        start_sp.setBounds(310,50,70,30);
        start_sp.addActionListener(this);
        start_sp.setText("start");
        start_sp.setFocusable(false);

        actions_panel.add(label5);
        actions_panel.add(start_sp);
        actions_panel.add(sp_data);

        JLabel  label6 = new JLabel("SPD:");
        label6.setFont(new Font("Calibri", Font.BOLD, 20));
        label6.setForeground(Color.WHITE);
        label6.setBounds(310,100,70,50);

        spd_data = new JTextField("int src,int dsr");
        spd_data.setBounds(390,110,150,30);

        start_spd = new JButton();
        start_spd.setBounds(310,150,70,30);
        start_spd.addActionListener(this);
        start_spd.setText("start");
        start_spd.setFocusable(false);

        actions_panel.add(label6);
        actions_panel.add(start_spd);
        actions_panel.add(spd_data);

        JLabel  label7 = new JLabel("tsp:");
        label7.setFont(new Font("Calibri", Font.BOLD, 20));
        label7.setForeground(Color.WHITE);
        label7.setBounds(310,200,70,50);

        tsp_data = new JTextField("int node,int node,int node.....");
        tsp_data.setBounds(390,210,150,30);

        start_tsp = new JButton();
        start_tsp.setBounds(310,250,70,30);
        start_tsp.addActionListener(this);
        start_tsp.setText("start");
        start_tsp.setFocusable(false);

        actions_panel.add(label7);
        actions_panel.add(start_tsp);
        actions_panel.add(tsp_data);


    }

    /**
     * draw the node and edges actions and prompts
     * add
     * delete
     * @param actions_panel
     */
    public void show_nodes_staf(JPanel actions_panel){
        //set the node add
        JLabel  label = new JLabel("node:");
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        label.setBounds(10,0,70,50);

        nodedata = new JTextField("double,double,double");
        nodedata.setBounds(90,10,150,30);

        addNode = new JButton();
        addNode.setBounds(10,50,70,30);
        addNode.addActionListener(this);
        addNode.setText("add");
        addNode.setFocusable(false);

        actions_panel.add(label);
        actions_panel.add(addNode);
        actions_panel.add(nodedata);


        // set the edge add
        JLabel  label2 = new JLabel("edge:");
        label2.setFont(new Font("Calibri", Font.BOLD, 20));
        label2.setForeground(Color.WHITE);
        label2.setBounds(10,100,70,50);

        edgedata = new JTextField("int,int,double");
        edgedata.setBounds(90,110,150,30);

        addEdge = new JButton();
        addEdge.setBounds(10,150,70,30);
        addEdge.addActionListener(this);
        addEdge.setText("add");
        addEdge.setFocusable(false);

        actions_panel.add(label2);
        actions_panel.add(addEdge);
        actions_panel.add(edgedata);

        //set the node del
        JLabel  label3 = new JLabel("del node:");
        label3.setFont(new Font("Calibri", Font.BOLD, 20));
        label3.setForeground(Color.WHITE);
        label3.setBounds(10,200,90,50);

        delnodedata = new JTextField("int");
        delnodedata.setBounds(90,210,150,30);

        delNode = new JButton();
        delNode.setBounds(10,250,70,30);
        delNode.addActionListener(this);
        delNode.setText("del");
        delNode.setFocusable(false);

        actions_panel.add(label3);
        actions_panel.add(delNode);
        actions_panel.add(delnodedata);


        // set the edge del
        JLabel  label4 = new JLabel("del edge:");
        label4.setFont(new Font("Calibri", Font.BOLD, 20));
        label4.setForeground(Color.WHITE);
        label4.setBounds(10,300,90,50);

        deledgedata = new JTextField("int,int");
        deledgedata.setBounds(90,310,150,30);

        delEdge = new JButton();
        delEdge.setBounds(10,350,70,30);
        delEdge.addActionListener(this);
        delEdge.setText("del");
        delEdge.setFocusable(false);
        actions_panel.add(label4);
        actions_panel.add(delEdge);
        actions_panel.add(deledgedata);
    }

    /**
     * the action listener part
     * listens to actions of certin alements in the gui
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addNode && my_algo != null){
            String node_to_add = nodedata.getText();
            nodedata.setText("");
            String[] splited_data = node_to_add.split(",");
            if(splited_data.length == 3) {
                double x = 0.0;
                double y = 0.0;
                double z = 0.0;

                try{
                    x = Double.parseDouble(splited_data[0]);
                    y = Double.parseDouble(splited_data[1]);
                    z = Double.parseDouble(splited_data[2]);
                    Node_data n =new Node_data(new Geo_location(x,y,z),0.0,"",0);
                    my_algo.getGraph().addNode(n);
                    graph_panel.set_graph(my_algo.getGraph());
                    graph_panel.clear();
                    graph_panel.update();
                }
                catch (Exception error){
                    nodedata.setText("error");
                }
            }
        }
        else if(e.getSource() == addEdge && my_algo != null){
            String edge_to_add = edgedata.getText();
            edgedata.setText("");
            String[] splited_data = edge_to_add.split(",");
            if(splited_data.length == 3) {
                int x = 0;
                int y = 0;
                double z = 0.0;

                try {
                    x = Integer.parseInt(splited_data[0]);
                    y = Integer.parseInt(splited_data[1]);
                    z = Double.parseDouble(splited_data[2]);
                    my_algo.getGraph().connect(x,y,z);
                    System.out.println("e");
                    graph_panel.set_graph(my_algo.getGraph());
                    graph_panel.clear();
                    graph_panel.update();
                } catch (Exception error) {
                    edgedata.setText("error");
                }
            }
        }
        else if(e.getSource() == delNode && my_algo != null){
            String node_to_del = delnodedata.getText();
            delnodedata.setText("");
            try{
                int x = Integer.parseInt(node_to_del);
                my_algo.getGraph().removeNode(x);
                graph_panel.set_graph(my_algo.getGraph());
                graph_panel.clear();
                graph_panel.update();

            }
            catch (Exception error){
                delnodedata.setText("error");
            }

        }
        else if(e.getSource() == delEdge && my_algo != null){
            String edge_to_del = deledgedata.getText();
            deledgedata.setText("");
            String[] splited_data = edge_to_del.split(",");
            if(splited_data.length == 2) {
                int x = 0;
                int y = 0;

                try {
                    x = Integer.parseInt(splited_data[0]);
                    y = Integer.parseInt(splited_data[1]);
                    my_algo.getGraph().removeEdge(x,y);
                    graph_panel.set_graph(my_algo.getGraph());
                    graph_panel.clear();
                    graph_panel.update();
                } catch (Exception error) {
                    deledgedata.setText("error");
                }
            }
        }
        else if(e.getSource() == algo_center && my_algo != null){
            Node_data n = (Node_data) my_algo.center();
            if(n!=null) {
                graph_panel.clear();
                n.setTag(1);
                graph_panel.set_graph(my_algo.getGraph());
                graph_panel.clear();
                graph_panel.update();
                n.setTag(0);
            }

        }
        else if(e.getSource() == algo_connected && my_algo != null){

                graph_panel.clear();
                if(my_algo.isConnected()){
                    graph_panel.fillgreen();
                }
                else{
                    graph_panel.fillred();
                }
        }
        else if(e.getSource() == start_sp && my_algo != null){
            String data = sp_data.getText();
            sp_data.setText("");
            String[] splited_data = data.split(",");
            if(splited_data.length == 2) {
                int x = 0;
                int y = 0;

                try {
                    x = Integer.parseInt(splited_data[0]);
                    y = Integer.parseInt(splited_data[1]);
                    graph_panel.change_edges(my_algo.shortestPath(x,y));
                    graph_panel.set_graph(my_algo.getGraph());
                    graph_panel.clear();
                    graph_panel.update();
                    graph_panel.zero_edges();
                    sp_data.setText("");
                } catch (Exception error) {
                    error.printStackTrace();
                    sp_data.setText("error");
                }
            }

        }
        else if(e.getSource() == start_spd && my_algo != null){
            String data = spd_data.getText();
            spd_data.setText("");
            String[] splited_data = data.split(",");
            if(splited_data.length == 2) {
                int x = 0;
                int y = 0;

                try {
                    x = Integer.parseInt(splited_data[0]);
                    y = Integer.parseInt(splited_data[1]);
                    spd_data.setText(""+my_algo.shortestPathDist(x,y));
                } catch (Exception error) {
                    error.printStackTrace();
                    spd_data.setText("error");
                }
            }


        }
        else if(e.getSource() == start_tsp && my_algo != null){
            String data = tsp_data.getText();
            tsp_data.setText("");
            String[] splited_data = data.split(",");
            try{
                LinkedList<NodeData> city = new LinkedList<>();
                for(int i =0;i<splited_data.length;i++){
                    city.add(my_algo.getGraph().getNode(Integer.parseInt(splited_data[i])));
                }
                LinkedList<NodeData> city_ans = (LinkedList<NodeData>) my_algo.tsp(city);
                if(city_ans != null){
                    tsp_data.setText(city_ans.toString());
                }
                else {
                    tsp_data.setText("no way");
                }
            }
            catch (Exception error){

                tsp_data.setText("no way");
            }
        }
        else if(e.getSource() == load){
            JFileChooser choosefile = new JFileChooser();
            int open = choosefile.showOpenDialog(null);
            if(open == JFileChooser.APPROVE_OPTION){
                String path = choosefile.getSelectedFile().getAbsolutePath();
                boolean l = my_algo.load(path);
                if(l) {
                    graph_panel.clear();
                    graph_panel.set_graph(my_algo.getGraph());
                    graph_panel.update();
                }
            }
        }
        else if(e.getSource() == save){
            JFileChooser choosefile = new JFileChooser();
            int open = choosefile.showOpenDialog(null);
            if(open == JFileChooser.APPROVE_OPTION && my_algo != null){
                String path = choosefile.getSelectedFile().getAbsolutePath();
                my_algo.save(path);
            }
        }
        else if(e.getSource() == show){
                graph_panel.clear();
                graph_panel.set_graph(my_algo.getGraph());
                graph_panel.update();

        }
    }
}
