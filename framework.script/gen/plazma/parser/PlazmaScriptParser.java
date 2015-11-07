// $ANTLR 3.2 Sep 23, 2009 12:02:23 grammar/PlazmaScript.g 2015-06-02 08:38:02

  package plazma.parser;
  import plazma.*; 
  import java.util.Map;
  import java.util.HashMap;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;

public class PlazmaScriptParser extends Parser {
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
    public static final int Date=44;
    public static final int While=37;
    public static final int ID_LIST=13;
    public static final int DD=77;
    public static final int QMark=71;
    public static final int Add=57;
    public static final int EOF=-1;
    public static final int BREAK=23;
    public static final int Int=73;
    public static final int Identifier=28;
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
    public static final int In=38;
    public static final int Bool=43;
    public static final int NEquals=50;
    public static final int Continue=27;
    public static final int Subtract=58;
    public static final int EXP_PAIR=10;
    public static final int Modulus=61;
    public static final int Multiply=59;
    public static final int OBrace=62;
    public static final int INDEXES=19;
    public static final int NEGATE=17;
    public static final int Colon=72;
    public static final int Excl=54;
    public static final int Digit=74;
    public static final int LIST=21;
    public static final int For=36;
    public static final int Divide=60;
    public static final int Def=35;
    public static final int SColon=68;
    public static final int LOOKUP=22;
    public static final int RangeE=39;
    public static final int Range=40;
    public static final int OBracket=64;
    public static final int Break=26;
    public static final int BLOCK=4;
    public static final int MAP=20;
    public static final int GT=55;
    public static final int STATEMENTS=6;
    public static final int UNARY_MIN=16;
    public static final int ASSIGNMENT=7;
    public static final int Else=34;
    public static final int Comma=70;
    public static final int Equals=49;
    public static final int Integer=41;
    public static final int Pow=53;
    public static final int LTEquals=52;

    // delegates
    // delegators


        public PlazmaScriptParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public PlazmaScriptParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return PlazmaScriptParser.tokenNames; }
    public String getGrammarFileName() { return "grammar/PlazmaScript.g"; }


      public Map<String, Function> functions = new HashMap<String, Function>();
      
      private void defineFunction(String id, Object idList, Object block) {

        // `idList` is possibly null! Create an empty tree in that case. 
        CommonTree idListTree = idList == null ? new CommonTree() : (CommonTree)idList;

        // `block` is never null.
        CommonTree blockTree = (CommonTree)block;

        // The function name with the number of parameters after it the unique key
        String key = id + idListTree.getChildCount();
        functions.put(key, new Function(id, idListTree, blockTree));
      }


    public static class parse_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "parse"
    // grammar/PlazmaScript.g:62:1: parse : block EOF -> block ;
    public final PlazmaScriptParser.parse_return parse() throws RecognitionException {
        PlazmaScriptParser.parse_return retval = new PlazmaScriptParser.parse_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EOF2=null;
        PlazmaScriptParser.block_return block1 = null;


        Object EOF2_tree=null;
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:63:3: ( block EOF -> block )
            // grammar/PlazmaScript.g:63:6: block EOF
            {
            pushFollow(FOLLOW_block_in_parse175);
            block1=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block1.getTree());
            EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_parse177); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_EOF.add(EOF2);



            // AST REWRITE
            // elements: block
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 63:16: -> block
            {
                adaptor.addChild(root_0, stream_block.nextTree());

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "parse"

    public static class block_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "block"
    // grammar/PlazmaScript.g:66:1: block : ( statement | functionDecl )* ( Return expression ';' )? -> ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) ) ;
    public final PlazmaScriptParser.block_return block() throws RecognitionException {
        PlazmaScriptParser.block_return retval = new PlazmaScriptParser.block_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Return5=null;
        Token char_literal7=null;
        PlazmaScriptParser.statement_return statement3 = null;

        PlazmaScriptParser.functionDecl_return functionDecl4 = null;

        PlazmaScriptParser.expression_return expression6 = null;


        Object Return5_tree=null;
        Object char_literal7_tree=null;
        RewriteRuleTokenStream stream_Return=new RewriteRuleTokenStream(adaptor,"token Return");
        RewriteRuleTokenStream stream_SColon=new RewriteRuleTokenStream(adaptor,"token SColon");
        RewriteRuleSubtreeStream stream_statement=new RewriteRuleSubtreeStream(adaptor,"rule statement");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_functionDecl=new RewriteRuleSubtreeStream(adaptor,"rule functionDecl");
        try {
            // grammar/PlazmaScript.g:67:3: ( ( statement | functionDecl )* ( Return expression ';' )? -> ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) ) )
            // grammar/PlazmaScript.g:67:6: ( statement | functionDecl )* ( Return expression ';' )?
            {
            // grammar/PlazmaScript.g:67:6: ( statement | functionDecl )*
            loop1:
            do {
                int alt1=3;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=Break && LA1_0<=If)||(LA1_0>=For && LA1_0<=While)||LA1_0==ContextIdentifier) ) {
                    alt1=1;
                }
                else if ( (LA1_0==Def) ) {
                    alt1=2;
                }


                switch (alt1) {
            	case 1 :
            	    // grammar/PlazmaScript.g:67:7: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_block196);
            	    statement3=statement();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_statement.add(statement3.getTree());

            	    }
            	    break;
            	case 2 :
            	    // grammar/PlazmaScript.g:67:19: functionDecl
            	    {
            	    pushFollow(FOLLOW_functionDecl_in_block200);
            	    functionDecl4=functionDecl();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_functionDecl.add(functionDecl4.getTree());

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // grammar/PlazmaScript.g:67:34: ( Return expression ';' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==Return) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // grammar/PlazmaScript.g:67:35: Return expression ';'
                    {
                    Return5=(Token)match(input,Return,FOLLOW_Return_in_block205); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Return.add(Return5);

                    pushFollow(FOLLOW_expression_in_block207);
                    expression6=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression6.getTree());
                    char_literal7=(Token)match(input,SColon,FOLLOW_SColon_in_block209); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SColon.add(char_literal7);


                    }
                    break;

            }



            // AST REWRITE
            // elements: expression, statement
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 68:6: -> ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) )
            {
                // grammar/PlazmaScript.g:68:9: ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BLOCK, "BLOCK"), root_1);

                // grammar/PlazmaScript.g:68:17: ^( STATEMENTS ( statement )* )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(STATEMENTS, "STATEMENTS"), root_2);

                // grammar/PlazmaScript.g:68:30: ( statement )*
                while ( stream_statement.hasNext() ) {
                    adaptor.addChild(root_2, stream_statement.nextTree());

                }
                stream_statement.reset();

                adaptor.addChild(root_1, root_2);
                }
                // grammar/PlazmaScript.g:68:42: ^( RETURN ( expression )? )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(RETURN, "RETURN"), root_2);

                // grammar/PlazmaScript.g:68:51: ( expression )?
                if ( stream_expression.hasNext() ) {
                    adaptor.addChild(root_2, stream_expression.nextTree());

                }
                stream_expression.reset();

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "block"

    public static class statement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "statement"
    // grammar/PlazmaScript.g:71:1: statement : ( assignment ';' -> assignment | functionCall ';' -> functionCall | ifStatement | forStatement | whileStatement | Break ';' -> Break | Continue ';' -> Continue );
    public final PlazmaScriptParser.statement_return statement() throws RecognitionException {
        PlazmaScriptParser.statement_return retval = new PlazmaScriptParser.statement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal9=null;
        Token char_literal11=null;
        Token Break15=null;
        Token char_literal16=null;
        Token Continue17=null;
        Token char_literal18=null;
        PlazmaScriptParser.assignment_return assignment8 = null;

        PlazmaScriptParser.functionCall_return functionCall10 = null;

        PlazmaScriptParser.ifStatement_return ifStatement12 = null;

        PlazmaScriptParser.forStatement_return forStatement13 = null;

        PlazmaScriptParser.whileStatement_return whileStatement14 = null;


        Object char_literal9_tree=null;
        Object char_literal11_tree=null;
        Object Break15_tree=null;
        Object char_literal16_tree=null;
        Object Continue17_tree=null;
        Object char_literal18_tree=null;
        RewriteRuleTokenStream stream_Break=new RewriteRuleTokenStream(adaptor,"token Break");
        RewriteRuleTokenStream stream_Continue=new RewriteRuleTokenStream(adaptor,"token Continue");
        RewriteRuleTokenStream stream_SColon=new RewriteRuleTokenStream(adaptor,"token SColon");
        RewriteRuleSubtreeStream stream_functionCall=new RewriteRuleSubtreeStream(adaptor,"rule functionCall");
        RewriteRuleSubtreeStream stream_assignment=new RewriteRuleSubtreeStream(adaptor,"rule assignment");
        try {
            // grammar/PlazmaScript.g:72:3: ( assignment ';' -> assignment | functionCall ';' -> functionCall | ifStatement | forStatement | whileStatement | Break ';' -> Break | Continue ';' -> Continue )
            int alt3=7;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA3_1 = input.LA(2);

                if ( (LA3_1==OParen) ) {
                    alt3=2;
                }
                else if ( (LA3_1==OBracket||LA3_1==Assign) ) {
                    alt3=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 1, input);

                    throw nvae;
                }
                }
                break;
            case ContextIdentifier:
                {
                alt3=1;
                }
                break;
            case Println:
            case Print:
            case Assert:
            case Size:
                {
                alt3=2;
                }
                break;
            case If:
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
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // grammar/PlazmaScript.g:72:6: assignment ';'
                    {
                    pushFollow(FOLLOW_assignment_in_statement251);
                    assignment8=assignment();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_assignment.add(assignment8.getTree());
                    char_literal9=(Token)match(input,SColon,FOLLOW_SColon_in_statement253); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SColon.add(char_literal9);



                    // AST REWRITE
                    // elements: assignment
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 72:23: -> assignment
                    {
                        adaptor.addChild(root_0, stream_assignment.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:73:6: functionCall ';'
                    {
                    pushFollow(FOLLOW_functionCall_in_statement266);
                    functionCall10=functionCall();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_functionCall.add(functionCall10.getTree());
                    char_literal11=(Token)match(input,SColon,FOLLOW_SColon_in_statement268); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SColon.add(char_literal11);



                    // AST REWRITE
                    // elements: functionCall
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 73:23: -> functionCall
                    {
                        adaptor.addChild(root_0, stream_functionCall.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // grammar/PlazmaScript.g:74:6: ifStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_ifStatement_in_statement279);
                    ifStatement12=ifStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ifStatement12.getTree());

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScript.g:75:6: forStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_forStatement_in_statement286);
                    forStatement13=forStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, forStatement13.getTree());

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScript.g:76:6: whileStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_whileStatement_in_statement293);
                    whileStatement14=whileStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, whileStatement14.getTree());

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScript.g:77:6: Break ';'
                    {
                    Break15=(Token)match(input,Break,FOLLOW_Break_in_statement300); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Break.add(Break15);

                    char_literal16=(Token)match(input,SColon,FOLLOW_SColon_in_statement302); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SColon.add(char_literal16);



                    // AST REWRITE
                    // elements: Break
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 77:16: -> Break
                    {
                        adaptor.addChild(root_0, stream_Break.nextNode());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 7 :
                    // grammar/PlazmaScript.g:78:6: Continue ';'
                    {
                    Continue17=(Token)match(input,Continue,FOLLOW_Continue_in_statement313); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Continue.add(Continue17);

                    char_literal18=(Token)match(input,SColon,FOLLOW_SColon_in_statement315); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SColon.add(char_literal18);



                    // AST REWRITE
                    // elements: Continue
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 78:19: -> Continue
                    {
                        adaptor.addChild(root_0, stream_Continue.nextNode());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "statement"

    public static class assignment_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "assignment"
    // grammar/PlazmaScript.g:81:1: assignment : ( Identifier ( indexes )? '=' expression -> ^( ASSIGNMENT Identifier ( indexes )? expression ) | anyIdentifier ( indexes )? '=' expression -> ^( ASSIGNMENT ( indexes )? expression ) );
    public final PlazmaScriptParser.assignment_return assignment() throws RecognitionException {
        PlazmaScriptParser.assignment_return retval = new PlazmaScriptParser.assignment_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Identifier19=null;
        Token char_literal21=null;
        Token char_literal25=null;
        PlazmaScriptParser.indexes_return indexes20 = null;

        PlazmaScriptParser.expression_return expression22 = null;

        PlazmaScriptParser.anyIdentifier_return anyIdentifier23 = null;

        PlazmaScriptParser.indexes_return indexes24 = null;

        PlazmaScriptParser.expression_return expression26 = null;


        Object Identifier19_tree=null;
        Object char_literal21_tree=null;
        Object char_literal25_tree=null;
        RewriteRuleTokenStream stream_Assign=new RewriteRuleTokenStream(adaptor,"token Assign");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_anyIdentifier=new RewriteRuleSubtreeStream(adaptor,"rule anyIdentifier");
        RewriteRuleSubtreeStream stream_indexes=new RewriteRuleSubtreeStream(adaptor,"rule indexes");
        try {
            // grammar/PlazmaScript.g:82:3: ( Identifier ( indexes )? '=' expression -> ^( ASSIGNMENT Identifier ( indexes )? expression ) | anyIdentifier ( indexes )? '=' expression -> ^( ASSIGNMENT ( indexes )? expression ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==Identifier) ) {
                int LA6_1 = input.LA(2);

                if ( (synpred11_PlazmaScript()) ) {
                    alt6=1;
                }
                else if ( (true) ) {
                    alt6=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA6_0==ContextIdentifier) ) {
                alt6=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // grammar/PlazmaScript.g:82:6: Identifier ( indexes )? '=' expression
                    {
                    Identifier19=(Token)match(input,Identifier,FOLLOW_Identifier_in_assignment333); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier19);

                    // grammar/PlazmaScript.g:82:17: ( indexes )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==OBracket) ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_assignment335);
                            indexes20=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes20.getTree());

                            }
                            break;

                    }

                    char_literal21=(Token)match(input,Assign,FOLLOW_Assign_in_assignment338); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Assign.add(char_literal21);

                    pushFollow(FOLLOW_expression_in_assignment340);
                    expression22=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression22.getTree());


                    // AST REWRITE
                    // elements: Identifier, expression, indexes
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 82:41: -> ^( ASSIGNMENT Identifier ( indexes )? expression )
                    {
                        // grammar/PlazmaScript.g:82:44: ^( ASSIGNMENT Identifier ( indexes )? expression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ASSIGNMENT, "ASSIGNMENT"), root_1);

                        adaptor.addChild(root_1, stream_Identifier.nextNode());
                        // grammar/PlazmaScript.g:82:68: ( indexes )?
                        if ( stream_indexes.hasNext() ) {
                            adaptor.addChild(root_1, stream_indexes.nextTree());

                        }
                        stream_indexes.reset();
                        adaptor.addChild(root_1, stream_expression.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:83:6: anyIdentifier ( indexes )? '=' expression
                    {
                    pushFollow(FOLLOW_anyIdentifier_in_assignment360);
                    anyIdentifier23=anyIdentifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_anyIdentifier.add(anyIdentifier23.getTree());
                    // grammar/PlazmaScript.g:83:20: ( indexes )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==OBracket) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_assignment362);
                            indexes24=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes24.getTree());

                            }
                            break;

                    }

                    char_literal25=(Token)match(input,Assign,FOLLOW_Assign_in_assignment365); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Assign.add(char_literal25);

                    pushFollow(FOLLOW_expression_in_assignment367);
                    expression26=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression26.getTree());


                    // AST REWRITE
                    // elements: expression, indexes
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 83:44: -> ^( ASSIGNMENT ( indexes )? expression )
                    {
                        // grammar/PlazmaScript.g:83:47: ^( ASSIGNMENT ( indexes )? expression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ASSIGNMENT, "ASSIGNMENT"), root_1);

                        adaptor.addChild(root_1, new CommonTree(new CommonToken(Identifier, (anyIdentifier23!=null?input.toString(anyIdentifier23.start,anyIdentifier23.stop):null))));
                        // grammar/PlazmaScript.g:83:127: ( indexes )?
                        if ( stream_indexes.hasNext() ) {
                            adaptor.addChild(root_1, stream_indexes.nextTree());

                        }
                        stream_indexes.reset();
                        adaptor.addChild(root_1, stream_expression.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "assignment"

    public static class functionCall_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "functionCall"
    // grammar/PlazmaScript.g:86:1: functionCall : ( Identifier '(' ( exprList )? ')' -> ^( FUNC_CALL Identifier ( exprList )? ) | Println '(' ( expression )? ')' -> ^( FUNC_CALL Println ( expression )? ) | Print '(' expression ')' -> ^( FUNC_CALL Print expression ) | Assert '(' expression ')' -> ^( FUNC_CALL Assert expression ) | Size '(' expression ')' -> ^( FUNC_CALL Size expression ) );
    public final PlazmaScriptParser.functionCall_return functionCall() throws RecognitionException {
        PlazmaScriptParser.functionCall_return retval = new PlazmaScriptParser.functionCall_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Identifier27=null;
        Token char_literal28=null;
        Token char_literal30=null;
        Token Println31=null;
        Token char_literal32=null;
        Token char_literal34=null;
        Token Print35=null;
        Token char_literal36=null;
        Token char_literal38=null;
        Token Assert39=null;
        Token char_literal40=null;
        Token char_literal42=null;
        Token Size43=null;
        Token char_literal44=null;
        Token char_literal46=null;
        PlazmaScriptParser.exprList_return exprList29 = null;

        PlazmaScriptParser.expression_return expression33 = null;

        PlazmaScriptParser.expression_return expression37 = null;

        PlazmaScriptParser.expression_return expression41 = null;

        PlazmaScriptParser.expression_return expression45 = null;


        Object Identifier27_tree=null;
        Object char_literal28_tree=null;
        Object char_literal30_tree=null;
        Object Println31_tree=null;
        Object char_literal32_tree=null;
        Object char_literal34_tree=null;
        Object Print35_tree=null;
        Object char_literal36_tree=null;
        Object char_literal38_tree=null;
        Object Assert39_tree=null;
        Object char_literal40_tree=null;
        Object char_literal42_tree=null;
        Object Size43_tree=null;
        Object char_literal44_tree=null;
        Object char_literal46_tree=null;
        RewriteRuleTokenStream stream_Println=new RewriteRuleTokenStream(adaptor,"token Println");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_Assert=new RewriteRuleTokenStream(adaptor,"token Assert");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
        RewriteRuleTokenStream stream_Print=new RewriteRuleTokenStream(adaptor,"token Print");
        RewriteRuleTokenStream stream_Size=new RewriteRuleTokenStream(adaptor,"token Size");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_exprList=new RewriteRuleSubtreeStream(adaptor,"rule exprList");
        try {
            // grammar/PlazmaScript.g:87:3: ( Identifier '(' ( exprList )? ')' -> ^( FUNC_CALL Identifier ( exprList )? ) | Println '(' ( expression )? ')' -> ^( FUNC_CALL Println ( expression )? ) | Print '(' expression ')' -> ^( FUNC_CALL Print expression ) | Assert '(' expression ')' -> ^( FUNC_CALL Assert expression ) | Size '(' expression ')' -> ^( FUNC_CALL Size expression ) )
            int alt9=5;
            switch ( input.LA(1) ) {
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
            case Size:
                {
                alt9=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // grammar/PlazmaScript.g:87:6: Identifier '(' ( exprList )? ')'
                    {
                    Identifier27=(Token)match(input,Identifier,FOLLOW_Identifier_in_functionCall394); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier27);

                    char_literal28=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall396); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal28);

                    // grammar/PlazmaScript.g:87:21: ( exprList )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( ((LA7_0>=Identifier && LA7_0<=Size)||(LA7_0>=Integer && LA7_0<=String)||LA7_0==Excl||LA7_0==Subtract||LA7_0==OBrace||LA7_0==OParen||LA7_0==ContextIdentifier) ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall398);
                            exprList29=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList29.getTree());

                            }
                            break;

                    }

                    char_literal30=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall401); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal30);



                    // AST REWRITE
                    // elements: Identifier, exprList
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 87:35: -> ^( FUNC_CALL Identifier ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:87:38: ^( FUNC_CALL Identifier ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Identifier.nextNode());
                        // grammar/PlazmaScript.g:87:61: ( exprList )?
                        if ( stream_exprList.hasNext() ) {
                            adaptor.addChild(root_1, stream_exprList.nextTree());

                        }
                        stream_exprList.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:88:6: Println '(' ( expression )? ')'
                    {
                    Println31=(Token)match(input,Println,FOLLOW_Println_in_functionCall419); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Println.add(Println31);

                    char_literal32=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall421); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal32);

                    // grammar/PlazmaScript.g:88:18: ( expression )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( ((LA8_0>=Identifier && LA8_0<=Size)||(LA8_0>=Integer && LA8_0<=String)||LA8_0==Excl||LA8_0==Subtract||LA8_0==OBrace||LA8_0==OParen||LA8_0==ContextIdentifier) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: expression
                            {
                            pushFollow(FOLLOW_expression_in_functionCall423);
                            expression33=expression();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_expression.add(expression33.getTree());

                            }
                            break;

                    }

                    char_literal34=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall426); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal34);



                    // AST REWRITE
                    // elements: expression, Println
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 88:35: -> ^( FUNC_CALL Println ( expression )? )
                    {
                        // grammar/PlazmaScript.g:88:38: ^( FUNC_CALL Println ( expression )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Println.nextNode());
                        // grammar/PlazmaScript.g:88:58: ( expression )?
                        if ( stream_expression.hasNext() ) {
                            adaptor.addChild(root_1, stream_expression.nextTree());

                        }
                        stream_expression.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // grammar/PlazmaScript.g:89:6: Print '(' expression ')'
                    {
                    Print35=(Token)match(input,Print,FOLLOW_Print_in_functionCall445); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Print.add(Print35);

                    char_literal36=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall447); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal36);

                    pushFollow(FOLLOW_expression_in_functionCall449);
                    expression37=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression37.getTree());
                    char_literal38=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall451); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal38);



                    // AST REWRITE
                    // elements: Print, expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 89:35: -> ^( FUNC_CALL Print expression )
                    {
                        // grammar/PlazmaScript.g:89:38: ^( FUNC_CALL Print expression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Print.nextNode());
                        adaptor.addChild(root_1, stream_expression.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 4 :
                    // grammar/PlazmaScript.g:90:6: Assert '(' expression ')'
                    {
                    Assert39=(Token)match(input,Assert,FOLLOW_Assert_in_functionCall472); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Assert.add(Assert39);

                    char_literal40=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall474); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal40);

                    pushFollow(FOLLOW_expression_in_functionCall476);
                    expression41=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression41.getTree());
                    char_literal42=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall478); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal42);



                    // AST REWRITE
                    // elements: expression, Assert
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 90:35: -> ^( FUNC_CALL Assert expression )
                    {
                        // grammar/PlazmaScript.g:90:38: ^( FUNC_CALL Assert expression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Assert.nextNode());
                        adaptor.addChild(root_1, stream_expression.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 5 :
                    // grammar/PlazmaScript.g:91:6: Size '(' expression ')'
                    {
                    Size43=(Token)match(input,Size,FOLLOW_Size_in_functionCall498); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Size.add(Size43);

                    char_literal44=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall500); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal44);

                    pushFollow(FOLLOW_expression_in_functionCall502);
                    expression45=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression45.getTree());
                    char_literal46=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall504); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal46);



                    // AST REWRITE
                    // elements: expression, Size
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 91:35: -> ^( FUNC_CALL Size expression )
                    {
                        // grammar/PlazmaScript.g:91:38: ^( FUNC_CALL Size expression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Size.nextNode());
                        adaptor.addChild(root_1, stream_expression.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "functionCall"

    public static class ifStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ifStatement"
    // grammar/PlazmaScript.g:94:1: ifStatement : ifStat ( elseIfStat )* ( elseStat )? -> ^( IF ifStat ( elseIfStat )* ( elseStat )? ) ;
    public final PlazmaScriptParser.ifStatement_return ifStatement() throws RecognitionException {
        PlazmaScriptParser.ifStatement_return retval = new PlazmaScriptParser.ifStatement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        PlazmaScriptParser.ifStat_return ifStat47 = null;

        PlazmaScriptParser.elseIfStat_return elseIfStat48 = null;

        PlazmaScriptParser.elseStat_return elseStat49 = null;


        RewriteRuleSubtreeStream stream_elseIfStat=new RewriteRuleSubtreeStream(adaptor,"rule elseIfStat");
        RewriteRuleSubtreeStream stream_ifStat=new RewriteRuleSubtreeStream(adaptor,"rule ifStat");
        RewriteRuleSubtreeStream stream_elseStat=new RewriteRuleSubtreeStream(adaptor,"rule elseStat");
        try {
            // grammar/PlazmaScript.g:95:3: ( ifStat ( elseIfStat )* ( elseStat )? -> ^( IF ifStat ( elseIfStat )* ( elseStat )? ) )
            // grammar/PlazmaScript.g:95:6: ifStat ( elseIfStat )* ( elseStat )?
            {
            pushFollow(FOLLOW_ifStat_in_ifStatement533);
            ifStat47=ifStat();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_ifStat.add(ifStat47.getTree());
            // grammar/PlazmaScript.g:95:13: ( elseIfStat )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==Else) ) {
                    int LA10_1 = input.LA(2);

                    if ( (LA10_1==If) ) {
                        alt10=1;
                    }


                }


                switch (alt10) {
            	case 1 :
            	    // grammar/PlazmaScript.g:0:0: elseIfStat
            	    {
            	    pushFollow(FOLLOW_elseIfStat_in_ifStatement535);
            	    elseIfStat48=elseIfStat();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_elseIfStat.add(elseIfStat48.getTree());

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            // grammar/PlazmaScript.g:95:25: ( elseStat )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==Else) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // grammar/PlazmaScript.g:0:0: elseStat
                    {
                    pushFollow(FOLLOW_elseStat_in_ifStatement538);
                    elseStat49=elseStat();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_elseStat.add(elseStat49.getTree());

                    }
                    break;

            }



            // AST REWRITE
            // elements: elseIfStat, ifStat, elseStat
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 95:35: -> ^( IF ifStat ( elseIfStat )* ( elseStat )? )
            {
                // grammar/PlazmaScript.g:95:38: ^( IF ifStat ( elseIfStat )* ( elseStat )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(IF, "IF"), root_1);

                adaptor.addChild(root_1, stream_ifStat.nextTree());
                // grammar/PlazmaScript.g:95:50: ( elseIfStat )*
                while ( stream_elseIfStat.hasNext() ) {
                    adaptor.addChild(root_1, stream_elseIfStat.nextTree());

                }
                stream_elseIfStat.reset();
                // grammar/PlazmaScript.g:95:62: ( elseStat )?
                if ( stream_elseStat.hasNext() ) {
                    adaptor.addChild(root_1, stream_elseStat.nextTree());

                }
                stream_elseStat.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ifStatement"

    public static class ifStat_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ifStat"
    // grammar/PlazmaScript.g:98:1: ifStat : If '(' expression ')' '{' block '}' -> ^( EXP expression block ) ;
    public final PlazmaScriptParser.ifStat_return ifStat() throws RecognitionException {
        PlazmaScriptParser.ifStat_return retval = new PlazmaScriptParser.ifStat_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token If50=null;
        Token char_literal51=null;
        Token char_literal53=null;
        Token char_literal54=null;
        Token char_literal56=null;
        PlazmaScriptParser.expression_return expression52 = null;

        PlazmaScriptParser.block_return block55 = null;


        Object If50_tree=null;
        Object char_literal51_tree=null;
        Object char_literal53_tree=null;
        Object char_literal54_tree=null;
        Object char_literal56_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_If=new RewriteRuleTokenStream(adaptor,"token If");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:99:3: ( If '(' expression ')' '{' block '}' -> ^( EXP expression block ) )
            // grammar/PlazmaScript.g:99:6: If '(' expression ')' '{' block '}'
            {
            If50=(Token)match(input,If,FOLLOW_If_in_ifStat567); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_If.add(If50);

            char_literal51=(Token)match(input,OParen,FOLLOW_OParen_in_ifStat569); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal51);

            pushFollow(FOLLOW_expression_in_ifStat571);
            expression52=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression52.getTree());
            char_literal53=(Token)match(input,CParen,FOLLOW_CParen_in_ifStat573); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal53);

            char_literal54=(Token)match(input,OBrace,FOLLOW_OBrace_in_ifStat575); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal54);

            pushFollow(FOLLOW_block_in_ifStat577);
            block55=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block55.getTree());
            char_literal56=(Token)match(input,CBrace,FOLLOW_CBrace_in_ifStat579); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal56);



            // AST REWRITE
            // elements: block, expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 99:42: -> ^( EXP expression block )
            {
                // grammar/PlazmaScript.g:99:45: ^( EXP expression block )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EXP, "EXP"), root_1);

                adaptor.addChild(root_1, stream_expression.nextTree());
                adaptor.addChild(root_1, stream_block.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ifStat"

    public static class elseIfStat_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elseIfStat"
    // grammar/PlazmaScript.g:102:1: elseIfStat : Else If '(' expression ')' '{' block '}' -> ^( EXP expression block ) ;
    public final PlazmaScriptParser.elseIfStat_return elseIfStat() throws RecognitionException {
        PlazmaScriptParser.elseIfStat_return retval = new PlazmaScriptParser.elseIfStat_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Else57=null;
        Token If58=null;
        Token char_literal59=null;
        Token char_literal61=null;
        Token char_literal62=null;
        Token char_literal64=null;
        PlazmaScriptParser.expression_return expression60 = null;

        PlazmaScriptParser.block_return block63 = null;


        Object Else57_tree=null;
        Object If58_tree=null;
        Object char_literal59_tree=null;
        Object char_literal61_tree=null;
        Object char_literal62_tree=null;
        Object char_literal64_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_Else=new RewriteRuleTokenStream(adaptor,"token Else");
        RewriteRuleTokenStream stream_If=new RewriteRuleTokenStream(adaptor,"token If");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:103:3: ( Else If '(' expression ')' '{' block '}' -> ^( EXP expression block ) )
            // grammar/PlazmaScript.g:103:6: Else If '(' expression ')' '{' block '}'
            {
            Else57=(Token)match(input,Else,FOLLOW_Else_in_elseIfStat603); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Else.add(Else57);

            If58=(Token)match(input,If,FOLLOW_If_in_elseIfStat605); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_If.add(If58);

            char_literal59=(Token)match(input,OParen,FOLLOW_OParen_in_elseIfStat607); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal59);

            pushFollow(FOLLOW_expression_in_elseIfStat609);
            expression60=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression60.getTree());
            char_literal61=(Token)match(input,CParen,FOLLOW_CParen_in_elseIfStat611); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal61);

            char_literal62=(Token)match(input,OBrace,FOLLOW_OBrace_in_elseIfStat613); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal62);

            pushFollow(FOLLOW_block_in_elseIfStat615);
            block63=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block63.getTree());
            char_literal64=(Token)match(input,CBrace,FOLLOW_CBrace_in_elseIfStat617); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal64);



            // AST REWRITE
            // elements: block, expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 103:47: -> ^( EXP expression block )
            {
                // grammar/PlazmaScript.g:103:50: ^( EXP expression block )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EXP, "EXP"), root_1);

                adaptor.addChild(root_1, stream_expression.nextTree());
                adaptor.addChild(root_1, stream_block.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "elseIfStat"

    public static class elseStat_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elseStat"
    // grammar/PlazmaScript.g:106:1: elseStat : Else '{' block '}' -> ^( EXP block ) ;
    public final PlazmaScriptParser.elseStat_return elseStat() throws RecognitionException {
        PlazmaScriptParser.elseStat_return retval = new PlazmaScriptParser.elseStat_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Else65=null;
        Token char_literal66=null;
        Token char_literal68=null;
        PlazmaScriptParser.block_return block67 = null;


        Object Else65_tree=null;
        Object char_literal66_tree=null;
        Object char_literal68_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_Else=new RewriteRuleTokenStream(adaptor,"token Else");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:107:3: ( Else '{' block '}' -> ^( EXP block ) )
            // grammar/PlazmaScript.g:107:6: Else '{' block '}'
            {
            Else65=(Token)match(input,Else,FOLLOW_Else_in_elseStat641); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Else.add(Else65);

            char_literal66=(Token)match(input,OBrace,FOLLOW_OBrace_in_elseStat643); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal66);

            pushFollow(FOLLOW_block_in_elseStat645);
            block67=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block67.getTree());
            char_literal68=(Token)match(input,CBrace,FOLLOW_CBrace_in_elseStat647); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal68);



            // AST REWRITE
            // elements: block
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 107:25: -> ^( EXP block )
            {
                // grammar/PlazmaScript.g:107:28: ^( EXP block )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EXP, "EXP"), root_1);

                adaptor.addChild(root_1, stream_block.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "elseStat"

    public static class functionDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "functionDecl"
    // grammar/PlazmaScript.g:110:1: functionDecl : Def Identifier '(' ( idList )? ')' '{' block '}' ;
    public final PlazmaScriptParser.functionDecl_return functionDecl() throws RecognitionException {
        PlazmaScriptParser.functionDecl_return retval = new PlazmaScriptParser.functionDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Def69=null;
        Token Identifier70=null;
        Token char_literal71=null;
        Token char_literal73=null;
        Token char_literal74=null;
        Token char_literal76=null;
        PlazmaScriptParser.idList_return idList72 = null;

        PlazmaScriptParser.block_return block75 = null;


        Object Def69_tree=null;
        Object Identifier70_tree=null;
        Object char_literal71_tree=null;
        Object char_literal73_tree=null;
        Object char_literal74_tree=null;
        Object char_literal76_tree=null;

        try {
            // grammar/PlazmaScript.g:111:3: ( Def Identifier '(' ( idList )? ')' '{' block '}' )
            // grammar/PlazmaScript.g:111:6: Def Identifier '(' ( idList )? ')' '{' block '}'
            {
            root_0 = (Object)adaptor.nil();

            Def69=(Token)match(input,Def,FOLLOW_Def_in_functionDecl669); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Def69_tree = (Object)adaptor.create(Def69);
            adaptor.addChild(root_0, Def69_tree);
            }
            Identifier70=(Token)match(input,Identifier,FOLLOW_Identifier_in_functionDecl671); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier70_tree = (Object)adaptor.create(Identifier70);
            adaptor.addChild(root_0, Identifier70_tree);
            }
            char_literal71=(Token)match(input,OParen,FOLLOW_OParen_in_functionDecl673); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal71_tree = (Object)adaptor.create(char_literal71);
            adaptor.addChild(root_0, char_literal71_tree);
            }
            // grammar/PlazmaScript.g:111:25: ( idList )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==Identifier) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // grammar/PlazmaScript.g:0:0: idList
                    {
                    pushFollow(FOLLOW_idList_in_functionDecl675);
                    idList72=idList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, idList72.getTree());

                    }
                    break;

            }

            char_literal73=(Token)match(input,CParen,FOLLOW_CParen_in_functionDecl678); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal73_tree = (Object)adaptor.create(char_literal73);
            adaptor.addChild(root_0, char_literal73_tree);
            }
            char_literal74=(Token)match(input,OBrace,FOLLOW_OBrace_in_functionDecl680); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal74_tree = (Object)adaptor.create(char_literal74);
            adaptor.addChild(root_0, char_literal74_tree);
            }
            pushFollow(FOLLOW_block_in_functionDecl682);
            block75=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, block75.getTree());
            char_literal76=(Token)match(input,CBrace,FOLLOW_CBrace_in_functionDecl684); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal76_tree = (Object)adaptor.create(char_literal76);
            adaptor.addChild(root_0, char_literal76_tree);
            }
            if ( state.backtracking==0 ) {
              defineFunction((Identifier70!=null?Identifier70.getText():null), (idList72!=null?((Object)idList72.tree):null), (block75!=null?((Object)block75.tree):null));
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "functionDecl"

    public static class forStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "forStatement"
    // grammar/PlazmaScript.g:120:1: forStatement : For '(' Identifier 'in' expression ')' '{' block '}' -> ^( For Identifier expression block ) ;
    public final PlazmaScriptParser.forStatement_return forStatement() throws RecognitionException {
        PlazmaScriptParser.forStatement_return retval = new PlazmaScriptParser.forStatement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token For77=null;
        Token char_literal78=null;
        Token Identifier79=null;
        Token string_literal80=null;
        Token char_literal82=null;
        Token char_literal83=null;
        Token char_literal85=null;
        PlazmaScriptParser.expression_return expression81 = null;

        PlazmaScriptParser.block_return block84 = null;


        Object For77_tree=null;
        Object char_literal78_tree=null;
        Object Identifier79_tree=null;
        Object string_literal80_tree=null;
        Object char_literal82_tree=null;
        Object char_literal83_tree=null;
        Object char_literal85_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_In=new RewriteRuleTokenStream(adaptor,"token In");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_For=new RewriteRuleTokenStream(adaptor,"token For");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:121:3: ( For '(' Identifier 'in' expression ')' '{' block '}' -> ^( For Identifier expression block ) )
            // grammar/PlazmaScript.g:121:6: For '(' Identifier 'in' expression ')' '{' block '}'
            {
            For77=(Token)match(input,For,FOLLOW_For_in_forStatement713); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_For.add(For77);

            char_literal78=(Token)match(input,OParen,FOLLOW_OParen_in_forStatement715); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal78);

            Identifier79=(Token)match(input,Identifier,FOLLOW_Identifier_in_forStatement717); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Identifier.add(Identifier79);

            string_literal80=(Token)match(input,In,FOLLOW_In_in_forStatement719); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_In.add(string_literal80);

            pushFollow(FOLLOW_expression_in_forStatement721);
            expression81=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression81.getTree());
            char_literal82=(Token)match(input,CParen,FOLLOW_CParen_in_forStatement723); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal82);

            char_literal83=(Token)match(input,OBrace,FOLLOW_OBrace_in_forStatement725); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal83);

            pushFollow(FOLLOW_block_in_forStatement727);
            block84=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block84.getTree());
            char_literal85=(Token)match(input,CBrace,FOLLOW_CBrace_in_forStatement729); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal85);



            // AST REWRITE
            // elements: block, For, expression, Identifier
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 122:6: -> ^( For Identifier expression block )
            {
                // grammar/PlazmaScript.g:122:9: ^( For Identifier expression block )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_For.nextNode(), root_1);

                adaptor.addChild(root_1, stream_Identifier.nextNode());
                adaptor.addChild(root_1, stream_expression.nextTree());
                adaptor.addChild(root_1, stream_block.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "forStatement"

    public static class whileStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "whileStatement"
    // grammar/PlazmaScript.g:126:1: whileStatement : While '(' expression ')' '{' block '}' -> ^( While expression block ) ;
    public final PlazmaScriptParser.whileStatement_return whileStatement() throws RecognitionException {
        PlazmaScriptParser.whileStatement_return retval = new PlazmaScriptParser.whileStatement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token While86=null;
        Token char_literal87=null;
        Token char_literal89=null;
        Token char_literal90=null;
        Token char_literal92=null;
        PlazmaScriptParser.expression_return expression88 = null;

        PlazmaScriptParser.block_return block91 = null;


        Object While86_tree=null;
        Object char_literal87_tree=null;
        Object char_literal89_tree=null;
        Object char_literal90_tree=null;
        Object char_literal92_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_While=new RewriteRuleTokenStream(adaptor,"token While");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:127:3: ( While '(' expression ')' '{' block '}' -> ^( While expression block ) )
            // grammar/PlazmaScript.g:127:6: While '(' expression ')' '{' block '}'
            {
            While86=(Token)match(input,While,FOLLOW_While_in_whileStatement764); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_While.add(While86);

            char_literal87=(Token)match(input,OParen,FOLLOW_OParen_in_whileStatement766); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal87);

            pushFollow(FOLLOW_expression_in_whileStatement768);
            expression88=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression88.getTree());
            char_literal89=(Token)match(input,CParen,FOLLOW_CParen_in_whileStatement770); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal89);

            char_literal90=(Token)match(input,OBrace,FOLLOW_OBrace_in_whileStatement772); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal90);

            pushFollow(FOLLOW_block_in_whileStatement774);
            block91=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block91.getTree());
            char_literal92=(Token)match(input,CBrace,FOLLOW_CBrace_in_whileStatement776); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal92);



            // AST REWRITE
            // elements: block, While, expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 127:45: -> ^( While expression block )
            {
                // grammar/PlazmaScript.g:127:48: ^( While expression block )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_While.nextNode(), root_1);

                adaptor.addChild(root_1, stream_expression.nextTree());
                adaptor.addChild(root_1, stream_block.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "whileStatement"

    public static class idList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "idList"
    // grammar/PlazmaScript.g:130:1: idList : Identifier ( ',' Identifier )* -> ^( ID_LIST ( Identifier )+ ) ;
    public final PlazmaScriptParser.idList_return idList() throws RecognitionException {
        PlazmaScriptParser.idList_return retval = new PlazmaScriptParser.idList_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Identifier93=null;
        Token char_literal94=null;
        Token Identifier95=null;

        Object Identifier93_tree=null;
        Object char_literal94_tree=null;
        Object Identifier95_tree=null;
        RewriteRuleTokenStream stream_Comma=new RewriteRuleTokenStream(adaptor,"token Comma");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");

        try {
            // grammar/PlazmaScript.g:131:3: ( Identifier ( ',' Identifier )* -> ^( ID_LIST ( Identifier )+ ) )
            // grammar/PlazmaScript.g:131:6: Identifier ( ',' Identifier )*
            {
            Identifier93=(Token)match(input,Identifier,FOLLOW_Identifier_in_idList800); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Identifier.add(Identifier93);

            // grammar/PlazmaScript.g:131:17: ( ',' Identifier )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==Comma) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // grammar/PlazmaScript.g:131:18: ',' Identifier
            	    {
            	    char_literal94=(Token)match(input,Comma,FOLLOW_Comma_in_idList803); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Comma.add(char_literal94);

            	    Identifier95=(Token)match(input,Identifier,FOLLOW_Identifier_in_idList805); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Identifier.add(Identifier95);


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);



            // AST REWRITE
            // elements: Identifier
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 131:35: -> ^( ID_LIST ( Identifier )+ )
            {
                // grammar/PlazmaScript.g:131:38: ^( ID_LIST ( Identifier )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ID_LIST, "ID_LIST"), root_1);

                if ( !(stream_Identifier.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_Identifier.hasNext() ) {
                    adaptor.addChild(root_1, stream_Identifier.nextNode());

                }
                stream_Identifier.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "idList"

    public static class exprList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "exprList"
    // grammar/PlazmaScript.g:134:1: exprList : expression ( ',' expression )* -> ^( EXP_LIST ( expression )+ ) ;
    public final PlazmaScriptParser.exprList_return exprList() throws RecognitionException {
        PlazmaScriptParser.exprList_return retval = new PlazmaScriptParser.exprList_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal97=null;
        PlazmaScriptParser.expression_return expression96 = null;

        PlazmaScriptParser.expression_return expression98 = null;


        Object char_literal97_tree=null;
        RewriteRuleTokenStream stream_Comma=new RewriteRuleTokenStream(adaptor,"token Comma");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // grammar/PlazmaScript.g:135:3: ( expression ( ',' expression )* -> ^( EXP_LIST ( expression )+ ) )
            // grammar/PlazmaScript.g:135:6: expression ( ',' expression )*
            {
            pushFollow(FOLLOW_expression_in_exprList830);
            expression96=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression96.getTree());
            // grammar/PlazmaScript.g:135:17: ( ',' expression )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==Comma) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // grammar/PlazmaScript.g:135:18: ',' expression
            	    {
            	    char_literal97=(Token)match(input,Comma,FOLLOW_Comma_in_exprList833); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Comma.add(char_literal97);

            	    pushFollow(FOLLOW_expression_in_exprList835);
            	    expression98=expression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_expression.add(expression98.getTree());

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);



            // AST REWRITE
            // elements: expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 135:35: -> ^( EXP_LIST ( expression )+ )
            {
                // grammar/PlazmaScript.g:135:38: ^( EXP_LIST ( expression )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EXP_LIST, "EXP_LIST"), root_1);

                if ( !(stream_expression.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_expression.hasNext() ) {
                    adaptor.addChild(root_1, stream_expression.nextTree());

                }
                stream_expression.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "exprList"

    public static class exprPair_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "exprPair"
    // grammar/PlazmaScript.g:138:1: exprPair : ( expression ':' expression ) -> ^( EXP_PAIR expression expression ) ;
    public final PlazmaScriptParser.exprPair_return exprPair() throws RecognitionException {
        PlazmaScriptParser.exprPair_return retval = new PlazmaScriptParser.exprPair_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal100=null;
        PlazmaScriptParser.expression_return expression99 = null;

        PlazmaScriptParser.expression_return expression101 = null;


        Object char_literal100_tree=null;
        RewriteRuleTokenStream stream_Colon=new RewriteRuleTokenStream(adaptor,"token Colon");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // grammar/PlazmaScript.g:139:3: ( ( expression ':' expression ) -> ^( EXP_PAIR expression expression ) )
            // grammar/PlazmaScript.g:139:6: ( expression ':' expression )
            {
            // grammar/PlazmaScript.g:139:6: ( expression ':' expression )
            // grammar/PlazmaScript.g:139:7: expression ':' expression
            {
            pushFollow(FOLLOW_expression_in_exprPair861);
            expression99=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression99.getTree());
            char_literal100=(Token)match(input,Colon,FOLLOW_Colon_in_exprPair863); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Colon.add(char_literal100);

            pushFollow(FOLLOW_expression_in_exprPair865);
            expression101=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression101.getTree());

            }



            // AST REWRITE
            // elements: expression, expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 139:34: -> ^( EXP_PAIR expression expression )
            {
                // grammar/PlazmaScript.g:139:37: ^( EXP_PAIR expression expression )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EXP_PAIR, "EXP_PAIR"), root_1);

                adaptor.addChild(root_1, stream_expression.nextTree());
                adaptor.addChild(root_1, stream_expression.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "exprPair"

    public static class exprMap_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "exprMap"
    // grammar/PlazmaScript.g:142:1: exprMap : exprPair ( ',' exprPair )* -> ^( EXP_MAP ( exprPair )+ ) ;
    public final PlazmaScriptParser.exprMap_return exprMap() throws RecognitionException {
        PlazmaScriptParser.exprMap_return retval = new PlazmaScriptParser.exprMap_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal103=null;
        PlazmaScriptParser.exprPair_return exprPair102 = null;

        PlazmaScriptParser.exprPair_return exprPair104 = null;


        Object char_literal103_tree=null;
        RewriteRuleTokenStream stream_Comma=new RewriteRuleTokenStream(adaptor,"token Comma");
        RewriteRuleSubtreeStream stream_exprPair=new RewriteRuleSubtreeStream(adaptor,"rule exprPair");
        try {
            // grammar/PlazmaScript.g:143:3: ( exprPair ( ',' exprPair )* -> ^( EXP_MAP ( exprPair )+ ) )
            // grammar/PlazmaScript.g:143:6: exprPair ( ',' exprPair )*
            {
            pushFollow(FOLLOW_exprPair_in_exprMap890);
            exprPair102=exprPair();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_exprPair.add(exprPair102.getTree());
            // grammar/PlazmaScript.g:143:15: ( ',' exprPair )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==Comma) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // grammar/PlazmaScript.g:143:16: ',' exprPair
            	    {
            	    char_literal103=(Token)match(input,Comma,FOLLOW_Comma_in_exprMap893); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Comma.add(char_literal103);

            	    pushFollow(FOLLOW_exprPair_in_exprMap895);
            	    exprPair104=exprPair();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_exprPair.add(exprPair104.getTree());

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);



            // AST REWRITE
            // elements: exprPair
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 143:31: -> ^( EXP_MAP ( exprPair )+ )
            {
                // grammar/PlazmaScript.g:143:34: ^( EXP_MAP ( exprPair )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EXP_MAP, "EXP_MAP"), root_1);

                if ( !(stream_exprPair.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_exprPair.hasNext() ) {
                    adaptor.addChild(root_1, stream_exprPair.nextTree());

                }
                stream_exprPair.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "exprMap"

    public static class expression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expression"
    // grammar/PlazmaScript.g:147:1: expression : condExpr ;
    public final PlazmaScriptParser.expression_return expression() throws RecognitionException {
        PlazmaScriptParser.expression_return retval = new PlazmaScriptParser.expression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        PlazmaScriptParser.condExpr_return condExpr105 = null;



        try {
            // grammar/PlazmaScript.g:148:3: ( condExpr )
            // grammar/PlazmaScript.g:148:6: condExpr
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_condExpr_in_expression921);
            condExpr105=condExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, condExpr105.getTree());

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "expression"

    public static class condExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "condExpr"
    // grammar/PlazmaScript.g:151:1: condExpr : ( orExpr -> orExpr ) ( '?' a= expression ':' b= expression -> ^( TERNARY orExpr $a $b) | In expression -> ^( In orExpr expression ) | RangeE expression -> ^( RangeE orExpr expression ) | Range expression -> ^( Range orExpr expression ) )? ;
    public final PlazmaScriptParser.condExpr_return condExpr() throws RecognitionException {
        PlazmaScriptParser.condExpr_return retval = new PlazmaScriptParser.condExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal107=null;
        Token char_literal108=null;
        Token In109=null;
        Token RangeE111=null;
        Token Range113=null;
        PlazmaScriptParser.expression_return a = null;

        PlazmaScriptParser.expression_return b = null;

        PlazmaScriptParser.orExpr_return orExpr106 = null;

        PlazmaScriptParser.expression_return expression110 = null;

        PlazmaScriptParser.expression_return expression112 = null;

        PlazmaScriptParser.expression_return expression114 = null;


        Object char_literal107_tree=null;
        Object char_literal108_tree=null;
        Object In109_tree=null;
        Object RangeE111_tree=null;
        Object Range113_tree=null;
        RewriteRuleTokenStream stream_RangeE=new RewriteRuleTokenStream(adaptor,"token RangeE");
        RewriteRuleTokenStream stream_Range=new RewriteRuleTokenStream(adaptor,"token Range");
        RewriteRuleTokenStream stream_In=new RewriteRuleTokenStream(adaptor,"token In");
        RewriteRuleTokenStream stream_Colon=new RewriteRuleTokenStream(adaptor,"token Colon");
        RewriteRuleTokenStream stream_QMark=new RewriteRuleTokenStream(adaptor,"token QMark");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_orExpr=new RewriteRuleSubtreeStream(adaptor,"rule orExpr");
        try {
            // grammar/PlazmaScript.g:152:3: ( ( orExpr -> orExpr ) ( '?' a= expression ':' b= expression -> ^( TERNARY orExpr $a $b) | In expression -> ^( In orExpr expression ) | RangeE expression -> ^( RangeE orExpr expression ) | Range expression -> ^( Range orExpr expression ) )? )
            // grammar/PlazmaScript.g:152:6: ( orExpr -> orExpr ) ( '?' a= expression ':' b= expression -> ^( TERNARY orExpr $a $b) | In expression -> ^( In orExpr expression ) | RangeE expression -> ^( RangeE orExpr expression ) | Range expression -> ^( Range orExpr expression ) )?
            {
            // grammar/PlazmaScript.g:152:6: ( orExpr -> orExpr )
            // grammar/PlazmaScript.g:152:7: orExpr
            {
            pushFollow(FOLLOW_orExpr_in_condExpr936);
            orExpr106=orExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_orExpr.add(orExpr106.getTree());


            // AST REWRITE
            // elements: orExpr
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 152:14: -> orExpr
            {
                adaptor.addChild(root_0, stream_orExpr.nextTree());

            }

            retval.tree = root_0;}
            }

            // grammar/PlazmaScript.g:153:6: ( '?' a= expression ':' b= expression -> ^( TERNARY orExpr $a $b) | In expression -> ^( In orExpr expression ) | RangeE expression -> ^( RangeE orExpr expression ) | Range expression -> ^( Range orExpr expression ) )?
            int alt16=5;
            switch ( input.LA(1) ) {
                case QMark:
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
            }

            switch (alt16) {
                case 1 :
                    // grammar/PlazmaScript.g:153:8: '?' a= expression ':' b= expression
                    {
                    char_literal107=(Token)match(input,QMark,FOLLOW_QMark_in_condExpr951); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_QMark.add(char_literal107);

                    pushFollow(FOLLOW_expression_in_condExpr955);
                    a=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(a.getTree());
                    char_literal108=(Token)match(input,Colon,FOLLOW_Colon_in_condExpr957); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Colon.add(char_literal108);

                    pushFollow(FOLLOW_expression_in_condExpr961);
                    b=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(b.getTree());


                    // AST REWRITE
                    // elements: b, a, orExpr
                    // token labels: 
                    // rule labels: retval, b, a
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_b=new RewriteRuleSubtreeStream(adaptor,"rule b",b!=null?b.tree:null);
                    RewriteRuleSubtreeStream stream_a=new RewriteRuleSubtreeStream(adaptor,"rule a",a!=null?a.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 153:42: -> ^( TERNARY orExpr $a $b)
                    {
                        // grammar/PlazmaScript.g:153:45: ^( TERNARY orExpr $a $b)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TERNARY, "TERNARY"), root_1);

                        adaptor.addChild(root_1, stream_orExpr.nextTree());
                        adaptor.addChild(root_1, stream_a.nextTree());
                        adaptor.addChild(root_1, stream_b.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:154:8: In expression
                    {
                    In109=(Token)match(input,In,FOLLOW_In_in_condExpr984); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_In.add(In109);

                    pushFollow(FOLLOW_expression_in_condExpr986);
                    expression110=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression110.getTree());


                    // AST REWRITE
                    // elements: expression, orExpr, In
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 154:42: -> ^( In orExpr expression )
                    {
                        // grammar/PlazmaScript.g:154:45: ^( In orExpr expression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_In.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_orExpr.nextTree());
                        adaptor.addChild(root_1, stream_expression.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // grammar/PlazmaScript.g:156:8: RangeE expression
                    {
                    RangeE111=(Token)match(input,RangeE,FOLLOW_RangeE_in_condExpr1031); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RangeE.add(RangeE111);

                    pushFollow(FOLLOW_expression_in_condExpr1033);
                    expression112=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression112.getTree());


                    // AST REWRITE
                    // elements: orExpr, RangeE, expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 156:42: -> ^( RangeE orExpr expression )
                    {
                        // grammar/PlazmaScript.g:156:45: ^( RangeE orExpr expression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_RangeE.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_orExpr.nextTree());
                        adaptor.addChild(root_1, stream_expression.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 4 :
                    // grammar/PlazmaScript.g:157:8: Range expression
                    {
                    Range113=(Token)match(input,Range,FOLLOW_Range_in_condExpr1068); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Range.add(Range113);

                    pushFollow(FOLLOW_expression_in_condExpr1070);
                    expression114=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression114.getTree());


                    // AST REWRITE
                    // elements: expression, orExpr, Range
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 157:42: -> ^( Range orExpr expression )
                    {
                        // grammar/PlazmaScript.g:157:45: ^( Range orExpr expression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_Range.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_orExpr.nextTree());
                        adaptor.addChild(root_1, stream_expression.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "condExpr"

    public static class orExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "orExpr"
    // grammar/PlazmaScript.g:162:1: orExpr : andExpr ( '||' andExpr )* ;
    public final PlazmaScriptParser.orExpr_return orExpr() throws RecognitionException {
        PlazmaScriptParser.orExpr_return retval = new PlazmaScriptParser.orExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal116=null;
        PlazmaScriptParser.andExpr_return andExpr115 = null;

        PlazmaScriptParser.andExpr_return andExpr117 = null;


        Object string_literal116_tree=null;

        try {
            // grammar/PlazmaScript.g:163:3: ( andExpr ( '||' andExpr )* )
            // grammar/PlazmaScript.g:163:6: andExpr ( '||' andExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_andExpr_in_orExpr1135);
            andExpr115=andExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, andExpr115.getTree());
            // grammar/PlazmaScript.g:163:14: ( '||' andExpr )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==Or) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // grammar/PlazmaScript.g:163:15: '||' andExpr
            	    {
            	    string_literal116=(Token)match(input,Or,FOLLOW_Or_in_orExpr1138); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal116_tree = (Object)adaptor.create(string_literal116);
            	    root_0 = (Object)adaptor.becomeRoot(string_literal116_tree, root_0);
            	    }
            	    pushFollow(FOLLOW_andExpr_in_orExpr1141);
            	    andExpr117=andExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, andExpr117.getTree());

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "orExpr"

    public static class andExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "andExpr"
    // grammar/PlazmaScript.g:166:1: andExpr : equExpr ( '&&' equExpr )* ;
    public final PlazmaScriptParser.andExpr_return andExpr() throws RecognitionException {
        PlazmaScriptParser.andExpr_return retval = new PlazmaScriptParser.andExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal119=null;
        PlazmaScriptParser.equExpr_return equExpr118 = null;

        PlazmaScriptParser.equExpr_return equExpr120 = null;


        Object string_literal119_tree=null;

        try {
            // grammar/PlazmaScript.g:167:3: ( equExpr ( '&&' equExpr )* )
            // grammar/PlazmaScript.g:167:6: equExpr ( '&&' equExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_equExpr_in_andExpr1157);
            equExpr118=equExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, equExpr118.getTree());
            // grammar/PlazmaScript.g:167:14: ( '&&' equExpr )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==And) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // grammar/PlazmaScript.g:167:15: '&&' equExpr
            	    {
            	    string_literal119=(Token)match(input,And,FOLLOW_And_in_andExpr1160); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal119_tree = (Object)adaptor.create(string_literal119);
            	    root_0 = (Object)adaptor.becomeRoot(string_literal119_tree, root_0);
            	    }
            	    pushFollow(FOLLOW_equExpr_in_andExpr1163);
            	    equExpr120=equExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, equExpr120.getTree());

            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "andExpr"

    public static class equExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "equExpr"
    // grammar/PlazmaScript.g:170:1: equExpr : relExpr ( ( '==' | '!=' ) relExpr )* ;
    public final PlazmaScriptParser.equExpr_return equExpr() throws RecognitionException {
        PlazmaScriptParser.equExpr_return retval = new PlazmaScriptParser.equExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set122=null;
        PlazmaScriptParser.relExpr_return relExpr121 = null;

        PlazmaScriptParser.relExpr_return relExpr123 = null;


        Object set122_tree=null;

        try {
            // grammar/PlazmaScript.g:171:3: ( relExpr ( ( '==' | '!=' ) relExpr )* )
            // grammar/PlazmaScript.g:171:6: relExpr ( ( '==' | '!=' ) relExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_relExpr_in_equExpr1179);
            relExpr121=relExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, relExpr121.getTree());
            // grammar/PlazmaScript.g:171:14: ( ( '==' | '!=' ) relExpr )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( ((LA19_0>=Equals && LA19_0<=NEquals)) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // grammar/PlazmaScript.g:171:15: ( '==' | '!=' ) relExpr
            	    {
            	    set122=(Token)input.LT(1);
            	    set122=(Token)input.LT(1);
            	    if ( (input.LA(1)>=Equals && input.LA(1)<=NEquals) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set122), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_relExpr_in_equExpr1191);
            	    relExpr123=relExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, relExpr123.getTree());

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "equExpr"

    public static class relExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "relExpr"
    // grammar/PlazmaScript.g:174:1: relExpr : addExpr ( ( '>=' | '<=' | '>' | '<' ) addExpr )* ;
    public final PlazmaScriptParser.relExpr_return relExpr() throws RecognitionException {
        PlazmaScriptParser.relExpr_return retval = new PlazmaScriptParser.relExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set125=null;
        PlazmaScriptParser.addExpr_return addExpr124 = null;

        PlazmaScriptParser.addExpr_return addExpr126 = null;


        Object set125_tree=null;

        try {
            // grammar/PlazmaScript.g:175:3: ( addExpr ( ( '>=' | '<=' | '>' | '<' ) addExpr )* )
            // grammar/PlazmaScript.g:175:6: addExpr ( ( '>=' | '<=' | '>' | '<' ) addExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_addExpr_in_relExpr1207);
            addExpr124=addExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, addExpr124.getTree());
            // grammar/PlazmaScript.g:175:14: ( ( '>=' | '<=' | '>' | '<' ) addExpr )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( ((LA20_0>=GTEquals && LA20_0<=LTEquals)||(LA20_0>=GT && LA20_0<=LT)) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // grammar/PlazmaScript.g:175:15: ( '>=' | '<=' | '>' | '<' ) addExpr
            	    {
            	    set125=(Token)input.LT(1);
            	    set125=(Token)input.LT(1);
            	    if ( (input.LA(1)>=GTEquals && input.LA(1)<=LTEquals)||(input.LA(1)>=GT && input.LA(1)<=LT) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set125), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_addExpr_in_relExpr1227);
            	    addExpr126=addExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, addExpr126.getTree());

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "relExpr"

    public static class addExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "addExpr"
    // grammar/PlazmaScript.g:178:1: addExpr : mulExpr ( ( '+' | '-' ) mulExpr )* ;
    public final PlazmaScriptParser.addExpr_return addExpr() throws RecognitionException {
        PlazmaScriptParser.addExpr_return retval = new PlazmaScriptParser.addExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set128=null;
        PlazmaScriptParser.mulExpr_return mulExpr127 = null;

        PlazmaScriptParser.mulExpr_return mulExpr129 = null;


        Object set128_tree=null;

        try {
            // grammar/PlazmaScript.g:179:3: ( mulExpr ( ( '+' | '-' ) mulExpr )* )
            // grammar/PlazmaScript.g:179:6: mulExpr ( ( '+' | '-' ) mulExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_mulExpr_in_addExpr1243);
            mulExpr127=mulExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, mulExpr127.getTree());
            // grammar/PlazmaScript.g:179:14: ( ( '+' | '-' ) mulExpr )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( ((LA21_0>=Add && LA21_0<=Subtract)) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // grammar/PlazmaScript.g:179:15: ( '+' | '-' ) mulExpr
            	    {
            	    set128=(Token)input.LT(1);
            	    set128=(Token)input.LT(1);
            	    if ( (input.LA(1)>=Add && input.LA(1)<=Subtract) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set128), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_mulExpr_in_addExpr1255);
            	    mulExpr129=mulExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, mulExpr129.getTree());

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "addExpr"

    public static class mulExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "mulExpr"
    // grammar/PlazmaScript.g:182:1: mulExpr : powExpr ( ( '*' | '/' | '%' ) powExpr )* ;
    public final PlazmaScriptParser.mulExpr_return mulExpr() throws RecognitionException {
        PlazmaScriptParser.mulExpr_return retval = new PlazmaScriptParser.mulExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set131=null;
        PlazmaScriptParser.powExpr_return powExpr130 = null;

        PlazmaScriptParser.powExpr_return powExpr132 = null;


        Object set131_tree=null;

        try {
            // grammar/PlazmaScript.g:183:3: ( powExpr ( ( '*' | '/' | '%' ) powExpr )* )
            // grammar/PlazmaScript.g:183:6: powExpr ( ( '*' | '/' | '%' ) powExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_powExpr_in_mulExpr1271);
            powExpr130=powExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, powExpr130.getTree());
            // grammar/PlazmaScript.g:183:14: ( ( '*' | '/' | '%' ) powExpr )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0>=Multiply && LA22_0<=Modulus)) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // grammar/PlazmaScript.g:183:15: ( '*' | '/' | '%' ) powExpr
            	    {
            	    set131=(Token)input.LT(1);
            	    set131=(Token)input.LT(1);
            	    if ( (input.LA(1)>=Multiply && input.LA(1)<=Modulus) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set131), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_powExpr_in_mulExpr1287);
            	    powExpr132=powExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, powExpr132.getTree());

            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "mulExpr"

    public static class powExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "powExpr"
    // grammar/PlazmaScript.g:186:1: powExpr : unaryExpr ( '^' unaryExpr )* ;
    public final PlazmaScriptParser.powExpr_return powExpr() throws RecognitionException {
        PlazmaScriptParser.powExpr_return retval = new PlazmaScriptParser.powExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal134=null;
        PlazmaScriptParser.unaryExpr_return unaryExpr133 = null;

        PlazmaScriptParser.unaryExpr_return unaryExpr135 = null;


        Object char_literal134_tree=null;

        try {
            // grammar/PlazmaScript.g:187:3: ( unaryExpr ( '^' unaryExpr )* )
            // grammar/PlazmaScript.g:187:6: unaryExpr ( '^' unaryExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_unaryExpr_in_powExpr1303);
            unaryExpr133=unaryExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpr133.getTree());
            // grammar/PlazmaScript.g:187:16: ( '^' unaryExpr )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==Pow) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // grammar/PlazmaScript.g:187:17: '^' unaryExpr
            	    {
            	    char_literal134=(Token)match(input,Pow,FOLLOW_Pow_in_powExpr1306); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal134_tree = (Object)adaptor.create(char_literal134);
            	    root_0 = (Object)adaptor.becomeRoot(char_literal134_tree, root_0);
            	    }
            	    pushFollow(FOLLOW_unaryExpr_in_powExpr1309);
            	    unaryExpr135=unaryExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpr135.getTree());

            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "powExpr"

    public static class unaryExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "unaryExpr"
    // grammar/PlazmaScript.g:190:1: unaryExpr : ( '-' atom -> ^( UNARY_MIN atom ) | '!' atom -> ^( NEGATE atom ) | atom );
    public final PlazmaScriptParser.unaryExpr_return unaryExpr() throws RecognitionException {
        PlazmaScriptParser.unaryExpr_return retval = new PlazmaScriptParser.unaryExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal136=null;
        Token char_literal138=null;
        PlazmaScriptParser.atom_return atom137 = null;

        PlazmaScriptParser.atom_return atom139 = null;

        PlazmaScriptParser.atom_return atom140 = null;


        Object char_literal136_tree=null;
        Object char_literal138_tree=null;
        RewriteRuleTokenStream stream_Excl=new RewriteRuleTokenStream(adaptor,"token Excl");
        RewriteRuleTokenStream stream_Subtract=new RewriteRuleTokenStream(adaptor,"token Subtract");
        RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");
        try {
            // grammar/PlazmaScript.g:191:3: ( '-' atom -> ^( UNARY_MIN atom ) | '!' atom -> ^( NEGATE atom ) | atom )
            int alt24=3;
            switch ( input.LA(1) ) {
            case Subtract:
                {
                alt24=1;
                }
                break;
            case Excl:
                {
                alt24=2;
                }
                break;
            case Identifier:
            case Println:
            case Print:
            case Assert:
            case Size:
            case Integer:
            case Number:
            case Bool:
            case Date:
            case Null:
            case String:
            case OBrace:
            case OParen:
            case ContextIdentifier:
                {
                alt24=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }

            switch (alt24) {
                case 1 :
                    // grammar/PlazmaScript.g:191:6: '-' atom
                    {
                    char_literal136=(Token)match(input,Subtract,FOLLOW_Subtract_in_unaryExpr1327); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Subtract.add(char_literal136);

                    pushFollow(FOLLOW_atom_in_unaryExpr1329);
                    atom137=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom137.getTree());


                    // AST REWRITE
                    // elements: atom
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 191:15: -> ^( UNARY_MIN atom )
                    {
                        // grammar/PlazmaScript.g:191:18: ^( UNARY_MIN atom )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(UNARY_MIN, "UNARY_MIN"), root_1);

                        adaptor.addChild(root_1, stream_atom.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:192:6: '!' atom
                    {
                    char_literal138=(Token)match(input,Excl,FOLLOW_Excl_in_unaryExpr1344); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Excl.add(char_literal138);

                    pushFollow(FOLLOW_atom_in_unaryExpr1346);
                    atom139=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom139.getTree());


                    // AST REWRITE
                    // elements: atom
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 192:15: -> ^( NEGATE atom )
                    {
                        // grammar/PlazmaScript.g:192:18: ^( NEGATE atom )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(NEGATE, "NEGATE"), root_1);

                        adaptor.addChild(root_1, stream_atom.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // grammar/PlazmaScript.g:193:6: atom
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_atom_in_unaryExpr1361);
                    atom140=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, atom140.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "unaryExpr"

    public static class atom_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "atom"
    // grammar/PlazmaScript.g:196:1: atom : ( Integer | Number | Bool | Date | Null | lookup );
    public final PlazmaScriptParser.atom_return atom() throws RecognitionException {
        PlazmaScriptParser.atom_return retval = new PlazmaScriptParser.atom_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Integer141=null;
        Token Number142=null;
        Token Bool143=null;
        Token Date144=null;
        Token Null145=null;
        PlazmaScriptParser.lookup_return lookup146 = null;


        Object Integer141_tree=null;
        Object Number142_tree=null;
        Object Bool143_tree=null;
        Object Date144_tree=null;
        Object Null145_tree=null;

        try {
            // grammar/PlazmaScript.g:197:3: ( Integer | Number | Bool | Date | Null | lookup )
            int alt25=6;
            switch ( input.LA(1) ) {
            case Integer:
                {
                alt25=1;
                }
                break;
            case Number:
                {
                alt25=2;
                }
                break;
            case Bool:
                {
                alt25=3;
                }
                break;
            case Date:
                {
                alt25=4;
                }
                break;
            case Null:
                {
                alt25=5;
                }
                break;
            case Identifier:
            case Println:
            case Print:
            case Assert:
            case Size:
            case String:
            case OBrace:
            case OParen:
            case ContextIdentifier:
                {
                alt25=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }

            switch (alt25) {
                case 1 :
                    // grammar/PlazmaScript.g:197:6: Integer
                    {
                    root_0 = (Object)adaptor.nil();

                    Integer141=(Token)match(input,Integer,FOLLOW_Integer_in_atom1375); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Integer141_tree = (Object)adaptor.create(Integer141);
                    adaptor.addChild(root_0, Integer141_tree);
                    }

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:198:6: Number
                    {
                    root_0 = (Object)adaptor.nil();

                    Number142=(Token)match(input,Number,FOLLOW_Number_in_atom1382); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Number142_tree = (Object)adaptor.create(Number142);
                    adaptor.addChild(root_0, Number142_tree);
                    }

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScript.g:199:6: Bool
                    {
                    root_0 = (Object)adaptor.nil();

                    Bool143=(Token)match(input,Bool,FOLLOW_Bool_in_atom1389); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Bool143_tree = (Object)adaptor.create(Bool143);
                    adaptor.addChild(root_0, Bool143_tree);
                    }

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScript.g:200:6: Date
                    {
                    root_0 = (Object)adaptor.nil();

                    Date144=(Token)match(input,Date,FOLLOW_Date_in_atom1396); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Date144_tree = (Object)adaptor.create(Date144);
                    adaptor.addChild(root_0, Date144_tree);
                    }

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScript.g:201:6: Null
                    {
                    root_0 = (Object)adaptor.nil();

                    Null145=(Token)match(input,Null,FOLLOW_Null_in_atom1403); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Null145_tree = (Object)adaptor.create(Null145);
                    adaptor.addChild(root_0, Null145_tree);
                    }

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScript.g:202:6: lookup
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_lookup_in_atom1410);
                    lookup146=lookup();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lookup146.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "atom"

    public static class list_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "list"
    // grammar/PlazmaScript.g:205:1: list : '{' ( exprList )? '}' -> ^( LIST ( exprList )? ) ;
    public final PlazmaScriptParser.list_return list() throws RecognitionException {
        PlazmaScriptParser.list_return retval = new PlazmaScriptParser.list_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal147=null;
        Token char_literal149=null;
        PlazmaScriptParser.exprList_return exprList148 = null;


        Object char_literal147_tree=null;
        Object char_literal149_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_exprList=new RewriteRuleSubtreeStream(adaptor,"rule exprList");
        try {
            // grammar/PlazmaScript.g:206:3: ( '{' ( exprList )? '}' -> ^( LIST ( exprList )? ) )
            // grammar/PlazmaScript.g:206:6: '{' ( exprList )? '}'
            {
            char_literal147=(Token)match(input,OBrace,FOLLOW_OBrace_in_list1424); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal147);

            // grammar/PlazmaScript.g:206:10: ( exprList )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( ((LA26_0>=Identifier && LA26_0<=Size)||(LA26_0>=Integer && LA26_0<=String)||LA26_0==Excl||LA26_0==Subtract||LA26_0==OBrace||LA26_0==OParen||LA26_0==ContextIdentifier) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // grammar/PlazmaScript.g:0:0: exprList
                    {
                    pushFollow(FOLLOW_exprList_in_list1426);
                    exprList148=exprList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_exprList.add(exprList148.getTree());

                    }
                    break;

            }

            char_literal149=(Token)match(input,CBrace,FOLLOW_CBrace_in_list1429); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal149);



            // AST REWRITE
            // elements: exprList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 206:24: -> ^( LIST ( exprList )? )
            {
                // grammar/PlazmaScript.g:206:27: ^( LIST ( exprList )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LIST, "LIST"), root_1);

                // grammar/PlazmaScript.g:206:34: ( exprList )?
                if ( stream_exprList.hasNext() ) {
                    adaptor.addChild(root_1, stream_exprList.nextTree());

                }
                stream_exprList.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "list"

    public static class map_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "map"
    // grammar/PlazmaScript.g:209:1: map : '{' ( ':' | exprMap ) '}' -> ^( MAP ( exprMap )? ) ;
    public final PlazmaScriptParser.map_return map() throws RecognitionException {
        PlazmaScriptParser.map_return retval = new PlazmaScriptParser.map_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal150=null;
        Token char_literal151=null;
        Token char_literal153=null;
        PlazmaScriptParser.exprMap_return exprMap152 = null;


        Object char_literal150_tree=null;
        Object char_literal151_tree=null;
        Object char_literal153_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_Colon=new RewriteRuleTokenStream(adaptor,"token Colon");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_exprMap=new RewriteRuleSubtreeStream(adaptor,"rule exprMap");
        try {
            // grammar/PlazmaScript.g:210:3: ( '{' ( ':' | exprMap ) '}' -> ^( MAP ( exprMap )? ) )
            // grammar/PlazmaScript.g:210:6: '{' ( ':' | exprMap ) '}'
            {
            char_literal150=(Token)match(input,OBrace,FOLLOW_OBrace_in_map1452); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal150);

            // grammar/PlazmaScript.g:210:10: ( ':' | exprMap )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==Colon) ) {
                alt27=1;
            }
            else if ( ((LA27_0>=Identifier && LA27_0<=Size)||(LA27_0>=Integer && LA27_0<=String)||LA27_0==Excl||LA27_0==Subtract||LA27_0==OBrace||LA27_0==OParen||LA27_0==ContextIdentifier) ) {
                alt27=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // grammar/PlazmaScript.g:210:11: ':'
                    {
                    char_literal151=(Token)match(input,Colon,FOLLOW_Colon_in_map1455); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Colon.add(char_literal151);


                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:210:17: exprMap
                    {
                    pushFollow(FOLLOW_exprMap_in_map1459);
                    exprMap152=exprMap();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_exprMap.add(exprMap152.getTree());

                    }
                    break;

            }

            char_literal153=(Token)match(input,CBrace,FOLLOW_CBrace_in_map1462); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal153);



            // AST REWRITE
            // elements: exprMap
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 210:30: -> ^( MAP ( exprMap )? )
            {
                // grammar/PlazmaScript.g:210:33: ^( MAP ( exprMap )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(MAP, "MAP"), root_1);

                // grammar/PlazmaScript.g:210:39: ( exprMap )?
                if ( stream_exprMap.hasNext() ) {
                    adaptor.addChild(root_1, stream_exprMap.nextTree());

                }
                stream_exprMap.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "map"

    public static class lookup_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "lookup"
    // grammar/PlazmaScript.g:213:1: lookup : ( functionCall ( indexes )? -> ^( LOOKUP functionCall ( indexes )? ) | list ( indexes )? -> ^( LOOKUP list ( indexes )? ) | map ( indexes )? -> ^( LOOKUP map ( indexes )? ) | Identifier ( indexes )? -> ^( LOOKUP Identifier ( indexes )? ) | anyIdentifier ( indexes )? -> ^( LOOKUP ( indexes )? ) | String ( indexes )? -> ^( LOOKUP String ( indexes )? ) | '(' expression ')' ( indexes )? -> ^( LOOKUP expression ( indexes )? ) );
    public final PlazmaScriptParser.lookup_return lookup() throws RecognitionException {
        PlazmaScriptParser.lookup_return retval = new PlazmaScriptParser.lookup_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Identifier160=null;
        Token String164=null;
        Token char_literal166=null;
        Token char_literal168=null;
        PlazmaScriptParser.functionCall_return functionCall154 = null;

        PlazmaScriptParser.indexes_return indexes155 = null;

        PlazmaScriptParser.list_return list156 = null;

        PlazmaScriptParser.indexes_return indexes157 = null;

        PlazmaScriptParser.map_return map158 = null;

        PlazmaScriptParser.indexes_return indexes159 = null;

        PlazmaScriptParser.indexes_return indexes161 = null;

        PlazmaScriptParser.anyIdentifier_return anyIdentifier162 = null;

        PlazmaScriptParser.indexes_return indexes163 = null;

        PlazmaScriptParser.indexes_return indexes165 = null;

        PlazmaScriptParser.expression_return expression167 = null;

        PlazmaScriptParser.indexes_return indexes169 = null;


        Object Identifier160_tree=null;
        Object String164_tree=null;
        Object char_literal166_tree=null;
        Object char_literal168_tree=null;
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_String=new RewriteRuleTokenStream(adaptor,"token String");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_functionCall=new RewriteRuleSubtreeStream(adaptor,"rule functionCall");
        RewriteRuleSubtreeStream stream_anyIdentifier=new RewriteRuleSubtreeStream(adaptor,"rule anyIdentifier");
        RewriteRuleSubtreeStream stream_map=new RewriteRuleSubtreeStream(adaptor,"rule map");
        RewriteRuleSubtreeStream stream_indexes=new RewriteRuleSubtreeStream(adaptor,"rule indexes");
        RewriteRuleSubtreeStream stream_list=new RewriteRuleSubtreeStream(adaptor,"rule list");
        try {
            // grammar/PlazmaScript.g:214:3: ( functionCall ( indexes )? -> ^( LOOKUP functionCall ( indexes )? ) | list ( indexes )? -> ^( LOOKUP list ( indexes )? ) | map ( indexes )? -> ^( LOOKUP map ( indexes )? ) | Identifier ( indexes )? -> ^( LOOKUP Identifier ( indexes )? ) | anyIdentifier ( indexes )? -> ^( LOOKUP ( indexes )? ) | String ( indexes )? -> ^( LOOKUP String ( indexes )? ) | '(' expression ')' ( indexes )? -> ^( LOOKUP expression ( indexes )? ) )
            int alt35=7;
            alt35 = dfa35.predict(input);
            switch (alt35) {
                case 1 :
                    // grammar/PlazmaScript.g:214:6: functionCall ( indexes )?
                    {
                    pushFollow(FOLLOW_functionCall_in_lookup1485);
                    functionCall154=functionCall();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_functionCall.add(functionCall154.getTree());
                    // grammar/PlazmaScript.g:214:19: ( indexes )?
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==OBracket) ) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1487);
                            indexes155=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes155.getTree());

                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: functionCall, indexes
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 214:34: -> ^( LOOKUP functionCall ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:214:37: ^( LOOKUP functionCall ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_functionCall.nextTree());
                        // grammar/PlazmaScript.g:214:59: ( indexes )?
                        if ( stream_indexes.hasNext() ) {
                            adaptor.addChild(root_1, stream_indexes.nextTree());

                        }
                        stream_indexes.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:215:6: list ( indexes )?
                    {
                    pushFollow(FOLLOW_list_in_lookup1512);
                    list156=list();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_list.add(list156.getTree());
                    // grammar/PlazmaScript.g:215:11: ( indexes )?
                    int alt29=2;
                    int LA29_0 = input.LA(1);

                    if ( (LA29_0==OBracket) ) {
                        alt29=1;
                    }
                    switch (alt29) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1514);
                            indexes157=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes157.getTree());

                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: list, indexes
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 215:34: -> ^( LOOKUP list ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:215:37: ^( LOOKUP list ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_list.nextTree());
                        // grammar/PlazmaScript.g:215:51: ( indexes )?
                        if ( stream_indexes.hasNext() ) {
                            adaptor.addChild(root_1, stream_indexes.nextTree());

                        }
                        stream_indexes.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // grammar/PlazmaScript.g:216:6: map ( indexes )?
                    {
                    pushFollow(FOLLOW_map_in_lookup1547);
                    map158=map();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_map.add(map158.getTree());
                    // grammar/PlazmaScript.g:216:10: ( indexes )?
                    int alt30=2;
                    int LA30_0 = input.LA(1);

                    if ( (LA30_0==OBracket) ) {
                        alt30=1;
                    }
                    switch (alt30) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1549);
                            indexes159=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes159.getTree());

                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: map, indexes
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 216:34: -> ^( LOOKUP map ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:216:37: ^( LOOKUP map ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_map.nextTree());
                        // grammar/PlazmaScript.g:216:50: ( indexes )?
                        if ( stream_indexes.hasNext() ) {
                            adaptor.addChild(root_1, stream_indexes.nextTree());

                        }
                        stream_indexes.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 4 :
                    // grammar/PlazmaScript.g:217:6: Identifier ( indexes )?
                    {
                    Identifier160=(Token)match(input,Identifier,FOLLOW_Identifier_in_lookup1585); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier160);

                    // grammar/PlazmaScript.g:217:17: ( indexes )?
                    int alt31=2;
                    int LA31_0 = input.LA(1);

                    if ( (LA31_0==OBracket) ) {
                        alt31=1;
                    }
                    switch (alt31) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1587);
                            indexes161=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes161.getTree());

                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: Identifier, indexes
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 217:34: -> ^( LOOKUP Identifier ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:217:37: ^( LOOKUP Identifier ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_Identifier.nextNode());
                        // grammar/PlazmaScript.g:217:57: ( indexes )?
                        if ( stream_indexes.hasNext() ) {
                            adaptor.addChild(root_1, stream_indexes.nextTree());

                        }
                        stream_indexes.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 5 :
                    // grammar/PlazmaScript.g:218:6: anyIdentifier ( indexes )?
                    {
                    pushFollow(FOLLOW_anyIdentifier_in_lookup1614);
                    anyIdentifier162=anyIdentifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_anyIdentifier.add(anyIdentifier162.getTree());
                    // grammar/PlazmaScript.g:218:20: ( indexes )?
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( (LA32_0==OBracket) ) {
                        alt32=1;
                    }
                    switch (alt32) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1616);
                            indexes163=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes163.getTree());

                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: indexes
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 218:34: -> ^( LOOKUP ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:218:37: ^( LOOKUP ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, new CommonTree(new CommonToken(Identifier, (anyIdentifier162!=null?input.toString(anyIdentifier162.start,anyIdentifier162.stop):null))));
                        // grammar/PlazmaScript.g:218:113: ( indexes )?
                        if ( stream_indexes.hasNext() ) {
                            adaptor.addChild(root_1, stream_indexes.nextTree());

                        }
                        stream_indexes.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 6 :
                    // grammar/PlazmaScript.g:219:6: String ( indexes )?
                    {
                    String164=(Token)match(input,String,FOLLOW_String_in_lookup1642); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_String.add(String164);

                    // grammar/PlazmaScript.g:219:13: ( indexes )?
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==OBracket) ) {
                        alt33=1;
                    }
                    switch (alt33) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1644);
                            indexes165=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes165.getTree());

                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: String, indexes
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 219:34: -> ^( LOOKUP String ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:219:37: ^( LOOKUP String ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_String.nextNode());
                        // grammar/PlazmaScript.g:219:53: ( indexes )?
                        if ( stream_indexes.hasNext() ) {
                            adaptor.addChild(root_1, stream_indexes.nextTree());

                        }
                        stream_indexes.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 7 :
                    // grammar/PlazmaScript.g:220:6: '(' expression ')' ( indexes )?
                    {
                    char_literal166=(Token)match(input,OParen,FOLLOW_OParen_in_lookup1675); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal166);

                    pushFollow(FOLLOW_expression_in_lookup1677);
                    expression167=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression167.getTree());
                    char_literal168=(Token)match(input,CParen,FOLLOW_CParen_in_lookup1679); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal168);

                    // grammar/PlazmaScript.g:220:25: ( indexes )?
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==OBracket) ) {
                        alt34=1;
                    }
                    switch (alt34) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1681);
                            indexes169=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes169.getTree());

                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: indexes, expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 220:34: -> ^( LOOKUP expression ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:220:37: ^( LOOKUP expression ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_expression.nextTree());
                        // grammar/PlazmaScript.g:220:57: ( indexes )?
                        if ( stream_indexes.hasNext() ) {
                            adaptor.addChild(root_1, stream_indexes.nextTree());

                        }
                        stream_indexes.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "lookup"

    public static class indexes_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "indexes"
    // grammar/PlazmaScript.g:223:1: indexes : ( '[' expression ']' )+ -> ^( INDEXES ( expression )+ ) ;
    public final PlazmaScriptParser.indexes_return indexes() throws RecognitionException {
        PlazmaScriptParser.indexes_return retval = new PlazmaScriptParser.indexes_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal170=null;
        Token char_literal172=null;
        PlazmaScriptParser.expression_return expression171 = null;


        Object char_literal170_tree=null;
        Object char_literal172_tree=null;
        RewriteRuleTokenStream stream_CBracket=new RewriteRuleTokenStream(adaptor,"token CBracket");
        RewriteRuleTokenStream stream_OBracket=new RewriteRuleTokenStream(adaptor,"token OBracket");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // grammar/PlazmaScript.g:224:3: ( ( '[' expression ']' )+ -> ^( INDEXES ( expression )+ ) )
            // grammar/PlazmaScript.g:224:6: ( '[' expression ']' )+
            {
            // grammar/PlazmaScript.g:224:6: ( '[' expression ']' )+
            int cnt36=0;
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==OBracket) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // grammar/PlazmaScript.g:224:7: '[' expression ']'
            	    {
            	    char_literal170=(Token)match(input,OBracket,FOLLOW_OBracket_in_indexes1708); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_OBracket.add(char_literal170);

            	    pushFollow(FOLLOW_expression_in_indexes1710);
            	    expression171=expression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_expression.add(expression171.getTree());
            	    char_literal172=(Token)match(input,CBracket,FOLLOW_CBracket_in_indexes1712); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_CBracket.add(char_literal172);


            	    }
            	    break;

            	default :
            	    if ( cnt36 >= 1 ) break loop36;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(36, input);
                        throw eee;
                }
                cnt36++;
            } while (true);



            // AST REWRITE
            // elements: expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 224:28: -> ^( INDEXES ( expression )+ )
            {
                // grammar/PlazmaScript.g:224:31: ^( INDEXES ( expression )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(INDEXES, "INDEXES"), root_1);

                if ( !(stream_expression.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_expression.hasNext() ) {
                    adaptor.addChild(root_1, stream_expression.nextTree());

                }
                stream_expression.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "indexes"

    public static class anyIdentifier_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "anyIdentifier"
    // grammar/PlazmaScript.g:305:1: anyIdentifier : ( ContextIdentifier | Identifier );
    public final PlazmaScriptParser.anyIdentifier_return anyIdentifier() throws RecognitionException {
        PlazmaScriptParser.anyIdentifier_return retval = new PlazmaScriptParser.anyIdentifier_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set173=null;

        Object set173_tree=null;

        try {
            // grammar/PlazmaScript.g:306:3: ( ContextIdentifier | Identifier )
            // grammar/PlazmaScript.g:
            {
            root_0 = (Object)adaptor.nil();

            set173=(Token)input.LT(1);
            if ( input.LA(1)==Identifier||input.LA(1)==ContextIdentifier ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set173));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "anyIdentifier"

    // $ANTLR start synpred11_PlazmaScript
    public final void synpred11_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:82:6: ( Identifier ( indexes )? '=' expression )
        // grammar/PlazmaScript.g:82:6: Identifier ( indexes )? '=' expression
        {
        match(input,Identifier,FOLLOW_Identifier_in_synpred11_PlazmaScript333); if (state.failed) return ;
        // grammar/PlazmaScript.g:82:17: ( indexes )?
        int alt37=2;
        int LA37_0 = input.LA(1);

        if ( (LA37_0==OBracket) ) {
            alt37=1;
        }
        switch (alt37) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: indexes
                {
                pushFollow(FOLLOW_indexes_in_synpred11_PlazmaScript335);
                indexes();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        match(input,Assign,FOLLOW_Assign_in_synpred11_PlazmaScript338); if (state.failed) return ;
        pushFollow(FOLLOW_expression_in_synpred11_PlazmaScript340);
        expression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred11_PlazmaScript

    // $ANTLR start synpred53_PlazmaScript
    public final void synpred53_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:214:6: ( functionCall ( indexes )? )
        // grammar/PlazmaScript.g:214:6: functionCall ( indexes )?
        {
        pushFollow(FOLLOW_functionCall_in_synpred53_PlazmaScript1485);
        functionCall();

        state._fsp--;
        if (state.failed) return ;
        // grammar/PlazmaScript.g:214:19: ( indexes )?
        int alt40=2;
        int LA40_0 = input.LA(1);

        if ( (LA40_0==OBracket) ) {
            alt40=1;
        }
        switch (alt40) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: indexes
                {
                pushFollow(FOLLOW_indexes_in_synpred53_PlazmaScript1487);
                indexes();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred53_PlazmaScript

    // $ANTLR start synpred55_PlazmaScript
    public final void synpred55_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:215:6: ( list ( indexes )? )
        // grammar/PlazmaScript.g:215:6: list ( indexes )?
        {
        pushFollow(FOLLOW_list_in_synpred55_PlazmaScript1512);
        list();

        state._fsp--;
        if (state.failed) return ;
        // grammar/PlazmaScript.g:215:11: ( indexes )?
        int alt41=2;
        int LA41_0 = input.LA(1);

        if ( (LA41_0==OBracket) ) {
            alt41=1;
        }
        switch (alt41) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: indexes
                {
                pushFollow(FOLLOW_indexes_in_synpred55_PlazmaScript1514);
                indexes();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred55_PlazmaScript

    // $ANTLR start synpred57_PlazmaScript
    public final void synpred57_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:216:6: ( map ( indexes )? )
        // grammar/PlazmaScript.g:216:6: map ( indexes )?
        {
        pushFollow(FOLLOW_map_in_synpred57_PlazmaScript1547);
        map();

        state._fsp--;
        if (state.failed) return ;
        // grammar/PlazmaScript.g:216:10: ( indexes )?
        int alt42=2;
        int LA42_0 = input.LA(1);

        if ( (LA42_0==OBracket) ) {
            alt42=1;
        }
        switch (alt42) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: indexes
                {
                pushFollow(FOLLOW_indexes_in_synpred57_PlazmaScript1549);
                indexes();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred57_PlazmaScript

    // $ANTLR start synpred59_PlazmaScript
    public final void synpred59_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:217:6: ( Identifier ( indexes )? )
        // grammar/PlazmaScript.g:217:6: Identifier ( indexes )?
        {
        match(input,Identifier,FOLLOW_Identifier_in_synpred59_PlazmaScript1585); if (state.failed) return ;
        // grammar/PlazmaScript.g:217:17: ( indexes )?
        int alt43=2;
        int LA43_0 = input.LA(1);

        if ( (LA43_0==OBracket) ) {
            alt43=1;
        }
        switch (alt43) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: indexes
                {
                pushFollow(FOLLOW_indexes_in_synpred59_PlazmaScript1587);
                indexes();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred59_PlazmaScript

    // $ANTLR start synpred61_PlazmaScript
    public final void synpred61_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:218:6: ( anyIdentifier ( indexes )? )
        // grammar/PlazmaScript.g:218:6: anyIdentifier ( indexes )?
        {
        pushFollow(FOLLOW_anyIdentifier_in_synpred61_PlazmaScript1614);
        anyIdentifier();

        state._fsp--;
        if (state.failed) return ;
        // grammar/PlazmaScript.g:218:20: ( indexes )?
        int alt44=2;
        int LA44_0 = input.LA(1);

        if ( (LA44_0==OBracket) ) {
            alt44=1;
        }
        switch (alt44) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: indexes
                {
                pushFollow(FOLLOW_indexes_in_synpred61_PlazmaScript1616);
                indexes();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred61_PlazmaScript

    // Delegated rules

    public final boolean synpred53_PlazmaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred53_PlazmaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred55_PlazmaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred55_PlazmaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred11_PlazmaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred11_PlazmaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred61_PlazmaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred61_PlazmaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred57_PlazmaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred57_PlazmaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred59_PlazmaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred59_PlazmaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA35 dfa35 = new DFA35(this);
    static final String DFA35_eotS =
        "\15\uffff";
    static final String DFA35_eofS =
        "\15\uffff";
    static final String DFA35_minS =
        "\1\34\1\0\4\uffff\1\0\6\uffff";
    static final String DFA35_maxS =
        "\1\116\1\0\4\uffff\1\0\6\uffff";
    static final String DFA35_acceptS =
        "\2\uffff\1\1\4\uffff\1\5\1\6\1\7\1\4\1\2\1\3";
    static final String DFA35_specialS =
        "\1\uffff\1\0\4\uffff\1\1\6\uffff}>";
    static final String[] DFA35_transitionS = {
            "\1\1\4\2\15\uffff\1\10\17\uffff\1\6\3\uffff\1\11\13\uffff\1"+
            "\7",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA35_eot = DFA.unpackEncodedString(DFA35_eotS);
    static final short[] DFA35_eof = DFA.unpackEncodedString(DFA35_eofS);
    static final char[] DFA35_min = DFA.unpackEncodedStringToUnsignedChars(DFA35_minS);
    static final char[] DFA35_max = DFA.unpackEncodedStringToUnsignedChars(DFA35_maxS);
    static final short[] DFA35_accept = DFA.unpackEncodedString(DFA35_acceptS);
    static final short[] DFA35_special = DFA.unpackEncodedString(DFA35_specialS);
    static final short[][] DFA35_transition;

    static {
        int numStates = DFA35_transitionS.length;
        DFA35_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA35_transition[i] = DFA.unpackEncodedString(DFA35_transitionS[i]);
        }
    }

    class DFA35 extends DFA {

        public DFA35(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 35;
            this.eot = DFA35_eot;
            this.eof = DFA35_eof;
            this.min = DFA35_min;
            this.max = DFA35_max;
            this.accept = DFA35_accept;
            this.special = DFA35_special;
            this.transition = DFA35_transition;
        }
        public String getDescription() {
            return "213:1: lookup : ( functionCall ( indexes )? -> ^( LOOKUP functionCall ( indexes )? ) | list ( indexes )? -> ^( LOOKUP list ( indexes )? ) | map ( indexes )? -> ^( LOOKUP map ( indexes )? ) | Identifier ( indexes )? -> ^( LOOKUP Identifier ( indexes )? ) | anyIdentifier ( indexes )? -> ^( LOOKUP ( indexes )? ) | String ( indexes )? -> ^( LOOKUP String ( indexes )? ) | '(' expression ')' ( indexes )? -> ^( LOOKUP expression ( indexes )? ) );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA35_1 = input.LA(1);

                         
                        int index35_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred53_PlazmaScript()) ) {s = 2;}

                        else if ( (synpred59_PlazmaScript()) ) {s = 10;}

                        else if ( (synpred61_PlazmaScript()) ) {s = 7;}

                         
                        input.seek(index35_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA35_6 = input.LA(1);

                         
                        int index35_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred55_PlazmaScript()) ) {s = 11;}

                        else if ( (synpred57_PlazmaScript()) ) {s = 12;}

                         
                        input.seek(index35_6);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 35, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_block_in_parse175 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_parse177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_block196 = new BitSet(new long[]{0x0000003BFE000002L,0x0000000000004000L});
    public static final BitSet FOLLOW_functionDecl_in_block200 = new BitSet(new long[]{0x0000003BFE000002L,0x0000000000004000L});
    public static final BitSet FOLLOW_Return_in_block205 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_expression_in_block207 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_SColon_in_block209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_statement251 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_SColon_in_statement253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_statement266 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_SColon_in_statement268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStatement_in_statement279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forStatement_in_statement286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whileStatement_in_statement293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Break_in_statement300 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_SColon_in_statement302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Continue_in_statement313 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_SColon_in_statement315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_assignment333 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_indexes_in_assignment335 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_Assign_in_assignment338 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_expression_in_assignment340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_anyIdentifier_in_assignment360 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_indexes_in_assignment362 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_Assign_in_assignment365 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_expression_in_assignment367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_functionCall394 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_OParen_in_functionCall396 = new BitSet(new long[]{0x44407E01F0000000L,0x000000000000400CL});
    public static final BitSet FOLLOW_exprList_in_functionCall398 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_CParen_in_functionCall401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Println_in_functionCall419 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_OParen_in_functionCall421 = new BitSet(new long[]{0x44407E01F0000000L,0x000000000000400CL});
    public static final BitSet FOLLOW_expression_in_functionCall423 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_CParen_in_functionCall426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Print_in_functionCall445 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_OParen_in_functionCall447 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_expression_in_functionCall449 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_CParen_in_functionCall451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Assert_in_functionCall472 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_OParen_in_functionCall474 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_expression_in_functionCall476 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_CParen_in_functionCall478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Size_in_functionCall498 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_OParen_in_functionCall500 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_expression_in_functionCall502 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_CParen_in_functionCall504 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStat_in_ifStatement533 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_elseIfStat_in_ifStatement535 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_elseStat_in_ifStatement538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_If_in_ifStat567 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_OParen_in_ifStat569 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_expression_in_ifStat571 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_CParen_in_ifStat573 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_OBrace_in_ifStat575 = new BitSet(new long[]{0x0000003BFE000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_block_in_ifStat577 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_CBrace_in_ifStat579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Else_in_elseIfStat603 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_If_in_elseIfStat605 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_OParen_in_elseIfStat607 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_expression_in_elseIfStat609 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_CParen_in_elseIfStat611 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_OBrace_in_elseIfStat613 = new BitSet(new long[]{0x0000003BFE000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_block_in_elseIfStat615 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_CBrace_in_elseIfStat617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Else_in_elseStat641 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_OBrace_in_elseStat643 = new BitSet(new long[]{0x0000003BFE000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_block_in_elseStat645 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_CBrace_in_elseStat647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Def_in_functionDecl669 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_functionDecl671 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_OParen_in_functionDecl673 = new BitSet(new long[]{0x0000000010000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_idList_in_functionDecl675 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_CParen_in_functionDecl678 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_OBrace_in_functionDecl680 = new BitSet(new long[]{0x0000003BFE000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_block_in_functionDecl682 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_CBrace_in_functionDecl684 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_For_in_forStatement713 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_OParen_in_forStatement715 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_forStatement717 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_In_in_forStatement719 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_expression_in_forStatement721 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_CParen_in_forStatement723 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_OBrace_in_forStatement725 = new BitSet(new long[]{0x0000003BFE000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_block_in_forStatement727 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_CBrace_in_forStatement729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_While_in_whileStatement764 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_OParen_in_whileStatement766 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_expression_in_whileStatement768 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_CParen_in_whileStatement770 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_OBrace_in_whileStatement772 = new BitSet(new long[]{0x0000003BFE000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_block_in_whileStatement774 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_CBrace_in_whileStatement776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_idList800 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000040L});
    public static final BitSet FOLLOW_Comma_in_idList803 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_idList805 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000040L});
    public static final BitSet FOLLOW_expression_in_exprList830 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000040L});
    public static final BitSet FOLLOW_Comma_in_exprList833 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_expression_in_exprList835 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000040L});
    public static final BitSet FOLLOW_expression_in_exprPair861 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_Colon_in_exprPair863 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_expression_in_exprPair865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprPair_in_exprMap890 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000040L});
    public static final BitSet FOLLOW_Comma_in_exprMap893 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_exprPair_in_exprMap895 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000040L});
    public static final BitSet FOLLOW_condExpr_in_expression921 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orExpr_in_condExpr936 = new BitSet(new long[]{0x000001C000000002L,0x0000000000000080L});
    public static final BitSet FOLLOW_QMark_in_condExpr951 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_expression_in_condExpr955 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_Colon_in_condExpr957 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_expression_in_condExpr961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_In_in_condExpr984 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_expression_in_condExpr986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RangeE_in_condExpr1031 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_expression_in_condExpr1033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Range_in_condExpr1068 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_expression_in_condExpr1070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_andExpr_in_orExpr1135 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_Or_in_orExpr1138 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_andExpr_in_orExpr1141 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_equExpr_in_andExpr1157 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_And_in_andExpr1160 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_equExpr_in_andExpr1163 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_relExpr_in_equExpr1179 = new BitSet(new long[]{0x0006000000000002L});
    public static final BitSet FOLLOW_set_in_equExpr1182 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_relExpr_in_equExpr1191 = new BitSet(new long[]{0x0006000000000002L});
    public static final BitSet FOLLOW_addExpr_in_relExpr1207 = new BitSet(new long[]{0x0198000000000002L});
    public static final BitSet FOLLOW_set_in_relExpr1210 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_addExpr_in_relExpr1227 = new BitSet(new long[]{0x0198000000000002L});
    public static final BitSet FOLLOW_mulExpr_in_addExpr1243 = new BitSet(new long[]{0x0600000000000002L});
    public static final BitSet FOLLOW_set_in_addExpr1246 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_mulExpr_in_addExpr1255 = new BitSet(new long[]{0x0600000000000002L});
    public static final BitSet FOLLOW_powExpr_in_mulExpr1271 = new BitSet(new long[]{0x3800000000000002L});
    public static final BitSet FOLLOW_set_in_mulExpr1274 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_powExpr_in_mulExpr1287 = new BitSet(new long[]{0x3800000000000002L});
    public static final BitSet FOLLOW_unaryExpr_in_powExpr1303 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_Pow_in_powExpr1306 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_unaryExpr_in_powExpr1309 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_Subtract_in_unaryExpr1327 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Excl_in_unaryExpr1344 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Integer_in_atom1375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Number_in_atom1382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Bool_in_atom1389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Date_in_atom1396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Null_in_atom1403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_atom1410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OBrace_in_list1424 = new BitSet(new long[]{0xC4407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_exprList_in_list1426 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_CBrace_in_list1429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OBrace_in_map1452 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004104L});
    public static final BitSet FOLLOW_Colon_in_map1455 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_exprMap_in_map1459 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_CBrace_in_map1462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_lookup1485 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_indexes_in_lookup1487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_in_lookup1512 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_indexes_in_lookup1514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_map_in_lookup1547 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_indexes_in_lookup1549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_lookup1585 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_indexes_in_lookup1587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_anyIdentifier_in_lookup1614 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_indexes_in_lookup1616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_String_in_lookup1642 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_indexes_in_lookup1644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OParen_in_lookup1675 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_expression_in_lookup1677 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_CParen_in_lookup1679 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_indexes_in_lookup1681 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OBracket_in_indexes1708 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_expression_in_indexes1710 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_CBracket_in_indexes1712 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_set_in_anyIdentifier0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_synpred11_PlazmaScript333 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_indexes_in_synpred11_PlazmaScript335 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_Assign_in_synpred11_PlazmaScript338 = new BitSet(new long[]{0x44407E01F0000000L,0x0000000000004004L});
    public static final BitSet FOLLOW_expression_in_synpred11_PlazmaScript340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_synpred53_PlazmaScript1485 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_indexes_in_synpred53_PlazmaScript1487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_in_synpred55_PlazmaScript1512 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_indexes_in_synpred55_PlazmaScript1514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_map_in_synpred57_PlazmaScript1547 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_indexes_in_synpred57_PlazmaScript1549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_synpred59_PlazmaScript1585 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_indexes_in_synpred59_PlazmaScript1587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_anyIdentifier_in_synpred61_PlazmaScript1614 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_indexes_in_synpred61_PlazmaScript1616 = new BitSet(new long[]{0x0000000000000002L});

}