
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dibla
 */
public class Fantasmino extends Personaggi{
    char direzioneFantasmino,versoContrario,nuovaDirezione;
    int ultimoX,ultimoY,x,y,pallinaX,pallinaY,xTemporanea,yTemporanea,ultimaXPallina,ultimaYPallina,numeroCasuale,nuovaX,nuovaY,validaX,validaY;
    
    Set<Character> set;
    
    
    public Fantasmino(int x, int y){
        direzioneFantasmino='L';
        pallinaX = x/grandezzaBlocco-1;
        pallinaY = x/grandezzaBlocco-1;
        ultimaXPallina = pallinaX;
        ultimaYPallina = pallinaY;
        nuovaDirezione = versoContrario;
        this.ultimoX = x;
        this.ultimoY = y;
        this.x = x;
        this.y = y;
        set = new HashSet<Character>();
    }

    
    public void aggiornaPalline(){
        xTemporanea = x/grandezzaBlocco-1;
        yTemporanea = y/grandezzaBlocco-1;
        
        if (xTemporanea != pallinaX || yTemporanea != pallinaY){
            ultimaXPallina = pallinaX;
            ultimaYPallina = pallinaY;
            pallinaX = xTemporanea;
            pallinaY = yTemporanea;
        }
    } 
    
    public boolean cambioDiDirezioneONo(){
        if(x%grandezzaBlocco==0 && y%grandezzaBlocco==0)
            return true;
        return false;
    }

    public char nuovaDirezione(){
        versoContrario='U';
        nuovaX=x;
        nuovaY=y;
        validaX=x;
        validaY=y;
        
        switch(direzioneFantasmino){
            case 'L':
                versoContrario='R';
                break;     
            case 'R':
                versoContrario='L';
                break;     
            case 'U':
                versoContrario='D';
                break;     
            case 'D':
                versoContrario='U';
                break;     
        }

        nuovaDirezione = versoContrario;
        
        while (nuovaDirezione == versoContrario || !direzioneValida(validaX,validaY)){
            if (set.size()==3){
                nuovaDirezione=versoContrario;
                break;
            }
        
            numeroCasuale = (int)(Math.random()*4) + 1;
            if (numeroCasuale == 1){
                nuovaDirezione = 'L';
                nuovaX-=velocità; 
                validaX-= velocità;
            }
            else if (numeroCasuale == 2){
                nuovaDirezione = 'R';
                nuovaX+=velocità; 
                validaX+= grandezzaBlocco;
            }
            
            else if (numeroCasuale == 3){
                nuovaDirezione = 'U';
                nuovaY-=velocità; 
                validaY-=velocità;
            }
            else if (numeroCasuale == 4){
                nuovaDirezione = 'D';
                nuovaY+=velocità; 
                validaY+=grandezzaBlocco;
            }
            if (nuovaDirezione != versoContrario){
                set.add(new Character(nuovaDirezione));
            }
        }
        return nuovaDirezione;
    }

    public void movimentoFantasmino(){
        ultimoX=x;
        ultimoY=y;

        if (cambioDiDirezioneONo()){
            direzioneFantasmino = nuovaDirezione();
        }

        switch(direzioneFantasmino){
            case 'L':
                if (direzioneValida(x-velocità,y))
                    x -= velocità;
                break;     
            case 'R':
                if (direzioneValida(x+grandezzaBlocco,y))
                    x+= velocità;
                break;     
            case 'U':
                if (direzioneValida(x,y-velocità))
                    y-= velocità;
                break;     
            case 'D':
                if (direzioneValida(x,y+grandezzaBlocco))
                    y+= velocità;
                break;     
        }
    }
}
