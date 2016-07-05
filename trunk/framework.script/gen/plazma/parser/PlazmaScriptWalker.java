// $ANTLR 3.2 Sep 23, 2009 12:02:23 grammar/PlazmaScriptWalker.g 2016-07-05 11:24:22

  package plazma.parser;
  import plazma.*;
  import plazma.ast.*;
  import plazma.ast.functions.*;
  import plazma.ast.operators.*;
  import plazma.ast.values.*;  
  import java.util.Map;
  import java.util.HashMap;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class PlazmaScriptWalker extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BLOCK", "RETURN", "STATEMENTS", "ASSIGNMENT", "FUNC_CALL", "EXP", "EXP_PAIR", "EXP_MAP", "EXP_LIST", "ID_LIST", "IF", "TERNARY", "UNARY_MIN", "NEGATE", "FUNCTION", "INDEXES", "INDEX", "ATTRIBUTE", "CALL", "TAIL", "TAILS", "MAP", "LIST", "LOOKUP", "BREAK", "CONTINUE", "Return", "Break", "Continue", "Identifier", "Println", "Print", "Assert", "Date", "List", "If", "Else", "Var", "Def", "For", "While", "In", "RangeE", "Range", "Integer", "Number", "Bool", "Null", "String", "XorWord", "Or", "BitOr", "OrWord", "And", "BitAnd", "AndWord", "Equals", "NEquals", "GTEquals", "LTEquals", "Pow", "Not", "NotWord", "GT", "LT", "Add", "Subtract", "Multiply", "Divide", "Modulus", "OBrace", "CBrace", "OBracket", "CBracket", "OParen", "CParen", "SColon", "Assign", "Comma", "QMark", "Colon", "Int", "Digit", "ContextIdentifier", "Comment", "Space", "YYYY", "MM", "DD", "'.'"
    };
    public static final int FUNCTION=18;
    public static final int LT=68;
    public static final int TERNARY=15;
    public static final int EXP_LIST=12;
    public static final int Date=37;
    public static final int EOF=-1;
    public static final int DD=92;
    public static final int QMark=83;
    public static final int NotWord=66;
    public static final int BREAK=28;
    public static final int Identifier=33;
    public static final int Int=85;
    public static final int FUNC_CALL=8;
    public static final int T__93=93;
    public static final int CParen=79;
    public static final int EXP=9;
    public static final int Comment=88;
    public static final int MM=91;
    public static final int CBrace=75;
    public static final int RETURN=5;
    public static final int OrWord=56;
    public static final int Null=51;
    public static final int ContextIdentifier=87;
    public static final int CBracket=77;
    public static final int Println=34;
    public static final int Bool=50;
    public static final int Modulus=73;
    public static final int Colon=84;
    public static final int AndWord=59;
    public static final int LIST=26;
    public static final int Def=42;
    public static final int RangeE=46;
    public static final int LOOKUP=27;
    public static final int Range=47;
    public static final int Break=31;
    public static final int BitOr=55;
    public static final int GT=67;
    public static final int STATEMENTS=6;
    public static final int CALL=22;
    public static final int Else=40;
    public static final int Equals=60;
    public static final int Var=41;
    public static final int XorWord=53;
    public static final int OParen=78;
    public static final int YYYY=90;
    public static final int Assert=36;
    public static final int ATTRIBUTE=21;
    public static final int While=44;
    public static final int ID_LIST=13;
    public static final int Add=69;
    public static final int TAIL=23;
    public static final int IF=14;
    public static final int Space=89;
    public static final int INDEX=20;
    public static final int Assign=81;
    public static final int EXP_MAP=11;
    public static final int Number=49;
    public static final int CONTINUE=29;
    public static final int Print=35;
    public static final int GTEquals=62;
    public static final int String=52;
    public static final int Or=54;
    public static final int Return=30;
    public static final int If=39;
    public static final int And=57;
    public static final int In=45;
    public static final int NEquals=61;
    public static final int Continue=32;
    public static final int Subtract=70;
    public static final int EXP_PAIR=10;
    public static final int BitAnd=58;
    public static final int Multiply=71;
    public static final int OBrace=74;
    public static final int INDEXES=19;
    public static final int NEGATE=17;
    public static final int Digit=86;
    public static final int For=43;
    public static final int Divide=72;
    public static final int List=38;
    public static final int TAILS=24;
    public static final int SColon=80;
    public static final int OBracket=76;
    public static final int BLOCK=4;
    public static final int MAP=25;
    public static final int Not=65;
    public static final int UNARY_MIN=16;
    public static final int ASSIGNMENT=7;
    public static final int Comma=82;
    public static final int Integer=48;
    public static final int Pow=64;
    public static final int LTEquals=63;

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

                if ( ((LA2_0>=TERNARY && LA2_0<=NEGATE)||LA2_0==LOOKUP||(LA2_0>=In && LA2_0<=Null)||(LA2_0>=XorWord && LA2_0<=Pow)||(LA2_0>=GT && LA2_0<=Modulus)) ) {
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
    // grammar/PlazmaScriptWalker.g:80:1: functionCall returns [LNode node] : ( ^( FUNC_CALL Identifier ( exprList )? ) | ^( FUNC_CALL Println ( expression )? ) | ^( FUNC_CALL Print expression ) | ^( FUNC_CALL Assert expression ) | ^( FUNC_CALL Date ( exprList )? ) | ^( FUNC_CALL List ( exprList )? ) );
    public final LNode functionCall() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier14=null;
        java.util.List<LNode> exprList15 = null;

        LNode expression16 = null;

        LNode expression17 = null;

        LNode expression18 = null;

        java.util.List<LNode> exprList19 = null;

        java.util.List<LNode> exprList20 = null;


        try {
            // grammar/PlazmaScriptWalker.g:81:3: ( ^( FUNC_CALL Identifier ( exprList )? ) | ^( FUNC_CALL Println ( expression )? ) | ^( FUNC_CALL Print expression ) | ^( FUNC_CALL Assert expression ) | ^( FUNC_CALL Date ( exprList )? ) | ^( FUNC_CALL List ( exprList )? ) )
            int alt10=6;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==FUNC_CALL) ) {
                int LA10_1 = input.LA(2);

                if ( (LA10_1==DOWN) ) {
                    switch ( input.LA(3) ) {
                    case Identifier:
                        {
                        alt10=1;
                        }
                        break;
                    case Println:
                        {
                        alt10=2;
                        }
                        break;
                    case Print:
                        {
                        alt10=3;
                        }
                        break;
                    case Assert:
                        {
                        alt10=4;
                        }
                        break;
                    case Date:
                        {
                        alt10=5;
                        }
                        break;
                    case List:
                        {
                        alt10=6;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 10, 2, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
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

                    if ( ((LA7_0>=TERNARY && LA7_0<=NEGATE)||LA7_0==LOOKUP||(LA7_0>=In && LA7_0<=Null)||(LA7_0>=XorWord && LA7_0<=Pow)||(LA7_0>=GT && LA7_0<=Modulus)) ) {
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
    // grammar/PlazmaScriptWalker.g:89:1: ifStatement returns [LNode node] : ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? ) ;
    public final LNode ifStatement() throws RecognitionException {
        LNode node = null;

        IfNode ifNode = new IfNode();
        try {
            // grammar/PlazmaScriptWalker.g:92:3: ( ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? ) )
            // grammar/PlazmaScriptWalker.g:92:6: ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? )
            {
            match(input,IF,FOLLOW_IF_in_ifStatement487); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_ifStat_in_ifStatement489);
            ifStat(ifNode);

            state._fsp--;

            // grammar/PlazmaScriptWalker.g:92:26: ( elseIfStat[ifNode] )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==EXP) ) {
                    int LA11_1 = input.LA(2);

                    if ( (LA11_1==DOWN) ) {
                        int LA11_3 = input.LA(3);

                        if ( ((LA11_3>=TERNARY && LA11_3<=NEGATE)||LA11_3==LOOKUP||(LA11_3>=In && LA11_3<=Null)||(LA11_3>=XorWord && LA11_3<=Pow)||(LA11_3>=GT && LA11_3<=Modulus)) ) {
                            alt11=1;
                        }


                    }


                }


                switch (alt11) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:92:27: elseIfStat[ifNode]
            	    {
            	    pushFollow(FOLLOW_elseIfStat_in_ifStatement493);
            	    elseIfStat(ifNode);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            // grammar/PlazmaScriptWalker.g:92:48: ( elseStat[ifNode] )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==EXP) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:92:49: elseStat[ifNode]
                    {
                    pushFollow(FOLLOW_elseStat_in_ifStatement499);
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
    // grammar/PlazmaScriptWalker.g:95:1: ifStat[IfNode parent] : ^( EXP expression block ) ;
    public final void ifStat(IfNode parent) throws RecognitionException {
        LNode expression21 = null;

        LNode block22 = null;


        try {
            // grammar/PlazmaScriptWalker.g:96:3: ( ^( EXP expression block ) )
            // grammar/PlazmaScriptWalker.g:96:6: ^( EXP expression block )
            {
            match(input,EXP,FOLLOW_EXP_in_ifStat519); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_ifStat521);
            expression21=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_ifStat523);
            block22=block();

            state._fsp--;


            match(input, Token.UP, null); 
            parent.addChoice(expression21, block22);

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
    // grammar/PlazmaScriptWalker.g:99:1: elseIfStat[IfNode parent] : ^( EXP expression block ) ;
    public final void elseIfStat(IfNode parent) throws RecognitionException {
        LNode expression23 = null;

        LNode block24 = null;


        try {
            // grammar/PlazmaScriptWalker.g:100:3: ( ^( EXP expression block ) )
            // grammar/PlazmaScriptWalker.g:100:6: ^( EXP expression block )
            {
            match(input,EXP,FOLLOW_EXP_in_elseIfStat542); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_elseIfStat544);
            expression23=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_elseIfStat546);
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
    // $ANTLR end "elseIfStat"


    // $ANTLR start "elseStat"
    // grammar/PlazmaScriptWalker.g:103:1: elseStat[IfNode parent] : ^( EXP block ) ;
    public final void elseStat(IfNode parent) throws RecognitionException {
        LNode block25 = null;


        try {
            // grammar/PlazmaScriptWalker.g:104:3: ( ^( EXP block ) )
            // grammar/PlazmaScriptWalker.g:104:6: ^( EXP block )
            {
            match(input,EXP,FOLLOW_EXP_in_elseStat565); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_block_in_elseStat567);
            block25=block();

            state._fsp--;


            match(input, Token.UP, null); 
            parent.addChoice(new BooleanNode(true), block25);

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
    // grammar/PlazmaScriptWalker.g:111:1: forStatement returns [LNode node] : ^( For Identifier a= expression block ) ;
    public final LNode forStatement() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier26=null;
        LNode a = null;

        LNode block27 = null;


        try {
            // grammar/PlazmaScriptWalker.g:112:3: ( ^( For Identifier a= expression block ) )
            // grammar/PlazmaScriptWalker.g:112:6: ^( For Identifier a= expression block )
            {
            match(input,For,FOLLOW_For_in_forStatement598); 

            match(input, Token.DOWN, null); 
            Identifier26=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_forStatement600); 
            pushFollow(FOLLOW_expression_in_forStatement604);
            a=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_forStatement606);
            block27=block();

            state._fsp--;


            match(input, Token.UP, null); 
            node = new ForStatementNode2((Identifier26!=null?Identifier26.getText():null), a, block27, currentScope);

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
    // grammar/PlazmaScriptWalker.g:116:1: whileStatement returns [LNode node] : ^( While expression block ) ;
    public final LNode whileStatement() throws RecognitionException {
        LNode node = null;

        LNode expression28 = null;

        LNode block29 = null;


        try {
            // grammar/PlazmaScriptWalker.g:117:3: ( ^( While expression block ) )
            // grammar/PlazmaScriptWalker.g:117:6: ^( While expression block )
            {
            match(input,While,FOLLOW_While_in_whileStatement631); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_whileStatement633);
            expression28=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_whileStatement635);
            block29=block();

            state._fsp--;


            match(input, Token.UP, null); 
            node = new WhileStatementNode(expression28, block29);

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
    // grammar/PlazmaScriptWalker.g:120:1: idList returns [java.util.List<String> i] : ^( ID_LIST ( Identifier )+ ) ;
    public final java.util.List<String> idList() throws RecognitionException {
        java.util.List<String> i = null;

        CommonTree Identifier30=null;

        i = new java.util.ArrayList<String>();
        try {
            // grammar/PlazmaScriptWalker.g:122:3: ( ^( ID_LIST ( Identifier )+ ) )
            // grammar/PlazmaScriptWalker.g:122:6: ^( ID_LIST ( Identifier )+ )
            {
            match(input,ID_LIST,FOLLOW_ID_LIST_in_idList662); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:122:16: ( Identifier )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==Identifier) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:122:17: Identifier
            	    {
            	    Identifier30=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_idList665); 
            	    i.add((Identifier30!=null?Identifier30.getText():null));

            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
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
    // grammar/PlazmaScriptWalker.g:125:1: exprList returns [java.util.List<LNode> e] : ^( EXP_LIST ( expression )+ ) ;
    public final java.util.List<LNode> exprList() throws RecognitionException {
        java.util.List<LNode> e = null;

        LNode expression31 = null;


        e = new java.util.ArrayList<LNode>();
        try {
            // grammar/PlazmaScriptWalker.g:127:3: ( ^( EXP_LIST ( expression )+ ) )
            // grammar/PlazmaScriptWalker.g:127:6: ^( EXP_LIST ( expression )+ )
            {
            match(input,EXP_LIST,FOLLOW_EXP_LIST_in_exprList695); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:127:17: ( expression )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0>=TERNARY && LA14_0<=NEGATE)||LA14_0==LOOKUP||(LA14_0>=In && LA14_0<=Null)||(LA14_0>=XorWord && LA14_0<=Pow)||(LA14_0>=GT && LA14_0<=Modulus)) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:127:18: expression
            	    {
            	    pushFollow(FOLLOW_expression_in_exprList698);
            	    expression31=expression();

            	    state._fsp--;

            	    e.add(expression31);

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
        return e;
    }
    // $ANTLR end "exprList"


    // $ANTLR start "exprPair"
    // grammar/PlazmaScriptWalker.g:129:1: exprPair returns [PairNode node] : ^( EXP_PAIR k= expression v= expression ) ;
    public final PairNode exprPair() throws RecognitionException {
        PairNode node = null;

        LNode k = null;

        LNode v = null;


        try {
            // grammar/PlazmaScriptWalker.g:130:3: ( ^( EXP_PAIR k= expression v= expression ) )
            // grammar/PlazmaScriptWalker.g:130:6: ^( EXP_PAIR k= expression v= expression )
            {
            match(input,EXP_PAIR,FOLLOW_EXP_PAIR_in_exprPair721); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_exprPair725);
            k=expression();

            state._fsp--;

            pushFollow(FOLLOW_expression_in_exprPair729);
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
    // grammar/PlazmaScriptWalker.g:133:1: exprMap returns [java.util.List<PairNode> e] : ^( EXP_MAP ( exprPair )+ ) ;
    public final java.util.List<PairNode> exprMap() throws RecognitionException {
        java.util.List<PairNode> e = null;

        PairNode exprPair32 = null;


        e = new java.util.ArrayList<PairNode>();
        try {
            // grammar/PlazmaScriptWalker.g:135:3: ( ^( EXP_MAP ( exprPair )+ ) )
            // grammar/PlazmaScriptWalker.g:135:6: ^( EXP_MAP ( exprPair )+ )
            {
            match(input,EXP_MAP,FOLLOW_EXP_MAP_in_exprMap757); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:135:16: ( exprPair )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==EXP_PAIR) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:135:17: exprPair
            	    {
            	    pushFollow(FOLLOW_exprPair_in_exprMap760);
            	    exprPair32=exprPair();

            	    state._fsp--;

            	    e.add(exprPair32);

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
    // $ANTLR end "exprMap"


    // $ANTLR start "expression"
    // grammar/PlazmaScriptWalker.g:139:1: expression returns [LNode node] : ( ^( TERNARY a= expression b= expression c= expression ) | ^( In a= expression b= expression ) | ^( RangeE a= expression b= expression ) | ^( Range a= expression b= expression ) | ^( 'xor' a= expression b= expression ) | ^( '||' a= expression b= expression ) | ^( '|' a= expression b= expression ) | ^( 'or' a= expression b= expression ) | ^( '&&' a= expression b= expression ) | ^( '&' a= expression b= expression ) | ^( 'and' a= expression b= expression ) | ^( '==' a= expression b= expression ) | ^( '!=' a= expression b= expression ) | ^( '>=' a= expression b= expression ) | ^( '<=' a= expression b= expression ) | ^( '>' a= expression b= expression ) | ^( '<' a= expression b= expression ) | ^( '+' a= expression b= expression ) | ^( '-' a= expression b= expression ) | ^( '*' a= expression b= expression ) | ^( '/' a= expression b= expression ) | ^( '%' a= expression b= expression ) | ^( '^' a= expression b= expression ) | ^( UNARY_MIN a= expression ) | ^( NEGATE a= expression ) | Integer | Number | Bool | Null | lookup );
    public final LNode expression() throws RecognitionException {
        LNode node = null;

        CommonTree Integer33=null;
        CommonTree Number34=null;
        CommonTree Bool35=null;
        LNode a = null;

        LNode b = null;

        LNode c = null;

        LNode lookup36 = null;


        try {
            // grammar/PlazmaScriptWalker.g:140:3: ( ^( TERNARY a= expression b= expression c= expression ) | ^( In a= expression b= expression ) | ^( RangeE a= expression b= expression ) | ^( Range a= expression b= expression ) | ^( 'xor' a= expression b= expression ) | ^( '||' a= expression b= expression ) | ^( '|' a= expression b= expression ) | ^( 'or' a= expression b= expression ) | ^( '&&' a= expression b= expression ) | ^( '&' a= expression b= expression ) | ^( 'and' a= expression b= expression ) | ^( '==' a= expression b= expression ) | ^( '!=' a= expression b= expression ) | ^( '>=' a= expression b= expression ) | ^( '<=' a= expression b= expression ) | ^( '>' a= expression b= expression ) | ^( '<' a= expression b= expression ) | ^( '+' a= expression b= expression ) | ^( '-' a= expression b= expression ) | ^( '*' a= expression b= expression ) | ^( '/' a= expression b= expression ) | ^( '%' a= expression b= expression ) | ^( '^' a= expression b= expression ) | ^( UNARY_MIN a= expression ) | ^( NEGATE a= expression ) | Integer | Number | Bool | Null | lookup )
            int alt16=30;
            switch ( input.LA(1) ) {
            case TERNARY:
                {
                alt16=1;
                }
                break;
            case In:
                {
                alt16=2;
                }
                break;
            case RangeE:
                {
                alt16=3;
                }
                break;
            case Range:
                {
                alt16=4;
                }
                break;
            case XorWord:
                {
                alt16=5;
                }
                break;
            case Or:
                {
                alt16=6;
                }
                break;
            case BitOr:
                {
                alt16=7;
                }
                break;
            case OrWord:
                {
                alt16=8;
                }
                break;
            case And:
                {
                alt16=9;
                }
                break;
            case BitAnd:
                {
                alt16=10;
                }
                break;
            case AndWord:
                {
                alt16=11;
                }
                break;
            case Equals:
                {
                alt16=12;
                }
                break;
            case NEquals:
                {
                alt16=13;
                }
                break;
            case GTEquals:
                {
                alt16=14;
                }
                break;
            case LTEquals:
                {
                alt16=15;
                }
                break;
            case GT:
                {
                alt16=16;
                }
                break;
            case LT:
                {
                alt16=17;
                }
                break;
            case Add:
                {
                alt16=18;
                }
                break;
            case Subtract:
                {
                alt16=19;
                }
                break;
            case Multiply:
                {
                alt16=20;
                }
                break;
            case Divide:
                {
                alt16=21;
                }
                break;
            case Modulus:
                {
                alt16=22;
                }
                break;
            case Pow:
                {
                alt16=23;
                }
                break;
            case UNARY_MIN:
                {
                alt16=24;
                }
                break;
            case NEGATE:
                {
                alt16=25;
                }
                break;
            case Integer:
                {
                alt16=26;
                }
                break;
            case Number:
                {
                alt16=27;
                }
                break;
            case Bool:
                {
                alt16=28;
                }
                break;
            case Null:
                {
                alt16=29;
                }
                break;
            case LOOKUP:
                {
                alt16=30;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }

            switch (alt16) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:140:6: ^( TERNARY a= expression b= expression c= expression )
                    {
                    match(input,TERNARY,FOLLOW_TERNARY_in_expression785); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression789);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression793);
                    b=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression797);
                    c=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new TernaryNode(a, b, c);

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:141:6: ^( In a= expression b= expression )
                    {
                    match(input,In,FOLLOW_In_in_expression808); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression812);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression816);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new InNode(a, b);

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:143:6: ^( RangeE a= expression b= expression )
                    {
                    match(input,RangeE,FOLLOW_RangeE_in_expression848); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression852);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression856);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new RangeENode(a, b);

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:144:6: ^( Range a= expression b= expression )
                    {
                    match(input,Range,FOLLOW_Range_in_expression882); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression886);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression890);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new RangeNode(a, b);

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:146:6: ^( 'xor' a= expression b= expression )
                    {
                    match(input,XorWord,FOLLOW_XorWord_in_expression920); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression924);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression928);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new XorNode(a, b);

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScriptWalker.g:147:6: ^( '||' a= expression b= expression )
                    {
                    match(input,Or,FOLLOW_Or_in_expression954); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression958);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression962);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new OrNode(a, b);

                    }
                    break;
                case 7 :
                    // grammar/PlazmaScriptWalker.g:148:6: ^( '|' a= expression b= expression )
                    {
                    match(input,BitOr,FOLLOW_BitOr_in_expression989); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression993);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression997);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new BitOrNode(a, b);

                    }
                    break;
                case 8 :
                    // grammar/PlazmaScriptWalker.g:149:6: ^( 'or' a= expression b= expression )
                    {
                    match(input,OrWord,FOLLOW_OrWord_in_expression1027); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1031);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1035);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new OrNode(a, b);

                    }
                    break;
                case 9 :
                    // grammar/PlazmaScriptWalker.g:150:6: ^( '&&' a= expression b= expression )
                    {
                    match(input,And,FOLLOW_And_in_expression1064); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1068);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1072);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AndNode(a, b);

                    }
                    break;
                case 10 :
                    // grammar/PlazmaScriptWalker.g:151:6: ^( '&' a= expression b= expression )
                    {
                    match(input,BitAnd,FOLLOW_BitAnd_in_expression1099); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1103);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1107);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new BitAndNode(a, b);

                    }
                    break;
                case 11 :
                    // grammar/PlazmaScriptWalker.g:152:6: ^( 'and' a= expression b= expression )
                    {
                    match(input,AndWord,FOLLOW_AndWord_in_expression1137); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1141);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1145);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AndNode(a, b);

                    }
                    break;
                case 12 :
                    // grammar/PlazmaScriptWalker.g:153:6: ^( '==' a= expression b= expression )
                    {
                    match(input,Equals,FOLLOW_Equals_in_expression1173); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1177);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1181);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new EQNode(a, b);

                    }
                    break;
                case 13 :
                    // grammar/PlazmaScriptWalker.g:154:6: ^( '!=' a= expression b= expression )
                    {
                    match(input,NEquals,FOLLOW_NEquals_in_expression1208); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1212);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1216);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new NENode(a, b);

                    }
                    break;
                case 14 :
                    // grammar/PlazmaScriptWalker.g:155:6: ^( '>=' a= expression b= expression )
                    {
                    match(input,GTEquals,FOLLOW_GTEquals_in_expression1243); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1247);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1251);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new GTENode(a, b);

                    }
                    break;
                case 15 :
                    // grammar/PlazmaScriptWalker.g:156:6: ^( '<=' a= expression b= expression )
                    {
                    match(input,LTEquals,FOLLOW_LTEquals_in_expression1278); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1282);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1286);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new LTENode(a, b);

                    }
                    break;
                case 16 :
                    // grammar/PlazmaScriptWalker.g:157:6: ^( '>' a= expression b= expression )
                    {
                    match(input,GT,FOLLOW_GT_in_expression1313); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1317);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1321);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new GTNode(a, b);

                    }
                    break;
                case 17 :
                    // grammar/PlazmaScriptWalker.g:158:6: ^( '<' a= expression b= expression )
                    {
                    match(input,LT,FOLLOW_LT_in_expression1349); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1353);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1357);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new LTNode(a, b);

                    }
                    break;
                case 18 :
                    // grammar/PlazmaScriptWalker.g:159:6: ^( '+' a= expression b= expression )
                    {
                    match(input,Add,FOLLOW_Add_in_expression1385); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1389);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1393);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AddNode(a, b);

                    }
                    break;
                case 19 :
                    // grammar/PlazmaScriptWalker.g:160:6: ^( '-' a= expression b= expression )
                    {
                    match(input,Subtract,FOLLOW_Subtract_in_expression1421); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1425);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1429);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new SubNode(a, b);

                    }
                    break;
                case 20 :
                    // grammar/PlazmaScriptWalker.g:161:6: ^( '*' a= expression b= expression )
                    {
                    match(input,Multiply,FOLLOW_Multiply_in_expression1457); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1461);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1465);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new MulNode(a, b);

                    }
                    break;
                case 21 :
                    // grammar/PlazmaScriptWalker.g:162:6: ^( '/' a= expression b= expression )
                    {
                    match(input,Divide,FOLLOW_Divide_in_expression1493); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1497);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1501);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new DivNode(a, b);

                    }
                    break;
                case 22 :
                    // grammar/PlazmaScriptWalker.g:164:6: ^( '%' a= expression b= expression )
                    {
                    match(input,Modulus,FOLLOW_Modulus_in_expression1532); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1536);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1540);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new ModNode(a, b);

                    }
                    break;
                case 23 :
                    // grammar/PlazmaScriptWalker.g:165:6: ^( '^' a= expression b= expression )
                    {
                    match(input,Pow,FOLLOW_Pow_in_expression1568); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1572);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1576);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new PowNode(a, b);

                    }
                    break;
                case 24 :
                    // grammar/PlazmaScriptWalker.g:166:6: ^( UNARY_MIN a= expression )
                    {
                    match(input,UNARY_MIN,FOLLOW_UNARY_MIN_in_expression1604); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1608);
                    a=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new UnaryMinusNode(a);

                    }
                    break;
                case 25 :
                    // grammar/PlazmaScriptWalker.g:167:6: ^( NEGATE a= expression )
                    {
                    match(input,NEGATE,FOLLOW_NEGATE_in_expression1643); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1647);
                    a=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new NotNode(a);

                    }
                    break;
                case 26 :
                    // grammar/PlazmaScriptWalker.g:169:6: Integer
                    {
                    Integer33=(CommonTree)match(input,Integer,FOLLOW_Integer_in_expression1686); 
                    node = new IntegerNode((Integer33!=null?Integer33.getText():null));

                    }
                    break;
                case 27 :
                    // grammar/PlazmaScriptWalker.g:170:6: Number
                    {
                    Number34=(CommonTree)match(input,Number,FOLLOW_Number_in_expression1762); 
                    node = new NumberNode((Number34!=null?Number34.getText():null));

                    }
                    break;
                case 28 :
                    // grammar/PlazmaScriptWalker.g:171:6: Bool
                    {
                    Bool35=(CommonTree)match(input,Bool,FOLLOW_Bool_in_expression1840); 
                    node = new BooleanNode((Bool35!=null?Bool35.getText():null));

                    }
                    break;
                case 29 :
                    // grammar/PlazmaScriptWalker.g:172:6: Null
                    {
                    match(input,Null,FOLLOW_Null_in_expression1921); 
                    node = new NullNode();

                    }
                    break;
                case 30 :
                    // grammar/PlazmaScriptWalker.g:173:6: lookup
                    {
                    pushFollow(FOLLOW_lookup_in_expression1975);
                    lookup36=lookup();

                    state._fsp--;

                    node = lookup36;

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
    // grammar/PlazmaScriptWalker.g:176:1: list returns [LNode node] : ^( LIST ( exprList )? ) ;
    public final LNode list() throws RecognitionException {
        LNode node = null;

        java.util.List<LNode> exprList37 = null;


        try {
            // grammar/PlazmaScriptWalker.g:177:3: ( ^( LIST ( exprList )? ) )
            // grammar/PlazmaScriptWalker.g:177:6: ^( LIST ( exprList )? )
            {
            match(input,LIST,FOLLOW_LIST_in_list2039); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // grammar/PlazmaScriptWalker.g:177:13: ( exprList )?
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==EXP_LIST) ) {
                    alt17=1;
                }
                switch (alt17) {
                    case 1 :
                        // grammar/PlazmaScriptWalker.g:177:13: exprList
                        {
                        pushFollow(FOLLOW_exprList_in_list2041);
                        exprList37=exprList();

                        state._fsp--;


                        }
                        break;

                }


                match(input, Token.UP, null); 
            }
            node = new ListNode(exprList37);

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
    // grammar/PlazmaScriptWalker.g:180:1: map returns [LNode node] : ^( MAP ( exprMap )? ) ;
    public final LNode map() throws RecognitionException {
        LNode node = null;

        java.util.List<PairNode> exprMap38 = null;


        try {
            // grammar/PlazmaScriptWalker.g:181:3: ( ^( MAP ( exprMap )? ) )
            // grammar/PlazmaScriptWalker.g:181:6: ^( MAP ( exprMap )? )
            {
            match(input,MAP,FOLLOW_MAP_in_map2064); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // grammar/PlazmaScriptWalker.g:181:12: ( exprMap )?
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==EXP_MAP) ) {
                    alt18=1;
                }
                switch (alt18) {
                    case 1 :
                        // grammar/PlazmaScriptWalker.g:181:12: exprMap
                        {
                        pushFollow(FOLLOW_exprMap_in_map2066);
                        exprMap38=exprMap();

                        state._fsp--;


                        }
                        break;

                }


                match(input, Token.UP, null); 
            }
            node = new MapNode(exprMap38);

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
    // grammar/PlazmaScriptWalker.g:184:1: lookup returns [LNode node] : ( ^( LOOKUP functionCall (i= indexes )? ) | ^( LOOKUP list (i= indexes )? ) | ^( LOOKUP map (i= indexes )? ) | ^( LOOKUP expression (i= indexes )? ) | ^( LOOKUP Identifier (i= indexes )? ) | ^( LOOKUP String (i= indexes )? ) );
    public final LNode lookup() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier43=null;
        CommonTree String44=null;
        java.util.List<LNode> i = null;

        LNode functionCall39 = null;

        LNode list40 = null;

        LNode map41 = null;

        LNode expression42 = null;


        try {
            // grammar/PlazmaScriptWalker.g:185:3: ( ^( LOOKUP functionCall (i= indexes )? ) | ^( LOOKUP list (i= indexes )? ) | ^( LOOKUP map (i= indexes )? ) | ^( LOOKUP expression (i= indexes )? ) | ^( LOOKUP Identifier (i= indexes )? ) | ^( LOOKUP String (i= indexes )? ) )
            int alt25=6;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==LOOKUP) ) {
                int LA25_1 = input.LA(2);

                if ( (LA25_1==DOWN) ) {
                    switch ( input.LA(3) ) {
                    case Identifier:
                        {
                        alt25=5;
                        }
                        break;
                    case String:
                        {
                        alt25=6;
                        }
                        break;
                    case TERNARY:
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
                        alt25=4;
                        }
                        break;
                    case FUNC_CALL:
                        {
                        alt25=1;
                        }
                        break;
                    case MAP:
                        {
                        alt25=3;
                        }
                        break;
                    case LIST:
                        {
                        alt25=2;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 25, 2, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 25, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }
            switch (alt25) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:185:6: ^( LOOKUP functionCall (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2089); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_functionCall_in_lookup2091);
                    functionCall39=functionCall();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:185:29: (i= indexes )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==TAILS) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:185:29: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2095);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(functionCall39, i) : functionCall39;

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:186:6: ^( LOOKUP list (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2107); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_list_in_lookup2109);
                    list40=list();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:186:21: (i= indexes )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==TAILS) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:186:21: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2113);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(list40, i) : list40;

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:187:6: ^( LOOKUP map (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2133); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_map_in_lookup2135);
                    map41=map();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:187:20: (i= indexes )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==TAILS) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:187:20: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2139);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(map41, i) : map41;

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:188:6: ^( LOOKUP expression (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2162); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_lookup2164);
                    expression42=expression();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:188:27: (i= indexes )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==TAILS) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:188:27: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2168);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(expression42, i) : expression42;

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:189:6: ^( LOOKUP Identifier (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2182); 

                    match(input, Token.DOWN, null); 
                    Identifier43=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_lookup2184); 
                    // grammar/PlazmaScriptWalker.g:189:27: (i= indexes )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==TAILS) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:189:27: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2188);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(new IdentifierNode((Identifier43!=null?Identifier43.getText():null), currentScope, globalScope), i) : new IdentifierNode((Identifier43!=null?Identifier43.getText():null), currentScope, globalScope);

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScriptWalker.g:190:6: ^( LOOKUP String (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup2202); 

                    match(input, Token.DOWN, null); 
                    String44=(CommonTree)match(input,String,FOLLOW_String_in_lookup2204); 
                    // grammar/PlazmaScriptWalker.g:190:23: (i= indexes )?
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( (LA24_0==TAILS) ) {
                        alt24=1;
                    }
                    switch (alt24) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:190:23: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2208);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(new StringNode((String44!=null?String44.getText():null)), i) : new StringNode((String44!=null?String44.getText():null));

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
    // grammar/PlazmaScriptWalker.g:193:1: indexes returns [java.util.List<LNode> e] : ^( TAILS ( tail )+ ) ;
    public final java.util.List<LNode> indexes() throws RecognitionException {
        java.util.List<LNode> e = null;

        LNode tail45 = null;


        e = new java.util.ArrayList<LNode>();
        try {
            // grammar/PlazmaScriptWalker.g:196:3: ( ^( TAILS ( tail )+ ) )
            // grammar/PlazmaScriptWalker.g:196:8: ^( TAILS ( tail )+ )
            {
            match(input,TAILS,FOLLOW_TAILS_in_indexes2247); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:196:18: ( tail )+
            int cnt26=0;
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( ((LA26_0>=INDEX && LA26_0<=CALL)) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:196:19: tail
            	    {
            	    pushFollow(FOLLOW_tail_in_indexes2252);
            	    tail45=tail();

            	    state._fsp--;

            	    e.add(tail45);

            	    }
            	    break;

            	default :
            	    if ( cnt26 >= 1 ) break loop26;
                        EarlyExitException eee =
                            new EarlyExitException(26, input);
                        throw eee;
                }
                cnt26++;
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
    // grammar/PlazmaScriptWalker.g:200:1: tail returns [LNode node] : ( ^( INDEX expression ) | ^( ATTRIBUTE Identifier ) | ^( CALL Identifier ( exprList )? ) );
    public final LNode tail() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier47=null;
        CommonTree Identifier48=null;
        LNode expression46 = null;

        java.util.List<LNode> exprList49 = null;


        try {
            // grammar/PlazmaScriptWalker.g:201:2: ( ^( INDEX expression ) | ^( ATTRIBUTE Identifier ) | ^( CALL Identifier ( exprList )? ) )
            int alt28=3;
            switch ( input.LA(1) ) {
            case INDEX:
                {
                alt28=1;
                }
                break;
            case ATTRIBUTE:
                {
                alt28=2;
                }
                break;
            case CALL:
                {
                alt28=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }

            switch (alt28) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:201:4: ^( INDEX expression )
                    {
                    match(input,INDEX,FOLLOW_INDEX_in_tail2277); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_tail2279);
                    expression46=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = expression46;

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:202:4: ^( ATTRIBUTE Identifier )
                    {
                    match(input,ATTRIBUTE,FOLLOW_ATTRIBUTE_in_tail2298); 

                    match(input, Token.DOWN, null); 
                    Identifier47=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_tail2300); 

                    match(input, Token.UP, null); 
                    node = new StringNode((Identifier47!=null?Identifier47.getText():null));

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:203:4: ^( CALL Identifier ( exprList )? )
                    {
                    match(input,CALL,FOLLOW_CALL_in_tail2315); 

                    match(input, Token.DOWN, null); 
                    Identifier48=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_tail2317); 
                    // grammar/PlazmaScriptWalker.g:203:22: ( exprList )?
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==EXP_LIST) ) {
                        alt27=1;
                    }
                    switch (alt27) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:203:22: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_tail2319);
                            exprList49=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new MethodCallNode((Identifier48!=null?Identifier48.getText():null), exprList49, functions, globalScope);

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
    // grammar/PlazmaScriptWalker.g:206:1: variableDef returns [LNode node] : Var ;
    public final LNode variableDef() throws RecognitionException {
        LNode node = null;

        CommonTree Var50=null;

        try {
            // grammar/PlazmaScriptWalker.g:207:3: ( Var )
            // grammar/PlazmaScriptWalker.g:207:5: Var
            {
            Var50=(CommonTree)match(input,Var,FOLLOW_Var_in_variableDef2343); 
            node = new VariableDefNode((Var50!=null?Var50.getText():null), null);

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


 

    public static final BitSet FOLLOW_block_in_walk50 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BLOCK_in_block81 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STATEMENTS_in_block84 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_block87 = new BitSet(new long[]{0x0000180188004188L});
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
    public static final BitSet FOLLOW_variableDef_in_assignment319 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_Identifier_in_assignment322 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_indexes_in_assignment324 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_assignment327 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall349 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_functionCall351 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall353 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall365 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Println_in_functionCall367 = new BitSet(new long[]{0xFFEFE00009038008L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_functionCall369 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall382 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Print_in_functionCall384 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_functionCall386 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall401 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Assert_in_functionCall403 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_functionCall405 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall419 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Date_in_functionCall421 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall423 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall441 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_List_in_functionCall443 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall445 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IF_in_ifStatement487 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ifStat_in_ifStatement489 = new BitSet(new long[]{0x0000000000000208L});
    public static final BitSet FOLLOW_elseIfStat_in_ifStatement493 = new BitSet(new long[]{0x0000000000000208L});
    public static final BitSet FOLLOW_elseStat_in_ifStatement499 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_ifStat519 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_ifStat521 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_ifStat523 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_elseIfStat542 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_elseIfStat544 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_elseIfStat546 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_elseStat565 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_block_in_elseStat567 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_For_in_forStatement598 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_forStatement600 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_forStatement604 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_forStatement606 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_While_in_whileStatement631 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_whileStatement633 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_whileStatement635 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_LIST_in_idList662 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_idList665 = new BitSet(new long[]{0x0000000200000008L});
    public static final BitSet FOLLOW_EXP_LIST_in_exprList695 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_exprList698 = new BitSet(new long[]{0xFFEFE00009038008L,0x00000000000003F9L});
    public static final BitSet FOLLOW_EXP_PAIR_in_exprPair721 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_exprPair725 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_exprPair729 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_MAP_in_exprMap757 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprPair_in_exprMap760 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_TERNARY_in_expression785 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression789 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_expression793 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_expression797 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_In_in_expression808 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression812 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_expression816 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RangeE_in_expression848 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression852 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_expression856 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Range_in_expression882 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression886 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_expression890 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_XorWord_in_expression920 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression924 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_expression928 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Or_in_expression954 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression958 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_expression962 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BitOr_in_expression989 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression993 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_expression997 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_OrWord_in_expression1027 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1031 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_expression1035 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_And_in_expression1064 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1068 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_expression1072 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BitAnd_in_expression1099 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1103 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_expression1107 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_AndWord_in_expression1137 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1141 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_expression1145 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Equals_in_expression1173 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1177 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_expression1181 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEquals_in_expression1208 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1212 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_expression1216 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GTEquals_in_expression1243 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1247 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_expression1251 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LTEquals_in_expression1278 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1282 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_expression1286 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GT_in_expression1313 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1317 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_expression1321 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LT_in_expression1349 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1353 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_expression1357 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Add_in_expression1385 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1389 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_expression1393 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Subtract_in_expression1421 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1425 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_expression1429 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Multiply_in_expression1457 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1461 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_expression1465 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Divide_in_expression1493 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1497 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_expression1501 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Modulus_in_expression1532 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1536 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_expression1540 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Pow_in_expression1568 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1572 = new BitSet(new long[]{0xFFEFE00009038000L,0x00000000000003F9L});
    public static final BitSet FOLLOW_expression_in_expression1576 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_UNARY_MIN_in_expression1604 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1608 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEGATE_in_expression1643 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1647 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Integer_in_expression1686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Number_in_expression1762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Bool_in_expression1840 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Null_in_expression1921 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_expression1975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LIST_in_list2039 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprList_in_list2041 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MAP_in_map2064 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprMap_in_map2066 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2089 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_functionCall_in_lookup2091 = new BitSet(new long[]{0x0000000001000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2095 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2107 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_list_in_lookup2109 = new BitSet(new long[]{0x0000000001000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2113 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2133 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_map_in_lookup2135 = new BitSet(new long[]{0x0000000001000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2139 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2162 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_lookup2164 = new BitSet(new long[]{0x0000000001000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2168 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2182 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_lookup2184 = new BitSet(new long[]{0x0000000001000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2188 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup2202 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_String_in_lookup2204 = new BitSet(new long[]{0x0000000001000008L});
    public static final BitSet FOLLOW_indexes_in_lookup2208 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TAILS_in_indexes2247 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_tail_in_indexes2252 = new BitSet(new long[]{0x0000000000700008L});
    public static final BitSet FOLLOW_INDEX_in_tail2277 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_tail2279 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ATTRIBUTE_in_tail2298 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_tail2300 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CALL_in_tail2315 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_tail2317 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_tail2319 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Var_in_variableDef2343 = new BitSet(new long[]{0x0000000000000002L});

}