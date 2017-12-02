grammar Query;

 @header {
 	package ca.ece.ubc.cpen221.parser;
 }
 
 /* Lexer Rules */
 NUM : [1-5] ;
 STRING: [A-Za-z]+ ([ \t]+[A-Za-z]+)* ; 
 COMP : '>' | '>=' | '<' | '<=' | '=' ;
 WHITESPACE : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ -> skip ;
 LPAREN : '(' ;
 RPAREN : ')' ;
 OR : '||' ;
 AND : '&&' ;
 
 /* Parser Rules */
 root : orExpr ;
 orExpr : andExpr (OR andExpr)* ; 
 andExpr : atom (AND atom)* ;
 atom : inExpr | categoryExpr | ratingExpr | priceExpr | nameExpr | LPAREN orExpr RPAREN ;
 inExpr : 'in' LPAREN STRING RPAREN ;
 categoryExpr : 'category' LPAREN STRING RPAREN ;
 ratingExpr : 'rating' COMP NUM ;
 priceExpr : 'price' COMP NUM ;
 nameExpr : 'name' LPAREN STRING RPAREN ;