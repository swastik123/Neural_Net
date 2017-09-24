import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class Data {

	protected List<Double> attributes = new ArrayList<>();
	static HashMap<String,Double> h1=new HashMap<>();
	static HashMap<Integer,Double> h2=new HashMap<>();
	// static HashMap<String,Double> h3=new HashMap<>();





	static List a2=new ArrayList();
	public Data()
	{
	}
	public List getAttributes() {
		return attributes;
	}
	public void setAttributes(List attributes) {
		this.attributes = attributes;
	}



	public static   Data parseTrainData(String line)
	{
		Data data=new Data();





		String[] fields = line.split(",");

		int j=fields.length-1;
		/*while(!((Integer)Integer.parseInt(fields[j]) instanceof Integer)) {
			   r=new Random();
		       j=r.nextInt(5);
	      }*/

		for(int i=0;i<fields.length;i++) {
			/*  if(i==0) {
				   Random r=new Random();
			        j=r.nextInt(5);
			  }*/

			if(i!=j) {
				if(NumberUtils.isNumber(fields[i] )){

					data.attributes.add(Double.parseDouble(fields[i]));

				}

				else {
					if(h1.containsKey(fields[i])) {



						data.attributes.add(h1.get(fields[i]));

					}
					else {
						if(h2.containsKey(i)) {


							Double n1=h2.get(i);
							h2.put(i, n1+1.0);
							h1.put(fields[i], h2.get(i));
							data.attributes.add(h1.get(fields[i]));
						}



						else {
							h2.put(i, 0.0);
							h1.put(fields[i], h2.get(i));
							data.attributes.add(h1.get(fields[i]));
						}



					}

				}
			}
			else {
				if(NumberUtils.isNumber(fields[i] )){
					a2.add(Double.parseDouble(fields[i]));


				}
				else {
					if(h1.containsKey(fields[i])) {



						a2.add(h1.get(fields[i]));

					}
					else {
					if(h2.containsKey(i)) {


						Double n1=h2.get(i);
						h2.put(i, n1+1.0);
						h1.put(fields[i], h2.get(i));
						a2.add(h1.get(fields[i]));
					}



					else {
						h2.put(i, 0.0);
						h1.put(fields[i], h2.get(i));
						a2.add(h1.get(fields[i]));
					}
					}



					  /*if(h3.size()==0){
						  h3.put(fields[i], 0.0);
						  a2.add(h3.get(fields[i]));
					  }
					  else {
						  if(h3.containsKey(fields[i])) {
							  a2.add(h3.get(fields[i]));
						  }
						  else {
							 double n2=h3.get(key)
						  }
					  }*/
				}
			}

		}

		return data;

	}

		  /* if(fields.length==14) {




		 }

		  if (fields.length != numberOfColumns)
	            throw new IllegalArgumentException("Invalid number of columns in train file: " + fields.length);
		  for (int i = 0; i < fields.length; i++)
	        {

	            if (i == 0)
	                data.m_subjectID = Integer.parseInt(fields[i]);
	            else
			  if (i >= 0 && i <= 2)
	                data.attributes.add(Double.parseDouble(fields[i]));

			  if (i >= 4 && i <= 7)
	                data.attributes.add(Double.parseDouble(fields[i]));

			  if (i >= 9 && i <= 13)
	                data.attributes.add(Double.parseDouble(fields[i]));


	            else if (i == 28)
	            {
	                int classValue = Integer.parseInt(fields[i]);

	                if (classValue == 1)
	                    data.m_class = DataSet.DataClass.PARKINSON;
	                else if (classValue == 0)
	                    data.m_class = DataSet.DataClass.HEALTHY;
	                else
	                    throw new IllegalArgumentException("Invalid class value in train file: " + classValue);

	            }
	        }
		  }
*/



	/*  public static void main(String[] args) {
		  String[][] s=new String[2][5];

		  String []s1= {"1","2","4.5","ram","male"};
		   s[0][0]= "1";
		   s[0][1]="2";
		   s[0][2]="male";
		   s[0][3]="1.2";
		   s[0][4]="wer";
		   s[1][0]="2";
		   s[1][1]="4";
			s[1][2]="female";
		   s[1][3]="2.0";
		  s[1][4]="qer";

		  Data data=  parseTrainData(s1);
		  for(int i=0;i<data.attributes.size();i++) {
			 System.out.println(data.attributes.get(i));
		  }
	  }
	  */

	public List<Double> getTargetList(List a2)
	{
		List<Double> targetList = new ArrayList<>();
		int min=(int) a2.get(0);
		int max=(int)a2.get(0);
		for(int j=1;j<a2.size();j++) {
			if((int)a2.get(j) < min)
				min=(int) a2.get(j);
			if((int)a2.get(j) > max)
				max=(int) a2.get(j);

		}
		int average=(max-min)/2;
		for(int i=0;i<a2.size();i++) {
			if((int)a2.get(i)>average)
				targetList.add(1.0);
			else {
				if((int)a2.get(i)<average)
					targetList.add(0.0);
			}
		}
		return targetList;
	}



}
