package apllication;

import java.io.*;

public enum LeitorJson {
    DEPUTADO("/JsonFiles/deputados.json"),
    SENADOR("/JsonFiles/senadores.json"),
    GOVERNADOR("/JsonFiles/governadores.json"),
    PRESIDENTE("/JsonFiles/presidentes.json");

    private String resourceURL;

    LeitorJson(String resourceURL){
        this.resourceURL = resourceURL;
    }

    public String lerArquivo(){
        String json = "";

        try{
            BufferedReader br = new BufferedReader(new FileReader(getClass().getResource(resourceURL).getFile()));

            json = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public void atualizarArquivo(String json){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(getClass().getResource(resourceURL).getFile(), false));

            bw.write(json);

            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
