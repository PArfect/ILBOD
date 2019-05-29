# ILBOD
This project was made by 3 students from TélécomSudParis: Arthur Oulmi, Pierre-Antoine Mouny and Richard Nguyen, as part of an industrial/research and development school project called Cassiopee.

We named this project ILBOD and it is an android app based on TensorflowLite Object Detection examples. The purpose of our app is to position its user indoor through Neural Network Object Detection. We choose to use TensorflowLit example as a base because it would be too time consuming to reprogram all the camera and tracking features that would be needed.

The app was is at the moment made for our school ground floor. (Specific labelmap and neural network as well as a xml map using androidstudio layout) However, it can be adapted to other places. (You can check HowToAdapt.txt)

Our network was retrained from ssd_mobilenet_v2_coco with a set of more than 1000 photographs of our school and our own labelmap because the specific objects required for our detection was not present in coco labelmap. You can find an evaluation of the network in NeuralNetworkEvaluation.txt.

Most of the code is in French, but a UML Class Diagram is available in Diagramme folder. To summarize how the app works, we have made two facade class that are managing respectively the positioning and the map itself. The map is a graph of node having a location as well as four nodes for the four cardinal directions. As such, when an object is detected it is sent to the class managing the positioning which increments an integer for the different location where the object is present. The integer depends of factors like the frequency an object appears in the different locations. The location with the highest integer occurrence will be the location detected. Then the user can choose a destination in the scrolling menu and the path will be shown. The path is calculated using Breadth First Search algorithm.






