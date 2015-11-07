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
  EXP;
  EXP_PAIR;
  EXP_MAP;
  EXP_LIST;
  ID_LIST;
  IF;
  TERNARY;
  UNARY_MIN;
  NEGATE;
  FUNCTION;
  INDEXES;
  MAP;
  LIST;  
  LOOKUP;
  BREAK;
  CONTINUE;
}

@parser::header {
  package plazma.parser;
  import plazma.*; 
  import java.util.Map;
  import java.util.HashMap;
}

@lexer::header {
  package plazma.parser;
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
  |  ifStatement
  |  forStatement
  |  whileStatement
  |  Break ';' -> Break
  |  Continue ';' -> Continue
  ;

assignment
  :  Identifier indexes? '=' expression -> ^(ASSIGNMENT Identifier indexes? expression)
  |  anyIdentifier indexes? '=' expression -> ^(ASSIGNMENT {new CommonTree(new CommonToken(Identifier, $anyIdentifier.text))} indexes? expression)
  ;

functionCall
  :  Identifier '(' exprList? ')' -> ^(FUNC_CALL Identifier exprList?)
  |  Println '(' expression? ')'  -> ^(FUNC_CALL Println expression?)
  |  Print '(' expression ')'     -> ^(FUNC_CALL Print expression)
  |  Assert '(' expression ')'    -> ^(FUNC_CALL Assert expression)
  |  Size '(' expression ')'      -> ^(FUNC_CALL Size expression)
  ;

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
  :  (orExpr -> orExpr) 
     ( '?' a=expression ':' b=expression -> ^(TERNARY orExpr $a $b)
     | In expression                     -> ^(In orExpr expression)
     
     | RangeE expression                 -> ^(RangeE orExpr expression)
     | Range expression                  -> ^(Range orExpr expression)
               
     )?
  ;

orExpr
  :  andExpr ('||'^ andExpr)*
  ;

andExpr
  :  equExpr ('&&'^ equExpr)*
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
  :  '-' atom -> ^(UNARY_MIN atom)
  |  '!' atom -> ^(NEGATE atom)
  |  atom
  ;

atom
  :  Integer
  |  Number
  |  Bool
  |  Date
  |  Null
  |  lookup
  ;

list
  :  '{' exprList? '}' -> ^(LIST exprList?)
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
  :  ('[' expression ']')+ -> ^(INDEXES expression+)
  ;


Println  : 'println';
Print    : 'print';
Assert   : 'assert';
Size     : 'size';
Def      : 'def';
If       : 'if';
Else     : 'else';
Return   : 'return';
For      : 'for';
While    : 'while';
In       : 'in';
Null     : 'null';
Break    : 'break';
Continue : 'continue';

Or       : '||';
And      : '&&';
Equals   : '==';
NEquals  : '!=';
GTEquals : '>=';
LTEquals : '<=';
Pow      : '^';
Excl     : '!';
GT       : '>';
LT       : '<';
Add      : '+';
Subtract : '-';
Multiply : '*';
Divide   : '/';
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
  :  Int;


Number
  : (Int '..') => Integer {$type=Integer;}  
    | Int ('.' Digit*)?
  ;



Date 
  : '#Date{' YYYY '-' MM '-' DD '}';
  

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

fragment Int
  :  '1'..'9' Digit* | '0';
  
fragment Digit 
  :  '0'..'9'
  ;
  
fragment YYYY
  //:  Int;
  :  '1'..'9' Digit*;  

fragment MM
  :  ('1'..'9') | ('0' '1'..'9') | ('1' '0'..'2');

fragment DD
//:  ('1'..'9') | ('0'..'2' '0'..'9') | ('3' '0'..'1') | ~('00');
  :  ('1'..'9') | ('0' '1'..'9') | ('1'..'2' '0'..'9') | ('3' '0'..'1');


 