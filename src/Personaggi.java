/**
 *
 * @author dibla
 */

public class Personaggi{
    int contatoreAnimazioni=0;
    int i,j;
    boolean[][] mappa;
    int grandezzaBlocco;
    int max;
    int velocità;

    public Personaggi(){
        grandezzaBlocco=20;
        velocità = 4;
        max = 400;
        mappa = new boolean[20][20];
        for(i =0;i<20;i++){
            for(j=0;j<20;j++)
                mappa[i][j] = false;
        }
    }
    public void aggiornaMappaPF(boolean[][] mappaPacmanFantasmi){
        for(i =0;i<20;i++){
            for(j=0;j<20;j++)
                mappa[i][j] = mappaPacmanFantasmi[i][j];
        }
    }

    public boolean direzioneValida(int x, int y){
        if((((x)%20==0) || ((y)%20)==0) && 20<=x && x<400 && 20<= y && y<400 && mappa[x/20-1][y/20-1])
            return true;
        return false;
    } 
}