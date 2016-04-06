// $ANTLR 3.2 Sep 23, 2009 12:02:23 grammar/PlazmaScript.g 2016-04-06 09:39:28

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
    public static final int While=43;
    public static final int Date=37;
    public static final int ID_LIST=13;
    public static final int DD=85;
    public static final int QMark=76;
    public static final int Add=62;
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
    public static final int ContextIdentifier=80;
    public static final int CBracket=70;
    public static final int Println=34;
    public static final int Bool=49;
    public static final int In=44;
    public static final int NEquals=55;
    public static final int Continue=32;
    public static final int Subtract=63;
    public static final int EXP_PAIR=10;
    public static final int Modulus=66;
    public static final int Multiply=64;
    public static final int OBrace=67;
    public static final int NEGATE=17;
    public static final int INDEXES=19;
    public static final int Colon=77;
    public static final int Excl=59;
    public static final int Digit=79;
    public static final int LIST=26;
    public static final int For=42;
    public static final int Divide=65;
    public static final int T__86=86;
    public static final int TAILS=24;
    public static final int Def=41;
    public static final int SColon=73;
    public static final int RangeE=45;
    public static final int LOOKUP=27;
    public static final int Range=46;
    public static final int OBracket=69;
    public static final int Break=31;
    public static final int BLOCK=4;
    public static final int MAP=25;
    public static final int GT=60;
    public static final int STATEMENTS=6;
    public static final int UNARY_MIN=16;
    public static final int ASSIGNMENT=7;
    public static final int CALL=22;
    public static final int Else=39;
    public static final int Comma=75;
    public static final int Equals=54;
    public static final int Integer=47;
    public static final int Var=40;
    public static final int Pow=58;
    public static final int LTEquals=57;

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

                    if ( (LA5_0==OBracket||LA5_0==86) ) {
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
                    // elements: Identifier, indexes, expression, variableDef
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

                    if ( (LA7_0==OBracket||LA7_0==86) ) {
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
                    // elements: variableDef, indexes, expression
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
    // grammar/PlazmaScript.g:94:1: functionCall : ( Identifier '(' ( exprList )? ')' -> ^( FUNC_CALL Identifier ( exprList )? ) | Println '(' ( expression )? ')' -> ^( FUNC_CALL Println ( expression )? ) | Print '(' expression ')' -> ^( FUNC_CALL Print expression ) | Assert '(' expression ')' -> ^( FUNC_CALL Assert expression ) | Date '(' ( exprList )? ')' -> ^( FUNC_CALL Date ( exprList )? ) );
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
        PlazmaScriptParser.exprList_return exprList33 = null;

        PlazmaScriptParser.expression_return expression37 = null;

        PlazmaScriptParser.expression_return expression41 = null;

        PlazmaScriptParser.expression_return expression45 = null;

        PlazmaScriptParser.exprList_return exprList49 = null;


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
        RewriteRuleTokenStream stream_Println=new RewriteRuleTokenStream(adaptor,"token Println");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_Date=new RewriteRuleTokenStream(adaptor,"token Date");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_Assert=new RewriteRuleTokenStream(adaptor,"token Assert");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
        RewriteRuleTokenStream stream_Print=new RewriteRuleTokenStream(adaptor,"token Print");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_exprList=new RewriteRuleSubtreeStream(adaptor,"rule exprList");
        try {
            // grammar/PlazmaScript.g:95:3: ( Identifier '(' ( exprList )? ')' -> ^( FUNC_CALL Identifier ( exprList )? ) | Println '(' ( expression )? ')' -> ^( FUNC_CALL Println ( expression )? ) | Print '(' expression ')' -> ^( FUNC_CALL Print expression ) | Assert '(' expression ')' -> ^( FUNC_CALL Assert expression ) | Date '(' ( exprList )? ')' -> ^( FUNC_CALL Date ( exprList )? ) )
            int alt12=5;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                alt12=1;
                }
                break;
            case Println:
                {
                alt12=2;
                }
                break;
            case Print:
                {
                alt12=3;
                }
                break;
            case Assert:
                {
                alt12=4;
                }
                break;
            case Date:
                {
                alt12=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
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

                    if ( ((LA9_0>=Identifier && LA9_0<=Date)||(LA9_0>=Integer && LA9_0<=String)||LA9_0==Excl||LA9_0==Subtract||LA9_0==OBrace||LA9_0==OBracket||LA9_0==OParen||LA9_0==ContextIdentifier) ) {
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

                    if ( ((LA10_0>=Identifier && LA10_0<=Date)||(LA10_0>=Integer && LA10_0<=String)||LA10_0==Excl||LA10_0==Subtract||LA10_0==OBrace||LA10_0==OBracket||LA10_0==OParen||LA10_0==ContextIdentifier) ) {
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
                    // grammar/PlazmaScript.g:100:6: Date '(' ( exprList )? ')'
                    {
                    Date47=(Token)match(input,Date,FOLLOW_Date_in_functionCall588); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Date.add(Date47);

                    char_literal48=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall590); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal48);

                    // grammar/PlazmaScript.g:100:15: ( exprList )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( ((LA11_0>=Identifier && LA11_0<=Date)||(LA11_0>=Integer && LA11_0<=String)||LA11_0==Excl||LA11_0==Subtract||LA11_0==OBrace||LA11_0==OBracket||LA11_0==OParen||LA11_0==ContextIdentifier) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall592);
                            exprList49=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList49.getTree());

                            }
                            break;

                    }

                    char_literal50=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall595); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal50);



                    // AST REWRITE
                    // elements: Date, exprList
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 100:34: -> ^( FUNC_CALL Date ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:100:37: ^( FUNC_CALL Date ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Date.nextNode());
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

        PlazmaScriptParser.ifStat_return ifStat51 = null;

        PlazmaScriptParser.elseIfStat_return elseIfStat52 = null;

        PlazmaScriptParser.elseStat_return elseStat53 = null;


        RewriteRuleSubtreeStream stream_elseIfStat=new RewriteRuleSubtreeStream(adaptor,"rule elseIfStat");
        RewriteRuleSubtreeStream stream_ifStat=new RewriteRuleSubtreeStream(adaptor,"rule ifStat");
        RewriteRuleSubtreeStream stream_elseStat=new RewriteRuleSubtreeStream(adaptor,"rule elseStat");
        try {
            // grammar/PlazmaScript.g:108:3: ( ifStat ( elseIfStat )* ( elseStat )? -> ^( IF ifStat ( elseIfStat )* ( elseStat )? ) )
            // grammar/PlazmaScript.g:108:6: ifStat ( elseIfStat )* ( elseStat )?
            {
            pushFollow(FOLLOW_ifStat_in_ifStatement631);
            ifStat51=ifStat();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_ifStat.add(ifStat51.getTree());
            // grammar/PlazmaScript.g:108:13: ( elseIfStat )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==Else) ) {
                    int LA13_1 = input.LA(2);

                    if ( (LA13_1==If) ) {
                        alt13=1;
                    }


                }


                switch (alt13) {
            	case 1 :
            	    // grammar/PlazmaScript.g:0:0: elseIfStat
            	    {
            	    pushFollow(FOLLOW_elseIfStat_in_ifStatement633);
            	    elseIfStat52=elseIfStat();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_elseIfStat.add(elseIfStat52.getTree());

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            // grammar/PlazmaScript.g:108:25: ( elseStat )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==Else) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // grammar/PlazmaScript.g:0:0: elseStat
                    {
                    pushFollow(FOLLOW_elseStat_in_ifStatement636);
                    elseStat53=elseStat();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_elseStat.add(elseStat53.getTree());

                    }
                    break;

            }



            // AST REWRITE
            // elements: elseStat, elseIfStat, ifStat
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

        Token If54=null;
        Token char_literal55=null;
        Token char_literal57=null;
        Token char_literal58=null;
        Token char_literal60=null;
        PlazmaScriptParser.expression_return expression56 = null;

        PlazmaScriptParser.block_return block59 = null;


        Object If54_tree=null;
        Object char_literal55_tree=null;
        Object char_literal57_tree=null;
        Object char_literal58_tree=null;
        Object char_literal60_tree=null;
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
            If54=(Token)match(input,If,FOLLOW_If_in_ifStat665); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_If.add(If54);

            char_literal55=(Token)match(input,OParen,FOLLOW_OParen_in_ifStat667); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal55);

            pushFollow(FOLLOW_expression_in_ifStat669);
            expression56=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression56.getTree());
            char_literal57=(Token)match(input,CParen,FOLLOW_CParen_in_ifStat671); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal57);

            char_literal58=(Token)match(input,OBrace,FOLLOW_OBrace_in_ifStat673); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal58);

            pushFollow(FOLLOW_block_in_ifStat675);
            block59=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block59.getTree());
            char_literal60=(Token)match(input,CBrace,FOLLOW_CBrace_in_ifStat677); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal60);



            // AST REWRITE
            // elements: expression, block
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

        Token Else61=null;
        Token If62=null;
        Token char_literal63=null;
        Token char_literal65=null;
        Token char_literal66=null;
        Token char_literal68=null;
        PlazmaScriptParser.expression_return expression64 = null;

        PlazmaScriptParser.block_return block67 = null;


        Object Else61_tree=null;
        Object If62_tree=null;
        Object char_literal63_tree=null;
        Object char_literal65_tree=null;
        Object char_literal66_tree=null;
        Object char_literal68_tree=null;
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
            Else61=(Token)match(input,Else,FOLLOW_Else_in_elseIfStat701); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Else.add(Else61);

            If62=(Token)match(input,If,FOLLOW_If_in_elseIfStat703); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_If.add(If62);

            char_literal63=(Token)match(input,OParen,FOLLOW_OParen_in_elseIfStat705); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal63);

            pushFollow(FOLLOW_expression_in_elseIfStat707);
            expression64=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression64.getTree());
            char_literal65=(Token)match(input,CParen,FOLLOW_CParen_in_elseIfStat709); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal65);

            char_literal66=(Token)match(input,OBrace,FOLLOW_OBrace_in_elseIfStat711); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal66);

            pushFollow(FOLLOW_block_in_elseIfStat713);
            block67=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block67.getTree());
            char_literal68=(Token)match(input,CBrace,FOLLOW_CBrace_in_elseIfStat715); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal68);



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

        Token Else69=null;
        Token char_literal70=null;
        Token char_literal72=null;
        PlazmaScriptParser.block_return block71 = null;


        Object Else69_tree=null;
        Object char_literal70_tree=null;
        Object char_literal72_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_Else=new RewriteRuleTokenStream(adaptor,"token Else");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:120:3: ( Else '{' block '}' -> ^( EXP block ) )
            // grammar/PlazmaScript.g:120:6: Else '{' block '}'
            {
            Else69=(Token)match(input,Else,FOLLOW_Else_in_elseStat739); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Else.add(Else69);

            char_literal70=(Token)match(input,OBrace,FOLLOW_OBrace_in_elseStat741); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal70);

            pushFollow(FOLLOW_block_in_elseStat743);
            block71=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block71.getTree());
            char_literal72=(Token)match(input,CBrace,FOLLOW_CBrace_in_elseStat745); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal72);



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

        Token Var73=null;

        Object Var73_tree=null;

        try {
            // grammar/PlazmaScript.g:124:2: ( Var )
            // grammar/PlazmaScript.g:124:4: Var
            {
            root_0 = (Object)adaptor.nil();

            Var73=(Token)match(input,Var,FOLLOW_Var_in_variableDef765); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Var73_tree = (Object)adaptor.create(Var73);
            adaptor.addChild(root_0, Var73_tree);
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

        Token Def74=null;
        Token Identifier75=null;
        Token char_literal76=null;
        Token char_literal78=null;
        Token char_literal79=null;
        Token char_literal81=null;
        PlazmaScriptParser.idList_return idList77 = null;

        PlazmaScriptParser.block_return block80 = null;


        Object Def74_tree=null;
        Object Identifier75_tree=null;
        Object char_literal76_tree=null;
        Object char_literal78_tree=null;
        Object char_literal79_tree=null;
        Object char_literal81_tree=null;

        try {
            // grammar/PlazmaScript.g:142:3: ( Def Identifier '(' ( idList )? ')' '{' block '}' )
            // grammar/PlazmaScript.g:142:6: Def Identifier '(' ( idList )? ')' '{' block '}'
            {
            root_0 = (Object)adaptor.nil();

            Def74=(Token)match(input,Def,FOLLOW_Def_in_functionDecl797); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Def74_tree = (Object)adaptor.create(Def74);
            adaptor.addChild(root_0, Def74_tree);
            }
            Identifier75=(Token)match(input,Identifier,FOLLOW_Identifier_in_functionDecl799); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier75_tree = (Object)adaptor.create(Identifier75);
            adaptor.addChild(root_0, Identifier75_tree);
            }
            char_literal76=(Token)match(input,OParen,FOLLOW_OParen_in_functionDecl801); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal76_tree = (Object)adaptor.create(char_literal76);
            adaptor.addChild(root_0, char_literal76_tree);
            }
            // grammar/PlazmaScript.g:142:25: ( idList )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==Identifier) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // grammar/PlazmaScript.g:0:0: idList
                    {
                    pushFollow(FOLLOW_idList_in_functionDecl803);
                    idList77=idList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, idList77.getTree());

                    }
                    break;

            }

            char_literal78=(Token)match(input,CParen,FOLLOW_CParen_in_functionDecl806); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal78_tree = (Object)adaptor.create(char_literal78);
            adaptor.addChild(root_0, char_literal78_tree);
            }
            char_literal79=(Token)match(input,OBrace,FOLLOW_OBrace_in_functionDecl808); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal79_tree = (Object)adaptor.create(char_literal79);
            adaptor.addChild(root_0, char_literal79_tree);
            }
            pushFollow(FOLLOW_block_in_functionDecl810);
            block80=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, block80.getTree());
            char_literal81=(Token)match(input,CBrace,FOLLOW_CBrace_in_functionDecl812); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal81_tree = (Object)adaptor.create(char_literal81);
            adaptor.addChild(root_0, char_literal81_tree);
            }
            if ( state.backtracking==0 ) {
              defineFunction((Identifier75!=null?Identifier75.getText():null), (idList77!=null?((Object)idList77.tree):null), (block80!=null?((Object)block80.tree):null));
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

        Token For82=null;
        Token char_literal83=null;
        Token Identifier84=null;
        Token string_literal85=null;
        Token char_literal87=null;
        Token char_literal88=null;
        Token char_literal90=null;
        PlazmaScriptParser.expression_return expression86 = null;

        PlazmaScriptParser.block_return block89 = null;


        Object For82_tree=null;
        Object char_literal83_tree=null;
        Object Identifier84_tree=null;
        Object string_literal85_tree=null;
        Object char_literal87_tree=null;
        Object char_literal88_tree=null;
        Object char_literal90_tree=null;
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
            For82=(Token)match(input,For,FOLLOW_For_in_forStatement841); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_For.add(For82);

            char_literal83=(Token)match(input,OParen,FOLLOW_OParen_in_forStatement843); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal83);

            Identifier84=(Token)match(input,Identifier,FOLLOW_Identifier_in_forStatement845); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Identifier.add(Identifier84);

            string_literal85=(Token)match(input,In,FOLLOW_In_in_forStatement847); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_In.add(string_literal85);

            pushFollow(FOLLOW_expression_in_forStatement849);
            expression86=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression86.getTree());
            char_literal87=(Token)match(input,CParen,FOLLOW_CParen_in_forStatement851); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal87);

            char_literal88=(Token)match(input,OBrace,FOLLOW_OBrace_in_forStatement853); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal88);

            pushFollow(FOLLOW_block_in_forStatement855);
            block89=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block89.getTree());
            char_literal90=(Token)match(input,CBrace,FOLLOW_CBrace_in_forStatement857); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal90);



            // AST REWRITE
            // elements: block, For, Identifier, expression
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

        Token While91=null;
        Token char_literal92=null;
        Token char_literal94=null;
        Token char_literal95=null;
        Token char_literal97=null;
        PlazmaScriptParser.expression_return expression93 = null;

        PlazmaScriptParser.block_return block96 = null;


        Object While91_tree=null;
        Object char_literal92_tree=null;
        Object char_literal94_tree=null;
        Object char_literal95_tree=null;
        Object char_literal97_tree=null;
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
            While91=(Token)match(input,While,FOLLOW_While_in_whileStatement892); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_While.add(While91);

            char_literal92=(Token)match(input,OParen,FOLLOW_OParen_in_whileStatement894); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal92);

            pushFollow(FOLLOW_expression_in_whileStatement896);
            expression93=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression93.getTree());
            char_literal94=(Token)match(input,CParen,FOLLOW_CParen_in_whileStatement898); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal94);

            char_literal95=(Token)match(input,OBrace,FOLLOW_OBrace_in_whileStatement900); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal95);

            pushFollow(FOLLOW_block_in_whileStatement902);
            block96=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block96.getTree());
            char_literal97=(Token)match(input,CBrace,FOLLOW_CBrace_in_whileStatement904); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal97);



            // AST REWRITE
            // elements: expression, block, While
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

        Token Identifier98=null;
        Token char_literal99=null;
        Token Identifier100=null;

        Object Identifier98_tree=null;
        Object char_literal99_tree=null;
        Object Identifier100_tree=null;
        RewriteRuleTokenStream stream_Comma=new RewriteRuleTokenStream(adaptor,"token Comma");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");

        try {
            // grammar/PlazmaScript.g:162:3: ( Identifier ( ',' Identifier )* -> ^( ID_LIST ( Identifier )+ ) )
            // grammar/PlazmaScript.g:162:6: Identifier ( ',' Identifier )*
            {
            Identifier98=(Token)match(input,Identifier,FOLLOW_Identifier_in_idList928); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Identifier.add(Identifier98);

            // grammar/PlazmaScript.g:162:17: ( ',' Identifier )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==Comma) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // grammar/PlazmaScript.g:162:18: ',' Identifier
            	    {
            	    char_literal99=(Token)match(input,Comma,FOLLOW_Comma_in_idList931); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Comma.add(char_literal99);

            	    Identifier100=(Token)match(input,Identifier,FOLLOW_Identifier_in_idList933); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Identifier.add(Identifier100);


            	    }
            	    break;

            	default :
            	    break loop16;
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

        Token char_literal102=null;
        PlazmaScriptParser.expression_return expression101 = null;

        PlazmaScriptParser.expression_return expression103 = null;


        Object char_literal102_tree=null;
        RewriteRuleTokenStream stream_Comma=new RewriteRuleTokenStream(adaptor,"token Comma");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // grammar/PlazmaScript.g:166:3: ( expression ( ',' expression )* -> ^( EXP_LIST ( expression )+ ) )
            // grammar/PlazmaScript.g:166:6: expression ( ',' expression )*
            {
            pushFollow(FOLLOW_expression_in_exprList958);
            expression101=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression101.getTree());
            // grammar/PlazmaScript.g:166:17: ( ',' expression )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==Comma) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // grammar/PlazmaScript.g:166:18: ',' expression
            	    {
            	    char_literal102=(Token)match(input,Comma,FOLLOW_Comma_in_exprList961); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Comma.add(char_literal102);

            	    pushFollow(FOLLOW_expression_in_exprList963);
            	    expression103=expression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_expression.add(expression103.getTree());

            	    }
            	    break;

            	default :
            	    break loop17;
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

        Token char_literal105=null;
        PlazmaScriptParser.expression_return expression104 = null;

        PlazmaScriptParser.expression_return expression106 = null;


        Object char_literal105_tree=null;
        RewriteRuleTokenStream stream_Colon=new RewriteRuleTokenStream(adaptor,"token Colon");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // grammar/PlazmaScript.g:170:3: ( ( expression ':' expression ) -> ^( EXP_PAIR expression expression ) )
            // grammar/PlazmaScript.g:170:6: ( expression ':' expression )
            {
            // grammar/PlazmaScript.g:170:6: ( expression ':' expression )
            // grammar/PlazmaScript.g:170:7: expression ':' expression
            {
            pushFollow(FOLLOW_expression_in_exprPair989);
            expression104=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression104.getTree());
            char_literal105=(Token)match(input,Colon,FOLLOW_Colon_in_exprPair991); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Colon.add(char_literal105);

            pushFollow(FOLLOW_expression_in_exprPair993);
            expression106=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression106.getTree());

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

        Token char_literal108=null;
        PlazmaScriptParser.exprPair_return exprPair107 = null;

        PlazmaScriptParser.exprPair_return exprPair109 = null;


        Object char_literal108_tree=null;
        RewriteRuleTokenStream stream_Comma=new RewriteRuleTokenStream(adaptor,"token Comma");
        RewriteRuleSubtreeStream stream_exprPair=new RewriteRuleSubtreeStream(adaptor,"rule exprPair");
        try {
            // grammar/PlazmaScript.g:174:3: ( exprPair ( ',' exprPair )* -> ^( EXP_MAP ( exprPair )+ ) )
            // grammar/PlazmaScript.g:174:6: exprPair ( ',' exprPair )*
            {
            pushFollow(FOLLOW_exprPair_in_exprMap1018);
            exprPair107=exprPair();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_exprPair.add(exprPair107.getTree());
            // grammar/PlazmaScript.g:174:15: ( ',' exprPair )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==Comma) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // grammar/PlazmaScript.g:174:16: ',' exprPair
            	    {
            	    char_literal108=(Token)match(input,Comma,FOLLOW_Comma_in_exprMap1021); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Comma.add(char_literal108);

            	    pushFollow(FOLLOW_exprPair_in_exprMap1023);
            	    exprPair109=exprPair();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_exprPair.add(exprPair109.getTree());

            	    }
            	    break;

            	default :
            	    break loop18;
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

        PlazmaScriptParser.condExpr_return condExpr110 = null;



        try {
            // grammar/PlazmaScript.g:179:3: ( condExpr )
            // grammar/PlazmaScript.g:179:6: condExpr
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_condExpr_in_expression1049);
            condExpr110=condExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, condExpr110.getTree());

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
    // grammar/PlazmaScript.g:182:1: condExpr : ( orExpr -> orExpr ) ( '?' a= expression ':' b= expression -> ^( TERNARY orExpr $a $b) | In expression -> ^( In orExpr expression ) | RangeE expression -> ^( RangeE orExpr expression ) | Range expression -> ^( Range orExpr expression ) )? ;
    public final PlazmaScriptParser.condExpr_return condExpr() throws RecognitionException {
        PlazmaScriptParser.condExpr_return retval = new PlazmaScriptParser.condExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal112=null;
        Token char_literal113=null;
        Token In114=null;
        Token RangeE116=null;
        Token Range118=null;
        PlazmaScriptParser.expression_return a = null;

        PlazmaScriptParser.expression_return b = null;

        PlazmaScriptParser.orExpr_return orExpr111 = null;

        PlazmaScriptParser.expression_return expression115 = null;

        PlazmaScriptParser.expression_return expression117 = null;

        PlazmaScriptParser.expression_return expression119 = null;


        Object char_literal112_tree=null;
        Object char_literal113_tree=null;
        Object In114_tree=null;
        Object RangeE116_tree=null;
        Object Range118_tree=null;
        RewriteRuleTokenStream stream_RangeE=new RewriteRuleTokenStream(adaptor,"token RangeE");
        RewriteRuleTokenStream stream_Range=new RewriteRuleTokenStream(adaptor,"token Range");
        RewriteRuleTokenStream stream_In=new RewriteRuleTokenStream(adaptor,"token In");
        RewriteRuleTokenStream stream_Colon=new RewriteRuleTokenStream(adaptor,"token Colon");
        RewriteRuleTokenStream stream_QMark=new RewriteRuleTokenStream(adaptor,"token QMark");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_orExpr=new RewriteRuleSubtreeStream(adaptor,"rule orExpr");
        try {
            // grammar/PlazmaScript.g:183:3: ( ( orExpr -> orExpr ) ( '?' a= expression ':' b= expression -> ^( TERNARY orExpr $a $b) | In expression -> ^( In orExpr expression ) | RangeE expression -> ^( RangeE orExpr expression ) | Range expression -> ^( Range orExpr expression ) )? )
            // grammar/PlazmaScript.g:183:6: ( orExpr -> orExpr ) ( '?' a= expression ':' b= expression -> ^( TERNARY orExpr $a $b) | In expression -> ^( In orExpr expression ) | RangeE expression -> ^( RangeE orExpr expression ) | Range expression -> ^( Range orExpr expression ) )?
            {
            // grammar/PlazmaScript.g:183:6: ( orExpr -> orExpr )
            // grammar/PlazmaScript.g:183:7: orExpr
            {
            pushFollow(FOLLOW_orExpr_in_condExpr1064);
            orExpr111=orExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_orExpr.add(orExpr111.getTree());


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
            // 183:14: -> orExpr
            {
                adaptor.addChild(root_0, stream_orExpr.nextTree());

            }

            retval.tree = root_0;}
            }

            // grammar/PlazmaScript.g:184:6: ( '?' a= expression ':' b= expression -> ^( TERNARY orExpr $a $b) | In expression -> ^( In orExpr expression ) | RangeE expression -> ^( RangeE orExpr expression ) | Range expression -> ^( Range orExpr expression ) )?
            int alt19=5;
            switch ( input.LA(1) ) {
                case QMark:
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
            }

            switch (alt19) {
                case 1 :
                    // grammar/PlazmaScript.g:184:8: '?' a= expression ':' b= expression
                    {
                    char_literal112=(Token)match(input,QMark,FOLLOW_QMark_in_condExpr1079); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_QMark.add(char_literal112);

                    pushFollow(FOLLOW_expression_in_condExpr1083);
                    a=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(a.getTree());
                    char_literal113=(Token)match(input,Colon,FOLLOW_Colon_in_condExpr1085); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Colon.add(char_literal113);

                    pushFollow(FOLLOW_expression_in_condExpr1089);
                    b=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(b.getTree());


                    // AST REWRITE
                    // elements: orExpr, b, a
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
                    // 184:42: -> ^( TERNARY orExpr $a $b)
                    {
                        // grammar/PlazmaScript.g:184:45: ^( TERNARY orExpr $a $b)
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
                    // grammar/PlazmaScript.g:185:8: In expression
                    {
                    In114=(Token)match(input,In,FOLLOW_In_in_condExpr1112); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_In.add(In114);

                    pushFollow(FOLLOW_expression_in_condExpr1114);
                    expression115=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression115.getTree());


                    // AST REWRITE
                    // elements: In, expression, orExpr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 185:42: -> ^( In orExpr expression )
                    {
                        // grammar/PlazmaScript.g:185:45: ^( In orExpr expression )
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
                    // grammar/PlazmaScript.g:187:8: RangeE expression
                    {
                    RangeE116=(Token)match(input,RangeE,FOLLOW_RangeE_in_condExpr1159); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RangeE.add(RangeE116);

                    pushFollow(FOLLOW_expression_in_condExpr1161);
                    expression117=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression117.getTree());


                    // AST REWRITE
                    // elements: RangeE, expression, orExpr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 187:42: -> ^( RangeE orExpr expression )
                    {
                        // grammar/PlazmaScript.g:187:45: ^( RangeE orExpr expression )
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
                    // grammar/PlazmaScript.g:188:8: Range expression
                    {
                    Range118=(Token)match(input,Range,FOLLOW_Range_in_condExpr1196); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Range.add(Range118);

                    pushFollow(FOLLOW_expression_in_condExpr1198);
                    expression119=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression119.getTree());


                    // AST REWRITE
                    // elements: Range, orExpr, expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 188:42: -> ^( Range orExpr expression )
                    {
                        // grammar/PlazmaScript.g:188:45: ^( Range orExpr expression )
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
    // grammar/PlazmaScript.g:193:1: orExpr : andExpr ( '||' andExpr )* ;
    public final PlazmaScriptParser.orExpr_return orExpr() throws RecognitionException {
        PlazmaScriptParser.orExpr_return retval = new PlazmaScriptParser.orExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal121=null;
        PlazmaScriptParser.andExpr_return andExpr120 = null;

        PlazmaScriptParser.andExpr_return andExpr122 = null;


        Object string_literal121_tree=null;

        try {
            // grammar/PlazmaScript.g:194:3: ( andExpr ( '||' andExpr )* )
            // grammar/PlazmaScript.g:194:6: andExpr ( '||' andExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_andExpr_in_orExpr1263);
            andExpr120=andExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, andExpr120.getTree());
            // grammar/PlazmaScript.g:194:14: ( '||' andExpr )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==Or) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // grammar/PlazmaScript.g:194:15: '||' andExpr
            	    {
            	    string_literal121=(Token)match(input,Or,FOLLOW_Or_in_orExpr1266); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal121_tree = (Object)adaptor.create(string_literal121);
            	    root_0 = (Object)adaptor.becomeRoot(string_literal121_tree, root_0);
            	    }
            	    pushFollow(FOLLOW_andExpr_in_orExpr1269);
            	    andExpr122=andExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, andExpr122.getTree());

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
    // $ANTLR end "orExpr"

    public static class andExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "andExpr"
    // grammar/PlazmaScript.g:197:1: andExpr : equExpr ( '&&' equExpr )* ;
    public final PlazmaScriptParser.andExpr_return andExpr() throws RecognitionException {
        PlazmaScriptParser.andExpr_return retval = new PlazmaScriptParser.andExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal124=null;
        PlazmaScriptParser.equExpr_return equExpr123 = null;

        PlazmaScriptParser.equExpr_return equExpr125 = null;


        Object string_literal124_tree=null;

        try {
            // grammar/PlazmaScript.g:198:3: ( equExpr ( '&&' equExpr )* )
            // grammar/PlazmaScript.g:198:6: equExpr ( '&&' equExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_equExpr_in_andExpr1285);
            equExpr123=equExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, equExpr123.getTree());
            // grammar/PlazmaScript.g:198:14: ( '&&' equExpr )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==And) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // grammar/PlazmaScript.g:198:15: '&&' equExpr
            	    {
            	    string_literal124=(Token)match(input,And,FOLLOW_And_in_andExpr1288); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal124_tree = (Object)adaptor.create(string_literal124);
            	    root_0 = (Object)adaptor.becomeRoot(string_literal124_tree, root_0);
            	    }
            	    pushFollow(FOLLOW_equExpr_in_andExpr1291);
            	    equExpr125=equExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, equExpr125.getTree());

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
    // $ANTLR end "andExpr"

    public static class equExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "equExpr"
    // grammar/PlazmaScript.g:201:1: equExpr : relExpr ( ( '==' | '!=' ) relExpr )* ;
    public final PlazmaScriptParser.equExpr_return equExpr() throws RecognitionException {
        PlazmaScriptParser.equExpr_return retval = new PlazmaScriptParser.equExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set127=null;
        PlazmaScriptParser.relExpr_return relExpr126 = null;

        PlazmaScriptParser.relExpr_return relExpr128 = null;


        Object set127_tree=null;

        try {
            // grammar/PlazmaScript.g:202:3: ( relExpr ( ( '==' | '!=' ) relExpr )* )
            // grammar/PlazmaScript.g:202:6: relExpr ( ( '==' | '!=' ) relExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_relExpr_in_equExpr1307);
            relExpr126=relExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, relExpr126.getTree());
            // grammar/PlazmaScript.g:202:14: ( ( '==' | '!=' ) relExpr )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0>=Equals && LA22_0<=NEquals)) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // grammar/PlazmaScript.g:202:15: ( '==' | '!=' ) relExpr
            	    {
            	    set127=(Token)input.LT(1);
            	    set127=(Token)input.LT(1);
            	    if ( (input.LA(1)>=Equals && input.LA(1)<=NEquals) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set127), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_relExpr_in_equExpr1319);
            	    relExpr128=relExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, relExpr128.getTree());

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
    // $ANTLR end "equExpr"

    public static class relExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "relExpr"
    // grammar/PlazmaScript.g:205:1: relExpr : addExpr ( ( '>=' | '<=' | '>' | '<' ) addExpr )* ;
    public final PlazmaScriptParser.relExpr_return relExpr() throws RecognitionException {
        PlazmaScriptParser.relExpr_return retval = new PlazmaScriptParser.relExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set130=null;
        PlazmaScriptParser.addExpr_return addExpr129 = null;

        PlazmaScriptParser.addExpr_return addExpr131 = null;


        Object set130_tree=null;

        try {
            // grammar/PlazmaScript.g:206:3: ( addExpr ( ( '>=' | '<=' | '>' | '<' ) addExpr )* )
            // grammar/PlazmaScript.g:206:6: addExpr ( ( '>=' | '<=' | '>' | '<' ) addExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_addExpr_in_relExpr1335);
            addExpr129=addExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, addExpr129.getTree());
            // grammar/PlazmaScript.g:206:14: ( ( '>=' | '<=' | '>' | '<' ) addExpr )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( ((LA23_0>=GTEquals && LA23_0<=LTEquals)||(LA23_0>=GT && LA23_0<=LT)) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // grammar/PlazmaScript.g:206:15: ( '>=' | '<=' | '>' | '<' ) addExpr
            	    {
            	    set130=(Token)input.LT(1);
            	    set130=(Token)input.LT(1);
            	    if ( (input.LA(1)>=GTEquals && input.LA(1)<=LTEquals)||(input.LA(1)>=GT && input.LA(1)<=LT) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set130), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_addExpr_in_relExpr1355);
            	    addExpr131=addExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, addExpr131.getTree());

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
    // $ANTLR end "relExpr"

    public static class addExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "addExpr"
    // grammar/PlazmaScript.g:209:1: addExpr : mulExpr ( ( '+' | '-' ) mulExpr )* ;
    public final PlazmaScriptParser.addExpr_return addExpr() throws RecognitionException {
        PlazmaScriptParser.addExpr_return retval = new PlazmaScriptParser.addExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set133=null;
        PlazmaScriptParser.mulExpr_return mulExpr132 = null;

        PlazmaScriptParser.mulExpr_return mulExpr134 = null;


        Object set133_tree=null;

        try {
            // grammar/PlazmaScript.g:210:3: ( mulExpr ( ( '+' | '-' ) mulExpr )* )
            // grammar/PlazmaScript.g:210:6: mulExpr ( ( '+' | '-' ) mulExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_mulExpr_in_addExpr1371);
            mulExpr132=mulExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, mulExpr132.getTree());
            // grammar/PlazmaScript.g:210:14: ( ( '+' | '-' ) mulExpr )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( ((LA24_0>=Add && LA24_0<=Subtract)) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // grammar/PlazmaScript.g:210:15: ( '+' | '-' ) mulExpr
            	    {
            	    set133=(Token)input.LT(1);
            	    set133=(Token)input.LT(1);
            	    if ( (input.LA(1)>=Add && input.LA(1)<=Subtract) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set133), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_mulExpr_in_addExpr1383);
            	    mulExpr134=mulExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, mulExpr134.getTree());

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
    // $ANTLR end "addExpr"

    public static class mulExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "mulExpr"
    // grammar/PlazmaScript.g:213:1: mulExpr : powExpr ( ( '*' | '/' | '%' ) powExpr )* ;
    public final PlazmaScriptParser.mulExpr_return mulExpr() throws RecognitionException {
        PlazmaScriptParser.mulExpr_return retval = new PlazmaScriptParser.mulExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set136=null;
        PlazmaScriptParser.powExpr_return powExpr135 = null;

        PlazmaScriptParser.powExpr_return powExpr137 = null;


        Object set136_tree=null;

        try {
            // grammar/PlazmaScript.g:214:3: ( powExpr ( ( '*' | '/' | '%' ) powExpr )* )
            // grammar/PlazmaScript.g:214:6: powExpr ( ( '*' | '/' | '%' ) powExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_powExpr_in_mulExpr1399);
            powExpr135=powExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, powExpr135.getTree());
            // grammar/PlazmaScript.g:214:14: ( ( '*' | '/' | '%' ) powExpr )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( ((LA25_0>=Multiply && LA25_0<=Modulus)) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // grammar/PlazmaScript.g:214:15: ( '*' | '/' | '%' ) powExpr
            	    {
            	    set136=(Token)input.LT(1);
            	    set136=(Token)input.LT(1);
            	    if ( (input.LA(1)>=Multiply && input.LA(1)<=Modulus) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set136), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_powExpr_in_mulExpr1415);
            	    powExpr137=powExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, powExpr137.getTree());

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
    // $ANTLR end "mulExpr"

    public static class powExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "powExpr"
    // grammar/PlazmaScript.g:217:1: powExpr : unaryExpr ( '^' unaryExpr )* ;
    public final PlazmaScriptParser.powExpr_return powExpr() throws RecognitionException {
        PlazmaScriptParser.powExpr_return retval = new PlazmaScriptParser.powExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal139=null;
        PlazmaScriptParser.unaryExpr_return unaryExpr138 = null;

        PlazmaScriptParser.unaryExpr_return unaryExpr140 = null;


        Object char_literal139_tree=null;

        try {
            // grammar/PlazmaScript.g:218:3: ( unaryExpr ( '^' unaryExpr )* )
            // grammar/PlazmaScript.g:218:6: unaryExpr ( '^' unaryExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_unaryExpr_in_powExpr1431);
            unaryExpr138=unaryExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpr138.getTree());
            // grammar/PlazmaScript.g:218:16: ( '^' unaryExpr )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==Pow) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // grammar/PlazmaScript.g:218:17: '^' unaryExpr
            	    {
            	    char_literal139=(Token)match(input,Pow,FOLLOW_Pow_in_powExpr1434); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal139_tree = (Object)adaptor.create(char_literal139);
            	    root_0 = (Object)adaptor.becomeRoot(char_literal139_tree, root_0);
            	    }
            	    pushFollow(FOLLOW_unaryExpr_in_powExpr1437);
            	    unaryExpr140=unaryExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpr140.getTree());

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
    // $ANTLR end "powExpr"

    public static class unaryExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "unaryExpr"
    // grammar/PlazmaScript.g:221:1: unaryExpr : ( '-' atom -> ^( UNARY_MIN atom ) | '!' atom -> ^( NEGATE atom ) | atom );
    public final PlazmaScriptParser.unaryExpr_return unaryExpr() throws RecognitionException {
        PlazmaScriptParser.unaryExpr_return retval = new PlazmaScriptParser.unaryExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal141=null;
        Token char_literal143=null;
        PlazmaScriptParser.atom_return atom142 = null;

        PlazmaScriptParser.atom_return atom144 = null;

        PlazmaScriptParser.atom_return atom145 = null;


        Object char_literal141_tree=null;
        Object char_literal143_tree=null;
        RewriteRuleTokenStream stream_Excl=new RewriteRuleTokenStream(adaptor,"token Excl");
        RewriteRuleTokenStream stream_Subtract=new RewriteRuleTokenStream(adaptor,"token Subtract");
        RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");
        try {
            // grammar/PlazmaScript.g:222:3: ( '-' atom -> ^( UNARY_MIN atom ) | '!' atom -> ^( NEGATE atom ) | atom )
            int alt27=3;
            switch ( input.LA(1) ) {
            case Subtract:
                {
                alt27=1;
                }
                break;
            case Excl:
                {
                alt27=2;
                }
                break;
            case Identifier:
            case Println:
            case Print:
            case Assert:
            case Date:
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
                alt27=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }

            switch (alt27) {
                case 1 :
                    // grammar/PlazmaScript.g:222:6: '-' atom
                    {
                    char_literal141=(Token)match(input,Subtract,FOLLOW_Subtract_in_unaryExpr1455); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Subtract.add(char_literal141);

                    pushFollow(FOLLOW_atom_in_unaryExpr1457);
                    atom142=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom142.getTree());


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
                    // 222:15: -> ^( UNARY_MIN atom )
                    {
                        // grammar/PlazmaScript.g:222:18: ^( UNARY_MIN atom )
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
                    // grammar/PlazmaScript.g:223:6: '!' atom
                    {
                    char_literal143=(Token)match(input,Excl,FOLLOW_Excl_in_unaryExpr1472); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Excl.add(char_literal143);

                    pushFollow(FOLLOW_atom_in_unaryExpr1474);
                    atom144=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom144.getTree());


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
                    // 223:15: -> ^( NEGATE atom )
                    {
                        // grammar/PlazmaScript.g:223:18: ^( NEGATE atom )
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
                    // grammar/PlazmaScript.g:224:6: atom
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_atom_in_unaryExpr1489);
                    atom145=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, atom145.getTree());

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
    // grammar/PlazmaScript.g:227:1: atom : ( Integer | Number | Bool | Null | lookup );
    public final PlazmaScriptParser.atom_return atom() throws RecognitionException {
        PlazmaScriptParser.atom_return retval = new PlazmaScriptParser.atom_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Integer146=null;
        Token Number147=null;
        Token Bool148=null;
        Token Null149=null;
        PlazmaScriptParser.lookup_return lookup150 = null;


        Object Integer146_tree=null;
        Object Number147_tree=null;
        Object Bool148_tree=null;
        Object Null149_tree=null;

        try {
            // grammar/PlazmaScript.g:228:3: ( Integer | Number | Bool | Null | lookup )
            int alt28=5;
            switch ( input.LA(1) ) {
            case Integer:
                {
                alt28=1;
                }
                break;
            case Number:
                {
                alt28=2;
                }
                break;
            case Bool:
                {
                alt28=3;
                }
                break;
            case Null:
                {
                alt28=4;
                }
                break;
            case Identifier:
            case Println:
            case Print:
            case Assert:
            case Date:
            case String:
            case OBrace:
            case OBracket:
            case OParen:
            case ContextIdentifier:
                {
                alt28=5;
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
                    // grammar/PlazmaScript.g:228:6: Integer
                    {
                    root_0 = (Object)adaptor.nil();

                    Integer146=(Token)match(input,Integer,FOLLOW_Integer_in_atom1503); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Integer146_tree = (Object)adaptor.create(Integer146);
                    adaptor.addChild(root_0, Integer146_tree);
                    }

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:229:6: Number
                    {
                    root_0 = (Object)adaptor.nil();

                    Number147=(Token)match(input,Number,FOLLOW_Number_in_atom1510); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Number147_tree = (Object)adaptor.create(Number147);
                    adaptor.addChild(root_0, Number147_tree);
                    }

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScript.g:230:6: Bool
                    {
                    root_0 = (Object)adaptor.nil();

                    Bool148=(Token)match(input,Bool,FOLLOW_Bool_in_atom1517); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Bool148_tree = (Object)adaptor.create(Bool148);
                    adaptor.addChild(root_0, Bool148_tree);
                    }

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScript.g:232:6: Null
                    {
                    root_0 = (Object)adaptor.nil();

                    Null149=(Token)match(input,Null,FOLLOW_Null_in_atom1527); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Null149_tree = (Object)adaptor.create(Null149);
                    adaptor.addChild(root_0, Null149_tree);
                    }

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScript.g:233:6: lookup
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_lookup_in_atom1534);
                    lookup150=lookup();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lookup150.getTree());

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
    // grammar/PlazmaScript.g:236:1: list : '[' ( exprList )? ']' -> ^( LIST ( exprList )? ) ;
    public final PlazmaScriptParser.list_return list() throws RecognitionException {
        PlazmaScriptParser.list_return retval = new PlazmaScriptParser.list_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal151=null;
        Token char_literal153=null;
        PlazmaScriptParser.exprList_return exprList152 = null;


        Object char_literal151_tree=null;
        Object char_literal153_tree=null;
        RewriteRuleTokenStream stream_CBracket=new RewriteRuleTokenStream(adaptor,"token CBracket");
        RewriteRuleTokenStream stream_OBracket=new RewriteRuleTokenStream(adaptor,"token OBracket");
        RewriteRuleSubtreeStream stream_exprList=new RewriteRuleSubtreeStream(adaptor,"rule exprList");
        try {
            // grammar/PlazmaScript.g:237:3: ( '[' ( exprList )? ']' -> ^( LIST ( exprList )? ) )
            // grammar/PlazmaScript.g:237:6: '[' ( exprList )? ']'
            {
            char_literal151=(Token)match(input,OBracket,FOLLOW_OBracket_in_list1548); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBracket.add(char_literal151);

            // grammar/PlazmaScript.g:237:10: ( exprList )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( ((LA29_0>=Identifier && LA29_0<=Date)||(LA29_0>=Integer && LA29_0<=String)||LA29_0==Excl||LA29_0==Subtract||LA29_0==OBrace||LA29_0==OBracket||LA29_0==OParen||LA29_0==ContextIdentifier) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // grammar/PlazmaScript.g:0:0: exprList
                    {
                    pushFollow(FOLLOW_exprList_in_list1550);
                    exprList152=exprList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_exprList.add(exprList152.getTree());

                    }
                    break;

            }

            char_literal153=(Token)match(input,CBracket,FOLLOW_CBracket_in_list1553); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBracket.add(char_literal153);



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
            // 237:24: -> ^( LIST ( exprList )? )
            {
                // grammar/PlazmaScript.g:237:27: ^( LIST ( exprList )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LIST, "LIST"), root_1);

                // grammar/PlazmaScript.g:237:34: ( exprList )?
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
    // grammar/PlazmaScript.g:240:1: map : '{' ( ':' | exprMap ) '}' -> ^( MAP ( exprMap )? ) ;
    public final PlazmaScriptParser.map_return map() throws RecognitionException {
        PlazmaScriptParser.map_return retval = new PlazmaScriptParser.map_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal154=null;
        Token char_literal155=null;
        Token char_literal157=null;
        PlazmaScriptParser.exprMap_return exprMap156 = null;


        Object char_literal154_tree=null;
        Object char_literal155_tree=null;
        Object char_literal157_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_Colon=new RewriteRuleTokenStream(adaptor,"token Colon");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_exprMap=new RewriteRuleSubtreeStream(adaptor,"rule exprMap");
        try {
            // grammar/PlazmaScript.g:241:3: ( '{' ( ':' | exprMap ) '}' -> ^( MAP ( exprMap )? ) )
            // grammar/PlazmaScript.g:241:6: '{' ( ':' | exprMap ) '}'
            {
            char_literal154=(Token)match(input,OBrace,FOLLOW_OBrace_in_map1576); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal154);

            // grammar/PlazmaScript.g:241:10: ( ':' | exprMap )
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==Colon) ) {
                alt30=1;
            }
            else if ( ((LA30_0>=Identifier && LA30_0<=Date)||(LA30_0>=Integer && LA30_0<=String)||LA30_0==Excl||LA30_0==Subtract||LA30_0==OBrace||LA30_0==OBracket||LA30_0==OParen||LA30_0==ContextIdentifier) ) {
                alt30=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }
            switch (alt30) {
                case 1 :
                    // grammar/PlazmaScript.g:241:11: ':'
                    {
                    char_literal155=(Token)match(input,Colon,FOLLOW_Colon_in_map1579); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Colon.add(char_literal155);


                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:241:17: exprMap
                    {
                    pushFollow(FOLLOW_exprMap_in_map1583);
                    exprMap156=exprMap();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_exprMap.add(exprMap156.getTree());

                    }
                    break;

            }

            char_literal157=(Token)match(input,CBrace,FOLLOW_CBrace_in_map1586); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal157);



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
            // 241:30: -> ^( MAP ( exprMap )? )
            {
                // grammar/PlazmaScript.g:241:33: ^( MAP ( exprMap )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(MAP, "MAP"), root_1);

                // grammar/PlazmaScript.g:241:39: ( exprMap )?
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
    // grammar/PlazmaScript.g:244:1: lookup : ( functionCall ( indexes )? -> ^( LOOKUP functionCall ( indexes )? ) | list ( indexes )? -> ^( LOOKUP list ( indexes )? ) | map ( indexes )? -> ^( LOOKUP map ( indexes )? ) | Identifier ( indexes )? -> ^( LOOKUP Identifier ( indexes )? ) | anyIdentifier ( indexes )? -> ^( LOOKUP ( indexes )? ) | String ( indexes )? -> ^( LOOKUP String ( indexes )? ) | '(' expression ')' ( indexes )? -> ^( LOOKUP expression ( indexes )? ) );
    public final PlazmaScriptParser.lookup_return lookup() throws RecognitionException {
        PlazmaScriptParser.lookup_return retval = new PlazmaScriptParser.lookup_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Identifier164=null;
        Token String168=null;
        Token char_literal170=null;
        Token char_literal172=null;
        PlazmaScriptParser.functionCall_return functionCall158 = null;

        PlazmaScriptParser.indexes_return indexes159 = null;

        PlazmaScriptParser.list_return list160 = null;

        PlazmaScriptParser.indexes_return indexes161 = null;

        PlazmaScriptParser.map_return map162 = null;

        PlazmaScriptParser.indexes_return indexes163 = null;

        PlazmaScriptParser.indexes_return indexes165 = null;

        PlazmaScriptParser.anyIdentifier_return anyIdentifier166 = null;

        PlazmaScriptParser.indexes_return indexes167 = null;

        PlazmaScriptParser.indexes_return indexes169 = null;

        PlazmaScriptParser.expression_return expression171 = null;

        PlazmaScriptParser.indexes_return indexes173 = null;


        Object Identifier164_tree=null;
        Object String168_tree=null;
        Object char_literal170_tree=null;
        Object char_literal172_tree=null;
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
            // grammar/PlazmaScript.g:245:3: ( functionCall ( indexes )? -> ^( LOOKUP functionCall ( indexes )? ) | list ( indexes )? -> ^( LOOKUP list ( indexes )? ) | map ( indexes )? -> ^( LOOKUP map ( indexes )? ) | Identifier ( indexes )? -> ^( LOOKUP Identifier ( indexes )? ) | anyIdentifier ( indexes )? -> ^( LOOKUP ( indexes )? ) | String ( indexes )? -> ^( LOOKUP String ( indexes )? ) | '(' expression ')' ( indexes )? -> ^( LOOKUP expression ( indexes )? ) )
            int alt38=7;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA38_1 = input.LA(2);

                if ( (LA38_1==OParen) ) {
                    alt38=1;
                }
                else if ( (synpred62_PlazmaScript()) ) {
                    alt38=4;
                }
                else if ( (synpred64_PlazmaScript()) ) {
                    alt38=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 38, 1, input);

                    throw nvae;
                }
                }
                break;
            case Println:
            case Print:
            case Assert:
            case Date:
                {
                alt38=1;
                }
                break;
            case OBracket:
                {
                alt38=2;
                }
                break;
            case OBrace:
                {
                alt38=3;
                }
                break;
            case ContextIdentifier:
                {
                alt38=5;
                }
                break;
            case String:
                {
                alt38=6;
                }
                break;
            case OParen:
                {
                alt38=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }

            switch (alt38) {
                case 1 :
                    // grammar/PlazmaScript.g:245:6: functionCall ( indexes )?
                    {
                    pushFollow(FOLLOW_functionCall_in_lookup1609);
                    functionCall158=functionCall();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_functionCall.add(functionCall158.getTree());
                    // grammar/PlazmaScript.g:245:19: ( indexes )?
                    int alt31=2;
                    int LA31_0 = input.LA(1);

                    if ( (LA31_0==OBracket||LA31_0==86) ) {
                        alt31=1;
                    }
                    switch (alt31) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1611);
                            indexes159=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes159.getTree());

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
                    // 245:34: -> ^( LOOKUP functionCall ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:245:37: ^( LOOKUP functionCall ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_functionCall.nextTree());
                        // grammar/PlazmaScript.g:245:59: ( indexes )?
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
                    // grammar/PlazmaScript.g:246:6: list ( indexes )?
                    {
                    pushFollow(FOLLOW_list_in_lookup1636);
                    list160=list();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_list.add(list160.getTree());
                    // grammar/PlazmaScript.g:246:11: ( indexes )?
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( (LA32_0==OBracket||LA32_0==86) ) {
                        alt32=1;
                    }
                    switch (alt32) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1638);
                            indexes161=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes161.getTree());

                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: indexes, list
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 246:34: -> ^( LOOKUP list ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:246:37: ^( LOOKUP list ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_list.nextTree());
                        // grammar/PlazmaScript.g:246:51: ( indexes )?
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
                    // grammar/PlazmaScript.g:247:6: map ( indexes )?
                    {
                    pushFollow(FOLLOW_map_in_lookup1671);
                    map162=map();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_map.add(map162.getTree());
                    // grammar/PlazmaScript.g:247:10: ( indexes )?
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==OBracket||LA33_0==86) ) {
                        alt33=1;
                    }
                    switch (alt33) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1673);
                            indexes163=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes163.getTree());

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
                    // 247:34: -> ^( LOOKUP map ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:247:37: ^( LOOKUP map ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_map.nextTree());
                        // grammar/PlazmaScript.g:247:50: ( indexes )?
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
                    // grammar/PlazmaScript.g:248:6: Identifier ( indexes )?
                    {
                    Identifier164=(Token)match(input,Identifier,FOLLOW_Identifier_in_lookup1709); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier164);

                    // grammar/PlazmaScript.g:248:17: ( indexes )?
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==OBracket||LA34_0==86) ) {
                        alt34=1;
                    }
                    switch (alt34) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1711);
                            indexes165=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes165.getTree());

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
                    // 248:34: -> ^( LOOKUP Identifier ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:248:37: ^( LOOKUP Identifier ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_Identifier.nextNode());
                        // grammar/PlazmaScript.g:248:57: ( indexes )?
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
                    // grammar/PlazmaScript.g:249:6: anyIdentifier ( indexes )?
                    {
                    pushFollow(FOLLOW_anyIdentifier_in_lookup1738);
                    anyIdentifier166=anyIdentifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_anyIdentifier.add(anyIdentifier166.getTree());
                    // grammar/PlazmaScript.g:249:20: ( indexes )?
                    int alt35=2;
                    int LA35_0 = input.LA(1);

                    if ( (LA35_0==OBracket||LA35_0==86) ) {
                        alt35=1;
                    }
                    switch (alt35) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1740);
                            indexes167=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes167.getTree());

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
                    // 249:34: -> ^( LOOKUP ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:249:37: ^( LOOKUP ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, new CommonTree(new CommonToken(Identifier, (anyIdentifier166!=null?input.toString(anyIdentifier166.start,anyIdentifier166.stop):null))));
                        // grammar/PlazmaScript.g:249:113: ( indexes )?
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
                    // grammar/PlazmaScript.g:250:6: String ( indexes )?
                    {
                    String168=(Token)match(input,String,FOLLOW_String_in_lookup1766); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_String.add(String168);

                    // grammar/PlazmaScript.g:250:13: ( indexes )?
                    int alt36=2;
                    int LA36_0 = input.LA(1);

                    if ( (LA36_0==OBracket||LA36_0==86) ) {
                        alt36=1;
                    }
                    switch (alt36) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1768);
                            indexes169=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes169.getTree());

                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: indexes, String
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 250:34: -> ^( LOOKUP String ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:250:37: ^( LOOKUP String ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_String.nextNode());
                        // grammar/PlazmaScript.g:250:53: ( indexes )?
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
                    // grammar/PlazmaScript.g:251:6: '(' expression ')' ( indexes )?
                    {
                    char_literal170=(Token)match(input,OParen,FOLLOW_OParen_in_lookup1799); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal170);

                    pushFollow(FOLLOW_expression_in_lookup1801);
                    expression171=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression171.getTree());
                    char_literal172=(Token)match(input,CParen,FOLLOW_CParen_in_lookup1803); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal172);

                    // grammar/PlazmaScript.g:251:25: ( indexes )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==OBracket||LA37_0==86) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1805);
                            indexes173=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes173.getTree());

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
                    // 251:34: -> ^( LOOKUP expression ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:251:37: ^( LOOKUP expression ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_expression.nextTree());
                        // grammar/PlazmaScript.g:251:57: ( indexes )?
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
    // grammar/PlazmaScript.g:254:1: indexes : ( tail )+ -> ^( TAILS ( tail )+ ) ;
    public final PlazmaScriptParser.indexes_return indexes() throws RecognitionException {
        PlazmaScriptParser.indexes_return retval = new PlazmaScriptParser.indexes_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        PlazmaScriptParser.tail_return tail174 = null;


        RewriteRuleSubtreeStream stream_tail=new RewriteRuleSubtreeStream(adaptor,"rule tail");
        try {
            // grammar/PlazmaScript.g:256:3: ( ( tail )+ -> ^( TAILS ( tail )+ ) )
            // grammar/PlazmaScript.g:256:6: ( tail )+
            {
            // grammar/PlazmaScript.g:256:6: ( tail )+
            int cnt39=0;
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==OBracket||LA39_0==86) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // grammar/PlazmaScript.g:256:7: tail
            	    {
            	    pushFollow(FOLLOW_tail_in_indexes1835);
            	    tail174=tail();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_tail.add(tail174.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt39 >= 1 ) break loop39;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(39, input);
                        throw eee;
                }
                cnt39++;
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
            // 256:14: -> ^( TAILS ( tail )+ )
            {
                // grammar/PlazmaScript.g:256:17: ^( TAILS ( tail )+ )
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
    // grammar/PlazmaScript.g:259:1: tail : ( '[' expression ']' -> ^( INDEX expression ) | '.' Identifier -> ^( ATTRIBUTE Identifier ) | '.' Identifier '(' ( exprList )? ')' -> ^( CALL Identifier ( exprList )? ) );
    public final PlazmaScriptParser.tail_return tail() throws RecognitionException {
        PlazmaScriptParser.tail_return retval = new PlazmaScriptParser.tail_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal175=null;
        Token char_literal177=null;
        Token char_literal178=null;
        Token Identifier179=null;
        Token char_literal180=null;
        Token Identifier181=null;
        Token char_literal182=null;
        Token char_literal184=null;
        PlazmaScriptParser.expression_return expression176 = null;

        PlazmaScriptParser.exprList_return exprList183 = null;


        Object char_literal175_tree=null;
        Object char_literal177_tree=null;
        Object char_literal178_tree=null;
        Object Identifier179_tree=null;
        Object char_literal180_tree=null;
        Object Identifier181_tree=null;
        Object char_literal182_tree=null;
        Object char_literal184_tree=null;
        RewriteRuleTokenStream stream_CBracket=new RewriteRuleTokenStream(adaptor,"token CBracket");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_OBracket=new RewriteRuleTokenStream(adaptor,"token OBracket");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
        RewriteRuleTokenStream stream_86=new RewriteRuleTokenStream(adaptor,"token 86");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_exprList=new RewriteRuleSubtreeStream(adaptor,"rule exprList");
        try {
            // grammar/PlazmaScript.g:260:2: ( '[' expression ']' -> ^( INDEX expression ) | '.' Identifier -> ^( ATTRIBUTE Identifier ) | '.' Identifier '(' ( exprList )? ')' -> ^( CALL Identifier ( exprList )? ) )
            int alt41=3;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==OBracket) ) {
                alt41=1;
            }
            else if ( (LA41_0==86) ) {
                int LA41_2 = input.LA(2);

                if ( (LA41_2==Identifier) ) {
                    int LA41_3 = input.LA(3);

                    if ( (LA41_3==OParen) ) {
                        alt41=3;
                    }
                    else if ( (LA41_3==EOF||(LA41_3>=In && LA41_3<=Range)||(LA41_3>=Or && LA41_3<=Pow)||(LA41_3>=GT && LA41_3<=Modulus)||(LA41_3>=CBrace && LA41_3<=CBracket)||(LA41_3>=CParen && LA41_3<=Colon)||LA41_3==86) ) {
                        alt41=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 41, 3, input);

                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 41, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;
            }
            switch (alt41) {
                case 1 :
                    // grammar/PlazmaScript.g:260:4: '[' expression ']'
                    {
                    char_literal175=(Token)match(input,OBracket,FOLLOW_OBracket_in_tail1858); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OBracket.add(char_literal175);

                    pushFollow(FOLLOW_expression_in_tail1860);
                    expression176=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression176.getTree());
                    char_literal177=(Token)match(input,CBracket,FOLLOW_CBracket_in_tail1862); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CBracket.add(char_literal177);



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
                    // 260:37: -> ^( INDEX expression )
                    {
                        // grammar/PlazmaScript.g:260:40: ^( INDEX expression )
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
                    // grammar/PlazmaScript.g:261:4: '.' Identifier
                    {
                    char_literal178=(Token)match(input,86,FOLLOW_86_in_tail1889); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_86.add(char_literal178);

                    Identifier179=(Token)match(input,Identifier,FOLLOW_Identifier_in_tail1891); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier179);



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
                    // 261:37: -> ^( ATTRIBUTE Identifier )
                    {
                        // grammar/PlazmaScript.g:261:40: ^( ATTRIBUTE Identifier )
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
                    // grammar/PlazmaScript.g:262:4: '.' Identifier '(' ( exprList )? ')'
                    {
                    char_literal180=(Token)match(input,86,FOLLOW_86_in_tail1922); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_86.add(char_literal180);

                    Identifier181=(Token)match(input,Identifier,FOLLOW_Identifier_in_tail1924); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier181);

                    char_literal182=(Token)match(input,OParen,FOLLOW_OParen_in_tail1926); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal182);

                    // grammar/PlazmaScript.g:262:23: ( exprList )?
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( ((LA40_0>=Identifier && LA40_0<=Date)||(LA40_0>=Integer && LA40_0<=String)||LA40_0==Excl||LA40_0==Subtract||LA40_0==OBrace||LA40_0==OBracket||LA40_0==OParen||LA40_0==ContextIdentifier) ) {
                        alt40=1;
                    }
                    switch (alt40) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_tail1928);
                            exprList183=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList183.getTree());

                            }
                            break;

                    }

                    char_literal184=(Token)match(input,CParen,FOLLOW_CParen_in_tail1931); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal184);



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
                    // 262:37: -> ^( CALL Identifier ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:262:40: ^( CALL Identifier ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CALL, "CALL"), root_1);

                        adaptor.addChild(root_1, stream_Identifier.nextNode());
                        // grammar/PlazmaScript.g:262:58: ( exprList )?
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
    // grammar/PlazmaScript.g:345:1: anyIdentifier : ( ContextIdentifier | Identifier );
    public final PlazmaScriptParser.anyIdentifier_return anyIdentifier() throws RecognitionException {
        PlazmaScriptParser.anyIdentifier_return retval = new PlazmaScriptParser.anyIdentifier_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set185=null;

        Object set185_tree=null;

        try {
            // grammar/PlazmaScript.g:346:3: ( ContextIdentifier | Identifier )
            // grammar/PlazmaScript.g:
            {
            root_0 = (Object)adaptor.nil();

            set185=(Token)input.LT(1);
            if ( input.LA(1)==Identifier||input.LA(1)==ContextIdentifier ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set185));
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
        int alt42=2;
        int LA42_0 = input.LA(1);

        if ( (LA42_0==Var) ) {
            alt42=1;
        }
        switch (alt42) {
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
        int alt43=2;
        int LA43_0 = input.LA(1);

        if ( (LA43_0==OBracket||LA43_0==86) ) {
            alt43=1;
        }
        switch (alt43) {
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

    // $ANTLR start synpred62_PlazmaScript
    public final void synpred62_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:248:6: ( Identifier ( indexes )? )
        // grammar/PlazmaScript.g:248:6: Identifier ( indexes )?
        {
        match(input,Identifier,FOLLOW_Identifier_in_synpred62_PlazmaScript1709); if (state.failed) return ;
        // grammar/PlazmaScript.g:248:17: ( indexes )?
        int alt49=2;
        int LA49_0 = input.LA(1);

        if ( (LA49_0==OBracket||LA49_0==86) ) {
            alt49=1;
        }
        switch (alt49) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: indexes
                {
                pushFollow(FOLLOW_indexes_in_synpred62_PlazmaScript1711);
                indexes();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred62_PlazmaScript

    // $ANTLR start synpred64_PlazmaScript
    public final void synpred64_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:249:6: ( anyIdentifier ( indexes )? )
        // grammar/PlazmaScript.g:249:6: anyIdentifier ( indexes )?
        {
        pushFollow(FOLLOW_anyIdentifier_in_synpred64_PlazmaScript1738);
        anyIdentifier();

        state._fsp--;
        if (state.failed) return ;
        // grammar/PlazmaScript.g:249:20: ( indexes )?
        int alt50=2;
        int LA50_0 = input.LA(1);

        if ( (LA50_0==OBracket||LA50_0==86) ) {
            alt50=1;
        }
        switch (alt50) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: indexes
                {
                pushFollow(FOLLOW_indexes_in_synpred64_PlazmaScript1740);
                indexes();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred64_PlazmaScript

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
    public final boolean synpred62_PlazmaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred62_PlazmaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred64_PlazmaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred64_PlazmaScript_fragment(); // can never throw exception
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
        "\22\uffff";
    static final String DFA3_eofS =
        "\22\uffff";
    static final String DFA3_minS =
        "\1\37\1\uffff\6\0\12\uffff";
    static final String DFA3_maxS =
        "\1\120\1\uffff\6\0\12\uffff";
    static final String DFA3_acceptS =
        "\1\uffff\1\1\6\uffff\1\3\3\uffff\1\4\1\5\1\6\1\7\1\10\1\2";
    static final String DFA3_specialS =
        "\2\uffff\1\0\1\1\1\2\1\3\1\4\1\5\12\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\17\1\20\1\2\1\4\1\5\1\6\1\7\1\14\1\uffff\1\1\1\uffff\1\15"+
            "\1\16\7\uffff\1\10\17\uffff\1\10\1\uffff\1\10\1\uffff\1\10\10"+
            "\uffff\1\3",
            "",
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

                        else if ( (synpred5_PlazmaScript()) ) {s = 17;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 8;}

                         
                        input.seek(index3_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA3_3 = input.LA(1);

                         
                        int index3_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_PlazmaScript()) ) {s = 1;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 8;}

                         
                        input.seek(index3_3);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA3_4 = input.LA(1);

                         
                        int index3_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 17;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 8;}

                         
                        input.seek(index3_4);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA3_5 = input.LA(1);

                         
                        int index3_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 17;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 8;}

                         
                        input.seek(index3_5);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA3_6 = input.LA(1);

                         
                        int index3_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 17;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 8;}

                         
                        input.seek(index3_6);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA3_7 = input.LA(1);

                         
                        int index3_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 17;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 8;}

                         
                        input.seek(index3_7);
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
    public static final BitSet FOLLOW_statement_in_block224 = new BitSet(new long[]{0x00080F7FC0000002L,0x00000000000100A8L});
    public static final BitSet FOLLOW_functionDecl_in_block228 = new BitSet(new long[]{0x00080F7FC0000002L,0x00000000000100A8L});
    public static final BitSet FOLLOW_Return_in_block233 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_expression_in_block235 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_SColon_in_block237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_statement279 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_SColon_in_statement281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_statement294 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_SColon_in_statement296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_statement307 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_SColon_in_statement309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStatement_in_statement353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forStatement_in_statement360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whileStatement_in_statement367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Break_in_statement374 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_SColon_in_statement376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Continue_in_statement387 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_SColon_in_statement389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDef_in_assignment410 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_Identifier_in_assignment413 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400420L});
    public static final BitSet FOLLOW_indexes_in_assignment415 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_Assign_in_assignment418 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_expression_in_assignment420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDef_in_assignment443 = new BitSet(new long[]{0x0000010200000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_anyIdentifier_in_assignment446 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400420L});
    public static final BitSet FOLLOW_indexes_in_assignment448 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_Assign_in_assignment451 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_expression_in_assignment453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_functionCall483 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_OParen_in_functionCall485 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000101A8L});
    public static final BitSet FOLLOW_exprList_in_functionCall487 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_CParen_in_functionCall490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Println_in_functionCall508 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_OParen_in_functionCall510 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000101A8L});
    public static final BitSet FOLLOW_expression_in_functionCall512 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_CParen_in_functionCall515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Print_in_functionCall534 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_OParen_in_functionCall536 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_expression_in_functionCall538 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_CParen_in_functionCall540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Assert_in_functionCall561 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_OParen_in_functionCall563 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_expression_in_functionCall565 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_CParen_in_functionCall567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Date_in_functionCall588 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_OParen_in_functionCall590 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000101A8L});
    public static final BitSet FOLLOW_exprList_in_functionCall592 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_CParen_in_functionCall595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStat_in_ifStatement631 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_elseIfStat_in_ifStatement633 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_elseStat_in_ifStatement636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_If_in_ifStat665 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_OParen_in_ifStat667 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_expression_in_ifStat669 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_CParen_in_ifStat671 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_OBrace_in_ifStat673 = new BitSet(new long[]{0x00080F7FC0000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_block_in_ifStat675 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_CBrace_in_ifStat677 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Else_in_elseIfStat701 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_If_in_elseIfStat703 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_OParen_in_elseIfStat705 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_expression_in_elseIfStat707 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_CParen_in_elseIfStat709 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_OBrace_in_elseIfStat711 = new BitSet(new long[]{0x00080F7FC0000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_block_in_elseIfStat713 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_CBrace_in_elseIfStat715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Else_in_elseStat739 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_OBrace_in_elseStat741 = new BitSet(new long[]{0x00080F7FC0000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_block_in_elseStat743 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_CBrace_in_elseStat745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Var_in_variableDef765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Def_in_functionDecl797 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_Identifier_in_functionDecl799 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_OParen_in_functionDecl801 = new BitSet(new long[]{0x0000000200000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_idList_in_functionDecl803 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_CParen_in_functionDecl806 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_OBrace_in_functionDecl808 = new BitSet(new long[]{0x00080F7FC0000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_block_in_functionDecl810 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_CBrace_in_functionDecl812 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_For_in_forStatement841 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_OParen_in_forStatement843 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_Identifier_in_forStatement845 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_In_in_forStatement847 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_expression_in_forStatement849 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_CParen_in_forStatement851 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_OBrace_in_forStatement853 = new BitSet(new long[]{0x00080F7FC0000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_block_in_forStatement855 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_CBrace_in_forStatement857 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_While_in_whileStatement892 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_OParen_in_whileStatement894 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_expression_in_whileStatement896 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_CParen_in_whileStatement898 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_OBrace_in_whileStatement900 = new BitSet(new long[]{0x00080F7FC0000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_block_in_whileStatement902 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_CBrace_in_whileStatement904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_idList928 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000800L});
    public static final BitSet FOLLOW_Comma_in_idList931 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_Identifier_in_idList933 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000800L});
    public static final BitSet FOLLOW_expression_in_exprList958 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000800L});
    public static final BitSet FOLLOW_Comma_in_exprList961 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_expression_in_exprList963 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000800L});
    public static final BitSet FOLLOW_expression_in_exprPair989 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_Colon_in_exprPair991 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_expression_in_exprPair993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprPair_in_exprMap1018 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000800L});
    public static final BitSet FOLLOW_Comma_in_exprMap1021 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_exprPair_in_exprMap1023 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000800L});
    public static final BitSet FOLLOW_condExpr_in_expression1049 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orExpr_in_condExpr1064 = new BitSet(new long[]{0x0000700000000002L,0x0000000000001000L});
    public static final BitSet FOLLOW_QMark_in_condExpr1079 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_expression_in_condExpr1083 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_Colon_in_condExpr1085 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_expression_in_condExpr1089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_In_in_condExpr1112 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_expression_in_condExpr1114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RangeE_in_condExpr1159 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_expression_in_condExpr1161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Range_in_condExpr1196 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_expression_in_condExpr1198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_andExpr_in_orExpr1263 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_Or_in_orExpr1266 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_andExpr_in_orExpr1269 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_equExpr_in_andExpr1285 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_And_in_andExpr1288 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_equExpr_in_andExpr1291 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_relExpr_in_equExpr1307 = new BitSet(new long[]{0x00C0000000000002L});
    public static final BitSet FOLLOW_set_in_equExpr1310 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_relExpr_in_equExpr1319 = new BitSet(new long[]{0x00C0000000000002L});
    public static final BitSet FOLLOW_addExpr_in_relExpr1335 = new BitSet(new long[]{0x3300000000000002L});
    public static final BitSet FOLLOW_set_in_relExpr1338 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_addExpr_in_relExpr1355 = new BitSet(new long[]{0x3300000000000002L});
    public static final BitSet FOLLOW_mulExpr_in_addExpr1371 = new BitSet(new long[]{0xC000000000000002L});
    public static final BitSet FOLLOW_set_in_addExpr1374 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_mulExpr_in_addExpr1383 = new BitSet(new long[]{0xC000000000000002L});
    public static final BitSet FOLLOW_powExpr_in_mulExpr1399 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000007L});
    public static final BitSet FOLLOW_set_in_mulExpr1402 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_powExpr_in_mulExpr1415 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000007L});
    public static final BitSet FOLLOW_unaryExpr_in_powExpr1431 = new BitSet(new long[]{0x0400000000000002L});
    public static final BitSet FOLLOW_Pow_in_powExpr1434 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_unaryExpr_in_powExpr1437 = new BitSet(new long[]{0x0400000000000002L});
    public static final BitSet FOLLOW_Subtract_in_unaryExpr1455 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Excl_in_unaryExpr1472 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Integer_in_atom1503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Number_in_atom1510 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Bool_in_atom1517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Null_in_atom1527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_atom1534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OBracket_in_list1548 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100E8L});
    public static final BitSet FOLLOW_exprList_in_list1550 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_CBracket_in_list1553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OBrace_in_map1576 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000120A8L});
    public static final BitSet FOLLOW_Colon_in_map1579 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_exprMap_in_map1583 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_CBrace_in_map1586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_lookup1609 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400020L});
    public static final BitSet FOLLOW_indexes_in_lookup1611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_in_lookup1636 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400020L});
    public static final BitSet FOLLOW_indexes_in_lookup1638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_map_in_lookup1671 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400020L});
    public static final BitSet FOLLOW_indexes_in_lookup1673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_lookup1709 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400020L});
    public static final BitSet FOLLOW_indexes_in_lookup1711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_anyIdentifier_in_lookup1738 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400020L});
    public static final BitSet FOLLOW_indexes_in_lookup1740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_String_in_lookup1766 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400020L});
    public static final BitSet FOLLOW_indexes_in_lookup1768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OParen_in_lookup1799 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_expression_in_lookup1801 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_CParen_in_lookup1803 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400020L});
    public static final BitSet FOLLOW_indexes_in_lookup1805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tail_in_indexes1835 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400020L});
    public static final BitSet FOLLOW_OBracket_in_tail1858 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_expression_in_tail1860 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_CBracket_in_tail1862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_86_in_tail1889 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_Identifier_in_tail1891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_86_in_tail1922 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_Identifier_in_tail1924 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_OParen_in_tail1926 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000101A8L});
    public static final BitSet FOLLOW_exprList_in_tail1928 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_CParen_in_tail1931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_anyIdentifier0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_synpred4_PlazmaScript279 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_SColon_in_synpred4_PlazmaScript281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_synpred5_PlazmaScript294 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_SColon_in_synpred5_PlazmaScript296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_synpred6_PlazmaScript307 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_SColon_in_synpred6_PlazmaScript309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDef_in_synpred13_PlazmaScript410 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_Identifier_in_synpred13_PlazmaScript413 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400420L});
    public static final BitSet FOLLOW_indexes_in_synpred13_PlazmaScript415 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_Assign_in_synpred13_PlazmaScript418 = new BitSet(new long[]{0x880F813E00000000L,0x00000000000100A8L});
    public static final BitSet FOLLOW_expression_in_synpred13_PlazmaScript420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_synpred62_PlazmaScript1709 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400020L});
    public static final BitSet FOLLOW_indexes_in_synpred62_PlazmaScript1711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_anyIdentifier_in_synpred64_PlazmaScript1738 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400020L});
    public static final BitSet FOLLOW_indexes_in_synpred64_PlazmaScript1740 = new BitSet(new long[]{0x0000000000000002L});

}