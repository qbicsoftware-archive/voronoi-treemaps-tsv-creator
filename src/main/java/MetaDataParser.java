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
import java.util.*;

/**
 * Created by svenfillinger on 22.01.16.
 */
public class MetaDataParser {

    public Map<String, List<String>> parseMetaData(String file, String geneListColumn, String termColumn){

        HashMap<String, List<String>> data = new HashMap<>();

        Path filePath = FileSystems.getDefault().getPath(file);

        try(BufferedReader br = Files.newBufferedReader(filePath)){

            String currentLine;

            String header = null;

            HashMap<String, Integer> columnIndices;

            while((currentLine = br.readLine()) != null){
                header = currentLine;
                break;
            }

            if(header == null || header.isEmpty()){
                throw new GeneralDataParserException("First line empty? Need header for parsing.");
            } else{
                columnIndices = Utils.getIndexFromHeader(header, termColumn, geneListColumn);
                if(columnIndices == null){
                    throw new GeneralDataParserException("One or both of the given column names could not be found in the header");
                }
            }

            while((currentLine = br.readLine()) != null){
                String[] content = currentLine.trim().split("\t");

                ArrayList<String> geneList = new ArrayList<>(Arrays.asList(content[columnIndices.get(geneListColumn)].replaceAll("\\s+", "").split(",")));

                data.put(content[columnIndices.get(termColumn)], geneList);
            }

        } catch (IOException e){
            throw new GeneralDataParserException(String.format("Could not read file %s.", filePath.getFileName()), e);
        }

        return data;
    }


}
