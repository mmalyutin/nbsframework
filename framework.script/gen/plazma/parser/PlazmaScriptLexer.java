// $ANTLR 3.2 Sep 23, 2009 12:02:23 grammar/PlazmaScript.g 2016-03-29 12:50:46

  package plazma.parser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class PlazmaScriptLexer extends Lexer {
    public static final int FUNCTION=18;
    public static final int OParen=67;
    public static final int LT=57;
    public static final int YYYY=79;
    public static final int Assert=31;
    public static final int TERNARY=15;
    public static final int EXP_LIST=12;
    public static final int While=39;
    public static final int Date=33;
    public static final int ID_LIST=13;
    public static final int Add=58;
    public static final int QMark=72;
    public static final int DD=81;
    public static final int EOF=-1;
    public static final int BREAK=23;
    public static final int Identifier=28;
    public static final int Int=74;
    public static final int IF=14;
    public static final int FUNC_CALL=8;
    public static final int Space=78;
    public static final int Size=32;
    public static final int Assign=70;
    public static final int CParen=68;
    public static final int EXP_MAP=11;
    public static final int Number=44;
    public static final int Comment=77;
    public static final int EXP=9;
    public static final int CONTINUE=24;
    public static final int MM=80;
    public static final int GTEquals=52;
    public static final int Print=30;
    public static final int CBrace=64;
    public static final int RETURN=5;
    public static final int String=47;
    public static final int Or=48;
    public static final int Return=25;
    public static final int If=34;
    public static final int And=49;
    public static final int Null=46;
    public static final int ContextIdentifier=76;
    public static final int CBracket=66;
    public static final int Println=29;
    public static final int In=40;
    public static final int Bool=45;
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
    public static final int For=38;
    public static final int Divide=61;
    public static final int Def=37;
    public static final int SColon=69;
    public static final int RangeE=41;
    public static final int LOOKUP=22;
    public static final int Range=42;
    public static final int OBracket=65;
    public static final int Break=26;
    public static final int BLOCK=4;
    public static final int MAP=20;
    public static final int STATEMENTS=6;
    public static final int GT=56;
    public static final int UNARY_MIN=16;
    public static final int ASSIGNMENT=7;
    public static final int Else=35;
    public static final int Equals=50;
    public static final int Comma=71;
    public static final int Integer=43;
    public static final int Var=36;
    public static final int Pow=54;
    public static final int LTEquals=53;

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

    // $ANTLR start "Println"
    public final void mPrintln() throws RecognitionException {
        try {
            int _type = Println;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:249:10: ( 'println' )
            // grammar/PlazmaScript.g:249:12: 'println'
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
            // grammar/PlazmaScript.g:250:10: ( 'print' )
            // grammar/PlazmaScript.g:250:12: 'print'
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
            // grammar/PlazmaScript.g:251:10: ( 'assert' )
            // grammar/PlazmaScript.g:251:12: 'assert'
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

    // $ANTLR start "Size"
    public final void mSize() throws RecognitionException {
        try {
            int _type = Size;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:252:10: ( 'size' )
            // grammar/PlazmaScript.g:252:12: 'size'
            {
            match("size"); if (state.failed) return ;


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Size"

    // $ANTLR start "Var"
    public final void mVar() throws RecognitionException {
        try {
            int _type = Var;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:253:10: ( 'var' )
            // grammar/PlazmaScript.g:253:12: 'var'
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
            // grammar/PlazmaScript.g:254:10: ( 'def' )
            // grammar/PlazmaScript.g:254:12: 'def'
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
            // grammar/PlazmaScript.g:255:10: ( 'if' )
            // grammar/PlazmaScript.g:255:12: 'if'
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
            // grammar/PlazmaScript.g:256:10: ( 'else' )
            // grammar/PlazmaScript.g:256:12: 'else'
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
            // grammar/PlazmaScript.g:257:10: ( 'return' )
            // grammar/PlazmaScript.g:257:12: 'return'
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
            // grammar/PlazmaScript.g:258:10: ( 'for' )
            // grammar/PlazmaScript.g:258:12: 'for'
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
            // grammar/PlazmaScript.g:259:10: ( 'while' )
            // grammar/PlazmaScript.g:259:12: 'while'
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
            // grammar/PlazmaScript.g:260:10: ( 'in' )
            // grammar/PlazmaScript.g:260:12: 'in'
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
            // grammar/PlazmaScript.g:261:10: ( 'null' )
            // grammar/PlazmaScript.g:261:12: 'null'
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
            // grammar/PlazmaScript.g:262:10: ( 'break' )
            // grammar/PlazmaScript.g:262:12: 'break'
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
            // grammar/PlazmaScript.g:263:10: ( 'continue' )
            // grammar/PlazmaScript.g:263:12: 'continue'
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

    // $ANTLR start "Or"
    public final void mOr() throws RecognitionException {
        try {
            int _type = Or;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:265:10: ( '||' )
            // grammar/PlazmaScript.g:265:12: '||'
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

    // $ANTLR start "And"
    public final void mAnd() throws RecognitionException {
        try {
            int _type = And;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:266:10: ( '&&' )
            // grammar/PlazmaScript.g:266:12: '&&'
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

    // $ANTLR start "Equals"
    public final void mEquals() throws RecognitionException {
        try {
            int _type = Equals;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:267:10: ( '==' )
            // grammar/PlazmaScript.g:267:12: '=='
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
            // grammar/PlazmaScript.g:268:10: ( '!=' )
            // grammar/PlazmaScript.g:268:12: '!='
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
            // grammar/PlazmaScript.g:269:10: ( '>=' )
            // grammar/PlazmaScript.g:269:12: '>='
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
            // grammar/PlazmaScript.g:270:10: ( '<=' )
            // grammar/PlazmaScript.g:270:12: '<='
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
            // grammar/PlazmaScript.g:271:10: ( '^' )
            // grammar/PlazmaScript.g:271:12: '^'
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

    // $ANTLR start "Excl"
    public final void mExcl() throws RecognitionException {
        try {
            int _type = Excl;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:272:10: ( '!' )
            // grammar/PlazmaScript.g:272:12: '!'
            {
            match('!'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Excl"

    // $ANTLR start "GT"
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:273:10: ( '>' )
            // grammar/PlazmaScript.g:273:12: '>'
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
            // grammar/PlazmaScript.g:274:10: ( '<' )
            // grammar/PlazmaScript.g:274:12: '<'
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
            // grammar/PlazmaScript.g:275:10: ( '+' )
            // grammar/PlazmaScript.g:275:12: '+'
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
            // grammar/PlazmaScript.g:276:10: ( '-' )
            // grammar/PlazmaScript.g:276:12: '-'
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
            // grammar/PlazmaScript.g:277:10: ( '*' )
            // grammar/PlazmaScript.g:277:12: '*'
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
            // grammar/PlazmaScript.g:278:10: ( '/' )
            // grammar/PlazmaScript.g:278:12: '/'
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
            // grammar/PlazmaScript.g:279:10: ( '%' )
            // grammar/PlazmaScript.g:279:12: '%'
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
            // grammar/PlazmaScript.g:280:10: ( '{' )
            // grammar/PlazmaScript.g:280:12: '{'
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
            // grammar/PlazmaScript.g:281:10: ( '}' )
            // grammar/PlazmaScript.g:281:12: '}'
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
            // grammar/PlazmaScript.g:282:10: ( '[' )
            // grammar/PlazmaScript.g:282:12: '['
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
            // grammar/PlazmaScript.g:283:10: ( ']' )
            // grammar/PlazmaScript.g:283:12: ']'
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
            // grammar/PlazmaScript.g:284:10: ( '(' )
            // grammar/PlazmaScript.g:284:12: '('
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
            // grammar/PlazmaScript.g:285:10: ( ')' )
            // grammar/PlazmaScript.g:285:12: ')'
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
            // grammar/PlazmaScript.g:286:10: ( ';' )
            // grammar/PlazmaScript.g:286:12: ';'
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
            // grammar/PlazmaScript.g:287:10: ( '=' )
            // grammar/PlazmaScript.g:287:12: '='
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
            // grammar/PlazmaScript.g:288:10: ( ',' )
            // grammar/PlazmaScript.g:288:12: ','
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
            // grammar/PlazmaScript.g:289:10: ( '?' )
            // grammar/PlazmaScript.g:289:12: '?'
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
            // grammar/PlazmaScript.g:290:10: ( ':' )
            // grammar/PlazmaScript.g:290:12: ':'
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
            // grammar/PlazmaScript.g:292:10: ( '..' )
            // grammar/PlazmaScript.g:292:12: '..'
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
            // grammar/PlazmaScript.g:293:10: ( '..<' )
            // grammar/PlazmaScript.g:293:12: '..<'
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
            // grammar/PlazmaScript.g:295:10: ( 'Date' )
            // grammar/PlazmaScript.g:295:12: 'Date'
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

    // $ANTLR start "Bool"
    public final void mBool() throws RecognitionException {
        try {
            int _type = Bool;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammar/PlazmaScript.g:298:3: ( 'true' | 'false' )
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
                    // grammar/PlazmaScript.g:298:6: 'true'
                    {
                    match("true"); if (state.failed) return ;


                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:299:6: 'false'
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
            // grammar/PlazmaScript.g:314:3: ( Int )
            // grammar/PlazmaScript.g:314:6: Int
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
            // grammar/PlazmaScript.g:318:3: ( ( Int '..' )=> Integer | Int ( '.' ( Digit )* )? )
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
                    // grammar/PlazmaScript.g:318:5: ( Int '..' )=> Integer
                    {
                    mInteger(); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      _type=Integer;
                    }

                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:319:7: Int ( '.' ( Digit )* )?
                    {
                    mInt(); if (state.failed) return ;
                    // grammar/PlazmaScript.g:319:11: ( '.' ( Digit )* )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0=='.') ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // grammar/PlazmaScript.g:319:12: '.' ( Digit )*
                            {
                            match('.'); if (state.failed) return ;
                            // grammar/PlazmaScript.g:319:16: ( Digit )*
                            loop2:
                            do {
                                int alt2=2;
                                int LA2_0 = input.LA(1);

                                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                                    alt2=1;
                                }


                                switch (alt2) {
                            	case 1 :
                            	    // grammar/PlazmaScript.g:319:16: Digit
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
            // grammar/PlazmaScript.g:333:3: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | Digit )* )
            // grammar/PlazmaScript.g:333:6: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | Digit )*
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

            // grammar/PlazmaScript.g:333:34: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | Digit )*
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
            // grammar/PlazmaScript.g:342:3: ( ( '$' Identifier ) | ( '$' ( Identifier )? '{' Identifier '}' ) )
            int alt7=2;
            alt7 = dfa7.predict(input);
            switch (alt7) {
                case 1 :
                    // grammar/PlazmaScript.g:342:6: ( '$' Identifier )
                    {
                    // grammar/PlazmaScript.g:342:6: ( '$' Identifier )
                    // grammar/PlazmaScript.g:342:7: '$' Identifier
                    {
                    match('$'); if (state.failed) return ;
                    mIdentifier(); if (state.failed) return ;

                    }


                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:342:25: ( '$' ( Identifier )? '{' Identifier '}' )
                    {
                    // grammar/PlazmaScript.g:342:25: ( '$' ( Identifier )? '{' Identifier '}' )
                    // grammar/PlazmaScript.g:342:26: '$' ( Identifier )? '{' Identifier '}'
                    {
                    match('$'); if (state.failed) return ;
                    // grammar/PlazmaScript.g:342:30: ( Identifier )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( ((LA6_0>='A' && LA6_0<='Z')||LA6_0=='_'||(LA6_0>='a' && LA6_0<='z')) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // grammar/PlazmaScript.g:342:30: Identifier
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
            // grammar/PlazmaScript.g:358:3: ( '\"' (~ ( '\"' | '\\\\' ) | '\\\\' ( '\\\\' | '\"' ) )* '\"' | '\\'' (~ ( '\\'' | '\\\\' ) | '\\\\' ( '\\\\' | '\\'' ) )* '\\'' )
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
                    // grammar/PlazmaScript.g:358:6: '\"' (~ ( '\"' | '\\\\' ) | '\\\\' ( '\\\\' | '\"' ) )* '\"'
                    {
                    match('\"'); if (state.failed) return ;
                    // grammar/PlazmaScript.g:358:11: (~ ( '\"' | '\\\\' ) | '\\\\' ( '\\\\' | '\"' ) )*
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
                    	    // grammar/PlazmaScript.g:358:12: ~ ( '\"' | '\\\\' )
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
                    	    // grammar/PlazmaScript.g:358:29: '\\\\' ( '\\\\' | '\"' )
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
                    // grammar/PlazmaScript.g:359:6: '\\'' (~ ( '\\'' | '\\\\' ) | '\\\\' ( '\\\\' | '\\'' ) )* '\\''
                    {
                    match('\''); if (state.failed) return ;
                    // grammar/PlazmaScript.g:359:11: (~ ( '\\'' | '\\\\' ) | '\\\\' ( '\\\\' | '\\'' ) )*
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
                    	    // grammar/PlazmaScript.g:359:12: ~ ( '\\'' | '\\\\' )
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
                    	    // grammar/PlazmaScript.g:359:29: '\\\\' ( '\\\\' | '\\'' )
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
            // grammar/PlazmaScript.g:363:3: ( '//' (~ ( '\\r' | '\\n' ) )* | '/*' ( . )* '*/' )
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
                    // grammar/PlazmaScript.g:363:6: '//' (~ ( '\\r' | '\\n' ) )*
                    {
                    match("//"); if (state.failed) return ;

                    // grammar/PlazmaScript.g:363:11: (~ ( '\\r' | '\\n' ) )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( ((LA11_0>='\u0000' && LA11_0<='\t')||(LA11_0>='\u000B' && LA11_0<='\f')||(LA11_0>='\u000E' && LA11_0<='\uFFFF')) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // grammar/PlazmaScript.g:363:11: ~ ( '\\r' | '\\n' )
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
                    // grammar/PlazmaScript.g:364:6: '/*' ( . )* '*/'
                    {
                    match("/*"); if (state.failed) return ;

                    // grammar/PlazmaScript.g:364:11: ( . )*
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
                    	    // grammar/PlazmaScript.g:364:11: .
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
            // grammar/PlazmaScript.g:368:3: ( ( ' ' | '\\t' | '\\r' | '\\n' | '\\u000C' ) )
            // grammar/PlazmaScript.g:368:6: ( ' ' | '\\t' | '\\r' | '\\n' | '\\u000C' )
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
            // grammar/PlazmaScript.g:372:3: ( '1' .. '9' ( Digit )* | '0' )
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
                    // grammar/PlazmaScript.g:372:6: '1' .. '9' ( Digit )*
                    {
                    matchRange('1','9'); if (state.failed) return ;
                    // grammar/PlazmaScript.g:372:15: ( Digit )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( ((LA14_0>='0' && LA14_0<='9')) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // grammar/PlazmaScript.g:372:15: Digit
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
                    // grammar/PlazmaScript.g:372:24: '0'
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
            // grammar/PlazmaScript.g:375:3: ( '0' .. '9' )
            // grammar/PlazmaScript.g:375:6: '0' .. '9'
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
            // grammar/PlazmaScript.g:380:3: ( '1' .. '9' ( Digit )* )
            // grammar/PlazmaScript.g:380:6: '1' .. '9' ( Digit )*
            {
            matchRange('1','9'); if (state.failed) return ;
            // grammar/PlazmaScript.g:380:15: ( Digit )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>='0' && LA16_0<='9')) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // grammar/PlazmaScript.g:380:15: Digit
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
            // grammar/PlazmaScript.g:383:3: ( ( '1' .. '9' ) | ( '0' '1' .. '9' ) | ( '1' '0' .. '2' ) )
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
                    // grammar/PlazmaScript.g:383:6: ( '1' .. '9' )
                    {
                    // grammar/PlazmaScript.g:383:6: ( '1' .. '9' )
                    // grammar/PlazmaScript.g:383:7: '1' .. '9'
                    {
                    matchRange('1','9'); if (state.failed) return ;

                    }


                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:383:19: ( '0' '1' .. '9' )
                    {
                    // grammar/PlazmaScript.g:383:19: ( '0' '1' .. '9' )
                    // grammar/PlazmaScript.g:383:20: '0' '1' .. '9'
                    {
                    match('0'); if (state.failed) return ;
                    matchRange('1','9'); if (state.failed) return ;

                    }


                    }
                    break;
                case 3 :
                    // grammar/PlazmaScript.g:383:36: ( '1' '0' .. '2' )
                    {
                    // grammar/PlazmaScript.g:383:36: ( '1' '0' .. '2' )
                    // grammar/PlazmaScript.g:383:37: '1' '0' .. '2'
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
            // grammar/PlazmaScript.g:387:3: ( ( '1' .. '9' ) | ( '0' '1' .. '9' ) | ( '1' .. '2' '0' .. '9' ) | ( '3' '0' .. '1' ) )
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
                    // grammar/PlazmaScript.g:387:6: ( '1' .. '9' )
                    {
                    // grammar/PlazmaScript.g:387:6: ( '1' .. '9' )
                    // grammar/PlazmaScript.g:387:7: '1' .. '9'
                    {
                    matchRange('1','9'); if (state.failed) return ;

                    }


                    }
                    break;
                case 2 :
                    // grammar/PlazmaScript.g:387:19: ( '0' '1' .. '9' )
                    {
                    // grammar/PlazmaScript.g:387:19: ( '0' '1' .. '9' )
                    // grammar/PlazmaScript.g:387:20: '0' '1' .. '9'
                    {
                    match('0'); if (state.failed) return ;
                    matchRange('1','9'); if (state.failed) return ;

                    }


                    }
                    break;
                case 3 :
                    // grammar/PlazmaScript.g:387:36: ( '1' .. '2' '0' .. '9' )
                    {
                    // grammar/PlazmaScript.g:387:36: ( '1' .. '2' '0' .. '9' )
                    // grammar/PlazmaScript.g:387:37: '1' .. '2' '0' .. '9'
                    {
                    matchRange('1','2'); if (state.failed) return ;
                    matchRange('0','9'); if (state.failed) return ;

                    }


                    }
                    break;
                case 4 :
                    // grammar/PlazmaScript.g:387:58: ( '3' '0' .. '1' )
                    {
                    // grammar/PlazmaScript.g:387:58: ( '3' '0' .. '1' )
                    // grammar/PlazmaScript.g:387:59: '3' '0' .. '1'
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
        // grammar/PlazmaScript.g:1:8: ( Println | Print | Assert | Size | Var | Def | If | Else | Return | For | While | In | Null | Break | Continue | Or | And | Equals | NEquals | GTEquals | LTEquals | Pow | Excl | GT | LT | Add | Subtract | Multiply | Divide | Modulus | OBrace | CBrace | OBracket | CBracket | OParen | CParen | SColon | Assign | Comma | QMark | Colon | Range | RangeE | Date | Bool | Integer | Number | Identifier | ContextIdentifier | String | Comment | Space )
        int alt19=52;
        alt19 = dfa19.predict(input);
        switch (alt19) {
            case 1 :
                // grammar/PlazmaScript.g:1:10: Println
                {
                mPrintln(); if (state.failed) return ;

                }
                break;
            case 2 :
                // grammar/PlazmaScript.g:1:18: Print
                {
                mPrint(); if (state.failed) return ;

                }
                break;
            case 3 :
                // grammar/PlazmaScript.g:1:24: Assert
                {
                mAssert(); if (state.failed) return ;

                }
                break;
            case 4 :
                // grammar/PlazmaScript.g:1:31: Size
                {
                mSize(); if (state.failed) return ;

                }
                break;
            case 5 :
                // grammar/PlazmaScript.g:1:36: Var
                {
                mVar(); if (state.failed) return ;

                }
                break;
            case 6 :
                // grammar/PlazmaScript.g:1:40: Def
                {
                mDef(); if (state.failed) return ;

                }
                break;
            case 7 :
                // grammar/PlazmaScript.g:1:44: If
                {
                mIf(); if (state.failed) return ;

                }
                break;
            case 8 :
                // grammar/PlazmaScript.g:1:47: Else
                {
                mElse(); if (state.failed) return ;

                }
                break;
            case 9 :
                // grammar/PlazmaScript.g:1:52: Return
                {
                mReturn(); if (state.failed) return ;

                }
                break;
            case 10 :
                // grammar/PlazmaScript.g:1:59: For
                {
                mFor(); if (state.failed) return ;

                }
                break;
            case 11 :
                // grammar/PlazmaScript.g:1:63: While
                {
                mWhile(); if (state.failed) return ;

                }
                break;
            case 12 :
                // grammar/PlazmaScript.g:1:69: In
                {
                mIn(); if (state.failed) return ;

                }
                break;
            case 13 :
                // grammar/PlazmaScript.g:1:72: Null
                {
                mNull(); if (state.failed) return ;

                }
                break;
            case 14 :
                // grammar/PlazmaScript.g:1:77: Break
                {
                mBreak(); if (state.failed) return ;

                }
                break;
            case 15 :
                // grammar/PlazmaScript.g:1:83: Continue
                {
                mContinue(); if (state.failed) return ;

                }
                break;
            case 16 :
                // grammar/PlazmaScript.g:1:92: Or
                {
                mOr(); if (state.failed) return ;

                }
                break;
            case 17 :
                // grammar/PlazmaScript.g:1:95: And
                {
                mAnd(); if (state.failed) return ;

                }
                break;
            case 18 :
                // grammar/PlazmaScript.g:1:99: Equals
                {
                mEquals(); if (state.failed) return ;

                }
                break;
            case 19 :
                // grammar/PlazmaScript.g:1:106: NEquals
                {
                mNEquals(); if (state.failed) return ;

                }
                break;
            case 20 :
                // grammar/PlazmaScript.g:1:114: GTEquals
                {
                mGTEquals(); if (state.failed) return ;

                }
                break;
            case 21 :
                // grammar/PlazmaScript.g:1:123: LTEquals
                {
                mLTEquals(); if (state.failed) return ;

                }
                break;
            case 22 :
                // grammar/PlazmaScript.g:1:132: Pow
                {
                mPow(); if (state.failed) return ;

                }
                break;
            case 23 :
                // grammar/PlazmaScript.g:1:136: Excl
                {
                mExcl(); if (state.failed) return ;

                }
                break;
            case 24 :
                // grammar/PlazmaScript.g:1:141: GT
                {
                mGT(); if (state.failed) return ;

                }
                break;
            case 25 :
                // grammar/PlazmaScript.g:1:144: LT
                {
                mLT(); if (state.failed) return ;

                }
                break;
            case 26 :
                // grammar/PlazmaScript.g:1:147: Add
                {
                mAdd(); if (state.failed) return ;

                }
                break;
            case 27 :
                // grammar/PlazmaScript.g:1:151: Subtract
                {
                mSubtract(); if (state.failed) return ;

                }
                break;
            case 28 :
                // grammar/PlazmaScript.g:1:160: Multiply
                {
                mMultiply(); if (state.failed) return ;

                }
                break;
            case 29 :
                // grammar/PlazmaScript.g:1:169: Divide
                {
                mDivide(); if (state.failed) return ;

                }
                break;
            case 30 :
                // grammar/PlazmaScript.g:1:176: Modulus
                {
                mModulus(); if (state.failed) return ;

                }
                break;
            case 31 :
                // grammar/PlazmaScript.g:1:184: OBrace
                {
                mOBrace(); if (state.failed) return ;

                }
                break;
            case 32 :
                // grammar/PlazmaScript.g:1:191: CBrace
                {
                mCBrace(); if (state.failed) return ;

                }
                break;
            case 33 :
                // grammar/PlazmaScript.g:1:198: OBracket
                {
                mOBracket(); if (state.failed) return ;

                }
                break;
            case 34 :
                // grammar/PlazmaScript.g:1:207: CBracket
                {
                mCBracket(); if (state.failed) return ;

                }
                break;
            case 35 :
                // grammar/PlazmaScript.g:1:216: OParen
                {
                mOParen(); if (state.failed) return ;

                }
                break;
            case 36 :
                // grammar/PlazmaScript.g:1:223: CParen
                {
                mCParen(); if (state.failed) return ;

                }
                break;
            case 37 :
                // grammar/PlazmaScript.g:1:230: SColon
                {
                mSColon(); if (state.failed) return ;

                }
                break;
            case 38 :
                // grammar/PlazmaScript.g:1:237: Assign
                {
                mAssign(); if (state.failed) return ;

                }
                break;
            case 39 :
                // grammar/PlazmaScript.g:1:244: Comma
                {
                mComma(); if (state.failed) return ;

                }
                break;
            case 40 :
                // grammar/PlazmaScript.g:1:250: QMark
                {
                mQMark(); if (state.failed) return ;

                }
                break;
            case 41 :
                // grammar/PlazmaScript.g:1:256: Colon
                {
                mColon(); if (state.failed) return ;

                }
                break;
            case 42 :
                // grammar/PlazmaScript.g:1:262: Range
                {
                mRange(); if (state.failed) return ;

                }
                break;
            case 43 :
                // grammar/PlazmaScript.g:1:268: RangeE
                {
                mRangeE(); if (state.failed) return ;

                }
                break;
            case 44 :
                // grammar/PlazmaScript.g:1:275: Date
                {
                mDate(); if (state.failed) return ;

                }
                break;
            case 45 :
                // grammar/PlazmaScript.g:1:280: Bool
                {
                mBool(); if (state.failed) return ;

                }
                break;
            case 46 :
                // grammar/PlazmaScript.g:1:285: Integer
                {
                mInteger(); if (state.failed) return ;

                }
                break;
            case 47 :
                // grammar/PlazmaScript.g:1:293: Number
                {
                mNumber(); if (state.failed) return ;

                }
                break;
            case 48 :
                // grammar/PlazmaScript.g:1:300: Identifier
                {
                mIdentifier(); if (state.failed) return ;

                }
                break;
            case 49 :
                // grammar/PlazmaScript.g:1:311: ContextIdentifier
                {
                mContextIdentifier(); if (state.failed) return ;

                }
                break;
            case 50 :
                // grammar/PlazmaScript.g:1:329: String
                {
                mString(); if (state.failed) return ;

                }
                break;
            case 51 :
                // grammar/PlazmaScript.g:1:336: Comment
                {
                mComment(); if (state.failed) return ;

                }
                break;
            case 52 :
                // grammar/PlazmaScript.g:1:344: Space
                {
                mSpace(); if (state.failed) return ;

                }
                break;

        }

    }

    // $ANTLR start synpred1_PlazmaScript
    public final void synpred1_PlazmaScript_fragment() throws RecognitionException {   
        // grammar/PlazmaScript.g:318:5: ( Int '..' )
        // grammar/PlazmaScript.g:318:6: Int '..'
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
            return "341:1: ContextIdentifier : ( ( '$' Identifier ) | ( '$' ( Identifier )? '{' Identifier '}' ) );";
        }
    }
    static final String DFA19_eotS =
        "\1\uffff\15\51\2\uffff\1\75\1\77\1\101\1\103\4\uffff\1\105\14\uffff"+
        "\2\51\2\112\4\uffff\5\51\1\121\1\122\10\51\12\uffff\1\134\2\51\1"+
        "\112\2\uffff\3\51\1\142\1\143\2\uffff\2\51\1\146\5\51\2\uffff\4"+
        "\51\1\160\2\uffff\1\161\1\51\1\uffff\2\51\1\165\2\51\1\170\1\171"+
        "\1\173\1\51\2\uffff\1\51\1\171\1\176\1\uffff\1\177\1\51\2\uffff"+
        "\1\51\1\uffff\1\u0082\1\u0083\2\uffff\1\51\1\u0085\2\uffff\1\51"+
        "\1\uffff\1\u0087\1\uffff";
    static final String DFA19_eofS =
        "\u0088\uffff";
    static final String DFA19_minS =
        "\1\11\1\162\1\163\1\151\1\141\1\145\1\146\1\154\1\145\1\141\1\150"+
        "\1\165\1\162\1\157\2\uffff\4\75\4\uffff\1\52\13\uffff\1\56\1\141"+
        "\1\162\2\56\4\uffff\1\151\1\163\1\172\1\162\1\146\2\60\1\163\1\164"+
        "\1\162\1\154\1\151\1\154\1\145\1\156\12\uffff\1\74\1\164\1\165\1"+
        "\56\2\uffff\1\156\2\145\2\60\2\uffff\1\145\1\165\1\60\1\163\2\154"+
        "\1\141\1\164\2\uffff\2\145\1\164\1\162\1\60\2\uffff\1\60\1\162\1"+
        "\uffff\2\145\1\60\1\153\1\151\3\60\1\164\2\uffff\1\156\2\60\1\uffff"+
        "\1\60\1\156\2\uffff\1\156\1\uffff\2\60\2\uffff\1\165\1\60\2\uffff"+
        "\1\145\1\uffff\1\60\1\uffff";
    static final String DFA19_maxS =
        "\1\175\1\162\1\163\1\151\1\141\1\145\1\156\1\154\1\145\1\157\1"+
        "\150\1\165\1\162\1\157\2\uffff\4\75\4\uffff\1\57\13\uffff\1\56\1"+
        "\141\1\162\1\71\1\56\4\uffff\1\151\1\163\1\172\1\162\1\146\2\172"+
        "\1\163\1\164\1\162\1\154\1\151\1\154\1\145\1\156\12\uffff\1\74\1"+
        "\164\1\165\1\71\2\uffff\1\156\2\145\2\172\2\uffff\1\145\1\165\1"+
        "\172\1\163\2\154\1\141\1\164\2\uffff\2\145\1\164\1\162\1\172\2\uffff"+
        "\1\172\1\162\1\uffff\2\145\1\172\1\153\1\151\3\172\1\164\2\uffff"+
        "\1\156\2\172\1\uffff\1\172\1\156\2\uffff\1\156\1\uffff\2\172\2\uffff"+
        "\1\165\1\172\2\uffff\1\145\1\uffff\1\172\1\uffff";
    static final String DFA19_acceptS =
        "\16\uffff\1\20\1\21\4\uffff\1\26\1\32\1\33\1\34\1\uffff\1\36\1"+
        "\37\1\40\1\41\1\42\1\43\1\44\1\45\1\47\1\50\1\51\5\uffff\1\60\1"+
        "\61\1\62\1\64\17\uffff\1\22\1\46\1\23\1\27\1\24\1\30\1\25\1\31\1"+
        "\63\1\35\4\uffff\1\56\1\57\5\uffff\1\7\1\14\10\uffff\1\53\1\52\5"+
        "\uffff\1\5\1\6\2\uffff\1\12\11\uffff\1\4\1\10\3\uffff\1\15\2\uffff"+
        "\1\54\1\55\1\uffff\1\2\2\uffff\1\13\1\16\2\uffff\1\3\1\11\1\uffff"+
        "\1\1\1\uffff\1\17";
    static final String DFA19_specialS =
        "\u0088\uffff}>";
    static final String[] DFA19_transitionS = {
            "\2\54\1\uffff\2\54\22\uffff\1\54\1\21\1\53\1\uffff\1\52\1\31"+
            "\1\17\1\53\1\36\1\37\1\27\1\25\1\41\1\26\1\44\1\30\1\50\11\47"+
            "\1\43\1\40\1\23\1\20\1\22\1\42\1\uffff\3\51\1\45\26\51\1\34"+
            "\1\uffff\1\35\1\24\1\51\1\uffff\1\2\1\14\1\15\1\5\1\7\1\11\2"+
            "\51\1\6\4\51\1\13\1\51\1\1\1\51\1\10\1\3\1\46\1\51\1\4\1\12"+
            "\3\51\1\32\1\16\1\33",
            "\1\55",
            "\1\56",
            "\1\57",
            "\1\60",
            "\1\61",
            "\1\62\7\uffff\1\63",
            "\1\64",
            "\1\65",
            "\1\67\15\uffff\1\66",
            "\1\70",
            "\1\71",
            "\1\72",
            "\1\73",
            "",
            "",
            "\1\74",
            "\1\76",
            "\1\100",
            "\1\102",
            "",
            "",
            "",
            "",
            "\1\104\4\uffff\1\104",
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
            "\1\106",
            "\1\107",
            "\1\110",
            "\1\113\1\uffff\12\111",
            "\1\113",
            "",
            "",
            "",
            "",
            "\1\114",
            "\1\115",
            "\1\116",
            "\1\117",
            "\1\120",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\123",
            "\1\124",
            "\1\125",
            "\1\126",
            "\1\127",
            "\1\130",
            "\1\131",
            "\1\132",
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
            "\1\133",
            "\1\135",
            "\1\136",
            "\1\113\1\uffff\12\111",
            "",
            "",
            "\1\137",
            "\1\140",
            "\1\141",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "",
            "",
            "\1\144",
            "\1\145",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\147",
            "\1\150",
            "\1\151",
            "\1\152",
            "\1\153",
            "",
            "",
            "\1\154",
            "\1\155",
            "\1\156",
            "\1\157",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "",
            "",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\162",
            "",
            "\1\163",
            "\1\164",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\166",
            "\1\167",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\13\51\1\172\16\51",
            "\1\174",
            "",
            "",
            "\1\175",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\1\u0080",
            "",
            "",
            "\1\u0081",
            "",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "",
            "",
            "\1\u0084",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
            "",
            "",
            "\1\u0086",
            "",
            "\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32\51",
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
            return "1:1: Tokens : ( Println | Print | Assert | Size | Var | Def | If | Else | Return | For | While | In | Null | Break | Continue | Or | And | Equals | NEquals | GTEquals | LTEquals | Pow | Excl | GT | LT | Add | Subtract | Multiply | Divide | Modulus | OBrace | CBrace | OBracket | CBracket | OParen | CParen | SColon | Assign | Comma | QMark | Colon | Range | RangeE | Date | Bool | Integer | Number | Identifier | ContextIdentifier | String | Comment | Space );";
        }
    }
 

}