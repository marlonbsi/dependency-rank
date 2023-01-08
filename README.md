# dependency-rank
Master's degree research. Software requirements prioritization method based on dependency relations between features.

The most commonly used software requirements prioritization techniques depend heavily on human effort in their performances, so the proposed method intended to reduce the amount of effort employed by automating part of the task in an attempt to improve agility and reliability to the process. Therefore the method used requirements documentations of a software project as a basis for extracting these relations. A prototype that uses natural language processing tools was developed, its application aimed to recognize candidate classes contained in software requirements specification documents, written as user stories, turning possible to identify existing links between the features.

After this analysis, a suggested ranking, which employs as the main criterion to prioritize the requirements with greater number of dependencies, is generated. The method was tested in two experiments: a real problem already implemented and another hypothetical, which had its investigation aided by professionals. The results of the experiments showed that the candidate classes identification strategy implemented reached, in its best performance, 0.857 as F1 score for classification models. This index helped the prototype to classify up to 70% of the requirements at the same intervals to those obtained by human judgment. The main challenge for future developments is to increase the subjective analysis of the method.

`OpenNLP` `User Stories` `Java`
