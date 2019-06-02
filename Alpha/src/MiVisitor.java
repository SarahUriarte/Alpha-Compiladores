import generated.Parser2;
import generated.Parser2BaseVisitor;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;

public class MiVisitor extends Parser2BaseVisitor<Object> {

    private almacenVarGlobales globales;
    private almacenVarLocales locales;
    boolean isLocal;


    public MiVisitor() {

        this.globales = new almacenVarGlobales();
        this.locales = new almacenVarLocales();
        this.isLocal = false;
    }
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
        boolean tipoCorrecto = false;
        if(isLocal){
            Ident exists = locales.buscar(ctx.ID().getText());
            if(exists == null){
                printError("semantic error: undefined indentifier ",ctx.ID().getSymbol());
            }
            else{
                Object valor = visit(ctx.expression());
                if(exists.type == 1 && valor instanceof Integer){
                    exists.setValue(valor);
                    tipoCorrecto = true;
                }
                else if(exists.type == 2 && valor instanceof String){
                    exists.setValue(valor);
                    tipoCorrecto = true;
                }
                else if(exists.type == 3 && valor instanceof Boolean){
                    exists.setValue(valor);
                    tipoCorrecto = true;
                }

            }
        }else{
            Ident exists = globales.buscar(ctx.ID().getText());
            if(exists == null){
                printError("semantic error: undefined indentifier ",ctx.ID().getSymbol());
            }
            else{
                Object valor = visit(ctx.expression());
                if(exists.type == 1 && valor instanceof Integer){
                    exists.setValue(valor);
                    tipoCorrecto = true;
                }
                else if(exists.type == 2 && valor instanceof String){
                    exists.setValue(valor);
                    tipoCorrecto = true;
                }
                else if(exists.type == 3 && valor instanceof Boolean){
                    exists.setValue(valor);
                    tipoCorrecto = true;
                }

            }

        }
        if(!tipoCorrecto){
            printError("Tipo incorrecto ",ctx.ID().getSymbol());
        }
        globales.imprimir();
        locales.imprimir();

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
        isLocal=true;
        visit(ctx.statementExpression());
        visit(ctx.singleCommand(0));
        visit(ctx.singleCommand(1));
        isLocal=false;
        return null;
    }

    @Override
    public Object visitWhileSCAST(Parser2.WhileSCASTContext ctx) {
        isLocal=true;
        Object valor = visit(ctx.statementExpression());
        if(valor instanceof Boolean){
            for (int i = 0; i < (int) valor; i++){
                visit(ctx.singleCommand());
            }
        }
        isLocal=false;
        return null;
    }

    @Override
    public Object visitLetSCAST(Parser2.LetSCASTContext ctx) {

        visit(ctx.declaration());
        visit(ctx.singleCommand());

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
        Object valor = visit(ctx.expression());
        //hacer al final la insercion de las constantes porque ocupo validar el tipo y no visita typedenoter, ademas este inserta valor
        return null;
    }

    @Override
    public Object visitVarDeclAST(Parser2.VarDeclASTContext ctx) {

        String tipo = (String)visit(ctx.typedenoter());
        if(isLocal){
            if(tipo.equals("int")){
                if(!locales.insertar(ctx.ID().getSymbol(),1)){
                    System.out.println("La variable ya fue declarada, se procede a actualizar");
                }
            }
            else if(tipo.equals("string")){
                if(!locales.insertar(ctx.ID().getSymbol(),2)){
                    System.out.println("La variable ya fue declarada, se procede a actualizar");
                }
            }
            else if(tipo.equals("boolean")){
                if(!locales.insertar(ctx.ID().getSymbol(),3)){
                    System.out.println("La variable ya fue declarada, se procede a actualizar");
                }
            }
            else{
                System.out.println("Ni int, ni string, ni boolean");
            }

        }else{
            if(tipo.equals("int")){
                if(!globales.insertar(ctx.ID().getSymbol(),1)){
                    System.out.println("La variable ya fue declarada, se procede a actualizar");
                }
            }
            else if(tipo.equals("string")){
                if(!globales.insertar(ctx.ID().getSymbol(),2)){
                    System.out.println("La variable ya fue declarada, se procede a actualizar");
                }
            }
            else if(tipo.equals("boolean")){
                if(!globales.insertar(ctx.ID().getSymbol(),3)){
                    System.out.println("La variable ya fue declarada, se procede a actualizar");
                }
            }
            else{
                System.out.println("Ni int, ni string, ni boolean");
            }

        }


        return null;
    }

    @Override
    public Object visitTypedenoterIntAST(Parser2.TypedenoterIntASTContext ctx)
    {
       return ctx.INT().getText();
    }

    @Override
    public Object visitTypedenoterStringGAST(Parser2.TypedenoterStringGASTContext ctx)
    {
        return ctx.STR().getText();
    }

    @Override
    public Object visitTypedenoterBoolAST(Parser2.TypedenoterBoolASTContext ctx) {
        return ctx.BOOL().getText();
    }

    @Override
    public Object visitStExpressionAST(Parser2.StExpressionASTContext ctx) {
        visit(ctx.expression(0));
        for (int i = 1; i < ctx.expression().size(); i++){
            visit(ctx.expression(i));
            visit(ctx.logicOperator(i-1));
        }
        return null;

    }
    @Override
    public Object visitExpressionAST(Parser2.ExpressionASTContext ctx) {
        ArrayList<Object> listExp = new ArrayList<>();
        Object value1 = visit(ctx.primaryExpression(0));
        Object otherValues;
        Object operator;
        listExp.add(value1);
        Boolean type = true;

        int size = ctx.primaryExpression().size();
        for (int i = 1; i < ctx.primaryExpression().size(); i++){
            operator = visit(ctx.operator(i-1));
            otherValues = visit(ctx.primaryExpression(i));
            listExp.add(operator);
            listExp.add(otherValues);
        }
        if(value1 instanceof Boolean){
            if(size > 1){
                System.out.println("No pueden sumar booleanos");
                return null;
            }
        }
        if(value1 instanceof Integer){
            for (int i = 2; i < listExp.size(); i = i+2){
                if(!(listExp.get(i) instanceof Integer)){
                    type = false;
                    break;
                }
            }
            if(!type){
                System.out.println("No puede sumar números con otros tipos de datos");
                return null;
            }
            int i = 1;
            //Ciclo para hacer las operaciones de primer nivel (* y /)
            while (i < listExp.size()){
                if(listExp.get(i).equals('*') || listExp.get(i).equals('/')){
                    int res = oper((char)listExp.get(i),(int)listExp.get(i-1),(int)listExp.get(i+1));
                    listExp.remove(i + 1);
                    listExp.remove(i);
                    listExp.set(i-1,res);
                }
                else {
                    i = i+ 2;
                }
            }
            //Ciclo para hacer las operaciones de segundo nivel (+ y -)
            int val = (int)listExp.get(0);
            listExp.remove(0);
            i = 0;
            while (listExp.size() > 0){
                val = oper((char)listExp.get(i),(int)val,(int)listExp.get(i+1));
                listExp.remove(i+1);
                listExp.remove(i);

            }
            return val;
        }

        if(value1 instanceof String){
            for (int i = 2; i < listExp.size(); i = i+2){
                if(!(listExp.get(i) instanceof String && listExp.get(i-1).equals('+'))){
                    type = false;
                    break;
                }
            }
            if(!type){
                System.out.println("Está usando un operador inválido o sumando Strings con otros tipos de datos");
                return null;
            }
            String val = (String)listExp.get(0);
            listExp.remove(0);
            int i = 0;
            while (listExp.size() > 0){
                val = operString((char)listExp.get(i),(String)val,(String)listExp.get(i+1));
                listExp.remove(i+1);
                listExp.remove(i);
            }
            return val;
        }
        return value1;
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
        return s1.substring(0,s1.length()-1) +" "+ s2.substring(1);
    }


    @Override
    public Object visitNumPEAST(Parser2.NumPEASTContext ctx) {

        return Integer.parseInt(ctx.NUM().getText());
    }

    @Override
    public Object visitIdPEAST(Parser2.IdPEASTContext ctx) {
        Ident aux;
        if(isLocal){
            aux = locales.buscar(ctx.ID().getText());
            if(aux != null){
                return aux.valor;
            }else{
                aux = globales.buscar(ctx.ID().getText());
                return aux.valor;
            }

        }else{
            aux = globales.buscar(ctx.ID().getText());
            return aux.valor;
        }
    }

    @Override
    public Object visitStringPEAST(Parser2.StringPEASTContext ctx) {
        return ctx.STRING().getText();
    }

    @Override
    public Object visitBooleanPEAST(Parser2.BooleanPEASTContext ctx) {
        String boolText = ctx.BOOLEAN().getText();
        if (boolText.equals("true")){
            return true;
        }
        else if(boolText.equals("False")){
            return false;
        }
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
    public Object visitLogicOperator(Parser2.LogicOperatorContext ctx) {
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

