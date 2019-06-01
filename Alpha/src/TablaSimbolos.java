import org.antlr.v4.runtime.*;
import java.util.LinkedList;

/**
 * Created by oviquez on 19/9/2016.
 */
public class TablaSimbolos {
    LinkedList<Object> tabla;
    private int nivelActual;
    class Ident{
        Token tok;
        int type; //Esto probablemente cambie a un tipo más estructurado
        int nivel;
        Object valor;
        public Ident(Token t, int tp){
            tok = t;
            type = tp;
            nivel = -1;
            valor = 0;
        }
        public void setValue(Object v){
            valor = v;
        }

    }

    public TablaSimbolos()
    {
        tabla = new LinkedList<Object>();
        this.nivelActual = 0;
    }

    public void insertar(Token id, int tipo)
    {
        Ident i = new Ident(id,tipo);
        tabla.add(i);
    }

    public Ident buscar(String nombre)
    {
        //Este buscar no es así, porque hay que recorrer la lista al revés
        Ident temp=null;
        for(Object id : tabla)
            if (((Ident)id).tok.getText().equals(nombre))
                temp = ((Ident)id);
        return temp;
    }
    public void openScope(){
        nivelActual ++;
    }
    public void closeScope(){
        //hay que sacar todos los identificadores del nivel que se está cerrando
        nivelActual --;
    }

    //Falta openscope()
    //Falta closeScope()
    public void imprimir() {
        for (int i = 0; i < tabla.size(); i++) {

            Token s = (Token) ((Ident) tabla.get(i)).tok;
            if(((Ident)tabla.get(i)).valor instanceof String){

            }
            System.out.println("Nombre: " + s.getText()+" - "+((Ident)tabla.get(i)).type
            +" - "+((Ident)tabla.get(i)).valor);
            /*if (s.getType() == 0) System.out.println("\tTipo: Indefinido");
            else if (s.getType() == 1) System.out.println("\tTipo: Integer\n");
            else if (s.getType() == 2) System.out.println("\tTipo: String\n");*/
        }
    }
}
