grammar PlazmaScript;

options {
  backtrack=true;  
  output=AST;
}


tokens {
  BLOCK;
  RETURN;
  STATEMENTS;
  ASSIGNMENT;
  FUNC_CALL;
  //METHOD_CALL;
  EXP;
  EXP_PAIR;
  EXP_MAP;
  EXP_LIST;
  ID_LIST;
  IF;
  TERNARY;
  UNARY_PLUS;  
  UNARY_MIN;
  NEGATE;
  FUNCTION;
  INDEXES;
  INDEX;
  ATTRIBUTE;
  CALL;
  TAIL;
  TAILS;
  MAP;
  LIST;
  SET;  
  LOOKUP;
  BREAK;
  CONTINUE;
}

@parser::header {
  package org.plazmaforge.framework.script.parser;
  import org.plazmaforge.framework.script.*; 
  import java.util.Map;
  import java.util.HashMap;
}

@lexer::header {
  package org.plazmaforge.framework.script.parser;
}

@parser::members {
  public Map<String, Function> functions = new HashMap<String, Function>();
  
  private void defineFunction(String id, Object idList, Object block) {

    // `idList` is possibly null! Create an empty tree in that case. 
    CommonTree idListTree = idList == null ? new CommonTree() : (CommonTree)idList;

    // `block` is never null.
    CommonTree blockTree = (CommonTree)block;

    // The function name with the number of parameters after it the unique key
    String key = id + idListTree.getChildCount();
    functions.put(key, new Function(id, idListTree, blockTree));
  }
}


parse
  :  block EOF -> block
  ;

block
  :  (statement | functionDecl)* (Return expression ';')? 
     -> ^(BLOCK ^(STATEMENTS statement*) ^(RETURN expression?))
  ;

statement
  :  assignment ';'   -> assignment
  |  functionCall ';' -> functionCall
  |  lookup ';' -> lookup                                 // ???
  |  ifStatement
  |  forStatement
  |  whileStatement
  |  Break ';' -> Break
  |  Continue ';' -> Continue
  ;

  
assignment
  :  variableDef? Identifier indexes? '=' expression -> ^(ASSIGNMENT variableDef? Identifier indexes? expression)
  |  variableDef? anyIdentifier indexes? '=' expression -> ^(ASSIGNMENT variableDef? {new CommonTree(new CommonToken(Identifier, $anyIdentifier.text))} indexes? expression)
  ;

functionCall
  :  Identifier '(' exprList? ')' -> ^(FUNC_CALL Identifier exprList?)
  |  Println '(' expression? ')'  -> ^(FUNC_CALL Println expression?)
  |  Print '(' expression ')'     -> ^(FUNC_CALL Print expression)
  |  Assert '(' expression ')'    -> ^(FUNC_CALL Assert expression)
  |  Date '(' exprList? ')'      -> ^(FUNC_CALL Date exprList?)
  |  DateTime '(' exprList? ')'      -> ^(FUNC_CALL DateTime exprList?)  
  |  List '(' exprList? ')'      -> ^(FUNC_CALL List exprList?)
  |  Set '(' exprList? ')'      -> ^(FUNC_CALL Set exprList?)  
  ;

//methodCall
//  :  Identifier '(' exprList? ')' -> ^(METHOD_CALL Identifier exprList?)
//  ;
  
ifStatement
  :  ifStat elseIfStat* elseStat? -> ^(IF ifStat elseIfStat* elseStat?)
  ;

ifStat
  :  If '(' expression ')' '{' block '}' -> ^(EXP expression block)
  ;

elseIfStat
  :  Else If '(' expression ')' '{' block '}' -> ^(EXP expression block)
  ;

elseStat
  :  Else '{' block '}' -> ^(EXP block)
  ;

variableDef
 : Var
 ;

//Declare
//  : (Var | (Var ('int' | 'float')));
   
//Declare
//  : (Var | ('int' | 'float'));

//Declare
//  : 'var';

//Declare
//  : (Var | Identifier);

//Var      : 'var';
  
functionDecl
  :  Def Identifier '(' idList? ')' '{' block '}' 
     {defineFunction($Identifier.text, $idList.tree, $block.tree);}
  ;

//forStatement
//  :  For '(' Identifier '=' expression RangeSeparator expression ')' '{' block '}' 
//     -> ^(For Identifier expression RangeSeparator expression block)
//  ;
  
forStatement
  :  For '(' Identifier 'in' expression ')' '{' block '}' 
     -> ^(For Identifier expression block)
  ;
  

whileStatement
  :  While '(' expression ')' '{' block '}' -> ^(While expression block)
  ;

idList
  :  Identifier (',' Identifier)* -> ^(ID_LIST Identifier+)
  ;

exprList
  :  expression (',' expression)* -> ^(EXP_LIST expression+)
  ;

exprPair
  :  (expression ':' expression) -> ^(EXP_PAIR expression expression)
  ;

exprMap
  :  exprPair (',' exprPair)* -> ^(EXP_MAP exprPair+)
  ;


expression
  :  condExpr
  ;

condExpr
  :  (startExpr -> startExpr) 
     ( '?' a=expression ':' b=expression -> ^(TERNARY startExpr $a $b)
     | In expression                     -> ^(In startExpr expression)
     
     | RangeE expression                 -> ^(RangeE startExpr expression)
     | Range expression                  -> ^(Range startExpr expression)
               
     )?
  ;

startExpr
 : orExpr
 ;

orExpr
  :  andExpr (('xor' | '||' | '|' | 'or')^ andExpr)*
  ;


andExpr
  :  equExpr (('&&' | '&' | 'and')^ equExpr)*
  ;

equExpr
  :  relExpr (('==' | '!=')^ relExpr)*
  ;

relExpr
  :  addExpr (('>=' | '<=' | '>' | '<')^ addExpr)*
  ;

addExpr
  :  mulExpr (('+' | '-')^ mulExpr)*
  ;

mulExpr
  :  powExpr (('*' | '/' | '%')^ powExpr)*
  ;

powExpr
  :  unaryExpr ('^'^ unaryExpr)*
  ;
  
unaryExpr
  :  '+' atom -> ^(UNARY_PLUS atom)
  |  '-' atom -> ^(UNARY_MIN atom)
  |  '!' atom -> ^(NEGATE atom)
  |  'not' atom -> ^(NEGATE atom)  
  |  atom
  ;

atom
  :  Integer
  |  Number
  |  Bool
  |  Null
  |  NaN
  |  Infinity 
  |  lookup
  ;

list
  :  '[' exprList? ']' -> ^(LIST exprList?)
  ;

map
  :  '{' (':' | exprMap) '}' -> ^(MAP exprMap?)
  ;

lookup
  :  functionCall indexes?       -> ^(LOOKUP functionCall indexes?)
  |  list indexes?               -> ^(LOOKUP list indexes?)
  |  map indexes?                -> ^(LOOKUP map indexes?)  
  |  Identifier indexes?         -> ^(LOOKUP Identifier indexes?)
  |  anyIdentifier indexes?      -> ^(LOOKUP {new CommonTree(new CommonToken(Identifier, $anyIdentifier.text))} indexes?)  
  |  String indexes?             -> ^(LOOKUP String indexes?)
  |  '(' expression ')' indexes? -> ^(LOOKUP expression indexes?)
  ;

indexes
  //:  ('[' expression ']')+ -> ^(INDEXES expression+)
  :  (tail)+ -> ^(TAILS tail+)
  ;

tail
 : '[' expression ']'               -> ^(INDEX expression)
 | '.' Identifier                   -> ^(ATTRIBUTE Identifier)
 | '.' Identifier '(' exprList? ')' -> ^(CALL Identifier exprList?)
 ;


Println  : 'println';
Print    : 'print';
Assert   : 'assert';
Var      : 'var';
Def      : 'def';
If       : 'if';
Else     : 'else';
Return   : 'return';
For      : 'for';
While    : 'while';
In       : 'in';
Null     : 'null';
NaN      : 'NaN';
Infinity : 'Infinity';
Break    : 'break';
Continue : 'continue';

XorWord  : 'xor';
Or       : '||';
BitOr    : '|';
OrWord   : 'or';
And      : '&&';
BitAnd   : '&';
AndWord  : 'and';
Equals   : '==';
NEquals  : '!=';
GTEquals : '>=';
LTEquals : '<=';
Pow      : '^';
Not      : '!';
NotWord  : 'not';
GT       : '>';
LT       : '<';
Add      : '+';
Subtract : '-';
Multiply : '*';
Divide   : '/';
//IntDivide: '\\';
Modulus  : '%';
OBrace   : '{';
CBrace   : '}';
OBracket : '[';
CBracket : ']';
OParen   : '(';
CParen   : ')';
SColon   : ';';
Assign   : '=';
Comma    : ',';
QMark    : '?';
Colon    : ':';

Range    : '..';
RangeE   : '..<';

Date     : 'Date';
DateTime : 'DateTime';
List     : 'List';
Set      : 'Set';

Bool
  :  'true' 
  |  'false'
  ;

//Integer
//  :  '1'..'9' Digit* | '0';

//Number
//  :  ('1'..'9' Digit* | '0') ('.' Digit*)?
//  ;

//Number
//  :  ('0'..'9')+ '.' ('0'..'9')+
//  ;

Integer
  :  DecimalNumeral;


Number
  : (DecimalNumeral '..') => Integer {$type=Integer;}  
  | DecimalFloatingPoint
  ;

fragment DecimalFloatingPoint
  //: Digits '.' // unsupported because conflict with '..' (in range)  
  : Digits ('.' Digits)? ExponentPart? 
  | '.' Digits ExponentPart?
  ;
  
   

anyIdentifier
  : ContextIdentifier | Identifier
  ; 
  
Identifier
  :  ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | Digit)*
  ;


//ContextIdentifier
//  :  '$' Identifier 
//  ;

ContextIdentifier
  :  ('$' Identifier) | ('$' Identifier? '{' Identifier '}') 
  ;

//typeIdentifier
//  :  Identifier (('.' Identifier)*)? 
//  ;

//TypeIdentifier2
//  :  (('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | Digit)*) (('.' (('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | Digit)*))*)? 
//  ;


String
@after {
  setText(getText().substring(1, getText().length()-1).replaceAll("\\\\(.)", "$1"));
}
  :  '"'  (~('"' | '\\')  | '\\' ('\\' | '"'))* '"' 
  |  '\'' (~('\'' | '\\') | '\\' ('\\' | '\''))* '\''
  ;

Comment
  :  '//' ~('\r' | '\n')* {skip();}
  |  '/*' .* '*/'         {skip();}
  ;

Space
  :  (' ' | '\t' | '\r' | '\n' | '\u000C') {skip();}
  ;
  
  
  
fragment ExponentPart
    :   ExponentIndicator SignedInteger
    ;

fragment ExponentIndicator
    :   ('e' | 'E')
    ;

fragment SignedInteger
    :   Sign? Digits
    ;

fragment Sign
    :   ('+' | '-')
    ;

fragment DecimalNumeral
  :  '0'
  |  NonZeroDigit Digits?;

fragment Digits
    :   Digit Digit*
    ;  
    
fragment Digit 
  :  '0'
  |  NonZeroDigit
  ;
  
fragment NonZeroDigit 
  :  '1'..'9'
  ;


 