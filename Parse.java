import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class Parse {
    private final ArrayList<File> files = new ArrayList<>();
    private final String path;
    private final String out;
    public Parse(String path, String out) {
        this.path=path;
        this.out = out;
    }

    public void parsing() throws IOException {
        File file = new File(path);
        getFiles(file);
        mergeFiles();
    }

    private void getFiles(File folder) {
        for (File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isDirectory()) {
                getFiles(fileEntry);
            } else {
                files.add(fileEntry);
            }
        }
    }

    private void mergeFiles() throws IOException {
        files.sort(Comparator.comparing(File::getName));
        BufferedWriter bw = new BufferedWriter(new FileWriter(out));
        for (File file : files) {
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    bw.write(line);
                    bw.newLine();
                    line = br.readLine();
                }
                br.close();
            }
        }
        bw.close();
    }
}
