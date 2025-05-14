package com.example.TicTacToe.datasource.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;



@Converter
public class IntArrayToStringConverter implements AttributeConverter<int[][], String> {

    private static final String ROW_SEPARATOR = ";";
    private static final String ELEMENT_SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(int[][] attribute) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < attribute.length; i++) {
            for (int j = 0; j < attribute.length; j++) {
                output.append(attribute[i][j]);
                output.append(j != attribute.length - 1 ? ELEMENT_SEPARATOR : ROW_SEPARATOR);
            }
        }
        return output.toString();
    }

    @Override
    public int[][] convertToEntityAttribute(String s) {
        String[] rows = s.split(ROW_SEPARATOR);
        int numRows = rows.length;
        int numCols = (numRows > 0 && !rows[0].isEmpty()) ? rows[0].split(ELEMENT_SEPARATOR).length : 0;
        int[][] array = new int[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            String[] elements = rows[i].split(ELEMENT_SEPARATOR);
            for (int j = 0; j < numCols; j++) {
                array[i][j] = Integer.parseInt(elements[j].trim());
            }
        }
        return array;
    }
}
