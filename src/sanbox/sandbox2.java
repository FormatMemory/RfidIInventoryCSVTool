package sanbox;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class sandbox2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String workspace = "C:\\Users\\DavidThinkle\\Dropbox\\InformationLab\\RecommendationSystem\\rfidLabInventory";
		String filename = "strScan.csv";
		String filepath = workspace+"\\"+filename;
		Reader in = new FileReader(filepath);
		ArrayList<String> retArrayList = new ArrayList<String>();
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for (CSVRecord record : records) {
			String s = record.get("TAG ID");
			retArrayList.add(s);
		}
		
		System.out.println(retArrayList);
		System.out.println(retArrayList.size());
	}

}
