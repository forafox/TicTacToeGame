package orgg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 04.01.2023 16:16
 */
public class TicTacToe implements ActionListener {
    ArrayList<String> listWin = new ArrayList<>(Arrays.asList("012","345","678","036","147","258","048","246"));
    Random random=new Random();
    JFrame frame= new JFrame();
    JPanel title_panel =  new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;
    TicTacToe(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25,25,25));
        textfield.setForeground(new Color(25,255,0));
        textfield.setFont(new Font("Ink Free",Font.BOLD,75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,100);

        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new  Color(150,150,150));

        title_panel.add(textfield);
        frame.add(title_panel,BorderLayout.NORTH);
        frame.add(button_panel);

        for(int i=0;i<9;i++){
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        firstTurn();
    }
    public void actionPerformed(ActionEvent e) {

        for(int i=0;i<9;i++){
            if(e.getSource()==buttons[i]){
                if(player1_turn){
                    if(buttons[i].getText().equals("")){
                        buttons[i].setForeground(new Color(255,0,0));
                        buttons[i].setText("X");
                        player1_turn=false;
                        textfield.setText("O turn");
                        check();
                    }
                }
                else{
                    if(buttons[i].getText().equals("")){
                        buttons[i].setForeground(new Color(0,0,255));
                        buttons[i].setText("O");
                        player1_turn=true;
                        textfield.setText("X turn");
                        check();
                    }
                }
            }
        }
    }
    public void firstTurn() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if(random.nextInt(2)==0){
            player1_turn=true;
            textfield.setText("X turn");
        }
        else{
            player1_turn=false;
            textfield.setText("O turn");
        }
    }
    public void check(){
        ArrayList<Integer> oTest = new ArrayList<>();
        ArrayList<Integer> xTest = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
           if(buttons[i].getText().equals("X"))
               xTest.add(i);
           if(buttons[i].getText().equals("O"))
               oTest.add(i);
        }
        if(xTest.size()==3) {
            if (listWin.contains(String.valueOf(xTest.get(0)) + xTest.get(1) + xTest.get(2))) {
                whoWins(true,xTest.get(0), xTest.get(1), xTest.get(2));
            }
        }
        if(oTest.size()==3) {
            if (listWin.contains(String.valueOf(oTest.get(0)) + oTest.get(1) + oTest.get(2))) {
                whoWins(false,oTest.get(0), oTest.get(1), oTest.get(2));
            }
        }
        if(oTest.size()==4){
            int an;
            for(int i=0;i<oTest.size();i++){
                an=oTest.get(i);
                oTest.remove(i);
                if (listWin.contains(String.valueOf(oTest.get(0)) + oTest.get(1) + oTest.get(2))) {
                    whoWins(false,oTest.get(0), oTest.get(1), oTest.get(2));
                }
                oTest.add(i,an);
            }
        }
        if(xTest.size()==4){
            int an;
            for(int i=0;i<xTest.size();i++){
                an=xTest.get(i);
                xTest.remove(i);
                if (listWin.contains(String.valueOf(xTest.get(0)) + xTest.get(1) + xTest.get(2))) {
                    whoWins(true,xTest.get(0), xTest.get(1), xTest.get(2));
                }
                xTest.add(i,an);
            }
        }
        if(xTest.size()+oTest.size()==9) draw();
    }
    public void whoWins(boolean xPlayerWin,int a,int b,int c){
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        if(xPlayerWin)
            textfield.setText("X wins");
        else
            textfield.setText("O wins");
    }
    public void draw(){
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("Draw!");
    }

}
