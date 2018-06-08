/*
 * Copyright (c) 2016.
 *
 * This file is part of voronoi-treemaps-tsv-creator.
 *
 * voronoi-treemaps-tsv-creator is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * voronoi-treemaps-tsv-creator is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with voronoi-treemaps-tsv-creator.
 * If not, see http://www.gnu.org/licenses/.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by svenfillinger on 22.01.16.
 */
public class GeneDataParser {

    /**
     * Stores the genes as key and corresponding data as value
     */
    private HashMap<String, Double> geneDataList;

    private HashMap<String, Integer> columnIndices;
    /**
     * Nullary constructor
     */
    public GeneDataParser(){}

    /**
     * The parser call. Expects a tsv file that contains a header with column names
     * and gene names and data as values
     * @param file The tsv file
     * @param geneNameColumn The name of the column containing the gene names
     * @param valueColumn The name of the column containing the values
     * @return A map providing O(1) access to the gene value
     */
    public Map<String, Double> parseGeneData(String file, String geneNameColumn, String valueColumn){

        HashMap<String, Double> data = new HashMap<>();

        Path filePath = FileSystems.getDefault().getPath(file);

        try(BufferedReader br = Files.newBufferedReader(filePath)){

            String currentLine;

            String header = null;

            while((currentLine = br.readLine()) != null){
                header = currentLine;
                break;
            }

            if(header == null || header.isEmpty()){
                throw new GeneralDataParserException("First line empty? Need header for parsing.");
            } else{
                columnIndices = Utils.getIndexFromHeader(header, geneNameColumn, valueColumn);
                if(columnIndices == null){
                    throw new GeneralDataParserException("One or more of the given column names could not be found in the header");
                }
            }

            while((currentLine = br.readLine()) != null){
                String[] content = currentLine.trim().split("\t");
                data.put(content[columnIndices.get(geneNameColumn)],
                        Double.parseDouble(content[columnIndices.get(valueColumn)].trim()));
            }

        } catch (IOException e){
            throw new GeneralDataParserException(String.format("Could not read file %s.", filePath.getFileName()), e);
        }

        return data;
    }



}
