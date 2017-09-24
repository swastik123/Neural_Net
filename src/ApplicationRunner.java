import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Srikanth on 2/27/2017.
 */
public class ApplicationRunner {

    private static int hiddenLayerCount;
    private static int hiddenLayerNodeCount;
    private static float learningRate;
    private static DataSet data;
    private static int trainingSetLimit;
    private static int datasetType;

    public static void setTrainingSetLimit(int trainingSetLimit) {
		ApplicationRunner.trainingSetLimit = trainingSetLimit;
	}

	public static void main(String[] args) {


        //TODO Integrate with pre processing and construct neural network

        learningRate = Float.parseFloat(args[1]);
        hiddenLayerCount = Integer.parseInt(args[2]);
        hiddenLayerNodeCount = Integer.parseInt(args[3]);
        datasetType = Integer.parseInt(args[4]);
        String datasetPath = String.valueOf(args[5]);

        try {
            run(datasetPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        trainingSetLimit = (int) (data.dataValues.size() * Integer.parseInt(args[0]) / 100);

       
        
        NeuralNet network = new NeuralNet();
        network.initializeNetwork();
        // calculate the forward propagation of each layer and calculate all parameters
        network.train();
        network.test();
    }

    public static int getHiddenLayerCount() {
        return hiddenLayerCount;
    }

    public static int getHiddenLayerNodeCount() {
        return hiddenLayerNodeCount;
    }

    public static float getLearningRate() {
        return learningRate;
    }


    public static DataSet getData() {
        return data;
    }

    public static int getTrainingSetLimit() {
        return trainingSetLimit;
    }

    public static int getDatasetType() {
        return datasetType;
    }

    private static void run(String trainPath) throws InstantiationException, IllegalAccessException, IOException {

        data = DataSet.parseTrainData(trainPath);
        //   DataSet testData = DataSet.parseTestFile(testPath);

        List<Double> l1 = data.dataValues.get(0).attributes;

        if (l1.size() == 14) {
            data.normalize(0);
            data.normalize(2);
            data.normalize(4);
            data.normalize(10);
            
            data.normalize(12);
        } else if (l1.size() == 4) {
            for (int i = 0; i < 4; i++)
                data.normalize(i);

        } else if (l1.size() == 13) {

            for (int i = 0; i <=2; i++) {
                data.normalize(i);
            }
            for (int i = 4; i <= 12; i++)
                data.normalize(i);
            
           
        }
        DataSet.parseTestData(l1.size());

       
    }
}
