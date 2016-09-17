/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portugol.core.llvm;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Bernardo
 */
public class Main {

    /**
     * O compilador porotugol possui suporte para os seguintes argumentos
     * 
     * Compilador source/programa.por -o obj/programa.bc
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        try {
            List<File> inputs = getInputs(args);
            List<String> outputs = getOutputs(args);
            compile(inputs, outputs);
        } catch (Exception e) {
            //TODO: Melhorar implementação
            System.out.println(e.getMessage());
        }
    }

    private static List<File> getInputs(String[] args) throws Exception {
        List<File> result = new ArrayList<>();
        String inputArg = null;
        String folder = null;
        for (int i = 0; i < args.length; i++) {
            if(args[i].equals("-o")) inputArg = args[i-1];
        }
        
        if(inputArg == null) throw new Exception("Entrada não foi encontrada!");
        try {
            result.add(new File(inputArg));
        } catch (Exception e) {
            throw new Exception("Não foi possível abrir o arquivo " + inputArg);
        }
        
        return result;
    }

    private static List<String> getOutputs(String[] args) throws Exception {
        List<String> result = new ArrayList<>();
        String outputArg = null;
        for (int i = 0; i < args.length; i++) {
            if(args[i].equals("-o")) outputArg = args[i+1];
        }
        
        if(outputArg == null) throw new Exception("Saída não foi encontrada!");
        result.add(outputArg);
        
        return result;
    }

    private static void compile(List<File> inputs, List<String> outputs) throws Exception {
        for (int i = 0; i < inputs.size(); i++) {
            File input = inputs.get(i);
            String output = outputs.get(i);
            
            try {
                Scanner scanner = new Scanner(input);
                String codigo = scanner.useDelimiter("\\A").next();
                try {
                    Compilador compilador = new Compilador(codigo);
                    compilador.getLLVM().writeBitcodeToFile(output);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }
            } catch (Exception e) {
                throw new Exception("Erro ao abrir o arquivo! \n" + e.getMessage());
            }
        }
    }
    
}
