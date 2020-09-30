package search.classes;

import java.io.*;

public class SearchController {

    private String fileDirectory;


    public void searchFileByName(File fileOrDir, String fileName) {
        fileDirectory = "Your file isn`t there";
        if(fileOrDir.isFile() && fileOrDir.getName().contains(fileName)) {
            fileDirectory = fileOrDir.getAbsolutePath();
            System.out.println(fileDirectory);
        }
        else if (fileOrDir.isDirectory()) {
            for (File currentFile : fileOrDir.listFiles()) {
                searchFileByName(currentFile, fileName);
            }
        }
    }

    public void searchFileByContent(File fileOrDir, String content) throws IOException {
        fileDirectory = "Your file isn`t there";
        if (fileOrDir.isFile()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileOrDir)));
            while (reader.ready()) {
                String currentLine = reader.readLine();
                if (currentLine.contains(content)) {
                    fileDirectory = fileOrDir.getAbsolutePath();
                    System.out.println(fileDirectory);
                }
            }
        }
        else if (fileOrDir.isDirectory()) {
            for (File currentFile : fileOrDir.listFiles()) {
                searchFileByContent(currentFile, content);
            }
        }
    }

}
