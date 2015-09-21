package parser;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RegexpTest {

	@Test
	public void test() {
		String s_var_regexp = "[a-zA-Z]+";
		assertTrue("hola".matches(s_var_regexp));
		
		s_var_regexp = "\\w+![A-Z]\\d+";
		assertTrue("hoja1!C10".matches(s_var_regexp));
		
		s_var_regexp = "[0-9]*\\.?[0-9]+";
		assertTrue( "1.0".matches(s_var_regexp) );
		assertTrue( "1".matches(s_var_regexp) );
		assertTrue( "1.123".matches(s_var_regexp) );
		assertTrue( "123.0".matches(s_var_regexp) );
		assertTrue( "123".matches(s_var_regexp) );
		assertTrue( "123.456".matches(s_var_regexp) );
		
	}

}
