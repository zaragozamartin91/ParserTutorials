package parser.token;

import java.util.regex.Pattern;

public class TokenInfo {
	public final Pattern regex;
	public final int token;

	public TokenInfo(Pattern regex, int token) {
		super();
		this.regex = regex;
		this.token = token;
	}

	public TokenInfo(String s_regex, int tokenType) {
		this.regex = Pattern.compile("^(" + s_regex + ")");
		this.token = tokenType;
	}
}// TokenInfo
