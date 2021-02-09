package main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import logic.Analyzer;
import org.apache.log4j.Logger;


public class ParseService {
    private static final Logger logger = Logger.getLogger(ParseService.class);
    public static void main(String[] args) throws IOException {

        System.out.println("Введите путь до файла\n(Пример: ./src/main/resources/text.txt )");
        Scanner in = new Scanner(System.in);
        String inputFileName = in.nextLine();
        String input = new String(Files.readAllBytes(Paths.get(inputFileName)));
        List<Map.Entry<String, String>> result = Analyzer.processAll(input);
        result.stream().filter(ss -> !ss.getKey().trim().isEmpty()).forEach(ss -> logger.info(ss.getKey() + " --> " + ss.getValue()));
    }
}
