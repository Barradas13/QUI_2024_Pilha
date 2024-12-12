import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.*;

public class Main{

    public static HashMap<String, Double> metais = new HashMap<>();
    public static String m1, m2;

    public static Double pegaMetal(int vez){
        String[] options = new String[metais.size()];

        int c = 0;

        for (String i : metais.keySet()) {
            options[c] = i;
            c++;
        }

        Object searchType = JOptionPane.showInputDialog(null, null, "Escolha um metal ", 
        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);;

        if(vez == 1){
            m1 = (String)searchType;
        }else{
            m2 = (String)searchType;
        }

        return metais.get(searchType);
    }
    
    public static void lerInformacoes(String file){
        
        try {

            File myObj = new File(file);
            Scanner input = new Scanner(myObj);

            while (input.hasNextLine()) {
                String data = input.nextLine();
                String valores[] = data.split(" ");

                metais.put(valores[0], Double.parseDouble(valores[1]));

            }

            input.close();

        } catch (IOException e) {
            System.out.println("Erro: ");
            e.printStackTrace();
        }
    
    }
    
    public static void main(String[] args) {
        lerInformacoes("Banco.txt");

        Double m1_valor = pegaMetal(1);
        Double m2_valor = pegaMetal(2);
        
        String retorno = "O metal que oxidou foi: ";

        Double valor = 0.00;
        
        if(m1_valor > m2_valor){
            retorno += m2 + "\n";
            retorno += "O metal que reduziu foi: " + m1 + "\n";
            valor = m1_valor - m2_valor;
        }else if(m2_valor > m1_valor){
            retorno += m1 + "\n";
            retorno += "O metal que reduziu foi: " + m2 + "\n";
            valor = m2_valor - m1_valor;
        }else{
            retorno = "Não há pilha desse tipo";
        }

        if(retorno != "Não há pilha desse tipo"){
            retorno += "\n\nO potencial da pilha é: " + valor + "V"; 
        }
        

        JOptionPane.showMessageDialog(null, retorno, "Pilha Formada", JOptionPane.INFORMATION_MESSAGE);

    }
}