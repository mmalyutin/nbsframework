// $ANTLR 3.2 Sep 23, 2009 12:02:23 grammar/PlazmaScript.g 2016-08-17 18:31:05

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BLOCK", "RETURN", "STATEMENTS", "ASSIGNMENT", "FUNC_CALL", "EXP", "EXP_PAIR", "EXP_MAP", "EXP_LIST", "ID_LIST", "IF", "TERNARY", "UNARY_PLUS", "UNARY_MIN", "NEGATE", "FUNCTION", "INDEXES", "INDEX", "ATTRIBUTE", "CALL", "TAIL", "TAILS", "MAP", "LIST", "SET", "LOOKUP", "BREAK", "CONTINUE", "Return", "Break", "Continue", "Identifier", "Println", "Print", "Assert", "Date", "DateTime", "List", "Set", "If", "Else", "Var", "Def", "For", "While", "In", "RangeE", "Range", "Integer", "Number", "Bool", "Null", "NaN", "Infinity", "String", "XorWord", "Or", "BitOr", "OrWord", "And", "BitAnd", "AndWord", "Equals", "NEquals", "GTEquals", "LTEquals", "Pow", "Not", "NotWord", "GT", "LT", "Add", "Subtract", "Multiply", "Divide", "Modulus", "OBrace", "CBrace", "OBracket", "CBracket", "OParen", "CParen", "SColon", "Assign", "Comma", "QMark", "Colon", "DecimalNumeral", "DecimalFloatingPoint", "Digits", "ExponentPart", "ContextIdentifier", "Digit", "Comment", "Space", "ExponentIndicator", "SignedInteger", "Sign", "NonZeroDigit", "'.'"
    };
    public static final int FUNCTION=19;
    public static final int LT=74;
    public static final int TERNARY=15;
    public static final int EXP_LIST=12;
    public static final int DateTime=40;
    public static final int Date=39;
    public static final int EOF=-1;
    public static final int QMark=89;
    public static final int NotWord=72;
    public static final int BREAK=30;
    public static final int Identifier=35;
    public static final int UNARY_PLUS=16;
    public static final int FUNC_CALL=8;
    public static final int CParen=85;
    public static final int Comment=97;
    public static final int EXP=9;
    public static final int Digits=93;
    public static final int RETURN=5;
    public static final int CBrace=81;
    public static final int ExponentPart=94;
    public static final int ExponentIndicator=99;
    public static final int Sign=101;
    public static final int DecimalNumeral=91;
    public static final int Null=55;
    public static final int OrWord=62;
    public static final int ContextIdentifier=95;
    public static final int CBracket=83;
    public static final int Println=36;
    public static final int Bool=54;
    public static final int Modulus=79;
    public static final int AndWord=65;
    public static final int Colon=90;
    public static final int LIST=27;
    public static final int Def=46;
    public static final int LOOKUP=29;
    public static final int RangeE=50;
    public static final int Range=51;
    public static final int Break=33;
    public static final int SignedInteger=100;
    public static final int BitOr=61;
    public static final int STATEMENTS=6;
    public static final int GT=73;
    public static final int CALL=23;
    public static final int DecimalFloatingPoint=92;
    public static final int Else=44;
    public static final int Equals=66;
    public static final int Var=45;
    public static final int XorWord=59;
    public static final int OParen=84;
    public static final int Assert=38;
    public static final int ATTRIBUTE=22;
    public static final int While=48;
    public static final int ID_LIST=13;
    public static final int Add=75;
    public static final int Set=42;
    public static final int TAIL=24;
    public static final int IF=14;
    public static final int Space=98;
    public static final int INDEX=21;
    public static final int Assign=87;
    public static final int EXP_MAP=11;
    public static final int NaN=56;
    public static final int Number=53;
    public static final int CONTINUE=31;
    public static final int T__103=103;
    public static final int Print=37;
    public static final int GTEquals=68;
    public static final int String=58;
    public static final int Or=60;
    public static final int Return=32;
    public static final int If=43;
    public static final int And=63;
    public static final int In=49;
    public static final int NEquals=67;
    public static final int Continue=34;
    public static final int Subtract=76;
    public static final int EXP_PAIR=10;
    public static final int BitAnd=64;
    public static final int Multiply=77;
    public static final int OBrace=80;
    public static final int INDEXES=20;
    public static final int NEGATE=18;
    public static final int SET=28;
    public static final int Digit=96;
    public static final int For=47;
    public static final int Divide=78;
    public static final int List=41;
    public static final int TAILS=25;
    public static final int SColon=86;
    public static final int OBracket=82;
    public static final int NonZeroDigit=102;
    public static final int BLOCK=4;
    public static final int MAP=26;
    public static final int Not=71;
    public static final int UNARY_MIN=17;
    public static final int ASSIGNMENT=7;
    public static final int Infinity=57;
    public static final int Comma=88;
    public static final int Integer=52;
    public static final int Pow=70;
    public static final int LTEquals=69;

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

                    if ( (LA5_0==OBracket||LA5_0==103) ) {
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

                    if ( (LA7_0==OBracket||LA7_0==103) ) {
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
    // grammar/PlazmaScript.g:96:1: functionCall : ( Identifier '(' ( exprList )? ')' -> ^( FUNC_CALL Identifier ( exprList )? ) | Println '(' ( expression )? ')' -> ^( FUNC_CALL Println ( expression )? ) | Print '(' expression ')' -> ^( FUNC_CALL Print expression ) | Assert '(' expression ')' -> ^( FUNC_CALL Assert expression ) | Date '(' ( exprList )? ')' -> ^( FUNC_CALL Date ( exprList )? ) | DateTime '(' ( exprList )? ')' -> ^( FUNC_CALL DateTime ( exprList )? ) | List '(' ( exprList )? ')' -> ^( FUNC_CALL List ( exprList )? ) | Set '(' ( exprList )? ')' -> ^( FUNC_CALL Set ( exprList )? ) );
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
        Token List55=null;
        Token char_literal56=null;
        Token char_literal58=null;
        Token Set59=null;
        Token char_literal60=null;
        Token char_literal62=null;
        PlazmaScriptParser.exprList_return exprList33 = null;

        PlazmaScriptParser.expression_return expression37 = null;

        PlazmaScriptParser.expression_return expression41 = null;

        PlazmaScriptParser.expression_return expression45 = null;

        PlazmaScriptParser.exprList_return exprList49 = null;

        PlazmaScriptParser.exprList_return exprList53 = null;

        PlazmaScriptParser.exprList_return exprList57 = null;

        PlazmaScriptParser.exprList_return exprList61 = null;


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
        Object List55_tree=null;
        Object char_literal56_tree=null;
        Object char_literal58_tree=null;
        Object Set59_tree=null;
        Object char_literal60_tree=null;
        Object char_literal62_tree=null;
        RewriteRuleTokenStream stream_Println=new RewriteRuleTokenStream(adaptor,"token Println");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_DateTime=new RewriteRuleTokenStream(adaptor,"token DateTime");
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
            // grammar/PlazmaScript.g:97:3: ( Identifier '(' ( exprList )? ')' -> ^( FUNC_CALL Identifier ( exprList )? ) | Println '(' ( expression )? ')' -> ^( FUNC_CALL Println ( expression )? ) | Print '(' expression ')' -> ^( FUNC_CALL Print expression ) | Assert '(' expression ')' -> ^( FUNC_CALL Assert expression ) | Date '(' ( exprList )? ')' -> ^( FUNC_CALL Date ( exprList )? ) | DateTime '(' ( exprList )? ')' -> ^( FUNC_CALL DateTime ( exprList )? ) | List '(' ( exprList )? ')' -> ^( FUNC_CALL List ( exprList )? ) | Set '(' ( exprList )? ')' -> ^( FUNC_CALL Set ( exprList )? ) )
            int alt15=8;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                alt15=1;
                }
                break;
            case Println:
                {
                alt15=2;
                }
                break;
            case Print:
                {
                alt15=3;
                }
                break;
            case Assert:
                {
                alt15=4;
                }
                break;
            case Date:
                {
                alt15=5;
                }
                break;
            case DateTime:
                {
                alt15=6;
                }
                break;
            case List:
                {
                alt15=7;
                }
                break;
            case Set:
                {
                alt15=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
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
                    // elements: DateTime, exprList
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
                    // grammar/PlazmaScript.g:103:6: List '(' ( exprList )? ')'
                    {
                    List55=(Token)match(input,List,FOLLOW_List_in_functionCall661); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_List.add(List55);

                    char_literal56=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall663); if (state.failed) return retval; 
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
                            pushFollow(FOLLOW_exprList_in_functionCall665);
                            exprList57=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList57.getTree());

                            }
                            break;

                    }

                    char_literal58=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall668); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal58);



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
                    // 103:34: -> ^( FUNC_CALL List ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:103:37: ^( FUNC_CALL List ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_List.nextNode());
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
                    // grammar/PlazmaScript.g:104:6: Set '(' ( exprList )? ')'
                    {
                    Set59=(Token)match(input,Set,FOLLOW_Set_in_functionCall691); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Set.add(Set59);

                    char_literal60=(Token)match(input,OParen,FOLLOW_OParen_in_functionCall693); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal60);

                    // grammar/PlazmaScript.g:104:14: ( exprList )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( ((LA14_0>=Identifier && LA14_0<=Set)||(LA14_0>=Integer && LA14_0<=String)||(LA14_0>=Not && LA14_0<=NotWord)||(LA14_0>=Add && LA14_0<=Subtract)||LA14_0==OBrace||LA14_0==OBracket||LA14_0==OParen||LA14_0==ContextIdentifier) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall695);
                            exprList61=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList61.getTree());

                            }
                            break;

                    }

                    char_literal62=(Token)match(input,CParen,FOLLOW_CParen_in_functionCall698); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal62);



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
                    // 104:33: -> ^( FUNC_CALL Set ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:104:36: ^( FUNC_CALL Set ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);

                        adaptor.addChild(root_1, stream_Set.nextNode());
                        // grammar/PlazmaScript.g:104:52: ( exprList )?
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
    // grammar/PlazmaScript.g:111:1: ifStatement : ifStat ( elseIfStat )* ( elseStat )? -> ^( IF ifStat ( elseIfStat )* ( elseStat )? ) ;
    public final PlazmaScriptParser.ifStatement_return ifStatement() throws RecognitionException {
        PlazmaScriptParser.ifStatement_return retval = new PlazmaScriptParser.ifStatement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        PlazmaScriptParser.ifStat_return ifStat63 = null;

        PlazmaScriptParser.elseIfStat_return elseIfStat64 = null;

        PlazmaScriptParser.elseStat_return elseStat65 = null;


        RewriteRuleSubtreeStream stream_elseIfStat=new RewriteRuleSubtreeStream(adaptor,"rule elseIfStat");
        RewriteRuleSubtreeStream stream_ifStat=new RewriteRuleSubtreeStream(adaptor,"rule ifStat");
        RewriteRuleSubtreeStream stream_elseStat=new RewriteRuleSubtreeStream(adaptor,"rule elseStat");
        try {
            // grammar/PlazmaScript.g:112:3: ( ifStat ( elseIfStat )* ( elseStat )? -> ^( IF ifStat ( elseIfStat )* ( elseStat )? ) )
            // grammar/PlazmaScript.g:112:6: ifStat ( elseIfStat )* ( elseStat )?
            {
            pushFollow(FOLLOW_ifStat_in_ifStatement736);
            ifStat63=ifStat();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_ifStat.add(ifStat63.getTree());
            // grammar/PlazmaScript.g:112:13: ( elseIfStat )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==Else) ) {
                    int LA16_1 = input.LA(2);

                    if ( (LA16_1==If) ) {
                        alt16=1;
                    }


                }


                switch (alt16) {
            	case 1 :
            	    // grammar/PlazmaScript.g:0:0: elseIfStat
            	    {
            	    pushFollow(FOLLOW_elseIfStat_in_ifStatement738);
            	    elseIfStat64=elseIfStat();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_elseIfStat.add(elseIfStat64.getTree());

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            // grammar/PlazmaScript.g:112:25: ( elseStat )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==Else) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // grammar/PlazmaScript.g:0:0: elseStat
                    {
                    pushFollow(FOLLOW_elseStat_in_ifStatement741);
                    elseStat65=elseStat();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_elseStat.add(elseStat65.getTree());

                    }
                    break;

            }



            // AST REWRITE
            // elements: ifStat, elseIfStat, elseStat
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 112:35: -> ^( IF ifStat ( elseIfStat )* ( elseStat )? )
            {
                // grammar/PlazmaScript.g:112:38: ^( IF ifStat ( elseIfStat )* ( elseStat )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(IF, "IF"), root_1);

                adaptor.addChild(root_1, stream_ifStat.nextTree());
                // grammar/PlazmaScript.g:112:50: ( elseIfStat )*
                while ( stream_elseIfStat.hasNext() ) {
                    adaptor.addChild(root_1, stream_elseIfStat.nextTree());

                }
                stream_elseIfStat.reset();
                // grammar/PlazmaScript.g:112:62: ( elseStat )?
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
    // grammar/PlazmaScript.g:115:1: ifStat : If '(' expression ')' '{' block '}' -> ^( EXP expression block ) ;
    public final PlazmaScriptParser.ifStat_return ifStat() throws RecognitionException {
        PlazmaScriptParser.ifStat_return retval = new PlazmaScriptParser.ifStat_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token If66=null;
        Token char_literal67=null;
        Token char_literal69=null;
        Token char_literal70=null;
        Token char_literal72=null;
        PlazmaScriptParser.expression_return expression68 = null;

        PlazmaScriptParser.block_return block71 = null;


        Object If66_tree=null;
        Object char_literal67_tree=null;
        Object char_literal69_tree=null;
        Object char_literal70_tree=null;
        Object char_literal72_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_If=new RewriteRuleTokenStream(adaptor,"token If");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:116:3: ( If '(' expression ')' '{' block '}' -> ^( EXP expression block ) )
            // grammar/PlazmaScript.g:116:6: If '(' expression ')' '{' block '}'
            {
            If66=(Token)match(input,If,FOLLOW_If_in_ifStat770); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_If.add(If66);

            char_literal67=(Token)match(input,OParen,FOLLOW_OParen_in_ifStat772); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal67);

            pushFollow(FOLLOW_expression_in_ifStat774);
            expression68=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression68.getTree());
            char_literal69=(Token)match(input,CParen,FOLLOW_CParen_in_ifStat776); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal69);

            char_literal70=(Token)match(input,OBrace,FOLLOW_OBrace_in_ifStat778); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal70);

            pushFollow(FOLLOW_block_in_ifStat780);
            block71=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block71.getTree());
            char_literal72=(Token)match(input,CBrace,FOLLOW_CBrace_in_ifStat782); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal72);



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
            // 116:42: -> ^( EXP expression block )
            {
                // grammar/PlazmaScript.g:116:45: ^( EXP expression block )
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
    // grammar/PlazmaScript.g:119:1: elseIfStat : Else If '(' expression ')' '{' block '}' -> ^( EXP expression block ) ;
    public final PlazmaScriptParser.elseIfStat_return elseIfStat() throws RecognitionException {
        PlazmaScriptParser.elseIfStat_return retval = new PlazmaScriptParser.elseIfStat_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Else73=null;
        Token If74=null;
        Token char_literal75=null;
        Token char_literal77=null;
        Token char_literal78=null;
        Token char_literal80=null;
        PlazmaScriptParser.expression_return expression76 = null;

        PlazmaScriptParser.block_return block79 = null;


        Object Else73_tree=null;
        Object If74_tree=null;
        Object char_literal75_tree=null;
        Object char_literal77_tree=null;
        Object char_literal78_tree=null;
        Object char_literal80_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_Else=new RewriteRuleTokenStream(adaptor,"token Else");
        RewriteRuleTokenStream stream_If=new RewriteRuleTokenStream(adaptor,"token If");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:120:3: ( Else If '(' expression ')' '{' block '}' -> ^( EXP expression block ) )
            // grammar/PlazmaScript.g:120:6: Else If '(' expression ')' '{' block '}'
            {
            Else73=(Token)match(input,Else,FOLLOW_Else_in_elseIfStat806); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Else.add(Else73);

            If74=(Token)match(input,If,FOLLOW_If_in_elseIfStat808); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_If.add(If74);

            char_literal75=(Token)match(input,OParen,FOLLOW_OParen_in_elseIfStat810); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal75);

            pushFollow(FOLLOW_expression_in_elseIfStat812);
            expression76=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression76.getTree());
            char_literal77=(Token)match(input,CParen,FOLLOW_CParen_in_elseIfStat814); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal77);

            char_literal78=(Token)match(input,OBrace,FOLLOW_OBrace_in_elseIfStat816); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal78);

            pushFollow(FOLLOW_block_in_elseIfStat818);
            block79=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block79.getTree());
            char_literal80=(Token)match(input,CBrace,FOLLOW_CBrace_in_elseIfStat820); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal80);



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
            // 120:47: -> ^( EXP expression block )
            {
                // grammar/PlazmaScript.g:120:50: ^( EXP expression block )
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
    // grammar/PlazmaScript.g:123:1: elseStat : Else '{' block '}' -> ^( EXP block ) ;
    public final PlazmaScriptParser.elseStat_return elseStat() throws RecognitionException {
        PlazmaScriptParser.elseStat_return retval = new PlazmaScriptParser.elseStat_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Else81=null;
        Token char_literal82=null;
        Token char_literal84=null;
        PlazmaScriptParser.block_return block83 = null;


        Object Else81_tree=null;
        Object char_literal82_tree=null;
        Object char_literal84_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_Else=new RewriteRuleTokenStream(adaptor,"token Else");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:124:3: ( Else '{' block '}' -> ^( EXP block ) )
            // grammar/PlazmaScript.g:124:6: Else '{' block '}'
            {
            Else81=(Token)match(input,Else,FOLLOW_Else_in_elseStat844); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Else.add(Else81);

            char_literal82=(Token)match(input,OBrace,FOLLOW_OBrace_in_elseStat846); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal82);

            pushFollow(FOLLOW_block_in_elseStat848);
            block83=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block83.getTree());
            char_literal84=(Token)match(input,CBrace,FOLLOW_CBrace_in_elseStat850); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal84);



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
            // 124:25: -> ^( EXP block )
            {
                // grammar/PlazmaScript.g:124:28: ^( EXP block )
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
    // grammar/PlazmaScript.g:127:1: variableDef : Var ;
    public final PlazmaScriptParser.variableDef_return variableDef() throws RecognitionException {
        PlazmaScriptParser.variableDef_return retval = new PlazmaScriptParser.variableDef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Var85=null;

        Object Var85_tree=null;

        try {
            // grammar/PlazmaScript.g:128:2: ( Var )
            // grammar/PlazmaScript.g:128:4: Var
            {
            root_0 = (Object)adaptor.nil();

            Var85=(Token)match(input,Var,FOLLOW_Var_in_variableDef870); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Var85_tree = (Object)adaptor.create(Var85);
            adaptor.addChild(root_0, Var85_tree);
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
    // grammar/PlazmaScript.g:145:1: functionDecl : Def Identifier '(' ( idList )? ')' '{' block '}' ;
    public final PlazmaScriptParser.functionDecl_return functionDecl() throws RecognitionException {
        PlazmaScriptParser.functionDecl_return retval = new PlazmaScriptParser.functionDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Def86=null;
        Token Identifier87=null;
        Token char_literal88=null;
        Token char_literal90=null;
        Token char_literal91=null;
        Token char_literal93=null;
        PlazmaScriptParser.idList_return idList89 = null;

        PlazmaScriptParser.block_return block92 = null;


        Object Def86_tree=null;
        Object Identifier87_tree=null;
        Object char_literal88_tree=null;
        Object char_literal90_tree=null;
        Object char_literal91_tree=null;
        Object char_literal93_tree=null;

        try {
            // grammar/PlazmaScript.g:146:3: ( Def Identifier '(' ( idList )? ')' '{' block '}' )
            // grammar/PlazmaScript.g:146:6: Def Identifier '(' ( idList )? ')' '{' block '}'
            {
            root_0 = (Object)adaptor.nil();

            Def86=(Token)match(input,Def,FOLLOW_Def_in_functionDecl902); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Def86_tree = (Object)adaptor.create(Def86);
            adaptor.addChild(root_0, Def86_tree);
            }
            Identifier87=(Token)match(input,Identifier,FOLLOW_Identifier_in_functionDecl904); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier87_tree = (Object)adaptor.create(Identifier87);
            adaptor.addChild(root_0, Identifier87_tree);
            }
            char_literal88=(Token)match(input,OParen,FOLLOW_OParen_in_functionDecl906); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal88_tree = (Object)adaptor.create(char_literal88);
            adaptor.addChild(root_0, char_literal88_tree);
            }
            // grammar/PlazmaScript.g:146:25: ( idList )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==Identifier) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // grammar/PlazmaScript.g:0:0: idList
                    {
                    pushFollow(FOLLOW_idList_in_functionDecl908);
                    idList89=idList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, idList89.getTree());

                    }
                    break;

            }

            char_literal90=(Token)match(input,CParen,FOLLOW_CParen_in_functionDecl911); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal90_tree = (Object)adaptor.create(char_literal90);
            adaptor.addChild(root_0, char_literal90_tree);
            }
            char_literal91=(Token)match(input,OBrace,FOLLOW_OBrace_in_functionDecl913); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal91_tree = (Object)adaptor.create(char_literal91);
            adaptor.addChild(root_0, char_literal91_tree);
            }
            pushFollow(FOLLOW_block_in_functionDecl915);
            block92=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, block92.getTree());
            char_literal93=(Token)match(input,CBrace,FOLLOW_CBrace_in_functionDecl917); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal93_tree = (Object)adaptor.create(char_literal93);
            adaptor.addChild(root_0, char_literal93_tree);
            }
            if ( state.backtracking==0 ) {
              defineFunction((Identifier87!=null?Identifier87.getText():null), (idList89!=null?((Object)idList89.tree):null), (block92!=null?((Object)block92.tree):null));
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
    // grammar/PlazmaScript.g:155:1: forStatement : For '(' Identifier 'in' expression ')' '{' block '}' -> ^( For Identifier expression block ) ;
    public final PlazmaScriptParser.forStatement_return forStatement() throws RecognitionException {
        PlazmaScriptParser.forStatement_return retval = new PlazmaScriptParser.forStatement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token For94=null;
        Token char_literal95=null;
        Token Identifier96=null;
        Token string_literal97=null;
        Token char_literal99=null;
        Token char_literal100=null;
        Token char_literal102=null;
        PlazmaScriptParser.expression_return expression98 = null;

        PlazmaScriptParser.block_return block101 = null;


        Object For94_tree=null;
        Object char_literal95_tree=null;
        Object Identifier96_tree=null;
        Object string_literal97_tree=null;
        Object char_literal99_tree=null;
        Object char_literal100_tree=null;
        Object char_literal102_tree=null;
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
            // grammar/PlazmaScript.g:156:3: ( For '(' Identifier 'in' expression ')' '{' block '}' -> ^( For Identifier expression block ) )
            // grammar/PlazmaScript.g:156:6: For '(' Identifier 'in' expression ')' '{' block '}'
            {
            For94=(Token)match(input,For,FOLLOW_For_in_forStatement946); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_For.add(For94);

            char_literal95=(Token)match(input,OParen,FOLLOW_OParen_in_forStatement948); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal95);

            Identifier96=(Token)match(input,Identifier,FOLLOW_Identifier_in_forStatement950); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Identifier.add(Identifier96);

            string_literal97=(Token)match(input,In,FOLLOW_In_in_forStatement952); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_In.add(string_literal97);

            pushFollow(FOLLOW_expression_in_forStatement954);
            expression98=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression98.getTree());
            char_literal99=(Token)match(input,CParen,FOLLOW_CParen_in_forStatement956); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal99);

            char_literal100=(Token)match(input,OBrace,FOLLOW_OBrace_in_forStatement958); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal100);

            pushFollow(FOLLOW_block_in_forStatement960);
            block101=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block101.getTree());
            char_literal102=(Token)match(input,CBrace,FOLLOW_CBrace_in_forStatement962); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal102);



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
            // 157:6: -> ^( For Identifier expression block )
            {
                // grammar/PlazmaScript.g:157:9: ^( For Identifier expression block )
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
    // grammar/PlazmaScript.g:161:1: whileStatement : While '(' expression ')' '{' block '}' -> ^( While expression block ) ;
    public final PlazmaScriptParser.whileStatement_return whileStatement() throws RecognitionException {
        PlazmaScriptParser.whileStatement_return retval = new PlazmaScriptParser.whileStatement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token While103=null;
        Token char_literal104=null;
        Token char_literal106=null;
        Token char_literal107=null;
        Token char_literal109=null;
        PlazmaScriptParser.expression_return expression105 = null;

        PlazmaScriptParser.block_return block108 = null;


        Object While103_tree=null;
        Object char_literal104_tree=null;
        Object char_literal106_tree=null;
        Object char_literal107_tree=null;
        Object char_literal109_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_While=new RewriteRuleTokenStream(adaptor,"token While");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // grammar/PlazmaScript.g:162:3: ( While '(' expression ')' '{' block '}' -> ^( While expression block ) )
            // grammar/PlazmaScript.g:162:6: While '(' expression ')' '{' block '}'
            {
            While103=(Token)match(input,While,FOLLOW_While_in_whileStatement997); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_While.add(While103);

            char_literal104=(Token)match(input,OParen,FOLLOW_OParen_in_whileStatement999); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OParen.add(char_literal104);

            pushFollow(FOLLOW_expression_in_whileStatement1001);
            expression105=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression105.getTree());
            char_literal106=(Token)match(input,CParen,FOLLOW_CParen_in_whileStatement1003); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CParen.add(char_literal106);

            char_literal107=(Token)match(input,OBrace,FOLLOW_OBrace_in_whileStatement1005); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal107);

            pushFollow(FOLLOW_block_in_whileStatement1007);
            block108=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block108.getTree());
            char_literal109=(Token)match(input,CBrace,FOLLOW_CBrace_in_whileStatement1009); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal109);



            // AST REWRITE
            // elements: While, expression, block
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 162:45: -> ^( While expression block )
            {
                // grammar/PlazmaScript.g:162:48: ^( While expression block )
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
    // grammar/PlazmaScript.g:165:1: idList : Identifier ( ',' Identifier )* -> ^( ID_LIST ( Identifier )+ ) ;
    public final PlazmaScriptParser.idList_return idList() throws RecognitionException {
        PlazmaScriptParser.idList_return retval = new PlazmaScriptParser.idList_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Identifier110=null;
        Token char_literal111=null;
        Token Identifier112=null;

        Object Identifier110_tree=null;
        Object char_literal111_tree=null;
        Object Identifier112_tree=null;
        RewriteRuleTokenStream stream_Comma=new RewriteRuleTokenStream(adaptor,"token Comma");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");

        try {
            // grammar/PlazmaScript.g:166:3: ( Identifier ( ',' Identifier )* -> ^( ID_LIST ( Identifier )+ ) )
            // grammar/PlazmaScript.g:166:6: Identifier ( ',' Identifier )*
            {
            Identifier110=(Token)match(input,Identifier,FOLLOW_Identifier_in_idList1033); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Identifier.add(Identifier110);

            // grammar/PlazmaScript.g:166:17: ( ',' Identifier )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==Comma) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // grammar/PlazmaScript.g:166:18: ',' Identifier
            	    {
            	    char_literal111=(Token)match(input,Comma,FOLLOW_Comma_in_idList1036); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Comma.add(char_literal111);

            	    Identifier112=(Token)match(input,Identifier,FOLLOW_Identifier_in_idList1038); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Identifier.add(Identifier112);


            	    }
            	    break;

            	default :
            	    break loop19;
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
            // 166:35: -> ^( ID_LIST ( Identifier )+ )
            {
                // grammar/PlazmaScript.g:166:38: ^( ID_LIST ( Identifier )+ )
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
    // grammar/PlazmaScript.g:169:1: exprList : expression ( ',' expression )* -> ^( EXP_LIST ( expression )+ ) ;
    public final PlazmaScriptParser.exprList_return exprList() throws RecognitionException {
        PlazmaScriptParser.exprList_return retval = new PlazmaScriptParser.exprList_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal114=null;
        PlazmaScriptParser.expression_return expression113 = null;

        PlazmaScriptParser.expression_return expression115 = null;


        Object char_literal114_tree=null;
        RewriteRuleTokenStream stream_Comma=new RewriteRuleTokenStream(adaptor,"token Comma");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // grammar/PlazmaScript.g:170:3: ( expression ( ',' expression )* -> ^( EXP_LIST ( expression )+ ) )
            // grammar/PlazmaScript.g:170:6: expression ( ',' expression )*
            {
            pushFollow(FOLLOW_expression_in_exprList1063);
            expression113=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression113.getTree());
            // grammar/PlazmaScript.g:170:17: ( ',' expression )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==Comma) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // grammar/PlazmaScript.g:170:18: ',' expression
            	    {
            	    char_literal114=(Token)match(input,Comma,FOLLOW_Comma_in_exprList1066); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Comma.add(char_literal114);

            	    pushFollow(FOLLOW_expression_in_exprList1068);
            	    expression115=expression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_expression.add(expression115.getTree());

            	    }
            	    break;

            	default :
            	    break loop20;
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
            // 170:35: -> ^( EXP_LIST ( expression )+ )
            {
                // grammar/PlazmaScript.g:170:38: ^( EXP_LIST ( expression )+ )
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
    // grammar/PlazmaScript.g:173:1: exprPair : ( expression ':' expression ) -> ^( EXP_PAIR expression expression ) ;
    public final PlazmaScriptParser.exprPair_return exprPair() throws RecognitionException {
        PlazmaScriptParser.exprPair_return retval = new PlazmaScriptParser.exprPair_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal117=null;
        PlazmaScriptParser.expression_return expression116 = null;

        PlazmaScriptParser.expression_return expression118 = null;


        Object char_literal117_tree=null;
        RewriteRuleTokenStream stream_Colon=new RewriteRuleTokenStream(adaptor,"token Colon");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // grammar/PlazmaScript.g:174:3: ( ( expression ':' expression ) -> ^( EXP_PAIR expression expression ) )
            // grammar/PlazmaScript.g:174:6: ( expression ':' expression )
            {
            // grammar/PlazmaScript.g:174:6: ( expression ':' expression )
            // grammar/PlazmaScript.g:174:7: expression ':' expression
            {
            pushFollow(FOLLOW_expression_in_exprPair1094);
            expression116=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression116.getTree());
            char_literal117=(Token)match(input,Colon,FOLLOW_Colon_in_exprPair1096); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Colon.add(char_literal117);

            pushFollow(FOLLOW_expression_in_exprPair1098);
            expression118=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression118.getTree());

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
            // 174:34: -> ^( EXP_PAIR expression expression )
            {
                // grammar/PlazmaScript.g:174:37: ^( EXP_PAIR expression expression )
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
    // grammar/PlazmaScript.g:177:1: exprMap : exprPair ( ',' exprPair )* -> ^( EXP_MAP ( exprPair )+ ) ;
    public final PlazmaScriptParser.exprMap_return exprMap() throws RecognitionException {
        PlazmaScriptParser.exprMap_return retval = new PlazmaScriptParser.exprMap_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal120=null;
        PlazmaScriptParser.exprPair_return exprPair119 = null;

        PlazmaScriptParser.exprPair_return exprPair121 = null;


        Object char_literal120_tree=null;
        RewriteRuleTokenStream stream_Comma=new RewriteRuleTokenStream(adaptor,"token Comma");
        RewriteRuleSubtreeStream stream_exprPair=new RewriteRuleSubtreeStream(adaptor,"rule exprPair");
        try {
            // grammar/PlazmaScript.g:178:3: ( exprPair ( ',' exprPair )* -> ^( EXP_MAP ( exprPair )+ ) )
            // grammar/PlazmaScript.g:178:6: exprPair ( ',' exprPair )*
            {
            pushFollow(FOLLOW_exprPair_in_exprMap1123);
            exprPair119=exprPair();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_exprPair.add(exprPair119.getTree());
            // grammar/PlazmaScript.g:178:15: ( ',' exprPair )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==Comma) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // grammar/PlazmaScript.g:178:16: ',' exprPair
            	    {
            	    char_literal120=(Token)match(input,Comma,FOLLOW_Comma_in_exprMap1126); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Comma.add(char_literal120);

            	    pushFollow(FOLLOW_exprPair_in_exprMap1128);
            	    exprPair121=exprPair();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_exprPair.add(exprPair121.getTree());

            	    }
            	    break;

            	default :
            	    break loop21;
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
            // 178:31: -> ^( EXP_MAP ( exprPair )+ )
            {
                // grammar/PlazmaScript.g:178:34: ^( EXP_MAP ( exprPair )+ )
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
    // grammar/PlazmaScript.g:182:1: expression : condExpr ;
    public final PlazmaScriptParser.expression_return expression() throws RecognitionException {
        PlazmaScriptParser.expression_return retval = new PlazmaScriptParser.expression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        PlazmaScriptParser.condExpr_return condExpr122 = null;



        try {
            // grammar/PlazmaScript.g:183:3: ( condExpr )
            // grammar/PlazmaScript.g:183:6: condExpr
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_condExpr_in_expression1154);
            condExpr122=condExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, condExpr122.getTree());

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
    // grammar/PlazmaScript.g:186:1: condExpr : ( startExpr -> startExpr ) ( '?' a= expression ':' b= expression -> ^( TERNARY startExpr $a $b) | In expression -> ^( In startExpr expression ) | RangeE expression -> ^( RangeE startExpr expression ) | Range expression -> ^( Range startExpr expression ) )? ;
    public final PlazmaScriptParser.condExpr_return condExpr() throws RecognitionException {
        PlazmaScriptParser.condExpr_return retval = new PlazmaScriptParser.condExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal124=null;
        Token char_literal125=null;
        Token In126=null;
        Token RangeE128=null;
        Token Range130=null;
        PlazmaScriptParser.expression_return a = null;

        PlazmaScriptParser.expression_return b = null;

        PlazmaScriptParser.startExpr_return startExpr123 = null;

        PlazmaScriptParser.expression_return expression127 = null;

        PlazmaScriptParser.expression_return expression129 = null;

        PlazmaScriptParser.expression_return expression131 = null;


        Object char_literal124_tree=null;
        Object char_literal125_tree=null;
        Object In126_tree=null;
        Object RangeE128_tree=null;
        Object Range130_tree=null;
        RewriteRuleTokenStream stream_RangeE=new RewriteRuleTokenStream(adaptor,"token RangeE");
        RewriteRuleTokenStream stream_Range=new RewriteRuleTokenStream(adaptor,"token Range");
        RewriteRuleTokenStream stream_In=new RewriteRuleTokenStream(adaptor,"token In");
        RewriteRuleTokenStream stream_Colon=new RewriteRuleTokenStream(adaptor,"token Colon");
        RewriteRuleTokenStream stream_QMark=new RewriteRuleTokenStream(adaptor,"token QMark");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_startExpr=new RewriteRuleSubtreeStream(adaptor,"rule startExpr");
        try {
            // grammar/PlazmaScript.g:187:3: ( ( startExpr -> startExpr ) ( '?' a= expression ':' b= expression -> ^( TERNARY startExpr $a $b) | In expression -> ^( In startExpr expression ) | RangeE expression -> ^( RangeE startExpr expression ) | Range expression -> ^( Range startExpr expression ) )? )
            // grammar/PlazmaScript.g:187:6: ( startExpr -> startExpr ) ( '?' a= expression ':' b= expression -> ^( TERNARY startExpr $a $b) | In expression -> ^( In startExpr expression ) | RangeE expression -> ^( RangeE startExpr expression ) | Range expression -> ^( Range startExpr expression ) )?
            {
            // grammar/PlazmaScript.g:187:6: ( startExpr -> startExpr )
            // grammar/PlazmaScript.g:187:7: startExpr
            {
            pushFollow(FOLLOW_startExpr_in_condExpr1169);
            startExpr123=startExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_startExpr.add(startExpr123.getTree());


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
            // 187:17: -> startExpr
            {
                adaptor.addChild(root_0, stream_startExpr.nextTree());

            }

            retval.tree = root_0;}
            }

            // grammar/PlazmaScript.g:188:6: ( '?' a= expression ':' b= expression -> ^( TERNARY startExpr $a $b) | In expression -> ^( In startExpr expression ) | RangeE expression -> ^( RangeE startExpr expression ) | Range expression -> ^( Range startExpr expression ) )?
            int alt22=5;
            switch ( input.LA(1) ) {
                case QMark:
                    {
                    alt22=1;
                    }
                    break;
                case In:
                    {
                    alt22=2;
                    }
                    break;
                case RangeE:
                    {
                    alt22=3;
                    }
                    break;
                case Range:
                    {
                    alt22=4;
                    }
                    break;
            }

            switch (alt22) {
                case 1 :
                    // grammar/PlazmaScript.g:188:8: '?' a= expression ':' b= expression
                    {
                    char_literal124=(Token)match(input,QMark,FOLLOW_QMark_in_condExpr1184); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_QMark.add(char_literal124);

                    pushFollow(FOLLOW_expression_in_condExpr1188);
                    a=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(a.getTree());
                    char_literal125=(Token)match(input,Colon,FOLLOW_Colon_in_condExpr1190); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Colon.add(char_literal125);

                    pushFollow(FOLLOW_expression_in_condExpr1194);
                    b=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(b.getTree());


                    // AST REWRITE
                    // elements: startExpr, a, b
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
                    // 188:42: -> ^( TERNARY startExpr $a $b)
                    {
                        // grammar/PlazmaScript.g:188:45: ^( TERNARY startExpr $a $b)
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
                    // grammar/PlazmaScript.g:189:8: In expression
                    {
                    In126=(Token)match(input,In,FOLLOW_In_in_condExpr1217); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_In.add(In126);

                    pushFollow(FOLLOW_expression_in_condExpr1219);
                    expression127=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression127.getTree());


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
                    // 189:42: -> ^( In startExpr expression )
                    {
                        // grammar/PlazmaScript.g:189:45: ^( In startExpr expression )
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
                    // grammar/PlazmaScript.g:191:8: RangeE expression
                    {
                    RangeE128=(Token)match(input,RangeE,FOLLOW_RangeE_in_condExpr1264); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RangeE.add(RangeE128);

                    pushFollow(FOLLOW_expression_in_condExpr1266);
                    expression129=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression129.getTree());


                    // AST REWRITE
                    // elements: startExpr, expression, RangeE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 191:42: -> ^( RangeE startExpr expression )
                    {
                        // grammar/PlazmaScript.g:191:45: ^( RangeE startExpr expression )
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
                    // grammar/PlazmaScript.g:192:8: Range expression
                    {
                    Range130=(Token)match(input,Range,FOLLOW_Range_in_condExpr1301); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Range.add(Range130);

                    pushFollow(FOLLOW_expression_in_condExpr1303);
                    expression131=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression131.getTree());


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
                    // 192:42: -> ^( Range startExpr expression )
                    {
                        // grammar/PlazmaScript.g:192:45: ^( Range startExpr expression )
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
    // grammar/PlazmaScript.g:197:1: startExpr : orExpr ;
    public final PlazmaScriptParser.startExpr_return startExpr() throws RecognitionException {
        PlazmaScriptParser.startExpr_return retval = new PlazmaScriptParser.startExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        PlazmaScriptParser.orExpr_return orExpr132 = null;



        try {
            // grammar/PlazmaScript.g:198:2: ( orExpr )
            // grammar/PlazmaScript.g:198:4: orExpr
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_orExpr_in_startExpr1366);
            orExpr132=orExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, orExpr132.getTree());

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
    // grammar/PlazmaScript.g:201:1: orExpr : andExpr ( ( 'xor' | '||' | '|' | 'or' ) andExpr )* ;
    public final PlazmaScriptParser.orExpr_return orExpr() throws RecognitionException {
        PlazmaScriptParser.orExpr_return retval = new PlazmaScriptParser.orExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set134=null;
        PlazmaScriptParser.andExpr_return andExpr133 = null;

        PlazmaScriptParser.andExpr_return andExpr135 = null;


        Object set134_tree=null;

        try {
            // grammar/PlazmaScript.g:202:3: ( andExpr ( ( 'xor' | '||' | '|' | 'or' ) andExpr )* )
            // grammar/PlazmaScript.g:202:6: andExpr ( ( 'xor' | '||' | '|' | 'or' ) andExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_andExpr_in_orExpr1379);
            andExpr133=andExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, andExpr133.getTree());
            // grammar/PlazmaScript.g:202:14: ( ( 'xor' | '||' | '|' | 'or' ) andExpr )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( ((LA23_0>=XorWord && LA23_0<=OrWord)) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // grammar/PlazmaScript.g:202:15: ( 'xor' | '||' | '|' | 'or' ) andExpr
            	    {
            	    set134=(Token)input.LT(1);
            	    set134=(Token)input.LT(1);
            	    if ( (input.LA(1)>=XorWord && input.LA(1)<=OrWord) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set134), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_andExpr_in_orExpr1399);
            	    andExpr135=andExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, andExpr135.getTree());

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
    // $ANTLR end "orExpr"

    public static class andExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "andExpr"
    // grammar/PlazmaScript.g:206:1: andExpr : equExpr ( ( '&&' | '&' | 'and' ) equExpr )* ;
    public final PlazmaScriptParser.andExpr_return andExpr() throws RecognitionException {
        PlazmaScriptParser.andExpr_return retval = new PlazmaScriptParser.andExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set137=null;
        PlazmaScriptParser.equExpr_return equExpr136 = null;

        PlazmaScriptParser.equExpr_return equExpr138 = null;


        Object set137_tree=null;

        try {
            // grammar/PlazmaScript.g:207:3: ( equExpr ( ( '&&' | '&' | 'and' ) equExpr )* )
            // grammar/PlazmaScript.g:207:6: equExpr ( ( '&&' | '&' | 'and' ) equExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_equExpr_in_andExpr1416);
            equExpr136=equExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, equExpr136.getTree());
            // grammar/PlazmaScript.g:207:14: ( ( '&&' | '&' | 'and' ) equExpr )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( ((LA24_0>=And && LA24_0<=AndWord)) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // grammar/PlazmaScript.g:207:15: ( '&&' | '&' | 'and' ) equExpr
            	    {
            	    set137=(Token)input.LT(1);
            	    set137=(Token)input.LT(1);
            	    if ( (input.LA(1)>=And && input.LA(1)<=AndWord) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set137), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_equExpr_in_andExpr1432);
            	    equExpr138=equExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, equExpr138.getTree());

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
    // $ANTLR end "andExpr"

    public static class equExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "equExpr"
    // grammar/PlazmaScript.g:210:1: equExpr : relExpr ( ( '==' | '!=' ) relExpr )* ;
    public final PlazmaScriptParser.equExpr_return equExpr() throws RecognitionException {
        PlazmaScriptParser.equExpr_return retval = new PlazmaScriptParser.equExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set140=null;
        PlazmaScriptParser.relExpr_return relExpr139 = null;

        PlazmaScriptParser.relExpr_return relExpr141 = null;


        Object set140_tree=null;

        try {
            // grammar/PlazmaScript.g:211:3: ( relExpr ( ( '==' | '!=' ) relExpr )* )
            // grammar/PlazmaScript.g:211:6: relExpr ( ( '==' | '!=' ) relExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_relExpr_in_equExpr1448);
            relExpr139=relExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, relExpr139.getTree());
            // grammar/PlazmaScript.g:211:14: ( ( '==' | '!=' ) relExpr )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( ((LA25_0>=Equals && LA25_0<=NEquals)) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // grammar/PlazmaScript.g:211:15: ( '==' | '!=' ) relExpr
            	    {
            	    set140=(Token)input.LT(1);
            	    set140=(Token)input.LT(1);
            	    if ( (input.LA(1)>=Equals && input.LA(1)<=NEquals) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set140), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_relExpr_in_equExpr1460);
            	    relExpr141=relExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, relExpr141.getTree());

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
    // $ANTLR end "equExpr"

    public static class relExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "relExpr"
    // grammar/PlazmaScript.g:214:1: relExpr : addExpr ( ( '>=' | '<=' | '>' | '<' ) addExpr )* ;
    public final PlazmaScriptParser.relExpr_return relExpr() throws RecognitionException {
        PlazmaScriptParser.relExpr_return retval = new PlazmaScriptParser.relExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set143=null;
        PlazmaScriptParser.addExpr_return addExpr142 = null;

        PlazmaScriptParser.addExpr_return addExpr144 = null;


        Object set143_tree=null;

        try {
            // grammar/PlazmaScript.g:215:3: ( addExpr ( ( '>=' | '<=' | '>' | '<' ) addExpr )* )
            // grammar/PlazmaScript.g:215:6: addExpr ( ( '>=' | '<=' | '>' | '<' ) addExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_addExpr_in_relExpr1476);
            addExpr142=addExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, addExpr142.getTree());
            // grammar/PlazmaScript.g:215:14: ( ( '>=' | '<=' | '>' | '<' ) addExpr )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( ((LA26_0>=GTEquals && LA26_0<=LTEquals)||(LA26_0>=GT && LA26_0<=LT)) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // grammar/PlazmaScript.g:215:15: ( '>=' | '<=' | '>' | '<' ) addExpr
            	    {
            	    set143=(Token)input.LT(1);
            	    set143=(Token)input.LT(1);
            	    if ( (input.LA(1)>=GTEquals && input.LA(1)<=LTEquals)||(input.LA(1)>=GT && input.LA(1)<=LT) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set143), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_addExpr_in_relExpr1496);
            	    addExpr144=addExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, addExpr144.getTree());

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
    // $ANTLR end "relExpr"

    public static class addExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "addExpr"
    // grammar/PlazmaScript.g:218:1: addExpr : mulExpr ( ( '+' | '-' ) mulExpr )* ;
    public final PlazmaScriptParser.addExpr_return addExpr() throws RecognitionException {
        PlazmaScriptParser.addExpr_return retval = new PlazmaScriptParser.addExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set146=null;
        PlazmaScriptParser.mulExpr_return mulExpr145 = null;

        PlazmaScriptParser.mulExpr_return mulExpr147 = null;


        Object set146_tree=null;

        try {
            // grammar/PlazmaScript.g:219:3: ( mulExpr ( ( '+' | '-' ) mulExpr )* )
            // grammar/PlazmaScript.g:219:6: mulExpr ( ( '+' | '-' ) mulExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_mulExpr_in_addExpr1512);
            mulExpr145=mulExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, mulExpr145.getTree());
            // grammar/PlazmaScript.g:219:14: ( ( '+' | '-' ) mulExpr )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( ((LA27_0>=Add && LA27_0<=Subtract)) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // grammar/PlazmaScript.g:219:15: ( '+' | '-' ) mulExpr
            	    {
            	    set146=(Token)input.LT(1);
            	    set146=(Token)input.LT(1);
            	    if ( (input.LA(1)>=Add && input.LA(1)<=Subtract) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set146), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_mulExpr_in_addExpr1524);
            	    mulExpr147=mulExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, mulExpr147.getTree());

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
    // $ANTLR end "addExpr"

    public static class mulExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "mulExpr"
    // grammar/PlazmaScript.g:222:1: mulExpr : powExpr ( ( '*' | '/' | '%' ) powExpr )* ;
    public final PlazmaScriptParser.mulExpr_return mulExpr() throws RecognitionException {
        PlazmaScriptParser.mulExpr_return retval = new PlazmaScriptParser.mulExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set149=null;
        PlazmaScriptParser.powExpr_return powExpr148 = null;

        PlazmaScriptParser.powExpr_return powExpr150 = null;


        Object set149_tree=null;

        try {
            // grammar/PlazmaScript.g:223:3: ( powExpr ( ( '*' | '/' | '%' ) powExpr )* )
            // grammar/PlazmaScript.g:223:6: powExpr ( ( '*' | '/' | '%' ) powExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_powExpr_in_mulExpr1540);
            powExpr148=powExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, powExpr148.getTree());
            // grammar/PlazmaScript.g:223:14: ( ( '*' | '/' | '%' ) powExpr )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( ((LA28_0>=Multiply && LA28_0<=Modulus)) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // grammar/PlazmaScript.g:223:15: ( '*' | '/' | '%' ) powExpr
            	    {
            	    set149=(Token)input.LT(1);
            	    set149=(Token)input.LT(1);
            	    if ( (input.LA(1)>=Multiply && input.LA(1)<=Modulus) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set149), root_0);
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_powExpr_in_mulExpr1556);
            	    powExpr150=powExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, powExpr150.getTree());

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
    // $ANTLR end "mulExpr"

    public static class powExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "powExpr"
    // grammar/PlazmaScript.g:226:1: powExpr : unaryExpr ( '^' unaryExpr )* ;
    public final PlazmaScriptParser.powExpr_return powExpr() throws RecognitionException {
        PlazmaScriptParser.powExpr_return retval = new PlazmaScriptParser.powExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal152=null;
        PlazmaScriptParser.unaryExpr_return unaryExpr151 = null;

        PlazmaScriptParser.unaryExpr_return unaryExpr153 = null;


        Object char_literal152_tree=null;

        try {
            // grammar/PlazmaScript.g:227:3: ( unaryExpr ( '^' unaryExpr )* )
            // grammar/PlazmaScript.g:227:6: unaryExpr ( '^' unaryExpr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_unaryExpr_in_powExpr1572);
            unaryExpr151=unaryExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpr151.getTree());
            // grammar/PlazmaScript.g:227:16: ( '^' unaryExpr )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==Pow) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // grammar/PlazmaScript.g:227:17: '^' unaryExpr
            	    {
            	    char_literal152=(Token)match(input,Pow,FOLLOW_Pow_in_powExpr1575); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal152_tree = (Object)adaptor.create(char_literal152);
            	    root_0 = (Object)adaptor.becomeRoot(char_literal152_tree, root_0);
            	    }
            	    pushFollow(FOLLOW_unaryExpr_in_powExpr1578);
            	    unaryExpr153=unaryExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpr153.getTree());

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
    // $ANTLR end "powExpr"

    public static class unaryExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "unaryExpr"
    // grammar/PlazmaScript.g:230:1: unaryExpr : ( '+' atom -> ^( UNARY_PLUS atom ) | '-' atom -> ^( UNARY_MIN atom ) | '!' atom -> ^( NEGATE atom ) | 'not' atom -> ^( NEGATE atom ) | atom );
    public final PlazmaScriptParser.unaryExpr_return unaryExpr() throws RecognitionException {
        PlazmaScriptParser.unaryExpr_return retval = new PlazmaScriptParser.unaryExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal154=null;
        Token char_literal156=null;
        Token char_literal158=null;
        Token string_literal160=null;
        PlazmaScriptParser.atom_return atom155 = null;

        PlazmaScriptParser.atom_return atom157 = null;

        PlazmaScriptParser.atom_return atom159 = null;

        PlazmaScriptParser.atom_return atom161 = null;

        PlazmaScriptParser.atom_return atom162 = null;


        Object char_literal154_tree=null;
        Object char_literal156_tree=null;
        Object char_literal158_tree=null;
        Object string_literal160_tree=null;
        RewriteRuleTokenStream stream_Add=new RewriteRuleTokenStream(adaptor,"token Add");
        RewriteRuleTokenStream stream_NotWord=new RewriteRuleTokenStream(adaptor,"token NotWord");
        RewriteRuleTokenStream stream_Subtract=new RewriteRuleTokenStream(adaptor,"token Subtract");
        RewriteRuleTokenStream stream_Not=new RewriteRuleTokenStream(adaptor,"token Not");
        RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");
        try {
            // grammar/PlazmaScript.g:231:3: ( '+' atom -> ^( UNARY_PLUS atom ) | '-' atom -> ^( UNARY_MIN atom ) | '!' atom -> ^( NEGATE atom ) | 'not' atom -> ^( NEGATE atom ) | atom )
            int alt30=5;
            switch ( input.LA(1) ) {
            case Add:
                {
                alt30=1;
                }
                break;
            case Subtract:
                {
                alt30=2;
                }
                break;
            case Not:
                {
                alt30=3;
                }
                break;
            case NotWord:
                {
                alt30=4;
                }
                break;
            case Identifier:
            case Println:
            case Print:
            case Assert:
            case Date:
            case DateTime:
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
                alt30=5;
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
                    // grammar/PlazmaScript.g:231:6: '+' atom
                    {
                    char_literal154=(Token)match(input,Add,FOLLOW_Add_in_unaryExpr1596); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Add.add(char_literal154);

                    pushFollow(FOLLOW_atom_in_unaryExpr1598);
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
                    // 231:15: -> ^( UNARY_PLUS atom )
                    {
                        // grammar/PlazmaScript.g:231:18: ^( UNARY_PLUS atom )
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
                    // grammar/PlazmaScript.g:232:6: '-' atom
                    {
                    char_literal156=(Token)match(input,Subtract,FOLLOW_Subtract_in_unaryExpr1613); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Subtract.add(char_literal156);

                    pushFollow(FOLLOW_atom_in_unaryExpr1615);
                    atom157=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom157.getTree());


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
                    // 232:15: -> ^( UNARY_MIN atom )
                    {
                        // grammar/PlazmaScript.g:232:18: ^( UNARY_MIN atom )
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
                    // grammar/PlazmaScript.g:233:6: '!' atom
                    {
                    char_literal158=(Token)match(input,Not,FOLLOW_Not_in_unaryExpr1630); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Not.add(char_literal158);

                    pushFollow(FOLLOW_atom_in_unaryExpr1632);
                    atom159=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom159.getTree());


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
                    // 233:15: -> ^( NEGATE atom )
                    {
                        // grammar/PlazmaScript.g:233:18: ^( NEGATE atom )
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
                    // grammar/PlazmaScript.g:234:6: 'not' atom
                    {
                    string_literal160=(Token)match(input,NotWord,FOLLOW_NotWord_in_unaryExpr1647); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NotWord.add(string_literal160);

                    pushFollow(FOLLOW_atom_in_unaryExpr1649);
                    atom161=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom161.getTree());


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
                    // 234:17: -> ^( NEGATE atom )
                    {
                        // grammar/PlazmaScript.g:234:20: ^( NEGATE atom )
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
                    // grammar/PlazmaScript.g:235:6: atom
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_atom_in_unaryExpr1666);
                    atom162=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, atom162.getTree());

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
    // grammar/PlazmaScript.g:238:1: atom : ( Integer | Number | Bool | Null | NaN | Infinity | lookup );
    public final PlazmaScriptParser.atom_return atom() throws RecognitionException {
        PlazmaScriptParser.atom_return retval = new PlazmaScriptParser.atom_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Integer163=null;
        Token Number164=null;
        Token Bool165=null;
        Token Null166=null;
        Token NaN167=null;
        Token Infinity168=null;
        PlazmaScriptParser.lookup_return lookup169 = null;


        Object Integer163_tree=null;
        Object Number164_tree=null;
        Object Bool165_tree=null;
        Object Null166_tree=null;
        Object NaN167_tree=null;
        Object Infinity168_tree=null;

        try {
            // grammar/PlazmaScript.g:239:3: ( Integer | Number | Bool | Null | NaN | Infinity | lookup )
            int alt31=7;
            switch ( input.LA(1) ) {
            case Integer:
                {
                alt31=1;
                }
                break;
            case Number:
                {
                alt31=2;
                }
                break;
            case Bool:
                {
                alt31=3;
                }
                break;
            case Null:
                {
                alt31=4;
                }
                break;
            case NaN:
                {
                alt31=5;
                }
                break;
            case Infinity:
                {
                alt31=6;
                }
                break;
            case Identifier:
            case Println:
            case Print:
            case Assert:
            case Date:
            case DateTime:
            case List:
            case Set:
            case String:
            case OBrace:
            case OBracket:
            case OParen:
            case ContextIdentifier:
                {
                alt31=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }

            switch (alt31) {
                case 1 :
                    // grammar/PlazmaScript.g:239:6: Integer
                    {
                    root_0 = (Object)adaptor.nil();

                    Integer163=(Token)match(input,Integer,FOLLOW_Integer_in_atom1680); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Integer163_tree = (Object)adaptor.create(Integer163);
                    adaptor.addChild(root_0, Integer163_tree);
                    }

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:240:6: Number
                    {
                    root_0 = (Object)adaptor.nil();

                    Number164=(Token)match(input,Number,FOLLOW_Number_in_atom1687); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Number164_tree = (Object)adaptor.create(Number164);
                    adaptor.addChild(root_0, Number164_tree);
                    }

                    }
                    break;
                case 3 :
                    // grammar/PlazmaScript.g:241:6: Bool
                    {
                    root_0 = (Object)adaptor.nil();

                    Bool165=(Token)match(input,Bool,FOLLOW_Bool_in_atom1694); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Bool165_tree = (Object)adaptor.create(Bool165);
                    adaptor.addChild(root_0, Bool165_tree);
                    }

                    }
                    break;
                case 4 :
                    // grammar/PlazmaScript.g:242:6: Null
                    {
                    root_0 = (Object)adaptor.nil();

                    Null166=(Token)match(input,Null,FOLLOW_Null_in_atom1701); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Null166_tree = (Object)adaptor.create(Null166);
                    adaptor.addChild(root_0, Null166_tree);
                    }

                    }
                    break;
                case 5 :
                    // grammar/PlazmaScript.g:243:6: NaN
                    {
                    root_0 = (Object)adaptor.nil();

                    NaN167=(Token)match(input,NaN,FOLLOW_NaN_in_atom1708); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NaN167_tree = (Object)adaptor.create(NaN167);
                    adaptor.addChild(root_0, NaN167_tree);
                    }

                    }
                    break;
                case 6 :
                    // grammar/PlazmaScript.g:244:6: Infinity
                    {
                    root_0 = (Object)adaptor.nil();

                    Infinity168=(Token)match(input,Infinity,FOLLOW_Infinity_in_atom1715); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Infinity168_tree = (Object)adaptor.create(Infinity168);
                    adaptor.addChild(root_0, Infinity168_tree);
                    }

                    }
                    break;
                case 7 :
                    // grammar/PlazmaScript.g:245:6: lookup
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_lookup_in_atom1723);
                    lookup169=lookup();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lookup169.getTree());

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
    // grammar/PlazmaScript.g:248:1: list : '[' ( exprList )? ']' -> ^( LIST ( exprList )? ) ;
    public final PlazmaScriptParser.list_return list() throws RecognitionException {
        PlazmaScriptParser.list_return retval = new PlazmaScriptParser.list_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal170=null;
        Token char_literal172=null;
        PlazmaScriptParser.exprList_return exprList171 = null;


        Object char_literal170_tree=null;
        Object char_literal172_tree=null;
        RewriteRuleTokenStream stream_CBracket=new RewriteRuleTokenStream(adaptor,"token CBracket");
        RewriteRuleTokenStream stream_OBracket=new RewriteRuleTokenStream(adaptor,"token OBracket");
        RewriteRuleSubtreeStream stream_exprList=new RewriteRuleSubtreeStream(adaptor,"rule exprList");
        try {
            // grammar/PlazmaScript.g:249:3: ( '[' ( exprList )? ']' -> ^( LIST ( exprList )? ) )
            // grammar/PlazmaScript.g:249:6: '[' ( exprList )? ']'
            {
            char_literal170=(Token)match(input,OBracket,FOLLOW_OBracket_in_list1737); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBracket.add(char_literal170);

            // grammar/PlazmaScript.g:249:10: ( exprList )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( ((LA32_0>=Identifier && LA32_0<=Set)||(LA32_0>=Integer && LA32_0<=String)||(LA32_0>=Not && LA32_0<=NotWord)||(LA32_0>=Add && LA32_0<=Subtract)||LA32_0==OBrace||LA32_0==OBracket||LA32_0==OParen||LA32_0==ContextIdentifier) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // grammar/PlazmaScript.g:0:0: exprList
                    {
                    pushFollow(FOLLOW_exprList_in_list1739);
                    exprList171=exprList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_exprList.add(exprList171.getTree());

                    }
                    break;

            }

            char_literal172=(Token)match(input,CBracket,FOLLOW_CBracket_in_list1742); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBracket.add(char_literal172);



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
            // 249:24: -> ^( LIST ( exprList )? )
            {
                // grammar/PlazmaScript.g:249:27: ^( LIST ( exprList )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LIST, "LIST"), root_1);

                // grammar/PlazmaScript.g:249:34: ( exprList )?
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
    // grammar/PlazmaScript.g:252:1: map : '{' ( ':' | exprMap ) '}' -> ^( MAP ( exprMap )? ) ;
    public final PlazmaScriptParser.map_return map() throws RecognitionException {
        PlazmaScriptParser.map_return retval = new PlazmaScriptParser.map_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal173=null;
        Token char_literal174=null;
        Token char_literal176=null;
        PlazmaScriptParser.exprMap_return exprMap175 = null;


        Object char_literal173_tree=null;
        Object char_literal174_tree=null;
        Object char_literal176_tree=null;
        RewriteRuleTokenStream stream_OBrace=new RewriteRuleTokenStream(adaptor,"token OBrace");
        RewriteRuleTokenStream stream_Colon=new RewriteRuleTokenStream(adaptor,"token Colon");
        RewriteRuleTokenStream stream_CBrace=new RewriteRuleTokenStream(adaptor,"token CBrace");
        RewriteRuleSubtreeStream stream_exprMap=new RewriteRuleSubtreeStream(adaptor,"rule exprMap");
        try {
            // grammar/PlazmaScript.g:253:3: ( '{' ( ':' | exprMap ) '}' -> ^( MAP ( exprMap )? ) )
            // grammar/PlazmaScript.g:253:6: '{' ( ':' | exprMap ) '}'
            {
            char_literal173=(Token)match(input,OBrace,FOLLOW_OBrace_in_map1765); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OBrace.add(char_literal173);

            // grammar/PlazmaScript.g:253:10: ( ':' | exprMap )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==Colon) ) {
                alt33=1;
            }
            else if ( ((LA33_0>=Identifier && LA33_0<=Set)||(LA33_0>=Integer && LA33_0<=String)||(LA33_0>=Not && LA33_0<=NotWord)||(LA33_0>=Add && LA33_0<=Subtract)||LA33_0==OBrace||LA33_0==OBracket||LA33_0==OParen||LA33_0==ContextIdentifier) ) {
                alt33=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }
            switch (alt33) {
                case 1 :
                    // grammar/PlazmaScript.g:253:11: ':'
                    {
                    char_literal174=(Token)match(input,Colon,FOLLOW_Colon_in_map1768); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Colon.add(char_literal174);


                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:253:17: exprMap
                    {
                    pushFollow(FOLLOW_exprMap_in_map1772);
                    exprMap175=exprMap();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_exprMap.add(exprMap175.getTree());

                    }
                    break;

            }

            char_literal176=(Token)match(input,CBrace,FOLLOW_CBrace_in_map1775); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CBrace.add(char_literal176);



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
            // 253:30: -> ^( MAP ( exprMap )? )
            {
                // grammar/PlazmaScript.g:253:33: ^( MAP ( exprMap )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(MAP, "MAP"), root_1);

                // grammar/PlazmaScript.g:253:39: ( exprMap )?
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
    // grammar/PlazmaScript.g:256:1: lookup : ( functionCall ( indexes )? -> ^( LOOKUP functionCall ( indexes )? ) | list ( indexes )? -> ^( LOOKUP list ( indexes )? ) | map ( indexes )? -> ^( LOOKUP map ( indexes )? ) | Identifier ( indexes )? -> ^( LOOKUP Identifier ( indexes )? ) | anyIdentifier ( indexes )? -> ^( LOOKUP ( indexes )? ) | String ( indexes )? -> ^( LOOKUP String ( indexes )? ) | '(' expression ')' ( indexes )? -> ^( LOOKUP expression ( indexes )? ) );
    public final PlazmaScriptParser.lookup_return lookup() throws RecognitionException {
        PlazmaScriptParser.lookup_return retval = new PlazmaScriptParser.lookup_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token Identifier183=null;
        Token String187=null;
        Token char_literal189=null;
        Token char_literal191=null;
        PlazmaScriptParser.functionCall_return functionCall177 = null;

        PlazmaScriptParser.indexes_return indexes178 = null;

        PlazmaScriptParser.list_return list179 = null;

        PlazmaScriptParser.indexes_return indexes180 = null;

        PlazmaScriptParser.map_return map181 = null;

        PlazmaScriptParser.indexes_return indexes182 = null;

        PlazmaScriptParser.indexes_return indexes184 = null;

        PlazmaScriptParser.anyIdentifier_return anyIdentifier185 = null;

        PlazmaScriptParser.indexes_return indexes186 = null;

        PlazmaScriptParser.indexes_return indexes188 = null;

        PlazmaScriptParser.expression_return expression190 = null;

        PlazmaScriptParser.indexes_return indexes192 = null;


        Object Identifier183_tree=null;
        Object String187_tree=null;
        Object char_literal189_tree=null;
        Object char_literal191_tree=null;
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
            // grammar/PlazmaScript.g:257:3: ( functionCall ( indexes )? -> ^( LOOKUP functionCall ( indexes )? ) | list ( indexes )? -> ^( LOOKUP list ( indexes )? ) | map ( indexes )? -> ^( LOOKUP map ( indexes )? ) | Identifier ( indexes )? -> ^( LOOKUP Identifier ( indexes )? ) | anyIdentifier ( indexes )? -> ^( LOOKUP ( indexes )? ) | String ( indexes )? -> ^( LOOKUP String ( indexes )? ) | '(' expression ')' ( indexes )? -> ^( LOOKUP expression ( indexes )? ) )
            int alt41=7;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA41_1 = input.LA(2);

                if ( (LA41_1==OParen) ) {
                    alt41=1;
                }
                else if ( (synpred77_PlazmaScript()) ) {
                    alt41=4;
                }
                else if ( (synpred79_PlazmaScript()) ) {
                    alt41=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 41, 1, input);

                    throw nvae;
                }
                }
                break;
            case Println:
            case Print:
            case Assert:
            case Date:
            case DateTime:
            case List:
            case Set:
                {
                alt41=1;
                }
                break;
            case OBracket:
                {
                alt41=2;
                }
                break;
            case OBrace:
                {
                alt41=3;
                }
                break;
            case ContextIdentifier:
                {
                alt41=5;
                }
                break;
            case String:
                {
                alt41=6;
                }
                break;
            case OParen:
                {
                alt41=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;
            }

            switch (alt41) {
                case 1 :
                    // grammar/PlazmaScript.g:257:6: functionCall ( indexes )?
                    {
                    pushFollow(FOLLOW_functionCall_in_lookup1798);
                    functionCall177=functionCall();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_functionCall.add(functionCall177.getTree());
                    // grammar/PlazmaScript.g:257:19: ( indexes )?
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==OBracket||LA34_0==103) ) {
                        alt34=1;
                    }
                    switch (alt34) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1800);
                            indexes178=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes178.getTree());

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
                    // 257:34: -> ^( LOOKUP functionCall ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:257:37: ^( LOOKUP functionCall ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_functionCall.nextTree());
                        // grammar/PlazmaScript.g:257:59: ( indexes )?
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
                    // grammar/PlazmaScript.g:258:6: list ( indexes )?
                    {
                    pushFollow(FOLLOW_list_in_lookup1825);
                    list179=list();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_list.add(list179.getTree());
                    // grammar/PlazmaScript.g:258:11: ( indexes )?
                    int alt35=2;
                    int LA35_0 = input.LA(1);

                    if ( (LA35_0==OBracket||LA35_0==103) ) {
                        alt35=1;
                    }
                    switch (alt35) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1827);
                            indexes180=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes180.getTree());

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
                    // 258:34: -> ^( LOOKUP list ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:258:37: ^( LOOKUP list ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_list.nextTree());
                        // grammar/PlazmaScript.g:258:51: ( indexes )?
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
                    // grammar/PlazmaScript.g:259:6: map ( indexes )?
                    {
                    pushFollow(FOLLOW_map_in_lookup1860);
                    map181=map();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_map.add(map181.getTree());
                    // grammar/PlazmaScript.g:259:10: ( indexes )?
                    int alt36=2;
                    int LA36_0 = input.LA(1);

                    if ( (LA36_0==OBracket||LA36_0==103) ) {
                        alt36=1;
                    }
                    switch (alt36) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1862);
                            indexes182=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes182.getTree());

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
                    // 259:34: -> ^( LOOKUP map ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:259:37: ^( LOOKUP map ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_map.nextTree());
                        // grammar/PlazmaScript.g:259:50: ( indexes )?
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
                    // grammar/PlazmaScript.g:260:6: Identifier ( indexes )?
                    {
                    Identifier183=(Token)match(input,Identifier,FOLLOW_Identifier_in_lookup1898); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier183);

                    // grammar/PlazmaScript.g:260:17: ( indexes )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==OBracket||LA37_0==103) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1900);
                            indexes184=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes184.getTree());

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
                    // 260:34: -> ^( LOOKUP Identifier ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:260:37: ^( LOOKUP Identifier ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_Identifier.nextNode());
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
                case 5 :
                    // grammar/PlazmaScript.g:261:6: anyIdentifier ( indexes )?
                    {
                    pushFollow(FOLLOW_anyIdentifier_in_lookup1927);
                    anyIdentifier185=anyIdentifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_anyIdentifier.add(anyIdentifier185.getTree());
                    // grammar/PlazmaScript.g:261:20: ( indexes )?
                    int alt38=2;
                    int LA38_0 = input.LA(1);

                    if ( (LA38_0==OBracket||LA38_0==103) ) {
                        alt38=1;
                    }
                    switch (alt38) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1929);
                            indexes186=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes186.getTree());

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
                    // 261:34: -> ^( LOOKUP ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:261:37: ^( LOOKUP ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, new CommonTree(new CommonToken(Identifier, (anyIdentifier185!=null?input.toString(anyIdentifier185.start,anyIdentifier185.stop):null))));
                        // grammar/PlazmaScript.g:261:113: ( indexes )?
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
                    // grammar/PlazmaScript.g:262:6: String ( indexes )?
                    {
                    String187=(Token)match(input,String,FOLLOW_String_in_lookup1955); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_String.add(String187);

                    // grammar/PlazmaScript.g:262:13: ( indexes )?
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==OBracket||LA39_0==103) ) {
                        alt39=1;
                    }
                    switch (alt39) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1957);
                            indexes188=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes188.getTree());

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
                    // 262:34: -> ^( LOOKUP String ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:262:37: ^( LOOKUP String ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_String.nextNode());
                        // grammar/PlazmaScript.g:262:53: ( indexes )?
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
                    // grammar/PlazmaScript.g:263:6: '(' expression ')' ( indexes )?
                    {
                    char_literal189=(Token)match(input,OParen,FOLLOW_OParen_in_lookup1988); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal189);

                    pushFollow(FOLLOW_expression_in_lookup1990);
                    expression190=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression190.getTree());
                    char_literal191=(Token)match(input,CParen,FOLLOW_CParen_in_lookup1992); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal191);

                    // grammar/PlazmaScript.g:263:25: ( indexes )?
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( (LA40_0==OBracket||LA40_0==103) ) {
                        alt40=1;
                    }
                    switch (alt40) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup1994);
                            indexes192=indexes();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_indexes.add(indexes192.getTree());

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
                    // 263:34: -> ^( LOOKUP expression ( indexes )? )
                    {
                        // grammar/PlazmaScript.g:263:37: ^( LOOKUP expression ( indexes )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LOOKUP, "LOOKUP"), root_1);

                        adaptor.addChild(root_1, stream_expression.nextTree());
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
    // grammar/PlazmaScript.g:266:1: indexes : ( tail )+ -> ^( TAILS ( tail )+ ) ;
    public final PlazmaScriptParser.indexes_return indexes() throws RecognitionException {
        PlazmaScriptParser.indexes_return retval = new PlazmaScriptParser.indexes_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        PlazmaScriptParser.tail_return tail193 = null;


        RewriteRuleSubtreeStream stream_tail=new RewriteRuleSubtreeStream(adaptor,"rule tail");
        try {
            // grammar/PlazmaScript.g:268:3: ( ( tail )+ -> ^( TAILS ( tail )+ ) )
            // grammar/PlazmaScript.g:268:6: ( tail )+
            {
            // grammar/PlazmaScript.g:268:6: ( tail )+
            int cnt42=0;
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==OBracket||LA42_0==103) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // grammar/PlazmaScript.g:268:7: tail
            	    {
            	    pushFollow(FOLLOW_tail_in_indexes2024);
            	    tail193=tail();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_tail.add(tail193.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt42 >= 1 ) break loop42;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(42, input);
                        throw eee;
                }
                cnt42++;
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
            // 268:14: -> ^( TAILS ( tail )+ )
            {
                // grammar/PlazmaScript.g:268:17: ^( TAILS ( tail )+ )
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
    // grammar/PlazmaScript.g:271:1: tail : ( '[' expression ']' -> ^( INDEX expression ) | '.' Identifier -> ^( ATTRIBUTE Identifier ) | '.' Identifier '(' ( exprList )? ')' -> ^( CALL Identifier ( exprList )? ) );
    public final PlazmaScriptParser.tail_return tail() throws RecognitionException {
        PlazmaScriptParser.tail_return retval = new PlazmaScriptParser.tail_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal194=null;
        Token char_literal196=null;
        Token char_literal197=null;
        Token Identifier198=null;
        Token char_literal199=null;
        Token Identifier200=null;
        Token char_literal201=null;
        Token char_literal203=null;
        PlazmaScriptParser.expression_return expression195 = null;

        PlazmaScriptParser.exprList_return exprList202 = null;


        Object char_literal194_tree=null;
        Object char_literal196_tree=null;
        Object char_literal197_tree=null;
        Object Identifier198_tree=null;
        Object char_literal199_tree=null;
        Object Identifier200_tree=null;
        Object char_literal201_tree=null;
        Object char_literal203_tree=null;
        RewriteRuleTokenStream stream_CBracket=new RewriteRuleTokenStream(adaptor,"token CBracket");
        RewriteRuleTokenStream stream_OParen=new RewriteRuleTokenStream(adaptor,"token OParen");
        RewriteRuleTokenStream stream_CParen=new RewriteRuleTokenStream(adaptor,"token CParen");
        RewriteRuleTokenStream stream_OBracket=new RewriteRuleTokenStream(adaptor,"token OBracket");
        RewriteRuleTokenStream stream_103=new RewriteRuleTokenStream(adaptor,"token 103");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_exprList=new RewriteRuleSubtreeStream(adaptor,"rule exprList");
        try {
            // grammar/PlazmaScript.g:272:2: ( '[' expression ']' -> ^( INDEX expression ) | '.' Identifier -> ^( ATTRIBUTE Identifier ) | '.' Identifier '(' ( exprList )? ')' -> ^( CALL Identifier ( exprList )? ) )
            int alt44=3;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==OBracket) ) {
                alt44=1;
            }
            else if ( (LA44_0==103) ) {
                int LA44_2 = input.LA(2);

                if ( (LA44_2==Identifier) ) {
                    int LA44_3 = input.LA(3);

                    if ( (LA44_3==OParen) ) {
                        alt44=3;
                    }
                    else if ( (LA44_3==EOF||(LA44_3>=In && LA44_3<=Range)||(LA44_3>=XorWord && LA44_3<=Pow)||(LA44_3>=GT && LA44_3<=Modulus)||(LA44_3>=CBrace && LA44_3<=CBracket)||(LA44_3>=CParen && LA44_3<=Colon)||LA44_3==103) ) {
                        alt44=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 44, 3, input);

                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 44, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                throw nvae;
            }
            switch (alt44) {
                case 1 :
                    // grammar/PlazmaScript.g:272:4: '[' expression ']'
                    {
                    char_literal194=(Token)match(input,OBracket,FOLLOW_OBracket_in_tail2047); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OBracket.add(char_literal194);

                    pushFollow(FOLLOW_expression_in_tail2049);
                    expression195=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression195.getTree());
                    char_literal196=(Token)match(input,CBracket,FOLLOW_CBracket_in_tail2051); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CBracket.add(char_literal196);



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
                    // 272:37: -> ^( INDEX expression )
                    {
                        // grammar/PlazmaScript.g:272:40: ^( INDEX expression )
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
                    // grammar/PlazmaScript.g:273:4: '.' Identifier
                    {
                    char_literal197=(Token)match(input,103,FOLLOW_103_in_tail2078); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_103.add(char_literal197);

                    Identifier198=(Token)match(input,Identifier,FOLLOW_Identifier_in_tail2080); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier198);



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
                    // 273:37: -> ^( ATTRIBUTE Identifier )
                    {
                        // grammar/PlazmaScript.g:273:40: ^( ATTRIBUTE Identifier )
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
                    // grammar/PlazmaScript.g:274:4: '.' Identifier '(' ( exprList )? ')'
                    {
                    char_literal199=(Token)match(input,103,FOLLOW_103_in_tail2111); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_103.add(char_literal199);

                    Identifier200=(Token)match(input,Identifier,FOLLOW_Identifier_in_tail2113); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier200);

                    char_literal201=(Token)match(input,OParen,FOLLOW_OParen_in_tail2115); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OParen.add(char_literal201);

                    // grammar/PlazmaScript.g:274:23: ( exprList )?
                    int alt43=2;
                    int LA43_0 = input.LA(1);

                    if ( ((LA43_0>=Identifier && LA43_0<=Set)||(LA43_0>=Integer && LA43_0<=String)||(LA43_0>=Not && LA43_0<=NotWord)||(LA43_0>=Add && LA43_0<=Subtract)||LA43_0==OBrace||LA43_0==OBracket||LA43_0==OParen||LA43_0==ContextIdentifier) ) {
                        alt43=1;
                    }
                    switch (alt43) {
                        case 1 :
                            // grammar/PlazmaScript.g:0:0: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_tail2117);
                            exprList202=exprList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_exprList.add(exprList202.getTree());

                            }
                            break;

                    }

                    char_literal203=(Token)match(input,CParen,FOLLOW_CParen_in_tail2120); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CParen.add(char_literal203);



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
                    // 274:37: -> ^( CALL Identifier ( exprList )? )
                    {
                        // grammar/PlazmaScript.g:274:40: ^( CALL Identifier ( exprList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CALL, "CALL"), root_1);

                        adaptor.addChild(root_1, stream_Identifier.nextNode());
                        // grammar/PlazmaScript.g:274:58: ( exprList )?
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
    // grammar/PlazmaScript.g:370:1: anyIdentifier : ( ContextIdentifier | Identifier );
    public final PlazmaScriptParser.anyIdentifier_return anyIdentifier() throws RecognitionException {
        PlazmaScriptParser.anyIdentifier_return retval = new PlazmaScriptParser.anyIdentifier_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set204=null;

        Object set204_tree=null;

        try {
            // grammar/PlazmaScript.g:371:3: ( ContextIdentifier | Identifier )
            // grammar/PlazmaScript.g:
            {
            root_0 = (Object)adaptor.nil();

            set204=(Token)input.LT(1);
            if ( input.LA(1)==Identifier||input.LA(1)==ContextIdentifier ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set204));
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
        int alt45=2;
        int LA45_0 = input.LA(1);

        if ( (LA45_0==Var) ) {
            alt45=1;
        }
        switch (alt45) {
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
        int alt46=2;
        int LA46_0 = input.LA(1);

        if ( (LA46_0==OBracket||LA46_0==103) ) {
            alt46=1;
        }
        switch (alt46) {
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

    // $ANTLR start synpred77_PlazmaScript
    public final void synpred77_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:260:6: ( Identifier ( indexes )? )
        // grammar/PlazmaScript.g:260:6: Identifier ( indexes )?
        {
        match(input,Identifier,FOLLOW_Identifier_in_synpred77_PlazmaScript1898); if (state.failed) return ;
        // grammar/PlazmaScript.g:260:17: ( indexes )?
        int alt55=2;
        int LA55_0 = input.LA(1);

        if ( (LA55_0==OBracket||LA55_0==103) ) {
            alt55=1;
        }
        switch (alt55) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: indexes
                {
                pushFollow(FOLLOW_indexes_in_synpred77_PlazmaScript1900);
                indexes();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred77_PlazmaScript

    // $ANTLR start synpred79_PlazmaScript
    public final void synpred79_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:261:6: ( anyIdentifier ( indexes )? )
        // grammar/PlazmaScript.g:261:6: anyIdentifier ( indexes )?
        {
        pushFollow(FOLLOW_anyIdentifier_in_synpred79_PlazmaScript1927);
        anyIdentifier();

        state._fsp--;
        if (state.failed) return ;
        // grammar/PlazmaScript.g:261:20: ( indexes )?
        int alt56=2;
        int LA56_0 = input.LA(1);

        if ( (LA56_0==OBracket||LA56_0==103) ) {
            alt56=1;
        }
        switch (alt56) {
            case 1 :
                // grammar/PlazmaScript.g:0:0: indexes
                {
                pushFollow(FOLLOW_indexes_in_synpred79_PlazmaScript1929);
                indexes();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred79_PlazmaScript

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
    public final boolean synpred77_PlazmaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred77_PlazmaScript_fragment(); // can never throw exception
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
    public final boolean synpred79_PlazmaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred79_PlazmaScript_fragment(); // can never throw exception
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
        "\25\uffff";
    static final String DFA3_eofS =
        "\25\uffff";
    static final String DFA3_minS =
        "\1\41\1\uffff\11\0\12\uffff";
    static final String DFA3_maxS =
        "\1\137\1\uffff\11\0\12\uffff";
    static final String DFA3_acceptS =
        "\1\uffff\1\1\11\uffff\1\3\3\uffff\1\4\1\5\1\6\1\7\1\10\1\2";
    static final String DFA3_specialS =
        "\2\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\12\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\22\1\23\1\2\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\17\1\uffff"+
            "\1\1\1\uffff\1\20\1\21\11\uffff\1\13\25\uffff\1\13\1\uffff\1"+
            "\13\1\uffff\1\13\12\uffff\1\3",
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

                        else if ( (synpred5_PlazmaScript()) ) {s = 20;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 11;}

                         
                        input.seek(index3_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA3_3 = input.LA(1);

                         
                        int index3_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_PlazmaScript()) ) {s = 1;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 11;}

                         
                        input.seek(index3_3);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA3_4 = input.LA(1);

                         
                        int index3_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 20;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 11;}

                         
                        input.seek(index3_4);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA3_5 = input.LA(1);

                         
                        int index3_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 20;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 11;}

                         
                        input.seek(index3_5);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA3_6 = input.LA(1);

                         
                        int index3_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 20;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 11;}

                         
                        input.seek(index3_6);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA3_7 = input.LA(1);

                         
                        int index3_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 20;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 11;}

                         
                        input.seek(index3_7);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA3_8 = input.LA(1);

                         
                        int index3_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 20;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 11;}

                         
                        input.seek(index3_8);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA3_9 = input.LA(1);

                         
                        int index3_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 20;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 11;}

                         
                        input.seek(index3_9);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA3_10 = input.LA(1);

                         
                        int index3_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_PlazmaScript()) ) {s = 20;}

                        else if ( (synpred6_PlazmaScript()) ) {s = 11;}

                         
                        input.seek(index3_10);
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
    public static final BitSet FOLLOW_statement_in_block236 = new BitSet(new long[]{0x0401EFFF00000002L,0x0000000080150000L});
    public static final BitSet FOLLOW_functionDecl_in_block240 = new BitSet(new long[]{0x0401EFFF00000002L,0x0000000080150000L});
    public static final BitSet FOLLOW_Return_in_block245 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_expression_in_block247 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_SColon_in_block249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_statement291 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_SColon_in_statement293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_statement306 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_SColon_in_statement308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_statement319 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_SColon_in_statement321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStatement_in_statement365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forStatement_in_statement372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whileStatement_in_statement379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Break_in_statement386 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_SColon_in_statement388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Continue_in_statement399 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_SColon_in_statement401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDef_in_assignment422 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_Identifier_in_assignment425 = new BitSet(new long[]{0x0000000000000000L,0x0000008000840000L});
    public static final BitSet FOLLOW_indexes_in_assignment427 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_Assign_in_assignment430 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_expression_in_assignment432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDef_in_assignment455 = new BitSet(new long[]{0x0000200800000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_anyIdentifier_in_assignment458 = new BitSet(new long[]{0x0000000000000000L,0x0000008000840000L});
    public static final BitSet FOLLOW_indexes_in_assignment460 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_Assign_in_assignment463 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_expression_in_assignment465 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_functionCall495 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_OParen_in_functionCall497 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080351980L});
    public static final BitSet FOLLOW_exprList_in_functionCall499 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_CParen_in_functionCall502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Println_in_functionCall520 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_OParen_in_functionCall522 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080351980L});
    public static final BitSet FOLLOW_expression_in_functionCall524 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_CParen_in_functionCall527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Print_in_functionCall546 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_OParen_in_functionCall548 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_expression_in_functionCall550 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_CParen_in_functionCall552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Assert_in_functionCall573 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_OParen_in_functionCall575 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_expression_in_functionCall577 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_CParen_in_functionCall579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Date_in_functionCall599 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_OParen_in_functionCall601 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080351980L});
    public static final BitSet FOLLOW_exprList_in_functionCall603 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_CParen_in_functionCall606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DateTime_in_functionCall629 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_OParen_in_functionCall631 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080351980L});
    public static final BitSet FOLLOW_exprList_in_functionCall633 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_CParen_in_functionCall636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_List_in_functionCall661 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_OParen_in_functionCall663 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080351980L});
    public static final BitSet FOLLOW_exprList_in_functionCall665 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_CParen_in_functionCall668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Set_in_functionCall691 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_OParen_in_functionCall693 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080351980L});
    public static final BitSet FOLLOW_exprList_in_functionCall695 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_CParen_in_functionCall698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStat_in_ifStatement736 = new BitSet(new long[]{0x0000100000000002L});
    public static final BitSet FOLLOW_elseIfStat_in_ifStatement738 = new BitSet(new long[]{0x0000100000000002L});
    public static final BitSet FOLLOW_elseStat_in_ifStatement741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_If_in_ifStat770 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_OParen_in_ifStat772 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_expression_in_ifStat774 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_CParen_in_ifStat776 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_OBrace_in_ifStat778 = new BitSet(new long[]{0x0401EFFF00000000L,0x0000000080150000L});
    public static final BitSet FOLLOW_block_in_ifStat780 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_CBrace_in_ifStat782 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Else_in_elseIfStat806 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_If_in_elseIfStat808 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_OParen_in_elseIfStat810 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_expression_in_elseIfStat812 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_CParen_in_elseIfStat814 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_OBrace_in_elseIfStat816 = new BitSet(new long[]{0x0401EFFF00000000L,0x0000000080150000L});
    public static final BitSet FOLLOW_block_in_elseIfStat818 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_CBrace_in_elseIfStat820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Else_in_elseStat844 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_OBrace_in_elseStat846 = new BitSet(new long[]{0x0401EFFF00000000L,0x0000000080150000L});
    public static final BitSet FOLLOW_block_in_elseStat848 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_CBrace_in_elseStat850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Var_in_variableDef870 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Def_in_functionDecl902 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_Identifier_in_functionDecl904 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_OParen_in_functionDecl906 = new BitSet(new long[]{0x0000000800000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_idList_in_functionDecl908 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_CParen_in_functionDecl911 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_OBrace_in_functionDecl913 = new BitSet(new long[]{0x0401EFFF00000000L,0x0000000080150000L});
    public static final BitSet FOLLOW_block_in_functionDecl915 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_CBrace_in_functionDecl917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_For_in_forStatement946 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_OParen_in_forStatement948 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_Identifier_in_forStatement950 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_In_in_forStatement952 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_expression_in_forStatement954 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_CParen_in_forStatement956 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_OBrace_in_forStatement958 = new BitSet(new long[]{0x0401EFFF00000000L,0x0000000080150000L});
    public static final BitSet FOLLOW_block_in_forStatement960 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_CBrace_in_forStatement962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_While_in_whileStatement997 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_OParen_in_whileStatement999 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_expression_in_whileStatement1001 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_CParen_in_whileStatement1003 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_OBrace_in_whileStatement1005 = new BitSet(new long[]{0x0401EFFF00000000L,0x0000000080150000L});
    public static final BitSet FOLLOW_block_in_whileStatement1007 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_CBrace_in_whileStatement1009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_idList1033 = new BitSet(new long[]{0x0000000000000002L,0x0000000001000000L});
    public static final BitSet FOLLOW_Comma_in_idList1036 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_Identifier_in_idList1038 = new BitSet(new long[]{0x0000000000000002L,0x0000000001000000L});
    public static final BitSet FOLLOW_expression_in_exprList1063 = new BitSet(new long[]{0x0000000000000002L,0x0000000001000000L});
    public static final BitSet FOLLOW_Comma_in_exprList1066 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_expression_in_exprList1068 = new BitSet(new long[]{0x0000000000000002L,0x0000000001000000L});
    public static final BitSet FOLLOW_expression_in_exprPair1094 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_Colon_in_exprPair1096 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_expression_in_exprPair1098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprPair_in_exprMap1123 = new BitSet(new long[]{0x0000000000000002L,0x0000000001000000L});
    public static final BitSet FOLLOW_Comma_in_exprMap1126 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_exprPair_in_exprMap1128 = new BitSet(new long[]{0x0000000000000002L,0x0000000001000000L});
    public static final BitSet FOLLOW_condExpr_in_expression1154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_startExpr_in_condExpr1169 = new BitSet(new long[]{0x000E000000000002L,0x0000000002000000L});
    public static final BitSet FOLLOW_QMark_in_condExpr1184 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_expression_in_condExpr1188 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_Colon_in_condExpr1190 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_expression_in_condExpr1194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_In_in_condExpr1217 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_expression_in_condExpr1219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RangeE_in_condExpr1264 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_expression_in_condExpr1266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Range_in_condExpr1301 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_expression_in_condExpr1303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orExpr_in_startExpr1366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_andExpr_in_orExpr1379 = new BitSet(new long[]{0x7800000000000002L});
    public static final BitSet FOLLOW_set_in_orExpr1382 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_andExpr_in_orExpr1399 = new BitSet(new long[]{0x7800000000000002L});
    public static final BitSet FOLLOW_equExpr_in_andExpr1416 = new BitSet(new long[]{0x8000000000000002L,0x0000000000000003L});
    public static final BitSet FOLLOW_set_in_andExpr1419 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_equExpr_in_andExpr1432 = new BitSet(new long[]{0x8000000000000002L,0x0000000000000003L});
    public static final BitSet FOLLOW_relExpr_in_equExpr1448 = new BitSet(new long[]{0x0000000000000002L,0x000000000000000CL});
    public static final BitSet FOLLOW_set_in_equExpr1451 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_relExpr_in_equExpr1460 = new BitSet(new long[]{0x0000000000000002L,0x000000000000000CL});
    public static final BitSet FOLLOW_addExpr_in_relExpr1476 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000630L});
    public static final BitSet FOLLOW_set_in_relExpr1479 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_addExpr_in_relExpr1496 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000630L});
    public static final BitSet FOLLOW_mulExpr_in_addExpr1512 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001800L});
    public static final BitSet FOLLOW_set_in_addExpr1515 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_mulExpr_in_addExpr1524 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001800L});
    public static final BitSet FOLLOW_powExpr_in_mulExpr1540 = new BitSet(new long[]{0x0000000000000002L,0x000000000000E000L});
    public static final BitSet FOLLOW_set_in_mulExpr1543 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_powExpr_in_mulExpr1556 = new BitSet(new long[]{0x0000000000000002L,0x000000000000E000L});
    public static final BitSet FOLLOW_unaryExpr_in_powExpr1572 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000040L});
    public static final BitSet FOLLOW_Pow_in_powExpr1575 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_unaryExpr_in_powExpr1578 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000040L});
    public static final BitSet FOLLOW_Add_in_unaryExpr1596 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Subtract_in_unaryExpr1613 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Not_in_unaryExpr1630 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1632 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NotWord_in_unaryExpr1647 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_unaryExpr1666 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Integer_in_atom1680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Number_in_atom1687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Bool_in_atom1694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Null_in_atom1701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NaN_in_atom1708 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Infinity_in_atom1715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_atom1723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OBracket_in_list1737 = new BitSet(new long[]{0x07F027F800000000L,0x00000000801D1980L});
    public static final BitSet FOLLOW_exprList_in_list1739 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_CBracket_in_list1742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OBrace_in_map1765 = new BitSet(new long[]{0x07F027F800000000L,0x0000000084151980L});
    public static final BitSet FOLLOW_Colon_in_map1768 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_exprMap_in_map1772 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_CBrace_in_map1775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_lookup1798 = new BitSet(new long[]{0x0000000000000002L,0x0000008000040000L});
    public static final BitSet FOLLOW_indexes_in_lookup1800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_in_lookup1825 = new BitSet(new long[]{0x0000000000000002L,0x0000008000040000L});
    public static final BitSet FOLLOW_indexes_in_lookup1827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_map_in_lookup1860 = new BitSet(new long[]{0x0000000000000002L,0x0000008000040000L});
    public static final BitSet FOLLOW_indexes_in_lookup1862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_lookup1898 = new BitSet(new long[]{0x0000000000000002L,0x0000008000040000L});
    public static final BitSet FOLLOW_indexes_in_lookup1900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_anyIdentifier_in_lookup1927 = new BitSet(new long[]{0x0000000000000002L,0x0000008000040000L});
    public static final BitSet FOLLOW_indexes_in_lookup1929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_String_in_lookup1955 = new BitSet(new long[]{0x0000000000000002L,0x0000008000040000L});
    public static final BitSet FOLLOW_indexes_in_lookup1957 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OParen_in_lookup1988 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_expression_in_lookup1990 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_CParen_in_lookup1992 = new BitSet(new long[]{0x0000000000000002L,0x0000008000040000L});
    public static final BitSet FOLLOW_indexes_in_lookup1994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tail_in_indexes2024 = new BitSet(new long[]{0x0000000000000002L,0x0000008000040000L});
    public static final BitSet FOLLOW_OBracket_in_tail2047 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_expression_in_tail2049 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_CBracket_in_tail2051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_103_in_tail2078 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_Identifier_in_tail2080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_103_in_tail2111 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_Identifier_in_tail2113 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_OParen_in_tail2115 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080351980L});
    public static final BitSet FOLLOW_exprList_in_tail2117 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_CParen_in_tail2120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_anyIdentifier0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_synpred4_PlazmaScript291 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_SColon_in_synpred4_PlazmaScript293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_synpred5_PlazmaScript306 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_SColon_in_synpred5_PlazmaScript308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_synpred6_PlazmaScript319 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_SColon_in_synpred6_PlazmaScript321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDef_in_synpred13_PlazmaScript422 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_Identifier_in_synpred13_PlazmaScript425 = new BitSet(new long[]{0x0000000000000000L,0x0000008000840000L});
    public static final BitSet FOLLOW_indexes_in_synpred13_PlazmaScript427 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_Assign_in_synpred13_PlazmaScript430 = new BitSet(new long[]{0x07F027F800000000L,0x0000000080151980L});
    public static final BitSet FOLLOW_expression_in_synpred13_PlazmaScript432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_synpred77_PlazmaScript1898 = new BitSet(new long[]{0x0000000000000002L,0x0000008000040000L});
    public static final BitSet FOLLOW_indexes_in_synpred77_PlazmaScript1900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_anyIdentifier_in_synpred79_PlazmaScript1927 = new BitSet(new long[]{0x0000000000000002L,0x0000008000040000L});
    public static final BitSet FOLLOW_indexes_in_synpred79_PlazmaScript1929 = new BitSet(new long[]{0x0000000000000002L});

}