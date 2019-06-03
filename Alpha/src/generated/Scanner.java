// Generated from C:/Users/Sarah/Documents/Alpha-Compiladores/Alpha\Scanner.g4 by ANTLR 4.7.2
package generated;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Scanner extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PyCOMA=1, ASSIGN=2, PIZQ=3, PDER=4, VIR=5, DOSPUN=6, SUM=7, SUB=8, MUL=9, 
		DIV=10, MAYOR=11, MENOR=12, IGUAL=13, AND=14, OR=15, IF=16, WHILE=17, 
		LET=18, THEN=19, ELSE=20, IN=21, DO=22, BEGIN=23, END=24, CONST=25, VAR=26, 
		INT=27, STR=28, BOOL=29, PRINT=30, BOOLEAN=31, ID=32, NUM=33, STRING=34, 
		SPECIAL_STRING=35, WS=36;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"PyCOMA", "ASSIGN", "PIZQ", "PDER", "VIR", "DOSPUN", "SUM", "SUB", "MUL", 
			"DIV", "MAYOR", "MENOR", "IGUAL", "AND", "OR", "IF", "WHILE", "LET", 
			"THEN", "ELSE", "IN", "DO", "BEGIN", "END", "CONST", "VAR", "INT", "STR", 
			"BOOL", "PRINT", "BOOLEAN", "ID", "NUM", "STRING", "SPECIAL_STRING", 
			"LETTER", "DIGIT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "':='", "'('", "')'", "'~'", "':'", "'+'", "'-'", "'*'", 
			"'/'", "'>'", "'<'", "'=='", "'&&'", "'||'", "'if'", "'while'", "'let'", 
			"'then'", "'else'", "'in'", "'do'", "'begin'", "'end'", "'const'", "'var'", 
			"'int'", "'string'", "'boolean'", "'print'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PyCOMA", "ASSIGN", "PIZQ", "PDER", "VIR", "DOSPUN", "SUM", "SUB", 
			"MUL", "DIV", "MAYOR", "MENOR", "IGUAL", "AND", "OR", "IF", "WHILE", 
			"LET", "THEN", "ELSE", "IN", "DO", "BEGIN", "END", "CONST", "VAR", "INT", 
			"STR", "BOOL", "PRINT", "BOOLEAN", "ID", "NUM", "STRING", "SPECIAL_STRING", 
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public Scanner(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Scanner.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2&\u00fa\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\3\2\3\2\3\3\3\3\3\3\3\4\3"+
		"\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3"+
		"\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24"+
		"\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\33"+
		"\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\3 \3 \3 \3 \3 \3 \3 \3 \3 \5 \u00c5\n \3!\3!\3!\7!\u00ca\n!\f!\16!\u00cd"+
		"\13!\3\"\3\"\7\"\u00d1\n\"\f\"\16\"\u00d4\13\"\3#\3#\3#\3#\7#\u00da\n"+
		"#\f#\16#\u00dd\13#\3#\3#\3$\3$\3$\3$\3$\3$\7$\u00e7\n$\f$\16$\u00ea\13"+
		"$\3$\3$\3$\3$\3%\3%\3&\3&\3\'\6\'\u00f5\n\'\r\'\16\'\u00f6\3\'\3\'\2\2"+
		"(\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37"+
		"= ?!A\"C#E$G%I\2K\2M&\3\2\5\3\2$$\4\2C\\c|\5\2\13\f\17\17\"\"\2\u0100"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2M\3\2\2\2\3O\3\2\2\2\5Q\3\2\2\2\7T\3\2\2\2\tV\3\2\2\2\13X\3\2\2\2"+
		"\rZ\3\2\2\2\17\\\3\2\2\2\21^\3\2\2\2\23`\3\2\2\2\25b\3\2\2\2\27d\3\2\2"+
		"\2\31f\3\2\2\2\33h\3\2\2\2\35k\3\2\2\2\37n\3\2\2\2!q\3\2\2\2#t\3\2\2\2"+
		"%z\3\2\2\2\'~\3\2\2\2)\u0083\3\2\2\2+\u0088\3\2\2\2-\u008b\3\2\2\2/\u008e"+
		"\3\2\2\2\61\u0094\3\2\2\2\63\u0098\3\2\2\2\65\u009e\3\2\2\2\67\u00a2\3"+
		"\2\2\29\u00a6\3\2\2\2;\u00ad\3\2\2\2=\u00b5\3\2\2\2?\u00c4\3\2\2\2A\u00c6"+
		"\3\2\2\2C\u00ce\3\2\2\2E\u00d5\3\2\2\2G\u00e0\3\2\2\2I\u00ef\3\2\2\2K"+
		"\u00f1\3\2\2\2M\u00f4\3\2\2\2OP\7=\2\2P\4\3\2\2\2QR\7<\2\2RS\7?\2\2S\6"+
		"\3\2\2\2TU\7*\2\2U\b\3\2\2\2VW\7+\2\2W\n\3\2\2\2XY\7\u0080\2\2Y\f\3\2"+
		"\2\2Z[\7<\2\2[\16\3\2\2\2\\]\7-\2\2]\20\3\2\2\2^_\7/\2\2_\22\3\2\2\2`"+
		"a\7,\2\2a\24\3\2\2\2bc\7\61\2\2c\26\3\2\2\2de\7@\2\2e\30\3\2\2\2fg\7>"+
		"\2\2g\32\3\2\2\2hi\7?\2\2ij\7?\2\2j\34\3\2\2\2kl\7(\2\2lm\7(\2\2m\36\3"+
		"\2\2\2no\7~\2\2op\7~\2\2p \3\2\2\2qr\7k\2\2rs\7h\2\2s\"\3\2\2\2tu\7y\2"+
		"\2uv\7j\2\2vw\7k\2\2wx\7n\2\2xy\7g\2\2y$\3\2\2\2z{\7n\2\2{|\7g\2\2|}\7"+
		"v\2\2}&\3\2\2\2~\177\7v\2\2\177\u0080\7j\2\2\u0080\u0081\7g\2\2\u0081"+
		"\u0082\7p\2\2\u0082(\3\2\2\2\u0083\u0084\7g\2\2\u0084\u0085\7n\2\2\u0085"+
		"\u0086\7u\2\2\u0086\u0087\7g\2\2\u0087*\3\2\2\2\u0088\u0089\7k\2\2\u0089"+
		"\u008a\7p\2\2\u008a,\3\2\2\2\u008b\u008c\7f\2\2\u008c\u008d\7q\2\2\u008d"+
		".\3\2\2\2\u008e\u008f\7d\2\2\u008f\u0090\7g\2\2\u0090\u0091\7i\2\2\u0091"+
		"\u0092\7k\2\2\u0092\u0093\7p\2\2\u0093\60\3\2\2\2\u0094\u0095\7g\2\2\u0095"+
		"\u0096\7p\2\2\u0096\u0097\7f\2\2\u0097\62\3\2\2\2\u0098\u0099\7e\2\2\u0099"+
		"\u009a\7q\2\2\u009a\u009b\7p\2\2\u009b\u009c\7u\2\2\u009c\u009d\7v\2\2"+
		"\u009d\64\3\2\2\2\u009e\u009f\7x\2\2\u009f\u00a0\7c\2\2\u00a0\u00a1\7"+
		"t\2\2\u00a1\66\3\2\2\2\u00a2\u00a3\7k\2\2\u00a3\u00a4\7p\2\2\u00a4\u00a5"+
		"\7v\2\2\u00a58\3\2\2\2\u00a6\u00a7\7u\2\2\u00a7\u00a8\7v\2\2\u00a8\u00a9"+
		"\7t\2\2\u00a9\u00aa\7k\2\2\u00aa\u00ab\7p\2\2\u00ab\u00ac\7i\2\2\u00ac"+
		":\3\2\2\2\u00ad\u00ae\7d\2\2\u00ae\u00af\7q\2\2\u00af\u00b0\7q\2\2\u00b0"+
		"\u00b1\7n\2\2\u00b1\u00b2\7g\2\2\u00b2\u00b3\7c\2\2\u00b3\u00b4\7p\2\2"+
		"\u00b4<\3\2\2\2\u00b5\u00b6\7r\2\2\u00b6\u00b7\7t\2\2\u00b7\u00b8\7k\2"+
		"\2\u00b8\u00b9\7p\2\2\u00b9\u00ba\7v\2\2\u00ba>\3\2\2\2\u00bb\u00bc\7"+
		"v\2\2\u00bc\u00bd\7t\2\2\u00bd\u00be\7w\2\2\u00be\u00c5\7g\2\2\u00bf\u00c0"+
		"\7h\2\2\u00c0\u00c1\7c\2\2\u00c1\u00c2\7n\2\2\u00c2\u00c3\7u\2\2\u00c3"+
		"\u00c5\7g\2\2\u00c4\u00bb\3\2\2\2\u00c4\u00bf\3\2\2\2\u00c5@\3\2\2\2\u00c6"+
		"\u00cb\5I%\2\u00c7\u00ca\5I%\2\u00c8\u00ca\5K&\2\u00c9\u00c7\3\2\2\2\u00c9"+
		"\u00c8\3\2\2\2\u00ca\u00cd\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2"+
		"\2\2\u00ccB\3\2\2\2\u00cd\u00cb\3\2\2\2\u00ce\u00d2\5K&\2\u00cf\u00d1"+
		"\5K&\2\u00d0\u00cf\3\2\2\2\u00d1\u00d4\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d2"+
		"\u00d3\3\2\2\2\u00d3D\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d5\u00db\7$\2\2\u00d6"+
		"\u00d7\7$\2\2\u00d7\u00da\7$\2\2\u00d8\u00da\n\2\2\2\u00d9\u00d6\3\2\2"+
		"\2\u00d9\u00d8\3\2\2\2\u00da\u00dd\3\2\2\2\u00db\u00d9\3\2\2\2\u00db\u00dc"+
		"\3\2\2\2\u00dc\u00de\3\2\2\2\u00dd\u00db\3\2\2\2\u00de\u00df\7$\2\2\u00df"+
		"F\3\2\2\2\u00e0\u00e1\7$\2\2\u00e1\u00e2\7^\2\2\u00e2\u00e8\7$\2\2\u00e3"+
		"\u00e4\7$\2\2\u00e4\u00e7\7$\2\2\u00e5\u00e7\n\2\2\2\u00e6\u00e3\3\2\2"+
		"\2\u00e6\u00e5\3\2\2\2\u00e7\u00ea\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9"+
		"\3\2\2\2\u00e9\u00eb\3\2\2\2\u00ea\u00e8\3\2\2\2\u00eb\u00ec\7^\2\2\u00ec"+
		"\u00ed\7$\2\2\u00ed\u00ee\7$\2\2\u00eeH\3\2\2\2\u00ef\u00f0\t\3\2\2\u00f0"+
		"J\3\2\2\2\u00f1\u00f2\4\62;\2\u00f2L\3\2\2\2\u00f3\u00f5\t\4\2\2\u00f4"+
		"\u00f3\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f6\u00f7\3\2"+
		"\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00f9\b\'\2\2\u00f9N\3\2\2\2\f\2\u00c4"+
		"\u00c9\u00cb\u00d2\u00d9\u00db\u00e6\u00e8\u00f6\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}