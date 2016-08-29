// $ANTLR 3.2 Sep 23, 2009 12:02:23 grammar/PlazmaScript.g 2016-08-29 16:18:07

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BLOCK", "RETURN", "STATEMENTS", "ASSIGNMENT", "FUNC_CALL", "EXP", "EXP_PAIR", "EXP_MAP", "EXP_LIST", "ID_LIST", "IF", "TERNARY", "UNARY_PLUS", "UNARY_MIN", "NEGATE", "FUNCTION", "INDEXES", "INDEX", "ATTRIBUTE", "CALL", "TAIL", "TAILS", "MAP", "LIST", "SET", "LOOKUP", "BREAK", "CONTINUE", "Return", "Break", "Continue", "Identifier", "Println", "Print", "Assert", "Date", "DateTime", "Time", "Duration", "Period", "List", "Set", "If", "Else", "Var", "Def", "For", "While", "In", "RangeE", "Range", "Integer", "Number", "Bool", "Null", "NaN", "Infinity", "String", "XorWord", "Or", "BitOr", "OrWord", "And", "BitAnd", "AndWord", "Equals", "NEquals", "GTEquals", "LTEquals", "Pow", "Not", "NotWord", "GT", "LT", "Add", "Subtract", "Multiply", "Divide", "Modulus", "OBrace", "CBrace", "OBracket", "CBracket", "OParen", "CParen", "SColon", "Assign", "Comma", "QMark", "Colon", "DecimalNumeral", "DecimalFloatingPoint", "Digits", "ExponentPart", "ContextIdentifier", "Digit", "Comment", "Space", "ExponentIndicator", "SignedInteger", "Sign", "NonZeroDigit", "'.'"
    };
    public static final int FUNCTION=19;
    public static final int LT=77;
    public static final int TERNARY=15;
    public static final int EXP_LIST=12;
    public static final int DateTime=40;
    public static final int Date=39;
    public static final int QMark=92;
    public static final int EOF=-1;
    public static final int NotWord=75;
    public static final int BREAK=30;
    public static final int Identifier=35;
    public static final int UNARY_PLUS=16;
    public static final int FUNC_CALL=8;
    public static final int CParen=88;
    public static final int Comment=100;
    public static final int EXP=9;
    public static final int Digits=96;
    public static final int RETURN=5;
    public static final int CBrace=84;
    public static final int ExponentPart=97;
    public static final int ExponentIndicator=102;
    public static final int Sign=104;
    public static final int DecimalNumeral=94;
    public static final int Null=58;
    public static final int OrWord=65;
    public static final int ContextIdentifier=98;
    public static final int Period=43;
    public static final int CBracket=86;
    public static final int Println=36;
    public static final int Bool=57;
    public static final int Modulus=82;
    public static final int Time=41;
    public static final int Colon=93;
    public static final int AndWord=68;
    public static final int LIST=27;
    public static final int Def=49;
    public static final int LOOKUP=29;
    public static final int RangeE=53;
    public static final int Range=54;
    public static final int Break=33;
    public static final int SignedInteger=103;
    public static final int BitOr=64;
    public static final int STATEMENTS=6;
    public static final int GT=76;
    public static final int CALL=23;
    public static final int DecimalFloatingPoint=95;
    public static final int Else=47;
    public static final int Equals=69;
    public static final int Var=48;
    public static final int XorWord=62;
    public static final int OParen=87;
    public static final int Assert=38;
    public static final int ATTRIBUTE=22;
    public static final int While=51;
    public static final int ID_LIST=13;
    public static final int Add=78;
    public static final int Set=45;
    public static final int TAIL=24;
    public static final int IF=14;
    public static final int Space=101;
    public static final int INDEX=21;
    public static final int Assign=90;
    public static final int EXP_MAP=11;
    public static final int NaN=59;
    public static final int Number=56;
    public static final int CONTINUE=31;
    public static final int Print=37;
    public static final int GTEquals=71;
    public static final int T__106=106;
    public static final int String=61;
    public static final int Or=63;
    public static final int Return=32;
    public static final int If=46;
    public static final int And=66;
    public static final int In=52;
    public static final int NEquals=70;
    public static final int Continue=34;
    public static final int Subtract=79;
    public static final int EXP_PAIR=10;
    public static final int BitAnd=67;
    public static final int Multiply=80;
    public static final int OBrace=83;
    public static final int INDEXES=20;
    public static final int NEGATE=18;
    public static final int SET=28;
    public static final int Duration=42;
    public static final int Digit=99;
    public static final int For=50;
    public static final int Divide=81;
    public static final int TAILS=25;
    public static final int List=44;
    public static final int SColon=89;
    public static final int OBracket=85;
    public static final int NonZeroDigit=105;
    public static final int BLOCK=4;
    public static final int MAP=26;
    public static final int Not=74;
    public static final int UNARY_MIN=17;
    public static final int ASSIGNMENT=7;
    public static final int Infinity=60;
    public static final int Comma=91;
    public static final int Integer=55;
    public static final int Pow=73;
    public static final int LTEquals=72;

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
    // grammar/PlazmaScript.g:70:1: parse : block EOF -> block ;
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
            // grammar/PlazmaScript.g:71:3: ( block EOF -> block )
            // grammar/PlazmaScript.g:71:6: block EOF
            {
            pushFollow(FOLLOW_block_in_parse215);
            block1=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block1.getTree());
            EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_parse217); if (state.failed) return retval; 
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
            // 71:16: -> block
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
    // grammar/PlazmaScript.g:74:1: block : ( statement | functionDecl )* ( Return expression ';' )? -> ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) ) ;
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
            // grammar/PlazmaScript.g:75:3: ( ( statement | functionDecl )* ( Return expression ';' )? -> ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) ) )
            // grammar/PlazmaScript.g:75:6: ( statement | functionDecl )* ( Return expression ';' )?
            {
            // grammar/PlazmaScript.g:75:6: ( statement | functionDecl )*
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
            	    // grammar/PlazmaScript.g:75:7: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_block236);
            	    statement3=statement();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_statement.add(statement3.getTree());

            	    }
            	    break;
            	case 2 :
            	    // grammar/PlazmaScript.g:75:19: functionDecl
            	    {
            	    pushFollow(FOLLOW_functionDecl_in_block240);
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

            // grammar/PlazmaScript.g:75:34: ( Return expression ';' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==Return) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // grammar/PlazmaScript.g:75:35: Return expression ';'
                    {
                    Return5=(Token)match(input,Return,FOLLOW_Return_in_block245); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Return.add(Return5);

                    pushFollow(FOLLOW_expression_in_block247);
                    expression6=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression6.getTree());
                    char_literal7=(Token)match(input,SColon,FOLLOW_SColon_in_block249); if (state.failed) return retval; 
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
            // 76:6: -> ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) )
            {
                // grammar/PlazmaScript.g:76:9: ^( BLOCK ^( STATEMENTS ( statement )* ) ^( RETURN ( expression )? ) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BLOCK, "BLOCK"), root_1);

                // grammar/PlazmaScript.g:76:17: ^( STATEMENTS ( statement )* )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(STATEMENTS, "STATEMENTS"), root_2);

                // grammar/PlazmaScript.g:76:30: ( statement )*
                while ( stream_statement.hasNext() ) {
                    adaptor.addChild(root_2, stream_statement.nextTree());

                }
                stream_statement.reset();

                adaptor.addChild(root_1, root_2);
                }
                // grammar/PlazmaScript.g:76:42: ^( RETURN ( expression )? )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(RETURN, "RETURN"), root_2);

                // grammar/PlazmaScript.g:76:51: ( expression )?
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
    // grammar/PlazmaScript.g:79:1: statement : ( assignment ';' -> assignment | functionCall ';' -> functionCall | lookup ';' -> lookup | ifStatement | forStatement | whileStatement | Break ';' -> Break | Continue ';' -> Continue );
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
            // grammar/PlazmaScript.g:80:3: ( assignment ';' -> assignment | functionCall ';' -> functionCall | lookup ';' -> lookup | ifStatement | forStatement | whileStatement | Break ';' -> Break | Continue ';' -> Continue )
            int alt3=8;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // grammar/PlazmaScript.g:80:6: assignment ';'
                    {
                    pushFollow(FOLLOW_assignment_in_statement291);
                    assignment8=assignment();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_assignment.add(assignment8.getTree());
                    char_literal9=(Token)match(input,SColon,FOLLOW_SColon_in_statement293); if (state.failed) return retval; 
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
                    // 80:23: -> assignment
                    {
                        adaptor.addChild(root_0, stream_assignment.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:81:6: functionCall ';'
                    {
                    pushFollow(FOLLOW_functionCall_in_statement306);
                    functionCall10=functionCall();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_functionCall.add(functionCall10.getTree());
                    char_literal11=(Token)match(input,SColon,FOLLOW_SColon_in_statement308); if (state.failed) return retval; 
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
                    // 81:23: -> functionCall
                    {
                        adaptor.addChild(root_0, stream_functionCall.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // grammar/PlazmaScript.g:82:6: lookup ';'
                    {
                    pushFollow(FOLLOW_lookup_in_statement319);
                    lookup12=lookup();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_lookup.add(lookup12.getTree());
                    char_literal13=(Token)match(input,SColon,FOLLOW_SColon_in_statement321); if (state.failed) return retval; 
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
                    // 82:17: -> lookup
                    {
                        adaptor.addChild(root_0, stream_lookup.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 4 :
                    // grammar/PlazmaScript.g:83:6: ifStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_ifStatement_in_statement365);
                    ifStatement14=ifStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ifStatement14.getTree());

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScript.g:84:6: forStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_forStatement_in_statement372);
                    forStatement15=forStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, forStatement15.getTree());

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScript.g:85:6: whileStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_whileStatement_in_statement379);
                    whileStatement16=whileStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, whileStatement16.getTree());

                    }
                    break;
                case 7 :
                    // grammar/PlazmaScript.g:86:6: Break ';'
                    {
                    Break17=(Token)match(input,Break,FOLLOW_Break_in_statement386); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Break.add(Break17);

                    char_literal18=(Token)match(input,SColon,FOLLOW_SColon_in_statement388); if (state.failed) return retval; 
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
                    // 86:16: -> Break
                    {
                        adaptor.addChild(root_0, stream_Break.nextNode());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 8 :
                    // grammar/PlazmaScript.g:87:6: Continue ';'
                    {
                    Continue19=(Token)match(input,Continue,FOLLOW_Continue_in_statement399); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Continue.add(Continue19);

                    char_literal20=(Token)match(input,SColon,FOLLOW_SColon_in_statement401); if (state.failed) return retval; 
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
                    // 87:19: -> Continue
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
    // grammar/PlazmaScript.g:91:1: assignment : ( ( variableDef )? Identifier ( indexes )? '=' expression -> ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression ) | ( variableDef )? anyIdentifier ( indexes )? '=' expression -> ^( ASSIGNMENT ( variableDef )? ( indexes )? expression ) );
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
            // grammar/PlazmaScript.g:92:3: ( ( variableDef )? Identifier ( indexes )? '=' expression -> ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression ) | ( variableDef )? anyIdentifier ( indexes )? '=' expression -> ^( ASSIGNMENT ( variableDef )? ( indexes )? expression ) )
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
                    // grammar/PlazmaScript.g:92:6: ( variableDef )? Identifier ( indexes )? '=' expression
                    {
                    // grammar/PlazmaScript.g:92:6: ( variableDef )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==Var) ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: variableDef
                            {
                            pushFollow(FOLLOW_variableDef_in_assignment422);
                            variableDef21=variableDef();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_variableDef.add(variableDef21.getTree());

                            }
                            break;

                    }

                    Identifier22=(Token)match(input,Identifier,FOLLOW_Identifier_in_assignment425); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier22);

                    // grammar/PlazmaScript.g:92:30: ( indexes )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==OBracket||LA5_0==106) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_assignment427);
                            indexes23=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes23.getTree());

                            }
                            break;

                    }

                    char_literal24=(Token)match(input,Assign,FOLLOW_Assign_in_assignment430); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Assign.add(char_literal24);

                    pushFollow(FOLLOW_expression_in_assignment432);
                    expression25=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression25.getTree());


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
                    // 92:54: -> ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression )
                    {
                        // grammar/PlazmaScript.g:92:57: ^( ASSIGNMENT ( variableDef )? Identifier ( indexes )? expression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ASSIGNMENT, "ASSIGNMENT"), root_1);

                        // grammar/PlazmaScript.g:92:70: ( variableDef )?
                        if ( stream_variableDef.hasNext() ) {
                            adaptor.addChild(root_1, stream_variableDef.nextTree());

                        }
                        stream_variableDef.reset();
                        adaptor.addChild(root_1, stream_Identifier.nextNode());
                        // grammar/PlazmaScript.g:92:94: ( indexes )?
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
                    // grammar/PlazmaScript.g:93:6: ( variableDef )? anyIdentifier ( indexes )? '=' expression
                    {
                    // grammar/PlazmaScript.g:93:6: ( variableDef )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==Var) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: variableDef
                            {
                            pushFollow(FOLLOW_variableDef_in_assignment455);
                            variableDef26=variableDef();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_variableDef.add(variableDef26.getTree());

                            }
                            break;

                    }

                    pushFollow(FOLLOW_anyIdentifier_in_assignment458);
                    anyIdentifier27=anyIdentifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_anyIdentifier.add(anyIdentifier27.getTree());
                    // grammar/PlazmaScript.g:93:33: ( indexes )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==OBracket||LA7_0==106) ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_assignment460);
                            indexes28=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes28.getTree());

                            }
                            break;

                    }

                    char_literal29=(Token)match(input,Assign,FOLLOW_Assign_in_assignment463); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Assign.add(char_literal29);

                    pushFollow(FOLLOW_expression_in_assignment465);
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
                    // 93:57: -> ^( ASSIGNMENT ( variableDef )? ( indexes )? expression )
                    {
                        // grammar/PlazmaScript.g:93:60: ^( ASSIGNMENT ( variableDef )? ( indexes )? expression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ASSIGNMENT, "ASSIGNMENT"), root_1);

                        // grammar/PlazmaScript.g:93:73: ( variableDef )?
                        if ( stream_variableDef.hasNext() ) {
                            adaptor.addChild(root_1, stream_variableDef.nextTree());

                        }
                        stream_variableDef.reset();
                        adaptor.addChild(root_1, new CommonTree(new CommonToken(Identifier, (anyIdentifier27!=null?input.toString(anyIdentifier27.start,anyIdentifier27.stop):null))));
                        // grammar/PlazmaScript.g:93:153: ( indexes )?
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
    // grammar/PlazmaScript.g:96:1: functionCall : ( Identifier '(' ( exprList )? ')' -> ^( FUNC_CALL Identifier ( exprList )? ) | Println '(' ( expression )? ')' -> ^( FUNC_CALL Println ( expression )? ) | Print '(' expression ')' -> ^( FUNC_CALL Print expression ) | Assert '(' expression ')' -> ^( FUNC_CALL Assert expression ) | Date '(' ( exprList )? ')' -> ^( FUNC_CALL Date ( exprList )? ) | DateTime '(' ( exprList )? ')' -> ^( FUNC_CALL DateTime ( exprList )? ) | Time '(' ( exprList )? ')' -> ^( FUNC_CALL Time ( exprList )? ) | Duration '(' ( expression )? ')' -> ^( FUNC_CALL Duration ( expression )? ) | Period '(' ( exprList )? ')' -> ^( FUNC_CALL Period ( exprList )? ) | List '(' ( exprList )? ')' -> ^( FUNC_CALL List ( exprList )? ) | Set '(' ( exprList )? ')' -> ^( FUNC_CALL Set ( exprList )? ) );
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
        Token DateTime51=null;
        Token char_literal52=null;
        Token char_literal54=null;
        Token Time55=null;
        Token char_literal56=null;
        Token char_literal58=null;
        Token Duration59=null;
        Token char_literal60=null;
        Token char_literal62=null;
        Token Period63=null;
        Token char_literal64=null;
        Token char_literal66=null;
        Token List67=null;
        Token char_literal68=null;
        Token char_literal70=null;
        Token Set71=null;
        Token char_literal72=null;
        Token char_literal74=null;
        PlazmaScriptParser.exprList_return exprList33 = null;

        PlazmaScriptParser.expression_return expression37 = null;

        PlazmaScriptParser.expression_return expression41 = null;

        PlazmaScriptParser.expression_return expression45 = null;

        PlazmaScriptParser.exprList_return exprList49 = null;

        PlazmaScriptParser.exprList_return exprList53 = null;

        PlazmaScriptParser.exprList_return exprList57 = null;

        PlazmaScriptParser.expression_return expression61 = null;

        PlazmaScriptParser.exprList_return exprList65 = null;

        PlazmaScriptParser.exprList_return exprList69 = null;

        PlazmaScriptParser.exprList_return exprList73 = null;


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
        Object DateTime51_tree=null;
        Object char_literal52_tree=null;
        Object char_literal54_tree=null;
        Object Time55_tree=null;
        Object char_literal56_tree=null;
        Object char_literal58_tree=null;
        Object Duration59_tree=null;
        Object char_literal60_tree=null;
        Object char_literal62_tree=null;
        Object Period63_tree=null;
        Object char_literal64_tree=null;
        Object char_literal66_tree=null;
        Object List67_tree=null;
        Object char_literal68_tree=null;
        Object char_literal70_tree=null;
        Object Set71_tree=null;
        Object char_literal72_tree=null;
        Object char_literal74_tree=null;
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
            // grammar/PlazmaScript.g:97:3: ( Identifier '(' ( exprList )? ')' -> ^( FUNC_CALL Identifier ( exprList )? ) | Println '(' ( expression )? ')' -> ^( FUNC_CALL Println ( expression )? ) | Print '(' expression ')' -> ^( FUNC_CALL Print expression ) | Assert '(' expression ')' -> ^( FUNC_CALL Assert expression ) | Date '(' ( exprList )? ')' -> ^( FUNC_CALL Date ( exprList )? ) | DateTime '(' ( exprList )? ')' -> ^( FUNC_CALL DateTime ( exprList )? ) | Time '(' ( exprList )? ')' -> ^( FUNC_CALL Time ( exprList )? ) | Duration '(' ( expression )? ')' -> ^( FUNC_CALL Duration ( expression )? ) | Period '(' ( exprList )? ')' -> ^( FUNC_CALL Period ( exprList )? ) | List '(' ( exprList )? ')' -> ^( FUNC_CALL List ( exprList )? ) | Set '(' ( exprList )? ')' -> ^( FUNC_CALL Set ( exprList )? ) )
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
                    // grammar/PlazmaScript.g:97:6: Identifier '(' ( exprList )? ')'
                    {
                    Identifier31=(Token)match(input,Identifier,FOLLOW_Identifier_in_functionCall495); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier31);

                    char_literal32=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall497); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal32);

                    // grammar/PlazmaScript.g:97:21: ( exprList )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( ((LA9_0>=Identifier && LA9_0<=Set)||(LA9_0>=Integer && LA9_0<=String)||(LA9_0>=Not && LA9_0<=NotWord)||(LA9_0>=Add && LA9_0<=Subtract)||LA9_0==OBrace||LA9_0==OBracket||LA9_0==OParen||LA9_0==ContextIdentifier) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall499);
                            exprList33=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList33.getTree());

                            }
                            break;

                    }

                    char_literal34=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall502); if (state.failed) return retval; 
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
                    // 97:35: -> ^( FUNC_CALL Identifier ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:97:38: ^( FUNC_CALL Identifier ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Identifier.nextNode());
                        // grammar/PlazmaScript.g:97:61: ( exprList )?
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
                    // grammar/PlazmaScript.g:98:6: Println '(' ( expression )? ')'
                    {
                    Println35=(Token)match(input,Println,FOLLOW_Println_in_functionCall520); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Println.add(Println35);

                    char_literal36=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall522); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal36);

                    // grammar/PlazmaScript.g:98:18: ( expression )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( ((LA10_0>=Identifier && LA10_0<=Set)||(LA10_0>=Integer && LA10_0<=String)||(LA10_0>=Not && LA10_0<=NotWord)||(LA10_0>=Add && LA10_0<=Subtract)||LA10_0==OBrace||LA10_0==OBracket||LA10_0==OParen||LA10_0==ContextIdentifier) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: expression
                            {
                            pushFollow(FOLLOW_expression_in_functionCall524);
                            expression37=expression();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_expression.add(expression37.getTree());

                            }
                            break;

                    }

                    char_literal38=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall527); if (state.failed) return retval; 
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
                    // 98:35: -> ^( FUNC_CALL Println ( expression )? )
                    {
                        // grammar/PlazmaScript.g:98:38: ^( FUNC_CALL Println ( expression )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Println.nextNode());
                        // grammar/PlazmaScript.g:98:58: ( expression )?
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
                    // grammar/PlazmaScript.g:99:6: Print '(' expression ')'
                    {
                    Print39=(Token)match(input,Print,FOLLOW_Print_in_functionCall546); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Print.add(Print39);

                    char_literal40=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall548); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal40);

                    pushFollow(FOLLOW_expression_in_functionCall550);
                    expression41=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression41.getTree());
                    char_literal42=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall552); if (state.failed) return retval; 
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
                    // 99:35: -> ^( FUNC_CALL Print expression )
                    {
                        // grammar/PlazmaScript.g:99:38: ^( FUNC_CALL Print expression )
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
                    // grammar/PlazmaScript.g:100:6: Assert '(' expression ')'
                    {
                    Assert43=(Token)match(input,Assert,FOLLOW_Assert_in_functionCall573); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Assert.add(Assert43);

                    char_literal44=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall575); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal44);

                    pushFollow(FOLLOW_expression_in_functionCall577);
                    expression45=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression45.getTree());
                    char_literal46=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall579); if (state.failed) return retval; 
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
                    // 100:35: -> ^( FUNC_CALL Assert expression )
                    {
                        // grammar/PlazmaScript.g:100:38: ^( FUNC_CALL Assert expression )
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
                    // grammar/PlazmaScript.g:101:6: Date '(' ( exprList )? ')'
                    {
                    Date47=(Token)match(input,Date,FOLLOW_Date_in_functionCall599); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Date.add(Date47);

                    char_literal48=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall601); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal48);

                    // grammar/PlazmaScript.g:101:15: ( exprList )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( ((LA11_0>=Identifier && LA11_0<=Set)||(LA11_0>=Integer && LA11_0<=String)||(LA11_0>=Not && LA11_0<=NotWord)||(LA11_0>=Add && LA11_0<=Subtract)||LA11_0==OBrace||LA11_0==OBracket||LA11_0==OParen||LA11_0==ContextIdentifier) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall603);
                            exprList49=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList49.getTree());

                            }
                            break;

                    }

                    char_literal50=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall606); if (state.failed) return retval; 
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
                    // 101:34: -> ^( FUNC_CALL Date ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:101:37: ^( FUNC_CALL Date ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Date.nextNode());
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
                case 6 :
                    // grammar/PlazmaScript.g:102:6: DateTime '(' ( exprList )? ')'
                    {
                    DateTime51=(Token)match(input,DateTime,FOLLOW_DateTime_in_functionCall629); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DateTime.add(DateTime51);

                    char_literal52=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall631); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal52);

                    // grammar/PlazmaScript.g:102:19: ( exprList )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( ((LA12_0>=Identifier && LA12_0<=Set)||(LA12_0>=Integer && LA12_0<=String)||(LA12_0>=Not && LA12_0<=NotWord)||(LA12_0>=Add && LA12_0<=Subtract)||LA12_0==OBrace||LA12_0==OBracket||LA12_0==OParen||LA12_0==ContextIdentifier) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall633);
                            exprList53=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList53.getTree());

                            }
                            break;

                    }

                    char_literal54=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall636); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal54);



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
                    // 102:38: -> ^( FUNC_CALL DateTime ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:102:41: ^( FUNC_CALL DateTime ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_DateTime.nextNode());
                        // grammar/PlazmaScript.g:102:62: ( exprList )?
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
                    // grammar/PlazmaScript.g:103:6: Time '(' ( exprList )? ')'
                    {
                    Time55=(Token)match(input,Time,FOLLOW_Time_in_functionCall659); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Time.add(Time55);

                    char_literal56=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall661); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal56);

                    // grammar/PlazmaScript.g:103:15: ( exprList )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( ((LA13_0>=Identifier && LA13_0<=Set)||(LA13_0>=Integer && LA13_0<=String)||(LA13_0>=Not && LA13_0<=NotWord)||(LA13_0>=Add && LA13_0<=Subtract)||LA13_0==OBrace||LA13_0==OBracket||LA13_0==OParen||LA13_0==ContextIdentifier) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall663);
                            exprList57=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList57.getTree());

                            }
                            break;

                    }

                    char_literal58=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall666); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal58);



                    // AST REWRITE
                    // elements: exprList, Time
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 103:34: -> ^( FUNC_CALL Time ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:103:37: ^( FUNC_CALL Time ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Time.nextNode());
                        // grammar/PlazmaScript.g:103:54: ( exprList )?
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
                    // grammar/PlazmaScript.g:104:6: Duration '(' ( expression )? ')'
                    {
                    Duration59=(Token)match(input,Duration,FOLLOW_Duration_in_functionCall689); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Duration.add(Duration59);

                    char_literal60=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall691); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal60);

                    // grammar/PlazmaScript.g:104:19: ( expression )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( ((LA14_0>=Identifier && LA14_0<=Set)||(LA14_0>=Integer && LA14_0<=String)||(LA14_0>=Not && LA14_0<=NotWord)||(LA14_0>=Add && LA14_0<=Subtract)||LA14_0==OBrace||LA14_0==OBracket||LA14_0==OParen||LA14_0==ContextIdentifier) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: expression
                            {
                            pushFollow(FOLLOW_expression_in_functionCall693);
                            expression61=expression();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_expression.add(expression61.getTree());

                            }
                            break;

                    }

                    char_literal62=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall696); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal62);



                    // AST REWRITE
                    // elements: Duration, expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 104:36: -> ^( FUNC_CALL Duration ( expression )? )
                    {
                        // grammar/PlazmaScript.g:104:39: ^( FUNC_CALL Duration ( expression )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Duration.nextNode());
                        // grammar/PlazmaScript.g:104:60: ( expression )?
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
                    // grammar/PlazmaScript.g:105:6: Period '(' ( exprList )? ')'
                    {
                    Period63=(Token)match(input,Period,FOLLOW_Period_in_functionCall715); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Period.add(Period63);

                    char_literal64=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall717); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal64);

                    // grammar/PlazmaScript.g:105:17: ( exprList )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( ((LA15_0>=Identifier && LA15_0<=Set)||(LA15_0>=Integer && LA15_0<=String)||(LA15_0>=Not && LA15_0<=NotWord)||(LA15_0>=Add && LA15_0<=Subtract)||LA15_0==OBrace||LA15_0==OBracket||LA15_0==OParen||LA15_0==ContextIdentifier) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall719);
                            exprList65=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList65.getTree());

                            }
                            break;

                    }

                    char_literal66=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall722); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal66);



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
                    // 105:32: -> ^( FUNC_CALL Period ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:105:35: ^( FUNC_CALL Period ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Period.nextNode());
                        // grammar/PlazmaScript.g:105:54: ( exprList )?
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
                    // grammar/PlazmaScript.g:106:6: List '(' ( exprList )? ')'
                    {
                    List67=(Token)match(input,List,FOLLOW_List_in_functionCall753); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_List.add(List67);

                    char_literal68=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall755); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal68);

                    // grammar/PlazmaScript.g:106:15: ( exprList )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( ((LA16_0>=Identifier && LA16_0<=Set)||(LA16_0>=Integer && LA16_0<=String)||(LA16_0>=Not && LA16_0<=NotWord)||(LA16_0>=Add && LA16_0<=Subtract)||LA16_0==OBrace||LA16_0==OBracket||LA16_0==OParen||LA16_0==ContextIdentifier) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall757);
                            exprList69=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList69.getTree());

                            }
                            break;

                    }

                    char_literal70=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall760); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal70);



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
                    // 106:34: -> ^( FUNC_CALL List ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:106:37: ^( FUNC_CALL List ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_List.nextNode());
                        // grammar/PlazmaScript.g:106:54: ( exprList )?
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
                    // grammar/PlazmaScript.g:107:6: Set '(' ( exprList )? ')'
                    {
                    Set71=(Token)match(input,Set,FOLLOW_Set_in_functionCall783); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Set.add(Set71);

                    char_literal72=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall785); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal72);

                    // grammar/PlazmaScript.g:107:14: ( exprList )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( ((LA17_0>=Identifier && LA17_0<=Set)||(LA17_0>=Integer && LA17_0<=String)||(LA17_0>=Not && LA17_0<=NotWord)||(LA17_0>=Add && LA17_0<=Subtract)||LA17_0==OBrace||LA17_0==OBracket||LA17_0==OParen||LA17_0==ContextIdentifier) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall787);
                            exprList73=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList73.getTree());

                            }
                            break;

                    }

                    char_literal74=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall790); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal74);



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
                    // 107:33: -> ^( FUNC_CALL Set ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:107:36: ^( FUNC_CALL Set ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Set.nextNode());
                        // grammar/PlazmaScript.g:107:52: ( exprList )?
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
    // grammar/PlazmaScript.g:114:1: ifStatement : ifStat ( elseIfStat )* ( elseStat )? -> ^( IF ifStat ( elseIfStat )* ( elseStat )? ) ;
    public final PlazmaScriptParser.ifStatement_return ifStatement() throws RecognitionException {
        PlazmaScriptParser.ifStatement_return retval = new PlazmaScriptParser.ifStatement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        PlazmaScriptParser.ifStat_return ifStat75 = null;

        PlazmaScriptParser.elseIfStat_return elseIfStat76 = null;

        PlazmaScriptParser.elseStat_return elseStat77 = null;


        RewriteRuleSubtreeStream stream_elseIfStat=new RewriteRuleSubtreeStream(adaptor,"rule elseIfStat");
        RewriteRuleSubtreeStream stream_ifStat=new RewriteRuleSubtreeStream(adaptor,"rule ifStat");
        RewriteRuleSubtreeStream stream_elseStat=new RewriteRuleSubtreeStream(adaptor,"rule elseStat");
        try {
            // grammar/PlazmaScript.g:115:3: ( ifStat ( elseIfStat )* ( elseStat )? -> ^( IF ifStat ( elseIfStat )* ( elseStat )? ) )
            // grammar/PlazmaScript.g:115:6: ifStat ( elseIfStat )* ( elseStat )?
            {
            pushFollow(FOLLOW_ifStat_in_ifStatement828);
            ifStat75=ifStat();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_ifStat.add(ifStat75.getTree());
            // grammar/PlazmaScript.g:115:13: ( elseIfStat )*
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
            	    pushFollow(FOLLOW_elseIfStat_in_ifStatement830);
            	    elseIfStat76=elseIfStat();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_elseIfStat.add(elseIfStat76.getTree());

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            // grammar/PlazmaScript.g:115:25: ( elseStat )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==Else) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // grammar/PlazmaScript.g:0:0: elseStat
                    {
                    pushFollow(FOLLOW_elseStat_in_ifStatement833);
                    elseStat77=elseStat();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_elseStat.add(elseStat77.getTree());

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
            // 115:35: -> ^( IF ifStat ( elseIfStat )* ( elseStat )? )
            {
                // grammar/PlazmaScript.g:115:38: ^( IF ifStat ( elseIfStat )* ( elseStat )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(IF, "IF"), root_1);

                adaptor.addChild(root_1, stream_ifStat.nextTree());
                // grammar/PlazmaScript.g:115:50: ( elseIfStat )*
                while ( stream_elseIfStat.hasNext() ) {
                    adaptor.addChild(root_1, stream_elseIfStat.nextTree());

                }
                stream_elseIfStat.reset();
                // grammar/PlazmaScript.g:115:62: ( elseStat )?
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
    // grammar/PlazmaScript.g:118:1: ifStat : If '(' expression ')' '{' block '}' -> ^( EXP expression block ) ;
    public final PlazmaScriptParser.ifStat_return ifStat() throws RecognitionException {
        PlazmaScriptParser.ifStat_return retval = new PlazmaScriptParser.ifStat_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token If78=null;
        Token char_literal79=null;
        Token char_literal81=null;
        Token char_literal82=null;
        Token char_literal84=null;
        PlazmaScriptParser.expression_return expression80 = null;

        PlazmaScriptParser.block_return block83 = null;


        Object If78_tree=null;
        Object char_literal79_tree=null;
        Object char_literal81_tree=null;
        Object char_literal82_tree=null;
        Object char_literal84_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_If=new RewriteRuleTokenStream(adaptor,"token If");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:119:3: ( If '(' expression ')' '{' block '}' -> ^( EXP expression block ) )
            // grammar/PlazmaScript.g:119:6: If '(' expression ')' '{' block '}'
            {
            If78=(Token)match(input,If,FOLLOW_If_in_ifStat862); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_If.add(If78);

            char_literal79=(Token)match(input,OParen,FOLLOW_OParen_in_ifStat864); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal79);

            pushFollow(FOLLOW_expression_in_ifStat866);
            expression80=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression80.getTree());
            char_literal81=(Token)match(input,CParen,FOLLOW_CParen_in_ifStat868); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal81);

            char_literal82=(Token)match(input,OBrace,FOLLOW_OBrace_in_ifStat870); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal82);

            pushFollow(FOLLOW_block_in_ifStat872);
            block83=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block83.getTree());
            char_literal84=(Token)match(input,CBrace,FOLLOW_CBrace_in_ifStat874); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal84);



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
            // 119:42: -> ^( EXP expression block )
            {
                // grammar/PlazmaScript.g:119:45: ^( EXP expression block )
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
    // grammar/PlazmaScript.g:122:1: elseIfStat : Else If '(' expression ')' '{' block '}' -> ^( EXP expression block ) ;
    public final PlazmaScriptParser.elseIfStat_return elseIfStat() throws RecognitionException {
        PlazmaScriptParser.elseIfStat_return retval = new PlazmaScriptParser.elseIfStat_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Else85=null;
        Token If86=null;
        Token char_literal87=null;
        Token char_literal89=null;
        Token char_literal90=null;
        Token char_literal92=null;
        PlazmaScriptParser.expression_return expression88 = null;

        PlazmaScriptParser.block_return block91 = null;


        Object Else85_tree=null;
        Object If86_tree=null;
        Object char_literal87_tree=null;
        Object char_literal89_tree=null;
        Object char_literal90_tree=null;
        Object char_literal92_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_Else=new RewriteRuleTokenStream(adaptor,"token Else");
        RewriteRuleTokenStream stream_If=new RewriteRuleTokenStream(adaptor,"token If");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:123:3: ( Else If '(' expression ')' '{' block '}' -> ^( EXP expression block ) )
            // grammar/PlazmaScript.g:123:6: Else If '(' expression ')' '{' block '}'
            {
            Else85=(Token)match(input,Else,FOLLOW_Else_in_elseIfStat898); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Else.add(Else85);

            If86=(Token)match(input,If,FOLLOW_If_in_elseIfStat900); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_If.add(If86);

            char_literal87=(Token)match(input,OParen,FOLLOW_OParen_in_elseIfStat902); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal87);

            pushFollow(FOLLOW_expression_in_elseIfStat904);
            expression88=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression88.getTree());
            char_literal89=(Token)match(input,CParen,FOLLOW_CParen_in_elseIfStat906); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal89);

            char_literal90=(Token)match(input,OBrace,FOLLOW_OBrace_in_elseIfStat908); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal90);

            pushFollow(FOLLOW_block_in_elseIfStat910);
            block91=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block91.getTree());
            char_literal92=(Token)match(input,CBrace,FOLLOW_CBrace_in_elseIfStat912); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal92);



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
            // 123:47: -> ^( EXP expression block )
            {
                // grammar/PlazmaScript.g:123:50: ^( EXP expression block )
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
    // grammar/PlazmaScript.g:126:1: elseStat : Else '{' block '}' -> ^( EXP block ) ;
    public final PlazmaScriptParser.elseStat_return elseStat() throws RecognitionException {
        PlazmaScriptParser.elseStat_return retval = new PlazmaScriptParser.elseStat_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Else93=null;
        Token char_literal94=null;
        Token char_literal96=null;
        PlazmaScriptParser.block_return block95 = null;


        Object Else93_tree=null;
        Object char_literal94_tree=null;
        Object char_literal96_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_Else=new RewriteRuleTokenStream(adaptor,"token Else");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:127:3: ( Else '{' block '}' -> ^( EXP block ) )
            // grammar/PlazmaScript.g:127:6: Else '{' block '}'
            {
            Else93=(Token)match(input,Else,FOLLOW_Else_in_elseStat936); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Else.add(Else93);

            char_literal94=(Token)match(input,OBrace,FOLLOW_OBrace_in_elseStat938); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal94);

            pushFollow(FOLLOW_block_in_elseStat940);
            block95=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block95.getTree());
            char_literal96=(Token)match(input,CBrace,FOLLOW_CBrace_in_elseStat942); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal96);



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
            // 127:25: -> ^( EXP block )
            {
                // grammar/PlazmaScript.g:127:28: ^( EXP block )
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
    // grammar/PlazmaScript.g:130:1: variableDef : Var ;
    public final PlazmaScriptParser.variableDef_return variableDef() throws RecognitionException {
        PlazmaScriptParser.variableDef_return retval = new PlazmaScriptParser.variableDef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Var97=null;

        Object Var97_tree=null;

        try {
            // grammar/PlazmaScript.g:131:2: ( Var )
            // grammar/PlazmaScript.g:131:4: Var
            {
            root_0 = (Object)adaptor.nil();

            Var97=(Token)match(input,Var,FOLLOW_Var_in_variableDef962); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Var97_tree = (Object)adaptor.create(Var97);
            adaptor.addChild(root_0, Var97_tree);
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
    // grammar/PlazmaScript.g:148:1: functionDecl : Def Identifier '(' ( idList )? ')' '{' block '}' ;
    public final PlazmaScriptParser.functionDecl_return functionDecl() throws RecognitionException {
        PlazmaScriptParser.functionDecl_return retval = new PlazmaScriptParser.functionDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Def98=null;
        Token Identifier99=null;
        Token char_literal100=null;
        Token char_literal102=null;
        Token char_literal103=null;
        Token char_literal105=null;
        PlazmaScriptParser.idList_return idList101 = null;

        PlazmaScriptParser.block_return block104 = null;


        Object Def98_tree=null;
        Object Identifier99_tree=null;
        Object char_literal100_tree=null;
        Object char_literal102_tree=null;
        Object char_literal103_tree=null;
        Object char_literal105_tree=null;

        try {
            // grammar/PlazmaScript.g:149:3: ( Def Identifier '(' ( idList )? ')' '{' block '}' )
            // grammar/PlazmaScript.g:149:6: Def Identifier '(' ( idList )? ')' '{' block '}'
            {
            root_0 = (Object)adaptor.nil();

            Def98=(Token)match(input,Def,FOLLOW_Def_in_functionDecl994); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Def98_tree = (Object)adaptor.create(Def98);
            adaptor.addChild(root_0, Def98_tree);
            }
            Identifier99=(Token)match(input,Identifier,FOLLOW_Identifier_in_functionDecl996); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier99_tree = (Object)adaptor.create(Identifier99);
            adaptor.addChild(root_0, Identifier99_tree);
            }
            char_literal100=(Token)match(input,OParen,FOLLOW_OParen_in_functionDecl998); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal100_tree = (Object)adaptor.create(char_literal100);
            adaptor.addChild(root_0, char_literal100_tree);
            }
            // grammar/PlazmaScript.g:149:25: ( idList )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==Identifier) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // grammar/PlazmaScript.g:0:0: idList
                    {
                    pushFollow(FOLLOW_idList_in_functionDecl1000);
                    idList101=idList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, idList101.getTree());

                    }
                    break;

            }

            char_literal102=(Token)match(input,CParen,FOLLOW_CParen_in_functionDecl1003); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal102_tree = (Object)adaptor.create(char_literal102);
            adaptor.addChild(root_0, char_literal102_tree);
            }
            char_literal103=(Token)match(input,OBrace,FOLLOW_OBrace_in_functionDecl1005); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal103_tree = (Object)adaptor.create(char_literal103);
            adaptor.addChild(root_0, char_literal103_tree);
            }
            pushFollow(FOLLOW_block_in_functionDecl1007);
            block104=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, block104.getTree());
            char_literal105=(Token)match(input,CBrace,FOLLOW_CBrace_in_functionDecl1009); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal105_tree = (Object)adaptor.create(char_literal105);
            adaptor.addChild(root_0, char_literal105_tree);
            }
            if ( state.backtracking==0 ) {
              defineFunction((Identifier99!=null?Identifier99.getText():null), (idList101!=null?((Object)idList101.tree):null), (block104!=null?((Object)block104.tree):null));
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
    // grammar/PlazmaScript.g:158:1: forStatement : For '(' Identifier 'in' expression ')' '{' block '}' -> ^( For Identifier expression block ) ;
    public final PlazmaScriptParser.forStatement_return forStatement() throws RecognitionException {
        PlazmaScriptParser.forStatement_return retval = new PlazmaScriptParser.forStatement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token For106=null;
        Token char_literal107=null;
        Token Identifier108=null;
        Token string_literal109=null;
        Token char_literal111=null;
        Token char_literal112=null;
        Token char_literal114=null;
        PlazmaScriptParser.expression_return expression110 = null;

        PlazmaScriptParser.block_return block113 = null;


        Object For106_tree=null;
        Object char_literal107_tree=null;
        Object Identifier108_tree=null;
        Object string_literal109_tree=null;
        Object char_literal111_tree=null;
        Object char_literal112_tree=null;
        Object char_literal114_tree=null;
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
            // grammar/PlazmaScript.g:159:3: ( For '(' Identifier 'in' expression ')' '{' block '}' -> ^( For Identifier expression block ) )
            // grammar/PlazmaScript.g:159:6: For '(' Identifier 'in' expression ')' '{' block '}'
            {
            For106=(Token)match(input,For,FOLLOW_For_in_forStatement1038); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_For.add(For106);

            char_literal107=(Token)match(input,OParen,FOLLOW_OParen_in_forStatement1040); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal107);

            Identifier108=(Token)match(input,Identifier,FOLLOW_Identifier_in_forStatement1042); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Identifier.add(Identifier108);

            string_literal109=(Token)match(input,In,FOLLOW_In_in_forStatement1044); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_In.add(string_literal109);

            pushFollow(FOLLOW_expression_in_forStatement1046);
            expression110=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression110.getTree());
            char_literal111=(Token)match(input,CParen,FOLLOW_CParen_in_forStatement1048); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal111);

            char_literal112=(Token)match(input,OBrace,FOLLOW_OBrace_in_forStatement1050); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal112);

            pushFollow(FOLLOW_block_in_forStatement1052);
            block113=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block113.getTree());
            char_literal114=(Token)match(input,CBrace,FOLLOW_CBrace_in_forStatement1054); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal114);



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
            // 160:6: -> ^( For Identifier expression block )
            {
                // grammar/PlazmaScript.g:160:9: ^( For Identifier expression block )
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
    // grammar/PlazmaScript.g:164:1: whileStatement : While '(' expression ')' '{' block '}' -> ^( While expression block ) ;
    public final PlazmaScriptParser.whileStatement_return whileStatement() throws RecognitionException {
        PlazmaScriptParser.whileStatement_return retval = new PlazmaScriptParser.whileStatement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token While115=null;
        Token char_literal116=null;
        Token char_literal118=null;
        Token char_literal119=null;
        Token char_literal121=null;
        PlazmaScriptParser.expression_return expression117 = null;

        PlazmaScriptParser.block_return block120 = null;


        Object While115_tree=null;
        Object char_literal116_tree=null;
        Object char_literal118_tree=null;
        Object char_literal119_tree=null;
        Object char_literal121_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_While=new RewriteRuleTokenStream(adaptor,"token While");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:165:3: ( While '(' expression ')' '{' block '}' -> ^( While expression block ) )
            // grammar/PlazmaScript.g:165:6: While '(' expression ')' '{' block '}'
            {
            While115=(Token)match(input,While,FOLLOW_While_in_whileStatement1089); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_While.add(While115);

            char_literal116=(Token)match(input,OParen,FOLLOW_OParen_in_whileStatement1091); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal116);

            pushFollow(FOLLOW_expression_in_whileStatement1093);
            expression117=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression117.getTree());
            char_literal118=(Token)match(input,CParen,FOLLOW_CParen_in_whileStatement1095); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal118);

            char_literal119=(Token)match(input,OBrace,FOLLOW_OBrace_in_whileStatement1097); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal119);

            pushFollow(FOLLOW_block_in_whileStatement1099);
            block120=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block120.getTree());
            char_literal121=(Token)match(input,CBrace,FOLLOW_CBrace_in_whileStatement1101); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal121);



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
            // 165:45: -> ^( While expression block )
            {
                // grammar/PlazmaScript.g:165:48: ^( While expression block )
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
    // grammar/PlazmaScript.g:168:1: idList : Identifier ( ',' Identifier )* -> ^( ID_LIST ( Identifier )+ ) ;
    public final PlazmaScriptParser.idList_return idList() throws RecognitionException {
        PlazmaScriptParser.idList_return retval = new PlazmaScriptParser.idList_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Identifier122=null;
        Token char_literal123=null;
        Token Identifier124=null;

        Object Identifier122_tree=null;
        Object char_literal123_tree=null;
        Object Identifier124_tree=null;
        RewriteRuleTokenStream stream_Comma=new RewriteRuleTokenStream(adaptor,"token Comma");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");

        try {
            // grammar/PlazmaScript.g:169:3: ( Identifier ( ',' Identifier )* -> ^( ID_LIST ( Identifier )+ ) )
            // grammar/PlazmaScript.g:169:6: Identifier ( ',' Identifier )*
            {
            Identifier122=(Token)match(input,Identifier,FOLLOW_Identifier_in_idList1125); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Identifier.add(Identifier122);

            // grammar/PlazmaScript.g:169:17: ( ',' Identifier )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==Comma) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // grammar/PlazmaScript.g:169:18: ',' Identifier
            	    {
            	    char_literal123=(Token)match(input,Comma,FOLLOW_Comma_in_idList1128); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Comma.add(char_literal123);

            	    Identifier124=(Token)match(input,Identifier,FOLLOW_Identifier_in_idList1130); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Identifier.add(Identifier124);


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
            // 169:35: -> ^( ID_LIST ( Identifier )+ )
            {
                // grammar/PlazmaScript.g:169:38: ^( ID_LIST ( Identifier )+ )
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
    // grammar/PlazmaScript.g:172:1: exprList : expression ( ',' expression )* -> ^( EXP_LIST ( expression )+ ) ;
    public final PlazmaScriptParser.exprList_return exprList() throws RecognitionException {
        PlazmaScriptParser.exprList_return retval = new PlazmaScriptParser.exprList_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal126=null;
        PlazmaScriptParser.expression_return expression125 = null;

        PlazmaScriptParser.expression_return expression127 = null;


        Object char_literal126_tree=null;
        RewriteRuleTokenStream stream_Comma=new RewriteRuleTokenStream(adaptor,"token Comma");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // grammar/PlazmaScript.g:173:3: ( expression ( ',' expression )* -> ^( EXP_LIST ( expression )+ ) )
            // grammar/PlazmaScript.g:173:6: expression ( ',' expression )*
            {
            pushFollow(FOLLOW_expression_in_exprList1155);
            expression125=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression125.getTree());
            // grammar/PlazmaScript.g:173:17: ( ',' expression )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==Comma) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // grammar/PlazmaScript.g:173:18: ',' expression
            	    {
            	    char_literal126=(Token)match(input,Comma,FOLLOW_Comma_in_exprList1158); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Comma.add(char_literal126);

            	    pushFollow(FOLLOW_expression_in_exprList1160);
            	    expression127=expression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_expression.add(expression127.getTree());

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
            // 173:35: -> ^( EXP_LIST ( expression )+ )
            {
                // grammar/PlazmaScript.g:173:38: ^( EXP_LIST ( expression )+ )
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
    // grammar/PlazmaScript.g:176:1: exprPair : ( expression ':' expression ) -> ^( EXP_PAIR expression expression ) ;
    public final PlazmaScriptParser.exprPair_return exprPair() throws RecognitionException {
        PlazmaScriptParser.exprPair_return retval = new PlazmaScriptParser.exprPair_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal129=null;
        PlazmaScriptParser.expression_return expression128 = null;

        PlazmaScriptParser.expression_return expression130 = null;


        Object char_literal129_tree=null;
        RewriteRuleTokenStream stream_Colon=new RewriteRuleTokenStream(adaptor,"token Colon");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // grammar/PlazmaScript.g:177:3: ( ( expression ':' expression ) -> ^( EXP_PAIR expression expression ) )
            // grammar/PlazmaScript.g:177:6: ( expression ':' expression )
            {
            // grammar/PlazmaScript.g:177:6: ( expression ':' expression )
            // grammar/PlazmaScript.g:177:7: expression ':' expression
            {
            pushFollow(FOLLOW_expression_in_exprPair1186);
            expression128=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression128.getTree());
            char_literal129=(Token)match(input,Colon,FOLLOW_Colon_in_exprPair1188); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Colon.add(char_literal129);

            pushFollow(FOLLOW_expression_in_exprPair1190);
            expression130=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression130.getTree());

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
            // 177:34: -> ^( EXP_PAIR expression expression )
            {
                // grammar/PlazmaScript.g:177:37: ^( EXP_PAIR expression expression )
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
    // grammar/PlazmaScript.g:180:1: exprMap : exprPair ( ',' exprPair )* -> ^( EXP_MAP ( exprPair )+ ) ;
    public final PlazmaScriptParser.exprMap_return exprMap() throws RecognitionException {
        PlazmaScriptParser.exprMap_return retval = new PlazmaScriptParser.exprMap_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal132=null;
        PlazmaScriptParser.exprPair_return exprPair131 = null;

        PlazmaScriptParser.exprPair_return exprPair133 = null;


        Object char_literal132_tree=null;
        RewriteRuleTokenStream stream_Comma=new RewriteRuleTokenStream(adaptor,"token Comma");
        RewriteRuleSubtreeStream stream_exprPair=new RewriteRuleSubtreeStream(adaptor,"rule exprPair");
        try {
            // grammar/PlazmaScript.g:181:3: ( exprPair ( ',' exprPair )* -> ^( EXP_MAP ( exprPair )+ ) )
            // grammar/PlazmaScript.g:181:6: exprPair ( ',' exprPair )*
            {
            pushFollow(FOLLOW_exprPair_in_exprMap1215);
            exprPair131=exprPair();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_exprPair.add(exprPair131.getTree());
            // grammar/PlazmaScript.g:181:15: ( ',' exprPair )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==Comma) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // grammar/PlazmaScript.g:181:16: ',' exprPair
            	    {
            	    char_literal132=(Token)match(input,Comma,FOLLOW_Comma_in_exprMap1218); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Comma.add(char_literal132);

            	    pushFollow(FOLLOW_exprPair_in_exprMap1220);
            	    exprPair133=exprPair();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_exprPair.add(exprPair133.getTree());

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
            // 181:31: -> ^( EXP_MAP ( exprPair )+ )
            {
                // grammar/PlazmaScript.g:181:34: ^( EXP_MAP ( exprPair )+ )
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
    // grammar/PlazmaScript.g:185:1: expression : condExpr ;
    public final PlazmaScriptParser.expression_return expression() throws RecognitionException {
        PlazmaScriptParser.expression_return retval = new PlazmaScriptParser.expression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        PlazmaScriptParser.condExpr_return condExpr134 = null;



        try {
            // grammar/PlazmaScript.g:186:3: ( condExpr )
            // grammar/PlazmaScript.g:186:6: condExpr
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_condExpr_in_expression1246);
            condExpr134=condExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, condExpr134.getTree());

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
    // grammar/PlazmaScript.g:189:1: condExpr : ( startExpr -> startExpr ) ( '?' a= expression ':' b= expression -> ^( TERNARY startExpr $a $b) | In expression -> ^( In startExpr expression ) | RangeE expression -> ^( RangeE startExpr expression ) | Range expression -> ^( Range startExpr expression ) )? ;
    public final PlazmaScriptParser.condExpr_return condExpr() throws RecognitionException {
        PlazmaScriptParser.condExpr_return retval = new PlazmaScriptParser.condExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal136=null;
        Token char_literal137=null;
        Token In138=null;
        Token RangeE140=null;
        Token Range142=null;
        PlazmaScriptParser.expression_return a = null;

        PlazmaScriptParser.expression_return b = null;

        PlazmaScriptParser.startExpr_return startExpr135 = null;

        PlazmaScriptParser.expression_return expression139 = null;

        PlazmaScriptParser.expression_return expression141 = null;

        PlazmaScriptParser.expression_return expression143 = null;


        Object char_literal136_tree=null;
        Object char_literal137_tree=null;
        Object In138_tree=null;
        Object RangeE140_tree=null;
        Object Range142_tree=null;
        RewriteRuleTokenStream stream_RangeE=new RewriteRuleTokenStream(adaptor,"token RangeE");
        RewriteRuleTokenStream stream_Range=new RewriteRuleTokenStream(adaptor,"token Range");
        RewriteRuleTokenStream stream_In=new RewriteRuleTokenStream(adaptor,"token In");
        RewriteRuleTokenStream stream_Colon=new RewriteRuleTokenStream(adaptor,"token Colon");
        RewriteRuleTokenStream stream_QMark=new RewriteRuleTokenStream(adaptor,"token QMark");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_startExpr=new RewriteRuleSubtreeStream(adaptor,"rule startExpr");
        try {
            // grammar/PlazmaScript.g:190:3: ( ( startExpr -> startExpr ) ( '?' a= expression ':' b= expression -> ^( TERNARY startExpr $a $b) | In expression -> ^( In startExpr expression ) | RangeE expression -> ^( RangeE startExpr expression ) | Range expression -> ^( Range startExpr expression ) )? )
            // grammar/PlazmaScript.g:190:6: ( startExpr -> startExpr ) ( '?' a= expression ':' b= expression -> ^( TERNARY startExpr $a $b) | In expression -> ^( In startExpr expression ) | RangeE expression -> ^( RangeE startExpr expression ) | Range expression -> ^( Range startExpr expression ) )?
            {
            // grammar/PlazmaScript.g:190:6: ( startExpr -> startExpr )
            // grammar/PlazmaScript.g:190:7: startExpr
            {
            pushFollow(FOLLOW_startExpr_in_condExpr1261);
            startExpr135=startExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_startExpr.add(startExpr135.getTree());


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
            // 190:17: -> startExpr
            {
                adaptor.addChild(root_0, stream_startExpr.nextTree());

            }

            retval.tree = root_0;}
            }

            // grammar/PlazmaScript.g:191:6: ( '?' a= expression ':' b= expression -> ^( TERNARY startExpr $a $b) | In expression -> ^( In startExpr expression ) | RangeE expression -> ^( RangeE startExpr expression ) | Range expression -> ^( Range startExpr expression ) )?
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
                    // grammar/PlazmaScript.g:191:8: '?' a= expression ':' b= expression
                    {
                    char_literal136=(Token)match(input,QMark,FOLLOW_QMark_in_condExpr1276); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_QMark.add(char_literal136);

                    pushFollow(FOLLOW_expression_in_condExpr1280);
                    a=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(a.getTree());
                    char_literal137=(Token)match(input,Colon,FOLLOW_Colon_in_condExpr1282); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Colon.add(char_literal137);

                    pushFollow(FOLLOW_expression_in_condExpr1286);
                    b=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(b.getTree());


                    // AST REWRITE
                    // elements: a, startExpr, b
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
                    // 191:42: -> ^( TERNARY startExpr $a $b)
                    {
                        // grammar/PlazmaScript.g:191:45: ^( TERNARY startExpr $a $b)
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
                    // grammar/PlazmaScript.g:192:8: In expression
                    {
                    In138=(Token)match(input,In,FOLLOW_In_in_condExpr1309); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_In.add(In138);

                    pushFollow(FOLLOW_expression_in_condExpr1311);
                    expression139=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression139.getTree());


                    // AST REWRITE
                    // elements: expression, startExpr, In
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 192:42: -> ^( In startExpr expression )
                    {
                        // grammar/PlazmaScript.g:192:45: ^( In startExpr expression )
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
                    // grammar/PlazmaScript.g:194:8: RangeE expression
                    {
                    RangeE140=(Token)match(input,RangeE,FOLLOW_RangeE_in_condExpr1356); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RangeE.add(RangeE140);

                    pushFollow(FOLLOW_expression_in_condExpr1358);
                    expression141=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression141.getTree());


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
                    // 194:42: -> ^( RangeE startExpr expression )
                    {
                        // grammar/PlazmaScript.g:194:45: ^( RangeE startExpr expression )
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
                    // grammar/PlazmaScript.g:195:8: Range expression
                    {
                    Range142=(Token)match(input,Range,FOLLOW_Range_in_condExpr1393); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Range.add(Range142);

                    pushFollow(FOLLOW_expression_in_condExpr1395);
                    expression143=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression143.getTree());


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
                    // 195:42: -> ^( Range startExpr expression )
                    {
                        // grammar/PlazmaScript.g:195:45: ^( Range startExpr expression )
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
    // grammar/PlazmaScript.g:200:1: startExpr : orExpr ;
    public final PlazmaScriptParser.startExpr_return startExpr() throws RecognitionException {
        PlazmaScriptParser.startExpr_return retval = new PlazmaScriptParser.startExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        PlazmaScriptParser.orExpr_return orExpr144 = null;



        try {
            // grammar/PlazmaScript.g:201:2: ( orExpr )
            // grammar/PlazmaScript.g:201:4: orExpr
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_orExpr_in_startExpr1458);
            orExpr144=orExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, orExpr144.getTree());

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
    // grammar/PlazmaScript.g:204:1: orExpr : andExpr ( ( 'xor' | '||' | '|' | 'or' ) andExpr )* ;
    public final PlazmaScriptParser.orExpr_return orExpr() throws RecognitionException {
        PlazmaScriptParser.orExpr_return retval = new PlazmaScriptParser.orExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set146=null;
        PlazmaScriptParser.andExpr_return andExpr145 = null;

        PlazmaScriptParser.andExpr_return andExpr147 = null;


        Object set146_tree=null;

        try {
            // grammar/PlazmaScript.g:205:3: ( andExpr ( ( 'xor' | '||' | '|' | 'or' ) andExpr )* )
            // grammar/PlazmaScript.g:205:6: andExpr ( ( 'xor' | '||' | '|' | 'or' ) andExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_andExpr_in_orExpr1471);
            andExpr145=andExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, andExpr145.getTree());
            // grammar/PlazmaScript.g:205:14: ( ( 'xor' | '||' | '|' | 'or' ) andExpr )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( ((LA26_0>=XorWord && LA26_0<=OrWord)) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // grammar/PlazmaScript.g:205:15: ( 'xor' | '||' | '|' | 'or' ) andExpr
            	    {
            	    set146=(Token)input.LT(1);
            	    set146=(Token)input.LT(1);
            	    if ( (input.LA(1)>=XorWord && input.LA(1)<=OrWord) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set146), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_andExpr_in_orExpr1491);
            	    andExpr147=andExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, andExpr147.getTree());

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
    // grammar/PlazmaScript.g:209:1: andExpr : equExpr ( ( '&&' | '&' | 'and' ) equExpr )* ;
    public final PlazmaScriptParser.andExpr_return andExpr() throws RecognitionException {
        PlazmaScriptParser.andExpr_return retval = new PlazmaScriptParser.andExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set149=null;
        PlazmaScriptParser.equExpr_return equExpr148 = null;

        PlazmaScriptParser.equExpr_return equExpr150 = null;


        Object set149_tree=null;

        try {
            // grammar/PlazmaScript.g:210:3: ( equExpr ( ( '&&' | '&' | 'and' ) equExpr )* )
            // grammar/PlazmaScript.g:210:6: equExpr ( ( '&&' | '&' | 'and' ) equExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_equExpr_in_andExpr1508);
            equExpr148=equExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, equExpr148.getTree());
            // grammar/PlazmaScript.g:210:14: ( ( '&&' | '&' | 'and' ) equExpr )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( ((LA27_0>=And && LA27_0<=AndWord)) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // grammar/PlazmaScript.g:210:15: ( '&&' | '&' | 'and' ) equExpr
            	    {
            	    set149=(Token)input.LT(1);
            	    set149=(Token)input.LT(1);
            	    if ( (input.LA(1)>=And && input.LA(1)<=AndWord) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set149), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_equExpr_in_andExpr1524);
            	    equExpr150=equExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, equExpr150.getTree());

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
    // grammar/PlazmaScript.g:213:1: equExpr : relExpr ( ( '==' | '!=' ) relExpr )* ;
    public final PlazmaScriptParser.equExpr_return equExpr() throws RecognitionException {
        PlazmaScriptParser.equExpr_return retval = new PlazmaScriptParser.equExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set152=null;
        PlazmaScriptParser.relExpr_return relExpr151 = null;

        PlazmaScriptParser.relExpr_return relExpr153 = null;


        Object set152_tree=null;

        try {
            // grammar/PlazmaScript.g:214:3: ( relExpr ( ( '==' | '!=' ) relExpr )* )
            // grammar/PlazmaScript.g:214:6: relExpr ( ( '==' | '!=' ) relExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_relExpr_in_equExpr1540);
            relExpr151=relExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, relExpr151.getTree());
            // grammar/PlazmaScript.g:214:14: ( ( '==' | '!=' ) relExpr )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( ((LA28_0>=Equals && LA28_0<=NEquals)) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // grammar/PlazmaScript.g:214:15: ( '==' | '!=' ) relExpr
            	    {
            	    set152=(Token)input.LT(1);
            	    set152=(Token)input.LT(1);
            	    if ( (input.LA(1)>=Equals && input.LA(1)<=NEquals) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set152), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_relExpr_in_equExpr1552);
            	    relExpr153=relExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, relExpr153.getTree());

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
    // grammar/PlazmaScript.g:217:1: relExpr : addExpr ( ( '>=' | '<=' | '>' | '<' ) addExpr )* ;
    public final PlazmaScriptParser.relExpr_return relExpr() throws RecognitionException {
        PlazmaScriptParser.relExpr_return retval = new PlazmaScriptParser.relExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set155=null;
        PlazmaScriptParser.addExpr_return addExpr154 = null;

        PlazmaScriptParser.addExpr_return addExpr156 = null;


        Object set155_tree=null;

        try {
            // grammar/PlazmaScript.g:218:3: ( addExpr ( ( '>=' | '<=' | '>' | '<' ) addExpr )* )
            // grammar/PlazmaScript.g:218:6: addExpr ( ( '>=' | '<=' | '>' | '<' ) addExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_addExpr_in_relExpr1568);
            addExpr154=addExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, addExpr154.getTree());
            // grammar/PlazmaScript.g:218:14: ( ( '>=' | '<=' | '>' | '<' ) addExpr )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( ((LA29_0>=GTEquals && LA29_0<=LTEquals)||(LA29_0>=GT && LA29_0<=LT)) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // grammar/PlazmaScript.g:218:15: ( '>=' | '<=' | '>' | '<' ) addExpr
            	    {
            	    set155=(Token)input.LT(1);
            	    set155=(Token)input.LT(1);
            	    if ( (input.LA(1)>=GTEquals && input.LA(1)<=LTEquals)||(input.LA(1)>=GT && input.LA(1)<=LT) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set155), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_addExpr_in_relExpr1588);
            	    addExpr156=addExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, addExpr156.getTree());

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
    // grammar/PlazmaScript.g:221:1: addExpr : mulExpr ( ( '+' | '-' ) mulExpr )* ;
    public final PlazmaScriptParser.addExpr_return addExpr() throws RecognitionException {
        PlazmaScriptParser.addExpr_return retval = new PlazmaScriptParser.addExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set158=null;
        PlazmaScriptParser.mulExpr_return mulExpr157 = null;

        PlazmaScriptParser.mulExpr_return mulExpr159 = null;


        Object set158_tree=null;

        try {
            // grammar/PlazmaScript.g:222:3: ( mulExpr ( ( '+' | '-' ) mulExpr )* )
            // grammar/PlazmaScript.g:222:6: mulExpr ( ( '+' | '-' ) mulExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_mulExpr_in_addExpr1604);
            mulExpr157=mulExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, mulExpr157.getTree());
            // grammar/PlazmaScript.g:222:14: ( ( '+' | '-' ) mulExpr )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( ((LA30_0>=Add && LA30_0<=Subtract)) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // grammar/PlazmaScript.g:222:15: ( '+' | '-' ) mulExpr
            	    {
            	    set158=(Token)input.LT(1);
            	    set158=(Token)input.LT(1);
            	    if ( (input.LA(1)>=Add && input.LA(1)<=Subtract) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set158), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_mulExpr_in_addExpr1616);
            	    mulExpr159=mulExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, mulExpr159.getTree());

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
    // grammar/PlazmaScript.g:225:1: mulExpr : powExpr ( ( '*' | '/' | '%' ) powExpr )* ;
    public final PlazmaScriptParser.mulExpr_return mulExpr() throws RecognitionException {
        PlazmaScriptParser.mulExpr_return retval = new PlazmaScriptParser.mulExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set161=null;
        PlazmaScriptParser.powExpr_return powExpr160 = null;

        PlazmaScriptParser.powExpr_return powExpr162 = null;


        Object set161_tree=null;

        try {
            // grammar/PlazmaScript.g:226:3: ( powExpr ( ( '*' | '/' | '%' ) powExpr )* )
            // grammar/PlazmaScript.g:226:6: powExpr ( ( '*' | '/' | '%' ) powExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_powExpr_in_mulExpr1632);
            powExpr160=powExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, powExpr160.getTree());
            // grammar/PlazmaScript.g:226:14: ( ( '*' | '/' | '%' ) powExpr )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( ((LA31_0>=Multiply && LA31_0<=Modulus)) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // grammar/PlazmaScript.g:226:15: ( '*' | '/' | '%' ) powExpr
            	    {
            	    set161=(Token)input.LT(1);
            	    set161=(Token)input.LT(1);
            	    if ( (input.LA(1)>=Multiply && input.LA(1)<=Modulus) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set161), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_powExpr_in_mulExpr1648);
            	    powExpr162=powExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, powExpr162.getTree());

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
    // grammar/PlazmaScript.g:229:1: powExpr : unaryExpr ( '^' unaryExpr )* ;
    public final PlazmaScriptParser.powExpr_return powExpr() throws RecognitionException {
        PlazmaScriptParser.powExpr_return retval = new PlazmaScriptParser.powExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal164=null;
        PlazmaScriptParser.unaryExpr_return unaryExpr163 = null;

        PlazmaScriptParser.unaryExpr_return unaryExpr165 = null;


        Object char_literal164_tree=null;

        try {
            // grammar/PlazmaScript.g:230:3: ( unaryExpr ( '^' unaryExpr )* )
            // grammar/PlazmaScript.g:230:6: unaryExpr ( '^' unaryExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_unaryExpr_in_powExpr1664);
            unaryExpr163=unaryExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpr163.getTree());
            // grammar/PlazmaScript.g:230:16: ( '^' unaryExpr )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==Pow) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // grammar/PlazmaScript.g:230:17: '^' unaryExpr
            	    {
            	    char_literal164=(Token)match(input,Pow,FOLLOW_Pow_in_powExpr1667); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal164_tree = (Object)adaptor.create(char_literal164);
            	    root_0 = (Object)adaptor.becomeRoot(char_literal164_tree, root_0);
            	    }
            	    pushFollow(FOLLOW_unaryExpr_in_powExpr1670);
            	    unaryExpr165=unaryExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpr165.getTree());

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
    // grammar/PlazmaScript.g:233:1: unaryExpr : ( '+' atom -> ^( UNARY_PLUS atom ) | '-' atom -> ^( UNARY_MIN atom ) | '!' atom -> ^( NEGATE atom ) | 'not' atom -> ^( NEGATE atom ) | atom );
    public final PlazmaScriptParser.unaryExpr_return unaryExpr() throws RecognitionException {
        PlazmaScriptParser.unaryExpr_return retval = new PlazmaScriptParser.unaryExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal166=null;
        Token char_literal168=null;
        Token char_literal170=null;
        Token string_literal172=null;
        PlazmaScriptParser.atom_return atom167 = null;

        PlazmaScriptParser.atom_return atom169 = null;

        PlazmaScriptParser.atom_return atom171 = null;

        PlazmaScriptParser.atom_return atom173 = null;

        PlazmaScriptParser.atom_return atom174 = null;


        Object char_literal166_tree=null;
        Object char_literal168_tree=null;
        Object char_literal170_tree=null;
        Object string_literal172_tree=null;
        RewriteRuleTokenStream stream_Add=new RewriteRuleTokenStream(adaptor,"token Add");
        RewriteRuleTokenStream stream_NotWord=new RewriteRuleTokenStream(adaptor,"token NotWord");
        RewriteRuleTokenStream stream_Subtract=new RewriteRuleTokenStream(adaptor,"token Subtract");
        RewriteRuleTokenStream stream_Not=new RewriteRuleTokenStream(adaptor,"token Not");
        RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");
        try {
            // grammar/PlazmaScript.g:234:3: ( '+' atom -> ^( UNARY_PLUS atom ) | '-' atom -> ^( UNARY_MIN atom ) | '!' atom -> ^( NEGATE atom ) | 'not' atom -> ^( NEGATE atom ) | atom )
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
                    // grammar/PlazmaScript.g:234:6: '+' atom
                    {
                    char_literal166=(Token)match(input,Add,FOLLOW_Add_in_unaryExpr1688); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Add.add(char_literal166);

                    pushFollow(FOLLOW_atom_in_unaryExpr1690);
                    atom167=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom167.getTree());


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
                    // 234:15: -> ^( UNARY_PLUS atom )
                    {
                        // grammar/PlazmaScript.g:234:18: ^( UNARY_PLUS atom )
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
                    // grammar/PlazmaScript.g:235:6: '-' atom
                    {
                    char_literal168=(Token)match(input,Subtract,FOLLOW_Subtract_in_unaryExpr1705); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Subtract.add(char_literal168);

                    pushFollow(FOLLOW_atom_in_unaryExpr1707);
                    atom169=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom169.getTree());


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
                    // 235:15: -> ^( UNARY_MIN atom )
                    {
                        // grammar/PlazmaScript.g:235:18: ^( UNARY_MIN atom )
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
                    // grammar/PlazmaScript.g:236:6: '!' atom
                    {
                    char_literal170=(Token)match(input,Not,FOLLOW_Not_in_unaryExpr1722); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Not.add(char_literal170);

                    pushFollow(FOLLOW_atom_in_unaryExpr1724);
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
                    // 236:15: -> ^( NEGATE atom )
                    {
                        // grammar/PlazmaScript.g:236:18: ^( NEGATE atom )
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
                    // grammar/PlazmaScript.g:237:6: 'not' atom
                    {
                    string_literal172=(Token)match(input,NotWord,FOLLOW_NotWord_in_unaryExpr1739); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NotWord.add(string_literal172);

                    pushFollow(FOLLOW_atom_in_unaryExpr1741);
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
                    // 237:17: -> ^( NEGATE atom )
                    {
                        // grammar/PlazmaScript.g:237:20: ^( NEGATE atom )
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
                    // grammar/PlazmaScript.g:238:6: atom
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_atom_in_unaryExpr1758);
                    atom174=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, atom174.getTree());

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
    // grammar/PlazmaScript.g:241:1: atom : ( Integer | Number | Bool | Null | NaN | Infinity | lookup );
    public final PlazmaScriptParser.atom_return atom() throws RecognitionException {
        PlazmaScriptParser.atom_return retval = new PlazmaScriptParser.atom_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Integer175=null;
        Token Number176=null;
        Token Bool177=null;
        Token Null178=null;
        Token NaN179=null;
        Token Infinity180=null;
        PlazmaScriptParser.lookup_return lookup181 = null;


        Object Integer175_tree=null;
        Object Number176_tree=null;
        Object Bool177_tree=null;
        Object Null178_tree=null;
        Object NaN179_tree=null;
        Object Infinity180_tree=null;

        try {
            // grammar/PlazmaScript.g:242:3: ( Integer | Number | Bool | Null | NaN | Infinity | lookup )
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
                    // grammar/PlazmaScript.g:242:6: Integer
                    {
                    root_0 = (Object)adaptor.nil();

                    Integer175=(Token)match(input,Integer,FOLLOW_Integer_in_atom1772); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Integer175_tree = (Object)adaptor.create(Integer175);
                    adaptor.addChild(root_0, Integer175_tree);
                    }

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:243:6: Number
                    {
                    root_0 = (Object)adaptor.nil();

                    Number176=(Token)match(input,Number,FOLLOW_Number_in_atom1779); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Number176_tree = (Object)adaptor.create(Number176);
                    adaptor.addChild(root_0, Number176_tree);
                    }

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScript.g:244:6: Bool
                    {
                    root_0 = (Object)adaptor.nil();

                    Bool177=(Token)match(input,Bool,FOLLOW_Bool_in_atom1786); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Bool177_tree = (Object)adaptor.create(Bool177);
                    adaptor.addChild(root_0, Bool177_tree);
                    }

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScript.g:245:6: Null
                    {
                    root_0 = (Object)adaptor.nil();

                    Null178=(Token)match(input,Null,FOLLOW_Null_in_atom1793); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Null178_tree = (Object)adaptor.create(Null178);
                    adaptor.addChild(root_0, Null178_tree);
                    }

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScript.g:246:6: NaN
                    {
                    root_0 = (Object)adaptor.nil();

                    NaN179=(Token)match(input,NaN,FOLLOW_NaN_in_atom1800); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NaN179_tree = (Object)adaptor.create(NaN179);
                    adaptor.addChild(root_0, NaN179_tree);
                    }

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScript.g:247:6: Infinity
                    {
                    root_0 = (Object)adaptor.nil();

                    Infinity180=(Token)match(input,Infinity,FOLLOW_Infinity_in_atom1807); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Infinity180_tree = (Object)adaptor.create(Infinity180);
                    adaptor.addChild(root_0, Infinity180_tree);
                    }

                    }
                    break;
                case 7 :
                    // grammar/PlazmaScript.g:248:6: lookup
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_lookup_in_atom1815);
                    lookup181=lookup();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lookup181.getTree());

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
    // grammar/PlazmaScript.g:251:1: list : '[' ( exprList )? ']' -> ^( LIST ( exprList )? ) ;
    public final PlazmaScriptParser.list_return list() throws RecognitionException {
        PlazmaScriptParser.list_return retval = new PlazmaScriptParser.list_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal182=null;
        Token char_literal184=null;
        PlazmaScriptParser.exprList_return exprList183 = null;


        Object char_literal182_tree=null;
        Object char_literal184_tree=null;
        RewriteRuleTokenStream stream_CBracket=new RewriteRuleTokenStream(adaptor,"token CBracket");
        RewriteRuleTokenStream stream_OBracket=new RewriteRuleTokenStream(adaptor,"token OBracket");
        RewriteRuleSubtreeStream stream_exprList=new RewriteRuleSubtreeStream(adaptor,"rule exprList");
        try {
            // grammar/PlazmaScript.g:252:3: ( '[' ( exprList )? ']' -> ^( LIST ( exprList )? ) )
            // grammar/PlazmaScript.g:252:6: '[' ( exprList )? ']'
            {
            char_literal182=(Token)match(input,OBracket,FOLLOW_OBracket_in_list1829); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBracket.add(char_literal182);

            // grammar/PlazmaScript.g:252:10: ( exprList )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( ((LA35_0>=Identifier && LA35_0<=Set)||(LA35_0>=Integer && LA35_0<=String)||(LA35_0>=Not && LA35_0<=NotWord)||(LA35_0>=Add && LA35_0<=Subtract)||LA35_0==OBrace||LA35_0==OBracket||LA35_0==OParen||LA35_0==ContextIdentifier) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // grammar/PlazmaScript.g:0:0: exprList
                    {
                    pushFollow(FOLLOW_exprList_in_list1831);
                    exprList183=exprList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_exprList.add(exprList183.getTree());

                    }
                    break;

            }

            char_literal184=(Token)match(input,CBracket,FOLLOW_CBracket_in_list1834); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBracket.add(char_literal184);



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
            // 252:24: -> ^( LIST ( exprList )? )
            {
                // grammar/PlazmaScript.g:252:27: ^( LIST ( exprList )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LIST, "LIST"), root_1);

                // grammar/PlazmaScript.g:252:34: ( exprList )?
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
    // grammar/PlazmaScript.g:255:1: map : '{' ( ':' | exprMap ) '}' -> ^( MAP ( exprMap )? ) ;
    public final PlazmaScriptParser.map_return map() throws RecognitionException {
        PlazmaScriptParser.map_return retval = new PlazmaScriptParser.map_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal185=null;
        Token char_literal186=null;
        Token char_literal188=null;
        PlazmaScriptParser.exprMap_return exprMap187 = null;


        Object char_literal185_tree=null;
        Object char_literal186_tree=null;
        Object char_literal188_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_Colon=new RewriteRuleTokenStream(adaptor,"token Colon");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_exprMap=new RewriteRuleSubtreeStream(adaptor,"rule exprMap");
        try {
            // grammar/PlazmaScript.g:256:3: ( '{' ( ':' | exprMap ) '}' -> ^( MAP ( exprMap )? ) )
            // grammar/PlazmaScript.g:256:6: '{' ( ':' | exprMap ) '}'
            {
            char_literal185=(Token)match(input,OBrace,FOLLOW_OBrace_in_map1857); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal185);

            // grammar/PlazmaScript.g:256:10: ( ':' | exprMap )
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
                    // grammar/PlazmaScript.g:256:11: ':'
                    {
                    char_literal186=(Token)match(input,Colon,FOLLOW_Colon_in_map1860); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Colon.add(char_literal186);


                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:256:17: exprMap
                    {
                    pushFollow(FOLLOW_exprMap_in_map1864);
                    exprMap187=exprMap();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_exprMap.add(exprMap187.getTree());

                    }
                    break;

            }

            char_literal188=(Token)match(input,CBrace,FOLLOW_CBrace_in_map1867); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal188);



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
            // 256:30: -> ^( MAP ( exprMap )? )
            {
                // grammar/PlazmaScript.g:256:33: ^( MAP ( exprMap )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(MAP, "MAP"), root_1);

                // grammar/PlazmaScript.g:256:39: ( exprMap )?
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
    // grammar/PlazmaScript.g:259:1: lookup : ( functionCall ( indexes )? -> ^( LOOKUP functionCall ( indexes )? ) | list ( indexes )? -> ^( LOOKUP list ( indexes )? ) | map ( indexes )? -> ^( LOOKUP map ( indexes )? ) | Identifier ( indexes )? -> ^( LOOKUP Identifier ( indexes )? ) | anyIdentifier ( indexes )? -> ^( LOOKUP ( indexes )? ) | String ( indexes )? -> ^( LOOKUP String ( indexes )? ) | '(' expression ')' ( indexes )? -> ^( LOOKUP expression ( indexes )? ) );
    public final PlazmaScriptParser.lookup_return lookup() throws RecognitionException {
        PlazmaScriptParser.lookup_return retval = new PlazmaScriptParser.lookup_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Identifier195=null;
        Token String199=null;
        Token char_literal201=null;
        Token char_literal203=null;
        PlazmaScriptParser.functionCall_return functionCall189 = null;

        PlazmaScriptParser.indexes_return indexes190 = null;

        PlazmaScriptParser.list_return list191 = null;

        PlazmaScriptParser.indexes_return indexes192 = null;

        PlazmaScriptParser.map_return map193 = null;

        PlazmaScriptParser.indexes_return indexes194 = null;

        PlazmaScriptParser.indexes_return indexes196 = null;

        PlazmaScriptParser.anyIdentifier_return anyIdentifier197 = null;

        PlazmaScriptParser.indexes_return indexes198 = null;

        PlazmaScriptParser.indexes_return indexes200 = null;

        PlazmaScriptParser.expression_return expression202 = null;

        PlazmaScriptParser.indexes_return indexes204 = null;


        Object Identifier195_tree=null;
        Object String199_tree=null;
        Object char_literal201_tree=null;
        Object char_literal203_tree=null;
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
            // grammar/PlazmaScript.g:260:3: ( functionCall ( indexes )? -> ^( LOOKUP functionCall ( indexes )? ) | list ( indexes )? -> ^( LOOKUP list ( indexes )? ) | map ( indexes )? -> ^( LOOKUP map ( indexes )? ) | Identifier ( indexes )? -> ^( LOOKUP Identifier ( indexes )? ) | anyIdentifier ( indexes )? -> ^( LOOKUP ( indexes )? ) | String ( indexes )? -> ^( LOOKUP String ( indexes )? ) | '(' expression ')' ( indexes )? -> ^( LOOKUP expression ( indexes )? ) )
            int alt44=7;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA44_1 = input.LA(2);

                if ( (LA44_1==OParen) ) {
                    alt44=1;
                }
                else if ( (synpred83_PlazmaScript()) ) {
                    alt44=4;
                }
                else if ( (synpred85_PlazmaScript()) ) {
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
                    // grammar/PlazmaScript.g:260:6: functionCall ( indexes )?
                    {
                    pushFollow(FOLLOW_functionCall_in_lookup1890);
                    functionCall189=functionCall();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_functionCall.add(functionCall189.getTree());
                    // grammar/PlazmaScript.g:260:19: ( indexes )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==OBracket||LA37_0==106) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1892);
                            indexes190=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes190.getTree());

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
                    // 260:34: -> ^( LOOKUP functionCall ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:260:37: ^( LOOKUP functionCall ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_functionCall.nextTree());
                        // grammar/PlazmaScript.g:260:59: ( indexes )?
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
                    // grammar/PlazmaScript.g:261:6: list ( indexes )?
                    {
                    pushFollow(FOLLOW_list_in_lookup1917);
                    list191=list();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_list.add(list191.getTree());
                    // grammar/PlazmaScript.g:261:11: ( indexes )?
                    int alt38=2;
                    int LA38_0 = input.LA(1);

                    if ( (LA38_0==OBracket||LA38_0==106) ) {
                        alt38=1;
                    }
                    switch (alt38) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1919);
                            indexes192=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes192.getTree());

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
                    // 261:34: -> ^( LOOKUP list ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:261:37: ^( LOOKUP list ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_list.nextTree());
                        // grammar/PlazmaScript.g:261:51: ( indexes )?
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
                    // grammar/PlazmaScript.g:262:6: map ( indexes )?
                    {
                    pushFollow(FOLLOW_map_in_lookup1952);
                    map193=map();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_map.add(map193.getTree());
                    // grammar/PlazmaScript.g:262:10: ( indexes )?
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==OBracket||LA39_0==106) ) {
                        alt39=1;
                    }
                    switch (alt39) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1954);
                            indexes194=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes194.getTree());

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
                    // 262:34: -> ^( LOOKUP map ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:262:37: ^( LOOKUP map ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_map.nextTree());
                        // grammar/PlazmaScript.g:262:50: ( indexes )?
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
                    // grammar/PlazmaScript.g:263:6: Identifier ( indexes )?
                    {
                    Identifier195=(Token)match(input,Identifier,FOLLOW_Identifier_in_lookup1990); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier195);

                    // grammar/PlazmaScript.g:263:17: ( indexes )?
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( (LA40_0==OBracket||LA40_0==106) ) {
                        alt40=1;
                    }
                    switch (alt40) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1992);
                            indexes196=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes196.getTree());

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
                    // 263:34: -> ^( LOOKUP Identifier ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:263:37: ^( LOOKUP Identifier ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_Identifier.nextNode());
                        // grammar/PlazmaScript.g:263:57: ( indexes )?
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
                    // grammar/PlazmaScript.g:264:6: anyIdentifier ( indexes )?
                    {
                    pushFollow(FOLLOW_anyIdentifier_in_lookup2019);
                    anyIdentifier197=anyIdentifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_anyIdentifier.add(anyIdentifier197.getTree());
                    // grammar/PlazmaScript.g:264:20: ( indexes )?
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==OBracket||LA41_0==106) ) {
                        alt41=1;
                    }
                    switch (alt41) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2021);
                            indexes198=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes198.getTree());

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
                    // 264:34: -> ^( LOOKUP ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:264:37: ^( LOOKUP ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, new CommonTree(new CommonToken(Identifier, (anyIdentifier197!=null?input.toString(anyIdentifier197.start,anyIdentifier197.stop):null))));
                        // grammar/PlazmaScript.g:264:113: ( indexes )?
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
                    // grammar/PlazmaScript.g:265:6: String ( indexes )?
                    {
                    String199=(Token)match(input,String,FOLLOW_String_in_lookup2047); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_String.add(String199);

                    // grammar/PlazmaScript.g:265:13: ( indexes )?
                    int alt42=2;
                    int LA42_0 = input.LA(1);

                    if ( (LA42_0==OBracket||LA42_0==106) ) {
                        alt42=1;
                    }
                    switch (alt42) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2049);
                            indexes200=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes200.getTree());

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
                    // 265:34: -> ^( LOOKUP String ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:265:37: ^( LOOKUP String ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_String.nextNode());
                        // grammar/PlazmaScript.g:265:53: ( indexes )?
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
                    // grammar/PlazmaScript.g:266:6: '(' expression ')' ( indexes )?
                    {
                    char_literal201=(Token)match(input,OParen,FOLLOW_OParen_in_lookup2080); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal201);

                    pushFollow(FOLLOW_expression_in_lookup2082);
                    expression202=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression202.getTree());
                    char_literal203=(Token)match(input,CParen,FOLLOW_CParen_in_lookup2084); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal203);

                    // grammar/PlazmaScript.g:266:25: ( indexes )?
                    int alt43=2;
                    int LA43_0 = input.LA(1);

                    if ( (LA43_0==OBracket||LA43_0==106) ) {
                        alt43=1;
                    }
                    switch (alt43) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup2086);
                            indexes204=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes204.getTree());

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
                    // 266:34: -> ^( LOOKUP expression ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:266:37: ^( LOOKUP expression ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_expression.nextTree());
                        // grammar/PlazmaScript.g:266:57: ( indexes )?
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
    // grammar/PlazmaScript.g:269:1: indexes : ( tail )+ -> ^( TAILS ( tail )+ ) ;
    public final PlazmaScriptParser.indexes_return indexes() throws RecognitionException {
        PlazmaScriptParser.indexes_return retval = new PlazmaScriptParser.indexes_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        PlazmaScriptParser.tail_return tail205 = null;


        RewriteRuleSubtreeStream stream_tail=new RewriteRuleSubtreeStream(adaptor,"rule tail");
        try {
            // grammar/PlazmaScript.g:271:3: ( ( tail )+ -> ^( TAILS ( tail )+ ) )
            // grammar/PlazmaScript.g:271:6: ( tail )+
            {
            // grammar/PlazmaScript.g:271:6: ( tail )+
            int cnt45=0;
            loop45:
            do {
                int alt45=2;
                int LA45_0 = input.LA(1);

                if ( (LA45_0==OBracket||LA45_0==106) ) {
                    alt45=1;
                }


                switch (alt45) {
            	case 1 :
            	    // grammar/PlazmaScript.g:271:7: tail
            	    {
            	    pushFollow(FOLLOW_tail_in_indexes2116);
            	    tail205=tail();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_tail.add(tail205.getTree());

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
            // 271:14: -> ^( TAILS ( tail )+ )
            {
                // grammar/PlazmaScript.g:271:17: ^( TAILS ( tail )+ )
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
    // grammar/PlazmaScript.g:274:1: tail : ( '[' expression ']' -> ^( INDEX expression ) | '.' Identifier -> ^( ATTRIBUTE Identifier ) | '.' Identifier '(' ( exprList )? ')' -> ^( CALL Identifier ( exprList )? ) );
    public final PlazmaScriptParser.tail_return tail() throws RecognitionException {
        PlazmaScriptParser.tail_return retval = new PlazmaScriptParser.tail_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal206=null;
        Token char_literal208=null;
        Token char_literal209=null;
        Token Identifier210=null;
        Token char_literal211=null;
        Token Identifier212=null;
        Token char_literal213=null;
        Token char_literal215=null;
        PlazmaScriptParser.expression_return expression207 = null;

        PlazmaScriptParser.exprList_return exprList214 = null;


        Object char_literal206_tree=null;
        Object char_literal208_tree=null;
        Object char_literal209_tree=null;
        Object Identifier210_tree=null;
        Object char_literal211_tree=null;
        Object Identifier212_tree=null;
        Object char_literal213_tree=null;
        Object char_literal215_tree=null;
        RewriteRuleTokenStream stream_CBracket=new RewriteRuleTokenStream(adaptor,"token CBracket");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_OBracket=new RewriteRuleTokenStream(adaptor,"token OBracket");
        RewriteRuleTokenStream stream_106=new RewriteRuleTokenStream(adaptor,"token 106");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_exprList=new RewriteRuleSubtreeStream(adaptor,"rule exprList");
        try {
            // grammar/PlazmaScript.g:275:2: ( '[' expression ']' -> ^( INDEX expression ) | '.' Identifier -> ^( ATTRIBUTE Identifier ) | '.' Identifier '(' ( exprList )? ')' -> ^( CALL Identifier ( exprList )? ) )
            int alt47=3;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==OBracket) ) {
                alt47=1;
            }
            else if ( (LA47_0==106) ) {
                int LA47_2 = input.LA(2);

                if ( (LA47_2==Identifier) ) {
                    int LA47_3 = input.LA(3);

                    if ( (LA47_3==OParen) ) {
                        alt47=3;
                    }
                    else if ( (LA47_3==EOF||(LA47_3>=In && LA47_3<=Range)||(LA47_3>=XorWord && LA47_3<=Pow)||(LA47_3>=GT && LA47_3<=Modulus)||(LA47_3>=CBrace && LA47_3<=CBracket)||(LA47_3>=CParen && LA47_3<=Colon)||LA47_3==106) ) {
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
                    // grammar/PlazmaScript.g:275:4: '[' expression ']'
                    {
                    char_literal206=(Token)match(input,OBracket,FOLLOW_OBracket_in_tail2139); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OBracket.add(char_literal206);

                    pushFollow(FOLLOW_expression_in_tail2141);
                    expression207=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression207.getTree());
                    char_literal208=(Token)match(input,CBracket,FOLLOW_CBracket_in_tail2143); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CBracket.add(char_literal208);



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
                    // 275:37: -> ^( INDEX expression )
                    {
                        // grammar/PlazmaScript.g:275:40: ^( INDEX expression )
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
                    // grammar/PlazmaScript.g:276:4: '.' Identifier
                    {
                    char_literal209=(Token)match(input,106,FOLLOW_106_in_tail2170); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_106.add(char_literal209);

                    Identifier210=(Token)match(input,Identifier,FOLLOW_Identifier_in_tail2172); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier210);



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
                    // 276:37: -> ^( ATTRIBUTE Identifier )
                    {
                        // grammar/PlazmaScript.g:276:40: ^( ATTRIBUTE Identifier )
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
                    // grammar/PlazmaScript.g:277:4: '.' Identifier '(' ( exprList )? ')'
                    {
                    char_literal211=(Token)match(input,106,FOLLOW_106_in_tail2203); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_106.add(char_literal211);

                    Identifier212=(Token)match(input,Identifier,FOLLOW_Identifier_in_tail2205); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier212);

                    char_literal213=(Token)match(input,OParen,FOLLOW_OParen_in_tail2207); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal213);

                    // grammar/PlazmaScript.g:277:23: ( exprList )?
                    int alt46=2;
                    int LA46_0 = input.LA(1);

                    if ( ((LA46_0>=Identifier && LA46_0<=Set)||(LA46_0>=Integer && LA46_0<=String)||(LA46_0>=Not && LA46_0<=NotWord)||(LA46_0>=Add && LA46_0<=Subtract)||LA46_0==OBrace||LA46_0==OBracket||LA46_0==OParen||LA46_0==ContextIdentifier) ) {
                        alt46=1;
                    }
                    switch (alt46) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_tail2209);
                            exprList214=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList214.getTree());

                            }
                            break;

                    }

                    char_literal215=(Token)match(input,CParen,FOLLOW_CParen_in_tail2212); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal215);



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
                    // 277:37: -> ^( CALL Identifier ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:277:40: ^( CALL Identifier ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CALL, "CALL"), root_1);

                        adaptor.addChild(root_1, stream_Identifier.nextNode());
                        // grammar/PlazmaScript.g:277:58: ( exprList )?
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
    // grammar/PlazmaScript.g:376:1: anyIdentifier : ( ContextIdentifier | Identifier );
    public final PlazmaScriptParser.anyIdentifier_return anyIdentifier() throws RecognitionException {
        PlazmaScriptParser.anyIdentifier_return retval = new PlazmaScriptParser.anyIdentifier_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set216=null;

        Object set216_tree=null;

        try {
            // grammar/PlazmaScript.g:377:3: ( ContextIdentifier | Identifier )
            // grammar/PlazmaScript.g:
            {
            root_0 = (Object)adaptor.nil();

            set216=(Token)input.LT(1);
            if ( input.LA(1)==Identifier||input.LA(1)==ContextIdentifier ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set216));
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
        // grammar/PlazmaScript.g:80:6: ( assignment ';' )
        // grammar/PlazmaScript.g:80:6: assignment ';'
        {
        pushFollow(FOLLOW_assignment_in_synpred4_PlazmaScript291);
        assignment();

        state._fsp--;
        if (state.failed) return ;
        match(input,SColon,FOLLOW_SColon_in_synpred4_PlazmaScript293); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred4_PlazmaScript

    // $ANTLR start synpred5_PlazmaScript
    public final void synpred5_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:81:6: ( functionCall ';' )
        // grammar/PlazmaScript.g:81:6: functionCall ';'
        {
        pushFollow(FOLLOW_functionCall_in_synpred5_PlazmaScript306);
        functionCall();

        state._fsp--;
        if (state.failed) return ;
        match(input,SColon,FOLLOW_SColon_in_synpred5_PlazmaScript308); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred5_PlazmaScript

    // $ANTLR start synpred6_PlazmaScript
    public final void synpred6_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:82:6: ( lookup ';' )
        // grammar/PlazmaScript.g:82:6: lookup ';'
        {
        pushFollow(FOLLOW_lookup_in_synpred6_PlazmaScript319);
        lookup();

        state._fsp--;
        if (state.failed) return ;
        match(input,SColon,FOLLOW_SColon_in_synpred6_PlazmaScript321); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred6_PlazmaScript

    // $ANTLR start synpred13_PlazmaScript
    public final void synpred13_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:92:6: ( ( variableDef )? Identifier ( indexes )? '=' expression )
        // grammar/PlazmaScript.g:92:6: ( variableDef )? Identifier ( indexes )? '=' expression
        {
        // grammar/PlazmaScript.g:92:6: ( variableDef )?
        int alt48=2;
        int LA48_0 = input.LA(1);

        if ( (LA48_0==Var) ) {
            alt48=1;
        }
        switch (alt48) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: variableDef
                {
                pushFollow(FOLLOW_variableDef_in_synpred13_PlazmaScript422);
                variableDef();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        match(input,Identifier,FOLLOW_Identifier_in_synpred13_PlazmaScript425); if (state.failed) return ;
        // grammar/PlazmaScript.g:92:30: ( indexes )?
        int alt49=2;
        int LA49_0 = input.LA(1);

        if ( (LA49_0==OBracket||LA49_0==106) ) {
            alt49=1;
        }
        switch (alt49) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: indexes
                {
                pushFollow(FOLLOW_indexes_in_synpred13_PlazmaScript427);
                indexes();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        match(input,Assign,FOLLOW_Assign_in_synpred13_PlazmaScript430); if (state.failed) return ;
        pushFollow(FOLLOW_expression_in_synpred13_PlazmaScript432);
        expression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred13_PlazmaScript

    // $ANTLR start synpred83_PlazmaScript
    public final void synpred83_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:263:6: ( Identifier ( indexes )? )
        // grammar/PlazmaScript.g:263:6: Identifier ( indexes )?
        {
        match(input,Identifier,FOLLOW_Identifier_in_synpred83_PlazmaScript1990); if (state.failed) return ;
        // grammar/PlazmaScript.g:263:17: ( indexes )?
        int alt61=2;
        int LA61_0 = input.LA(1);

        if ( (LA61_0==OBracket||LA61_0==106) ) {
            alt61=1;
        }
        switch (alt61) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: indexes
                {
                pushFollow(FOLLOW_indexes_in_synpred83_PlazmaScript1992);
                indexes();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred83_PlazmaScript

    // $ANTLR start synpred85_PlazmaScript
    public final void synpred85_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:264:6: ( anyIdentifier ( indexes )? )
        // grammar/PlazmaScript.g:264:6: anyIdentifier ( indexes )?
        {
        pushFollow(FOLLOW_anyIdentifier_in_synpred85_PlazmaScript2019);
        anyIdentifier();

        state._fsp--;
        if (state.failed) return ;
        // grammar/PlazmaScript.g:264:20: ( indexes )?
        int alt62=2;
        int LA62_0 = input.LA(1);

        if ( (LA62_0==OBracket||LA62_0==106) ) {
            alt62=1;
        }
        switch (alt62) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: indexes
                {
                pushFollow(FOLLOW_indexes_in_synpred85_PlazmaScript2021);
                indexes();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred85_PlazmaScript

    // Delegated rules

    public final boolean synpred83_PlazmaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred83_PlazmaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred85_PlazmaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred85_PlazmaScript_fragment(); // can never throw exception
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
        "\30\uffff";
    static final String DFA3_eofS =
        "\30\uffff";
    static final String DFA3_minS =
        "\1\41\1\uffff\14\0\12\uffff";
    static final String DFA3_maxS =
        "\1\142\1\uffff\14\0\12\uffff";
    static final String DFA3_acceptS =
        "\1\uffff\1\1\14\uffff\1\3\3\uffff\1\4\1\5\1\6\1\7\1\10\1\2";
    static final String DFA3_specialS =
        "\2\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\12"+
        "\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\25\1\26\1\2\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
            "\1\22\1\uffff\1\1\1\uffff\1\23\1\24\11\uffff\1\16\25\uffff\1"+
            "\16\1\uffff\1\16\1\uffff\1\16\12\uffff\1\3",
            "",
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
            return "79:1: statement : ( assignment ';' -> assignment | functionCall ';' -> functionCall | lookup ';' -> lookup | ifStatement | forStatement | whileStatement | Break ';' -> Break | Continue ';' -> Continue );";
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

                        else if ( (synpred5_PlazmaScript()) ) {s = 23;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 14;}

                         
                        input.seek(index3_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA3_3 = input.LA(1);

                         
                        int index3_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_PlazmaScript()) ) {s = 1;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 14;}

                         
                        input.seek(index3_3);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA3_4 = input.LA(1);

                         
                        int index3_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 23;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 14;}

                         
                        input.seek(index3_4);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA3_5 = input.LA(1);

                         
                        int index3_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 23;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 14;}

                         
                        input.seek(index3_5);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA3_6 = input.LA(1);

                         
                        int index3_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 23;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 14;}

                         
                        input.seek(index3_6);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA3_7 = input.LA(1);

                         
                        int index3_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 23;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 14;}

                         
                        input.seek(index3_7);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA3_8 = input.LA(1);

                         
                        int index3_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 23;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 14;}

                         
                        input.seek(index3_8);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA3_9 = input.LA(1);

                         
                        int index3_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 23;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 14;}

                         
                        input.seek(index3_9);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA3_10 = input.LA(1);

                         
                        int index3_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 23;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 14;}

                         
                        input.seek(index3_10);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA3_11 = input.LA(1);

                         
                        int index3_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 23;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 14;}

                         
                        input.seek(index3_11);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA3_12 = input.LA(1);

                         
                        int index3_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 23;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 14;}

                         
                        input.seek(index3_12);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA3_13 = input.LA(1);

                         
                        int index3_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 23;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 14;}

                         
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
 

    public static final BitSet FOLLOW_block_in_parse215 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_parse217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_block236 = new BitSet(new long[]{0x200F7FFF00000002L,0x0000000400A80000L});
    public static final BitSet FOLLOW_functionDecl_in_block240 = new BitSet(new long[]{0x200F7FFF00000002L,0x0000000400A80000L});
    public static final BitSet FOLLOW_Return_in_block245 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_expression_in_block247 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_SColon_in_block249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_statement291 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_SColon_in_statement293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_statement306 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_SColon_in_statement308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_statement319 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_SColon_in_statement321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStatement_in_statement365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forStatement_in_statement372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whileStatement_in_statement379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Break_in_statement386 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_SColon_in_statement388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Continue_in_statement399 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_SColon_in_statement401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDef_in_assignment422 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_Identifier_in_assignment425 = new BitSet(new long[]{0x0000000000000000L,0x0000040004200000L});
    public static final BitSet FOLLOW_indexes_in_assignment427 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_Assign_in_assignment430 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_expression_in_assignment432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDef_in_assignment455 = new BitSet(new long[]{0x0001000800000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_anyIdentifier_in_assignment458 = new BitSet(new long[]{0x0000000000000000L,0x0000040004200000L});
    public static final BitSet FOLLOW_indexes_in_assignment460 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_Assign_in_assignment463 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_expression_in_assignment465 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_functionCall495 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_OParen_in_functionCall497 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000401A8CC00L});
    public static final BitSet FOLLOW_exprList_in_functionCall499 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_CParen_in_functionCall502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Println_in_functionCall520 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_OParen_in_functionCall522 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000401A8CC00L});
    public static final BitSet FOLLOW_expression_in_functionCall524 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_CParen_in_functionCall527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Print_in_functionCall546 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_OParen_in_functionCall548 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_expression_in_functionCall550 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_CParen_in_functionCall552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Assert_in_functionCall573 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_OParen_in_functionCall575 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_expression_in_functionCall577 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_CParen_in_functionCall579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Date_in_functionCall599 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_OParen_in_functionCall601 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000401A8CC00L});
    public static final BitSet FOLLOW_exprList_in_functionCall603 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_CParen_in_functionCall606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DateTime_in_functionCall629 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_OParen_in_functionCall631 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000401A8CC00L});
    public static final BitSet FOLLOW_exprList_in_functionCall633 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_CParen_in_functionCall636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Time_in_functionCall659 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_OParen_in_functionCall661 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000401A8CC00L});
    public static final BitSet FOLLOW_exprList_in_functionCall663 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_CParen_in_functionCall666 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Duration_in_functionCall689 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_OParen_in_functionCall691 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000401A8CC00L});
    public static final BitSet FOLLOW_expression_in_functionCall693 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_CParen_in_functionCall696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Period_in_functionCall715 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_OParen_in_functionCall717 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000401A8CC00L});
    public static final BitSet FOLLOW_exprList_in_functionCall719 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_CParen_in_functionCall722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_List_in_functionCall753 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_OParen_in_functionCall755 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000401A8CC00L});
    public static final BitSet FOLLOW_exprList_in_functionCall757 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_CParen_in_functionCall760 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Set_in_functionCall783 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_OParen_in_functionCall785 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000401A8CC00L});
    public static final BitSet FOLLOW_exprList_in_functionCall787 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_CParen_in_functionCall790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStat_in_ifStatement828 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_elseIfStat_in_ifStatement830 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_elseStat_in_ifStatement833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_If_in_ifStat862 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_OParen_in_ifStat864 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_expression_in_ifStat866 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_CParen_in_ifStat868 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_OBrace_in_ifStat870 = new BitSet(new long[]{0x200F7FFF00000000L,0x0000000400A80000L});
    public static final BitSet FOLLOW_block_in_ifStat872 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_CBrace_in_ifStat874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Else_in_elseIfStat898 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_If_in_elseIfStat900 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_OParen_in_elseIfStat902 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_expression_in_elseIfStat904 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_CParen_in_elseIfStat906 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_OBrace_in_elseIfStat908 = new BitSet(new long[]{0x200F7FFF00000000L,0x0000000400A80000L});
    public static final BitSet FOLLOW_block_in_elseIfStat910 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_CBrace_in_elseIfStat912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Else_in_elseStat936 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_OBrace_in_elseStat938 = new BitSet(new long[]{0x200F7FFF00000000L,0x0000000400A80000L});
    public static final BitSet FOLLOW_block_in_elseStat940 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_CBrace_in_elseStat942 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Var_in_variableDef962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Def_in_functionDecl994 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_Identifier_in_functionDecl996 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_OParen_in_functionDecl998 = new BitSet(new long[]{0x0000000800000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_idList_in_functionDecl1000 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_CParen_in_functionDecl1003 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_OBrace_in_functionDecl1005 = new BitSet(new long[]{0x200F7FFF00000000L,0x0000000400A80000L});
    public static final BitSet FOLLOW_block_in_functionDecl1007 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_CBrace_in_functionDecl1009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_For_in_forStatement1038 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_OParen_in_forStatement1040 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_Identifier_in_forStatement1042 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_In_in_forStatement1044 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_expression_in_forStatement1046 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_CParen_in_forStatement1048 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_OBrace_in_forStatement1050 = new BitSet(new long[]{0x200F7FFF00000000L,0x0000000400A80000L});
    public static final BitSet FOLLOW_block_in_forStatement1052 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_CBrace_in_forStatement1054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_While_in_whileStatement1089 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_OParen_in_whileStatement1091 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_expression_in_whileStatement1093 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_CParen_in_whileStatement1095 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_OBrace_in_whileStatement1097 = new BitSet(new long[]{0x200F7FFF00000000L,0x0000000400A80000L});
    public static final BitSet FOLLOW_block_in_whileStatement1099 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_CBrace_in_whileStatement1101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_idList1125 = new BitSet(new long[]{0x0000000000000002L,0x0000000008000000L});
    public static final BitSet FOLLOW_Comma_in_idList1128 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_Identifier_in_idList1130 = new BitSet(new long[]{0x0000000000000002L,0x0000000008000000L});
    public static final BitSet FOLLOW_expression_in_exprList1155 = new BitSet(new long[]{0x0000000000000002L,0x0000000008000000L});
    public static final BitSet FOLLOW_Comma_in_exprList1158 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_expression_in_exprList1160 = new BitSet(new long[]{0x0000000000000002L,0x0000000008000000L});
    public static final BitSet FOLLOW_expression_in_exprPair1186 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_Colon_in_exprPair1188 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_expression_in_exprPair1190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprPair_in_exprMap1215 = new BitSet(new long[]{0x0000000000000002L,0x0000000008000000L});
    public static final BitSet FOLLOW_Comma_in_exprMap1218 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_exprPair_in_exprMap1220 = new BitSet(new long[]{0x0000000000000002L,0x0000000008000000L});
    public static final BitSet FOLLOW_condExpr_in_expression1246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_startExpr_in_condExpr1261 = new BitSet(new long[]{0x0070000000000002L,0x0000000010000000L});
    public static final BitSet FOLLOW_QMark_in_condExpr1276 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_expression_in_condExpr1280 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_Colon_in_condExpr1282 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_expression_in_condExpr1286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_In_in_condExpr1309 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_expression_in_condExpr1311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RangeE_in_condExpr1356 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_expression_in_condExpr1358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Range_in_condExpr1393 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_expression_in_condExpr1395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orExpr_in_startExpr1458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_andExpr_in_orExpr1471 = new BitSet(new long[]{0xC000000000000002L,0x0000000000000003L});
    public static final BitSet FOLLOW_set_in_orExpr1474 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_andExpr_in_orExpr1491 = new BitSet(new long[]{0xC000000000000002L,0x0000000000000003L});
    public static final BitSet FOLLOW_equExpr_in_andExpr1508 = new BitSet(new long[]{0x0000000000000002L,0x000000000000001CL});
    public static final BitSet FOLLOW_set_in_andExpr1511 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_equExpr_in_andExpr1524 = new BitSet(new long[]{0x0000000000000002L,0x000000000000001CL});
    public static final BitSet FOLLOW_relExpr_in_equExpr1540 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000060L});
    public static final BitSet FOLLOW_set_in_equExpr1543 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_relExpr_in_equExpr1552 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000060L});
    public static final BitSet FOLLOW_addExpr_in_relExpr1568 = new BitSet(new long[]{0x0000000000000002L,0x0000000000003180L});
    public static final BitSet FOLLOW_set_in_relExpr1571 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_addExpr_in_relExpr1588 = new BitSet(new long[]{0x0000000000000002L,0x0000000000003180L});
    public static final BitSet FOLLOW_mulExpr_in_addExpr1604 = new BitSet(new long[]{0x0000000000000002L,0x000000000000C000L});
    public static final BitSet FOLLOW_set_in_addExpr1607 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_mulExpr_in_addExpr1616 = new BitSet(new long[]{0x0000000000000002L,0x000000000000C000L});
    public static final BitSet FOLLOW_powExpr_in_mulExpr1632 = new BitSet(new long[]{0x0000000000000002L,0x0000000000070000L});
    public static final BitSet FOLLOW_set_in_mulExpr1635 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_powExpr_in_mulExpr1648 = new BitSet(new long[]{0x0000000000000002L,0x0000000000070000L});
    public static final BitSet FOLLOW_unaryExpr_in_powExpr1664 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_Pow_in_powExpr1667 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_unaryExpr_in_powExpr1670 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_Add_in_unaryExpr1688 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Subtract_in_unaryExpr1705 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Not_in_unaryExpr1722 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NotWord_in_unaryExpr1739 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Integer_in_atom1772 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Number_in_atom1779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Bool_in_atom1786 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Null_in_atom1793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NaN_in_atom1800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Infinity_in_atom1807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_atom1815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OBracket_in_list1829 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400E8CC00L});
    public static final BitSet FOLLOW_exprList_in_list1831 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_CBracket_in_list1834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OBrace_in_map1857 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000420A8CC00L});
    public static final BitSet FOLLOW_Colon_in_map1860 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_exprMap_in_map1864 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_CBrace_in_map1867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_lookup1890 = new BitSet(new long[]{0x0000000000000002L,0x0000040000200000L});
    public static final BitSet FOLLOW_indexes_in_lookup1892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_in_lookup1917 = new BitSet(new long[]{0x0000000000000002L,0x0000040000200000L});
    public static final BitSet FOLLOW_indexes_in_lookup1919 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_map_in_lookup1952 = new BitSet(new long[]{0x0000000000000002L,0x0000040000200000L});
    public static final BitSet FOLLOW_indexes_in_lookup1954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_lookup1990 = new BitSet(new long[]{0x0000000000000002L,0x0000040000200000L});
    public static final BitSet FOLLOW_indexes_in_lookup1992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_anyIdentifier_in_lookup2019 = new BitSet(new long[]{0x0000000000000002L,0x0000040000200000L});
    public static final BitSet FOLLOW_indexes_in_lookup2021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_String_in_lookup2047 = new BitSet(new long[]{0x0000000000000002L,0x0000040000200000L});
    public static final BitSet FOLLOW_indexes_in_lookup2049 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OParen_in_lookup2080 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_expression_in_lookup2082 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_CParen_in_lookup2084 = new BitSet(new long[]{0x0000000000000002L,0x0000040000200000L});
    public static final BitSet FOLLOW_indexes_in_lookup2086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tail_in_indexes2116 = new BitSet(new long[]{0x0000000000000002L,0x0000040000200000L});
    public static final BitSet FOLLOW_OBracket_in_tail2139 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_expression_in_tail2141 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_CBracket_in_tail2143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_106_in_tail2170 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_Identifier_in_tail2172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_106_in_tail2203 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_Identifier_in_tail2205 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_OParen_in_tail2207 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000401A8CC00L});
    public static final BitSet FOLLOW_exprList_in_tail2209 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_CParen_in_tail2212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_anyIdentifier0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_synpred4_PlazmaScript291 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_SColon_in_synpred4_PlazmaScript293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_synpred5_PlazmaScript306 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_SColon_in_synpred5_PlazmaScript308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_synpred6_PlazmaScript319 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_SColon_in_synpred6_PlazmaScript321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDef_in_synpred13_PlazmaScript422 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_Identifier_in_synpred13_PlazmaScript425 = new BitSet(new long[]{0x0000000000000000L,0x0000040004200000L});
    public static final BitSet FOLLOW_indexes_in_synpred13_PlazmaScript427 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_Assign_in_synpred13_PlazmaScript430 = new BitSet(new long[]{0x3F813FF800000000L,0x0000000400A8CC00L});
    public static final BitSet FOLLOW_expression_in_synpred13_PlazmaScript432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_synpred83_PlazmaScript1990 = new BitSet(new long[]{0x0000000000000002L,0x0000040000200000L});
    public static final BitSet FOLLOW_indexes_in_synpred83_PlazmaScript1992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_anyIdentifier_in_synpred85_PlazmaScript2019 = new BitSet(new long[]{0x0000000000000002L,0x0000040000200000L});
    public static final BitSet FOLLOW_indexes_in_synpred85_PlazmaScript2021 = new BitSet(new long[]{0x0000000000000002L});

}