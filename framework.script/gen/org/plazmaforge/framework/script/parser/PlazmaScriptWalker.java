// $ANTLR 3.2 Sep 23, 2009 12:02:23 grammar/PlazmaScriptWalker.g 2016-08-29 16:18:09

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BLOCK", "RETURN", "STATEMENTS", "ASSIGNMENT", "FUNC_CALL", "EXP", "EXP_PAIR", "EXP_MAP", "EXP_LIST", "ID_LIST", "IF", "TERNARY", "UNARY_PLUS", "UNARY_MIN", "NEGATE", "FUNCTION", "INDEXES", "INDEX", "ATTRIBUTE", "CALL", "TAIL", "TAILS", "MAP", "LIST", "SET", "LOOKUP", "BREAK", "CONTINUE", "Return", "Break", "Continue", "Identifier", "Println", "Print", "Assert", "Date", "DateTime", "Time", "Duration", "Period", "List", "Set", "If", "Else", "Var", "Def", "For", "While", "In", "RangeE", "Range", "Integer", "Number", "Bool", "Null", "NaN", "Infinity", "String", "XorWord", "Or", "BitOr", "OrWord", "And", "BitAnd", "AndWord", "Equals", "NEquals", "GTEquals", "LTEquals", "Pow", "Not", "NotWord", "GT", "LT", "Add", "Subtract", "Multiply", "Divide", "Modulus", "OBrace", "CBrace", "OBracket", "CBracket", "OParen", "CParen", "SColon", "Assign", "Comma", "QMark", "Colon", "DecimalNumeral", "DecimalFloatingPoint", "Digits", "ExponentPart", "ContextIdentifier", "Digit", "Comment", "Space", "ExponentIndicator", "SignedInteger", "Sign", "NonZeroDigit", "'.'"
    };
    public static final int FUNCTION=19;
    public static final int LT=77;
    public static final int TERNARY=15;
    public static final int EXP_LIST=12;
    public static final int DateTime=40;
    public static final int Date=39;
    public static final int EOF=-1;
    public static final int QMark=92;
    public static final int NotWord=75;
    public static final int BREAK=30;
    public static final int Identifier=35;
    public static final int UNARY_PLUS=16;
    public static final int FUNC_CALL=8;
    public static final int CParen=88;
    public static final int Comment=100;
    public static final int EXP=9;
    public static final int Digits=96;
    public static final int CBrace=84;
    public static final int RETURN=5;
    public static final int ExponentPart=97;
    public static final int ExponentIndicator=102;
    public static final int Sign=104;
    public static final int OrWord=65;
    public static final int Null=58;
    public static final int DecimalNumeral=94;
    public static final int CBracket=86;
    public static final int Period=43;
    public static final int ContextIdentifier=98;
    public static final int Println=36;
    public static final int Bool=57;
    public static final int Modulus=82;
    public static final int Time=41;
    public static final int AndWord=68;
    public static final int Colon=93;
    public static final int LIST=27;
    public static final int Def=49;
    public static final int RangeE=53;
    public static final int LOOKUP=29;
    public static final int Range=54;
    public static final int Break=33;
    public static final int SignedInteger=103;
    public static final int BitOr=64;
    public static final int STATEMENTS=6;
    public static final int GT=76;
    public static final int CALL=23;
    public static final int DecimalFloatingPoint=95;
    public static final int Else=47;
    public static final int Equals=69;
    public static final int Var=48;
    public static final int XorWord=62;
    public static final int OParen=87;
    public static final int Assert=38;
    public static final int ATTRIBUTE=22;
    public static final int While=51;
    public static final int ID_LIST=13;
    public static final int Add=78;
    public static final int Set=45;
    public static final int TAIL=24;
    public static final int IF=14;
    public static final int Space=101;
    public static final int INDEX=21;
    public static final int Assign=90;
    public static final int EXP_MAP=11;
    public static final int NaN=59;
    public static final int Number=56;
    public static final int CONTINUE=31;
    public static final int Print=37;
    public static final int GTEquals=71;
    public static final int T__106=106;
    public static final int String=61;
    public static final int Or=63;
    public static final int Return=32;
    public static final int If=46;
    public static final int And=66;
    public static final int In=52;
    public static final int NEquals=70;
    public static final int Continue=34;
    public static final int Subtract=79;
    public static final int EXP_PAIR=10;
    public static final int BitAnd=67;
    public static final int Multiply=80;
    public static final int OBrace=83;
    public static final int INDEXES=20;
    public static final int NEGATE=18;
    public static final int SET=28;
    public static final int Duration=42;
    public static final int Digit=99;
    public static final int For=50;
    public static final int Divide=81;
    public static final int List=44;
    public static final int TAILS=25;
    public static final int SColon=89;
    public static final int OBracket=85;
    public static final int NonZeroDigit=105;
    public static final int BLOCK=4;
    public static final int MAP=26;
    public static final int Not=74;
    public static final int UNARY_MIN=17;
    public static final int ASSIGNMENT=7;
    public static final int Infinity=60;
    public static final int Comma=91;
    public static final int Integer=55;
    public static final int Pow=73;
    public static final int LTEquals=72;

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
    // grammar/PlazmaScriptWalker.g:80:1: functionCall returns [LNode node] : ( ^( FUNC_CALL Identifier ( exprList )? ) | ^( FUNC_CALL Println ( expression )? ) | ^( FUNC_CALL Print expression ) | ^( FUNC_CALL Assert expression ) | ^( FUNC_CALL Date ( exprList )? ) | ^( FUNC_CALL DateTime ( exprList )? ) | ^( FUNC_CALL Time ( exprList )? ) | ^( FUNC_CALL Duration ( expression )? ) | ^( FUNC_CALL Period ( exprList )? ) | ^( FUNC_CALL List ( exprList )? ) | ^( FUNC_CALL Set ( exprList )? ) );
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

        LNode expression22 = null;

        java.util.List<LNode> exprList23 = null;

        java.util.List<LNode> exprList24 = null;

        java.util.List<LNode> exprList25 = null;


        try {
            // grammar/PlazmaScriptWalker.g:81:3: ( ^( FUNC_CALL Identifier ( exprList )? ) | ^( FUNC_CALL Println ( expression )? ) | ^( FUNC_CALL Print expression ) | ^( FUNC_CALL Assert expression ) | ^( FUNC_CALL Date ( exprList )? ) | ^( FUNC_CALL DateTime ( exprList )? ) | ^( FUNC_CALL Time ( exprList )? ) | ^( FUNC_CALL Duration ( expression )? ) | ^( FUNC_CALL Period ( exprList )? ) | ^( FUNC_CALL List ( exprList )? ) | ^( FUNC_CALL Set ( exprList )? ) )
            int alt15=11;
            alt15 = dfa15.predict(input);
            switch (alt15) {
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
                    // grammar/PlazmaScriptWalker.g:86:6: ^( FUNC_CALL DateTime ( exprList )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall441); 

                    match(input, Token.DOWN, null); 
                    match(input,DateTime,FOLLOW_DateTime_in_functionCall443); 
                    // grammar/PlazmaScriptWalker.g:86:27: ( exprList )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==EXP_LIST) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:86:27: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall445);
                            exprList20=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new DateTimeNode(exprList20);

                    }
                    break;
                case 7 :
                    // grammar/PlazmaScriptWalker.g:87:6: ^( FUNC_CALL Time ( exprList )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall461); 

                    match(input, Token.DOWN, null); 
                    match(input,Time,FOLLOW_Time_in_functionCall463); 
                    // grammar/PlazmaScriptWalker.g:87:23: ( exprList )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==EXP_LIST) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:87:23: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall465);
                            exprList21=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new TimeNode(exprList21);

                    }
                    break;
                case 8 :
                    // grammar/PlazmaScriptWalker.g:88:6: ^( FUNC_CALL Duration ( expression )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall483); 

                    match(input, Token.DOWN, null); 
                    match(input,Duration,FOLLOW_Duration_in_functionCall485); 
                    // grammar/PlazmaScriptWalker.g:88:27: ( expression )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( ((LA11_0>=TERNARY && LA11_0<=NEGATE)||LA11_0==LOOKUP||(LA11_0>=In && LA11_0<=Infinity)||(LA11_0>=XorWord && LA11_0<=Pow)||(LA11_0>=GT && LA11_0<=Modulus)) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:88:27: expression
                            {
                            pushFollow(FOLLOW_expression_in_functionCall487);
                            expression22=expression();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new DurationNode(expression22);

                    }
                    break;
                case 9 :
                    // grammar/PlazmaScriptWalker.g:89:6: ^( FUNC_CALL Period ( exprList )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall499); 

                    match(input, Token.DOWN, null); 
                    match(input,Period,FOLLOW_Period_in_functionCall501); 
                    // grammar/PlazmaScriptWalker.g:89:25: ( exprList )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==EXP_LIST) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:89:25: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall503);
                            exprList23=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new PeriodNode(exprList23);

                    }
                    break;
                case 10 :
                    // grammar/PlazmaScriptWalker.g:90:6: ^( FUNC_CALL List ( exprList )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall527); 

                    match(input, Token.DOWN, null); 
                    match(input,List,FOLLOW_List_in_functionCall529); 
                    // grammar/PlazmaScriptWalker.g:90:23: ( exprList )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==EXP_LIST) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:90:23: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall531);
                            exprList24=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new ListNode(exprList24);

                    }
                    break;
                case 11 :
                    // grammar/PlazmaScriptWalker.g:91:6: ^( FUNC_CALL Set ( exprList )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall549); 

                    match(input, Token.DOWN, null); 
                    match(input,Set,FOLLOW_Set_in_functionCall551); 
                    // grammar/PlazmaScriptWalker.g:91:22: ( exprList )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==EXP_LIST) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:91:22: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall553);
                            exprList25=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new SetNode(exprList25);

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
    // grammar/PlazmaScriptWalker.g:94:1: ifStatement returns [LNode node] : ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? ) ;
    public final LNode ifStatement() throws RecognitionException {
        LNode node = null;

        IfNode ifNode = new IfNode();
        try {
            // grammar/PlazmaScriptWalker.g:97:3: ( ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? ) )
            // grammar/PlazmaScriptWalker.g:97:6: ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? )
            {
            match(input,IF,FOLLOW_IF_in_ifStatement598); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_ifStat_in_ifStatement600);
            ifStat(ifNode);

            state._fsp--;

            // grammar/PlazmaScriptWalker.g:97:26: ( elseIfStat[ifNode] )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==EXP) ) {
                    int LA16_1 = input.LA(2);

                    if ( (LA16_1==DOWN) ) {
                        int LA16_3 = input.LA(3);

                        if ( ((LA16_3>=TERNARY && LA16_3<=NEGATE)||LA16_3==LOOKUP||(LA16_3>=In && LA16_3<=Infinity)||(LA16_3>=XorWord && LA16_3<=Pow)||(LA16_3>=GT && LA16_3<=Modulus)) ) {
                            alt16=1;
                        }


                    }


                }


                switch (alt16) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:97:27: elseIfStat[ifNode]
            	    {
            	    pushFollow(FOLLOW_elseIfStat_in_ifStatement604);
            	    elseIfStat(ifNode);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            // grammar/PlazmaScriptWalker.g:97:48: ( elseStat[ifNode] )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==EXP) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:97:49: elseStat[ifNode]
                    {
                    pushFollow(FOLLOW_elseStat_in_ifStatement610);
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
    // grammar/PlazmaScriptWalker.g:100:1: ifStat[IfNode parent] : ^( EXP expression block ) ;
    public final void ifStat(IfNode parent) throws RecognitionException {
        LNode expression26 = null;

        LNode block27 = null;


        try {
            // grammar/PlazmaScriptWalker.g:101:3: ( ^( EXP expression block ) )
            // grammar/PlazmaScriptWalker.g:101:6: ^( EXP expression block )
            {
            match(input,EXP,FOLLOW_EXP_in_ifStat630); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_ifStat632);
            expression26=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_ifStat634);
            block27=block();

            state._fsp--;


            match(input, Token.UP, null); 
            parent.addChoice(expression26, block27);

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
    // grammar/PlazmaScriptWalker.g:104:1: elseIfStat[IfNode parent] : ^( EXP expression block ) ;
    public final void elseIfStat(IfNode parent) throws RecognitionException {
        LNode expression28 = null;

        LNode block29 = null;


        try {
            // grammar/PlazmaScriptWalker.g:105:3: ( ^( EXP expression block ) )
            // grammar/PlazmaScriptWalker.g:105:6: ^( EXP expression block )
            {
            match(input,EXP,FOLLOW_EXP_in_elseIfStat653); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_elseIfStat655);
            expression28=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_elseIfStat657);
            block29=block();

            state._fsp--;


            match(input, Token.UP, null); 
            parent.addChoice(expression28, block29);

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
    // grammar/PlazmaScriptWalker.g:108:1: elseStat[IfNode parent] : ^( EXP block ) ;
    public final void elseStat(IfNode parent) throws RecognitionException {
        LNode block30 = null;


        try {
            // grammar/PlazmaScriptWalker.g:109:3: ( ^( EXP block ) )
            // grammar/PlazmaScriptWalker.g:109:6: ^( EXP block )
            {
            match(input,EXP,FOLLOW_EXP_in_elseStat676); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_block_in_elseStat678);
            block30=block();

            state._fsp--;


            match(input, Token.UP, null); 
            parent.addChoice(new BooleanNode(true), block30);

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
    // grammar/PlazmaScriptWalker.g:116:1: forStatement returns [LNode node] : ^( For Identifier a= expression block ) ;
    public final LNode forStatement() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier31=null;
        LNode a = null;

        LNode block32 = null;


        try {
            // grammar/PlazmaScriptWalker.g:117:3: ( ^( For Identifier a= expression block ) )
            // grammar/PlazmaScriptWalker.g:117:6: ^( For Identifier a= expression block )
            {
            match(input,For,FOLLOW_For_in_forStatement709); 

            match(input, Token.DOWN, null); 
            Identifier31=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_forStatement711); 
            pushFollow(FOLLOW_expression_in_forStatement715);
            a=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_forStatement717);
            block32=block();

            state._fsp--;


            match(input, Token.UP, null); 
            node = new ForStatementNode2((Identifier31!=null?Identifier31.getText():null), a, block32, currentScope);

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
    // grammar/PlazmaScriptWalker.g:121:1: whileStatement returns [LNode node] : ^( While expression block ) ;
    public final LNode whileStatement() throws RecognitionException {
        LNode node = null;

        LNode expression33 = null;

        LNode block34 = null;


        try {
            // grammar/PlazmaScriptWalker.g:122:3: ( ^( While expression block ) )
            // grammar/PlazmaScriptWalker.g:122:6: ^( While expression block )
            {
            match(input,While,FOLLOW_While_in_whileStatement742); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_whileStatement744);
            expression33=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_whileStatement746);
            block34=block();

            state._fsp--;


            match(input, Token.UP, null); 
            node = new WhileStatementNode(expression33, block34);

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
    // grammar/PlazmaScriptWalker.g:125:1: idList returns [java.util.List<String> i] : ^( ID_LIST ( Identifier )+ ) ;
    public final java.util.List<String> idList() throws RecognitionException {
        java.util.List<String> i = null;

        CommonTree Identifier35=null;

        i = new java.util.ArrayList<String>();
        try {
            // grammar/PlazmaScriptWalker.g:127:3: ( ^( ID_LIST ( Identifier )+ ) )
            // grammar/PlazmaScriptWalker.g:127:6: ^( ID_LIST ( Identifier )+ )
            {
            match(input,ID_LIST,FOLLOW_ID_LIST_in_idList773); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:127:16: ( Identifier )+
            int cnt18=0;
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==Identifier) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:127:17: Identifier
            	    {
            	    Identifier35=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_idList776); 
            	    i.add((Identifier35!=null?Identifier35.getText():null));

            	    }
            	    break;

            	default :
            	    if ( cnt18 >= 1 ) break loop18;
                        EarlyExitException eee =
                            new EarlyExitException(18, input);
                        throw eee;
                }
                cnt18++;
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
    // grammar/PlazmaScriptWalker.g:130:1: exprList returns [java.util.List<LNode> e] : ^( EXP_LIST ( expression )+ ) ;
    public final java.util.List<LNode> exprList() throws RecognitionException {
        java.util.List<LNode> e = null;

        LNode expression36 = null;


        e = new java.util.ArrayList<LNode>();
        try {
            // grammar/PlazmaScriptWalker.g:132:3: ( ^( EXP_LIST ( expression )+ ) )
            // grammar/PlazmaScriptWalker.g:132:6: ^( EXP_LIST ( expression )+ )
            {
            match(input,EXP_LIST,FOLLOW_EXP_LIST_in_exprList806); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:132:17: ( expression )+
            int cnt19=0;
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( ((LA19_0>=TERNARY && LA19_0<=NEGATE)||LA19_0==LOOKUP||(LA19_0>=In && LA19_0<=Infinity)||(LA19_0>=XorWord && LA19_0<=Pow)||(LA19_0>=GT && LA19_0<=Modulus)) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:132:18: expression
            	    {
            	    pushFollow(FOLLOW_expression_in_exprList809);
            	    expression36=expression();

            	    state._fsp--;

            	    e.add(expression36);

            	    }
            	    break;

            	default :
            	    if ( cnt19 >= 1 ) break loop19;
                        EarlyExitException eee =
                            new EarlyExitException(19, input);
                        throw eee;
                }
                cnt19++;
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
    // grammar/PlazmaScriptWalker.g:134:1: exprPair returns [PairNode node] : ^( EXP_PAIR k= expression v= expression ) ;
    public final PairNode exprPair() throws RecognitionException {
        PairNode node = null;

        LNode k = null;

        LNode v = null;


        try {
            // grammar/PlazmaScriptWalker.g:135:3: ( ^( EXP_PAIR k= expression v= expression ) )
            // grammar/PlazmaScriptWalker.g:135:6: ^( EXP_PAIR k= expression v= expression )
            {
            match(input,EXP_PAIR,FOLLOW_EXP_PAIR_in_exprPair832); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_exprPair836);
            k=expression();

            state._fsp--;

            pushFollow(FOLLOW_expression_in_exprPair840);
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
    // grammar/PlazmaScriptWalker.g:138:1: exprMap returns [java.util.List<PairNode> e] : ^( EXP_MAP ( exprPair )+ ) ;
    public final java.util.List<PairNode> exprMap() throws RecognitionException {
        java.util.List<PairNode> e = null;

        PairNode exprPair37 = null;


        e = new java.util.ArrayList<PairNode>();
        try {
            // grammar/PlazmaScriptWalker.g:140:3: ( ^( EXP_MAP ( exprPair )+ ) )
            // grammar/PlazmaScriptWalker.g:140:6: ^( EXP_MAP ( exprPair )+ )
            {
            match(input,EXP_MAP,FOLLOW_EXP_MAP_in_exprMap868); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:140:16: ( exprPair )+
            int cnt20=0;
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==EXP_PAIR) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:140:17: exprPair
            	    {
            	    pushFollow(FOLLOW_exprPair_in_exprMap871);
            	    exprPair37=exprPair();

            	    state._fsp--;

            	    e.add(exprPair37);

            	    }
            	    break;

            	default :
            	    if ( cnt20 >= 1 ) break loop20;
                        EarlyExitException eee =
                            new EarlyExitException(20, input);
                        throw eee;
                }
                cnt20++;
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
    // grammar/PlazmaScriptWalker.g:144:1: expression returns [LNode node] : ( ^( TERNARY a= expression b= expression c= expression ) | ^( In a= expression b= expression ) | ^( RangeE a= expression b= expression ) | ^( Range a= expression b= expression ) | ^( 'xor' a= expression b= expression ) | ^( '||' a= expression b= expression ) | ^( '|' a= expression b= expression ) | ^( 'or' a= expression b= expression ) | ^( '&&' a= expression b= expression ) | ^( '&' a= expression b= expression ) | ^( 'and' a= expression b= expression ) | ^( '==' a= expression b= expression ) | ^( '!=' a= expression b= expression ) | ^( '>=' a= expression b= expression ) | ^( '<=' a= expression b= expression ) | ^( '>' a= expression b= expression ) | ^( '<' a= expression b= expression ) | ^( '+' a= expression b= expression ) | ^( '-' a= expression b= expression ) | ^( '*' a= expression b= expression ) | ^( '/' a= expression b= expression ) | ^( '%' a= expression b= expression ) | ^( '^' a= expression b= expression ) | ^( UNARY_PLUS a= expression ) | ^( UNARY_MIN a= expression ) | ^( NEGATE a= expression ) | Integer | Number | Bool | Null | NaN | Infinity | lookup );
    public final LNode expression() throws RecognitionException {
        LNode node = null;

        CommonTree Integer38=null;
        CommonTree Number39=null;
        CommonTree Bool40=null;
        LNode a = null;

        LNode b = null;

        LNode c = null;

        LNode lookup41 = null;


        try {
            // grammar/PlazmaScriptWalker.g:145:3: ( ^( TERNARY a= expression b= expression c= expression ) | ^( In a= expression b= expression ) | ^( RangeE a= expression b= expression ) | ^( Range a= expression b= expression ) | ^( 'xor' a= expression b= expression ) | ^( '||' a= expression b= expression ) | ^( '|' a= expression b= expression ) | ^( 'or' a= expression b= expression ) | ^( '&&' a= expression b= expression ) | ^( '&' a= expression b= expression ) | ^( 'and' a= expression b= expression ) | ^( '==' a= expression b= expression ) | ^( '!=' a= expression b= expression ) | ^( '>=' a= expression b= expression ) | ^( '<=' a= expression b= expression ) | ^( '>' a= expression b= expression ) | ^( '<' a= expression b= expression ) | ^( '+' a= expression b= expression ) | ^( '-' a= expression b= expression ) | ^( '*' a= expression b= expression ) | ^( '/' a= expression b= expression ) | ^( '%' a= expression b= expression ) | ^( '^' a= expression b= expression ) | ^( UNARY_PLUS a= expression ) | ^( UNARY_MIN a= expression ) | ^( NEGATE a= expression ) | Integer | Number | Bool | Null | NaN | Infinity | lookup )
            int alt21=33;
            switch ( input.LA(1) ) {
            case TERNARY:
                {
                alt21=1;
                }
                break;
            case In:
                {
                alt21=2;
                }
                break;
            case RangeE:
                {
                alt21=3;
                }
                break;
            case Range:
                {
                alt21=4;
                }
                break;
            case XorWord:
                {
                alt21=5;
                }
                break;
            case Or:
                {
                alt21=6;
                }
                break;
            case BitOr:
                {
                alt21=7;
                }
                break;
            case OrWord:
                {
                alt21=8;
                }
                break;
            case And:
                {
                alt21=9;
                }
                break;
            case BitAnd:
                {
                alt21=10;
                }
                break;
            case AndWord:
                {
                alt21=11;
                }
                break;
            case Equals:
                {
                alt21=12;
                }
                break;
            case NEquals:
                {
                alt21=13;
                }
                break;
            case GTEquals:
                {
                alt21=14;
                }
                break;
            case LTEquals:
                {
                alt21=15;
                }
                break;
            case GT:
                {
                alt21=16;
                }
                break;
            case LT:
                {
                alt21=17;
                }
                break;
            case Add:
                {
                alt21=18;
                }
                break;
            case Subtract:
                {
                alt21=19;
                }
                break;
            case Multiply:
                {
                alt21=20;
                }
                break;
            case Divide:
                {
                alt21=21;
                }
                break;
            case Modulus:
                {
                alt21=22;
                }
                break;
            case Pow:
                {
                alt21=23;
                }
                break;
            case UNARY_PLUS:
                {
                alt21=24;
                }
                break;
            case UNARY_MIN:
                {
                alt21=25;
                }
                break;
            case NEGATE:
                {
                alt21=26;
                }
                break;
            case Integer:
                {
                alt21=27;
                }
                break;
            case Number:
                {
                alt21=28;
                }
                break;
            case Bool:
                {
                alt21=29;
                }
                break;
            case Null:
                {
                alt21=30;
                }
                break;
            case NaN:
                {
                alt21=31;
                }
                break;
            case Infinity:
                {
                alt21=32;
                }
                break;
            case LOOKUP:
                {
                alt21=33;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }

            switch (alt21) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:145:6: ^( TERNARY a= expression b= expression c= expression )
                    {
                    match(input,TERNARY,FOLLOW_TERNARY_in_expression896); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression900);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression904);
                    b=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression908);
                    c=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new TernaryNode(a, b, c);

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:146:6: ^( In a= expression b= expression )
                    {
                    match(input,In,FOLLOW_In_in_expression919); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression923);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression927);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new InNode(a, b);

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:148:6: ^( RangeE a= expression b= expression )
                    {
                    match(input,RangeE,FOLLOW_RangeE_in_expression959); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression963);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression967);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new RangeENode(a, b);

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:149:6: ^( Range a= expression b= expression )
                    {
                    match(input,Range,FOLLOW_Range_in_expression993); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression997);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1001);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new RangeNode(a, b);

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:151:6: ^( 'xor' a= expression b= expression )
                    {
                    match(input,XorWord,FOLLOW_XorWord_in_expression1031); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1035);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1039);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new XorNode(a, b);

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScriptWalker.g:152:6: ^( '||' a= expression b= expression )
                    {
                    match(input,Or,FOLLOW_Or_in_expression1065); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1069);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1073);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new OrNode(a, b);

                    }
                    break;
                case 7 :
                    // grammar/PlazmaScriptWalker.g:153:6: ^( '|' a= expression b= expression )
                    {
                    match(input,BitOr,FOLLOW_BitOr_in_expression1100); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1104);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1108);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new BitOrNode(a, b);

                    }
                    break;
                case 8 :
                    // grammar/PlazmaScriptWalker.g:154:6: ^( 'or' a= expression b= expression )
                    {
                    match(input,OrWord,FOLLOW_OrWord_in_expression1138); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1142);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1146);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new OrNode(a, b);

                    }
                    break;
                case 9 :
                    // grammar/PlazmaScriptWalker.g:155:6: ^( '&&' a= expression b= expression )
                    {
                    match(input,And,FOLLOW_And_in_expression1175); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1179);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1183);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AndNode(a, b);

                    }
                    break;
                case 10 :
                    // grammar/PlazmaScriptWalker.g:156:6: ^( '&' a= expression b= expression )
                    {
                    match(input,BitAnd,FOLLOW_BitAnd_in_expression1210); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1214);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1218);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new BitAndNode(a, b);

                    }
                    break;
                case 11 :
                    // grammar/PlazmaScriptWalker.g:157:6: ^( 'and' a= expression b= expression )
                    {
                    match(input,AndWord,FOLLOW_AndWord_in_expression1248); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1252);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1256);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AndNode(a, b);

                    }
                    break;
                case 12 :
                    // grammar/PlazmaScriptWalker.g:158:6: ^( '==' a= expression b= expression )
                    {
                    match(input,Equals,FOLLOW_Equals_in_expression1284); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1288);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1292);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new EQNode(a, b);

                    }
                    break;
                case 13 :
                    // grammar/PlazmaScriptWalker.g:159:6: ^( '!=' a= expression b= expression )
                    {
                    match(input,NEquals,FOLLOW_NEquals_in_expression1319); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1323);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1327);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new NENode(a, b);

                    }
                    break;
                case 14 :
                    // grammar/PlazmaScriptWalker.g:160:6: ^( '>=' a= expression b= expression )
                    {
                    match(input,GTEquals,FOLLOW_GTEquals_in_expression1354); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1358);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1362);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new GTENode(a, b);

                    }
                    break;
                case 15 :
                    // grammar/PlazmaScriptWalker.g:161:6: ^( '<=' a= expression b= expression )
                    {
                    match(input,LTEquals,FOLLOW_LTEquals_in_expression1389); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1393);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1397);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new LTENode(a, b);

                    }
                    break;
                case 16 :
                    // grammar/PlazmaScriptWalker.g:162:6: ^( '>' a= expression b= expression )
                    {
                    match(input,GT,FOLLOW_GT_in_expression1424); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1428);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1432);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new GTNode(a, b);

                    }
                    break;
                case 17 :
                    // grammar/PlazmaScriptWalker.g:163:6: ^( '<' a= expression b= expression )
                    {
                    match(input,LT,FOLLOW_LT_in_expression1460); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1464);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1468);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new LTNode(a, b);

                    }
                    break;
                case 18 :
                    // grammar/PlazmaScriptWalker.g:164:6: ^( '+' a= expression b= expression )
                    {
                    match(input,Add,FOLLOW_Add_in_expression1496); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1500);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1504);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AddNode(a, b);

                    }
                    break;
                case 19 :
                    // grammar/PlazmaScriptWalker.g:165:6: ^( '-' a= expression b= expression )
                    {
                    match(input,Subtract,FOLLOW_Subtract_in_expression1532); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1536);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1540);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new SubNode(a, b);

                    }
                    break;
                case 20 :
                    // grammar/PlazmaScriptWalker.g:166:6: ^( '*' a= expression b= expression )
                    {
                    match(input,Multiply,FOLLOW_Multiply_in_expression1568); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1572);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1576);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new MulNode(a, b);

                    }
                    break;
                case 21 :
                    // grammar/PlazmaScriptWalker.g:167:6: ^( '/' a= expression b= expression )
                    {
                    match(input,Divide,FOLLOW_Divide_in_expression1604); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1608);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1612);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new DivNode(a, b);

                    }
                    break;
                case 22 :
                    // grammar/PlazmaScriptWalker.g:169:6: ^( '%' a= expression b= expression )
                    {
                    match(input,Modulus,FOLLOW_Modulus_in_expression1643); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1647);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1651);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new ModNode(a, b);

                    }
                    break;
                case 23 :
                    // grammar/PlazmaScriptWalker.g:170:6: ^( '^' a= expression b= expression )
                    {
                    match(input,Pow,FOLLOW_Pow_in_expression1679); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1683);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1687);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new PowNode(a, b);

                    }
                    break;
                case 24 :
                    // grammar/PlazmaScriptWalker.g:171:6: ^( UNARY_PLUS a= expression )
                    {
                    match(input,UNARY_PLUS,FOLLOW_UNARY_PLUS_in_expression1715); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1719);
                    a=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new UnaryPlusNode(a);

                    }
                    break;
                case 25 :
                    // grammar/PlazmaScriptWalker.g:172:6: ^( UNARY_MIN a= expression )
                    {
                    match(input,UNARY_MIN,FOLLOW_UNARY_MIN_in_expression1756); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1760);
                    a=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new UnaryMinusNode(a);

                    }
                    break;
                case 26 :
                    // grammar/PlazmaScriptWalker.g:173:6: ^( NEGATE a= expression )
                    {
                    match(input,NEGATE,FOLLOW_NEGATE_in_expression1795); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1799);
                    a=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new NotNode(a);

                    }
                    break;
                case 27 :
                    // grammar/PlazmaScriptWalker.g:175:6: Integer
                    {
                    Integer38=(CommonTree)match(input,Integer,FOLLOW_Integer_in_expression1838); 
                    node = new IntegerNode((Integer38!=null?Integer38.getText():null));

                    }
                    break;
                case 28 :
                    // grammar/PlazmaScriptWalker.g:176:6: Number
                    {
                    Number39=(CommonTree)match(input,Number,FOLLOW_Number_in_expression1914); 
                    node = new NumberNode((Number39!=null?Number39.getText():null));

                    }
                    break;
                case 29 :
                    // grammar/PlazmaScriptWalker.g:177:6: Bool
                    {
                    Bool40=(CommonTree)match(input,Bool,FOLLOW_Bool_in_expression1992); 
                    node = new BooleanNode((Bool40!=null?Bool40.getText():null));

                    }
                    break;
                case 30 :
                    // grammar/PlazmaScriptWalker.g:178:6: Null
                    {
                    match(input,Null,FOLLOW_Null_in_expression2073); 
                    node = new NullNode();

                    }
                    break;
                case 31 :
                    // grammar/PlazmaScriptWalker.g:179:6: NaN
                    {
                    match(input,NaN,FOLLOW_NaN_in_expression2127); 
                    node = new NaNNode();

                    }
                    break;
                case 32 :
                    // grammar/PlazmaScriptWalker.g:180:6: Infinity
                    {
                    match(input,Infinity,FOLLOW_Infinity_in_expression2184); 
                    node = new InfinityNode();

                    }
                    break;
                case 33 :
                    // grammar/PlazmaScriptWalker.g:181:6: lookup
                    {
                    pushFollow(FOLLOW_lookup_in_expression2236);
                    lookup41=lookup();

                    state._fsp--;

                    node = lookup41;

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
    // grammar/PlazmaScriptWalker.g:184:1: list returns [LNode node] : ^( LIST ( exprList )? ) ;
    public final LNode list() throws RecognitionException {
        LNode node = null;

        java.util.List<LNode> exprList42 = null;


        try {
            // grammar/PlazmaScriptWalker.g:185:3: ( ^( LIST ( exprList )? ) )
            // grammar/PlazmaScriptWalker.g:185:6: ^( LIST ( exprList )? )
            {
            match(input,LIST,FOLLOW_LIST_in_list2300); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // grammar/PlazmaScriptWalker.g:185:13: ( exprList )?
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==EXP_LIST) ) {
                    alt22=1;
                }
                switch (alt22) {
                    case 1 :
                        // grammar/PlazmaScriptWalker.g:185:13: exprList
                        {
                        pushFollow(FOLLOW_exprList_in_list2302);
                        exprList42=exprList();

                        state._fsp--;


                        }
                        break;

                }


                match(input, Token.UP, null); 
            }
            node = new ListNode(exprList42);

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
    // grammar/PlazmaScriptWalker.g:188:1: map returns [LNode node] : ^( MAP ( exprMap )? ) ;
    public final LNode map() throws RecognitionException {
        LNode node = null;

        java.util.List<PairNode> exprMap43 = null;


        try {
            // grammar/PlazmaScriptWalker.g:189:3: ( ^( MAP ( exprMap )? ) )
            // grammar/PlazmaScriptWalker.g:189:6: ^( MAP ( exprMap )? )
            {
            match(input,MAP,FOLLOW_MAP_in_map2325); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // grammar/PlazmaScriptWalker.g:189:12: ( exprMap )?
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==EXP_MAP) ) {
                    alt23=1;
                }
                switch (alt23) {
                    case 1 :
                        // grammar/PlazmaScriptWalker.g:189:12: exprMap
                        {
                        pushFollow(FOLLOW_exprMap_in_map2327);
                        exprMap43=exprMap();

                        state._fsp--;


                        }
                        break;

                }


                match(input, Token.UP, null); 
            }
            node = new MapNode(exprMap43);

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
    // grammar/PlazmaScriptWalker.g:192:1: lookup returns [LNode node] : ( ^( LOOKUP functionCall (i= indexes )? ) | ^( LOOKUP list (i= indexes )? ) | ^( LOOKUP map (i= indexes )? ) | ^( LOOKUP expression (i= indexes )? ) | ^( LOOKUP Identifier (i= indexes )? ) | ^( LOOKUP String (i= indexes )? ) );
    public final LNode lookup() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier48=null;
        CommonTree String49=null;
        java.util.List<LNode> i = null;

        LNode functionCall44 = null;

        LNode list45 = null;

        LNode map46 = null;

        LNode expression47 = null;


        try {
            // grammar/PlazmaScriptWalker.g:193:3: ( ^( LOOKUP functionCall (i= indexes )? ) | ^( LOOKUP list (i= indexes )? ) | ^( LOOKUP map (i= indexes )? ) | ^( LOOKUP expression (i= indexes )? ) | ^( LOOKUP Identifier (i= indexes )? ) | ^( LOOKUP String (i= indexes )? ) )
            int alt30=6;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==LOOKUP) ) {
                int LA30_1 = input.LA(2);

                if ( (LA30_1==DOWN) ) {
                    switch ( input.LA(3) ) {
                    case Identifier:
                        {
                        alt30=5;
                        }
                        break;
                    case String:
                        {
                        alt30=6;
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
                        alt30=4;
                        }
                        break;
                    case MAP:
                        {
                        alt30=3;
                        }
                        break;
                    case LIST:
                        {
                        alt30=2;
                        }
                        break;
                    case FUNC_CALL:
                        {
                        alt30=1;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 30, 2, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 30, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }
            switch (alt30) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:193:6: ^( LOOKUP functionCall (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2350); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_functionCall_in_lookup2352);
                    functionCall44=functionCall();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:193:29: (i= indexes )?
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( (LA24_0==TAILS) ) {
                        alt24=1;
                    }
                    switch (alt24) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:193:29: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2356);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(functionCall44, i) : functionCall44;

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:194:6: ^( LOOKUP list (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2368); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_list_in_lookup2370);
                    list45=list();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:194:21: (i= indexes )?
                    int alt25=2;
                    int LA25_0 = input.LA(1);

                    if ( (LA25_0==TAILS) ) {
                        alt25=1;
                    }
                    switch (alt25) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:194:21: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2374);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(list45, i) : list45;

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:195:6: ^( LOOKUP map (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2394); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_map_in_lookup2396);
                    map46=map();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:195:20: (i= indexes )?
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==TAILS) ) {
                        alt26=1;
                    }
                    switch (alt26) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:195:20: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2400);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(map46, i) : map46;

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:196:6: ^( LOOKUP expression (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2423); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_lookup2425);
                    expression47=expression();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:196:27: (i= indexes )?
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==TAILS) ) {
                        alt27=1;
                    }
                    switch (alt27) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:196:27: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2429);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(expression47, i) : expression47;

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:197:6: ^( LOOKUP Identifier (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2443); 

                    match(input, Token.DOWN, null); 
                    Identifier48=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_lookup2445); 
                    // grammar/PlazmaScriptWalker.g:197:27: (i= indexes )?
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==TAILS) ) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:197:27: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2449);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(new IdentifierNode((Identifier48!=null?Identifier48.getText():null), currentScope, globalScope), i) : new IdentifierNode((Identifier48!=null?Identifier48.getText():null), currentScope, globalScope);

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScriptWalker.g:198:6: ^( LOOKUP String (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2463); 

                    match(input, Token.DOWN, null); 
                    String49=(CommonTree)match(input,String,FOLLOW_String_in_lookup2465); 
                    // grammar/PlazmaScriptWalker.g:198:23: (i= indexes )?
                    int alt29=2;
                    int LA29_0 = input.LA(1);

                    if ( (LA29_0==TAILS) ) {
                        alt29=1;
                    }
                    switch (alt29) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:198:23: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2469);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(new StringNode((String49!=null?String49.getText():null)), i) : new StringNode((String49!=null?String49.getText():null));

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
    // grammar/PlazmaScriptWalker.g:201:1: indexes returns [java.util.List<LNode> e] : ^( TAILS ( tail )+ ) ;
    public final java.util.List<LNode> indexes() throws RecognitionException {
        java.util.List<LNode> e = null;

        LNode tail50 = null;


        e = new java.util.ArrayList<LNode>();
        try {
            // grammar/PlazmaScriptWalker.g:204:3: ( ^( TAILS ( tail )+ ) )
            // grammar/PlazmaScriptWalker.g:204:8: ^( TAILS ( tail )+ )
            {
            match(input,TAILS,FOLLOW_TAILS_in_indexes2508); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:204:18: ( tail )+
            int cnt31=0;
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( ((LA31_0>=INDEX && LA31_0<=CALL)) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:204:19: tail
            	    {
            	    pushFollow(FOLLOW_tail_in_indexes2513);
            	    tail50=tail();

            	    state._fsp--;

            	    e.add(tail50);

            	    }
            	    break;

            	default :
            	    if ( cnt31 >= 1 ) break loop31;
                        EarlyExitException eee =
                            new EarlyExitException(31, input);
                        throw eee;
                }
                cnt31++;
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
    // grammar/PlazmaScriptWalker.g:208:1: tail returns [LNode node] : ( ^( INDEX expression ) | ^( ATTRIBUTE Identifier ) | ^( CALL Identifier ( exprList )? ) );
    public final LNode tail() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier52=null;
        CommonTree Identifier53=null;
        LNode expression51 = null;

        java.util.List<LNode> exprList54 = null;


        try {
            // grammar/PlazmaScriptWalker.g:209:2: ( ^( INDEX expression ) | ^( ATTRIBUTE Identifier ) | ^( CALL Identifier ( exprList )? ) )
            int alt33=3;
            switch ( input.LA(1) ) {
            case INDEX:
                {
                alt33=1;
                }
                break;
            case ATTRIBUTE:
                {
                alt33=2;
                }
                break;
            case CALL:
                {
                alt33=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }

            switch (alt33) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:209:4: ^( INDEX expression )
                    {
                    match(input,INDEX,FOLLOW_INDEX_in_tail2538); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_tail2540);
                    expression51=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = expression51;

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:210:4: ^( ATTRIBUTE Identifier )
                    {
                    match(input,ATTRIBUTE,FOLLOW_ATTRIBUTE_in_tail2559); 

                    match(input, Token.DOWN, null); 
                    Identifier52=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_tail2561); 

                    match(input, Token.UP, null); 
                    node = new StringNode((Identifier52!=null?Identifier52.getText():null));

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:211:4: ^( CALL Identifier ( exprList )? )
                    {
                    match(input,CALL,FOLLOW_CALL_in_tail2576); 

                    match(input, Token.DOWN, null); 
                    Identifier53=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_tail2578); 
                    // grammar/PlazmaScriptWalker.g:211:22: ( exprList )?
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( (LA32_0==EXP_LIST) ) {
                        alt32=1;
                    }
                    switch (alt32) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:211:22: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_tail2580);
                            exprList54=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new MethodCallNode((Identifier53!=null?Identifier53.getText():null), exprList54, functions, globalScope);

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
    // grammar/PlazmaScriptWalker.g:214:1: variableDef returns [LNode node] : Var ;
    public final LNode variableDef() throws RecognitionException {
        LNode node = null;

        CommonTree Var55=null;

        try {
            // grammar/PlazmaScriptWalker.g:215:3: ( Var )
            // grammar/PlazmaScriptWalker.g:215:5: Var
            {
            Var55=(CommonTree)match(input,Var,FOLLOW_Var_in_variableDef2604); 
            node = new VariableDefNode((Var55!=null?Var55.getText():null), null);

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


    protected DFA15 dfa15 = new DFA15(this);
    static final String DFA15_eotS =
        "\16\uffff";
    static final String DFA15_eofS =
        "\16\uffff";
    static final String DFA15_minS =
        "\1\10\1\2\1\43\13\uffff";
    static final String DFA15_maxS =
        "\1\10\1\2\1\55\13\uffff";
    static final String DFA15_acceptS =
        "\3\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13";
    static final String DFA15_specialS =
        "\16\uffff}>";
    static final String[] DFA15_transitionS = {
            "\1\1",
            "\1\2",
            "\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA15_eot = DFA.unpackEncodedString(DFA15_eotS);
    static final short[] DFA15_eof = DFA.unpackEncodedString(DFA15_eofS);
    static final char[] DFA15_min = DFA.unpackEncodedStringToUnsignedChars(DFA15_minS);
    static final char[] DFA15_max = DFA.unpackEncodedStringToUnsignedChars(DFA15_maxS);
    static final short[] DFA15_accept = DFA.unpackEncodedString(DFA15_acceptS);
    static final short[] DFA15_special = DFA.unpackEncodedString(DFA15_specialS);
    static final short[][] DFA15_transition;

    static {
        int numStates = DFA15_transitionS.length;
        DFA15_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA15_transition[i] = DFA.unpackEncodedString(DFA15_transitionS[i]);
        }
    }

    class DFA15 extends DFA {

        public DFA15(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 15;
            this.eot = DFA15_eot;
            this.eof = DFA15_eof;
            this.min = DFA15_min;
            this.max = DFA15_max;
            this.accept = DFA15_accept;
            this.special = DFA15_special;
            this.transition = DFA15_transition;
        }
        public String getDescription() {
            return "80:1: functionCall returns [LNode node] : ( ^( FUNC_CALL Identifier ( exprList )? ) | ^( FUNC_CALL Println ( expression )? ) | ^( FUNC_CALL Print expression ) | ^( FUNC_CALL Assert expression ) | ^( FUNC_CALL Date ( exprList )? ) | ^( FUNC_CALL DateTime ( exprList )? ) | ^( FUNC_CALL Time ( exprList )? ) | ^( FUNC_CALL Duration ( expression )? ) | ^( FUNC_CALL Period ( exprList )? ) | ^( FUNC_CALL List ( exprList )? ) | ^( FUNC_CALL Set ( exprList )? ) );";
        }
    }
 

    public static final BitSet FOLLOW_block_in_walk50 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BLOCK_in_block81 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STATEMENTS_in_block84 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_block87 = new BitSet(new long[]{0x000C000620004188L});
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
    public static final BitSet FOLLOW_Identifier_in_assignment322 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_indexes_in_assignment324 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_assignment327 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall349 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_functionCall351 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall353 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall365 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Println_in_functionCall367 = new BitSet(new long[]{0xDFF0000022078008L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_functionCall369 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall382 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Print_in_functionCall384 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_functionCall386 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall401 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Assert_in_functionCall403 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_functionCall405 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall419 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Date_in_functionCall421 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall423 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall441 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_DateTime_in_functionCall443 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall445 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall461 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Time_in_functionCall463 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall465 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall483 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Duration_in_functionCall485 = new BitSet(new long[]{0xDFF0000022078008L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_functionCall487 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall499 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Period_in_functionCall501 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall503 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall527 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_List_in_functionCall529 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall531 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall549 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Set_in_functionCall551 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall553 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IF_in_ifStatement598 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ifStat_in_ifStatement600 = new BitSet(new long[]{0x0000000000000208L});
    public static final BitSet FOLLOW_elseIfStat_in_ifStatement604 = new BitSet(new long[]{0x0000000000000208L});
    public static final BitSet FOLLOW_elseStat_in_ifStatement610 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_ifStat630 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_ifStat632 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_ifStat634 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_elseIfStat653 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_elseIfStat655 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_elseIfStat657 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_elseStat676 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_block_in_elseStat678 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_For_in_forStatement709 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_forStatement711 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_forStatement715 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_forStatement717 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_While_in_whileStatement742 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_whileStatement744 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_whileStatement746 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_LIST_in_idList773 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_idList776 = new BitSet(new long[]{0x0000000800000008L});
    public static final BitSet FOLLOW_EXP_LIST_in_exprList806 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_exprList809 = new BitSet(new long[]{0xDFF0000022078008L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_EXP_PAIR_in_exprPair832 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_exprPair836 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_exprPair840 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_MAP_in_exprMap868 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprPair_in_exprMap871 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_TERNARY_in_expression896 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression900 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_expression904 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_expression908 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_In_in_expression919 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression923 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_expression927 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RangeE_in_expression959 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression963 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_expression967 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Range_in_expression993 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression997 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_expression1001 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_XorWord_in_expression1031 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1035 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_expression1039 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Or_in_expression1065 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1069 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_expression1073 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BitOr_in_expression1100 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1104 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_expression1108 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_OrWord_in_expression1138 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1142 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_expression1146 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_And_in_expression1175 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1179 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_expression1183 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BitAnd_in_expression1210 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1214 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_expression1218 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_AndWord_in_expression1248 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1252 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_expression1256 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Equals_in_expression1284 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1288 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_expression1292 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEquals_in_expression1319 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1323 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_expression1327 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GTEquals_in_expression1354 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1358 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_expression1362 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LTEquals_in_expression1389 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1393 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_expression1397 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GT_in_expression1424 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1428 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_expression1432 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LT_in_expression1460 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1464 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_expression1468 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Add_in_expression1496 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1500 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_expression1504 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Subtract_in_expression1532 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1536 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_expression1540 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Multiply_in_expression1568 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1572 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_expression1576 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Divide_in_expression1604 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1608 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_expression1612 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Modulus_in_expression1643 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1647 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_expression1651 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Pow_in_expression1679 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1683 = new BitSet(new long[]{0xDFF0000022078000L,0x000000000007F3FFL});
    public static final BitSet FOLLOW_expression_in_expression1687 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_UNARY_PLUS_in_expression1715 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1719 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_UNARY_MIN_in_expression1756 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1760 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEGATE_in_expression1795 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1799 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Integer_in_expression1838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Number_in_expression1914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Bool_in_expression1992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Null_in_expression2073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NaN_in_expression2127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Infinity_in_expression2184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_expression2236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LIST_in_list2300 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprList_in_list2302 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MAP_in_map2325 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprMap_in_map2327 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2350 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_functionCall_in_lookup2352 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2356 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2368 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_list_in_lookup2370 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2374 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2394 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_map_in_lookup2396 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2400 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2423 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_lookup2425 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2429 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2443 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_lookup2445 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2449 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2463 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_String_in_lookup2465 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2469 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TAILS_in_indexes2508 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_tail_in_indexes2513 = new BitSet(new long[]{0x0000000000E00008L});
    public static final BitSet FOLLOW_INDEX_in_tail2538 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_tail2540 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ATTRIBUTE_in_tail2559 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_tail2561 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CALL_in_tail2576 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_tail2578 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_tail2580 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Var_in_variableDef2604 = new BitSet(new long[]{0x0000000000000002L});

}