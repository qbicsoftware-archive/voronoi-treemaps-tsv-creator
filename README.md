# voronoi-treemaps-tsv-creator
Converts a table like derived from DAVID with columns like "Term", "Genes" and a table with the Gene/Protein names plus data column (Expression/Ratio/p-value, etc) into a tsv file that can be processed for Voronoi-Treemap creation


## Input files

### The annotation file

| term        | genes           | 
| ------------- | ------------- | 
| GO:0006281~DNA repair      | B1AU75_MOUSE, Q4FK11_MOUSE, Q91ZH2_MOUSE, PRP19_MOUSE, RIR1_MOUSE, Q3ULD6_MOUSE, Q3UAZ7_MOUSE, Q3TU85_MOUSE | 
| GO:0060537~muscle tissue development  | Q4FK11_MOUSE, Q91ZH2_MOUSE, PRP19_MOUSE, Q3UAZ7_MOUSE, Q3TU85_MOUSE      | 
| GO:0006284~base-excision repair | Q564G1_MOUSE, B1AU75_MOUSE, PRP19_MOUSE, MYH10_MOUSE, Q3UJR8_MOUSE      |    


### The gene/protein linker file

| gene        | value           | 
| ------------- |:-------------:| 
| B1AU75_MOUSE      | 3.04 | 
| Q91ZH2_MOUSE      | 20.4      | 
| PRP19_MOUSE | 5.06      | 
