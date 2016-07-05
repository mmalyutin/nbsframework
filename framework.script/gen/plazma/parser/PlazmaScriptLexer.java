// $ANTLR 3.2 Sep 23, 2009 12:02:23 grammar/PlazmaScript.g 2016-07-05 11:24:21

  package plazma.parser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class PlazmaScriptLexer extends Lexer {
    public static final int FUNCTION=18;
    public static final int LT=68;
    public static final int TERNARY=15;
    public static final int EXP_LIST=12;
    public static final int Date=37;
    public static final int EOF=-1;
    public static final int DD=92;
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
    public static final int CBrace=75;
    public static final int RETURN=5;
    public static final int OrWord=56;
    public static final int Null=51;
    public static final int ContextIdentifier=87;
    public static final int CBracket=77;
    public static final int Println=34;
    public static final int Bool=50;
    public static final int Modulus=73;
    public static final int Colon=84;
    public static final int AndWord=59;
    public static final int LIST=26;
    public static final int Def=42;
    public static final int RangeE=46;
    public static final int LOOKUP=27;
    public static final int Range=47;
    public static final int Break=31;
    public static final int BitOr=55;
    public static final int GT=67;
    public static final int STATEMENTS=6;
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

    public PlazmaScriptLexer() {;} 
    public PlazmaScriptLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public PlazmaScriptLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "grammar/PlazmaScript.g"; }

    // $ANTLR start "T__93"
    public final void mT__93() throws RecognitionException {
        try {
            int _type = T__93;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:7:7: ( '.' )
            // grammar/PlazmaScript.g:7:9: '.'
            {
            match('.'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__93"

    // $ANTLR start "Println"
    public final void mPrintln() throws RecognitionException {
        try {
            int _type = Println;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:271:10: ( 'println' )
            // grammar/PlazmaScript.g:271:12: 'println'
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
            // grammar/PlazmaScript.g:272:10: ( 'print' )
            // grammar/PlazmaScript.g:272:12: 'print'
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
            // grammar/PlazmaScript.g:273:10: ( 'assert' )
            // grammar/PlazmaScript.g:273:12: 'assert'
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
            // grammar/PlazmaScript.g:274:10: ( 'var' )
            // grammar/PlazmaScript.g:274:12: 'var'
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
            // grammar/PlazmaScript.g:275:10: ( 'def' )
            // grammar/PlazmaScript.g:275:12: 'def'
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
            // grammar/PlazmaScript.g:276:10: ( 'if' )
            // grammar/PlazmaScript.g:276:12: 'if'
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
            // grammar/PlazmaScript.g:277:10: ( 'else' )
            // grammar/PlazmaScript.g:277:12: 'else'
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
            // grammar/PlazmaScript.g:278:10: ( 'return' )
            // grammar/PlazmaScript.g:278:12: 'return'
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
            // grammar/PlazmaScript.g:279:10: ( 'for' )
            // grammar/PlazmaScript.g:279:12: 'for'
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
            // grammar/PlazmaScript.g:280:10: ( 'while' )
            // grammar/PlazmaScript.g:280:12: 'while'
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
            // grammar/PlazmaScript.g:281:10: ( 'in' )
            // grammar/PlazmaScript.g:281:12: 'in'
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
            // grammar/PlazmaScript.g:282:10: ( 'null' )
            // grammar/PlazmaScript.g:282:12: 'null'
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

    // $ANTLR start "Break"
    public final void mBreak() throws RecognitionException {
        try {
            int _type = Break;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:283:10: ( 'break' )
            // grammar/PlazmaScript.g:283:12: 'break'
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
            // grammar/PlazmaScript.g:284:10: ( 'continue' )
            // grammar/PlazmaScript.g:284:12: 'continue'
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
            // grammar/PlazmaScript.g:286:10: ( 'xor' )
            // grammar/PlazmaScript.g:286:12: 'xor'
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
            // grammar/PlazmaScript.g:287:10: ( '||' )
            // grammar/PlazmaScript.g:287:12: '||'
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
            // grammar/PlazmaScript.g:288:10: ( '|' )
            // grammar/PlazmaScript.g:288:12: '|'
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
            // grammar/PlazmaScript.g:289:10: ( 'or' )
            // grammar/PlazmaScript.g:289:12: 'or'
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
            // grammar/PlazmaScript.g:290:10: ( '&&' )
            // grammar/PlazmaScript.g:290:12: '&&'
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
            // grammar/PlazmaScript.g:291:10: ( '&' )
            // grammar/PlazmaScript.g:291:12: '&'
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
            // grammar/PlazmaScript.g:292:10: ( 'and' )
            // grammar/PlazmaScript.g:292:12: 'and'
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
            // grammar/PlazmaScript.g:293:10: ( '==' )
            // grammar/PlazmaScript.g:293:12: '=='
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
            // grammar/PlazmaScript.g:294:10: ( '!=' )
            // grammar/PlazmaScript.g:294:12: '!='
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
            // grammar/PlazmaScript.g:295:10: ( '>=' )
            // grammar/PlazmaScript.g:295:12: '>='
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
            // grammar/PlazmaScript.g:296:10: ( '<=' )
            // grammar/PlazmaScript.g:296:12: '<='
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
            // grammar/PlazmaScript.g:297:10: ( '^' )
            // grammar/PlazmaScript.g:297:12: '^'
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
            // grammar/PlazmaScript.g:298:10: ( '!' )
            // grammar/PlazmaScript.g:298:12: '!'
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
            // grammar/PlazmaScript.g:299:10: ( 'not' )
            // grammar/PlazmaScript.g:299:12: 'not'
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
            // grammar/PlazmaScript.g:300:10: ( '>' )
            // grammar/PlazmaScript.g:300:12: '>'
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
            // grammar/PlazmaScript.g:301:10: ( '<' )
            // grammar/PlazmaScript.g:301:12: '<'
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
            // grammar/PlazmaScript.g:302:10: ( '+' )
            // grammar/PlazmaScript.g:302:12: '+'
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
            // grammar/PlazmaScript.g:303:10: ( '-' )
            // grammar/PlazmaScript.g:303:12: '-'
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
            // grammar/PlazmaScript.g:304:10: ( '*' )
            // grammar/PlazmaScript.g:304:12: '*'
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
            // grammar/PlazmaScript.g:305:10: ( '/' )
            // grammar/PlazmaScript.g:305:12: '/'
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
            // grammar/PlazmaScript.g:307:10: ( '%' )
            // grammar/PlazmaScript.g:307:12: '%'
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
            // grammar/PlazmaScript.g:308:10: ( '{' )
            // grammar/PlazmaScript.g:308:12: '{'
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
            // grammar/PlazmaScript.g:309:10: ( '}' )
            // grammar/PlazmaScript.g:309:12: '}'
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
            // grammar/PlazmaScript.g:310:10: ( '[' )
            // grammar/PlazmaScript.g:310:12: '['
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
            // grammar/PlazmaScript.g:311:10: ( ']' )
            // grammar/PlazmaScript.g:311:12: ']'
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
            // grammar/PlazmaScript.g:312:10: ( '(' )
            // grammar/PlazmaScript.g:312:12: '('
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
            // grammar/PlazmaScript.g:313:10: ( ')' )
            // grammar/PlazmaScript.g:313:12: ')'
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
            // grammar/PlazmaScript.g:314:10: ( ';' )
            // grammar/PlazmaScript.g:314:12: ';'
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
            // grammar/PlazmaScript.g:315:10: ( '=' )
            // grammar/PlazmaScript.g:315:12: '='
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
            // grammar/PlazmaScript.g:316:10: ( ',' )
            // grammar/PlazmaScript.g:316:12: ','
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
            // grammar/PlazmaScript.g:317:10: ( '?' )
            // grammar/PlazmaScript.g:317:12: '?'
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
            // grammar/PlazmaScript.g:318:10: ( ':' )
            // grammar/PlazmaScript.g:318:12: ':'
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
            // grammar/PlazmaScript.g:320:10: ( '..' )
            // grammar/PlazmaScript.g:320:12: '..'
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
            // grammar/PlazmaScript.g:321:10: ( '..<' )
            // grammar/PlazmaScript.g:321:12: '..<'
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
            // grammar/PlazmaScript.g:323:10: ( 'Date' )
            // grammar/PlazmaScript.g:323:12: 'Date'
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

    // $ANTLR start "List"
    public final void mList() throws RecognitionException {
        try {
            int _type = List;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:324:10: ( 'List' )
            // grammar/PlazmaScript.g:324:12: 'List'
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

    // $ANTLR start "Bool"
    public final void mBool() throws RecognitionException {
        try {
            int _type = Bool;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:327:3: ( 'true' | 'false' )
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
                    // grammar/PlazmaScript.g:327:6: 'true'
                    {
                    match("true"); if (state.failed) return ;


                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:328:6: 'false'
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
            // grammar/PlazmaScript.g:343:3: ( Int )
            // grammar/PlazmaScript.g:343:6: Int
            {
            mInt(); if (state.failed) return ;

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
            // grammar/PlazmaScript.g:347:3: ( ( Int '..' )=> Integer | Int ( '.' ( Digit )* )? )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( ((LA4_0>='1' && LA4_0<='9')) ) {
                int LA4_1 = input.LA(2);

                if ( (synpred1_PlazmaScript()) ) {
                    alt4=1;
                }
                else if ( (true) ) {
                    alt4=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA4_0=='0') ) {
                int LA4_2 = input.LA(2);

                if ( (synpred1_PlazmaScript()) ) {
                    alt4=1;
                }
                else if ( (true) ) {
                    alt4=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // grammar/PlazmaScript.g:347:5: ( Int '..' )=> Integer
                    {
                    mInteger(); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      _type=Integer;
                    }

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:348:7: Int ( '.' ( Digit )* )?
                    {
                    mInt(); if (state.failed) return ;
                    // grammar/PlazmaScript.g:348:11: ( '.' ( Digit )* )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0=='.') ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // grammar/PlazmaScript.g:348:12: '.' ( Digit )*
                            {
                            match('.'); if (state.failed) return ;
                            // grammar/PlazmaScript.g:348:16: ( Digit )*
                            loop2:
                            do {
                                int alt2=2;
                                int LA2_0 = input.LA(1);

                                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                                    alt2=1;
                                }


                                switch (alt2) {
                            	case 1 :
                            	    // grammar/PlazmaScript.g:348:16: Digit
                            	    {
                            	    mDigit(); if (state.failed) return ;

                            	    }
                            	    break;

                            	default :
                            	    break loop2;
                                }
                            } while (true);


                            }
                            break;

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
    // $ANTLR end "Number"

    // $ANTLR start "Identifier"
    public final void mIdentifier() throws RecognitionException {
        try {
            int _type = Identifier;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:362:3: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | Digit )* )
            // grammar/PlazmaScript.g:362:6: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | Digit )*
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

            // grammar/PlazmaScript.g:362:34: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | Digit )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='0' && LA5_0<='9')||(LA5_0>='A' && LA5_0<='Z')||LA5_0=='_'||(LA5_0>='a' && LA5_0<='z')) ) {
                    alt5=1;
                }


                switch (alt5) {
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
            	    break loop5;
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
            // grammar/PlazmaScript.g:371:3: ( ( '$' Identifier ) | ( '$' ( Identifier )? '{' Identifier '}' ) )
            int alt7=2;
            alt7 = dfa7.predict(input);
            switch (alt7) {
                case 1 :
                    // grammar/PlazmaScript.g:371:6: ( '$' Identifier )
                    {
                    // grammar/PlazmaScript.g:371:6: ( '$' Identifier )
                    // grammar/PlazmaScript.g:371:7: '$' Identifier
                    {
                    match('$'); if (state.failed) return ;
                    mIdentifier(); if (state.failed) return ;

                    }


                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:371:25: ( '$' ( Identifier )? '{' Identifier '}' )
                    {
                    // grammar/PlazmaScript.g:371:25: ( '$' ( Identifier )? '{' Identifier '}' )
                    // grammar/PlazmaScript.g:371:26: '$' ( Identifier )? '{' Identifier '}'
                    {
                    match('$'); if (state.failed) return ;
                    // grammar/PlazmaScript.g:371:30: ( Identifier )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( ((LA6_0>='A' && LA6_0<='Z')||LA6_0=='_'||(LA6_0>='a' && LA6_0<='z')) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // grammar/PlazmaScript.g:371:30: Identifier
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
            // grammar/PlazmaScript.g:387:3: ( '\"' (~ ( '\"' | '\\\\' ) | '\\\\' ( '\\\\' | '\"' ) )* '\"' | '\\'' (~ ( '\\'' | '\\\\' ) | '\\\\' ( '\\\\' | '\\'' ) )* '\\'' )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0=='\"') ) {
                alt10=1;
            }
            else if ( (LA10_0=='\'') ) {
                alt10=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // grammar/PlazmaScript.g:387:6: '\"' (~ ( '\"' | '\\\\' ) | '\\\\' ( '\\\\' | '\"' ) )* '\"'
                    {
                    match('\"'); if (state.failed) return ;
                    // grammar/PlazmaScript.g:387:11: (~ ( '\"' | '\\\\' ) | '\\\\' ( '\\\\' | '\"' ) )*
                    loop8:
                    do {
                        int alt8=3;
                        int LA8_0 = input.LA(1);

                        if ( ((LA8_0>='\u0000' && LA8_0<='!')||(LA8_0>='#' && LA8_0<='[')||(LA8_0>=']' && LA8_0<='\uFFFF')) ) {
                            alt8=1;
                        }
                        else if ( (LA8_0=='\\') ) {
                            alt8=2;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // grammar/PlazmaScript.g:387:12: ~ ( '\"' | '\\\\' )
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
                    	    // grammar/PlazmaScript.g:387:29: '\\\\' ( '\\\\' | '\"' )
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
                    	    break loop8;
                        }
                    } while (true);

                    match('\"'); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:388:6: '\\'' (~ ( '\\'' | '\\\\' ) | '\\\\' ( '\\\\' | '\\'' ) )* '\\''
                    {
                    match('\''); if (state.failed) return ;
                    // grammar/PlazmaScript.g:388:11: (~ ( '\\'' | '\\\\' ) | '\\\\' ( '\\\\' | '\\'' ) )*
                    loop9:
                    do {
                        int alt9=3;
                        int LA9_0 = input.LA(1);

                        if ( ((LA9_0>='\u0000' && LA9_0<='&')||(LA9_0>='(' && LA9_0<='[')||(LA9_0>=']' && LA9_0<='\uFFFF')) ) {
                            alt9=1;
                        }
                        else if ( (LA9_0=='\\') ) {
                            alt9=2;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // grammar/PlazmaScript.g:388:12: ~ ( '\\'' | '\\\\' )
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
                    	    // grammar/PlazmaScript.g:388:29: '\\\\' ( '\\\\' | '\\'' )
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
                    	    break loop9;
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
            // grammar/PlazmaScript.g:392:3: ( '//' (~ ( '\\r' | '\\n' ) )* | '/*' ( . )* '*/' )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0=='/') ) {
                int LA13_1 = input.LA(2);

                if ( (LA13_1=='/') ) {
                    alt13=1;
                }
                else if ( (LA13_1=='*') ) {
                    alt13=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // grammar/PlazmaScript.g:392:6: '//' (~ ( '\\r' | '\\n' ) )*
                    {
                    match("//"); if (state.failed) return ;

                    // grammar/PlazmaScript.g:392:11: (~ ( '\\r' | '\\n' ) )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( ((LA11_0>='\u0000' && LA11_0<='\t')||(LA11_0>='\u000B' && LA11_0<='\f')||(LA11_0>='\u000E' && LA11_0<='\uFFFF')) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // grammar/PlazmaScript.g:392:11: ~ ( '\\r' | '\\n' )
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
                    	    break loop11;
                        }
                    } while (true);

                    if ( state.backtracking==0 ) {
                      skip();
                    }

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:393:6: '/*' ( . )* '*/'
                    {
                    match("/*"); if (state.failed) return ;

                    // grammar/PlazmaScript.g:393:11: ( . )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0=='*') ) {
                            int LA12_1 = input.LA(2);

                            if ( (LA12_1=='/') ) {
                                alt12=2;
                            }
                            else if ( ((LA12_1>='\u0000' && LA12_1<='.')||(LA12_1>='0' && LA12_1<='\uFFFF')) ) {
                                alt12=1;
                            }


                        }
                        else if ( ((LA12_0>='\u0000' && LA12_0<=')')||(LA12_0>='+' && LA12_0<='\uFFFF')) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // grammar/PlazmaScript.g:393:11: .
                    	    {
                    	    matchAny(); if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop12;
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
            // grammar/PlazmaScript.g:397:3: ( ( ' ' | '\\t' | '\\r' | '\\n' | '\\u000C' ) )
            // grammar/PlazmaScript.g:397:6: ( ' ' | '\\t' | '\\r' | '\\n' | '\\u000C' )
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

    // $ANTLR start "Int"
    public final void mInt() throws RecognitionException {
        try {
            // grammar/PlazmaScript.g:401:3: ( '1' .. '9' ( Digit )* | '0' )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( ((LA15_0>='1' && LA15_0<='9')) ) {
                alt15=1;
            }
            else if ( (LA15_0=='0') ) {
                alt15=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // grammar/PlazmaScript.g:401:6: '1' .. '9' ( Digit )*
                    {
                    matchRange('1','9'); if (state.failed) return ;
                    // grammar/PlazmaScript.g:401:15: ( Digit )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( ((LA14_0>='0' && LA14_0<='9')) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // grammar/PlazmaScript.g:401:15: Digit
                    	    {
                    	    mDigit(); if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:401:24: '0'
                    {
                    match('0'); if (state.failed) return ;

                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "Int"

    // $ANTLR start "Digit"
    public final void mDigit() throws RecognitionException {
        try {
            // grammar/PlazmaScript.g:404:3: ( '0' .. '9' )
            // grammar/PlazmaScript.g:404:6: '0' .. '9'
            {
            matchRange('0','9'); if (state.failed) return ;

            }

        }
        finally {
        }
    }
    // $ANTLR end "Digit"

    // $ANTLR start "YYYY"
    public final void mYYYY() throws RecognitionException {
        try {
            // grammar/PlazmaScript.g:409:3: ( '1' .. '9' ( Digit )* )
            // grammar/PlazmaScript.g:409:6: '1' .. '9' ( Digit )*
            {
            matchRange('1','9'); if (state.failed) return ;
            // grammar/PlazmaScript.g:409:15: ( Digit )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>='0' && LA16_0<='9')) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // grammar/PlazmaScript.g:409:15: Digit
            	    {
            	    mDigit(); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "YYYY"

    // $ANTLR start "MM"
    public final void mMM() throws RecognitionException {
        try {
            // grammar/PlazmaScript.g:412:3: ( ( '1' .. '9' ) | ( '0' '1' .. '9' ) | ( '1' '0' .. '2' ) )
            int alt17=3;
            switch ( input.LA(1) ) {
            case '1':
                {
                int LA17_1 = input.LA(2);

                if ( ((LA17_1>='0' && LA17_1<='2')) ) {
                    alt17=3;
                }
                else {
                    alt17=1;}
                }
                break;
            case '0':
                {
                alt17=2;
                }
                break;
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                {
                alt17=1;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // grammar/PlazmaScript.g:412:6: ( '1' .. '9' )
                    {
                    // grammar/PlazmaScript.g:412:6: ( '1' .. '9' )
                    // grammar/PlazmaScript.g:412:7: '1' .. '9'
                    {
                    matchRange('1','9'); if (state.failed) return ;

                    }


                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:412:19: ( '0' '1' .. '9' )
                    {
                    // grammar/PlazmaScript.g:412:19: ( '0' '1' .. '9' )
                    // grammar/PlazmaScript.g:412:20: '0' '1' .. '9'
                    {
                    match('0'); if (state.failed) return ;
                    matchRange('1','9'); if (state.failed) return ;

                    }


                    }
                    break;
                case 3 :
                    // grammar/PlazmaScript.g:412:36: ( '1' '0' .. '2' )
                    {
                    // grammar/PlazmaScript.g:412:36: ( '1' '0' .. '2' )
                    // grammar/PlazmaScript.g:412:37: '1' '0' .. '2'
                    {
                    match('1'); if (state.failed) return ;
                    matchRange('0','2'); if (state.failed) return ;

                    }


                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "MM"

    // $ANTLR start "DD"
    public final void mDD() throws RecognitionException {
        try {
            // grammar/PlazmaScript.g:416:3: ( ( '1' .. '9' ) | ( '0' '1' .. '9' ) | ( '1' .. '2' '0' .. '9' ) | ( '3' '0' .. '1' ) )
            int alt18=4;
            switch ( input.LA(1) ) {
            case '1':
            case '2':
                {
                int LA18_1 = input.LA(2);

                if ( ((LA18_1>='0' && LA18_1<='9')) ) {
                    alt18=3;
                }
                else {
                    alt18=1;}
                }
                break;
            case '0':
                {
                alt18=2;
                }
                break;
            case '3':
                {
                int LA18_3 = input.LA(2);

                if ( ((LA18_3>='0' && LA18_3<='1')) ) {
                    alt18=4;
                }
                else {
                    alt18=1;}
                }
                break;
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                {
                alt18=1;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // grammar/PlazmaScript.g:416:6: ( '1' .. '9' )
                    {
                    // grammar/PlazmaScript.g:416:6: ( '1' .. '9' )
                    // grammar/PlazmaScript.g:416:7: '1' .. '9'
                    {
                    matchRange('1','9'); if (state.failed) return ;

                    }


                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:416:19: ( '0' '1' .. '9' )
                    {
                    // grammar/PlazmaScript.g:416:19: ( '0' '1' .. '9' )
                    // grammar/PlazmaScript.g:416:20: '0' '1' .. '9'
                    {
                    match('0'); if (state.failed) return ;
                    matchRange('1','9'); if (state.failed) return ;

                    }


                    }
                    break;
                case 3 :
                    // grammar/PlazmaScript.g:416:36: ( '1' .. '2' '0' .. '9' )
                    {
                    // grammar/PlazmaScript.g:416:36: ( '1' .. '2' '0' .. '9' )
                    // grammar/PlazmaScript.g:416:37: '1' .. '2' '0' .. '9'
                    {
                    matchRange('1','2'); if (state.failed) return ;
                    matchRange('0','9'); if (state.failed) return ;

                    }


                    }
                    break;
                case 4 :
                    // grammar/PlazmaScript.g:416:58: ( '3' '0' .. '1' )
                    {
                    // grammar/PlazmaScript.g:416:58: ( '3' '0' .. '1' )
                    // grammar/PlazmaScript.g:416:59: '3' '0' .. '1'
                    {
                    match('3'); if (state.failed) return ;
                    matchRange('0','1'); if (state.failed) return ;

                    }


                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "DD"

    public void mTokens() throws RecognitionException {
        // grammar/PlazmaScript.g:1:8: ( T__93 | Println | Print | Assert | Var | Def | If | Else | Return | For | While | In | Null | Break | Continue | XorWord | Or | BitOr | OrWord | And | BitAnd | AndWord | Equals | NEquals | GTEquals | LTEquals | Pow | Not | NotWord | GT | LT | Add | Subtract | Multiply | Divide | Modulus | OBrace | CBrace | OBracket | CBracket | OParen | CParen | SColon | Assign | Comma | QMark | Colon | Range | RangeE | Date | List | Bool | Integer | Number | Identifier | ContextIdentifier | String | Comment | Space )
        int alt19=59;
        alt19 = dfa19.predict(input);
        switch (alt19) {
            case 1 :
                // grammar/PlazmaScript.g:1:10: T__93
                {
                mT__93(); if (state.failed) return ;

                }
                break;
            case 2 :
                // grammar/PlazmaScript.g:1:16: Println
                {
                mPrintln(); if (state.failed) return ;

                }
                break;
            case 3 :
                // grammar/PlazmaScript.g:1:24: Print
                {
                mPrint(); if (state.failed) return ;

                }
                break;
            case 4 :
                // grammar/PlazmaScript.g:1:30: Assert
                {
                mAssert(); if (state.failed) return ;

                }
                break;
            case 5 :
                // grammar/PlazmaScript.g:1:37: Var
                {
                mVar(); if (state.failed) return ;

                }
                break;
            case 6 :
                // grammar/PlazmaScript.g:1:41: Def
                {
                mDef(); if (state.failed) return ;

                }
                break;
            case 7 :
                // grammar/PlazmaScript.g:1:45: If
                {
                mIf(); if (state.failed) return ;

                }
                break;
            case 8 :
                // grammar/PlazmaScript.g:1:48: Else
                {
                mElse(); if (state.failed) return ;

                }
                break;
            case 9 :
                // grammar/PlazmaScript.g:1:53: Return
                {
                mReturn(); if (state.failed) return ;

                }
                break;
            case 10 :
                // grammar/PlazmaScript.g:1:60: For
                {
                mFor(); if (state.failed) return ;

                }
                break;
            case 11 :
                // grammar/PlazmaScript.g:1:64: While
                {
                mWhile(); if (state.failed) return ;

                }
                break;
            case 12 :
                // grammar/PlazmaScript.g:1:70: In
                {
                mIn(); if (state.failed) return ;

                }
                break;
            case 13 :
                // grammar/PlazmaScript.g:1:73: Null
                {
                mNull(); if (state.failed) return ;

                }
                break;
            case 14 :
                // grammar/PlazmaScript.g:1:78: Break
                {
                mBreak(); if (state.failed) return ;

                }
                break;
            case 15 :
                // grammar/PlazmaScript.g:1:84: Continue
                {
                mContinue(); if (state.failed) return ;

                }
                break;
            case 16 :
                // grammar/PlazmaScript.g:1:93: XorWord
                {
                mXorWord(); if (state.failed) return ;

                }
                break;
            case 17 :
                // grammar/PlazmaScript.g:1:101: Or
                {
                mOr(); if (state.failed) return ;

                }
                break;
            case 18 :
                // grammar/PlazmaScript.g:1:104: BitOr
                {
                mBitOr(); if (state.failed) return ;

                }
                break;
            case 19 :
                // grammar/PlazmaScript.g:1:110: OrWord
                {
                mOrWord(); if (state.failed) return ;

                }
                break;
            case 20 :
                // grammar/PlazmaScript.g:1:117: And
                {
                mAnd(); if (state.failed) return ;

                }
                break;
            case 21 :
                // grammar/PlazmaScript.g:1:121: BitAnd
                {
                mBitAnd(); if (state.failed) return ;

                }
                break;
            case 22 :
                // grammar/PlazmaScript.g:1:128: AndWord
                {
                mAndWord(); if (state.failed) return ;

                }
                break;
            case 23 :
                // grammar/PlazmaScript.g:1:136: Equals
                {
                mEquals(); if (state.failed) return ;

                }
                break;
            case 24 :
                // grammar/PlazmaScript.g:1:143: NEquals
                {
                mNEquals(); if (state.failed) return ;

                }
                break;
            case 25 :
                // grammar/PlazmaScript.g:1:151: GTEquals
                {
                mGTEquals(); if (state.failed) return ;

                }
                break;
            case 26 :
                // grammar/PlazmaScript.g:1:160: LTEquals
                {
                mLTEquals(); if (state.failed) return ;

                }
                break;
            case 27 :
                // grammar/PlazmaScript.g:1:169: Pow
                {
                mPow(); if (state.failed) return ;

                }
                break;
            case 28 :
                // grammar/PlazmaScript.g:1:173: Not
                {
                mNot(); if (state.failed) return ;

                }
                break;
            case 29 :
                // grammar/PlazmaScript.g:1:177: NotWord
                {
                mNotWord(); if (state.failed) return ;

                }
                break;
            case 30 :
                // grammar/PlazmaScript.g:1:185: GT
                {
                mGT(); if (state.failed) return ;

                }
                break;
            case 31 :
                // grammar/PlazmaScript.g:1:188: LT
                {
                mLT(); if (state.failed) return ;

                }
                break;
            case 32 :
                // grammar/PlazmaScript.g:1:191: Add
                {
                mAdd(); if (state.failed) return ;

                }
                break;
            case 33 :
                // grammar/PlazmaScript.g:1:195: Subtract
                {
                mSubtract(); if (state.failed) return ;

                }
                break;
            case 34 :
                // grammar/PlazmaScript.g:1:204: Multiply
                {
                mMultiply(); if (state.failed) return ;

                }
                break;
            case 35 :
                // grammar/PlazmaScript.g:1:213: Divide
                {
                mDivide(); if (state.failed) return ;

                }
                break;
            case 36 :
                // grammar/PlazmaScript.g:1:220: Modulus
                {
                mModulus(); if (state.failed) return ;

                }
                break;
            case 37 :
                // grammar/PlazmaScript.g:1:228: OBrace
                {
                mOBrace(); if (state.failed) return ;

                }
                break;
            case 38 :
                // grammar/PlazmaScript.g:1:235: CBrace
                {
                mCBrace(); if (state.failed) return ;

                }
                break;
            case 39 :
                // grammar/PlazmaScript.g:1:242: OBracket
                {
                mOBracket(); if (state.failed) return ;

                }
                break;
            case 40 :
                // grammar/PlazmaScript.g:1:251: CBracket
                {
                mCBracket(); if (state.failed) return ;

                }
                break;
            case 41 :
                // grammar/PlazmaScript.g:1:260: OParen
                {
                mOParen(); if (state.failed) return ;

                }
                break;
            case 42 :
                // grammar/PlazmaScript.g:1:267: CParen
                {
                mCParen(); if (state.failed) return ;

                }
                break;
            case 43 :
                // grammar/PlazmaScript.g:1:274: SColon
                {
                mSColon(); if (state.failed) return ;

                }
                break;
            case 44 :
                // grammar/PlazmaScript.g:1:281: Assign
                {
                mAssign(); if (state.failed) return ;

                }
                break;
            case 45 :
                // grammar/PlazmaScript.g:1:288: Comma
                {
                mComma(); if (state.failed) return ;

                }
                break;
            case 46 :
                // grammar/PlazmaScript.g:1:294: QMark
                {
                mQMark(); if (state.failed) return ;

                }
                break;
            case 47 :
                // grammar/PlazmaScript.g:1:300: Colon
                {
                mColon(); if (state.failed) return ;

                }
                break;
            case 48 :
                // grammar/PlazmaScript.g:1:306: Range
                {
                mRange(); if (state.failed) return ;

                }
                break;
            case 49 :
                // grammar/PlazmaScript.g:1:312: RangeE
                {
                mRangeE(); if (state.failed) return ;

                }
                break;
            case 50 :
                // grammar/PlazmaScript.g:1:319: Date
                {
                mDate(); if (state.failed) return ;

                }
                break;
            case 51 :
                // grammar/PlazmaScript.g:1:324: List
                {
                mList(); if (state.failed) return ;

                }
                break;
            case 52 :
                // grammar/PlazmaScript.g:1:329: Bool
                {
                mBool(); if (state.failed) return ;

                }
                break;
            case 53 :
                // grammar/PlazmaScript.g:1:334: Integer
                {
                mInteger(); if (state.failed) return ;

                }
                break;
            case 54 :
                // grammar/PlazmaScript.g:1:342: Number
                {
                mNumber(); if (state.failed) return ;

                }
                break;
            case 55 :
                // grammar/PlazmaScript.g:1:349: Identifier
                {
                mIdentifier(); if (state.failed) return ;

                }
                break;
            case 56 :
                // grammar/PlazmaScript.g:1:360: ContextIdentifier
                {
                mContextIdentifier(); if (state.failed) return ;

                }
                break;
            case 57 :
                // grammar/PlazmaScript.g:1:378: String
                {
                mString(); if (state.failed) return ;

                }
                break;
            case 58 :
                // grammar/PlazmaScript.g:1:385: Comment
                {
                mComment(); if (state.failed) return ;

                }
                break;
            case 59 :
                // grammar/PlazmaScript.g:1:393: Space
                {
                mSpace(); if (state.failed) return ;

                }
                break;

        }

    }

    // $ANTLR start synpred1_PlazmaScript
    public final void synpred1_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:347:5: ( Int '..' )
        // grammar/PlazmaScript.g:347:6: Int '..'
        {
        mInt(); if (state.failed) return ;
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


    protected DFA7 dfa7 = new DFA7(this);
    protected DFA19 dfa19 = new DFA19(this);
    static final String DFA7_eotS =
        "\2\uffff\1\5\1\uffff\1\5\1\uffff";
    static final String DFA7_eofS =
        "\6\uffff";
    static final String DFA7_minS =
        "\1\44\1\101\1\60\1\uffff\1\60\1\uffff";
    static final String DFA7_maxS =
        "\1\44\2\173\1\uffff\1\173\1\uffff";
    static final String DFA7_acceptS =
        "\3\uffff\1\2\1\uffff\1\1";
    static final String DFA7_specialS =
        "\6\uffff}>";
    static final String[] DFA7_transitionS = {
            "\1\1",
            "\32\2\4\uffff\1\2\1\uffff\32\2\1\3",
            "\12\4\7\uffff\32\4\4\uffff\1\4\1\uffff\32\4\1\3",
            "",
            "\12\4\7\uffff\32\4\4\uffff\1\4\1\uffff\32\4\1\3",
            ""
    };

    static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
    static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
    static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
    static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
    static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
    static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
    static final short[][] DFA7_transition;

    static {
        int numStates = DFA7_transitionS.length;
        DFA7_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
        }
    }

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = DFA7_eot;
            this.eof = DFA7_eof;
            this.min = DFA7_min;
            this.max = DFA7_max;
            this.accept = DFA7_accept;
            this.special = DFA7_special;
            this.transition = DFA7_transition;
        }
        public String getDescription() {
            return "370:1: ContextIdentifier : ( ( '$' Identifier ) | ( '$' ( Identifier )? '{' Identifier '}' ) );";
        }
    }
    static final String DFA19_eotS =
        "\1\uffff\1\60\15\53\1\103\1\53\1\106\1\110\1\112\1\114\1\116\4"+
        "\uffff\1\120\13\uffff\3\53\2\125\4\uffff\1\130\1\uffff\5\53\1\136"+
        "\1\137\12\53\2\uffff\1\152\14\uffff\3\53\1\125\4\uffff\2\53\1\160"+
        "\1\161\1\162\2\uffff\2\53\1\165\3\53\1\171\2\53\1\174\1\uffff\5"+
        "\53\3\uffff\1\u0082\1\53\1\uffff\2\53\1\u0086\1\uffff\2\53\1\uffff"+
        "\1\u0089\1\u008a\1\u008b\1\u008d\1\53\1\uffff\1\53\1\u008b\1\u0090"+
        "\1\uffff\1\u0091\1\53\3\uffff\1\53\1\uffff\1\u0094\1\u0095\2\uffff"+
        "\1\53\1\u0097\2\uffff\1\53\1\uffff\1\u0099\1\uffff";
    static final String DFA19_eofS =
        "\u009a\uffff";
    static final String DFA19_minS =
        "\1\11\1\56\1\162\1\156\1\141\1\145\1\146\1\154\1\145\1\141\1\150"+
        "\1\157\1\162\2\157\1\174\1\162\1\46\4\75\4\uffff\1\52\13\uffff\1"+
        "\141\1\151\1\162\2\56\4\uffff\1\74\1\uffff\1\151\1\163\1\144\1\162"+
        "\1\146\2\60\1\163\1\164\1\162\1\154\1\151\1\154\1\164\1\145\1\156"+
        "\1\162\2\uffff\1\60\14\uffff\1\164\1\163\1\165\1\56\4\uffff\1\156"+
        "\1\145\3\60\2\uffff\1\145\1\165\1\60\1\163\2\154\1\60\1\141\1\164"+
        "\1\60\1\uffff\1\145\1\164\1\145\1\164\1\162\3\uffff\1\60\1\162\1"+
        "\uffff\2\145\1\60\1\uffff\1\153\1\151\1\uffff\4\60\1\164\1\uffff"+
        "\1\156\2\60\1\uffff\1\60\1\156\3\uffff\1\156\1\uffff\2\60\2\uffff"+
        "\1\165\1\60\2\uffff\1\145\1\uffff\1\60\1\uffff";
    static final String DFA19_maxS =
        "\1\175\1\56\1\162\1\163\1\141\1\145\1\156\1\154\1\145\1\157\1\150"+
        "\1\165\1\162\2\157\1\174\1\162\1\46\4\75\4\uffff\1\57\13\uffff\1"+
        "\141\1\151\1\162\1\71\1\56\4\uffff\1\74\1\uffff\1\151\1\163\1\144"+
        "\1\162\1\146\2\172\1\163\1\164\1\162\1\154\1\151\1\154\1\164\1\145"+
        "\1\156\1\162\2\uffff\1\172\14\uffff\1\164\1\163\1\165\1\71\4\uffff"+
        "\1\156\1\145\3\172\2\uffff\1\145\1\165\1\172\1\163\2\154\1\172\1"+
        "\141\1\164\1\172\1\uffff\1\145\1\164\1\145\1\164\1\162\3\uffff\1"+
        "\172\1\162\1\uffff\2\145\1\172\1\uffff\1\153\1\151\1\uffff\4\172"+
        "\1\164\1\uffff\1\156\2\172\1\uffff\1\172\1\156\3\uffff\1\156\1\uffff"+
        "\2\172\2\uffff\1\165\1\172\2\uffff\1\145\1\uffff\1\172\1\uffff";
    static final String DFA19_acceptS =
        "\26\uffff\1\33\1\40\1\41\1\42\1\uffff\1\44\1\45\1\46\1\47\1\50"+
        "\1\51\1\52\1\53\1\55\1\56\1\57\5\uffff\1\67\1\70\1\71\1\73\1\uffff"+
        "\1\1\21\uffff\1\21\1\22\1\uffff\1\24\1\25\1\27\1\54\1\30\1\34\1"+
        "\31\1\36\1\32\1\37\1\72\1\43\4\uffff\1\65\1\66\1\61\1\60\5\uffff"+
        "\1\7\1\14\12\uffff\1\23\5\uffff\1\26\1\5\1\6\2\uffff\1\12\3\uffff"+
        "\1\35\2\uffff\1\20\5\uffff\1\10\3\uffff\1\15\2\uffff\1\62\1\63\1"+
        "\64\1\uffff\1\3\2\uffff\1\13\1\16\2\uffff\1\4\1\11\1\uffff\1\2\1"+
        "\uffff\1\17";
    static final String DFA19_specialS =
        "\u009a\uffff}>";
    static final String[] DFA19_transitionS = {
            "\2\56\1\uffff\2\56\22\uffff\1\56\1\23\1\55\1\uffff\1\54\1\33"+
            "\1\21\1\55\1\40\1\41\1\31\1\27\1\43\1\30\1\1\1\32\1\52\11\51"+
            "\1\45\1\42\1\25\1\22\1\24\1\44\1\uffff\3\53\1\46\7\53\1\47\16"+
            "\53\1\36\1\uffff\1\37\1\26\1\53\1\uffff\1\3\1\14\1\15\1\5\1"+
            "\7\1\11\2\53\1\6\4\53\1\13\1\20\1\2\1\53\1\10\1\53\1\50\1\53"+
            "\1\4\1\12\1\16\2\53\1\34\1\17\1\35",
            "\1\57",
            "\1\61",
            "\1\63\4\uffff\1\62",
            "\1\64",
            "\1\65",
            "\1\66\7\uffff\1\67",
            "\1\70",
            "\1\71",
            "\1\73\15\uffff\1\72",
            "\1\74",
            "\1\76\5\uffff\1\75",
            "\1\77",
            "\1\100",
            "\1\101",
            "\1\102",
            "\1\104",
            "\1\105",
            "\1\107",
            "\1\111",
            "\1\113",
            "\1\115",
            "",
            "",
            "",
            "",
            "\1\117\4\uffff\1\117",
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
            "\1\121",
            "\1\122",
            "\1\123",
            "\1\126\1\uffff\12\124",
            "\1\126",
            "",
            "",
            "",
            "",
            "\1\127",
            "",
            "\1\131",
            "\1\132",
            "\1\133",
            "\1\134",
            "\1\135",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\140",
            "\1\141",
            "\1\142",
            "\1\143",
            "\1\144",
            "\1\145",
            "\1\146",
            "\1\147",
            "\1\150",
            "\1\151",
            "",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
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
            "\1\153",
            "\1\154",
            "\1\155",
            "\1\126\1\uffff\12\124",
            "",
            "",
            "",
            "",
            "\1\156",
            "\1\157",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "",
            "\1\163",
            "\1\164",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\166",
            "\1\167",
            "\1\170",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\172",
            "\1\173",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "\1\175",
            "\1\176",
            "\1\177",
            "\1\u0080",
            "\1\u0081",
            "",
            "",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u0083",
            "",
            "\1\u0084",
            "\1\u0085",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "\1\u0087",
            "\1\u0088",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\13\53\1\u008c\16"+
            "\53",
            "\1\u008e",
            "",
            "\1\u008f",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u0092",
            "",
            "",
            "",
            "\1\u0093",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "",
            "\1\u0096",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "",
            "\1\u0098",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            ""
    };

    static final short[] DFA19_eot = DFA.unpackEncodedString(DFA19_eotS);
    static final short[] DFA19_eof = DFA.unpackEncodedString(DFA19_eofS);
    static final char[] DFA19_min = DFA.unpackEncodedStringToUnsignedChars(DFA19_minS);
    static final char[] DFA19_max = DFA.unpackEncodedStringToUnsignedChars(DFA19_maxS);
    static final short[] DFA19_accept = DFA.unpackEncodedString(DFA19_acceptS);
    static final short[] DFA19_special = DFA.unpackEncodedString(DFA19_specialS);
    static final short[][] DFA19_transition;

    static {
        int numStates = DFA19_transitionS.length;
        DFA19_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA19_transition[i] = DFA.unpackEncodedString(DFA19_transitionS[i]);
        }
    }

    class DFA19 extends DFA {

        public DFA19(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 19;
            this.eot = DFA19_eot;
            this.eof = DFA19_eof;
            this.min = DFA19_min;
            this.max = DFA19_max;
            this.accept = DFA19_accept;
            this.special = DFA19_special;
            this.transition = DFA19_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__93 | Println | Print | Assert | Var | Def | If | Else | Return | For | While | In | Null | Break | Continue | XorWord | Or | BitOr | OrWord | And | BitAnd | AndWord | Equals | NEquals | GTEquals | LTEquals | Pow | Not | NotWord | GT | LT | Add | Subtract | Multiply | Divide | Modulus | OBrace | CBrace | OBracket | CBracket | OParen | CParen | SColon | Assign | Comma | QMark | Colon | Range | RangeE | Date | List | Bool | Integer | Number | Identifier | ContextIdentifier | String | Comment | Space );";
        }
    }
 

}