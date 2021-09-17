package com.kiva.sudoku.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

public class FileUtil {

    private FileUtil() {
    }

    public static boolean isFileArgumentPatternValid(String arg) {
        var pattern = Pattern.compile("--{1,2}[A-Za-z]+=.+");
        if (pattern.matcher(arg).matches()) {
            return true;
        } else {
            throw new IllegalArgumentException("No argument --file found.");
        }
    }

    public static Path getPathFromArgument(String arg) {
        String[] fileArgument = arg.split("=");
        if (!fileArgument[0].equals("--file")) {
            throw new IllegalArgumentException("No argument --file found.");
        }

        if(fileArgument.length != 2){
            throw new IllegalArgumentException("Argument must be --file=/path");
        }
        return Paths.get(fileArgument[1]);
    }

    public static List<String> readCSVFileContent(Path filePath) throws IOException {

        if (!Files.exists(filePath)) {
                throw new IllegalArgumentException("CSV File does not exist");
        }

        return Files.readAllLines(filePath);
    }
}
