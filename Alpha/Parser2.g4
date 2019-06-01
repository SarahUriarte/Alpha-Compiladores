parser grammar Parser2;

options {
  tokenVocab = Scanner;
}

program : singleCommand EOF                                                     #programAST;
command : singleCommand (PyCOMA singleCommand)*                                 #commandAST;
singleCommand : ID ASSIGN expression                                            #assignSCast
        |ID PIZQ expression PDER                                                #callSCAST
        |IF expression THEN singleCommand
                        ELSE singleCommand                                      #ifSCAST
        | WHILE expression DO singleCommand                                     #whileSCAST
        | LET declaration IN singleCommand                                      #letSCAST
        | BEGIN command END                                                     #beginSCASD
        | PRINT PIZQ expression PDER                                            #printAST;
declaration  : singleDeclaration (PyCOMA singleDeclaration)*                    #declarationAST;
singleDeclaration : CONST ID VIR expression                                     #constDeclAST
    	   | VAR ID DOSPUN typedenoter                                          #varDeclAST;
typedenoter :   INT                                                             #typedenoterIntAST
                | STR                                                           #typedenoterStringGAST
                | BOOL                                                          #typedenoterBoolAST;
expression : primaryExpression (operator primaryExpression)*                    #expressionAST;
primaryExpression : NUM                                                         #numPEAST
                    | ID                                                        #idPEAST
                    | STRING                                                    #stringPEAST
                    | BOOLEAN                                                   #booleanPEAST
                    | PIZQ expression PDER                                      #groupPEAST;
operator : SUM | SUB | MUL | DIV | MAYOR | MENOR | IGUAL | AND | OR             #operatorAST;


