// $ANTLR 3.2 Sep 23, 2009 12:02:23 grammar/PlazmaScriptWalker.g 2016-03-25 15:07:21

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BLOCK", "RETURN", "STATEMENTS", "ASSIGNMENT", "FUNC_CALL", "EXP", "EXP_PAIR", "EXP_MAP", "EXP_LIST", "ID_LIST", "IF", "TERNARY", "UNARY_MIN", "NEGATE", "FUNCTION", "INDEXES", "MAP", "LIST", "LOOKUP", "BREAK", "CONTINUE", "Return", "Break", "Continue", "Identifier", "Println", "Print", "Assert", "Size", "If", "Else", "Var", "Def", "For", "While", "In", "RangeE", "Range", "Integer", "Number", "Bool", "Date", "Null", "String", "Or", "And", "Equals", "NEquals", "GTEquals", "LTEquals", "Pow", "Excl", "GT", "LT", "Add", "Subtract", "Multiply", "Divide", "Modulus", "OBrace", "CBrace", "OBracket", "CBracket", "OParen", "CParen", "SColon", "Assign", "Comma", "QMark", "Colon", "Int", "Digit", "YYYY", "MM", "DD", "ContextIdentifier", "Comment", "Space"
    };
    public static final int FUNCTION=18;
    public static final int OParen=67;
    public static final int LT=57;
    public static final int YYYY=76;
    public static final int Assert=31;
    public static final int TERNARY=15;
    public static final int EXP_LIST=12;
    public static final int While=38;
    public static final int Date=45;
    public static final int ID_LIST=13;
    public static final int Add=58;
    public static final int QMark=72;
    public static final int DD=78;
    public static final int EOF=-1;
    public static final int BREAK=23;
    public static final int Identifier=28;
    public static final int Int=74;
    public static final int IF=14;
    public static final int FUNC_CALL=8;
    public static final int Space=81;
    public static final int Size=32;
    public static final int Assign=70;
    public static final int CParen=68;
    public static final int EXP_MAP=11;
    public static final int Number=43;
    public static final int Comment=80;
    public static final int EXP=9;
    public static final int CONTINUE=24;
    public static final int MM=77;
    public static final int GTEquals=52;
    public static final int Print=30;
    public static final int CBrace=64;
    public static final int RETURN=5;
    public static final int String=47;
    public static final int Or=48;
    public static final int Return=25;
    public static final int If=33;
    public static final int And=49;
    public static final int Null=46;
    public static final int ContextIdentifier=79;
    public static final int CBracket=66;
    public static final int Println=29;
    public static final int In=39;
    public static final int Bool=44;
    public static final int NEquals=51;
    public static final int Continue=27;
    public static final int Subtract=59;
    public static final int EXP_PAIR=10;
    public static final int Modulus=62;
    public static final int Multiply=60;
    public static final int OBrace=63;
    public static final int NEGATE=17;
    public static final int INDEXES=19;
    public static final int Colon=73;
    public static final int Excl=55;
    public static final int Digit=75;
    public static final int LIST=21;
    public static final int For=37;
    public static final int Divide=61;
    public static final int Def=36;
    public static final int SColon=69;
    public static final int RangeE=40;
    public static final int LOOKUP=22;
    public static final int Range=41;
    public static final int OBracket=65;
    public static final int Break=26;
    public static final int BLOCK=4;
    public static final int MAP=20;
    public static final int STATEMENTS=6;
    public static final int GT=56;
    public static final int UNARY_MIN=16;
    public static final int ASSIGNMENT=7;
    public static final int Else=34;
    public static final int Equals=50;
    public static final int Comma=71;
    public static final int Integer=42;
    public static final int Var=35;
    public static final int Pow=54;
    public static final int LTEquals=53;

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



          
          //BlockNode bn = new BlockNode();
          //node = bn;
          //Scope local = new Scope(currentScope);
          //currentScope = local;

          Scope local = new Scope(currentScope);
          currentScope = local;
          BlockNode bn = new BlockNode(local);
          node = bn;
          

        try {
            // grammar/PlazmaScriptWalker.g:57:3: ( ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) ) )
            // grammar/PlazmaScriptWalker.g:57:6: ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) )
            {
            match(input,BLOCK,FOLLOW_BLOCK_in_block81); 

            match(input, Token.DOWN, null); 
            match(input,STATEMENTS,FOLLOW_STATEMENTS_in_block84); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // grammar/PlazmaScriptWalker.g:57:27: ( statement )*
                loop1:
                do {
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( ((LA1_0>=ASSIGNMENT && LA1_0<=FUNC_CALL)||LA1_0==IF||(LA1_0>=Break && LA1_0<=Continue)||(LA1_0>=For && LA1_0<=While)) ) {
                        alt1=1;
                    }


                    switch (alt1) {
                	case 1 :
                	    // grammar/PlazmaScriptWalker.g:57:28: statement
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
                // grammar/PlazmaScriptWalker.g:57:86: ( expression )?
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>=TERNARY && LA2_0<=NEGATE)||LA2_0==LOOKUP||(LA2_0>=In && LA2_0<=Null)||(LA2_0>=Or && LA2_0<=Pow)||(LA2_0>=GT && LA2_0<=Modulus)) ) {
                    alt2=1;
                }
                switch (alt2) {
                    case 1 :
                        // grammar/PlazmaScriptWalker.g:57:87: expression
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
    // grammar/PlazmaScriptWalker.g:60:1: statement returns [LNode node] : ( assignment | functionCall | ifStatement | forStatement | whileStatement | Break | Continue );
    public final LNode statement() throws RecognitionException {
        LNode node = null;

        LNode assignment4 = null;

        LNode functionCall5 = null;

        LNode ifStatement6 = null;

        LNode forStatement7 = null;

        LNode whileStatement8 = null;


        try {
            // grammar/PlazmaScriptWalker.g:61:3: ( assignment | functionCall | ifStatement | forStatement | whileStatement | Break | Continue )
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
                    // grammar/PlazmaScriptWalker.g:61:6: assignment
                    {
                    pushFollow(FOLLOW_assignment_in_statement122);
                    assignment4=assignment();

                    state._fsp--;

                    node = assignment4;

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:62:6: functionCall
                    {
                    pushFollow(FOLLOW_functionCall_in_statement135);
                    functionCall5=functionCall();

                    state._fsp--;

                    node = functionCall5;

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:63:6: ifStatement
                    {
                    pushFollow(FOLLOW_ifStatement_in_statement146);
                    ifStatement6=ifStatement();

                    state._fsp--;

                    node = ifStatement6;

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:64:6: forStatement
                    {
                    pushFollow(FOLLOW_forStatement_in_statement158);
                    forStatement7=forStatement();

                    state._fsp--;

                    node = forStatement7;

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:66:6: whileStatement
                    {
                    pushFollow(FOLLOW_whileStatement_in_statement170);
                    whileStatement8=whileStatement();

                    state._fsp--;

                    node = whileStatement8;

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScriptWalker.g:67:6: Break
                    {
                    match(input,Break,FOLLOW_Break_in_statement179); 
                    node = new BreakNode();

                    }
                    break;
                case 7 :
                    // grammar/PlazmaScriptWalker.g:68:6: Continue
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
    // grammar/PlazmaScriptWalker.g:73:1: assignment returns [LNode node] : ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression ) ;
    public final LNode assignment() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier10=null;
        LNode variableDef9 = null;

        java.util.List<LNode> indexes11 = null;

        LNode expression12 = null;


        try {
            // grammar/PlazmaScriptWalker.g:74:3: ( ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression ) )
            // grammar/PlazmaScriptWalker.g:74:6: ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression )
            {
            match(input,ASSIGNMENT,FOLLOW_ASSIGNMENT_in_assignment298); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:74:19: ( variableDef )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==Var) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:74:19: variableDef
                    {
                    pushFollow(FOLLOW_variableDef_in_assignment300);
                    variableDef9=variableDef();

                    state._fsp--;


                    }
                    break;

            }

            Identifier10=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_assignment303); 
            // grammar/PlazmaScriptWalker.g:74:43: ( indexes )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==INDEXES) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:74:43: indexes
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
    // grammar/PlazmaScriptWalker.g:77:1: functionCall returns [LNode node] : ( ^( FUNC_CALL Identifier ( exprList )? ) | ^( FUNC_CALL Println ( expression )? ) | ^( FUNC_CALL Print expression ) | ^( FUNC_CALL Assert expression ) | ^( FUNC_CALL Size expression ) );
    public final LNode functionCall() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier13=null;
        java.util.List<LNode> exprList14 = null;

        LNode expression15 = null;

        LNode expression16 = null;

        LNode expression17 = null;

        LNode expression18 = null;


        try {
            // grammar/PlazmaScriptWalker.g:78:3: ( ^( FUNC_CALL Identifier ( exprList )? ) | ^( FUNC_CALL Println ( expression )? ) | ^( FUNC_CALL Print expression ) | ^( FUNC_CALL Assert expression ) | ^( FUNC_CALL Size expression ) )
            int alt8=5;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==FUNC_CALL) ) {
                int LA8_1 = input.LA(2);

                if ( (LA8_1==DOWN) ) {
                    switch ( input.LA(3) ) {
                    case Identifier:
                        {
                        alt8=1;
                        }
                        break;
                    case Println:
                        {
                        alt8=2;
                        }
                        break;
                    case Print:
                        {
                        alt8=3;
                        }
                        break;
                    case Assert:
                        {
                        alt8=4;
                        }
                        break;
                    case Size:
                        {
                        alt8=5;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 8, 2, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:78:6: ^( FUNC_CALL Identifier ( exprList )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall330); 

                    match(input, Token.DOWN, null); 
                    Identifier13=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_functionCall332); 
                    // grammar/PlazmaScriptWalker.g:78:29: ( exprList )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==EXP_LIST) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:78:29: exprList
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
                    // grammar/PlazmaScriptWalker.g:79:6: ^( FUNC_CALL Println ( expression )? )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall346); 

                    match(input, Token.DOWN, null); 
                    match(input,Println,FOLLOW_Println_in_functionCall348); 
                    // grammar/PlazmaScriptWalker.g:79:26: ( expression )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( ((LA7_0>=TERNARY && LA7_0<=NEGATE)||LA7_0==LOOKUP||(LA7_0>=In && LA7_0<=Null)||(LA7_0>=Or && LA7_0<=Pow)||(LA7_0>=GT && LA7_0<=Modulus)) ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:79:26: expression
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
                    // grammar/PlazmaScriptWalker.g:80:6: ^( FUNC_CALL Print expression )
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
                    // grammar/PlazmaScriptWalker.g:81:6: ^( FUNC_CALL Assert expression )
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
                    // grammar/PlazmaScriptWalker.g:82:6: ^( FUNC_CALL Size expression )
                    {
                    match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_functionCall400); 

                    match(input, Token.DOWN, null); 
                    match(input,Size,FOLLOW_Size_in_functionCall402); 
                    pushFollow(FOLLOW_expression_in_functionCall404);
                    expression18=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new SizeNode(expression18);

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
    // grammar/PlazmaScriptWalker.g:85:1: ifStatement returns [LNode node] : ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? ) ;
    public final LNode ifStatement() throws RecognitionException {
        LNode node = null;

        IfNode ifNode = new IfNode();
        try {
            // grammar/PlazmaScriptWalker.g:88:3: ( ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? ) )
            // grammar/PlazmaScriptWalker.g:88:6: ^( IF ifStat[ifNode] ( elseIfStat[ifNode] )* ( elseStat[ifNode] )? )
            {
            match(input,IF,FOLLOW_IF_in_ifStatement442); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_ifStat_in_ifStatement444);
            ifStat(ifNode);

            state._fsp--;

            // grammar/PlazmaScriptWalker.g:88:26: ( elseIfStat[ifNode] )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==EXP) ) {
                    int LA9_1 = input.LA(2);

                    if ( (LA9_1==DOWN) ) {
                        int LA9_3 = input.LA(3);

                        if ( ((LA9_3>=TERNARY && LA9_3<=NEGATE)||LA9_3==LOOKUP||(LA9_3>=In && LA9_3<=Null)||(LA9_3>=Or && LA9_3<=Pow)||(LA9_3>=GT && LA9_3<=Modulus)) ) {
                            alt9=1;
                        }


                    }


                }


                switch (alt9) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:88:27: elseIfStat[ifNode]
            	    {
            	    pushFollow(FOLLOW_elseIfStat_in_ifStatement448);
            	    elseIfStat(ifNode);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            // grammar/PlazmaScriptWalker.g:88:48: ( elseStat[ifNode] )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==EXP) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:88:49: elseStat[ifNode]
                    {
                    pushFollow(FOLLOW_elseStat_in_ifStatement454);
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
    // grammar/PlazmaScriptWalker.g:91:1: ifStat[IfNode parent] : ^( EXP expression block ) ;
    public final void ifStat(IfNode parent) throws RecognitionException {
        LNode expression19 = null;

        LNode block20 = null;


        try {
            // grammar/PlazmaScriptWalker.g:92:3: ( ^( EXP expression block ) )
            // grammar/PlazmaScriptWalker.g:92:6: ^( EXP expression block )
            {
            match(input,EXP,FOLLOW_EXP_in_ifStat474); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_ifStat476);
            expression19=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_ifStat478);
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
    // grammar/PlazmaScriptWalker.g:95:1: elseIfStat[IfNode parent] : ^( EXP expression block ) ;
    public final void elseIfStat(IfNode parent) throws RecognitionException {
        LNode expression21 = null;

        LNode block22 = null;


        try {
            // grammar/PlazmaScriptWalker.g:96:3: ( ^( EXP expression block ) )
            // grammar/PlazmaScriptWalker.g:96:6: ^( EXP expression block )
            {
            match(input,EXP,FOLLOW_EXP_in_elseIfStat497); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_elseIfStat499);
            expression21=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_elseIfStat501);
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
    // grammar/PlazmaScriptWalker.g:99:1: elseStat[IfNode parent] : ^( EXP block ) ;
    public final void elseStat(IfNode parent) throws RecognitionException {
        LNode block23 = null;


        try {
            // grammar/PlazmaScriptWalker.g:100:3: ( ^( EXP block ) )
            // grammar/PlazmaScriptWalker.g:100:6: ^( EXP block )
            {
            match(input,EXP,FOLLOW_EXP_in_elseStat520); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_block_in_elseStat522);
            block23=block();

            state._fsp--;


            match(input, Token.UP, null); 
            parent.addChoice(new AtomNode(true), block23);

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
    // grammar/PlazmaScriptWalker.g:107:1: forStatement returns [LNode node] : ^( For Identifier a= expression block ) ;
    public final LNode forStatement() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier24=null;
        LNode a = null;

        LNode block25 = null;


        try {
            // grammar/PlazmaScriptWalker.g:108:3: ( ^( For Identifier a= expression block ) )
            // grammar/PlazmaScriptWalker.g:108:6: ^( For Identifier a= expression block )
            {
            match(input,For,FOLLOW_For_in_forStatement553); 

            match(input, Token.DOWN, null); 
            Identifier24=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_forStatement555); 
            pushFollow(FOLLOW_expression_in_forStatement559);
            a=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_forStatement561);
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
    // grammar/PlazmaScriptWalker.g:112:1: whileStatement returns [LNode node] : ^( While expression block ) ;
    public final LNode whileStatement() throws RecognitionException {
        LNode node = null;

        LNode expression26 = null;

        LNode block27 = null;


        try {
            // grammar/PlazmaScriptWalker.g:113:3: ( ^( While expression block ) )
            // grammar/PlazmaScriptWalker.g:113:6: ^( While expression block )
            {
            match(input,While,FOLLOW_While_in_whileStatement586); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_whileStatement588);
            expression26=expression();

            state._fsp--;

            pushFollow(FOLLOW_block_in_whileStatement590);
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
    // grammar/PlazmaScriptWalker.g:116:1: idList returns [java.util.List<String> i] : ^( ID_LIST ( Identifier )+ ) ;
    public final java.util.List<String> idList() throws RecognitionException {
        java.util.List<String> i = null;

        CommonTree Identifier28=null;

        i = new java.util.ArrayList<String>();
        try {
            // grammar/PlazmaScriptWalker.g:118:3: ( ^( ID_LIST ( Identifier )+ ) )
            // grammar/PlazmaScriptWalker.g:118:6: ^( ID_LIST ( Identifier )+ )
            {
            match(input,ID_LIST,FOLLOW_ID_LIST_in_idList617); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:118:16: ( Identifier )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==Identifier) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:118:17: Identifier
            	    {
            	    Identifier28=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_idList620); 
            	    i.add((Identifier28!=null?Identifier28.getText():null));

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
        return i;
    }
    // $ANTLR end "idList"


    // $ANTLR start "exprList"
    // grammar/PlazmaScriptWalker.g:121:1: exprList returns [java.util.List<LNode> e] : ^( EXP_LIST ( expression )+ ) ;
    public final java.util.List<LNode> exprList() throws RecognitionException {
        java.util.List<LNode> e = null;

        LNode expression29 = null;


        e = new java.util.ArrayList<LNode>();
        try {
            // grammar/PlazmaScriptWalker.g:123:3: ( ^( EXP_LIST ( expression )+ ) )
            // grammar/PlazmaScriptWalker.g:123:6: ^( EXP_LIST ( expression )+ )
            {
            match(input,EXP_LIST,FOLLOW_EXP_LIST_in_exprList650); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:123:17: ( expression )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>=TERNARY && LA12_0<=NEGATE)||LA12_0==LOOKUP||(LA12_0>=In && LA12_0<=Null)||(LA12_0>=Or && LA12_0<=Pow)||(LA12_0>=GT && LA12_0<=Modulus)) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:123:18: expression
            	    {
            	    pushFollow(FOLLOW_expression_in_exprList653);
            	    expression29=expression();

            	    state._fsp--;

            	    e.add(expression29);

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
    // $ANTLR end "exprList"


    // $ANTLR start "exprPair"
    // grammar/PlazmaScriptWalker.g:125:1: exprPair returns [PairNode node] : ^( EXP_PAIR k= expression v= expression ) ;
    public final PairNode exprPair() throws RecognitionException {
        PairNode node = null;

        LNode k = null;

        LNode v = null;


        try {
            // grammar/PlazmaScriptWalker.g:126:3: ( ^( EXP_PAIR k= expression v= expression ) )
            // grammar/PlazmaScriptWalker.g:126:6: ^( EXP_PAIR k= expression v= expression )
            {
            match(input,EXP_PAIR,FOLLOW_EXP_PAIR_in_exprPair676); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_exprPair680);
            k=expression();

            state._fsp--;

            pushFollow(FOLLOW_expression_in_exprPair684);
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
    // grammar/PlazmaScriptWalker.g:129:1: exprMap returns [java.util.List<PairNode> e] : ^( EXP_MAP ( exprPair )+ ) ;
    public final java.util.List<PairNode> exprMap() throws RecognitionException {
        java.util.List<PairNode> e = null;

        PairNode exprPair30 = null;


        e = new java.util.ArrayList<PairNode>();
        try {
            // grammar/PlazmaScriptWalker.g:131:3: ( ^( EXP_MAP ( exprPair )+ ) )
            // grammar/PlazmaScriptWalker.g:131:6: ^( EXP_MAP ( exprPair )+ )
            {
            match(input,EXP_MAP,FOLLOW_EXP_MAP_in_exprMap712); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:131:16: ( exprPair )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==EXP_PAIR) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:131:17: exprPair
            	    {
            	    pushFollow(FOLLOW_exprPair_in_exprMap715);
            	    exprPair30=exprPair();

            	    state._fsp--;

            	    e.add(exprPair30);

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
    // $ANTLR end "exprMap"


    // $ANTLR start "expression"
    // grammar/PlazmaScriptWalker.g:135:1: expression returns [LNode node] : ( ^( TERNARY a= expression b= expression c= expression ) | ^( In a= expression b= expression ) | ^( RangeE a= expression b= expression ) | ^( Range a= expression b= expression ) | ^( '||' a= expression b= expression ) | ^( '&&' a= expression b= expression ) | ^( '==' a= expression b= expression ) | ^( '!=' a= expression b= expression ) | ^( '>=' a= expression b= expression ) | ^( '<=' a= expression b= expression ) | ^( '>' a= expression b= expression ) | ^( '<' a= expression b= expression ) | ^( '+' a= expression b= expression ) | ^( '-' a= expression b= expression ) | ^( '*' a= expression b= expression ) | ^( '/' a= expression b= expression ) | ^( '%' a= expression b= expression ) | ^( '^' a= expression b= expression ) | ^( UNARY_MIN a= expression ) | ^( NEGATE a= expression ) | Integer | Number | Bool | Date | Null | lookup );
    public final LNode expression() throws RecognitionException {
        LNode node = null;

        CommonTree Integer31=null;
        CommonTree Number32=null;
        CommonTree Bool33=null;
        CommonTree Date34=null;
        LNode a = null;

        LNode b = null;

        LNode c = null;

        LNode lookup35 = null;


        try {
            // grammar/PlazmaScriptWalker.g:136:3: ( ^( TERNARY a= expression b= expression c= expression ) | ^( In a= expression b= expression ) | ^( RangeE a= expression b= expression ) | ^( Range a= expression b= expression ) | ^( '||' a= expression b= expression ) | ^( '&&' a= expression b= expression ) | ^( '==' a= expression b= expression ) | ^( '!=' a= expression b= expression ) | ^( '>=' a= expression b= expression ) | ^( '<=' a= expression b= expression ) | ^( '>' a= expression b= expression ) | ^( '<' a= expression b= expression ) | ^( '+' a= expression b= expression ) | ^( '-' a= expression b= expression ) | ^( '*' a= expression b= expression ) | ^( '/' a= expression b= expression ) | ^( '%' a= expression b= expression ) | ^( '^' a= expression b= expression ) | ^( UNARY_MIN a= expression ) | ^( NEGATE a= expression ) | Integer | Number | Bool | Date | Null | lookup )
            int alt14=26;
            switch ( input.LA(1) ) {
            case TERNARY:
                {
                alt14=1;
                }
                break;
            case In:
                {
                alt14=2;
                }
                break;
            case RangeE:
                {
                alt14=3;
                }
                break;
            case Range:
                {
                alt14=4;
                }
                break;
            case Or:
                {
                alt14=5;
                }
                break;
            case And:
                {
                alt14=6;
                }
                break;
            case Equals:
                {
                alt14=7;
                }
                break;
            case NEquals:
                {
                alt14=8;
                }
                break;
            case GTEquals:
                {
                alt14=9;
                }
                break;
            case LTEquals:
                {
                alt14=10;
                }
                break;
            case GT:
                {
                alt14=11;
                }
                break;
            case LT:
                {
                alt14=12;
                }
                break;
            case Add:
                {
                alt14=13;
                }
                break;
            case Subtract:
                {
                alt14=14;
                }
                break;
            case Multiply:
                {
                alt14=15;
                }
                break;
            case Divide:
                {
                alt14=16;
                }
                break;
            case Modulus:
                {
                alt14=17;
                }
                break;
            case Pow:
                {
                alt14=18;
                }
                break;
            case UNARY_MIN:
                {
                alt14=19;
                }
                break;
            case NEGATE:
                {
                alt14=20;
                }
                break;
            case Integer:
                {
                alt14=21;
                }
                break;
            case Number:
                {
                alt14=22;
                }
                break;
            case Bool:
                {
                alt14=23;
                }
                break;
            case Date:
                {
                alt14=24;
                }
                break;
            case Null:
                {
                alt14=25;
                }
                break;
            case LOOKUP:
                {
                alt14=26;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:136:6: ^( TERNARY a= expression b= expression c= expression )
                    {
                    match(input,TERNARY,FOLLOW_TERNARY_in_expression740); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression744);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression748);
                    b=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression752);
                    c=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new TernaryNode(a, b, c);

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:137:6: ^( In a= expression b= expression )
                    {
                    match(input,In,FOLLOW_In_in_expression763); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression767);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression771);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new InNode(a, b);

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:139:6: ^( RangeE a= expression b= expression )
                    {
                    match(input,RangeE,FOLLOW_RangeE_in_expression803); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression807);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression811);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new RangeENode(a, b);

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:140:6: ^( Range a= expression b= expression )
                    {
                    match(input,Range,FOLLOW_Range_in_expression837); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression841);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression845);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new RangeNode(a, b);

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:142:6: ^( '||' a= expression b= expression )
                    {
                    match(input,Or,FOLLOW_Or_in_expression875); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression879);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression883);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new OrNode(a, b);

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScriptWalker.g:143:6: ^( '&&' a= expression b= expression )
                    {
                    match(input,And,FOLLOW_And_in_expression910); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression914);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression918);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AndNode(a, b);

                    }
                    break;
                case 7 :
                    // grammar/PlazmaScriptWalker.g:144:6: ^( '==' a= expression b= expression )
                    {
                    match(input,Equals,FOLLOW_Equals_in_expression945); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression949);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression953);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new EqualsNode(a, b);

                    }
                    break;
                case 8 :
                    // grammar/PlazmaScriptWalker.g:145:6: ^( '!=' a= expression b= expression )
                    {
                    match(input,NEquals,FOLLOW_NEquals_in_expression980); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression984);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression988);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new NotEqualsNode(a, b);

                    }
                    break;
                case 9 :
                    // grammar/PlazmaScriptWalker.g:146:6: ^( '>=' a= expression b= expression )
                    {
                    match(input,GTEquals,FOLLOW_GTEquals_in_expression1015); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1019);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1023);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new GTEqualsNode(a, b);

                    }
                    break;
                case 10 :
                    // grammar/PlazmaScriptWalker.g:147:6: ^( '<=' a= expression b= expression )
                    {
                    match(input,LTEquals,FOLLOW_LTEquals_in_expression1050); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1054);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1058);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new LTEqualsNode(a, b);

                    }
                    break;
                case 11 :
                    // grammar/PlazmaScriptWalker.g:148:6: ^( '>' a= expression b= expression )
                    {
                    match(input,GT,FOLLOW_GT_in_expression1085); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1089);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1093);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new GTNode(a, b);

                    }
                    break;
                case 12 :
                    // grammar/PlazmaScriptWalker.g:149:6: ^( '<' a= expression b= expression )
                    {
                    match(input,LT,FOLLOW_LT_in_expression1121); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1125);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1129);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new LTNode(a, b);

                    }
                    break;
                case 13 :
                    // grammar/PlazmaScriptWalker.g:150:6: ^( '+' a= expression b= expression )
                    {
                    match(input,Add,FOLLOW_Add_in_expression1157); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1161);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1165);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new AddNode(a, b);

                    }
                    break;
                case 14 :
                    // grammar/PlazmaScriptWalker.g:151:6: ^( '-' a= expression b= expression )
                    {
                    match(input,Subtract,FOLLOW_Subtract_in_expression1193); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1197);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1201);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new SubNode(a, b);

                    }
                    break;
                case 15 :
                    // grammar/PlazmaScriptWalker.g:152:6: ^( '*' a= expression b= expression )
                    {
                    match(input,Multiply,FOLLOW_Multiply_in_expression1229); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1233);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1237);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new MulNode(a, b);

                    }
                    break;
                case 16 :
                    // grammar/PlazmaScriptWalker.g:153:6: ^( '/' a= expression b= expression )
                    {
                    match(input,Divide,FOLLOW_Divide_in_expression1265); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1269);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1273);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new DivNode(a, b);

                    }
                    break;
                case 17 :
                    // grammar/PlazmaScriptWalker.g:154:6: ^( '%' a= expression b= expression )
                    {
                    match(input,Modulus,FOLLOW_Modulus_in_expression1301); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1305);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1309);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new ModNode(a, b);

                    }
                    break;
                case 18 :
                    // grammar/PlazmaScriptWalker.g:155:6: ^( '^' a= expression b= expression )
                    {
                    match(input,Pow,FOLLOW_Pow_in_expression1337); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1341);
                    a=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1345);
                    b=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new PowNode(a, b);

                    }
                    break;
                case 19 :
                    // grammar/PlazmaScriptWalker.g:156:6: ^( UNARY_MIN a= expression )
                    {
                    match(input,UNARY_MIN,FOLLOW_UNARY_MIN_in_expression1373); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1377);
                    a=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new UnaryMinusNode(a);

                    }
                    break;
                case 20 :
                    // grammar/PlazmaScriptWalker.g:157:6: ^( NEGATE a= expression )
                    {
                    match(input,NEGATE,FOLLOW_NEGATE_in_expression1412); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1416);
                    a=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    node = new NegateNode(a);

                    }
                    break;
                case 21 :
                    // grammar/PlazmaScriptWalker.g:159:6: Integer
                    {
                    Integer31=(CommonTree)match(input,Integer,FOLLOW_Integer_in_expression1455); 
                    node = new AtomNode(new Integer((Integer31!=null?Integer31.getText():null)));

                    }
                    break;
                case 22 :
                    // grammar/PlazmaScriptWalker.g:160:6: Number
                    {
                    Number32=(CommonTree)match(input,Number,FOLLOW_Number_in_expression1506); 
                    node = new AtomNode(Double.parseDouble((Number32!=null?Number32.getText():null)));

                    }
                    break;
                case 23 :
                    // grammar/PlazmaScriptWalker.g:161:6: Bool
                    {
                    Bool33=(CommonTree)match(input,Bool,FOLLOW_Bool_in_expression1558); 
                    node = new AtomNode(Boolean.parseBoolean((Bool33!=null?Bool33.getText():null)));

                    }
                    break;
                case 24 :
                    // grammar/PlazmaScriptWalker.g:162:6: Date
                    {
                    Date34=(CommonTree)match(input,Date,FOLLOW_Date_in_expression1612); 
                    node = new AtomNode(AtomNode.parseDate((Date34!=null?Date34.getText():null)));

                    }
                    break;
                case 25 :
                    // grammar/PlazmaScriptWalker.g:163:6: Null
                    {
                    match(input,Null,FOLLOW_Null_in_expression1668); 
                    node = new NullNode();

                    }
                    break;
                case 26 :
                    // grammar/PlazmaScriptWalker.g:164:6: lookup
                    {
                    pushFollow(FOLLOW_lookup_in_expression1722);
                    lookup35=lookup();

                    state._fsp--;

                    node = lookup35;

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
    // grammar/PlazmaScriptWalker.g:167:1: list returns [LNode node] : ^( LIST ( exprList )? ) ;
    public final LNode list() throws RecognitionException {
        LNode node = null;

        java.util.List<LNode> exprList36 = null;


        try {
            // grammar/PlazmaScriptWalker.g:168:3: ( ^( LIST ( exprList )? ) )
            // grammar/PlazmaScriptWalker.g:168:6: ^( LIST ( exprList )? )
            {
            match(input,LIST,FOLLOW_LIST_in_list1786); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // grammar/PlazmaScriptWalker.g:168:13: ( exprList )?
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==EXP_LIST) ) {
                    alt15=1;
                }
                switch (alt15) {
                    case 1 :
                        // grammar/PlazmaScriptWalker.g:168:13: exprList
                        {
                        pushFollow(FOLLOW_exprList_in_list1788);
                        exprList36=exprList();

                        state._fsp--;


                        }
                        break;

                }


                match(input, Token.UP, null); 
            }
            node = new ListNode(exprList36);

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
    // grammar/PlazmaScriptWalker.g:171:1: map returns [LNode node] : ^( MAP ( exprMap )? ) ;
    public final LNode map() throws RecognitionException {
        LNode node = null;

        java.util.List<PairNode> exprMap37 = null;


        try {
            // grammar/PlazmaScriptWalker.g:172:3: ( ^( MAP ( exprMap )? ) )
            // grammar/PlazmaScriptWalker.g:172:6: ^( MAP ( exprMap )? )
            {
            match(input,MAP,FOLLOW_MAP_in_map1811); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // grammar/PlazmaScriptWalker.g:172:12: ( exprMap )?
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==EXP_MAP) ) {
                    alt16=1;
                }
                switch (alt16) {
                    case 1 :
                        // grammar/PlazmaScriptWalker.g:172:12: exprMap
                        {
                        pushFollow(FOLLOW_exprMap_in_map1813);
                        exprMap37=exprMap();

                        state._fsp--;


                        }
                        break;

                }


                match(input, Token.UP, null); 
            }
            node = new MapNode(exprMap37);

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
    // grammar/PlazmaScriptWalker.g:175:1: lookup returns [LNode node] : ( ^( LOOKUP functionCall (i= indexes )? ) | ^( LOOKUP list (i= indexes )? ) | ^( LOOKUP map (i= indexes )? ) | ^( LOOKUP expression (i= indexes )? ) | ^( LOOKUP Identifier (i= indexes )? ) | ^( LOOKUP String (i= indexes )? ) );
    public final LNode lookup() throws RecognitionException {
        LNode node = null;

        CommonTree Identifier42=null;
        CommonTree String43=null;
        java.util.List<LNode> i = null;

        LNode functionCall38 = null;

        LNode list39 = null;

        LNode map40 = null;

        LNode expression41 = null;


        try {
            // grammar/PlazmaScriptWalker.g:176:3: ( ^( LOOKUP functionCall (i= indexes )? ) | ^( LOOKUP list (i= indexes )? ) | ^( LOOKUP map (i= indexes )? ) | ^( LOOKUP expression (i= indexes )? ) | ^( LOOKUP Identifier (i= indexes )? ) | ^( LOOKUP String (i= indexes )? ) )
            int alt23=6;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==LOOKUP) ) {
                int LA23_1 = input.LA(2);

                if ( (LA23_1==DOWN) ) {
                    switch ( input.LA(3) ) {
                    case Identifier:
                        {
                        alt23=5;
                        }
                        break;
                    case String:
                        {
                        alt23=6;
                        }
                        break;
                    case FUNC_CALL:
                        {
                        alt23=1;
                        }
                        break;
                    case MAP:
                        {
                        alt23=3;
                        }
                        break;
                    case LIST:
                        {
                        alt23=2;
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
                        alt23=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 23, 2, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }
            switch (alt23) {
                case 1 :
                    // grammar/PlazmaScriptWalker.g:176:6: ^( LOOKUP functionCall (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup1836); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_functionCall_in_lookup1838);
                    functionCall38=functionCall();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:176:29: (i= indexes )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==INDEXES) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:176:29: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1842);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(functionCall38, i) : functionCall38;

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScriptWalker.g:177:6: ^( LOOKUP list (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup1854); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_list_in_lookup1856);
                    list39=list();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:177:21: (i= indexes )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==INDEXES) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:177:21: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1860);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(list39, i) : list39;

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScriptWalker.g:178:6: ^( LOOKUP map (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup1880); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_map_in_lookup1882);
                    map40=map();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:178:20: (i= indexes )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==INDEXES) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:178:20: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1886);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(map40, i) : map40;

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScriptWalker.g:179:6: ^( LOOKUP expression (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup1909); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_lookup1911);
                    expression41=expression();

                    state._fsp--;

                    // grammar/PlazmaScriptWalker.g:179:27: (i= indexes )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==INDEXES) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:179:27: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1915);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(expression41, i) : expression41;

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScriptWalker.g:180:6: ^( LOOKUP Identifier (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup1929); 

                    match(input, Token.DOWN, null); 
                    Identifier42=(CommonTree)match(input,Identifier,FOLLOW_Identifier_in_lookup1931); 
                    // grammar/PlazmaScriptWalker.g:180:27: (i= indexes )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==INDEXES) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:180:27: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1935);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(new IdentifierNode((Identifier42!=null?Identifier42.getText():null), currentScope, globalScope), i) : new IdentifierNode((Identifier42!=null?Identifier42.getText():null), currentScope, globalScope);

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScriptWalker.g:181:6: ^( LOOKUP String (i= indexes )? )
                    {
                    match(input,LOOKUP,FOLLOW_LOOKUP_in_lookup1949); 

                    match(input, Token.DOWN, null); 
                    String43=(CommonTree)match(input,String,FOLLOW_String_in_lookup1951); 
                    // grammar/PlazmaScriptWalker.g:181:23: (i= indexes )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==INDEXES) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // grammar/PlazmaScriptWalker.g:181:23: i= indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1955);
                            i=indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    node = i != null ? new LookupNode(new AtomNode((String43!=null?String43.getText():null)), i) : new AtomNode((String43!=null?String43.getText():null));

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
    // grammar/PlazmaScriptWalker.g:184:1: indexes returns [java.util.List<LNode> e] : ^( INDEXES ( expression )+ ) ;
    public final java.util.List<LNode> indexes() throws RecognitionException {
        java.util.List<LNode> e = null;

        LNode expression44 = null;


        e = new java.util.ArrayList<LNode>();
        try {
            // grammar/PlazmaScriptWalker.g:186:3: ( ^( INDEXES ( expression )+ ) )
            // grammar/PlazmaScriptWalker.g:186:6: ^( INDEXES ( expression )+ )
            {
            match(input,INDEXES,FOLLOW_INDEXES_in_indexes1989); 

            match(input, Token.DOWN, null); 
            // grammar/PlazmaScriptWalker.g:186:16: ( expression )+
            int cnt24=0;
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( ((LA24_0>=TERNARY && LA24_0<=NEGATE)||LA24_0==LOOKUP||(LA24_0>=In && LA24_0<=Null)||(LA24_0>=Or && LA24_0<=Pow)||(LA24_0>=GT && LA24_0<=Modulus)) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // grammar/PlazmaScriptWalker.g:186:17: expression
            	    {
            	    pushFollow(FOLLOW_expression_in_indexes1992);
            	    expression44=expression();

            	    state._fsp--;

            	    e.add(expression44);

            	    }
            	    break;

            	default :
            	    if ( cnt24 >= 1 ) break loop24;
                        EarlyExitException eee =
                            new EarlyExitException(24, input);
                        throw eee;
                }
                cnt24++;
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


    // $ANTLR start "variableDef"
    // grammar/PlazmaScriptWalker.g:189:1: variableDef returns [LNode node] : Var ;
    public final LNode variableDef() throws RecognitionException {
        LNode node = null;

        CommonTree Var45=null;

        try {
            // grammar/PlazmaScriptWalker.g:190:3: ( Var )
            // grammar/PlazmaScriptWalker.g:190:5: Var
            {
            Var45=(CommonTree)match(input,Var,FOLLOW_Var_in_variableDef2016); 
            node = new VariableDefNode((Var45!=null?Var45.getText():null), null);

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
    public static final BitSet FOLLOW_statement_in_block87 = new BitSet(new long[]{0x000000600C004188L});
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
    public static final BitSet FOLLOW_variableDef_in_assignment300 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_assignment303 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_indexes_in_assignment305 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_expression_in_assignment308 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall330 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_functionCall332 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_exprList_in_functionCall334 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall346 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Println_in_functionCall348 = new BitSet(new long[]{0x7F7F7F80004B8008L});
    public static final BitSet FOLLOW_expression_in_functionCall350 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall363 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Print_in_functionCall365 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_expression_in_functionCall367 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall382 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Assert_in_functionCall384 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_expression_in_functionCall386 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_functionCall400 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Size_in_functionCall402 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_expression_in_functionCall404 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IF_in_ifStatement442 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ifStat_in_ifStatement444 = new BitSet(new long[]{0x0000000000000208L});
    public static final BitSet FOLLOW_elseIfStat_in_ifStatement448 = new BitSet(new long[]{0x0000000000000208L});
    public static final BitSet FOLLOW_elseStat_in_ifStatement454 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_ifStat474 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_ifStat476 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_ifStat478 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_elseIfStat497 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_elseIfStat499 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_elseIfStat501 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_in_elseStat520 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_block_in_elseStat522 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_For_in_forStatement553 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_forStatement555 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_expression_in_forStatement559 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_forStatement561 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_While_in_whileStatement586 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_whileStatement588 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_block_in_whileStatement590 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_LIST_in_idList617 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_idList620 = new BitSet(new long[]{0x0000000010000008L});
    public static final BitSet FOLLOW_EXP_LIST_in_exprList650 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_exprList653 = new BitSet(new long[]{0x7F7F7F80004B8008L});
    public static final BitSet FOLLOW_EXP_PAIR_in_exprPair676 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_exprPair680 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_expression_in_exprPair684 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXP_MAP_in_exprMap712 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprPair_in_exprMap715 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_TERNARY_in_expression740 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression744 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_expression_in_expression748 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_expression_in_expression752 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_In_in_expression763 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression767 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_expression_in_expression771 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RangeE_in_expression803 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression807 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_expression_in_expression811 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Range_in_expression837 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression841 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_expression_in_expression845 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Or_in_expression875 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression879 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_expression_in_expression883 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_And_in_expression910 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression914 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_expression_in_expression918 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Equals_in_expression945 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression949 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_expression_in_expression953 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEquals_in_expression980 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression984 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_expression_in_expression988 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GTEquals_in_expression1015 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1019 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_expression_in_expression1023 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LTEquals_in_expression1050 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1054 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_expression_in_expression1058 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GT_in_expression1085 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1089 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_expression_in_expression1093 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LT_in_expression1121 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1125 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_expression_in_expression1129 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Add_in_expression1157 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1161 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_expression_in_expression1165 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Subtract_in_expression1193 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1197 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_expression_in_expression1201 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Multiply_in_expression1229 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1233 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_expression_in_expression1237 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Divide_in_expression1265 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1269 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_expression_in_expression1273 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Modulus_in_expression1301 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1305 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_expression_in_expression1309 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Pow_in_expression1337 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1341 = new BitSet(new long[]{0x7F7F7F80004B8000L});
    public static final BitSet FOLLOW_expression_in_expression1345 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_UNARY_MIN_in_expression1373 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1377 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEGATE_in_expression1412 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1416 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Integer_in_expression1455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Number_in_expression1506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Bool_in_expression1558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Date_in_expression1612 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Null_in_expression1668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_expression1722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LIST_in_list1786 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprList_in_list1788 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MAP_in_map1811 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprMap_in_map1813 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup1836 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_functionCall_in_lookup1838 = new BitSet(new long[]{0x0000000000080008L});
    public static final BitSet FOLLOW_indexes_in_lookup1842 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup1854 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_list_in_lookup1856 = new BitSet(new long[]{0x0000000000080008L});
    public static final BitSet FOLLOW_indexes_in_lookup1860 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup1880 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_map_in_lookup1882 = new BitSet(new long[]{0x0000000000080008L});
    public static final BitSet FOLLOW_indexes_in_lookup1886 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup1909 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_lookup1911 = new BitSet(new long[]{0x0000000000080008L});
    public static final BitSet FOLLOW_indexes_in_lookup1915 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup1929 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_lookup1931 = new BitSet(new long[]{0x0000000000080008L});
    public static final BitSet FOLLOW_indexes_in_lookup1935 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOOKUP_in_lookup1949 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_String_in_lookup1951 = new BitSet(new long[]{0x0000000000080008L});
    public static final BitSet FOLLOW_indexes_in_lookup1955 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INDEXES_in_indexes1989 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_indexes1992 = new BitSet(new long[]{0x7F7F7F80004B8008L});
    public static final BitSet FOLLOW_Var_in_variableDef2016 = new BitSet(new long[]{0x0000000000000002L});

}