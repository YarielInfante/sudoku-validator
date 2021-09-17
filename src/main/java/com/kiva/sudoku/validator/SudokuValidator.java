package com.kiva.sudoku.validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SudokuValidator {

    private final List<String> grid;
    private static final int GRID_SIZE = 9;

    public SudokuValidator(List<String> grid) {
        this.grid = grid;
    }

    public boolean isValid() {
        return isStructureValid()
                && validateRows()
                && validateColumns()
                && validateSubGrids();
    }

    private boolean isStructureValid() {
        return grid != null
                && grid.size() == GRID_SIZE
                && grid.stream().noneMatch(row -> row.split(",").length != GRID_SIZE);
    }


    private boolean validateRows() {

        for (String row : grid) {

            Map<Integer, Integer> numbersCount = new HashMap<>();
            String[] columns = row.split(",");

            for (String column : columns) {
                numbersCount.put(
                        Integer.valueOf(column),
                        numbersCount.getOrDefault(Integer.valueOf(column), 0) + 1
                );
            }

            final int max = numbersCount.values().stream().mapToInt(Integer::intValue).max().orElse(0);

            if (max != 1) return false;
        }

        return true;
    }

    private boolean validateColumns() {

        final String[][] gridArray2d = parseTo2dArray();

        for (var columnIndex = 0; columnIndex < GRID_SIZE; columnIndex++) {

            Map<Integer, Integer> numbersCount = new HashMap<>();

            for (var rowIndex = 0; rowIndex < GRID_SIZE; rowIndex++) {

                numbersCount.put(
                        Integer.valueOf(gridArray2d[rowIndex][columnIndex]),
                        numbersCount.getOrDefault(Integer.valueOf(gridArray2d[rowIndex][columnIndex]), 0) + 1
                );
            }
            int max = numbersCount.values().stream().mapToInt(Integer::intValue).max().orElse(0);

            if (max != 1) return false;
        }
        return true;
    }


    private boolean validateSubGrids() {
        String[][] gridArray2d = parseTo2dArray();

        var rowStart = 0;
        var rowEnd = 2;
        var columnStart = 0;
        var columnEnd = 2;

        for (var gridIndex = 0; gridIndex < GRID_SIZE; gridIndex++) {

            Map<Integer, Integer> numbersCount = new HashMap<>();
            for (int rowIndex = rowStart; rowIndex <= rowEnd; rowIndex++) {
                for (int columnIndex = columnStart; columnIndex <= columnEnd; columnIndex++) {
                    numbersCount.put(
                            Integer.valueOf(gridArray2d[rowIndex][columnIndex]),
                            numbersCount.getOrDefault(Integer.valueOf(gridArray2d[rowIndex][columnIndex]), 0) + 1
                    );
                }
            }
            int max = numbersCount.values().stream().mapToInt(Integer::intValue).max().orElse(0);
            if (max != 1) return false;

            rowStart += 3;
            rowEnd += 3;

            if (rowStart == GRID_SIZE) {
                rowStart = 0;
                rowEnd = 2;
                columnStart += 3;
                columnEnd += 3;
            }
        }

        return true;
    }

    private String[][] parseTo2dArray() {
        var array2D = new String[GRID_SIZE][GRID_SIZE];

        String[] gridArray = grid.toArray(new String[0]);

        for (var row = 0; row < gridArray.length; row++) {
            String[] split = gridArray[row].split(",");
            System.arraycopy(split, 0, array2D[row], 0, split.length);
        }
        return array2D;
    }
}
