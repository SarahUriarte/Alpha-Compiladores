import generated.Parser2;
import generated.Parser2BaseVisitor;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class MiVisitor extends Parser2BaseVisitor<Object> {

    private almacenVarGlobales globales;
    private almacenVarLocales locales;



    @Override
    public Object visitProgramAST(Parser2.ProgramASTContext ctx) {
        visit(ctx.singleCommand());
        return null;
    }

    @Override
    public Object visitCommandAST(Parser2.CommandASTContext ctx) {
        visit(ctx.singleCommand(0));
        for (int i = 1; i < ctx.singleCommand().size(); i++){
            visit(ctx.singleCommand(i));
        }
        return null;
    }

    @Override
    public Object visitAssignSCast(Parser2.AssignSCastContext ctx) {
        //En python es aquí a donde se mete en la tabla, porque es donde se asigna
        /*TablaSimbolos.Ident exists = miTabla.buscar(ctx.ID().getText());
        if(exists == null){
            printError("semantic error: undefined indentifier ",ctx.ID().getSymbol());
        }
        else{
            Object valor = visit(ctx.expression());
            if(exists.type == 1 && valor instanceof Integer){
                exists.setValue(valor);
            }
            else if(exists.type == 2 && valor instanceof String){
                exists.setValue(valor);
            }
            else if(exists.type == 3 && valor instanceof Boolean){
                exists.setValue(valor);
            }

        }*/
        return null;
    }

    @Override
    public Object visitCallSCAST(Parser2.CallSCASTContext ctx) {
        /*if(ctx.ID().getText().equals("print"))
        {
            Object value = visit(ctx.expression());
            System.out.println(value);
        }
        visit(ctx.expression());*/
        return null;
    }

    @Override
    public Object visitIfSCAST(Parser2.IfSCASTContext ctx) {
        visit(ctx.expression());
        visit(ctx.singleCommand(0));
        visit(ctx.singleCommand(1));
        return null;
    }

    @Override
    public Object visitWhileSCAST(Parser2.WhileSCASTContext ctx) {
        Object valor = visit(ctx.expression());
        if(valor instanceof Integer){
            for (int i = 0; i < (int) valor; i++){
                visit(ctx.singleCommand());
            }
        }
        return null;
    }

    @Override
    public Object visitLetSCAST(Parser2.LetSCASTContext ctx) {
        //miTabla.openScope();
        visit(ctx.declaration());
        visit(ctx.singleCommand());
       // miTabla.imprimir();
       // miTabla.closeScope();
        return null;
    }

    @Override
    public Object visitBeginSCASD(Parser2.BeginSCASDContext ctx) {
        visit(ctx.command());
        return null;
    }

    @Override
    public Object visitPrintAST(Parser2.PrintASTContext ctx) {
        return null;
    }

    @Override
    public Object visitDeclarationAST(Parser2.DeclarationASTContext ctx) {
        visit(ctx.singleDeclaration(0));
        for (int i = 1; i < ctx.singleDeclaration().size(); i++){
            visit(ctx.singleDeclaration(i));
        }
        return null;
    }

    @Override
    public Object visitConstDeclAST(Parser2.ConstDeclASTContext ctx) {
        visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitVarDeclAST(Parser2.VarDeclASTContext ctx) {

        String tipo = (String)visit(ctx.typedenoter());
        if(tipo.equals("int")){
           // miTabla.insertar(ctx.ID().getSymbol(),1);
        }
        else if(tipo.equals("string")){
           // miTabla.insertar(ctx.ID().getSymbol(),2);
        }
        else if(tipo.equals("boolean")){
            //miTabla.insertar(ctx.ID().getSymbol(),3);
        }
        else{
            System.out.println("Ni int ni string, ni boolean");
            return null;
        }

        return null;
    }

    /*@Override
    public Object visitTypedenoterAST(Parser2.TypedenoterASTContext ctx) {

        return ctx.ID().getText();
    }*/
    @Override
    public Object visitTypedenoterIntAST(Parser2.TypedenoterIntASTContext ctx) {
        return null;
    }

    @Override
    public Object visitTypedenoterStringGAST(Parser2.TypedenoterStringGASTContext ctx) {
        return null;
    }

    @Override
    public Object visitTypedenoterBoolAST(Parser2.TypedenoterBoolASTContext ctx) {
        return null;
    }


    @Override
    public Object visitExpressionAST(Parser2.ExpressionASTContext ctx) {

        return null;
    }
    @Override
    public Object visitNumPEAST(Parser2.NumPEASTContext ctx) {
        return Integer.parseInt(ctx.NUM().getText());
    }

    @Override
    public Object visitIdPEAST(Parser2.IdPEASTContext ctx) {
        /*TablaSimbolos.Ident exists = miTabla.buscar(ctx.ID().getText());
        if(exists == null){
            printError("semantic error: undefined indentifier ",ctx.ID().getSymbol());
        }
        else{
            return exists.valor;
        }*/
        return null;
    }

    @Override
    public Object visitStringPEAST(Parser2.StringPEASTContext ctx) {
        return ctx.STRING().getText();
    }

    @Override
    public Object visitBooleanPEAST(Parser2.BooleanPEASTContext ctx) {
        return null;
    }

    @Override
    public Object visitGroupPEAST(Parser2.GroupPEASTContext ctx) {

        return visit(ctx.expression());
    }

    @Override
    public Object visitOperator(Parser2.OperatorContext ctx) {

        return ctx.getText().charAt(0);
    }

    @Override
    public Object visit(ParseTree parseTree) {
        return null;
    }

    @Override
    public Object visitChildren(RuleNode ruleNode) {
        return null;
    }

    @Override
    public Object visitTerminal(TerminalNode terminalNode) {
        return null;
    }

    @Override
    public Object visitErrorNode(ErrorNode errorNode) {
        return null;
    }



    private void printError(String msg, Token t){
        System.out.println(msg+
                t.getText()+" ("+t.getLine()+":"+
                t.getCharPositionInLine()+")");
    }

}
/*Object value = new Object();
        if(visit(ctx.primaryExpression(0)) instanceof Integer){
            value = (Integer) visit(ctx.primaryExpression(0));
            for (int i = 1; i < ctx.primaryExpression().size(); i++){
                char oper = (char) visit(ctx.operator(i-1));
                Integer value2 = (Integer) visit(ctx.primaryExpression(i));
                value = oper(oper,(Integer) value,value2);
            }
        }
        return value;*/
    /* Object value;
        String texto ;
        int valor ;
        value = visit(ctx.primaryExpression(0));
        if(value instanceof Integer){
            valor = (Integer) value;
            for (int i = 1; i < ctx.primaryExpression().size(); i++) {
                char oper = (Character) visit(ctx.operator(i-1));
                Object value2 = visit(ctx.primaryExpression(i));
                if(value2 instanceof Integer){
                    int valor2 = (Integer) value2;
                    valor = oper(oper, valor , valor2);
                }
                else{
                    System.out.println("Error al operar integer con string");
                }
            }
            return valor;
        }
        else{
            texto = (String) value;
            for (int i = 1; i < ctx.primaryExpression().size(); i++) {
                char oper = (Character) visit(ctx.operator(i-1));
                if(oper == '+'){
                    Object val2 = visit(ctx.primaryExpression(i));
                    if(val2 instanceof String){
                        String texto2 = (String) val2;
                        texto = operString(oper,texto,texto2);
                    }
                    else{
                        System.out.println("Error no se puede operar int y string");
                    }
                }
                else{
                    System.out.println("Para el string solo se puede usar +");
                }
            }
            return texto;
        }

    }
    private int oper(char op,int o1,int o2){
        switch (op){
            case '+': return o1 + o2;
            case '-': return o1 - o2;
            case '*': return o1 * o2;
            case '/': return o1 / o2;
        }
        return 0;
    }
    private String operString(char op, String s1, String s2){
        switch (op){
            case '+': return s1.substring(0,s1.length()-1) +" "+ s2.substring(1);
            /*case '-': return o1 - o2;
            case '*': return o1 * o2;
            case '/': return o1 / o2;*/

