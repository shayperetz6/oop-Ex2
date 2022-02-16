package ex2;

import javax.swing.*;
import java.awt.*;

public class graph_guui {
    public static void main(String args[]){
        myframe frame = new myframe();
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
}
