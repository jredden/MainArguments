package com.zenred.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Demonstrates a simple arg parse with subsequent actions
 * 
 * @author jredden
 * 
 */

public class ParserExample {

	private static Map<String, ParseAction> argParsingList = new HashMap<String, ParseAction>();

	private static ParseAction theGood = new ParseAction() {
		@Override
		public void parseAction(Object object) {
			System.out.println("The Good!" + " " + object.toString());
		}
	};

	private static ParseAction theBad = new ParseAction() {
		@Override
		public void parseAction(Object object) {
			System.out.println("The Bad!" + " " + object.toString());
		}
	};

	private static ParseAction theUgly = new ParseAction() {
		@Override
		public void parseAction(Object object) {
			System.out.println("The Ugly!" + " " + object.toString());
		}
	};

	static {
		argParsingList.put("-tg", theGood);
		argParsingList.put("--theGood", theGood);
		argParsingList.put("-tb", theBad);
		argParsingList.put("--theBad", theBad);
		argParsingList.put("-tu", theUgly);
		argParsingList.put("--theUgly", theUgly);
	}

	/**
	 * run with something like -tg=software -tb=scripting -tu=source_code
	 * 
	 * No input arguments does nothing.  The defaults are in the app at that point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ArgParser argParser = new ArgParser();
		argParser.setParseList(argParsingList);

		try {
			argParser.parse(args);
		} catch (com.zenred.util.ArgParser.ParseException pee) {
			System.err.println("Parsing Error:"+pee.getMessage());
		}
	}

}
