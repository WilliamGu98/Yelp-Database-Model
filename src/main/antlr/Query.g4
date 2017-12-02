grammar Query;

 @header {
 	package ca.ece.ubc.cpen221.parser;
 }
 
 /* Lexer Rules */
 NUM : [1-5] ;
 STRING: [A-Za-z]+ ([ \t]+[A-Za-z]+)* ; 
 GT : '>' ;
 GTE : '>=' ;
 LT : '<' ;
 LTE : '<=' ;
 EQ : '=' ;
 WHITESPACE : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ -> skip ;
 LPAREN : '(' ;
 RPAREN : ')' ;
 OR : '||' ;
 AND : '&&' ;
 
 /* Parser Rules */
 root : orExpr ;
 orExpr : andExpr (OR andExpr)* ; 
 andExpr : atom (AND atom)* ;
 comparator : GT | GTE | LT | LTE | EQ ;
 atom : inExpr | categoryExpr | ratingExpr | priceExpr | nameExpr | LPAREN orExpr RPAREN ;
 inExpr : 'in' LPAREN STRING RPAREN ;
 categoryExpr : 'category' LPAREN STRING RPAREN ;
 ratingExpr : 'rating' comparator NUM ;
 priceExpr : 'price' comparator NUM ;
 nameExpr : 'name' LPAREN STRING RPAREN ;