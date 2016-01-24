/*
 * Copyright (c) 2016.
 *
 * This file is part of voronoi-treemaps-tsv-creator.
 *
 * voronoi-treemaps-tsv-creator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * voronoi-treemaps-tsv-creator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with voronoi-treemaps-tsv-creator.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

import java.util.HashMap;

/**
 * Created by sven on 1/24/16.
 */
public class Utils {

    public static HashMap<String, Integer> getIndexFromHeader(String header, String... names){

        HashMap<String, Integer> nameIndices = new HashMap<>();

        for(int i = 0; i < names.length; i++){
            nameIndices.put(names[i], -1);
            if(!header.contains(names[i])){
                return null;
            }
        }

        String[] headerContent = header.trim().split("\t");

        for(int i = 0; i< headerContent.length; i++){
            if(nameIndices.get(headerContent[i]) != null){
                nameIndices.replace(headerContent[i], i);
            }
        }
        return nameIndices;
    }

}
