// $ANTLR 3.2 Sep 23, 2009 12:02:23 grammar/PlazmaScriptWalker.g 2016-08-08 15:42:55

  package org.plazmaforge.framework.script.parser;
  import org.plazmaforge.framework.script.*;
  import org.plazmaforge.framework.script.ast.*;
  import org.plazmaforge.framework.script.ast.functions.*;
  import org.plazmaforge.framework.script.ast.operators.*;
  import org.plazmaforge.framework.script.ast.values.*;  
  import java.util.Map;
  import java.util.HashMap;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class PlazmaScriptWalker extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BLOCK", "RETURN", "STATEMENTS", "ASSIGNMENT", "FUNC_CALL", "EXP", "EXP_PAIR", "EXP_MAP", "EXP_LIST", "ID_LIST", "IF", "TERNARY", "UNARY_PLUS", "UNARY_MIN", "NEGATE", "FUNCTION", "INDEXES", "INDEX", "ATTRIBUTE", "CALL", "TAIL", "TAILS", "MAP", "LIST", "SET", "LOOKUP", "BREAK", "CONTINUE", "Return", "Break", "Continue", "Identifier", "Println", "Print", "Assert", "Date", "List", "Set", "If", "Else", "Var", "Def", "For", "While", "In", "RangeE", "Range", "Integer", "Number", "Bool", "Null", "NaN", "Infinity", "String", "XorWord", "Or", "BitOr", "OrWord", "And", "BitAnd", "AndWord", "Equals", "NEquals", "GTEquals", "LTEquals", "Pow", "Not", "NotWord", "GT", "LT", "Add", "Subtract", "Multiply", "Divide", "Modulus", "OBrace", "CBrace", "OBracket", "CBracket", "OParen", "CParen", "SColon", "Assign", "Comma", "QMark", "Colon", "Int", "Digit", "ContextIdentifier", "Comment", "Space", "YYYY", "MM", "DD", "'.'"
    };
    public static final int FUNCTION=19;
    public static final int LT=73;
    public static final int TERNARY=15;
    public static final int EXP_LIST=12;
    public static final int Date=39;
    public static final int EOF=-1;
    public static final int DD=97;
    public static final int QMark=88;
    public static final int NotWord=71;
    public static final int BREAK=30;
    public static final int Identifier=35;
    public static final int Int=90;
    public static final int UNARY_PLUS=16;
    public static final int FUNC_CALL=8;
    public static final int CParen=84;
    public static final int Comment=93;
    public static final int EXP=9;
    public static final int MM=96;
    public static final int CBrace=80;
    public static final int RETURN=5;
    public static final int T__98=98;
    public static final int OrWord=61;
    public static final int Null=54;
    public static final int CBracket=82;
    public static final int ContextIdentifier=92;
    public static final int Println=36;
    public static final int Bool=53;
    public static final int Modulus=78;
    public static final int Colon=89;
    public static final int AndWord=64;
    public static final int LIST=27;
    public static final int Def=45;
    public static final int RangeE=49;
    public static final int LOOKUP=29;
    public static final int Range=50;
    public static final int Break=33;
    public static final int BitOr=60;
    public static final int GT=72;
    public static final int STATEMENTS=6;
    public static final int CALL=23;
    public static final int Else=43;
    public static final int Equals=65;
    public static final int Var=44;
    public static final int XorWord=58;
    public static final int OParen=83;
    public static final int YYYY=95;
    public static final int Assert=38;
    public static final int ATTRIBUTE=22;
    public static final int While=47;
    public static final int ID_LIST=13;
    public static final int Add=74;
    public static final int Set=41;
    public static final int TAIL=24;
    public static final int IF=14;
    public static final int Space=94;
    public static final int INDEX=21;
    public static final int Assign=86;
    public static final int EXP_MAP=11;
    public static final int NaN=55;
    public static final int Number=52;
    public static final int CONTINUE=31;
    public static final int Print=37;
    public static final int GTEquals=67;
    public static final int String=57;
    public static final int Or=59;
    public static final int Return=32;
    public static final int If=42;
    public static final int And=62;
    public static final int In=48;
    public static final int NEquals=66;
    public static final int Continue=34;
    public static final int Subtract=75;
    public static final int EXP_PAIR=10;
    public static final int BitAnd=63;
    public static final int Multiply=76;
    public static final int OBrace=79;
    public static final int INDEXES=20;
    public static final int NEGATE=18;
    public static final int SET=28;
    public static final int Digit=91;
    public static final int For=46;
    public static final int Divide=77;
    public static final int List=40;
    public static final int TAILS=25;
    public static final int SColon=85;
    public static final int OBracket=81;
    public static final int BLOCK=4;
    public static final int MAP=26;
    public static final int Not=70;
    public static final int UNARY_MIN=17;
    public static final int ASSIGNMENT=7;
    public static final int Infinity=56;
    public static final int Comma=87;
    public static final int Integer=51;
    public static final int Pow=69;
    public static final int LTEquals=68;

    // delegates
    // delegators


        public PlazmaScriptWalker(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public PlazmaScriptWalker(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return PlazmaScriptWalker.tokenNames; }
    public String getGrammarFileName() { return "grammar/PlazmaScriptWalker.g"; }


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
      
      



    // $ANTLR start "walk"
    // grammar/PlazmaScriptWalker.g:38:1: walk returns [LNode node] : block ;
    public final LNode walk() throws RecognitionException {
        LNode node = null;

        LNode block1 = null;


        try {
            // grammar/PlazmaScriptWalker.g:39:3: ( block )
            // grammar/PlazmaScriptWalker.g:39:6: block
            {
            pushFollow(FOLLOW_block_in_walk50);
            block1=block();

            state._fsp--;

            node = block1;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return node;
    }
    // $ANTLR end "walk"


    // $ANTLR start "block"
    // grammar/PlazmaScriptWalker.g:42:1: block returns [LNode node] : ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) ) ;
    public final LNode block() throws RecognitionException {
        LNode node = null;

        LNode statement2 = null;

        LNode expression3 = null;



          
          //BlockNode bn = new BlockNode();
          //node = bn;
          //Scope local = new Scope(currentScope);
          //currentScope = local;

          Scope local = new Scope(currentScope);
          currentScope = local;
          BlockNode bn = new BlockNode(local);
          node = bn;
          

        try {
            // grammar/PlazmaScriptWalker.g:59:3: ( ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) ) )
            // grammar/PlazmaScriptWalker.g:59:6: ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) )
            {
            match(input,BLOCK,FOLLOW_BLOCK_in_block81); 

            match(input, Token.DOWN, null); 
            match(input,STATEMENTS,FOLLOW_STATEMENTS_in_block84); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // grammar/PlazmaScriptWalker.g:59:27: ( statement )*
                loop1:
                do {
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( ((LA1_0>=ASSIGNMENT && LA1_0<=FUNC_CALL)||LA1_0==IF||LA1_0==LOOKUP||(LA1_0>=Break && LA1_0<=Continue)||(LA1_0>=For && LA1_0<=While)) ) {
                        alt1=1;
                    }


                    switch (alt1) {
                	case 1 :
                	    // grammar/PlazmaScriptWalker.g:59:28: statement
                	    {
                	    pushFollow(FOLLOW_statement_in_block87);
                	    statement2=statement();

                	    state._fsp--;

                	    bn.addStatement(statement2);

                	    }
                	    break;

                	default :
                	    break loop1;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }
            match(input,RETURN,FOLLOW_RETURN_in_block95); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // grammar/PlazmaScriptWalker.g:59:86: ( expression )?
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>=TERNARY && LA2_0<=NEGATE)||LA2_0==LOOKUP||(LA2_0>=In && LA2_0<=Infinity)||(LA2_0>=XorWord && LA2_0<=Pow)||(LA2_0>=GT && LA2_0<=Modulus)) ) {
                    alt2=1;
                }
                switch (alt2) {
                    case 1 :
                        // grammar/PlazmaScriptWalker.g:59:87: expression
                        {
                        pushFollow(FOLLOW_expression_in_block98);
                        expression3=expression();

                        state._fsp--;

                        bn.addReturn(expression3);

                        }
                        break;

                }


                match(input, Token.UP, null); 
            }

            match(input, Token.UP, null); 

            }


              currentScope = currentScope.parent();

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return node;
    }
    // $ANTLR end "block"


    // $ANTLR start "statement"
    // grammar/PlazmaScriptWalker.g:62:1: statement returns [LNode node] : ( assignment | functionCall | lookup | ifStatement | forStatement | whileStatement | Break | Continue );
    public final LNode statement() throws RecognitionException {
        LNode node = null;

        LNode assignment4 = null;

        LNode functionCall5 = null;

        LNode lookup6 = null;

        LNode ifStatement7 = null;

        LNode forStatement8 = null;

        LNode whileStatement9 = null;


        try {
            // grammar/PlazmaScriptWalker.g:63:3: ( assignment | functionCall | lookup | ifStatement | forStatement | whileStatement | Break | Continue )
            int alt3=8;
            switch ( input.LA(1) ) {
            case ASSIGNMENT:
                {
                alt3=1;
                }
                break;
            case FUNC_CALL:
                {
                alt3=2;
                }
                break;
            case LOOKUP:
                {
                alt3=3;
                }
                break;
            case IF:
                {
                alt3=4;
                }
                break;
            case For:
                {
                alt3=5;
                }
                break;
            case While:
                {
                alt3=6;
                }
                break;
            case Break:
                {
                alt3=7;
                }
                break;
            case Continue:
                {
                alt3=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:63:6: assignment
                    {
                    pushFollow(FOLLOW_assignment_in_statement122);
                    assignment4=assignment();

                    state._fsp--;

                    node = assignment4;

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:64:6: functionCall
                    {
                    pushFollow(FOLLOW_functionCall_in_statement135);
                    functionCall5=functionCall();

                    state._fsp--;

                    node = functionCall5;

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:65:6: lookup
                    {
                    pushFollow(FOLLOW_lookup_in_statement146);
                    lookup6=lookup();

                    state._fsp--;

                    node = lookup6;

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:66:6: ifStatement
                    {
                    pushFollow(FOLLOW_ifStatement_in_statement165);
                    ifStatement7=ifStatement();

                    state._fsp--;

                    node = ifStatement7;

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:67:6: forStatement
                    {
                    pushFollow(FOLLOW_forStatement_in_statement177);
                    forStatement8=forStatement();

                    state._fsp--;

                    node = forStatement8;

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScriptWalker.g:69:6: whileStatement
                    {
                    pushFollow(FOLLOW_whileStatement_in_statement189);
                    whileStatement9=whileStatement();

                    state._fsp--;

                    node = whileStatement9;

                    }
                    break;
                case 7 :
                    // grammar/PlazmaScriptWalker.g:70:6: Break
                    {
                    match(input,Break,FOLLOW_Break_in_statement198); 
                    node = new BreakNode();

                    }
                    break;
                case 8 :
                    // grammar/PlazmaScriptWalker.g:71:6: Continue
                    {
                    match(input,Continue,FOLLOW_Continue_in_statement251); 
                    node = new ContinueNode();

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return node;
    }
    // $ANTLR end "statement"


    // $ANTLR start "assignment"
    // grammar/PlazmaScriptWalker.g:76:1: assignment returns [LNode node] : ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression ) ;
    public final LNode assignment() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier11=null;
        LNode variableDef10 = null;

        java.util.List<LNode> indexes12 = null;

        LNode expression13 = null;


        try {
            // grammar/PlazmaScriptWalker.g:77:3: ( ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression ) )
            // grammar/PlazmaScriptWalker.g:77:6: ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression )
            {
            match(input,ASSIGNMENT,FOLLOW_ASSIGNMENT_in_assignment317); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:77:19: ( variableDef )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==Var) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:77:19: variableDef
                    {
                    pushFollow(FOLLOW_variableDef_in_assignment319);
                    variableDef10=variableDef();

                    state._fsp--;


                    }
                    break;

            }

            Identifier11=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_assignment322); 
            // grammar/PlazmaScriptWalker.g:77:43: ( indexes )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==TAILS) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:77:43: indexes
                    {
                    pushFollow(FOLLOW_indexes_in_assignment324);
                    indexes12=indexes();

                    state._fsp--;


                    }
                    break;

            }

            pushFollow(FOLLOW_expression_in_assignment327);
            expression13=expression();

            state._fsp--;


            match(input, Token.UP, null); 
            node = new AssignmentNode(variableDef10, (Identifier11!=null?Identifier11.getText():null), indexes12, expression13, currentScope, globalScope);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return node;
    }
    // $ANTLR end "assignment"


    // $ANTLR start "functionCall"
    // grammar/PlazmaScriptWalker.g:80:1: functionCall returns [LNode node] : ( ^( FUNC_CALL Identifier ( exprList )? ) | ^( FUNC_CALL Println ( expression )? ) | ^( FUNC_CALL Print expression ) | ^( FUNC_CALL Assert expression ) | ^( FUNC_CALL Date ( exprList )? ) | ^( FUNC_CALL List ( exprList )? ) | ^( FUNC_CALL Set ( exprList )? ) );
    public final LNode functionCall() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier14=null;
        java.util.List<LNode> exprList15 = null;

        LNode expression16 = null;

        LNode expression17 = null;

        LNode expression18 = null;

        java.util.List<LNode> exprList19 = null;

        java.util.List<LNode> exprList20 = null;

        java.util.List<LNode> exprList21 = null;


        try {
            // grammar/PlazmaScriptWalker.g:81:3: ( ^( FUNC_CALL Identifier ( exprList )? ) | ^( FUNC_CALL Println ( expression )? ) | ^( FUNC_CALL Print expression ) | ^( FUNC_CALL Assert expression ) | ^( FUNC_CALL Date ( exprList )? ) | ^( FUNC_CALL List ( exprList )? ) | ^( FUNC_CALL Set ( exprList )? ) )
            int alt11=7;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:81:6: ^( FUNC_CALL Identifier ( exprList )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall349); 

                    match(input, Token.DOWN, null); 
                    Identifier14=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_functionCall351); 
                    // grammar/PlazmaScriptWalker.g:81:29: ( exprList )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==EXP_LIST) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:81:29: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall353);
                            exprList15=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new FunctionCallNode((Identifier14!=null?Identifier14.getText():null), exprList15, functions, globalScope);

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:82:6: ^( FUNC_CALL Println ( expression )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall365); 

                    match(input, Token.DOWN, null); 
                    match(input,Println,FOLLOW_Println_in_functionCall367); 
                    // grammar/PlazmaScriptWalker.g:82:26: ( expression )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( ((LA7_0>=TERNARY && LA7_0<=NEGATE)||LA7_0==LOOKUP||(LA7_0>=In && LA7_0<=Infinity)||(LA7_0>=XorWord && LA7_0<=Pow)||(LA7_0>=GT && LA7_0<=Modulus)) ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:82:26: expression
                            {
                            pushFollow(FOLLOW_expression_in_functionCall369);
                            expression16=expression();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new PrintlnNode(expression16);

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:83:6: ^( FUNC_CALL Print expression )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall382); 

                    match(input, Token.DOWN, null); 
                    match(input,Print,FOLLOW_Print_in_functionCall384); 
                    pushFollow(FOLLOW_expression_in_functionCall386);
                    expression17=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new PrintNode(expression17);

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:84:6: ^( FUNC_CALL Assert expression )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall401); 

                    match(input, Token.DOWN, null); 
                    match(input,Assert,FOLLOW_Assert_in_functionCall403); 
                    pushFollow(FOLLOW_expression_in_functionCall405);
                    expression18=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AssertNode(expression18);

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:85:6: ^( FUNC_CALL Date ( exprList )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall419); 

                    match(input, Token.DOWN, null); 
                    match(input,Date,FOLLOW_Date_in_functionCall421); 
                    // grammar/PlazmaScriptWalker.g:85:23: ( exprList )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==EXP_LIST) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:85:23: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall423);
                            exprList19=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new DateNode(exprList19);

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScriptWalker.g:86:6: ^( FUNC_CALL List ( exprList )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall441); 

                    match(input, Token.DOWN, null); 
                    match(input,List,FOLLOW_List_in_functionCall443); 
                    // grammar/PlazmaScriptWalker.g:86:23: ( exprList )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==EXP_LIST) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:86:23: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall445);
                            exprList20=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new ListNode(exprList20);

                    }
                    break;
                case 7 :
                    // grammar/PlazmaScriptWalker.g:87:6: ^( FUNC_CALL Set ( exprList )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall463); 

                    match(input, Token.DOWN, null); 
                    match(input,Set,FOLLOW_Set_in_functionCall465); 
                    // grammar/PlazmaScriptWalker.g:87:22: ( exprList )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==EXP_LIST) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:87:22: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall467);
                            exprList21=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new SetNode(exprList21);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return node;
    }
    // $ANTLR end "functionCall"


    // $ANTLR start "ifStatement"
    // grammar/PlazmaScriptWalker.g:90:1: ifStatement returns [LNode node] : ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? ) ;
    public final LNode ifStatement() throws RecognitionException {
        LNode node = null;

        IfNode ifNode = new IfNode();
        try {
            // grammar/PlazmaScriptWalker.g:93:3: ( ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? ) )
            // grammar/PlazmaScriptWalker.g:93:6: ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? )
            {
            match(input,IF,FOLLOW_IF_in_ifStatement511); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_ifStat_in_ifStatement513);
            ifStat(ifNode);

            state._fsp--;

            // grammar/PlazmaScriptWalker.g:93:26: ( elseIfStat[ifNode] )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==EXP) ) {
                    int LA12_1 = input.LA(2);

                    if ( (LA12_1==DOWN) ) {
                        int LA12_3 = input.LA(3);

                        if ( ((LA12_3>=TERNARY && LA12_3<=NEGATE)||LA12_3==LOOKUP||(LA12_3>=In && LA12_3<=Infinity)||(LA12_3>=XorWord && LA12_3<=Pow)||(LA12_3>=GT && LA12_3<=Modulus)) ) {
                            alt12=1;
                        }


                    }


                }


                switch (alt12) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:93:27: elseIfStat[ifNode]
            	    {
            	    pushFollow(FOLLOW_elseIfStat_in_ifStatement517);
            	    elseIfStat(ifNode);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            // grammar/PlazmaScriptWalker.g:93:48: ( elseStat[ifNode] )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==EXP) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:93:49: elseStat[ifNode]
                    {
                    pushFollow(FOLLOW_elseStat_in_ifStatement523);
                    elseStat(ifNode);

                    state._fsp--;


                    }
                    break;

            }


            match(input, Token.UP, null); 

            }

            node = ifNode;
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return node;
    }
    // $ANTLR end "ifStatement"


    // $ANTLR start "ifStat"
    // grammar/PlazmaScriptWalker.g:96:1: ifStat[IfNode parent] : ^( EXP expression block ) ;
    public final void ifStat(IfNode parent) throws RecognitionException {
        LNode expression22 = null;

        LNode block23 = null;


        try {
            // grammar/PlazmaScriptWalker.g:97:3: ( ^( EXP expression block ) )
            // grammar/PlazmaScriptWalker.g:97:6: ^( EXP expression block )
            {
            match(input,EXP,FOLLOW_EXP_in_ifStat543); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_ifStat545);
            expression22=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_ifStat547);
            block23=block();

            state._fsp--;


            match(input, Token.UP, null); 
            parent.addChoice(expression22, block23);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "ifStat"


    // $ANTLR start "elseIfStat"
    // grammar/PlazmaScriptWalker.g:100:1: elseIfStat[IfNode parent] : ^( EXP expression block ) ;
    public final void elseIfStat(IfNode parent) throws RecognitionException {
        LNode expression24 = null;

        LNode block25 = null;


        try {
            // grammar/PlazmaScriptWalker.g:101:3: ( ^( EXP expression block ) )
            // grammar/PlazmaScriptWalker.g:101:6: ^( EXP expression block )
            {
            match(input,EXP,FOLLOW_EXP_in_elseIfStat566); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_elseIfStat568);
            expression24=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_elseIfStat570);
            block25=block();

            state._fsp--;


            match(input, Token.UP, null); 
            parent.addChoice(expression24, block25);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "elseIfStat"


    // $ANTLR start "elseStat"
    // grammar/PlazmaScriptWalker.g:104:1: elseStat[IfNode parent] : ^( EXP block ) ;
    public final void elseStat(IfNode parent) throws RecognitionException {
        LNode block26 = null;


        try {
            // grammar/PlazmaScriptWalker.g:105:3: ( ^( EXP block ) )
            // grammar/PlazmaScriptWalker.g:105:6: ^( EXP block )
            {
            match(input,EXP,FOLLOW_EXP_in_elseStat589); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_block_in_elseStat591);
            block26=block();

            state._fsp--;


            match(input, Token.UP, null); 
            parent.addChoice(new BooleanNode(true), block26);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "elseStat"


    // $ANTLR start "forStatement"
    // grammar/PlazmaScriptWalker.g:112:1: forStatement returns [LNode node] : ^( For Identifier a= expression block ) ;
    public final LNode forStatement() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier27=null;
        LNode a = null;

        LNode block28 = null;


        try {
            // grammar/PlazmaScriptWalker.g:113:3: ( ^( For Identifier a= expression block ) )
            // grammar/PlazmaScriptWalker.g:113:6: ^( For Identifier a= expression block )
            {
            match(input,For,FOLLOW_For_in_forStatement622); 

            match(input, Token.DOWN, null); 
            Identifier27=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_forStatement624); 
            pushFollow(FOLLOW_expression_in_forStatement628);
            a=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_forStatement630);
            block28=block();

            state._fsp--;


            match(input, Token.UP, null); 
            node = new ForStatementNode2((Identifier27!=null?Identifier27.getText():null), a, block28, currentScope);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return node;
    }
    // $ANTLR end "forStatement"


    // $ANTLR start "whileStatement"
    // grammar/PlazmaScriptWalker.g:117:1: whileStatement returns [LNode node] : ^( While expression block ) ;
    public final LNode whileStatement() throws RecognitionException {
        LNode node = null;

        LNode expression29 = null;

        LNode block30 = null;


        try {
            // grammar/PlazmaScriptWalker.g:118:3: ( ^( While expression block ) )
            // grammar/PlazmaScriptWalker.g:118:6: ^( While expression block )
            {
            match(input,While,FOLLOW_While_in_whileStatement655); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_whileStatement657);
            expression29=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_whileStatement659);
            block30=block();

            state._fsp--;


            match(input, Token.UP, null); 
            node = new WhileStatementNode(expression29, block30);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return node;
    }
    // $ANTLR end "whileStatement"


    // $ANTLR start "idList"
    // grammar/PlazmaScriptWalker.g:121:1: idList returns [java.util.List<String> i] : ^( ID_LIST ( Identifier )+ ) ;
    public final java.util.List<String> idList() throws RecognitionException {
        java.util.List<String> i = null;

        CommonTree Identifier31=null;

        i = new java.util.ArrayList<String>();
        try {
            // grammar/PlazmaScriptWalker.g:123:3: ( ^( ID_LIST ( Identifier )+ ) )
            // grammar/PlazmaScriptWalker.g:123:6: ^( ID_LIST ( Identifier )+ )
            {
            match(input,ID_LIST,FOLLOW_ID_LIST_in_idList686); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:123:16: ( Identifier )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==Identifier) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:123:17: Identifier
            	    {
            	    Identifier31=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_idList689); 
            	    i.add((Identifier31!=null?Identifier31.getText():null));

            	    }
            	    break;

            	default :
            	    if ( cnt14 >= 1 ) break loop14;
                        EarlyExitException eee =
                            new EarlyExitException(14, input);
                        throw eee;
                }
                cnt14++;
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return i;
    }
    // $ANTLR end "idList"


    // $ANTLR start "exprList"
    // grammar/PlazmaScriptWalker.g:126:1: exprList returns [java.util.List<LNode> e] : ^( EXP_LIST ( expression )+ ) ;
    public final java.util.List<LNode> exprList() throws RecognitionException {
        java.util.List<LNode> e = null;

        LNode expression32 = null;


        e = new java.util.ArrayList<LNode>();
        try {
            // grammar/PlazmaScriptWalker.g:128:3: ( ^( EXP_LIST ( expression )+ ) )
            // grammar/PlazmaScriptWalker.g:128:6: ^( EXP_LIST ( expression )+ )
            {
            match(input,EXP_LIST,FOLLOW_EXP_LIST_in_exprList719); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:128:17: ( expression )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>=TERNARY && LA15_0<=NEGATE)||LA15_0==LOOKUP||(LA15_0>=In && LA15_0<=Infinity)||(LA15_0>=XorWord && LA15_0<=Pow)||(LA15_0>=GT && LA15_0<=Modulus)) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:128:18: expression
            	    {
            	    pushFollow(FOLLOW_expression_in_exprList722);
            	    expression32=expression();

            	    state._fsp--;

            	    e.add(expression32);

            	    }
            	    break;

            	default :
            	    if ( cnt15 >= 1 ) break loop15;
                        EarlyExitException eee =
                            new EarlyExitException(15, input);
                        throw eee;
                }
                cnt15++;
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return e;
    }
    // $ANTLR end "exprList"


    // $ANTLR start "exprPair"
    // grammar/PlazmaScriptWalker.g:130:1: exprPair returns [PairNode node] : ^( EXP_PAIR k= expression v= expression ) ;
    public final PairNode exprPair() throws RecognitionException {
        PairNode node = null;

        LNode k = null;

        LNode v = null;


        try {
            // grammar/PlazmaScriptWalker.g:131:3: ( ^( EXP_PAIR k= expression v= expression ) )
            // grammar/PlazmaScriptWalker.g:131:6: ^( EXP_PAIR k= expression v= expression )
            {
            match(input,EXP_PAIR,FOLLOW_EXP_PAIR_in_exprPair745); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_exprPair749);
            k=expression();

            state._fsp--;

            pushFollow(FOLLOW_expression_in_exprPair753);
            v=expression();

            state._fsp--;


            match(input, Token.UP, null); 
            node = new PairNode(k, v);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return node;
    }
    // $ANTLR end "exprPair"


    // $ANTLR start "exprMap"
    // grammar/PlazmaScriptWalker.g:134:1: exprMap returns [java.util.List<PairNode> e] : ^( EXP_MAP ( exprPair )+ ) ;
    public final java.util.List<PairNode> exprMap() throws RecognitionException {
        java.util.List<PairNode> e = null;

        PairNode exprPair33 = null;


        e = new java.util.ArrayList<PairNode>();
        try {
            // grammar/PlazmaScriptWalker.g:136:3: ( ^( EXP_MAP ( exprPair )+ ) )
            // grammar/PlazmaScriptWalker.g:136:6: ^( EXP_MAP ( exprPair )+ )
            {
            match(input,EXP_MAP,FOLLOW_EXP_MAP_in_exprMap781); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:136:16: ( exprPair )+
            int cnt16=0;
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==EXP_PAIR) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:136:17: exprPair
            	    {
            	    pushFollow(FOLLOW_exprPair_in_exprMap784);
            	    exprPair33=exprPair();

            	    state._fsp--;

            	    e.add(exprPair33);

            	    }
            	    break;

            	default :
            	    if ( cnt16 >= 1 ) break loop16;
                        EarlyExitException eee =
                            new EarlyExitException(16, input);
                        throw eee;
                }
                cnt16++;
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return e;
    }
    // $ANTLR end "exprMap"


    // $ANTLR start "expression"
    // grammar/PlazmaScriptWalker.g:140:1: expression returns [LNode node] : ( ^( TERNARY a= expression b= expression c= expression ) | ^( In a= expression b= expression ) | ^( RangeE a= expression b= expression ) | ^( Range a= expression b= expression ) | ^( 'xor' a= expression b= expression ) | ^( '||' a= expression b= expression ) | ^( '|' a= expression b= expression ) | ^( 'or' a= expression b= expression ) | ^( '&&' a= expression b= expression ) | ^( '&' a= expression b= expression ) | ^( 'and' a= expression b= expression ) | ^( '==' a= expression b= expression ) | ^( '!=' a= expression b= expression ) | ^( '>=' a= expression b= expression ) | ^( '<=' a= expression b= expression ) | ^( '>' a= expression b= expression ) | ^( '<' a= expression b= expression ) | ^( '+' a= expression b= expression ) | ^( '-' a= expression b= expression ) | ^( '*' a= expression b= expression ) | ^( '/' a= expression b= expression ) | ^( '%' a= expression b= expression ) | ^( '^' a= expression b= expression ) | ^( UNARY_PLUS a= expression ) | ^( UNARY_MIN a= expression ) | ^( NEGATE a= expression ) | Integer | Number | Bool | Null | NaN | Infinity | lookup );
    public final LNode expression() throws RecognitionException {
        LNode node = null;

        CommonTree Integer34=null;
        CommonTree Number35=null;
        CommonTree Bool36=null;
        LNode a = null;

        LNode b = null;

        LNode c = null;

        LNode lookup37 = null;


        try {
            // grammar/PlazmaScriptWalker.g:141:3: ( ^( TERNARY a= expression b= expression c= expression ) | ^( In a= expression b= expression ) | ^( RangeE a= expression b= expression ) | ^( Range a= expression b= expression ) | ^( 'xor' a= expression b= expression ) | ^( '||' a= expression b= expression ) | ^( '|' a= expression b= expression ) | ^( 'or' a= expression b= expression ) | ^( '&&' a= expression b= expression ) | ^( '&' a= expression b= expression ) | ^( 'and' a= expression b= expression ) | ^( '==' a= expression b= expression ) | ^( '!=' a= expression b= expression ) | ^( '>=' a= expression b= expression ) | ^( '<=' a= expression b= expression ) | ^( '>' a= expression b= expression ) | ^( '<' a= expression b= expression ) | ^( '+' a= expression b= expression ) | ^( '-' a= expression b= expression ) | ^( '*' a= expression b= expression ) | ^( '/' a= expression b= expression ) | ^( '%' a= expression b= expression ) | ^( '^' a= expression b= expression ) | ^( UNARY_PLUS a= expression ) | ^( UNARY_MIN a= expression ) | ^( NEGATE a= expression ) | Integer | Number | Bool | Null | NaN | Infinity | lookup )
            int alt17=33;
            switch ( input.LA(1) ) {
            case TERNARY:
                {
                alt17=1;
                }
                break;
            case In:
                {
                alt17=2;
                }
                break;
            case RangeE:
                {
                alt17=3;
                }
                break;
            case Range:
                {
                alt17=4;
                }
                break;
            case XorWord:
                {
                alt17=5;
                }
                break;
            case Or:
                {
                alt17=6;
                }
                break;
            case BitOr:
                {
                alt17=7;
                }
                break;
            case OrWord:
                {
                alt17=8;
                }
                break;
            case And:
                {
                alt17=9;
                }
                break;
            case BitAnd:
                {
                alt17=10;
                }
                break;
            case AndWord:
                {
                alt17=11;
                }
                break;
            case Equals:
                {
                alt17=12;
                }
                break;
            case NEquals:
                {
                alt17=13;
                }
                break;
            case GTEquals:
                {
                alt17=14;
                }
                break;
            case LTEquals:
                {
                alt17=15;
                }
                break;
            case GT:
                {
                alt17=16;
                }
                break;
            case LT:
                {
                alt17=17;
                }
                break;
            case Add:
                {
                alt17=18;
                }
                break;
            case Subtract:
                {
                alt17=19;
                }
                break;
            case Multiply:
                {
                alt17=20;
                }
                break;
            case Divide:
                {
                alt17=21;
                }
                break;
            case Modulus:
                {
                alt17=22;
                }
                break;
            case Pow:
                {
                alt17=23;
                }
                break;
            case UNARY_PLUS:
                {
                alt17=24;
                }
                break;
            case UNARY_MIN:
                {
                alt17=25;
                }
                break;
            case NEGATE:
                {
                alt17=26;
                }
                break;
            case Integer:
                {
                alt17=27;
                }
                break;
            case Number:
                {
                alt17=28;
                }
                break;
            case Bool:
                {
                alt17=29;
                }
                break;
            case Null:
                {
                alt17=30;
                }
                break;
            case NaN:
                {
                alt17=31;
                }
                break;
            case Infinity:
                {
                alt17=32;
                }
                break;
            case LOOKUP:
                {
                alt17=33;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:141:6: ^( TERNARY a= expression b= expression c= expression )
                    {
                    match(input,TERNARY,FOLLOW_TERNARY_in_expression809); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression813);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression817);
                    b=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression821);
                    c=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new TernaryNode(a, b, c);

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:142:6: ^( In a= expression b= expression )
                    {
                    match(input,In,FOLLOW_In_in_expression832); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression836);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression840);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new InNode(a, b);

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:144:6: ^( RangeE a= expression b= expression )
                    {
                    match(input,RangeE,FOLLOW_RangeE_in_expression872); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression876);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression880);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new RangeENode(a, b);

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:145:6: ^( Range a= expression b= expression )
                    {
                    match(input,Range,FOLLOW_Range_in_expression906); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression910);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression914);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new RangeNode(a, b);

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:147:6: ^( 'xor' a= expression b= expression )
                    {
                    match(input,XorWord,FOLLOW_XorWord_in_expression944); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression948);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression952);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new XorNode(a, b);

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScriptWalker.g:148:6: ^( '||' a= expression b= expression )
                    {
                    match(input,Or,FOLLOW_Or_in_expression978); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression982);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression986);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new OrNode(a, b);

                    }
                    break;
                case 7 :
                    // grammar/PlazmaScriptWalker.g:149:6: ^( '|' a= expression b= expression )
                    {
                    match(input,BitOr,FOLLOW_BitOr_in_expression1013); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1017);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1021);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new BitOrNode(a, b);

                    }
                    break;
                case 8 :
                    // grammar/PlazmaScriptWalker.g:150:6: ^( 'or' a= expression b= expression )
                    {
                    match(input,OrWord,FOLLOW_OrWord_in_expression1051); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1055);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1059);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new OrNode(a, b);

                    }
                    break;
                case 9 :
                    // grammar/PlazmaScriptWalker.g:151:6: ^( '&&' a= expression b= expression )
                    {
                    match(input,And,FOLLOW_And_in_expression1088); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1092);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1096);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AndNode(a, b);

                    }
                    break;
                case 10 :
                    // grammar/PlazmaScriptWalker.g:152:6: ^( '&' a= expression b= expression )
                    {
                    match(input,BitAnd,FOLLOW_BitAnd_in_expression1123); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1127);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1131);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new BitAndNode(a, b);

                    }
                    break;
                case 11 :
                    // grammar/PlazmaScriptWalker.g:153:6: ^( 'and' a= expression b= expression )
                    {
                    match(input,AndWord,FOLLOW_AndWord_in_expression1161); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1165);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1169);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AndNode(a, b);

                    }
                    break;
                case 12 :
                    // grammar/PlazmaScriptWalker.g:154:6: ^( '==' a= expression b= expression )
                    {
                    match(input,Equals,FOLLOW_Equals_in_expression1197); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1201);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1205);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new EQNode(a, b);

                    }
                    break;
                case 13 :
                    // grammar/PlazmaScriptWalker.g:155:6: ^( '!=' a= expression b= expression )
                    {
                    match(input,NEquals,FOLLOW_NEquals_in_expression1232); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1236);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1240);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new NENode(a, b);

                    }
                    break;
                case 14 :
                    // grammar/PlazmaScriptWalker.g:156:6: ^( '>=' a= expression b= expression )
                    {
                    match(input,GTEquals,FOLLOW_GTEquals_in_expression1267); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1271);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1275);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new GTENode(a, b);

                    }
                    break;
                case 15 :
                    // grammar/PlazmaScriptWalker.g:157:6: ^( '<=' a= expression b= expression )
                    {
                    match(input,LTEquals,FOLLOW_LTEquals_in_expression1302); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1306);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1310);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new LTENode(a, b);

                    }
                    break;
                case 16 :
                    // grammar/PlazmaScriptWalker.g:158:6: ^( '>' a= expression b= expression )
                    {
                    match(input,GT,FOLLOW_GT_in_expression1337); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1341);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1345);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new GTNode(a, b);

                    }
                    break;
                case 17 :
                    // grammar/PlazmaScriptWalker.g:159:6: ^( '<' a= expression b= expression )
                    {
                    match(input,LT,FOLLOW_LT_in_expression1373); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1377);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1381);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new LTNode(a, b);

                    }
                    break;
                case 18 :
                    // grammar/PlazmaScriptWalker.g:160:6: ^( '+' a= expression b= expression )
                    {
                    match(input,Add,FOLLOW_Add_in_expression1409); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1413);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1417);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AddNode(a, b);

                    }
                    break;
                case 19 :
                    // grammar/PlazmaScriptWalker.g:161:6: ^( '-' a= expression b= expression )
                    {
                    match(input,Subtract,FOLLOW_Subtract_in_expression1445); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1449);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1453);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new SubNode(a, b);

                    }
                    break;
                case 20 :
                    // grammar/PlazmaScriptWalker.g:162:6: ^( '*' a= expression b= expression )
                    {
                    match(input,Multiply,FOLLOW_Multiply_in_expression1481); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1485);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1489);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new MulNode(a, b);

                    }
                    break;
                case 21 :
                    // grammar/PlazmaScriptWalker.g:163:6: ^( '/' a= expression b= expression )
                    {
                    match(input,Divide,FOLLOW_Divide_in_expression1517); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1521);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1525);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new DivNode(a, b);

                    }
                    break;
                case 22 :
                    // grammar/PlazmaScriptWalker.g:165:6: ^( '%' a= expression b= expression )
                    {
                    match(input,Modulus,FOLLOW_Modulus_in_expression1556); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1560);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1564);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new ModNode(a, b);

                    }
                    break;
                case 23 :
                    // grammar/PlazmaScriptWalker.g:166:6: ^( '^' a= expression b= expression )
                    {
                    match(input,Pow,FOLLOW_Pow_in_expression1592); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1596);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1600);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new PowNode(a, b);

                    }
                    break;
                case 24 :
                    // grammar/PlazmaScriptWalker.g:167:6: ^( UNARY_PLUS a= expression )
                    {
                    match(input,UNARY_PLUS,FOLLOW_UNARY_PLUS_in_expression1628); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1632);
                    a=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new UnaryPlusNode(a);

                    }
                    break;
                case 25 :
                    // grammar/PlazmaScriptWalker.g:168:6: ^( UNARY_MIN a= expression )
                    {
                    match(input,UNARY_MIN,FOLLOW_UNARY_MIN_in_expression1669); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1673);
                    a=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new UnaryMinusNode(a);

                    }
                    break;
                case 26 :
                    // grammar/PlazmaScriptWalker.g:169:6: ^( NEGATE a= expression )
                    {
                    match(input,NEGATE,FOLLOW_NEGATE_in_expression1708); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1712);
                    a=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new NotNode(a);

                    }
                    break;
                case 27 :
                    // grammar/PlazmaScriptWalker.g:171:6: Integer
                    {
                    Integer34=(CommonTree)match(input,Integer,FOLLOW_Integer_in_expression1751); 
                    node = new IntegerNode((Integer34!=null?Integer34.getText():null));

                    }
                    break;
                case 28 :
                    // grammar/PlazmaScriptWalker.g:172:6: Number
                    {
                    Number35=(CommonTree)match(input,Number,FOLLOW_Number_in_expression1827); 
                    node = new NumberNode((Number35!=null?Number35.getText():null));

                    }
                    break;
                case 29 :
                    // grammar/PlazmaScriptWalker.g:173:6: Bool
                    {
                    Bool36=(CommonTree)match(input,Bool,FOLLOW_Bool_in_expression1905); 
                    node = new BooleanNode((Bool36!=null?Bool36.getText():null));

                    }
                    break;
                case 30 :
                    // grammar/PlazmaScriptWalker.g:174:6: Null
                    {
                    match(input,Null,FOLLOW_Null_in_expression1986); 
                    node = new NullNode();

                    }
                    break;
                case 31 :
                    // grammar/PlazmaScriptWalker.g:175:6: NaN
                    {
                    match(input,NaN,FOLLOW_NaN_in_expression2040); 
                    node = new NaNNode();

                    }
                    break;
                case 32 :
                    // grammar/PlazmaScriptWalker.g:176:6: Infinity
                    {
                    match(input,Infinity,FOLLOW_Infinity_in_expression2097); 
                    node = new InfinityNode();

                    }
                    break;
                case 33 :
                    // grammar/PlazmaScriptWalker.g:177:6: lookup
                    {
                    pushFollow(FOLLOW_lookup_in_expression2149);
                    lookup37=lookup();

                    state._fsp--;

                    node = lookup37;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return node;
    }
    // $ANTLR end "expression"


    // $ANTLR start "list"
    // grammar/PlazmaScriptWalker.g:180:1: list returns [LNode node] : ^( LIST ( exprList )? ) ;
    public final LNode list() throws RecognitionException {
        LNode node = null;

        java.util.List<LNode> exprList38 = null;


        try {
            // grammar/PlazmaScriptWalker.g:181:3: ( ^( LIST ( exprList )? ) )
            // grammar/PlazmaScriptWalker.g:181:6: ^( LIST ( exprList )? )
            {
            match(input,LIST,FOLLOW_LIST_in_list2213); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // grammar/PlazmaScriptWalker.g:181:13: ( exprList )?
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==EXP_LIST) ) {
                    alt18=1;
                }
                switch (alt18) {
                    case 1 :
                        // grammar/PlazmaScriptWalker.g:181:13: exprList
                        {
                        pushFollow(FOLLOW_exprList_in_list2215);
                        exprList38=exprList();

                        state._fsp--;


                        }
                        break;

                }


                match(input, Token.UP, null); 
            }
            node = new ListNode(exprList38);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return node;
    }
    // $ANTLR end "list"


    // $ANTLR start "map"
    // grammar/PlazmaScriptWalker.g:184:1: map returns [LNode node] : ^( MAP ( exprMap )? ) ;
    public final LNode map() throws RecognitionException {
        LNode node = null;

        java.util.List<PairNode> exprMap39 = null;


        try {
            // grammar/PlazmaScriptWalker.g:185:3: ( ^( MAP ( exprMap )? ) )
            // grammar/PlazmaScriptWalker.g:185:6: ^( MAP ( exprMap )? )
            {
            match(input,MAP,FOLLOW_MAP_in_map2238); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // grammar/PlazmaScriptWalker.g:185:12: ( exprMap )?
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==EXP_MAP) ) {
                    alt19=1;
                }
                switch (alt19) {
                    case 1 :
                        // grammar/PlazmaScriptWalker.g:185:12: exprMap
                        {
                        pushFollow(FOLLOW_exprMap_in_map2240);
                        exprMap39=exprMap();

                        state._fsp--;


                        }
                        break;

                }


                match(input, Token.UP, null); 
            }
            node = new MapNode(exprMap39);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return node;
    }
    // $ANTLR end "map"


    // $ANTLR start "lookup"
    // grammar/PlazmaScriptWalker.g:188:1: lookup returns [LNode node] : ( ^( LOOKUP functionCall (i= indexes )? ) | ^( LOOKUP list (i= indexes )? ) | ^( LOOKUP map (i= indexes )? ) | ^( LOOKUP expression (i= indexes )? ) | ^( LOOKUP Identifier (i= indexes )? ) | ^( LOOKUP String (i= indexes )? ) );
    public final LNode lookup() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier44=null;
        CommonTree String45=null;
        java.util.List<LNode> i = null;

        LNode functionCall40 = null;

        LNode list41 = null;

        LNode map42 = null;

        LNode expression43 = null;


        try {
            // grammar/PlazmaScriptWalker.g:189:3: ( ^( LOOKUP functionCall (i= indexes )? ) | ^( LOOKUP list (i= indexes )? ) | ^( LOOKUP map (i= indexes )? ) | ^( LOOKUP expression (i= indexes )? ) | ^( LOOKUP Identifier (i= indexes )? ) | ^( LOOKUP String (i= indexes )? ) )
            int alt26=6;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==LOOKUP) ) {
                int LA26_1 = input.LA(2);

                if ( (LA26_1==DOWN) ) {
                    switch ( input.LA(3) ) {
                    case Identifier:
                        {
                        alt26=5;
                        }
                        break;
                    case String:
                        {
                        alt26=6;
                        }
                        break;
                    case FUNC_CALL:
                        {
                        alt26=1;
                        }
                        break;
                    case LIST:
                        {
                        alt26=2;
                        }
                        break;
                    case TERNARY:
                    case UNARY_PLUS:
                    case UNARY_MIN:
                    case NEGATE:
                    case LOOKUP:
                    case In:
                    case RangeE:
                    case Range:
                    case Integer:
                    case Number:
                    case Bool:
                    case Null:
                    case NaN:
                    case Infinity:
                    case XorWord:
                    case Or:
                    case BitOr:
                    case OrWord:
                    case And:
                    case BitAnd:
                    case AndWord:
                    case Equals:
                    case NEquals:
                    case GTEquals:
                    case LTEquals:
                    case Pow:
                    case GT:
                    case LT:
                    case Add:
                    case Subtract:
                    case Multiply:
                    case Divide:
                    case Modulus:
                        {
                        alt26=4;
                        }
                        break;
                    case MAP:
                        {
                        alt26=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 26, 2, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 26, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:189:6: ^( LOOKUP functionCall (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2263); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_functionCall_in_lookup2265);
                    functionCall40=functionCall();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:189:29: (i= indexes )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==TAILS) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:189:29: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2269);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(functionCall40, i) : functionCall40;

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:190:6: ^( LOOKUP list (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2281); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_list_in_lookup2283);
                    list41=list();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:190:21: (i= indexes )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==TAILS) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:190:21: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2287);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(list41, i) : list41;

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:191:6: ^( LOOKUP map (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2307); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_map_in_lookup2309);
                    map42=map();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:191:20: (i= indexes )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==TAILS) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:191:20: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2313);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(map42, i) : map42;

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:192:6: ^( LOOKUP expression (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2336); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_lookup2338);
                    expression43=expression();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:192:27: (i= indexes )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==TAILS) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:192:27: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2342);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(expression43, i) : expression43;

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:193:6: ^( LOOKUP Identifier (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2356); 

                    match(input, Token.DOWN, null); 
                    Identifier44=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_lookup2358); 
                    // grammar/PlazmaScriptWalker.g:193:27: (i= indexes )?
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( (LA24_0==TAILS) ) {
                        alt24=1;
                    }
                    switch (alt24) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:193:27: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2362);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(new IdentifierNode((Identifier44!=null?Identifier44.getText():null), currentScope, globalScope), i) : new IdentifierNode((Identifier44!=null?Identifier44.getText():null), currentScope, globalScope);

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScriptWalker.g:194:6: ^( LOOKUP String (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2376); 

                    match(input, Token.DOWN, null); 
                    String45=(CommonTree)match(input,String,FOLLOW_String_in_lookup2378); 
                    // grammar/PlazmaScriptWalker.g:194:23: (i= indexes )?
                    int alt25=2;
                    int LA25_0 = input.LA(1);

                    if ( (LA25_0==TAILS) ) {
                        alt25=1;
                    }
                    switch (alt25) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:194:23: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2382);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(new StringNode((String45!=null?String45.getText():null)), i) : new StringNode((String45!=null?String45.getText():null));

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return node;
    }
    // $ANTLR end "lookup"


    // $ANTLR start "indexes"
    // grammar/PlazmaScriptWalker.g:197:1: indexes returns [java.util.List<LNode> e] : ^( TAILS ( tail )+ ) ;
    public final java.util.List<LNode> indexes() throws RecognitionException {
        java.util.List<LNode> e = null;

        LNode tail46 = null;


        e = new java.util.ArrayList<LNode>();
        try {
            // grammar/PlazmaScriptWalker.g:200:3: ( ^( TAILS ( tail )+ ) )
            // grammar/PlazmaScriptWalker.g:200:8: ^( TAILS ( tail )+ )
            {
            match(input,TAILS,FOLLOW_TAILS_in_indexes2421); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:200:18: ( tail )+
            int cnt27=0;
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( ((LA27_0>=INDEX && LA27_0<=CALL)) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:200:19: tail
            	    {
            	    pushFollow(FOLLOW_tail_in_indexes2426);
            	    tail46=tail();

            	    state._fsp--;

            	    e.add(tail46);

            	    }
            	    break;

            	default :
            	    if ( cnt27 >= 1 ) break loop27;
                        EarlyExitException eee =
                            new EarlyExitException(27, input);
                        throw eee;
                }
                cnt27++;
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return e;
    }
    // $ANTLR end "indexes"


    // $ANTLR start "tail"
    // grammar/PlazmaScriptWalker.g:204:1: tail returns [LNode node] : ( ^( INDEX expression ) | ^( ATTRIBUTE Identifier ) | ^( CALL Identifier ( exprList )? ) );
    public final LNode tail() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier48=null;
        CommonTree Identifier49=null;
        LNode expression47 = null;

        java.util.List<LNode> exprList50 = null;


        try {
            // grammar/PlazmaScriptWalker.g:205:2: ( ^( INDEX expression ) | ^( ATTRIBUTE Identifier ) | ^( CALL Identifier ( exprList )? ) )
            int alt29=3;
            switch ( input.LA(1) ) {
            case INDEX:
                {
                alt29=1;
                }
                break;
            case ATTRIBUTE:
                {
                alt29=2;
                }
                break;
            case CALL:
                {
                alt29=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }

            switch (alt29) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:205:4: ^( INDEX expression )
                    {
                    match(input,INDEX,FOLLOW_INDEX_in_tail2451); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_tail2453);
                    expression47=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = expression47;

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:206:4: ^( ATTRIBUTE Identifier )
                    {
                    match(input,ATTRIBUTE,FOLLOW_ATTRIBUTE_in_tail2472); 

                    match(input, Token.DOWN, null); 
                    Identifier48=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_tail2474); 

                    match(input, Token.UP, null); 
                    node = new StringNode((Identifier48!=null?Identifier48.getText():null));

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:207:4: ^( CALL Identifier ( exprList )? )
                    {
                    match(input,CALL,FOLLOW_CALL_in_tail2489); 

                    match(input, Token.DOWN, null); 
                    Identifier49=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_tail2491); 
                    // grammar/PlazmaScriptWalker.g:207:22: ( exprList )?
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==EXP_LIST) ) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:207:22: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_tail2493);
                            exprList50=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new MethodCallNode((Identifier49!=null?Identifier49.getText():null), exprList50, functions, globalScope);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return node;
    }
    // $ANTLR end "tail"


    // $ANTLR start "variableDef"
    // grammar/PlazmaScriptWalker.g:210:1: variableDef returns [LNode node] : Var ;
    public final LNode variableDef() throws RecognitionException {
        LNode node = null;

        CommonTree Var51=null;

        try {
            // grammar/PlazmaScriptWalker.g:211:3: ( Var )
            // grammar/PlazmaScriptWalker.g:211:5: Var
            {
            Var51=(CommonTree)match(input,Var,FOLLOW_Var_in_variableDef2517); 
            node = new VariableDefNode((Var51!=null?Var51.getText():null), null);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return node;
    }
    // $ANTLR end "variableDef"

    // Delegated rules


    protected DFA11 dfa11 = new DFA11(this);
    static final String DFA11_eotS =
        "\12\uffff";
    static final String DFA11_eofS =
        "\12\uffff";
    static final String DFA11_minS =
        "\1\10\1\2\1\43\7\uffff";
    static final String DFA11_maxS =
        "\1\10\1\2\1\51\7\uffff";
    static final String DFA11_acceptS =
        "\3\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7";
    static final String DFA11_specialS =
        "\12\uffff}>";
    static final String[] DFA11_transitionS = {
            "\1\1",
            "\1\2",
            "\1\3\1\4\1\5\1\6\1\7\1\10\1\11",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
    static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
    static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
    static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
    static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
    static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
    static final short[][] DFA11_transition;

    static {
        int numStates = DFA11_transitionS.length;
        DFA11_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = DFA11_eot;
            this.eof = DFA11_eof;
            this.min = DFA11_min;
            this.max = DFA11_max;
            this.accept = DFA11_accept;
            this.special = DFA11_special;
            this.transition = DFA11_transition;
        }
        public String getDescription() {
            return "80:1: functionCall returns [LNode node] : ( ^( FUNC_CALL Identifier ( exprList )? ) | ^( FUNC_CALL Println ( expression )? ) | ^( FUNC_CALL Print expression ) | ^( FUNC_CALL Assert expression ) | ^( FUNC_CALL Date ( exprList )? ) | ^( FUNC_CALL List ( exprList )? ) | ^( FUNC_CALL Set ( exprList )? ) );";
        }
    }
 

    public static final BitSet FOLLOW_block_in_walk50 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BLOCK_in_block81 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STATEMENTS_in_block84 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_block87 = new BitSet(new long[]{0x0000C00620004188L});
    public static final BitSet FOLLOW_RETURN_in_block95 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_block98 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_assignment_in_statement122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_statement135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_statement146 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStatement_in_statement165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forStatement_in_statement177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whileStatement_in_statement189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Break_in_statement198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Continue_in_statement251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGNMENT_in_assignment317 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_variableDef_in_assignment319 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_Identifier_in_assignment322 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_indexes_in_assignment324 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_assignment327 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall349 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_functionCall351 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall353 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall365 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Println_in_functionCall367 = new BitSet(new long[]{0xFDFF000022078008L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_functionCall369 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall382 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Print_in_functionCall384 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_functionCall386 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall401 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Assert_in_functionCall403 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_functionCall405 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall419 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Date_in_functionCall421 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall423 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall441 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_List_in_functionCall443 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall445 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall463 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Set_in_functionCall465 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall467 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IF_in_ifStatement511 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ifStat_in_ifStatement513 = new BitSet(new long[]{0x0000000000000208L});
    public static final BitSet FOLLOW_elseIfStat_in_ifStatement517 = new BitSet(new long[]{0x0000000000000208L});
    public static final BitSet FOLLOW_elseStat_in_ifStatement523 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_ifStat543 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_ifStat545 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_ifStat547 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_elseIfStat566 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_elseIfStat568 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_elseIfStat570 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_elseStat589 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_block_in_elseStat591 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_For_in_forStatement622 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_forStatement624 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_forStatement628 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_forStatement630 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_While_in_whileStatement655 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_whileStatement657 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_whileStatement659 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_LIST_in_idList686 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_idList689 = new BitSet(new long[]{0x0000000800000008L});
    public static final BitSet FOLLOW_EXP_LIST_in_exprList719 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_exprList722 = new BitSet(new long[]{0xFDFF000022078008L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_EXP_PAIR_in_exprPair745 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_exprPair749 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_exprPair753 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_MAP_in_exprMap781 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprPair_in_exprMap784 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_TERNARY_in_expression809 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression813 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_expression817 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_expression821 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_In_in_expression832 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression836 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_expression840 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RangeE_in_expression872 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression876 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_expression880 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Range_in_expression906 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression910 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_expression914 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_XorWord_in_expression944 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression948 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_expression952 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Or_in_expression978 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression982 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_expression986 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BitOr_in_expression1013 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1017 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_expression1021 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_OrWord_in_expression1051 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1055 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_expression1059 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_And_in_expression1088 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1092 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_expression1096 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BitAnd_in_expression1123 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1127 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_expression1131 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_AndWord_in_expression1161 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1165 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_expression1169 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Equals_in_expression1197 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1201 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_expression1205 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEquals_in_expression1232 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1236 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_expression1240 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GTEquals_in_expression1267 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1271 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_expression1275 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LTEquals_in_expression1302 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1306 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_expression1310 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GT_in_expression1337 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1341 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_expression1345 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LT_in_expression1373 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1377 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_expression1381 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Add_in_expression1409 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1413 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_expression1417 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Subtract_in_expression1445 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1449 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_expression1453 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Multiply_in_expression1481 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1485 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_expression1489 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Divide_in_expression1517 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1521 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_expression1525 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Modulus_in_expression1556 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1560 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_expression1564 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Pow_in_expression1592 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1596 = new BitSet(new long[]{0xFDFF000022078000L,0x0000000000007F3FL});
    public static final BitSet FOLLOW_expression_in_expression1600 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_UNARY_PLUS_in_expression1628 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1632 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_UNARY_MIN_in_expression1669 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1673 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEGATE_in_expression1708 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1712 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Integer_in_expression1751 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Number_in_expression1827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Bool_in_expression1905 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Null_in_expression1986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NaN_in_expression2040 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Infinity_in_expression2097 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_expression2149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LIST_in_list2213 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprList_in_list2215 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MAP_in_map2238 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprMap_in_map2240 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2263 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_functionCall_in_lookup2265 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2269 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2281 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_list_in_lookup2283 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2287 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2307 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_map_in_lookup2309 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2313 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2336 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_lookup2338 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2342 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2356 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_lookup2358 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2362 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2376 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_String_in_lookup2378 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2382 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TAILS_in_indexes2421 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_tail_in_indexes2426 = new BitSet(new long[]{0x0000000000E00008L});
    public static final BitSet FOLLOW_INDEX_in_tail2451 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_tail2453 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ATTRIBUTE_in_tail2472 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_tail2474 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CALL_in_tail2489 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_tail2491 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_tail2493 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Var_in_variableDef2517 = new BitSet(new long[]{0x0000000000000002L});

}