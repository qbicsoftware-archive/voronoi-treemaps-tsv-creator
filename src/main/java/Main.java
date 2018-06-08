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

import org.apache.commons.cli.*;

import java.util.List;
import java.util.Map;

/**
 * Created by svenfillinger on 22.01.16.
 */
public class Main {

    public static final String PROGRAM_NAME = "voronoi-treemaps-tsv-creator";

    public static final String CLUSTER_NAME = "clusterName";

    public static final String GENE_NAME = "gene";

    public static final String VALUE_NAME = "value";

    public static final String TSV_HEADER = String.format("%s\t%s\t%s", CLUSTER_NAME, GENE_NAME, VALUE_NAME );

    public static void main(String[] args){

        String helpInfo = String.format("%s.jar -t <file> -d <file> [-h]", PROGRAM_NAME);

        Options options = new Options();
        options.addOption("h", "help", false, "show this help page");
        options.addOption("t", "termfile", true, "A tsv-file containing the terms and gene-lists");
        options.addOption("d", "datafile", true, "A tsv-file containing the genes and their values (expression/ratios/p-values)");

        HelpFormatter helpFormatter = new HelpFormatter();

        CommandLineParser parser = new DefaultParser();

        if (args.length == 0){
            System.err.println("You have to provide the proper number of input files!");
            helpFormatter.printHelp(helpInfo, options);
            System.exit(1);
        }

        try{
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption('h')){
                helpFormatter.printHelp(helpInfo, options);
                System.exit(0);
            }

            if(cmd.hasOption("t") && cmd.hasOption("d")){
                // Sample_metainfo.map
                String termFile = cmd.getOptionValue("t");

                // Study_Sample_Analysis_file.map
                String dataFile = cmd.getOptionValue("d");

                // Run it!
                createVoronoiTSV(termFile, dataFile);

            } else {
                helpFormatter.printHelp(helpInfo, options);
                System.exit(1);
            }


        } catch (ParseException e){
            System.err.println("Something went wrong reading your command line arguments!\n");
            helpFormatter.printHelp(helpInfo, options);
            System.exit(1);
        }

    }


    /**
     * Creates a TSV file that can be progressed with the Voronoi-Treemaps tools
     * @param termFile A tsv file containing the clusters with genes
     * @param dataFile A tsv file containing the genes with their values (expression/ratio/p-value)
     */
    public static void createVoronoiTSV(String termFile, String dataFile){

        GeneDataParser geneDataParser = new GeneDataParser();
        MetaDataParser metaDataParser = new MetaDataParser();

        Map<String, Double> geneValues = geneDataParser.parseGeneData(dataFile, Settings.GENE_COLUMN, Settings.VALUE_COLUMN);

        Map<String, List<String>> termContainer = metaDataParser.parseMetaData(termFile, Settings.GENE_LIST_COLUMN, Settings.TERM_COLUMN);

        termContainer.forEach((String term, List<String> geneList) -> {
            geneList.forEach(gene -> {
                if (geneValues.get(gene) != null)
                    System.out.println(String.format("%s\t%s\t%f", term, gene, geneValues.get(gene)));
            });
        });
    }

}
