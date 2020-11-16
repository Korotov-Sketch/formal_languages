package logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Automaton;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Analyzer {
    private static List<Path> jsonLexers;

    static {
        try {
            jsonLexers = Files.walk(Path.of("./src/main/resources/automatons"))
                    .filter(path -> !Files.isDirectory(path))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map.Entry<String, String> process(String str, int skip) throws IOException {
        String resultLexer = "";
        String resultClass = "";
        int resultRank = Integer.MAX_VALUE;
        ObjectMapper mapper = new ObjectMapper();
        for (Path lexer : jsonLexers) {
            Automaton[] automatons = mapper.readValue(Files.readString(lexer), Automaton[].class);
            for (Automaton automaton : automatons) {
                Map.Entry<Boolean, Integer> processed = automaton.process(str, skip);
                if (processed.getKey()) {
                    String newLexer = str.substring(skip, skip + processed.getValue());
                    if (resultLexer.length() < newLexer.length()) {
                        resultLexer = newLexer;
                        resultRank = automaton.getRank();
                        resultClass = lexer.toFile().getName().split("\\.")[0];
                    } else if (resultLexer.length() == newLexer.length() && resultRank > automaton.getRank()) {
                        resultLexer = newLexer;
                        resultRank = automaton.getRank();
                        resultClass = lexer.toFile().getName().split("\\.")[0];
                    }
                }
            }
        }
        return Map.entry(resultLexer, resultClass);
    }

    public static List<Map.Entry<String, String>> processAll(String str) throws IOException {
        List<Map.Entry<String, String>> result = new ArrayList<>();
        int i = 0;
        while (i < str.length()) {
            Map.Entry<String, String> analyzeResult = process(str, i);
            i += analyzeResult.getKey().length();
            result.add(analyzeResult);
        }
        return result;
    }

    public static String analyzeForTesting(String str) throws IOException {
        return process(str, 0).getValue();
    }
}
