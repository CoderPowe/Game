import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
public class TicTacToe
{
    
    public static boolean over=false;
    public static int winner=0;
    public static String currentState="X";
    final public static int POSITION[][]={
        {150,50},{210,50},{270,50},
        {150,110},{210,110},{270,110},
        {150,170},{210,170},{270,170}
    };
    public static int [][] matchedSet={
      {0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8},
      {0,1,2},{3,4,5},{6,7,8}
    };
    
    public static boolean checkOver(JButton btn[]){
     boolean result=false;
      for(int i=0;i<matchedSet.length;i++){
        if((btn[matchedSet[i][0]].getText().equals("X") || btn[matchedSet[i][0]].getText().equals("O")) && btn[matchedSet[i][0]].getText().equals(btn[matchedSet[i][1]].getText()) && btn[matchedSet[i][0]].getText().equals(btn[matchedSet[i][2]].getText()))
      {
         btn[matchedSet[i][0]].setBackground(Color.YELLOW);
         btn[matchedSet[i][1]].setBackground(Color.YELLOW);
         btn[matchedSet[i][2]].setBackground(Color.YELLOW);
         if(btn[matchedSet[i][0]].getText().equals("X"))
         winner=1;
         else if(btn[matchedSet[i][0]].getText().equals("O"))
         winner=2;
         result=true;
         break;
      }
      }
      return result;
    }
    public static boolean completeAll(JButton btn[]){
      boolean result=true;
      for(int i=0;i<btn.length;i++){
        if(btn[i].getText().equals(""))
        {
          result=false;
          break;
        }
      }
      return result;
    }
    public static void changeState(){
      currentState=(currentState.equals("X"))? "O":"X";
    }
    public static void main(String[] args)
    {
         JFrame frame=new JFrame("Tic-Tac-Toe");
         frame.setSize(500,400);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setLocation(400,100);
         frame.setResizable(false);
         JButton b[]=new JButton[9];
         Font f=new Font("Arial",Font.PLAIN,30);
         for(int i=0;i<b.length;i++)
            {
              b[i]=new JButton();
              b[i].setFont(f);
              b[i].setText("");
              b[i].setBounds(POSITION[i][0],POSITION[i][1],60,60);
              b[i].setFocusPainted(false);
            }
        JLabel plate=new JLabel();
        plate.setBounds(600,50,900,25);
        plate.setForeground(Color.BLACK);
        plate.setText("TIC-TAC-TOE");
        Font name=new Font("Algerian", Font.PLAIN, 32);
        plate.setFont(name);
        for(int loop=0;loop<b.length;loop++){
            final int index=loop;
            b[index].addActionListener(new ActionListener(){
                  public void actionPerformed(ActionEvent e){
               if(!over){
                   if(b[index].getText().equals("")){
                        if(currentState.equals("X"))
                         b[index].setText("X");
                        else 
                         b[index].setText("O");
                        changeState();
                      }
                    }
                if(completeAll(b)){
                   if(checkOver(b)){
                     String mes="Player "+Integer.toString(winner)+" wins !!";
                     JOptionPane.showMessageDialog(null,mes);
                  }
                   else{
                     JOptionPane.showMessageDialog(null,"Match draw !! None of you wins!!");
                   }
                   over=true;
      }
      else{
         if(checkOver(b)){
               String mes="Player "+Integer.toString(winner)+" wins !!";
               JOptionPane.showMessageDialog(null,mes);
               over=true;
         }
      }
   }});
}
        JButton refresh=new JButton("Reset");
        refresh.setBounds(200,300,100,30);
        Font font=new Font("Arial",Font.PLAIN,24);
        refresh.setFont(font);
        refresh.setForeground(Color.BLACK);
        refresh.setBackground(Color.PINK);
        refresh.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e)
                {
                  for(int i=0;i<b.length;i++){
                    b[i].setText("");
                    b[i].setBackground(null);
                  }
                  over=false;
                  currentState="X";
                }
});
for(int i=0;i<b.length;i++)
{
    frame.add(b[i]);
}
   frame.add(refresh);
   frame.add(plate);
   frame.setLayout(null);
   frame.setVisible(true);
}
}