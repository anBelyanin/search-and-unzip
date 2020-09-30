package unzip.classes;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzippingController {

    private ZipInputStream zipInputStream;
    private FileOutputStream fileOutputStream;

    private static void printSuccessMessage() {
        System.out.println("Operation completed");
    }

    private static void printUnsuccessfulMessage() {
        System.out.println("Operation not completed");
    }

    public void unzipAllFilesInDirectory(File inputDirectory, String outputDirectory) throws IOException {
        if(inputDirectory.isDirectory()) {
            File [] filesInDirectory = inputDirectory.listFiles();
            for(File currentLoopFile : filesInDirectory) {
                if(currentLoopFile.getName().contains(".zip")) {
                    unzipFileInDirectory(currentLoopFile, outputDirectory);
                }
                else {
                    continue;
                }
            }
        }
        else {
            System.out.println("Incorrect input directory");
        }
    }

    private void unzipFileInDirectory(File zipArchive, String outputDirectory) throws IOException {
        zipInputStream = new ZipInputStream(new FileInputStream(zipArchive));
        ZipEntry zipEntry;
        while((zipEntry = zipInputStream.getNextEntry()) != null) {
            fileOutputStream = new FileOutputStream(outputDirectory + "Unzipped_" + zipEntry.getName());
            fileOutputStream.write(zipInputStream.readAllBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        }
        zipInputStream.close();
        UnzippingController.printSuccessMessage();
    }
}
