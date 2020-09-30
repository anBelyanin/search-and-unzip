package unzip.classes;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class UnzippingController {

    private ZipInputStream zipInputStream;
    private FileOutputStream fileOutputStream;

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

    public void unzipFileInDirectory(File zipArchive, String outputDirectory) throws IOException {
        zipInputStream = new ZipInputStream(new FileInputStream(zipArchive));
        ZipEntry zipEntry;
        while((zipEntry = zipInputStream.getNextEntry()) != null) {
            fileOutputStream = new FileOutputStream(outputDirectory + "Unzipped_" + zipEntry.getName());
            System.out.println(zipEntry.getName());
            fileOutputStream.write(zipInputStream.readAllBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        }
        zipInputStream.close();

    }
}
