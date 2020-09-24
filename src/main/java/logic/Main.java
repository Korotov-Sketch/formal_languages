package logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "C:/Users/Koteuko/IdeaProjects/formal-languages/src/main/resources/alphabet_and_keywords_for_java.json";
        String json = new String(Files.readAllBytes(Paths.get(fileName)));
        ObjectMapper mapper = new ObjectMapper();
        Data data = mapper.readValue(json, Data.class);
        Set<Automaton> keyWordAutomata = new HashSet<>();
        HashMap<String, Priority> parsedWords = new HashMap<>();
        KeyWordAutomaton typesAutomaton = new KeyWordAutomaton(data.getTypes(), Priority.TYPE);
        keyWordAutomata.add(typesAutomaton);
        KeyWordAutomaton keywordsAutomaton = new KeyWordAutomaton(data.getKeywords(), Priority.KEYWORD);
        keyWordAutomata.add(keywordsAutomaton);
        IdentifierAutomaton identifierAutomaton = new IdentifierAutomaton(data.getAlphas(), data.getNumbers(), Priority.IDENTIFIER);
        keyWordAutomata.add(identifierAutomaton);
        SignsAutomaton punctuationSignsAutomaton = new SignsAutomaton(data.getPunctuationSigns(), Priority.SIGN);
        keyWordAutomata.add(punctuationSignsAutomaton);
        NumberAutomaton numberAutomaton = new NumberAutomaton(data.getSignsForNumbers(), data.getNumbers(),  Priority.NUMBER);
        keyWordAutomata.add(numberAutomaton);


        String inputFileName = "C:/Users/Koteuko/IdeaProjects/formal-languages/src/main/resources/input.txt";
        String input = new String(Files.readAllBytes(Paths.get(inputFileName)));
        while (input.length() > 0) {
            if (input.startsWith(" ")) {
                input = input.substring(1);
            } else {
                TreeSet<Triple> currentResults = new TreeSet<>();
                String finalInput = input;
                keyWordAutomata.forEach(keyWordAutomaton -> currentResults.add(keyWordAutomaton.getResult(finalInput)));
                currentResults.removeIf(triple -> !triple.getSecond());
                Triple res = currentResults.first();
                parsedWords.put(input.substring(0, res.getFirst()), res.getThird());
                input = input.substring(res.getFirst());
            }
        }
        parsedWords.forEach((s, priority) -> System.out.println(s + " --> " + priority));
    }
}
