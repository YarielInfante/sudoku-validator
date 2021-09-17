package com.kiva.sudoku.validator;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Main {

    public static void main(String... args) throws IOException {
        Map<String, String> argsMap = new HashMap<>();

        Pattern p = Pattern.compile("-{1,2}[A-Za-z]+=.+");
// clean with empty rows

        for (String arg : args) {
            if (!p.matcher(arg).matches()) {
                throw new IllegalArgumentException("arguments must follow format --{argument}={value}");
            }
            String[] split = arg.split("=");
            argsMap.put(split[0].trim(), split[1].trim());

            String filePath = argsMap.get("--file");

            List<String> strings = Files.readAllLines(Paths.get(filePath));
//            boolean validateCSVRows = validateCSVRows(strings);
//            boolean validateCSVRows = validateCSVColumns(strings);
            boolean validateCSVRows = validateCSVSubGrid(strings);
            System.out.println(validateCSVRows);
        }


    }

    private static boolean validateCSVRows(List<String> rows) {


        for (String row : rows) {
            Map<Integer, Integer> map = new HashMap<>();
            String[] split = row.split(",");

            for (String s : split) {
                map.put(Integer.valueOf(s), map.getOrDefault(Integer.valueOf(s), 0) + 1);
            }
            int max = map.values().stream().mapToInt(Integer::intValue).max().orElse(0);

            if (max != 1) return false;
        }

        return true;
    }

    private static boolean validateCSVColumns(List<String> columns) {

        String[][] d2 = parseTo2dArray(columns);

        for (int i = 0; i < 9; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i1 = 0; i1 < 9; i1++) {
                map.put(Integer.valueOf(d2[i1][i]), map.getOrDefault(Integer.valueOf(d2[i1][i]), 0) + 1);
            }
            int max = map.values().stream().mapToInt(Integer::intValue).max().orElse(0);

            if (max != 1) return false;
        }
        return true;
    }


    private static boolean validateCSVSubGrid(List<String> columns) {
        String[][] d2 = parseTo2dArray(columns);

        int rowStart = 0;
        int rowEnd = 2;
        int columnStart = 0;
        int columnEnd = 2;


        for (int ip = 0; ip < 9; ip++) {

            Map<Integer, Integer> map = new HashMap<>();
            for (int i1 = rowStart; i1 <= rowEnd; i1++) {
                for (int i2 = columnStart; i2 <= columnEnd; i2++) {
                    map.put(Integer.valueOf(d2[i1][i2]), map.getOrDefault(Integer.valueOf(d2[i1][i2]), 0) + 1);
                }
            }
            int max = map.values().stream().mapToInt(Integer::intValue).max().orElse(0);
            if (max != 1) return false;

            rowStart += 3;
            rowEnd += 3;


            if (rowStart == 9) {
                rowStart = 0;
                rowEnd = 2;
                columnStart += 3;
                columnEnd += 3;
            }
        }


        return true;
    }

    private static String[][] parseTo2dArray(List<String> columns) {
        String[][] d2 = new String[9][9];

        String[] objects = columns.toArray(new String[0]);

        for (int i = 0; i < objects.length; i++) {
            String[] split = objects[i].split(",");
            System.arraycopy(split, 0, d2[i], 0, split.length);
        }
        return d2;
    }
}
