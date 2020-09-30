import search.enums.Actions;
import search.classes.SearchController;
import search.enums.SearchSubactions;
import unzip.classes.UnzippingController;

import java.io.*;


public class Main {

    private static BufferedReader consoleReader;
    private static SearchController searchController;
    private static UnzippingController unzippingController;

    private static String userInputAction;
    private static String userInputSubaction;
    private static Actions userAction;
    private static SearchSubactions searchSubaction;

    private static String userInputPath;
    private static String userInputFileName;
    private static String userInputContent;
    private static String userOutputPath;
    private static File sourceDirectory;


    public static void main(String[] args) {

        consoleReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Chose action. {-search; -unzip}");
            try {
                userInputAction = consoleReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (userInputAction.equals("-search")) {
                userAction = Actions.SEARCH;
                searchController = new SearchController();

                System.out.println("Search by name or content? {-name; -content}");
                try {
                    userInputSubaction = consoleReader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (userInputSubaction.equals("-name")) searchSubaction = SearchSubactions.SEARCHBYNAME;
                else if (userInputSubaction.equals("-content")) searchSubaction = SearchSubactions.SEARCHBYCONTENT;
                else System.out.println("Incorrect subaction");

                System.out.println("Enter the paths to SEARCH operation. \n Where do this?");
                try {
                    userInputPath = consoleReader.readLine();
                    sourceDirectory = new File(userInputPath);

                    if (searchSubaction.equals(SearchSubactions.SEARCHBYNAME)) {
                        System.out.println("Enter the name of source file:");
                        userInputFileName = consoleReader.readLine();
                        searchController.searchFileByName(sourceDirectory, userInputFileName);
                    } else if (searchSubaction.equals(SearchSubactions.SEARCHBYCONTENT)) {
                        System.out.println("Enter content into source file:");
                        userInputContent = consoleReader.readLine();
                        searchController.searchFileByContent(sourceDirectory, userInputContent);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (userInputAction.equals("-unzip")) {
                userAction = Actions.UNZIP;
                unzippingController = new UnzippingController();

                System.out.println("Enter the path to directory with .zip archives");
                try {
                    userInputPath = consoleReader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Where to unzip?");
                try {
                    userOutputPath = consoleReader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    unzippingController.unzipAllFilesInDirectory(new File(userInputPath), userOutputPath);
                } catch (IOException e) {
                    e.getMessage();
                }
            } else {
                System.out.println("Incorrect command");
            }
        }
    }
}
