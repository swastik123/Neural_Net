import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Node {
    private double outputX;
    private double gradient;
    private Layer layer;
    private boolean visited;
    private double targetValue;
    private List<Edge> adjList;
    private List<Edge> revAdjList;
    private double bias;

    public Node(Layer layer) {
        this.layer = layer;
        adjList = new ArrayList<>();
        revAdjList = new ArrayList<>();
        // randomly generate weight of bias connection
        if (this.layer.getLayerType() != LayerType.INPUT) {
            Random rand = new Random();
            bias = rand.nextFloat() * 2 - 1;
        }

    }

    public void performForwardPassCalculation() {
        float sum = 0;
        for (Edge prevLayerEdge : revAdjList) {
            Node prevNode = prevLayerEdge.getDestination();
            double outputValue = prevNode.getOutputX();
            Double weight = prevLayerEdge.getWeight();
            sum += outputValue * weight;
        }
        // add bias value
        sum += bias;
        outputX = Utility.getSigmoidValue(sum);
    }

    public void calculateOutputGradientValue() {
        gradient = (targetValue - outputX) * sigmoidDerivative();
    }

    public void calculateGradientValue() {
        double sum = 0.0;

        for (Edge edge : adjList)
            // Sum all the gradient*connection
            sum += edge.getDestination().getGradient() * edge.getWeight();

        // calculate gradient of this node
        gradient = sum * sigmoidDerivative();
    }

    public void updateEdgesWeight() {
        for (int i = 0;i < adjList.size();i++) {
            Double newWeight = adjList.get(i).getWeight() + (ApplicationRunner.getLearningRate() * outputX *
                    adjList.get(i).getDestination().getGradient());
            adjList.get(i).setWeight(newWeight);
            adjList.get(i).getDestination().revAdjList.get(i).setWeight(newWeight);
            System.out.print("      " + newWeight);
        }
        System.out.println();
    }

    private double sigmoidDerivative() {
        return outputX * (1 - outputX);
    }


    public void addEdge(Edge edge) {
        adjList.add(edge);
    }

    public void addRevEdge(Edge edge) {
        revAdjList.add(edge);
    }

    public double getOutputX() {
        return outputX;
    }

    public void setOutputX(double outputX) {
        this.outputX = outputX;
    }

    public double getGradient() {
        return gradient;
    }

    public void setGradient(double gradient) {
        this.gradient = gradient;
    }

    public Layer getLayer() {
        return layer;
    }

    public void setLayer(Layer layer) {
        this.layer = layer;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public double getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(double targetValue) {
        this.targetValue = targetValue;
    }

    public List<Edge> getAdjList() {
        return adjList;
    }

    public List<Edge> getRevAdjList() {
        return revAdjList;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }
}
