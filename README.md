# Neural_Net
This assignment is focussed on building the Neural Network algorithm

convert file  to .csv format file to improve efficiency
Run the .java program using any IDE like Eclipse
Include given jarFiles(commons-lang3-3.5,commons-lang3-3.5-javadoc)
Arguments needed for the application to run successfully
-------------------------------------------------------

arg 0 - determines the percentage of data that will be considered as training set. e.g. 80
arg 1 - determines the learning rate. e.g .01
arg 2 - determines number of hidden layers to be present in the neural net
arg 3 - determines number of nodes in hidden layer.
arg 4 - dataset type
		1 - Housing	
		2 -Iris	
		3 -AdultIncome 
arg 5 - dataset file path

example argument values -- 80 .01 2 2 1 E:\\housing.csv 


Following are the files used as the design demands
--------------------------------------------------

1.	ApplicationRunner.java - This file acts as driver class which has the main method. It initiates the neural network object and initiates the process.
2.	NeuralNet.java - This is the main entity which holds all the layers. It controls all the layers in the network. It also initiates both the forward and backward pass.
3.	Layer.java	-	This entity is part of neural network which has list of nodes(neurons) belonging to the layer.
4.	Edge.java - This class connects between nodes in different layer. It holds the weight that gets updated on each iteration.
5.	Node.java - This entity does all the calculation at the node level for the neural network.
6.	Data.java and Dataset.java - The two classes takes care of parsing the data and building the dataset.
