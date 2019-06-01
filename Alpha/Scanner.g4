lexer grammar Scanner;

//símbolos
PyCOMA  : ';' ;
ASSIGN  : ':=';
PIZQ    : '(';
PDER    : ')';
VIR     : '~';
DOSPUN  : ':';
SUM     : '+';
SUB     : '-';
MUL     : '*';
DIV     : '/';
MAYOR   : '>';
MENOR   : '<';
IGUAL   : '==';
AND     : '&&';
OR      : '||';

//palabras reservadas
IF      : 'if' ;
WHILE   : 'while' ;
LET     : 'let';
THEN    : 'then';
ELSE    : 'else';
IN      : 'in';
DO      : 'do';
BEGIN   : 'begin';
END     : 'end';
CONST   : 'const';
VAR     : 'var';
INT     : 'int';
STR     : 'string';
BOOL    : 'boolean';
PRINT   : 'print';

ID : LETTER (LETTER|DIGIT)* ;
NUM : DIGIT DIGIT* ;

STRING        :   '"' ('""'|~'"')* '"' ;
SPECIAL_STRING        :   '"''\\''"' ('""'|~'"')* '\\''"''"' ;
BOOLEAN: 'true'|'false';
fragment LETTER : 'a'..'z' | 'A'..'Z';
fragment DIGIT : '0'..'9' ;

WS  :   [ \t\n\r]+ -> skip ;

