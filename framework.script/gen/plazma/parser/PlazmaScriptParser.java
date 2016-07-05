// $ANTLR 3.2 Sep 23, 2009 12:02:23 grammar/PlazmaScript.g 2016-07-05 11:24:20

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BLOCK", "RETURN", "STATEMENTS", "ASSIGNMENT", "FUNC_CALL", "EXP", "EXP_PAIR", "EXP_MAP", "EXP_LIST", "ID_LIST", "IF", "TERNARY", "UNARY_MIN", "NEGATE", "FUNCTION", "INDEXES", "INDEX", "ATTRIBUTE", "CALL", "TAIL", "TAILS", "MAP", "LIST", "LOOKUP", "BREAK", "CONTINUE", "Return", "Break", "Continue", "Identifier", "Println", "Print", "Assert", "Date", "List", "If", "Else", "Var", "Def", "For", "While", "In", "RangeE", "Range", "Integer", "Number", "Bool", "Null", "String", "XorWord", "Or", "BitOr", "OrWord", "And", "BitAnd", "AndWord", "Equals", "NEquals", "GTEquals", "LTEquals", "Pow", "Not", "NotWord", "GT", "LT", "Add", "Subtract", "Multiply", "Divide", "Modulus", "OBrace", "CBrace", "OBracket", "CBracket", "OParen", "CParen", "SColon", "Assign", "Comma", "QMark", "Colon", "Int", "Digit", "ContextIdentifier", "Comment", "Space", "YYYY", "MM", "DD", "'.'"
    };
    public static final int FUNCTION=18;
    public static final int LT=68;
    public static final int TERNARY=15;
    public static final int EXP_LIST=12;
    public static final int Date=37;
    public static final int DD=92;
    public static final int EOF=-1;
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
    public static final int RETURN=5;
    public static final int CBrace=75;
    public static final int Null=51;
    public static final int OrWord=56;
    public static final int CBracket=77;
    public static final int ContextIdentifier=87;
    public static final int Println=34;
    public static final int Bool=50;
    public static final int Modulus=73;
    public static final int AndWord=59;
    public static final int Colon=84;
    public static final int LIST=26;
    public static final int Def=42;
    public static final int LOOKUP=27;
    public static final int RangeE=46;
    public static final int Range=47;
    public static final int Break=31;
    public static final int BitOr=55;
    public static final int STATEMENTS=6;
    public static final int GT=67;
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
    // grammar/PlazmaScript.g:68:1: parse : block EOF -> block ;
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
            // grammar/PlazmaScript.g:69:3: ( block EOF -> block )
            // grammar/PlazmaScript.g:69:6: block EOF
            {
            pushFollow(FOLLOW_block_in_parse203);
            block1=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block1.getTree());
            EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_parse205); if (state.failed) return retval; 
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
            // 69:16: -> block
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
    // grammar/PlazmaScript.g:72:1: block : ( statement | functionDecl )* ( Return expression ';' )? -> ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) ) ;
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
            // grammar/PlazmaScript.g:73:3: ( ( statement | functionDecl )* ( Return expression ';' )? -> ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) ) )
            // grammar/PlazmaScript.g:73:6: ( statement | functionDecl )* ( Return expression ';' )?
            {
            // grammar/PlazmaScript.g:73:6: ( statement | functionDecl )*
            loop1:
            do {
                int alt1=3;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=Break && LA1_0<=If)||LA1_0==Var||(LA1_0>=For && LA1_0<=While)||LA1_0==String||LA1_0==OBrace||LA1_0==OBracket||LA1_0==OParen||LA1_0==ContextIdentifier) ) {
                    alt1=1;
                }
                else if ( (LA1_0==Def) ) {
                    alt1=2;
                }


                switch (alt1) {
            	case 1 :
            	    // grammar/PlazmaScript.g:73:7: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_block224);
            	    statement3=statement();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_statement.add(statement3.getTree());

            	    }
            	    break;
            	case 2 :
            	    // grammar/PlazmaScript.g:73:19: functionDecl
            	    {
            	    pushFollow(FOLLOW_functionDecl_in_block228);
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

            // grammar/PlazmaScript.g:73:34: ( Return expression ';' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==Return) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // grammar/PlazmaScript.g:73:35: Return expression ';'
                    {
                    Return5=(Token)match(input,Return,FOLLOW_Return_in_block233); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Return.add(Return5);

                    pushFollow(FOLLOW_expression_in_block235);
                    expression6=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression6.getTree());
                    char_literal7=(Token)match(input,SColon,FOLLOW_SColon_in_block237); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SColon.add(char_literal7);


                    }
                    break;

            }



            // AST REWRITE
            // elements: statement, expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 74:6: -> ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) )
            {
                // grammar/PlazmaScript.g:74:9: ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BLOCK, "BLOCK"), root_1);

                // grammar/PlazmaScript.g:74:17: ^( STATEMENTS ( statement )* )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(STATEMENTS, "STATEMENTS"), root_2);

                // grammar/PlazmaScript.g:74:30: ( statement )*
                while ( stream_statement.hasNext() ) {
                    adaptor.addChild(root_2, stream_statement.nextTree());

                }
                stream_statement.reset();

                adaptor.addChild(root_1, root_2);
                }
                // grammar/PlazmaScript.g:74:42: ^( RETURN ( expression )? )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(RETURN, "RETURN"), root_2);

                // grammar/PlazmaScript.g:74:51: ( expression )?
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
    // grammar/PlazmaScript.g:77:1: statement : ( assignment ';' -> assignment | functionCall ';' -> functionCall | lookup ';' -> lookup | ifStatement | forStatement | whileStatement | Break ';' -> Break | Continue ';' -> Continue );
    public final PlazmaScriptParser.statement_return statement() throws RecognitionException {
        PlazmaScriptParser.statement_return retval = new PlazmaScriptParser.statement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal9=null;
        Token char_literal11=null;
        Token char_literal13=null;
        Token Break17=null;
        Token char_literal18=null;
        Token Continue19=null;
        Token char_literal20=null;
        PlazmaScriptParser.assignment_return assignment8 = null;

        PlazmaScriptParser.functionCall_return functionCall10 = null;

        PlazmaScriptParser.lookup_return lookup12 = null;

        PlazmaScriptParser.ifStatement_return ifStatement14 = null;

        PlazmaScriptParser.forStatement_return forStatement15 = null;

        PlazmaScriptParser.whileStatement_return whileStatement16 = null;


        Object char_literal9_tree=null;
        Object char_literal11_tree=null;
        Object char_literal13_tree=null;
        Object Break17_tree=null;
        Object char_literal18_tree=null;
        Object Continue19_tree=null;
        Object char_literal20_tree=null;
        RewriteRuleTokenStream stream_Break=new RewriteRuleTokenStream(adaptor,"token Break");
        RewriteRuleTokenStream stream_Continue=new RewriteRuleTokenStream(adaptor,"token Continue");
        RewriteRuleTokenStream stream_SColon=new RewriteRuleTokenStream(adaptor,"token SColon");
        RewriteRuleSubtreeStream stream_functionCall=new RewriteRuleSubtreeStream(adaptor,"rule functionCall");
        RewriteRuleSubtreeStream stream_assignment=new RewriteRuleSubtreeStream(adaptor,"rule assignment");
        RewriteRuleSubtreeStream stream_lookup=new RewriteRuleSubtreeStream(adaptor,"rule lookup");
        try {
            // grammar/PlazmaScript.g:78:3: ( assignment ';' -> assignment | functionCall ';' -> functionCall | lookup ';' -> lookup | ifStatement | forStatement | whileStatement | Break ';' -> Break | Continue ';' -> Continue )
            int alt3=8;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // grammar/PlazmaScript.g:78:6: assignment ';'
                    {
                    pushFollow(FOLLOW_assignment_in_statement279);
                    assignment8=assignment();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_assignment.add(assignment8.getTree());
                    char_literal9=(Token)match(input,SColon,FOLLOW_SColon_in_statement281); if (state.failed) return retval; 
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
                    // 78:23: -> assignment
                    {
                        adaptor.addChild(root_0, stream_assignment.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:79:6: functionCall ';'
                    {
                    pushFollow(FOLLOW_functionCall_in_statement294);
                    functionCall10=functionCall();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_functionCall.add(functionCall10.getTree());
                    char_literal11=(Token)match(input,SColon,FOLLOW_SColon_in_statement296); if (state.failed) return retval; 
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
                    // 79:23: -> functionCall
                    {
                        adaptor.addChild(root_0, stream_functionCall.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // grammar/PlazmaScript.g:80:6: lookup ';'
                    {
                    pushFollow(FOLLOW_lookup_in_statement307);
                    lookup12=lookup();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_lookup.add(lookup12.getTree());
                    char_literal13=(Token)match(input,SColon,FOLLOW_SColon_in_statement309); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SColon.add(char_literal13);



                    // AST REWRITE
                    // elements: lookup
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 80:17: -> lookup
                    {
                        adaptor.addChild(root_0, stream_lookup.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 4 :
                    // grammar/PlazmaScript.g:81:6: ifStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_ifStatement_in_statement353);
                    ifStatement14=ifStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ifStatement14.getTree());

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScript.g:82:6: forStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_forStatement_in_statement360);
                    forStatement15=forStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, forStatement15.getTree());

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScript.g:83:6: whileStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_whileStatement_in_statement367);
                    whileStatement16=whileStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, whileStatement16.getTree());

                    }
                    break;
                case 7 :
                    // grammar/PlazmaScript.g:84:6: Break ';'
                    {
                    Break17=(Token)match(input,Break,FOLLOW_Break_in_statement374); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Break.add(Break17);

                    char_literal18=(Token)match(input,SColon,FOLLOW_SColon_in_statement376); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SColon.add(char_literal18);



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
                    // 84:16: -> Break
                    {
                        adaptor.addChild(root_0, stream_Break.nextNode());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 8 :
                    // grammar/PlazmaScript.g:85:6: Continue ';'
                    {
                    Continue19=(Token)match(input,Continue,FOLLOW_Continue_in_statement387); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Continue.add(Continue19);

                    char_literal20=(Token)match(input,SColon,FOLLOW_SColon_in_statement389); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SColon.add(char_literal20);



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
                    // 85:19: -> Continue
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
    // grammar/PlazmaScript.g:89:1: assignment : ( ( variableDef )? Identifier ( indexes )? '=' expression -> ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression ) | ( variableDef )? anyIdentifier ( indexes )? '=' expression -> ^( ASSIGNMENT ( variableDef )? ( indexes )? expression ) );
    public final PlazmaScriptParser.assignment_return assignment() throws RecognitionException {
        PlazmaScriptParser.assignment_return retval = new PlazmaScriptParser.assignment_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Identifier22=null;
        Token char_literal24=null;
        Token char_literal29=null;
        PlazmaScriptParser.variableDef_return variableDef21 = null;

        PlazmaScriptParser.indexes_return indexes23 = null;

        PlazmaScriptParser.expression_return expression25 = null;

        PlazmaScriptParser.variableDef_return variableDef26 = null;

        PlazmaScriptParser.anyIdentifier_return anyIdentifier27 = null;

        PlazmaScriptParser.indexes_return indexes28 = null;

        PlazmaScriptParser.expression_return expression30 = null;


        Object Identifier22_tree=null;
        Object char_literal24_tree=null;
        Object char_literal29_tree=null;
        RewriteRuleTokenStream stream_Assign=new RewriteRuleTokenStream(adaptor,"token Assign");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_variableDef=new RewriteRuleSubtreeStream(adaptor,"rule variableDef");
        RewriteRuleSubtreeStream stream_anyIdentifier=new RewriteRuleSubtreeStream(adaptor,"rule anyIdentifier");
        RewriteRuleSubtreeStream stream_indexes=new RewriteRuleSubtreeStream(adaptor,"rule indexes");
        try {
            // grammar/PlazmaScript.g:90:3: ( ( variableDef )? Identifier ( indexes )? '=' expression -> ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression ) | ( variableDef )? anyIdentifier ( indexes )? '=' expression -> ^( ASSIGNMENT ( variableDef )? ( indexes )? expression ) )
            int alt8=2;
            switch ( input.LA(1) ) {
            case Var:
                {
                int LA8_1 = input.LA(2);

                if ( (synpred13_PlazmaScript()) ) {
                    alt8=1;
                }
                else if ( (true) ) {
                    alt8=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 1, input);

                    throw nvae;
                }
                }
                break;
            case Identifier:
                {
                int LA8_2 = input.LA(2);

                if ( (synpred13_PlazmaScript()) ) {
                    alt8=1;
                }
                else if ( (true) ) {
                    alt8=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 2, input);

                    throw nvae;
                }
                }
                break;
            case ContextIdentifier:
                {
                alt8=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // grammar/PlazmaScript.g:90:6: ( variableDef )? Identifier ( indexes )? '=' expression
                    {
                    // grammar/PlazmaScript.g:90:6: ( variableDef )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==Var) ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: variableDef
                            {
                            pushFollow(FOLLOW_variableDef_in_assignment410);
                            variableDef21=variableDef();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_variableDef.add(variableDef21.getTree());

                            }
                            break;

                    }

                    Identifier22=(Token)match(input,Identifier,FOLLOW_Identifier_in_assignment413); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier22);

                    // grammar/PlazmaScript.g:90:30: ( indexes )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==OBracket||LA5_0==93) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_assignment415);
                            indexes23=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes23.getTree());

                            }
                            break;

                    }

                    char_literal24=(Token)match(input,Assign,FOLLOW_Assign_in_assignment418); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Assign.add(char_literal24);

                    pushFollow(FOLLOW_expression_in_assignment420);
                    expression25=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression25.getTree());


                    // AST REWRITE
                    // elements: expression, Identifier, indexes, variableDef
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 90:54: -> ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression )
                    {
                        // grammar/PlazmaScript.g:90:57: ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ASSIGNMENT, "ASSIGNMENT"), root_1);

                        // grammar/PlazmaScript.g:90:70: ( variableDef )?
                        if ( stream_variableDef.hasNext() ) {
                            adaptor.addChild(root_1, stream_variableDef.nextTree());

                        }
                        stream_variableDef.reset();
                        adaptor.addChild(root_1, stream_Identifier.nextNode());
                        // grammar/PlazmaScript.g:90:94: ( indexes )?
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
                    // grammar/PlazmaScript.g:91:6: ( variableDef )? anyIdentifier ( indexes )? '=' expression
                    {
                    // grammar/PlazmaScript.g:91:6: ( variableDef )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==Var) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: variableDef
                            {
                            pushFollow(FOLLOW_variableDef_in_assignment443);
                            variableDef26=variableDef();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_variableDef.add(variableDef26.getTree());

                            }
                            break;

                    }

                    pushFollow(FOLLOW_anyIdentifier_in_assignment446);
                    anyIdentifier27=anyIdentifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_anyIdentifier.add(anyIdentifier27.getTree());
                    // grammar/PlazmaScript.g:91:33: ( indexes )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==OBracket||LA7_0==93) ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_assignment448);
                            indexes28=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes28.getTree());

                            }
                            break;

                    }

                    char_literal29=(Token)match(input,Assign,FOLLOW_Assign_in_assignment451); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Assign.add(char_literal29);

                    pushFollow(FOLLOW_expression_in_assignment453);
                    expression30=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression30.getTree());


                    // AST REWRITE
                    // elements: expression, indexes, variableDef
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 91:57: -> ^( ASSIGNMENT ( variableDef )? ( indexes )? expression )
                    {
                        // grammar/PlazmaScript.g:91:60: ^( ASSIGNMENT ( variableDef )? ( indexes )? expression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ASSIGNMENT, "ASSIGNMENT"), root_1);

                        // grammar/PlazmaScript.g:91:73: ( variableDef )?
                        if ( stream_variableDef.hasNext() ) {
                            adaptor.addChild(root_1, stream_variableDef.nextTree());

                        }
                        stream_variableDef.reset();
                        adaptor.addChild(root_1, new CommonTree(new CommonToken(Identifier, (anyIdentifier27!=null?input.toString(anyIdentifier27.start,anyIdentifier27.stop):null))));
                        // grammar/PlazmaScript.g:91:153: ( indexes )?
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
    // grammar/PlazmaScript.g:94:1: functionCall : ( Identifier '(' ( exprList )? ')' -> ^( FUNC_CALL Identifier ( exprList )? ) | Println '(' ( expression )? ')' -> ^( FUNC_CALL Println ( expression )? ) | Print '(' expression ')' -> ^( FUNC_CALL Print expression ) | Assert '(' expression ')' -> ^( FUNC_CALL Assert expression ) | Date '(' ( exprList )? ')' -> ^( FUNC_CALL Date ( exprList )? ) | List '(' ( exprList )? ')' -> ^( FUNC_CALL List ( exprList )? ) );
    public final PlazmaScriptParser.functionCall_return functionCall() throws RecognitionException {
        PlazmaScriptParser.functionCall_return retval = new PlazmaScriptParser.functionCall_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Identifier31=null;
        Token char_literal32=null;
        Token char_literal34=null;
        Token Println35=null;
        Token char_literal36=null;
        Token char_literal38=null;
        Token Print39=null;
        Token char_literal40=null;
        Token char_literal42=null;
        Token Assert43=null;
        Token char_literal44=null;
        Token char_literal46=null;
        Token Date47=null;
        Token char_literal48=null;
        Token char_literal50=null;
        Token List51=null;
        Token char_literal52=null;
        Token char_literal54=null;
        PlazmaScriptParser.exprList_return exprList33 = null;

        PlazmaScriptParser.expression_return expression37 = null;

        PlazmaScriptParser.expression_return expression41 = null;

        PlazmaScriptParser.expression_return expression45 = null;

        PlazmaScriptParser.exprList_return exprList49 = null;

        PlazmaScriptParser.exprList_return exprList53 = null;


        Object Identifier31_tree=null;
        Object char_literal32_tree=null;
        Object char_literal34_tree=null;
        Object Println35_tree=null;
        Object char_literal36_tree=null;
        Object char_literal38_tree=null;
        Object Print39_tree=null;
        Object char_literal40_tree=null;
        Object char_literal42_tree=null;
        Object Assert43_tree=null;
        Object char_literal44_tree=null;
        Object char_literal46_tree=null;
        Object Date47_tree=null;
        Object char_literal48_tree=null;
        Object char_literal50_tree=null;
        Object List51_tree=null;
        Object char_literal52_tree=null;
        Object char_literal54_tree=null;
        RewriteRuleTokenStream stream_Println=new RewriteRuleTokenStream(adaptor,"token Println");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_Date=new RewriteRuleTokenStream(adaptor,"token Date");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_List=new RewriteRuleTokenStream(adaptor,"token List");
        RewriteRuleTokenStream stream_Assert=new RewriteRuleTokenStream(adaptor,"token Assert");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
        RewriteRuleTokenStream stream_Print=new RewriteRuleTokenStream(adaptor,"token Print");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_exprList=new RewriteRuleSubtreeStream(adaptor,"rule exprList");
        try {
            // grammar/PlazmaScript.g:95:3: ( Identifier '(' ( exprList )? ')' -> ^( FUNC_CALL Identifier ( exprList )? ) | Println '(' ( expression )? ')' -> ^( FUNC_CALL Println ( expression )? ) | Print '(' expression ')' -> ^( FUNC_CALL Print expression ) | Assert '(' expression ')' -> ^( FUNC_CALL Assert expression ) | Date '(' ( exprList )? ')' -> ^( FUNC_CALL Date ( exprList )? ) | List '(' ( exprList )? ')' -> ^( FUNC_CALL List ( exprList )? ) )
            int alt13=6;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                alt13=1;
                }
                break;
            case Println:
                {
                alt13=2;
                }
                break;
            case Print:
                {
                alt13=3;
                }
                break;
            case Assert:
                {
                alt13=4;
                }
                break;
            case Date:
                {
                alt13=5;
                }
                break;
            case List:
                {
                alt13=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }

            switch (alt13) {
                case 1 :
                    // grammar/PlazmaScript.g:95:6: Identifier '(' ( exprList )? ')'
                    {
                    Identifier31=(Token)match(input,Identifier,FOLLOW_Identifier_in_functionCall483); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier31);

                    char_literal32=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall485); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal32);

                    // grammar/PlazmaScript.g:95:21: ( exprList )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( ((LA9_0>=Identifier && LA9_0<=List)||(LA9_0>=Integer && LA9_0<=String)||(LA9_0>=Not && LA9_0<=NotWord)||LA9_0==Subtract||LA9_0==OBrace||LA9_0==OBracket||LA9_0==OParen||LA9_0==ContextIdentifier) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall487);
                            exprList33=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList33.getTree());

                            }
                            break;

                    }

                    char_literal34=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall490); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal34);



                    // AST REWRITE
                    // elements: exprList, Identifier
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 95:35: -> ^( FUNC_CALL Identifier ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:95:38: ^( FUNC_CALL Identifier ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Identifier.nextNode());
                        // grammar/PlazmaScript.g:95:61: ( exprList )?
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
                    // grammar/PlazmaScript.g:96:6: Println '(' ( expression )? ')'
                    {
                    Println35=(Token)match(input,Println,FOLLOW_Println_in_functionCall508); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Println.add(Println35);

                    char_literal36=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall510); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal36);

                    // grammar/PlazmaScript.g:96:18: ( expression )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( ((LA10_0>=Identifier && LA10_0<=List)||(LA10_0>=Integer && LA10_0<=String)||(LA10_0>=Not && LA10_0<=NotWord)||LA10_0==Subtract||LA10_0==OBrace||LA10_0==OBracket||LA10_0==OParen||LA10_0==ContextIdentifier) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: expression
                            {
                            pushFollow(FOLLOW_expression_in_functionCall512);
                            expression37=expression();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_expression.add(expression37.getTree());

                            }
                            break;

                    }

                    char_literal38=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall515); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal38);



                    // AST REWRITE
                    // elements: Println, expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 96:35: -> ^( FUNC_CALL Println ( expression )? )
                    {
                        // grammar/PlazmaScript.g:96:38: ^( FUNC_CALL Println ( expression )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Println.nextNode());
                        // grammar/PlazmaScript.g:96:58: ( expression )?
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
                    // grammar/PlazmaScript.g:97:6: Print '(' expression ')'
                    {
                    Print39=(Token)match(input,Print,FOLLOW_Print_in_functionCall534); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Print.add(Print39);

                    char_literal40=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall536); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal40);

                    pushFollow(FOLLOW_expression_in_functionCall538);
                    expression41=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression41.getTree());
                    char_literal42=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall540); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal42);



                    // AST REWRITE
                    // elements: expression, Print
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 97:35: -> ^( FUNC_CALL Print expression )
                    {
                        // grammar/PlazmaScript.g:97:38: ^( FUNC_CALL Print expression )
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
                    // grammar/PlazmaScript.g:98:6: Assert '(' expression ')'
                    {
                    Assert43=(Token)match(input,Assert,FOLLOW_Assert_in_functionCall561); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Assert.add(Assert43);

                    char_literal44=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall563); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal44);

                    pushFollow(FOLLOW_expression_in_functionCall565);
                    expression45=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression45.getTree());
                    char_literal46=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall567); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal46);



                    // AST REWRITE
                    // elements: Assert, expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 98:35: -> ^( FUNC_CALL Assert expression )
                    {
                        // grammar/PlazmaScript.g:98:38: ^( FUNC_CALL Assert expression )
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
                    // grammar/PlazmaScript.g:99:6: Date '(' ( exprList )? ')'
                    {
                    Date47=(Token)match(input,Date,FOLLOW_Date_in_functionCall587); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Date.add(Date47);

                    char_literal48=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall589); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal48);

                    // grammar/PlazmaScript.g:99:15: ( exprList )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( ((LA11_0>=Identifier && LA11_0<=List)||(LA11_0>=Integer && LA11_0<=String)||(LA11_0>=Not && LA11_0<=NotWord)||LA11_0==Subtract||LA11_0==OBrace||LA11_0==OBracket||LA11_0==OParen||LA11_0==ContextIdentifier) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall591);
                            exprList49=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList49.getTree());

                            }
                            break;

                    }

                    char_literal50=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall594); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal50);



                    // AST REWRITE
                    // elements: exprList, Date
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 99:34: -> ^( FUNC_CALL Date ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:99:37: ^( FUNC_CALL Date ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Date.nextNode());
                        // grammar/PlazmaScript.g:99:54: ( exprList )?
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
                case 6 :
                    // grammar/PlazmaScript.g:100:6: List '(' ( exprList )? ')'
                    {
                    List51=(Token)match(input,List,FOLLOW_List_in_functionCall617); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_List.add(List51);

                    char_literal52=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall619); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal52);

                    // grammar/PlazmaScript.g:100:15: ( exprList )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( ((LA12_0>=Identifier && LA12_0<=List)||(LA12_0>=Integer && LA12_0<=String)||(LA12_0>=Not && LA12_0<=NotWord)||LA12_0==Subtract||LA12_0==OBrace||LA12_0==OBracket||LA12_0==OParen||LA12_0==ContextIdentifier) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall621);
                            exprList53=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList53.getTree());

                            }
                            break;

                    }

                    char_literal54=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall624); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal54);



                    // AST REWRITE
                    // elements: exprList, List
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 100:34: -> ^( FUNC_CALL List ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:100:37: ^( FUNC_CALL List ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_List.nextNode());
                        // grammar/PlazmaScript.g:100:54: ( exprList )?
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
    // grammar/PlazmaScript.g:107:1: ifStatement : ifStat ( elseIfStat )* ( elseStat )? -> ^( IF ifStat ( elseIfStat )* ( elseStat )? ) ;
    public final PlazmaScriptParser.ifStatement_return ifStatement() throws RecognitionException {
        PlazmaScriptParser.ifStatement_return retval = new PlazmaScriptParser.ifStatement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        PlazmaScriptParser.ifStat_return ifStat55 = null;

        PlazmaScriptParser.elseIfStat_return elseIfStat56 = null;

        PlazmaScriptParser.elseStat_return elseStat57 = null;


        RewriteRuleSubtreeStream stream_elseIfStat=new RewriteRuleSubtreeStream(adaptor,"rule elseIfStat");
        RewriteRuleSubtreeStream stream_ifStat=new RewriteRuleSubtreeStream(adaptor,"rule ifStat");
        RewriteRuleSubtreeStream stream_elseStat=new RewriteRuleSubtreeStream(adaptor,"rule elseStat");
        try {
            // grammar/PlazmaScript.g:108:3: ( ifStat ( elseIfStat )* ( elseStat )? -> ^( IF ifStat ( elseIfStat )* ( elseStat )? ) )
            // grammar/PlazmaScript.g:108:6: ifStat ( elseIfStat )* ( elseStat )?
            {
            pushFollow(FOLLOW_ifStat_in_ifStatement662);
            ifStat55=ifStat();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_ifStat.add(ifStat55.getTree());
            // grammar/PlazmaScript.g:108:13: ( elseIfStat )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==Else) ) {
                    int LA14_1 = input.LA(2);

                    if ( (LA14_1==If) ) {
                        alt14=1;
                    }


                }


                switch (alt14) {
            	case 1 :
            	    // grammar/PlazmaScript.g:0:0: elseIfStat
            	    {
            	    pushFollow(FOLLOW_elseIfStat_in_ifStatement664);
            	    elseIfStat56=elseIfStat();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_elseIfStat.add(elseIfStat56.getTree());

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            // grammar/PlazmaScript.g:108:25: ( elseStat )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==Else) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // grammar/PlazmaScript.g:0:0: elseStat
                    {
                    pushFollow(FOLLOW_elseStat_in_ifStatement667);
                    elseStat57=elseStat();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_elseStat.add(elseStat57.getTree());

                    }
                    break;

            }



            // AST REWRITE
            // elements: elseStat, ifStat, elseIfStat
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 108:35: -> ^( IF ifStat ( elseIfStat )* ( elseStat )? )
            {
                // grammar/PlazmaScript.g:108:38: ^( IF ifStat ( elseIfStat )* ( elseStat )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(IF, "IF"), root_1);

                adaptor.addChild(root_1, stream_ifStat.nextTree());
                // grammar/PlazmaScript.g:108:50: ( elseIfStat )*
                while ( stream_elseIfStat.hasNext() ) {
                    adaptor.addChild(root_1, stream_elseIfStat.nextTree());

                }
                stream_elseIfStat.reset();
                // grammar/PlazmaScript.g:108:62: ( elseStat )?
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
    // grammar/PlazmaScript.g:111:1: ifStat : If '(' expression ')' '{' block '}' -> ^( EXP expression block ) ;
    public final PlazmaScriptParser.ifStat_return ifStat() throws RecognitionException {
        PlazmaScriptParser.ifStat_return retval = new PlazmaScriptParser.ifStat_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token If58=null;
        Token char_literal59=null;
        Token char_literal61=null;
        Token char_literal62=null;
        Token char_literal64=null;
        PlazmaScriptParser.expression_return expression60 = null;

        PlazmaScriptParser.block_return block63 = null;


        Object If58_tree=null;
        Object char_literal59_tree=null;
        Object char_literal61_tree=null;
        Object char_literal62_tree=null;
        Object char_literal64_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_If=new RewriteRuleTokenStream(adaptor,"token If");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:112:3: ( If '(' expression ')' '{' block '}' -> ^( EXP expression block ) )
            // grammar/PlazmaScript.g:112:6: If '(' expression ')' '{' block '}'
            {
            If58=(Token)match(input,If,FOLLOW_If_in_ifStat696); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_If.add(If58);

            char_literal59=(Token)match(input,OParen,FOLLOW_OParen_in_ifStat698); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal59);

            pushFollow(FOLLOW_expression_in_ifStat700);
            expression60=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression60.getTree());
            char_literal61=(Token)match(input,CParen,FOLLOW_CParen_in_ifStat702); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal61);

            char_literal62=(Token)match(input,OBrace,FOLLOW_OBrace_in_ifStat704); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal62);

            pushFollow(FOLLOW_block_in_ifStat706);
            block63=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block63.getTree());
            char_literal64=(Token)match(input,CBrace,FOLLOW_CBrace_in_ifStat708); if (state.failed) return retval; 
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
            // 112:42: -> ^( EXP expression block )
            {
                // grammar/PlazmaScript.g:112:45: ^( EXP expression block )
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
    // grammar/PlazmaScript.g:115:1: elseIfStat : Else If '(' expression ')' '{' block '}' -> ^( EXP expression block ) ;
    public final PlazmaScriptParser.elseIfStat_return elseIfStat() throws RecognitionException {
        PlazmaScriptParser.elseIfStat_return retval = new PlazmaScriptParser.elseIfStat_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Else65=null;
        Token If66=null;
        Token char_literal67=null;
        Token char_literal69=null;
        Token char_literal70=null;
        Token char_literal72=null;
        PlazmaScriptParser.expression_return expression68 = null;

        PlazmaScriptParser.block_return block71 = null;


        Object Else65_tree=null;
        Object If66_tree=null;
        Object char_literal67_tree=null;
        Object char_literal69_tree=null;
        Object char_literal70_tree=null;
        Object char_literal72_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_Else=new RewriteRuleTokenStream(adaptor,"token Else");
        RewriteRuleTokenStream stream_If=new RewriteRuleTokenStream(adaptor,"token If");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:116:3: ( Else If '(' expression ')' '{' block '}' -> ^( EXP expression block ) )
            // grammar/PlazmaScript.g:116:6: Else If '(' expression ')' '{' block '}'
            {
            Else65=(Token)match(input,Else,FOLLOW_Else_in_elseIfStat732); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Else.add(Else65);

            If66=(Token)match(input,If,FOLLOW_If_in_elseIfStat734); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_If.add(If66);

            char_literal67=(Token)match(input,OParen,FOLLOW_OParen_in_elseIfStat736); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal67);

            pushFollow(FOLLOW_expression_in_elseIfStat738);
            expression68=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression68.getTree());
            char_literal69=(Token)match(input,CParen,FOLLOW_CParen_in_elseIfStat740); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal69);

            char_literal70=(Token)match(input,OBrace,FOLLOW_OBrace_in_elseIfStat742); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal70);

            pushFollow(FOLLOW_block_in_elseIfStat744);
            block71=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block71.getTree());
            char_literal72=(Token)match(input,CBrace,FOLLOW_CBrace_in_elseIfStat746); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal72);



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
            // 116:47: -> ^( EXP expression block )
            {
                // grammar/PlazmaScript.g:116:50: ^( EXP expression block )
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
    // grammar/PlazmaScript.g:119:1: elseStat : Else '{' block '}' -> ^( EXP block ) ;
    public final PlazmaScriptParser.elseStat_return elseStat() throws RecognitionException {
        PlazmaScriptParser.elseStat_return retval = new PlazmaScriptParser.elseStat_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Else73=null;
        Token char_literal74=null;
        Token char_literal76=null;
        PlazmaScriptParser.block_return block75 = null;


        Object Else73_tree=null;
        Object char_literal74_tree=null;
        Object char_literal76_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_Else=new RewriteRuleTokenStream(adaptor,"token Else");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:120:3: ( Else '{' block '}' -> ^( EXP block ) )
            // grammar/PlazmaScript.g:120:6: Else '{' block '}'
            {
            Else73=(Token)match(input,Else,FOLLOW_Else_in_elseStat770); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Else.add(Else73);

            char_literal74=(Token)match(input,OBrace,FOLLOW_OBrace_in_elseStat772); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal74);

            pushFollow(FOLLOW_block_in_elseStat774);
            block75=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block75.getTree());
            char_literal76=(Token)match(input,CBrace,FOLLOW_CBrace_in_elseStat776); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal76);



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
            // 120:25: -> ^( EXP block )
            {
                // grammar/PlazmaScript.g:120:28: ^( EXP block )
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

    public static class variableDef_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "variableDef"
    // grammar/PlazmaScript.g:123:1: variableDef : Var ;
    public final PlazmaScriptParser.variableDef_return variableDef() throws RecognitionException {
        PlazmaScriptParser.variableDef_return retval = new PlazmaScriptParser.variableDef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Var77=null;

        Object Var77_tree=null;

        try {
            // grammar/PlazmaScript.g:124:2: ( Var )
            // grammar/PlazmaScript.g:124:4: Var
            {
            root_0 = (Object)adaptor.nil();

            Var77=(Token)match(input,Var,FOLLOW_Var_in_variableDef796); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Var77_tree = (Object)adaptor.create(Var77);
            adaptor.addChild(root_0, Var77_tree);
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
    // $ANTLR end "variableDef"

    public static class functionDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "functionDecl"
    // grammar/PlazmaScript.g:141:1: functionDecl : Def Identifier '(' ( idList )? ')' '{' block '}' ;
    public final PlazmaScriptParser.functionDecl_return functionDecl() throws RecognitionException {
        PlazmaScriptParser.functionDecl_return retval = new PlazmaScriptParser.functionDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Def78=null;
        Token Identifier79=null;
        Token char_literal80=null;
        Token char_literal82=null;
        Token char_literal83=null;
        Token char_literal85=null;
        PlazmaScriptParser.idList_return idList81 = null;

        PlazmaScriptParser.block_return block84 = null;


        Object Def78_tree=null;
        Object Identifier79_tree=null;
        Object char_literal80_tree=null;
        Object char_literal82_tree=null;
        Object char_literal83_tree=null;
        Object char_literal85_tree=null;

        try {
            // grammar/PlazmaScript.g:142:3: ( Def Identifier '(' ( idList )? ')' '{' block '}' )
            // grammar/PlazmaScript.g:142:6: Def Identifier '(' ( idList )? ')' '{' block '}'
            {
            root_0 = (Object)adaptor.nil();

            Def78=(Token)match(input,Def,FOLLOW_Def_in_functionDecl828); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Def78_tree = (Object)adaptor.create(Def78);
            adaptor.addChild(root_0, Def78_tree);
            }
            Identifier79=(Token)match(input,Identifier,FOLLOW_Identifier_in_functionDecl830); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier79_tree = (Object)adaptor.create(Identifier79);
            adaptor.addChild(root_0, Identifier79_tree);
            }
            char_literal80=(Token)match(input,OParen,FOLLOW_OParen_in_functionDecl832); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal80_tree = (Object)adaptor.create(char_literal80);
            adaptor.addChild(root_0, char_literal80_tree);
            }
            // grammar/PlazmaScript.g:142:25: ( idList )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==Identifier) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // grammar/PlazmaScript.g:0:0: idList
                    {
                    pushFollow(FOLLOW_idList_in_functionDecl834);
                    idList81=idList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, idList81.getTree());

                    }
                    break;

            }

            char_literal82=(Token)match(input,CParen,FOLLOW_CParen_in_functionDecl837); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal82_tree = (Object)adaptor.create(char_literal82);
            adaptor.addChild(root_0, char_literal82_tree);
            }
            char_literal83=(Token)match(input,OBrace,FOLLOW_OBrace_in_functionDecl839); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal83_tree = (Object)adaptor.create(char_literal83);
            adaptor.addChild(root_0, char_literal83_tree);
            }
            pushFollow(FOLLOW_block_in_functionDecl841);
            block84=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, block84.getTree());
            char_literal85=(Token)match(input,CBrace,FOLLOW_CBrace_in_functionDecl843); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal85_tree = (Object)adaptor.create(char_literal85);
            adaptor.addChild(root_0, char_literal85_tree);
            }
            if ( state.backtracking==0 ) {
              defineFunction((Identifier79!=null?Identifier79.getText():null), (idList81!=null?((Object)idList81.tree):null), (block84!=null?((Object)block84.tree):null));
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
    // grammar/PlazmaScript.g:151:1: forStatement : For '(' Identifier 'in' expression ')' '{' block '}' -> ^( For Identifier expression block ) ;
    public final PlazmaScriptParser.forStatement_return forStatement() throws RecognitionException {
        PlazmaScriptParser.forStatement_return retval = new PlazmaScriptParser.forStatement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token For86=null;
        Token char_literal87=null;
        Token Identifier88=null;
        Token string_literal89=null;
        Token char_literal91=null;
        Token char_literal92=null;
        Token char_literal94=null;
        PlazmaScriptParser.expression_return expression90 = null;

        PlazmaScriptParser.block_return block93 = null;


        Object For86_tree=null;
        Object char_literal87_tree=null;
        Object Identifier88_tree=null;
        Object string_literal89_tree=null;
        Object char_literal91_tree=null;
        Object char_literal92_tree=null;
        Object char_literal94_tree=null;
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
            // grammar/PlazmaScript.g:152:3: ( For '(' Identifier 'in' expression ')' '{' block '}' -> ^( For Identifier expression block ) )
            // grammar/PlazmaScript.g:152:6: For '(' Identifier 'in' expression ')' '{' block '}'
            {
            For86=(Token)match(input,For,FOLLOW_For_in_forStatement872); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_For.add(For86);

            char_literal87=(Token)match(input,OParen,FOLLOW_OParen_in_forStatement874); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal87);

            Identifier88=(Token)match(input,Identifier,FOLLOW_Identifier_in_forStatement876); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Identifier.add(Identifier88);

            string_literal89=(Token)match(input,In,FOLLOW_In_in_forStatement878); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_In.add(string_literal89);

            pushFollow(FOLLOW_expression_in_forStatement880);
            expression90=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression90.getTree());
            char_literal91=(Token)match(input,CParen,FOLLOW_CParen_in_forStatement882); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal91);

            char_literal92=(Token)match(input,OBrace,FOLLOW_OBrace_in_forStatement884); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal92);

            pushFollow(FOLLOW_block_in_forStatement886);
            block93=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block93.getTree());
            char_literal94=(Token)match(input,CBrace,FOLLOW_CBrace_in_forStatement888); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal94);



            // AST REWRITE
            // elements: Identifier, For, block, expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 153:6: -> ^( For Identifier expression block )
            {
                // grammar/PlazmaScript.g:153:9: ^( For Identifier expression block )
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
    // grammar/PlazmaScript.g:157:1: whileStatement : While '(' expression ')' '{' block '}' -> ^( While expression block ) ;
    public final PlazmaScriptParser.whileStatement_return whileStatement() throws RecognitionException {
        PlazmaScriptParser.whileStatement_return retval = new PlazmaScriptParser.whileStatement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token While95=null;
        Token char_literal96=null;
        Token char_literal98=null;
        Token char_literal99=null;
        Token char_literal101=null;
        PlazmaScriptParser.expression_return expression97 = null;

        PlazmaScriptParser.block_return block100 = null;


        Object While95_tree=null;
        Object char_literal96_tree=null;
        Object char_literal98_tree=null;
        Object char_literal99_tree=null;
        Object char_literal101_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_While=new RewriteRuleTokenStream(adaptor,"token While");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:158:3: ( While '(' expression ')' '{' block '}' -> ^( While expression block ) )
            // grammar/PlazmaScript.g:158:6: While '(' expression ')' '{' block '}'
            {
            While95=(Token)match(input,While,FOLLOW_While_in_whileStatement923); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_While.add(While95);

            char_literal96=(Token)match(input,OParen,FOLLOW_OParen_in_whileStatement925); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal96);

            pushFollow(FOLLOW_expression_in_whileStatement927);
            expression97=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression97.getTree());
            char_literal98=(Token)match(input,CParen,FOLLOW_CParen_in_whileStatement929); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal98);

            char_literal99=(Token)match(input,OBrace,FOLLOW_OBrace_in_whileStatement931); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal99);

            pushFollow(FOLLOW_block_in_whileStatement933);
            block100=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block100.getTree());
            char_literal101=(Token)match(input,CBrace,FOLLOW_CBrace_in_whileStatement935); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal101);



            // AST REWRITE
            // elements: block, expression, While
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 158:45: -> ^( While expression block )
            {
                // grammar/PlazmaScript.g:158:48: ^( While expression block )
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
    // grammar/PlazmaScript.g:161:1: idList : Identifier ( ',' Identifier )* -> ^( ID_LIST ( Identifier )+ ) ;
    public final PlazmaScriptParser.idList_return idList() throws RecognitionException {
        PlazmaScriptParser.idList_return retval = new PlazmaScriptParser.idList_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Identifier102=null;
        Token char_literal103=null;
        Token Identifier104=null;

        Object Identifier102_tree=null;
        Object char_literal103_tree=null;
        Object Identifier104_tree=null;
        RewriteRuleTokenStream stream_Comma=new RewriteRuleTokenStream(adaptor,"token Comma");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");

        try {
            // grammar/PlazmaScript.g:162:3: ( Identifier ( ',' Identifier )* -> ^( ID_LIST ( Identifier )+ ) )
            // grammar/PlazmaScript.g:162:6: Identifier ( ',' Identifier )*
            {
            Identifier102=(Token)match(input,Identifier,FOLLOW_Identifier_in_idList959); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Identifier.add(Identifier102);

            // grammar/PlazmaScript.g:162:17: ( ',' Identifier )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==Comma) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // grammar/PlazmaScript.g:162:18: ',' Identifier
            	    {
            	    char_literal103=(Token)match(input,Comma,FOLLOW_Comma_in_idList962); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Comma.add(char_literal103);

            	    Identifier104=(Token)match(input,Identifier,FOLLOW_Identifier_in_idList964); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Identifier.add(Identifier104);


            	    }
            	    break;

            	default :
            	    break loop17;
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
            // 162:35: -> ^( ID_LIST ( Identifier )+ )
            {
                // grammar/PlazmaScript.g:162:38: ^( ID_LIST ( Identifier )+ )
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
    // grammar/PlazmaScript.g:165:1: exprList : expression ( ',' expression )* -> ^( EXP_LIST ( expression )+ ) ;
    public final PlazmaScriptParser.exprList_return exprList() throws RecognitionException {
        PlazmaScriptParser.exprList_return retval = new PlazmaScriptParser.exprList_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal106=null;
        PlazmaScriptParser.expression_return expression105 = null;

        PlazmaScriptParser.expression_return expression107 = null;


        Object char_literal106_tree=null;
        RewriteRuleTokenStream stream_Comma=new RewriteRuleTokenStream(adaptor,"token Comma");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // grammar/PlazmaScript.g:166:3: ( expression ( ',' expression )* -> ^( EXP_LIST ( expression )+ ) )
            // grammar/PlazmaScript.g:166:6: expression ( ',' expression )*
            {
            pushFollow(FOLLOW_expression_in_exprList989);
            expression105=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression105.getTree());
            // grammar/PlazmaScript.g:166:17: ( ',' expression )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==Comma) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // grammar/PlazmaScript.g:166:18: ',' expression
            	    {
            	    char_literal106=(Token)match(input,Comma,FOLLOW_Comma_in_exprList992); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Comma.add(char_literal106);

            	    pushFollow(FOLLOW_expression_in_exprList994);
            	    expression107=expression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_expression.add(expression107.getTree());

            	    }
            	    break;

            	default :
            	    break loop18;
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
            // 166:35: -> ^( EXP_LIST ( expression )+ )
            {
                // grammar/PlazmaScript.g:166:38: ^( EXP_LIST ( expression )+ )
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
    // grammar/PlazmaScript.g:169:1: exprPair : ( expression ':' expression ) -> ^( EXP_PAIR expression expression ) ;
    public final PlazmaScriptParser.exprPair_return exprPair() throws RecognitionException {
        PlazmaScriptParser.exprPair_return retval = new PlazmaScriptParser.exprPair_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal109=null;
        PlazmaScriptParser.expression_return expression108 = null;

        PlazmaScriptParser.expression_return expression110 = null;


        Object char_literal109_tree=null;
        RewriteRuleTokenStream stream_Colon=new RewriteRuleTokenStream(adaptor,"token Colon");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // grammar/PlazmaScript.g:170:3: ( ( expression ':' expression ) -> ^( EXP_PAIR expression expression ) )
            // grammar/PlazmaScript.g:170:6: ( expression ':' expression )
            {
            // grammar/PlazmaScript.g:170:6: ( expression ':' expression )
            // grammar/PlazmaScript.g:170:7: expression ':' expression
            {
            pushFollow(FOLLOW_expression_in_exprPair1020);
            expression108=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression108.getTree());
            char_literal109=(Token)match(input,Colon,FOLLOW_Colon_in_exprPair1022); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Colon.add(char_literal109);

            pushFollow(FOLLOW_expression_in_exprPair1024);
            expression110=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression110.getTree());

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
            // 170:34: -> ^( EXP_PAIR expression expression )
            {
                // grammar/PlazmaScript.g:170:37: ^( EXP_PAIR expression expression )
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
    // grammar/PlazmaScript.g:173:1: exprMap : exprPair ( ',' exprPair )* -> ^( EXP_MAP ( exprPair )+ ) ;
    public final PlazmaScriptParser.exprMap_return exprMap() throws RecognitionException {
        PlazmaScriptParser.exprMap_return retval = new PlazmaScriptParser.exprMap_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal112=null;
        PlazmaScriptParser.exprPair_return exprPair111 = null;

        PlazmaScriptParser.exprPair_return exprPair113 = null;


        Object char_literal112_tree=null;
        RewriteRuleTokenStream stream_Comma=new RewriteRuleTokenStream(adaptor,"token Comma");
        RewriteRuleSubtreeStream stream_exprPair=new RewriteRuleSubtreeStream(adaptor,"rule exprPair");
        try {
            // grammar/PlazmaScript.g:174:3: ( exprPair ( ',' exprPair )* -> ^( EXP_MAP ( exprPair )+ ) )
            // grammar/PlazmaScript.g:174:6: exprPair ( ',' exprPair )*
            {
            pushFollow(FOLLOW_exprPair_in_exprMap1049);
            exprPair111=exprPair();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_exprPair.add(exprPair111.getTree());
            // grammar/PlazmaScript.g:174:15: ( ',' exprPair )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==Comma) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // grammar/PlazmaScript.g:174:16: ',' exprPair
            	    {
            	    char_literal112=(Token)match(input,Comma,FOLLOW_Comma_in_exprMap1052); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Comma.add(char_literal112);

            	    pushFollow(FOLLOW_exprPair_in_exprMap1054);
            	    exprPair113=exprPair();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_exprPair.add(exprPair113.getTree());

            	    }
            	    break;

            	default :
            	    break loop19;
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
            // 174:31: -> ^( EXP_MAP ( exprPair )+ )
            {
                // grammar/PlazmaScript.g:174:34: ^( EXP_MAP ( exprPair )+ )
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
    // grammar/PlazmaScript.g:178:1: expression : condExpr ;
    public final PlazmaScriptParser.expression_return expression() throws RecognitionException {
        PlazmaScriptParser.expression_return retval = new PlazmaScriptParser.expression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        PlazmaScriptParser.condExpr_return condExpr114 = null;



        try {
            // grammar/PlazmaScript.g:179:3: ( condExpr )
            // grammar/PlazmaScript.g:179:6: condExpr
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_condExpr_in_expression1080);
            condExpr114=condExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, condExpr114.getTree());

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
    // grammar/PlazmaScript.g:182:1: condExpr : ( startExpr -> startExpr ) ( '?' a= expression ':' b= expression -> ^( TERNARY startExpr $a $b) | In expression -> ^( In startExpr expression ) | RangeE expression -> ^( RangeE startExpr expression ) | Range expression -> ^( Range startExpr expression ) )? ;
    public final PlazmaScriptParser.condExpr_return condExpr() throws RecognitionException {
        PlazmaScriptParser.condExpr_return retval = new PlazmaScriptParser.condExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal116=null;
        Token char_literal117=null;
        Token In118=null;
        Token RangeE120=null;
        Token Range122=null;
        PlazmaScriptParser.expression_return a = null;

        PlazmaScriptParser.expression_return b = null;

        PlazmaScriptParser.startExpr_return startExpr115 = null;

        PlazmaScriptParser.expression_return expression119 = null;

        PlazmaScriptParser.expression_return expression121 = null;

        PlazmaScriptParser.expression_return expression123 = null;


        Object char_literal116_tree=null;
        Object char_literal117_tree=null;
        Object In118_tree=null;
        Object RangeE120_tree=null;
        Object Range122_tree=null;
        RewriteRuleTokenStream stream_RangeE=new RewriteRuleTokenStream(adaptor,"token RangeE");
        RewriteRuleTokenStream stream_Range=new RewriteRuleTokenStream(adaptor,"token Range");
        RewriteRuleTokenStream stream_In=new RewriteRuleTokenStream(adaptor,"token In");
        RewriteRuleTokenStream stream_Colon=new RewriteRuleTokenStream(adaptor,"token Colon");
        RewriteRuleTokenStream stream_QMark=new RewriteRuleTokenStream(adaptor,"token QMark");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_startExpr=new RewriteRuleSubtreeStream(adaptor,"rule startExpr");
        try {
            // grammar/PlazmaScript.g:183:3: ( ( startExpr -> startExpr ) ( '?' a= expression ':' b= expression -> ^( TERNARY startExpr $a $b) | In expression -> ^( In startExpr expression ) | RangeE expression -> ^( RangeE startExpr expression ) | Range expression -> ^( Range startExpr expression ) )? )
            // grammar/PlazmaScript.g:183:6: ( startExpr -> startExpr ) ( '?' a= expression ':' b= expression -> ^( TERNARY startExpr $a $b) | In expression -> ^( In startExpr expression ) | RangeE expression -> ^( RangeE startExpr expression ) | Range expression -> ^( Range startExpr expression ) )?
            {
            // grammar/PlazmaScript.g:183:6: ( startExpr -> startExpr )
            // grammar/PlazmaScript.g:183:7: startExpr
            {
            pushFollow(FOLLOW_startExpr_in_condExpr1095);
            startExpr115=startExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_startExpr.add(startExpr115.getTree());


            // AST REWRITE
            // elements: startExpr
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 183:17: -> startExpr
            {
                adaptor.addChild(root_0, stream_startExpr.nextTree());

            }

            retval.tree = root_0;}
            }

            // grammar/PlazmaScript.g:184:6: ( '?' a= expression ':' b= expression -> ^( TERNARY startExpr $a $b) | In expression -> ^( In startExpr expression ) | RangeE expression -> ^( RangeE startExpr expression ) | Range expression -> ^( Range startExpr expression ) )?
            int alt20=5;
            switch ( input.LA(1) ) {
                case QMark:
                    {
                    alt20=1;
                    }
                    break;
                case In:
                    {
                    alt20=2;
                    }
                    break;
                case RangeE:
                    {
                    alt20=3;
                    }
                    break;
                case Range:
                    {
                    alt20=4;
                    }
                    break;
            }

            switch (alt20) {
                case 1 :
                    // grammar/PlazmaScript.g:184:8: '?' a= expression ':' b= expression
                    {
                    char_literal116=(Token)match(input,QMark,FOLLOW_QMark_in_condExpr1110); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_QMark.add(char_literal116);

                    pushFollow(FOLLOW_expression_in_condExpr1114);
                    a=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(a.getTree());
                    char_literal117=(Token)match(input,Colon,FOLLOW_Colon_in_condExpr1116); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Colon.add(char_literal117);

                    pushFollow(FOLLOW_expression_in_condExpr1120);
                    b=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(b.getTree());


                    // AST REWRITE
                    // elements: b, startExpr, a
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
                    // 184:42: -> ^( TERNARY startExpr $a $b)
                    {
                        // grammar/PlazmaScript.g:184:45: ^( TERNARY startExpr $a $b)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TERNARY, "TERNARY"), root_1);

                        adaptor.addChild(root_1, stream_startExpr.nextTree());
                        adaptor.addChild(root_1, stream_a.nextTree());
                        adaptor.addChild(root_1, stream_b.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:185:8: In expression
                    {
                    In118=(Token)match(input,In,FOLLOW_In_in_condExpr1143); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_In.add(In118);

                    pushFollow(FOLLOW_expression_in_condExpr1145);
                    expression119=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression119.getTree());


                    // AST REWRITE
                    // elements: startExpr, expression, In
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 185:42: -> ^( In startExpr expression )
                    {
                        // grammar/PlazmaScript.g:185:45: ^( In startExpr expression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_In.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_startExpr.nextTree());
                        adaptor.addChild(root_1, stream_expression.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // grammar/PlazmaScript.g:187:8: RangeE expression
                    {
                    RangeE120=(Token)match(input,RangeE,FOLLOW_RangeE_in_condExpr1190); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RangeE.add(RangeE120);

                    pushFollow(FOLLOW_expression_in_condExpr1192);
                    expression121=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression121.getTree());


                    // AST REWRITE
                    // elements: expression, RangeE, startExpr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 187:42: -> ^( RangeE startExpr expression )
                    {
                        // grammar/PlazmaScript.g:187:45: ^( RangeE startExpr expression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_RangeE.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_startExpr.nextTree());
                        adaptor.addChild(root_1, stream_expression.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 4 :
                    // grammar/PlazmaScript.g:188:8: Range expression
                    {
                    Range122=(Token)match(input,Range,FOLLOW_Range_in_condExpr1227); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Range.add(Range122);

                    pushFollow(FOLLOW_expression_in_condExpr1229);
                    expression123=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression123.getTree());


                    // AST REWRITE
                    // elements: startExpr, Range, expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 188:42: -> ^( Range startExpr expression )
                    {
                        // grammar/PlazmaScript.g:188:45: ^( Range startExpr expression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_Range.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_startExpr.nextTree());
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

    public static class startExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "startExpr"
    // grammar/PlazmaScript.g:193:1: startExpr : orExpr ;
    public final PlazmaScriptParser.startExpr_return startExpr() throws RecognitionException {
        PlazmaScriptParser.startExpr_return retval = new PlazmaScriptParser.startExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        PlazmaScriptParser.orExpr_return orExpr124 = null;



        try {
            // grammar/PlazmaScript.g:194:2: ( orExpr )
            // grammar/PlazmaScript.g:194:4: orExpr
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_orExpr_in_startExpr1292);
            orExpr124=orExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, orExpr124.getTree());

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
    // $ANTLR end "startExpr"

    public static class orExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "orExpr"
    // grammar/PlazmaScript.g:197:1: orExpr : andExpr ( ( 'xor' | '||' | '|' | 'or' ) andExpr )* ;
    public final PlazmaScriptParser.orExpr_return orExpr() throws RecognitionException {
        PlazmaScriptParser.orExpr_return retval = new PlazmaScriptParser.orExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set126=null;
        PlazmaScriptParser.andExpr_return andExpr125 = null;

        PlazmaScriptParser.andExpr_return andExpr127 = null;


        Object set126_tree=null;

        try {
            // grammar/PlazmaScript.g:198:3: ( andExpr ( ( 'xor' | '||' | '|' | 'or' ) andExpr )* )
            // grammar/PlazmaScript.g:198:6: andExpr ( ( 'xor' | '||' | '|' | 'or' ) andExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_andExpr_in_orExpr1305);
            andExpr125=andExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, andExpr125.getTree());
            // grammar/PlazmaScript.g:198:14: ( ( 'xor' | '||' | '|' | 'or' ) andExpr )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( ((LA21_0>=XorWord && LA21_0<=OrWord)) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // grammar/PlazmaScript.g:198:15: ( 'xor' | '||' | '|' | 'or' ) andExpr
            	    {
            	    set126=(Token)input.LT(1);
            	    set126=(Token)input.LT(1);
            	    if ( (input.LA(1)>=XorWord && input.LA(1)<=OrWord) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set126), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_andExpr_in_orExpr1325);
            	    andExpr127=andExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, andExpr127.getTree());

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
    // $ANTLR end "orExpr"

    public static class andExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "andExpr"
    // grammar/PlazmaScript.g:202:1: andExpr : equExpr ( ( '&&' | '&' | 'and' ) equExpr )* ;
    public final PlazmaScriptParser.andExpr_return andExpr() throws RecognitionException {
        PlazmaScriptParser.andExpr_return retval = new PlazmaScriptParser.andExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set129=null;
        PlazmaScriptParser.equExpr_return equExpr128 = null;

        PlazmaScriptParser.equExpr_return equExpr130 = null;


        Object set129_tree=null;

        try {
            // grammar/PlazmaScript.g:203:3: ( equExpr ( ( '&&' | '&' | 'and' ) equExpr )* )
            // grammar/PlazmaScript.g:203:6: equExpr ( ( '&&' | '&' | 'and' ) equExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_equExpr_in_andExpr1342);
            equExpr128=equExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, equExpr128.getTree());
            // grammar/PlazmaScript.g:203:14: ( ( '&&' | '&' | 'and' ) equExpr )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0>=And && LA22_0<=AndWord)) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // grammar/PlazmaScript.g:203:15: ( '&&' | '&' | 'and' ) equExpr
            	    {
            	    set129=(Token)input.LT(1);
            	    set129=(Token)input.LT(1);
            	    if ( (input.LA(1)>=And && input.LA(1)<=AndWord) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set129), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_equExpr_in_andExpr1358);
            	    equExpr130=equExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, equExpr130.getTree());

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
    // $ANTLR end "andExpr"

    public static class equExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "equExpr"
    // grammar/PlazmaScript.g:206:1: equExpr : relExpr ( ( '==' | '!=' ) relExpr )* ;
    public final PlazmaScriptParser.equExpr_return equExpr() throws RecognitionException {
        PlazmaScriptParser.equExpr_return retval = new PlazmaScriptParser.equExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set132=null;
        PlazmaScriptParser.relExpr_return relExpr131 = null;

        PlazmaScriptParser.relExpr_return relExpr133 = null;


        Object set132_tree=null;

        try {
            // grammar/PlazmaScript.g:207:3: ( relExpr ( ( '==' | '!=' ) relExpr )* )
            // grammar/PlazmaScript.g:207:6: relExpr ( ( '==' | '!=' ) relExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_relExpr_in_equExpr1374);
            relExpr131=relExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, relExpr131.getTree());
            // grammar/PlazmaScript.g:207:14: ( ( '==' | '!=' ) relExpr )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( ((LA23_0>=Equals && LA23_0<=NEquals)) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // grammar/PlazmaScript.g:207:15: ( '==' | '!=' ) relExpr
            	    {
            	    set132=(Token)input.LT(1);
            	    set132=(Token)input.LT(1);
            	    if ( (input.LA(1)>=Equals && input.LA(1)<=NEquals) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set132), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_relExpr_in_equExpr1386);
            	    relExpr133=relExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, relExpr133.getTree());

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
    // $ANTLR end "equExpr"

    public static class relExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "relExpr"
    // grammar/PlazmaScript.g:210:1: relExpr : addExpr ( ( '>=' | '<=' | '>' | '<' ) addExpr )* ;
    public final PlazmaScriptParser.relExpr_return relExpr() throws RecognitionException {
        PlazmaScriptParser.relExpr_return retval = new PlazmaScriptParser.relExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set135=null;
        PlazmaScriptParser.addExpr_return addExpr134 = null;

        PlazmaScriptParser.addExpr_return addExpr136 = null;


        Object set135_tree=null;

        try {
            // grammar/PlazmaScript.g:211:3: ( addExpr ( ( '>=' | '<=' | '>' | '<' ) addExpr )* )
            // grammar/PlazmaScript.g:211:6: addExpr ( ( '>=' | '<=' | '>' | '<' ) addExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_addExpr_in_relExpr1402);
            addExpr134=addExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, addExpr134.getTree());
            // grammar/PlazmaScript.g:211:14: ( ( '>=' | '<=' | '>' | '<' ) addExpr )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( ((LA24_0>=GTEquals && LA24_0<=LTEquals)||(LA24_0>=GT && LA24_0<=LT)) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // grammar/PlazmaScript.g:211:15: ( '>=' | '<=' | '>' | '<' ) addExpr
            	    {
            	    set135=(Token)input.LT(1);
            	    set135=(Token)input.LT(1);
            	    if ( (input.LA(1)>=GTEquals && input.LA(1)<=LTEquals)||(input.LA(1)>=GT && input.LA(1)<=LT) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set135), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_addExpr_in_relExpr1422);
            	    addExpr136=addExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, addExpr136.getTree());

            	    }
            	    break;

            	default :
            	    break loop24;
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
    // grammar/PlazmaScript.g:214:1: addExpr : mulExpr ( ( '+' | '-' ) mulExpr )* ;
    public final PlazmaScriptParser.addExpr_return addExpr() throws RecognitionException {
        PlazmaScriptParser.addExpr_return retval = new PlazmaScriptParser.addExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set138=null;
        PlazmaScriptParser.mulExpr_return mulExpr137 = null;

        PlazmaScriptParser.mulExpr_return mulExpr139 = null;


        Object set138_tree=null;

        try {
            // grammar/PlazmaScript.g:215:3: ( mulExpr ( ( '+' | '-' ) mulExpr )* )
            // grammar/PlazmaScript.g:215:6: mulExpr ( ( '+' | '-' ) mulExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_mulExpr_in_addExpr1438);
            mulExpr137=mulExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, mulExpr137.getTree());
            // grammar/PlazmaScript.g:215:14: ( ( '+' | '-' ) mulExpr )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( ((LA25_0>=Add && LA25_0<=Subtract)) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // grammar/PlazmaScript.g:215:15: ( '+' | '-' ) mulExpr
            	    {
            	    set138=(Token)input.LT(1);
            	    set138=(Token)input.LT(1);
            	    if ( (input.LA(1)>=Add && input.LA(1)<=Subtract) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set138), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_mulExpr_in_addExpr1450);
            	    mulExpr139=mulExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, mulExpr139.getTree());

            	    }
            	    break;

            	default :
            	    break loop25;
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
    // grammar/PlazmaScript.g:218:1: mulExpr : powExpr ( ( '*' | '/' | '%' ) powExpr )* ;
    public final PlazmaScriptParser.mulExpr_return mulExpr() throws RecognitionException {
        PlazmaScriptParser.mulExpr_return retval = new PlazmaScriptParser.mulExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set141=null;
        PlazmaScriptParser.powExpr_return powExpr140 = null;

        PlazmaScriptParser.powExpr_return powExpr142 = null;


        Object set141_tree=null;

        try {
            // grammar/PlazmaScript.g:219:3: ( powExpr ( ( '*' | '/' | '%' ) powExpr )* )
            // grammar/PlazmaScript.g:219:6: powExpr ( ( '*' | '/' | '%' ) powExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_powExpr_in_mulExpr1466);
            powExpr140=powExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, powExpr140.getTree());
            // grammar/PlazmaScript.g:219:14: ( ( '*' | '/' | '%' ) powExpr )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( ((LA26_0>=Multiply && LA26_0<=Modulus)) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // grammar/PlazmaScript.g:219:15: ( '*' | '/' | '%' ) powExpr
            	    {
            	    set141=(Token)input.LT(1);
            	    set141=(Token)input.LT(1);
            	    if ( (input.LA(1)>=Multiply && input.LA(1)<=Modulus) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set141), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_powExpr_in_mulExpr1482);
            	    powExpr142=powExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, powExpr142.getTree());

            	    }
            	    break;

            	default :
            	    break loop26;
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
    // grammar/PlazmaScript.g:222:1: powExpr : unaryExpr ( '^' unaryExpr )* ;
    public final PlazmaScriptParser.powExpr_return powExpr() throws RecognitionException {
        PlazmaScriptParser.powExpr_return retval = new PlazmaScriptParser.powExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal144=null;
        PlazmaScriptParser.unaryExpr_return unaryExpr143 = null;

        PlazmaScriptParser.unaryExpr_return unaryExpr145 = null;


        Object char_literal144_tree=null;

        try {
            // grammar/PlazmaScript.g:223:3: ( unaryExpr ( '^' unaryExpr )* )
            // grammar/PlazmaScript.g:223:6: unaryExpr ( '^' unaryExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_unaryExpr_in_powExpr1498);
            unaryExpr143=unaryExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpr143.getTree());
            // grammar/PlazmaScript.g:223:16: ( '^' unaryExpr )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==Pow) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // grammar/PlazmaScript.g:223:17: '^' unaryExpr
            	    {
            	    char_literal144=(Token)match(input,Pow,FOLLOW_Pow_in_powExpr1501); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal144_tree = (Object)adaptor.create(char_literal144);
            	    root_0 = (Object)adaptor.becomeRoot(char_literal144_tree, root_0);
            	    }
            	    pushFollow(FOLLOW_unaryExpr_in_powExpr1504);
            	    unaryExpr145=unaryExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpr145.getTree());

            	    }
            	    break;

            	default :
            	    break loop27;
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
    // grammar/PlazmaScript.g:226:1: unaryExpr : ( '-' atom -> ^( UNARY_MIN atom ) | '!' atom -> ^( NEGATE atom ) | 'not' atom -> ^( NEGATE atom ) | atom );
    public final PlazmaScriptParser.unaryExpr_return unaryExpr() throws RecognitionException {
        PlazmaScriptParser.unaryExpr_return retval = new PlazmaScriptParser.unaryExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal146=null;
        Token char_literal148=null;
        Token string_literal150=null;
        PlazmaScriptParser.atom_return atom147 = null;

        PlazmaScriptParser.atom_return atom149 = null;

        PlazmaScriptParser.atom_return atom151 = null;

        PlazmaScriptParser.atom_return atom152 = null;


        Object char_literal146_tree=null;
        Object char_literal148_tree=null;
        Object string_literal150_tree=null;
        RewriteRuleTokenStream stream_NotWord=new RewriteRuleTokenStream(adaptor,"token NotWord");
        RewriteRuleTokenStream stream_Subtract=new RewriteRuleTokenStream(adaptor,"token Subtract");
        RewriteRuleTokenStream stream_Not=new RewriteRuleTokenStream(adaptor,"token Not");
        RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");
        try {
            // grammar/PlazmaScript.g:227:3: ( '-' atom -> ^( UNARY_MIN atom ) | '!' atom -> ^( NEGATE atom ) | 'not' atom -> ^( NEGATE atom ) | atom )
            int alt28=4;
            switch ( input.LA(1) ) {
            case Subtract:
                {
                alt28=1;
                }
                break;
            case Not:
                {
                alt28=2;
                }
                break;
            case NotWord:
                {
                alt28=3;
                }
                break;
            case Identifier:
            case Println:
            case Print:
            case Assert:
            case Date:
            case List:
            case Integer:
            case Number:
            case Bool:
            case Null:
            case String:
            case OBrace:
            case OBracket:
            case OParen:
            case ContextIdentifier:
                {
                alt28=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }

            switch (alt28) {
                case 1 :
                    // grammar/PlazmaScript.g:227:6: '-' atom
                    {
                    char_literal146=(Token)match(input,Subtract,FOLLOW_Subtract_in_unaryExpr1522); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Subtract.add(char_literal146);

                    pushFollow(FOLLOW_atom_in_unaryExpr1524);
                    atom147=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom147.getTree());


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
                    // 227:15: -> ^( UNARY_MIN atom )
                    {
                        // grammar/PlazmaScript.g:227:18: ^( UNARY_MIN atom )
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
                    // grammar/PlazmaScript.g:228:6: '!' atom
                    {
                    char_literal148=(Token)match(input,Not,FOLLOW_Not_in_unaryExpr1539); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Not.add(char_literal148);

                    pushFollow(FOLLOW_atom_in_unaryExpr1541);
                    atom149=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom149.getTree());


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
                    // 228:15: -> ^( NEGATE atom )
                    {
                        // grammar/PlazmaScript.g:228:18: ^( NEGATE atom )
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
                    // grammar/PlazmaScript.g:229:6: 'not' atom
                    {
                    string_literal150=(Token)match(input,NotWord,FOLLOW_NotWord_in_unaryExpr1556); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NotWord.add(string_literal150);

                    pushFollow(FOLLOW_atom_in_unaryExpr1558);
                    atom151=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom151.getTree());


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
                    // 229:17: -> ^( NEGATE atom )
                    {
                        // grammar/PlazmaScript.g:229:20: ^( NEGATE atom )
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
                case 4 :
                    // grammar/PlazmaScript.g:230:6: atom
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_atom_in_unaryExpr1575);
                    atom152=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, atom152.getTree());

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
    // grammar/PlazmaScript.g:233:1: atom : ( Integer | Number | Bool | Null | lookup );
    public final PlazmaScriptParser.atom_return atom() throws RecognitionException {
        PlazmaScriptParser.atom_return retval = new PlazmaScriptParser.atom_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Integer153=null;
        Token Number154=null;
        Token Bool155=null;
        Token Null156=null;
        PlazmaScriptParser.lookup_return lookup157 = null;


        Object Integer153_tree=null;
        Object Number154_tree=null;
        Object Bool155_tree=null;
        Object Null156_tree=null;

        try {
            // grammar/PlazmaScript.g:234:3: ( Integer | Number | Bool | Null | lookup )
            int alt29=5;
            switch ( input.LA(1) ) {
            case Integer:
                {
                alt29=1;
                }
                break;
            case Number:
                {
                alt29=2;
                }
                break;
            case Bool:
                {
                alt29=3;
                }
                break;
            case Null:
                {
                alt29=4;
                }
                break;
            case Identifier:
            case Println:
            case Print:
            case Assert:
            case Date:
            case List:
            case String:
            case OBrace:
            case OBracket:
            case OParen:
            case ContextIdentifier:
                {
                alt29=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }

            switch (alt29) {
                case 1 :
                    // grammar/PlazmaScript.g:234:6: Integer
                    {
                    root_0 = (Object)adaptor.nil();

                    Integer153=(Token)match(input,Integer,FOLLOW_Integer_in_atom1589); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Integer153_tree = (Object)adaptor.create(Integer153);
                    adaptor.addChild(root_0, Integer153_tree);
                    }

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:235:6: Number
                    {
                    root_0 = (Object)adaptor.nil();

                    Number154=(Token)match(input,Number,FOLLOW_Number_in_atom1596); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Number154_tree = (Object)adaptor.create(Number154);
                    adaptor.addChild(root_0, Number154_tree);
                    }

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScript.g:236:6: Bool
                    {
                    root_0 = (Object)adaptor.nil();

                    Bool155=(Token)match(input,Bool,FOLLOW_Bool_in_atom1603); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Bool155_tree = (Object)adaptor.create(Bool155);
                    adaptor.addChild(root_0, Bool155_tree);
                    }

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScript.g:237:6: Null
                    {
                    root_0 = (Object)adaptor.nil();

                    Null156=(Token)match(input,Null,FOLLOW_Null_in_atom1610); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Null156_tree = (Object)adaptor.create(Null156);
                    adaptor.addChild(root_0, Null156_tree);
                    }

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScript.g:238:6: lookup
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_lookup_in_atom1617);
                    lookup157=lookup();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lookup157.getTree());

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
    // grammar/PlazmaScript.g:241:1: list : '[' ( exprList )? ']' -> ^( LIST ( exprList )? ) ;
    public final PlazmaScriptParser.list_return list() throws RecognitionException {
        PlazmaScriptParser.list_return retval = new PlazmaScriptParser.list_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal158=null;
        Token char_literal160=null;
        PlazmaScriptParser.exprList_return exprList159 = null;


        Object char_literal158_tree=null;
        Object char_literal160_tree=null;
        RewriteRuleTokenStream stream_CBracket=new RewriteRuleTokenStream(adaptor,"token CBracket");
        RewriteRuleTokenStream stream_OBracket=new RewriteRuleTokenStream(adaptor,"token OBracket");
        RewriteRuleSubtreeStream stream_exprList=new RewriteRuleSubtreeStream(adaptor,"rule exprList");
        try {
            // grammar/PlazmaScript.g:242:3: ( '[' ( exprList )? ']' -> ^( LIST ( exprList )? ) )
            // grammar/PlazmaScript.g:242:6: '[' ( exprList )? ']'
            {
            char_literal158=(Token)match(input,OBracket,FOLLOW_OBracket_in_list1631); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBracket.add(char_literal158);

            // grammar/PlazmaScript.g:242:10: ( exprList )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( ((LA30_0>=Identifier && LA30_0<=List)||(LA30_0>=Integer && LA30_0<=String)||(LA30_0>=Not && LA30_0<=NotWord)||LA30_0==Subtract||LA30_0==OBrace||LA30_0==OBracket||LA30_0==OParen||LA30_0==ContextIdentifier) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // grammar/PlazmaScript.g:0:0: exprList
                    {
                    pushFollow(FOLLOW_exprList_in_list1633);
                    exprList159=exprList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_exprList.add(exprList159.getTree());

                    }
                    break;

            }

            char_literal160=(Token)match(input,CBracket,FOLLOW_CBracket_in_list1636); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBracket.add(char_literal160);



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
            // 242:24: -> ^( LIST ( exprList )? )
            {
                // grammar/PlazmaScript.g:242:27: ^( LIST ( exprList )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LIST, "LIST"), root_1);

                // grammar/PlazmaScript.g:242:34: ( exprList )?
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
    // grammar/PlazmaScript.g:245:1: map : '{' ( ':' | exprMap ) '}' -> ^( MAP ( exprMap )? ) ;
    public final PlazmaScriptParser.map_return map() throws RecognitionException {
        PlazmaScriptParser.map_return retval = new PlazmaScriptParser.map_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal161=null;
        Token char_literal162=null;
        Token char_literal164=null;
        PlazmaScriptParser.exprMap_return exprMap163 = null;


        Object char_literal161_tree=null;
        Object char_literal162_tree=null;
        Object char_literal164_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_Colon=new RewriteRuleTokenStream(adaptor,"token Colon");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_exprMap=new RewriteRuleSubtreeStream(adaptor,"rule exprMap");
        try {
            // grammar/PlazmaScript.g:246:3: ( '{' ( ':' | exprMap ) '}' -> ^( MAP ( exprMap )? ) )
            // grammar/PlazmaScript.g:246:6: '{' ( ':' | exprMap ) '}'
            {
            char_literal161=(Token)match(input,OBrace,FOLLOW_OBrace_in_map1659); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal161);

            // grammar/PlazmaScript.g:246:10: ( ':' | exprMap )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==Colon) ) {
                alt31=1;
            }
            else if ( ((LA31_0>=Identifier && LA31_0<=List)||(LA31_0>=Integer && LA31_0<=String)||(LA31_0>=Not && LA31_0<=NotWord)||LA31_0==Subtract||LA31_0==OBrace||LA31_0==OBracket||LA31_0==OParen||LA31_0==ContextIdentifier) ) {
                alt31=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // grammar/PlazmaScript.g:246:11: ':'
                    {
                    char_literal162=(Token)match(input,Colon,FOLLOW_Colon_in_map1662); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Colon.add(char_literal162);


                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:246:17: exprMap
                    {
                    pushFollow(FOLLOW_exprMap_in_map1666);
                    exprMap163=exprMap();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_exprMap.add(exprMap163.getTree());

                    }
                    break;

            }

            char_literal164=(Token)match(input,CBrace,FOLLOW_CBrace_in_map1669); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal164);



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
            // 246:30: -> ^( MAP ( exprMap )? )
            {
                // grammar/PlazmaScript.g:246:33: ^( MAP ( exprMap )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(MAP, "MAP"), root_1);

                // grammar/PlazmaScript.g:246:39: ( exprMap )?
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
    // grammar/PlazmaScript.g:249:1: lookup : ( functionCall ( indexes )? -> ^( LOOKUP functionCall ( indexes )? ) | list ( indexes )? -> ^( LOOKUP list ( indexes )? ) | map ( indexes )? -> ^( LOOKUP map ( indexes )? ) | Identifier ( indexes )? -> ^( LOOKUP Identifier ( indexes )? ) | anyIdentifier ( indexes )? -> ^( LOOKUP ( indexes )? ) | String ( indexes )? -> ^( LOOKUP String ( indexes )? ) | '(' expression ')' ( indexes )? -> ^( LOOKUP expression ( indexes )? ) );
    public final PlazmaScriptParser.lookup_return lookup() throws RecognitionException {
        PlazmaScriptParser.lookup_return retval = new PlazmaScriptParser.lookup_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Identifier171=null;
        Token String175=null;
        Token char_literal177=null;
        Token char_literal179=null;
        PlazmaScriptParser.functionCall_return functionCall165 = null;

        PlazmaScriptParser.indexes_return indexes166 = null;

        PlazmaScriptParser.list_return list167 = null;

        PlazmaScriptParser.indexes_return indexes168 = null;

        PlazmaScriptParser.map_return map169 = null;

        PlazmaScriptParser.indexes_return indexes170 = null;

        PlazmaScriptParser.indexes_return indexes172 = null;

        PlazmaScriptParser.anyIdentifier_return anyIdentifier173 = null;

        PlazmaScriptParser.indexes_return indexes174 = null;

        PlazmaScriptParser.indexes_return indexes176 = null;

        PlazmaScriptParser.expression_return expression178 = null;

        PlazmaScriptParser.indexes_return indexes180 = null;


        Object Identifier171_tree=null;
        Object String175_tree=null;
        Object char_literal177_tree=null;
        Object char_literal179_tree=null;
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
            // grammar/PlazmaScript.g:250:3: ( functionCall ( indexes )? -> ^( LOOKUP functionCall ( indexes )? ) | list ( indexes )? -> ^( LOOKUP list ( indexes )? ) | map ( indexes )? -> ^( LOOKUP map ( indexes )? ) | Identifier ( indexes )? -> ^( LOOKUP Identifier ( indexes )? ) | anyIdentifier ( indexes )? -> ^( LOOKUP ( indexes )? ) | String ( indexes )? -> ^( LOOKUP String ( indexes )? ) | '(' expression ')' ( indexes )? -> ^( LOOKUP expression ( indexes )? ) )
            int alt39=7;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA39_1 = input.LA(2);

                if ( (LA39_1==OParen) ) {
                    alt39=1;
                }
                else if ( (synpred70_PlazmaScript()) ) {
                    alt39=4;
                }
                else if ( (synpred72_PlazmaScript()) ) {
                    alt39=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 39, 1, input);

                    throw nvae;
                }
                }
                break;
            case Println:
            case Print:
            case Assert:
            case Date:
            case List:
                {
                alt39=1;
                }
                break;
            case OBracket:
                {
                alt39=2;
                }
                break;
            case OBrace:
                {
                alt39=3;
                }
                break;
            case ContextIdentifier:
                {
                alt39=5;
                }
                break;
            case String:
                {
                alt39=6;
                }
                break;
            case OParen:
                {
                alt39=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;
            }

            switch (alt39) {
                case 1 :
                    // grammar/PlazmaScript.g:250:6: functionCall ( indexes )?
                    {
                    pushFollow(FOLLOW_functionCall_in_lookup1692);
                    functionCall165=functionCall();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_functionCall.add(functionCall165.getTree());
                    // grammar/PlazmaScript.g:250:19: ( indexes )?
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( (LA32_0==OBracket||LA32_0==93) ) {
                        alt32=1;
                    }
                    switch (alt32) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1694);
                            indexes166=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes166.getTree());

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
                    // 250:34: -> ^( LOOKUP functionCall ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:250:37: ^( LOOKUP functionCall ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_functionCall.nextTree());
                        // grammar/PlazmaScript.g:250:59: ( indexes )?
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
                    // grammar/PlazmaScript.g:251:6: list ( indexes )?
                    {
                    pushFollow(FOLLOW_list_in_lookup1719);
                    list167=list();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_list.add(list167.getTree());
                    // grammar/PlazmaScript.g:251:11: ( indexes )?
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==OBracket||LA33_0==93) ) {
                        alt33=1;
                    }
                    switch (alt33) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1721);
                            indexes168=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes168.getTree());

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
                    // 251:34: -> ^( LOOKUP list ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:251:37: ^( LOOKUP list ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_list.nextTree());
                        // grammar/PlazmaScript.g:251:51: ( indexes )?
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
                    // grammar/PlazmaScript.g:252:6: map ( indexes )?
                    {
                    pushFollow(FOLLOW_map_in_lookup1754);
                    map169=map();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_map.add(map169.getTree());
                    // grammar/PlazmaScript.g:252:10: ( indexes )?
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==OBracket||LA34_0==93) ) {
                        alt34=1;
                    }
                    switch (alt34) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1756);
                            indexes170=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes170.getTree());

                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: indexes, map
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 252:34: -> ^( LOOKUP map ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:252:37: ^( LOOKUP map ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_map.nextTree());
                        // grammar/PlazmaScript.g:252:50: ( indexes )?
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
                    // grammar/PlazmaScript.g:253:6: Identifier ( indexes )?
                    {
                    Identifier171=(Token)match(input,Identifier,FOLLOW_Identifier_in_lookup1792); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier171);

                    // grammar/PlazmaScript.g:253:17: ( indexes )?
                    int alt35=2;
                    int LA35_0 = input.LA(1);

                    if ( (LA35_0==OBracket||LA35_0==93) ) {
                        alt35=1;
                    }
                    switch (alt35) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1794);
                            indexes172=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes172.getTree());

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
                    // 253:34: -> ^( LOOKUP Identifier ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:253:37: ^( LOOKUP Identifier ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_Identifier.nextNode());
                        // grammar/PlazmaScript.g:253:57: ( indexes )?
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
                    // grammar/PlazmaScript.g:254:6: anyIdentifier ( indexes )?
                    {
                    pushFollow(FOLLOW_anyIdentifier_in_lookup1821);
                    anyIdentifier173=anyIdentifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_anyIdentifier.add(anyIdentifier173.getTree());
                    // grammar/PlazmaScript.g:254:20: ( indexes )?
                    int alt36=2;
                    int LA36_0 = input.LA(1);

                    if ( (LA36_0==OBracket||LA36_0==93) ) {
                        alt36=1;
                    }
                    switch (alt36) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1823);
                            indexes174=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes174.getTree());

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
                    // 254:34: -> ^( LOOKUP ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:254:37: ^( LOOKUP ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, new CommonTree(new CommonToken(Identifier, (anyIdentifier173!=null?input.toString(anyIdentifier173.start,anyIdentifier173.stop):null))));
                        // grammar/PlazmaScript.g:254:113: ( indexes )?
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
                    // grammar/PlazmaScript.g:255:6: String ( indexes )?
                    {
                    String175=(Token)match(input,String,FOLLOW_String_in_lookup1849); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_String.add(String175);

                    // grammar/PlazmaScript.g:255:13: ( indexes )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==OBracket||LA37_0==93) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1851);
                            indexes176=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes176.getTree());

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
                    // 255:34: -> ^( LOOKUP String ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:255:37: ^( LOOKUP String ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_String.nextNode());
                        // grammar/PlazmaScript.g:255:53: ( indexes )?
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
                    // grammar/PlazmaScript.g:256:6: '(' expression ')' ( indexes )?
                    {
                    char_literal177=(Token)match(input,OParen,FOLLOW_OParen_in_lookup1882); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal177);

                    pushFollow(FOLLOW_expression_in_lookup1884);
                    expression178=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression178.getTree());
                    char_literal179=(Token)match(input,CParen,FOLLOW_CParen_in_lookup1886); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal179);

                    // grammar/PlazmaScript.g:256:25: ( indexes )?
                    int alt38=2;
                    int LA38_0 = input.LA(1);

                    if ( (LA38_0==OBracket||LA38_0==93) ) {
                        alt38=1;
                    }
                    switch (alt38) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1888);
                            indexes180=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes180.getTree());

                            }
                            break;

                    }



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
                    // 256:34: -> ^( LOOKUP expression ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:256:37: ^( LOOKUP expression ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_expression.nextTree());
                        // grammar/PlazmaScript.g:256:57: ( indexes )?
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
    // grammar/PlazmaScript.g:259:1: indexes : ( tail )+ -> ^( TAILS ( tail )+ ) ;
    public final PlazmaScriptParser.indexes_return indexes() throws RecognitionException {
        PlazmaScriptParser.indexes_return retval = new PlazmaScriptParser.indexes_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        PlazmaScriptParser.tail_return tail181 = null;


        RewriteRuleSubtreeStream stream_tail=new RewriteRuleSubtreeStream(adaptor,"rule tail");
        try {
            // grammar/PlazmaScript.g:261:3: ( ( tail )+ -> ^( TAILS ( tail )+ ) )
            // grammar/PlazmaScript.g:261:6: ( tail )+
            {
            // grammar/PlazmaScript.g:261:6: ( tail )+
            int cnt40=0;
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==OBracket||LA40_0==93) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // grammar/PlazmaScript.g:261:7: tail
            	    {
            	    pushFollow(FOLLOW_tail_in_indexes1918);
            	    tail181=tail();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_tail.add(tail181.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt40 >= 1 ) break loop40;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(40, input);
                        throw eee;
                }
                cnt40++;
            } while (true);



            // AST REWRITE
            // elements: tail
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 261:14: -> ^( TAILS ( tail )+ )
            {
                // grammar/PlazmaScript.g:261:17: ^( TAILS ( tail )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TAILS, "TAILS"), root_1);

                if ( !(stream_tail.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_tail.hasNext() ) {
                    adaptor.addChild(root_1, stream_tail.nextTree());

                }
                stream_tail.reset();

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

    public static class tail_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "tail"
    // grammar/PlazmaScript.g:264:1: tail : ( '[' expression ']' -> ^( INDEX expression ) | '.' Identifier -> ^( ATTRIBUTE Identifier ) | '.' Identifier '(' ( exprList )? ')' -> ^( CALL Identifier ( exprList )? ) );
    public final PlazmaScriptParser.tail_return tail() throws RecognitionException {
        PlazmaScriptParser.tail_return retval = new PlazmaScriptParser.tail_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal182=null;
        Token char_literal184=null;
        Token char_literal185=null;
        Token Identifier186=null;
        Token char_literal187=null;
        Token Identifier188=null;
        Token char_literal189=null;
        Token char_literal191=null;
        PlazmaScriptParser.expression_return expression183 = null;

        PlazmaScriptParser.exprList_return exprList190 = null;


        Object char_literal182_tree=null;
        Object char_literal184_tree=null;
        Object char_literal185_tree=null;
        Object Identifier186_tree=null;
        Object char_literal187_tree=null;
        Object Identifier188_tree=null;
        Object char_literal189_tree=null;
        Object char_literal191_tree=null;
        RewriteRuleTokenStream stream_CBracket=new RewriteRuleTokenStream(adaptor,"token CBracket");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_93=new RewriteRuleTokenStream(adaptor,"token 93");
        RewriteRuleTokenStream stream_OBracket=new RewriteRuleTokenStream(adaptor,"token OBracket");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_exprList=new RewriteRuleSubtreeStream(adaptor,"rule exprList");
        try {
            // grammar/PlazmaScript.g:265:2: ( '[' expression ']' -> ^( INDEX expression ) | '.' Identifier -> ^( ATTRIBUTE Identifier ) | '.' Identifier '(' ( exprList )? ')' -> ^( CALL Identifier ( exprList )? ) )
            int alt42=3;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==OBracket) ) {
                alt42=1;
            }
            else if ( (LA42_0==93) ) {
                int LA42_2 = input.LA(2);

                if ( (LA42_2==Identifier) ) {
                    int LA42_3 = input.LA(3);

                    if ( (LA42_3==OParen) ) {
                        alt42=3;
                    }
                    else if ( (LA42_3==EOF||(LA42_3>=In && LA42_3<=Range)||(LA42_3>=XorWord && LA42_3<=Pow)||(LA42_3>=GT && LA42_3<=Modulus)||(LA42_3>=CBrace && LA42_3<=CBracket)||(LA42_3>=CParen && LA42_3<=Colon)||LA42_3==93) ) {
                        alt42=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 42, 3, input);

                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 42, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;
            }
            switch (alt42) {
                case 1 :
                    // grammar/PlazmaScript.g:265:4: '[' expression ']'
                    {
                    char_literal182=(Token)match(input,OBracket,FOLLOW_OBracket_in_tail1941); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OBracket.add(char_literal182);

                    pushFollow(FOLLOW_expression_in_tail1943);
                    expression183=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression183.getTree());
                    char_literal184=(Token)match(input,CBracket,FOLLOW_CBracket_in_tail1945); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CBracket.add(char_literal184);



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
                    // 265:37: -> ^( INDEX expression )
                    {
                        // grammar/PlazmaScript.g:265:40: ^( INDEX expression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(INDEX, "INDEX"), root_1);

                        adaptor.addChild(root_1, stream_expression.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:266:4: '.' Identifier
                    {
                    char_literal185=(Token)match(input,93,FOLLOW_93_in_tail1972); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_93.add(char_literal185);

                    Identifier186=(Token)match(input,Identifier,FOLLOW_Identifier_in_tail1974); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier186);



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
                    // 266:37: -> ^( ATTRIBUTE Identifier )
                    {
                        // grammar/PlazmaScript.g:266:40: ^( ATTRIBUTE Identifier )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ATTRIBUTE, "ATTRIBUTE"), root_1);

                        adaptor.addChild(root_1, stream_Identifier.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // grammar/PlazmaScript.g:267:4: '.' Identifier '(' ( exprList )? ')'
                    {
                    char_literal187=(Token)match(input,93,FOLLOW_93_in_tail2005); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_93.add(char_literal187);

                    Identifier188=(Token)match(input,Identifier,FOLLOW_Identifier_in_tail2007); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier188);

                    char_literal189=(Token)match(input,OParen,FOLLOW_OParen_in_tail2009); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal189);

                    // grammar/PlazmaScript.g:267:23: ( exprList )?
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( ((LA41_0>=Identifier && LA41_0<=List)||(LA41_0>=Integer && LA41_0<=String)||(LA41_0>=Not && LA41_0<=NotWord)||LA41_0==Subtract||LA41_0==OBrace||LA41_0==OBracket||LA41_0==OParen||LA41_0==ContextIdentifier) ) {
                        alt41=1;
                    }
                    switch (alt41) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_tail2011);
                            exprList190=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList190.getTree());

                            }
                            break;

                    }

                    char_literal191=(Token)match(input,CParen,FOLLOW_CParen_in_tail2014); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal191);



                    // AST REWRITE
                    // elements: exprList, Identifier
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 267:37: -> ^( CALL Identifier ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:267:40: ^( CALL Identifier ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CALL, "CALL"), root_1);

                        adaptor.addChild(root_1, stream_Identifier.nextNode());
                        // grammar/PlazmaScript.g:267:58: ( exprList )?
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
    // $ANTLR end "tail"

    public static class anyIdentifier_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "anyIdentifier"
    // grammar/PlazmaScript.g:357:1: anyIdentifier : ( ContextIdentifier | Identifier );
    public final PlazmaScriptParser.anyIdentifier_return anyIdentifier() throws RecognitionException {
        PlazmaScriptParser.anyIdentifier_return retval = new PlazmaScriptParser.anyIdentifier_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set192=null;

        Object set192_tree=null;

        try {
            // grammar/PlazmaScript.g:358:3: ( ContextIdentifier | Identifier )
            // grammar/PlazmaScript.g:
            {
            root_0 = (Object)adaptor.nil();

            set192=(Token)input.LT(1);
            if ( input.LA(1)==Identifier||input.LA(1)==ContextIdentifier ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set192));
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

    // $ANTLR start synpred4_PlazmaScript
    public final void synpred4_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:78:6: ( assignment ';' )
        // grammar/PlazmaScript.g:78:6: assignment ';'
        {
        pushFollow(FOLLOW_assignment_in_synpred4_PlazmaScript279);
        assignment();

        state._fsp--;
        if (state.failed) return ;
        match(input,SColon,FOLLOW_SColon_in_synpred4_PlazmaScript281); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred4_PlazmaScript

    // $ANTLR start synpred5_PlazmaScript
    public final void synpred5_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:79:6: ( functionCall ';' )
        // grammar/PlazmaScript.g:79:6: functionCall ';'
        {
        pushFollow(FOLLOW_functionCall_in_synpred5_PlazmaScript294);
        functionCall();

        state._fsp--;
        if (state.failed) return ;
        match(input,SColon,FOLLOW_SColon_in_synpred5_PlazmaScript296); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred5_PlazmaScript

    // $ANTLR start synpred6_PlazmaScript
    public final void synpred6_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:80:6: ( lookup ';' )
        // grammar/PlazmaScript.g:80:6: lookup ';'
        {
        pushFollow(FOLLOW_lookup_in_synpred6_PlazmaScript307);
        lookup();

        state._fsp--;
        if (state.failed) return ;
        match(input,SColon,FOLLOW_SColon_in_synpred6_PlazmaScript309); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred6_PlazmaScript

    // $ANTLR start synpred13_PlazmaScript
    public final void synpred13_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:90:6: ( ( variableDef )? Identifier ( indexes )? '=' expression )
        // grammar/PlazmaScript.g:90:6: ( variableDef )? Identifier ( indexes )? '=' expression
        {
        // grammar/PlazmaScript.g:90:6: ( variableDef )?
        int alt43=2;
        int LA43_0 = input.LA(1);

        if ( (LA43_0==Var) ) {
            alt43=1;
        }
        switch (alt43) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: variableDef
                {
                pushFollow(FOLLOW_variableDef_in_synpred13_PlazmaScript410);
                variableDef();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        match(input,Identifier,FOLLOW_Identifier_in_synpred13_PlazmaScript413); if (state.failed) return ;
        // grammar/PlazmaScript.g:90:30: ( indexes )?
        int alt44=2;
        int LA44_0 = input.LA(1);

        if ( (LA44_0==OBracket||LA44_0==93) ) {
            alt44=1;
        }
        switch (alt44) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: indexes
                {
                pushFollow(FOLLOW_indexes_in_synpred13_PlazmaScript415);
                indexes();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        match(input,Assign,FOLLOW_Assign_in_synpred13_PlazmaScript418); if (state.failed) return ;
        pushFollow(FOLLOW_expression_in_synpred13_PlazmaScript420);
        expression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred13_PlazmaScript

    // $ANTLR start synpred70_PlazmaScript
    public final void synpred70_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:253:6: ( Identifier ( indexes )? )
        // grammar/PlazmaScript.g:253:6: Identifier ( indexes )?
        {
        match(input,Identifier,FOLLOW_Identifier_in_synpred70_PlazmaScript1792); if (state.failed) return ;
        // grammar/PlazmaScript.g:253:17: ( indexes )?
        int alt51=2;
        int LA51_0 = input.LA(1);

        if ( (LA51_0==OBracket||LA51_0==93) ) {
            alt51=1;
        }
        switch (alt51) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: indexes
                {
                pushFollow(FOLLOW_indexes_in_synpred70_PlazmaScript1794);
                indexes();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred70_PlazmaScript

    // $ANTLR start synpred72_PlazmaScript
    public final void synpred72_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:254:6: ( anyIdentifier ( indexes )? )
        // grammar/PlazmaScript.g:254:6: anyIdentifier ( indexes )?
        {
        pushFollow(FOLLOW_anyIdentifier_in_synpred72_PlazmaScript1821);
        anyIdentifier();

        state._fsp--;
        if (state.failed) return ;
        // grammar/PlazmaScript.g:254:20: ( indexes )?
        int alt52=2;
        int LA52_0 = input.LA(1);

        if ( (LA52_0==OBracket||LA52_0==93) ) {
            alt52=1;
        }
        switch (alt52) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: indexes
                {
                pushFollow(FOLLOW_indexes_in_synpred72_PlazmaScript1823);
                indexes();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred72_PlazmaScript

    // Delegated rules

    public final boolean synpred5_PlazmaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_PlazmaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred4_PlazmaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_PlazmaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred6_PlazmaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred6_PlazmaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred13_PlazmaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred13_PlazmaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred70_PlazmaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred70_PlazmaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred72_PlazmaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred72_PlazmaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA3 dfa3 = new DFA3(this);
    static final String DFA3_eotS =
        "\23\uffff";
    static final String DFA3_eofS =
        "\23\uffff";
    static final String DFA3_minS =
        "\1\37\1\uffff\7\0\12\uffff";
    static final String DFA3_maxS =
        "\1\127\1\uffff\7\0\12\uffff";
    static final String DFA3_acceptS =
        "\1\uffff\1\1\7\uffff\1\3\3\uffff\1\4\1\5\1\6\1\7\1\10\1\2";
    static final String DFA3_specialS =
        "\2\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\12\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\20\1\21\1\2\1\4\1\5\1\6\1\7\1\10\1\15\1\uffff\1\1\1\uffff"+
            "\1\16\1\17\7\uffff\1\11\25\uffff\1\11\1\uffff\1\11\1\uffff\1"+
            "\11\10\uffff\1\3",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
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

    static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
    static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
    static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars(DFA3_minS);
    static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars(DFA3_maxS);
    static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
    static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
    static final short[][] DFA3_transition;

    static {
        int numStates = DFA3_transitionS.length;
        DFA3_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
        }
    }

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = DFA3_eot;
            this.eof = DFA3_eof;
            this.min = DFA3_min;
            this.max = DFA3_max;
            this.accept = DFA3_accept;
            this.special = DFA3_special;
            this.transition = DFA3_transition;
        }
        public String getDescription() {
            return "77:1: statement : ( assignment ';' -> assignment | functionCall ';' -> functionCall | lookup ';' -> lookup | ifStatement | forStatement | whileStatement | Break ';' -> Break | Continue ';' -> Continue );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA3_2 = input.LA(1);

                         
                        int index3_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_PlazmaScript()) ) {s = 1;}

                        else if ( (synpred5_PlazmaScript()) ) {s = 18;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 9;}

                         
                        input.seek(index3_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA3_3 = input.LA(1);

                         
                        int index3_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_PlazmaScript()) ) {s = 1;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 9;}

                         
                        input.seek(index3_3);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA3_4 = input.LA(1);

                         
                        int index3_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 18;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 9;}

                         
                        input.seek(index3_4);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA3_5 = input.LA(1);

                         
                        int index3_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 18;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 9;}

                         
                        input.seek(index3_5);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA3_6 = input.LA(1);

                         
                        int index3_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 18;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 9;}

                         
                        input.seek(index3_6);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA3_7 = input.LA(1);

                         
                        int index3_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 18;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 9;}

                         
                        input.seek(index3_7);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA3_8 = input.LA(1);

                         
                        int index3_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 18;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 9;}

                         
                        input.seek(index3_8);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 3, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_block_in_parse203 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_parse205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_block224 = new BitSet(new long[]{0x00101EFFC0000002L,0x0000000000805400L});
    public static final BitSet FOLLOW_functionDecl_in_block228 = new BitSet(new long[]{0x00101EFFC0000002L,0x0000000000805400L});
    public static final BitSet FOLLOW_Return_in_block233 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_expression_in_block235 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_SColon_in_block237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_statement279 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_SColon_in_statement281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_statement294 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_SColon_in_statement296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_statement307 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_SColon_in_statement309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStatement_in_statement353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forStatement_in_statement360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whileStatement_in_statement367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Break_in_statement374 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_SColon_in_statement376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Continue_in_statement387 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_SColon_in_statement389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDef_in_assignment410 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_Identifier_in_assignment413 = new BitSet(new long[]{0x0000000000000000L,0x0000000020021000L});
    public static final BitSet FOLLOW_indexes_in_assignment415 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_Assign_in_assignment418 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_expression_in_assignment420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDef_in_assignment443 = new BitSet(new long[]{0x0000020200000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_anyIdentifier_in_assignment446 = new BitSet(new long[]{0x0000000000000000L,0x0000000020021000L});
    public static final BitSet FOLLOW_indexes_in_assignment448 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_Assign_in_assignment451 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_expression_in_assignment453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_functionCall483 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_OParen_in_functionCall485 = new BitSet(new long[]{0x001F027E00000000L,0x000000000080D446L});
    public static final BitSet FOLLOW_exprList_in_functionCall487 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_CParen_in_functionCall490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Println_in_functionCall508 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_OParen_in_functionCall510 = new BitSet(new long[]{0x001F027E00000000L,0x000000000080D446L});
    public static final BitSet FOLLOW_expression_in_functionCall512 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_CParen_in_functionCall515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Print_in_functionCall534 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_OParen_in_functionCall536 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_expression_in_functionCall538 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_CParen_in_functionCall540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Assert_in_functionCall561 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_OParen_in_functionCall563 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_expression_in_functionCall565 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_CParen_in_functionCall567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Date_in_functionCall587 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_OParen_in_functionCall589 = new BitSet(new long[]{0x001F027E00000000L,0x000000000080D446L});
    public static final BitSet FOLLOW_exprList_in_functionCall591 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_CParen_in_functionCall594 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_List_in_functionCall617 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_OParen_in_functionCall619 = new BitSet(new long[]{0x001F027E00000000L,0x000000000080D446L});
    public static final BitSet FOLLOW_exprList_in_functionCall621 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_CParen_in_functionCall624 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStat_in_ifStatement662 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_elseIfStat_in_ifStatement664 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_elseStat_in_ifStatement667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_If_in_ifStat696 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_OParen_in_ifStat698 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_expression_in_ifStat700 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_CParen_in_ifStat702 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_OBrace_in_ifStat704 = new BitSet(new long[]{0x00101EFFC0000000L,0x0000000000805400L});
    public static final BitSet FOLLOW_block_in_ifStat706 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_CBrace_in_ifStat708 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Else_in_elseIfStat732 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_If_in_elseIfStat734 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_OParen_in_elseIfStat736 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_expression_in_elseIfStat738 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_CParen_in_elseIfStat740 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_OBrace_in_elseIfStat742 = new BitSet(new long[]{0x00101EFFC0000000L,0x0000000000805400L});
    public static final BitSet FOLLOW_block_in_elseIfStat744 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_CBrace_in_elseIfStat746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Else_in_elseStat770 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_OBrace_in_elseStat772 = new BitSet(new long[]{0x00101EFFC0000000L,0x0000000000805400L});
    public static final BitSet FOLLOW_block_in_elseStat774 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_CBrace_in_elseStat776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Var_in_variableDef796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Def_in_functionDecl828 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_Identifier_in_functionDecl830 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_OParen_in_functionDecl832 = new BitSet(new long[]{0x0000000200000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_idList_in_functionDecl834 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_CParen_in_functionDecl837 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_OBrace_in_functionDecl839 = new BitSet(new long[]{0x00101EFFC0000000L,0x0000000000805400L});
    public static final BitSet FOLLOW_block_in_functionDecl841 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_CBrace_in_functionDecl843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_For_in_forStatement872 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_OParen_in_forStatement874 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_Identifier_in_forStatement876 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_In_in_forStatement878 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_expression_in_forStatement880 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_CParen_in_forStatement882 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_OBrace_in_forStatement884 = new BitSet(new long[]{0x00101EFFC0000000L,0x0000000000805400L});
    public static final BitSet FOLLOW_block_in_forStatement886 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_CBrace_in_forStatement888 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_While_in_whileStatement923 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_OParen_in_whileStatement925 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_expression_in_whileStatement927 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_CParen_in_whileStatement929 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_OBrace_in_whileStatement931 = new BitSet(new long[]{0x00101EFFC0000000L,0x0000000000805400L});
    public static final BitSet FOLLOW_block_in_whileStatement933 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_CBrace_in_whileStatement935 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_idList959 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_Comma_in_idList962 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_Identifier_in_idList964 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_expression_in_exprList989 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_Comma_in_exprList992 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_expression_in_exprList994 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_expression_in_exprPair1020 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_Colon_in_exprPair1022 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_expression_in_exprPair1024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprPair_in_exprMap1049 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_Comma_in_exprMap1052 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_exprPair_in_exprMap1054 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_condExpr_in_expression1080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_startExpr_in_condExpr1095 = new BitSet(new long[]{0x0000E00000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_QMark_in_condExpr1110 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_expression_in_condExpr1114 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_Colon_in_condExpr1116 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_expression_in_condExpr1120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_In_in_condExpr1143 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_expression_in_condExpr1145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RangeE_in_condExpr1190 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_expression_in_condExpr1192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Range_in_condExpr1227 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_expression_in_condExpr1229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orExpr_in_startExpr1292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_andExpr_in_orExpr1305 = new BitSet(new long[]{0x01E0000000000002L});
    public static final BitSet FOLLOW_set_in_orExpr1308 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_andExpr_in_orExpr1325 = new BitSet(new long[]{0x01E0000000000002L});
    public static final BitSet FOLLOW_equExpr_in_andExpr1342 = new BitSet(new long[]{0x0E00000000000002L});
    public static final BitSet FOLLOW_set_in_andExpr1345 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_equExpr_in_andExpr1358 = new BitSet(new long[]{0x0E00000000000002L});
    public static final BitSet FOLLOW_relExpr_in_equExpr1374 = new BitSet(new long[]{0x3000000000000002L});
    public static final BitSet FOLLOW_set_in_equExpr1377 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_relExpr_in_equExpr1386 = new BitSet(new long[]{0x3000000000000002L});
    public static final BitSet FOLLOW_addExpr_in_relExpr1402 = new BitSet(new long[]{0xC000000000000002L,0x0000000000000018L});
    public static final BitSet FOLLOW_set_in_relExpr1405 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_addExpr_in_relExpr1422 = new BitSet(new long[]{0xC000000000000002L,0x0000000000000018L});
    public static final BitSet FOLLOW_mulExpr_in_addExpr1438 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000060L});
    public static final BitSet FOLLOW_set_in_addExpr1441 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_mulExpr_in_addExpr1450 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000060L});
    public static final BitSet FOLLOW_powExpr_in_mulExpr1466 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000380L});
    public static final BitSet FOLLOW_set_in_mulExpr1469 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_powExpr_in_mulExpr1482 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000380L});
    public static final BitSet FOLLOW_unaryExpr_in_powExpr1498 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_Pow_in_powExpr1501 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_unaryExpr_in_powExpr1504 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_Subtract_in_unaryExpr1522 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Not_in_unaryExpr1539 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NotWord_in_unaryExpr1556 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Integer_in_atom1589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Number_in_atom1596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Bool_in_atom1603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Null_in_atom1610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_atom1617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OBracket_in_list1631 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000807446L});
    public static final BitSet FOLLOW_exprList_in_list1633 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_CBracket_in_list1636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OBrace_in_map1659 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000905446L});
    public static final BitSet FOLLOW_Colon_in_map1662 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_exprMap_in_map1666 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_CBrace_in_map1669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_lookup1692 = new BitSet(new long[]{0x0000000000000002L,0x0000000020001000L});
    public static final BitSet FOLLOW_indexes_in_lookup1694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_in_lookup1719 = new BitSet(new long[]{0x0000000000000002L,0x0000000020001000L});
    public static final BitSet FOLLOW_indexes_in_lookup1721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_map_in_lookup1754 = new BitSet(new long[]{0x0000000000000002L,0x0000000020001000L});
    public static final BitSet FOLLOW_indexes_in_lookup1756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_lookup1792 = new BitSet(new long[]{0x0000000000000002L,0x0000000020001000L});
    public static final BitSet FOLLOW_indexes_in_lookup1794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_anyIdentifier_in_lookup1821 = new BitSet(new long[]{0x0000000000000002L,0x0000000020001000L});
    public static final BitSet FOLLOW_indexes_in_lookup1823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_String_in_lookup1849 = new BitSet(new long[]{0x0000000000000002L,0x0000000020001000L});
    public static final BitSet FOLLOW_indexes_in_lookup1851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OParen_in_lookup1882 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_expression_in_lookup1884 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_CParen_in_lookup1886 = new BitSet(new long[]{0x0000000000000002L,0x0000000020001000L});
    public static final BitSet FOLLOW_indexes_in_lookup1888 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tail_in_indexes1918 = new BitSet(new long[]{0x0000000000000002L,0x0000000020001000L});
    public static final BitSet FOLLOW_OBracket_in_tail1941 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_expression_in_tail1943 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_CBracket_in_tail1945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_93_in_tail1972 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_Identifier_in_tail1974 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_93_in_tail2005 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_Identifier_in_tail2007 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_OParen_in_tail2009 = new BitSet(new long[]{0x001F027E00000000L,0x000000000080D446L});
    public static final BitSet FOLLOW_exprList_in_tail2011 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_CParen_in_tail2014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_anyIdentifier0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_synpred4_PlazmaScript279 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_SColon_in_synpred4_PlazmaScript281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_synpred5_PlazmaScript294 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_SColon_in_synpred5_PlazmaScript296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_synpred6_PlazmaScript307 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_SColon_in_synpred6_PlazmaScript309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDef_in_synpred13_PlazmaScript410 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_Identifier_in_synpred13_PlazmaScript413 = new BitSet(new long[]{0x0000000000000000L,0x0000000020021000L});
    public static final BitSet FOLLOW_indexes_in_synpred13_PlazmaScript415 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_Assign_in_synpred13_PlazmaScript418 = new BitSet(new long[]{0x001F027E00000000L,0x0000000000805446L});
    public static final BitSet FOLLOW_expression_in_synpred13_PlazmaScript420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_synpred70_PlazmaScript1792 = new BitSet(new long[]{0x0000000000000002L,0x0000000020001000L});
    public static final BitSet FOLLOW_indexes_in_synpred70_PlazmaScript1794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_anyIdentifier_in_synpred72_PlazmaScript1821 = new BitSet(new long[]{0x0000000000000002L,0x0000000020001000L});
    public static final BitSet FOLLOW_indexes_in_synpred72_PlazmaScript1823 = new BitSet(new long[]{0x0000000000000002L});

}