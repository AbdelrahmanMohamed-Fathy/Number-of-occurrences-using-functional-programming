import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        List<String> listOfSentencesToCheck = new ArrayList<>();
        System.out.println("Enter list of string 1 string at a time: (stops after empty string)");
        String in = input.nextLine();
        while(!in.isBlank())
        {
            listOfSentencesToCheck.add(in);
            in = input.nextLine();
        }
        System.out.println("Enter Key:");
        String wordToCheck = input.nextLine();

        Function<String, List<String>> splitSentenceToWords = (sentence) -> Arrays.asList(sentence.split(" "));

        BiFunction<String, List<String>, List<Integer> >calcOccurrences = (key, SentenceList) -> {
            return SentenceList.parallelStream().map(splitSentenceToWords)
                    .map((wordList) -> wordList.parallelStream()
                            .map((word)->word.equals(key)?(1):(0)).reduce((n, m)->n+m))
                    .map((n)-> n.orElse((0))).toList();
        };

        System.out.println(calcOccurrences.apply(wordToCheck,listOfSentencesToCheck));
    }
}
