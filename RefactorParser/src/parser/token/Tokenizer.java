package parser.token;

import java.util.LinkedList;
import java.util.regex.Matcher;

/**
 * Lee un string y lo separa en componentes fundamentales o tokens a partir de
 * expresiones regulares cargadas a la instancia.
 */
public class Tokenizer {
	/**
	 * guarda las asociaciones entre expresiones regulares y los tipos de
	 * tokens.
	 */
	private LinkedList<TokenInfo> tokenInfos;

	/** Guarda los tokens */
	private LinkedList<Token> tokens;

	public Tokenizer() {
		super();
		tokenInfos = new LinkedList<TokenInfo>();
		tokens = new LinkedList<Token>();
	}

	/**
	 * Crea un Tokenizer basico para manejar expresiones matematicas estandar.
	 * 
	 * @return Tokenizer basico para manejar expresiones matematicas estandar.
	 */
	public static Tokenizer createStandardExpressionTokenizer() {
		Tokenizer tokenizer = new Tokenizer();

		tokenizer.add("[+-]", Token.PLUSMINUS);
		tokenizer.add("[*]", Token.MULT);
		tokenizer.add("[/]", Token.DIV);
		tokenizer.add("\\^", Token.RAISED);

		// String funcs = FunctionExpressionNode.getAllFunctions();
		String funcs = "sin|cos|exp|ln|sqrt";
		tokenizer.add("(" + funcs + ")(?!\\w)", Token.FUNCTION);

		tokenizer.add("\\(", Token.OPEN_BRACKET);
		tokenizer.add("\\)", Token.CLOSE_BRACKET);
		tokenizer.add("(?:\\d+\\.?|\\.\\d)\\d*(?:[Ee][-+]?\\d+)?", Token.NUMBER);
		tokenizer.add("[a-zA-Z]\\w*", Token.VARIABLE);

		return tokenizer;
	}

	/**
	 * Agrega una nueva expresion regular y se le asigna un tipo.
	 * 
	 * @param s_regex
	 *            String con expresion regular a detectar.
	 * @param tokenType
	 *            Tipo de token que representa.
	 */
	public void add(String s_regex, int tokenType) {
		tokenInfos.add(new TokenInfo(s_regex, tokenType));
	}

	/**
	 * Tokeniza un string.
	 * 
	 * Acceder al resultado mediante getTokens.
	 * 
	 * @param str
	 *            String a tokenizar
	 */
	public Tokenizer tokenize(String str) {
		String s = str.trim();
		int totalLength = s.length();
		tokens.clear();
		while (!s.equals("")) {
			int remaining = s.length();
			boolean match = false;
			
			for (TokenInfo info : tokenInfos) {
				Matcher m = info.regex.matcher(s);
				if (m.find()) {
					match = true;
					String tok = m.group().trim();

					s = m.replaceFirst("").trim();
					tokens.add(new Token(info.token, tok, totalLength - remaining));
					break;
				}
			}
			if (!match)
				throw new TokenizerException("Unexpected character in input: " + s);
		}

		return this;
	}

	/**
	 * Retorna la lista de tokens.
	 * 
	 * @return lista de tokens.
	 */
	public LinkedList<Token> getTokens() {
		return tokens;
	}
}
