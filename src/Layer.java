import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Layer {
    private List<Node> nodes;
    private LayerType layerType;
    private Layer previousLayer;

    public Layer(Layer previousLayer, LayerType layerType) {
        nodes = new ArrayList<>();
        this.previousLayer = previousLayer;
        this.layerType = layerType;
    }

    public boolean initializeInputLayer() {

        if (ApplicationRunner.getData().dataValues == null || ApplicationRunner.getData().dataValues.size() == 0)
            return false;
        // access data : input accessing
        List lstinputNodes = ApplicationRunner.getData().dataValues.get(0).getAttributes();
        for (int i = 0; i < lstinputNodes.size(); i++) {
            Node node = new Node(this);
            nodes.add(node);
        }

        return true;
    }

    public void initializeHiddenLayer() {
        for (int i = 0; i < ApplicationRunner.getHiddenLayerNodeCount(); i++) {
            Node node = new Node(this);
            Random rand = new Random();
            // create edges from previous node
            List<Node> previousNodes = previousLayer.getNodes();
            for (Node prevNode : previousNodes) {
                float value = rand.nextFloat() * 2 - 1;
                Edge edge = new Edge(value, prevNode, node);
                Edge revEdge = new Edge(value, node, prevNode);
                prevNode.addEdge(edge);
                node.addRevEdge(revEdge);
            }
            nodes.add(node);
        }
    }

    public void initializeOutputLayer() {
        Node outputNode = new Node(this);
        Random rand = new Random();
        List<Node> previousNodes = previousLayer.getNodes();
        for (Node prevNode : previousNodes) {
            float value = rand.nextFloat() * 2 - 1;
            Edge edge = new Edge(value, prevNode, outputNode);
            Edge revEdge = new Edge(value, outputNode, prevNode);
            prevNode.addEdge(edge);
            outputNode.addRevEdge(revEdge);
        }

        nodes.add(outputNode);
    }

    public void setInputLayerOutputValues(int recordId) {
        if (this.layerType != LayerType.INPUT)
            return;
        // access data : input accessing
        List<Double> inputLayerOutputValues = ApplicationRunner.getData().dataValues.get(recordId).attributes;

        for (int i = 0; i < inputLayerOutputValues.size(); i++) {
            nodes.get(i).setOutputX(inputLayerOutputValues.get(i));
        }
    }

    public void setTargetValues(double outputLayerTargetValue) {
        if (this.layerType != LayerType.OUTPUT)
            return;

        nodes.get(0).setTargetValue(outputLayerTargetValue);
    }

    public void performForwardPassCalculation() {
        for (Node node : nodes)
            node.performForwardPassCalculation();
    }

    public void calculateOutputGradientValue() {
        if (this.layerType != LayerType.OUTPUT)
            return;

        nodes.get(0).calculateOutputGradientValue();
    }

    public void calculateGradientValue() {
        for (Node node : nodes)
            node.calculateGradientValue();
    }

    public void updateEdgesWeight(int hiddenLayerId) {
        System.out.println("Hidden Layer Id " + hiddenLayerId + " :");

        for (int i = 0;i < nodes.size();i++) {
            nodes.get(i).updateEdgesWeight();
        }
    }

    public Double calculateError(int recordId) {
        // access data : Output
        Node outputNode = nodes.get(0);
        Double nodeOutput = outputNode.getOutputX() ;

        Double actualOutput = (Double) Data.a2.get(recordId);
        Double err=actualOutput-nodeOutput;

        return   err*err;
    }
    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public LayerType getLayerType() {
        return layerType;
    }

    public void setLayerType(LayerType layerType) {
        this.layerType = layerType;
    }
}
