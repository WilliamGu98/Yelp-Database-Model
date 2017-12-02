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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, NUM=6, STRING=7, COMP=8, WHITESPACE=9, 
		LPAREN=10, RPAREN=11, OR=12, AND=13;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "NUM", "STRING", "COMP", "WHITESPACE", 
		"LPAREN", "RPAREN", "OR", "AND"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'in'", "'category'", "'rating'", "'price'", "'name'", null, null, 
		null, null, "'('", "')'", "'||'", "'&&'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, "NUM", "STRING", "COMP", "WHITESPACE", 
		"LPAREN", "RPAREN", "OR", "AND"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\17k\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\7\3\7\3\b\6\b?\n\b\r\b\16\b@\3\b\6\bD\n\b\r\b\16\bE\3\b\6"+
		"\bI\n\b\r\b\16\bJ\7\bM\n\b\f\b\16\bP\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\5\tY\n\t\3\n\6\n\\\n\n\r\n\16\n]\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3"+
		"\r\3\16\3\16\3\16\2\2\17\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f"+
		"\27\r\31\16\33\17\3\2\6\3\2\63\67\4\2C\\c|\4\2\13\13\"\"\5\2\13\f\16\17"+
		"\"\"\2s\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\3\35\3\2\2\2\5 \3\2\2\2\7)\3\2\2\2\t"+
		"\60\3\2\2\2\13\66\3\2\2\2\r;\3\2\2\2\17>\3\2\2\2\21X\3\2\2\2\23[\3\2\2"+
		"\2\25a\3\2\2\2\27c\3\2\2\2\31e\3\2\2\2\33h\3\2\2\2\35\36\7k\2\2\36\37"+
		"\7p\2\2\37\4\3\2\2\2 !\7e\2\2!\"\7c\2\2\"#\7v\2\2#$\7g\2\2$%\7i\2\2%&"+
		"\7q\2\2&\'\7t\2\2\'(\7{\2\2(\6\3\2\2\2)*\7t\2\2*+\7c\2\2+,\7v\2\2,-\7"+
		"k\2\2-.\7p\2\2./\7i\2\2/\b\3\2\2\2\60\61\7r\2\2\61\62\7t\2\2\62\63\7k"+
		"\2\2\63\64\7e\2\2\64\65\7g\2\2\65\n\3\2\2\2\66\67\7p\2\2\678\7c\2\289"+
		"\7o\2\29:\7g\2\2:\f\3\2\2\2;<\t\2\2\2<\16\3\2\2\2=?\t\3\2\2>=\3\2\2\2"+
		"?@\3\2\2\2@>\3\2\2\2@A\3\2\2\2AN\3\2\2\2BD\t\4\2\2CB\3\2\2\2DE\3\2\2\2"+
		"EC\3\2\2\2EF\3\2\2\2FH\3\2\2\2GI\t\3\2\2HG\3\2\2\2IJ\3\2\2\2JH\3\2\2\2"+
		"JK\3\2\2\2KM\3\2\2\2LC\3\2\2\2MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2O\20\3\2\2"+
		"\2PN\3\2\2\2QY\7@\2\2RS\7@\2\2SY\7?\2\2TY\7>\2\2UV\7>\2\2VY\7?\2\2WY\7"+
		"?\2\2XQ\3\2\2\2XR\3\2\2\2XT\3\2\2\2XU\3\2\2\2XW\3\2\2\2Y\22\3\2\2\2Z\\"+
		"\t\5\2\2[Z\3\2\2\2\\]\3\2\2\2][\3\2\2\2]^\3\2\2\2^_\3\2\2\2_`\b\n\2\2"+
		"`\24\3\2\2\2ab\7*\2\2b\26\3\2\2\2cd\7+\2\2d\30\3\2\2\2ef\7~\2\2fg\7~\2"+
		"\2g\32\3\2\2\2hi\7(\2\2ij\7(\2\2j\34\3\2\2\2\t\2@EJNX]\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}