// $ANTLR 3.2 Sep 23, 2009 12:02:23 grammar/PlazmaScriptWalker.g 2016-08-18 18:46:52

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BLOCK", "RETURN", "STATEMENTS", "ASSIGNMENT", "FUNC_CALL", "EXP", "EXP_PAIR", "EXP_MAP", "EXP_LIST", "ID_LIST", "IF", "TERNARY", "UNARY_PLUS", "UNARY_MIN", "NEGATE", "FUNCTION", "INDEXES", "INDEX", "ATTRIBUTE", "CALL", "TAIL", "TAILS", "MAP", "LIST", "SET", "LOOKUP", "BREAK", "CONTINUE", "Return", "Break", "Continue", "Identifier", "Println", "Print", "Assert", "Date", "DateTime", "Time", "List", "Set", "If", "Else", "Var", "Def", "For", "While", "In", "RangeE", "Range", "Integer", "Number", "Bool", "Null", "NaN", "Infinity", "String", "XorWord", "Or", "BitOr", "OrWord", "And", "BitAnd", "AndWord", "Equals", "NEquals", "GTEquals", "LTEquals", "Pow", "Not", "NotWord", "GT", "LT", "Add", "Subtract", "Multiply", "Divide", "Modulus", "OBrace", "CBrace", "OBracket", "CBracket", "OParen", "CParen", "SColon", "Assign", "Comma", "QMark", "Colon", "DecimalNumeral", "DecimalFloatingPoint", "Digits", "ExponentPart", "ContextIdentifier", "Digit", "Comment", "Space", "ExponentIndicator", "SignedInteger", "Sign", "NonZeroDigit", "'.'"
    };
    public static final int FUNCTION=19;
    public static final int LT=75;
    public static final int TERNARY=15;
    public static final int EXP_LIST=12;
    public static final int DateTime=40;
    public static final int Date=39;
    public static final int EOF=-1;
    public static final int QMark=90;
    public static final int NotWord=73;
    public static final int BREAK=30;
    public static final int Identifier=35;
    public static final int UNARY_PLUS=16;
    public static final int FUNC_CALL=8;
    public static final int CParen=86;
    public static final int Comment=98;
    public static final int EXP=9;
    public static final int Digits=94;
    public static final int CBrace=82;
    public static final int RETURN=5;
    public static final int ExponentPart=95;
    public static final int ExponentIndicator=100;
    public static final int Sign=102;
    public static final int OrWord=63;
    public static final int Null=56;
    public static final int DecimalNumeral=92;
    public static final int CBracket=84;
    public static final int ContextIdentifier=96;
    public static final int Println=36;
    public static final int Bool=55;
    public static final int Modulus=80;
    public static final int Time=41;
    public static final int AndWord=66;
    public static final int Colon=91;
    public static final int LIST=27;
    public static final int Def=47;
    public static final int RangeE=51;
    public static final int LOOKUP=29;
    public static final int Range=52;
    public static final int Break=33;
    public static final int SignedInteger=101;
    public static final int BitOr=62;
    public static final int STATEMENTS=6;
    public static final int GT=74;
    public static final int CALL=23;
    public static final int DecimalFloatingPoint=93;
    public static final int Else=45;
    public static final int Equals=67;
    public static final int Var=46;
    public static final int XorWord=60;
    public static final int OParen=85;
    public static final int Assert=38;
    public static final int ATTRIBUTE=22;
    public static final int While=49;
    public static final int ID_LIST=13;
    public static final int Add=76;
    public static final int Set=43;
    public static final int TAIL=24;
    public static final int IF=14;
    public static final int Space=99;
    public static final int INDEX=21;
    public static final int Assign=88;
    public static final int EXP_MAP=11;
    public static final int NaN=57;
    public static final int Number=54;
    public static final int CONTINUE=31;
    public static final int T__104=104;
    public static final int Print=37;
    public static final int GTEquals=69;
    public static final int String=59;
    public static final int Or=61;
    public static final int Return=32;
    public static final int If=44;
    public static final int And=64;
    public static final int In=50;
    public static final int NEquals=68;
    public static final int Continue=34;
    public static final int Subtract=77;
    public static final int EXP_PAIR=10;
    public static final int BitAnd=65;
    public static final int Multiply=78;
    public static final int OBrace=81;
    public static final int INDEXES=20;
    public static final int NEGATE=18;
    public static final int SET=28;
    public static final int Digit=97;
    public static final int For=48;
    public static final int Divide=79;
    public static final int List=42;
    public static final int TAILS=25;
    public static final int SColon=87;
    public static final int OBracket=83;
    public static final int NonZeroDigit=103;
    public static final int BLOCK=4;
    public static final int MAP=26;
    public static final int Not=72;
    public static final int UNARY_MIN=17;
    public static final int ASSIGNMENT=7;
    public static final int Infinity=58;
    public static final int Comma=89;
    public static final int Integer=53;
    public static final int Pow=71;
    public static final int LTEquals=70;

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
    // grammar/PlazmaScriptWalker.g:80:1: functionCall returns [LNode node] : ( ^( FUNC_CALL Identifier ( exprList )? ) | ^( FUNC_CALL Println ( expression )? ) | ^( FUNC_CALL Print expression ) | ^( FUNC_CALL Assert expression ) | ^( FUNC_CALL Date ( exprList )? ) | ^( FUNC_CALL DateTime ( exprList )? ) | ^( FUNC_CALL Time ( exprList )? ) | ^( FUNC_CALL List ( exprList )? ) | ^( FUNC_CALL Set ( exprList )? ) );
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

        java.util.List<LNode> exprList22 = null;

        java.util.List<LNode> exprList23 = null;


        try {
            // grammar/PlazmaScriptWalker.g:81:3: ( ^( FUNC_CALL Identifier ( exprList )? ) | ^( FUNC_CALL Println ( expression )? ) | ^( FUNC_CALL Print expression ) | ^( FUNC_CALL Assert expression ) | ^( FUNC_CALL Date ( exprList )? ) | ^( FUNC_CALL DateTime ( exprList )? ) | ^( FUNC_CALL Time ( exprList )? ) | ^( FUNC_CALL List ( exprList )? ) | ^( FUNC_CALL Set ( exprList )? ) )
            int alt13=9;
            alt13 = dfa13.predict(input);
            switch (alt13) {
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
                    // grammar/PlazmaScriptWalker.g:88:6: ^( FUNC_CALL List ( exprList )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall485); 

                    match(input, Token.DOWN, null); 
                    match(input,List,FOLLOW_List_in_functionCall487); 
                    // grammar/PlazmaScriptWalker.g:88:23: ( exprList )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==EXP_LIST) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:88:23: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall489);
                            exprList22=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new ListNode(exprList22);

                    }
                    break;
                case 9 :
                    // grammar/PlazmaScriptWalker.g:89:6: ^( FUNC_CALL Set ( exprList )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall507); 

                    match(input, Token.DOWN, null); 
                    match(input,Set,FOLLOW_Set_in_functionCall509); 
                    // grammar/PlazmaScriptWalker.g:89:22: ( exprList )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==EXP_LIST) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:89:22: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall511);
                            exprList23=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new SetNode(exprList23);

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
    // grammar/PlazmaScriptWalker.g:92:1: ifStatement returns [LNode node] : ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? ) ;
    public final LNode ifStatement() throws RecognitionException {
        LNode node = null;

        IfNode ifNode = new IfNode();
        try {
            // grammar/PlazmaScriptWalker.g:95:3: ( ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? ) )
            // grammar/PlazmaScriptWalker.g:95:6: ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? )
            {
            match(input,IF,FOLLOW_IF_in_ifStatement556); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_ifStat_in_ifStatement558);
            ifStat(ifNode);

            state._fsp--;

            // grammar/PlazmaScriptWalker.g:95:26: ( elseIfStat[ifNode] )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==EXP) ) {
                    int LA14_1 = input.LA(2);

                    if ( (LA14_1==DOWN) ) {
                        int LA14_3 = input.LA(3);

                        if ( ((LA14_3>=TERNARY && LA14_3<=NEGATE)||LA14_3==LOOKUP||(LA14_3>=In && LA14_3<=Infinity)||(LA14_3>=XorWord && LA14_3<=Pow)||(LA14_3>=GT && LA14_3<=Modulus)) ) {
                            alt14=1;
                        }


                    }


                }


                switch (alt14) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:95:27: elseIfStat[ifNode]
            	    {
            	    pushFollow(FOLLOW_elseIfStat_in_ifStatement562);
            	    elseIfStat(ifNode);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            // grammar/PlazmaScriptWalker.g:95:48: ( elseStat[ifNode] )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==EXP) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:95:49: elseStat[ifNode]
                    {
                    pushFollow(FOLLOW_elseStat_in_ifStatement568);
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
    // grammar/PlazmaScriptWalker.g:98:1: ifStat[IfNode parent] : ^( EXP expression block ) ;
    public final void ifStat(IfNode parent) throws RecognitionException {
        LNode expression24 = null;

        LNode block25 = null;


        try {
            // grammar/PlazmaScriptWalker.g:99:3: ( ^( EXP expression block ) )
            // grammar/PlazmaScriptWalker.g:99:6: ^( EXP expression block )
            {
            match(input,EXP,FOLLOW_EXP_in_ifStat588); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_ifStat590);
            expression24=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_ifStat592);
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
    // $ANTLR end "ifStat"


    // $ANTLR start "elseIfStat"
    // grammar/PlazmaScriptWalker.g:102:1: elseIfStat[IfNode parent] : ^( EXP expression block ) ;
    public final void elseIfStat(IfNode parent) throws RecognitionException {
        LNode expression26 = null;

        LNode block27 = null;


        try {
            // grammar/PlazmaScriptWalker.g:103:3: ( ^( EXP expression block ) )
            // grammar/PlazmaScriptWalker.g:103:6: ^( EXP expression block )
            {
            match(input,EXP,FOLLOW_EXP_in_elseIfStat611); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_elseIfStat613);
            expression26=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_elseIfStat615);
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
    // $ANTLR end "elseIfStat"


    // $ANTLR start "elseStat"
    // grammar/PlazmaScriptWalker.g:106:1: elseStat[IfNode parent] : ^( EXP block ) ;
    public final void elseStat(IfNode parent) throws RecognitionException {
        LNode block28 = null;


        try {
            // grammar/PlazmaScriptWalker.g:107:3: ( ^( EXP block ) )
            // grammar/PlazmaScriptWalker.g:107:6: ^( EXP block )
            {
            match(input,EXP,FOLLOW_EXP_in_elseStat634); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_block_in_elseStat636);
            block28=block();

            state._fsp--;


            match(input, Token.UP, null); 
            parent.addChoice(new BooleanNode(true), block28);

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
    // grammar/PlazmaScriptWalker.g:114:1: forStatement returns [LNode node] : ^( For Identifier a= expression block ) ;
    public final LNode forStatement() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier29=null;
        LNode a = null;

        LNode block30 = null;


        try {
            // grammar/PlazmaScriptWalker.g:115:3: ( ^( For Identifier a= expression block ) )
            // grammar/PlazmaScriptWalker.g:115:6: ^( For Identifier a= expression block )
            {
            match(input,For,FOLLOW_For_in_forStatement667); 

            match(input, Token.DOWN, null); 
            Identifier29=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_forStatement669); 
            pushFollow(FOLLOW_expression_in_forStatement673);
            a=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_forStatement675);
            block30=block();

            state._fsp--;


            match(input, Token.UP, null); 
            node = new ForStatementNode2((Identifier29!=null?Identifier29.getText():null), a, block30, currentScope);

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
    // grammar/PlazmaScriptWalker.g:119:1: whileStatement returns [LNode node] : ^( While expression block ) ;
    public final LNode whileStatement() throws RecognitionException {
        LNode node = null;

        LNode expression31 = null;

        LNode block32 = null;


        try {
            // grammar/PlazmaScriptWalker.g:120:3: ( ^( While expression block ) )
            // grammar/PlazmaScriptWalker.g:120:6: ^( While expression block )
            {
            match(input,While,FOLLOW_While_in_whileStatement700); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_whileStatement702);
            expression31=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_whileStatement704);
            block32=block();

            state._fsp--;


            match(input, Token.UP, null); 
            node = new WhileStatementNode(expression31, block32);

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
    // grammar/PlazmaScriptWalker.g:123:1: idList returns [java.util.List<String> i] : ^( ID_LIST ( Identifier )+ ) ;
    public final java.util.List<String> idList() throws RecognitionException {
        java.util.List<String> i = null;

        CommonTree Identifier33=null;

        i = new java.util.ArrayList<String>();
        try {
            // grammar/PlazmaScriptWalker.g:125:3: ( ^( ID_LIST ( Identifier )+ ) )
            // grammar/PlazmaScriptWalker.g:125:6: ^( ID_LIST ( Identifier )+ )
            {
            match(input,ID_LIST,FOLLOW_ID_LIST_in_idList731); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:125:16: ( Identifier )+
            int cnt16=0;
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==Identifier) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:125:17: Identifier
            	    {
            	    Identifier33=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_idList734); 
            	    i.add((Identifier33!=null?Identifier33.getText():null));

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
        return i;
    }
    // $ANTLR end "idList"


    // $ANTLR start "exprList"
    // grammar/PlazmaScriptWalker.g:128:1: exprList returns [java.util.List<LNode> e] : ^( EXP_LIST ( expression )+ ) ;
    public final java.util.List<LNode> exprList() throws RecognitionException {
        java.util.List<LNode> e = null;

        LNode expression34 = null;


        e = new java.util.ArrayList<LNode>();
        try {
            // grammar/PlazmaScriptWalker.g:130:3: ( ^( EXP_LIST ( expression )+ ) )
            // grammar/PlazmaScriptWalker.g:130:6: ^( EXP_LIST ( expression )+ )
            {
            match(input,EXP_LIST,FOLLOW_EXP_LIST_in_exprList764); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:130:17: ( expression )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0>=TERNARY && LA17_0<=NEGATE)||LA17_0==LOOKUP||(LA17_0>=In && LA17_0<=Infinity)||(LA17_0>=XorWord && LA17_0<=Pow)||(LA17_0>=GT && LA17_0<=Modulus)) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:130:18: expression
            	    {
            	    pushFollow(FOLLOW_expression_in_exprList767);
            	    expression34=expression();

            	    state._fsp--;

            	    e.add(expression34);

            	    }
            	    break;

            	default :
            	    if ( cnt17 >= 1 ) break loop17;
                        EarlyExitException eee =
                            new EarlyExitException(17, input);
                        throw eee;
                }
                cnt17++;
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
    // grammar/PlazmaScriptWalker.g:132:1: exprPair returns [PairNode node] : ^( EXP_PAIR k= expression v= expression ) ;
    public final PairNode exprPair() throws RecognitionException {
        PairNode node = null;

        LNode k = null;

        LNode v = null;


        try {
            // grammar/PlazmaScriptWalker.g:133:3: ( ^( EXP_PAIR k= expression v= expression ) )
            // grammar/PlazmaScriptWalker.g:133:6: ^( EXP_PAIR k= expression v= expression )
            {
            match(input,EXP_PAIR,FOLLOW_EXP_PAIR_in_exprPair790); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_exprPair794);
            k=expression();

            state._fsp--;

            pushFollow(FOLLOW_expression_in_exprPair798);
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
    // grammar/PlazmaScriptWalker.g:136:1: exprMap returns [java.util.List<PairNode> e] : ^( EXP_MAP ( exprPair )+ ) ;
    public final java.util.List<PairNode> exprMap() throws RecognitionException {
        java.util.List<PairNode> e = null;

        PairNode exprPair35 = null;


        e = new java.util.ArrayList<PairNode>();
        try {
            // grammar/PlazmaScriptWalker.g:138:3: ( ^( EXP_MAP ( exprPair )+ ) )
            // grammar/PlazmaScriptWalker.g:138:6: ^( EXP_MAP ( exprPair )+ )
            {
            match(input,EXP_MAP,FOLLOW_EXP_MAP_in_exprMap826); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:138:16: ( exprPair )+
            int cnt18=0;
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==EXP_PAIR) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:138:17: exprPair
            	    {
            	    pushFollow(FOLLOW_exprPair_in_exprMap829);
            	    exprPair35=exprPair();

            	    state._fsp--;

            	    e.add(exprPair35);

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
        return e;
    }
    // $ANTLR end "exprMap"


    // $ANTLR start "expression"
    // grammar/PlazmaScriptWalker.g:142:1: expression returns [LNode node] : ( ^( TERNARY a= expression b= expression c= expression ) | ^( In a= expression b= expression ) | ^( RangeE a= expression b= expression ) | ^( Range a= expression b= expression ) | ^( 'xor' a= expression b= expression ) | ^( '||' a= expression b= expression ) | ^( '|' a= expression b= expression ) | ^( 'or' a= expression b= expression ) | ^( '&&' a= expression b= expression ) | ^( '&' a= expression b= expression ) | ^( 'and' a= expression b= expression ) | ^( '==' a= expression b= expression ) | ^( '!=' a= expression b= expression ) | ^( '>=' a= expression b= expression ) | ^( '<=' a= expression b= expression ) | ^( '>' a= expression b= expression ) | ^( '<' a= expression b= expression ) | ^( '+' a= expression b= expression ) | ^( '-' a= expression b= expression ) | ^( '*' a= expression b= expression ) | ^( '/' a= expression b= expression ) | ^( '%' a= expression b= expression ) | ^( '^' a= expression b= expression ) | ^( UNARY_PLUS a= expression ) | ^( UNARY_MIN a= expression ) | ^( NEGATE a= expression ) | Integer | Number | Bool | Null | NaN | Infinity | lookup );
    public final LNode expression() throws RecognitionException {
        LNode node = null;

        CommonTree Integer36=null;
        CommonTree Number37=null;
        CommonTree Bool38=null;
        LNode a = null;

        LNode b = null;

        LNode c = null;

        LNode lookup39 = null;


        try {
            // grammar/PlazmaScriptWalker.g:143:3: ( ^( TERNARY a= expression b= expression c= expression ) | ^( In a= expression b= expression ) | ^( RangeE a= expression b= expression ) | ^( Range a= expression b= expression ) | ^( 'xor' a= expression b= expression ) | ^( '||' a= expression b= expression ) | ^( '|' a= expression b= expression ) | ^( 'or' a= expression b= expression ) | ^( '&&' a= expression b= expression ) | ^( '&' a= expression b= expression ) | ^( 'and' a= expression b= expression ) | ^( '==' a= expression b= expression ) | ^( '!=' a= expression b= expression ) | ^( '>=' a= expression b= expression ) | ^( '<=' a= expression b= expression ) | ^( '>' a= expression b= expression ) | ^( '<' a= expression b= expression ) | ^( '+' a= expression b= expression ) | ^( '-' a= expression b= expression ) | ^( '*' a= expression b= expression ) | ^( '/' a= expression b= expression ) | ^( '%' a= expression b= expression ) | ^( '^' a= expression b= expression ) | ^( UNARY_PLUS a= expression ) | ^( UNARY_MIN a= expression ) | ^( NEGATE a= expression ) | Integer | Number | Bool | Null | NaN | Infinity | lookup )
            int alt19=33;
            switch ( input.LA(1) ) {
            case TERNARY:
                {
                alt19=1;
                }
                break;
            case In:
                {
                alt19=2;
                }
                break;
            case RangeE:
                {
                alt19=3;
                }
                break;
            case Range:
                {
                alt19=4;
                }
                break;
            case XorWord:
                {
                alt19=5;
                }
                break;
            case Or:
                {
                alt19=6;
                }
                break;
            case BitOr:
                {
                alt19=7;
                }
                break;
            case OrWord:
                {
                alt19=8;
                }
                break;
            case And:
                {
                alt19=9;
                }
                break;
            case BitAnd:
                {
                alt19=10;
                }
                break;
            case AndWord:
                {
                alt19=11;
                }
                break;
            case Equals:
                {
                alt19=12;
                }
                break;
            case NEquals:
                {
                alt19=13;
                }
                break;
            case GTEquals:
                {
                alt19=14;
                }
                break;
            case LTEquals:
                {
                alt19=15;
                }
                break;
            case GT:
                {
                alt19=16;
                }
                break;
            case LT:
                {
                alt19=17;
                }
                break;
            case Add:
                {
                alt19=18;
                }
                break;
            case Subtract:
                {
                alt19=19;
                }
                break;
            case Multiply:
                {
                alt19=20;
                }
                break;
            case Divide:
                {
                alt19=21;
                }
                break;
            case Modulus:
                {
                alt19=22;
                }
                break;
            case Pow:
                {
                alt19=23;
                }
                break;
            case UNARY_PLUS:
                {
                alt19=24;
                }
                break;
            case UNARY_MIN:
                {
                alt19=25;
                }
                break;
            case NEGATE:
                {
                alt19=26;
                }
                break;
            case Integer:
                {
                alt19=27;
                }
                break;
            case Number:
                {
                alt19=28;
                }
                break;
            case Bool:
                {
                alt19=29;
                }
                break;
            case Null:
                {
                alt19=30;
                }
                break;
            case NaN:
                {
                alt19=31;
                }
                break;
            case Infinity:
                {
                alt19=32;
                }
                break;
            case LOOKUP:
                {
                alt19=33;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }

            switch (alt19) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:143:6: ^( TERNARY a= expression b= expression c= expression )
                    {
                    match(input,TERNARY,FOLLOW_TERNARY_in_expression854); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression858);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression862);
                    b=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression866);
                    c=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new TernaryNode(a, b, c);

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:144:6: ^( In a= expression b= expression )
                    {
                    match(input,In,FOLLOW_In_in_expression877); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression881);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression885);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new InNode(a, b);

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:146:6: ^( RangeE a= expression b= expression )
                    {
                    match(input,RangeE,FOLLOW_RangeE_in_expression917); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression921);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression925);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new RangeENode(a, b);

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:147:6: ^( Range a= expression b= expression )
                    {
                    match(input,Range,FOLLOW_Range_in_expression951); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression955);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression959);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new RangeNode(a, b);

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:149:6: ^( 'xor' a= expression b= expression )
                    {
                    match(input,XorWord,FOLLOW_XorWord_in_expression989); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression993);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression997);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new XorNode(a, b);

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScriptWalker.g:150:6: ^( '||' a= expression b= expression )
                    {
                    match(input,Or,FOLLOW_Or_in_expression1023); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1027);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1031);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new OrNode(a, b);

                    }
                    break;
                case 7 :
                    // grammar/PlazmaScriptWalker.g:151:6: ^( '|' a= expression b= expression )
                    {
                    match(input,BitOr,FOLLOW_BitOr_in_expression1058); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1062);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1066);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new BitOrNode(a, b);

                    }
                    break;
                case 8 :
                    // grammar/PlazmaScriptWalker.g:152:6: ^( 'or' a= expression b= expression )
                    {
                    match(input,OrWord,FOLLOW_OrWord_in_expression1096); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1100);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1104);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new OrNode(a, b);

                    }
                    break;
                case 9 :
                    // grammar/PlazmaScriptWalker.g:153:6: ^( '&&' a= expression b= expression )
                    {
                    match(input,And,FOLLOW_And_in_expression1133); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1137);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1141);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AndNode(a, b);

                    }
                    break;
                case 10 :
                    // grammar/PlazmaScriptWalker.g:154:6: ^( '&' a= expression b= expression )
                    {
                    match(input,BitAnd,FOLLOW_BitAnd_in_expression1168); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1172);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1176);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new BitAndNode(a, b);

                    }
                    break;
                case 11 :
                    // grammar/PlazmaScriptWalker.g:155:6: ^( 'and' a= expression b= expression )
                    {
                    match(input,AndWord,FOLLOW_AndWord_in_expression1206); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1210);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1214);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AndNode(a, b);

                    }
                    break;
                case 12 :
                    // grammar/PlazmaScriptWalker.g:156:6: ^( '==' a= expression b= expression )
                    {
                    match(input,Equals,FOLLOW_Equals_in_expression1242); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1246);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1250);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new EQNode(a, b);

                    }
                    break;
                case 13 :
                    // grammar/PlazmaScriptWalker.g:157:6: ^( '!=' a= expression b= expression )
                    {
                    match(input,NEquals,FOLLOW_NEquals_in_expression1277); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1281);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1285);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new NENode(a, b);

                    }
                    break;
                case 14 :
                    // grammar/PlazmaScriptWalker.g:158:6: ^( '>=' a= expression b= expression )
                    {
                    match(input,GTEquals,FOLLOW_GTEquals_in_expression1312); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1316);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1320);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new GTENode(a, b);

                    }
                    break;
                case 15 :
                    // grammar/PlazmaScriptWalker.g:159:6: ^( '<=' a= expression b= expression )
                    {
                    match(input,LTEquals,FOLLOW_LTEquals_in_expression1347); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1351);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1355);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new LTENode(a, b);

                    }
                    break;
                case 16 :
                    // grammar/PlazmaScriptWalker.g:160:6: ^( '>' a= expression b= expression )
                    {
                    match(input,GT,FOLLOW_GT_in_expression1382); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1386);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1390);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new GTNode(a, b);

                    }
                    break;
                case 17 :
                    // grammar/PlazmaScriptWalker.g:161:6: ^( '<' a= expression b= expression )
                    {
                    match(input,LT,FOLLOW_LT_in_expression1418); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1422);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1426);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new LTNode(a, b);

                    }
                    break;
                case 18 :
                    // grammar/PlazmaScriptWalker.g:162:6: ^( '+' a= expression b= expression )
                    {
                    match(input,Add,FOLLOW_Add_in_expression1454); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1458);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1462);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AddNode(a, b);

                    }
                    break;
                case 19 :
                    // grammar/PlazmaScriptWalker.g:163:6: ^( '-' a= expression b= expression )
                    {
                    match(input,Subtract,FOLLOW_Subtract_in_expression1490); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1494);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1498);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new SubNode(a, b);

                    }
                    break;
                case 20 :
                    // grammar/PlazmaScriptWalker.g:164:6: ^( '*' a= expression b= expression )
                    {
                    match(input,Multiply,FOLLOW_Multiply_in_expression1526); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1530);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1534);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new MulNode(a, b);

                    }
                    break;
                case 21 :
                    // grammar/PlazmaScriptWalker.g:165:6: ^( '/' a= expression b= expression )
                    {
                    match(input,Divide,FOLLOW_Divide_in_expression1562); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1566);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1570);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new DivNode(a, b);

                    }
                    break;
                case 22 :
                    // grammar/PlazmaScriptWalker.g:167:6: ^( '%' a= expression b= expression )
                    {
                    match(input,Modulus,FOLLOW_Modulus_in_expression1601); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1605);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1609);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new ModNode(a, b);

                    }
                    break;
                case 23 :
                    // grammar/PlazmaScriptWalker.g:168:6: ^( '^' a= expression b= expression )
                    {
                    match(input,Pow,FOLLOW_Pow_in_expression1637); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1641);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1645);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new PowNode(a, b);

                    }
                    break;
                case 24 :
                    // grammar/PlazmaScriptWalker.g:169:6: ^( UNARY_PLUS a= expression )
                    {
                    match(input,UNARY_PLUS,FOLLOW_UNARY_PLUS_in_expression1673); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1677);
                    a=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new UnaryPlusNode(a);

                    }
                    break;
                case 25 :
                    // grammar/PlazmaScriptWalker.g:170:6: ^( UNARY_MIN a= expression )
                    {
                    match(input,UNARY_MIN,FOLLOW_UNARY_MIN_in_expression1714); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1718);
                    a=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new UnaryMinusNode(a);

                    }
                    break;
                case 26 :
                    // grammar/PlazmaScriptWalker.g:171:6: ^( NEGATE a= expression )
                    {
                    match(input,NEGATE,FOLLOW_NEGATE_in_expression1753); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1757);
                    a=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new NotNode(a);

                    }
                    break;
                case 27 :
                    // grammar/PlazmaScriptWalker.g:173:6: Integer
                    {
                    Integer36=(CommonTree)match(input,Integer,FOLLOW_Integer_in_expression1796); 
                    node = new IntegerNode((Integer36!=null?Integer36.getText():null));

                    }
                    break;
                case 28 :
                    // grammar/PlazmaScriptWalker.g:174:6: Number
                    {
                    Number37=(CommonTree)match(input,Number,FOLLOW_Number_in_expression1872); 
                    node = new NumberNode((Number37!=null?Number37.getText():null));

                    }
                    break;
                case 29 :
                    // grammar/PlazmaScriptWalker.g:175:6: Bool
                    {
                    Bool38=(CommonTree)match(input,Bool,FOLLOW_Bool_in_expression1950); 
                    node = new BooleanNode((Bool38!=null?Bool38.getText():null));

                    }
                    break;
                case 30 :
                    // grammar/PlazmaScriptWalker.g:176:6: Null
                    {
                    match(input,Null,FOLLOW_Null_in_expression2031); 
                    node = new NullNode();

                    }
                    break;
                case 31 :
                    // grammar/PlazmaScriptWalker.g:177:6: NaN
                    {
                    match(input,NaN,FOLLOW_NaN_in_expression2085); 
                    node = new NaNNode();

                    }
                    break;
                case 32 :
                    // grammar/PlazmaScriptWalker.g:178:6: Infinity
                    {
                    match(input,Infinity,FOLLOW_Infinity_in_expression2142); 
                    node = new InfinityNode();

                    }
                    break;
                case 33 :
                    // grammar/PlazmaScriptWalker.g:179:6: lookup
                    {
                    pushFollow(FOLLOW_lookup_in_expression2194);
                    lookup39=lookup();

                    state._fsp--;

                    node = lookup39;

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
    // grammar/PlazmaScriptWalker.g:182:1: list returns [LNode node] : ^( LIST ( exprList )? ) ;
    public final LNode list() throws RecognitionException {
        LNode node = null;

        java.util.List<LNode> exprList40 = null;


        try {
            // grammar/PlazmaScriptWalker.g:183:3: ( ^( LIST ( exprList )? ) )
            // grammar/PlazmaScriptWalker.g:183:6: ^( LIST ( exprList )? )
            {
            match(input,LIST,FOLLOW_LIST_in_list2258); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // grammar/PlazmaScriptWalker.g:183:13: ( exprList )?
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==EXP_LIST) ) {
                    alt20=1;
                }
                switch (alt20) {
                    case 1 :
                        // grammar/PlazmaScriptWalker.g:183:13: exprList
                        {
                        pushFollow(FOLLOW_exprList_in_list2260);
                        exprList40=exprList();

                        state._fsp--;


                        }
                        break;

                }


                match(input, Token.UP, null); 
            }
            node = new ListNode(exprList40);

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
    // grammar/PlazmaScriptWalker.g:186:1: map returns [LNode node] : ^( MAP ( exprMap )? ) ;
    public final LNode map() throws RecognitionException {
        LNode node = null;

        java.util.List<PairNode> exprMap41 = null;


        try {
            // grammar/PlazmaScriptWalker.g:187:3: ( ^( MAP ( exprMap )? ) )
            // grammar/PlazmaScriptWalker.g:187:6: ^( MAP ( exprMap )? )
            {
            match(input,MAP,FOLLOW_MAP_in_map2283); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // grammar/PlazmaScriptWalker.g:187:12: ( exprMap )?
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==EXP_MAP) ) {
                    alt21=1;
                }
                switch (alt21) {
                    case 1 :
                        // grammar/PlazmaScriptWalker.g:187:12: exprMap
                        {
                        pushFollow(FOLLOW_exprMap_in_map2285);
                        exprMap41=exprMap();

                        state._fsp--;


                        }
                        break;

                }


                match(input, Token.UP, null); 
            }
            node = new MapNode(exprMap41);

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
    // grammar/PlazmaScriptWalker.g:190:1: lookup returns [LNode node] : ( ^( LOOKUP functionCall (i= indexes )? ) | ^( LOOKUP list (i= indexes )? ) | ^( LOOKUP map (i= indexes )? ) | ^( LOOKUP expression (i= indexes )? ) | ^( LOOKUP Identifier (i= indexes )? ) | ^( LOOKUP String (i= indexes )? ) );
    public final LNode lookup() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier46=null;
        CommonTree String47=null;
        java.util.List<LNode> i = null;

        LNode functionCall42 = null;

        LNode list43 = null;

        LNode map44 = null;

        LNode expression45 = null;


        try {
            // grammar/PlazmaScriptWalker.g:191:3: ( ^( LOOKUP functionCall (i= indexes )? ) | ^( LOOKUP list (i= indexes )? ) | ^( LOOKUP map (i= indexes )? ) | ^( LOOKUP expression (i= indexes )? ) | ^( LOOKUP Identifier (i= indexes )? ) | ^( LOOKUP String (i= indexes )? ) )
            int alt28=6;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==LOOKUP) ) {
                int LA28_1 = input.LA(2);

                if ( (LA28_1==DOWN) ) {
                    switch ( input.LA(3) ) {
                    case Identifier:
                        {
                        alt28=5;
                        }
                        break;
                    case String:
                        {
                        alt28=6;
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
                        alt28=4;
                        }
                        break;
                    case MAP:
                        {
                        alt28=3;
                        }
                        break;
                    case LIST:
                        {
                        alt28=2;
                        }
                        break;
                    case FUNC_CALL:
                        {
                        alt28=1;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 28, 2, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 28, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:191:6: ^( LOOKUP functionCall (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2308); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_functionCall_in_lookup2310);
                    functionCall42=functionCall();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:191:29: (i= indexes )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==TAILS) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:191:29: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2314);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(functionCall42, i) : functionCall42;

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:192:6: ^( LOOKUP list (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2326); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_list_in_lookup2328);
                    list43=list();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:192:21: (i= indexes )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==TAILS) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:192:21: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2332);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(list43, i) : list43;

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:193:6: ^( LOOKUP map (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2352); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_map_in_lookup2354);
                    map44=map();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:193:20: (i= indexes )?
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( (LA24_0==TAILS) ) {
                        alt24=1;
                    }
                    switch (alt24) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:193:20: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2358);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(map44, i) : map44;

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:194:6: ^( LOOKUP expression (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2381); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_lookup2383);
                    expression45=expression();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:194:27: (i= indexes )?
                    int alt25=2;
                    int LA25_0 = input.LA(1);

                    if ( (LA25_0==TAILS) ) {
                        alt25=1;
                    }
                    switch (alt25) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:194:27: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2387);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(expression45, i) : expression45;

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:195:6: ^( LOOKUP Identifier (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2401); 

                    match(input, Token.DOWN, null); 
                    Identifier46=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_lookup2403); 
                    // grammar/PlazmaScriptWalker.g:195:27: (i= indexes )?
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==TAILS) ) {
                        alt26=1;
                    }
                    switch (alt26) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:195:27: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2407);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(new IdentifierNode((Identifier46!=null?Identifier46.getText():null), currentScope, globalScope), i) : new IdentifierNode((Identifier46!=null?Identifier46.getText():null), currentScope, globalScope);

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScriptWalker.g:196:6: ^( LOOKUP String (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2421); 

                    match(input, Token.DOWN, null); 
                    String47=(CommonTree)match(input,String,FOLLOW_String_in_lookup2423); 
                    // grammar/PlazmaScriptWalker.g:196:23: (i= indexes )?
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==TAILS) ) {
                        alt27=1;
                    }
                    switch (alt27) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:196:23: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2427);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(new StringNode((String47!=null?String47.getText():null)), i) : new StringNode((String47!=null?String47.getText():null));

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
    // grammar/PlazmaScriptWalker.g:199:1: indexes returns [java.util.List<LNode> e] : ^( TAILS ( tail )+ ) ;
    public final java.util.List<LNode> indexes() throws RecognitionException {
        java.util.List<LNode> e = null;

        LNode tail48 = null;


        e = new java.util.ArrayList<LNode>();
        try {
            // grammar/PlazmaScriptWalker.g:202:3: ( ^( TAILS ( tail )+ ) )
            // grammar/PlazmaScriptWalker.g:202:8: ^( TAILS ( tail )+ )
            {
            match(input,TAILS,FOLLOW_TAILS_in_indexes2466); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:202:18: ( tail )+
            int cnt29=0;
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( ((LA29_0>=INDEX && LA29_0<=CALL)) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:202:19: tail
            	    {
            	    pushFollow(FOLLOW_tail_in_indexes2471);
            	    tail48=tail();

            	    state._fsp--;

            	    e.add(tail48);

            	    }
            	    break;

            	default :
            	    if ( cnt29 >= 1 ) break loop29;
                        EarlyExitException eee =
                            new EarlyExitException(29, input);
                        throw eee;
                }
                cnt29++;
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
    // grammar/PlazmaScriptWalker.g:206:1: tail returns [LNode node] : ( ^( INDEX expression ) | ^( ATTRIBUTE Identifier ) | ^( CALL Identifier ( exprList )? ) );
    public final LNode tail() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier50=null;
        CommonTree Identifier51=null;
        LNode expression49 = null;

        java.util.List<LNode> exprList52 = null;


        try {
            // grammar/PlazmaScriptWalker.g:207:2: ( ^( INDEX expression ) | ^( ATTRIBUTE Identifier ) | ^( CALL Identifier ( exprList )? ) )
            int alt31=3;
            switch ( input.LA(1) ) {
            case INDEX:
                {
                alt31=1;
                }
                break;
            case ATTRIBUTE:
                {
                alt31=2;
                }
                break;
            case CALL:
                {
                alt31=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }

            switch (alt31) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:207:4: ^( INDEX expression )
                    {
                    match(input,INDEX,FOLLOW_INDEX_in_tail2496); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_tail2498);
                    expression49=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = expression49;

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:208:4: ^( ATTRIBUTE Identifier )
                    {
                    match(input,ATTRIBUTE,FOLLOW_ATTRIBUTE_in_tail2517); 

                    match(input, Token.DOWN, null); 
                    Identifier50=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_tail2519); 

                    match(input, Token.UP, null); 
                    node = new StringNode((Identifier50!=null?Identifier50.getText():null));

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:209:4: ^( CALL Identifier ( exprList )? )
                    {
                    match(input,CALL,FOLLOW_CALL_in_tail2534); 

                    match(input, Token.DOWN, null); 
                    Identifier51=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_tail2536); 
                    // grammar/PlazmaScriptWalker.g:209:22: ( exprList )?
                    int alt30=2;
                    int LA30_0 = input.LA(1);

                    if ( (LA30_0==EXP_LIST) ) {
                        alt30=1;
                    }
                    switch (alt30) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:209:22: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_tail2538);
                            exprList52=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new MethodCallNode((Identifier51!=null?Identifier51.getText():null), exprList52, functions, globalScope);

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
    // grammar/PlazmaScriptWalker.g:212:1: variableDef returns [LNode node] : Var ;
    public final LNode variableDef() throws RecognitionException {
        LNode node = null;

        CommonTree Var53=null;

        try {
            // grammar/PlazmaScriptWalker.g:213:3: ( Var )
            // grammar/PlazmaScriptWalker.g:213:5: Var
            {
            Var53=(CommonTree)match(input,Var,FOLLOW_Var_in_variableDef2562); 
            node = new VariableDefNode((Var53!=null?Var53.getText():null), null);

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


    protected DFA13 dfa13 = new DFA13(this);
    static final String DFA13_eotS =
        "\14\uffff";
    static final String DFA13_eofS =
        "\14\uffff";
    static final String DFA13_minS =
        "\1\10\1\2\1\43\11\uffff";
    static final String DFA13_maxS =
        "\1\10\1\2\1\53\11\uffff";
    static final String DFA13_acceptS =
        "\3\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11";
    static final String DFA13_specialS =
        "\14\uffff}>";
    static final String[] DFA13_transitionS = {
            "\1\1",
            "\1\2",
            "\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13",
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

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }
        public String getDescription() {
            return "80:1: functionCall returns [LNode node] : ( ^( FUNC_CALL Identifier ( exprList )? ) | ^( FUNC_CALL Println ( expression )? ) | ^( FUNC_CALL Print expression ) | ^( FUNC_CALL Assert expression ) | ^( FUNC_CALL Date ( exprList )? ) | ^( FUNC_CALL DateTime ( exprList )? ) | ^( FUNC_CALL Time ( exprList )? ) | ^( FUNC_CALL List ( exprList )? ) | ^( FUNC_CALL Set ( exprList )? ) );";
        }
    }
 

    public static final BitSet FOLLOW_block_in_walk50 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BLOCK_in_block81 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STATEMENTS_in_block84 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_block87 = new BitSet(new long[]{0x0003000620004188L});
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
    public static final BitSet FOLLOW_Identifier_in_assignment322 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_indexes_in_assignment324 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_assignment327 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall349 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_functionCall351 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall353 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall365 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Println_in_functionCall367 = new BitSet(new long[]{0xF7FC000022078008L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_functionCall369 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall382 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Print_in_functionCall384 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_functionCall386 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall401 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Assert_in_functionCall403 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
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
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall485 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_List_in_functionCall487 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall489 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall507 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Set_in_functionCall509 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall511 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IF_in_ifStatement556 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ifStat_in_ifStatement558 = new BitSet(new long[]{0x0000000000000208L});
    public static final BitSet FOLLOW_elseIfStat_in_ifStatement562 = new BitSet(new long[]{0x0000000000000208L});
    public static final BitSet FOLLOW_elseStat_in_ifStatement568 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_ifStat588 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_ifStat590 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_ifStat592 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_elseIfStat611 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_elseIfStat613 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_elseIfStat615 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_elseStat634 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_block_in_elseStat636 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_For_in_forStatement667 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_forStatement669 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_forStatement673 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_forStatement675 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_While_in_whileStatement700 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_whileStatement702 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_whileStatement704 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_LIST_in_idList731 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_idList734 = new BitSet(new long[]{0x0000000800000008L});
    public static final BitSet FOLLOW_EXP_LIST_in_exprList764 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_exprList767 = new BitSet(new long[]{0xF7FC000022078008L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_EXP_PAIR_in_exprPair790 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_exprPair794 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_exprPair798 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_MAP_in_exprMap826 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprPair_in_exprMap829 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_TERNARY_in_expression854 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression858 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_expression862 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_expression866 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_In_in_expression877 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression881 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_expression885 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RangeE_in_expression917 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression921 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_expression925 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Range_in_expression951 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression955 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_expression959 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_XorWord_in_expression989 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression993 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_expression997 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Or_in_expression1023 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1027 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_expression1031 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BitOr_in_expression1058 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1062 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_expression1066 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_OrWord_in_expression1096 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1100 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_expression1104 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_And_in_expression1133 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1137 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_expression1141 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BitAnd_in_expression1168 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1172 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_expression1176 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_AndWord_in_expression1206 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1210 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_expression1214 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Equals_in_expression1242 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1246 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_expression1250 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEquals_in_expression1277 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1281 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_expression1285 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GTEquals_in_expression1312 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1316 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_expression1320 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LTEquals_in_expression1347 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1351 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_expression1355 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GT_in_expression1382 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1386 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_expression1390 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LT_in_expression1418 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1422 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_expression1426 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Add_in_expression1454 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1458 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_expression1462 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Subtract_in_expression1490 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1494 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_expression1498 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Multiply_in_expression1526 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1530 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_expression1534 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Divide_in_expression1562 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1566 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_expression1570 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Modulus_in_expression1601 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1605 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_expression1609 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Pow_in_expression1637 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1641 = new BitSet(new long[]{0xF7FC000022078000L,0x000000000001FCFFL});
    public static final BitSet FOLLOW_expression_in_expression1645 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_UNARY_PLUS_in_expression1673 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1677 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_UNARY_MIN_in_expression1714 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1718 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEGATE_in_expression1753 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1757 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Integer_in_expression1796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Number_in_expression1872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Bool_in_expression1950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Null_in_expression2031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NaN_in_expression2085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Infinity_in_expression2142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_expression2194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LIST_in_list2258 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprList_in_list2260 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MAP_in_map2283 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprMap_in_map2285 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2308 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_functionCall_in_lookup2310 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2314 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2326 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_list_in_lookup2328 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2332 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2352 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_map_in_lookup2354 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2358 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2381 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_lookup2383 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2387 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2401 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_lookup2403 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2407 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2421 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_String_in_lookup2423 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2427 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TAILS_in_indexes2466 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_tail_in_indexes2471 = new BitSet(new long[]{0x0000000000E00008L});
    public static final BitSet FOLLOW_INDEX_in_tail2496 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_tail2498 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ATTRIBUTE_in_tail2517 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_tail2519 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CALL_in_tail2534 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_tail2536 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_tail2538 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Var_in_variableDef2562 = new BitSet(new long[]{0x0000000000000002L});

}