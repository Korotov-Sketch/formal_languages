package domain;

import logic.Analyzer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class AutomatonTest extends Assert {

    @Test
    public void testBoolean() throws IOException {
        String trueValue = Analyzer.analyzeForTesting("true");
        Assert.assertEquals("boolean", trueValue);

        String falseValue = Analyzer.analyzeForTesting("false");
        Assert.assertEquals("boolean", falseValue);

        String wrongValue = Analyzer.analyzeForTesting("falses");
        Assert.assertNotEquals("boolean", wrongValue);
    }

    @Test
    public void testIdentifier() throws IOException {
        String breaksValue = Analyzer.analyzeForTesting("breaks");
        Assert.assertEquals("identifier", breaksValue);

        String breakValue = Analyzer.analyzeForTesting("break");
        Assert.assertNotEquals("identifier", breakValue);
    }

    @Test
    public void testKeyword() throws IOException {
        List<String> keywords = Arrays.asList("if", "else", "switch", "case", "default", "while", "do", "break", "continue", "for", "try", "catch",
                "finally", "throw", "throws", "import", "package", "class", "interface", "extends", "implements", "static",
                "final", "void", "abstract", "new", "return", "this", "super", "synchronized", "volatile", "const", "goto",
                "instanceof", "enum", "transient", "strictfp", "public", "private", "protected");

        keywords.forEach(keyword -> {
            String res = null;
            try {
                res = Analyzer.analyzeForTesting(keyword);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Assert.assertEquals("keyword", res);
        });
    }

    @Test
    public void testNumber() throws IOException {
        String num546 = Analyzer.analyzeForTesting("546");
        Assert.assertEquals("number", num546);

        String numWithExp = Analyzer.analyzeForTesting("1.5e11");
        Assert.assertEquals("number", numWithExp);

        String wrongValue = Analyzer.analyzeForTesting("number");
        Assert.assertNotEquals("number", wrongValue);
    }

    @Test
    public void testOperator() throws IOException {
        List<String> operators = Arrays.asList("=", ">", "&&", "!", "-", "<=");

        operators.forEach(keyword -> {
            String res = null;
            try {
                res = Analyzer.analyzeForTesting(keyword);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Assert.assertEquals("operator", res);
        });
    }

    @Test
    public void testType() throws IOException {
        List<String> types = Arrays.asList("byte", "short", "int", "long", "float", "double", "char", "boolean", "Integer", "String", "Boolean",
                "Long", "Character", "Short", "Double", "Float", "Byte");

        types.forEach(keyword -> {
            String res = null;
            try {
                res = Analyzer.analyzeForTesting(keyword);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Assert.assertEquals("type", res);
        });
    }

    @Test
    public void testSigns() throws IOException {
        List<String> signs = Arrays.asList( "(", ")", "{", "}", "[", "]", ";", ":", "?", ".", ",");

        signs.forEach(keyword -> {
            String res = null;
            try {
                res = Analyzer.analyzeForTesting(keyword);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Assert.assertEquals("sign", res);
        });

    }

    @Test
    public void testWhitespace() throws IOException {
        String ws1 = Analyzer.analyzeForTesting(" ");
        Assert.assertEquals("whitespace", ws1);

        String ws2 = Analyzer.analyzeForTesting("   ");
        Assert.assertEquals("whitespace", ws2);

        String ws3 = Analyzer.analyzeForTesting("\n");
        Assert.assertEquals("whitespace", ws3);
    }


}