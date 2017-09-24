import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataSet {
	protected List<Data> dataValues = new ArrayList<>();


	public DataSet()
	{}

	public DataSet(List<Data> dataValues)
	{
		this.dataValues = dataValues;

	}



	public static  DataSet parseTrainData(String filePath) throws IOException
	{

		DataSet trainData = new DataSet();
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line;

		int i=0;
		while ((line = br.readLine()) != null)
		{
			if(nullCheck(line)) {

				Data data=Data.parseTrainData(line);
				//	Data.setType(DataSet.DataType.intToType(i));

				trainData.dataValues.add(data);


			}
		}


		return trainData;







	}

	public static  List parseTestData(int j) throws IOException
	{

		List a3=Data.a2;
		if(j==13)
		{
			Double min=(Double) a3.get(0);
			Double max=(Double) a3.get(0);

			for(int i=1;i<a3.size();i++) {
				if((Double)a3.get(i)>max)
					max=(Double)a3.get(i);

				else if((Double)a3.get(i)<min){
					min=(Double)a3.get(i);
				}
			}

			Double avg=(max+min)/2;
			for(int i=0;i<a3.size();i++) {
				if((Double)a3.get(i)>avg)
					Data.a2.set(i, 1.0);
				else if((Double)a3.get(i)<avg)
					Data.a2.set(i, 0.0);
			}
		}





		return Data.a2;
	}

	public static   boolean nullCheck(String line) {
		String s2[]= {"!","@","]","#","$","%","^","&","*","?","["};

		String[] fields=line.split(",");
		for(int i=0;i<fields.length;i++) {
			for(String sd:s2) {
				if(fields[i].equals(sd))
					return false;
			}

			if(fields[i].equals(" "))
				return false;
		}

		return true;
	}
	public void normalize(int i)
	{

		List<Double> max = new ArrayList<>(dataValues.get(0).getAttributes());
		Double maxAttribute=max.get(i);
		List<Double> min = new ArrayList<>(dataValues.get(0).getAttributes());
		Double minAttribute=min.get(i);

		for (Data currentExample : dataValues)
		{
			List<Double> currentAttributes = currentExample.getAttributes();


			Double currentAttribute = currentAttributes.get(i);


			// Update max:
			if (currentAttribute > maxAttribute)
				maxAttribute= currentAttribute;

			// Update min:
			if (currentAttribute < minAttribute)
				minAttribute= currentAttribute;

		}

		// Normalize:
		for (Data currentExample : dataValues)
		{
			List<Double> currentAttributes = currentExample.getAttributes();


			Double currentAttribute = currentAttributes.get(i);


			Double newValue = (currentAttribute - minAttribute) / (maxAttribute - minAttribute);
			currentAttributes.set(i, newValue);
		}
	}




}
