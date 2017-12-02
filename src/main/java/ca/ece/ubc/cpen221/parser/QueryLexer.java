// Generated from Query.g4 by ANTLR 4.7

 	package ca.ece.ubc.cpen221.parser;
 
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QueryLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, NUM=15, STRING=16, WHITESPACE=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "NUM", "STRING", "WHITESPACE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'||'", "'&&'", "'('", "')'", "'>'", "'>='", "'<'", "'<='", "'='", 
		"'in'", "'category'", "'rating'", "'price'", "'name'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "NUM", "STRING", "WHITESPACE"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public QueryLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Query.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\23v\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3"+
		"\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3"+
		"\17\3\17\3\17\3\20\3\20\3\21\6\21]\n\21\r\21\16\21^\3\21\6\21b\n\21\r"+
		"\21\16\21c\3\21\6\21g\n\21\r\21\16\21h\7\21k\n\21\f\21\16\21n\13\21\3"+
		"\22\6\22q\n\22\r\22\16\22r\3\22\3\22\2\2\23\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23\3\2\6\3\2\63\67"+
		"\4\2C\\c|\4\2\13\13\"\"\5\2\13\f\16\17\"\"\2z\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\3%\3\2\2\2\5(\3\2\2"+
		"\2\7+\3\2\2\2\t-\3\2\2\2\13/\3\2\2\2\r\61\3\2\2\2\17\64\3\2\2\2\21\66"+
		"\3\2\2\2\239\3\2\2\2\25;\3\2\2\2\27>\3\2\2\2\31G\3\2\2\2\33N\3\2\2\2\35"+
		"T\3\2\2\2\37Y\3\2\2\2!\\\3\2\2\2#p\3\2\2\2%&\7~\2\2&\'\7~\2\2\'\4\3\2"+
		"\2\2()\7(\2\2)*\7(\2\2*\6\3\2\2\2+,\7*\2\2,\b\3\2\2\2-.\7+\2\2.\n\3\2"+
		"\2\2/\60\7@\2\2\60\f\3\2\2\2\61\62\7@\2\2\62\63\7?\2\2\63\16\3\2\2\2\64"+
		"\65\7>\2\2\65\20\3\2\2\2\66\67\7>\2\2\678\7?\2\28\22\3\2\2\29:\7?\2\2"+
		":\24\3\2\2\2;<\7k\2\2<=\7p\2\2=\26\3\2\2\2>?\7e\2\2?@\7c\2\2@A\7v\2\2"+
		"AB\7g\2\2BC\7i\2\2CD\7q\2\2DE\7t\2\2EF\7{\2\2F\30\3\2\2\2GH\7t\2\2HI\7"+
		"c\2\2IJ\7v\2\2JK\7k\2\2KL\7p\2\2LM\7i\2\2M\32\3\2\2\2NO\7r\2\2OP\7t\2"+
		"\2PQ\7k\2\2QR\7e\2\2RS\7g\2\2S\34\3\2\2\2TU\7p\2\2UV\7c\2\2VW\7o\2\2W"+
		"X\7g\2\2X\36\3\2\2\2YZ\t\2\2\2Z \3\2\2\2[]\t\3\2\2\\[\3\2\2\2]^\3\2\2"+
		"\2^\\\3\2\2\2^_\3\2\2\2_l\3\2\2\2`b\t\4\2\2a`\3\2\2\2bc\3\2\2\2ca\3\2"+
		"\2\2cd\3\2\2\2df\3\2\2\2eg\t\3\2\2fe\3\2\2\2gh\3\2\2\2hf\3\2\2\2hi\3\2"+
		"\2\2ik\3\2\2\2ja\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2m\"\3\2\2\2nl\3"+
		"\2\2\2oq\t\5\2\2po\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3\2\2\2st\3\2\2\2tu\b"+
		"\22\2\2u$\3\2\2\2\b\2^chlr\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}