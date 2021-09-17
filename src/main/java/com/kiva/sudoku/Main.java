package com.kiva.sudoku;

import com.kiva.sudoku.util.FileUtil;
import com.kiva.sudoku.validator.SudokuValidator;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String... args) throws IOException {

        for (String arg : args) {

            FileUtil.isFileArgumentPatternValid(arg);

            var filePath = FileUtil.getPathFromArgument(arg);

            List<String> strings = FileUtil.readCSVFileContent(filePath);

            boolean isSudokuValid = new SudokuValidator(strings).isValid();

            if (isSudokuValid)
                System.out.println("Valid");
            else
                System.out.println("Invalid");
        }
    }

}
