// $ANTLR 3.2 Sep 23, 2009 12:02:23 grammar/PlazmaScriptWalker.g 2016-08-17 18:31:07

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BLOCK", "RETURN", "STATEMENTS", "ASSIGNMENT", "FUNC_CALL", "EXP", "EXP_PAIR", "EXP_MAP", "EXP_LIST", "ID_LIST", "IF", "TERNARY", "UNARY_PLUS", "UNARY_MIN", "NEGATE", "FUNCTION", "INDEXES", "INDEX", "ATTRIBUTE", "CALL", "TAIL", "TAILS", "MAP", "LIST", "SET", "LOOKUP", "BREAK", "CONTINUE", "Return", "Break", "Continue", "Identifier", "Println", "Print", "Assert", "Date", "DateTime", "List", "Set", "If", "Else", "Var", "Def", "For", "While", "In", "RangeE", "Range", "Integer", "Number", "Bool", "Null", "NaN", "Infinity", "String", "XorWord", "Or", "BitOr", "OrWord", "And", "BitAnd", "AndWord", "Equals", "NEquals", "GTEquals", "LTEquals", "Pow", "Not", "NotWord", "GT", "LT", "Add", "Subtract", "Multiply", "Divide", "Modulus", "OBrace", "CBrace", "OBracket", "CBracket", "OParen", "CParen", "SColon", "Assign", "Comma", "QMark", "Colon", "DecimalNumeral", "DecimalFloatingPoint", "Digits", "ExponentPart", "ContextIdentifier", "Digit", "Comment", "Space", "ExponentIndicator", "SignedInteger", "Sign", "NonZeroDigit", "'.'"
    };
    public static final int FUNCTION=19;
    public static final int LT=74;
    public static final int TERNARY=15;
    public static final int EXP_LIST=12;
    public static final int DateTime=40;
    public static final int Date=39;
    public static final int EOF=-1;
    public static final int QMark=89;
    public static final int NotWord=72;
    public static final int BREAK=30;
    public static final int Identifier=35;
    public static final int UNARY_PLUS=16;
    public static final int FUNC_CALL=8;
    public static final int CParen=85;
    public static final int Comment=97;
    public static final int EXP=9;
    public static final int Digits=93;
    public static final int CBrace=81;
    public static final int RETURN=5;
    public static final int ExponentPart=94;
    public static final int ExponentIndicator=99;
    public static final int Sign=101;
    public static final int OrWord=62;
    public static final int Null=55;
    public static final int DecimalNumeral=91;
    public static final int CBracket=83;
    public static final int ContextIdentifier=95;
    public static final int Println=36;
    public static final int Bool=54;
    public static final int Modulus=79;
    public static final int Colon=90;
    public static final int AndWord=65;
    public static final int LIST=27;
    public static final int Def=46;
    public static final int RangeE=50;
    public static final int LOOKUP=29;
    public static final int Range=51;
    public static final int Break=33;
    public static final int SignedInteger=100;
    public static final int BitOr=61;
    public static final int STATEMENTS=6;
    public static final int GT=73;
    public static final int CALL=23;
    public static final int DecimalFloatingPoint=92;
    public static final int Else=44;
    public static final int Equals=66;
    public static final int Var=45;
    public static final int XorWord=59;
    public static final int OParen=84;
    public static final int Assert=38;
    public static final int ATTRIBUTE=22;
    public static final int While=48;
    public static final int ID_LIST=13;
    public static final int Add=75;
    public static final int Set=42;
    public static final int TAIL=24;
    public static final int IF=14;
    public static final int Space=98;
    public static final int INDEX=21;
    public static final int Assign=87;
    public static final int EXP_MAP=11;
    public static final int NaN=56;
    public static final int Number=53;
    public static final int CONTINUE=31;
    public static final int T__103=103;
    public static final int Print=37;
    public static final int GTEquals=68;
    public static final int String=58;
    public static final int Or=60;
    public static final int Return=32;
    public static final int If=43;
    public static final int And=63;
    public static final int In=49;
    public static final int NEquals=67;
    public static final int Continue=34;
    public static final int Subtract=76;
    public static final int EXP_PAIR=10;
    public static final int BitAnd=64;
    public static final int Multiply=77;
    public static final int OBrace=80;
    public static final int INDEXES=20;
    public static final int NEGATE=18;
    public static final int SET=28;
    public static final int Digit=96;
    public static final int For=47;
    public static final int Divide=78;
    public static final int List=41;
    public static final int TAILS=25;
    public static final int SColon=86;
    public static final int OBracket=82;
    public static final int NonZeroDigit=102;
    public static final int BLOCK=4;
    public static final int MAP=26;
    public static final int Not=71;
    public static final int UNARY_MIN=17;
    public static final int ASSIGNMENT=7;
    public static final int Infinity=57;
    public static final int Comma=88;
    public static final int Integer=52;
    public static final int Pow=70;
    public static final int LTEquals=69;

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
    // grammar/PlazmaScriptWalker.g:80:1: functionCall returns [LNode node] : ( ^( FUNC_CALL Identifier ( exprList )? ) | ^( FUNC_CALL Println ( expression )? ) | ^( FUNC_CALL Print expression ) | ^( FUNC_CALL Assert expression ) | ^( FUNC_CALL Date ( exprList )? ) | ^( FUNC_CALL DateTime ( exprList )? ) | ^( FUNC_CALL List ( exprList )? ) | ^( FUNC_CALL Set ( exprList )? ) );
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


        try {
            // grammar/PlazmaScriptWalker.g:81:3: ( ^( FUNC_CALL Identifier ( exprList )? ) | ^( FUNC_CALL Println ( expression )? ) | ^( FUNC_CALL Print expression ) | ^( FUNC_CALL Assert expression ) | ^( FUNC_CALL Date ( exprList )? ) | ^( FUNC_CALL DateTime ( exprList )? ) | ^( FUNC_CALL List ( exprList )? ) | ^( FUNC_CALL Set ( exprList )? ) )
            int alt12=8;
            alt12 = dfa12.predict(input);
            switch (alt12) {
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
                    // grammar/PlazmaScriptWalker.g:87:6: ^( FUNC_CALL List ( exprList )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall461); 

                    match(input, Token.DOWN, null); 
                    match(input,List,FOLLOW_List_in_functionCall463); 
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
                    node = new ListNode(exprList21);

                    }
                    break;
                case 8 :
                    // grammar/PlazmaScriptWalker.g:88:6: ^( FUNC_CALL Set ( exprList )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall483); 

                    match(input, Token.DOWN, null); 
                    match(input,Set,FOLLOW_Set_in_functionCall485); 
                    // grammar/PlazmaScriptWalker.g:88:22: ( exprList )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==EXP_LIST) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:88:22: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall487);
                            exprList22=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new SetNode(exprList22);

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
    // grammar/PlazmaScriptWalker.g:91:1: ifStatement returns [LNode node] : ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? ) ;
    public final LNode ifStatement() throws RecognitionException {
        LNode node = null;

        IfNode ifNode = new IfNode();
        try {
            // grammar/PlazmaScriptWalker.g:94:3: ( ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? ) )
            // grammar/PlazmaScriptWalker.g:94:6: ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? )
            {
            match(input,IF,FOLLOW_IF_in_ifStatement532); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_ifStat_in_ifStatement534);
            ifStat(ifNode);

            state._fsp--;

            // grammar/PlazmaScriptWalker.g:94:26: ( elseIfStat[ifNode] )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==EXP) ) {
                    int LA13_1 = input.LA(2);

                    if ( (LA13_1==DOWN) ) {
                        int LA13_3 = input.LA(3);

                        if ( ((LA13_3>=TERNARY && LA13_3<=NEGATE)||LA13_3==LOOKUP||(LA13_3>=In && LA13_3<=Infinity)||(LA13_3>=XorWord && LA13_3<=Pow)||(LA13_3>=GT && LA13_3<=Modulus)) ) {
                            alt13=1;
                        }


                    }


                }


                switch (alt13) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:94:27: elseIfStat[ifNode]
            	    {
            	    pushFollow(FOLLOW_elseIfStat_in_ifStatement538);
            	    elseIfStat(ifNode);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            // grammar/PlazmaScriptWalker.g:94:48: ( elseStat[ifNode] )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==EXP) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:94:49: elseStat[ifNode]
                    {
                    pushFollow(FOLLOW_elseStat_in_ifStatement544);
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
    // grammar/PlazmaScriptWalker.g:97:1: ifStat[IfNode parent] : ^( EXP expression block ) ;
    public final void ifStat(IfNode parent) throws RecognitionException {
        LNode expression23 = null;

        LNode block24 = null;


        try {
            // grammar/PlazmaScriptWalker.g:98:3: ( ^( EXP expression block ) )
            // grammar/PlazmaScriptWalker.g:98:6: ^( EXP expression block )
            {
            match(input,EXP,FOLLOW_EXP_in_ifStat564); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_ifStat566);
            expression23=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_ifStat568);
            block24=block();

            state._fsp--;


            match(input, Token.UP, null); 
            parent.addChoice(expression23, block24);

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
    // grammar/PlazmaScriptWalker.g:101:1: elseIfStat[IfNode parent] : ^( EXP expression block ) ;
    public final void elseIfStat(IfNode parent) throws RecognitionException {
        LNode expression25 = null;

        LNode block26 = null;


        try {
            // grammar/PlazmaScriptWalker.g:102:3: ( ^( EXP expression block ) )
            // grammar/PlazmaScriptWalker.g:102:6: ^( EXP expression block )
            {
            match(input,EXP,FOLLOW_EXP_in_elseIfStat587); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_elseIfStat589);
            expression25=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_elseIfStat591);
            block26=block();

            state._fsp--;


            match(input, Token.UP, null); 
            parent.addChoice(expression25, block26);

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
    // grammar/PlazmaScriptWalker.g:105:1: elseStat[IfNode parent] : ^( EXP block ) ;
    public final void elseStat(IfNode parent) throws RecognitionException {
        LNode block27 = null;


        try {
            // grammar/PlazmaScriptWalker.g:106:3: ( ^( EXP block ) )
            // grammar/PlazmaScriptWalker.g:106:6: ^( EXP block )
            {
            match(input,EXP,FOLLOW_EXP_in_elseStat610); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_block_in_elseStat612);
            block27=block();

            state._fsp--;


            match(input, Token.UP, null); 
            parent.addChoice(new BooleanNode(true), block27);

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
    // grammar/PlazmaScriptWalker.g:113:1: forStatement returns [LNode node] : ^( For Identifier a= expression block ) ;
    public final LNode forStatement() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier28=null;
        LNode a = null;

        LNode block29 = null;


        try {
            // grammar/PlazmaScriptWalker.g:114:3: ( ^( For Identifier a= expression block ) )
            // grammar/PlazmaScriptWalker.g:114:6: ^( For Identifier a= expression block )
            {
            match(input,For,FOLLOW_For_in_forStatement643); 

            match(input, Token.DOWN, null); 
            Identifier28=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_forStatement645); 
            pushFollow(FOLLOW_expression_in_forStatement649);
            a=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_forStatement651);
            block29=block();

            state._fsp--;


            match(input, Token.UP, null); 
            node = new ForStatementNode2((Identifier28!=null?Identifier28.getText():null), a, block29, currentScope);

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
    // grammar/PlazmaScriptWalker.g:118:1: whileStatement returns [LNode node] : ^( While expression block ) ;
    public final LNode whileStatement() throws RecognitionException {
        LNode node = null;

        LNode expression30 = null;

        LNode block31 = null;


        try {
            // grammar/PlazmaScriptWalker.g:119:3: ( ^( While expression block ) )
            // grammar/PlazmaScriptWalker.g:119:6: ^( While expression block )
            {
            match(input,While,FOLLOW_While_in_whileStatement676); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_whileStatement678);
            expression30=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_whileStatement680);
            block31=block();

            state._fsp--;


            match(input, Token.UP, null); 
            node = new WhileStatementNode(expression30, block31);

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
    // grammar/PlazmaScriptWalker.g:122:1: idList returns [java.util.List<String> i] : ^( ID_LIST ( Identifier )+ ) ;
    public final java.util.List<String> idList() throws RecognitionException {
        java.util.List<String> i = null;

        CommonTree Identifier32=null;

        i = new java.util.ArrayList<String>();
        try {
            // grammar/PlazmaScriptWalker.g:124:3: ( ^( ID_LIST ( Identifier )+ ) )
            // grammar/PlazmaScriptWalker.g:124:6: ^( ID_LIST ( Identifier )+ )
            {
            match(input,ID_LIST,FOLLOW_ID_LIST_in_idList707); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:124:16: ( Identifier )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==Identifier) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:124:17: Identifier
            	    {
            	    Identifier32=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_idList710); 
            	    i.add((Identifier32!=null?Identifier32.getText():null));

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
        return i;
    }
    // $ANTLR end "idList"


    // $ANTLR start "exprList"
    // grammar/PlazmaScriptWalker.g:127:1: exprList returns [java.util.List<LNode> e] : ^( EXP_LIST ( expression )+ ) ;
    public final java.util.List<LNode> exprList() throws RecognitionException {
        java.util.List<LNode> e = null;

        LNode expression33 = null;


        e = new java.util.ArrayList<LNode>();
        try {
            // grammar/PlazmaScriptWalker.g:129:3: ( ^( EXP_LIST ( expression )+ ) )
            // grammar/PlazmaScriptWalker.g:129:6: ^( EXP_LIST ( expression )+ )
            {
            match(input,EXP_LIST,FOLLOW_EXP_LIST_in_exprList740); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:129:17: ( expression )+
            int cnt16=0;
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>=TERNARY && LA16_0<=NEGATE)||LA16_0==LOOKUP||(LA16_0>=In && LA16_0<=Infinity)||(LA16_0>=XorWord && LA16_0<=Pow)||(LA16_0>=GT && LA16_0<=Modulus)) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:129:18: expression
            	    {
            	    pushFollow(FOLLOW_expression_in_exprList743);
            	    expression33=expression();

            	    state._fsp--;

            	    e.add(expression33);

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
    // $ANTLR end "exprList"


    // $ANTLR start "exprPair"
    // grammar/PlazmaScriptWalker.g:131:1: exprPair returns [PairNode node] : ^( EXP_PAIR k= expression v= expression ) ;
    public final PairNode exprPair() throws RecognitionException {
        PairNode node = null;

        LNode k = null;

        LNode v = null;


        try {
            // grammar/PlazmaScriptWalker.g:132:3: ( ^( EXP_PAIR k= expression v= expression ) )
            // grammar/PlazmaScriptWalker.g:132:6: ^( EXP_PAIR k= expression v= expression )
            {
            match(input,EXP_PAIR,FOLLOW_EXP_PAIR_in_exprPair766); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_exprPair770);
            k=expression();

            state._fsp--;

            pushFollow(FOLLOW_expression_in_exprPair774);
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
    // grammar/PlazmaScriptWalker.g:135:1: exprMap returns [java.util.List<PairNode> e] : ^( EXP_MAP ( exprPair )+ ) ;
    public final java.util.List<PairNode> exprMap() throws RecognitionException {
        java.util.List<PairNode> e = null;

        PairNode exprPair34 = null;


        e = new java.util.ArrayList<PairNode>();
        try {
            // grammar/PlazmaScriptWalker.g:137:3: ( ^( EXP_MAP ( exprPair )+ ) )
            // grammar/PlazmaScriptWalker.g:137:6: ^( EXP_MAP ( exprPair )+ )
            {
            match(input,EXP_MAP,FOLLOW_EXP_MAP_in_exprMap802); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:137:16: ( exprPair )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==EXP_PAIR) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:137:17: exprPair
            	    {
            	    pushFollow(FOLLOW_exprPair_in_exprMap805);
            	    exprPair34=exprPair();

            	    state._fsp--;

            	    e.add(exprPair34);

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
    // $ANTLR end "exprMap"


    // $ANTLR start "expression"
    // grammar/PlazmaScriptWalker.g:141:1: expression returns [LNode node] : ( ^( TERNARY a= expression b= expression c= expression ) | ^( In a= expression b= expression ) | ^( RangeE a= expression b= expression ) | ^( Range a= expression b= expression ) | ^( 'xor' a= expression b= expression ) | ^( '||' a= expression b= expression ) | ^( '|' a= expression b= expression ) | ^( 'or' a= expression b= expression ) | ^( '&&' a= expression b= expression ) | ^( '&' a= expression b= expression ) | ^( 'and' a= expression b= expression ) | ^( '==' a= expression b= expression ) | ^( '!=' a= expression b= expression ) | ^( '>=' a= expression b= expression ) | ^( '<=' a= expression b= expression ) | ^( '>' a= expression b= expression ) | ^( '<' a= expression b= expression ) | ^( '+' a= expression b= expression ) | ^( '-' a= expression b= expression ) | ^( '*' a= expression b= expression ) | ^( '/' a= expression b= expression ) | ^( '%' a= expression b= expression ) | ^( '^' a= expression b= expression ) | ^( UNARY_PLUS a= expression ) | ^( UNARY_MIN a= expression ) | ^( NEGATE a= expression ) | Integer | Number | Bool | Null | NaN | Infinity | lookup );
    public final LNode expression() throws RecognitionException {
        LNode node = null;

        CommonTree Integer35=null;
        CommonTree Number36=null;
        CommonTree Bool37=null;
        LNode a = null;

        LNode b = null;

        LNode c = null;

        LNode lookup38 = null;


        try {
            // grammar/PlazmaScriptWalker.g:142:3: ( ^( TERNARY a= expression b= expression c= expression ) | ^( In a= expression b= expression ) | ^( RangeE a= expression b= expression ) | ^( Range a= expression b= expression ) | ^( 'xor' a= expression b= expression ) | ^( '||' a= expression b= expression ) | ^( '|' a= expression b= expression ) | ^( 'or' a= expression b= expression ) | ^( '&&' a= expression b= expression ) | ^( '&' a= expression b= expression ) | ^( 'and' a= expression b= expression ) | ^( '==' a= expression b= expression ) | ^( '!=' a= expression b= expression ) | ^( '>=' a= expression b= expression ) | ^( '<=' a= expression b= expression ) | ^( '>' a= expression b= expression ) | ^( '<' a= expression b= expression ) | ^( '+' a= expression b= expression ) | ^( '-' a= expression b= expression ) | ^( '*' a= expression b= expression ) | ^( '/' a= expression b= expression ) | ^( '%' a= expression b= expression ) | ^( '^' a= expression b= expression ) | ^( UNARY_PLUS a= expression ) | ^( UNARY_MIN a= expression ) | ^( NEGATE a= expression ) | Integer | Number | Bool | Null | NaN | Infinity | lookup )
            int alt18=33;
            switch ( input.LA(1) ) {
            case TERNARY:
                {
                alt18=1;
                }
                break;
            case In:
                {
                alt18=2;
                }
                break;
            case RangeE:
                {
                alt18=3;
                }
                break;
            case Range:
                {
                alt18=4;
                }
                break;
            case XorWord:
                {
                alt18=5;
                }
                break;
            case Or:
                {
                alt18=6;
                }
                break;
            case BitOr:
                {
                alt18=7;
                }
                break;
            case OrWord:
                {
                alt18=8;
                }
                break;
            case And:
                {
                alt18=9;
                }
                break;
            case BitAnd:
                {
                alt18=10;
                }
                break;
            case AndWord:
                {
                alt18=11;
                }
                break;
            case Equals:
                {
                alt18=12;
                }
                break;
            case NEquals:
                {
                alt18=13;
                }
                break;
            case GTEquals:
                {
                alt18=14;
                }
                break;
            case LTEquals:
                {
                alt18=15;
                }
                break;
            case GT:
                {
                alt18=16;
                }
                break;
            case LT:
                {
                alt18=17;
                }
                break;
            case Add:
                {
                alt18=18;
                }
                break;
            case Subtract:
                {
                alt18=19;
                }
                break;
            case Multiply:
                {
                alt18=20;
                }
                break;
            case Divide:
                {
                alt18=21;
                }
                break;
            case Modulus:
                {
                alt18=22;
                }
                break;
            case Pow:
                {
                alt18=23;
                }
                break;
            case UNARY_PLUS:
                {
                alt18=24;
                }
                break;
            case UNARY_MIN:
                {
                alt18=25;
                }
                break;
            case NEGATE:
                {
                alt18=26;
                }
                break;
            case Integer:
                {
                alt18=27;
                }
                break;
            case Number:
                {
                alt18=28;
                }
                break;
            case Bool:
                {
                alt18=29;
                }
                break;
            case Null:
                {
                alt18=30;
                }
                break;
            case NaN:
                {
                alt18=31;
                }
                break;
            case Infinity:
                {
                alt18=32;
                }
                break;
            case LOOKUP:
                {
                alt18=33;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:142:6: ^( TERNARY a= expression b= expression c= expression )
                    {
                    match(input,TERNARY,FOLLOW_TERNARY_in_expression830); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression834);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression838);
                    b=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression842);
                    c=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new TernaryNode(a, b, c);

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:143:6: ^( In a= expression b= expression )
                    {
                    match(input,In,FOLLOW_In_in_expression853); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression857);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression861);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new InNode(a, b);

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:145:6: ^( RangeE a= expression b= expression )
                    {
                    match(input,RangeE,FOLLOW_RangeE_in_expression893); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression897);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression901);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new RangeENode(a, b);

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:146:6: ^( Range a= expression b= expression )
                    {
                    match(input,Range,FOLLOW_Range_in_expression927); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression931);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression935);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new RangeNode(a, b);

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:148:6: ^( 'xor' a= expression b= expression )
                    {
                    match(input,XorWord,FOLLOW_XorWord_in_expression965); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression969);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression973);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new XorNode(a, b);

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScriptWalker.g:149:6: ^( '||' a= expression b= expression )
                    {
                    match(input,Or,FOLLOW_Or_in_expression999); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1003);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1007);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new OrNode(a, b);

                    }
                    break;
                case 7 :
                    // grammar/PlazmaScriptWalker.g:150:6: ^( '|' a= expression b= expression )
                    {
                    match(input,BitOr,FOLLOW_BitOr_in_expression1034); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1038);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1042);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new BitOrNode(a, b);

                    }
                    break;
                case 8 :
                    // grammar/PlazmaScriptWalker.g:151:6: ^( 'or' a= expression b= expression )
                    {
                    match(input,OrWord,FOLLOW_OrWord_in_expression1072); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1076);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1080);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new OrNode(a, b);

                    }
                    break;
                case 9 :
                    // grammar/PlazmaScriptWalker.g:152:6: ^( '&&' a= expression b= expression )
                    {
                    match(input,And,FOLLOW_And_in_expression1109); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1113);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1117);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AndNode(a, b);

                    }
                    break;
                case 10 :
                    // grammar/PlazmaScriptWalker.g:153:6: ^( '&' a= expression b= expression )
                    {
                    match(input,BitAnd,FOLLOW_BitAnd_in_expression1144); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1148);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1152);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new BitAndNode(a, b);

                    }
                    break;
                case 11 :
                    // grammar/PlazmaScriptWalker.g:154:6: ^( 'and' a= expression b= expression )
                    {
                    match(input,AndWord,FOLLOW_AndWord_in_expression1182); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1186);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1190);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AndNode(a, b);

                    }
                    break;
                case 12 :
                    // grammar/PlazmaScriptWalker.g:155:6: ^( '==' a= expression b= expression )
                    {
                    match(input,Equals,FOLLOW_Equals_in_expression1218); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1222);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1226);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new EQNode(a, b);

                    }
                    break;
                case 13 :
                    // grammar/PlazmaScriptWalker.g:156:6: ^( '!=' a= expression b= expression )
                    {
                    match(input,NEquals,FOLLOW_NEquals_in_expression1253); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1257);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1261);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new NENode(a, b);

                    }
                    break;
                case 14 :
                    // grammar/PlazmaScriptWalker.g:157:6: ^( '>=' a= expression b= expression )
                    {
                    match(input,GTEquals,FOLLOW_GTEquals_in_expression1288); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1292);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1296);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new GTENode(a, b);

                    }
                    break;
                case 15 :
                    // grammar/PlazmaScriptWalker.g:158:6: ^( '<=' a= expression b= expression )
                    {
                    match(input,LTEquals,FOLLOW_LTEquals_in_expression1323); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1327);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1331);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new LTENode(a, b);

                    }
                    break;
                case 16 :
                    // grammar/PlazmaScriptWalker.g:159:6: ^( '>' a= expression b= expression )
                    {
                    match(input,GT,FOLLOW_GT_in_expression1358); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1362);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1366);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new GTNode(a, b);

                    }
                    break;
                case 17 :
                    // grammar/PlazmaScriptWalker.g:160:6: ^( '<' a= expression b= expression )
                    {
                    match(input,LT,FOLLOW_LT_in_expression1394); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1398);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1402);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new LTNode(a, b);

                    }
                    break;
                case 18 :
                    // grammar/PlazmaScriptWalker.g:161:6: ^( '+' a= expression b= expression )
                    {
                    match(input,Add,FOLLOW_Add_in_expression1430); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1434);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1438);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AddNode(a, b);

                    }
                    break;
                case 19 :
                    // grammar/PlazmaScriptWalker.g:162:6: ^( '-' a= expression b= expression )
                    {
                    match(input,Subtract,FOLLOW_Subtract_in_expression1466); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1470);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1474);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new SubNode(a, b);

                    }
                    break;
                case 20 :
                    // grammar/PlazmaScriptWalker.g:163:6: ^( '*' a= expression b= expression )
                    {
                    match(input,Multiply,FOLLOW_Multiply_in_expression1502); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1506);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1510);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new MulNode(a, b);

                    }
                    break;
                case 21 :
                    // grammar/PlazmaScriptWalker.g:164:6: ^( '/' a= expression b= expression )
                    {
                    match(input,Divide,FOLLOW_Divide_in_expression1538); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1542);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1546);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new DivNode(a, b);

                    }
                    break;
                case 22 :
                    // grammar/PlazmaScriptWalker.g:166:6: ^( '%' a= expression b= expression )
                    {
                    match(input,Modulus,FOLLOW_Modulus_in_expression1577); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1581);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1585);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new ModNode(a, b);

                    }
                    break;
                case 23 :
                    // grammar/PlazmaScriptWalker.g:167:6: ^( '^' a= expression b= expression )
                    {
                    match(input,Pow,FOLLOW_Pow_in_expression1613); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1617);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1621);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new PowNode(a, b);

                    }
                    break;
                case 24 :
                    // grammar/PlazmaScriptWalker.g:168:6: ^( UNARY_PLUS a= expression )
                    {
                    match(input,UNARY_PLUS,FOLLOW_UNARY_PLUS_in_expression1649); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1653);
                    a=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new UnaryPlusNode(a);

                    }
                    break;
                case 25 :
                    // grammar/PlazmaScriptWalker.g:169:6: ^( UNARY_MIN a= expression )
                    {
                    match(input,UNARY_MIN,FOLLOW_UNARY_MIN_in_expression1690); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1694);
                    a=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new UnaryMinusNode(a);

                    }
                    break;
                case 26 :
                    // grammar/PlazmaScriptWalker.g:170:6: ^( NEGATE a= expression )
                    {
                    match(input,NEGATE,FOLLOW_NEGATE_in_expression1729); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1733);
                    a=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new NotNode(a);

                    }
                    break;
                case 27 :
                    // grammar/PlazmaScriptWalker.g:172:6: Integer
                    {
                    Integer35=(CommonTree)match(input,Integer,FOLLOW_Integer_in_expression1772); 
                    node = new IntegerNode((Integer35!=null?Integer35.getText():null));

                    }
                    break;
                case 28 :
                    // grammar/PlazmaScriptWalker.g:173:6: Number
                    {
                    Number36=(CommonTree)match(input,Number,FOLLOW_Number_in_expression1848); 
                    node = new NumberNode((Number36!=null?Number36.getText():null));

                    }
                    break;
                case 29 :
                    // grammar/PlazmaScriptWalker.g:174:6: Bool
                    {
                    Bool37=(CommonTree)match(input,Bool,FOLLOW_Bool_in_expression1926); 
                    node = new BooleanNode((Bool37!=null?Bool37.getText():null));

                    }
                    break;
                case 30 :
                    // grammar/PlazmaScriptWalker.g:175:6: Null
                    {
                    match(input,Null,FOLLOW_Null_in_expression2007); 
                    node = new NullNode();

                    }
                    break;
                case 31 :
                    // grammar/PlazmaScriptWalker.g:176:6: NaN
                    {
                    match(input,NaN,FOLLOW_NaN_in_expression2061); 
                    node = new NaNNode();

                    }
                    break;
                case 32 :
                    // grammar/PlazmaScriptWalker.g:177:6: Infinity
                    {
                    match(input,Infinity,FOLLOW_Infinity_in_expression2118); 
                    node = new InfinityNode();

                    }
                    break;
                case 33 :
                    // grammar/PlazmaScriptWalker.g:178:6: lookup
                    {
                    pushFollow(FOLLOW_lookup_in_expression2170);
                    lookup38=lookup();

                    state._fsp--;

                    node = lookup38;

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
    // grammar/PlazmaScriptWalker.g:181:1: list returns [LNode node] : ^( LIST ( exprList )? ) ;
    public final LNode list() throws RecognitionException {
        LNode node = null;

        java.util.List<LNode> exprList39 = null;


        try {
            // grammar/PlazmaScriptWalker.g:182:3: ( ^( LIST ( exprList )? ) )
            // grammar/PlazmaScriptWalker.g:182:6: ^( LIST ( exprList )? )
            {
            match(input,LIST,FOLLOW_LIST_in_list2234); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // grammar/PlazmaScriptWalker.g:182:13: ( exprList )?
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==EXP_LIST) ) {
                    alt19=1;
                }
                switch (alt19) {
                    case 1 :
                        // grammar/PlazmaScriptWalker.g:182:13: exprList
                        {
                        pushFollow(FOLLOW_exprList_in_list2236);
                        exprList39=exprList();

                        state._fsp--;


                        }
                        break;

                }


                match(input, Token.UP, null); 
            }
            node = new ListNode(exprList39);

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
    // grammar/PlazmaScriptWalker.g:185:1: map returns [LNode node] : ^( MAP ( exprMap )? ) ;
    public final LNode map() throws RecognitionException {
        LNode node = null;

        java.util.List<PairNode> exprMap40 = null;


        try {
            // grammar/PlazmaScriptWalker.g:186:3: ( ^( MAP ( exprMap )? ) )
            // grammar/PlazmaScriptWalker.g:186:6: ^( MAP ( exprMap )? )
            {
            match(input,MAP,FOLLOW_MAP_in_map2259); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // grammar/PlazmaScriptWalker.g:186:12: ( exprMap )?
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==EXP_MAP) ) {
                    alt20=1;
                }
                switch (alt20) {
                    case 1 :
                        // grammar/PlazmaScriptWalker.g:186:12: exprMap
                        {
                        pushFollow(FOLLOW_exprMap_in_map2261);
                        exprMap40=exprMap();

                        state._fsp--;


                        }
                        break;

                }


                match(input, Token.UP, null); 
            }
            node = new MapNode(exprMap40);

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
    // grammar/PlazmaScriptWalker.g:189:1: lookup returns [LNode node] : ( ^( LOOKUP functionCall (i= indexes )? ) | ^( LOOKUP list (i= indexes )? ) | ^( LOOKUP map (i= indexes )? ) | ^( LOOKUP expression (i= indexes )? ) | ^( LOOKUP Identifier (i= indexes )? ) | ^( LOOKUP String (i= indexes )? ) );
    public final LNode lookup() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier45=null;
        CommonTree String46=null;
        java.util.List<LNode> i = null;

        LNode functionCall41 = null;

        LNode list42 = null;

        LNode map43 = null;

        LNode expression44 = null;


        try {
            // grammar/PlazmaScriptWalker.g:190:3: ( ^( LOOKUP functionCall (i= indexes )? ) | ^( LOOKUP list (i= indexes )? ) | ^( LOOKUP map (i= indexes )? ) | ^( LOOKUP expression (i= indexes )? ) | ^( LOOKUP Identifier (i= indexes )? ) | ^( LOOKUP String (i= indexes )? ) )
            int alt27=6;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==LOOKUP) ) {
                int LA27_1 = input.LA(2);

                if ( (LA27_1==DOWN) ) {
                    switch ( input.LA(3) ) {
                    case Identifier:
                        {
                        alt27=5;
                        }
                        break;
                    case String:
                        {
                        alt27=6;
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
                        alt27=4;
                        }
                        break;
                    case FUNC_CALL:
                        {
                        alt27=1;
                        }
                        break;
                    case MAP:
                        {
                        alt27=3;
                        }
                        break;
                    case LIST:
                        {
                        alt27=2;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 27, 2, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 27, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:190:6: ^( LOOKUP functionCall (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2284); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_functionCall_in_lookup2286);
                    functionCall41=functionCall();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:190:29: (i= indexes )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==TAILS) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:190:29: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2290);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(functionCall41, i) : functionCall41;

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:191:6: ^( LOOKUP list (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2302); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_list_in_lookup2304);
                    list42=list();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:191:21: (i= indexes )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==TAILS) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:191:21: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2308);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(list42, i) : list42;

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:192:6: ^( LOOKUP map (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2328); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_map_in_lookup2330);
                    map43=map();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:192:20: (i= indexes )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==TAILS) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:192:20: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2334);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(map43, i) : map43;

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:193:6: ^( LOOKUP expression (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2357); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_lookup2359);
                    expression44=expression();

                    state._fsp--;

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
                            pushFollow(FOLLOW_indexes_in_lookup2363);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(expression44, i) : expression44;

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:194:6: ^( LOOKUP Identifier (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2377); 

                    match(input, Token.DOWN, null); 
                    Identifier45=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_lookup2379); 
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
                            pushFollow(FOLLOW_indexes_in_lookup2383);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(new IdentifierNode((Identifier45!=null?Identifier45.getText():null), currentScope, globalScope), i) : new IdentifierNode((Identifier45!=null?Identifier45.getText():null), currentScope, globalScope);

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScriptWalker.g:195:6: ^( LOOKUP String (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2397); 

                    match(input, Token.DOWN, null); 
                    String46=(CommonTree)match(input,String,FOLLOW_String_in_lookup2399); 
                    // grammar/PlazmaScriptWalker.g:195:23: (i= indexes )?
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==TAILS) ) {
                        alt26=1;
                    }
                    switch (alt26) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:195:23: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2403);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(new StringNode((String46!=null?String46.getText():null)), i) : new StringNode((String46!=null?String46.getText():null));

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
    // grammar/PlazmaScriptWalker.g:198:1: indexes returns [java.util.List<LNode> e] : ^( TAILS ( tail )+ ) ;
    public final java.util.List<LNode> indexes() throws RecognitionException {
        java.util.List<LNode> e = null;

        LNode tail47 = null;


        e = new java.util.ArrayList<LNode>();
        try {
            // grammar/PlazmaScriptWalker.g:201:3: ( ^( TAILS ( tail )+ ) )
            // grammar/PlazmaScriptWalker.g:201:8: ^( TAILS ( tail )+ )
            {
            match(input,TAILS,FOLLOW_TAILS_in_indexes2442); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:201:18: ( tail )+
            int cnt28=0;
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( ((LA28_0>=INDEX && LA28_0<=CALL)) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:201:19: tail
            	    {
            	    pushFollow(FOLLOW_tail_in_indexes2447);
            	    tail47=tail();

            	    state._fsp--;

            	    e.add(tail47);

            	    }
            	    break;

            	default :
            	    if ( cnt28 >= 1 ) break loop28;
                        EarlyExitException eee =
                            new EarlyExitException(28, input);
                        throw eee;
                }
                cnt28++;
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
    // grammar/PlazmaScriptWalker.g:205:1: tail returns [LNode node] : ( ^( INDEX expression ) | ^( ATTRIBUTE Identifier ) | ^( CALL Identifier ( exprList )? ) );
    public final LNode tail() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier49=null;
        CommonTree Identifier50=null;
        LNode expression48 = null;

        java.util.List<LNode> exprList51 = null;


        try {
            // grammar/PlazmaScriptWalker.g:206:2: ( ^( INDEX expression ) | ^( ATTRIBUTE Identifier ) | ^( CALL Identifier ( exprList )? ) )
            int alt30=3;
            switch ( input.LA(1) ) {
            case INDEX:
                {
                alt30=1;
                }
                break;
            case ATTRIBUTE:
                {
                alt30=2;
                }
                break;
            case CALL:
                {
                alt30=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }

            switch (alt30) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:206:4: ^( INDEX expression )
                    {
                    match(input,INDEX,FOLLOW_INDEX_in_tail2472); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_tail2474);
                    expression48=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = expression48;

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:207:4: ^( ATTRIBUTE Identifier )
                    {
                    match(input,ATTRIBUTE,FOLLOW_ATTRIBUTE_in_tail2493); 

                    match(input, Token.DOWN, null); 
                    Identifier49=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_tail2495); 

                    match(input, Token.UP, null); 
                    node = new StringNode((Identifier49!=null?Identifier49.getText():null));

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:208:4: ^( CALL Identifier ( exprList )? )
                    {
                    match(input,CALL,FOLLOW_CALL_in_tail2510); 

                    match(input, Token.DOWN, null); 
                    Identifier50=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_tail2512); 
                    // grammar/PlazmaScriptWalker.g:208:22: ( exprList )?
                    int alt29=2;
                    int LA29_0 = input.LA(1);

                    if ( (LA29_0==EXP_LIST) ) {
                        alt29=1;
                    }
                    switch (alt29) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:208:22: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_tail2514);
                            exprList51=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new MethodCallNode((Identifier50!=null?Identifier50.getText():null), exprList51, functions, globalScope);

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
    // grammar/PlazmaScriptWalker.g:211:1: variableDef returns [LNode node] : Var ;
    public final LNode variableDef() throws RecognitionException {
        LNode node = null;

        CommonTree Var52=null;

        try {
            // grammar/PlazmaScriptWalker.g:212:3: ( Var )
            // grammar/PlazmaScriptWalker.g:212:5: Var
            {
            Var52=(CommonTree)match(input,Var,FOLLOW_Var_in_variableDef2538); 
            node = new VariableDefNode((Var52!=null?Var52.getText():null), null);

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


    protected DFA12 dfa12 = new DFA12(this);
    static final String DFA12_eotS =
        "\13\uffff";
    static final String DFA12_eofS =
        "\13\uffff";
    static final String DFA12_minS =
        "\1\10\1\2\1\43\10\uffff";
    static final String DFA12_maxS =
        "\1\10\1\2\1\52\10\uffff";
    static final String DFA12_acceptS =
        "\3\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10";
    static final String DFA12_specialS =
        "\13\uffff}>";
    static final String[] DFA12_transitionS = {
            "\1\1",
            "\1\2",
            "\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
    static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
    static final short[][] DFA12_transition;

    static {
        int numStates = DFA12_transitionS.length;
        DFA12_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
        }
    }

    class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = DFA12_eot;
            this.eof = DFA12_eof;
            this.min = DFA12_min;
            this.max = DFA12_max;
            this.accept = DFA12_accept;
            this.special = DFA12_special;
            this.transition = DFA12_transition;
        }
        public String getDescription() {
            return "80:1: functionCall returns [LNode node] : ( ^( FUNC_CALL Identifier ( exprList )? ) | ^( FUNC_CALL Println ( expression )? ) | ^( FUNC_CALL Print expression ) | ^( FUNC_CALL Assert expression ) | ^( FUNC_CALL Date ( exprList )? ) | ^( FUNC_CALL DateTime ( exprList )? ) | ^( FUNC_CALL List ( exprList )? ) | ^( FUNC_CALL Set ( exprList )? ) );";
        }
    }
 

    public static final BitSet FOLLOW_block_in_walk50 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BLOCK_in_block81 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STATEMENTS_in_block84 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_block87 = new BitSet(new long[]{0x0001800620004188L});
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
    public static final BitSet FOLLOW_Identifier_in_assignment322 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_indexes_in_assignment324 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_assignment327 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall349 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_functionCall351 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall353 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall365 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Println_in_functionCall367 = new BitSet(new long[]{0xFBFE000022078008L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_functionCall369 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall382 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Print_in_functionCall384 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_functionCall386 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall401 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Assert_in_functionCall403 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_functionCall405 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall419 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Date_in_functionCall421 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall423 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall441 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_DateTime_in_functionCall443 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall445 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall461 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_List_in_functionCall463 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall465 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall483 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Set_in_functionCall485 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall487 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IF_in_ifStatement532 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ifStat_in_ifStatement534 = new BitSet(new long[]{0x0000000000000208L});
    public static final BitSet FOLLOW_elseIfStat_in_ifStatement538 = new BitSet(new long[]{0x0000000000000208L});
    public static final BitSet FOLLOW_elseStat_in_ifStatement544 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_ifStat564 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_ifStat566 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_ifStat568 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_elseIfStat587 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_elseIfStat589 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_elseIfStat591 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_elseStat610 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_block_in_elseStat612 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_For_in_forStatement643 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_forStatement645 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_forStatement649 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_forStatement651 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_While_in_whileStatement676 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_whileStatement678 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_whileStatement680 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_LIST_in_idList707 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_idList710 = new BitSet(new long[]{0x0000000800000008L});
    public static final BitSet FOLLOW_EXP_LIST_in_exprList740 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_exprList743 = new BitSet(new long[]{0xFBFE000022078008L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_EXP_PAIR_in_exprPair766 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_exprPair770 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_exprPair774 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_MAP_in_exprMap802 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprPair_in_exprMap805 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_TERNARY_in_expression830 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression834 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_expression838 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_expression842 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_In_in_expression853 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression857 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_expression861 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RangeE_in_expression893 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression897 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_expression901 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Range_in_expression927 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression931 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_expression935 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_XorWord_in_expression965 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression969 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_expression973 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Or_in_expression999 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1003 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_expression1007 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BitOr_in_expression1034 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1038 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_expression1042 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_OrWord_in_expression1072 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1076 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_expression1080 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_And_in_expression1109 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1113 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_expression1117 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BitAnd_in_expression1144 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1148 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_expression1152 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_AndWord_in_expression1182 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1186 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_expression1190 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Equals_in_expression1218 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1222 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_expression1226 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEquals_in_expression1253 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1257 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_expression1261 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GTEquals_in_expression1288 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1292 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_expression1296 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LTEquals_in_expression1323 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1327 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_expression1331 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GT_in_expression1358 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1362 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_expression1366 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LT_in_expression1394 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1398 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_expression1402 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Add_in_expression1430 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1434 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_expression1438 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Subtract_in_expression1466 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1470 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_expression1474 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Multiply_in_expression1502 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1506 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_expression1510 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Divide_in_expression1538 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1542 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_expression1546 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Modulus_in_expression1577 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1581 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_expression1585 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Pow_in_expression1613 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1617 = new BitSet(new long[]{0xFBFE000022078000L,0x000000000000FE7FL});
    public static final BitSet FOLLOW_expression_in_expression1621 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_UNARY_PLUS_in_expression1649 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1653 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_UNARY_MIN_in_expression1690 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1694 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEGATE_in_expression1729 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1733 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Integer_in_expression1772 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Number_in_expression1848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Bool_in_expression1926 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Null_in_expression2007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NaN_in_expression2061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Infinity_in_expression2118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_expression2170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LIST_in_list2234 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprList_in_list2236 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MAP_in_map2259 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprMap_in_map2261 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2284 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_functionCall_in_lookup2286 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2290 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2302 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_list_in_lookup2304 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2308 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2328 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_map_in_lookup2330 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2334 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2357 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_lookup2359 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2363 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2377 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_lookup2379 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2383 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2397 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_String_in_lookup2399 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2403 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TAILS_in_indexes2442 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_tail_in_indexes2447 = new BitSet(new long[]{0x0000000000E00008L});
    public static final BitSet FOLLOW_INDEX_in_tail2472 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_tail2474 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ATTRIBUTE_in_tail2493 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_tail2495 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CALL_in_tail2510 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_tail2512 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_tail2514 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Var_in_variableDef2538 = new BitSet(new long[]{0x0000000000000002L});

}