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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, NUM=15, STRING=16;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "NUM", "STRING"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'||'", "'&&'", "'('", "')'", "'>'", "'>='", "'<'", "'<='", "'='", 
		"'in'", "'category'", "'rating'", "'price'", "'name'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "NUM", "STRING"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\22m\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3"+
		"\2\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t"+
		"\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3"+
		"\17\3\20\3\20\3\21\6\21[\n\21\r\21\16\21\\\3\21\6\21`\n\21\r\21\16\21"+
		"a\3\21\6\21e\n\21\r\21\16\21f\7\21i\n\21\f\21\16\21l\13\21\2\2\22\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22\3\2\5\3\2\63\67\4\2C\\c|\4\2\13\13\"\"\2p\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\3#\3\2\2\2\5&\3\2\2\2\7)\3\2\2"+
		"\2\t+\3\2\2\2\13-\3\2\2\2\r/\3\2\2\2\17\62\3\2\2\2\21\64\3\2\2\2\23\67"+
		"\3\2\2\2\259\3\2\2\2\27<\3\2\2\2\31E\3\2\2\2\33L\3\2\2\2\35R\3\2\2\2\37"+
		"W\3\2\2\2!Z\3\2\2\2#$\7~\2\2$%\7~\2\2%\4\3\2\2\2&\'\7(\2\2\'(\7(\2\2("+
		"\6\3\2\2\2)*\7*\2\2*\b\3\2\2\2+,\7+\2\2,\n\3\2\2\2-.\7@\2\2.\f\3\2\2\2"+
		"/\60\7@\2\2\60\61\7?\2\2\61\16\3\2\2\2\62\63\7>\2\2\63\20\3\2\2\2\64\65"+
		"\7>\2\2\65\66\7?\2\2\66\22\3\2\2\2\678\7?\2\28\24\3\2\2\29:\7k\2\2:;\7"+
		"p\2\2;\26\3\2\2\2<=\7e\2\2=>\7c\2\2>?\7v\2\2?@\7g\2\2@A\7i\2\2AB\7q\2"+
		"\2BC\7t\2\2CD\7{\2\2D\30\3\2\2\2EF\7t\2\2FG\7c\2\2GH\7v\2\2HI\7k\2\2I"+
		"J\7p\2\2JK\7i\2\2K\32\3\2\2\2LM\7r\2\2MN\7t\2\2NO\7k\2\2OP\7e\2\2PQ\7"+
		"g\2\2Q\34\3\2\2\2RS\7p\2\2ST\7c\2\2TU\7o\2\2UV\7g\2\2V\36\3\2\2\2WX\t"+
		"\2\2\2X \3\2\2\2Y[\t\3\2\2ZY\3\2\2\2[\\\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2"+
		"]j\3\2\2\2^`\t\4\2\2_^\3\2\2\2`a\3\2\2\2a_\3\2\2\2ab\3\2\2\2bd\3\2\2\2"+
		"ce\t\3\2\2dc\3\2\2\2ef\3\2\2\2fd\3\2\2\2fg\3\2\2\2gi\3\2\2\2h_\3\2\2\2"+
		"il\3\2\2\2jh\3\2\2\2jk\3\2\2\2k\"\3\2\2\2lj\3\2\2\2\7\2\\afj\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}