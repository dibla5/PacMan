
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dibla
 */
 public class Campo extends JPanel{
    Image pacmanImage,pacmanSuImage,pacmanGiùImage,pacmanSinistraImage,pacmanDestraImage,blinky1,clyde1,inky1,pinky1,blinky2,clyde2,inky2,pinky2,viteImage,immagineMenù; 
    Pacman p;
    Fantasmino blinky,clyde,pinky,inky;
    private Audio sounds;
    private long timer,tempoAttuale;
    int morto,punteggioAttuale,punteggioPiùAlto,numeroDiVite,grandezzaBlocco,max,nuovo,i,j,ultimaPallinaMangiataX,ultimaPallinaMangiataY,frame,tempo;
    private boolean eliminaPunteggioPiùAlto;
    boolean fermo;
    private boolean[][] mappaPacmanFantasmi,mappaPalline;
    
    boolean persoUnaVita;
    
    boolean menùPrincipale;
    boolean schermataVittoria;
    boolean schermataSconfitta;
    
    public Campo(){
        pacmanImage = new ImageIcon("src\\images\\pacman.jpg").getImage();
        pacmanSuImage = new ImageIcon("src\\images\\pacmanup.jpg").getImage();
        pacmanGiùImage = new ImageIcon("src\\images\\pacmandown.jpg").getImage(); 
        pacmanSinistraImage = new ImageIcon("src\\images\\pacmanleft.jpg").getImage(); 
        pacmanDestraImage = new ImageIcon("src\\images\\pacmanright.jpg").getImage(); 
        blinky1 = new ImageIcon("src\\images\\ghost10.jpg").getImage(); 
        clyde1 = new ImageIcon("src\\images\\ghost20.jpg").getImage(); 
        inky1 = new ImageIcon("src\\images\\ghost30.jpg").getImage(); 
        pinky1 = new ImageIcon("src\\images\\ghost40.jpg").getImage(); 
        blinky2 = new ImageIcon("src\\images\\ghost11.jpg").getImage(); 
        clyde2 = new ImageIcon("src\\images\\ghost21.jpg").getImage(); 
        inky2 = new ImageIcon("src\\images\\ghost31.jpg").getImage(); 
        pinky2 = new ImageIcon("src\\images\\ghost41.jpg").getImage(); 
        viteImage = new ImageIcon("src\\images\\heart.png").getImage(); 
        immagineMenù = new ImageIcon("src\\images\\titleScreen.jpg").getImage();
        
        
        mappaPacmanFantasmi = new boolean[20][20];
        mappaPalline = new boolean[20][20];
        
        p = new Pacman(200,300);
        blinky = new Fantasmino(180,180);
        clyde = new Fantasmino(200,180);
        inky = new Fantasmino(220,180);
        pinky = new Fantasmino(220,180);
        
        morto=0;
        i=0;
        j=0;
        numeroDiVite=2;
        punteggioAttuale=0;
        frame = 0;
        persoUnaVita=false;
        
        tempoAttuale = System.currentTimeMillis();
        timer = System.currentTimeMillis();
        tempo = 0;
        
        eliminaPunteggioPiùAlto= false;
        fermo=false;
        max=400;
        grandezzaBlocco=20;
        nuovo=0;
        ultimaPallinaMangiataX = 0;
        ultimaPallinaMangiataY = 0;
        menùPrincipale = true;
        schermataVittoria = false;
        schermataSconfitta = false;
        sounds = new Audio();
    }
    
    
    
    public void disegnaCampo(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,600,600);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,420,420);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,20,600);
        g.fillRect(0,0,600,20);
        g.setColor(Color.decode("#f40000"));
        g.drawRect(19,19,382,382);
        
        g.setColor(Color.decode("#ff9700"));

        g.fillRect(40,40,60,20);
          aggiornaMappa(40,40,60,20);
        g.fillRect(120,40,60,20);
          aggiornaMappa(120,40,60,20);
        g.fillRect(200,20,20,40);
          aggiornaMappa(200,20,20,40);
        g.fillRect(240,40,60,20);
          aggiornaMappa(240,40,60,20);
        g.fillRect(320,40,60,20);
          aggiornaMappa(320,40,60,20);
        g.fillRect(40,80,60,20);
          aggiornaMappa(40,80,60,20);
        g.fillRect(160,80,100,20);
          aggiornaMappa(160,80,100,20);
        g.fillRect(200,80,20,60);
          aggiornaMappa(200,80,20,60);
        g.fillRect(320,80,60,20);
          aggiornaMappa(320,80,60,20);

        g.fillRect(20,120,80,60);
          aggiornaMappa(20,120,80,60);
        g.fillRect(320,120,80,60);
          aggiornaMappa(320,120,80,60);
        g.fillRect(20,200,80,60);
          aggiornaMappa(20,200,80,60);
        g.fillRect(320,200,80,60);
          aggiornaMappa(320,200,80,60);

        g.fillRect(160,160,40,20);
          aggiornaMappa(160,160,40,20);
        g.fillRect(220,160,40,20);
          aggiornaMappa(220,160,40,20);
        g.fillRect(160,180,20,20);
          aggiornaMappa(160,180,20,20);
        g.fillRect(160,200,100,20);
          aggiornaMappa(160,200,100,20);
        g.fillRect(240,180,20,20);
          aggiornaMappa(240,180,20,20);
        g.setColor(Color.decode("#ff9700"));


        g.fillRect(120,120,60,20);
          aggiornaMappa(120,120,60,20);
        g.fillRect(120,80,20,100);
          aggiornaMappa(120,80,20,100);
        g.fillRect(280,80,20,100);
          aggiornaMappa(280,80,20,100);
        g.fillRect(240,120,60,20);
          aggiornaMappa(240,120,60,20);

        g.fillRect(280,200,20,60);
          aggiornaMappa(280,200,20,60);
        g.fillRect(120,200,20,60);
          aggiornaMappa(120,200,20,60);
        g.fillRect(160,240,100,20);
          aggiornaMappa(160,240,100,20);
        g.fillRect(200,260,20,40);
          aggiornaMappa(200,260,20,40);

        g.fillRect(120,280,60,20);
          aggiornaMappa(120,280,60,20);
        g.fillRect(240,280,60,20);
          aggiornaMappa(240,280,60,20);

        g.fillRect(40,280,60,20);
          aggiornaMappa(40,280,60,20);
        g.fillRect(80,280,20,60);
          aggiornaMappa(80,280,20,60);
        g.fillRect(320,280,60,20);
          aggiornaMappa(320,280,60,20);
        g.fillRect(320,280,20,60);
          aggiornaMappa(320,280,20,60);

        g.fillRect(20,320,40,20);
          aggiornaMappa(20,320,40,20);
        g.fillRect(360,320,40,20);
          aggiornaMappa(360,320,40,20);
        g.fillRect(160,320,100,20);
          aggiornaMappa(160,320,100,20);
        g.fillRect(200,320,20,60);
          aggiornaMappa(200,320,20,60);

        g.fillRect(40,360,140,20);
          aggiornaMappa(40,360,140,20);
        g.fillRect(240,360,140,20);
          aggiornaMappa(240,360,140,20);
        g.fillRect(280,320,20,40);
          aggiornaMappa(280,320,20,60);
        g.fillRect(120,320,20,60);
          aggiornaMappa(120,320,20,60);
    }
    
    public void resetPartita(){
        for(i=0;i<20;i++){
            for(j=0;j<20;j++){
                mappaPacmanFantasmi[i][j]=true;
                mappaPalline[i][j]=true;
            }
        }

        for(i = 5;i<14;i++){
            for(j = 5;j<12;j++){
                mappaPalline[i][j]=false;
            }
        }
        mappaPalline[9][7] = false;
        mappaPalline[8][8] = false;
        mappaPalline[9][8] = false;
        mappaPalline[10][8] = false;

    }

    public void aggiornaMappa(int x,int y, int width, int height){
        for (int i =x/grandezzaBlocco; i<x/grandezzaBlocco+width/grandezzaBlocco;i++){
            for (int j=y/grandezzaBlocco;j<y/grandezzaBlocco+height/grandezzaBlocco;j++){
                mappaPacmanFantasmi[i-1][j-1]=false;
                mappaPalline[i-1][j-1]=false;
            }
        }
    } 
   
    public void settaggioPunteggioPiùAlto(){
        File file = new File("punteggio.txt");
        Scanner sc;
        try{
            sc = new Scanner(file);
            punteggioPiùAlto = sc.nextInt();
            sc.close();
        }
        catch(Exception e){}
    }

    public void aggiornaPunteggio(int score){
        PrintWriter out;
        try{
            out = new PrintWriter("punteggio.txt");
            out.println(score);
            out.close();
        }
        catch(Exception e){}
        
        punteggioPiùAlto=score;
        eliminaPunteggioPiùAlto=true;
    }

    public void rimuoviPunteggioPiùAlto(){
        PrintWriter out;
        try{
            out = new PrintWriter("punteggio.txt");
            out.println("0");
            out.close();
        }
        catch(Exception e){}
        
        punteggioPiùAlto=0;
        eliminaPunteggioPiùAlto=true;
    }
    
    public void disegnaPalline(Graphics g){
        g.setColor(Color.YELLOW);
        for (int i=1;i<20;i++){
            for (int j=1;j<20;j++){
                if (mappaPalline[i-1][j-1])
                    g.fillOval(i*20+8,j*20+8,4,4);
            }
        }
        g.fillOval(25,25,10,10);
        g.fillOval(385,25,10,10);
        g.fillOval(25,385,10,10);
        g.fillOval(385,385,10,10);
    }

    public void riempiPalline(int x, int y, Graphics g){
        g.setColor(Color.YELLOW);
        g.fillOval(x*20+28,y*20+28,4,4);
        g.setColor(Color.WHITE);
        g.fillOval(25,25,10,10);
        g.fillOval(385,25,10,10);
        g.fillOval(25,385,10,10);
        g.fillOval(385,385,10,10);
    }

    
    public void disegnaVite(Graphics g){
        g.setColor(Color.BLACK);

        for(i = 0;i<numeroDiVite;i++){
            g.drawImage(viteImage, grandezzaBlocco*(i+1),max+5,grandezzaBlocco,grandezzaBlocco, this);
        }
  }
    public void paint(Graphics g){
        if (morto > 0){
            g.drawImage(pacmanImage,p.x,p.y,Color.BLACK,null);
            g.setColor(Color.BLACK);

            if (morto == 4)
                g.fillRect(p.x,p.y,20,7);
            else if ( morto == 3)
                g.fillRect(p.x,p.y,20,14);
            else if ( morto == 2)
                g.fillRect(p.x,p.y,20,20); 
            else if ( morto == 1)
                g.fillRect(p.x,p.y,20,20); 

            
            if (morto != 1)
                tempo = 100;
            else
                tempo = 2000;
            
            if (tempoAttuale - timer >= tempo){
                morto--;
                timer = tempoAttuale;
                
                if (morto == 0){
                    if (numeroDiVite==-1){
                        if (punteggioAttuale > punteggioPiùAlto)
                            aggiornaPunteggio(punteggioAttuale);
                        schermataSconfitta=true;
                    }
                }
            }
            return;
        }

      
        if (menùPrincipale){
            g.setColor(Color.BLACK);
            g.fillRect(0,0,600,600);
            g.drawImage(immagineMenù,0,0,Color.BLACK,null);

            nuovo = 1;
            return;
        } 

        
        else if (schermataVittoria){
            g.setColor(Color.BLACK);
            g.fillRect(0,0,600,600);
//            g.drawImage(immagineVittoria,0,0,Color.BLACK,null);
            nuovo = 1;
            return;
        }

        else if (schermataSconfitta){
            g.setColor(Color.BLACK);
            g.fillRect(0,0,600,600);
//            g.drawImage(immagineSconfitta,0,0,Color.BLACK,null);
            nuovo = 1;
            return;
        }

        if (eliminaPunteggioPiùAlto){
            g.setColor(Color.BLACK);
            g.fillRect(0,0,600,18);
            g.setColor(Color.YELLOW);
            eliminaPunteggioPiùAlto= false;
            g.drawString("Punteggio attuale: "+(punteggioAttuale)+"          Record: "+punteggioPiùAlto,20,10);
        }

        

        if (nuovo==1){
            resetPartita();
            p = new Pacman(200,300);
            blinky = new Fantasmino(180,180);
            clyde = new Fantasmino(200,180);
            inky = new Fantasmino(220,180);
            pinky = new Fantasmino(220,180);
            punteggioAttuale = 0;
            disegnaCampo(g);
            disegnaPalline(g);
            disegnaVite(g);
            
            p.aggiornaMappaPF(mappaPacmanFantasmi);
            
            p.mappa[9][7]=false; 
            
            blinky.aggiornaMappaPF(mappaPacmanFantasmi);
            clyde.aggiornaMappaPF(mappaPacmanFantasmi);
            inky.aggiornaMappaPF(mappaPacmanFantasmi);
            pinky.aggiornaMappaPF(mappaPacmanFantasmi);

            g.setColor(Color.YELLOW);
            g.drawString("Punteggio attuale: "+(punteggioAttuale)+"          Record: "+punteggioPiùAlto,20,10);
            nuovo++;
        }
        else if (nuovo == 2){
            nuovo++;
        }
        else if (nuovo == 3){
            nuovo++;
            sounds.newGame();
            timer = System.currentTimeMillis();
            return;
        }
        else if (nuovo == 4){
            long currTime = System.currentTimeMillis();
            if (currTime - timer >= 5000){
              nuovo=0;
            }
            else
              return;
        }

        g.copyArea(p.x-20,p.y-20,80,80,0,0);
        g.copyArea(blinky.x-20,blinky.y-20,80,80,0,0);
        g.copyArea(clyde.x-20,clyde.y-20,80,80,0,0);
        g.copyArea(inky.x-20,inky.y-20,80,80,0,0);
        g.copyArea(pinky.x-20,pinky.y-20,80,80,0,0);



        if (p.x == blinky.x && Math.abs(p.y-blinky.y) < 10)
            persoUnaVita=true;
        else if (p.x == clyde.x && Math.abs(p.y-clyde.y) < 10)
            persoUnaVita=true;
        else if (p.x == inky.x && Math.abs(p.y-inky.y) < 10)
            persoUnaVita=true;
        else if (p.x == pinky.x && Math.abs(p.y-pinky.y) < 10)
            persoUnaVita=true;
        else if (p.y == blinky.y && Math.abs(p.x-blinky.x) < 10)
            persoUnaVita=true;
        else if (p.y == clyde.y && Math.abs(p.x-clyde.x) < 10)
            persoUnaVita=true;
        else if (p.y == inky.y && Math.abs(p.x-inky.x) < 10)
            persoUnaVita=true;
        else if (p.y == pinky.y && Math.abs(p.x-pinky.x) < 10)
            persoUnaVita=true;

        if (persoUnaVita && !fermo){
            morto=4;

            sounds.death();
            sounds.nomNomStop();

            numeroDiVite--;
            fermo=true;
      //      disegnaVite(g);
            timer = System.currentTimeMillis();
        }

        g.setColor(Color.BLACK);
        g.fillRect(p.ultimoX,p.ultimoY,20,20);
        g.fillRect(blinky.ultimoX,blinky.ultimoY,20,20);
        g.fillRect(clyde.ultimoX,clyde.ultimoY,20,20);
        g.fillRect(inky.ultimoX,inky.ultimoY,20,20);
        g.fillRect(pinky.ultimoX,pinky.ultimoY,20,20);

       
        if ( mappaPalline[p.pallinaX][p.pallinaY] && nuovo!=2 && nuovo !=3){
            ultimaPallinaMangiataX = p.pallinaX;
            ultimaPallinaMangiataY = p.pallinaY;

            sounds.nomNom();

            p.pallineMangiate++;

            mappaPalline[p.pallinaX][p.pallinaY]=false;

            punteggioAttuale += 50;

            g.setColor(Color.BLACK);
            g.fillRect(0,0,600,20);
            g.setColor(Color.YELLOW);
            g.drawString("Punteggio attuale: "+(punteggioAttuale)+"          Record: "+punteggioPiùAlto,20,10);

            
            if (p.pallineMangiate == 173){
                  if (punteggioAttuale > punteggioPiùAlto)
                      aggiornaPunteggio(punteggioAttuale);
                  schermataVittoria = true;

                  return;
            }
        }

        else if ( (p.pallinaX != ultimaPallinaMangiataX || p.pallinaY != ultimaPallinaMangiataY ) || p.fermo){
            sounds.nomNomStop();
        }

        //<editor-fold defaultstate="collapsed" desc="Disegno le palline "calpestate" dai fantasmi">
        if (mappaPalline[blinky.ultimaXPallina][blinky.ultimaYPallina])
            riempiPalline(blinky.ultimaXPallina,blinky.ultimaYPallina,g);
        if (mappaPalline[clyde.ultimaXPallina][clyde.ultimaYPallina])
            riempiPalline(clyde.ultimaXPallina,clyde.ultimaYPallina,g);
        if (mappaPalline[inky.ultimaXPallina][inky.ultimaYPallina])
            riempiPalline(inky.ultimaXPallina,inky.ultimaYPallina,g);
        if (mappaPalline[pinky.ultimaXPallina][pinky.ultimaYPallina])
            riempiPalline(pinky.ultimaXPallina,pinky.ultimaYPallina,g);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Disegno dei fantasmi ed eventuali cambio immagine">
        
        if (blinky.contatoreAnimazioni < 5){
            g.drawImage(blinky1,blinky.x,blinky.y,Color.BLACK,null);
            g.drawImage(clyde1,clyde.x,clyde.y,Color.BLACK,null);
            g.drawImage(inky1,inky.x,inky.y,Color.BLACK,null);
            g.drawImage(pinky1,pinky.x,pinky.y,Color.BLACK,null);
            blinky.contatoreAnimazioni++;
        }
        else{
            g.drawImage(blinky2,blinky.x,blinky.y,Color.BLACK,null);
            g.drawImage(clyde2,clyde.x,clyde.y,Color.BLACK,null);
            g.drawImage(inky2,inky.x,inky.y,Color.BLACK,null);
            g.drawImage(pinky2,pinky.x,pinky.y,Color.BLACK,null);
            if (blinky.contatoreAnimazioni >=10)
                blinky.contatoreAnimazioni=0;
            else
                blinky.contatoreAnimazioni++;
        }
        
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Disegno del pacman ed eventuali cambi immagine">
       
        if (p.contatoreAnimazioni < 5)
        {
         
          g.drawImage(pacmanImage,p.x,p.y,Color.BLACK,null);
        }
        else
        {
         
          if (p.contatoreAnimazioni >=10)
            p.contatoreAnimazioni=0;

          switch(p.direzioneAttuale)
          {
            case 'L':
               g.drawImage(pacmanSinistraImage,p.x,p.y,Color.BLACK,null);
               break;     
            case 'R':
               g.drawImage(pacmanDestraImage,p.x,p.y,Color.BLACK,null);
               break;     
            case 'U':
               g.drawImage(pacmanSuImage,p.x,p.y,Color.BLACK,null);
               break;     
            case 'D':
               g.drawImage(pacmanGiùImage,p.x,p.y,Color.BLACK,null);
               break;      
          }
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Disegno del bordo bianco (per sicurezza)">
        g.setColor(Color.WHITE);
        g.drawRect(19,19,382,382);
        //</editor-fold>
  }
}