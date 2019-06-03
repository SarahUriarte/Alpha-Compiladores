import generated.Parser2;
import generated.Parser2BaseVisitor;
import javafx.beans.binding.ObjectExpression;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;

import java.util.ArrayList;

public class MiVisitor extends Parser2BaseVisitor<Object> {

    private almacenVarGlobales globales;
    private almacenVarLocales locales;
    boolean isLocal;
    private boolean vieneDeStat = false;
    private boolean printSt = false;

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
        Ident exists;
        String text = ctx.ID().getText();
        if(isLocal){
            exists = locales.buscar(ctx.ID().getText());
            if(exists != null) {
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
            }else {
                exists = globales.buscar(ctx.ID().getText());
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
        }else{
            exists = globales.buscar(ctx.ID().getText());
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
        //vieneDeStat=true;
        ArrayList<Object> valores;
        valores = (ArrayList)visit(ctx.statementExpression());
        ArrayList<Boolean> booleanos = new ArrayList<>();
        ArrayList<String> conectores = new ArrayList<>();
        Object va1 = 0;
        Object va2 = 0;
        Object op = "";
        boolean conec = false;
        boolean or = false;
        boolean correcto = true;
        //valida e inserta en las listas de booleanos y conectores
        for (int i = 0; i<valores.size();i++){
            va1 =  valores.get(0);
            op =  valores.get(1);
            va2 =  valores.get(2);
            if(va1 instanceof Integer && va2 instanceof Integer){

                if(operar((Integer)va1, (String) op,(Integer) va2)){
                    booleanos.add(true);
                    valores.remove(0);
                    valores.remove(0);
                    valores.remove(0);
                    if(valores.size()>0){
                        if(valores.get(0).equals("&&")||valores.get(0).equals("||")) {
                            conectores.add((String) valores.get(0));
                            valores.remove(0);
                        }else{
                            System.out.println("se espera un and o un or");
                        }

                    }
                }else{
                    booleanos.add(false);
                    valores.remove(0);
                    valores.remove(0);
                    valores.remove(0);
                    if(valores.size()>0){
                        if(valores.get(0).equals("&&")||valores.get(0).equals("||")) {
                            conectores.add((String) valores.get(0));
                            valores.remove(0);
                        }else{
                            System.out.println("se espera un and o un or");
                        }

                    }
                }
            }else if(va1 instanceof String && va2 instanceof String){
                if(operString((String)va1,(String) op,(String) va2)){
                    booleanos.add(true);
                    valores.remove(0);
                    valores.remove(0);
                    valores.remove(0);
                    if(valores.size()>0){
                        if(valores.get(0).equals("&&")||valores.get(0).equals("||")) {
                            conectores.add((String) valores.get(0));
                            valores.remove(0);
                        }else{
                            System.out.println("se espera un and o un or");
                        }
                    }
                }else{
                    booleanos.add(false);
                    valores.remove(0);
                    valores.remove(0);
                    valores.remove(0);
                    if(valores.size()>0){
                        if(valores.get(0).equals("&&")||valores.get(0).equals("||")) {
                            conectores.add((String) valores.get(0));
                            valores.remove(0);
                        }else{
                            System.out.println("se espera un and o un or");
                        }

                    }
                }
            }
            else if(va1 instanceof Boolean && va2 instanceof Boolean){
                if(operarBool((Boolean) va1,(String) op,(Boolean) va2)){
                    booleanos.add(true);
                    valores.remove(0);
                    valores.remove(0);
                    valores.remove(0);
                    if(valores.size()>0){
                        if(valores.get(0).equals("&&")||valores.get(0).equals("||")) {
                            conectores.add((String) valores.get(0));
                            valores.remove(0);
                        }else{
                            System.out.println("se espera un and o un or");
                        }
                    }
                }else{
                    booleanos.add(false);
                    valores.remove(0);
                    valores.remove(0);
                    valores.remove(0);
                    if(valores.size()>0){
                        if(valores.get(0).equals("&&")||valores.get(0).equals("||")) {
                            conectores.add((String) valores.get(0));
                            valores.remove(0);
                        }else{
                            System.out.println("se espera un and o un or");
                        }

                    }
                }
            }

            else{
                System.out.println("no se pueden operar elementos de distinto tipo");
                return null;
            }
        }

        if (conectores.size() > 0) {
            for (int k = 0; k<conectores.size(); k++) {
                if (conectores.get(k).equals("&&")){
                    conec = true;
                }
            }
            if (conec) {
                for (int l = 0; l<booleanos.size(); l++) {
                    if (!booleanos.get(l)) {
                        correcto = false;
                    }
                    //si todos no son true entonces no se visita
                }
                if(correcto){
                    visit(ctx.singleCommand(0));
                }
                else{
                    visit(ctx.singleCommand(1));
                }
            } else{
                //aqui va lo de los booleanos
                for (int m = 0; m < booleanos.size(); m++) {
                    if (booleanos.get(m)) {
                        or = true;
                        break;
                    }
                }
                if(or){
                    visit(ctx.singleCommand(0));
                }else{
                    visit(ctx.singleCommand(1));
                }

            }
        }else{
            if(booleanos.get(0)){
                visit(ctx.singleCommand(0));
            }else{
                visit(ctx.singleCommand(1));
            }
        }
        //isLocal=false;
        /*if(operarWhile(valores)){
            vieneDeStat=false;
            visit(ctx.singleCommand(0));
        }else{
            vieneDeStat=false;
            visit(ctx.singleCommand(1));
        }*/
        return null;
    }

    @Override
    public Object visitWhileSCAST(Parser2.WhileSCASTContext ctx) {
        isLocal=true;
        vieneDeStat = true;
        ArrayList<Object> valores;
        ArrayList<Object> valoresAux;
        valores = (ArrayList)visit(ctx.statementExpression());
        valoresAux = (ArrayList) valores.clone();
        Integer iteraciones =0;
        if(valores.size()==1){
            //booleanos.add(true);
            while(true){
                if(iteraciones==1000){
                    System.out.println("Stack Overflow");
                    break;
                }
            }
            visit(ctx.singleCommand());
            iteraciones++;
            return null;
        }
        while (operarWhile(valoresAux)){
            vieneDeStat = false;
            visit(ctx.singleCommand());
            valoresAux = (ArrayList) valores.clone();
        }
        //isLocal=false;
        vieneDeStat = false;
        return null;
    }

    public boolean operarWhile(ArrayList<Object> exp){
        vieneDeStat = true;
        ArrayList<Boolean> booleanos = new ArrayList<>();
        ArrayList<String> conectores = new ArrayList<>();
        Object va1 = 0;
        Object va2 = 0;
        Object op = "";
        boolean conec = false;
        boolean or = false;

        while (exp.size()>0){
            va1 =  exp.get(0);
            op =  exp.get(1);
            va2 =  exp.get(2);
            if(va1 instanceof String){
                Object val = buscarValorID((String) va1);
                if(val != null){
                    va1 = val;
                }
            }
            if(va2 instanceof String){
                Object val = buscarValorID((String) va2);
                if(val != null){
                    va2 = val;
                }
            }
            if(va1 instanceof Integer && va2 instanceof Integer){

                if(operar((Integer)va1, (String) op,(Integer) va2)){
                    booleanos.add(true);
                    exp.remove(0);
                    exp.remove(0);
                    exp.remove(0);
                    if(exp.size()>0){
                        if(exp.get(0).equals("&&")||exp.get(0).equals("||")) {
                            conectores.add((String) exp.get(0));
                            exp.remove(0);
                        }else{
                            System.out.println("se espera un and o un or");
                        }
                    }
                }else{
                    booleanos.add(false);
                    exp.remove(0);
                    exp.remove(0);
                    exp.remove(0);
                    if(exp.size()>0){
                        if(exp.get(0).equals("&&")||exp.get(0).equals("||")) {
                            conectores.add((String) exp.get(0));
                            exp.remove(0);
                        }else{
                            System.out.println("se espera un and o un or");
                        }

                    }
                }
            }else if(va1 instanceof String && va2 instanceof String){
                if(operString((String)va1,(String) op,(String) va2)){
                    booleanos.add(true);
                    exp.remove(0);
                    exp.remove(0);
                    exp.remove(0);
                    if(exp.size()>0){
                        if(exp.get(0).equals("&&")||exp.get(0).equals("||")) {
                            conectores.add((String) exp.get(0));
                            exp.remove(0);
                        }else{
                            System.out.println("se espera un and o un or");
                        }
                    }
                }else{
                    booleanos.add(false);
                    exp.remove(0);
                    exp.remove(0);
                    exp.remove(0);
                    if(exp.size()>0){
                        if(exp.get(0).equals("&&")||exp.get(0).equals("||")) {
                            conectores.add((String) exp.get(0));
                            exp.remove(0);
                        }else{
                            System.out.println("se espera un and o un or");
                        }

                    }
                }
            }
            else if(va1 instanceof Boolean && va2 instanceof Boolean){
                if(operarBool((Boolean) va1,(String) op,(Boolean) va2)){
                    booleanos.add(true);
                    exp.remove(0);
                    exp.remove(0);
                    exp.remove(0);
                    if(exp.size()>0){
                        if(exp.get(0).equals("&&")||exp.get(0).equals("||")) {
                            conectores.add((String) exp.get(0));
                            exp.remove(0);
                        }else{
                            System.out.println("se espera un and o un or");
                        }
                    }
                }else{
                    booleanos.add(false);
                    exp.remove(0);
                    exp.remove(0);
                    exp.remove(0);
                    if(exp.size()>0){
                        if(exp.get(0).equals("&&")||exp.get(0).equals("||")) {
                            conectores.add((String) exp.get(0));
                            exp.remove(0);
                        }else{
                            System.out.println("se espera un and o un or");
                        }

                    }
                }
            }
        }

        if (booleanos.size() <= 0) {
            return false;
        }
        else{
            //valida si todo lo que vienen son &&
            if (conectores.size() > 0) {
                for (int k = 0; k < conectores.size(); k++) {
                    if (conectores.get(k).equals("&&")) {
                        conec = true;
                    } else {
                        conec = false;
                    }
                }
                //valida la ejecucion de las && con true
                if (conec) {
                    for (int l = 0; l < booleanos.size(); l++) {
                        if (!booleanos.get(l)) {
                            return false;
                            //visit(ctx.singleCommand());
                        }
                        //si todos no son true entonces no se visita
                    }
                    return true;
                } else {
                    for (int m = 0; m < booleanos.size(); m++) {
                        if (booleanos.get(m)) {
                            return true;
                        }
                    }
                }
            }
            else{
                    //si no hay mas validaciones de conectores, solo hay un booleano
                    if(booleanos.get(0)){
                        return true;
                    }
                }
            }
        return false;
    }

    public boolean operar(Integer op1, String operador, Integer op2){

        if (operador.equals(">")){
            if(op1>op2)
                return true;
        }
        if(operador.equals("<")){
            if(op1<op2)
                return true;
        }
        if(operador.equals("==")){
            if(op1==op2)
                return true;
        }if(operador.equals("")){
            return true;
        }
        return false;

    }
    public boolean operString(String op1, String operador, String op2){
        if(operador.equals("==")) {
            if (op1 == op2)
                return true;

        }
        return false;
    }
    public boolean operarBool(Boolean op1, String op, Boolean op2){
        if(op.equals("==")){
            if(op1==op2)
                return true;
        }else{
            System.out.println("solo se pueden operar igualaciones con tipo de dato booleano");
        }
        return false;
    }

    @Override
    public Object visitLetSCAST(Parser2.LetSCASTContext ctx) {

        visit(ctx.declaration());
        visit(ctx.singleCommand());

        return null;
    }

    @Override
    public Object visitBeginSCASD(Parser2.BeginSCASDContext ctx) {
        isLocal=true;
        visit(ctx.command());
        //imprimir();
        return null;
    }

    @Override
    public Object visitPrintAST(Parser2.PrintASTContext ctx) {

        /*System.out.println(visit(ctx.expression()));
        return null;*/
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
        System.out.println(exp1);
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
                    //System.out.println("La variable ya fue declarada, se procede a actualizar");
                }
            }
            else if(tipo.equals("string")){
                if(!locales.insertar(ctx.ID().getSymbol(),2)){
                    //System.out.println("La variable ya fue declarada, se procede a actualizar");
                }
            }
            else if(tipo.equals("boolean")){
                if(!locales.insertar(ctx.ID().getSymbol(),3)){
                    //System.out.println("La variable ya fue declarada, se procede a actualizar");
                }
            }
            else{
                System.out.println("Ni int, ni string, ni boolean");
            }

        }else{
            if(tipo.equals("int")){
                if(!globales.insertar(ctx.ID().getSymbol(),1)){
                    //System.out.println("La variable ya fue declarada, se procede a actualizar");
                }
            }
            else if(tipo.equals("string")){
                if(!globales.insertar(ctx.ID().getSymbol(),2)){
                    //System.out.println("La variable ya fue declarada, se procede a actualizar");
                }
            }
            else if(tipo.equals("boolean")){
                if(!globales.insertar(ctx.ID().getSymbol(),3)){
                    //System.out.println("La variable ya fue declarada, se procede a actualizar");
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
        String text = "";
        if(vieneDeStat){
            return ctx.ID().getText();
        }
        else {
            if(isLocal){
                aux = locales.buscar(ctx.ID().getText());
                if(aux != null){
                    return aux.valor;
                }else{
                    aux = globales.buscar(ctx.ID().getText());
                    return aux.valor;
                }

            }
        }
        /*else{
            aux = globales.buscar(ctx.ID().getText());
            if(aux!=null){
                 return aux.valor;
            }else{
                return null;
            }
        }*/
        return null;
    }
    public Object buscarValorID(String nombreVar){
        Ident aux;
        if(isLocal){
            aux = locales.buscar(nombreVar);
            if(aux != null){
                return aux.valor;
            }else{
                aux = globales.buscar(nombreVar);
                return aux.valor;
            }
        }
        return null;
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
        else if(boolText.equals("false")){
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
        //return ctx.getText().charAt(0);
        return ctx.getText();
    }


    private void printError(String msg, Token t){
        System.out.println(msg+
                t.getText()+" ("+t.getLine()+":"+
                t.getCharPositionInLine()+")");
    }
    private void imprimir(){
        globales.imprimir();
        locales.imprimir();
    }



}
