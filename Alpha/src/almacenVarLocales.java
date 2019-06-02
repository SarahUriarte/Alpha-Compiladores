import org.antlr.v4.runtime.Token;
import java.util.LinkedList;


public class almacenVarLocales {
    LinkedList<Object> almacen;


    public almacenVarLocales(){
        almacen = new LinkedList<Object>();


    }
    public void insertar(Token id, int tipo)
    {
        Ident temp = buscar(id.getText());
        if(temp == null){
            Ident i = new Ident(id,tipo);
            almacen.add(i);
        }else{

        }
    }
    public void actualizar(String nombre, Object v){
        Ident temp = buscar(nombre);
        if(temp != null){
            temp.valor = v;
        }

    }

    public Ident buscar(String nombre)
    {
        Ident temp=null;
        for(Object id : almacen)
            if (((Ident)id).tok.getText().equals(nombre))
                temp = ((Ident)id);
        return temp;
    }
    public void imprimir() {
        for (int i = 0; i < almacen.size(); i++) {

            Token s = (Token) ((Ident) almacen.get(i)).tok;
            if(((Ident)almacen.get(i)).valor instanceof String){

            }
            System.out.println("Nombre: " + s.getText()+" - "+((Ident)almacen.get(i)).type
                    +" - "+((Ident)almacen.get(i)).valor);

        }
    }

}
