tree grammar PlazmaScriptWalker;

options {
  tokenVocab=PlazmaScript;
  ASTLabelType=CommonTree;
}

@header {
  package plazma.parser;
  import plazma.*;
  import plazma.ast.*;
  import plazma.ast.functions.*;
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
  BlockNode bn = new BlockNode();
  node = bn;
  Scope local = new Scope(currentScope);
  currentScope = local;
}
@after {
  currentScope = currentScope.parent();
}
  :  ^(BLOCK ^(STATEMENTS (statement {bn.addStatement($statement.node);})*) ^(RETURN (expression {bn.addReturn($expression.node);})?))
  ;

statement returns [LNode node]
  :  assignment     {node = $assignment.node;}
  |  functionCall   {node = $functionCall.node;}
  |  ifStatement    {node = $ifStatement.node;}
  |  forStatement   {node = $forStatement.node;}
//  |  forStatement2  {node = $forStatement2.node;}  
  |  whileStatement {node = $whileStatement.node;}
  |  Break                                             {node = new BreakNode();}
  |  Continue                                          {node = new ContinueNode();}
  
  ;

assignment returns [LNode node]
  :  ^(ASSIGNMENT Identifier indexes? expression) {node = new AssignmentNode($Identifier.text, $indexes.e, $expression.node, currentScope, globalScope);}
  ;

functionCall returns [LNode node]
  :  ^(FUNC_CALL Identifier exprList?) {node = new FunctionCallNode($Identifier.text, $exprList.e, functions, globalScope);}
  |  ^(FUNC_CALL Println expression?)  {node = new PrintlnNode($expression.node);}
  |  ^(FUNC_CALL Print expression)     {node = new PrintNode($expression.node);}
  |  ^(FUNC_CALL Assert expression)    {node = new AssertNode($expression.node);}
  |  ^(FUNC_CALL Size expression)      {node = new SizeNode($expression.node);}
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
  :  ^(EXP block) {parent.addChoice(new AtomNode(true), $block.node);}
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
  
  |  ^('||' a=expression b=expression)                 {node = new OrNode($a.node, $b.node);}
  |  ^('&&' a=expression b=expression)                 {node = new AndNode($a.node, $b.node);}
  |  ^('==' a=expression b=expression)                 {node = new EqualsNode($a.node, $b.node);}
  |  ^('!=' a=expression b=expression)                 {node = new NotEqualsNode($a.node, $b.node);}
  |  ^('>=' a=expression b=expression)                 {node = new GTEqualsNode($a.node, $b.node);}
  |  ^('<=' a=expression b=expression)                 {node = new LTEqualsNode($a.node, $b.node);}
  |  ^('>' a=expression b=expression)                  {node = new GTNode($a.node, $b.node);}
  |  ^('<' a=expression b=expression)                  {node = new LTNode($a.node, $b.node);}
  |  ^('+' a=expression b=expression)                  {node = new AddNode($a.node, $b.node);}
  |  ^('-' a=expression b=expression)                  {node = new SubNode($a.node, $b.node);}
  |  ^('*' a=expression b=expression)                  {node = new MulNode($a.node, $b.node);}
  |  ^('/' a=expression b=expression)                  {node = new DivNode($a.node, $b.node);}
  |  ^('%' a=expression b=expression)                  {node = new ModNode($a.node, $b.node);}
  |  ^('^' a=expression b=expression)                  {node = new PowNode($a.node, $b.node);}
  |  ^(UNARY_MIN a=expression)                         {node = new UnaryMinusNode($a.node);}
  |  ^(NEGATE a=expression)                            {node = new NegateNode($a.node);}
 
  |  Integer                                           {node = new AtomNode(new Integer($Integer.text));}
  |  Number                                            {node = new AtomNode(Double.parseDouble($Number.text));}
  |  Bool                                              {node = new AtomNode(Boolean.parseBoolean($Bool.text));}
  |  Date                                              {node = new AtomNode(AtomNode.parseDate($Date.text));}  
  |  Null                                              {node = new NullNode();}
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
  |  ^(LOOKUP String i=indexes?)       {node = $i.e != null ? new LookupNode(new AtomNode($String.text), $indexes.e) : new AtomNode($String.text);}
  ;

indexes returns [java.util.List<LNode> e]
@init {e = new java.util.ArrayList<LNode>();}
  :  ^(INDEXES (expression {e.add($expression.node);})+)
  ;
  
