// $ANTLR 3.2 Sep 23, 2009 12:02:23 grammar/PlazmaScript.g 2016-09-06 15:40:00

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BLOCK", "RETURN", "STATEMENTS", "ASSIGNMENT", "DECLARE", "FUNC_CALL", "EXP", "EXP_PAIR", "EXP_MAP", "EXP_LIST", "ID_LIST", "IF", "TERNARY", "UNARY_PLUS", "UNARY_MIN", "NEGATE", "FUNCTION", "INDEXES", "INDEX", "ATTRIBUTE", "CALL", "TAIL", "TAILS", "MAP", "LIST", "SET", "LOOKUP", "BREAK", "CONTINUE", "Return", "Break", "Continue", "Identifier", "Println", "Print", "Assert", "Date", "DateTime", "Time", "Duration", "Period", "List", "Set", "If", "Else", "Var", "Def", "For", "While", "In", "RangeE", "Range", "Integer", "Number", "Bool", "Null", "NaN", "Infinity", "String", "XorWord", "Or", "BitOr", "OrWord", "And", "BitAnd", "AndWord", "Equals", "NEquals", "GTEquals", "LTEquals", "Pow", "Not", "NotWord", "GT", "LT", "Add", "Subtract", "Multiply", "Divide", "Modulus", "OBrace", "CBrace", "OBracket", "CBracket", "OParen", "CParen", "SColon", "Assign", "Comma", "QMark", "Colon", "DecimalNumeral", "DecimalFloatingPoint", "Digits", "ExponentPart", "ContextIdentifier", "Digit", "Comment", "Space", "ExponentIndicator", "SignedInteger", "Sign", "NonZeroDigit", "'.'"
    };
    public static final int FUNCTION=20;
    public static final int LT=78;
    public static final int TERNARY=16;
    public static final int EXP_LIST=13;
    public static final int DateTime=41;
    public static final int Date=40;
    public static final int QMark=93;
    public static final int EOF=-1;
    public static final int NotWord=76;
    public static final int BREAK=31;
    public static final int Identifier=36;
    public static final int UNARY_PLUS=17;
    public static final int FUNC_CALL=9;
    public static final int CParen=89;
    public static final int Comment=101;
    public static final int EXP=10;
    public static final int Digits=97;
    public static final int RETURN=5;
    public static final int CBrace=85;
    public static final int ExponentPart=98;
    public static final int ExponentIndicator=103;
    public static final int Sign=105;
    public static final int DecimalNumeral=95;
    public static final int Null=59;
    public static final int OrWord=66;
    public static final int ContextIdentifier=99;
    public static final int Period=44;
    public static final int CBracket=87;
    public static final int Println=37;
    public static final int Bool=58;
    public static final int Modulus=83;
    public static final int Time=42;
    public static final int Colon=94;
    public static final int AndWord=69;
    public static final int LIST=28;
    public static final int Def=50;
    public static final int LOOKUP=30;
    public static final int RangeE=54;
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
    public static final int TAILS=26;
    public static final int List=45;
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
    // grammar/PlazmaScript.g:71:1: parse : block EOF -> block ;
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
            // grammar/PlazmaScript.g:72:3: ( block EOF -> block )
            // grammar/PlazmaScript.g:72:6: block EOF
            {
            pushFollow(FOLLOW_block_in_parse220);
            block1=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block1.getTree());
            EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_parse222); if (state.failed) return retval; 
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
            // 72:16: -> block
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
    // grammar/PlazmaScript.g:75:1: block : ( statement | functionDecl )* ( Return expression ';' )? -> ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) ) ;
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
            // grammar/PlazmaScript.g:76:3: ( ( statement | functionDecl )* ( Return expression ';' )? -> ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) ) )
            // grammar/PlazmaScript.g:76:6: ( statement | functionDecl )* ( Return expression ';' )?
            {
            // grammar/PlazmaScript.g:76:6: ( statement | functionDecl )*
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
            	    // grammar/PlazmaScript.g:76:7: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_block241);
            	    statement3=statement();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_statement.add(statement3.getTree());

            	    }
            	    break;
            	case 2 :
            	    // grammar/PlazmaScript.g:76:19: functionDecl
            	    {
            	    pushFollow(FOLLOW_functionDecl_in_block245);
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

            // grammar/PlazmaScript.g:76:34: ( Return expression ';' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==Return) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // grammar/PlazmaScript.g:76:35: Return expression ';'
                    {
                    Return5=(Token)match(input,Return,FOLLOW_Return_in_block250); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Return.add(Return5);

                    pushFollow(FOLLOW_expression_in_block252);
                    expression6=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression6.getTree());
                    char_literal7=(Token)match(input,SColon,FOLLOW_SColon_in_block254); if (state.failed) return retval; 
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
            // 77:6: -> ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) )
            {
                // grammar/PlazmaScript.g:77:9: ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BLOCK, "BLOCK"), root_1);

                // grammar/PlazmaScript.g:77:17: ^( STATEMENTS ( statement )* )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(STATEMENTS, "STATEMENTS"), root_2);

                // grammar/PlazmaScript.g:77:30: ( statement )*
                while ( stream_statement.hasNext() ) {
                    adaptor.addChild(root_2, stream_statement.nextTree());

                }
                stream_statement.reset();

                adaptor.addChild(root_1, root_2);
                }
                // grammar/PlazmaScript.g:77:42: ^( RETURN ( expression )? )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(RETURN, "RETURN"), root_2);

                // grammar/PlazmaScript.g:77:51: ( expression )?
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
    // grammar/PlazmaScript.g:80:1: statement : ( assignment ';' -> assignment | declare ';' -> declare | functionCall ';' -> functionCall | lookup ';' -> lookup | ifStatement | forStatement | whileStatement | Break ';' -> Break | Continue ';' -> Continue );
    public final PlazmaScriptParser.statement_return statement() throws RecognitionException {
        PlazmaScriptParser.statement_return retval = new PlazmaScriptParser.statement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal9=null;
        Token char_literal11=null;
        Token char_literal13=null;
        Token char_literal15=null;
        Token Break19=null;
        Token char_literal20=null;
        Token Continue21=null;
        Token char_literal22=null;
        PlazmaScriptParser.assignment_return assignment8 = null;

        PlazmaScriptParser.declare_return declare10 = null;

        PlazmaScriptParser.functionCall_return functionCall12 = null;

        PlazmaScriptParser.lookup_return lookup14 = null;

        PlazmaScriptParser.ifStatement_return ifStatement16 = null;

        PlazmaScriptParser.forStatement_return forStatement17 = null;

        PlazmaScriptParser.whileStatement_return whileStatement18 = null;


        Object char_literal9_tree=null;
        Object char_literal11_tree=null;
        Object char_literal13_tree=null;
        Object char_literal15_tree=null;
        Object Break19_tree=null;
        Object char_literal20_tree=null;
        Object Continue21_tree=null;
        Object char_literal22_tree=null;
        RewriteRuleTokenStream stream_Break=new RewriteRuleTokenStream(adaptor,"token Break");
        RewriteRuleTokenStream stream_Continue=new RewriteRuleTokenStream(adaptor,"token Continue");
        RewriteRuleTokenStream stream_SColon=new RewriteRuleTokenStream(adaptor,"token SColon");
        RewriteRuleSubtreeStream stream_functionCall=new RewriteRuleSubtreeStream(adaptor,"rule functionCall");
        RewriteRuleSubtreeStream stream_assignment=new RewriteRuleSubtreeStream(adaptor,"rule assignment");
        RewriteRuleSubtreeStream stream_lookup=new RewriteRuleSubtreeStream(adaptor,"rule lookup");
        RewriteRuleSubtreeStream stream_declare=new RewriteRuleSubtreeStream(adaptor,"rule declare");
        try {
            // grammar/PlazmaScript.g:81:3: ( assignment ';' -> assignment | declare ';' -> declare | functionCall ';' -> functionCall | lookup ';' -> lookup | ifStatement | forStatement | whileStatement | Break ';' -> Break | Continue ';' -> Continue )
            int alt3=9;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // grammar/PlazmaScript.g:81:6: assignment ';'
                    {
                    pushFollow(FOLLOW_assignment_in_statement296);
                    assignment8=assignment();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_assignment.add(assignment8.getTree());
                    char_literal9=(Token)match(input,SColon,FOLLOW_SColon_in_statement298); if (state.failed) return retval; 
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
                    // 81:23: -> assignment
                    {
                        adaptor.addChild(root_0, stream_assignment.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:82:6: declare ';'
                    {
                    pushFollow(FOLLOW_declare_in_statement311);
                    declare10=declare();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_declare.add(declare10.getTree());
                    char_literal11=(Token)match(input,SColon,FOLLOW_SColon_in_statement313); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SColon.add(char_literal11);



                    // AST REWRITE
                    // elements: declare
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 82:20: -> declare
                    {
                        adaptor.addChild(root_0, stream_declare.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // grammar/PlazmaScript.g:83:6: functionCall ';'
                    {
                    pushFollow(FOLLOW_functionCall_in_statement328);
                    functionCall12=functionCall();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_functionCall.add(functionCall12.getTree());
                    char_literal13=(Token)match(input,SColon,FOLLOW_SColon_in_statement330); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SColon.add(char_literal13);



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
                    // 83:23: -> functionCall
                    {
                        adaptor.addChild(root_0, stream_functionCall.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 4 :
                    // grammar/PlazmaScript.g:84:6: lookup ';'
                    {
                    pushFollow(FOLLOW_lookup_in_statement341);
                    lookup14=lookup();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_lookup.add(lookup14.getTree());
                    char_literal15=(Token)match(input,SColon,FOLLOW_SColon_in_statement343); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SColon.add(char_literal15);



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
                    // 84:17: -> lookup
                    {
                        adaptor.addChild(root_0, stream_lookup.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 5 :
                    // grammar/PlazmaScript.g:85:6: ifStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_ifStatement_in_statement387);
                    ifStatement16=ifStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ifStatement16.getTree());

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScript.g:86:6: forStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_forStatement_in_statement394);
                    forStatement17=forStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, forStatement17.getTree());

                    }
                    break;
                case 7 :
                    // grammar/PlazmaScript.g:87:6: whileStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_whileStatement_in_statement401);
                    whileStatement18=whileStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, whileStatement18.getTree());

                    }
                    break;
                case 8 :
                    // grammar/PlazmaScript.g:88:6: Break ';'
                    {
                    Break19=(Token)match(input,Break,FOLLOW_Break_in_statement408); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Break.add(Break19);

                    char_literal20=(Token)match(input,SColon,FOLLOW_SColon_in_statement410); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SColon.add(char_literal20);



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
                    // 88:16: -> Break
                    {
                        adaptor.addChild(root_0, stream_Break.nextNode());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 9 :
                    // grammar/PlazmaScript.g:89:6: Continue ';'
                    {
                    Continue21=(Token)match(input,Continue,FOLLOW_Continue_in_statement421); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Continue.add(Continue21);

                    char_literal22=(Token)match(input,SColon,FOLLOW_SColon_in_statement423); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SColon.add(char_literal22);



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
                    // 89:19: -> Continue
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
    // grammar/PlazmaScript.g:93:1: assignment : ( ( variableDef )? Identifier ( indexes )? '=' expression -> ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression ) | ( variableDef )? anyIdentifier ( indexes )? '=' expression -> ^( ASSIGNMENT ( variableDef )? ( indexes )? expression ) );
    public final PlazmaScriptParser.assignment_return assignment() throws RecognitionException {
        PlazmaScriptParser.assignment_return retval = new PlazmaScriptParser.assignment_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Identifier24=null;
        Token char_literal26=null;
        Token char_literal31=null;
        PlazmaScriptParser.variableDef_return variableDef23 = null;

        PlazmaScriptParser.indexes_return indexes25 = null;

        PlazmaScriptParser.expression_return expression27 = null;

        PlazmaScriptParser.variableDef_return variableDef28 = null;

        PlazmaScriptParser.anyIdentifier_return anyIdentifier29 = null;

        PlazmaScriptParser.indexes_return indexes30 = null;

        PlazmaScriptParser.expression_return expression32 = null;


        Object Identifier24_tree=null;
        Object char_literal26_tree=null;
        Object char_literal31_tree=null;
        RewriteRuleTokenStream stream_Assign=new RewriteRuleTokenStream(adaptor,"token Assign");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_variableDef=new RewriteRuleSubtreeStream(adaptor,"rule variableDef");
        RewriteRuleSubtreeStream stream_anyIdentifier=new RewriteRuleSubtreeStream(adaptor,"rule anyIdentifier");
        RewriteRuleSubtreeStream stream_indexes=new RewriteRuleSubtreeStream(adaptor,"rule indexes");
        try {
            // grammar/PlazmaScript.g:94:3: ( ( variableDef )? Identifier ( indexes )? '=' expression -> ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression ) | ( variableDef )? anyIdentifier ( indexes )? '=' expression -> ^( ASSIGNMENT ( variableDef )? ( indexes )? expression ) )
            int alt8=2;
            switch ( input.LA(1) ) {
            case Var:
                {
                int LA8_1 = input.LA(2);

                if ( (synpred14_PlazmaScript()) ) {
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

                if ( (synpred14_PlazmaScript()) ) {
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
                    // grammar/PlazmaScript.g:94:6: ( variableDef )? Identifier ( indexes )? '=' expression
                    {
                    // grammar/PlazmaScript.g:94:6: ( variableDef )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==Var) ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: variableDef
                            {
                            pushFollow(FOLLOW_variableDef_in_assignment444);
                            variableDef23=variableDef();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_variableDef.add(variableDef23.getTree());

                            }
                            break;

                    }

                    Identifier24=(Token)match(input,Identifier,FOLLOW_Identifier_in_assignment447); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier24);

                    // grammar/PlazmaScript.g:94:30: ( indexes )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==OBracket||LA5_0==107) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_assignment449);
                            indexes25=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes25.getTree());

                            }
                            break;

                    }

                    char_literal26=(Token)match(input,Assign,FOLLOW_Assign_in_assignment452); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Assign.add(char_literal26);

                    pushFollow(FOLLOW_expression_in_assignment454);
                    expression27=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression27.getTree());


                    // AST REWRITE
                    // elements: variableDef, expression, indexes, Identifier
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 94:54: -> ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression )
                    {
                        // grammar/PlazmaScript.g:94:57: ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ASSIGNMENT, "ASSIGNMENT"), root_1);

                        // grammar/PlazmaScript.g:94:70: ( variableDef )?
                        if ( stream_variableDef.hasNext() ) {
                            adaptor.addChild(root_1, stream_variableDef.nextTree());

                        }
                        stream_variableDef.reset();
                        adaptor.addChild(root_1, stream_Identifier.nextNode());
                        // grammar/PlazmaScript.g:94:94: ( indexes )?
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
                    // grammar/PlazmaScript.g:95:6: ( variableDef )? anyIdentifier ( indexes )? '=' expression
                    {
                    // grammar/PlazmaScript.g:95:6: ( variableDef )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==Var) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: variableDef
                            {
                            pushFollow(FOLLOW_variableDef_in_assignment477);
                            variableDef28=variableDef();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_variableDef.add(variableDef28.getTree());

                            }
                            break;

                    }

                    pushFollow(FOLLOW_anyIdentifier_in_assignment480);
                    anyIdentifier29=anyIdentifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_anyIdentifier.add(anyIdentifier29.getTree());
                    // grammar/PlazmaScript.g:95:33: ( indexes )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==OBracket||LA7_0==107) ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_assignment482);
                            indexes30=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes30.getTree());

                            }
                            break;

                    }

                    char_literal31=(Token)match(input,Assign,FOLLOW_Assign_in_assignment485); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Assign.add(char_literal31);

                    pushFollow(FOLLOW_expression_in_assignment487);
                    expression32=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression32.getTree());


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
                    // 95:57: -> ^( ASSIGNMENT ( variableDef )? ( indexes )? expression )
                    {
                        // grammar/PlazmaScript.g:95:60: ^( ASSIGNMENT ( variableDef )? ( indexes )? expression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ASSIGNMENT, "ASSIGNMENT"), root_1);

                        // grammar/PlazmaScript.g:95:73: ( variableDef )?
                        if ( stream_variableDef.hasNext() ) {
                            adaptor.addChild(root_1, stream_variableDef.nextTree());

                        }
                        stream_variableDef.reset();
                        adaptor.addChild(root_1, new CommonTree(new CommonToken(Identifier, (anyIdentifier29!=null?input.toString(anyIdentifier29.start,anyIdentifier29.stop):null))));
                        // grammar/PlazmaScript.g:95:153: ( indexes )?
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

    public static class declare_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "declare"
    // grammar/PlazmaScript.g:98:1: declare : variableDef Identifier -> ^( DECLARE variableDef Identifier ) ;
    public final PlazmaScriptParser.declare_return declare() throws RecognitionException {
        PlazmaScriptParser.declare_return retval = new PlazmaScriptParser.declare_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Identifier34=null;
        PlazmaScriptParser.variableDef_return variableDef33 = null;


        Object Identifier34_tree=null;
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
        RewriteRuleSubtreeStream stream_variableDef=new RewriteRuleSubtreeStream(adaptor,"rule variableDef");
        try {
            // grammar/PlazmaScript.g:99:3: ( variableDef Identifier -> ^( DECLARE variableDef Identifier ) )
            // grammar/PlazmaScript.g:99:5: variableDef Identifier
            {
            pushFollow(FOLLOW_variableDef_in_declare518);
            variableDef33=variableDef();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_variableDef.add(variableDef33.getTree());
            Identifier34=(Token)match(input,Identifier,FOLLOW_Identifier_in_declare520); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Identifier.add(Identifier34);



            // AST REWRITE
            // elements: Identifier, variableDef
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 99:28: -> ^( DECLARE variableDef Identifier )
            {
                // grammar/PlazmaScript.g:99:32: ^( DECLARE variableDef Identifier )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(DECLARE, "DECLARE"), root_1);

                adaptor.addChild(root_1, stream_variableDef.nextTree());
                adaptor.addChild(root_1, stream_Identifier.nextNode());

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
    // $ANTLR end "declare"

    public static class functionCall_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "functionCall"
    // grammar/PlazmaScript.g:102:1: functionCall : ( Identifier '(' ( exprList )? ')' -> ^( FUNC_CALL Identifier ( exprList )? ) | Println '(' ( expression )? ')' -> ^( FUNC_CALL Println ( expression )? ) | Print '(' expression ')' -> ^( FUNC_CALL Print expression ) | Assert '(' expression ')' -> ^( FUNC_CALL Assert expression ) | Date '(' ( exprList )? ')' -> ^( FUNC_CALL Date ( exprList )? ) | DateTime '(' ( exprList )? ')' -> ^( FUNC_CALL DateTime ( exprList )? ) | Time '(' ( exprList )? ')' -> ^( FUNC_CALL Time ( exprList )? ) | Duration '(' ( expression )? ')' -> ^( FUNC_CALL Duration ( expression )? ) | Period '(' ( exprList )? ')' -> ^( FUNC_CALL Period ( exprList )? ) | List '(' ( exprList )? ')' -> ^( FUNC_CALL List ( exprList )? ) | Set '(' ( exprList )? ')' -> ^( FUNC_CALL Set ( exprList )? ) );
    public final PlazmaScriptParser.functionCall_return functionCall() throws RecognitionException {
        PlazmaScriptParser.functionCall_return retval = new PlazmaScriptParser.functionCall_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Identifier35=null;
        Token char_literal36=null;
        Token char_literal38=null;
        Token Println39=null;
        Token char_literal40=null;
        Token char_literal42=null;
        Token Print43=null;
        Token char_literal44=null;
        Token char_literal46=null;
        Token Assert47=null;
        Token char_literal48=null;
        Token char_literal50=null;
        Token Date51=null;
        Token char_literal52=null;
        Token char_literal54=null;
        Token DateTime55=null;
        Token char_literal56=null;
        Token char_literal58=null;
        Token Time59=null;
        Token char_literal60=null;
        Token char_literal62=null;
        Token Duration63=null;
        Token char_literal64=null;
        Token char_literal66=null;
        Token Period67=null;
        Token char_literal68=null;
        Token char_literal70=null;
        Token List71=null;
        Token char_literal72=null;
        Token char_literal74=null;
        Token Set75=null;
        Token char_literal76=null;
        Token char_literal78=null;
        PlazmaScriptParser.exprList_return exprList37 = null;

        PlazmaScriptParser.expression_return expression41 = null;

        PlazmaScriptParser.expression_return expression45 = null;

        PlazmaScriptParser.expression_return expression49 = null;

        PlazmaScriptParser.exprList_return exprList53 = null;

        PlazmaScriptParser.exprList_return exprList57 = null;

        PlazmaScriptParser.exprList_return exprList61 = null;

        PlazmaScriptParser.expression_return expression65 = null;

        PlazmaScriptParser.exprList_return exprList69 = null;

        PlazmaScriptParser.exprList_return exprList73 = null;

        PlazmaScriptParser.exprList_return exprList77 = null;


        Object Identifier35_tree=null;
        Object char_literal36_tree=null;
        Object char_literal38_tree=null;
        Object Println39_tree=null;
        Object char_literal40_tree=null;
        Object char_literal42_tree=null;
        Object Print43_tree=null;
        Object char_literal44_tree=null;
        Object char_literal46_tree=null;
        Object Assert47_tree=null;
        Object char_literal48_tree=null;
        Object char_literal50_tree=null;
        Object Date51_tree=null;
        Object char_literal52_tree=null;
        Object char_literal54_tree=null;
        Object DateTime55_tree=null;
        Object char_literal56_tree=null;
        Object char_literal58_tree=null;
        Object Time59_tree=null;
        Object char_literal60_tree=null;
        Object char_literal62_tree=null;
        Object Duration63_tree=null;
        Object char_literal64_tree=null;
        Object char_literal66_tree=null;
        Object Period67_tree=null;
        Object char_literal68_tree=null;
        Object char_literal70_tree=null;
        Object List71_tree=null;
        Object char_literal72_tree=null;
        Object char_literal74_tree=null;
        Object Set75_tree=null;
        Object char_literal76_tree=null;
        Object char_literal78_tree=null;
        RewriteRuleTokenStream stream_Period=new RewriteRuleTokenStream(adaptor,"token Period");
        RewriteRuleTokenStream stream_Println=new RewriteRuleTokenStream(adaptor,"token Println");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_Assert=new RewriteRuleTokenStream(adaptor,"token Assert");
        RewriteRuleTokenStream stream_Print=new RewriteRuleTokenStream(adaptor,"token Print");
        RewriteRuleTokenStream stream_Time=new RewriteRuleTokenStream(adaptor,"token Time");
        RewriteRuleTokenStream stream_Date=new RewriteRuleTokenStream(adaptor,"token Date");
        RewriteRuleTokenStream stream_DateTime=new RewriteRuleTokenStream(adaptor,"token DateTime");
        RewriteRuleTokenStream stream_Duration=new RewriteRuleTokenStream(adaptor,"token Duration");
        RewriteRuleTokenStream stream_Set=new RewriteRuleTokenStream(adaptor,"token Set");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
        RewriteRuleTokenStream stream_List=new RewriteRuleTokenStream(adaptor,"token List");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_exprList=new RewriteRuleSubtreeStream(adaptor,"rule exprList");
        try {
            // grammar/PlazmaScript.g:103:3: ( Identifier '(' ( exprList )? ')' -> ^( FUNC_CALL Identifier ( exprList )? ) | Println '(' ( expression )? ')' -> ^( FUNC_CALL Println ( expression )? ) | Print '(' expression ')' -> ^( FUNC_CALL Print expression ) | Assert '(' expression ')' -> ^( FUNC_CALL Assert expression ) | Date '(' ( exprList )? ')' -> ^( FUNC_CALL Date ( exprList )? ) | DateTime '(' ( exprList )? ')' -> ^( FUNC_CALL DateTime ( exprList )? ) | Time '(' ( exprList )? ')' -> ^( FUNC_CALL Time ( exprList )? ) | Duration '(' ( expression )? ')' -> ^( FUNC_CALL Duration ( expression )? ) | Period '(' ( exprList )? ')' -> ^( FUNC_CALL Period ( exprList )? ) | List '(' ( exprList )? ')' -> ^( FUNC_CALL List ( exprList )? ) | Set '(' ( exprList )? ')' -> ^( FUNC_CALL Set ( exprList )? ) )
            int alt18=11;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                alt18=1;
                }
                break;
            case Println:
                {
                alt18=2;
                }
                break;
            case Print:
                {
                alt18=3;
                }
                break;
            case Assert:
                {
                alt18=4;
                }
                break;
            case Date:
                {
                alt18=5;
                }
                break;
            case DateTime:
                {
                alt18=6;
                }
                break;
            case Time:
                {
                alt18=7;
                }
                break;
            case Duration:
                {
                alt18=8;
                }
                break;
            case Period:
                {
                alt18=9;
                }
                break;
            case List:
                {
                alt18=10;
                }
                break;
            case Set:
                {
                alt18=11;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // grammar/PlazmaScript.g:103:6: Identifier '(' ( exprList )? ')'
                    {
                    Identifier35=(Token)match(input,Identifier,FOLLOW_Identifier_in_functionCall546); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier35);

                    char_literal36=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall548); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal36);

                    // grammar/PlazmaScript.g:103:21: ( exprList )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( ((LA9_0>=Identifier && LA9_0<=Set)||(LA9_0>=Integer && LA9_0<=String)||(LA9_0>=Not && LA9_0<=NotWord)||(LA9_0>=Add && LA9_0<=Subtract)||LA9_0==OBrace||LA9_0==OBracket||LA9_0==OParen||LA9_0==ContextIdentifier) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall550);
                            exprList37=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList37.getTree());

                            }
                            break;

                    }

                    char_literal38=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall553); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal38);



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
                    // 103:35: -> ^( FUNC_CALL Identifier ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:103:38: ^( FUNC_CALL Identifier ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Identifier.nextNode());
                        // grammar/PlazmaScript.g:103:61: ( exprList )?
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
                    // grammar/PlazmaScript.g:104:6: Println '(' ( expression )? ')'
                    {
                    Println39=(Token)match(input,Println,FOLLOW_Println_in_functionCall571); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Println.add(Println39);

                    char_literal40=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall573); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal40);

                    // grammar/PlazmaScript.g:104:18: ( expression )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( ((LA10_0>=Identifier && LA10_0<=Set)||(LA10_0>=Integer && LA10_0<=String)||(LA10_0>=Not && LA10_0<=NotWord)||(LA10_0>=Add && LA10_0<=Subtract)||LA10_0==OBrace||LA10_0==OBracket||LA10_0==OParen||LA10_0==ContextIdentifier) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: expression
                            {
                            pushFollow(FOLLOW_expression_in_functionCall575);
                            expression41=expression();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_expression.add(expression41.getTree());

                            }
                            break;

                    }

                    char_literal42=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall578); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal42);



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
                    // 104:35: -> ^( FUNC_CALL Println ( expression )? )
                    {
                        // grammar/PlazmaScript.g:104:38: ^( FUNC_CALL Println ( expression )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Println.nextNode());
                        // grammar/PlazmaScript.g:104:58: ( expression )?
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
                    // grammar/PlazmaScript.g:105:6: Print '(' expression ')'
                    {
                    Print43=(Token)match(input,Print,FOLLOW_Print_in_functionCall597); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Print.add(Print43);

                    char_literal44=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall599); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal44);

                    pushFollow(FOLLOW_expression_in_functionCall601);
                    expression45=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression45.getTree());
                    char_literal46=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall603); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal46);



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
                    // 105:35: -> ^( FUNC_CALL Print expression )
                    {
                        // grammar/PlazmaScript.g:105:38: ^( FUNC_CALL Print expression )
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
                    // grammar/PlazmaScript.g:106:6: Assert '(' expression ')'
                    {
                    Assert47=(Token)match(input,Assert,FOLLOW_Assert_in_functionCall624); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Assert.add(Assert47);

                    char_literal48=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall626); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal48);

                    pushFollow(FOLLOW_expression_in_functionCall628);
                    expression49=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression49.getTree());
                    char_literal50=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall630); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal50);



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
                    // 106:35: -> ^( FUNC_CALL Assert expression )
                    {
                        // grammar/PlazmaScript.g:106:38: ^( FUNC_CALL Assert expression )
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
                    // grammar/PlazmaScript.g:107:6: Date '(' ( exprList )? ')'
                    {
                    Date51=(Token)match(input,Date,FOLLOW_Date_in_functionCall650); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Date.add(Date51);

                    char_literal52=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall652); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal52);

                    // grammar/PlazmaScript.g:107:15: ( exprList )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( ((LA11_0>=Identifier && LA11_0<=Set)||(LA11_0>=Integer && LA11_0<=String)||(LA11_0>=Not && LA11_0<=NotWord)||(LA11_0>=Add && LA11_0<=Subtract)||LA11_0==OBrace||LA11_0==OBracket||LA11_0==OParen||LA11_0==ContextIdentifier) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall654);
                            exprList53=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList53.getTree());

                            }
                            break;

                    }

                    char_literal54=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall657); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal54);



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
                    // 107:34: -> ^( FUNC_CALL Date ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:107:37: ^( FUNC_CALL Date ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Date.nextNode());
                        // grammar/PlazmaScript.g:107:54: ( exprList )?
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
                    // grammar/PlazmaScript.g:108:6: DateTime '(' ( exprList )? ')'
                    {
                    DateTime55=(Token)match(input,DateTime,FOLLOW_DateTime_in_functionCall680); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DateTime.add(DateTime55);

                    char_literal56=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall682); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal56);

                    // grammar/PlazmaScript.g:108:19: ( exprList )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( ((LA12_0>=Identifier && LA12_0<=Set)||(LA12_0>=Integer && LA12_0<=String)||(LA12_0>=Not && LA12_0<=NotWord)||(LA12_0>=Add && LA12_0<=Subtract)||LA12_0==OBrace||LA12_0==OBracket||LA12_0==OParen||LA12_0==ContextIdentifier) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall684);
                            exprList57=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList57.getTree());

                            }
                            break;

                    }

                    char_literal58=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall687); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal58);



                    // AST REWRITE
                    // elements: exprList, DateTime
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 108:38: -> ^( FUNC_CALL DateTime ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:108:41: ^( FUNC_CALL DateTime ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_DateTime.nextNode());
                        // grammar/PlazmaScript.g:108:62: ( exprList )?
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
                    // grammar/PlazmaScript.g:109:6: Time '(' ( exprList )? ')'
                    {
                    Time59=(Token)match(input,Time,FOLLOW_Time_in_functionCall710); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Time.add(Time59);

                    char_literal60=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall712); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal60);

                    // grammar/PlazmaScript.g:109:15: ( exprList )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( ((LA13_0>=Identifier && LA13_0<=Set)||(LA13_0>=Integer && LA13_0<=String)||(LA13_0>=Not && LA13_0<=NotWord)||(LA13_0>=Add && LA13_0<=Subtract)||LA13_0==OBrace||LA13_0==OBracket||LA13_0==OParen||LA13_0==ContextIdentifier) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall714);
                            exprList61=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList61.getTree());

                            }
                            break;

                    }

                    char_literal62=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall717); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal62);



                    // AST REWRITE
                    // elements: Time, exprList
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 109:34: -> ^( FUNC_CALL Time ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:109:37: ^( FUNC_CALL Time ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Time.nextNode());
                        // grammar/PlazmaScript.g:109:54: ( exprList )?
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
                case 8 :
                    // grammar/PlazmaScript.g:110:6: Duration '(' ( expression )? ')'
                    {
                    Duration63=(Token)match(input,Duration,FOLLOW_Duration_in_functionCall740); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Duration.add(Duration63);

                    char_literal64=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall742); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal64);

                    // grammar/PlazmaScript.g:110:19: ( expression )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( ((LA14_0>=Identifier && LA14_0<=Set)||(LA14_0>=Integer && LA14_0<=String)||(LA14_0>=Not && LA14_0<=NotWord)||(LA14_0>=Add && LA14_0<=Subtract)||LA14_0==OBrace||LA14_0==OBracket||LA14_0==OParen||LA14_0==ContextIdentifier) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: expression
                            {
                            pushFollow(FOLLOW_expression_in_functionCall744);
                            expression65=expression();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_expression.add(expression65.getTree());

                            }
                            break;

                    }

                    char_literal66=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall747); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal66);



                    // AST REWRITE
                    // elements: expression, Duration
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 110:36: -> ^( FUNC_CALL Duration ( expression )? )
                    {
                        // grammar/PlazmaScript.g:110:39: ^( FUNC_CALL Duration ( expression )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Duration.nextNode());
                        // grammar/PlazmaScript.g:110:60: ( expression )?
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
                case 9 :
                    // grammar/PlazmaScript.g:111:6: Period '(' ( exprList )? ')'
                    {
                    Period67=(Token)match(input,Period,FOLLOW_Period_in_functionCall766); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Period.add(Period67);

                    char_literal68=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall768); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal68);

                    // grammar/PlazmaScript.g:111:17: ( exprList )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( ((LA15_0>=Identifier && LA15_0<=Set)||(LA15_0>=Integer && LA15_0<=String)||(LA15_0>=Not && LA15_0<=NotWord)||(LA15_0>=Add && LA15_0<=Subtract)||LA15_0==OBrace||LA15_0==OBracket||LA15_0==OParen||LA15_0==ContextIdentifier) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall770);
                            exprList69=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList69.getTree());

                            }
                            break;

                    }

                    char_literal70=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall773); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal70);



                    // AST REWRITE
                    // elements: exprList, Period
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 111:32: -> ^( FUNC_CALL Period ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:111:35: ^( FUNC_CALL Period ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Period.nextNode());
                        // grammar/PlazmaScript.g:111:54: ( exprList )?
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
                case 10 :
                    // grammar/PlazmaScript.g:112:6: List '(' ( exprList )? ')'
                    {
                    List71=(Token)match(input,List,FOLLOW_List_in_functionCall804); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_List.add(List71);

                    char_literal72=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall806); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal72);

                    // grammar/PlazmaScript.g:112:15: ( exprList )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( ((LA16_0>=Identifier && LA16_0<=Set)||(LA16_0>=Integer && LA16_0<=String)||(LA16_0>=Not && LA16_0<=NotWord)||(LA16_0>=Add && LA16_0<=Subtract)||LA16_0==OBrace||LA16_0==OBracket||LA16_0==OParen||LA16_0==ContextIdentifier) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall808);
                            exprList73=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList73.getTree());

                            }
                            break;

                    }

                    char_literal74=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall811); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal74);



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
                    // 112:34: -> ^( FUNC_CALL List ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:112:37: ^( FUNC_CALL List ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_List.nextNode());
                        // grammar/PlazmaScript.g:112:54: ( exprList )?
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
                case 11 :
                    // grammar/PlazmaScript.g:113:6: Set '(' ( exprList )? ')'
                    {
                    Set75=(Token)match(input,Set,FOLLOW_Set_in_functionCall834); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Set.add(Set75);

                    char_literal76=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall836); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal76);

                    // grammar/PlazmaScript.g:113:14: ( exprList )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( ((LA17_0>=Identifier && LA17_0<=Set)||(LA17_0>=Integer && LA17_0<=String)||(LA17_0>=Not && LA17_0<=NotWord)||(LA17_0>=Add && LA17_0<=Subtract)||LA17_0==OBrace||LA17_0==OBracket||LA17_0==OParen||LA17_0==ContextIdentifier) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall838);
                            exprList77=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList77.getTree());

                            }
                            break;

                    }

                    char_literal78=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall841); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal78);



                    // AST REWRITE
                    // elements: exprList, Set
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 113:33: -> ^( FUNC_CALL Set ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:113:36: ^( FUNC_CALL Set ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Set.nextNode());
                        // grammar/PlazmaScript.g:113:52: ( exprList )?
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
    // grammar/PlazmaScript.g:120:1: ifStatement : ifStat ( elseIfStat )* ( elseStat )? -> ^( IF ifStat ( elseIfStat )* ( elseStat )? ) ;
    public final PlazmaScriptParser.ifStatement_return ifStatement() throws RecognitionException {
        PlazmaScriptParser.ifStatement_return retval = new PlazmaScriptParser.ifStatement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        PlazmaScriptParser.ifStat_return ifStat79 = null;

        PlazmaScriptParser.elseIfStat_return elseIfStat80 = null;

        PlazmaScriptParser.elseStat_return elseStat81 = null;


        RewriteRuleSubtreeStream stream_elseIfStat=new RewriteRuleSubtreeStream(adaptor,"rule elseIfStat");
        RewriteRuleSubtreeStream stream_ifStat=new RewriteRuleSubtreeStream(adaptor,"rule ifStat");
        RewriteRuleSubtreeStream stream_elseStat=new RewriteRuleSubtreeStream(adaptor,"rule elseStat");
        try {
            // grammar/PlazmaScript.g:121:3: ( ifStat ( elseIfStat )* ( elseStat )? -> ^( IF ifStat ( elseIfStat )* ( elseStat )? ) )
            // grammar/PlazmaScript.g:121:6: ifStat ( elseIfStat )* ( elseStat )?
            {
            pushFollow(FOLLOW_ifStat_in_ifStatement879);
            ifStat79=ifStat();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_ifStat.add(ifStat79.getTree());
            // grammar/PlazmaScript.g:121:13: ( elseIfStat )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==Else) ) {
                    int LA19_1 = input.LA(2);

                    if ( (LA19_1==If) ) {
                        alt19=1;
                    }


                }


                switch (alt19) {
            	case 1 :
            	    // grammar/PlazmaScript.g:0:0: elseIfStat
            	    {
            	    pushFollow(FOLLOW_elseIfStat_in_ifStatement881);
            	    elseIfStat80=elseIfStat();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_elseIfStat.add(elseIfStat80.getTree());

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            // grammar/PlazmaScript.g:121:25: ( elseStat )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==Else) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // grammar/PlazmaScript.g:0:0: elseStat
                    {
                    pushFollow(FOLLOW_elseStat_in_ifStatement884);
                    elseStat81=elseStat();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_elseStat.add(elseStat81.getTree());

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
            // 121:35: -> ^( IF ifStat ( elseIfStat )* ( elseStat )? )
            {
                // grammar/PlazmaScript.g:121:38: ^( IF ifStat ( elseIfStat )* ( elseStat )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(IF, "IF"), root_1);

                adaptor.addChild(root_1, stream_ifStat.nextTree());
                // grammar/PlazmaScript.g:121:50: ( elseIfStat )*
                while ( stream_elseIfStat.hasNext() ) {
                    adaptor.addChild(root_1, stream_elseIfStat.nextTree());

                }
                stream_elseIfStat.reset();
                // grammar/PlazmaScript.g:121:62: ( elseStat )?
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
    // grammar/PlazmaScript.g:124:1: ifStat : If '(' expression ')' '{' block '}' -> ^( EXP expression block ) ;
    public final PlazmaScriptParser.ifStat_return ifStat() throws RecognitionException {
        PlazmaScriptParser.ifStat_return retval = new PlazmaScriptParser.ifStat_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token If82=null;
        Token char_literal83=null;
        Token char_literal85=null;
        Token char_literal86=null;
        Token char_literal88=null;
        PlazmaScriptParser.expression_return expression84 = null;

        PlazmaScriptParser.block_return block87 = null;


        Object If82_tree=null;
        Object char_literal83_tree=null;
        Object char_literal85_tree=null;
        Object char_literal86_tree=null;
        Object char_literal88_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_If=new RewriteRuleTokenStream(adaptor,"token If");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:125:3: ( If '(' expression ')' '{' block '}' -> ^( EXP expression block ) )
            // grammar/PlazmaScript.g:125:6: If '(' expression ')' '{' block '}'
            {
            If82=(Token)match(input,If,FOLLOW_If_in_ifStat913); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_If.add(If82);

            char_literal83=(Token)match(input,OParen,FOLLOW_OParen_in_ifStat915); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal83);

            pushFollow(FOLLOW_expression_in_ifStat917);
            expression84=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression84.getTree());
            char_literal85=(Token)match(input,CParen,FOLLOW_CParen_in_ifStat919); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal85);

            char_literal86=(Token)match(input,OBrace,FOLLOW_OBrace_in_ifStat921); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal86);

            pushFollow(FOLLOW_block_in_ifStat923);
            block87=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block87.getTree());
            char_literal88=(Token)match(input,CBrace,FOLLOW_CBrace_in_ifStat925); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal88);



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
            // 125:42: -> ^( EXP expression block )
            {
                // grammar/PlazmaScript.g:125:45: ^( EXP expression block )
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
    // grammar/PlazmaScript.g:128:1: elseIfStat : Else If '(' expression ')' '{' block '}' -> ^( EXP expression block ) ;
    public final PlazmaScriptParser.elseIfStat_return elseIfStat() throws RecognitionException {
        PlazmaScriptParser.elseIfStat_return retval = new PlazmaScriptParser.elseIfStat_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Else89=null;
        Token If90=null;
        Token char_literal91=null;
        Token char_literal93=null;
        Token char_literal94=null;
        Token char_literal96=null;
        PlazmaScriptParser.expression_return expression92 = null;

        PlazmaScriptParser.block_return block95 = null;


        Object Else89_tree=null;
        Object If90_tree=null;
        Object char_literal91_tree=null;
        Object char_literal93_tree=null;
        Object char_literal94_tree=null;
        Object char_literal96_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_Else=new RewriteRuleTokenStream(adaptor,"token Else");
        RewriteRuleTokenStream stream_If=new RewriteRuleTokenStream(adaptor,"token If");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:129:3: ( Else If '(' expression ')' '{' block '}' -> ^( EXP expression block ) )
            // grammar/PlazmaScript.g:129:6: Else If '(' expression ')' '{' block '}'
            {
            Else89=(Token)match(input,Else,FOLLOW_Else_in_elseIfStat949); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Else.add(Else89);

            If90=(Token)match(input,If,FOLLOW_If_in_elseIfStat951); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_If.add(If90);

            char_literal91=(Token)match(input,OParen,FOLLOW_OParen_in_elseIfStat953); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal91);

            pushFollow(FOLLOW_expression_in_elseIfStat955);
            expression92=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression92.getTree());
            char_literal93=(Token)match(input,CParen,FOLLOW_CParen_in_elseIfStat957); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal93);

            char_literal94=(Token)match(input,OBrace,FOLLOW_OBrace_in_elseIfStat959); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal94);

            pushFollow(FOLLOW_block_in_elseIfStat961);
            block95=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block95.getTree());
            char_literal96=(Token)match(input,CBrace,FOLLOW_CBrace_in_elseIfStat963); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal96);



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
            // 129:47: -> ^( EXP expression block )
            {
                // grammar/PlazmaScript.g:129:50: ^( EXP expression block )
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
    // grammar/PlazmaScript.g:132:1: elseStat : Else '{' block '}' -> ^( EXP block ) ;
    public final PlazmaScriptParser.elseStat_return elseStat() throws RecognitionException {
        PlazmaScriptParser.elseStat_return retval = new PlazmaScriptParser.elseStat_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Else97=null;
        Token char_literal98=null;
        Token char_literal100=null;
        PlazmaScriptParser.block_return block99 = null;


        Object Else97_tree=null;
        Object char_literal98_tree=null;
        Object char_literal100_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_Else=new RewriteRuleTokenStream(adaptor,"token Else");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:133:3: ( Else '{' block '}' -> ^( EXP block ) )
            // grammar/PlazmaScript.g:133:6: Else '{' block '}'
            {
            Else97=(Token)match(input,Else,FOLLOW_Else_in_elseStat987); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Else.add(Else97);

            char_literal98=(Token)match(input,OBrace,FOLLOW_OBrace_in_elseStat989); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal98);

            pushFollow(FOLLOW_block_in_elseStat991);
            block99=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block99.getTree());
            char_literal100=(Token)match(input,CBrace,FOLLOW_CBrace_in_elseStat993); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal100);



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
            // 133:25: -> ^( EXP block )
            {
                // grammar/PlazmaScript.g:133:28: ^( EXP block )
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
    // grammar/PlazmaScript.g:136:1: variableDef : Var ;
    public final PlazmaScriptParser.variableDef_return variableDef() throws RecognitionException {
        PlazmaScriptParser.variableDef_return retval = new PlazmaScriptParser.variableDef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Var101=null;

        Object Var101_tree=null;

        try {
            // grammar/PlazmaScript.g:137:2: ( Var )
            // grammar/PlazmaScript.g:137:4: Var
            {
            root_0 = (Object)adaptor.nil();

            Var101=(Token)match(input,Var,FOLLOW_Var_in_variableDef1013); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Var101_tree = (Object)adaptor.create(Var101);
            adaptor.addChild(root_0, Var101_tree);
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
    // grammar/PlazmaScript.g:154:1: functionDecl : Def Identifier '(' ( idList )? ')' '{' block '}' ;
    public final PlazmaScriptParser.functionDecl_return functionDecl() throws RecognitionException {
        PlazmaScriptParser.functionDecl_return retval = new PlazmaScriptParser.functionDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Def102=null;
        Token Identifier103=null;
        Token char_literal104=null;
        Token char_literal106=null;
        Token char_literal107=null;
        Token char_literal109=null;
        PlazmaScriptParser.idList_return idList105 = null;

        PlazmaScriptParser.block_return block108 = null;


        Object Def102_tree=null;
        Object Identifier103_tree=null;
        Object char_literal104_tree=null;
        Object char_literal106_tree=null;
        Object char_literal107_tree=null;
        Object char_literal109_tree=null;

        try {
            // grammar/PlazmaScript.g:155:3: ( Def Identifier '(' ( idList )? ')' '{' block '}' )
            // grammar/PlazmaScript.g:155:6: Def Identifier '(' ( idList )? ')' '{' block '}'
            {
            root_0 = (Object)adaptor.nil();

            Def102=(Token)match(input,Def,FOLLOW_Def_in_functionDecl1045); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Def102_tree = (Object)adaptor.create(Def102);
            adaptor.addChild(root_0, Def102_tree);
            }
            Identifier103=(Token)match(input,Identifier,FOLLOW_Identifier_in_functionDecl1047); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier103_tree = (Object)adaptor.create(Identifier103);
            adaptor.addChild(root_0, Identifier103_tree);
            }
            char_literal104=(Token)match(input,OParen,FOLLOW_OParen_in_functionDecl1049); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal104_tree = (Object)adaptor.create(char_literal104);
            adaptor.addChild(root_0, char_literal104_tree);
            }
            // grammar/PlazmaScript.g:155:25: ( idList )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==Identifier) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // grammar/PlazmaScript.g:0:0: idList
                    {
                    pushFollow(FOLLOW_idList_in_functionDecl1051);
                    idList105=idList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, idList105.getTree());

                    }
                    break;

            }

            char_literal106=(Token)match(input,CParen,FOLLOW_CParen_in_functionDecl1054); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal106_tree = (Object)adaptor.create(char_literal106);
            adaptor.addChild(root_0, char_literal106_tree);
            }
            char_literal107=(Token)match(input,OBrace,FOLLOW_OBrace_in_functionDecl1056); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal107_tree = (Object)adaptor.create(char_literal107);
            adaptor.addChild(root_0, char_literal107_tree);
            }
            pushFollow(FOLLOW_block_in_functionDecl1058);
            block108=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, block108.getTree());
            char_literal109=(Token)match(input,CBrace,FOLLOW_CBrace_in_functionDecl1060); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal109_tree = (Object)adaptor.create(char_literal109);
            adaptor.addChild(root_0, char_literal109_tree);
            }
            if ( state.backtracking==0 ) {
              defineFunction((Identifier103!=null?Identifier103.getText():null), (idList105!=null?((Object)idList105.tree):null), (block108!=null?((Object)block108.tree):null));
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
    // grammar/PlazmaScript.g:164:1: forStatement : For '(' Identifier 'in' expression ')' '{' block '}' -> ^( For Identifier expression block ) ;
    public final PlazmaScriptParser.forStatement_return forStatement() throws RecognitionException {
        PlazmaScriptParser.forStatement_return retval = new PlazmaScriptParser.forStatement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token For110=null;
        Token char_literal111=null;
        Token Identifier112=null;
        Token string_literal113=null;
        Token char_literal115=null;
        Token char_literal116=null;
        Token char_literal118=null;
        PlazmaScriptParser.expression_return expression114 = null;

        PlazmaScriptParser.block_return block117 = null;


        Object For110_tree=null;
        Object char_literal111_tree=null;
        Object Identifier112_tree=null;
        Object string_literal113_tree=null;
        Object char_literal115_tree=null;
        Object char_literal116_tree=null;
        Object char_literal118_tree=null;
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
            // grammar/PlazmaScript.g:165:3: ( For '(' Identifier 'in' expression ')' '{' block '}' -> ^( For Identifier expression block ) )
            // grammar/PlazmaScript.g:165:6: For '(' Identifier 'in' expression ')' '{' block '}'
            {
            For110=(Token)match(input,For,FOLLOW_For_in_forStatement1089); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_For.add(For110);

            char_literal111=(Token)match(input,OParen,FOLLOW_OParen_in_forStatement1091); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal111);

            Identifier112=(Token)match(input,Identifier,FOLLOW_Identifier_in_forStatement1093); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Identifier.add(Identifier112);

            string_literal113=(Token)match(input,In,FOLLOW_In_in_forStatement1095); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_In.add(string_literal113);

            pushFollow(FOLLOW_expression_in_forStatement1097);
            expression114=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression114.getTree());
            char_literal115=(Token)match(input,CParen,FOLLOW_CParen_in_forStatement1099); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal115);

            char_literal116=(Token)match(input,OBrace,FOLLOW_OBrace_in_forStatement1101); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal116);

            pushFollow(FOLLOW_block_in_forStatement1103);
            block117=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block117.getTree());
            char_literal118=(Token)match(input,CBrace,FOLLOW_CBrace_in_forStatement1105); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal118);



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
            // 166:6: -> ^( For Identifier expression block )
            {
                // grammar/PlazmaScript.g:166:9: ^( For Identifier expression block )
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
    // grammar/PlazmaScript.g:170:1: whileStatement : While '(' expression ')' '{' block '}' -> ^( While expression block ) ;
    public final PlazmaScriptParser.whileStatement_return whileStatement() throws RecognitionException {
        PlazmaScriptParser.whileStatement_return retval = new PlazmaScriptParser.whileStatement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token While119=null;
        Token char_literal120=null;
        Token char_literal122=null;
        Token char_literal123=null;
        Token char_literal125=null;
        PlazmaScriptParser.expression_return expression121 = null;

        PlazmaScriptParser.block_return block124 = null;


        Object While119_tree=null;
        Object char_literal120_tree=null;
        Object char_literal122_tree=null;
        Object char_literal123_tree=null;
        Object char_literal125_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_While=new RewriteRuleTokenStream(adaptor,"token While");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:171:3: ( While '(' expression ')' '{' block '}' -> ^( While expression block ) )
            // grammar/PlazmaScript.g:171:6: While '(' expression ')' '{' block '}'
            {
            While119=(Token)match(input,While,FOLLOW_While_in_whileStatement1140); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_While.add(While119);

            char_literal120=(Token)match(input,OParen,FOLLOW_OParen_in_whileStatement1142); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal120);

            pushFollow(FOLLOW_expression_in_whileStatement1144);
            expression121=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression121.getTree());
            char_literal122=(Token)match(input,CParen,FOLLOW_CParen_in_whileStatement1146); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal122);

            char_literal123=(Token)match(input,OBrace,FOLLOW_OBrace_in_whileStatement1148); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal123);

            pushFollow(FOLLOW_block_in_whileStatement1150);
            block124=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block124.getTree());
            char_literal125=(Token)match(input,CBrace,FOLLOW_CBrace_in_whileStatement1152); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal125);



            // AST REWRITE
            // elements: expression, While, block
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 171:45: -> ^( While expression block )
            {
                // grammar/PlazmaScript.g:171:48: ^( While expression block )
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
    // grammar/PlazmaScript.g:174:1: idList : Identifier ( ',' Identifier )* -> ^( ID_LIST ( Identifier )+ ) ;
    public final PlazmaScriptParser.idList_return idList() throws RecognitionException {
        PlazmaScriptParser.idList_return retval = new PlazmaScriptParser.idList_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Identifier126=null;
        Token char_literal127=null;
        Token Identifier128=null;

        Object Identifier126_tree=null;
        Object char_literal127_tree=null;
        Object Identifier128_tree=null;
        RewriteRuleTokenStream stream_Comma=new RewriteRuleTokenStream(adaptor,"token Comma");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");

        try {
            // grammar/PlazmaScript.g:175:3: ( Identifier ( ',' Identifier )* -> ^( ID_LIST ( Identifier )+ ) )
            // grammar/PlazmaScript.g:175:6: Identifier ( ',' Identifier )*
            {
            Identifier126=(Token)match(input,Identifier,FOLLOW_Identifier_in_idList1176); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Identifier.add(Identifier126);

            // grammar/PlazmaScript.g:175:17: ( ',' Identifier )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==Comma) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // grammar/PlazmaScript.g:175:18: ',' Identifier
            	    {
            	    char_literal127=(Token)match(input,Comma,FOLLOW_Comma_in_idList1179); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Comma.add(char_literal127);

            	    Identifier128=(Token)match(input,Identifier,FOLLOW_Identifier_in_idList1181); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Identifier.add(Identifier128);


            	    }
            	    break;

            	default :
            	    break loop22;
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
            // 175:35: -> ^( ID_LIST ( Identifier )+ )
            {
                // grammar/PlazmaScript.g:175:38: ^( ID_LIST ( Identifier )+ )
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
    // grammar/PlazmaScript.g:178:1: exprList : expression ( ',' expression )* -> ^( EXP_LIST ( expression )+ ) ;
    public final PlazmaScriptParser.exprList_return exprList() throws RecognitionException {
        PlazmaScriptParser.exprList_return retval = new PlazmaScriptParser.exprList_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal130=null;
        PlazmaScriptParser.expression_return expression129 = null;

        PlazmaScriptParser.expression_return expression131 = null;


        Object char_literal130_tree=null;
        RewriteRuleTokenStream stream_Comma=new RewriteRuleTokenStream(adaptor,"token Comma");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // grammar/PlazmaScript.g:179:3: ( expression ( ',' expression )* -> ^( EXP_LIST ( expression )+ ) )
            // grammar/PlazmaScript.g:179:6: expression ( ',' expression )*
            {
            pushFollow(FOLLOW_expression_in_exprList1206);
            expression129=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression129.getTree());
            // grammar/PlazmaScript.g:179:17: ( ',' expression )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==Comma) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // grammar/PlazmaScript.g:179:18: ',' expression
            	    {
            	    char_literal130=(Token)match(input,Comma,FOLLOW_Comma_in_exprList1209); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Comma.add(char_literal130);

            	    pushFollow(FOLLOW_expression_in_exprList1211);
            	    expression131=expression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_expression.add(expression131.getTree());

            	    }
            	    break;

            	default :
            	    break loop23;
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
            // 179:35: -> ^( EXP_LIST ( expression )+ )
            {
                // grammar/PlazmaScript.g:179:38: ^( EXP_LIST ( expression )+ )
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
    // grammar/PlazmaScript.g:182:1: exprPair : ( expression ':' expression ) -> ^( EXP_PAIR expression expression ) ;
    public final PlazmaScriptParser.exprPair_return exprPair() throws RecognitionException {
        PlazmaScriptParser.exprPair_return retval = new PlazmaScriptParser.exprPair_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal133=null;
        PlazmaScriptParser.expression_return expression132 = null;

        PlazmaScriptParser.expression_return expression134 = null;


        Object char_literal133_tree=null;
        RewriteRuleTokenStream stream_Colon=new RewriteRuleTokenStream(adaptor,"token Colon");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // grammar/PlazmaScript.g:183:3: ( ( expression ':' expression ) -> ^( EXP_PAIR expression expression ) )
            // grammar/PlazmaScript.g:183:6: ( expression ':' expression )
            {
            // grammar/PlazmaScript.g:183:6: ( expression ':' expression )
            // grammar/PlazmaScript.g:183:7: expression ':' expression
            {
            pushFollow(FOLLOW_expression_in_exprPair1237);
            expression132=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression132.getTree());
            char_literal133=(Token)match(input,Colon,FOLLOW_Colon_in_exprPair1239); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Colon.add(char_literal133);

            pushFollow(FOLLOW_expression_in_exprPair1241);
            expression134=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression134.getTree());

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
            // 183:34: -> ^( EXP_PAIR expression expression )
            {
                // grammar/PlazmaScript.g:183:37: ^( EXP_PAIR expression expression )
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
    // grammar/PlazmaScript.g:186:1: exprMap : exprPair ( ',' exprPair )* -> ^( EXP_MAP ( exprPair )+ ) ;
    public final PlazmaScriptParser.exprMap_return exprMap() throws RecognitionException {
        PlazmaScriptParser.exprMap_return retval = new PlazmaScriptParser.exprMap_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal136=null;
        PlazmaScriptParser.exprPair_return exprPair135 = null;

        PlazmaScriptParser.exprPair_return exprPair137 = null;


        Object char_literal136_tree=null;
        RewriteRuleTokenStream stream_Comma=new RewriteRuleTokenStream(adaptor,"token Comma");
        RewriteRuleSubtreeStream stream_exprPair=new RewriteRuleSubtreeStream(adaptor,"rule exprPair");
        try {
            // grammar/PlazmaScript.g:187:3: ( exprPair ( ',' exprPair )* -> ^( EXP_MAP ( exprPair )+ ) )
            // grammar/PlazmaScript.g:187:6: exprPair ( ',' exprPair )*
            {
            pushFollow(FOLLOW_exprPair_in_exprMap1266);
            exprPair135=exprPair();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_exprPair.add(exprPair135.getTree());
            // grammar/PlazmaScript.g:187:15: ( ',' exprPair )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==Comma) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // grammar/PlazmaScript.g:187:16: ',' exprPair
            	    {
            	    char_literal136=(Token)match(input,Comma,FOLLOW_Comma_in_exprMap1269); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Comma.add(char_literal136);

            	    pushFollow(FOLLOW_exprPair_in_exprMap1271);
            	    exprPair137=exprPair();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_exprPair.add(exprPair137.getTree());

            	    }
            	    break;

            	default :
            	    break loop24;
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
            // 187:31: -> ^( EXP_MAP ( exprPair )+ )
            {
                // grammar/PlazmaScript.g:187:34: ^( EXP_MAP ( exprPair )+ )
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
    // grammar/PlazmaScript.g:191:1: expression : condExpr ;
    public final PlazmaScriptParser.expression_return expression() throws RecognitionException {
        PlazmaScriptParser.expression_return retval = new PlazmaScriptParser.expression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        PlazmaScriptParser.condExpr_return condExpr138 = null;



        try {
            // grammar/PlazmaScript.g:192:3: ( condExpr )
            // grammar/PlazmaScript.g:192:6: condExpr
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_condExpr_in_expression1297);
            condExpr138=condExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, condExpr138.getTree());

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
    // grammar/PlazmaScript.g:195:1: condExpr : ( startExpr -> startExpr ) ( '?' a= expression ':' b= expression -> ^( TERNARY startExpr $a $b) | In expression -> ^( In startExpr expression ) | RangeE expression -> ^( RangeE startExpr expression ) | Range expression -> ^( Range startExpr expression ) )? ;
    public final PlazmaScriptParser.condExpr_return condExpr() throws RecognitionException {
        PlazmaScriptParser.condExpr_return retval = new PlazmaScriptParser.condExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal140=null;
        Token char_literal141=null;
        Token In142=null;
        Token RangeE144=null;
        Token Range146=null;
        PlazmaScriptParser.expression_return a = null;

        PlazmaScriptParser.expression_return b = null;

        PlazmaScriptParser.startExpr_return startExpr139 = null;

        PlazmaScriptParser.expression_return expression143 = null;

        PlazmaScriptParser.expression_return expression145 = null;

        PlazmaScriptParser.expression_return expression147 = null;


        Object char_literal140_tree=null;
        Object char_literal141_tree=null;
        Object In142_tree=null;
        Object RangeE144_tree=null;
        Object Range146_tree=null;
        RewriteRuleTokenStream stream_RangeE=new RewriteRuleTokenStream(adaptor,"token RangeE");
        RewriteRuleTokenStream stream_Range=new RewriteRuleTokenStream(adaptor,"token Range");
        RewriteRuleTokenStream stream_In=new RewriteRuleTokenStream(adaptor,"token In");
        RewriteRuleTokenStream stream_Colon=new RewriteRuleTokenStream(adaptor,"token Colon");
        RewriteRuleTokenStream stream_QMark=new RewriteRuleTokenStream(adaptor,"token QMark");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_startExpr=new RewriteRuleSubtreeStream(adaptor,"rule startExpr");
        try {
            // grammar/PlazmaScript.g:196:3: ( ( startExpr -> startExpr ) ( '?' a= expression ':' b= expression -> ^( TERNARY startExpr $a $b) | In expression -> ^( In startExpr expression ) | RangeE expression -> ^( RangeE startExpr expression ) | Range expression -> ^( Range startExpr expression ) )? )
            // grammar/PlazmaScript.g:196:6: ( startExpr -> startExpr ) ( '?' a= expression ':' b= expression -> ^( TERNARY startExpr $a $b) | In expression -> ^( In startExpr expression ) | RangeE expression -> ^( RangeE startExpr expression ) | Range expression -> ^( Range startExpr expression ) )?
            {
            // grammar/PlazmaScript.g:196:6: ( startExpr -> startExpr )
            // grammar/PlazmaScript.g:196:7: startExpr
            {
            pushFollow(FOLLOW_startExpr_in_condExpr1312);
            startExpr139=startExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_startExpr.add(startExpr139.getTree());


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
            // 196:17: -> startExpr
            {
                adaptor.addChild(root_0, stream_startExpr.nextTree());

            }

            retval.tree = root_0;}
            }

            // grammar/PlazmaScript.g:197:6: ( '?' a= expression ':' b= expression -> ^( TERNARY startExpr $a $b) | In expression -> ^( In startExpr expression ) | RangeE expression -> ^( RangeE startExpr expression ) | Range expression -> ^( Range startExpr expression ) )?
            int alt25=5;
            switch ( input.LA(1) ) {
                case QMark:
                    {
                    alt25=1;
                    }
                    break;
                case In:
                    {
                    alt25=2;
                    }
                    break;
                case RangeE:
                    {
                    alt25=3;
                    }
                    break;
                case Range:
                    {
                    alt25=4;
                    }
                    break;
            }

            switch (alt25) {
                case 1 :
                    // grammar/PlazmaScript.g:197:8: '?' a= expression ':' b= expression
                    {
                    char_literal140=(Token)match(input,QMark,FOLLOW_QMark_in_condExpr1327); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_QMark.add(char_literal140);

                    pushFollow(FOLLOW_expression_in_condExpr1331);
                    a=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(a.getTree());
                    char_literal141=(Token)match(input,Colon,FOLLOW_Colon_in_condExpr1333); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Colon.add(char_literal141);

                    pushFollow(FOLLOW_expression_in_condExpr1337);
                    b=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(b.getTree());


                    // AST REWRITE
                    // elements: startExpr, b, a
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
                    // 197:42: -> ^( TERNARY startExpr $a $b)
                    {
                        // grammar/PlazmaScript.g:197:45: ^( TERNARY startExpr $a $b)
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
                    // grammar/PlazmaScript.g:198:8: In expression
                    {
                    In142=(Token)match(input,In,FOLLOW_In_in_condExpr1360); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_In.add(In142);

                    pushFollow(FOLLOW_expression_in_condExpr1362);
                    expression143=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression143.getTree());


                    // AST REWRITE
                    // elements: In, startExpr, expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 198:42: -> ^( In startExpr expression )
                    {
                        // grammar/PlazmaScript.g:198:45: ^( In startExpr expression )
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
                    // grammar/PlazmaScript.g:200:8: RangeE expression
                    {
                    RangeE144=(Token)match(input,RangeE,FOLLOW_RangeE_in_condExpr1407); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RangeE.add(RangeE144);

                    pushFollow(FOLLOW_expression_in_condExpr1409);
                    expression145=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression145.getTree());


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
                    // 200:42: -> ^( RangeE startExpr expression )
                    {
                        // grammar/PlazmaScript.g:200:45: ^( RangeE startExpr expression )
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
                    // grammar/PlazmaScript.g:201:8: Range expression
                    {
                    Range146=(Token)match(input,Range,FOLLOW_Range_in_condExpr1444); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Range.add(Range146);

                    pushFollow(FOLLOW_expression_in_condExpr1446);
                    expression147=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression147.getTree());


                    // AST REWRITE
                    // elements: Range, startExpr, expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 201:42: -> ^( Range startExpr expression )
                    {
                        // grammar/PlazmaScript.g:201:45: ^( Range startExpr expression )
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
    // grammar/PlazmaScript.g:206:1: startExpr : orExpr ;
    public final PlazmaScriptParser.startExpr_return startExpr() throws RecognitionException {
        PlazmaScriptParser.startExpr_return retval = new PlazmaScriptParser.startExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        PlazmaScriptParser.orExpr_return orExpr148 = null;



        try {
            // grammar/PlazmaScript.g:207:2: ( orExpr )
            // grammar/PlazmaScript.g:207:4: orExpr
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_orExpr_in_startExpr1509);
            orExpr148=orExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, orExpr148.getTree());

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
    // grammar/PlazmaScript.g:210:1: orExpr : andExpr ( ( 'xor' | '||' | '|' | 'or' ) andExpr )* ;
    public final PlazmaScriptParser.orExpr_return orExpr() throws RecognitionException {
        PlazmaScriptParser.orExpr_return retval = new PlazmaScriptParser.orExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set150=null;
        PlazmaScriptParser.andExpr_return andExpr149 = null;

        PlazmaScriptParser.andExpr_return andExpr151 = null;


        Object set150_tree=null;

        try {
            // grammar/PlazmaScript.g:211:3: ( andExpr ( ( 'xor' | '||' | '|' | 'or' ) andExpr )* )
            // grammar/PlazmaScript.g:211:6: andExpr ( ( 'xor' | '||' | '|' | 'or' ) andExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_andExpr_in_orExpr1522);
            andExpr149=andExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, andExpr149.getTree());
            // grammar/PlazmaScript.g:211:14: ( ( 'xor' | '||' | '|' | 'or' ) andExpr )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( ((LA26_0>=XorWord && LA26_0<=OrWord)) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // grammar/PlazmaScript.g:211:15: ( 'xor' | '||' | '|' | 'or' ) andExpr
            	    {
            	    set150=(Token)input.LT(1);
            	    set150=(Token)input.LT(1);
            	    if ( (input.LA(1)>=XorWord && input.LA(1)<=OrWord) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set150), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_andExpr_in_orExpr1542);
            	    andExpr151=andExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, andExpr151.getTree());

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
    // $ANTLR end "orExpr"

    public static class andExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "andExpr"
    // grammar/PlazmaScript.g:215:1: andExpr : equExpr ( ( '&&' | '&' | 'and' ) equExpr )* ;
    public final PlazmaScriptParser.andExpr_return andExpr() throws RecognitionException {
        PlazmaScriptParser.andExpr_return retval = new PlazmaScriptParser.andExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set153=null;
        PlazmaScriptParser.equExpr_return equExpr152 = null;

        PlazmaScriptParser.equExpr_return equExpr154 = null;


        Object set153_tree=null;

        try {
            // grammar/PlazmaScript.g:216:3: ( equExpr ( ( '&&' | '&' | 'and' ) equExpr )* )
            // grammar/PlazmaScript.g:216:6: equExpr ( ( '&&' | '&' | 'and' ) equExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_equExpr_in_andExpr1559);
            equExpr152=equExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, equExpr152.getTree());
            // grammar/PlazmaScript.g:216:14: ( ( '&&' | '&' | 'and' ) equExpr )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( ((LA27_0>=And && LA27_0<=AndWord)) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // grammar/PlazmaScript.g:216:15: ( '&&' | '&' | 'and' ) equExpr
            	    {
            	    set153=(Token)input.LT(1);
            	    set153=(Token)input.LT(1);
            	    if ( (input.LA(1)>=And && input.LA(1)<=AndWord) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set153), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_equExpr_in_andExpr1575);
            	    equExpr154=equExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, equExpr154.getTree());

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
    // $ANTLR end "andExpr"

    public static class equExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "equExpr"
    // grammar/PlazmaScript.g:219:1: equExpr : relExpr ( ( '==' | '!=' ) relExpr )* ;
    public final PlazmaScriptParser.equExpr_return equExpr() throws RecognitionException {
        PlazmaScriptParser.equExpr_return retval = new PlazmaScriptParser.equExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set156=null;
        PlazmaScriptParser.relExpr_return relExpr155 = null;

        PlazmaScriptParser.relExpr_return relExpr157 = null;


        Object set156_tree=null;

        try {
            // grammar/PlazmaScript.g:220:3: ( relExpr ( ( '==' | '!=' ) relExpr )* )
            // grammar/PlazmaScript.g:220:6: relExpr ( ( '==' | '!=' ) relExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_relExpr_in_equExpr1591);
            relExpr155=relExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, relExpr155.getTree());
            // grammar/PlazmaScript.g:220:14: ( ( '==' | '!=' ) relExpr )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( ((LA28_0>=Equals && LA28_0<=NEquals)) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // grammar/PlazmaScript.g:220:15: ( '==' | '!=' ) relExpr
            	    {
            	    set156=(Token)input.LT(1);
            	    set156=(Token)input.LT(1);
            	    if ( (input.LA(1)>=Equals && input.LA(1)<=NEquals) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set156), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_relExpr_in_equExpr1603);
            	    relExpr157=relExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, relExpr157.getTree());

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
    // $ANTLR end "equExpr"

    public static class relExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "relExpr"
    // grammar/PlazmaScript.g:223:1: relExpr : addExpr ( ( '>=' | '<=' | '>' | '<' ) addExpr )* ;
    public final PlazmaScriptParser.relExpr_return relExpr() throws RecognitionException {
        PlazmaScriptParser.relExpr_return retval = new PlazmaScriptParser.relExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set159=null;
        PlazmaScriptParser.addExpr_return addExpr158 = null;

        PlazmaScriptParser.addExpr_return addExpr160 = null;


        Object set159_tree=null;

        try {
            // grammar/PlazmaScript.g:224:3: ( addExpr ( ( '>=' | '<=' | '>' | '<' ) addExpr )* )
            // grammar/PlazmaScript.g:224:6: addExpr ( ( '>=' | '<=' | '>' | '<' ) addExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_addExpr_in_relExpr1619);
            addExpr158=addExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, addExpr158.getTree());
            // grammar/PlazmaScript.g:224:14: ( ( '>=' | '<=' | '>' | '<' ) addExpr )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( ((LA29_0>=GTEquals && LA29_0<=LTEquals)||(LA29_0>=GT && LA29_0<=LT)) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // grammar/PlazmaScript.g:224:15: ( '>=' | '<=' | '>' | '<' ) addExpr
            	    {
            	    set159=(Token)input.LT(1);
            	    set159=(Token)input.LT(1);
            	    if ( (input.LA(1)>=GTEquals && input.LA(1)<=LTEquals)||(input.LA(1)>=GT && input.LA(1)<=LT) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set159), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_addExpr_in_relExpr1639);
            	    addExpr160=addExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, addExpr160.getTree());

            	    }
            	    break;

            	default :
            	    break loop29;
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
    // grammar/PlazmaScript.g:227:1: addExpr : mulExpr ( ( '+' | '-' ) mulExpr )* ;
    public final PlazmaScriptParser.addExpr_return addExpr() throws RecognitionException {
        PlazmaScriptParser.addExpr_return retval = new PlazmaScriptParser.addExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set162=null;
        PlazmaScriptParser.mulExpr_return mulExpr161 = null;

        PlazmaScriptParser.mulExpr_return mulExpr163 = null;


        Object set162_tree=null;

        try {
            // grammar/PlazmaScript.g:228:3: ( mulExpr ( ( '+' | '-' ) mulExpr )* )
            // grammar/PlazmaScript.g:228:6: mulExpr ( ( '+' | '-' ) mulExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_mulExpr_in_addExpr1655);
            mulExpr161=mulExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, mulExpr161.getTree());
            // grammar/PlazmaScript.g:228:14: ( ( '+' | '-' ) mulExpr )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( ((LA30_0>=Add && LA30_0<=Subtract)) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // grammar/PlazmaScript.g:228:15: ( '+' | '-' ) mulExpr
            	    {
            	    set162=(Token)input.LT(1);
            	    set162=(Token)input.LT(1);
            	    if ( (input.LA(1)>=Add && input.LA(1)<=Subtract) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set162), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_mulExpr_in_addExpr1667);
            	    mulExpr163=mulExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, mulExpr163.getTree());

            	    }
            	    break;

            	default :
            	    break loop30;
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
    // grammar/PlazmaScript.g:231:1: mulExpr : powExpr ( ( '*' | '/' | '%' ) powExpr )* ;
    public final PlazmaScriptParser.mulExpr_return mulExpr() throws RecognitionException {
        PlazmaScriptParser.mulExpr_return retval = new PlazmaScriptParser.mulExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set165=null;
        PlazmaScriptParser.powExpr_return powExpr164 = null;

        PlazmaScriptParser.powExpr_return powExpr166 = null;


        Object set165_tree=null;

        try {
            // grammar/PlazmaScript.g:232:3: ( powExpr ( ( '*' | '/' | '%' ) powExpr )* )
            // grammar/PlazmaScript.g:232:6: powExpr ( ( '*' | '/' | '%' ) powExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_powExpr_in_mulExpr1683);
            powExpr164=powExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, powExpr164.getTree());
            // grammar/PlazmaScript.g:232:14: ( ( '*' | '/' | '%' ) powExpr )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( ((LA31_0>=Multiply && LA31_0<=Modulus)) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // grammar/PlazmaScript.g:232:15: ( '*' | '/' | '%' ) powExpr
            	    {
            	    set165=(Token)input.LT(1);
            	    set165=(Token)input.LT(1);
            	    if ( (input.LA(1)>=Multiply && input.LA(1)<=Modulus) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set165), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_powExpr_in_mulExpr1699);
            	    powExpr166=powExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, powExpr166.getTree());

            	    }
            	    break;

            	default :
            	    break loop31;
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
    // grammar/PlazmaScript.g:235:1: powExpr : unaryExpr ( '^' unaryExpr )* ;
    public final PlazmaScriptParser.powExpr_return powExpr() throws RecognitionException {
        PlazmaScriptParser.powExpr_return retval = new PlazmaScriptParser.powExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal168=null;
        PlazmaScriptParser.unaryExpr_return unaryExpr167 = null;

        PlazmaScriptParser.unaryExpr_return unaryExpr169 = null;


        Object char_literal168_tree=null;

        try {
            // grammar/PlazmaScript.g:236:3: ( unaryExpr ( '^' unaryExpr )* )
            // grammar/PlazmaScript.g:236:6: unaryExpr ( '^' unaryExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_unaryExpr_in_powExpr1715);
            unaryExpr167=unaryExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpr167.getTree());
            // grammar/PlazmaScript.g:236:16: ( '^' unaryExpr )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==Pow) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // grammar/PlazmaScript.g:236:17: '^' unaryExpr
            	    {
            	    char_literal168=(Token)match(input,Pow,FOLLOW_Pow_in_powExpr1718); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal168_tree = (Object)adaptor.create(char_literal168);
            	    root_0 = (Object)adaptor.becomeRoot(char_literal168_tree, root_0);
            	    }
            	    pushFollow(FOLLOW_unaryExpr_in_powExpr1721);
            	    unaryExpr169=unaryExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpr169.getTree());

            	    }
            	    break;

            	default :
            	    break loop32;
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
    // grammar/PlazmaScript.g:239:1: unaryExpr : ( '+' atom -> ^( UNARY_PLUS atom ) | '-' atom -> ^( UNARY_MIN atom ) | '!' atom -> ^( NEGATE atom ) | 'not' atom -> ^( NEGATE atom ) | atom );
    public final PlazmaScriptParser.unaryExpr_return unaryExpr() throws RecognitionException {
        PlazmaScriptParser.unaryExpr_return retval = new PlazmaScriptParser.unaryExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal170=null;
        Token char_literal172=null;
        Token char_literal174=null;
        Token string_literal176=null;
        PlazmaScriptParser.atom_return atom171 = null;

        PlazmaScriptParser.atom_return atom173 = null;

        PlazmaScriptParser.atom_return atom175 = null;

        PlazmaScriptParser.atom_return atom177 = null;

        PlazmaScriptParser.atom_return atom178 = null;


        Object char_literal170_tree=null;
        Object char_literal172_tree=null;
        Object char_literal174_tree=null;
        Object string_literal176_tree=null;
        RewriteRuleTokenStream stream_Add=new RewriteRuleTokenStream(adaptor,"token Add");
        RewriteRuleTokenStream stream_NotWord=new RewriteRuleTokenStream(adaptor,"token NotWord");
        RewriteRuleTokenStream stream_Subtract=new RewriteRuleTokenStream(adaptor,"token Subtract");
        RewriteRuleTokenStream stream_Not=new RewriteRuleTokenStream(adaptor,"token Not");
        RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");
        try {
            // grammar/PlazmaScript.g:240:3: ( '+' atom -> ^( UNARY_PLUS atom ) | '-' atom -> ^( UNARY_MIN atom ) | '!' atom -> ^( NEGATE atom ) | 'not' atom -> ^( NEGATE atom ) | atom )
            int alt33=5;
            switch ( input.LA(1) ) {
            case Add:
                {
                alt33=1;
                }
                break;
            case Subtract:
                {
                alt33=2;
                }
                break;
            case Not:
                {
                alt33=3;
                }
                break;
            case NotWord:
                {
                alt33=4;
                }
                break;
            case Identifier:
            case Println:
            case Print:
            case Assert:
            case Date:
            case DateTime:
            case Time:
            case Duration:
            case Period:
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
                alt33=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }

            switch (alt33) {
                case 1 :
                    // grammar/PlazmaScript.g:240:6: '+' atom
                    {
                    char_literal170=(Token)match(input,Add,FOLLOW_Add_in_unaryExpr1739); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Add.add(char_literal170);

                    pushFollow(FOLLOW_atom_in_unaryExpr1741);
                    atom171=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom171.getTree());


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
                    // 240:15: -> ^( UNARY_PLUS atom )
                    {
                        // grammar/PlazmaScript.g:240:18: ^( UNARY_PLUS atom )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(UNARY_PLUS, "UNARY_PLUS"), root_1);

                        adaptor.addChild(root_1, stream_atom.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:241:6: '-' atom
                    {
                    char_literal172=(Token)match(input,Subtract,FOLLOW_Subtract_in_unaryExpr1756); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Subtract.add(char_literal172);

                    pushFollow(FOLLOW_atom_in_unaryExpr1758);
                    atom173=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom173.getTree());


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
                    // 241:15: -> ^( UNARY_MIN atom )
                    {
                        // grammar/PlazmaScript.g:241:18: ^( UNARY_MIN atom )
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
                case 3 :
                    // grammar/PlazmaScript.g:242:6: '!' atom
                    {
                    char_literal174=(Token)match(input,Not,FOLLOW_Not_in_unaryExpr1773); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Not.add(char_literal174);

                    pushFollow(FOLLOW_atom_in_unaryExpr1775);
                    atom175=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom175.getTree());


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
                    // 242:15: -> ^( NEGATE atom )
                    {
                        // grammar/PlazmaScript.g:242:18: ^( NEGATE atom )
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
                    // grammar/PlazmaScript.g:243:6: 'not' atom
                    {
                    string_literal176=(Token)match(input,NotWord,FOLLOW_NotWord_in_unaryExpr1790); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NotWord.add(string_literal176);

                    pushFollow(FOLLOW_atom_in_unaryExpr1792);
                    atom177=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom177.getTree());


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
                    // 243:17: -> ^( NEGATE atom )
                    {
                        // grammar/PlazmaScript.g:243:20: ^( NEGATE atom )
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
                case 5 :
                    // grammar/PlazmaScript.g:244:6: atom
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_atom_in_unaryExpr1809);
                    atom178=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, atom178.getTree());

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
    // grammar/PlazmaScript.g:247:1: atom : ( Integer | Number | Bool | Null | NaN | Infinity | lookup );
    public final PlazmaScriptParser.atom_return atom() throws RecognitionException {
        PlazmaScriptParser.atom_return retval = new PlazmaScriptParser.atom_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Integer179=null;
        Token Number180=null;
        Token Bool181=null;
        Token Null182=null;
        Token NaN183=null;
        Token Infinity184=null;
        PlazmaScriptParser.lookup_return lookup185 = null;


        Object Integer179_tree=null;
        Object Number180_tree=null;
        Object Bool181_tree=null;
        Object Null182_tree=null;
        Object NaN183_tree=null;
        Object Infinity184_tree=null;

        try {
            // grammar/PlazmaScript.g:248:3: ( Integer | Number | Bool | Null | NaN | Infinity | lookup )
            int alt34=7;
            switch ( input.LA(1) ) {
            case Integer:
                {
                alt34=1;
                }
                break;
            case Number:
                {
                alt34=2;
                }
                break;
            case Bool:
                {
                alt34=3;
                }
                break;
            case Null:
                {
                alt34=4;
                }
                break;
            case NaN:
                {
                alt34=5;
                }
                break;
            case Infinity:
                {
                alt34=6;
                }
                break;
            case Identifier:
            case Println:
            case Print:
            case Assert:
            case Date:
            case DateTime:
            case Time:
            case Duration:
            case Period:
            case List:
            case Set:
            case String:
            case OBrace:
            case OBracket:
            case OParen:
            case ContextIdentifier:
                {
                alt34=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }

            switch (alt34) {
                case 1 :
                    // grammar/PlazmaScript.g:248:6: Integer
                    {
                    root_0 = (Object)adaptor.nil();

                    Integer179=(Token)match(input,Integer,FOLLOW_Integer_in_atom1823); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Integer179_tree = (Object)adaptor.create(Integer179);
                    adaptor.addChild(root_0, Integer179_tree);
                    }

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:249:6: Number
                    {
                    root_0 = (Object)adaptor.nil();

                    Number180=(Token)match(input,Number,FOLLOW_Number_in_atom1830); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Number180_tree = (Object)adaptor.create(Number180);
                    adaptor.addChild(root_0, Number180_tree);
                    }

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScript.g:250:6: Bool
                    {
                    root_0 = (Object)adaptor.nil();

                    Bool181=(Token)match(input,Bool,FOLLOW_Bool_in_atom1837); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Bool181_tree = (Object)adaptor.create(Bool181);
                    adaptor.addChild(root_0, Bool181_tree);
                    }

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScript.g:251:6: Null
                    {
                    root_0 = (Object)adaptor.nil();

                    Null182=(Token)match(input,Null,FOLLOW_Null_in_atom1844); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Null182_tree = (Object)adaptor.create(Null182);
                    adaptor.addChild(root_0, Null182_tree);
                    }

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScript.g:252:6: NaN
                    {
                    root_0 = (Object)adaptor.nil();

                    NaN183=(Token)match(input,NaN,FOLLOW_NaN_in_atom1851); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NaN183_tree = (Object)adaptor.create(NaN183);
                    adaptor.addChild(root_0, NaN183_tree);
                    }

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScript.g:253:6: Infinity
                    {
                    root_0 = (Object)adaptor.nil();

                    Infinity184=(Token)match(input,Infinity,FOLLOW_Infinity_in_atom1858); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Infinity184_tree = (Object)adaptor.create(Infinity184);
                    adaptor.addChild(root_0, Infinity184_tree);
                    }

                    }
                    break;
                case 7 :
                    // grammar/PlazmaScript.g:254:6: lookup
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_lookup_in_atom1866);
                    lookup185=lookup();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lookup185.getTree());

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
    // grammar/PlazmaScript.g:257:1: list : '[' ( exprList )? ']' -> ^( LIST ( exprList )? ) ;
    public final PlazmaScriptParser.list_return list() throws RecognitionException {
        PlazmaScriptParser.list_return retval = new PlazmaScriptParser.list_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal186=null;
        Token char_literal188=null;
        PlazmaScriptParser.exprList_return exprList187 = null;


        Object char_literal186_tree=null;
        Object char_literal188_tree=null;
        RewriteRuleTokenStream stream_CBracket=new RewriteRuleTokenStream(adaptor,"token CBracket");
        RewriteRuleTokenStream stream_OBracket=new RewriteRuleTokenStream(adaptor,"token OBracket");
        RewriteRuleSubtreeStream stream_exprList=new RewriteRuleSubtreeStream(adaptor,"rule exprList");
        try {
            // grammar/PlazmaScript.g:258:3: ( '[' ( exprList )? ']' -> ^( LIST ( exprList )? ) )
            // grammar/PlazmaScript.g:258:6: '[' ( exprList )? ']'
            {
            char_literal186=(Token)match(input,OBracket,FOLLOW_OBracket_in_list1880); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBracket.add(char_literal186);

            // grammar/PlazmaScript.g:258:10: ( exprList )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( ((LA35_0>=Identifier && LA35_0<=Set)||(LA35_0>=Integer && LA35_0<=String)||(LA35_0>=Not && LA35_0<=NotWord)||(LA35_0>=Add && LA35_0<=Subtract)||LA35_0==OBrace||LA35_0==OBracket||LA35_0==OParen||LA35_0==ContextIdentifier) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // grammar/PlazmaScript.g:0:0: exprList
                    {
                    pushFollow(FOLLOW_exprList_in_list1882);
                    exprList187=exprList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_exprList.add(exprList187.getTree());

                    }
                    break;

            }

            char_literal188=(Token)match(input,CBracket,FOLLOW_CBracket_in_list1885); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBracket.add(char_literal188);



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
            // 258:24: -> ^( LIST ( exprList )? )
            {
                // grammar/PlazmaScript.g:258:27: ^( LIST ( exprList )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LIST, "LIST"), root_1);

                // grammar/PlazmaScript.g:258:34: ( exprList )?
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
    // grammar/PlazmaScript.g:261:1: map : '{' ( ':' | exprMap ) '}' -> ^( MAP ( exprMap )? ) ;
    public final PlazmaScriptParser.map_return map() throws RecognitionException {
        PlazmaScriptParser.map_return retval = new PlazmaScriptParser.map_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal189=null;
        Token char_literal190=null;
        Token char_literal192=null;
        PlazmaScriptParser.exprMap_return exprMap191 = null;


        Object char_literal189_tree=null;
        Object char_literal190_tree=null;
        Object char_literal192_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_Colon=new RewriteRuleTokenStream(adaptor,"token Colon");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_exprMap=new RewriteRuleSubtreeStream(adaptor,"rule exprMap");
        try {
            // grammar/PlazmaScript.g:262:3: ( '{' ( ':' | exprMap ) '}' -> ^( MAP ( exprMap )? ) )
            // grammar/PlazmaScript.g:262:6: '{' ( ':' | exprMap ) '}'
            {
            char_literal189=(Token)match(input,OBrace,FOLLOW_OBrace_in_map1908); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal189);

            // grammar/PlazmaScript.g:262:10: ( ':' | exprMap )
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==Colon) ) {
                alt36=1;
            }
            else if ( ((LA36_0>=Identifier && LA36_0<=Set)||(LA36_0>=Integer && LA36_0<=String)||(LA36_0>=Not && LA36_0<=NotWord)||(LA36_0>=Add && LA36_0<=Subtract)||LA36_0==OBrace||LA36_0==OBracket||LA36_0==OParen||LA36_0==ContextIdentifier) ) {
                alt36=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;
            }
            switch (alt36) {
                case 1 :
                    // grammar/PlazmaScript.g:262:11: ':'
                    {
                    char_literal190=(Token)match(input,Colon,FOLLOW_Colon_in_map1911); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Colon.add(char_literal190);


                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:262:17: exprMap
                    {
                    pushFollow(FOLLOW_exprMap_in_map1915);
                    exprMap191=exprMap();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_exprMap.add(exprMap191.getTree());

                    }
                    break;

            }

            char_literal192=(Token)match(input,CBrace,FOLLOW_CBrace_in_map1918); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal192);



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
            // 262:30: -> ^( MAP ( exprMap )? )
            {
                // grammar/PlazmaScript.g:262:33: ^( MAP ( exprMap )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(MAP, "MAP"), root_1);

                // grammar/PlazmaScript.g:262:39: ( exprMap )?
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
    // grammar/PlazmaScript.g:265:1: lookup : ( functionCall ( indexes )? -> ^( LOOKUP functionCall ( indexes )? ) | list ( indexes )? -> ^( LOOKUP list ( indexes )? ) | map ( indexes )? -> ^( LOOKUP map ( indexes )? ) | Identifier ( indexes )? -> ^( LOOKUP Identifier ( indexes )? ) | anyIdentifier ( indexes )? -> ^( LOOKUP ( indexes )? ) | String ( indexes )? -> ^( LOOKUP String ( indexes )? ) | '(' expression ')' ( indexes )? -> ^( LOOKUP expression ( indexes )? ) );
    public final PlazmaScriptParser.lookup_return lookup() throws RecognitionException {
        PlazmaScriptParser.lookup_return retval = new PlazmaScriptParser.lookup_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Identifier199=null;
        Token String203=null;
        Token char_literal205=null;
        Token char_literal207=null;
        PlazmaScriptParser.functionCall_return functionCall193 = null;

        PlazmaScriptParser.indexes_return indexes194 = null;

        PlazmaScriptParser.list_return list195 = null;

        PlazmaScriptParser.indexes_return indexes196 = null;

        PlazmaScriptParser.map_return map197 = null;

        PlazmaScriptParser.indexes_return indexes198 = null;

        PlazmaScriptParser.indexes_return indexes200 = null;

        PlazmaScriptParser.anyIdentifier_return anyIdentifier201 = null;

        PlazmaScriptParser.indexes_return indexes202 = null;

        PlazmaScriptParser.indexes_return indexes204 = null;

        PlazmaScriptParser.expression_return expression206 = null;

        PlazmaScriptParser.indexes_return indexes208 = null;


        Object Identifier199_tree=null;
        Object String203_tree=null;
        Object char_literal205_tree=null;
        Object char_literal207_tree=null;
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
            // grammar/PlazmaScript.g:266:3: ( functionCall ( indexes )? -> ^( LOOKUP functionCall ( indexes )? ) | list ( indexes )? -> ^( LOOKUP list ( indexes )? ) | map ( indexes )? -> ^( LOOKUP map ( indexes )? ) | Identifier ( indexes )? -> ^( LOOKUP Identifier ( indexes )? ) | anyIdentifier ( indexes )? -> ^( LOOKUP ( indexes )? ) | String ( indexes )? -> ^( LOOKUP String ( indexes )? ) | '(' expression ')' ( indexes )? -> ^( LOOKUP expression ( indexes )? ) )
            int alt44=7;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA44_1 = input.LA(2);

                if ( (LA44_1==OParen) ) {
                    alt44=1;
                }
                else if ( (synpred84_PlazmaScript()) ) {
                    alt44=4;
                }
                else if ( (synpred86_PlazmaScript()) ) {
                    alt44=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 44, 1, input);

                    throw nvae;
                }
                }
                break;
            case Println:
            case Print:
            case Assert:
            case Date:
            case DateTime:
            case Time:
            case Duration:
            case Period:
            case List:
            case Set:
                {
                alt44=1;
                }
                break;
            case OBracket:
                {
                alt44=2;
                }
                break;
            case OBrace:
                {
                alt44=3;
                }
                break;
            case ContextIdentifier:
                {
                alt44=5;
                }
                break;
            case String:
                {
                alt44=6;
                }
                break;
            case OParen:
                {
                alt44=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                throw nvae;
            }

            switch (alt44) {
                case 1 :
                    // grammar/PlazmaScript.g:266:6: functionCall ( indexes )?
                    {
                    pushFollow(FOLLOW_functionCall_in_lookup1941);
                    functionCall193=functionCall();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_functionCall.add(functionCall193.getTree());
                    // grammar/PlazmaScript.g:266:19: ( indexes )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==OBracket||LA37_0==107) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1943);
                            indexes194=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes194.getTree());

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
                    // 266:34: -> ^( LOOKUP functionCall ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:266:37: ^( LOOKUP functionCall ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_functionCall.nextTree());
                        // grammar/PlazmaScript.g:266:59: ( indexes )?
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
                    // grammar/PlazmaScript.g:267:6: list ( indexes )?
                    {
                    pushFollow(FOLLOW_list_in_lookup1968);
                    list195=list();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_list.add(list195.getTree());
                    // grammar/PlazmaScript.g:267:11: ( indexes )?
                    int alt38=2;
                    int LA38_0 = input.LA(1);

                    if ( (LA38_0==OBracket||LA38_0==107) ) {
                        alt38=1;
                    }
                    switch (alt38) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1970);
                            indexes196=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes196.getTree());

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
                    // 267:34: -> ^( LOOKUP list ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:267:37: ^( LOOKUP list ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_list.nextTree());
                        // grammar/PlazmaScript.g:267:51: ( indexes )?
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
                    // grammar/PlazmaScript.g:268:6: map ( indexes )?
                    {
                    pushFollow(FOLLOW_map_in_lookup2003);
                    map197=map();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_map.add(map197.getTree());
                    // grammar/PlazmaScript.g:268:10: ( indexes )?
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==OBracket||LA39_0==107) ) {
                        alt39=1;
                    }
                    switch (alt39) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2005);
                            indexes198=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes198.getTree());

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
                    // 268:34: -> ^( LOOKUP map ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:268:37: ^( LOOKUP map ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_map.nextTree());
                        // grammar/PlazmaScript.g:268:50: ( indexes )?
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
                    // grammar/PlazmaScript.g:269:6: Identifier ( indexes )?
                    {
                    Identifier199=(Token)match(input,Identifier,FOLLOW_Identifier_in_lookup2041); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier199);

                    // grammar/PlazmaScript.g:269:17: ( indexes )?
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( (LA40_0==OBracket||LA40_0==107) ) {
                        alt40=1;
                    }
                    switch (alt40) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2043);
                            indexes200=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes200.getTree());

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
                    // 269:34: -> ^( LOOKUP Identifier ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:269:37: ^( LOOKUP Identifier ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_Identifier.nextNode());
                        // grammar/PlazmaScript.g:269:57: ( indexes )?
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
                    // grammar/PlazmaScript.g:270:6: anyIdentifier ( indexes )?
                    {
                    pushFollow(FOLLOW_anyIdentifier_in_lookup2070);
                    anyIdentifier201=anyIdentifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_anyIdentifier.add(anyIdentifier201.getTree());
                    // grammar/PlazmaScript.g:270:20: ( indexes )?
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==OBracket||LA41_0==107) ) {
                        alt41=1;
                    }
                    switch (alt41) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2072);
                            indexes202=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes202.getTree());

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
                    // 270:34: -> ^( LOOKUP ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:270:37: ^( LOOKUP ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, new CommonTree(new CommonToken(Identifier, (anyIdentifier201!=null?input.toString(anyIdentifier201.start,anyIdentifier201.stop):null))));
                        // grammar/PlazmaScript.g:270:113: ( indexes )?
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
                    // grammar/PlazmaScript.g:271:6: String ( indexes )?
                    {
                    String203=(Token)match(input,String,FOLLOW_String_in_lookup2098); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_String.add(String203);

                    // grammar/PlazmaScript.g:271:13: ( indexes )?
                    int alt42=2;
                    int LA42_0 = input.LA(1);

                    if ( (LA42_0==OBracket||LA42_0==107) ) {
                        alt42=1;
                    }
                    switch (alt42) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2100);
                            indexes204=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes204.getTree());

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
                    // 271:34: -> ^( LOOKUP String ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:271:37: ^( LOOKUP String ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_String.nextNode());
                        // grammar/PlazmaScript.g:271:53: ( indexes )?
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
                    // grammar/PlazmaScript.g:272:6: '(' expression ')' ( indexes )?
                    {
                    char_literal205=(Token)match(input,OParen,FOLLOW_OParen_in_lookup2131); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal205);

                    pushFollow(FOLLOW_expression_in_lookup2133);
                    expression206=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression206.getTree());
                    char_literal207=(Token)match(input,CParen,FOLLOW_CParen_in_lookup2135); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal207);

                    // grammar/PlazmaScript.g:272:25: ( indexes )?
                    int alt43=2;
                    int LA43_0 = input.LA(1);

                    if ( (LA43_0==OBracket||LA43_0==107) ) {
                        alt43=1;
                    }
                    switch (alt43) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2137);
                            indexes208=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes208.getTree());

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
                    // 272:34: -> ^( LOOKUP expression ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:272:37: ^( LOOKUP expression ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_expression.nextTree());
                        // grammar/PlazmaScript.g:272:57: ( indexes )?
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
    // grammar/PlazmaScript.g:275:1: indexes : ( tail )+ -> ^( TAILS ( tail )+ ) ;
    public final PlazmaScriptParser.indexes_return indexes() throws RecognitionException {
        PlazmaScriptParser.indexes_return retval = new PlazmaScriptParser.indexes_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        PlazmaScriptParser.tail_return tail209 = null;


        RewriteRuleSubtreeStream stream_tail=new RewriteRuleSubtreeStream(adaptor,"rule tail");
        try {
            // grammar/PlazmaScript.g:277:3: ( ( tail )+ -> ^( TAILS ( tail )+ ) )
            // grammar/PlazmaScript.g:277:6: ( tail )+
            {
            // grammar/PlazmaScript.g:277:6: ( tail )+
            int cnt45=0;
            loop45:
            do {
                int alt45=2;
                int LA45_0 = input.LA(1);

                if ( (LA45_0==OBracket||LA45_0==107) ) {
                    alt45=1;
                }


                switch (alt45) {
            	case 1 :
            	    // grammar/PlazmaScript.g:277:7: tail
            	    {
            	    pushFollow(FOLLOW_tail_in_indexes2167);
            	    tail209=tail();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_tail.add(tail209.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt45 >= 1 ) break loop45;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(45, input);
                        throw eee;
                }
                cnt45++;
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
            // 277:14: -> ^( TAILS ( tail )+ )
            {
                // grammar/PlazmaScript.g:277:17: ^( TAILS ( tail )+ )
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
    // grammar/PlazmaScript.g:280:1: tail : ( '[' expression ']' -> ^( INDEX expression ) | '.' Identifier -> ^( ATTRIBUTE Identifier ) | '.' Identifier '(' ( exprList )? ')' -> ^( CALL Identifier ( exprList )? ) );
    public final PlazmaScriptParser.tail_return tail() throws RecognitionException {
        PlazmaScriptParser.tail_return retval = new PlazmaScriptParser.tail_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal210=null;
        Token char_literal212=null;
        Token char_literal213=null;
        Token Identifier214=null;
        Token char_literal215=null;
        Token Identifier216=null;
        Token char_literal217=null;
        Token char_literal219=null;
        PlazmaScriptParser.expression_return expression211 = null;

        PlazmaScriptParser.exprList_return exprList218 = null;


        Object char_literal210_tree=null;
        Object char_literal212_tree=null;
        Object char_literal213_tree=null;
        Object Identifier214_tree=null;
        Object char_literal215_tree=null;
        Object Identifier216_tree=null;
        Object char_literal217_tree=null;
        Object char_literal219_tree=null;
        RewriteRuleTokenStream stream_CBracket=new RewriteRuleTokenStream(adaptor,"token CBracket");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_OBracket=new RewriteRuleTokenStream(adaptor,"token OBracket");
        RewriteRuleTokenStream stream_107=new RewriteRuleTokenStream(adaptor,"token 107");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_exprList=new RewriteRuleSubtreeStream(adaptor,"rule exprList");
        try {
            // grammar/PlazmaScript.g:281:2: ( '[' expression ']' -> ^( INDEX expression ) | '.' Identifier -> ^( ATTRIBUTE Identifier ) | '.' Identifier '(' ( exprList )? ')' -> ^( CALL Identifier ( exprList )? ) )
            int alt47=3;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==OBracket) ) {
                alt47=1;
            }
            else if ( (LA47_0==107) ) {
                int LA47_2 = input.LA(2);

                if ( (LA47_2==Identifier) ) {
                    int LA47_3 = input.LA(3);

                    if ( (LA47_3==OParen) ) {
                        alt47=3;
                    }
                    else if ( (LA47_3==EOF||(LA47_3>=In && LA47_3<=Range)||(LA47_3>=XorWord && LA47_3<=Pow)||(LA47_3>=GT && LA47_3<=Modulus)||(LA47_3>=CBrace && LA47_3<=CBracket)||(LA47_3>=CParen && LA47_3<=Colon)||LA47_3==107) ) {
                        alt47=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 47, 3, input);

                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 47, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 47, 0, input);

                throw nvae;
            }
            switch (alt47) {
                case 1 :
                    // grammar/PlazmaScript.g:281:4: '[' expression ']'
                    {
                    char_literal210=(Token)match(input,OBracket,FOLLOW_OBracket_in_tail2190); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OBracket.add(char_literal210);

                    pushFollow(FOLLOW_expression_in_tail2192);
                    expression211=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression211.getTree());
                    char_literal212=(Token)match(input,CBracket,FOLLOW_CBracket_in_tail2194); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CBracket.add(char_literal212);



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
                    // 281:37: -> ^( INDEX expression )
                    {
                        // grammar/PlazmaScript.g:281:40: ^( INDEX expression )
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
                    // grammar/PlazmaScript.g:282:4: '.' Identifier
                    {
                    char_literal213=(Token)match(input,107,FOLLOW_107_in_tail2221); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_107.add(char_literal213);

                    Identifier214=(Token)match(input,Identifier,FOLLOW_Identifier_in_tail2223); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier214);



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
                    // 282:37: -> ^( ATTRIBUTE Identifier )
                    {
                        // grammar/PlazmaScript.g:282:40: ^( ATTRIBUTE Identifier )
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
                    // grammar/PlazmaScript.g:283:4: '.' Identifier '(' ( exprList )? ')'
                    {
                    char_literal215=(Token)match(input,107,FOLLOW_107_in_tail2254); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_107.add(char_literal215);

                    Identifier216=(Token)match(input,Identifier,FOLLOW_Identifier_in_tail2256); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier216);

                    char_literal217=(Token)match(input,OParen,FOLLOW_OParen_in_tail2258); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal217);

                    // grammar/PlazmaScript.g:283:23: ( exprList )?
                    int alt46=2;
                    int LA46_0 = input.LA(1);

                    if ( ((LA46_0>=Identifier && LA46_0<=Set)||(LA46_0>=Integer && LA46_0<=String)||(LA46_0>=Not && LA46_0<=NotWord)||(LA46_0>=Add && LA46_0<=Subtract)||LA46_0==OBrace||LA46_0==OBracket||LA46_0==OParen||LA46_0==ContextIdentifier) ) {
                        alt46=1;
                    }
                    switch (alt46) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_tail2260);
                            exprList218=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList218.getTree());

                            }
                            break;

                    }

                    char_literal219=(Token)match(input,CParen,FOLLOW_CParen_in_tail2263); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal219);



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
                    // 283:37: -> ^( CALL Identifier ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:283:40: ^( CALL Identifier ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CALL, "CALL"), root_1);

                        adaptor.addChild(root_1, stream_Identifier.nextNode());
                        // grammar/PlazmaScript.g:283:58: ( exprList )?
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
    // grammar/PlazmaScript.g:382:1: anyIdentifier : ( ContextIdentifier | Identifier );
    public final PlazmaScriptParser.anyIdentifier_return anyIdentifier() throws RecognitionException {
        PlazmaScriptParser.anyIdentifier_return retval = new PlazmaScriptParser.anyIdentifier_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set220=null;

        Object set220_tree=null;

        try {
            // grammar/PlazmaScript.g:383:3: ( ContextIdentifier | Identifier )
            // grammar/PlazmaScript.g:
            {
            root_0 = (Object)adaptor.nil();

            set220=(Token)input.LT(1);
            if ( input.LA(1)==Identifier||input.LA(1)==ContextIdentifier ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set220));
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
        // grammar/PlazmaScript.g:81:6: ( assignment ';' )
        // grammar/PlazmaScript.g:81:6: assignment ';'
        {
        pushFollow(FOLLOW_assignment_in_synpred4_PlazmaScript296);
        assignment();

        state._fsp--;
        if (state.failed) return ;
        match(input,SColon,FOLLOW_SColon_in_synpred4_PlazmaScript298); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred4_PlazmaScript

    // $ANTLR start synpred5_PlazmaScript
    public final void synpred5_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:82:6: ( declare ';' )
        // grammar/PlazmaScript.g:82:6: declare ';'
        {
        pushFollow(FOLLOW_declare_in_synpred5_PlazmaScript311);
        declare();

        state._fsp--;
        if (state.failed) return ;
        match(input,SColon,FOLLOW_SColon_in_synpred5_PlazmaScript313); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred5_PlazmaScript

    // $ANTLR start synpred6_PlazmaScript
    public final void synpred6_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:83:6: ( functionCall ';' )
        // grammar/PlazmaScript.g:83:6: functionCall ';'
        {
        pushFollow(FOLLOW_functionCall_in_synpred6_PlazmaScript328);
        functionCall();

        state._fsp--;
        if (state.failed) return ;
        match(input,SColon,FOLLOW_SColon_in_synpred6_PlazmaScript330); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred6_PlazmaScript

    // $ANTLR start synpred7_PlazmaScript
    public final void synpred7_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:84:6: ( lookup ';' )
        // grammar/PlazmaScript.g:84:6: lookup ';'
        {
        pushFollow(FOLLOW_lookup_in_synpred7_PlazmaScript341);
        lookup();

        state._fsp--;
        if (state.failed) return ;
        match(input,SColon,FOLLOW_SColon_in_synpred7_PlazmaScript343); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred7_PlazmaScript

    // $ANTLR start synpred14_PlazmaScript
    public final void synpred14_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:94:6: ( ( variableDef )? Identifier ( indexes )? '=' expression )
        // grammar/PlazmaScript.g:94:6: ( variableDef )? Identifier ( indexes )? '=' expression
        {
        // grammar/PlazmaScript.g:94:6: ( variableDef )?
        int alt48=2;
        int LA48_0 = input.LA(1);

        if ( (LA48_0==Var) ) {
            alt48=1;
        }
        switch (alt48) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: variableDef
                {
                pushFollow(FOLLOW_variableDef_in_synpred14_PlazmaScript444);
                variableDef();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        match(input,Identifier,FOLLOW_Identifier_in_synpred14_PlazmaScript447); if (state.failed) return ;
        // grammar/PlazmaScript.g:94:30: ( indexes )?
        int alt49=2;
        int LA49_0 = input.LA(1);

        if ( (LA49_0==OBracket||LA49_0==107) ) {
            alt49=1;
        }
        switch (alt49) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: indexes
                {
                pushFollow(FOLLOW_indexes_in_synpred14_PlazmaScript449);
                indexes();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        match(input,Assign,FOLLOW_Assign_in_synpred14_PlazmaScript452); if (state.failed) return ;
        pushFollow(FOLLOW_expression_in_synpred14_PlazmaScript454);
        expression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred14_PlazmaScript

    // $ANTLR start synpred84_PlazmaScript
    public final void synpred84_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:269:6: ( Identifier ( indexes )? )
        // grammar/PlazmaScript.g:269:6: Identifier ( indexes )?
        {
        match(input,Identifier,FOLLOW_Identifier_in_synpred84_PlazmaScript2041); if (state.failed) return ;
        // grammar/PlazmaScript.g:269:17: ( indexes )?
        int alt61=2;
        int LA61_0 = input.LA(1);

        if ( (LA61_0==OBracket||LA61_0==107) ) {
            alt61=1;
        }
        switch (alt61) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: indexes
                {
                pushFollow(FOLLOW_indexes_in_synpred84_PlazmaScript2043);
                indexes();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred84_PlazmaScript

    // $ANTLR start synpred86_PlazmaScript
    public final void synpred86_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:270:6: ( anyIdentifier ( indexes )? )
        // grammar/PlazmaScript.g:270:6: anyIdentifier ( indexes )?
        {
        pushFollow(FOLLOW_anyIdentifier_in_synpred86_PlazmaScript2070);
        anyIdentifier();

        state._fsp--;
        if (state.failed) return ;
        // grammar/PlazmaScript.g:270:20: ( indexes )?
        int alt62=2;
        int LA62_0 = input.LA(1);

        if ( (LA62_0==OBracket||LA62_0==107) ) {
            alt62=1;
        }
        switch (alt62) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: indexes
                {
                pushFollow(FOLLOW_indexes_in_synpred86_PlazmaScript2072);
                indexes();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred86_PlazmaScript

    // Delegated rules

    public final boolean synpred14_PlazmaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred14_PlazmaScript_fragment(); // can never throw exception
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
    public final boolean synpred86_PlazmaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred86_PlazmaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred7_PlazmaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred7_PlazmaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred84_PlazmaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred84_PlazmaScript_fragment(); // can never throw exception
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
        "\32\uffff";
    static final String DFA3_eofS =
        "\32\uffff";
    static final String DFA3_minS =
        "\1\42\15\0\14\uffff";
    static final String DFA3_maxS =
        "\1\143\15\0\14\uffff";
    static final String DFA3_acceptS =
        "\16\uffff\1\4\3\uffff\1\5\1\6\1\7\1\10\1\11\1\1\1\2\1\3";
    static final String DFA3_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1"+
        "\14\14\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\25\1\26\1\2\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
            "\1\22\1\uffff\1\1\1\uffff\1\23\1\24\11\uffff\1\16\25\uffff\1"+
            "\16\1\uffff\1\16\1\uffff\1\16\12\uffff\1\3",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
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
            return "80:1: statement : ( assignment ';' -> assignment | declare ';' -> declare | functionCall ';' -> functionCall | lookup ';' -> lookup | ifStatement | forStatement | whileStatement | Break ';' -> Break | Continue ';' -> Continue );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA3_1 = input.LA(1);

                         
                        int index3_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_PlazmaScript()) ) {s = 23;}

                        else if ( (synpred5_PlazmaScript()) ) {s = 24;}

                         
                        input.seek(index3_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA3_2 = input.LA(1);

                         
                        int index3_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_PlazmaScript()) ) {s = 23;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 25;}

                        else if ( (synpred7_PlazmaScript()) ) {s = 14;}

                         
                        input.seek(index3_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA3_3 = input.LA(1);

                         
                        int index3_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_PlazmaScript()) ) {s = 23;}

                        else if ( (synpred7_PlazmaScript()) ) {s = 14;}

                         
                        input.seek(index3_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA3_4 = input.LA(1);

                         
                        int index3_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred6_PlazmaScript()) ) {s = 25;}

                        else if ( (synpred7_PlazmaScript()) ) {s = 14;}

                         
                        input.seek(index3_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA3_5 = input.LA(1);

                         
                        int index3_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred6_PlazmaScript()) ) {s = 25;}

                        else if ( (synpred7_PlazmaScript()) ) {s = 14;}

                         
                        input.seek(index3_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA3_6 = input.LA(1);

                         
                        int index3_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred6_PlazmaScript()) ) {s = 25;}

                        else if ( (synpred7_PlazmaScript()) ) {s = 14;}

                         
                        input.seek(index3_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA3_7 = input.LA(1);

                         
                        int index3_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred6_PlazmaScript()) ) {s = 25;}

                        else if ( (synpred7_PlazmaScript()) ) {s = 14;}

                         
                        input.seek(index3_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA3_8 = input.LA(1);

                         
                        int index3_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred6_PlazmaScript()) ) {s = 25;}

                        else if ( (synpred7_PlazmaScript()) ) {s = 14;}

                         
                        input.seek(index3_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA3_9 = input.LA(1);

                         
                        int index3_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred6_PlazmaScript()) ) {s = 25;}

                        else if ( (synpred7_PlazmaScript()) ) {s = 14;}

                         
                        input.seek(index3_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA3_10 = input.LA(1);

                         
                        int index3_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred6_PlazmaScript()) ) {s = 25;}

                        else if ( (synpred7_PlazmaScript()) ) {s = 14;}

                         
                        input.seek(index3_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA3_11 = input.LA(1);

                         
                        int index3_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred6_PlazmaScript()) ) {s = 25;}

                        else if ( (synpred7_PlazmaScript()) ) {s = 14;}

                         
                        input.seek(index3_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA3_12 = input.LA(1);

                         
                        int index3_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred6_PlazmaScript()) ) {s = 25;}

                        else if ( (synpred7_PlazmaScript()) ) {s = 14;}

                         
                        input.seek(index3_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA3_13 = input.LA(1);

                         
                        int index3_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred6_PlazmaScript()) ) {s = 25;}

                        else if ( (synpred7_PlazmaScript()) ) {s = 14;}

                         
                        input.seek(index3_13);
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
 

    public static final BitSet FOLLOW_block_in_parse220 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_parse222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_block241 = new BitSet(new long[]{0x401EFFFE00000002L,0x0000000801500000L});
    public static final BitSet FOLLOW_functionDecl_in_block245 = new BitSet(new long[]{0x401EFFFE00000002L,0x0000000801500000L});
    public static final BitSet FOLLOW_Return_in_block250 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_expression_in_block252 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_SColon_in_block254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_statement296 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_SColon_in_statement298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declare_in_statement311 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_SColon_in_statement313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_statement328 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_SColon_in_statement330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_statement341 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_SColon_in_statement343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStatement_in_statement387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forStatement_in_statement394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whileStatement_in_statement401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Break_in_statement408 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_SColon_in_statement410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Continue_in_statement421 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_SColon_in_statement423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDef_in_assignment444 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_Identifier_in_assignment447 = new BitSet(new long[]{0x0000000000000000L,0x0000080008400000L});
    public static final BitSet FOLLOW_indexes_in_assignment449 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_Assign_in_assignment452 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_expression_in_assignment454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDef_in_assignment477 = new BitSet(new long[]{0x0002001000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_anyIdentifier_in_assignment480 = new BitSet(new long[]{0x0000000000000000L,0x0000080008400000L});
    public static final BitSet FOLLOW_indexes_in_assignment482 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_Assign_in_assignment485 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_expression_in_assignment487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDef_in_declare518 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_Identifier_in_declare520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_functionCall546 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_OParen_in_functionCall548 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000803519800L});
    public static final BitSet FOLLOW_exprList_in_functionCall550 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_CParen_in_functionCall553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Println_in_functionCall571 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_OParen_in_functionCall573 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000803519800L});
    public static final BitSet FOLLOW_expression_in_functionCall575 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_CParen_in_functionCall578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Print_in_functionCall597 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_OParen_in_functionCall599 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_expression_in_functionCall601 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_CParen_in_functionCall603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Assert_in_functionCall624 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_OParen_in_functionCall626 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_expression_in_functionCall628 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_CParen_in_functionCall630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Date_in_functionCall650 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_OParen_in_functionCall652 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000803519800L});
    public static final BitSet FOLLOW_exprList_in_functionCall654 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_CParen_in_functionCall657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DateTime_in_functionCall680 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_OParen_in_functionCall682 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000803519800L});
    public static final BitSet FOLLOW_exprList_in_functionCall684 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_CParen_in_functionCall687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Time_in_functionCall710 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_OParen_in_functionCall712 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000803519800L});
    public static final BitSet FOLLOW_exprList_in_functionCall714 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_CParen_in_functionCall717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Duration_in_functionCall740 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_OParen_in_functionCall742 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000803519800L});
    public static final BitSet FOLLOW_expression_in_functionCall744 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_CParen_in_functionCall747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Period_in_functionCall766 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_OParen_in_functionCall768 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000803519800L});
    public static final BitSet FOLLOW_exprList_in_functionCall770 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_CParen_in_functionCall773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_List_in_functionCall804 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_OParen_in_functionCall806 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000803519800L});
    public static final BitSet FOLLOW_exprList_in_functionCall808 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_CParen_in_functionCall811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Set_in_functionCall834 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_OParen_in_functionCall836 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000803519800L});
    public static final BitSet FOLLOW_exprList_in_functionCall838 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_CParen_in_functionCall841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStat_in_ifStatement879 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_elseIfStat_in_ifStatement881 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_elseStat_in_ifStatement884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_If_in_ifStat913 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_OParen_in_ifStat915 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_expression_in_ifStat917 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_CParen_in_ifStat919 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_OBrace_in_ifStat921 = new BitSet(new long[]{0x401EFFFE00000000L,0x0000000801500000L});
    public static final BitSet FOLLOW_block_in_ifStat923 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_CBrace_in_ifStat925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Else_in_elseIfStat949 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_If_in_elseIfStat951 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_OParen_in_elseIfStat953 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_expression_in_elseIfStat955 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_CParen_in_elseIfStat957 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_OBrace_in_elseIfStat959 = new BitSet(new long[]{0x401EFFFE00000000L,0x0000000801500000L});
    public static final BitSet FOLLOW_block_in_elseIfStat961 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_CBrace_in_elseIfStat963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Else_in_elseStat987 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_OBrace_in_elseStat989 = new BitSet(new long[]{0x401EFFFE00000000L,0x0000000801500000L});
    public static final BitSet FOLLOW_block_in_elseStat991 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_CBrace_in_elseStat993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Var_in_variableDef1013 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Def_in_functionDecl1045 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_Identifier_in_functionDecl1047 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_OParen_in_functionDecl1049 = new BitSet(new long[]{0x0000001000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_idList_in_functionDecl1051 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_CParen_in_functionDecl1054 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_OBrace_in_functionDecl1056 = new BitSet(new long[]{0x401EFFFE00000000L,0x0000000801500000L});
    public static final BitSet FOLLOW_block_in_functionDecl1058 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_CBrace_in_functionDecl1060 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_For_in_forStatement1089 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_OParen_in_forStatement1091 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_Identifier_in_forStatement1093 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_In_in_forStatement1095 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_expression_in_forStatement1097 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_CParen_in_forStatement1099 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_OBrace_in_forStatement1101 = new BitSet(new long[]{0x401EFFFE00000000L,0x0000000801500000L});
    public static final BitSet FOLLOW_block_in_forStatement1103 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_CBrace_in_forStatement1105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_While_in_whileStatement1140 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_OParen_in_whileStatement1142 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_expression_in_whileStatement1144 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_CParen_in_whileStatement1146 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_OBrace_in_whileStatement1148 = new BitSet(new long[]{0x401EFFFE00000000L,0x0000000801500000L});
    public static final BitSet FOLLOW_block_in_whileStatement1150 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_CBrace_in_whileStatement1152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_idList1176 = new BitSet(new long[]{0x0000000000000002L,0x0000000010000000L});
    public static final BitSet FOLLOW_Comma_in_idList1179 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_Identifier_in_idList1181 = new BitSet(new long[]{0x0000000000000002L,0x0000000010000000L});
    public static final BitSet FOLLOW_expression_in_exprList1206 = new BitSet(new long[]{0x0000000000000002L,0x0000000010000000L});
    public static final BitSet FOLLOW_Comma_in_exprList1209 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_expression_in_exprList1211 = new BitSet(new long[]{0x0000000000000002L,0x0000000010000000L});
    public static final BitSet FOLLOW_expression_in_exprPair1237 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_Colon_in_exprPair1239 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_expression_in_exprPair1241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprPair_in_exprMap1266 = new BitSet(new long[]{0x0000000000000002L,0x0000000010000000L});
    public static final BitSet FOLLOW_Comma_in_exprMap1269 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_exprPair_in_exprMap1271 = new BitSet(new long[]{0x0000000000000002L,0x0000000010000000L});
    public static final BitSet FOLLOW_condExpr_in_expression1297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_startExpr_in_condExpr1312 = new BitSet(new long[]{0x00E0000000000002L,0x0000000020000000L});
    public static final BitSet FOLLOW_QMark_in_condExpr1327 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_expression_in_condExpr1331 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_Colon_in_condExpr1333 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_expression_in_condExpr1337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_In_in_condExpr1360 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_expression_in_condExpr1362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RangeE_in_condExpr1407 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_expression_in_condExpr1409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Range_in_condExpr1444 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_expression_in_condExpr1446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orExpr_in_startExpr1509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_andExpr_in_orExpr1522 = new BitSet(new long[]{0x8000000000000002L,0x0000000000000007L});
    public static final BitSet FOLLOW_set_in_orExpr1525 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_andExpr_in_orExpr1542 = new BitSet(new long[]{0x8000000000000002L,0x0000000000000007L});
    public static final BitSet FOLLOW_equExpr_in_andExpr1559 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000038L});
    public static final BitSet FOLLOW_set_in_andExpr1562 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_equExpr_in_andExpr1575 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000038L});
    public static final BitSet FOLLOW_relExpr_in_equExpr1591 = new BitSet(new long[]{0x0000000000000002L,0x00000000000000C0L});
    public static final BitSet FOLLOW_set_in_equExpr1594 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_relExpr_in_equExpr1603 = new BitSet(new long[]{0x0000000000000002L,0x00000000000000C0L});
    public static final BitSet FOLLOW_addExpr_in_relExpr1619 = new BitSet(new long[]{0x0000000000000002L,0x0000000000006300L});
    public static final BitSet FOLLOW_set_in_relExpr1622 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_addExpr_in_relExpr1639 = new BitSet(new long[]{0x0000000000000002L,0x0000000000006300L});
    public static final BitSet FOLLOW_mulExpr_in_addExpr1655 = new BitSet(new long[]{0x0000000000000002L,0x0000000000018000L});
    public static final BitSet FOLLOW_set_in_addExpr1658 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_mulExpr_in_addExpr1667 = new BitSet(new long[]{0x0000000000000002L,0x0000000000018000L});
    public static final BitSet FOLLOW_powExpr_in_mulExpr1683 = new BitSet(new long[]{0x0000000000000002L,0x00000000000E0000L});
    public static final BitSet FOLLOW_set_in_mulExpr1686 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_powExpr_in_mulExpr1699 = new BitSet(new long[]{0x0000000000000002L,0x00000000000E0000L});
    public static final BitSet FOLLOW_unaryExpr_in_powExpr1715 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_Pow_in_powExpr1718 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_unaryExpr_in_powExpr1721 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_Add_in_unaryExpr1739 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Subtract_in_unaryExpr1756 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Not_in_unaryExpr1773 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NotWord_in_unaryExpr1790 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1792 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Integer_in_atom1823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Number_in_atom1830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Bool_in_atom1837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Null_in_atom1844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NaN_in_atom1851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Infinity_in_atom1858 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_atom1866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OBracket_in_list1880 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801D19800L});
    public static final BitSet FOLLOW_exprList_in_list1882 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_CBracket_in_list1885 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OBrace_in_map1908 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000841519800L});
    public static final BitSet FOLLOW_Colon_in_map1911 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_exprMap_in_map1915 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_CBrace_in_map1918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_lookup1941 = new BitSet(new long[]{0x0000000000000002L,0x0000080000400000L});
    public static final BitSet FOLLOW_indexes_in_lookup1943 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_in_lookup1968 = new BitSet(new long[]{0x0000000000000002L,0x0000080000400000L});
    public static final BitSet FOLLOW_indexes_in_lookup1970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_map_in_lookup2003 = new BitSet(new long[]{0x0000000000000002L,0x0000080000400000L});
    public static final BitSet FOLLOW_indexes_in_lookup2005 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_lookup2041 = new BitSet(new long[]{0x0000000000000002L,0x0000080000400000L});
    public static final BitSet FOLLOW_indexes_in_lookup2043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_anyIdentifier_in_lookup2070 = new BitSet(new long[]{0x0000000000000002L,0x0000080000400000L});
    public static final BitSet FOLLOW_indexes_in_lookup2072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_String_in_lookup2098 = new BitSet(new long[]{0x0000000000000002L,0x0000080000400000L});
    public static final BitSet FOLLOW_indexes_in_lookup2100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OParen_in_lookup2131 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_expression_in_lookup2133 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_CParen_in_lookup2135 = new BitSet(new long[]{0x0000000000000002L,0x0000080000400000L});
    public static final BitSet FOLLOW_indexes_in_lookup2137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tail_in_indexes2167 = new BitSet(new long[]{0x0000000000000002L,0x0000080000400000L});
    public static final BitSet FOLLOW_OBracket_in_tail2190 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_expression_in_tail2192 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_CBracket_in_tail2194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_107_in_tail2221 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_Identifier_in_tail2223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_107_in_tail2254 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_Identifier_in_tail2256 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_OParen_in_tail2258 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000803519800L});
    public static final BitSet FOLLOW_exprList_in_tail2260 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_CParen_in_tail2263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_anyIdentifier0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_synpred4_PlazmaScript296 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_SColon_in_synpred4_PlazmaScript298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declare_in_synpred5_PlazmaScript311 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_SColon_in_synpred5_PlazmaScript313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_synpred6_PlazmaScript328 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_SColon_in_synpred6_PlazmaScript330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_synpred7_PlazmaScript341 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_SColon_in_synpred7_PlazmaScript343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDef_in_synpred14_PlazmaScript444 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_Identifier_in_synpred14_PlazmaScript447 = new BitSet(new long[]{0x0000000000000000L,0x0000080008400000L});
    public static final BitSet FOLLOW_indexes_in_synpred14_PlazmaScript449 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_Assign_in_synpred14_PlazmaScript452 = new BitSet(new long[]{0x7F027FF000000000L,0x0000000801519800L});
    public static final BitSet FOLLOW_expression_in_synpred14_PlazmaScript454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_synpred84_PlazmaScript2041 = new BitSet(new long[]{0x0000000000000002L,0x0000080000400000L});
    public static final BitSet FOLLOW_indexes_in_synpred84_PlazmaScript2043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_anyIdentifier_in_synpred86_PlazmaScript2070 = new BitSet(new long[]{0x0000000000000002L,0x0000080000400000L});
    public static final BitSet FOLLOW_indexes_in_synpred86_PlazmaScript2072 = new BitSet(new long[]{0x0000000000000002L});

}