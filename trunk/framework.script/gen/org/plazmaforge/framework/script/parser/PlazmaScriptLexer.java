// $ANTLR 3.2 Sep 23, 2009 12:02:23 grammar/PlazmaScript.g 2016-09-06 15:40:01

  package org.plazmaforge.framework.script.parser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class PlazmaScriptLexer extends Lexer {
    public static final int FUNCTION=20;
    public static final int LT=78;
    public static final int TERNARY=16;
    public static final int EXP_LIST=13;
    public static final int DateTime=41;
    public static final int Date=40;
    public static final int EOF=-1;
    public static final int QMark=93;
    public static final int NotWord=76;
    public static final int BREAK=31;
    public static final int Identifier=36;
    public static final int UNARY_PLUS=17;
    public static final int FUNC_CALL=9;
    public static final int CParen=89;
    public static final int Comment=101;
    public static final int EXP=10;
    public static final int Digits=97;
    public static final int CBrace=85;
    public static final int RETURN=5;
    public static final int ExponentPart=98;
    public static final int ExponentIndicator=103;
    public static final int Sign=105;
    public static final int OrWord=66;
    public static final int Null=59;
    public static final int DecimalNumeral=95;
    public static final int CBracket=87;
    public static final int Period=44;
    public static final int ContextIdentifier=99;
    public static final int Println=37;
    public static final int Bool=58;
    public static final int Modulus=83;
    public static final int Time=42;
    public static final int AndWord=69;
    public static final int Colon=94;
    public static final int LIST=28;
    public static final int Def=50;
    public static final int RangeE=54;
    public static final int LOOKUP=30;
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
    public static final int List=45;
    public static final int TAILS=26;
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

    public PlazmaScriptLexer() {;} 
    public PlazmaScriptLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public PlazmaScriptLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "grammar/PlazmaScript.g"; }

    // $ANTLR start "T__107"
    public final void mT__107() throws RecognitionException {
        try {
            int _type = T__107;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:7:8: ( '.' )
            // grammar/PlazmaScript.g:7:10: '.'
            {
            match('.'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__107"

    // $ANTLR start "Println"
    public final void mPrintln() throws RecognitionException {
        try {
            int _type = Println;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:287:10: ( 'println' )
            // grammar/PlazmaScript.g:287:12: 'println'
            {
            match("println"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Println"

    // $ANTLR start "Print"
    public final void mPrint() throws RecognitionException {
        try {
            int _type = Print;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:288:10: ( 'print' )
            // grammar/PlazmaScript.g:288:12: 'print'
            {
            match("print"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Print"

    // $ANTLR start "Assert"
    public final void mAssert() throws RecognitionException {
        try {
            int _type = Assert;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:289:10: ( 'assert' )
            // grammar/PlazmaScript.g:289:12: 'assert'
            {
            match("assert"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Assert"

    // $ANTLR start "Var"
    public final void mVar() throws RecognitionException {
        try {
            int _type = Var;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:290:10: ( 'var' )
            // grammar/PlazmaScript.g:290:12: 'var'
            {
            match("var"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Var"

    // $ANTLR start "Def"
    public final void mDef() throws RecognitionException {
        try {
            int _type = Def;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:291:10: ( 'def' )
            // grammar/PlazmaScript.g:291:12: 'def'
            {
            match("def"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Def"

    // $ANTLR start "If"
    public final void mIf() throws RecognitionException {
        try {
            int _type = If;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:292:10: ( 'if' )
            // grammar/PlazmaScript.g:292:12: 'if'
            {
            match("if"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "If"

    // $ANTLR start "Else"
    public final void mElse() throws RecognitionException {
        try {
            int _type = Else;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:293:10: ( 'else' )
            // grammar/PlazmaScript.g:293:12: 'else'
            {
            match("else"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Else"

    // $ANTLR start "Return"
    public final void mReturn() throws RecognitionException {
        try {
            int _type = Return;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:294:10: ( 'return' )
            // grammar/PlazmaScript.g:294:12: 'return'
            {
            match("return"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Return"

    // $ANTLR start "For"
    public final void mFor() throws RecognitionException {
        try {
            int _type = For;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:295:10: ( 'for' )
            // grammar/PlazmaScript.g:295:12: 'for'
            {
            match("for"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "For"

    // $ANTLR start "While"
    public final void mWhile() throws RecognitionException {
        try {
            int _type = While;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:296:10: ( 'while' )
            // grammar/PlazmaScript.g:296:12: 'while'
            {
            match("while"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "While"

    // $ANTLR start "In"
    public final void mIn() throws RecognitionException {
        try {
            int _type = In;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:297:10: ( 'in' )
            // grammar/PlazmaScript.g:297:12: 'in'
            {
            match("in"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "In"

    // $ANTLR start "Null"
    public final void mNull() throws RecognitionException {
        try {
            int _type = Null;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:298:10: ( 'null' )
            // grammar/PlazmaScript.g:298:12: 'null'
            {
            match("null"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Null"

    // $ANTLR start "NaN"
    public final void mNaN() throws RecognitionException {
        try {
            int _type = NaN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:299:10: ( 'NaN' )
            // grammar/PlazmaScript.g:299:12: 'NaN'
            {
            match("NaN"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NaN"

    // $ANTLR start "Infinity"
    public final void mInfinity() throws RecognitionException {
        try {
            int _type = Infinity;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:300:10: ( 'Infinity' )
            // grammar/PlazmaScript.g:300:12: 'Infinity'
            {
            match("Infinity"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Infinity"

    // $ANTLR start "Break"
    public final void mBreak() throws RecognitionException {
        try {
            int _type = Break;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:301:10: ( 'break' )
            // grammar/PlazmaScript.g:301:12: 'break'
            {
            match("break"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Break"

    // $ANTLR start "Continue"
    public final void mContinue() throws RecognitionException {
        try {
            int _type = Continue;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:302:10: ( 'continue' )
            // grammar/PlazmaScript.g:302:12: 'continue'
            {
            match("continue"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Continue"

    // $ANTLR start "XorWord"
    public final void mXorWord() throws RecognitionException {
        try {
            int _type = XorWord;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:304:10: ( 'xor' )
            // grammar/PlazmaScript.g:304:12: 'xor'
            {
            match("xor"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "XorWord"

    // $ANTLR start "Or"
    public final void mOr() throws RecognitionException {
        try {
            int _type = Or;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:305:10: ( '||' )
            // grammar/PlazmaScript.g:305:12: '||'
            {
            match("||"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Or"

    // $ANTLR start "BitOr"
    public final void mBitOr() throws RecognitionException {
        try {
            int _type = BitOr;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:306:10: ( '|' )
            // grammar/PlazmaScript.g:306:12: '|'
            {
            match('|'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BitOr"

    // $ANTLR start "OrWord"
    public final void mOrWord() throws RecognitionException {
        try {
            int _type = OrWord;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:307:10: ( 'or' )
            // grammar/PlazmaScript.g:307:12: 'or'
            {
            match("or"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OrWord"

    // $ANTLR start "And"
    public final void mAnd() throws RecognitionException {
        try {
            int _type = And;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:308:10: ( '&&' )
            // grammar/PlazmaScript.g:308:12: '&&'
            {
            match("&&"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "And"

    // $ANTLR start "BitAnd"
    public final void mBitAnd() throws RecognitionException {
        try {
            int _type = BitAnd;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:309:10: ( '&' )
            // grammar/PlazmaScript.g:309:12: '&'
            {
            match('&'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BitAnd"

    // $ANTLR start "AndWord"
    public final void mAndWord() throws RecognitionException {
        try {
            int _type = AndWord;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:310:10: ( 'and' )
            // grammar/PlazmaScript.g:310:12: 'and'
            {
            match("and"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AndWord"

    // $ANTLR start "Equals"
    public final void mEquals() throws RecognitionException {
        try {
            int _type = Equals;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:311:10: ( '==' )
            // grammar/PlazmaScript.g:311:12: '=='
            {
            match("=="); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Equals"

    // $ANTLR start "NEquals"
    public final void mNEquals() throws RecognitionException {
        try {
            int _type = NEquals;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:312:10: ( '!=' )
            // grammar/PlazmaScript.g:312:12: '!='
            {
            match("!="); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NEquals"

    // $ANTLR start "GTEquals"
    public final void mGTEquals() throws RecognitionException {
        try {
            int _type = GTEquals;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:313:10: ( '>=' )
            // grammar/PlazmaScript.g:313:12: '>='
            {
            match(">="); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GTEquals"

    // $ANTLR start "LTEquals"
    public final void mLTEquals() throws RecognitionException {
        try {
            int _type = LTEquals;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:314:10: ( '<=' )
            // grammar/PlazmaScript.g:314:12: '<='
            {
            match("<="); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LTEquals"

    // $ANTLR start "Pow"
    public final void mPow() throws RecognitionException {
        try {
            int _type = Pow;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:315:10: ( '^' )
            // grammar/PlazmaScript.g:315:12: '^'
            {
            match('^'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Pow"

    // $ANTLR start "Not"
    public final void mNot() throws RecognitionException {
        try {
            int _type = Not;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:316:10: ( '!' )
            // grammar/PlazmaScript.g:316:12: '!'
            {
            match('!'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Not"

    // $ANTLR start "NotWord"
    public final void mNotWord() throws RecognitionException {
        try {
            int _type = NotWord;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:317:10: ( 'not' )
            // grammar/PlazmaScript.g:317:12: 'not'
            {
            match("not"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NotWord"

    // $ANTLR start "GT"
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:318:10: ( '>' )
            // grammar/PlazmaScript.g:318:12: '>'
            {
            match('>'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GT"

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:319:10: ( '<' )
            // grammar/PlazmaScript.g:319:12: '<'
            {
            match('<'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LT"

    // $ANTLR start "Add"
    public final void mAdd() throws RecognitionException {
        try {
            int _type = Add;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:320:10: ( '+' )
            // grammar/PlazmaScript.g:320:12: '+'
            {
            match('+'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Add"

    // $ANTLR start "Subtract"
    public final void mSubtract() throws RecognitionException {
        try {
            int _type = Subtract;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:321:10: ( '-' )
            // grammar/PlazmaScript.g:321:12: '-'
            {
            match('-'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Subtract"

    // $ANTLR start "Multiply"
    public final void mMultiply() throws RecognitionException {
        try {
            int _type = Multiply;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:322:10: ( '*' )
            // grammar/PlazmaScript.g:322:12: '*'
            {
            match('*'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Multiply"

    // $ANTLR start "Divide"
    public final void mDivide() throws RecognitionException {
        try {
            int _type = Divide;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:323:10: ( '/' )
            // grammar/PlazmaScript.g:323:12: '/'
            {
            match('/'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Divide"

    // $ANTLR start "Modulus"
    public final void mModulus() throws RecognitionException {
        try {
            int _type = Modulus;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:325:10: ( '%' )
            // grammar/PlazmaScript.g:325:12: '%'
            {
            match('%'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Modulus"

    // $ANTLR start "OBrace"
    public final void mOBrace() throws RecognitionException {
        try {
            int _type = OBrace;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:326:10: ( '{' )
            // grammar/PlazmaScript.g:326:12: '{'
            {
            match('{'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OBrace"

    // $ANTLR start "CBrace"
    public final void mCBrace() throws RecognitionException {
        try {
            int _type = CBrace;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:327:10: ( '}' )
            // grammar/PlazmaScript.g:327:12: '}'
            {
            match('}'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CBrace"

    // $ANTLR start "OBracket"
    public final void mOBracket() throws RecognitionException {
        try {
            int _type = OBracket;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:328:10: ( '[' )
            // grammar/PlazmaScript.g:328:12: '['
            {
            match('['); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OBracket"

    // $ANTLR start "CBracket"
    public final void mCBracket() throws RecognitionException {
        try {
            int _type = CBracket;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:329:10: ( ']' )
            // grammar/PlazmaScript.g:329:12: ']'
            {
            match(']'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CBracket"

    // $ANTLR start "OParen"
    public final void mOParen() throws RecognitionException {
        try {
            int _type = OParen;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:330:10: ( '(' )
            // grammar/PlazmaScript.g:330:12: '('
            {
            match('('); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OParen"

    // $ANTLR start "CParen"
    public final void mCParen() throws RecognitionException {
        try {
            int _type = CParen;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:331:10: ( ')' )
            // grammar/PlazmaScript.g:331:12: ')'
            {
            match(')'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CParen"

    // $ANTLR start "SColon"
    public final void mSColon() throws RecognitionException {
        try {
            int _type = SColon;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:332:10: ( ';' )
            // grammar/PlazmaScript.g:332:12: ';'
            {
            match(';'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SColon"

    // $ANTLR start "Assign"
    public final void mAssign() throws RecognitionException {
        try {
            int _type = Assign;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:333:10: ( '=' )
            // grammar/PlazmaScript.g:333:12: '='
            {
            match('='); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Assign"

    // $ANTLR start "Comma"
    public final void mComma() throws RecognitionException {
        try {
            int _type = Comma;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:334:10: ( ',' )
            // grammar/PlazmaScript.g:334:12: ','
            {
            match(','); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Comma"

    // $ANTLR start "QMark"
    public final void mQMark() throws RecognitionException {
        try {
            int _type = QMark;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:335:10: ( '?' )
            // grammar/PlazmaScript.g:335:12: '?'
            {
            match('?'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QMark"

    // $ANTLR start "Colon"
    public final void mColon() throws RecognitionException {
        try {
            int _type = Colon;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:336:10: ( ':' )
            // grammar/PlazmaScript.g:336:12: ':'
            {
            match(':'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Colon"

    // $ANTLR start "Range"
    public final void mRange() throws RecognitionException {
        try {
            int _type = Range;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:338:10: ( '..' )
            // grammar/PlazmaScript.g:338:12: '..'
            {
            match(".."); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Range"

    // $ANTLR start "RangeE"
    public final void mRangeE() throws RecognitionException {
        try {
            int _type = RangeE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:339:10: ( '..<' )
            // grammar/PlazmaScript.g:339:12: '..<'
            {
            match("..<"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RangeE"

    // $ANTLR start "Date"
    public final void mDate() throws RecognitionException {
        try {
            int _type = Date;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:341:10: ( 'Date' )
            // grammar/PlazmaScript.g:341:12: 'Date'
            {
            match("Date"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Date"

    // $ANTLR start "DateTime"
    public final void mDateTime() throws RecognitionException {
        try {
            int _type = DateTime;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:342:10: ( 'DateTime' )
            // grammar/PlazmaScript.g:342:12: 'DateTime'
            {
            match("DateTime"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DateTime"

    // $ANTLR start "Time"
    public final void mTime() throws RecognitionException {
        try {
            int _type = Time;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:343:10: ( 'Time' )
            // grammar/PlazmaScript.g:343:12: 'Time'
            {
            match("Time"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Time"

    // $ANTLR start "Duration"
    public final void mDuration() throws RecognitionException {
        try {
            int _type = Duration;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:344:10: ( 'Duration' )
            // grammar/PlazmaScript.g:344:12: 'Duration'
            {
            match("Duration"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Duration"

    // $ANTLR start "Period"
    public final void mPeriod() throws RecognitionException {
        try {
            int _type = Period;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:345:10: ( 'Period' )
            // grammar/PlazmaScript.g:345:12: 'Period'
            {
            match("Period"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Period"

    // $ANTLR start "List"
    public final void mList() throws RecognitionException {
        try {
            int _type = List;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:346:10: ( 'List' )
            // grammar/PlazmaScript.g:346:12: 'List'
            {
            match("List"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "List"

    // $ANTLR start "Set"
    public final void mSet() throws RecognitionException {
        try {
            int _type = Set;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:347:10: ( 'Set' )
            // grammar/PlazmaScript.g:347:12: 'Set'
            {
            match("Set"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Set"

    // $ANTLR start "Bool"
    public final void mBool() throws RecognitionException {
        try {
            int _type = Bool;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:350:3: ( 'true' | 'false' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='t') ) {
                alt1=1;
            }
            else if ( (LA1_0=='f') ) {
                alt1=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // grammar/PlazmaScript.g:350:6: 'true'
                    {
                    match("true"); if (state.failed) return ;


                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:351:6: 'false'
                    {
                    match("false"); if (state.failed) return ;


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Bool"

    // $ANTLR start "Integer"
    public final void mInteger() throws RecognitionException {
        try {
            int _type = Integer;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:366:3: ( DecimalNumeral )
            // grammar/PlazmaScript.g:366:6: DecimalNumeral
            {
            mDecimalNumeral(); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Integer"

    // $ANTLR start "Number"
    public final void mNumber() throws RecognitionException {
        try {
            int _type = Number;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:370:3: ( ( DecimalNumeral '..' )=> Integer | DecimalFloatingPoint )
            int alt2=2;
            switch ( input.LA(1) ) {
            case '0':
                {
                int LA2_1 = input.LA(2);

                if ( (synpred1_PlazmaScript()) ) {
                    alt2=1;
                }
                else if ( (true) ) {
                    alt2=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    throw nvae;
                }
                }
                break;
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                {
                int LA2_2 = input.LA(2);

                if ( (synpred1_PlazmaScript()) ) {
                    alt2=1;
                }
                else if ( (true) ) {
                    alt2=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 2, input);

                    throw nvae;
                }
                }
                break;
            case '.':
                {
                alt2=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // grammar/PlazmaScript.g:370:5: ( DecimalNumeral '..' )=> Integer
                    {
                    mInteger(); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      _type=Integer;
                    }

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:371:5: DecimalFloatingPoint
                    {
                    mDecimalFloatingPoint(); if (state.failed) return ;

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Number"

    // $ANTLR start "DecimalFloatingPoint"
    public final void mDecimalFloatingPoint() throws RecognitionException {
        try {
            // grammar/PlazmaScript.g:376:3: ( Digits ( '.' Digits )? ( ExponentPart )? | '.' Digits ( ExponentPart )? )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( ((LA6_0>='0' && LA6_0<='9')) ) {
                alt6=1;
            }
            else if ( (LA6_0=='.') ) {
                alt6=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // grammar/PlazmaScript.g:376:5: Digits ( '.' Digits )? ( ExponentPart )?
                    {
                    mDigits(); if (state.failed) return ;
                    // grammar/PlazmaScript.g:376:12: ( '.' Digits )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0=='.') ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // grammar/PlazmaScript.g:376:13: '.' Digits
                            {
                            match('.'); if (state.failed) return ;
                            mDigits(); if (state.failed) return ;

                            }
                            break;

                    }

                    // grammar/PlazmaScript.g:376:26: ( ExponentPart )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0=='E'||LA4_0=='e') ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // grammar/PlazmaScript.g:376:26: ExponentPart
                            {
                            mExponentPart(); if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:377:5: '.' Digits ( ExponentPart )?
                    {
                    match('.'); if (state.failed) return ;
                    mDigits(); if (state.failed) return ;
                    // grammar/PlazmaScript.g:377:16: ( ExponentPart )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0=='E'||LA5_0=='e') ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // grammar/PlazmaScript.g:377:16: ExponentPart
                            {
                            mExponentPart(); if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "DecimalFloatingPoint"

    // $ANTLR start "Identifier"
    public final void mIdentifier() throws RecognitionException {
        try {
            int _type = Identifier;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:387:3: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | Digit )* )
            // grammar/PlazmaScript.g:387:6: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | Digit )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();
            state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // grammar/PlazmaScript.g:387:34: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | Digit )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='0' && LA7_0<='9')||(LA7_0>='A' && LA7_0<='Z')||LA7_0=='_'||(LA7_0>='a' && LA7_0<='z')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // grammar/PlazmaScript.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();
            	    state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Identifier"

    // $ANTLR start "ContextIdentifier"
    public final void mContextIdentifier() throws RecognitionException {
        try {
            int _type = ContextIdentifier;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:396:3: ( ( '$' Identifier ) | ( '$' ( Identifier )? '{' Identifier '}' ) )
            int alt9=2;
            alt9 = dfa9.predict(input);
            switch (alt9) {
                case 1 :
                    // grammar/PlazmaScript.g:396:6: ( '$' Identifier )
                    {
                    // grammar/PlazmaScript.g:396:6: ( '$' Identifier )
                    // grammar/PlazmaScript.g:396:7: '$' Identifier
                    {
                    match('$'); if (state.failed) return ;
                    mIdentifier(); if (state.failed) return ;

                    }


                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:396:25: ( '$' ( Identifier )? '{' Identifier '}' )
                    {
                    // grammar/PlazmaScript.g:396:25: ( '$' ( Identifier )? '{' Identifier '}' )
                    // grammar/PlazmaScript.g:396:26: '$' ( Identifier )? '{' Identifier '}'
                    {
                    match('$'); if (state.failed) return ;
                    // grammar/PlazmaScript.g:396:30: ( Identifier )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( ((LA8_0>='A' && LA8_0<='Z')||LA8_0=='_'||(LA8_0>='a' && LA8_0<='z')) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // grammar/PlazmaScript.g:396:30: Identifier
                            {
                            mIdentifier(); if (state.failed) return ;

                            }
                            break;

                    }

                    match('{'); if (state.failed) return ;
                    mIdentifier(); if (state.failed) return ;
                    match('}'); if (state.failed) return ;

                    }


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ContextIdentifier"

    // $ANTLR start "String"
    public final void mString() throws RecognitionException {
        try {
            int _type = String;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:412:3: ( '\"' (~ ( '\"' | '\\\\' ) | '\\\\' ( '\\\\' | '\"' ) )* '\"' | '\\'' (~ ( '\\'' | '\\\\' ) | '\\\\' ( '\\\\' | '\\'' ) )* '\\'' )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0=='\"') ) {
                alt12=1;
            }
            else if ( (LA12_0=='\'') ) {
                alt12=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // grammar/PlazmaScript.g:412:6: '\"' (~ ( '\"' | '\\\\' ) | '\\\\' ( '\\\\' | '\"' ) )* '\"'
                    {
                    match('\"'); if (state.failed) return ;
                    // grammar/PlazmaScript.g:412:11: (~ ( '\"' | '\\\\' ) | '\\\\' ( '\\\\' | '\"' ) )*
                    loop10:
                    do {
                        int alt10=3;
                        int LA10_0 = input.LA(1);

                        if ( ((LA10_0>='\u0000' && LA10_0<='!')||(LA10_0>='#' && LA10_0<='[')||(LA10_0>=']' && LA10_0<='\uFFFF')) ) {
                            alt10=1;
                        }
                        else if ( (LA10_0=='\\') ) {
                            alt10=2;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // grammar/PlazmaScript.g:412:12: ~ ( '\"' | '\\\\' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();
                    	    state.failed=false;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return ;}
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;
                    	case 2 :
                    	    // grammar/PlazmaScript.g:412:29: '\\\\' ( '\\\\' | '\"' )
                    	    {
                    	    match('\\'); if (state.failed) return ;
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\\' ) {
                    	        input.consume();
                    	    state.failed=false;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return ;}
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);

                    match('\"'); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:413:6: '\\'' (~ ( '\\'' | '\\\\' ) | '\\\\' ( '\\\\' | '\\'' ) )* '\\''
                    {
                    match('\''); if (state.failed) return ;
                    // grammar/PlazmaScript.g:413:11: (~ ( '\\'' | '\\\\' ) | '\\\\' ( '\\\\' | '\\'' ) )*
                    loop11:
                    do {
                        int alt11=3;
                        int LA11_0 = input.LA(1);

                        if ( ((LA11_0>='\u0000' && LA11_0<='&')||(LA11_0>='(' && LA11_0<='[')||(LA11_0>=']' && LA11_0<='\uFFFF')) ) {
                            alt11=1;
                        }
                        else if ( (LA11_0=='\\') ) {
                            alt11=2;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // grammar/PlazmaScript.g:413:12: ~ ( '\\'' | '\\\\' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();
                    	    state.failed=false;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return ;}
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;
                    	case 2 :
                    	    // grammar/PlazmaScript.g:413:29: '\\\\' ( '\\\\' | '\\'' )
                    	    {
                    	    match('\\'); if (state.failed) return ;
                    	    if ( input.LA(1)=='\''||input.LA(1)=='\\' ) {
                    	        input.consume();
                    	    state.failed=false;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return ;}
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);

                    match('\''); if (state.failed) return ;

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
            if ( state.backtracking==0 ) {

                setText(getText().substring(1, getText().length()-1).replaceAll("\\\\(.)", "$1"));

            }    }
        finally {
        }
    }
    // $ANTLR end "String"

    // $ANTLR start "Comment"
    public final void mComment() throws RecognitionException {
        try {
            int _type = Comment;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:417:3: ( '//' (~ ( '\\r' | '\\n' ) )* | '/*' ( . )* '*/' )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0=='/') ) {
                int LA15_1 = input.LA(2);

                if ( (LA15_1=='/') ) {
                    alt15=1;
                }
                else if ( (LA15_1=='*') ) {
                    alt15=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 15, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // grammar/PlazmaScript.g:417:6: '//' (~ ( '\\r' | '\\n' ) )*
                    {
                    match("//"); if (state.failed) return ;

                    // grammar/PlazmaScript.g:417:11: (~ ( '\\r' | '\\n' ) )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( ((LA13_0>='\u0000' && LA13_0<='\t')||(LA13_0>='\u000B' && LA13_0<='\f')||(LA13_0>='\u000E' && LA13_0<='\uFFFF')) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // grammar/PlazmaScript.g:417:11: ~ ( '\\r' | '\\n' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();
                    	    state.failed=false;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return ;}
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);

                    if ( state.backtracking==0 ) {
                      skip();
                    }

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:418:6: '/*' ( . )* '*/'
                    {
                    match("/*"); if (state.failed) return ;

                    // grammar/PlazmaScript.g:418:11: ( . )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0=='*') ) {
                            int LA14_1 = input.LA(2);

                            if ( (LA14_1=='/') ) {
                                alt14=2;
                            }
                            else if ( ((LA14_1>='\u0000' && LA14_1<='.')||(LA14_1>='0' && LA14_1<='\uFFFF')) ) {
                                alt14=1;
                            }


                        }
                        else if ( ((LA14_0>='\u0000' && LA14_0<=')')||(LA14_0>='+' && LA14_0<='\uFFFF')) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // grammar/PlazmaScript.g:418:11: .
                    	    {
                    	    matchAny(); if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);

                    match("*/"); if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                      skip();
                    }

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Comment"

    // $ANTLR start "Space"
    public final void mSpace() throws RecognitionException {
        try {
            int _type = Space;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:422:3: ( ( ' ' | '\\t' | '\\r' | '\\n' | '\\u000C' ) )
            // grammar/PlazmaScript.g:422:6: ( ' ' | '\\t' | '\\r' | '\\n' | '\\u000C' )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||(input.LA(1)>='\f' && input.LA(1)<='\r')||input.LA(1)==' ' ) {
                input.consume();
            state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( state.backtracking==0 ) {
              skip();
            }

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Space"

    // $ANTLR start "ExponentPart"
    public final void mExponentPart() throws RecognitionException {
        try {
            // grammar/PlazmaScript.g:428:5: ( ExponentIndicator SignedInteger )
            // grammar/PlazmaScript.g:428:9: ExponentIndicator SignedInteger
            {
            mExponentIndicator(); if (state.failed) return ;
            mSignedInteger(); if (state.failed) return ;

            }

        }
        finally {
        }
    }
    // $ANTLR end "ExponentPart"

    // $ANTLR start "ExponentIndicator"
    public final void mExponentIndicator() throws RecognitionException {
        try {
            // grammar/PlazmaScript.g:432:5: ( ( 'e' | 'E' ) )
            // grammar/PlazmaScript.g:432:9: ( 'e' | 'E' )
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "ExponentIndicator"

    // $ANTLR start "SignedInteger"
    public final void mSignedInteger() throws RecognitionException {
        try {
            // grammar/PlazmaScript.g:436:5: ( ( Sign )? Digits )
            // grammar/PlazmaScript.g:436:9: ( Sign )? Digits
            {
            // grammar/PlazmaScript.g:436:9: ( Sign )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0=='+'||LA16_0=='-') ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // grammar/PlazmaScript.g:436:9: Sign
                    {
                    mSign(); if (state.failed) return ;

                    }
                    break;

            }

            mDigits(); if (state.failed) return ;

            }

        }
        finally {
        }
    }
    // $ANTLR end "SignedInteger"

    // $ANTLR start "Sign"
    public final void mSign() throws RecognitionException {
        try {
            // grammar/PlazmaScript.g:440:5: ( ( '+' | '-' ) )
            // grammar/PlazmaScript.g:440:9: ( '+' | '-' )
            {
            if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                input.consume();
            state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "Sign"

    // $ANTLR start "DecimalNumeral"
    public final void mDecimalNumeral() throws RecognitionException {
        try {
            // grammar/PlazmaScript.g:444:3: ( '0' | NonZeroDigit ( Digits )? )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0=='0') ) {
                alt18=1;
            }
            else if ( ((LA18_0>='1' && LA18_0<='9')) ) {
                alt18=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // grammar/PlazmaScript.g:444:6: '0'
                    {
                    match('0'); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:445:6: NonZeroDigit ( Digits )?
                    {
                    mNonZeroDigit(); if (state.failed) return ;
                    // grammar/PlazmaScript.g:445:19: ( Digits )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( ((LA17_0>='0' && LA17_0<='9')) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // grammar/PlazmaScript.g:445:19: Digits
                            {
                            mDigits(); if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "DecimalNumeral"

    // $ANTLR start "Digits"
    public final void mDigits() throws RecognitionException {
        try {
            // grammar/PlazmaScript.g:448:5: ( Digit ( Digit )* )
            // grammar/PlazmaScript.g:448:9: Digit ( Digit )*
            {
            mDigit(); if (state.failed) return ;
            // grammar/PlazmaScript.g:448:15: ( Digit )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( ((LA19_0>='0' && LA19_0<='9')) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // grammar/PlazmaScript.g:448:15: Digit
            	    {
            	    mDigit(); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "Digits"

    // $ANTLR start "Digit"
    public final void mDigit() throws RecognitionException {
        try {
            // grammar/PlazmaScript.g:452:3: ( '0' | NonZeroDigit )
            // grammar/PlazmaScript.g:
            {
            if ( (input.LA(1)>='0' && input.LA(1)<='9') ) {
                input.consume();
            state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "Digit"

    // $ANTLR start "NonZeroDigit"
    public final void mNonZeroDigit() throws RecognitionException {
        try {
            // grammar/PlazmaScript.g:457:3: ( '1' .. '9' )
            // grammar/PlazmaScript.g:457:6: '1' .. '9'
            {
            matchRange('1','9'); if (state.failed) return ;

            }

        }
        finally {
        }
    }
    // $ANTLR end "NonZeroDigit"

    public void mTokens() throws RecognitionException {
        // grammar/PlazmaScript.g:1:8: ( T__107 | Println | Print | Assert | Var | Def | If | Else | Return | For | While | In | Null | NaN | Infinity | Break | Continue | XorWord | Or | BitOr | OrWord | And | BitAnd | AndWord | Equals | NEquals | GTEquals | LTEquals | Pow | Not | NotWord | GT | LT | Add | Subtract | Multiply | Divide | Modulus | OBrace | CBrace | OBracket | CBracket | OParen | CParen | SColon | Assign | Comma | QMark | Colon | Range | RangeE | Date | DateTime | Time | Duration | Period | List | Set | Bool | Integer | Number | Identifier | ContextIdentifier | String | Comment | Space )
        int alt20=66;
        alt20 = dfa20.predict(input);
        switch (alt20) {
            case 1 :
                // grammar/PlazmaScript.g:1:10: T__107
                {
                mT__107(); if (state.failed) return ;

                }
                break;
            case 2 :
                // grammar/PlazmaScript.g:1:17: Println
                {
                mPrintln(); if (state.failed) return ;

                }
                break;
            case 3 :
                // grammar/PlazmaScript.g:1:25: Print
                {
                mPrint(); if (state.failed) return ;

                }
                break;
            case 4 :
                // grammar/PlazmaScript.g:1:31: Assert
                {
                mAssert(); if (state.failed) return ;

                }
                break;
            case 5 :
                // grammar/PlazmaScript.g:1:38: Var
                {
                mVar(); if (state.failed) return ;

                }
                break;
            case 6 :
                // grammar/PlazmaScript.g:1:42: Def
                {
                mDef(); if (state.failed) return ;

                }
                break;
            case 7 :
                // grammar/PlazmaScript.g:1:46: If
                {
                mIf(); if (state.failed) return ;

                }
                break;
            case 8 :
                // grammar/PlazmaScript.g:1:49: Else
                {
                mElse(); if (state.failed) return ;

                }
                break;
            case 9 :
                // grammar/PlazmaScript.g:1:54: Return
                {
                mReturn(); if (state.failed) return ;

                }
                break;
            case 10 :
                // grammar/PlazmaScript.g:1:61: For
                {
                mFor(); if (state.failed) return ;

                }
                break;
            case 11 :
                // grammar/PlazmaScript.g:1:65: While
                {
                mWhile(); if (state.failed) return ;

                }
                break;
            case 12 :
                // grammar/PlazmaScript.g:1:71: In
                {
                mIn(); if (state.failed) return ;

                }
                break;
            case 13 :
                // grammar/PlazmaScript.g:1:74: Null
                {
                mNull(); if (state.failed) return ;

                }
                break;
            case 14 :
                // grammar/PlazmaScript.g:1:79: NaN
                {
                mNaN(); if (state.failed) return ;

                }
                break;
            case 15 :
                // grammar/PlazmaScript.g:1:83: Infinity
                {
                mInfinity(); if (state.failed) return ;

                }
                break;
            case 16 :
                // grammar/PlazmaScript.g:1:92: Break
                {
                mBreak(); if (state.failed) return ;

                }
                break;
            case 17 :
                // grammar/PlazmaScript.g:1:98: Continue
                {
                mContinue(); if (state.failed) return ;

                }
                break;
            case 18 :
                // grammar/PlazmaScript.g:1:107: XorWord
                {
                mXorWord(); if (state.failed) return ;

                }
                break;
            case 19 :
                // grammar/PlazmaScript.g:1:115: Or
                {
                mOr(); if (state.failed) return ;

                }
                break;
            case 20 :
                // grammar/PlazmaScript.g:1:118: BitOr
                {
                mBitOr(); if (state.failed) return ;

                }
                break;
            case 21 :
                // grammar/PlazmaScript.g:1:124: OrWord
                {
                mOrWord(); if (state.failed) return ;

                }
                break;
            case 22 :
                // grammar/PlazmaScript.g:1:131: And
                {
                mAnd(); if (state.failed) return ;

                }
                break;
            case 23 :
                // grammar/PlazmaScript.g:1:135: BitAnd
                {
                mBitAnd(); if (state.failed) return ;

                }
                break;
            case 24 :
                // grammar/PlazmaScript.g:1:142: AndWord
                {
                mAndWord(); if (state.failed) return ;

                }
                break;
            case 25 :
                // grammar/PlazmaScript.g:1:150: Equals
                {
                mEquals(); if (state.failed) return ;

                }
                break;
            case 26 :
                // grammar/PlazmaScript.g:1:157: NEquals
                {
                mNEquals(); if (state.failed) return ;

                }
                break;
            case 27 :
                // grammar/PlazmaScript.g:1:165: GTEquals
                {
                mGTEquals(); if (state.failed) return ;

                }
                break;
            case 28 :
                // grammar/PlazmaScript.g:1:174: LTEquals
                {
                mLTEquals(); if (state.failed) return ;

                }
                break;
            case 29 :
                // grammar/PlazmaScript.g:1:183: Pow
                {
                mPow(); if (state.failed) return ;

                }
                break;
            case 30 :
                // grammar/PlazmaScript.g:1:187: Not
                {
                mNot(); if (state.failed) return ;

                }
                break;
            case 31 :
                // grammar/PlazmaScript.g:1:191: NotWord
                {
                mNotWord(); if (state.failed) return ;

                }
                break;
            case 32 :
                // grammar/PlazmaScript.g:1:199: GT
                {
                mGT(); if (state.failed) return ;

                }
                break;
            case 33 :
                // grammar/PlazmaScript.g:1:202: LT
                {
                mLT(); if (state.failed) return ;

                }
                break;
            case 34 :
                // grammar/PlazmaScript.g:1:205: Add
                {
                mAdd(); if (state.failed) return ;

                }
                break;
            case 35 :
                // grammar/PlazmaScript.g:1:209: Subtract
                {
                mSubtract(); if (state.failed) return ;

                }
                break;
            case 36 :
                // grammar/PlazmaScript.g:1:218: Multiply
                {
                mMultiply(); if (state.failed) return ;

                }
                break;
            case 37 :
                // grammar/PlazmaScript.g:1:227: Divide
                {
                mDivide(); if (state.failed) return ;

                }
                break;
            case 38 :
                // grammar/PlazmaScript.g:1:234: Modulus
                {
                mModulus(); if (state.failed) return ;

                }
                break;
            case 39 :
                // grammar/PlazmaScript.g:1:242: OBrace
                {
                mOBrace(); if (state.failed) return ;

                }
                break;
            case 40 :
                // grammar/PlazmaScript.g:1:249: CBrace
                {
                mCBrace(); if (state.failed) return ;

                }
                break;
            case 41 :
                // grammar/PlazmaScript.g:1:256: OBracket
                {
                mOBracket(); if (state.failed) return ;

                }
                break;
            case 42 :
                // grammar/PlazmaScript.g:1:265: CBracket
                {
                mCBracket(); if (state.failed) return ;

                }
                break;
            case 43 :
                // grammar/PlazmaScript.g:1:274: OParen
                {
                mOParen(); if (state.failed) return ;

                }
                break;
            case 44 :
                // grammar/PlazmaScript.g:1:281: CParen
                {
                mCParen(); if (state.failed) return ;

                }
                break;
            case 45 :
                // grammar/PlazmaScript.g:1:288: SColon
                {
                mSColon(); if (state.failed) return ;

                }
                break;
            case 46 :
                // grammar/PlazmaScript.g:1:295: Assign
                {
                mAssign(); if (state.failed) return ;

                }
                break;
            case 47 :
                // grammar/PlazmaScript.g:1:302: Comma
                {
                mComma(); if (state.failed) return ;

                }
                break;
            case 48 :
                // grammar/PlazmaScript.g:1:308: QMark
                {
                mQMark(); if (state.failed) return ;

                }
                break;
            case 49 :
                // grammar/PlazmaScript.g:1:314: Colon
                {
                mColon(); if (state.failed) return ;

                }
                break;
            case 50 :
                // grammar/PlazmaScript.g:1:320: Range
                {
                mRange(); if (state.failed) return ;

                }
                break;
            case 51 :
                // grammar/PlazmaScript.g:1:326: RangeE
                {
                mRangeE(); if (state.failed) return ;

                }
                break;
            case 52 :
                // grammar/PlazmaScript.g:1:333: Date
                {
                mDate(); if (state.failed) return ;

                }
                break;
            case 53 :
                // grammar/PlazmaScript.g:1:338: DateTime
                {
                mDateTime(); if (state.failed) return ;

                }
                break;
            case 54 :
                // grammar/PlazmaScript.g:1:347: Time
                {
                mTime(); if (state.failed) return ;

                }
                break;
            case 55 :
                // grammar/PlazmaScript.g:1:352: Duration
                {
                mDuration(); if (state.failed) return ;

                }
                break;
            case 56 :
                // grammar/PlazmaScript.g:1:361: Period
                {
                mPeriod(); if (state.failed) return ;

                }
                break;
            case 57 :
                // grammar/PlazmaScript.g:1:368: List
                {
                mList(); if (state.failed) return ;

                }
                break;
            case 58 :
                // grammar/PlazmaScript.g:1:373: Set
                {
                mSet(); if (state.failed) return ;

                }
                break;
            case 59 :
                // grammar/PlazmaScript.g:1:377: Bool
                {
                mBool(); if (state.failed) return ;

                }
                break;
            case 60 :
                // grammar/PlazmaScript.g:1:382: Integer
                {
                mInteger(); if (state.failed) return ;

                }
                break;
            case 61 :
                // grammar/PlazmaScript.g:1:390: Number
                {
                mNumber(); if (state.failed) return ;

                }
                break;
            case 62 :
                // grammar/PlazmaScript.g:1:397: Identifier
                {
                mIdentifier(); if (state.failed) return ;

                }
                break;
            case 63 :
                // grammar/PlazmaScript.g:1:408: ContextIdentifier
                {
                mContextIdentifier(); if (state.failed) return ;

                }
                break;
            case 64 :
                // grammar/PlazmaScript.g:1:426: String
                {
                mString(); if (state.failed) return ;

                }
                break;
            case 65 :
                // grammar/PlazmaScript.g:1:433: Comment
                {
                mComment(); if (state.failed) return ;

                }
                break;
            case 66 :
                // grammar/PlazmaScript.g:1:441: Space
                {
                mSpace(); if (state.failed) return ;

                }
                break;

        }

    }

    // $ANTLR start synpred1_PlazmaScript
    public final void synpred1_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:370:5: ( DecimalNumeral '..' )
        // grammar/PlazmaScript.g:370:6: DecimalNumeral '..'
        {
        mDecimalNumeral(); if (state.failed) return ;
        match(".."); if (state.failed) return ;


        }
    }
    // $ANTLR end synpred1_PlazmaScript

    public final boolean synpred1_PlazmaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_PlazmaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA9 dfa9 = new DFA9(this);
    protected DFA20 dfa20 = new DFA20(this);
    static final String DFA9_eotS =
        "\2\uffff\1\5\1\uffff\1\5\1\uffff";
    static final String DFA9_eofS =
        "\6\uffff";
    static final String DFA9_minS =
        "\1\44\1\101\1\60\1\uffff\1\60\1\uffff";
    static final String DFA9_maxS =
        "\1\44\2\173\1\uffff\1\173\1\uffff";
    static final String DFA9_acceptS =
        "\3\uffff\1\2\1\uffff\1\1";
    static final String DFA9_specialS =
        "\6\uffff}>";
    static final String[] DFA9_transitionS = {
            "\1\1",
            "\32\2\4\uffff\1\2\1\uffff\32\2\1\3",
            "\12\4\7\uffff\32\4\4\uffff\1\4\1\uffff\32\4\1\3",
            "",
            "\12\4\7\uffff\32\4\4\uffff\1\4\1\uffff\32\4\1\3",
            ""
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "395:1: ContextIdentifier : ( ( '$' Identifier ) | ( '$' ( Identifier )? '{' Identifier '}' ) );";
        }
    }
    static final String DFA20_eotS =
        "\1\uffff\1\66\17\60\1\113\1\60\1\116\1\120\1\122\1\124\1\126\4"+
        "\uffff\1\130\13\uffff\6\60\2\140\4\uffff\1\143\2\uffff\5\60\1\151"+
        "\1\152\14\60\2\uffff\1\167\14\uffff\7\60\1\uffff\1\140\2\uffff\2"+
        "\60\1\u0082\1\u0083\1\u0084\2\uffff\2\60\1\u0087\3\60\1\u008b\1"+
        "\u008c\3\60\1\u0090\1\uffff\5\60\1\u0096\1\60\1\140\2\60\3\uffff"+
        "\1\u009a\1\60\1\uffff\2\60\1\u009e\2\uffff\3\60\1\uffff\1\u00a3"+
        "\1\60\1\u00a5\1\60\1\u00a7\1\uffff\1\u00a8\1\u00aa\1\60\1\uffff"+
        "\1\60\1\u00a8\1\u00ad\1\uffff\1\60\1\u00af\2\60\1\uffff\1\60\1\uffff"+
        "\1\60\2\uffff\1\60\1\uffff\1\u00b5\1\u00b6\1\uffff\1\60\1\uffff"+
        "\3\60\1\u00bb\1\u00bc\2\uffff\4\60\2\uffff\1\u00c1\1\u00c2\1\u00c3"+
        "\1\u00c4\4\uffff";
    static final String DFA20_eofS =
        "\u00c5\uffff";
    static final String DFA20_minS =
        "\1\11\1\56\1\162\1\156\1\141\1\145\1\146\1\154\1\145\1\141\1\150"+
        "\1\157\1\141\1\156\1\162\2\157\1\174\1\162\1\46\4\75\4\uffff\1\52"+
        "\13\uffff\1\141\1\151\1\145\1\151\1\145\1\162\2\56\4\uffff\1\74"+
        "\2\uffff\1\151\1\163\1\144\1\162\1\146\2\60\1\163\1\164\1\162\1"+
        "\154\1\151\1\154\1\164\1\116\1\146\1\145\1\156\1\162\2\uffff\1\60"+
        "\14\uffff\1\164\1\162\1\155\1\162\1\163\1\164\1\165\1\uffff\1\56"+
        "\2\uffff\1\156\1\145\3\60\2\uffff\1\145\1\165\1\60\1\163\2\154\2"+
        "\60\1\151\1\141\1\164\1\60\1\uffff\1\145\1\141\1\145\1\151\1\164"+
        "\1\60\1\145\1\56\1\164\1\162\3\uffff\1\60\1\162\1\uffff\2\145\1"+
        "\60\2\uffff\1\156\1\153\1\151\1\uffff\1\60\1\164\1\60\1\157\1\60"+
        "\1\uffff\2\60\1\164\1\uffff\1\156\2\60\1\uffff\1\151\1\60\1\156"+
        "\1\151\1\uffff\1\151\1\uffff\1\144\2\uffff\1\156\1\uffff\2\60\1"+
        "\uffff\1\164\1\uffff\1\165\1\155\1\157\2\60\2\uffff\1\171\2\145"+
        "\1\156\2\uffff\4\60\4\uffff";
    static final String DFA20_maxS =
        "\1\175\1\71\1\162\1\163\1\141\1\145\1\156\1\154\1\145\1\157\1\150"+
        "\1\165\1\141\1\156\1\162\2\157\1\174\1\162\1\46\4\75\4\uffff\1\57"+
        "\13\uffff\1\165\1\151\1\145\1\151\1\145\1\162\2\145\4\uffff\1\74"+
        "\2\uffff\1\151\1\163\1\144\1\162\1\146\2\172\1\163\1\164\1\162\1"+
        "\154\1\151\1\154\1\164\1\116\1\146\1\145\1\156\1\162\2\uffff\1\172"+
        "\14\uffff\1\164\1\162\1\155\1\162\1\163\1\164\1\165\1\uffff\1\145"+
        "\2\uffff\1\156\1\145\3\172\2\uffff\1\145\1\165\1\172\1\163\2\154"+
        "\2\172\1\151\1\141\1\164\1\172\1\uffff\1\145\1\141\1\145\1\151\1"+
        "\164\1\172\2\145\1\164\1\162\3\uffff\1\172\1\162\1\uffff\2\145\1"+
        "\172\2\uffff\1\156\1\153\1\151\1\uffff\1\172\1\164\1\172\1\157\1"+
        "\172\1\uffff\2\172\1\164\1\uffff\1\156\2\172\1\uffff\1\151\1\172"+
        "\1\156\1\151\1\uffff\1\151\1\uffff\1\144\2\uffff\1\156\1\uffff\2"+
        "\172\1\uffff\1\164\1\uffff\1\165\1\155\1\157\2\172\2\uffff\1\171"+
        "\2\145\1\156\2\uffff\4\172\4\uffff";
    static final String DFA20_acceptS =
        "\30\uffff\1\35\1\42\1\43\1\44\1\uffff\1\46\1\47\1\50\1\51\1\52"+
        "\1\53\1\54\1\55\1\57\1\60\1\61\10\uffff\1\76\1\77\1\100\1\102\1"+
        "\uffff\1\75\1\1\23\uffff\1\23\1\24\1\uffff\1\26\1\27\1\31\1\56\1"+
        "\32\1\36\1\33\1\40\1\34\1\41\1\101\1\45\7\uffff\1\74\1\uffff\1\63"+
        "\1\62\5\uffff\1\7\1\14\14\uffff\1\25\12\uffff\1\30\1\5\1\6\2\uffff"+
        "\1\12\3\uffff\1\37\1\16\3\uffff\1\22\5\uffff\1\72\3\uffff\1\10\3"+
        "\uffff\1\15\4\uffff\1\64\1\uffff\1\66\1\uffff\1\71\1\73\1\uffff"+
        "\1\3\2\uffff\1\13\1\uffff\1\20\5\uffff\1\4\1\11\4\uffff\1\70\1\2"+
        "\4\uffff\1\17\1\21\1\65\1\67";
    static final String DFA20_specialS =
        "\u00c5\uffff}>";
    static final String[] DFA20_transitionS = {
            "\2\63\1\uffff\2\63\22\uffff\1\63\1\25\1\62\1\uffff\1\61\1\35"+
            "\1\23\1\62\1\42\1\43\1\33\1\31\1\45\1\32\1\1\1\34\1\56\11\57"+
            "\1\47\1\44\1\27\1\24\1\26\1\46\1\uffff\3\60\1\50\4\60\1\15\2"+
            "\60\1\53\1\60\1\14\1\60\1\52\2\60\1\54\1\51\6\60\1\40\1\uffff"+
            "\1\41\1\30\1\60\1\uffff\1\3\1\16\1\17\1\5\1\7\1\11\2\60\1\6"+
            "\4\60\1\13\1\22\1\2\1\60\1\10\1\60\1\55\1\60\1\4\1\12\1\20\2"+
            "\60\1\36\1\21\1\37",
            "\1\64\1\uffff\12\65",
            "\1\67",
            "\1\71\4\uffff\1\70",
            "\1\72",
            "\1\73",
            "\1\74\7\uffff\1\75",
            "\1\76",
            "\1\77",
            "\1\101\15\uffff\1\100",
            "\1\102",
            "\1\104\5\uffff\1\103",
            "\1\105",
            "\1\106",
            "\1\107",
            "\1\110",
            "\1\111",
            "\1\112",
            "\1\114",
            "\1\115",
            "\1\117",
            "\1\121",
            "\1\123",
            "\1\125",
            "",
            "",
            "",
            "",
            "\1\127\4\uffff\1\127",
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
            "\1\131\23\uffff\1\132",
            "\1\133",
            "\1\134",
            "\1\135",
            "\1\136",
            "\1\137",
            "\1\65\1\uffff\12\65\13\uffff\1\65\37\uffff\1\65",
            "\1\65\1\uffff\12\141\13\uffff\1\65\37\uffff\1\65",
            "",
            "",
            "",
            "",
            "\1\142",
            "",
            "",
            "\1\144",
            "\1\145",
            "\1\146",
            "\1\147",
            "\1\150",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "\1\153",
            "\1\154",
            "\1\155",
            "\1\156",
            "\1\157",
            "\1\160",
            "\1\161",
            "\1\162",
            "\1\163",
            "\1\164",
            "\1\165",
            "\1\166",
            "",
            "",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
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
            "",
            "\1\170",
            "\1\171",
            "\1\172",
            "\1\173",
            "\1\174",
            "\1\175",
            "\1\176",
            "",
            "\1\65\1\uffff\12\177\13\uffff\1\65\37\uffff\1\65",
            "",
            "",
            "\1\u0080",
            "\1\u0081",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "",
            "",
            "\1\u0085",
            "\1\u0086",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "\1\u0088",
            "\1\u0089",
            "\1\u008a",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "\1\u008d",
            "\1\u008e",
            "\1\u008f",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "",
            "\1\u0091",
            "\1\u0092",
            "\1\u0093",
            "\1\u0094",
            "\1\u0095",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "\1\u0097",
            "\1\65\1\uffff\12\177\13\uffff\1\65\37\uffff\1\65",
            "\1\u0098",
            "\1\u0099",
            "",
            "",
            "",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "\1\u009b",
            "",
            "\1\u009c",
            "\1\u009d",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "",
            "",
            "\1\u009f",
            "\1\u00a0",
            "\1\u00a1",
            "",
            "\12\60\7\uffff\23\60\1\u00a2\6\60\4\uffff\1\60\1\uffff\32"+
            "\60",
            "\1\u00a4",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "\1\u00a6",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\13\60\1\u00a9\16"+
            "\60",
            "\1\u00ab",
            "",
            "\1\u00ac",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "",
            "\1\u00ae",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "\1\u00b0",
            "\1\u00b1",
            "",
            "\1\u00b2",
            "",
            "\1\u00b3",
            "",
            "",
            "\1\u00b4",
            "",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "",
            "\1\u00b7",
            "",
            "\1\u00b8",
            "\1\u00b9",
            "\1\u00ba",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "",
            "",
            "\1\u00bd",
            "\1\u00be",
            "\1\u00bf",
            "\1\u00c0",
            "",
            "",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA20_eot = DFA.unpackEncodedString(DFA20_eotS);
    static final short[] DFA20_eof = DFA.unpackEncodedString(DFA20_eofS);
    static final char[] DFA20_min = DFA.unpackEncodedStringToUnsignedChars(DFA20_minS);
    static final char[] DFA20_max = DFA.unpackEncodedStringToUnsignedChars(DFA20_maxS);
    static final short[] DFA20_accept = DFA.unpackEncodedString(DFA20_acceptS);
    static final short[] DFA20_special = DFA.unpackEncodedString(DFA20_specialS);
    static final short[][] DFA20_transition;

    static {
        int numStates = DFA20_transitionS.length;
        DFA20_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA20_transition[i] = DFA.unpackEncodedString(DFA20_transitionS[i]);
        }
    }

    class DFA20 extends DFA {

        public DFA20(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 20;
            this.eot = DFA20_eot;
            this.eof = DFA20_eof;
            this.min = DFA20_min;
            this.max = DFA20_max;
            this.accept = DFA20_accept;
            this.special = DFA20_special;
            this.transition = DFA20_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__107 | Println | Print | Assert | Var | Def | If | Else | Return | For | While | In | Null | NaN | Infinity | Break | Continue | XorWord | Or | BitOr | OrWord | And | BitAnd | AndWord | Equals | NEquals | GTEquals | LTEquals | Pow | Not | NotWord | GT | LT | Add | Subtract | Multiply | Divide | Modulus | OBrace | CBrace | OBracket | CBracket | OParen | CParen | SColon | Assign | Comma | QMark | Colon | Range | RangeE | Date | DateTime | Time | Duration | Period | List | Set | Bool | Integer | Number | Identifier | ContextIdentifier | String | Comment | Space );";
        }
    }
 

}