/**
 *
 * @author dibla
 */

public class Pacman extends Personaggi{
    char direzioneAttuale;
    char direzioneDesiderata;

    int pallineMangiate;

    int ultimoX;
    int ultimoY;

    int x;
    int y;

    int pallinaX;
    int pallinaY;

    boolean teletrasporto;

    boolean fermo;

    public Pacman(int x, int y){
        teletrasporto=false;
        fermo = false;
        pallineMangiate=0;
        pallinaX = x/grandezzaBlocco-1;
        pallinaY = y/grandezzaBlocco-1;
        this.ultimoX=x;
        this.ultimoY=y;
        this.x = x;
        this.y = y;
        direzioneAttuale='L';
        direzioneDesiderata='L';
    }


    public void movimentoPacman(){
        ultimoX=x;
        ultimoY=y;

        if (x %20==0 && y%20==0 || (direzioneDesiderata=='L' && direzioneAttuale=='R') || (direzioneDesiderata=='R' && direzioneAttuale=='L') || (direzioneDesiderata=='U' && direzioneAttuale=='D') || (direzioneDesiderata=='D' && direzioneAttuale=='U')){
            switch(direzioneDesiderata){
                case 'L':
                    if ( direzioneValida(x-velocità,y))
                        x -= velocità;
                    break;     
                case 'R':
                    if ( direzioneValida(x+grandezzaBlocco,y))
                        x+= velocità;
                    break;     
                case 'U':
                    if ( direzioneValida(x,y-velocità))
                        y-= velocità;
                    break;     
                case 'D':
                    if ( direzioneValida(x,y+grandezzaBlocco))
                        y+= velocità;
                    break;     
            }
        }
        if (ultimoX==x && ultimoY==y){
            switch(direzioneAttuale){
                case 'L':
                    if( direzioneValida(x-velocità,y))
                        x -= velocità;
                    else{ 
                        if (y == 9*grandezzaBlocco && x < 2 * grandezzaBlocco){
                            x = max - grandezzaBlocco*1;
                            teletrasporto = true; 
                        }
                    }
                    break;     

                case 'R':
                    if ( direzioneValida(x+grandezzaBlocco,y))
                        x+= velocità;
                    else{
                        if (y == 9*grandezzaBlocco && x > max - grandezzaBlocco*2){
                            x = 1*grandezzaBlocco;
                            teletrasporto=true;
                        }
                    }
                    break;  

                case 'U':
                    if ( direzioneValida(x,y-velocità))
                        y-= velocità;
                    break;  

                case 'D':
                    if ( direzioneValida(x,y+grandezzaBlocco))
                        y+= velocità;
                    break;     
            }
        }

        else{
            direzioneAttuale=direzioneDesiderata;
        }

        if(ultimoX == x && ultimoY==y)
            fermo=true;

        else{
            fermo=false;
            contatoreAnimazioni ++;
        }
    }
    public void aggiornaPalline(){
        if (x%grandezzaBlocco == 0 && y%grandezzaBlocco == 0){
            pallinaX = x/grandezzaBlocco-1;
            pallinaY = y/grandezzaBlocco-1;
        }
    } 
}


