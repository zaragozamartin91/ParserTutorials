package parser.token;

import com.google.gson.Gson;

/**
 * Representa un elemento a parsear.
 * 
 * @author martin.zaragoza
 *
 */
public class Token {
	/** token no reconocido */
	public static final int EPSILON = 0;
	/** mas menos */
	public static final int PLUSMINUS = 1;
	
	public static final int MULT = 2;	
	public static final int DIV = 9;
	
	/** exponencial */
	public static final int RAISED = 3;
	/** nombres de funciones: sin ln sqrt, etc... */
	public static final int FUNCTION = 4;
	/** ( */
	public static final int OPEN_BRACKET = 5;
	/** ) */
	public static final int CLOSE_BRACKET = 6;
	/** representa numeros */
	public static final int NUMBER = 7;
	/** nombres de variables como C17 , var_21, etc... */
	public static final int VARIABLE = 8;

	/** id de token */
	public final int tokenType;
	/** secuencia a partir se obtuvo el token */
	public final String sequence;
	/** posicion que ocupaba la secuencia en el String original */
	public final int pos;

	public Token(int type, String sequence, int pos) {
		super();
		this.tokenType = type;
		this.sequence = sequence;
		this.pos = pos;
	}

	public Token(int token, String sequence) {
		super();
		this.tokenType = token;
		this.sequence = sequence;
		this.pos = -1;
	}

	public String toString() {
		return new Gson().toJson(this);
	}
}