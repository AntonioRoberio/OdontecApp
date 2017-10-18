package com.des.odontec.equipe.odontec.Controller;

/**
 * Created by Antonio on 18/10/2017.
 */

public class ResultadoFinalController {

    public String resutado(String anesteico,double pesoPaciente){
        String rst="";

        String anestesico=anesteico;
        char[] arrayAnestesico=anestesico.toCharArray();
        char caracteres=' ';
        String valor="";
        double peso=pesoPaciente;
        double mg;
        double dosagemMaxima;
        double resultado;

        for(int i=0;i<arrayAnestesico.length-1;i++){
            caracteres=arrayAnestesico[i];
            String n=String.valueOf(caracteres);
            if(n.matches("\\d")){
                valor+=n;
            }else if(n.equalsIgnoreCase(".")){
                valor+=n;
            }else if(n.equalsIgnoreCase("%")){
                break;
            }
        }

        mg=Double.parseDouble(valor);
        mg=(mg*10)*1.8;

        if(anestesico.contains("Articaína")){
            dosagemMaxima=7.0;
        }else if(anestesico.contains("Prilocaína")){
            dosagemMaxima=6.0;
        }else if(anestesico.contains("Bupivacaína")){
            dosagemMaxima=1.3;
        }else{
            dosagemMaxima=4.4;
        }
        resultado=(dosagemMaxima*peso)/mg;

        return rst=String.valueOf(Math.floor(resultado));
    }

}
