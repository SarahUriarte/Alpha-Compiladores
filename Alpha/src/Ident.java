import org.antlr.v4.runtime.Token;

class Ident{
    Token tok;
    int type;
    Object valor;

    public Ident(Token t, int tp){
        tok = t;
        type = tp;
        valor = 0;
    }
    public void setValue(Object v){
        valor = v;
    }

}