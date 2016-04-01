// $ANTLR 3.2 Sep 23, 2009 12:02:23 grammar/PlazmaScriptWalker.g 2016-04-01 16:20:03

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BLOCK", "RETURN", "STATEMENTS", "ASSIGNMENT", "FUNC_CALL", "EXP", "EXP_PAIR", "EXP_MAP", "EXP_LIST", "ID_LIST", "IF", "TERNARY", "UNARY_MIN", "NEGATE", "FUNCTION", "INDEXES", "INDEX", "ATTRIBUTE", "CALL", "TAIL", "TAILS", "MAP", "LIST", "LOOKUP", "BREAK", "CONTINUE", "Return", "Break", "Continue", "Identifier", "Println", "Print", "Assert", "Date", "If", "Else", "Var", "Def", "For", "While", "In", "RangeE", "Range", "Integer", "Number", "Bool", "Null", "String", "Or", "And", "Equals", "NEquals", "GTEquals", "LTEquals", "Pow", "Excl", "GT", "LT", "Add", "Subtract", "Multiply", "Divide", "Modulus", "OBrace", "CBrace", "OBracket", "CBracket", "OParen", "CParen", "SColon", "Assign", "Comma", "QMark", "Colon", "Int", "Digit", "ContextIdentifier", "Comment", "Space", "YYYY", "MM", "DD", "'.'"
    };
    public static final int FUNCTION=18;
    public static final int OParen=71;
    public static final int LT=61;
    public static final int YYYY=83;
    public static final int Assert=36;
    public static final int TERNARY=15;
    public static final int ATTRIBUTE=21;
    public static final int EXP_LIST=12;
    public static final int Date=37;
    public static final int While=43;
    public static final int ID_LIST=13;
    public static final int Add=62;
    public static final int QMark=76;
    public static final int DD=85;
    public static final int EOF=-1;
    public static final int BREAK=28;
    public static final int Int=78;
    public static final int Identifier=33;
    public static final int IF=14;
    public static final int FUNC_CALL=8;
    public static final int TAIL=23;
    public static final int Space=82;
    public static final int INDEX=20;
    public static final int Assign=74;
    public static final int CParen=72;
    public static final int EXP_MAP=11;
    public static final int Number=48;
    public static final int Comment=81;
    public static final int EXP=9;
    public static final int CONTINUE=29;
    public static final int MM=84;
    public static final int GTEquals=56;
    public static final int Print=35;
    public static final int CBrace=68;
    public static final int RETURN=5;
    public static final int String=51;
    public static final int Or=52;
    public static final int Return=30;
    public static final int If=38;
    public static final int And=53;
    public static final int Null=50;
    public static final int CBracket=70;
    public static final int ContextIdentifier=80;
    public static final int Println=34;
    public static final int In=44;
    public static final int Bool=49;
    public static final int NEquals=55;
    public static final int Continue=32;
    public static final int Subtract=63;
    public static final int EXP_PAIR=10;
    public static final int Modulus=66;
    public static final int Multiply=64;
    public static final int OBrace=67;
    public static final int INDEXES=19;
    public static final int NEGATE=17;
    public static final int Colon=77;
    public static final int Excl=59;
    public static final int Digit=79;
    public static final int LIST=26;
    public static final int For=42;
    public static final int T__86=86;
    public static final int Divide=65;
    public static final int TAILS=24;
    public static final int Def=41;
    public static final int SColon=73;
    public static final int LOOKUP=27;
    public static final int RangeE=45;
    public static final int Range=46;
    public static final int OBracket=69;
    public static final int Break=31;
    public static final int BLOCK=4;
    public static final int MAP=25;
    public static final int STATEMENTS=6;
    public static final int GT=60;
    public static final int UNARY_MIN=16;
    public static final int ASSIGNMENT=7;
    public static final int CALL=22;
    public static final int Else=39;
    public static final int Equals=54;
    public static final int Comma=75;
    public static final int Var=40;
    public static final int Integer=47;
    public static final int Pow=58;
    public static final int LTEquals=57;

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

                    if ( ((LA1_0>=ASSIGNMENT && LA1_0<=FUNC_CALL)||LA1_0==IF||(LA1_0>=Break && LA1_0<=Continue)||(LA1_0>=For && LA1_0<=While)) ) {
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

                if ( ((LA2_0>=TERNARY && LA2_0<=NEGATE)||LA2_0==LOOKUP||(LA2_0>=In && LA2_0<=Null)||(LA2_0>=Or && LA2_0<=Pow)||(LA2_0>=GT && LA2_0<=Modulus)) ) {
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
    // grammar/PlazmaScriptWalker.g:62:1: statement returns [LNode node] : ( assignment | functionCall | ifStatement | forStatement | whileStatement | Break | Continue );
    public final LNode statement() throws RecognitionException {
        LNode node = null;

        LNode assignment4 = null;

        LNode functionCall5 = null;

        LNode ifStatement6 = null;

        LNode forStatement7 = null;

        LNode whileStatement8 = null;


        try {
            // grammar/PlazmaScriptWalker.g:63:3: ( assignment | functionCall | ifStatement | forStatement | whileStatement | Break | Continue )
            int alt3=7;
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
            case IF:
                {
                alt3=3;
                }
                break;
            case For:
                {
                alt3=4;
                }
                break;
            case While:
                {
                alt3=5;
                }
                break;
            case Break:
                {
                alt3=6;
                }
                break;
            case Continue:
                {
                alt3=7;
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
                    // grammar/PlazmaScriptWalker.g:65:6: ifStatement
                    {
                    pushFollow(FOLLOW_ifStatement_in_statement146);
                    ifStatement6=ifStatement();

                    state._fsp--;

                    node = ifStatement6;

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:66:6: forStatement
                    {
                    pushFollow(FOLLOW_forStatement_in_statement158);
                    forStatement7=forStatement();

                    state._fsp--;

                    node = forStatement7;

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:68:6: whileStatement
                    {
                    pushFollow(FOLLOW_whileStatement_in_statement170);
                    whileStatement8=whileStatement();

                    state._fsp--;

                    node = whileStatement8;

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScriptWalker.g:69:6: Break
                    {
                    match(input,Break,FOLLOW_Break_in_statement179); 
                    node = new BreakNode();

                    }
                    break;
                case 7 :
                    // grammar/PlazmaScriptWalker.g:70:6: Continue
                    {
                    match(input,Continue,FOLLOW_Continue_in_statement232); 
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
    // grammar/PlazmaScriptWalker.g:75:1: assignment returns [LNode node] : ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression ) ;
    public final LNode assignment() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier10=null;
        LNode variableDef9 = null;

        java.util.List<LNode> indexes11 = null;

        LNode expression12 = null;


        try {
            // grammar/PlazmaScriptWalker.g:76:3: ( ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression ) )
            // grammar/PlazmaScriptWalker.g:76:6: ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression )
            {
            match(input,ASSIGNMENT,FOLLOW_ASSIGNMENT_in_assignment298); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:76:19: ( variableDef )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==Var) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:76:19: variableDef
                    {
                    pushFollow(FOLLOW_variableDef_in_assignment300);
                    variableDef9=variableDef();

                    state._fsp--;


                    }
                    break;

            }

            Identifier10=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_assignment303); 
            // grammar/PlazmaScriptWalker.g:76:43: ( indexes )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==TAILS) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:76:43: indexes
                    {
                    pushFollow(FOLLOW_indexes_in_assignment305);
                    indexes11=indexes();

                    state._fsp--;


                    }
                    break;

            }

            pushFollow(FOLLOW_expression_in_assignment308);
            expression12=expression();

            state._fsp--;


            match(input, Token.UP, null); 
            node = new AssignmentNode(variableDef9, (Identifier10!=null?Identifier10.getText():null), indexes11, expression12, currentScope, globalScope);

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
    // grammar/PlazmaScriptWalker.g:79:1: functionCall returns [LNode node] : ( ^( FUNC_CALL Identifier ( exprList )? ) | ^( FUNC_CALL Println ( expression )? ) | ^( FUNC_CALL Print expression ) | ^( FUNC_CALL Assert expression ) | ^( FUNC_CALL Date ( exprList )? ) );
    public final LNode functionCall() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier13=null;
        java.util.List<LNode> exprList14 = null;

        LNode expression15 = null;

        LNode expression16 = null;

        LNode expression17 = null;

        java.util.List<LNode> exprList18 = null;


        try {
            // grammar/PlazmaScriptWalker.g:80:3: ( ^( FUNC_CALL Identifier ( exprList )? ) | ^( FUNC_CALL Println ( expression )? ) | ^( FUNC_CALL Print expression ) | ^( FUNC_CALL Assert expression ) | ^( FUNC_CALL Date ( exprList )? ) )
            int alt9=5;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==FUNC_CALL) ) {
                int LA9_1 = input.LA(2);

                if ( (LA9_1==DOWN) ) {
                    switch ( input.LA(3) ) {
                    case Identifier:
                        {
                        alt9=1;
                        }
                        break;
                    case Println:
                        {
                        alt9=2;
                        }
                        break;
                    case Print:
                        {
                        alt9=3;
                        }
                        break;
                    case Assert:
                        {
                        alt9=4;
                        }
                        break;
                    case Date:
                        {
                        alt9=5;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 9, 2, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 9, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:80:6: ^( FUNC_CALL Identifier ( exprList )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall330); 

                    match(input, Token.DOWN, null); 
                    Identifier13=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_functionCall332); 
                    // grammar/PlazmaScriptWalker.g:80:29: ( exprList )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==EXP_LIST) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:80:29: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall334);
                            exprList14=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new FunctionCallNode((Identifier13!=null?Identifier13.getText():null), exprList14, functions, globalScope);

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:81:6: ^( FUNC_CALL Println ( expression )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall346); 

                    match(input, Token.DOWN, null); 
                    match(input,Println,FOLLOW_Println_in_functionCall348); 
                    // grammar/PlazmaScriptWalker.g:81:26: ( expression )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( ((LA7_0>=TERNARY && LA7_0<=NEGATE)||LA7_0==LOOKUP||(LA7_0>=In && LA7_0<=Null)||(LA7_0>=Or && LA7_0<=Pow)||(LA7_0>=GT && LA7_0<=Modulus)) ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:81:26: expression
                            {
                            pushFollow(FOLLOW_expression_in_functionCall350);
                            expression15=expression();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new PrintlnNode(expression15);

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:82:6: ^( FUNC_CALL Print expression )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall363); 

                    match(input, Token.DOWN, null); 
                    match(input,Print,FOLLOW_Print_in_functionCall365); 
                    pushFollow(FOLLOW_expression_in_functionCall367);
                    expression16=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new PrintNode(expression16);

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:83:6: ^( FUNC_CALL Assert expression )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall382); 

                    match(input, Token.DOWN, null); 
                    match(input,Assert,FOLLOW_Assert_in_functionCall384); 
                    pushFollow(FOLLOW_expression_in_functionCall386);
                    expression17=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AssertNode(expression17);

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:85:6: ^( FUNC_CALL Date ( exprList )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall401); 

                    match(input, Token.DOWN, null); 
                    match(input,Date,FOLLOW_Date_in_functionCall403); 
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
                            pushFollow(FOLLOW_exprList_in_functionCall405);
                            exprList18=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new DateNode(exprList18);

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
    // grammar/PlazmaScriptWalker.g:88:1: ifStatement returns [LNode node] : ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? ) ;
    public final LNode ifStatement() throws RecognitionException {
        LNode node = null;

        IfNode ifNode = new IfNode();
        try {
            // grammar/PlazmaScriptWalker.g:91:3: ( ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? ) )
            // grammar/PlazmaScriptWalker.g:91:6: ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? )
            {
            match(input,IF,FOLLOW_IF_in_ifStatement445); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_ifStat_in_ifStatement447);
            ifStat(ifNode);

            state._fsp--;

            // grammar/PlazmaScriptWalker.g:91:26: ( elseIfStat[ifNode] )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==EXP) ) {
                    int LA10_1 = input.LA(2);

                    if ( (LA10_1==DOWN) ) {
                        int LA10_3 = input.LA(3);

                        if ( ((LA10_3>=TERNARY && LA10_3<=NEGATE)||LA10_3==LOOKUP||(LA10_3>=In && LA10_3<=Null)||(LA10_3>=Or && LA10_3<=Pow)||(LA10_3>=GT && LA10_3<=Modulus)) ) {
                            alt10=1;
                        }


                    }


                }


                switch (alt10) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:91:27: elseIfStat[ifNode]
            	    {
            	    pushFollow(FOLLOW_elseIfStat_in_ifStatement451);
            	    elseIfStat(ifNode);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            // grammar/PlazmaScriptWalker.g:91:48: ( elseStat[ifNode] )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==EXP) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:91:49: elseStat[ifNode]
                    {
                    pushFollow(FOLLOW_elseStat_in_ifStatement457);
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
    // grammar/PlazmaScriptWalker.g:94:1: ifStat[IfNode parent] : ^( EXP expression block ) ;
    public final void ifStat(IfNode parent) throws RecognitionException {
        LNode expression19 = null;

        LNode block20 = null;


        try {
            // grammar/PlazmaScriptWalker.g:95:3: ( ^( EXP expression block ) )
            // grammar/PlazmaScriptWalker.g:95:6: ^( EXP expression block )
            {
            match(input,EXP,FOLLOW_EXP_in_ifStat477); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_ifStat479);
            expression19=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_ifStat481);
            block20=block();

            state._fsp--;


            match(input, Token.UP, null); 
            parent.addChoice(expression19, block20);

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
    // grammar/PlazmaScriptWalker.g:98:1: elseIfStat[IfNode parent] : ^( EXP expression block ) ;
    public final void elseIfStat(IfNode parent) throws RecognitionException {
        LNode expression21 = null;

        LNode block22 = null;


        try {
            // grammar/PlazmaScriptWalker.g:99:3: ( ^( EXP expression block ) )
            // grammar/PlazmaScriptWalker.g:99:6: ^( EXP expression block )
            {
            match(input,EXP,FOLLOW_EXP_in_elseIfStat500); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_elseIfStat502);
            expression21=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_elseIfStat504);
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
    // $ANTLR end "elseIfStat"


    // $ANTLR start "elseStat"
    // grammar/PlazmaScriptWalker.g:102:1: elseStat[IfNode parent] : ^( EXP block ) ;
    public final void elseStat(IfNode parent) throws RecognitionException {
        LNode block23 = null;


        try {
            // grammar/PlazmaScriptWalker.g:103:3: ( ^( EXP block ) )
            // grammar/PlazmaScriptWalker.g:103:6: ^( EXP block )
            {
            match(input,EXP,FOLLOW_EXP_in_elseStat523); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_block_in_elseStat525);
            block23=block();

            state._fsp--;


            match(input, Token.UP, null); 
            parent.addChoice(new BooleanNode(true), block23);

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
    // grammar/PlazmaScriptWalker.g:110:1: forStatement returns [LNode node] : ^( For Identifier a= expression block ) ;
    public final LNode forStatement() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier24=null;
        LNode a = null;

        LNode block25 = null;


        try {
            // grammar/PlazmaScriptWalker.g:111:3: ( ^( For Identifier a= expression block ) )
            // grammar/PlazmaScriptWalker.g:111:6: ^( For Identifier a= expression block )
            {
            match(input,For,FOLLOW_For_in_forStatement556); 

            match(input, Token.DOWN, null); 
            Identifier24=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_forStatement558); 
            pushFollow(FOLLOW_expression_in_forStatement562);
            a=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_forStatement564);
            block25=block();

            state._fsp--;


            match(input, Token.UP, null); 
            node = new ForStatementNode2((Identifier24!=null?Identifier24.getText():null), a, block25, currentScope);

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
    // grammar/PlazmaScriptWalker.g:115:1: whileStatement returns [LNode node] : ^( While expression block ) ;
    public final LNode whileStatement() throws RecognitionException {
        LNode node = null;

        LNode expression26 = null;

        LNode block27 = null;


        try {
            // grammar/PlazmaScriptWalker.g:116:3: ( ^( While expression block ) )
            // grammar/PlazmaScriptWalker.g:116:6: ^( While expression block )
            {
            match(input,While,FOLLOW_While_in_whileStatement589); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_whileStatement591);
            expression26=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_whileStatement593);
            block27=block();

            state._fsp--;


            match(input, Token.UP, null); 
            node = new WhileStatementNode(expression26, block27);

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
    // grammar/PlazmaScriptWalker.g:119:1: idList returns [java.util.List<String> i] : ^( ID_LIST ( Identifier )+ ) ;
    public final java.util.List<String> idList() throws RecognitionException {
        java.util.List<String> i = null;

        CommonTree Identifier28=null;

        i = new java.util.ArrayList<String>();
        try {
            // grammar/PlazmaScriptWalker.g:121:3: ( ^( ID_LIST ( Identifier )+ ) )
            // grammar/PlazmaScriptWalker.g:121:6: ^( ID_LIST ( Identifier )+ )
            {
            match(input,ID_LIST,FOLLOW_ID_LIST_in_idList620); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:121:16: ( Identifier )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==Identifier) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:121:17: Identifier
            	    {
            	    Identifier28=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_idList623); 
            	    i.add((Identifier28!=null?Identifier28.getText():null));

            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
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
    // grammar/PlazmaScriptWalker.g:124:1: exprList returns [java.util.List<LNode> e] : ^( EXP_LIST ( expression )+ ) ;
    public final java.util.List<LNode> exprList() throws RecognitionException {
        java.util.List<LNode> e = null;

        LNode expression29 = null;


        e = new java.util.ArrayList<LNode>();
        try {
            // grammar/PlazmaScriptWalker.g:126:3: ( ^( EXP_LIST ( expression )+ ) )
            // grammar/PlazmaScriptWalker.g:126:6: ^( EXP_LIST ( expression )+ )
            {
            match(input,EXP_LIST,FOLLOW_EXP_LIST_in_exprList653); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:126:17: ( expression )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0>=TERNARY && LA13_0<=NEGATE)||LA13_0==LOOKUP||(LA13_0>=In && LA13_0<=Null)||(LA13_0>=Or && LA13_0<=Pow)||(LA13_0>=GT && LA13_0<=Modulus)) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:126:18: expression
            	    {
            	    pushFollow(FOLLOW_expression_in_exprList656);
            	    expression29=expression();

            	    state._fsp--;

            	    e.add(expression29);

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
        return e;
    }
    // $ANTLR end "exprList"


    // $ANTLR start "exprPair"
    // grammar/PlazmaScriptWalker.g:128:1: exprPair returns [PairNode node] : ^( EXP_PAIR k= expression v= expression ) ;
    public final PairNode exprPair() throws RecognitionException {
        PairNode node = null;

        LNode k = null;

        LNode v = null;


        try {
            // grammar/PlazmaScriptWalker.g:129:3: ( ^( EXP_PAIR k= expression v= expression ) )
            // grammar/PlazmaScriptWalker.g:129:6: ^( EXP_PAIR k= expression v= expression )
            {
            match(input,EXP_PAIR,FOLLOW_EXP_PAIR_in_exprPair679); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_exprPair683);
            k=expression();

            state._fsp--;

            pushFollow(FOLLOW_expression_in_exprPair687);
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
    // grammar/PlazmaScriptWalker.g:132:1: exprMap returns [java.util.List<PairNode> e] : ^( EXP_MAP ( exprPair )+ ) ;
    public final java.util.List<PairNode> exprMap() throws RecognitionException {
        java.util.List<PairNode> e = null;

        PairNode exprPair30 = null;


        e = new java.util.ArrayList<PairNode>();
        try {
            // grammar/PlazmaScriptWalker.g:134:3: ( ^( EXP_MAP ( exprPair )+ ) )
            // grammar/PlazmaScriptWalker.g:134:6: ^( EXP_MAP ( exprPair )+ )
            {
            match(input,EXP_MAP,FOLLOW_EXP_MAP_in_exprMap715); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:134:16: ( exprPair )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==EXP_PAIR) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:134:17: exprPair
            	    {
            	    pushFollow(FOLLOW_exprPair_in_exprMap718);
            	    exprPair30=exprPair();

            	    state._fsp--;

            	    e.add(exprPair30);

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
    // $ANTLR end "exprMap"


    // $ANTLR start "expression"
    // grammar/PlazmaScriptWalker.g:138:1: expression returns [LNode node] : ( ^( TERNARY a= expression b= expression c= expression ) | ^( In a= expression b= expression ) | ^( RangeE a= expression b= expression ) | ^( Range a= expression b= expression ) | ^( '||' a= expression b= expression ) | ^( '&&' a= expression b= expression ) | ^( '==' a= expression b= expression ) | ^( '!=' a= expression b= expression ) | ^( '>=' a= expression b= expression ) | ^( '<=' a= expression b= expression ) | ^( '>' a= expression b= expression ) | ^( '<' a= expression b= expression ) | ^( '+' a= expression b= expression ) | ^( '-' a= expression b= expression ) | ^( '*' a= expression b= expression ) | ^( '/' a= expression b= expression ) | ^( '%' a= expression b= expression ) | ^( '^' a= expression b= expression ) | ^( UNARY_MIN a= expression ) | ^( NEGATE a= expression ) | Integer | Number | Bool | Null | lookup );
    public final LNode expression() throws RecognitionException {
        LNode node = null;

        CommonTree Integer31=null;
        CommonTree Number32=null;
        CommonTree Bool33=null;
        LNode a = null;

        LNode b = null;

        LNode c = null;

        LNode lookup34 = null;


        try {
            // grammar/PlazmaScriptWalker.g:139:3: ( ^( TERNARY a= expression b= expression c= expression ) | ^( In a= expression b= expression ) | ^( RangeE a= expression b= expression ) | ^( Range a= expression b= expression ) | ^( '||' a= expression b= expression ) | ^( '&&' a= expression b= expression ) | ^( '==' a= expression b= expression ) | ^( '!=' a= expression b= expression ) | ^( '>=' a= expression b= expression ) | ^( '<=' a= expression b= expression ) | ^( '>' a= expression b= expression ) | ^( '<' a= expression b= expression ) | ^( '+' a= expression b= expression ) | ^( '-' a= expression b= expression ) | ^( '*' a= expression b= expression ) | ^( '/' a= expression b= expression ) | ^( '%' a= expression b= expression ) | ^( '^' a= expression b= expression ) | ^( UNARY_MIN a= expression ) | ^( NEGATE a= expression ) | Integer | Number | Bool | Null | lookup )
            int alt15=25;
            switch ( input.LA(1) ) {
            case TERNARY:
                {
                alt15=1;
                }
                break;
            case In:
                {
                alt15=2;
                }
                break;
            case RangeE:
                {
                alt15=3;
                }
                break;
            case Range:
                {
                alt15=4;
                }
                break;
            case Or:
                {
                alt15=5;
                }
                break;
            case And:
                {
                alt15=6;
                }
                break;
            case Equals:
                {
                alt15=7;
                }
                break;
            case NEquals:
                {
                alt15=8;
                }
                break;
            case GTEquals:
                {
                alt15=9;
                }
                break;
            case LTEquals:
                {
                alt15=10;
                }
                break;
            case GT:
                {
                alt15=11;
                }
                break;
            case LT:
                {
                alt15=12;
                }
                break;
            case Add:
                {
                alt15=13;
                }
                break;
            case Subtract:
                {
                alt15=14;
                }
                break;
            case Multiply:
                {
                alt15=15;
                }
                break;
            case Divide:
                {
                alt15=16;
                }
                break;
            case Modulus:
                {
                alt15=17;
                }
                break;
            case Pow:
                {
                alt15=18;
                }
                break;
            case UNARY_MIN:
                {
                alt15=19;
                }
                break;
            case NEGATE:
                {
                alt15=20;
                }
                break;
            case Integer:
                {
                alt15=21;
                }
                break;
            case Number:
                {
                alt15=22;
                }
                break;
            case Bool:
                {
                alt15=23;
                }
                break;
            case Null:
                {
                alt15=24;
                }
                break;
            case LOOKUP:
                {
                alt15=25;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:139:6: ^( TERNARY a= expression b= expression c= expression )
                    {
                    match(input,TERNARY,FOLLOW_TERNARY_in_expression743); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression747);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression751);
                    b=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression755);
                    c=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new TernaryNode(a, b, c);

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:140:6: ^( In a= expression b= expression )
                    {
                    match(input,In,FOLLOW_In_in_expression766); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression770);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression774);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new InNode(a, b);

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:142:6: ^( RangeE a= expression b= expression )
                    {
                    match(input,RangeE,FOLLOW_RangeE_in_expression806); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression810);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression814);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new RangeENode(a, b);

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:143:6: ^( Range a= expression b= expression )
                    {
                    match(input,Range,FOLLOW_Range_in_expression840); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression844);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression848);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new RangeNode(a, b);

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:145:6: ^( '||' a= expression b= expression )
                    {
                    match(input,Or,FOLLOW_Or_in_expression878); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression882);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression886);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new OrNode(a, b);

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScriptWalker.g:146:6: ^( '&&' a= expression b= expression )
                    {
                    match(input,And,FOLLOW_And_in_expression913); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression917);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression921);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AndNode(a, b);

                    }
                    break;
                case 7 :
                    // grammar/PlazmaScriptWalker.g:147:6: ^( '==' a= expression b= expression )
                    {
                    match(input,Equals,FOLLOW_Equals_in_expression948); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression952);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression956);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new EQNode(a, b);

                    }
                    break;
                case 8 :
                    // grammar/PlazmaScriptWalker.g:148:6: ^( '!=' a= expression b= expression )
                    {
                    match(input,NEquals,FOLLOW_NEquals_in_expression983); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression987);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression991);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new NENode(a, b);

                    }
                    break;
                case 9 :
                    // grammar/PlazmaScriptWalker.g:149:6: ^( '>=' a= expression b= expression )
                    {
                    match(input,GTEquals,FOLLOW_GTEquals_in_expression1018); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1022);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1026);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new GTENode(a, b);

                    }
                    break;
                case 10 :
                    // grammar/PlazmaScriptWalker.g:150:6: ^( '<=' a= expression b= expression )
                    {
                    match(input,LTEquals,FOLLOW_LTEquals_in_expression1053); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1057);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1061);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new LTENode(a, b);

                    }
                    break;
                case 11 :
                    // grammar/PlazmaScriptWalker.g:151:6: ^( '>' a= expression b= expression )
                    {
                    match(input,GT,FOLLOW_GT_in_expression1088); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1092);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1096);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new GTNode(a, b);

                    }
                    break;
                case 12 :
                    // grammar/PlazmaScriptWalker.g:152:6: ^( '<' a= expression b= expression )
                    {
                    match(input,LT,FOLLOW_LT_in_expression1124); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1128);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1132);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new LTNode(a, b);

                    }
                    break;
                case 13 :
                    // grammar/PlazmaScriptWalker.g:153:6: ^( '+' a= expression b= expression )
                    {
                    match(input,Add,FOLLOW_Add_in_expression1160); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1164);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1168);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AddNode(a, b);

                    }
                    break;
                case 14 :
                    // grammar/PlazmaScriptWalker.g:154:6: ^( '-' a= expression b= expression )
                    {
                    match(input,Subtract,FOLLOW_Subtract_in_expression1196); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1200);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1204);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new SubNode(a, b);

                    }
                    break;
                case 15 :
                    // grammar/PlazmaScriptWalker.g:155:6: ^( '*' a= expression b= expression )
                    {
                    match(input,Multiply,FOLLOW_Multiply_in_expression1232); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1236);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1240);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new MulNode(a, b);

                    }
                    break;
                case 16 :
                    // grammar/PlazmaScriptWalker.g:156:6: ^( '/' a= expression b= expression )
                    {
                    match(input,Divide,FOLLOW_Divide_in_expression1268); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1272);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1276);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new DivNode(a, b);

                    }
                    break;
                case 17 :
                    // grammar/PlazmaScriptWalker.g:157:6: ^( '%' a= expression b= expression )
                    {
                    match(input,Modulus,FOLLOW_Modulus_in_expression1304); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1308);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1312);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new ModNode(a, b);

                    }
                    break;
                case 18 :
                    // grammar/PlazmaScriptWalker.g:158:6: ^( '^' a= expression b= expression )
                    {
                    match(input,Pow,FOLLOW_Pow_in_expression1340); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1344);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1348);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new PowNode(a, b);

                    }
                    break;
                case 19 :
                    // grammar/PlazmaScriptWalker.g:159:6: ^( UNARY_MIN a= expression )
                    {
                    match(input,UNARY_MIN,FOLLOW_UNARY_MIN_in_expression1376); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1380);
                    a=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new UnaryMinusNode(a);

                    }
                    break;
                case 20 :
                    // grammar/PlazmaScriptWalker.g:160:6: ^( NEGATE a= expression )
                    {
                    match(input,NEGATE,FOLLOW_NEGATE_in_expression1415); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1419);
                    a=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new NotNode(a);

                    }
                    break;
                case 21 :
                    // grammar/PlazmaScriptWalker.g:162:6: Integer
                    {
                    Integer31=(CommonTree)match(input,Integer,FOLLOW_Integer_in_expression1458); 
                    node = new NumberNode((Integer31!=null?Integer31.getText():null));

                    }
                    break;
                case 22 :
                    // grammar/PlazmaScriptWalker.g:163:6: Number
                    {
                    Number32=(CommonTree)match(input,Number,FOLLOW_Number_in_expression1534); 
                    node = new NumberNode((Number32!=null?Number32.getText():null));

                    }
                    break;
                case 23 :
                    // grammar/PlazmaScriptWalker.g:164:6: Bool
                    {
                    Bool33=(CommonTree)match(input,Bool,FOLLOW_Bool_in_expression1612); 
                    node = new BooleanNode((Bool33!=null?Bool33.getText():null));

                    }
                    break;
                case 24 :
                    // grammar/PlazmaScriptWalker.g:166:6: Null
                    {
                    match(input,Null,FOLLOW_Null_in_expression1696); 
                    node = new NullNode();

                    }
                    break;
                case 25 :
                    // grammar/PlazmaScriptWalker.g:167:6: lookup
                    {
                    pushFollow(FOLLOW_lookup_in_expression1750);
                    lookup34=lookup();

                    state._fsp--;

                    node = lookup34;

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
    // grammar/PlazmaScriptWalker.g:170:1: list returns [LNode node] : ^( LIST ( exprList )? ) ;
    public final LNode list() throws RecognitionException {
        LNode node = null;

        java.util.List<LNode> exprList35 = null;


        try {
            // grammar/PlazmaScriptWalker.g:171:3: ( ^( LIST ( exprList )? ) )
            // grammar/PlazmaScriptWalker.g:171:6: ^( LIST ( exprList )? )
            {
            match(input,LIST,FOLLOW_LIST_in_list1814); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // grammar/PlazmaScriptWalker.g:171:13: ( exprList )?
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==EXP_LIST) ) {
                    alt16=1;
                }
                switch (alt16) {
                    case 1 :
                        // grammar/PlazmaScriptWalker.g:171:13: exprList
                        {
                        pushFollow(FOLLOW_exprList_in_list1816);
                        exprList35=exprList();

                        state._fsp--;


                        }
                        break;

                }


                match(input, Token.UP, null); 
            }
            node = new ListNode(exprList35);

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
    // grammar/PlazmaScriptWalker.g:174:1: map returns [LNode node] : ^( MAP ( exprMap )? ) ;
    public final LNode map() throws RecognitionException {
        LNode node = null;

        java.util.List<PairNode> exprMap36 = null;


        try {
            // grammar/PlazmaScriptWalker.g:175:3: ( ^( MAP ( exprMap )? ) )
            // grammar/PlazmaScriptWalker.g:175:6: ^( MAP ( exprMap )? )
            {
            match(input,MAP,FOLLOW_MAP_in_map1839); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // grammar/PlazmaScriptWalker.g:175:12: ( exprMap )?
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==EXP_MAP) ) {
                    alt17=1;
                }
                switch (alt17) {
                    case 1 :
                        // grammar/PlazmaScriptWalker.g:175:12: exprMap
                        {
                        pushFollow(FOLLOW_exprMap_in_map1841);
                        exprMap36=exprMap();

                        state._fsp--;


                        }
                        break;

                }


                match(input, Token.UP, null); 
            }
            node = new MapNode(exprMap36);

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
    // grammar/PlazmaScriptWalker.g:178:1: lookup returns [LNode node] : ( ^( LOOKUP functionCall (i= indexes )? ) | ^( LOOKUP list (i= indexes )? ) | ^( LOOKUP map (i= indexes )? ) | ^( LOOKUP expression (i= indexes )? ) | ^( LOOKUP Identifier (i= indexes )? ) | ^( LOOKUP String (i= indexes )? ) );
    public final LNode lookup() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier41=null;
        CommonTree String42=null;
        java.util.List<LNode> i = null;

        LNode functionCall37 = null;

        LNode list38 = null;

        LNode map39 = null;

        LNode expression40 = null;


        try {
            // grammar/PlazmaScriptWalker.g:179:3: ( ^( LOOKUP functionCall (i= indexes )? ) | ^( LOOKUP list (i= indexes )? ) | ^( LOOKUP map (i= indexes )? ) | ^( LOOKUP expression (i= indexes )? ) | ^( LOOKUP Identifier (i= indexes )? ) | ^( LOOKUP String (i= indexes )? ) )
            int alt24=6;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==LOOKUP) ) {
                int LA24_1 = input.LA(2);

                if ( (LA24_1==DOWN) ) {
                    switch ( input.LA(3) ) {
                    case Identifier:
                        {
                        alt24=5;
                        }
                        break;
                    case String:
                        {
                        alt24=6;
                        }
                        break;
                    case FUNC_CALL:
                        {
                        alt24=1;
                        }
                        break;
                    case MAP:
                        {
                        alt24=3;
                        }
                        break;
                    case LIST:
                        {
                        alt24=2;
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
                    case Or:
                    case And:
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
                        alt24=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 24, 2, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 24, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }
            switch (alt24) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:179:6: ^( LOOKUP functionCall (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup1864); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_functionCall_in_lookup1866);
                    functionCall37=functionCall();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:179:29: (i= indexes )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==TAILS) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:179:29: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1870);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(functionCall37, i) : functionCall37;

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:180:6: ^( LOOKUP list (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup1882); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_list_in_lookup1884);
                    list38=list();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:180:21: (i= indexes )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==TAILS) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:180:21: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1888);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(list38, i) : list38;

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:181:6: ^( LOOKUP map (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup1908); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_map_in_lookup1910);
                    map39=map();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:181:20: (i= indexes )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==TAILS) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:181:20: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1914);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(map39, i) : map39;

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:182:6: ^( LOOKUP expression (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup1937); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_lookup1939);
                    expression40=expression();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:182:27: (i= indexes )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==TAILS) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:182:27: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1943);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(expression40, i) : expression40;

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:183:6: ^( LOOKUP Identifier (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup1957); 

                    match(input, Token.DOWN, null); 
                    Identifier41=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_lookup1959); 
                    // grammar/PlazmaScriptWalker.g:183:27: (i= indexes )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==TAILS) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:183:27: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1963);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(new IdentifierNode((Identifier41!=null?Identifier41.getText():null), currentScope, globalScope), i) : new IdentifierNode((Identifier41!=null?Identifier41.getText():null), currentScope, globalScope);

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScriptWalker.g:184:6: ^( LOOKUP String (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup1977); 

                    match(input, Token.DOWN, null); 
                    String42=(CommonTree)match(input,String,FOLLOW_String_in_lookup1979); 
                    // grammar/PlazmaScriptWalker.g:184:23: (i= indexes )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==TAILS) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:184:23: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1983);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(new StringNode((String42!=null?String42.getText():null)), i) : new StringNode((String42!=null?String42.getText():null));

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
    // grammar/PlazmaScriptWalker.g:187:1: indexes returns [java.util.List<LNode> e] : ^( TAILS ( tail )+ ) ;
    public final java.util.List<LNode> indexes() throws RecognitionException {
        java.util.List<LNode> e = null;

        LNode tail43 = null;


        e = new java.util.ArrayList<LNode>();
        try {
            // grammar/PlazmaScriptWalker.g:190:3: ( ^( TAILS ( tail )+ ) )
            // grammar/PlazmaScriptWalker.g:190:8: ^( TAILS ( tail )+ )
            {
            match(input,TAILS,FOLLOW_TAILS_in_indexes2022); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:190:18: ( tail )+
            int cnt25=0;
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( ((LA25_0>=INDEX && LA25_0<=CALL)) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:190:19: tail
            	    {
            	    pushFollow(FOLLOW_tail_in_indexes2027);
            	    tail43=tail();

            	    state._fsp--;

            	    e.add(tail43);

            	    }
            	    break;

            	default :
            	    if ( cnt25 >= 1 ) break loop25;
                        EarlyExitException eee =
                            new EarlyExitException(25, input);
                        throw eee;
                }
                cnt25++;
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
    // grammar/PlazmaScriptWalker.g:194:1: tail returns [LNode node] : ( ^( INDEX expression ) | ^( ATTRIBUTE Identifier ) | ^( CALL Identifier ( exprList )? ) );
    public final LNode tail() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier45=null;
        CommonTree Identifier46=null;
        LNode expression44 = null;

        java.util.List<LNode> exprList47 = null;


        try {
            // grammar/PlazmaScriptWalker.g:195:2: ( ^( INDEX expression ) | ^( ATTRIBUTE Identifier ) | ^( CALL Identifier ( exprList )? ) )
            int alt27=3;
            switch ( input.LA(1) ) {
            case INDEX:
                {
                alt27=1;
                }
                break;
            case ATTRIBUTE:
                {
                alt27=2;
                }
                break;
            case CALL:
                {
                alt27=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }

            switch (alt27) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:195:4: ^( INDEX expression )
                    {
                    match(input,INDEX,FOLLOW_INDEX_in_tail2052); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_tail2054);
                    expression44=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = expression44;

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:196:4: ^( ATTRIBUTE Identifier )
                    {
                    match(input,ATTRIBUTE,FOLLOW_ATTRIBUTE_in_tail2073); 

                    match(input, Token.DOWN, null); 
                    Identifier45=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_tail2075); 

                    match(input, Token.UP, null); 
                    node = new StringNode((Identifier45!=null?Identifier45.getText():null));

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:197:4: ^( CALL Identifier ( exprList )? )
                    {
                    match(input,CALL,FOLLOW_CALL_in_tail2090); 

                    match(input, Token.DOWN, null); 
                    Identifier46=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_tail2092); 
                    // grammar/PlazmaScriptWalker.g:197:22: ( exprList )?
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==EXP_LIST) ) {
                        alt26=1;
                    }
                    switch (alt26) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:197:22: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_tail2094);
                            exprList47=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new MethodCallNode((Identifier46!=null?Identifier46.getText():null), exprList47, functions, globalScope);

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
    // grammar/PlazmaScriptWalker.g:200:1: variableDef returns [LNode node] : Var ;
    public final LNode variableDef() throws RecognitionException {
        LNode node = null;

        CommonTree Var48=null;

        try {
            // grammar/PlazmaScriptWalker.g:201:3: ( Var )
            // grammar/PlazmaScriptWalker.g:201:5: Var
            {
            Var48=(CommonTree)match(input,Var,FOLLOW_Var_in_variableDef2118); 
            node = new VariableDefNode((Var48!=null?Var48.getText():null), null);

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
    public static final BitSet FOLLOW_statement_in_block87 = new BitSet(new long[]{0x00000C0180004188L});
    public static final BitSet FOLLOW_RETURN_in_block95 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_block98 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_assignment_in_statement122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_statement135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStatement_in_statement146 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forStatement_in_statement158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whileStatement_in_statement170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Break_in_statement179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Continue_in_statement232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGNMENT_in_assignment298 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_variableDef_in_assignment300 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_Identifier_in_assignment303 = new BitSet(new long[]{0xF7F7F00009038000L,0x0000000000000007L});
    public static final BitSet FOLLOW_indexes_in_assignment305 = new BitSet(new long[]{0xF7F7F00009038000L,0x0000000000000007L});
    public static final BitSet FOLLOW_expression_in_assignment308 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall330 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_functionCall332 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall334 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall346 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Println_in_functionCall348 = new BitSet(new long[]{0xF7F7F00009038008L,0x0000000000000007L});
    public static final BitSet FOLLOW_expression_in_functionCall350 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall363 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Print_in_functionCall365 = new BitSet(new long[]{0xF7F7F00009038000L,0x0000000000000007L});
    public static final BitSet FOLLOW_expression_in_functionCall367 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall382 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Assert_in_functionCall384 = new BitSet(new long[]{0xF7F7F00009038000L,0x0000000000000007L});
    public static final BitSet FOLLOW_expression_in_functionCall386 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall401 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Date_in_functionCall403 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall405 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IF_in_ifStatement445 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ifStat_in_ifStatement447 = new BitSet(new long[]{0x0000000000000208L});
    public static final BitSet FOLLOW_elseIfStat_in_ifStatement451 = new BitSet(new long[]{0x0000000000000208L});
    public static final BitSet FOLLOW_elseStat_in_ifStatement457 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_ifStat477 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_ifStat479 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_ifStat481 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_elseIfStat500 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_elseIfStat502 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_elseIfStat504 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_elseStat523 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_block_in_elseStat525 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_For_in_forStatement556 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_forStatement558 = new BitSet(new long[]{0xF7F7F00009038000L,0x0000000000000007L});
    public static final BitSet FOLLOW_expression_in_forStatement562 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_forStatement564 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_While_in_whileStatement589 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_whileStatement591 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_whileStatement593 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_LIST_in_idList620 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_idList623 = new BitSet(new long[]{0x0000000200000008L});
    public static final BitSet FOLLOW_EXP_LIST_in_exprList653 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_exprList656 = new BitSet(new long[]{0xF7F7F00009038008L,0x0000000000000007L});
    public static final BitSet FOLLOW_EXP_PAIR_in_exprPair679 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_exprPair683 = new BitSet(new long[]{0xF7F7F00009038000L,0x0000000000000007L});
    public static final BitSet FOLLOW_expression_in_exprPair687 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_MAP_in_exprMap715 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprPair_in_exprMap718 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_TERNARY_in_expression743 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression747 = new BitSet(new long[]{0xF7F7F00009038000L,0x0000000000000007L});
    public static final BitSet FOLLOW_expression_in_expression751 = new BitSet(new long[]{0xF7F7F00009038000L,0x0000000000000007L});
    public static final BitSet FOLLOW_expression_in_expression755 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_In_in_expression766 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression770 = new BitSet(new long[]{0xF7F7F00009038000L,0x0000000000000007L});
    public static final BitSet FOLLOW_expression_in_expression774 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RangeE_in_expression806 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression810 = new BitSet(new long[]{0xF7F7F00009038000L,0x0000000000000007L});
    public static final BitSet FOLLOW_expression_in_expression814 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Range_in_expression840 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression844 = new BitSet(new long[]{0xF7F7F00009038000L,0x0000000000000007L});
    public static final BitSet FOLLOW_expression_in_expression848 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Or_in_expression878 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression882 = new BitSet(new long[]{0xF7F7F00009038000L,0x0000000000000007L});
    public static final BitSet FOLLOW_expression_in_expression886 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_And_in_expression913 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression917 = new BitSet(new long[]{0xF7F7F00009038000L,0x0000000000000007L});
    public static final BitSet FOLLOW_expression_in_expression921 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Equals_in_expression948 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression952 = new BitSet(new long[]{0xF7F7F00009038000L,0x0000000000000007L});
    public static final BitSet FOLLOW_expression_in_expression956 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEquals_in_expression983 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression987 = new BitSet(new long[]{0xF7F7F00009038000L,0x0000000000000007L});
    public static final BitSet FOLLOW_expression_in_expression991 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GTEquals_in_expression1018 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1022 = new BitSet(new long[]{0xF7F7F00009038000L,0x0000000000000007L});
    public static final BitSet FOLLOW_expression_in_expression1026 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LTEquals_in_expression1053 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1057 = new BitSet(new long[]{0xF7F7F00009038000L,0x0000000000000007L});
    public static final BitSet FOLLOW_expression_in_expression1061 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GT_in_expression1088 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1092 = new BitSet(new long[]{0xF7F7F00009038000L,0x0000000000000007L});
    public static final BitSet FOLLOW_expression_in_expression1096 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LT_in_expression1124 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1128 = new BitSet(new long[]{0xF7F7F00009038000L,0x0000000000000007L});
    public static final BitSet FOLLOW_expression_in_expression1132 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Add_in_expression1160 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1164 = new BitSet(new long[]{0xF7F7F00009038000L,0x0000000000000007L});
    public static final BitSet FOLLOW_expression_in_expression1168 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Subtract_in_expression1196 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1200 = new BitSet(new long[]{0xF7F7F00009038000L,0x0000000000000007L});
    public static final BitSet FOLLOW_expression_in_expression1204 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Multiply_in_expression1232 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1236 = new BitSet(new long[]{0xF7F7F00009038000L,0x0000000000000007L});
    public static final BitSet FOLLOW_expression_in_expression1240 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Divide_in_expression1268 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1272 = new BitSet(new long[]{0xF7F7F00009038000L,0x0000000000000007L});
    public static final BitSet FOLLOW_expression_in_expression1276 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Modulus_in_expression1304 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1308 = new BitSet(new long[]{0xF7F7F00009038000L,0x0000000000000007L});
    public static final BitSet FOLLOW_expression_in_expression1312 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Pow_in_expression1340 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1344 = new BitSet(new long[]{0xF7F7F00009038000L,0x0000000000000007L});
    public static final BitSet FOLLOW_expression_in_expression1348 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_UNARY_MIN_in_expression1376 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1380 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEGATE_in_expression1415 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1419 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Integer_in_expression1458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Number_in_expression1534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Bool_in_expression1612 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Null_in_expression1696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_expression1750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LIST_in_list1814 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprList_in_list1816 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MAP_in_map1839 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprMap_in_map1841 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup1864 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_functionCall_in_lookup1866 = new BitSet(new long[]{0x0000000001000008L});
    public static final BitSet FOLLOW_indexes_in_lookup1870 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup1882 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_list_in_lookup1884 = new BitSet(new long[]{0x0000000001000008L});
    public static final BitSet FOLLOW_indexes_in_lookup1888 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup1908 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_map_in_lookup1910 = new BitSet(new long[]{0x0000000001000008L});
    public static final BitSet FOLLOW_indexes_in_lookup1914 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup1937 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_lookup1939 = new BitSet(new long[]{0x0000000001000008L});
    public static final BitSet FOLLOW_indexes_in_lookup1943 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup1957 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_lookup1959 = new BitSet(new long[]{0x0000000001000008L});
    public static final BitSet FOLLOW_indexes_in_lookup1963 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup1977 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_String_in_lookup1979 = new BitSet(new long[]{0x0000000001000008L});
    public static final BitSet FOLLOW_indexes_in_lookup1983 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TAILS_in_indexes2022 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_tail_in_indexes2027 = new BitSet(new long[]{0x0000000000700008L});
    public static final BitSet FOLLOW_INDEX_in_tail2052 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_tail2054 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ATTRIBUTE_in_tail2073 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_tail2075 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CALL_in_tail2090 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_tail2092 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_tail2094 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Var_in_variableDef2118 = new BitSet(new long[]{0x0000000000000002L});

}