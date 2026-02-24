package model;
import java.util.ArrayList;

public class FotosJob {

    private ArrayList<String> urlFotos;

    public FotosJob() {
        this.urlFotos = new ArrayList<String>();
    }

    public void adicionaFoto(String urlFoto){
        this.urlFotos.add(urlFoto);
    }

    public ArrayList<String> getFotos(){
        if(urlFotos.size()>0){
            return this.urlFotos;
        } else{
            return null;
        }
    }


}
