// $ANTLR 3.2 Sep 23, 2009 12:02:23 grammar/PlazmaScriptWalker.g 2015-06-02 08:38:04

  package plazma.parser;
  import plazma.*;
  import plazma.ast.*;
  import plazma.ast.functions.*;
  import java.util.Map;
  import java.util.HashMap;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class PlazmaScriptWalker extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BLOCK", "RETURN", "STATEMENTS", "ASSIGNMENT", "FUNC_CALL", "EXP", "EXP_PAIR", "EXP_MAP", "EXP_LIST", "ID_LIST", "IF", "TERNARY", "UNARY_MIN", "NEGATE", "FUNCTION", "INDEXES", "MAP", "LIST", "LOOKUP", "BREAK", "CONTINUE", "Return", "Break", "Continue", "Identifier", "Println", "Print", "Assert", "Size", "If", "Else", "Def", "For", "While", "In", "RangeE", "Range", "Integer", "Number", "Bool", "Date", "Null", "String", "Or", "And", "Equals", "NEquals", "GTEquals", "LTEquals", "Pow", "Excl", "GT", "LT", "Add", "Subtract", "Multiply", "Divide", "Modulus", "OBrace", "CBrace", "OBracket", "CBracket", "OParen", "CParen", "SColon", "Assign", "Comma", "QMark", "Colon", "Int", "Digit", "YYYY", "MM", "DD", "ContextIdentifier", "Comment", "Space"
    };
    public static final int FUNCTION=18;
    public static final int OParen=66;
    public static final int LT=56;
    public static final int YYYY=75;
    public static final int Assert=31;
    public static final int TERNARY=15;
    public static final int EXP_LIST=12;
    public static final int While=37;
    public static final int Date=44;
    public static final int ID_LIST=13;
    public static final int Add=57;
    public static final int QMark=71;
    public static final int DD=77;
    public static final int EOF=-1;
    public static final int BREAK=23;
    public static final int Identifier=28;
    public static final int Int=73;
    public static final int IF=14;
    public static final int FUNC_CALL=8;
    public static final int Space=80;
    public static final int Size=32;
    public static final int Assign=69;
    public static final int CParen=67;
    public static final int EXP_MAP=11;
    public static final int Number=42;
    public static final int Comment=79;
    public static final int EXP=9;
    public static final int CONTINUE=24;
    public static final int MM=76;
    public static final int GTEquals=51;
    public static final int Print=30;
    public static final int CBrace=63;
    public static final int RETURN=5;
    public static final int String=46;
    public static final int Or=47;
    public static final int Return=25;
    public static final int If=33;
    public static final int And=48;
    public static final int Null=45;
    public static final int ContextIdentifier=78;
    public static final int CBracket=65;
    public static final int Println=29;
    public static final int Bool=43;
    public static final int In=38;
    public static final int NEquals=50;
    public static final int Continue=27;
    public static final int Subtract=58;
    public static final int EXP_PAIR=10;
    public static final int Modulus=61;
    public static final int Multiply=59;
    public static final int OBrace=62;
    public static final int NEGATE=17;
    public static final int INDEXES=19;
    public static final int Colon=72;
    public static final int Excl=54;
    public static final int Digit=74;
    public static final int LIST=21;
    public static final int For=36;
    public static final int Divide=60;
    public static final int Def=35;
    public static final int SColon=68;
    public static final int RangeE=39;
    public static final int LOOKUP=22;
    public static final int Range=40;
    public static final int OBracket=64;
    public static final int Break=26;
    public static final int BLOCK=4;
    public static final int MAP=20;
    public static final int STATEMENTS=6;
    public static final int GT=55;
    public static final int UNARY_MIN=16;
    public static final int ASSIGNMENT=7;
    public static final int Else=34;
    public static final int Equals=49;
    public static final int Comma=70;
    public static final int Integer=41;
    public static final int Pow=53;
    public static final int LTEquals=52;

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
    // grammar/PlazmaScriptWalker.g:36:1: walk returns [LNode node] : block ;
    public final LNode walk() throws RecognitionException {
        LNode node = null;

        LNode block1 = null;


        try {
            // grammar/PlazmaScriptWalker.g:37:3: ( block )
            // grammar/PlazmaScriptWalker.g:37:6: block
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
    // grammar/PlazmaScriptWalker.g:40:1: block returns [LNode node] : ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) ) ;
    public final LNode block() throws RecognitionException {
        LNode node = null;

        LNode statement2 = null;

        LNode expression3 = null;



          BlockNode bn = new BlockNode();
          node = bn;
          Scope local = new Scope(currentScope);
          currentScope = local;

        try {
            // grammar/PlazmaScriptWalker.g:50:3: ( ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) ) )
            // grammar/PlazmaScriptWalker.g:50:6: ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) )
            {
            match(input,BLOCK,FOLLOW_BLOCK_in_block81); 

            match(input, Token.DOWN, null); 
            match(input,STATEMENTS,FOLLOW_STATEMENTS_in_block84); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // grammar/PlazmaScriptWalker.g:50:27: ( statement )*
                loop1:
                do {
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( ((LA1_0>=ASSIGNMENT && LA1_0<=FUNC_CALL)||LA1_0==IF||(LA1_0>=Break && LA1_0<=Continue)||(LA1_0>=For && LA1_0<=While)) ) {
                        alt1=1;
                    }


                    switch (alt1) {
                	case 1 :
                	    // grammar/PlazmaScriptWalker.g:50:28: statement
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
                // grammar/PlazmaScriptWalker.g:50:86: ( expression )?
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>=TERNARY && LA2_0<=NEGATE)||LA2_0==LOOKUP||(LA2_0>=In && LA2_0<=Null)||(LA2_0>=Or && LA2_0<=Pow)||(LA2_0>=GT && LA2_0<=Modulus)) ) {
                    alt2=1;
                }
                switch (alt2) {
                    case 1 :
                        // grammar/PlazmaScriptWalker.g:50:87: expression
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
    // grammar/PlazmaScriptWalker.g:53:1: statement returns [LNode node] : ( assignment | functionCall | ifStatement | forStatement | whileStatement | Break | Continue );
    public final LNode statement() throws RecognitionException {
        LNode node = null;

        LNode assignment4 = null;

        LNode functionCall5 = null;

        LNode ifStatement6 = null;

        LNode forStatement7 = null;

        LNode whileStatement8 = null;


        try {
            // grammar/PlazmaScriptWalker.g:54:3: ( assignment | functionCall | ifStatement | forStatement | whileStatement | Break | Continue )
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
                    // grammar/PlazmaScriptWalker.g:54:6: assignment
                    {
                    pushFollow(FOLLOW_assignment_in_statement122);
                    assignment4=assignment();

                    state._fsp--;

                    node = assignment4;

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:55:6: functionCall
                    {
                    pushFollow(FOLLOW_functionCall_in_statement135);
                    functionCall5=functionCall();

                    state._fsp--;

                    node = functionCall5;

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:56:6: ifStatement
                    {
                    pushFollow(FOLLOW_ifStatement_in_statement146);
                    ifStatement6=ifStatement();

                    state._fsp--;

                    node = ifStatement6;

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:57:6: forStatement
                    {
                    pushFollow(FOLLOW_forStatement_in_statement158);
                    forStatement7=forStatement();

                    state._fsp--;

                    node = forStatement7;

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:59:6: whileStatement
                    {
                    pushFollow(FOLLOW_whileStatement_in_statement170);
                    whileStatement8=whileStatement();

                    state._fsp--;

                    node = whileStatement8;

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScriptWalker.g:60:6: Break
                    {
                    match(input,Break,FOLLOW_Break_in_statement179); 
                    node = new BreakNode();

                    }
                    break;
                case 7 :
                    // grammar/PlazmaScriptWalker.g:61:6: Continue
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
    // grammar/PlazmaScriptWalker.g:65:1: assignment returns [LNode node] : ^( ASSIGNMENT Identifier ( indexes )? expression ) ;
    public final LNode assignment() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier9=null;
        java.util.List<LNode> indexes10 = null;

        LNode expression11 = null;


        try {
            // grammar/PlazmaScriptWalker.g:66:3: ( ^( ASSIGNMENT Identifier ( indexes )? expression ) )
            // grammar/PlazmaScriptWalker.g:66:6: ^( ASSIGNMENT Identifier ( indexes )? expression )
            {
            match(input,ASSIGNMENT,FOLLOW_ASSIGNMENT_in_assignment297); 

            match(input, Token.DOWN, null); 
            Identifier9=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_assignment299); 
            // grammar/PlazmaScriptWalker.g:66:30: ( indexes )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==INDEXES) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:66:30: indexes
                    {
                    pushFollow(FOLLOW_indexes_in_assignment301);
                    indexes10=indexes();

                    state._fsp--;


                    }
                    break;

            }

            pushFollow(FOLLOW_expression_in_assignment304);
            expression11=expression();

            state._fsp--;


            match(input, Token.UP, null); 
            node = new AssignmentNode((Identifier9!=null?Identifier9.getText():null), indexes10, expression11, currentScope, globalScope);

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
    // grammar/PlazmaScriptWalker.g:69:1: functionCall returns [LNode node] : ( ^( FUNC_CALL Identifier ( exprList )? ) | ^( FUNC_CALL Println ( expression )? ) | ^( FUNC_CALL Print expression ) | ^( FUNC_CALL Assert expression ) | ^( FUNC_CALL Size expression ) );
    public final LNode functionCall() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier12=null;
        java.util.List<LNode> exprList13 = null;

        LNode expression14 = null;

        LNode expression15 = null;

        LNode expression16 = null;

        LNode expression17 = null;


        try {
            // grammar/PlazmaScriptWalker.g:70:3: ( ^( FUNC_CALL Identifier ( exprList )? ) | ^( FUNC_CALL Println ( expression )? ) | ^( FUNC_CALL Print expression ) | ^( FUNC_CALL Assert expression ) | ^( FUNC_CALL Size expression ) )
            int alt7=5;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==FUNC_CALL) ) {
                int LA7_1 = input.LA(2);

                if ( (LA7_1==DOWN) ) {
                    switch ( input.LA(3) ) {
                    case Identifier:
                        {
                        alt7=1;
                        }
                        break;
                    case Println:
                        {
                        alt7=2;
                        }
                        break;
                    case Print:
                        {
                        alt7=3;
                        }
                        break;
                    case Assert:
                        {
                        alt7=4;
                        }
                        break;
                    case Size:
                        {
                        alt7=5;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 7, 2, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:70:6: ^( FUNC_CALL Identifier ( exprList )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall326); 

                    match(input, Token.DOWN, null); 
                    Identifier12=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_functionCall328); 
                    // grammar/PlazmaScriptWalker.g:70:29: ( exprList )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==EXP_LIST) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:70:29: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall330);
                            exprList13=exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new FunctionCallNode((Identifier12!=null?Identifier12.getText():null), exprList13, functions, globalScope);

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:71:6: ^( FUNC_CALL Println ( expression )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall342); 

                    match(input, Token.DOWN, null); 
                    match(input,Println,FOLLOW_Println_in_functionCall344); 
                    // grammar/PlazmaScriptWalker.g:71:26: ( expression )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( ((LA6_0>=TERNARY && LA6_0<=NEGATE)||LA6_0==LOOKUP||(LA6_0>=In && LA6_0<=Null)||(LA6_0>=Or && LA6_0<=Pow)||(LA6_0>=GT && LA6_0<=Modulus)) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:71:26: expression
                            {
                            pushFollow(FOLLOW_expression_in_functionCall346);
                            expression14=expression();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = new PrintlnNode(expression14);

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:72:6: ^( FUNC_CALL Print expression )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall359); 

                    match(input, Token.DOWN, null); 
                    match(input,Print,FOLLOW_Print_in_functionCall361); 
                    pushFollow(FOLLOW_expression_in_functionCall363);
                    expression15=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new PrintNode(expression15);

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:73:6: ^( FUNC_CALL Assert expression )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall378); 

                    match(input, Token.DOWN, null); 
                    match(input,Assert,FOLLOW_Assert_in_functionCall380); 
                    pushFollow(FOLLOW_expression_in_functionCall382);
                    expression16=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AssertNode(expression16);

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:74:6: ^( FUNC_CALL Size expression )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall396); 

                    match(input, Token.DOWN, null); 
                    match(input,Size,FOLLOW_Size_in_functionCall398); 
                    pushFollow(FOLLOW_expression_in_functionCall400);
                    expression17=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new SizeNode(expression17);

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
    // grammar/PlazmaScriptWalker.g:77:1: ifStatement returns [LNode node] : ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? ) ;
    public final LNode ifStatement() throws RecognitionException {
        LNode node = null;

        IfNode ifNode = new IfNode();
        try {
            // grammar/PlazmaScriptWalker.g:80:3: ( ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? ) )
            // grammar/PlazmaScriptWalker.g:80:6: ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? )
            {
            match(input,IF,FOLLOW_IF_in_ifStatement438); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_ifStat_in_ifStatement440);
            ifStat(ifNode);

            state._fsp--;

            // grammar/PlazmaScriptWalker.g:80:26: ( elseIfStat[ifNode] )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==EXP) ) {
                    int LA8_1 = input.LA(2);

                    if ( (LA8_1==DOWN) ) {
                        int LA8_3 = input.LA(3);

                        if ( ((LA8_3>=TERNARY && LA8_3<=NEGATE)||LA8_3==LOOKUP||(LA8_3>=In && LA8_3<=Null)||(LA8_3>=Or && LA8_3<=Pow)||(LA8_3>=GT && LA8_3<=Modulus)) ) {
                            alt8=1;
                        }


                    }


                }


                switch (alt8) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:80:27: elseIfStat[ifNode]
            	    {
            	    pushFollow(FOLLOW_elseIfStat_in_ifStatement444);
            	    elseIfStat(ifNode);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            // grammar/PlazmaScriptWalker.g:80:48: ( elseStat[ifNode] )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==EXP) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:80:49: elseStat[ifNode]
                    {
                    pushFollow(FOLLOW_elseStat_in_ifStatement450);
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
    // grammar/PlazmaScriptWalker.g:83:1: ifStat[IfNode parent] : ^( EXP expression block ) ;
    public final void ifStat(IfNode parent) throws RecognitionException {
        LNode expression18 = null;

        LNode block19 = null;


        try {
            // grammar/PlazmaScriptWalker.g:84:3: ( ^( EXP expression block ) )
            // grammar/PlazmaScriptWalker.g:84:6: ^( EXP expression block )
            {
            match(input,EXP,FOLLOW_EXP_in_ifStat470); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_ifStat472);
            expression18=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_ifStat474);
            block19=block();

            state._fsp--;


            match(input, Token.UP, null); 
            parent.addChoice(expression18, block19);

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
    // grammar/PlazmaScriptWalker.g:87:1: elseIfStat[IfNode parent] : ^( EXP expression block ) ;
    public final void elseIfStat(IfNode parent) throws RecognitionException {
        LNode expression20 = null;

        LNode block21 = null;


        try {
            // grammar/PlazmaScriptWalker.g:88:3: ( ^( EXP expression block ) )
            // grammar/PlazmaScriptWalker.g:88:6: ^( EXP expression block )
            {
            match(input,EXP,FOLLOW_EXP_in_elseIfStat493); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_elseIfStat495);
            expression20=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_elseIfStat497);
            block21=block();

            state._fsp--;


            match(input, Token.UP, null); 
            parent.addChoice(expression20, block21);

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
    // grammar/PlazmaScriptWalker.g:91:1: elseStat[IfNode parent] : ^( EXP block ) ;
    public final void elseStat(IfNode parent) throws RecognitionException {
        LNode block22 = null;


        try {
            // grammar/PlazmaScriptWalker.g:92:3: ( ^( EXP block ) )
            // grammar/PlazmaScriptWalker.g:92:6: ^( EXP block )
            {
            match(input,EXP,FOLLOW_EXP_in_elseStat516); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_block_in_elseStat518);
            block22=block();

            state._fsp--;


            match(input, Token.UP, null); 
            parent.addChoice(new AtomNode(true), block22);

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
    // grammar/PlazmaScriptWalker.g:99:1: forStatement returns [LNode node] : ^( For Identifier a= expression block ) ;
    public final LNode forStatement() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier23=null;
        LNode a = null;

        LNode block24 = null;


        try {
            // grammar/PlazmaScriptWalker.g:100:3: ( ^( For Identifier a= expression block ) )
            // grammar/PlazmaScriptWalker.g:100:6: ^( For Identifier a= expression block )
            {
            match(input,For,FOLLOW_For_in_forStatement549); 

            match(input, Token.DOWN, null); 
            Identifier23=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_forStatement551); 
            pushFollow(FOLLOW_expression_in_forStatement555);
            a=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_forStatement557);
            block24=block();

            state._fsp--;


            match(input, Token.UP, null); 
            node = new ForStatementNode2((Identifier23!=null?Identifier23.getText():null), a, block24, currentScope);

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
    // grammar/PlazmaScriptWalker.g:104:1: whileStatement returns [LNode node] : ^( While expression block ) ;
    public final LNode whileStatement() throws RecognitionException {
        LNode node = null;

        LNode expression25 = null;

        LNode block26 = null;


        try {
            // grammar/PlazmaScriptWalker.g:105:3: ( ^( While expression block ) )
            // grammar/PlazmaScriptWalker.g:105:6: ^( While expression block )
            {
            match(input,While,FOLLOW_While_in_whileStatement582); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_whileStatement584);
            expression25=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_whileStatement586);
            block26=block();

            state._fsp--;


            match(input, Token.UP, null); 
            node = new WhileStatementNode(expression25, block26);

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
    // grammar/PlazmaScriptWalker.g:108:1: idList returns [java.util.List<String> i] : ^( ID_LIST ( Identifier )+ ) ;
    public final java.util.List<String> idList() throws RecognitionException {
        java.util.List<String> i = null;

        CommonTree Identifier27=null;

        i = new java.util.ArrayList<String>();
        try {
            // grammar/PlazmaScriptWalker.g:110:3: ( ^( ID_LIST ( Identifier )+ ) )
            // grammar/PlazmaScriptWalker.g:110:6: ^( ID_LIST ( Identifier )+ )
            {
            match(input,ID_LIST,FOLLOW_ID_LIST_in_idList613); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:110:16: ( Identifier )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==Identifier) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:110:17: Identifier
            	    {
            	    Identifier27=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_idList616); 
            	    i.add((Identifier27!=null?Identifier27.getText():null));

            	    }
            	    break;

            	default :
            	    if ( cnt10 >= 1 ) break loop10;
                        EarlyExitException eee =
                            new EarlyExitException(10, input);
                        throw eee;
                }
                cnt10++;
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
    // grammar/PlazmaScriptWalker.g:113:1: exprList returns [java.util.List<LNode> e] : ^( EXP_LIST ( expression )+ ) ;
    public final java.util.List<LNode> exprList() throws RecognitionException {
        java.util.List<LNode> e = null;

        LNode expression28 = null;


        e = new java.util.ArrayList<LNode>();
        try {
            // grammar/PlazmaScriptWalker.g:115:3: ( ^( EXP_LIST ( expression )+ ) )
            // grammar/PlazmaScriptWalker.g:115:6: ^( EXP_LIST ( expression )+ )
            {
            match(input,EXP_LIST,FOLLOW_EXP_LIST_in_exprList646); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:115:17: ( expression )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>=TERNARY && LA11_0<=NEGATE)||LA11_0==LOOKUP||(LA11_0>=In && LA11_0<=Null)||(LA11_0>=Or && LA11_0<=Pow)||(LA11_0>=GT && LA11_0<=Modulus)) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:115:18: expression
            	    {
            	    pushFollow(FOLLOW_expression_in_exprList649);
            	    expression28=expression();

            	    state._fsp--;

            	    e.add(expression28);

            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
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
    // grammar/PlazmaScriptWalker.g:117:1: exprPair returns [PairNode node] : ^( EXP_PAIR k= expression v= expression ) ;
    public final PairNode exprPair() throws RecognitionException {
        PairNode node = null;

        LNode k = null;

        LNode v = null;


        try {
            // grammar/PlazmaScriptWalker.g:118:3: ( ^( EXP_PAIR k= expression v= expression ) )
            // grammar/PlazmaScriptWalker.g:118:6: ^( EXP_PAIR k= expression v= expression )
            {
            match(input,EXP_PAIR,FOLLOW_EXP_PAIR_in_exprPair672); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_exprPair676);
            k=expression();

            state._fsp--;

            pushFollow(FOLLOW_expression_in_exprPair680);
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
    // grammar/PlazmaScriptWalker.g:121:1: exprMap returns [java.util.List<PairNode> e] : ^( EXP_MAP ( exprPair )+ ) ;
    public final java.util.List<PairNode> exprMap() throws RecognitionException {
        java.util.List<PairNode> e = null;

        PairNode exprPair29 = null;


        e = new java.util.ArrayList<PairNode>();
        try {
            // grammar/PlazmaScriptWalker.g:123:3: ( ^( EXP_MAP ( exprPair )+ ) )
            // grammar/PlazmaScriptWalker.g:123:6: ^( EXP_MAP ( exprPair )+ )
            {
            match(input,EXP_MAP,FOLLOW_EXP_MAP_in_exprMap708); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:123:16: ( exprPair )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==EXP_PAIR) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:123:17: exprPair
            	    {
            	    pushFollow(FOLLOW_exprPair_in_exprMap711);
            	    exprPair29=exprPair();

            	    state._fsp--;

            	    e.add(exprPair29);

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
        return e;
    }
    // $ANTLR end "exprMap"


    // $ANTLR start "expression"
    // grammar/PlazmaScriptWalker.g:127:1: expression returns [LNode node] : ( ^( TERNARY a= expression b= expression c= expression ) | ^( In a= expression b= expression ) | ^( RangeE a= expression b= expression ) | ^( Range a= expression b= expression ) | ^( '||' a= expression b= expression ) | ^( '&&' a= expression b= expression ) | ^( '==' a= expression b= expression ) | ^( '!=' a= expression b= expression ) | ^( '>=' a= expression b= expression ) | ^( '<=' a= expression b= expression ) | ^( '>' a= expression b= expression ) | ^( '<' a= expression b= expression ) | ^( '+' a= expression b= expression ) | ^( '-' a= expression b= expression ) | ^( '*' a= expression b= expression ) | ^( '/' a= expression b= expression ) | ^( '%' a= expression b= expression ) | ^( '^' a= expression b= expression ) | ^( UNARY_MIN a= expression ) | ^( NEGATE a= expression ) | Integer | Number | Bool | Date | Null | lookup );
    public final LNode expression() throws RecognitionException {
        LNode node = null;

        CommonTree Integer30=null;
        CommonTree Number31=null;
        CommonTree Bool32=null;
        CommonTree Date33=null;
        LNode a = null;

        LNode b = null;

        LNode c = null;

        LNode lookup34 = null;


        try {
            // grammar/PlazmaScriptWalker.g:128:3: ( ^( TERNARY a= expression b= expression c= expression ) | ^( In a= expression b= expression ) | ^( RangeE a= expression b= expression ) | ^( Range a= expression b= expression ) | ^( '||' a= expression b= expression ) | ^( '&&' a= expression b= expression ) | ^( '==' a= expression b= expression ) | ^( '!=' a= expression b= expression ) | ^( '>=' a= expression b= expression ) | ^( '<=' a= expression b= expression ) | ^( '>' a= expression b= expression ) | ^( '<' a= expression b= expression ) | ^( '+' a= expression b= expression ) | ^( '-' a= expression b= expression ) | ^( '*' a= expression b= expression ) | ^( '/' a= expression b= expression ) | ^( '%' a= expression b= expression ) | ^( '^' a= expression b= expression ) | ^( UNARY_MIN a= expression ) | ^( NEGATE a= expression ) | Integer | Number | Bool | Date | Null | lookup )
            int alt13=26;
            switch ( input.LA(1) ) {
            case TERNARY:
                {
                alt13=1;
                }
                break;
            case In:
                {
                alt13=2;
                }
                break;
            case RangeE:
                {
                alt13=3;
                }
                break;
            case Range:
                {
                alt13=4;
                }
                break;
            case Or:
                {
                alt13=5;
                }
                break;
            case And:
                {
                alt13=6;
                }
                break;
            case Equals:
                {
                alt13=7;
                }
                break;
            case NEquals:
                {
                alt13=8;
                }
                break;
            case GTEquals:
                {
                alt13=9;
                }
                break;
            case LTEquals:
                {
                alt13=10;
                }
                break;
            case GT:
                {
                alt13=11;
                }
                break;
            case LT:
                {
                alt13=12;
                }
                break;
            case Add:
                {
                alt13=13;
                }
                break;
            case Subtract:
                {
                alt13=14;
                }
                break;
            case Multiply:
                {
                alt13=15;
                }
                break;
            case Divide:
                {
                alt13=16;
                }
                break;
            case Modulus:
                {
                alt13=17;
                }
                break;
            case Pow:
                {
                alt13=18;
                }
                break;
            case UNARY_MIN:
                {
                alt13=19;
                }
                break;
            case NEGATE:
                {
                alt13=20;
                }
                break;
            case Integer:
                {
                alt13=21;
                }
                break;
            case Number:
                {
                alt13=22;
                }
                break;
            case Bool:
                {
                alt13=23;
                }
                break;
            case Date:
                {
                alt13=24;
                }
                break;
            case Null:
                {
                alt13=25;
                }
                break;
            case LOOKUP:
                {
                alt13=26;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }

            switch (alt13) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:128:6: ^( TERNARY a= expression b= expression c= expression )
                    {
                    match(input,TERNARY,FOLLOW_TERNARY_in_expression736); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression740);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression744);
                    b=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression748);
                    c=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new TernaryNode(a, b, c);

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:129:6: ^( In a= expression b= expression )
                    {
                    match(input,In,FOLLOW_In_in_expression759); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression763);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression767);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new InNode(a, b);

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:131:6: ^( RangeE a= expression b= expression )
                    {
                    match(input,RangeE,FOLLOW_RangeE_in_expression799); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression803);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression807);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new RangeENode(a, b);

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:132:6: ^( Range a= expression b= expression )
                    {
                    match(input,Range,FOLLOW_Range_in_expression833); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression837);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression841);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new RangeNode(a, b);

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:134:6: ^( '||' a= expression b= expression )
                    {
                    match(input,Or,FOLLOW_Or_in_expression871); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression875);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression879);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new OrNode(a, b);

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScriptWalker.g:135:6: ^( '&&' a= expression b= expression )
                    {
                    match(input,And,FOLLOW_And_in_expression906); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression910);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression914);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AndNode(a, b);

                    }
                    break;
                case 7 :
                    // grammar/PlazmaScriptWalker.g:136:6: ^( '==' a= expression b= expression )
                    {
                    match(input,Equals,FOLLOW_Equals_in_expression941); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression945);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression949);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new EqualsNode(a, b);

                    }
                    break;
                case 8 :
                    // grammar/PlazmaScriptWalker.g:137:6: ^( '!=' a= expression b= expression )
                    {
                    match(input,NEquals,FOLLOW_NEquals_in_expression976); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression980);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression984);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new NotEqualsNode(a, b);

                    }
                    break;
                case 9 :
                    // grammar/PlazmaScriptWalker.g:138:6: ^( '>=' a= expression b= expression )
                    {
                    match(input,GTEquals,FOLLOW_GTEquals_in_expression1011); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1015);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1019);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new GTEqualsNode(a, b);

                    }
                    break;
                case 10 :
                    // grammar/PlazmaScriptWalker.g:139:6: ^( '<=' a= expression b= expression )
                    {
                    match(input,LTEquals,FOLLOW_LTEquals_in_expression1046); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1050);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1054);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new LTEqualsNode(a, b);

                    }
                    break;
                case 11 :
                    // grammar/PlazmaScriptWalker.g:140:6: ^( '>' a= expression b= expression )
                    {
                    match(input,GT,FOLLOW_GT_in_expression1081); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1085);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1089);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new GTNode(a, b);

                    }
                    break;
                case 12 :
                    // grammar/PlazmaScriptWalker.g:141:6: ^( '<' a= expression b= expression )
                    {
                    match(input,LT,FOLLOW_LT_in_expression1117); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1121);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1125);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new LTNode(a, b);

                    }
                    break;
                case 13 :
                    // grammar/PlazmaScriptWalker.g:142:6: ^( '+' a= expression b= expression )
                    {
                    match(input,Add,FOLLOW_Add_in_expression1153); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1157);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1161);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AddNode(a, b);

                    }
                    break;
                case 14 :
                    // grammar/PlazmaScriptWalker.g:143:6: ^( '-' a= expression b= expression )
                    {
                    match(input,Subtract,FOLLOW_Subtract_in_expression1189); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1193);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1197);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new SubNode(a, b);

                    }
                    break;
                case 15 :
                    // grammar/PlazmaScriptWalker.g:144:6: ^( '*' a= expression b= expression )
                    {
                    match(input,Multiply,FOLLOW_Multiply_in_expression1225); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1229);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1233);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new MulNode(a, b);

                    }
                    break;
                case 16 :
                    // grammar/PlazmaScriptWalker.g:145:6: ^( '/' a= expression b= expression )
                    {
                    match(input,Divide,FOLLOW_Divide_in_expression1261); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1265);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1269);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new DivNode(a, b);

                    }
                    break;
                case 17 :
                    // grammar/PlazmaScriptWalker.g:146:6: ^( '%' a= expression b= expression )
                    {
                    match(input,Modulus,FOLLOW_Modulus_in_expression1297); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1301);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1305);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new ModNode(a, b);

                    }
                    break;
                case 18 :
                    // grammar/PlazmaScriptWalker.g:147:6: ^( '^' a= expression b= expression )
                    {
                    match(input,Pow,FOLLOW_Pow_in_expression1333); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1337);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1341);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new PowNode(a, b);

                    }
                    break;
                case 19 :
                    // grammar/PlazmaScriptWalker.g:148:6: ^( UNARY_MIN a= expression )
                    {
                    match(input,UNARY_MIN,FOLLOW_UNARY_MIN_in_expression1369); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1373);
                    a=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new UnaryMinusNode(a);

                    }
                    break;
                case 20 :
                    // grammar/PlazmaScriptWalker.g:149:6: ^( NEGATE a= expression )
                    {
                    match(input,NEGATE,FOLLOW_NEGATE_in_expression1408); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1412);
                    a=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new NegateNode(a);

                    }
                    break;
                case 21 :
                    // grammar/PlazmaScriptWalker.g:151:6: Integer
                    {
                    Integer30=(CommonTree)match(input,Integer,FOLLOW_Integer_in_expression1451); 
                    node = new AtomNode(new Integer((Integer30!=null?Integer30.getText():null)));

                    }
                    break;
                case 22 :
                    // grammar/PlazmaScriptWalker.g:152:6: Number
                    {
                    Number31=(CommonTree)match(input,Number,FOLLOW_Number_in_expression1502); 
                    node = new AtomNode(Double.parseDouble((Number31!=null?Number31.getText():null)));

                    }
                    break;
                case 23 :
                    // grammar/PlazmaScriptWalker.g:153:6: Bool
                    {
                    Bool32=(CommonTree)match(input,Bool,FOLLOW_Bool_in_expression1554); 
                    node = new AtomNode(Boolean.parseBoolean((Bool32!=null?Bool32.getText():null)));

                    }
                    break;
                case 24 :
                    // grammar/PlazmaScriptWalker.g:154:6: Date
                    {
                    Date33=(CommonTree)match(input,Date,FOLLOW_Date_in_expression1608); 
                    node = new AtomNode(AtomNode.parseDate((Date33!=null?Date33.getText():null)));

                    }
                    break;
                case 25 :
                    // grammar/PlazmaScriptWalker.g:155:6: Null
                    {
                    match(input,Null,FOLLOW_Null_in_expression1664); 
                    node = new NullNode();

                    }
                    break;
                case 26 :
                    // grammar/PlazmaScriptWalker.g:156:6: lookup
                    {
                    pushFollow(FOLLOW_lookup_in_expression1718);
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
    // grammar/PlazmaScriptWalker.g:159:1: list returns [LNode node] : ^( LIST ( exprList )? ) ;
    public final LNode list() throws RecognitionException {
        LNode node = null;

        java.util.List<LNode> exprList35 = null;


        try {
            // grammar/PlazmaScriptWalker.g:160:3: ( ^( LIST ( exprList )? ) )
            // grammar/PlazmaScriptWalker.g:160:6: ^( LIST ( exprList )? )
            {
            match(input,LIST,FOLLOW_LIST_in_list1782); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // grammar/PlazmaScriptWalker.g:160:13: ( exprList )?
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==EXP_LIST) ) {
                    alt14=1;
                }
                switch (alt14) {
                    case 1 :
                        // grammar/PlazmaScriptWalker.g:160:13: exprList
                        {
                        pushFollow(FOLLOW_exprList_in_list1784);
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
    // grammar/PlazmaScriptWalker.g:163:1: map returns [LNode node] : ^( MAP ( exprMap )? ) ;
    public final LNode map() throws RecognitionException {
        LNode node = null;

        java.util.List<PairNode> exprMap36 = null;


        try {
            // grammar/PlazmaScriptWalker.g:164:3: ( ^( MAP ( exprMap )? ) )
            // grammar/PlazmaScriptWalker.g:164:6: ^( MAP ( exprMap )? )
            {
            match(input,MAP,FOLLOW_MAP_in_map1807); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // grammar/PlazmaScriptWalker.g:164:12: ( exprMap )?
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==EXP_MAP) ) {
                    alt15=1;
                }
                switch (alt15) {
                    case 1 :
                        // grammar/PlazmaScriptWalker.g:164:12: exprMap
                        {
                        pushFollow(FOLLOW_exprMap_in_map1809);
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
    // grammar/PlazmaScriptWalker.g:167:1: lookup returns [LNode node] : ( ^( LOOKUP functionCall (i= indexes )? ) | ^( LOOKUP list (i= indexes )? ) | ^( LOOKUP map (i= indexes )? ) | ^( LOOKUP expression (i= indexes )? ) | ^( LOOKUP Identifier (i= indexes )? ) | ^( LOOKUP String (i= indexes )? ) );
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
            // grammar/PlazmaScriptWalker.g:168:3: ( ^( LOOKUP functionCall (i= indexes )? ) | ^( LOOKUP list (i= indexes )? ) | ^( LOOKUP map (i= indexes )? ) | ^( LOOKUP expression (i= indexes )? ) | ^( LOOKUP Identifier (i= indexes )? ) | ^( LOOKUP String (i= indexes )? ) )
            int alt22=6;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==LOOKUP) ) {
                int LA22_1 = input.LA(2);

                if ( (LA22_1==DOWN) ) {
                    switch ( input.LA(3) ) {
                    case Identifier:
                        {
                        alt22=5;
                        }
                        break;
                    case String:
                        {
                        alt22=6;
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
                    case Date:
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
                        alt22=4;
                        }
                        break;
                    case FUNC_CALL:
                        {
                        alt22=1;
                        }
                        break;
                    case MAP:
                        {
                        alt22=3;
                        }
                        break;
                    case LIST:
                        {
                        alt22=2;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 22, 2, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:168:6: ^( LOOKUP functionCall (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup1832); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_functionCall_in_lookup1834);
                    functionCall37=functionCall();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:168:29: (i= indexes )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==INDEXES) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:168:29: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1838);
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
                    // grammar/PlazmaScriptWalker.g:169:6: ^( LOOKUP list (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup1850); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_list_in_lookup1852);
                    list38=list();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:169:21: (i= indexes )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==INDEXES) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:169:21: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1856);
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
                    // grammar/PlazmaScriptWalker.g:170:6: ^( LOOKUP map (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup1876); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_map_in_lookup1878);
                    map39=map();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:170:20: (i= indexes )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==INDEXES) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:170:20: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1882);
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
                    // grammar/PlazmaScriptWalker.g:171:6: ^( LOOKUP expression (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup1905); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_lookup1907);
                    expression40=expression();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:171:27: (i= indexes )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==INDEXES) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:171:27: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1911);
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
                    // grammar/PlazmaScriptWalker.g:172:6: ^( LOOKUP Identifier (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup1925); 

                    match(input, Token.DOWN, null); 
                    Identifier41=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_lookup1927); 
                    // grammar/PlazmaScriptWalker.g:172:27: (i= indexes )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==INDEXES) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:172:27: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1931);
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
                    // grammar/PlazmaScriptWalker.g:173:6: ^( LOOKUP String (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup1945); 

                    match(input, Token.DOWN, null); 
                    String42=(CommonTree)match(input,String,FOLLOW_String_in_lookup1947); 
                    // grammar/PlazmaScriptWalker.g:173:23: (i= indexes )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==INDEXES) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:173:23: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1951);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(new AtomNode((String42!=null?String42.getText():null)), i) : new AtomNode((String42!=null?String42.getText():null));

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
    // grammar/PlazmaScriptWalker.g:176:1: indexes returns [java.util.List<LNode> e] : ^( INDEXES ( expression )+ ) ;
    public final java.util.List<LNode> indexes() throws RecognitionException {
        java.util.List<LNode> e = null;

        LNode expression43 = null;


        e = new java.util.ArrayList<LNode>();
        try {
            // grammar/PlazmaScriptWalker.g:178:3: ( ^( INDEXES ( expression )+ ) )
            // grammar/PlazmaScriptWalker.g:178:6: ^( INDEXES ( expression )+ )
            {
            match(input,INDEXES,FOLLOW_INDEXES_in_indexes1985); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:178:16: ( expression )+
            int cnt23=0;
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( ((LA23_0>=TERNARY && LA23_0<=NEGATE)||LA23_0==LOOKUP||(LA23_0>=In && LA23_0<=Null)||(LA23_0>=Or && LA23_0<=Pow)||(LA23_0>=GT && LA23_0<=Modulus)) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:178:17: expression
            	    {
            	    pushFollow(FOLLOW_expression_in_indexes1988);
            	    expression43=expression();

            	    state._fsp--;

            	    e.add(expression43);

            	    }
            	    break;

            	default :
            	    if ( cnt23 >= 1 ) break loop23;
                        EarlyExitException eee =
                            new EarlyExitException(23, input);
                        throw eee;
                }
                cnt23++;
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

    // Delegated rules


 

    public static final BitSet FOLLOW_block_in_walk50 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BLOCK_in_block81 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STATEMENTS_in_block84 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_block87 = new BitSet(new long[]{0x000000300C004188L});
    public static final BitSet FOLLOW_RETURN_in_block95 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_block98 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_assignment_in_statement122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_statement135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStatement_in_statement146 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forStatement_in_statement158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whileStatement_in_statement170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Break_in_statement179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Continue_in_statement232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGNMENT_in_assignment297 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_assignment299 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_indexes_in_assignment301 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_expression_in_assignment304 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall326 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_functionCall328 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall330 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall342 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Println_in_functionCall344 = new BitSet(new long[]{0x3FBFBFC0004B8008L});
    public static final BitSet FOLLOW_expression_in_functionCall346 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall359 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Print_in_functionCall361 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_expression_in_functionCall363 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall378 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Assert_in_functionCall380 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_expression_in_functionCall382 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall396 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Size_in_functionCall398 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_expression_in_functionCall400 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IF_in_ifStatement438 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ifStat_in_ifStatement440 = new BitSet(new long[]{0x0000000000000208L});
    public static final BitSet FOLLOW_elseIfStat_in_ifStatement444 = new BitSet(new long[]{0x0000000000000208L});
    public static final BitSet FOLLOW_elseStat_in_ifStatement450 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_ifStat470 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_ifStat472 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_ifStat474 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_elseIfStat493 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_elseIfStat495 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_elseIfStat497 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_elseStat516 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_block_in_elseStat518 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_For_in_forStatement549 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_forStatement551 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_expression_in_forStatement555 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_forStatement557 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_While_in_whileStatement582 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_whileStatement584 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_whileStatement586 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_LIST_in_idList613 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_idList616 = new BitSet(new long[]{0x0000000010000008L});
    public static final BitSet FOLLOW_EXP_LIST_in_exprList646 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_exprList649 = new BitSet(new long[]{0x3FBFBFC0004B8008L});
    public static final BitSet FOLLOW_EXP_PAIR_in_exprPair672 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_exprPair676 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_expression_in_exprPair680 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_MAP_in_exprMap708 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprPair_in_exprMap711 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_TERNARY_in_expression736 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression740 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_expression_in_expression744 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_expression_in_expression748 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_In_in_expression759 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression763 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_expression_in_expression767 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RangeE_in_expression799 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression803 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_expression_in_expression807 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Range_in_expression833 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression837 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_expression_in_expression841 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Or_in_expression871 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression875 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_expression_in_expression879 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_And_in_expression906 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression910 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_expression_in_expression914 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Equals_in_expression941 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression945 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_expression_in_expression949 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEquals_in_expression976 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression980 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_expression_in_expression984 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GTEquals_in_expression1011 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1015 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_expression_in_expression1019 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LTEquals_in_expression1046 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1050 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_expression_in_expression1054 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GT_in_expression1081 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1085 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_expression_in_expression1089 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LT_in_expression1117 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1121 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_expression_in_expression1125 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Add_in_expression1153 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1157 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_expression_in_expression1161 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Subtract_in_expression1189 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1193 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_expression_in_expression1197 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Multiply_in_expression1225 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1229 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_expression_in_expression1233 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Divide_in_expression1261 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1265 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_expression_in_expression1269 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Modulus_in_expression1297 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1301 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_expression_in_expression1305 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Pow_in_expression1333 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1337 = new BitSet(new long[]{0x3FBFBFC0004B8000L});
    public static final BitSet FOLLOW_expression_in_expression1341 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_UNARY_MIN_in_expression1369 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1373 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEGATE_in_expression1408 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1412 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Integer_in_expression1451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Number_in_expression1502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Bool_in_expression1554 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Date_in_expression1608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Null_in_expression1664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_expression1718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LIST_in_list1782 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprList_in_list1784 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MAP_in_map1807 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprMap_in_map1809 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup1832 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_functionCall_in_lookup1834 = new BitSet(new long[]{0x0000000000080008L});
    public static final BitSet FOLLOW_indexes_in_lookup1838 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup1850 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_list_in_lookup1852 = new BitSet(new long[]{0x0000000000080008L});
    public static final BitSet FOLLOW_indexes_in_lookup1856 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup1876 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_map_in_lookup1878 = new BitSet(new long[]{0x0000000000080008L});
    public static final BitSet FOLLOW_indexes_in_lookup1882 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup1905 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_lookup1907 = new BitSet(new long[]{0x0000000000080008L});
    public static final BitSet FOLLOW_indexes_in_lookup1911 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup1925 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_lookup1927 = new BitSet(new long[]{0x0000000000080008L});
    public static final BitSet FOLLOW_indexes_in_lookup1931 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup1945 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_String_in_lookup1947 = new BitSet(new long[]{0x0000000000080008L});
    public static final BitSet FOLLOW_indexes_in_lookup1951 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INDEXES_in_indexes1985 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_indexes1988 = new BitSet(new long[]{0x3FBFBFC0004B8008L});

}