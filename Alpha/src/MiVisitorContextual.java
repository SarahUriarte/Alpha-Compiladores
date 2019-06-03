import generated.Parser2;
import generated.Parser2BaseVisitor;
import generated.Parser2Visitor;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

public class MiVisitorContextual extends Parser2BaseVisitor<Object> {
    private TablaSimbolos miTabla;
    boolean printSt = false;
    public ArrayList<String> listaErrores;
    public MiVisitorContextual() {
        this.miTabla = new TablaSimbolos();
        this.listaErrores = new ArrayList<>();
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
        String tex = ctx.ID().getText();
        TablaSimbolos.Ident exists = miTabla.buscar(ctx.ID().getText());
        if(exists == null){
            printError("semantic error: undefined indentifier ",ctx.ID().getSymbol());
            return null;
        }
        else{
            Object valor = visit(ctx.expression());
            if(valor == null){
                return null;
            }
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
        if(!tipoCorrecto){
            printError("Tipo incorrecto ",ctx.ID().getSymbol());
        }

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
        visit(ctx.statementExpression());
        visit(ctx.singleCommand(0));
        //miTabla.imprimir();
        visit(ctx.singleCommand(1));
        return null;
    }

    @Override
    public Object visitWhileSCAST(Parser2.WhileSCASTContext ctx) {
        visit(ctx.singleCommand());
        return null;
    }

    @Override
    public Object visitLetSCAST(Parser2.LetSCASTContext ctx) {
        miTabla.openScope();
        //miTabla.imprimir();
        visit(ctx.declaration());
        visit(ctx.singleCommand());
        //miTabla.imprimir();
        miTabla.closeScope();
        return null;
    }

    @Override
    public Object visitBeginSCASD(Parser2.BeginSCASDContext ctx) {
        visit(ctx.command());
        return null;
    }

    @Override
    public Object visitPrintAST(Parser2.PrintASTContext ctx) {
        printSt = true;
        ArrayList<Object> printExp = (ArrayList<Object>) visit(ctx.expression());
        Object exp1 = printExp.get(0);
        for(int i = 1; i < printExp.size(); i++){
            if(printExp.get(i) instanceof Character){
                if((Character)printExp.get(i) != '+'){
                    exp1 = exp1.toString() + printExp.get(i).toString();
                }
            }
            else {
                exp1 = exp1.toString() + printExp.get(i).toString();
            }
        }
        //System.out.println(exp1);
        printSt =false;
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
        String var = ctx.ID().getText();
        String tipo = (String) visit(ctx.typedenoter());
        if(tipo.equals("int")){
            miTabla.insertar(ctx.ID().getSymbol(),1);
        }
        else if(tipo.equals("string")){
            miTabla.insertar(ctx.ID().getSymbol(),2);
        }
        else if(tipo.equals("boolean")){
            miTabla.insertar(ctx.ID().getSymbol(),3);
        }
        else{
            String error = "Ni int ni string, ni boolean";
            listaErrores.add(error);
            return null;
        }

        return null;
    }

    @Override
    public Object visitTypedenoterIntAST(Parser2.TypedenoterIntASTContext ctx) {
        return ctx.INT().getText();
    }

    @Override
    public Object visitTypedenoterStringGAST(Parser2.TypedenoterStringGASTContext ctx) {
        return ctx.STR().getText();
    }

    @Override
    public Object visitTypedenoterBoolAST(Parser2.TypedenoterBoolASTContext ctx) {
        return ctx.BOOL().getText();
    }
    @Override
    public Object visitStExpressionAST(Parser2.StExpressionASTContext ctx) {
        Object firstExpression = visit(ctx.expression(0));
        ArrayList<Object> secondExpPart = new ArrayList<>();
        secondExpPart.add(firstExpression);
        for (int i = 1; i < ctx.expression().size(); i++){
            secondExpPart.add(visit(ctx.logicOperator(i-1)));
            secondExpPart.add(visit(ctx.expression(i)));
        }
        return secondExpPart;
    }

    @Override
    public Object visitExpressionAST(Parser2.ExpressionASTContext ctx) {
        ArrayList<Object> listExp = new ArrayList<>();
        Object value1 = visit(ctx.primaryExpression(0));
        Object otherValues;
        Object operator;
        listExp.add(value1);
        Boolean type = true;
        String error;
        int size = ctx.primaryExpression().size();
        for (int i = 1; i < ctx.primaryExpression().size(); i++){
            operator = visit(ctx.operator(i-1));
            otherValues = visit(ctx.primaryExpression(i));
            listExp.add(operator);
            listExp.add(otherValues);
        }
        if(printSt){
            return listExp;
        }
        if(value1 == null){
            return null;
        }
        if(value1 instanceof Boolean){
            if(size > 1){
                error = "No pueden sumar booleanos";
                listaErrores.add(error);
                return null;
            }
        }
        if(value1 instanceof Integer){
            for (int i = 2; i < listExp.size(); i = i+2){
                if(listExp.get(i) == null){
                    return null;
                }
                if(!(listExp.get(i) instanceof Integer)){
                    type = false;
                    break;
                }
            }
            if(!type){
                error = "No puede sumar números con otros tipos de datos";
                listaErrores.add(error);
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
                if(listExp.get(i) == null){

                    return null;
                }
                if(!(listExp.get(i) instanceof String && listExp.get(i-1).equals('+'))){
                    type = false;
                    break;
                }
            }
            if(!type){
                error = "Está usando un operador inválido o sumando Strings con otros tipos de datos";
                listaErrores.add(error);
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
        return s1+s2;
    }
    @Override
    public Object visitNumPEAST(Parser2.NumPEASTContext ctx) {
        return Integer.parseInt(ctx.NUM().getText());
    }
    @Override
    public Object visitBooleanPEAST(Parser2.BooleanPEASTContext ctx) {
        String boolText = ctx.BOOLEAN().getText();
        if (boolText.equals("true")){
            return true;
        }
        else if(boolText.equals("false")){
            return false;
        }
        return null;
    }


    @Override
    public Object visitStringPEAST(Parser2.StringPEASTContext ctx) {
        String text = ctx.STRING().getText();
        return text.substring(1,text.length()-1);
    }
    @Override
    public Object visitIdPEAST(Parser2.IdPEASTContext ctx) {
        String texto = ctx.ID().getText();
        TablaSimbolos.Ident exists = miTabla.buscar(ctx.ID().getText());
        if(exists == null){
            printError("semantic error: undefined indentifier ",ctx.ID().getSymbol());
        }
        else{
            if(exists.valor == null){
                printError("No le ha asignado un valor a la variable ",ctx.ID().getSymbol());
                return null;
            }else{
                return exists.valor;
            }

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
        return ctx.getText().charAt(0);
    }

    private void printError(String msg, Token t){
        String message = msg+
                t.getText()+" ("+t.getLine()+":"+
                t.getCharPositionInLine()+")";
        listaErrores.add(message);
    }
}
