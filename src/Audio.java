
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dibla
 */


/* This class controls all sound effects*/
public class Audio{
    
    Clip mangiata;
    Clip inizioPartita;
    Clip morte;
    
    boolean fermo;
       

    
    public Audio(){
        fermo=true; 
        URL url;
        AudioInputStream audioIn;
        
        try{
            
            url = this.getClass().getClassLoader().getResource("sounds/nomnom.wav");
            audioIn = AudioSystem.getAudioInputStream(url);
            mangiata = AudioSystem.getClip();
            mangiata.open(audioIn);
            
            
            url = this.getClass().getClassLoader().getResource("sounds/newGame.wav");
            audioIn = AudioSystem.getAudioInputStream(url);
            inizioPartita = AudioSystem.getClip();
            inizioPartita.open(audioIn);
            
            
            url = this.getClass().getClassLoader().getResource("sounds/death.wav");
            audioIn = AudioSystem.getAudioInputStream(url);
            morte = AudioSystem.getClip();
            morte.open(audioIn);

        }
        catch(Exception e){}
    }
    
    public void nomNom(){
        
        if (!fermo)
          return;

        fermo=false;
        mangiata.stop();
        mangiata.setFramePosition(0);
        mangiata.loop(Clip.LOOP_CONTINUOUSLY);
    }

    
    public void nomNomStop(){
        fermo=true;
        mangiata.stop();
        mangiata.setFramePosition(0);
    }
    
    
    public void newGame(){
        inizioPartita.stop();
        inizioPartita.setFramePosition(0);
        inizioPartita.start();
    }
    
    
    public void death(){
        morte.stop();
        morte.setFramePosition(0);
        morte.start();
    }
}

