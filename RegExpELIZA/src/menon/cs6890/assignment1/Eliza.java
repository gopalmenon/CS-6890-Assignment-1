package menon.CS6890.assignment1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Eliza {
	
	//Responses to the user
	private static ResponseContainer[] rule2Response = {new ResponseContainer("Do computers worry you?"), 
		                                                new ResponseContainer("What do you think about machines?"), 
				                                        new ResponseContainer("Why do you mention computers?"), 
				                                        new ResponseContainer("What do you think machines have to do with your problem?")};
	
	private static ResponseContainer[] rule4Response = {new ResponseContainer("Please don't apologize"),
		                                                new ResponseContainer("Apologies are not necessary"),
		                                                new ResponseContainer("What feelings do you have when you apologize")};
	
	private static ResponseContainer[] rule5Response = {new ResponseContainer("Do you often think of %s"),
														new ResponseContainer("Does thinking of %s bring anything else to mind?"),
														new ResponseContainer("What else do you remember"),
														new ResponseContainer("Why do you recall %s right now?"),
														new ResponseContainer("What in the present situation reminds you of %s"), 
														new ResponseContainer("What is the connection between me and %s")};
	
	private static ResponseContainer[] rule6Response = {new ResponseContainer("Did you think I would forget %s"),
														new ResponseContainer("Why do you think I should recall %s now"),
														new ResponseContainer("What about %s"),
														new ResponseContainer("You mentioned %s"),
														new ResponseContainer("%s if %s"),
														new ResponseContainer("Do you really think its likely that %s"),
														new ResponseContainer("Do you wish that %s"),
														new ResponseContainer("What do you think about %s"),
														new ResponseContainer("Really-- if %s")};
	
	private static ResponseContainer[] rule7Response = {new ResponseContainer("Really-- %s"),
														new ResponseContainer("Have you ever fantasized %s while you were awake?"),
														new ResponseContainer("Have you dreamt %s before?")};

	private static ResponseContainer[] rule9Response = {new ResponseContainer("What does this dream suggest to you?"),
                                             			new ResponseContainer("Do you dream often?"),
                                             			new ResponseContainer("What persons appear in your dreams?"),
                                             			new ResponseContainer("Don't you believe that dream has to do with your problem?")};

	private static ResponseContainer[] rule10Response = {new ResponseContainer("Who else in your family %s"),
                                             			 new ResponseContainer("Tell me more about your family")};

	private static ResponseContainer[] rule11Response = {new ResponseContainer("Your father"),
                                             			 new ResponseContainer("Does he influence you strongly?"),
                                             			 new ResponseContainer("What else comes to mind when you think of your father?")};
	
	private static ResponseContainer[] rule37Response = {new ResponseContainer("Very interesting"),
														 new ResponseContainer("I am not sure I understand you fully"),
														 new ResponseContainer("What does that suggest to you?"),
														 new ResponseContainer("Please continue"),
														 new ResponseContainer("Go on"),
														 new ResponseContainer("Do you feel strongly about discussing such things?")};
	
	private static Random randomNumberGenerator = new Random();
	
	private static final String EMPTY_STRING = "";
	private static final String SUBSTITUTION_TEXT_STRING = "%s";
	
	public static void main(String[] args) {
		
		try {
			//Regular expression for rules
			Pattern rule1Pattern = Pattern.compile("[\\s\\w\\p{Punct}]*hello[\\s\\w\\p{Punct}]*", Pattern.CASE_INSENSITIVE);
			Pattern rule2Pattern = Pattern.compile("[\\s\\w\\p{Punct}]*computer[\\s\\w\\p{Punct}]*", Pattern.CASE_INSENSITIVE);
			Pattern rule3Pattern = Pattern.compile("[\\s\\w\\p{Punct}]*name[\\s\\w\\p{Punct}]*", Pattern.CASE_INSENSITIVE);
			Pattern rule4Pattern = Pattern.compile("[\\s\\w\\p{Punct}]*sorry[\\s\\w\\p{Punct}]*", Pattern.CASE_INSENSITIVE);
			Pattern rule5Pattern = Pattern.compile("[\\s\\w\\p{Punct}]*I remember[\\s\\w\\p{Punct}]*", Pattern.CASE_INSENSITIVE);
			Pattern rule5SpecificPattern = Pattern.compile("I remember", Pattern.CASE_INSENSITIVE);
			Pattern rule6Pattern = Pattern.compile("[\\s\\w\\p{Punct}]*do you remember[\\s\\w\\p{Punct}]*", Pattern.CASE_INSENSITIVE);
			Pattern rule6SpecificPattern = Pattern.compile("do you remember", Pattern.CASE_INSENSITIVE);
			Pattern rule7Pattern = Pattern.compile("[\\s\\w\\p{Punct}]*I dreamt[\\s\\w\\p{Punct}]*", Pattern.CASE_INSENSITIVE);
			Pattern rule7SpecificPattern = Pattern.compile("I dreamt", Pattern.CASE_INSENSITIVE);
			Pattern rule8Pattern = Pattern.compile("[\\s\\w\\p{Punct}]*dream about[\\s\\w\\p{Punct}]*", Pattern.CASE_INSENSITIVE);
			Pattern rule8SpecificPattern = Pattern.compile("dream about", Pattern.CASE_INSENSITIVE);
			Pattern rule9Pattern = Pattern.compile("[\\s\\w\\p{Punct}]*dream[\\s\\w\\p{Punct}]*", Pattern.CASE_INSENSITIVE);
			Pattern rule10Pattern = Pattern.compile("[\\s\\w\\p{Punct}]*my mother[\\s\\w\\p{Punct}]*", Pattern.CASE_INSENSITIVE);
			Pattern rule10SpecificPattern = Pattern.compile("my mother", Pattern.CASE_INSENSITIVE);
			Pattern rule11Pattern = Pattern.compile("[\\s\\w\\p{Punct}]*my father[\\s\\w\\p{Punct}]*", Pattern.CASE_INSENSITIVE);
			
			Matcher ruleMatcher = null;
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			//Get user input from console and print out response
			String userQuery = null, substitutionText1 = null, substitutionText2 = null;
			//Process user input
			while (true) {
				userQuery = bufferedReader.readLine();
				//Find out what pattern the user input matches and give response
				if (rule1Pattern.matcher(userQuery).matches()) {
					System.out.println("How do you do. Please state your problem.");
				} else if (rule2Pattern.matcher(userQuery).matches()) {
					System.out.println(getRuleResponeText(rule2Response));
				} else if (rule3Pattern.matcher(userQuery).matches()) {
					System.out.println("I am not interested in names");
				} else if (rule4Pattern.matcher(userQuery).matches()) {
					System.out.println(getRuleResponeText(rule4Response));
				} else if (rule5Pattern.matcher(userQuery).matches()) {
					ruleMatcher = rule5SpecificPattern.matcher(userQuery);
					if (ruleMatcher.find()) {
						System.out.println(getRuleResponeText(rule5Response, EMPTY_STRING, userQuery.substring(ruleMatcher.end()), EMPTY_STRING));
					}
				} else if (rule6Pattern.matcher(userQuery).matches()) {
					substitutionText1 = EMPTY_STRING;
					substitutionText2 = EMPTY_STRING;
					ruleMatcher = rule6SpecificPattern.matcher(userQuery);
					if (ruleMatcher.find()) {
						if (ruleMatcher.start() != 0) {
							substitutionText1 = userQuery.substring(0, ruleMatcher.start());
						}
						if (ruleMatcher.end() != userQuery.length()) {
							substitutionText2 = userQuery.substring(ruleMatcher.end());
						}
						System.out.println(getRuleResponeText(rule6Response, substitutionText1, substitutionText2, EMPTY_STRING));
					}
				} else if (rule7Pattern.matcher(userQuery).matches()) {
					substitutionText1 = EMPTY_STRING;
					substitutionText2 = EMPTY_STRING;
					ruleMatcher = rule7SpecificPattern.matcher(userQuery);
					if (ruleMatcher.find()) {
						if (ruleMatcher.start() != 0) {
							substitutionText1 = userQuery.substring(0, ruleMatcher.start());
						}
						if (ruleMatcher.end() != userQuery.length()) {
							substitutionText2 = userQuery.substring(ruleMatcher.end());
						}
						System.out.println(getRuleResponeText(rule7Response, substitutionText1, substitutionText2, EMPTY_STRING));
					}
				} else if (rule8Pattern.matcher(userQuery).matches()) {
					substitutionText2 = EMPTY_STRING;
					ruleMatcher = rule8SpecificPattern.matcher(userQuery);
					if (ruleMatcher.find()) {
						if (ruleMatcher.end() != userQuery.length()) {
							substitutionText2 = userQuery.substring(ruleMatcher.end());
						}
						System.out.format("How do you feel about %s in reality?", substitutionText2);
					}
				} else if (rule9Pattern.matcher(userQuery).matches()) {
					System.out.println(getRuleResponeText(rule9Response));
				} else if (rule10Pattern.matcher(userQuery).matches()) {
					substitutionText2 = EMPTY_STRING;
					ruleMatcher = rule10SpecificPattern.matcher(userQuery);
					if (ruleMatcher.find()) {
						if (ruleMatcher.end() != userQuery.length()) {
							substitutionText2 = userQuery.substring(ruleMatcher.end());
						}
						System.out.println(getRuleResponeText(rule10Response, substitutionText1, substitutionText2, EMPTY_STRING));
					}
				} else if (rule11Pattern.matcher(userQuery).matches()) {
					System.out.println(getRuleResponeText(rule11Response));
				} else {
					System.out.println(getRuleResponeText(rule37Response));
				}
			}
		} catch (PatternSyntaxException e) {
			System.out.println("PatternSyntaxException thrown " + e);
		} catch (IOException e) {
			System.out.println("IOException thrown " + e);
		} catch (Exception e) {
			System.out.println("Exception thrown " + e);
		}
	}
	
	//Return response text for response without substitution text
	private static String getRuleResponeText(ResponseContainer[] ruleResponses) {
		
		return ruleResponses[randomNumberGenerator.nextInt(ruleResponses.length)].getResponseText();
		
	}
	
	//Return response text for response with substitution text
	private static String getRuleResponeText(ResponseContainer[] ruleResponses, String substitutionText1, String substitutionText2, String substitutionText3) {
		
		ResponseContainer responseContainer = ruleResponses[randomNumberGenerator.nextInt(ruleResponses.length)];
		int numberOfSubstitutionTexts = responseContainer.getNumberOfSubstitutionTexts();
		if (numberOfSubstitutionTexts == 1) {
			return String.format(responseContainer.getResponseText(), substitutionText2.trim());
		} else if (numberOfSubstitutionTexts == 2) {
			return String.format(responseContainer.getResponseText(), substitutionText1.trim(), substitutionText2.trim());
		} else if (numberOfSubstitutionTexts == 3) {
			return String.format(responseContainer.getResponseText(), substitutionText1.trim(), substitutionText2.trim(), substitutionText3.trim());
		} else return responseContainer.getResponseText();
	}
	
	//Internal class for storing response text 
	private static class ResponseContainer {
		
		private String responseText;
		private int numberOfSubstitutionTexts;
		
		public ResponseContainer(String responseText) {
			this.responseText = responseText;
			this.numberOfSubstitutionTexts = 0;
			int startIndex = 0, position = 0;
			position = responseText.indexOf(SUBSTITUTION_TEXT_STRING, startIndex);
			while (position >= 0) {
				++this.numberOfSubstitutionTexts;
				startIndex = position + 1;
				if (startIndex > responseText.length() - 1) {
					break;
				}
				position = responseText.indexOf(SUBSTITUTION_TEXT_STRING, startIndex);
			}
		}
		public String getResponseText() {
			return responseText;
		}
		public int getNumberOfSubstitutionTexts() {
			return numberOfSubstitutionTexts;
		}
		
	}
}
