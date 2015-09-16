package parser.token;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ParserException;


public class Tokenizer {
	private List<TokenInfo> tokenInfos = new LinkedList<TokenInfo>();
	/**
	 * Contiene informacion sobre los tokens encontrados.
	 */
	private LinkedList<Token> tokens = new LinkedList<Token>();

	public void add(String regex, int token) {
		tokenInfos.add(new TokenInfo(Pattern.compile("^(" + regex + ")"), token));
	}


	public void tokenize(String str) {
		String s = str.trim();
		// se limpia la lista de tokens encontrados
		tokens.clear();

		while (!s.equals("")) {
			boolean match = false;

			for (TokenInfo tokenInfo : tokenInfos) {
				Matcher matcher = tokenInfo.regex.matcher(s);

				if (matcher.find()) {
					match = true;

					String s_token = matcher.group().trim();
					tokens.add(new Token(tokenInfo.token, s_token));

					s = matcher.replaceFirst("").trim();
					break;
				}
			}

			if (!match) {
				throw new ParserException("unexpected char in " + s);
			}
		}
	}// tokenize

	public LinkedList<Token> getTokens() {
		return tokens;
	}
}// Tokenizer
