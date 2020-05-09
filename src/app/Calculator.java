package app;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import static constants.Constants.*;

public class Calculator {
	
	private static volatile Calculator instance;

    private Calculator() {}

    //Singleton Locking /Lazy loading with Double checked Locking
    public static Calculator getInstance() {
        if(instance == null) {
           synchronized(Calculator.class) {
               //double checking Singleton instance
               if(instance == null) {
                   instance = new Calculator();
               }
           }
        }
        return instance;
    }
	
	public void calculateMultiplesTwoSeven() {
		IntStream.rangeClosed(1, 100).forEach(
			n -> {									
				if(n % 2 == 0 && n %7 == 0) System.out.println(MSG_TWOSEVEN);
				else if(n % 2 == 0 ) System.out.println(MSG_TWO);
				else if(n % 7 == 0) System.out.println(MSG_SEVEN);
				else System.out.println(n);			
			}
		);
	}
	
	/*
	 * Accepts maximum what a long number can hold otherwise a BigNumber 
	 * can be implemented in the lambda if bigger numbers are evaluated.
	 */
	private Long calculateFactorial(long n) {	
	    return LongStream.rangeClosed( 1, n )
	                     .reduce(1, ( long a, long b ) -> a * b);
	}
	
	public void calculateCombinationsFormula(long m, long r) throws FunctionRestrictionException {
		if(!(r<=m)) throw new FunctionRestrictionException(MSG_RESTRICTION_EXCEPTION); 
		System.out.println( 
				calculateFactorial(m) /
				( calculateFactorial(r) * calculateFactorial( (m-r) ) ) 
		);
	}
	
	/* Groups by occurrence frequency lower to higher and sub-grouped alphabetically special chars first, 
	 * then upper case words alphabetically, then lower case words alphabetically. 
	 * If upper and lower ordering is not OK, then it is possible to apply a lower case to all found words before grouping.
	 */
	public void calculateWordOccurences(String text) {
		List<String> wordList = Arrays.asList(text.split(" "));
		wordList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)))
			.entrySet().stream()
	        .sorted((Map.Entry.<String, Integer>comparingByKey()))
	        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new))
	        .entrySet().stream()
	        .sorted((Map.Entry.<String, Integer>comparingByValue()))
	        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new))
			.forEach((k, v) -> System.out.println(k +" - "+ v));
	}

}
