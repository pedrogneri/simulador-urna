package apllication;

import javax.swing.*;
import java.io.*;

public enum ArquivosJson {
    DEPUTADO("deputados.json"),
    SENADOR("senadores.json"),
    GOVERNADOR("governadores.json"),
    PRESIDENTE("presidentes.json");

    private String path;
    ArquivosJson(String path){
        this.path = path;
    }

    private String getPath(){
        return "./src/JsonFiles/" + this.path;
    }

    public String lerArquivo() {
        String json = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(getPath()));
            json = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public void atualizarArquivo(String json){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(getPath()));
            bw.write(json);
            bw.flush();
            bw.close();
        } catch (IOException e) { e.printStackTrace(); }
    }

}
