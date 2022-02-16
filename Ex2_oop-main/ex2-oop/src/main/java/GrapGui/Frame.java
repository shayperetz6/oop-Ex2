package GrapGui;

import api.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Frame extends JFrame implements KeyListener, ActionListener {
    Panel panel;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu editMenu;
    JMenu algoMenu;
    JMenuItem loadItem;
    JMenuItem saveItem;
    JMenuItem exitItem;
    JMenuItem delete_nodeItem;
    JMenuItem delete_edgeItem;
    JMenuItem add_nodeItem;
    JMenuItem add_edgeItem;
    JMenuItem short_pathItem;
    JMenuItem centerItem;
    JMenuItem tspItem;
    DWG graph;
    algo_dwg ag;
    public Frame(DWG g)
    {
        super();
        graph=g;
        panel= new Panel(g);
        this.add(panel);
        this.addKeyListener(this);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Graph-GUI");
        menuBar=new JMenuBar();
        fileMenu=new JMenu("File");
        editMenu =new JMenu("Delete");
        algoMenu=new JMenu("Algo");
        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");
        delete_nodeItem=new JMenuItem("DeleteNode");
        delete_edgeItem=new JMenuItem("DeleteEdge");
        delete_nodeItem=new JMenuItem("AddNode");
        delete_edgeItem=new JMenuItem("AddEdge");
        short_pathItem=new JMenuItem("short pat");
        centerItem=new JMenuItem("center");
        tspItem=new JMenuItem("tsp");

        tspItem.addActionListener(this);
        centerItem.addActionListener(this);
        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        delete_nodeItem.addActionListener(this);
        delete_edgeItem.addActionListener(this);
        delete_nodeItem.addActionListener(this);
        delete_edgeItem.addActionListener(this);
        short_pathItem.addActionListener(this);

        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        editMenu.add(delete_nodeItem);
        editMenu.add(delete_edgeItem);
        editMenu.add(add_nodeItem);
        editMenu.add(add_edgeItem);
        algoMenu.add(short_pathItem);
        algoMenu.add(tspItem);
        algoMenu.add(centerItem);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(algoMenu);
        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);
        this.setJMenuBar(menuBar);
        this.setVisible(true);

    }
    public static void main(String[] args){

        algo_dwg graph=new algo_dwg();
        graph.load("resources/data/G1.json");
        JFrame mainFrame=new JFrame(String.valueOf((DWG) graph.getGraph()));
        mainFrame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==loadItem) {

            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("."));
            fc.setDialogTitle("loading file");
            int select = fc.showOpenDialog(null);
            if (select == JFileChooser.APPROVE_OPTION) {
                String path = fc.getSelectedFile().getAbsolutePath();
                this.ag.load(path);
                try {
                    new Frame((DWG) this.ag.getGraph());
                    setVisible(false);
                    dispose();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        if(e.getSource()==saveItem) {
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("."));
            fc.setDialogTitle("saving file");
            int select= fc.showSaveDialog(null);
            if (select == JFileChooser.APPROVE_OPTION) {
                String path = fc.getSelectedFile().getAbsolutePath();
                this.ag.save(path);
            }
        }
        if(e.getSource()==exitItem) {
            System.exit(0);
        }
        if(e.getSource()== centerItem)
        {
            JOptionPane.showMessageDialog(null, "The center of the graph is:" + this.ag.center(), "Center", JOptionPane.PLAIN_MESSAGE);
        }
        if(e.getSource()==short_pathItem){
            String src = JOptionPane.showInputDialog(" Enter the source vertex");
            String dst = JOptionPane.showInputDialog(" Enter the destination vertex");
            String ans="";
            int i=0;
            List<NodeData> list = this.ag.shortestPath(Integer.parseInt(src), Integer.parseInt(dst));
            while (i < list.size()) {
                ans = ans + "-->" + list.get(i).getKey();
                i++;
            }
            JOptionPane.showMessageDialog(null, "The shortest path is:" + ans, "Shortest Path List", JOptionPane.PLAIN_MESSAGE);
          //  panel.shortPath(list,graph);
        }
        if (e.getSource()== tspItem)
        {
            String list = JOptionPane.showInputDialog("please enter list of nodes (like this : '1,8,4')");
            String[] s= list.split(" ");
            List<NodeData> lst=new ArrayList<>();
            int i=0;
            NodeData tmp;
            while (i<s.length)
            {
                lst.add(ag.getGraph().getNode(Integer.parseInt(s[i])));
                i++;
            }
            List<NodeData> tsp_list=ag.tsp(lst);
            i=0;
            String ans="";
            while(i<tsp_list.size())
            {
                ans+= "-->"+tsp_list.get(i).getKey();
                i++;
            }
            JOptionPane.showMessageDialog(null, ans, "tsp", JOptionPane.DEFAULT_OPTION);
        }
        if(e.getSource() == add_nodeItem)
        {

        }



    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

