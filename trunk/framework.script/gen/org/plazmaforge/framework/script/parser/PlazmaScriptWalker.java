// $ANTLR 3.2 Sep 23, 2009 12:02:23 grammar/PlazmaScriptWalker.g 2016-09-06 15:40:02

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BLOCK", "RETURN", "STATEMENTS", "ASSIGNMENT", "DECLARE", "FUNC_CALL", "EXP", "EXP_PAIR", "EXP_MAP", "EXP_LIST", "ID_LIST", "IF", "TERNARY", "UNARY_PLUS", "UNARY_MIN", "NEGATE", "FUNCTION", "INDEXES", "INDEX", "ATTRIBUTE", "CALL", "TAIL", "TAILS", "MAP", "LIST", "SET", "LOOKUP", "BREAK", "CONTINUE", "Return", "Break", "Continue", "Identifier", "Println", "Print", "Assert", "Date", "DateTime", "Time", "Duration", "Period", "List", "Set", "If", "Else", "Var", "Def", "For", "While", "In", "RangeE", "Range", "Integer", "Number", "Bool", "Null", "NaN", "Infinity", "String", "XorWord", "Or", "BitOr", "OrWord", "And", "BitAnd", "AndWord", "Equals", "NEquals", "GTEquals", "LTEquals", "Pow", "Not", "NotWord", "GT", "LT", "Add", "Subtract", "Multiply", "Divide", "Modulus", "OBrace", "CBrace", "OBracket", "CBracket", "OParen", "CParen", "SColon", "Assign", "Comma", "QMark", "Colon", "DecimalNumeral", "DecimalFloatingPoint", "Digits", "ExponentPart", "ContextIdentifier", "Digit", "Comment", "Space", "ExponentIndicator", "SignedInteger", "Sign", "NonZeroDigit", "'.'"
    };
    public static final int FUNCTION=20;
    public static final int LT=78;
    public static final int TERNARY=16;
    public static final int EXP_LIST=13;
    public static final int DateTime=41;
    public static final int Date=40;
    public static final int EOF=-1;
    public static final int QMark=93;
    public static final int NotWord=76;
    public static final int BREAK=31;
    public static final int Identifier=36;
    public static final int UNARY_PLUS=17;
    public static final int FUNC_CALL=9;
    public static final int CParen=89;
    public static final int Comment=101;
    public static final int EXP=10;
    public static final int Digits=97;
    public static final int CBrace=85;
    public static final int RETURN=5;
    public static final int ExponentPart=98;
    public static final int ExponentIndicator=103;
    public static final int Sign=105;
    public static final int OrWord=66;
    public static final int Null=59;
    public static final int DecimalNumeral=95;
    public static final int CBracket=87;
    public static final int Period=44;
    public static final int ContextIdentifier=99;
    public static final int Println=37;
    public static final int Bool=58;
    public static final int Modulus=83;
    public static final int Time=42;
    public static final int AndWord=69;
    public static final int Colon=94;
    public static final int LIST=28;
    public static final int Def=50;
    public static final int RangeE=54;
    public static final int LOOKUP=30;
    public static final int Range=55;
    public static final int Break=34;
    public static final int SignedInteger=104;
    public static final int BitOr=65;
    public static final int STATEMENTS=6;
    public static final int GT=77;
    public static final int CALL=24;
    public static final int DecimalFloatingPoint=96;
    public static final int Else=48;
    public static final int Equals=70;
    public static final int Var=49;
    public static final int XorWord=63;
    public static final int OParen=88;
    public static final int Assert=39;
    public static final int ATTRIBUTE=23;
    public static final int While=52;
    public static final int ID_LIST=14;
    public static final int Add=79;
    public static final int Set=46;
    public static final int TAIL=25;
    public static final int IF=15;
    public static final int Space=102;
    public static final int INDEX=22;
    public static final int Assign=91;
    public static final int EXP_MAP=12;
    public static final int NaN=60;
    public static final int Number=57;
    public static final int CONTINUE=32;
    public static final int T__107=107;
    public static final int Print=38;
    public static final int GTEquals=72;
    public static final int String=62;
    public static final int Or=64;
    public static final int Return=33;
    public static final int If=47;
    public static final int And=67;
    public static final int In=53;
    public static final int NEquals=71;
    public static final int Continue=35;
    public static final int Subtract=80;
    public static final int EXP_PAIR=11;
    public static final int BitAnd=68;
    public static final int Multiply=81;
    public static final int OBrace=84;
    public static final int INDEXES=21;
    public static final int NEGATE=19;
    public static final int SET=29;
    public static final int Duration=43;
    public static final int Digit=100;
    public static final int For=51;
    public static final int Divide=82;
    public static final int List=45;
    public static final int TAILS=26;
    public static final int SColon=90;
    public static final int OBracket=86;
    public static final int NonZeroDigit=106;
    public static final int BLOCK=4;
    public static final int MAP=27;
    public static final int Not=75;
    public static final int DECLARE=8;
    public static final int UNARY_MIN=18;
    public static final int ASSIGNMENT=7;
    public static final int Infinity=61;
    public static final int Comma=92;
    public static final int Integer=56;
    public static final int Pow=74;
    public static final int LTEquals=73;

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
    // grammar/PlazmaScriptWalker.g:62:1: statement returns [LNode node] : ( assignment | declare | functionCall | lookup | ifStatement | forStatement | whileStatement | Break | Continue );
    public final LNode statement() throws RecognitionException {
        LNode node = null;

        LNode assignment4 = null;

        LNode declare5 = null;

        LNode functionCall6 = null;

        LNode lookup7 = null;

        LNode ifStatement8 = null;

        LNode forStatement9 = null;

        LNode whileStatement10 = null;


        try {
            // grammar/PlazmaScriptWalker.g:63:3: ( assignment | declare | functionCall | lookup | ifStatement | forStatement | whileStatement | Break | Continue )
            int alt3=9;
            switch ( input.LA(1) ) {
            case ASSIGNMENT:
                {
                alt3=1;
                }
                break;
            case DECLARE:
                {
                alt3=2;
                }
                break;
            case FUNC_CALL:
                {
                alt3=3;
                }
                break;
            case LOOKUP:
                {
                alt3=4;
                }
                break;
            case IF:
                {
                alt3=5;
                }
                break;
            case For:
                {
                alt3=6;
                }
                break;
            case While:
                {
                alt3=7;
                }
                break;
            case Break:
                {
                alt3=8;
                }
                break;
            case Continue:
                {
                alt3=9;
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
                    // grammar/PlazmaScriptWalker.g:64:6: declare
                    {
                    pushFollow(FOLLOW_declare_in_statement135);
                    declare5=declare();

                    state._fsp--;

                    node = declare5;

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:65:6: functionCall
                    {
                    pushFollow(FOLLOW_functionCall_in_statement151);
                    functionCall6=functionCall();

                    state._fsp--;

                    node = functionCall6;

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:66:6: lookup
                    {
                    pushFollow(FOLLOW_lookup_in_statement162);
                    lookup7=lookup();

                    state._fsp--;

                    node = lookup7;

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:67:6: ifStatement
                    {
                    pushFollow(FOLLOW_ifStatement_in_statement181);
                    ifStatement8=ifStatement();

                    state._fsp--;

                    node = ifStatement8;

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScriptWalker.g:68:6: forStatement
                    {
                    pushFollow(FOLLOW_forStatement_in_statement193);
                    forStatement9=forStatement();

                    state._fsp--;

                    node = forStatement9;

                    }
                    break;
                case 7 :
                    // grammar/PlazmaScriptWalker.g:70:6: whileStatement
                    {
                    pushFollow(FOLLOW_whileStatement_in_statement205);
                    whileStatement10=whileStatement();

                    state._fsp--;

                    node = whileStatement10;

                    }
                    break;
                case 8 :
                    // grammar/PlazmaScriptWalker.g:71:6: Break
                    {
                    match(input,Break,FOLLOW_Break_in_statement214); 
                    node = new BreakNode();

                    }
                    break;
                case 9 :
                    // grammar/PlazmaScriptWalker.g:72:6: Continue
                    {
                    match(input,Continue,FOLLOW_Continue_in_statement267); 
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
    // grammar/PlazmaScriptWalker.g:77:1: assignment returns [LNode node] : ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression ) ;
    public final LNode assignment() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier12=null;
        LNode variableDef11 = null;

        java.util.List<LNode> indexes13 = null;

        LNode expression14 = null;


        try {
            // grammar/PlazmaScriptWalker.g:78:3: ( ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression ) )
            // grammar/PlazmaScriptWalker.g:78:6: ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression )
            {
            match(input,ASSIGNMENT,FOLLOW_ASSIGNMENT_in_assignment333); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:78:19: ( variableDef )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==Var) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:78:19: variableDef
                    {
                    pushFollow(FOLLOW_variableDef_in_assignment335);
                    variableDef11=variableDef();

                    state._fsp--;


                    }
                    break;

            }

            Identifier12=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_assignment338); 
            // grammar/PlazmaScriptWalker.g:78:43: ( indexes )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==TAILS) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:78:43: indexes
                    {
                    pushFollow(FOLLOW_indexes_in_assignment340);
                    indexes13=indexes();

                    state._fsp--;


                    }
                    break;

            }

            pushFollow(FOLLOW_expression_in_assignment343);
            expression14=expression();

            state._fsp--;


            match(input, Token.UP, null); 
            node = new AssignmentNode(variableDef11, (Identifier12!=null?Identifier12.getText():null), indexes13, expression14, currentScope, globalScope);

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


    // $ANTLR start "declare"
    // grammar/PlazmaScriptWalker.g:81:1: declare returns [LNode node] : ^( DECLARE variableDef Identifier ) ;
    public final LNode declare() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier16=null;
        LNode variableDef15 = null;


        try {
            // grammar/PlazmaScriptWalker.g:82:3: ( ^( DECLARE variableDef Identifier ) )
            // grammar/PlazmaScriptWalker.g:82:6: ^( DECLARE variableDef Identifier )
            {
            match(input,DECLARE,FOLLOW_DECLARE_in_declare365); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_variableDef_in_declare367);
            variableDef15=variableDef();

            state._fsp--;

            Identifier16=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_declare369); 

            match(input, Token.UP, null); 
            node = new AssignmentNode(variableDef15, (Identifier16!=null?Identifier16.getText():null), null, null, currentScope, globalScope);

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
    // $ANTLR end "declare"


    // $ANTLR start "functionCall"
    // grammar/PlazmaScriptWalker.g:85:1: functionCall returns [LNode node] : ( ^( FUNC_CALL Identifier ( exprList )? ) | ^( FUNC_CALL Println ( expression )? ) | ^( FUNC_CALL Print expression ) | ^( FUNC_CALL Assert expression ) | ^( FUNC_CALL Date ( exprList )? ) | ^( FUNC_CALL DateTime ( exprList )? ) | ^( FUNC_CALL Time ( exprList )? ) | ^( FUNC_CALL Duration ( expression )? ) | ^( FUNC_CALL Period ( exprList )? ) | ^( FUNC_CALL List ( exprList )? ) | ^( FUNC_CALL Set ( exprList )? ) );
    public final LNode functionCall() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier17=null;
        java.util.List<LNode> exprList18 = null;

        LNode expression19 = null;

        LNode expression20 = null;

        LNode expression21 = null;

        java.util.List<LNode> exprList22 = null;

        java.util.List<LNode> exprList23 = null;

        java.util.List<LNode> exprList24 = null;

        LNode expression25 = null;

        java.util.List<LNode> exprList26 = null;

        java.util.List<LNode> exprList27 = null;

        java.util.List<LNode> exprList28 = null;


        try {
            // grammar/PlazmaScriptWalker.g:86:3: ( ^( FUNC_CALL Identifier ( exprList )? ) | ^( FUNC_CALL Println ( expression )? ) | ^( FUNC_CALL Print expression ) | ^( FUNC_CALL Assert expression ) | ^( FUNC_CALL Date ( exprList )? ) | ^( FUNC_CALL DateTime ( exprList )? ) | ^( FUNC_CALL Time ( exprList )? ) | ^( FUNC_CALL Duration ( expression )? ) | ^( FUNC_CALL Period ( exprList )? ) | ^( FUNC_CALL List ( exprList )? ) | ^( FUNC_CALL Set ( exprList )? ) )
            int alt15=11;
            alt15 = dfa15.predict(input);
            switch (alt15) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:86:6: ^( FUNC_CALL Identifier ( exprList )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall391); 

                    match(input, Token.DOWN, null); 
                    Identifier17=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_functionCall393); 
                    // grammar/PlazmaScriptWalker.g:86:29: ( exprList )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==EXP_LIST) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:86:29: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall395);
                            exprList18=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new FunctionCallNode((Identifier17!=null?Identifier17.getText():null), exprList18, functions, globalScope);

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:87:6: ^( FUNC_CALL Println ( expression )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall407); 

                    match(input, Token.DOWN, null); 
                    match(input,Println,FOLLOW_Println_in_functionCall409); 
                    // grammar/PlazmaScriptWalker.g:87:26: ( expression )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( ((LA7_0>=TERNARY && LA7_0<=NEGATE)||LA7_0==LOOKUP||(LA7_0>=In && LA7_0<=Infinity)||(LA7_0>=XorWord && LA7_0<=Pow)||(LA7_0>=GT && LA7_0<=Modulus)) ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:87:26: expression
                            {
                            pushFollow(FOLLOW_expression_in_functionCall411);
                            expression19=expression();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new PrintlnNode(expression19);

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:88:6: ^( FUNC_CALL Print expression )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall424); 

                    match(input, Token.DOWN, null); 
                    match(input,Print,FOLLOW_Print_in_functionCall426); 
                    pushFollow(FOLLOW_expression_in_functionCall428);
                    expression20=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new PrintNode(expression20);

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:89:6: ^( FUNC_CALL Assert expression )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall443); 

                    match(input, Token.DOWN, null); 
                    match(input,Assert,FOLLOW_Assert_in_functionCall445); 
                    pushFollow(FOLLOW_expression_in_functionCall447);
                    expression21=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AssertNode(expression21);

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:90:6: ^( FUNC_CALL Date ( exprList )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall461); 

                    match(input, Token.DOWN, null); 
                    match(input,Date,FOLLOW_Date_in_functionCall463); 
                    // grammar/PlazmaScriptWalker.g:90:23: ( exprList )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==EXP_LIST) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:90:23: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall465);
                            exprList22=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new DateNode(exprList22);

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScriptWalker.g:91:6: ^( FUNC_CALL DateTime ( exprList )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall483); 

                    match(input, Token.DOWN, null); 
                    match(input,DateTime,FOLLOW_DateTime_in_functionCall485); 
                    // grammar/PlazmaScriptWalker.g:91:27: ( exprList )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==EXP_LIST) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:91:27: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall487);
                            exprList23=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new DateTimeNode(exprList23);

                    }
                    break;
                case 7 :
                    // grammar/PlazmaScriptWalker.g:92:6: ^( FUNC_CALL Time ( exprList )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall503); 

                    match(input, Token.DOWN, null); 
                    match(input,Time,FOLLOW_Time_in_functionCall505); 
                    // grammar/PlazmaScriptWalker.g:92:23: ( exprList )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==EXP_LIST) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:92:23: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall507);
                            exprList24=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new TimeNode(exprList24);

                    }
                    break;
                case 8 :
                    // grammar/PlazmaScriptWalker.g:93:6: ^( FUNC_CALL Duration ( expression )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall525); 

                    match(input, Token.DOWN, null); 
                    match(input,Duration,FOLLOW_Duration_in_functionCall527); 
                    // grammar/PlazmaScriptWalker.g:93:27: ( expression )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( ((LA11_0>=TERNARY && LA11_0<=NEGATE)||LA11_0==LOOKUP||(LA11_0>=In && LA11_0<=Infinity)||(LA11_0>=XorWord && LA11_0<=Pow)||(LA11_0>=GT && LA11_0<=Modulus)) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:93:27: expression
                            {
                            pushFollow(FOLLOW_expression_in_functionCall529);
                            expression25=expression();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new DurationNode(expression25);

                    }
                    break;
                case 9 :
                    // grammar/PlazmaScriptWalker.g:94:6: ^( FUNC_CALL Period ( exprList )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall541); 

                    match(input, Token.DOWN, null); 
                    match(input,Period,FOLLOW_Period_in_functionCall543); 
                    // grammar/PlazmaScriptWalker.g:94:25: ( exprList )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==EXP_LIST) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:94:25: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall545);
                            exprList26=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new PeriodNode(exprList26);

                    }
                    break;
                case 10 :
                    // grammar/PlazmaScriptWalker.g:95:6: ^( FUNC_CALL List ( exprList )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall569); 

                    match(input, Token.DOWN, null); 
                    match(input,List,FOLLOW_List_in_functionCall571); 
                    // grammar/PlazmaScriptWalker.g:95:23: ( exprList )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==EXP_LIST) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:95:23: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall573);
                            exprList27=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new ListNode(exprList27);

                    }
                    break;
                case 11 :
                    // grammar/PlazmaScriptWalker.g:96:6: ^( FUNC_CALL Set ( exprList )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall591); 

                    match(input, Token.DOWN, null); 
                    match(input,Set,FOLLOW_Set_in_functionCall593); 
                    // grammar/PlazmaScriptWalker.g:96:22: ( exprList )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==EXP_LIST) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:96:22: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall595);
                            exprList28=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new SetNode(exprList28);

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
    // grammar/PlazmaScriptWalker.g:99:1: ifStatement returns [LNode node] : ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? ) ;
    public final LNode ifStatement() throws RecognitionException {
        LNode node = null;

        IfNode ifNode = new IfNode();
        try {
            // grammar/PlazmaScriptWalker.g:102:3: ( ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? ) )
            // grammar/PlazmaScriptWalker.g:102:6: ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? )
            {
            match(input,IF,FOLLOW_IF_in_ifStatement640); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_ifStat_in_ifStatement642);
            ifStat(ifNode);

            state._fsp--;

            // grammar/PlazmaScriptWalker.g:102:26: ( elseIfStat[ifNode] )*
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
            	    // grammar/PlazmaScriptWalker.g:102:27: elseIfStat[ifNode]
            	    {
            	    pushFollow(FOLLOW_elseIfStat_in_ifStatement646);
            	    elseIfStat(ifNode);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            // grammar/PlazmaScriptWalker.g:102:48: ( elseStat[ifNode] )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==EXP) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:102:49: elseStat[ifNode]
                    {
                    pushFollow(FOLLOW_elseStat_in_ifStatement652);
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
    // grammar/PlazmaScriptWalker.g:105:1: ifStat[IfNode parent] : ^( EXP expression block ) ;
    public final void ifStat(IfNode parent) throws RecognitionException {
        LNode expression29 = null;

        LNode block30 = null;


        try {
            // grammar/PlazmaScriptWalker.g:106:3: ( ^( EXP expression block ) )
            // grammar/PlazmaScriptWalker.g:106:6: ^( EXP expression block )
            {
            match(input,EXP,FOLLOW_EXP_in_ifStat672); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_ifStat674);
            expression29=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_ifStat676);
            block30=block();

            state._fsp--;


            match(input, Token.UP, null); 
            parent.addChoice(expression29, block30);

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
    // grammar/PlazmaScriptWalker.g:109:1: elseIfStat[IfNode parent] : ^( EXP expression block ) ;
    public final void elseIfStat(IfNode parent) throws RecognitionException {
        LNode expression31 = null;

        LNode block32 = null;


        try {
            // grammar/PlazmaScriptWalker.g:110:3: ( ^( EXP expression block ) )
            // grammar/PlazmaScriptWalker.g:110:6: ^( EXP expression block )
            {
            match(input,EXP,FOLLOW_EXP_in_elseIfStat695); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_elseIfStat697);
            expression31=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_elseIfStat699);
            block32=block();

            state._fsp--;


            match(input, Token.UP, null); 
            parent.addChoice(expression31, block32);

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
    // grammar/PlazmaScriptWalker.g:113:1: elseStat[IfNode parent] : ^( EXP block ) ;
    public final void elseStat(IfNode parent) throws RecognitionException {
        LNode block33 = null;


        try {
            // grammar/PlazmaScriptWalker.g:114:3: ( ^( EXP block ) )
            // grammar/PlazmaScriptWalker.g:114:6: ^( EXP block )
            {
            match(input,EXP,FOLLOW_EXP_in_elseStat718); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_block_in_elseStat720);
            block33=block();

            state._fsp--;


            match(input, Token.UP, null); 
            parent.addChoice(new BooleanNode(true), block33);

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
    // grammar/PlazmaScriptWalker.g:121:1: forStatement returns [LNode node] : ^( For Identifier a= expression block ) ;
    public final LNode forStatement() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier34=null;
        LNode a = null;

        LNode block35 = null;


        try {
            // grammar/PlazmaScriptWalker.g:122:3: ( ^( For Identifier a= expression block ) )
            // grammar/PlazmaScriptWalker.g:122:6: ^( For Identifier a= expression block )
            {
            match(input,For,FOLLOW_For_in_forStatement751); 

            match(input, Token.DOWN, null); 
            Identifier34=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_forStatement753); 
            pushFollow(FOLLOW_expression_in_forStatement757);
            a=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_forStatement759);
            block35=block();

            state._fsp--;


            match(input, Token.UP, null); 
            node = new ForStatementNode2((Identifier34!=null?Identifier34.getText():null), a, block35, currentScope);

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
    // grammar/PlazmaScriptWalker.g:126:1: whileStatement returns [LNode node] : ^( While expression block ) ;
    public final LNode whileStatement() throws RecognitionException {
        LNode node = null;

        LNode expression36 = null;

        LNode block37 = null;


        try {
            // grammar/PlazmaScriptWalker.g:127:3: ( ^( While expression block ) )
            // grammar/PlazmaScriptWalker.g:127:6: ^( While expression block )
            {
            match(input,While,FOLLOW_While_in_whileStatement784); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_whileStatement786);
            expression36=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_whileStatement788);
            block37=block();

            state._fsp--;


            match(input, Token.UP, null); 
            node = new WhileStatementNode(expression36, block37);

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
    // grammar/PlazmaScriptWalker.g:130:1: idList returns [java.util.List<String> i] : ^( ID_LIST ( Identifier )+ ) ;
    public final java.util.List<String> idList() throws RecognitionException {
        java.util.List<String> i = null;

        CommonTree Identifier38=null;

        i = new java.util.ArrayList<String>();
        try {
            // grammar/PlazmaScriptWalker.g:132:3: ( ^( ID_LIST ( Identifier )+ ) )
            // grammar/PlazmaScriptWalker.g:132:6: ^( ID_LIST ( Identifier )+ )
            {
            match(input,ID_LIST,FOLLOW_ID_LIST_in_idList815); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:132:16: ( Identifier )+
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
            	    // grammar/PlazmaScriptWalker.g:132:17: Identifier
            	    {
            	    Identifier38=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_idList818); 
            	    i.add((Identifier38!=null?Identifier38.getText():null));

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
    // grammar/PlazmaScriptWalker.g:135:1: exprList returns [java.util.List<LNode> e] : ^( EXP_LIST ( expression )+ ) ;
    public final java.util.List<LNode> exprList() throws RecognitionException {
        java.util.List<LNode> e = null;

        LNode expression39 = null;


        e = new java.util.ArrayList<LNode>();
        try {
            // grammar/PlazmaScriptWalker.g:137:3: ( ^( EXP_LIST ( expression )+ ) )
            // grammar/PlazmaScriptWalker.g:137:6: ^( EXP_LIST ( expression )+ )
            {
            match(input,EXP_LIST,FOLLOW_EXP_LIST_in_exprList848); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:137:17: ( expression )+
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
            	    // grammar/PlazmaScriptWalker.g:137:18: expression
            	    {
            	    pushFollow(FOLLOW_expression_in_exprList851);
            	    expression39=expression();

            	    state._fsp--;

            	    e.add(expression39);

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
    // grammar/PlazmaScriptWalker.g:139:1: exprPair returns [PairNode node] : ^( EXP_PAIR k= expression v= expression ) ;
    public final PairNode exprPair() throws RecognitionException {
        PairNode node = null;

        LNode k = null;

        LNode v = null;


        try {
            // grammar/PlazmaScriptWalker.g:140:3: ( ^( EXP_PAIR k= expression v= expression ) )
            // grammar/PlazmaScriptWalker.g:140:6: ^( EXP_PAIR k= expression v= expression )
            {
            match(input,EXP_PAIR,FOLLOW_EXP_PAIR_in_exprPair874); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_exprPair878);
            k=expression();

            state._fsp--;

            pushFollow(FOLLOW_expression_in_exprPair882);
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
    // grammar/PlazmaScriptWalker.g:143:1: exprMap returns [java.util.List<PairNode> e] : ^( EXP_MAP ( exprPair )+ ) ;
    public final java.util.List<PairNode> exprMap() throws RecognitionException {
        java.util.List<PairNode> e = null;

        PairNode exprPair40 = null;


        e = new java.util.ArrayList<PairNode>();
        try {
            // grammar/PlazmaScriptWalker.g:145:3: ( ^( EXP_MAP ( exprPair )+ ) )
            // grammar/PlazmaScriptWalker.g:145:6: ^( EXP_MAP ( exprPair )+ )
            {
            match(input,EXP_MAP,FOLLOW_EXP_MAP_in_exprMap910); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:145:16: ( exprPair )+
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
            	    // grammar/PlazmaScriptWalker.g:145:17: exprPair
            	    {
            	    pushFollow(FOLLOW_exprPair_in_exprMap913);
            	    exprPair40=exprPair();

            	    state._fsp--;

            	    e.add(exprPair40);

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
    // grammar/PlazmaScriptWalker.g:149:1: expression returns [LNode node] : ( ^( TERNARY a= expression b= expression c= expression ) | ^( In a= expression b= expression ) | ^( RangeE a= expression b= expression ) | ^( Range a= expression b= expression ) | ^( 'xor' a= expression b= expression ) | ^( '||' a= expression b= expression ) | ^( '|' a= expression b= expression ) | ^( 'or' a= expression b= expression ) | ^( '&&' a= expression b= expression ) | ^( '&' a= expression b= expression ) | ^( 'and' a= expression b= expression ) | ^( '==' a= expression b= expression ) | ^( '!=' a= expression b= expression ) | ^( '>=' a= expression b= expression ) | ^( '<=' a= expression b= expression ) | ^( '>' a= expression b= expression ) | ^( '<' a= expression b= expression ) | ^( '+' a= expression b= expression ) | ^( '-' a= expression b= expression ) | ^( '*' a= expression b= expression ) | ^( '/' a= expression b= expression ) | ^( '%' a= expression b= expression ) | ^( '^' a= expression b= expression ) | ^( UNARY_PLUS a= expression ) | ^( UNARY_MIN a= expression ) | ^( NEGATE a= expression ) | Integer | Number | Bool | Null | NaN | Infinity | lookup );
    public final LNode expression() throws RecognitionException {
        LNode node = null;

        CommonTree Integer41=null;
        CommonTree Number42=null;
        CommonTree Bool43=null;
        LNode a = null;

        LNode b = null;

        LNode c = null;

        LNode lookup44 = null;


        try {
            // grammar/PlazmaScriptWalker.g:150:3: ( ^( TERNARY a= expression b= expression c= expression ) | ^( In a= expression b= expression ) | ^( RangeE a= expression b= expression ) | ^( Range a= expression b= expression ) | ^( 'xor' a= expression b= expression ) | ^( '||' a= expression b= expression ) | ^( '|' a= expression b= expression ) | ^( 'or' a= expression b= expression ) | ^( '&&' a= expression b= expression ) | ^( '&' a= expression b= expression ) | ^( 'and' a= expression b= expression ) | ^( '==' a= expression b= expression ) | ^( '!=' a= expression b= expression ) | ^( '>=' a= expression b= expression ) | ^( '<=' a= expression b= expression ) | ^( '>' a= expression b= expression ) | ^( '<' a= expression b= expression ) | ^( '+' a= expression b= expression ) | ^( '-' a= expression b= expression ) | ^( '*' a= expression b= expression ) | ^( '/' a= expression b= expression ) | ^( '%' a= expression b= expression ) | ^( '^' a= expression b= expression ) | ^( UNARY_PLUS a= expression ) | ^( UNARY_MIN a= expression ) | ^( NEGATE a= expression ) | Integer | Number | Bool | Null | NaN | Infinity | lookup )
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
                    // grammar/PlazmaScriptWalker.g:150:6: ^( TERNARY a= expression b= expression c= expression )
                    {
                    match(input,TERNARY,FOLLOW_TERNARY_in_expression938); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression942);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression946);
                    b=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression950);
                    c=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new TernaryNode(a, b, c);

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:151:6: ^( In a= expression b= expression )
                    {
                    match(input,In,FOLLOW_In_in_expression961); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression965);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression969);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new InNode(a, b);

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:153:6: ^( RangeE a= expression b= expression )
                    {
                    match(input,RangeE,FOLLOW_RangeE_in_expression1001); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1005);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1009);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new RangeENode(a, b);

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:154:6: ^( Range a= expression b= expression )
                    {
                    match(input,Range,FOLLOW_Range_in_expression1035); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1039);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1043);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new RangeNode(a, b);

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:156:6: ^( 'xor' a= expression b= expression )
                    {
                    match(input,XorWord,FOLLOW_XorWord_in_expression1073); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1077);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1081);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new XorNode(a, b);

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScriptWalker.g:157:6: ^( '||' a= expression b= expression )
                    {
                    match(input,Or,FOLLOW_Or_in_expression1107); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1111);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1115);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new OrNode(a, b);

                    }
                    break;
                case 7 :
                    // grammar/PlazmaScriptWalker.g:158:6: ^( '|' a= expression b= expression )
                    {
                    match(input,BitOr,FOLLOW_BitOr_in_expression1142); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1146);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1150);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new BitOrNode(a, b);

                    }
                    break;
                case 8 :
                    // grammar/PlazmaScriptWalker.g:159:6: ^( 'or' a= expression b= expression )
                    {
                    match(input,OrWord,FOLLOW_OrWord_in_expression1180); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1184);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1188);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new OrNode(a, b);

                    }
                    break;
                case 9 :
                    // grammar/PlazmaScriptWalker.g:160:6: ^( '&&' a= expression b= expression )
                    {
                    match(input,And,FOLLOW_And_in_expression1217); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1221);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1225);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AndNode(a, b);

                    }
                    break;
                case 10 :
                    // grammar/PlazmaScriptWalker.g:161:6: ^( '&' a= expression b= expression )
                    {
                    match(input,BitAnd,FOLLOW_BitAnd_in_expression1252); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1256);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1260);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new BitAndNode(a, b);

                    }
                    break;
                case 11 :
                    // grammar/PlazmaScriptWalker.g:162:6: ^( 'and' a= expression b= expression )
                    {
                    match(input,AndWord,FOLLOW_AndWord_in_expression1290); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1294);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1298);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AndNode(a, b);

                    }
                    break;
                case 12 :
                    // grammar/PlazmaScriptWalker.g:163:6: ^( '==' a= expression b= expression )
                    {
                    match(input,Equals,FOLLOW_Equals_in_expression1326); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1330);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1334);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new EQNode(a, b);

                    }
                    break;
                case 13 :
                    // grammar/PlazmaScriptWalker.g:164:6: ^( '!=' a= expression b= expression )
                    {
                    match(input,NEquals,FOLLOW_NEquals_in_expression1361); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1365);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1369);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new NENode(a, b);

                    }
                    break;
                case 14 :
                    // grammar/PlazmaScriptWalker.g:165:6: ^( '>=' a= expression b= expression )
                    {
                    match(input,GTEquals,FOLLOW_GTEquals_in_expression1396); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1400);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1404);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new GTENode(a, b);

                    }
                    break;
                case 15 :
                    // grammar/PlazmaScriptWalker.g:166:6: ^( '<=' a= expression b= expression )
                    {
                    match(input,LTEquals,FOLLOW_LTEquals_in_expression1431); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1435);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1439);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new LTENode(a, b);

                    }
                    break;
                case 16 :
                    // grammar/PlazmaScriptWalker.g:167:6: ^( '>' a= expression b= expression )
                    {
                    match(input,GT,FOLLOW_GT_in_expression1466); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1470);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1474);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new GTNode(a, b);

                    }
                    break;
                case 17 :
                    // grammar/PlazmaScriptWalker.g:168:6: ^( '<' a= expression b= expression )
                    {
                    match(input,LT,FOLLOW_LT_in_expression1502); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1506);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1510);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new LTNode(a, b);

                    }
                    break;
                case 18 :
                    // grammar/PlazmaScriptWalker.g:169:6: ^( '+' a= expression b= expression )
                    {
                    match(input,Add,FOLLOW_Add_in_expression1538); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1542);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1546);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AddNode(a, b);

                    }
                    break;
                case 19 :
                    // grammar/PlazmaScriptWalker.g:170:6: ^( '-' a= expression b= expression )
                    {
                    match(input,Subtract,FOLLOW_Subtract_in_expression1574); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1578);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1582);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new SubNode(a, b);

                    }
                    break;
                case 20 :
                    // grammar/PlazmaScriptWalker.g:171:6: ^( '*' a= expression b= expression )
                    {
                    match(input,Multiply,FOLLOW_Multiply_in_expression1610); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1614);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1618);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new MulNode(a, b);

                    }
                    break;
                case 21 :
                    // grammar/PlazmaScriptWalker.g:172:6: ^( '/' a= expression b= expression )
                    {
                    match(input,Divide,FOLLOW_Divide_in_expression1646); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1650);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1654);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new DivNode(a, b);

                    }
                    break;
                case 22 :
                    // grammar/PlazmaScriptWalker.g:174:6: ^( '%' a= expression b= expression )
                    {
                    match(input,Modulus,FOLLOW_Modulus_in_expression1685); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1689);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1693);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new ModNode(a, b);

                    }
                    break;
                case 23 :
                    // grammar/PlazmaScriptWalker.g:175:6: ^( '^' a= expression b= expression )
                    {
                    match(input,Pow,FOLLOW_Pow_in_expression1721); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1725);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1729);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new PowNode(a, b);

                    }
                    break;
                case 24 :
                    // grammar/PlazmaScriptWalker.g:176:6: ^( UNARY_PLUS a= expression )
                    {
                    match(input,UNARY_PLUS,FOLLOW_UNARY_PLUS_in_expression1757); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1761);
                    a=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new UnaryPlusNode(a);

                    }
                    break;
                case 25 :
                    // grammar/PlazmaScriptWalker.g:177:6: ^( UNARY_MIN a= expression )
                    {
                    match(input,UNARY_MIN,FOLLOW_UNARY_MIN_in_expression1798); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1802);
                    a=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new UnaryMinusNode(a);

                    }
                    break;
                case 26 :
                    // grammar/PlazmaScriptWalker.g:178:6: ^( NEGATE a= expression )
                    {
                    match(input,NEGATE,FOLLOW_NEGATE_in_expression1837); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1841);
                    a=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new NotNode(a);

                    }
                    break;
                case 27 :
                    // grammar/PlazmaScriptWalker.g:180:6: Integer
                    {
                    Integer41=(CommonTree)match(input,Integer,FOLLOW_Integer_in_expression1880); 
                    node = new IntegerNode((Integer41!=null?Integer41.getText():null));

                    }
                    break;
                case 28 :
                    // grammar/PlazmaScriptWalker.g:181:6: Number
                    {
                    Number42=(CommonTree)match(input,Number,FOLLOW_Number_in_expression1956); 
                    node = new NumberNode((Number42!=null?Number42.getText():null));

                    }
                    break;
                case 29 :
                    // grammar/PlazmaScriptWalker.g:182:6: Bool
                    {
                    Bool43=(CommonTree)match(input,Bool,FOLLOW_Bool_in_expression2034); 
                    node = new BooleanNode((Bool43!=null?Bool43.getText():null));

                    }
                    break;
                case 30 :
                    // grammar/PlazmaScriptWalker.g:183:6: Null
                    {
                    match(input,Null,FOLLOW_Null_in_expression2115); 
                    node = new NullNode();

                    }
                    break;
                case 31 :
                    // grammar/PlazmaScriptWalker.g:184:6: NaN
                    {
                    match(input,NaN,FOLLOW_NaN_in_expression2169); 
                    node = new NaNNode();

                    }
                    break;
                case 32 :
                    // grammar/PlazmaScriptWalker.g:185:6: Infinity
                    {
                    match(input,Infinity,FOLLOW_Infinity_in_expression2226); 
                    node = new InfinityNode();

                    }
                    break;
                case 33 :
                    // grammar/PlazmaScriptWalker.g:186:6: lookup
                    {
                    pushFollow(FOLLOW_lookup_in_expression2278);
                    lookup44=lookup();

                    state._fsp--;

                    node = lookup44;

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
    // grammar/PlazmaScriptWalker.g:189:1: list returns [LNode node] : ^( LIST ( exprList )? ) ;
    public final LNode list() throws RecognitionException {
        LNode node = null;

        java.util.List<LNode> exprList45 = null;


        try {
            // grammar/PlazmaScriptWalker.g:190:3: ( ^( LIST ( exprList )? ) )
            // grammar/PlazmaScriptWalker.g:190:6: ^( LIST ( exprList )? )
            {
            match(input,LIST,FOLLOW_LIST_in_list2342); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // grammar/PlazmaScriptWalker.g:190:13: ( exprList )?
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==EXP_LIST) ) {
                    alt22=1;
                }
                switch (alt22) {
                    case 1 :
                        // grammar/PlazmaScriptWalker.g:190:13: exprList
                        {
                        pushFollow(FOLLOW_exprList_in_list2344);
                        exprList45=exprList();

                        state._fsp--;


                        }
                        break;

                }


                match(input, Token.UP, null); 
            }
            node = new ListNode(exprList45);

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
    // grammar/PlazmaScriptWalker.g:193:1: map returns [LNode node] : ^( MAP ( exprMap )? ) ;
    public final LNode map() throws RecognitionException {
        LNode node = null;

        java.util.List<PairNode> exprMap46 = null;


        try {
            // grammar/PlazmaScriptWalker.g:194:3: ( ^( MAP ( exprMap )? ) )
            // grammar/PlazmaScriptWalker.g:194:6: ^( MAP ( exprMap )? )
            {
            match(input,MAP,FOLLOW_MAP_in_map2367); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // grammar/PlazmaScriptWalker.g:194:12: ( exprMap )?
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==EXP_MAP) ) {
                    alt23=1;
                }
                switch (alt23) {
                    case 1 :
                        // grammar/PlazmaScriptWalker.g:194:12: exprMap
                        {
                        pushFollow(FOLLOW_exprMap_in_map2369);
                        exprMap46=exprMap();

                        state._fsp--;


                        }
                        break;

                }


                match(input, Token.UP, null); 
            }
            node = new MapNode(exprMap46);

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
    // grammar/PlazmaScriptWalker.g:197:1: lookup returns [LNode node] : ( ^( LOOKUP functionCall (i= indexes )? ) | ^( LOOKUP list (i= indexes )? ) | ^( LOOKUP map (i= indexes )? ) | ^( LOOKUP expression (i= indexes )? ) | ^( LOOKUP Identifier (i= indexes )? ) | ^( LOOKUP String (i= indexes )? ) );
    public final LNode lookup() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier51=null;
        CommonTree String52=null;
        java.util.List<LNode> i = null;

        LNode functionCall47 = null;

        LNode list48 = null;

        LNode map49 = null;

        LNode expression50 = null;


        try {
            // grammar/PlazmaScriptWalker.g:198:3: ( ^( LOOKUP functionCall (i= indexes )? ) | ^( LOOKUP list (i= indexes )? ) | ^( LOOKUP map (i= indexes )? ) | ^( LOOKUP expression (i= indexes )? ) | ^( LOOKUP Identifier (i= indexes )? ) | ^( LOOKUP String (i= indexes )? ) )
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
                    case FUNC_CALL:
                        {
                        alt30=1;
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
                    // grammar/PlazmaScriptWalker.g:198:6: ^( LOOKUP functionCall (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2392); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_functionCall_in_lookup2394);
                    functionCall47=functionCall();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:198:29: (i= indexes )?
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( (LA24_0==TAILS) ) {
                        alt24=1;
                    }
                    switch (alt24) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:198:29: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2398);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(functionCall47, i) : functionCall47;

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:199:6: ^( LOOKUP list (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2410); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_list_in_lookup2412);
                    list48=list();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:199:21: (i= indexes )?
                    int alt25=2;
                    int LA25_0 = input.LA(1);

                    if ( (LA25_0==TAILS) ) {
                        alt25=1;
                    }
                    switch (alt25) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:199:21: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2416);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(list48, i) : list48;

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:200:6: ^( LOOKUP map (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2436); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_map_in_lookup2438);
                    map49=map();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:200:20: (i= indexes )?
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==TAILS) ) {
                        alt26=1;
                    }
                    switch (alt26) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:200:20: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2442);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(map49, i) : map49;

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:201:6: ^( LOOKUP expression (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2465); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_lookup2467);
                    expression50=expression();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:201:27: (i= indexes )?
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==TAILS) ) {
                        alt27=1;
                    }
                    switch (alt27) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:201:27: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2471);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(expression50, i) : expression50;

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:202:6: ^( LOOKUP Identifier (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2485); 

                    match(input, Token.DOWN, null); 
                    Identifier51=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_lookup2487); 
                    // grammar/PlazmaScriptWalker.g:202:27: (i= indexes )?
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==TAILS) ) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:202:27: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2491);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(new IdentifierNode((Identifier51!=null?Identifier51.getText():null), currentScope, globalScope), i) : new IdentifierNode((Identifier51!=null?Identifier51.getText():null), currentScope, globalScope);

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScriptWalker.g:203:6: ^( LOOKUP String (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2505); 

                    match(input, Token.DOWN, null); 
                    String52=(CommonTree)match(input,String,FOLLOW_String_in_lookup2507); 
                    // grammar/PlazmaScriptWalker.g:203:23: (i= indexes )?
                    int alt29=2;
                    int LA29_0 = input.LA(1);

                    if ( (LA29_0==TAILS) ) {
                        alt29=1;
                    }
                    switch (alt29) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:203:23: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2511);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(new StringNode((String52!=null?String52.getText():null)), i) : new StringNode((String52!=null?String52.getText():null));

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
    // grammar/PlazmaScriptWalker.g:206:1: indexes returns [java.util.List<LNode> e] : ^( TAILS ( tail )+ ) ;
    public final java.util.List<LNode> indexes() throws RecognitionException {
        java.util.List<LNode> e = null;

        LNode tail53 = null;


        e = new java.util.ArrayList<LNode>();
        try {
            // grammar/PlazmaScriptWalker.g:209:3: ( ^( TAILS ( tail )+ ) )
            // grammar/PlazmaScriptWalker.g:209:8: ^( TAILS ( tail )+ )
            {
            match(input,TAILS,FOLLOW_TAILS_in_indexes2550); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:209:18: ( tail )+
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
            	    // grammar/PlazmaScriptWalker.g:209:19: tail
            	    {
            	    pushFollow(FOLLOW_tail_in_indexes2555);
            	    tail53=tail();

            	    state._fsp--;

            	    e.add(tail53);

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
    // grammar/PlazmaScriptWalker.g:213:1: tail returns [LNode node] : ( ^( INDEX expression ) | ^( ATTRIBUTE Identifier ) | ^( CALL Identifier ( exprList )? ) );
    public final LNode tail() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier55=null;
        CommonTree Identifier56=null;
        LNode expression54 = null;

        java.util.List<LNode> exprList57 = null;


        try {
            // grammar/PlazmaScriptWalker.g:214:2: ( ^( INDEX expression ) | ^( ATTRIBUTE Identifier ) | ^( CALL Identifier ( exprList )? ) )
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
                    // grammar/PlazmaScriptWalker.g:214:4: ^( INDEX expression )
                    {
                    match(input,INDEX,FOLLOW_INDEX_in_tail2580); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_tail2582);
                    expression54=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = expression54;

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:215:4: ^( ATTRIBUTE Identifier )
                    {
                    match(input,ATTRIBUTE,FOLLOW_ATTRIBUTE_in_tail2601); 

                    match(input, Token.DOWN, null); 
                    Identifier55=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_tail2603); 

                    match(input, Token.UP, null); 
                    node = new StringNode((Identifier55!=null?Identifier55.getText():null));

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:216:4: ^( CALL Identifier ( exprList )? )
                    {
                    match(input,CALL,FOLLOW_CALL_in_tail2618); 

                    match(input, Token.DOWN, null); 
                    Identifier56=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_tail2620); 
                    // grammar/PlazmaScriptWalker.g:216:22: ( exprList )?
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( (LA32_0==EXP_LIST) ) {
                        alt32=1;
                    }
                    switch (alt32) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:216:22: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_tail2622);
                            exprList57=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new MethodCallNode((Identifier56!=null?Identifier56.getText():null), exprList57, functions, globalScope);

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
    // grammar/PlazmaScriptWalker.g:219:1: variableDef returns [LNode node] : Var ;
    public final LNode variableDef() throws RecognitionException {
        LNode node = null;

        CommonTree Var58=null;

        try {
            // grammar/PlazmaScriptWalker.g:220:3: ( Var )
            // grammar/PlazmaScriptWalker.g:220:5: Var
            {
            Var58=(CommonTree)match(input,Var,FOLLOW_Var_in_variableDef2646); 
            node = new VariableDefNode((Var58!=null?Var58.getText():null), null);

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
        "\1\11\1\2\1\44\13\uffff";
    static final String DFA15_maxS =
        "\1\11\1\2\1\56\13\uffff";
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
            return "85:1: functionCall returns [LNode node] : ( ^( FUNC_CALL Identifier ( exprList )? ) | ^( FUNC_CALL Println ( expression )? ) | ^( FUNC_CALL Print expression ) | ^( FUNC_CALL Assert expression ) | ^( FUNC_CALL Date ( exprList )? ) | ^( FUNC_CALL DateTime ( exprList )? ) | ^( FUNC_CALL Time ( exprList )? ) | ^( FUNC_CALL Duration ( expression )? ) | ^( FUNC_CALL Period ( exprList )? ) | ^( FUNC_CALL List ( exprList )? ) | ^( FUNC_CALL Set ( exprList )? ) );";
        }
    }
 

    public static final BitSet FOLLOW_block_in_walk50 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BLOCK_in_block81 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STATEMENTS_in_block84 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_block87 = new BitSet(new long[]{0x0018000C40008388L});
    public static final BitSet FOLLOW_RETURN_in_block95 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_block98 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_assignment_in_statement122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declare_in_statement135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_statement151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_statement162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStatement_in_statement181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forStatement_in_statement193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whileStatement_in_statement205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Break_in_statement214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Continue_in_statement267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGNMENT_in_assignment333 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_variableDef_in_assignment335 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_Identifier_in_assignment338 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_indexes_in_assignment340 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_assignment343 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DECLARE_in_declare365 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_variableDef_in_declare367 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_Identifier_in_declare369 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall391 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_functionCall393 = new BitSet(new long[]{0x0000000000002008L});
    public static final BitSet FOLLOW_exprList_in_functionCall395 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall407 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Println_in_functionCall409 = new BitSet(new long[]{0xBFE00000440F0008L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_functionCall411 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall424 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Print_in_functionCall426 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_functionCall428 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall443 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Assert_in_functionCall445 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_functionCall447 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall461 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Date_in_functionCall463 = new BitSet(new long[]{0x0000000000002008L});
    public static final BitSet FOLLOW_exprList_in_functionCall465 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall483 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_DateTime_in_functionCall485 = new BitSet(new long[]{0x0000000000002008L});
    public static final BitSet FOLLOW_exprList_in_functionCall487 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall503 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Time_in_functionCall505 = new BitSet(new long[]{0x0000000000002008L});
    public static final BitSet FOLLOW_exprList_in_functionCall507 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall525 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Duration_in_functionCall527 = new BitSet(new long[]{0xBFE00000440F0008L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_functionCall529 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall541 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Period_in_functionCall543 = new BitSet(new long[]{0x0000000000002008L});
    public static final BitSet FOLLOW_exprList_in_functionCall545 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall569 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_List_in_functionCall571 = new BitSet(new long[]{0x0000000000002008L});
    public static final BitSet FOLLOW_exprList_in_functionCall573 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall591 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Set_in_functionCall593 = new BitSet(new long[]{0x0000000000002008L});
    public static final BitSet FOLLOW_exprList_in_functionCall595 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IF_in_ifStatement640 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ifStat_in_ifStatement642 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_elseIfStat_in_ifStatement646 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_elseStat_in_ifStatement652 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_ifStat672 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_ifStat674 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_ifStat676 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_elseIfStat695 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_elseIfStat697 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_elseIfStat699 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_elseStat718 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_block_in_elseStat720 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_For_in_forStatement751 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_forStatement753 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_forStatement757 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_forStatement759 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_While_in_whileStatement784 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_whileStatement786 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_whileStatement788 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_LIST_in_idList815 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_idList818 = new BitSet(new long[]{0x0000001000000008L});
    public static final BitSet FOLLOW_EXP_LIST_in_exprList848 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_exprList851 = new BitSet(new long[]{0xBFE00000440F0008L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_EXP_PAIR_in_exprPair874 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_exprPair878 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_exprPair882 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_MAP_in_exprMap910 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprPair_in_exprMap913 = new BitSet(new long[]{0x0000000000000808L});
    public static final BitSet FOLLOW_TERNARY_in_expression938 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression942 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_expression946 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_expression950 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_In_in_expression961 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression965 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_expression969 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RangeE_in_expression1001 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1005 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_expression1009 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Range_in_expression1035 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1039 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_expression1043 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_XorWord_in_expression1073 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1077 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_expression1081 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Or_in_expression1107 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1111 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_expression1115 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BitOr_in_expression1142 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1146 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_expression1150 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_OrWord_in_expression1180 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1184 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_expression1188 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_And_in_expression1217 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1221 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_expression1225 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BitAnd_in_expression1252 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1256 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_expression1260 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_AndWord_in_expression1290 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1294 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_expression1298 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Equals_in_expression1326 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1330 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_expression1334 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEquals_in_expression1361 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1365 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_expression1369 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GTEquals_in_expression1396 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1400 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_expression1404 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LTEquals_in_expression1431 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1435 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_expression1439 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GT_in_expression1466 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1470 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_expression1474 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LT_in_expression1502 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1506 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_expression1510 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Add_in_expression1538 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1542 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_expression1546 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Subtract_in_expression1574 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1578 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_expression1582 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Multiply_in_expression1610 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1614 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_expression1618 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Divide_in_expression1646 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1650 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_expression1654 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Modulus_in_expression1685 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1689 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_expression1693 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Pow_in_expression1721 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1725 = new BitSet(new long[]{0xBFE00000440F0000L,0x00000000000FE7FFL});
    public static final BitSet FOLLOW_expression_in_expression1729 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_UNARY_PLUS_in_expression1757 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1761 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_UNARY_MIN_in_expression1798 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1802 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEGATE_in_expression1837 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1841 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Integer_in_expression1880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Number_in_expression1956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Bool_in_expression2034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Null_in_expression2115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NaN_in_expression2169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Infinity_in_expression2226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_expression2278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LIST_in_list2342 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprList_in_list2344 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MAP_in_map2367 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprMap_in_map2369 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2392 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_functionCall_in_lookup2394 = new BitSet(new long[]{0x0000000004000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2398 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2410 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_list_in_lookup2412 = new BitSet(new long[]{0x0000000004000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2416 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2436 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_map_in_lookup2438 = new BitSet(new long[]{0x0000000004000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2442 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2465 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_lookup2467 = new BitSet(new long[]{0x0000000004000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2471 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2485 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_lookup2487 = new BitSet(new long[]{0x0000000004000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2491 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2505 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_String_in_lookup2507 = new BitSet(new long[]{0x0000000004000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2511 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TAILS_in_indexes2550 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_tail_in_indexes2555 = new BitSet(new long[]{0x0000000001C00008L});
    public static final BitSet FOLLOW_INDEX_in_tail2580 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_tail2582 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ATTRIBUTE_in_tail2601 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_tail2603 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CALL_in_tail2618 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_tail2620 = new BitSet(new long[]{0x0000000000002008L});
    public static final BitSet FOLLOW_exprList_in_tail2622 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Var_in_variableDef2646 = new BitSet(new long[]{0x0000000000000002L});

}