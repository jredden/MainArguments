package com.zenred.util;

import java.util.Map;

public class ArgParser {

	public class ParseException extends Exception {

		/**
		 * 
		 * 
		 */
		private static final long serialVersionUID = 8460524809950893983L;

		public ParseException(String string) {
			super(string);
		}
	}

	private Map<String, ParseAction> argParsingList;

	public ArgParser() {
	}

	/**
	 * Initilizes argv parsing operations
	 * 
	 * argument and value must be seperated by "="
	 * 
	 * @param argParsingList
	 */
	public void setParseList(Map<String, ParseAction> argParsingList) {
		this.argParsingList = argParsingList;
	}

	public void parse(String[] arg) throws ParseException {
		for (String nextArg : arg) {
			String[] argPair = nextArg.split("=");
			ParseAction parseAction = this.argParsingList.get(argPair[0]);
			if (null == parseAction) {

				String arg0 = argPair[0] != null ? argPair[0]
						: "first arg part bad ?";
				String arg1 = "second part doesn't exist";
				if (argPair.length > 1) {
					arg1 = argPair[1] != null ? argPair[1]
							: "second arg part bad ?";
				}
				ParseException pee = new ParseException(arg0 + " and " + arg1
						+ " looks bogus ...");
			
				throw pee;
			} else {
				parseAction.parseAction(argPair[1]);
			}
		}
	}
}
