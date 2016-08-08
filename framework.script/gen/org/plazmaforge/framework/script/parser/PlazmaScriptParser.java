// $ANTLR 3.2 Sep 23, 2009 12:02:23 grammar/PlazmaScript.g 2016-08-08 13:14:47

  package org.plazmaforge.framework.script.parser;
  import org.plazmaforge.framework.script.*; 
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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BLOCK", "RETURN", "STATEMENTS", "ASSIGNMENT", "FUNC_CALL", "EXP", "EXP_PAIR", "EXP_MAP", "EXP_LIST", "ID_LIST", "IF", "TERNARY", "UNARY_MIN", "NEGATE", "FUNCTION", "INDEXES", "INDEX", "ATTRIBUTE", "CALL", "TAIL", "TAILS", "MAP", "LIST", "SET", "LOOKUP", "BREAK", "CONTINUE", "Return", "Break", "Continue", "Identifier", "Println", "Print", "Assert", "Date", "List", "Set", "If", "Else", "Var", "Def", "For", "While", "In", "RangeE", "Range", "Integer", "Number", "Bool", "Null", "NaN", "Infinity", "String", "XorWord", "Or", "BitOr", "OrWord", "And", "BitAnd", "AndWord", "Equals", "NEquals", "GTEquals", "LTEquals", "Pow", "Not", "NotWord", "GT", "LT", "Add", "Subtract", "Multiply", "Divide", "Modulus", "OBrace", "CBrace", "OBracket", "CBracket", "OParen", "CParen", "SColon", "Assign", "Comma", "QMark", "Colon", "Int", "Digit", "ContextIdentifier", "Comment", "Space", "YYYY", "MM", "DD", "'.'"
    };
    public static final int FUNCTION=18;
    public static final int LT=72;
    public static final int TERNARY=15;
    public static final int EXP_LIST=12;
    public static final int Date=38;
    public static final int DD=96;
    public static final int EOF=-1;
    public static final int QMark=87;
    public static final int NotWord=70;
    public static final int BREAK=29;
    public static final int Identifier=34;
    public static final int Int=89;
    public static final int FUNC_CALL=8;
    public static final int CParen=83;
    public static final int Comment=92;
    public static final int EXP=9;
    public static final int MM=95;
    public static final int RETURN=5;
    public static final int CBrace=79;
    public static final int T__97=97;
    public static final int Null=53;
    public static final int OrWord=60;
    public static final int ContextIdentifier=91;
    public static final int CBracket=81;
    public static final int Println=35;
    public static final int Bool=52;
    public static final int Modulus=77;
    public static final int AndWord=63;
    public static final int Colon=88;
    public static final int LIST=26;
    public static final int Def=44;
    public static final int LOOKUP=28;
    public static final int RangeE=48;
    public static final int Range=49;
    public static final int Break=32;
    public static final int BitOr=59;
    public static final int STATEMENTS=6;
    public static final int GT=71;
    public static final int CALL=22;
    public static final int Else=42;
    public static final int Equals=64;
    public static final int Var=43;
    public static final int XorWord=57;
    public static final int OParen=82;
    public static final int YYYY=94;
    public static final int Assert=37;
    public static final int ATTRIBUTE=21;
    public static final int While=46;
    public static final int ID_LIST=13;
    public static final int Add=73;
    public static final int Set=40;
    public static final int TAIL=23;
    public static final int IF=14;
    public static final int Space=93;
    public static final int INDEX=20;
    public static final int Assign=85;
    public static final int EXP_MAP=11;
    public static final int NaN=54;
    public static final int Number=51;
    public static final int CONTINUE=30;
    public static final int Print=36;
    public static final int GTEquals=66;
    public static final int String=56;
    public static final int Or=58;
    public static final int Return=31;
    public static final int If=41;
    public static final int And=61;
    public static final int In=47;
    public static final int NEquals=65;
    public static final int Continue=33;
    public static final int Subtract=74;
    public static final int EXP_PAIR=10;
    public static final int BitAnd=62;
    public static final int Multiply=75;
    public static final int OBrace=78;
    public static final int INDEXES=19;
    public static final int NEGATE=17;
    public static final int SET=27;
    public static final int Digit=90;
    public static final int For=45;
    public static final int Divide=76;
    public static final int List=39;
    public static final int TAILS=24;
    public static final int SColon=84;
    public static final int OBracket=80;
    public static final int BLOCK=4;
    public static final int MAP=25;
    public static final int Not=69;
    public static final int UNARY_MIN=16;
    public static final int ASSIGNMENT=7;
    public static final int Infinity=55;
    public static final int Comma=86;
    public static final int Integer=50;
    public static final int Pow=68;
    public static final int LTEquals=67;

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
    // grammar/PlazmaScript.g:69:1: parse : block EOF -> block ;
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
            // grammar/PlazmaScript.g:70:3: ( block EOF -> block )
            // grammar/PlazmaScript.g:70:6: block EOF
            {
            pushFollow(FOLLOW_block_in_parse208);
            block1=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block1.getTree());
            EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_parse210); if (state.failed) return retval; 
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
            // 70:16: -> block
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
    // grammar/PlazmaScript.g:73:1: block : ( statement | functionDecl )* ( Return expression ';' )? -> ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) ) ;
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
            // grammar/PlazmaScript.g:74:3: ( ( statement | functionDecl )* ( Return expression ';' )? -> ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) ) )
            // grammar/PlazmaScript.g:74:6: ( statement | functionDecl )* ( Return expression ';' )?
            {
            // grammar/PlazmaScript.g:74:6: ( statement | functionDecl )*
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
            	    // grammar/PlazmaScript.g:74:7: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_block229);
            	    statement3=statement();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_statement.add(statement3.getTree());

            	    }
            	    break;
            	case 2 :
            	    // grammar/PlazmaScript.g:74:19: functionDecl
            	    {
            	    pushFollow(FOLLOW_functionDecl_in_block233);
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

            // grammar/PlazmaScript.g:74:34: ( Return expression ';' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==Return) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // grammar/PlazmaScript.g:74:35: Return expression ';'
                    {
                    Return5=(Token)match(input,Return,FOLLOW_Return_in_block238); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Return.add(Return5);

                    pushFollow(FOLLOW_expression_in_block240);
                    expression6=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression6.getTree());
                    char_literal7=(Token)match(input,SColon,FOLLOW_SColon_in_block242); if (state.failed) return retval; 
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
            // 75:6: -> ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) )
            {
                // grammar/PlazmaScript.g:75:9: ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BLOCK, "BLOCK"), root_1);

                // grammar/PlazmaScript.g:75:17: ^( STATEMENTS ( statement )* )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(STATEMENTS, "STATEMENTS"), root_2);

                // grammar/PlazmaScript.g:75:30: ( statement )*
                while ( stream_statement.hasNext() ) {
                    adaptor.addChild(root_2, stream_statement.nextTree());

                }
                stream_statement.reset();

                adaptor.addChild(root_1, root_2);
                }
                // grammar/PlazmaScript.g:75:42: ^( RETURN ( expression )? )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(RETURN, "RETURN"), root_2);

                // grammar/PlazmaScript.g:75:51: ( expression )?
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
    // grammar/PlazmaScript.g:78:1: statement : ( assignment ';' -> assignment | functionCall ';' -> functionCall | lookup ';' -> lookup | ifStatement | forStatement | whileStatement | Break ';' -> Break | Continue ';' -> Continue );
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
            // grammar/PlazmaScript.g:79:3: ( assignment ';' -> assignment | functionCall ';' -> functionCall | lookup ';' -> lookup | ifStatement | forStatement | whileStatement | Break ';' -> Break | Continue ';' -> Continue )
            int alt3=8;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // grammar/PlazmaScript.g:79:6: assignment ';'
                    {
                    pushFollow(FOLLOW_assignment_in_statement284);
                    assignment8=assignment();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_assignment.add(assignment8.getTree());
                    char_literal9=(Token)match(input,SColon,FOLLOW_SColon_in_statement286); if (state.failed) return retval; 
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
                    // 79:23: -> assignment
                    {
                        adaptor.addChild(root_0, stream_assignment.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:80:6: functionCall ';'
                    {
                    pushFollow(FOLLOW_functionCall_in_statement299);
                    functionCall10=functionCall();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_functionCall.add(functionCall10.getTree());
                    char_literal11=(Token)match(input,SColon,FOLLOW_SColon_in_statement301); if (state.failed) return retval; 
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
                    // 80:23: -> functionCall
                    {
                        adaptor.addChild(root_0, stream_functionCall.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // grammar/PlazmaScript.g:81:6: lookup ';'
                    {
                    pushFollow(FOLLOW_lookup_in_statement312);
                    lookup12=lookup();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_lookup.add(lookup12.getTree());
                    char_literal13=(Token)match(input,SColon,FOLLOW_SColon_in_statement314); if (state.failed) return retval; 
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
                    // 81:17: -> lookup
                    {
                        adaptor.addChild(root_0, stream_lookup.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 4 :
                    // grammar/PlazmaScript.g:82:6: ifStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_ifStatement_in_statement358);
                    ifStatement14=ifStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ifStatement14.getTree());

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScript.g:83:6: forStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_forStatement_in_statement365);
                    forStatement15=forStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, forStatement15.getTree());

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScript.g:84:6: whileStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_whileStatement_in_statement372);
                    whileStatement16=whileStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, whileStatement16.getTree());

                    }
                    break;
                case 7 :
                    // grammar/PlazmaScript.g:85:6: Break ';'
                    {
                    Break17=(Token)match(input,Break,FOLLOW_Break_in_statement379); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Break.add(Break17);

                    char_literal18=(Token)match(input,SColon,FOLLOW_SColon_in_statement381); if (state.failed) return retval; 
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
                    // 85:16: -> Break
                    {
                        adaptor.addChild(root_0, stream_Break.nextNode());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 8 :
                    // grammar/PlazmaScript.g:86:6: Continue ';'
                    {
                    Continue19=(Token)match(input,Continue,FOLLOW_Continue_in_statement392); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Continue.add(Continue19);

                    char_literal20=(Token)match(input,SColon,FOLLOW_SColon_in_statement394); if (state.failed) return retval; 
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
                    // 86:19: -> Continue
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
    // grammar/PlazmaScript.g:90:1: assignment : ( ( variableDef )? Identifier ( indexes )? '=' expression -> ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression ) | ( variableDef )? anyIdentifier ( indexes )? '=' expression -> ^( ASSIGNMENT ( variableDef )? ( indexes )? expression ) );
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
            // grammar/PlazmaScript.g:91:3: ( ( variableDef )? Identifier ( indexes )? '=' expression -> ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression ) | ( variableDef )? anyIdentifier ( indexes )? '=' expression -> ^( ASSIGNMENT ( variableDef )? ( indexes )? expression ) )
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
                    // grammar/PlazmaScript.g:91:6: ( variableDef )? Identifier ( indexes )? '=' expression
                    {
                    // grammar/PlazmaScript.g:91:6: ( variableDef )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==Var) ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: variableDef
                            {
                            pushFollow(FOLLOW_variableDef_in_assignment415);
                            variableDef21=variableDef();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_variableDef.add(variableDef21.getTree());

                            }
                            break;

                    }

                    Identifier22=(Token)match(input,Identifier,FOLLOW_Identifier_in_assignment418); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier22);

                    // grammar/PlazmaScript.g:91:30: ( indexes )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==OBracket||LA5_0==97) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_assignment420);
                            indexes23=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes23.getTree());

                            }
                            break;

                    }

                    char_literal24=(Token)match(input,Assign,FOLLOW_Assign_in_assignment423); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Assign.add(char_literal24);

                    pushFollow(FOLLOW_expression_in_assignment425);
                    expression25=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression25.getTree());


                    // AST REWRITE
                    // elements: indexes, variableDef, Identifier, expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 91:54: -> ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression )
                    {
                        // grammar/PlazmaScript.g:91:57: ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ASSIGNMENT, "ASSIGNMENT"), root_1);

                        // grammar/PlazmaScript.g:91:70: ( variableDef )?
                        if ( stream_variableDef.hasNext() ) {
                            adaptor.addChild(root_1, stream_variableDef.nextTree());

                        }
                        stream_variableDef.reset();
                        adaptor.addChild(root_1, stream_Identifier.nextNode());
                        // grammar/PlazmaScript.g:91:94: ( indexes )?
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
                    // grammar/PlazmaScript.g:92:6: ( variableDef )? anyIdentifier ( indexes )? '=' expression
                    {
                    // grammar/PlazmaScript.g:92:6: ( variableDef )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==Var) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: variableDef
                            {
                            pushFollow(FOLLOW_variableDef_in_assignment448);
                            variableDef26=variableDef();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_variableDef.add(variableDef26.getTree());

                            }
                            break;

                    }

                    pushFollow(FOLLOW_anyIdentifier_in_assignment451);
                    anyIdentifier27=anyIdentifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_anyIdentifier.add(anyIdentifier27.getTree());
                    // grammar/PlazmaScript.g:92:33: ( indexes )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==OBracket||LA7_0==97) ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_assignment453);
                            indexes28=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes28.getTree());

                            }
                            break;

                    }

                    char_literal29=(Token)match(input,Assign,FOLLOW_Assign_in_assignment456); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Assign.add(char_literal29);

                    pushFollow(FOLLOW_expression_in_assignment458);
                    expression30=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression30.getTree());


                    // AST REWRITE
                    // elements: indexes, variableDef, expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 92:57: -> ^( ASSIGNMENT ( variableDef )? ( indexes )? expression )
                    {
                        // grammar/PlazmaScript.g:92:60: ^( ASSIGNMENT ( variableDef )? ( indexes )? expression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ASSIGNMENT, "ASSIGNMENT"), root_1);

                        // grammar/PlazmaScript.g:92:73: ( variableDef )?
                        if ( stream_variableDef.hasNext() ) {
                            adaptor.addChild(root_1, stream_variableDef.nextTree());

                        }
                        stream_variableDef.reset();
                        adaptor.addChild(root_1, new CommonTree(new CommonToken(Identifier, (anyIdentifier27!=null?input.toString(anyIdentifier27.start,anyIdentifier27.stop):null))));
                        // grammar/PlazmaScript.g:92:153: ( indexes )?
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
    // grammar/PlazmaScript.g:95:1: functionCall : ( Identifier '(' ( exprList )? ')' -> ^( FUNC_CALL Identifier ( exprList )? ) | Println '(' ( expression )? ')' -> ^( FUNC_CALL Println ( expression )? ) | Print '(' expression ')' -> ^( FUNC_CALL Print expression ) | Assert '(' expression ')' -> ^( FUNC_CALL Assert expression ) | Date '(' ( exprList )? ')' -> ^( FUNC_CALL Date ( exprList )? ) | List '(' ( exprList )? ')' -> ^( FUNC_CALL List ( exprList )? ) | Set '(' ( exprList )? ')' -> ^( FUNC_CALL Set ( exprList )? ) );
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
        Token Set55=null;
        Token char_literal56=null;
        Token char_literal58=null;
        PlazmaScriptParser.exprList_return exprList33 = null;

        PlazmaScriptParser.expression_return expression37 = null;

        PlazmaScriptParser.expression_return expression41 = null;

        PlazmaScriptParser.expression_return expression45 = null;

        PlazmaScriptParser.exprList_return exprList49 = null;

        PlazmaScriptParser.exprList_return exprList53 = null;

        PlazmaScriptParser.exprList_return exprList57 = null;


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
        Object Set55_tree=null;
        Object char_literal56_tree=null;
        Object char_literal58_tree=null;
        RewriteRuleTokenStream stream_Println=new RewriteRuleTokenStream(adaptor,"token Println");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_Date=new RewriteRuleTokenStream(adaptor,"token Date");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_Set=new RewriteRuleTokenStream(adaptor,"token Set");
        RewriteRuleTokenStream stream_List=new RewriteRuleTokenStream(adaptor,"token List");
        RewriteRuleTokenStream stream_Assert=new RewriteRuleTokenStream(adaptor,"token Assert");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
        RewriteRuleTokenStream stream_Print=new RewriteRuleTokenStream(adaptor,"token Print");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_exprList=new RewriteRuleSubtreeStream(adaptor,"rule exprList");
        try {
            // grammar/PlazmaScript.g:96:3: ( Identifier '(' ( exprList )? ')' -> ^( FUNC_CALL Identifier ( exprList )? ) | Println '(' ( expression )? ')' -> ^( FUNC_CALL Println ( expression )? ) | Print '(' expression ')' -> ^( FUNC_CALL Print expression ) | Assert '(' expression ')' -> ^( FUNC_CALL Assert expression ) | Date '(' ( exprList )? ')' -> ^( FUNC_CALL Date ( exprList )? ) | List '(' ( exprList )? ')' -> ^( FUNC_CALL List ( exprList )? ) | Set '(' ( exprList )? ')' -> ^( FUNC_CALL Set ( exprList )? ) )
            int alt14=7;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                alt14=1;
                }
                break;
            case Println:
                {
                alt14=2;
                }
                break;
            case Print:
                {
                alt14=3;
                }
                break;
            case Assert:
                {
                alt14=4;
                }
                break;
            case Date:
                {
                alt14=5;
                }
                break;
            case List:
                {
                alt14=6;
                }
                break;
            case Set:
                {
                alt14=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // grammar/PlazmaScript.g:96:6: Identifier '(' ( exprList )? ')'
                    {
                    Identifier31=(Token)match(input,Identifier,FOLLOW_Identifier_in_functionCall488); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier31);

                    char_literal32=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall490); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal32);

                    // grammar/PlazmaScript.g:96:21: ( exprList )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( ((LA9_0>=Identifier && LA9_0<=Set)||(LA9_0>=Integer && LA9_0<=String)||(LA9_0>=Not && LA9_0<=NotWord)||LA9_0==Subtract||LA9_0==OBrace||LA9_0==OBracket||LA9_0==OParen||LA9_0==ContextIdentifier) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall492);
                            exprList33=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList33.getTree());

                            }
                            break;

                    }

                    char_literal34=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall495); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal34);



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
                    // 96:35: -> ^( FUNC_CALL Identifier ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:96:38: ^( FUNC_CALL Identifier ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Identifier.nextNode());
                        // grammar/PlazmaScript.g:96:61: ( exprList )?
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
                    // grammar/PlazmaScript.g:97:6: Println '(' ( expression )? ')'
                    {
                    Println35=(Token)match(input,Println,FOLLOW_Println_in_functionCall513); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Println.add(Println35);

                    char_literal36=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall515); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal36);

                    // grammar/PlazmaScript.g:97:18: ( expression )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( ((LA10_0>=Identifier && LA10_0<=Set)||(LA10_0>=Integer && LA10_0<=String)||(LA10_0>=Not && LA10_0<=NotWord)||LA10_0==Subtract||LA10_0==OBrace||LA10_0==OBracket||LA10_0==OParen||LA10_0==ContextIdentifier) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: expression
                            {
                            pushFollow(FOLLOW_expression_in_functionCall517);
                            expression37=expression();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_expression.add(expression37.getTree());

                            }
                            break;

                    }

                    char_literal38=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall520); if (state.failed) return retval; 
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
                    // 97:35: -> ^( FUNC_CALL Println ( expression )? )
                    {
                        // grammar/PlazmaScript.g:97:38: ^( FUNC_CALL Println ( expression )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Println.nextNode());
                        // grammar/PlazmaScript.g:97:58: ( expression )?
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
                    // grammar/PlazmaScript.g:98:6: Print '(' expression ')'
                    {
                    Print39=(Token)match(input,Print,FOLLOW_Print_in_functionCall539); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Print.add(Print39);

                    char_literal40=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall541); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal40);

                    pushFollow(FOLLOW_expression_in_functionCall543);
                    expression41=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression41.getTree());
                    char_literal42=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall545); if (state.failed) return retval; 
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
                    // 98:35: -> ^( FUNC_CALL Print expression )
                    {
                        // grammar/PlazmaScript.g:98:38: ^( FUNC_CALL Print expression )
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
                    // grammar/PlazmaScript.g:99:6: Assert '(' expression ')'
                    {
                    Assert43=(Token)match(input,Assert,FOLLOW_Assert_in_functionCall566); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Assert.add(Assert43);

                    char_literal44=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall568); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal44);

                    pushFollow(FOLLOW_expression_in_functionCall570);
                    expression45=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression45.getTree());
                    char_literal46=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall572); if (state.failed) return retval; 
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
                    // 99:35: -> ^( FUNC_CALL Assert expression )
                    {
                        // grammar/PlazmaScript.g:99:38: ^( FUNC_CALL Assert expression )
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
                    Date47=(Token)match(input,Date,FOLLOW_Date_in_functionCall592); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Date.add(Date47);

                    char_literal48=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall594); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal48);

                    // grammar/PlazmaScript.g:100:15: ( exprList )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( ((LA11_0>=Identifier && LA11_0<=Set)||(LA11_0>=Integer && LA11_0<=String)||(LA11_0>=Not && LA11_0<=NotWord)||LA11_0==Subtract||LA11_0==OBrace||LA11_0==OBracket||LA11_0==OParen||LA11_0==ContextIdentifier) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall596);
                            exprList49=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList49.getTree());

                            }
                            break;

                    }

                    char_literal50=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall599); if (state.failed) return retval; 
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
                case 6 :
                    // grammar/PlazmaScript.g:101:6: List '(' ( exprList )? ')'
                    {
                    List51=(Token)match(input,List,FOLLOW_List_in_functionCall622); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_List.add(List51);

                    char_literal52=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall624); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal52);

                    // grammar/PlazmaScript.g:101:15: ( exprList )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( ((LA12_0>=Identifier && LA12_0<=Set)||(LA12_0>=Integer && LA12_0<=String)||(LA12_0>=Not && LA12_0<=NotWord)||LA12_0==Subtract||LA12_0==OBrace||LA12_0==OBracket||LA12_0==OParen||LA12_0==ContextIdentifier) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall626);
                            exprList53=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList53.getTree());

                            }
                            break;

                    }

                    char_literal54=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall629); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal54);



                    // AST REWRITE
                    // elements: List, exprList
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 101:34: -> ^( FUNC_CALL List ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:101:37: ^( FUNC_CALL List ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_List.nextNode());
                        // grammar/PlazmaScript.g:101:54: ( exprList )?
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
                case 7 :
                    // grammar/PlazmaScript.g:102:6: Set '(' ( exprList )? ')'
                    {
                    Set55=(Token)match(input,Set,FOLLOW_Set_in_functionCall652); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Set.add(Set55);

                    char_literal56=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall654); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal56);

                    // grammar/PlazmaScript.g:102:14: ( exprList )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( ((LA13_0>=Identifier && LA13_0<=Set)||(LA13_0>=Integer && LA13_0<=String)||(LA13_0>=Not && LA13_0<=NotWord)||LA13_0==Subtract||LA13_0==OBrace||LA13_0==OBracket||LA13_0==OParen||LA13_0==ContextIdentifier) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall656);
                            exprList57=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList57.getTree());

                            }
                            break;

                    }

                    char_literal58=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall659); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal58);



                    // AST REWRITE
                    // elements: Set, exprList
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 102:33: -> ^( FUNC_CALL Set ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:102:36: ^( FUNC_CALL Set ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Set.nextNode());
                        // grammar/PlazmaScript.g:102:52: ( exprList )?
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
    // grammar/PlazmaScript.g:109:1: ifStatement : ifStat ( elseIfStat )* ( elseStat )? -> ^( IF ifStat ( elseIfStat )* ( elseStat )? ) ;
    public final PlazmaScriptParser.ifStatement_return ifStatement() throws RecognitionException {
        PlazmaScriptParser.ifStatement_return retval = new PlazmaScriptParser.ifStatement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        PlazmaScriptParser.ifStat_return ifStat59 = null;

        PlazmaScriptParser.elseIfStat_return elseIfStat60 = null;

        PlazmaScriptParser.elseStat_return elseStat61 = null;


        RewriteRuleSubtreeStream stream_elseIfStat=new RewriteRuleSubtreeStream(adaptor,"rule elseIfStat");
        RewriteRuleSubtreeStream stream_ifStat=new RewriteRuleSubtreeStream(adaptor,"rule ifStat");
        RewriteRuleSubtreeStream stream_elseStat=new RewriteRuleSubtreeStream(adaptor,"rule elseStat");
        try {
            // grammar/PlazmaScript.g:110:3: ( ifStat ( elseIfStat )* ( elseStat )? -> ^( IF ifStat ( elseIfStat )* ( elseStat )? ) )
            // grammar/PlazmaScript.g:110:6: ifStat ( elseIfStat )* ( elseStat )?
            {
            pushFollow(FOLLOW_ifStat_in_ifStatement697);
            ifStat59=ifStat();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_ifStat.add(ifStat59.getTree());
            // grammar/PlazmaScript.g:110:13: ( elseIfStat )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==Else) ) {
                    int LA15_1 = input.LA(2);

                    if ( (LA15_1==If) ) {
                        alt15=1;
                    }


                }


                switch (alt15) {
            	case 1 :
            	    // grammar/PlazmaScript.g:0:0: elseIfStat
            	    {
            	    pushFollow(FOLLOW_elseIfStat_in_ifStatement699);
            	    elseIfStat60=elseIfStat();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_elseIfStat.add(elseIfStat60.getTree());

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

            // grammar/PlazmaScript.g:110:25: ( elseStat )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==Else) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // grammar/PlazmaScript.g:0:0: elseStat
                    {
                    pushFollow(FOLLOW_elseStat_in_ifStatement702);
                    elseStat61=elseStat();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_elseStat.add(elseStat61.getTree());

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
            // 110:35: -> ^( IF ifStat ( elseIfStat )* ( elseStat )? )
            {
                // grammar/PlazmaScript.g:110:38: ^( IF ifStat ( elseIfStat )* ( elseStat )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(IF, "IF"), root_1);

                adaptor.addChild(root_1, stream_ifStat.nextTree());
                // grammar/PlazmaScript.g:110:50: ( elseIfStat )*
                while ( stream_elseIfStat.hasNext() ) {
                    adaptor.addChild(root_1, stream_elseIfStat.nextTree());

                }
                stream_elseIfStat.reset();
                // grammar/PlazmaScript.g:110:62: ( elseStat )?
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
    // grammar/PlazmaScript.g:113:1: ifStat : If '(' expression ')' '{' block '}' -> ^( EXP expression block ) ;
    public final PlazmaScriptParser.ifStat_return ifStat() throws RecognitionException {
        PlazmaScriptParser.ifStat_return retval = new PlazmaScriptParser.ifStat_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token If62=null;
        Token char_literal63=null;
        Token char_literal65=null;
        Token char_literal66=null;
        Token char_literal68=null;
        PlazmaScriptParser.expression_return expression64 = null;

        PlazmaScriptParser.block_return block67 = null;


        Object If62_tree=null;
        Object char_literal63_tree=null;
        Object char_literal65_tree=null;
        Object char_literal66_tree=null;
        Object char_literal68_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_If=new RewriteRuleTokenStream(adaptor,"token If");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:114:3: ( If '(' expression ')' '{' block '}' -> ^( EXP expression block ) )
            // grammar/PlazmaScript.g:114:6: If '(' expression ')' '{' block '}'
            {
            If62=(Token)match(input,If,FOLLOW_If_in_ifStat731); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_If.add(If62);

            char_literal63=(Token)match(input,OParen,FOLLOW_OParen_in_ifStat733); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal63);

            pushFollow(FOLLOW_expression_in_ifStat735);
            expression64=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression64.getTree());
            char_literal65=(Token)match(input,CParen,FOLLOW_CParen_in_ifStat737); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal65);

            char_literal66=(Token)match(input,OBrace,FOLLOW_OBrace_in_ifStat739); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal66);

            pushFollow(FOLLOW_block_in_ifStat741);
            block67=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block67.getTree());
            char_literal68=(Token)match(input,CBrace,FOLLOW_CBrace_in_ifStat743); if (state.failed) return retval; 
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
            // 114:42: -> ^( EXP expression block )
            {
                // grammar/PlazmaScript.g:114:45: ^( EXP expression block )
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
    // grammar/PlazmaScript.g:117:1: elseIfStat : Else If '(' expression ')' '{' block '}' -> ^( EXP expression block ) ;
    public final PlazmaScriptParser.elseIfStat_return elseIfStat() throws RecognitionException {
        PlazmaScriptParser.elseIfStat_return retval = new PlazmaScriptParser.elseIfStat_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Else69=null;
        Token If70=null;
        Token char_literal71=null;
        Token char_literal73=null;
        Token char_literal74=null;
        Token char_literal76=null;
        PlazmaScriptParser.expression_return expression72 = null;

        PlazmaScriptParser.block_return block75 = null;


        Object Else69_tree=null;
        Object If70_tree=null;
        Object char_literal71_tree=null;
        Object char_literal73_tree=null;
        Object char_literal74_tree=null;
        Object char_literal76_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_Else=new RewriteRuleTokenStream(adaptor,"token Else");
        RewriteRuleTokenStream stream_If=new RewriteRuleTokenStream(adaptor,"token If");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:118:3: ( Else If '(' expression ')' '{' block '}' -> ^( EXP expression block ) )
            // grammar/PlazmaScript.g:118:6: Else If '(' expression ')' '{' block '}'
            {
            Else69=(Token)match(input,Else,FOLLOW_Else_in_elseIfStat767); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Else.add(Else69);

            If70=(Token)match(input,If,FOLLOW_If_in_elseIfStat769); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_If.add(If70);

            char_literal71=(Token)match(input,OParen,FOLLOW_OParen_in_elseIfStat771); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal71);

            pushFollow(FOLLOW_expression_in_elseIfStat773);
            expression72=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression72.getTree());
            char_literal73=(Token)match(input,CParen,FOLLOW_CParen_in_elseIfStat775); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal73);

            char_literal74=(Token)match(input,OBrace,FOLLOW_OBrace_in_elseIfStat777); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal74);

            pushFollow(FOLLOW_block_in_elseIfStat779);
            block75=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block75.getTree());
            char_literal76=(Token)match(input,CBrace,FOLLOW_CBrace_in_elseIfStat781); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal76);



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
            // 118:47: -> ^( EXP expression block )
            {
                // grammar/PlazmaScript.g:118:50: ^( EXP expression block )
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
    // grammar/PlazmaScript.g:121:1: elseStat : Else '{' block '}' -> ^( EXP block ) ;
    public final PlazmaScriptParser.elseStat_return elseStat() throws RecognitionException {
        PlazmaScriptParser.elseStat_return retval = new PlazmaScriptParser.elseStat_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Else77=null;
        Token char_literal78=null;
        Token char_literal80=null;
        PlazmaScriptParser.block_return block79 = null;


        Object Else77_tree=null;
        Object char_literal78_tree=null;
        Object char_literal80_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_Else=new RewriteRuleTokenStream(adaptor,"token Else");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:122:3: ( Else '{' block '}' -> ^( EXP block ) )
            // grammar/PlazmaScript.g:122:6: Else '{' block '}'
            {
            Else77=(Token)match(input,Else,FOLLOW_Else_in_elseStat805); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Else.add(Else77);

            char_literal78=(Token)match(input,OBrace,FOLLOW_OBrace_in_elseStat807); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal78);

            pushFollow(FOLLOW_block_in_elseStat809);
            block79=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block79.getTree());
            char_literal80=(Token)match(input,CBrace,FOLLOW_CBrace_in_elseStat811); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal80);



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
            // 122:25: -> ^( EXP block )
            {
                // grammar/PlazmaScript.g:122:28: ^( EXP block )
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
    // grammar/PlazmaScript.g:125:1: variableDef : Var ;
    public final PlazmaScriptParser.variableDef_return variableDef() throws RecognitionException {
        PlazmaScriptParser.variableDef_return retval = new PlazmaScriptParser.variableDef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Var81=null;

        Object Var81_tree=null;

        try {
            // grammar/PlazmaScript.g:126:2: ( Var )
            // grammar/PlazmaScript.g:126:4: Var
            {
            root_0 = (Object)adaptor.nil();

            Var81=(Token)match(input,Var,FOLLOW_Var_in_variableDef831); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Var81_tree = (Object)adaptor.create(Var81);
            adaptor.addChild(root_0, Var81_tree);
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
    // grammar/PlazmaScript.g:143:1: functionDecl : Def Identifier '(' ( idList )? ')' '{' block '}' ;
    public final PlazmaScriptParser.functionDecl_return functionDecl() throws RecognitionException {
        PlazmaScriptParser.functionDecl_return retval = new PlazmaScriptParser.functionDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Def82=null;
        Token Identifier83=null;
        Token char_literal84=null;
        Token char_literal86=null;
        Token char_literal87=null;
        Token char_literal89=null;
        PlazmaScriptParser.idList_return idList85 = null;

        PlazmaScriptParser.block_return block88 = null;


        Object Def82_tree=null;
        Object Identifier83_tree=null;
        Object char_literal84_tree=null;
        Object char_literal86_tree=null;
        Object char_literal87_tree=null;
        Object char_literal89_tree=null;

        try {
            // grammar/PlazmaScript.g:144:3: ( Def Identifier '(' ( idList )? ')' '{' block '}' )
            // grammar/PlazmaScript.g:144:6: Def Identifier '(' ( idList )? ')' '{' block '}'
            {
            root_0 = (Object)adaptor.nil();

            Def82=(Token)match(input,Def,FOLLOW_Def_in_functionDecl863); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Def82_tree = (Object)adaptor.create(Def82);
            adaptor.addChild(root_0, Def82_tree);
            }
            Identifier83=(Token)match(input,Identifier,FOLLOW_Identifier_in_functionDecl865); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier83_tree = (Object)adaptor.create(Identifier83);
            adaptor.addChild(root_0, Identifier83_tree);
            }
            char_literal84=(Token)match(input,OParen,FOLLOW_OParen_in_functionDecl867); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal84_tree = (Object)adaptor.create(char_literal84);
            adaptor.addChild(root_0, char_literal84_tree);
            }
            // grammar/PlazmaScript.g:144:25: ( idList )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==Identifier) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // grammar/PlazmaScript.g:0:0: idList
                    {
                    pushFollow(FOLLOW_idList_in_functionDecl869);
                    idList85=idList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, idList85.getTree());

                    }
                    break;

            }

            char_literal86=(Token)match(input,CParen,FOLLOW_CParen_in_functionDecl872); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal86_tree = (Object)adaptor.create(char_literal86);
            adaptor.addChild(root_0, char_literal86_tree);
            }
            char_literal87=(Token)match(input,OBrace,FOLLOW_OBrace_in_functionDecl874); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal87_tree = (Object)adaptor.create(char_literal87);
            adaptor.addChild(root_0, char_literal87_tree);
            }
            pushFollow(FOLLOW_block_in_functionDecl876);
            block88=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, block88.getTree());
            char_literal89=(Token)match(input,CBrace,FOLLOW_CBrace_in_functionDecl878); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal89_tree = (Object)adaptor.create(char_literal89);
            adaptor.addChild(root_0, char_literal89_tree);
            }
            if ( state.backtracking==0 ) {
              defineFunction((Identifier83!=null?Identifier83.getText():null), (idList85!=null?((Object)idList85.tree):null), (block88!=null?((Object)block88.tree):null));
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
    // grammar/PlazmaScript.g:153:1: forStatement : For '(' Identifier 'in' expression ')' '{' block '}' -> ^( For Identifier expression block ) ;
    public final PlazmaScriptParser.forStatement_return forStatement() throws RecognitionException {
        PlazmaScriptParser.forStatement_return retval = new PlazmaScriptParser.forStatement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token For90=null;
        Token char_literal91=null;
        Token Identifier92=null;
        Token string_literal93=null;
        Token char_literal95=null;
        Token char_literal96=null;
        Token char_literal98=null;
        PlazmaScriptParser.expression_return expression94 = null;

        PlazmaScriptParser.block_return block97 = null;


        Object For90_tree=null;
        Object char_literal91_tree=null;
        Object Identifier92_tree=null;
        Object string_literal93_tree=null;
        Object char_literal95_tree=null;
        Object char_literal96_tree=null;
        Object char_literal98_tree=null;
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
            // grammar/PlazmaScript.g:154:3: ( For '(' Identifier 'in' expression ')' '{' block '}' -> ^( For Identifier expression block ) )
            // grammar/PlazmaScript.g:154:6: For '(' Identifier 'in' expression ')' '{' block '}'
            {
            For90=(Token)match(input,For,FOLLOW_For_in_forStatement907); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_For.add(For90);

            char_literal91=(Token)match(input,OParen,FOLLOW_OParen_in_forStatement909); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal91);

            Identifier92=(Token)match(input,Identifier,FOLLOW_Identifier_in_forStatement911); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Identifier.add(Identifier92);

            string_literal93=(Token)match(input,In,FOLLOW_In_in_forStatement913); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_In.add(string_literal93);

            pushFollow(FOLLOW_expression_in_forStatement915);
            expression94=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression94.getTree());
            char_literal95=(Token)match(input,CParen,FOLLOW_CParen_in_forStatement917); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal95);

            char_literal96=(Token)match(input,OBrace,FOLLOW_OBrace_in_forStatement919); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal96);

            pushFollow(FOLLOW_block_in_forStatement921);
            block97=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block97.getTree());
            char_literal98=(Token)match(input,CBrace,FOLLOW_CBrace_in_forStatement923); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal98);



            // AST REWRITE
            // elements: Identifier, block, For, expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 155:6: -> ^( For Identifier expression block )
            {
                // grammar/PlazmaScript.g:155:9: ^( For Identifier expression block )
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
    // grammar/PlazmaScript.g:159:1: whileStatement : While '(' expression ')' '{' block '}' -> ^( While expression block ) ;
    public final PlazmaScriptParser.whileStatement_return whileStatement() throws RecognitionException {
        PlazmaScriptParser.whileStatement_return retval = new PlazmaScriptParser.whileStatement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token While99=null;
        Token char_literal100=null;
        Token char_literal102=null;
        Token char_literal103=null;
        Token char_literal105=null;
        PlazmaScriptParser.expression_return expression101 = null;

        PlazmaScriptParser.block_return block104 = null;


        Object While99_tree=null;
        Object char_literal100_tree=null;
        Object char_literal102_tree=null;
        Object char_literal103_tree=null;
        Object char_literal105_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_While=new RewriteRuleTokenStream(adaptor,"token While");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:160:3: ( While '(' expression ')' '{' block '}' -> ^( While expression block ) )
            // grammar/PlazmaScript.g:160:6: While '(' expression ')' '{' block '}'
            {
            While99=(Token)match(input,While,FOLLOW_While_in_whileStatement958); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_While.add(While99);

            char_literal100=(Token)match(input,OParen,FOLLOW_OParen_in_whileStatement960); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal100);

            pushFollow(FOLLOW_expression_in_whileStatement962);
            expression101=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression101.getTree());
            char_literal102=(Token)match(input,CParen,FOLLOW_CParen_in_whileStatement964); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal102);

            char_literal103=(Token)match(input,OBrace,FOLLOW_OBrace_in_whileStatement966); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal103);

            pushFollow(FOLLOW_block_in_whileStatement968);
            block104=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block104.getTree());
            char_literal105=(Token)match(input,CBrace,FOLLOW_CBrace_in_whileStatement970); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal105);



            // AST REWRITE
            // elements: While, block, expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 160:45: -> ^( While expression block )
            {
                // grammar/PlazmaScript.g:160:48: ^( While expression block )
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
    // grammar/PlazmaScript.g:163:1: idList : Identifier ( ',' Identifier )* -> ^( ID_LIST ( Identifier )+ ) ;
    public final PlazmaScriptParser.idList_return idList() throws RecognitionException {
        PlazmaScriptParser.idList_return retval = new PlazmaScriptParser.idList_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Identifier106=null;
        Token char_literal107=null;
        Token Identifier108=null;

        Object Identifier106_tree=null;
        Object char_literal107_tree=null;
        Object Identifier108_tree=null;
        RewriteRuleTokenStream stream_Comma=new RewriteRuleTokenStream(adaptor,"token Comma");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");

        try {
            // grammar/PlazmaScript.g:164:3: ( Identifier ( ',' Identifier )* -> ^( ID_LIST ( Identifier )+ ) )
            // grammar/PlazmaScript.g:164:6: Identifier ( ',' Identifier )*
            {
            Identifier106=(Token)match(input,Identifier,FOLLOW_Identifier_in_idList994); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Identifier.add(Identifier106);

            // grammar/PlazmaScript.g:164:17: ( ',' Identifier )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==Comma) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // grammar/PlazmaScript.g:164:18: ',' Identifier
            	    {
            	    char_literal107=(Token)match(input,Comma,FOLLOW_Comma_in_idList997); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Comma.add(char_literal107);

            	    Identifier108=(Token)match(input,Identifier,FOLLOW_Identifier_in_idList999); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Identifier.add(Identifier108);


            	    }
            	    break;

            	default :
            	    break loop18;
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
            // 164:35: -> ^( ID_LIST ( Identifier )+ )
            {
                // grammar/PlazmaScript.g:164:38: ^( ID_LIST ( Identifier )+ )
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
    // grammar/PlazmaScript.g:167:1: exprList : expression ( ',' expression )* -> ^( EXP_LIST ( expression )+ ) ;
    public final PlazmaScriptParser.exprList_return exprList() throws RecognitionException {
        PlazmaScriptParser.exprList_return retval = new PlazmaScriptParser.exprList_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal110=null;
        PlazmaScriptParser.expression_return expression109 = null;

        PlazmaScriptParser.expression_return expression111 = null;


        Object char_literal110_tree=null;
        RewriteRuleTokenStream stream_Comma=new RewriteRuleTokenStream(adaptor,"token Comma");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // grammar/PlazmaScript.g:168:3: ( expression ( ',' expression )* -> ^( EXP_LIST ( expression )+ ) )
            // grammar/PlazmaScript.g:168:6: expression ( ',' expression )*
            {
            pushFollow(FOLLOW_expression_in_exprList1024);
            expression109=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression109.getTree());
            // grammar/PlazmaScript.g:168:17: ( ',' expression )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==Comma) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // grammar/PlazmaScript.g:168:18: ',' expression
            	    {
            	    char_literal110=(Token)match(input,Comma,FOLLOW_Comma_in_exprList1027); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Comma.add(char_literal110);

            	    pushFollow(FOLLOW_expression_in_exprList1029);
            	    expression111=expression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_expression.add(expression111.getTree());

            	    }
            	    break;

            	default :
            	    break loop19;
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
            // 168:35: -> ^( EXP_LIST ( expression )+ )
            {
                // grammar/PlazmaScript.g:168:38: ^( EXP_LIST ( expression )+ )
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
    // grammar/PlazmaScript.g:171:1: exprPair : ( expression ':' expression ) -> ^( EXP_PAIR expression expression ) ;
    public final PlazmaScriptParser.exprPair_return exprPair() throws RecognitionException {
        PlazmaScriptParser.exprPair_return retval = new PlazmaScriptParser.exprPair_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal113=null;
        PlazmaScriptParser.expression_return expression112 = null;

        PlazmaScriptParser.expression_return expression114 = null;


        Object char_literal113_tree=null;
        RewriteRuleTokenStream stream_Colon=new RewriteRuleTokenStream(adaptor,"token Colon");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // grammar/PlazmaScript.g:172:3: ( ( expression ':' expression ) -> ^( EXP_PAIR expression expression ) )
            // grammar/PlazmaScript.g:172:6: ( expression ':' expression )
            {
            // grammar/PlazmaScript.g:172:6: ( expression ':' expression )
            // grammar/PlazmaScript.g:172:7: expression ':' expression
            {
            pushFollow(FOLLOW_expression_in_exprPair1055);
            expression112=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression112.getTree());
            char_literal113=(Token)match(input,Colon,FOLLOW_Colon_in_exprPair1057); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Colon.add(char_literal113);

            pushFollow(FOLLOW_expression_in_exprPair1059);
            expression114=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression114.getTree());

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
            // 172:34: -> ^( EXP_PAIR expression expression )
            {
                // grammar/PlazmaScript.g:172:37: ^( EXP_PAIR expression expression )
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
    // grammar/PlazmaScript.g:175:1: exprMap : exprPair ( ',' exprPair )* -> ^( EXP_MAP ( exprPair )+ ) ;
    public final PlazmaScriptParser.exprMap_return exprMap() throws RecognitionException {
        PlazmaScriptParser.exprMap_return retval = new PlazmaScriptParser.exprMap_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal116=null;
        PlazmaScriptParser.exprPair_return exprPair115 = null;

        PlazmaScriptParser.exprPair_return exprPair117 = null;


        Object char_literal116_tree=null;
        RewriteRuleTokenStream stream_Comma=new RewriteRuleTokenStream(adaptor,"token Comma");
        RewriteRuleSubtreeStream stream_exprPair=new RewriteRuleSubtreeStream(adaptor,"rule exprPair");
        try {
            // grammar/PlazmaScript.g:176:3: ( exprPair ( ',' exprPair )* -> ^( EXP_MAP ( exprPair )+ ) )
            // grammar/PlazmaScript.g:176:6: exprPair ( ',' exprPair )*
            {
            pushFollow(FOLLOW_exprPair_in_exprMap1084);
            exprPair115=exprPair();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_exprPair.add(exprPair115.getTree());
            // grammar/PlazmaScript.g:176:15: ( ',' exprPair )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==Comma) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // grammar/PlazmaScript.g:176:16: ',' exprPair
            	    {
            	    char_literal116=(Token)match(input,Comma,FOLLOW_Comma_in_exprMap1087); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Comma.add(char_literal116);

            	    pushFollow(FOLLOW_exprPair_in_exprMap1089);
            	    exprPair117=exprPair();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_exprPair.add(exprPair117.getTree());

            	    }
            	    break;

            	default :
            	    break loop20;
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
            // 176:31: -> ^( EXP_MAP ( exprPair )+ )
            {
                // grammar/PlazmaScript.g:176:34: ^( EXP_MAP ( exprPair )+ )
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
    // grammar/PlazmaScript.g:180:1: expression : condExpr ;
    public final PlazmaScriptParser.expression_return expression() throws RecognitionException {
        PlazmaScriptParser.expression_return retval = new PlazmaScriptParser.expression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        PlazmaScriptParser.condExpr_return condExpr118 = null;



        try {
            // grammar/PlazmaScript.g:181:3: ( condExpr )
            // grammar/PlazmaScript.g:181:6: condExpr
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_condExpr_in_expression1115);
            condExpr118=condExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, condExpr118.getTree());

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
    // grammar/PlazmaScript.g:184:1: condExpr : ( startExpr -> startExpr ) ( '?' a= expression ':' b= expression -> ^( TERNARY startExpr $a $b) | In expression -> ^( In startExpr expression ) | RangeE expression -> ^( RangeE startExpr expression ) | Range expression -> ^( Range startExpr expression ) )? ;
    public final PlazmaScriptParser.condExpr_return condExpr() throws RecognitionException {
        PlazmaScriptParser.condExpr_return retval = new PlazmaScriptParser.condExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal120=null;
        Token char_literal121=null;
        Token In122=null;
        Token RangeE124=null;
        Token Range126=null;
        PlazmaScriptParser.expression_return a = null;

        PlazmaScriptParser.expression_return b = null;

        PlazmaScriptParser.startExpr_return startExpr119 = null;

        PlazmaScriptParser.expression_return expression123 = null;

        PlazmaScriptParser.expression_return expression125 = null;

        PlazmaScriptParser.expression_return expression127 = null;


        Object char_literal120_tree=null;
        Object char_literal121_tree=null;
        Object In122_tree=null;
        Object RangeE124_tree=null;
        Object Range126_tree=null;
        RewriteRuleTokenStream stream_RangeE=new RewriteRuleTokenStream(adaptor,"token RangeE");
        RewriteRuleTokenStream stream_Range=new RewriteRuleTokenStream(adaptor,"token Range");
        RewriteRuleTokenStream stream_In=new RewriteRuleTokenStream(adaptor,"token In");
        RewriteRuleTokenStream stream_Colon=new RewriteRuleTokenStream(adaptor,"token Colon");
        RewriteRuleTokenStream stream_QMark=new RewriteRuleTokenStream(adaptor,"token QMark");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_startExpr=new RewriteRuleSubtreeStream(adaptor,"rule startExpr");
        try {
            // grammar/PlazmaScript.g:185:3: ( ( startExpr -> startExpr ) ( '?' a= expression ':' b= expression -> ^( TERNARY startExpr $a $b) | In expression -> ^( In startExpr expression ) | RangeE expression -> ^( RangeE startExpr expression ) | Range expression -> ^( Range startExpr expression ) )? )
            // grammar/PlazmaScript.g:185:6: ( startExpr -> startExpr ) ( '?' a= expression ':' b= expression -> ^( TERNARY startExpr $a $b) | In expression -> ^( In startExpr expression ) | RangeE expression -> ^( RangeE startExpr expression ) | Range expression -> ^( Range startExpr expression ) )?
            {
            // grammar/PlazmaScript.g:185:6: ( startExpr -> startExpr )
            // grammar/PlazmaScript.g:185:7: startExpr
            {
            pushFollow(FOLLOW_startExpr_in_condExpr1130);
            startExpr119=startExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_startExpr.add(startExpr119.getTree());


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
            // 185:17: -> startExpr
            {
                adaptor.addChild(root_0, stream_startExpr.nextTree());

            }

            retval.tree = root_0;}
            }

            // grammar/PlazmaScript.g:186:6: ( '?' a= expression ':' b= expression -> ^( TERNARY startExpr $a $b) | In expression -> ^( In startExpr expression ) | RangeE expression -> ^( RangeE startExpr expression ) | Range expression -> ^( Range startExpr expression ) )?
            int alt21=5;
            switch ( input.LA(1) ) {
                case QMark:
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
            }

            switch (alt21) {
                case 1 :
                    // grammar/PlazmaScript.g:186:8: '?' a= expression ':' b= expression
                    {
                    char_literal120=(Token)match(input,QMark,FOLLOW_QMark_in_condExpr1145); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_QMark.add(char_literal120);

                    pushFollow(FOLLOW_expression_in_condExpr1149);
                    a=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(a.getTree());
                    char_literal121=(Token)match(input,Colon,FOLLOW_Colon_in_condExpr1151); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Colon.add(char_literal121);

                    pushFollow(FOLLOW_expression_in_condExpr1155);
                    b=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(b.getTree());


                    // AST REWRITE
                    // elements: a, b, startExpr
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
                    // 186:42: -> ^( TERNARY startExpr $a $b)
                    {
                        // grammar/PlazmaScript.g:186:45: ^( TERNARY startExpr $a $b)
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
                    // grammar/PlazmaScript.g:187:8: In expression
                    {
                    In122=(Token)match(input,In,FOLLOW_In_in_condExpr1178); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_In.add(In122);

                    pushFollow(FOLLOW_expression_in_condExpr1180);
                    expression123=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression123.getTree());


                    // AST REWRITE
                    // elements: expression, In, startExpr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 187:42: -> ^( In startExpr expression )
                    {
                        // grammar/PlazmaScript.g:187:45: ^( In startExpr expression )
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
                    // grammar/PlazmaScript.g:189:8: RangeE expression
                    {
                    RangeE124=(Token)match(input,RangeE,FOLLOW_RangeE_in_condExpr1225); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RangeE.add(RangeE124);

                    pushFollow(FOLLOW_expression_in_condExpr1227);
                    expression125=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression125.getTree());


                    // AST REWRITE
                    // elements: startExpr, RangeE, expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 189:42: -> ^( RangeE startExpr expression )
                    {
                        // grammar/PlazmaScript.g:189:45: ^( RangeE startExpr expression )
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
                    // grammar/PlazmaScript.g:190:8: Range expression
                    {
                    Range126=(Token)match(input,Range,FOLLOW_Range_in_condExpr1262); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Range.add(Range126);

                    pushFollow(FOLLOW_expression_in_condExpr1264);
                    expression127=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression127.getTree());


                    // AST REWRITE
                    // elements: expression, Range, startExpr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 190:42: -> ^( Range startExpr expression )
                    {
                        // grammar/PlazmaScript.g:190:45: ^( Range startExpr expression )
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
    // grammar/PlazmaScript.g:195:1: startExpr : orExpr ;
    public final PlazmaScriptParser.startExpr_return startExpr() throws RecognitionException {
        PlazmaScriptParser.startExpr_return retval = new PlazmaScriptParser.startExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        PlazmaScriptParser.orExpr_return orExpr128 = null;



        try {
            // grammar/PlazmaScript.g:196:2: ( orExpr )
            // grammar/PlazmaScript.g:196:4: orExpr
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_orExpr_in_startExpr1327);
            orExpr128=orExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, orExpr128.getTree());

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
    // grammar/PlazmaScript.g:199:1: orExpr : andExpr ( ( 'xor' | '||' | '|' | 'or' ) andExpr )* ;
    public final PlazmaScriptParser.orExpr_return orExpr() throws RecognitionException {
        PlazmaScriptParser.orExpr_return retval = new PlazmaScriptParser.orExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set130=null;
        PlazmaScriptParser.andExpr_return andExpr129 = null;

        PlazmaScriptParser.andExpr_return andExpr131 = null;


        Object set130_tree=null;

        try {
            // grammar/PlazmaScript.g:200:3: ( andExpr ( ( 'xor' | '||' | '|' | 'or' ) andExpr )* )
            // grammar/PlazmaScript.g:200:6: andExpr ( ( 'xor' | '||' | '|' | 'or' ) andExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_andExpr_in_orExpr1340);
            andExpr129=andExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, andExpr129.getTree());
            // grammar/PlazmaScript.g:200:14: ( ( 'xor' | '||' | '|' | 'or' ) andExpr )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0>=XorWord && LA22_0<=OrWord)) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // grammar/PlazmaScript.g:200:15: ( 'xor' | '||' | '|' | 'or' ) andExpr
            	    {
            	    set130=(Token)input.LT(1);
            	    set130=(Token)input.LT(1);
            	    if ( (input.LA(1)>=XorWord && input.LA(1)<=OrWord) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set130), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_andExpr_in_orExpr1360);
            	    andExpr131=andExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, andExpr131.getTree());

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
    // $ANTLR end "orExpr"

    public static class andExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "andExpr"
    // grammar/PlazmaScript.g:204:1: andExpr : equExpr ( ( '&&' | '&' | 'and' ) equExpr )* ;
    public final PlazmaScriptParser.andExpr_return andExpr() throws RecognitionException {
        PlazmaScriptParser.andExpr_return retval = new PlazmaScriptParser.andExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set133=null;
        PlazmaScriptParser.equExpr_return equExpr132 = null;

        PlazmaScriptParser.equExpr_return equExpr134 = null;


        Object set133_tree=null;

        try {
            // grammar/PlazmaScript.g:205:3: ( equExpr ( ( '&&' | '&' | 'and' ) equExpr )* )
            // grammar/PlazmaScript.g:205:6: equExpr ( ( '&&' | '&' | 'and' ) equExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_equExpr_in_andExpr1377);
            equExpr132=equExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, equExpr132.getTree());
            // grammar/PlazmaScript.g:205:14: ( ( '&&' | '&' | 'and' ) equExpr )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( ((LA23_0>=And && LA23_0<=AndWord)) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // grammar/PlazmaScript.g:205:15: ( '&&' | '&' | 'and' ) equExpr
            	    {
            	    set133=(Token)input.LT(1);
            	    set133=(Token)input.LT(1);
            	    if ( (input.LA(1)>=And && input.LA(1)<=AndWord) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set133), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_equExpr_in_andExpr1393);
            	    equExpr134=equExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, equExpr134.getTree());

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
    // $ANTLR end "andExpr"

    public static class equExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "equExpr"
    // grammar/PlazmaScript.g:208:1: equExpr : relExpr ( ( '==' | '!=' ) relExpr )* ;
    public final PlazmaScriptParser.equExpr_return equExpr() throws RecognitionException {
        PlazmaScriptParser.equExpr_return retval = new PlazmaScriptParser.equExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set136=null;
        PlazmaScriptParser.relExpr_return relExpr135 = null;

        PlazmaScriptParser.relExpr_return relExpr137 = null;


        Object set136_tree=null;

        try {
            // grammar/PlazmaScript.g:209:3: ( relExpr ( ( '==' | '!=' ) relExpr )* )
            // grammar/PlazmaScript.g:209:6: relExpr ( ( '==' | '!=' ) relExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_relExpr_in_equExpr1409);
            relExpr135=relExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, relExpr135.getTree());
            // grammar/PlazmaScript.g:209:14: ( ( '==' | '!=' ) relExpr )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( ((LA24_0>=Equals && LA24_0<=NEquals)) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // grammar/PlazmaScript.g:209:15: ( '==' | '!=' ) relExpr
            	    {
            	    set136=(Token)input.LT(1);
            	    set136=(Token)input.LT(1);
            	    if ( (input.LA(1)>=Equals && input.LA(1)<=NEquals) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set136), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_relExpr_in_equExpr1421);
            	    relExpr137=relExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, relExpr137.getTree());

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
    // $ANTLR end "equExpr"

    public static class relExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "relExpr"
    // grammar/PlazmaScript.g:212:1: relExpr : addExpr ( ( '>=' | '<=' | '>' | '<' ) addExpr )* ;
    public final PlazmaScriptParser.relExpr_return relExpr() throws RecognitionException {
        PlazmaScriptParser.relExpr_return retval = new PlazmaScriptParser.relExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set139=null;
        PlazmaScriptParser.addExpr_return addExpr138 = null;

        PlazmaScriptParser.addExpr_return addExpr140 = null;


        Object set139_tree=null;

        try {
            // grammar/PlazmaScript.g:213:3: ( addExpr ( ( '>=' | '<=' | '>' | '<' ) addExpr )* )
            // grammar/PlazmaScript.g:213:6: addExpr ( ( '>=' | '<=' | '>' | '<' ) addExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_addExpr_in_relExpr1437);
            addExpr138=addExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, addExpr138.getTree());
            // grammar/PlazmaScript.g:213:14: ( ( '>=' | '<=' | '>' | '<' ) addExpr )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( ((LA25_0>=GTEquals && LA25_0<=LTEquals)||(LA25_0>=GT && LA25_0<=LT)) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // grammar/PlazmaScript.g:213:15: ( '>=' | '<=' | '>' | '<' ) addExpr
            	    {
            	    set139=(Token)input.LT(1);
            	    set139=(Token)input.LT(1);
            	    if ( (input.LA(1)>=GTEquals && input.LA(1)<=LTEquals)||(input.LA(1)>=GT && input.LA(1)<=LT) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set139), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_addExpr_in_relExpr1457);
            	    addExpr140=addExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, addExpr140.getTree());

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
    // $ANTLR end "relExpr"

    public static class addExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "addExpr"
    // grammar/PlazmaScript.g:216:1: addExpr : mulExpr ( ( '+' | '-' ) mulExpr )* ;
    public final PlazmaScriptParser.addExpr_return addExpr() throws RecognitionException {
        PlazmaScriptParser.addExpr_return retval = new PlazmaScriptParser.addExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set142=null;
        PlazmaScriptParser.mulExpr_return mulExpr141 = null;

        PlazmaScriptParser.mulExpr_return mulExpr143 = null;


        Object set142_tree=null;

        try {
            // grammar/PlazmaScript.g:217:3: ( mulExpr ( ( '+' | '-' ) mulExpr )* )
            // grammar/PlazmaScript.g:217:6: mulExpr ( ( '+' | '-' ) mulExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_mulExpr_in_addExpr1473);
            mulExpr141=mulExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, mulExpr141.getTree());
            // grammar/PlazmaScript.g:217:14: ( ( '+' | '-' ) mulExpr )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( ((LA26_0>=Add && LA26_0<=Subtract)) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // grammar/PlazmaScript.g:217:15: ( '+' | '-' ) mulExpr
            	    {
            	    set142=(Token)input.LT(1);
            	    set142=(Token)input.LT(1);
            	    if ( (input.LA(1)>=Add && input.LA(1)<=Subtract) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set142), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_mulExpr_in_addExpr1485);
            	    mulExpr143=mulExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, mulExpr143.getTree());

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
    // $ANTLR end "addExpr"

    public static class mulExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "mulExpr"
    // grammar/PlazmaScript.g:220:1: mulExpr : powExpr ( ( '*' | '/' | '%' ) powExpr )* ;
    public final PlazmaScriptParser.mulExpr_return mulExpr() throws RecognitionException {
        PlazmaScriptParser.mulExpr_return retval = new PlazmaScriptParser.mulExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set145=null;
        PlazmaScriptParser.powExpr_return powExpr144 = null;

        PlazmaScriptParser.powExpr_return powExpr146 = null;


        Object set145_tree=null;

        try {
            // grammar/PlazmaScript.g:221:3: ( powExpr ( ( '*' | '/' | '%' ) powExpr )* )
            // grammar/PlazmaScript.g:221:6: powExpr ( ( '*' | '/' | '%' ) powExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_powExpr_in_mulExpr1501);
            powExpr144=powExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, powExpr144.getTree());
            // grammar/PlazmaScript.g:221:14: ( ( '*' | '/' | '%' ) powExpr )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( ((LA27_0>=Multiply && LA27_0<=Modulus)) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // grammar/PlazmaScript.g:221:15: ( '*' | '/' | '%' ) powExpr
            	    {
            	    set145=(Token)input.LT(1);
            	    set145=(Token)input.LT(1);
            	    if ( (input.LA(1)>=Multiply && input.LA(1)<=Modulus) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set145), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_powExpr_in_mulExpr1517);
            	    powExpr146=powExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, powExpr146.getTree());

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
    // $ANTLR end "mulExpr"

    public static class powExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "powExpr"
    // grammar/PlazmaScript.g:224:1: powExpr : unaryExpr ( '^' unaryExpr )* ;
    public final PlazmaScriptParser.powExpr_return powExpr() throws RecognitionException {
        PlazmaScriptParser.powExpr_return retval = new PlazmaScriptParser.powExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal148=null;
        PlazmaScriptParser.unaryExpr_return unaryExpr147 = null;

        PlazmaScriptParser.unaryExpr_return unaryExpr149 = null;


        Object char_literal148_tree=null;

        try {
            // grammar/PlazmaScript.g:225:3: ( unaryExpr ( '^' unaryExpr )* )
            // grammar/PlazmaScript.g:225:6: unaryExpr ( '^' unaryExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_unaryExpr_in_powExpr1533);
            unaryExpr147=unaryExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpr147.getTree());
            // grammar/PlazmaScript.g:225:16: ( '^' unaryExpr )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==Pow) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // grammar/PlazmaScript.g:225:17: '^' unaryExpr
            	    {
            	    char_literal148=(Token)match(input,Pow,FOLLOW_Pow_in_powExpr1536); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal148_tree = (Object)adaptor.create(char_literal148);
            	    root_0 = (Object)adaptor.becomeRoot(char_literal148_tree, root_0);
            	    }
            	    pushFollow(FOLLOW_unaryExpr_in_powExpr1539);
            	    unaryExpr149=unaryExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpr149.getTree());

            	    }
            	    break;

            	default :
            	    break loop28;
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
    // grammar/PlazmaScript.g:228:1: unaryExpr : ( '-' atom -> ^( UNARY_MIN atom ) | '!' atom -> ^( NEGATE atom ) | 'not' atom -> ^( NEGATE atom ) | atom );
    public final PlazmaScriptParser.unaryExpr_return unaryExpr() throws RecognitionException {
        PlazmaScriptParser.unaryExpr_return retval = new PlazmaScriptParser.unaryExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal150=null;
        Token char_literal152=null;
        Token string_literal154=null;
        PlazmaScriptParser.atom_return atom151 = null;

        PlazmaScriptParser.atom_return atom153 = null;

        PlazmaScriptParser.atom_return atom155 = null;

        PlazmaScriptParser.atom_return atom156 = null;


        Object char_literal150_tree=null;
        Object char_literal152_tree=null;
        Object string_literal154_tree=null;
        RewriteRuleTokenStream stream_NotWord=new RewriteRuleTokenStream(adaptor,"token NotWord");
        RewriteRuleTokenStream stream_Subtract=new RewriteRuleTokenStream(adaptor,"token Subtract");
        RewriteRuleTokenStream stream_Not=new RewriteRuleTokenStream(adaptor,"token Not");
        RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");
        try {
            // grammar/PlazmaScript.g:229:3: ( '-' atom -> ^( UNARY_MIN atom ) | '!' atom -> ^( NEGATE atom ) | 'not' atom -> ^( NEGATE atom ) | atom )
            int alt29=4;
            switch ( input.LA(1) ) {
            case Subtract:
                {
                alt29=1;
                }
                break;
            case Not:
                {
                alt29=2;
                }
                break;
            case NotWord:
                {
                alt29=3;
                }
                break;
            case Identifier:
            case Println:
            case Print:
            case Assert:
            case Date:
            case List:
            case Set:
            case Integer:
            case Number:
            case Bool:
            case Null:
            case NaN:
            case Infinity:
            case String:
            case OBrace:
            case OBracket:
            case OParen:
            case ContextIdentifier:
                {
                alt29=4;
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
                    // grammar/PlazmaScript.g:229:6: '-' atom
                    {
                    char_literal150=(Token)match(input,Subtract,FOLLOW_Subtract_in_unaryExpr1557); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Subtract.add(char_literal150);

                    pushFollow(FOLLOW_atom_in_unaryExpr1559);
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
                    // 229:15: -> ^( UNARY_MIN atom )
                    {
                        // grammar/PlazmaScript.g:229:18: ^( UNARY_MIN atom )
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
                    // grammar/PlazmaScript.g:230:6: '!' atom
                    {
                    char_literal152=(Token)match(input,Not,FOLLOW_Not_in_unaryExpr1574); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Not.add(char_literal152);

                    pushFollow(FOLLOW_atom_in_unaryExpr1576);
                    atom153=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom153.getTree());


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
                    // 230:15: -> ^( NEGATE atom )
                    {
                        // grammar/PlazmaScript.g:230:18: ^( NEGATE atom )
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
                    // grammar/PlazmaScript.g:231:6: 'not' atom
                    {
                    string_literal154=(Token)match(input,NotWord,FOLLOW_NotWord_in_unaryExpr1591); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NotWord.add(string_literal154);

                    pushFollow(FOLLOW_atom_in_unaryExpr1593);
                    atom155=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom155.getTree());


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
                    // 231:17: -> ^( NEGATE atom )
                    {
                        // grammar/PlazmaScript.g:231:20: ^( NEGATE atom )
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
                    // grammar/PlazmaScript.g:232:6: atom
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_atom_in_unaryExpr1610);
                    atom156=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, atom156.getTree());

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
    // grammar/PlazmaScript.g:235:1: atom : ( Integer | Number | Bool | Null | NaN | Infinity | lookup );
    public final PlazmaScriptParser.atom_return atom() throws RecognitionException {
        PlazmaScriptParser.atom_return retval = new PlazmaScriptParser.atom_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Integer157=null;
        Token Number158=null;
        Token Bool159=null;
        Token Null160=null;
        Token NaN161=null;
        Token Infinity162=null;
        PlazmaScriptParser.lookup_return lookup163 = null;


        Object Integer157_tree=null;
        Object Number158_tree=null;
        Object Bool159_tree=null;
        Object Null160_tree=null;
        Object NaN161_tree=null;
        Object Infinity162_tree=null;

        try {
            // grammar/PlazmaScript.g:236:3: ( Integer | Number | Bool | Null | NaN | Infinity | lookup )
            int alt30=7;
            switch ( input.LA(1) ) {
            case Integer:
                {
                alt30=1;
                }
                break;
            case Number:
                {
                alt30=2;
                }
                break;
            case Bool:
                {
                alt30=3;
                }
                break;
            case Null:
                {
                alt30=4;
                }
                break;
            case NaN:
                {
                alt30=5;
                }
                break;
            case Infinity:
                {
                alt30=6;
                }
                break;
            case Identifier:
            case Println:
            case Print:
            case Assert:
            case Date:
            case List:
            case Set:
            case String:
            case OBrace:
            case OBracket:
            case OParen:
            case ContextIdentifier:
                {
                alt30=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }

            switch (alt30) {
                case 1 :
                    // grammar/PlazmaScript.g:236:6: Integer
                    {
                    root_0 = (Object)adaptor.nil();

                    Integer157=(Token)match(input,Integer,FOLLOW_Integer_in_atom1624); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Integer157_tree = (Object)adaptor.create(Integer157);
                    adaptor.addChild(root_0, Integer157_tree);
                    }

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:237:6: Number
                    {
                    root_0 = (Object)adaptor.nil();

                    Number158=(Token)match(input,Number,FOLLOW_Number_in_atom1631); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Number158_tree = (Object)adaptor.create(Number158);
                    adaptor.addChild(root_0, Number158_tree);
                    }

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScript.g:238:6: Bool
                    {
                    root_0 = (Object)adaptor.nil();

                    Bool159=(Token)match(input,Bool,FOLLOW_Bool_in_atom1638); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Bool159_tree = (Object)adaptor.create(Bool159);
                    adaptor.addChild(root_0, Bool159_tree);
                    }

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScript.g:239:6: Null
                    {
                    root_0 = (Object)adaptor.nil();

                    Null160=(Token)match(input,Null,FOLLOW_Null_in_atom1645); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Null160_tree = (Object)adaptor.create(Null160);
                    adaptor.addChild(root_0, Null160_tree);
                    }

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScript.g:240:6: NaN
                    {
                    root_0 = (Object)adaptor.nil();

                    NaN161=(Token)match(input,NaN,FOLLOW_NaN_in_atom1652); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NaN161_tree = (Object)adaptor.create(NaN161);
                    adaptor.addChild(root_0, NaN161_tree);
                    }

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScript.g:241:6: Infinity
                    {
                    root_0 = (Object)adaptor.nil();

                    Infinity162=(Token)match(input,Infinity,FOLLOW_Infinity_in_atom1659); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Infinity162_tree = (Object)adaptor.create(Infinity162);
                    adaptor.addChild(root_0, Infinity162_tree);
                    }

                    }
                    break;
                case 7 :
                    // grammar/PlazmaScript.g:242:6: lookup
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_lookup_in_atom1667);
                    lookup163=lookup();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lookup163.getTree());

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
    // grammar/PlazmaScript.g:245:1: list : '[' ( exprList )? ']' -> ^( LIST ( exprList )? ) ;
    public final PlazmaScriptParser.list_return list() throws RecognitionException {
        PlazmaScriptParser.list_return retval = new PlazmaScriptParser.list_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal164=null;
        Token char_literal166=null;
        PlazmaScriptParser.exprList_return exprList165 = null;


        Object char_literal164_tree=null;
        Object char_literal166_tree=null;
        RewriteRuleTokenStream stream_CBracket=new RewriteRuleTokenStream(adaptor,"token CBracket");
        RewriteRuleTokenStream stream_OBracket=new RewriteRuleTokenStream(adaptor,"token OBracket");
        RewriteRuleSubtreeStream stream_exprList=new RewriteRuleSubtreeStream(adaptor,"rule exprList");
        try {
            // grammar/PlazmaScript.g:246:3: ( '[' ( exprList )? ']' -> ^( LIST ( exprList )? ) )
            // grammar/PlazmaScript.g:246:6: '[' ( exprList )? ']'
            {
            char_literal164=(Token)match(input,OBracket,FOLLOW_OBracket_in_list1681); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBracket.add(char_literal164);

            // grammar/PlazmaScript.g:246:10: ( exprList )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( ((LA31_0>=Identifier && LA31_0<=Set)||(LA31_0>=Integer && LA31_0<=String)||(LA31_0>=Not && LA31_0<=NotWord)||LA31_0==Subtract||LA31_0==OBrace||LA31_0==OBracket||LA31_0==OParen||LA31_0==ContextIdentifier) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // grammar/PlazmaScript.g:0:0: exprList
                    {
                    pushFollow(FOLLOW_exprList_in_list1683);
                    exprList165=exprList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_exprList.add(exprList165.getTree());

                    }
                    break;

            }

            char_literal166=(Token)match(input,CBracket,FOLLOW_CBracket_in_list1686); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBracket.add(char_literal166);



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
            // 246:24: -> ^( LIST ( exprList )? )
            {
                // grammar/PlazmaScript.g:246:27: ^( LIST ( exprList )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LIST, "LIST"), root_1);

                // grammar/PlazmaScript.g:246:34: ( exprList )?
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
    // grammar/PlazmaScript.g:249:1: map : '{' ( ':' | exprMap ) '}' -> ^( MAP ( exprMap )? ) ;
    public final PlazmaScriptParser.map_return map() throws RecognitionException {
        PlazmaScriptParser.map_return retval = new PlazmaScriptParser.map_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal167=null;
        Token char_literal168=null;
        Token char_literal170=null;
        PlazmaScriptParser.exprMap_return exprMap169 = null;


        Object char_literal167_tree=null;
        Object char_literal168_tree=null;
        Object char_literal170_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_Colon=new RewriteRuleTokenStream(adaptor,"token Colon");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_exprMap=new RewriteRuleSubtreeStream(adaptor,"rule exprMap");
        try {
            // grammar/PlazmaScript.g:250:3: ( '{' ( ':' | exprMap ) '}' -> ^( MAP ( exprMap )? ) )
            // grammar/PlazmaScript.g:250:6: '{' ( ':' | exprMap ) '}'
            {
            char_literal167=(Token)match(input,OBrace,FOLLOW_OBrace_in_map1709); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal167);

            // grammar/PlazmaScript.g:250:10: ( ':' | exprMap )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==Colon) ) {
                alt32=1;
            }
            else if ( ((LA32_0>=Identifier && LA32_0<=Set)||(LA32_0>=Integer && LA32_0<=String)||(LA32_0>=Not && LA32_0<=NotWord)||LA32_0==Subtract||LA32_0==OBrace||LA32_0==OBracket||LA32_0==OParen||LA32_0==ContextIdentifier) ) {
                alt32=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }
            switch (alt32) {
                case 1 :
                    // grammar/PlazmaScript.g:250:11: ':'
                    {
                    char_literal168=(Token)match(input,Colon,FOLLOW_Colon_in_map1712); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Colon.add(char_literal168);


                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:250:17: exprMap
                    {
                    pushFollow(FOLLOW_exprMap_in_map1716);
                    exprMap169=exprMap();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_exprMap.add(exprMap169.getTree());

                    }
                    break;

            }

            char_literal170=(Token)match(input,CBrace,FOLLOW_CBrace_in_map1719); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal170);



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
            // 250:30: -> ^( MAP ( exprMap )? )
            {
                // grammar/PlazmaScript.g:250:33: ^( MAP ( exprMap )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(MAP, "MAP"), root_1);

                // grammar/PlazmaScript.g:250:39: ( exprMap )?
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
    // grammar/PlazmaScript.g:253:1: lookup : ( functionCall ( indexes )? -> ^( LOOKUP functionCall ( indexes )? ) | list ( indexes )? -> ^( LOOKUP list ( indexes )? ) | map ( indexes )? -> ^( LOOKUP map ( indexes )? ) | Identifier ( indexes )? -> ^( LOOKUP Identifier ( indexes )? ) | anyIdentifier ( indexes )? -> ^( LOOKUP ( indexes )? ) | String ( indexes )? -> ^( LOOKUP String ( indexes )? ) | '(' expression ')' ( indexes )? -> ^( LOOKUP expression ( indexes )? ) );
    public final PlazmaScriptParser.lookup_return lookup() throws RecognitionException {
        PlazmaScriptParser.lookup_return retval = new PlazmaScriptParser.lookup_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Identifier177=null;
        Token String181=null;
        Token char_literal183=null;
        Token char_literal185=null;
        PlazmaScriptParser.functionCall_return functionCall171 = null;

        PlazmaScriptParser.indexes_return indexes172 = null;

        PlazmaScriptParser.list_return list173 = null;

        PlazmaScriptParser.indexes_return indexes174 = null;

        PlazmaScriptParser.map_return map175 = null;

        PlazmaScriptParser.indexes_return indexes176 = null;

        PlazmaScriptParser.indexes_return indexes178 = null;

        PlazmaScriptParser.anyIdentifier_return anyIdentifier179 = null;

        PlazmaScriptParser.indexes_return indexes180 = null;

        PlazmaScriptParser.indexes_return indexes182 = null;

        PlazmaScriptParser.expression_return expression184 = null;

        PlazmaScriptParser.indexes_return indexes186 = null;


        Object Identifier177_tree=null;
        Object String181_tree=null;
        Object char_literal183_tree=null;
        Object char_literal185_tree=null;
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
            // grammar/PlazmaScript.g:254:3: ( functionCall ( indexes )? -> ^( LOOKUP functionCall ( indexes )? ) | list ( indexes )? -> ^( LOOKUP list ( indexes )? ) | map ( indexes )? -> ^( LOOKUP map ( indexes )? ) | Identifier ( indexes )? -> ^( LOOKUP Identifier ( indexes )? ) | anyIdentifier ( indexes )? -> ^( LOOKUP ( indexes )? ) | String ( indexes )? -> ^( LOOKUP String ( indexes )? ) | '(' expression ')' ( indexes )? -> ^( LOOKUP expression ( indexes )? ) )
            int alt40=7;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA40_1 = input.LA(2);

                if ( (LA40_1==OParen) ) {
                    alt40=1;
                }
                else if ( (synpred74_PlazmaScript()) ) {
                    alt40=4;
                }
                else if ( (synpred76_PlazmaScript()) ) {
                    alt40=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 40, 1, input);

                    throw nvae;
                }
                }
                break;
            case Println:
            case Print:
            case Assert:
            case Date:
            case List:
            case Set:
                {
                alt40=1;
                }
                break;
            case OBracket:
                {
                alt40=2;
                }
                break;
            case OBrace:
                {
                alt40=3;
                }
                break;
            case ContextIdentifier:
                {
                alt40=5;
                }
                break;
            case String:
                {
                alt40=6;
                }
                break;
            case OParen:
                {
                alt40=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;
            }

            switch (alt40) {
                case 1 :
                    // grammar/PlazmaScript.g:254:6: functionCall ( indexes )?
                    {
                    pushFollow(FOLLOW_functionCall_in_lookup1742);
                    functionCall171=functionCall();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_functionCall.add(functionCall171.getTree());
                    // grammar/PlazmaScript.g:254:19: ( indexes )?
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==OBracket||LA33_0==97) ) {
                        alt33=1;
                    }
                    switch (alt33) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1744);
                            indexes172=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes172.getTree());

                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: indexes, functionCall
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 254:34: -> ^( LOOKUP functionCall ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:254:37: ^( LOOKUP functionCall ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_functionCall.nextTree());
                        // grammar/PlazmaScript.g:254:59: ( indexes )?
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
                    // grammar/PlazmaScript.g:255:6: list ( indexes )?
                    {
                    pushFollow(FOLLOW_list_in_lookup1769);
                    list173=list();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_list.add(list173.getTree());
                    // grammar/PlazmaScript.g:255:11: ( indexes )?
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==OBracket||LA34_0==97) ) {
                        alt34=1;
                    }
                    switch (alt34) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1771);
                            indexes174=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes174.getTree());

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
                    // 255:34: -> ^( LOOKUP list ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:255:37: ^( LOOKUP list ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_list.nextTree());
                        // grammar/PlazmaScript.g:255:51: ( indexes )?
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
                    // grammar/PlazmaScript.g:256:6: map ( indexes )?
                    {
                    pushFollow(FOLLOW_map_in_lookup1804);
                    map175=map();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_map.add(map175.getTree());
                    // grammar/PlazmaScript.g:256:10: ( indexes )?
                    int alt35=2;
                    int LA35_0 = input.LA(1);

                    if ( (LA35_0==OBracket||LA35_0==97) ) {
                        alt35=1;
                    }
                    switch (alt35) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1806);
                            indexes176=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes176.getTree());

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
                    // 256:34: -> ^( LOOKUP map ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:256:37: ^( LOOKUP map ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_map.nextTree());
                        // grammar/PlazmaScript.g:256:50: ( indexes )?
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
                    // grammar/PlazmaScript.g:257:6: Identifier ( indexes )?
                    {
                    Identifier177=(Token)match(input,Identifier,FOLLOW_Identifier_in_lookup1842); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier177);

                    // grammar/PlazmaScript.g:257:17: ( indexes )?
                    int alt36=2;
                    int LA36_0 = input.LA(1);

                    if ( (LA36_0==OBracket||LA36_0==97) ) {
                        alt36=1;
                    }
                    switch (alt36) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1844);
                            indexes178=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes178.getTree());

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
                    // 257:34: -> ^( LOOKUP Identifier ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:257:37: ^( LOOKUP Identifier ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_Identifier.nextNode());
                        // grammar/PlazmaScript.g:257:57: ( indexes )?
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
                    // grammar/PlazmaScript.g:258:6: anyIdentifier ( indexes )?
                    {
                    pushFollow(FOLLOW_anyIdentifier_in_lookup1871);
                    anyIdentifier179=anyIdentifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_anyIdentifier.add(anyIdentifier179.getTree());
                    // grammar/PlazmaScript.g:258:20: ( indexes )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==OBracket||LA37_0==97) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1873);
                            indexes180=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes180.getTree());

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
                    // 258:34: -> ^( LOOKUP ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:258:37: ^( LOOKUP ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, new CommonTree(new CommonToken(Identifier, (anyIdentifier179!=null?input.toString(anyIdentifier179.start,anyIdentifier179.stop):null))));
                        // grammar/PlazmaScript.g:258:113: ( indexes )?
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
                    // grammar/PlazmaScript.g:259:6: String ( indexes )?
                    {
                    String181=(Token)match(input,String,FOLLOW_String_in_lookup1899); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_String.add(String181);

                    // grammar/PlazmaScript.g:259:13: ( indexes )?
                    int alt38=2;
                    int LA38_0 = input.LA(1);

                    if ( (LA38_0==OBracket||LA38_0==97) ) {
                        alt38=1;
                    }
                    switch (alt38) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1901);
                            indexes182=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes182.getTree());

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
                    // 259:34: -> ^( LOOKUP String ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:259:37: ^( LOOKUP String ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_String.nextNode());
                        // grammar/PlazmaScript.g:259:53: ( indexes )?
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
                    // grammar/PlazmaScript.g:260:6: '(' expression ')' ( indexes )?
                    {
                    char_literal183=(Token)match(input,OParen,FOLLOW_OParen_in_lookup1932); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal183);

                    pushFollow(FOLLOW_expression_in_lookup1934);
                    expression184=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression184.getTree());
                    char_literal185=(Token)match(input,CParen,FOLLOW_CParen_in_lookup1936); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal185);

                    // grammar/PlazmaScript.g:260:25: ( indexes )?
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==OBracket||LA39_0==97) ) {
                        alt39=1;
                    }
                    switch (alt39) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1938);
                            indexes186=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes186.getTree());

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
                    // 260:34: -> ^( LOOKUP expression ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:260:37: ^( LOOKUP expression ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_expression.nextTree());
                        // grammar/PlazmaScript.g:260:57: ( indexes )?
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
    // grammar/PlazmaScript.g:263:1: indexes : ( tail )+ -> ^( TAILS ( tail )+ ) ;
    public final PlazmaScriptParser.indexes_return indexes() throws RecognitionException {
        PlazmaScriptParser.indexes_return retval = new PlazmaScriptParser.indexes_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        PlazmaScriptParser.tail_return tail187 = null;


        RewriteRuleSubtreeStream stream_tail=new RewriteRuleSubtreeStream(adaptor,"rule tail");
        try {
            // grammar/PlazmaScript.g:265:3: ( ( tail )+ -> ^( TAILS ( tail )+ ) )
            // grammar/PlazmaScript.g:265:6: ( tail )+
            {
            // grammar/PlazmaScript.g:265:6: ( tail )+
            int cnt41=0;
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==OBracket||LA41_0==97) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // grammar/PlazmaScript.g:265:7: tail
            	    {
            	    pushFollow(FOLLOW_tail_in_indexes1968);
            	    tail187=tail();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_tail.add(tail187.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt41 >= 1 ) break loop41;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(41, input);
                        throw eee;
                }
                cnt41++;
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
            // 265:14: -> ^( TAILS ( tail )+ )
            {
                // grammar/PlazmaScript.g:265:17: ^( TAILS ( tail )+ )
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
    // grammar/PlazmaScript.g:268:1: tail : ( '[' expression ']' -> ^( INDEX expression ) | '.' Identifier -> ^( ATTRIBUTE Identifier ) | '.' Identifier '(' ( exprList )? ')' -> ^( CALL Identifier ( exprList )? ) );
    public final PlazmaScriptParser.tail_return tail() throws RecognitionException {
        PlazmaScriptParser.tail_return retval = new PlazmaScriptParser.tail_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal188=null;
        Token char_literal190=null;
        Token char_literal191=null;
        Token Identifier192=null;
        Token char_literal193=null;
        Token Identifier194=null;
        Token char_literal195=null;
        Token char_literal197=null;
        PlazmaScriptParser.expression_return expression189 = null;

        PlazmaScriptParser.exprList_return exprList196 = null;


        Object char_literal188_tree=null;
        Object char_literal190_tree=null;
        Object char_literal191_tree=null;
        Object Identifier192_tree=null;
        Object char_literal193_tree=null;
        Object Identifier194_tree=null;
        Object char_literal195_tree=null;
        Object char_literal197_tree=null;
        RewriteRuleTokenStream stream_CBracket=new RewriteRuleTokenStream(adaptor,"token CBracket");
        RewriteRuleTokenStream stream_97=new RewriteRuleTokenStream(adaptor,"token 97");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_OBracket=new RewriteRuleTokenStream(adaptor,"token OBracket");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_exprList=new RewriteRuleSubtreeStream(adaptor,"rule exprList");
        try {
            // grammar/PlazmaScript.g:269:2: ( '[' expression ']' -> ^( INDEX expression ) | '.' Identifier -> ^( ATTRIBUTE Identifier ) | '.' Identifier '(' ( exprList )? ')' -> ^( CALL Identifier ( exprList )? ) )
            int alt43=3;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==OBracket) ) {
                alt43=1;
            }
            else if ( (LA43_0==97) ) {
                int LA43_2 = input.LA(2);

                if ( (LA43_2==Identifier) ) {
                    int LA43_3 = input.LA(3);

                    if ( (LA43_3==OParen) ) {
                        alt43=3;
                    }
                    else if ( (LA43_3==EOF||(LA43_3>=In && LA43_3<=Range)||(LA43_3>=XorWord && LA43_3<=Pow)||(LA43_3>=GT && LA43_3<=Modulus)||(LA43_3>=CBrace && LA43_3<=CBracket)||(LA43_3>=CParen && LA43_3<=Colon)||LA43_3==97) ) {
                        alt43=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 43, 3, input);

                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 43, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;
            }
            switch (alt43) {
                case 1 :
                    // grammar/PlazmaScript.g:269:4: '[' expression ']'
                    {
                    char_literal188=(Token)match(input,OBracket,FOLLOW_OBracket_in_tail1991); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OBracket.add(char_literal188);

                    pushFollow(FOLLOW_expression_in_tail1993);
                    expression189=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression189.getTree());
                    char_literal190=(Token)match(input,CBracket,FOLLOW_CBracket_in_tail1995); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CBracket.add(char_literal190);



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
                    // 269:37: -> ^( INDEX expression )
                    {
                        // grammar/PlazmaScript.g:269:40: ^( INDEX expression )
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
                    // grammar/PlazmaScript.g:270:4: '.' Identifier
                    {
                    char_literal191=(Token)match(input,97,FOLLOW_97_in_tail2022); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_97.add(char_literal191);

                    Identifier192=(Token)match(input,Identifier,FOLLOW_Identifier_in_tail2024); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier192);



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
                    // 270:37: -> ^( ATTRIBUTE Identifier )
                    {
                        // grammar/PlazmaScript.g:270:40: ^( ATTRIBUTE Identifier )
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
                    // grammar/PlazmaScript.g:271:4: '.' Identifier '(' ( exprList )? ')'
                    {
                    char_literal193=(Token)match(input,97,FOLLOW_97_in_tail2055); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_97.add(char_literal193);

                    Identifier194=(Token)match(input,Identifier,FOLLOW_Identifier_in_tail2057); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier194);

                    char_literal195=(Token)match(input,OParen,FOLLOW_OParen_in_tail2059); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal195);

                    // grammar/PlazmaScript.g:271:23: ( exprList )?
                    int alt42=2;
                    int LA42_0 = input.LA(1);

                    if ( ((LA42_0>=Identifier && LA42_0<=Set)||(LA42_0>=Integer && LA42_0<=String)||(LA42_0>=Not && LA42_0<=NotWord)||LA42_0==Subtract||LA42_0==OBrace||LA42_0==OBracket||LA42_0==OParen||LA42_0==ContextIdentifier) ) {
                        alt42=1;
                    }
                    switch (alt42) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_tail2061);
                            exprList196=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList196.getTree());

                            }
                            break;

                    }

                    char_literal197=(Token)match(input,CParen,FOLLOW_CParen_in_tail2064); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal197);



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
                    // 271:37: -> ^( CALL Identifier ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:271:40: ^( CALL Identifier ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CALL, "CALL"), root_1);

                        adaptor.addChild(root_1, stream_Identifier.nextNode());
                        // grammar/PlazmaScript.g:271:58: ( exprList )?
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
    // grammar/PlazmaScript.g:364:1: anyIdentifier : ( ContextIdentifier | Identifier );
    public final PlazmaScriptParser.anyIdentifier_return anyIdentifier() throws RecognitionException {
        PlazmaScriptParser.anyIdentifier_return retval = new PlazmaScriptParser.anyIdentifier_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set198=null;

        Object set198_tree=null;

        try {
            // grammar/PlazmaScript.g:365:3: ( ContextIdentifier | Identifier )
            // grammar/PlazmaScript.g:
            {
            root_0 = (Object)adaptor.nil();

            set198=(Token)input.LT(1);
            if ( input.LA(1)==Identifier||input.LA(1)==ContextIdentifier ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set198));
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
        // grammar/PlazmaScript.g:79:6: ( assignment ';' )
        // grammar/PlazmaScript.g:79:6: assignment ';'
        {
        pushFollow(FOLLOW_assignment_in_synpred4_PlazmaScript284);
        assignment();

        state._fsp--;
        if (state.failed) return ;
        match(input,SColon,FOLLOW_SColon_in_synpred4_PlazmaScript286); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred4_PlazmaScript

    // $ANTLR start synpred5_PlazmaScript
    public final void synpred5_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:80:6: ( functionCall ';' )
        // grammar/PlazmaScript.g:80:6: functionCall ';'
        {
        pushFollow(FOLLOW_functionCall_in_synpred5_PlazmaScript299);
        functionCall();

        state._fsp--;
        if (state.failed) return ;
        match(input,SColon,FOLLOW_SColon_in_synpred5_PlazmaScript301); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred5_PlazmaScript

    // $ANTLR start synpred6_PlazmaScript
    public final void synpred6_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:81:6: ( lookup ';' )
        // grammar/PlazmaScript.g:81:6: lookup ';'
        {
        pushFollow(FOLLOW_lookup_in_synpred6_PlazmaScript312);
        lookup();

        state._fsp--;
        if (state.failed) return ;
        match(input,SColon,FOLLOW_SColon_in_synpred6_PlazmaScript314); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred6_PlazmaScript

    // $ANTLR start synpred13_PlazmaScript
    public final void synpred13_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:91:6: ( ( variableDef )? Identifier ( indexes )? '=' expression )
        // grammar/PlazmaScript.g:91:6: ( variableDef )? Identifier ( indexes )? '=' expression
        {
        // grammar/PlazmaScript.g:91:6: ( variableDef )?
        int alt44=2;
        int LA44_0 = input.LA(1);

        if ( (LA44_0==Var) ) {
            alt44=1;
        }
        switch (alt44) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: variableDef
                {
                pushFollow(FOLLOW_variableDef_in_synpred13_PlazmaScript415);
                variableDef();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        match(input,Identifier,FOLLOW_Identifier_in_synpred13_PlazmaScript418); if (state.failed) return ;
        // grammar/PlazmaScript.g:91:30: ( indexes )?
        int alt45=2;
        int LA45_0 = input.LA(1);

        if ( (LA45_0==OBracket||LA45_0==97) ) {
            alt45=1;
        }
        switch (alt45) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: indexes
                {
                pushFollow(FOLLOW_indexes_in_synpred13_PlazmaScript420);
                indexes();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        match(input,Assign,FOLLOW_Assign_in_synpred13_PlazmaScript423); if (state.failed) return ;
        pushFollow(FOLLOW_expression_in_synpred13_PlazmaScript425);
        expression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred13_PlazmaScript

    // $ANTLR start synpred74_PlazmaScript
    public final void synpred74_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:257:6: ( Identifier ( indexes )? )
        // grammar/PlazmaScript.g:257:6: Identifier ( indexes )?
        {
        match(input,Identifier,FOLLOW_Identifier_in_synpred74_PlazmaScript1842); if (state.failed) return ;
        // grammar/PlazmaScript.g:257:17: ( indexes )?
        int alt53=2;
        int LA53_0 = input.LA(1);

        if ( (LA53_0==OBracket||LA53_0==97) ) {
            alt53=1;
        }
        switch (alt53) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: indexes
                {
                pushFollow(FOLLOW_indexes_in_synpred74_PlazmaScript1844);
                indexes();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred74_PlazmaScript

    // $ANTLR start synpred76_PlazmaScript
    public final void synpred76_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:258:6: ( anyIdentifier ( indexes )? )
        // grammar/PlazmaScript.g:258:6: anyIdentifier ( indexes )?
        {
        pushFollow(FOLLOW_anyIdentifier_in_synpred76_PlazmaScript1871);
        anyIdentifier();

        state._fsp--;
        if (state.failed) return ;
        // grammar/PlazmaScript.g:258:20: ( indexes )?
        int alt54=2;
        int LA54_0 = input.LA(1);

        if ( (LA54_0==OBracket||LA54_0==97) ) {
            alt54=1;
        }
        switch (alt54) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: indexes
                {
                pushFollow(FOLLOW_indexes_in_synpred76_PlazmaScript1873);
                indexes();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred76_PlazmaScript

    // Delegated rules

    public final boolean synpred74_PlazmaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred74_PlazmaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
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
    public final boolean synpred76_PlazmaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred76_PlazmaScript_fragment(); // can never throw exception
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


    protected DFA3 dfa3 = new DFA3(this);
    static final String DFA3_eotS =
        "\24\uffff";
    static final String DFA3_eofS =
        "\24\uffff";
    static final String DFA3_minS =
        "\1\40\1\uffff\10\0\12\uffff";
    static final String DFA3_maxS =
        "\1\133\1\uffff\10\0\12\uffff";
    static final String DFA3_acceptS =
        "\1\uffff\1\1\10\uffff\1\3\3\uffff\1\4\1\5\1\6\1\7\1\10\1\2";
    static final String DFA3_specialS =
        "\2\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\12\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\21\1\22\1\2\1\4\1\5\1\6\1\7\1\10\1\11\1\16\1\uffff\1\1\1"+
            "\uffff\1\17\1\20\11\uffff\1\12\25\uffff\1\12\1\uffff\1\12\1"+
            "\uffff\1\12\10\uffff\1\3",
            "",
            "\1\uffff",
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
            return "78:1: statement : ( assignment ';' -> assignment | functionCall ';' -> functionCall | lookup ';' -> lookup | ifStatement | forStatement | whileStatement | Break ';' -> Break | Continue ';' -> Continue );";
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

                        else if ( (synpred5_PlazmaScript()) ) {s = 19;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 10;}

                         
                        input.seek(index3_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA3_3 = input.LA(1);

                         
                        int index3_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_PlazmaScript()) ) {s = 1;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 10;}

                         
                        input.seek(index3_3);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA3_4 = input.LA(1);

                         
                        int index3_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 19;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 10;}

                         
                        input.seek(index3_4);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA3_5 = input.LA(1);

                         
                        int index3_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 19;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 10;}

                         
                        input.seek(index3_5);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA3_6 = input.LA(1);

                         
                        int index3_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 19;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 10;}

                         
                        input.seek(index3_6);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA3_7 = input.LA(1);

                         
                        int index3_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 19;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 10;}

                         
                        input.seek(index3_7);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA3_8 = input.LA(1);

                         
                        int index3_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 19;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 10;}

                         
                        input.seek(index3_8);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA3_9 = input.LA(1);

                         
                        int index3_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 19;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 10;}

                         
                        input.seek(index3_9);
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
 

    public static final BitSet FOLLOW_block_in_parse208 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_parse210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_block229 = new BitSet(new long[]{0x01007BFF80000002L,0x0000000008054000L});
    public static final BitSet FOLLOW_functionDecl_in_block233 = new BitSet(new long[]{0x01007BFF80000002L,0x0000000008054000L});
    public static final BitSet FOLLOW_Return_in_block238 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_expression_in_block240 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_SColon_in_block242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_statement284 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_SColon_in_statement286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_statement299 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_SColon_in_statement301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_statement312 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_SColon_in_statement314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStatement_in_statement358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forStatement_in_statement365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whileStatement_in_statement372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Break_in_statement379 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_SColon_in_statement381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Continue_in_statement392 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_SColon_in_statement394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDef_in_assignment415 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_Identifier_in_assignment418 = new BitSet(new long[]{0x0000000000000000L,0x0000000200210000L});
    public static final BitSet FOLLOW_indexes_in_assignment420 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_Assign_in_assignment423 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_expression_in_assignment425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDef_in_assignment448 = new BitSet(new long[]{0x0000080400000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_anyIdentifier_in_assignment451 = new BitSet(new long[]{0x0000000000000000L,0x0000000200210000L});
    public static final BitSet FOLLOW_indexes_in_assignment453 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_Assign_in_assignment456 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_expression_in_assignment458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_functionCall488 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_OParen_in_functionCall490 = new BitSet(new long[]{0x01FC09FC00000000L,0x00000000080D4460L});
    public static final BitSet FOLLOW_exprList_in_functionCall492 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_CParen_in_functionCall495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Println_in_functionCall513 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_OParen_in_functionCall515 = new BitSet(new long[]{0x01FC09FC00000000L,0x00000000080D4460L});
    public static final BitSet FOLLOW_expression_in_functionCall517 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_CParen_in_functionCall520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Print_in_functionCall539 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_OParen_in_functionCall541 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_expression_in_functionCall543 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_CParen_in_functionCall545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Assert_in_functionCall566 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_OParen_in_functionCall568 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_expression_in_functionCall570 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_CParen_in_functionCall572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Date_in_functionCall592 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_OParen_in_functionCall594 = new BitSet(new long[]{0x01FC09FC00000000L,0x00000000080D4460L});
    public static final BitSet FOLLOW_exprList_in_functionCall596 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_CParen_in_functionCall599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_List_in_functionCall622 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_OParen_in_functionCall624 = new BitSet(new long[]{0x01FC09FC00000000L,0x00000000080D4460L});
    public static final BitSet FOLLOW_exprList_in_functionCall626 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_CParen_in_functionCall629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Set_in_functionCall652 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_OParen_in_functionCall654 = new BitSet(new long[]{0x01FC09FC00000000L,0x00000000080D4460L});
    public static final BitSet FOLLOW_exprList_in_functionCall656 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_CParen_in_functionCall659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStat_in_ifStatement697 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_elseIfStat_in_ifStatement699 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_elseStat_in_ifStatement702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_If_in_ifStat731 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_OParen_in_ifStat733 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_expression_in_ifStat735 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_CParen_in_ifStat737 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_OBrace_in_ifStat739 = new BitSet(new long[]{0x01007BFF80000000L,0x0000000008054000L});
    public static final BitSet FOLLOW_block_in_ifStat741 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_CBrace_in_ifStat743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Else_in_elseIfStat767 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_If_in_elseIfStat769 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_OParen_in_elseIfStat771 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_expression_in_elseIfStat773 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_CParen_in_elseIfStat775 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_OBrace_in_elseIfStat777 = new BitSet(new long[]{0x01007BFF80000000L,0x0000000008054000L});
    public static final BitSet FOLLOW_block_in_elseIfStat779 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_CBrace_in_elseIfStat781 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Else_in_elseStat805 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_OBrace_in_elseStat807 = new BitSet(new long[]{0x01007BFF80000000L,0x0000000008054000L});
    public static final BitSet FOLLOW_block_in_elseStat809 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_CBrace_in_elseStat811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Var_in_variableDef831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Def_in_functionDecl863 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_Identifier_in_functionDecl865 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_OParen_in_functionDecl867 = new BitSet(new long[]{0x0000000400000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_idList_in_functionDecl869 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_CParen_in_functionDecl872 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_OBrace_in_functionDecl874 = new BitSet(new long[]{0x01007BFF80000000L,0x0000000008054000L});
    public static final BitSet FOLLOW_block_in_functionDecl876 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_CBrace_in_functionDecl878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_For_in_forStatement907 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_OParen_in_forStatement909 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_Identifier_in_forStatement911 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_In_in_forStatement913 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_expression_in_forStatement915 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_CParen_in_forStatement917 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_OBrace_in_forStatement919 = new BitSet(new long[]{0x01007BFF80000000L,0x0000000008054000L});
    public static final BitSet FOLLOW_block_in_forStatement921 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_CBrace_in_forStatement923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_While_in_whileStatement958 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_OParen_in_whileStatement960 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_expression_in_whileStatement962 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_CParen_in_whileStatement964 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_OBrace_in_whileStatement966 = new BitSet(new long[]{0x01007BFF80000000L,0x0000000008054000L});
    public static final BitSet FOLLOW_block_in_whileStatement968 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_CBrace_in_whileStatement970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_idList994 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400000L});
    public static final BitSet FOLLOW_Comma_in_idList997 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_Identifier_in_idList999 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400000L});
    public static final BitSet FOLLOW_expression_in_exprList1024 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400000L});
    public static final BitSet FOLLOW_Comma_in_exprList1027 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_expression_in_exprList1029 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400000L});
    public static final BitSet FOLLOW_expression_in_exprPair1055 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_Colon_in_exprPair1057 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_expression_in_exprPair1059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprPair_in_exprMap1084 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400000L});
    public static final BitSet FOLLOW_Comma_in_exprMap1087 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_exprPair_in_exprMap1089 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400000L});
    public static final BitSet FOLLOW_condExpr_in_expression1115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_startExpr_in_condExpr1130 = new BitSet(new long[]{0x0003800000000002L,0x0000000000800000L});
    public static final BitSet FOLLOW_QMark_in_condExpr1145 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_expression_in_condExpr1149 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_Colon_in_condExpr1151 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_expression_in_condExpr1155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_In_in_condExpr1178 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_expression_in_condExpr1180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RangeE_in_condExpr1225 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_expression_in_condExpr1227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Range_in_condExpr1262 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_expression_in_condExpr1264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orExpr_in_startExpr1327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_andExpr_in_orExpr1340 = new BitSet(new long[]{0x1E00000000000002L});
    public static final BitSet FOLLOW_set_in_orExpr1343 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_andExpr_in_orExpr1360 = new BitSet(new long[]{0x1E00000000000002L});
    public static final BitSet FOLLOW_equExpr_in_andExpr1377 = new BitSet(new long[]{0xE000000000000002L});
    public static final BitSet FOLLOW_set_in_andExpr1380 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_equExpr_in_andExpr1393 = new BitSet(new long[]{0xE000000000000002L});
    public static final BitSet FOLLOW_relExpr_in_equExpr1409 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000003L});
    public static final BitSet FOLLOW_set_in_equExpr1412 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_relExpr_in_equExpr1421 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000003L});
    public static final BitSet FOLLOW_addExpr_in_relExpr1437 = new BitSet(new long[]{0x0000000000000002L,0x000000000000018CL});
    public static final BitSet FOLLOW_set_in_relExpr1440 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_addExpr_in_relExpr1457 = new BitSet(new long[]{0x0000000000000002L,0x000000000000018CL});
    public static final BitSet FOLLOW_mulExpr_in_addExpr1473 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000600L});
    public static final BitSet FOLLOW_set_in_addExpr1476 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_mulExpr_in_addExpr1485 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000600L});
    public static final BitSet FOLLOW_powExpr_in_mulExpr1501 = new BitSet(new long[]{0x0000000000000002L,0x0000000000003800L});
    public static final BitSet FOLLOW_set_in_mulExpr1504 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_powExpr_in_mulExpr1517 = new BitSet(new long[]{0x0000000000000002L,0x0000000000003800L});
    public static final BitSet FOLLOW_unaryExpr_in_powExpr1533 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_Pow_in_powExpr1536 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_unaryExpr_in_powExpr1539 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_Subtract_in_unaryExpr1557 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Not_in_unaryExpr1574 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NotWord_in_unaryExpr1591 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Integer_in_atom1624 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Number_in_atom1631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Bool_in_atom1638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Null_in_atom1645 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NaN_in_atom1652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Infinity_in_atom1659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_atom1667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OBracket_in_list1681 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008074460L});
    public static final BitSet FOLLOW_exprList_in_list1683 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_CBracket_in_list1686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OBrace_in_map1709 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000009054460L});
    public static final BitSet FOLLOW_Colon_in_map1712 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_exprMap_in_map1716 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_CBrace_in_map1719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_lookup1742 = new BitSet(new long[]{0x0000000000000002L,0x0000000200010000L});
    public static final BitSet FOLLOW_indexes_in_lookup1744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_in_lookup1769 = new BitSet(new long[]{0x0000000000000002L,0x0000000200010000L});
    public static final BitSet FOLLOW_indexes_in_lookup1771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_map_in_lookup1804 = new BitSet(new long[]{0x0000000000000002L,0x0000000200010000L});
    public static final BitSet FOLLOW_indexes_in_lookup1806 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_lookup1842 = new BitSet(new long[]{0x0000000000000002L,0x0000000200010000L});
    public static final BitSet FOLLOW_indexes_in_lookup1844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_anyIdentifier_in_lookup1871 = new BitSet(new long[]{0x0000000000000002L,0x0000000200010000L});
    public static final BitSet FOLLOW_indexes_in_lookup1873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_String_in_lookup1899 = new BitSet(new long[]{0x0000000000000002L,0x0000000200010000L});
    public static final BitSet FOLLOW_indexes_in_lookup1901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OParen_in_lookup1932 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_expression_in_lookup1934 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_CParen_in_lookup1936 = new BitSet(new long[]{0x0000000000000002L,0x0000000200010000L});
    public static final BitSet FOLLOW_indexes_in_lookup1938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tail_in_indexes1968 = new BitSet(new long[]{0x0000000000000002L,0x0000000200010000L});
    public static final BitSet FOLLOW_OBracket_in_tail1991 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_expression_in_tail1993 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_CBracket_in_tail1995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_97_in_tail2022 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_Identifier_in_tail2024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_97_in_tail2055 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_Identifier_in_tail2057 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_OParen_in_tail2059 = new BitSet(new long[]{0x01FC09FC00000000L,0x00000000080D4460L});
    public static final BitSet FOLLOW_exprList_in_tail2061 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_CParen_in_tail2064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_anyIdentifier0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_synpred4_PlazmaScript284 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_SColon_in_synpred4_PlazmaScript286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_synpred5_PlazmaScript299 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_SColon_in_synpred5_PlazmaScript301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_synpred6_PlazmaScript312 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_SColon_in_synpred6_PlazmaScript314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDef_in_synpred13_PlazmaScript415 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_Identifier_in_synpred13_PlazmaScript418 = new BitSet(new long[]{0x0000000000000000L,0x0000000200210000L});
    public static final BitSet FOLLOW_indexes_in_synpred13_PlazmaScript420 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_Assign_in_synpred13_PlazmaScript423 = new BitSet(new long[]{0x01FC09FC00000000L,0x0000000008054460L});
    public static final BitSet FOLLOW_expression_in_synpred13_PlazmaScript425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_synpred74_PlazmaScript1842 = new BitSet(new long[]{0x0000000000000002L,0x0000000200010000L});
    public static final BitSet FOLLOW_indexes_in_synpred74_PlazmaScript1844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_anyIdentifier_in_synpred76_PlazmaScript1871 = new BitSet(new long[]{0x0000000000000002L,0x0000000200010000L});
    public static final BitSet FOLLOW_indexes_in_synpred76_PlazmaScript1873 = new BitSet(new long[]{0x0000000000000002L});

}