
import javax.swing.*;
import java.awt.event.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dibla
 */

public class Gioco extends JApplet implements KeyListener{ 
    private Campo b;
    private Timer frameTimer;
    private long tempoAttuale,timer,titleTimer;
    
    public Gioco(){
        b = new Campo(); 
        b.requestFocus();
        
        JFrame f=new JFrame(); 
        f.setSize(500,500);
        f.setTitle("Pac man");
        f.setIconImage(new javax.swing.ImageIcon(getClass().getResource("images\\icon.png")).getImage());
        
        f.add(b);
        
        b.addKeyListener(this);  
        
        f.setVisible(true);
        f.setResizable(false);
        
        
        timer = -1;
        titleTimer = -1;
        b.nuovo=1;
        
        partita(true);

        frameTimer = new Timer(16,new ActionListener(){
            public void actionPerformed(ActionEvent e){
                partita(false);
            }
        });
 
        frameTimer.start();

        b.requestFocus();
    }

    
    public void repaint(){
        if (b.p.teletrasporto){
            b.repaint(b.p.ultimoX-20,b.p.ultimoY-20,80,80);
            b.p.teletrasporto=false;
        }
        b.repaint(0,0,600,20);
        b.repaint(0,420,600,40);
        b.repaint(b.p.x-20,b.p.y-20,80,80);
        b.repaint(b.blinky.x-20,b.blinky.y-20,80,80);
        b.repaint(b.clyde.x-20,b.clyde.y-20,80,80);
        b.repaint(b.inky.x-20,b.inky.y-20,80,80);
        b.repaint(b.pinky.x-20,b.pinky.y-20,80,80);
    }

    
    public void partita(boolean nuovaPartita){
        
        if (!b.menùPrincipale && !b.schermataVittoria && !b.schermataSconfitta){
            timer = -1;
            titleTimer = -1;
        }
        
        if (b.morto>0){
            b.repaint();
            return;
        }

        nuovaPartita = nuovaPartita || (b.nuovo !=0);

        if(b.menùPrincipale){
            if(titleTimer == -1)
                titleTimer = System.currentTimeMillis();
            tempoAttuale = System.currentTimeMillis();
            if (tempoAttuale - titleTimer>= 5000){
                b.menùPrincipale = false;
                titleTimer = -1;
            }
            b.repaint();
            return;
        }
        
        else if (b.schermataVittoria || b.schermataSconfitta){
            if (timer == -1)
                timer = System.currentTimeMillis();
            tempoAttuale = System.currentTimeMillis();
            
            if (tempoAttuale - timer>= 5000){
                b.schermataVittoria = false;
                b.schermataSconfitta = false;
                b.menùPrincipale = true;
                timer = -1;
            }
            b.repaint();
            return;
        }


        if (!nuovaPartita){
            b.p.movimentoPacman();
            b.blinky.movimentoFantasmino(); 
            b.clyde.movimentoFantasmino(); 
            b.inky.movimentoFantasmino(); 
            b.pinky.movimentoFantasmino(); 
            
            b.p.aggiornaPalline();
            b.blinky.aggiornaPalline();
            b.clyde.aggiornaPalline();
            b.inky.aggiornaPalline();
            b.pinky.aggiornaPalline();
        }

        if (b.fermo || nuovaPartita){
            frameTimer.stop();

            while (b.morto >0)
                partita(false);
            

            b.p.direzioneAttuale='L';
            b.p.direzioneDesiderata='L';
            b.p.x = 200;
            b.p.y = 300;
            b.blinky.x = 180;
            b.blinky.y = 180;
            b.clyde.x = 200;
            b.clyde.y = 180;
            b.inky.x = 220;
            b.inky.y = 180;
            b.pinky.x = 220;
            b.pinky.y = 180;

            b.repaint(0,0,600,600);

            b.fermo=false;
            frameTimer.start();
        }
        
        else
            repaint(); 
        
      }  

    
    
    public void keyPressed(KeyEvent e){
        if(b.menùPrincipale){
            b.menùPrincipale = false;
            return;
        }
        else if (b.schermataVittoria || b.schermataSconfitta){
            b.menùPrincipale = true;
            b.schermataVittoria = false;
            b.schermataSconfitta = false;
            return;
        }
        
        

       
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                b.p.direzioneDesiderata='L';
                break;     
            case KeyEvent.VK_RIGHT:
                b.p.direzioneDesiderata='R';
                break;     
            case KeyEvent.VK_UP:
                b.p.direzioneDesiderata='U';
                break;     
            case KeyEvent.VK_DOWN:
                b.p.direzioneDesiderata='D';
                break;     
        }

        repaint();
    }

    
    
    public static void main(String args[]) {
          Gioco p = new Gioco();
    }

    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
  
}
