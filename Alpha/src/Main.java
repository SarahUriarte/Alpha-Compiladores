import generated.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import javax.swing.*;

/**
 * Created by oviquez on 28/2/2018.
 */

public class Main {
    public static void main(String[] args){
        Scanner inst = null;
        Parser2 parser = null;
        ParseTree tree=null;

        //ANTLRInputStream input=null;
        CharStream input=null;
        CommonTokenStream tokens = null;
        MyErrorListener errorListener = null;
        try {
            //Archivo de prueba con varios niveles para el análisis contextual
            //input = CharStreams.fromFileName("examenContextual");
            input = CharStreams.fromFileName("examenInterprete");
            inst = new Scanner(input);
            tokens = new CommonTokenStream(inst);
            parser = new Parser2(tokens);

            errorListener = new MyErrorListener();

            inst.removeErrorListeners();
            inst.addErrorListener( errorListener );

            parser.removeErrorListeners();
            parser.addErrorListener ( errorListener );

            try {
                tree = parser.program();
                //MiVisitor mv = new MiVisitor();
                //mv.visit(tree);

            }
            catch(RecognitionException e){
                System.out.println("Error!!!");
                e.printStackTrace();
            }

            if (errorListener.hasErrors() == false) {
                System.out.println("Compilación de sintaxis exitosa!!\n");
                //java.util.concurrent.Future<JFrame> treeGUI = org.antlr.v4.gui.Trees.inspect(tree, parser);
                //treeGUI.get().setVisible(true);
                MiVisitorContextual mvc = new MiVisitorContextual();
                mvc.visit(tree);
                if(mvc.listaErrores.size() > 0){
                    System.out.println("Compilación Fallida por errores contextuales!!\n");
                    for(int i = 0; i < mvc.listaErrores.size(); i++){
                        System.out.println(mvc.listaErrores.get(i));
                    }
                }
                else{
                    System.out.println("Compilación de análisis contextual exitosa");
                    MiVisitor mv = new MiVisitor();
                    mv.visit(tree);
                }
            }
            else {
                System.out.println("Compilación de sintaxis fallida !!\n");
                System.out.println(errorListener.toString());
            }

        }
        catch(Exception e){System.out.println("No hay archivo");e.printStackTrace();}
        /*List<Token> lista = (List<Token>) inst.getAllTokens();

        for (Token t : lista)

            System.out.println(t.getType() + ":" + t.getText() + "\n");

        inst = new Scanner(input);
        //inst.reset();*/

        //Cambiar los listeners de errores sintácticos
    }

}
