grammar Query;

 @header {
 	package ca.ece.ubc.cpen221.parser;
 }
 
 /* Lexer Rules */
 NUM : [1-5] ;
 STRING: [A-Za-z]+ ([ \t]+[A-Za-z]+)* ; 
 
 /* Parser Rules */
 root : orExpr EOF ;
 orExpr : andExpr ('||' andExpr)* ; // ?
 andExpr : atom ('&&' atom)* ;
 atom : in | category | rating | price | name | '(' orExpr ')' ;
 ineq : '>' | '>=' | '<' | '<=' | '=' ;
 in : 'in' '(' STRING ')' ;
 category : 'category' '(' STRING ')' ;
 rating : 'rating' ineq NUM ;
 price : 'price' ineq NUM ;
 name : 'name' '(' STRING ')' ;