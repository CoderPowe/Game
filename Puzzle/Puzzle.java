import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Puzzle{
    public int freeSpacePosition;
    final public static int POSITION[][]={
        {150,50},{210,50},{270,50},
        {150,110},{210,110},{270,110},
        {150,170},{210,170},{270,170}
    };
    final public static int [][] Adjacent={
        {0,1,3},{1,0,2,4},{2,1,5},{3,0,4,6},
        {4,1,3,5,7},{5,2,4,8},{6,3,7},{7,4,6,8},
        {8,5,7}
    };
    public static int [] Button_Position=new int[8];
    public Puzzle(){
        this.freeSpacePosition=8;
        for(int i=0;i<8;i++){
            Button_Position[i]=i;
        }
    }
    public static int [] getAdjacent(int buttonLocation){
        int len=Adjacent[buttonLocation].length-1;
        int []array_to_return=new int[len];
        for(int i=0;i<len;i++){
            array_to_return[i]=Adjacent[buttonLocation][i+1];
        }
        return array_to_return;
    }
    public static boolean checkOver(){
        boolean res=true;
        for(int i=0;i<8;i++)
        {
            if(Button_Position[i]!=i){
                res=false;
                break;
            }
        }
        return res;
    }
    public static void main(String [] args){
        JFrame fr=new JFrame("Puzzle Game");
        fr.setSize(500,400);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setLocation(400,100);
        fr.setResizable(false);
        Puzzle obj=new Puzzle();
        JButton[] button=new JButton[8];
        final int Button_Width=60;
        final int Button_Height=60;
        Font f=new Font("Arial",Font.PLAIN,30);
        for(int i=0;i<8;i++){
            button[i]=new JButton();
            button[i].setBounds(POSITION[i][0],POSITION[i][1],Button_Width,Button_Height);
            button[i].setText(Integer.toString(i+1));
            button[i].setFont(f);
            button[i].setFocusPainted(false);
            fr.add(button[i]);
        }
        JButton refresh=new JButton("Reset");
        refresh.setBounds(200,300,100,30);
        Font f2=new Font("Arial",Font.PLAIN,20);
        refresh.setFont(f2);
        refresh.setForeground(Color.BLACK);
        refresh.setBackground(Color.PINK);
       for(int i=0;i<8;i++){
        final int index=i;
        button[index].addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
            int [] adjacent=getAdjacent(Button_Position[index]);
            for(int i=0;i<adjacent.length;i++)
            {
                if(adjacent[i] == obj.freeSpacePosition)
                {
                    int temp=obj.freeSpacePosition;
                    obj.freeSpacePosition=Button_Position[index];
                    Button_Position[index]=temp;
                    button[index].setBounds(POSITION[temp][0],POSITION[temp][1],Button_Width,Button_Height);
                }
            }
           if(checkOver())
           JOptionPane.showMessageDialog(null,"Game over! Puzzle solved!");
           }
        });
       }
        refresh.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 obj.freeSpacePosition=8;
                 for(int i=0;i<8;i++){
                      Button_Position[i]=i;
                   }
                 for(int i=0;i<8;i++){
                    button[i].setBounds(POSITION[Button_Position[i]][0],POSITION[Button_Position[i]][1],Button_Width,Button_Height);
                   }
             }
        });
        fr.add(refresh);
        fr.setLayout(null);
        fr.setVisible(true);
     }
}