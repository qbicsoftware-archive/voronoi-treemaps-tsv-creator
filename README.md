# voronoi-treemaps-tsv-creator
Converts a table like derived from DAVID with columns like "Term", "Genes" and a table with the Gene/Protein names plus data column (Expression/Ratio/p-value, etc) into a tsv file that can be processed for Voronoi-Treemap creation


## Input files
The input files must be **tab-separated**, and contain the **column names** in the **first line**!! The number of columns are not important, as long as the necessary columns with the right name is provided (see specification below).


### The annotation file specification
This file contains the annotation and grouping of the genes to a certain term/function. The term entries can have any name. Be aware that the gene list has to be a comma separated list of names/ids. 

This is an **example** of how the core columns should look like:


| term        | genes           | ... |
| ------------- | ------------- | ---- |
| GO:0006281~DNA repair      | B1AU75_MOUSE, Q4FK11_MOUSE, Q91ZH2_MOUSE, PRP19_MOUSE, RIR1_MOUSE, Q3ULD6_MOUSE, Q3UAZ7_MOUSE, Q3TU85_MOUSE | 
| GO:0060537~muscle tissue development  | Q4FK11_MOUSE, Q91ZH2_MOUSE, PRP19_MOUSE, Q3UAZ7_MOUSE, Q3TU85_MOUSE      | 
| GO:0006284~base-excision repair | Q564G1_MOUSE, B1AU75_MOUSE, PRP19_MOUSE, MYH10_MOUSE, Q3UJR8_MOUSE      |   
| ... | ... | ... |


### The gene/protein linker file specefication

This file links the genes to the value that should be visualized in the voronoi treemaps. This value can be p-values, expression values or ratios for example. Keep in mind, which sort of values you have used.

This is an **example** of how the core columns should look like:

| gene        | value           | 
| ------------- |:-------------:| 
| B1AU75_MOUSE      | 3.04 | 
| Q91ZH2_MOUSE      | 20.4      | 
| PRP19_MOUSE | 5.06      | 


## Output file

The output file will be a tsv-file, that can be directly used for creating voronoi-treemaps. The output from the example input files from above will look like this:

| term               | gene        | value     |
| ------------------ | ----------- | --------- |
| GO:0006281~DNA repair | B1AU75_MOUSE | 3.04 |
