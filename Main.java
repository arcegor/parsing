import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        Parse parse = new Parse(args[0], args[1]);
        parse.parsing();
    }
}