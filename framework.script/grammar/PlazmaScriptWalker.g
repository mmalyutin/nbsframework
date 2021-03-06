tree grammar PlazmaScriptWalker;

options {
  tokenVocab=PlazmaScript;
  ASTLabelType=CommonTree;
}

@header {
  package org.plazmaforge.framework.script.parser;
  import org.plazmaforge.framework.script.*;
  import org.plazmaforge.framework.script.ast.*;
  import org.plazmaforge.framework.script.ast.functions.*;
  import org.plazmaforge.framework.script.ast.operators.*;
  import org.plazmaforge.framework.script.ast.values.*;  
  import java.util.Map;
  import java.util.HashMap;
}

@members {
  public Map<String, Function> functions = null;
  Scope globalScope;  
  Scope currentScope;
  
  public PlazmaScriptWalker(CommonTreeNodeStream nodes, Map<String, Function> functions) {
    this(nodes, functions, null, null);
  }
  
  public PlazmaScriptWalker(CommonTreeNodeStream nodes, Map<String, Function> functions, Scope scope, Scope globalScope) {
    super(nodes);
    this.currentScope = scope;
    this.globalScope = ScriptUtils.getSafeScope(globalScope);
    this.functions = functions;
  }
  
  
}

walk returns [LNode node]
  :  block {node = $block.node;}
  ;

block returns [LNode node]
@init {
  
  //BlockNode bn = new BlockNode();
  //node = bn;
  //Scope local = new Scope(currentScope);
  //currentScope = local;

  Scope local = new Scope(currentScope);
  currentScope = local;
  BlockNode bn = new BlockNode(local);
  node = bn;
  
}
@after {
  currentScope = currentScope.parent();
}
  :  ^(BLOCK ^(STATEMENTS (statement {bn.addStatement($statement.node);})*) ^(RETURN (expression {bn.addReturn($expression.node);})?))
  ;

statement returns [LNode node]
  :  assignment     {node = $assignment.node;}
  |  declare        {node = $declare.node;}
  |  functionCall   {node = $functionCall.node;}
  |  lookup         {node = $lookup.node;}  
  |  ifStatement    {node = $ifStatement.node;}
  |  forStatement   {node = $forStatement.node;}
//  |  forStatement2  {node = $forStatement2.node;}  
  |  whileStatement {node = $whileStatement.node;}
  |  Break                                             {node = new BreakNode();}
  |  Continue                                          {node = new ContinueNode();}
  
  ;


assignment returns [LNode node]
  :  ^(ASSIGNMENT variableDef? Identifier indexes? expression) {node = new AssignmentNode($variableDef.node, $Identifier.text, $indexes.e, $expression.node, currentScope, globalScope);}
  ;

declare returns [LNode node]
  :  ^(DECLARE variableDef Identifier) {node = new AssignmentNode($variableDef.node, $Identifier.text, null, null, currentScope, globalScope);}
  ;

functionCall returns [LNode node]
  :  ^(FUNC_CALL Identifier exprList?) {node = new FunctionCallNode($Identifier.text, $exprList.e, functions, globalScope);}
  |  ^(FUNC_CALL Println expression?)  {node = new PrintlnNode($expression.node);}
  |  ^(FUNC_CALL Print expression)     {node = new PrintNode($expression.node);}
  |  ^(FUNC_CALL Assert expression)    {node = new AssertNode($expression.node);}
  |  ^(FUNC_CALL Date exprList?)       {node = new DateNode($exprList.e);}
  |  ^(FUNC_CALL DateTime exprList?)   {node = new DateTimeNode($exprList.e);}  
  |  ^(FUNC_CALL Time exprList?)       {node = new TimeNode($exprList.e);}
  |  ^(FUNC_CALL Duration expression?) {node = new DurationNode($expression.node);}
  |  ^(FUNC_CALL Period exprList?)     {node = new PeriodNode($exprList.e);}        
  |  ^(FUNC_CALL List exprList?)       {node = new ListNode($exprList.e);}
  |  ^(FUNC_CALL Set exprList?)        {node = new SetNode($exprList.e);}    
  ;

ifStatement returns [LNode node]
@init  {IfNode ifNode = new IfNode();}
@after {node = ifNode;}
  :  ^(IF ifStat[ifNode] (elseIfStat[ifNode])* (elseStat[ifNode])?)
  ;

ifStat[IfNode parent]
  :  ^(EXP expression block) {parent.addChoice($expression.node, $block.node);}
  ;

elseIfStat[IfNode parent]
  :  ^(EXP expression block) {parent.addChoice($expression.node, $block.node);}
  ;

elseStat[IfNode parent]
  :  ^(EXP block) {parent.addChoice(new BooleanNode(true), $block.node);}
  ;
   
//forStatement returns [LNode node]
//  :  ^(For Identifier a=expression RangeSeparator b=expression block) {node = new ForStatementNode($Identifier.text, $a.node, $RangeSeparator.text, $b.node, $block.node, currentScope);}
//  ;
  
forStatement returns [LNode node]
  :  ^(For Identifier a=expression block) {node = new ForStatementNode2($Identifier.text, $a.node, $block.node, currentScope);}
  ;
  

whileStatement returns [LNode node]
  :  ^(While expression block) {node = new WhileStatementNode($expression.node, $block.node);}
  ;

idList returns [java.util.List<String> i]
@init {i = new java.util.ArrayList<String>();}
  :  ^(ID_LIST (Identifier {i.add($Identifier.text);})+)
  ;

exprList returns [java.util.List<LNode> e]
@init  {e = new java.util.ArrayList<LNode>();}
  :  ^(EXP_LIST (expression {e.add($expression.node);})+)
  ;
exprPair returns [PairNode node]
  :  ^(EXP_PAIR k=expression v=expression) {node = new PairNode($k.node, $v.node);}
  ;

exprMap returns [java.util.List<PairNode> e]
@init  {e = new java.util.ArrayList<PairNode>();}
  :  ^(EXP_MAP (exprPair {e.add($exprPair.node);})+)
  ;


expression returns [LNode node]
  :  ^(TERNARY a=expression b=expression c=expression) {node = new TernaryNode($a.node, $b.node, $c.node);}
  |  ^(In a=expression b=expression)                   {node = new InNode($a.node, $b.node);}
  
  |  ^(RangeE a=expression b=expression)                {node = new RangeENode($a.node, $b.node);}
  |  ^(Range a=expression b=expression)                 {node = new RangeNode($a.node, $b.node);}
  
  |  ^('xor' a=expression b=expression)                {node = new XorNode($a.node, $b.node);}
  |  ^('||' a=expression b=expression)                 {node = new OrNode($a.node, $b.node);}
  |  ^('|' a=expression b=expression)                  {node = new BitOrNode($a.node, $b.node);}  
  |  ^('or' a=expression b=expression)                 {node = new OrNode($a.node, $b.node);}  
  |  ^('&&' a=expression b=expression)                 {node = new AndNode($a.node, $b.node);}
  |  ^('&' a=expression b=expression)                  {node = new BitAndNode($a.node, $b.node);}  
  |  ^('and' a=expression b=expression)                {node = new AndNode($a.node, $b.node);}  
  |  ^('==' a=expression b=expression)                 {node = new EQNode($a.node, $b.node);}
  |  ^('!=' a=expression b=expression)                 {node = new NENode($a.node, $b.node);}
  |  ^('>=' a=expression b=expression)                 {node = new GTENode($a.node, $b.node);}
  |  ^('<=' a=expression b=expression)                 {node = new LTENode($a.node, $b.node);}
  |  ^('>' a=expression b=expression)                  {node = new GTNode($a.node, $b.node);}
  |  ^('<' a=expression b=expression)                  {node = new LTNode($a.node, $b.node);}
  |  ^('+' a=expression b=expression)                  {node = new AddNode($a.node, $b.node);}
  |  ^('-' a=expression b=expression)                  {node = new SubNode($a.node, $b.node);}
  |  ^('*' a=expression b=expression)                  {node = new MulNode($a.node, $b.node);}
  |  ^('/' a=expression b=expression)                  {node = new DivNode($a.node, $b.node);}
  //|  ^('\\' a=expression b=expression)                  {node = new DivNode($a.node, $b.node);}  
  |  ^('%' a=expression b=expression)                  {node = new ModNode($a.node, $b.node);}
  |  ^('^' a=expression b=expression)                  {node = new PowNode($a.node, $b.node);}
  |  ^(UNARY_PLUS a=expression)                         {node = new UnaryPlusNode($a.node);}  
  |  ^(UNARY_MIN a=expression)                         {node = new UnaryMinusNode($a.node);}
  |  ^(NEGATE a=expression)                            {node = new NotNode($a.node);}
 
  |  Integer                                           {node = new IntegerNode($Integer.text);}                         // {node = new AtomNode(new Integer($Integer.text));}
  |  Number                                            {node = new NumberNode($Number.text);}                          // {node = new AtomNode(Double.parseDouble($Number.text));}
  |  Bool                                              {node = new BooleanNode($Bool.text);}                           // {node = new AtomNode(Boolean.parseBoolean($Bool.text));}
  |  Null                                              {node = new NullNode();}
  |  NaN                                               {node = new NaNNode();}  
  |  Infinity                                          {node = new InfinityNode();}  
  |  lookup                                            {node = $lookup.node;}
  ;

list returns [LNode node]
  :  ^(LIST exprList?) {node = new ListNode($exprList.e);}
  ;

map returns [LNode node]
  :  ^(MAP exprMap?) {node = new MapNode($exprMap.e);}
  ;

lookup returns [LNode node]
  :  ^(LOOKUP functionCall i=indexes?) {node = $i.e != null ? new LookupNode($functionCall.node, $indexes.e) : $functionCall.node;}
  |  ^(LOOKUP list i=indexes?)         {node = $i.e != null ? new LookupNode($list.node, $indexes.e) : $list.node;}
  |  ^(LOOKUP map i=indexes?)          {node = $i.e != null ? new LookupNode($map.node, $indexes.e) : $map.node;}  
  |  ^(LOOKUP expression i=indexes?)   {node = $i.e != null ? new LookupNode($expression.node, $indexes.e) : $expression.node;}
  |  ^(LOOKUP Identifier i=indexes?)   {node = $i.e != null ? new LookupNode(new IdentifierNode($Identifier.text, currentScope, globalScope), $indexes.e) : new IdentifierNode($Identifier.text, currentScope, globalScope);}
  |  ^(LOOKUP String i=indexes?)       {node = $i.e != null ? new LookupNode(new StringNode($String.text), $indexes.e) : new StringNode($String.text);}
  ;

indexes returns [java.util.List<LNode> e]
@init {e = new java.util.ArrayList<LNode>();}
  //:  ^(INDEXES (expression {e.add($expression.node);})+)
  :    ^(TAILS   (tail {e.add($tail.node);})+)
  ;

  
tail returns [LNode node]
 : ^(INDEX expression)           {node = $expression.node;}
 | ^(ATTRIBUTE Identifier)       {node = new StringNode($Identifier.text);}
 | ^(CALL Identifier exprList?)  {node = new MethodCallNode($Identifier.text, $exprList.e, functions, globalScope);}
 ;
 
variableDef returns [LNode node]  
  : Var {node = new VariableDefNode($Var.text, null);}
  ; 