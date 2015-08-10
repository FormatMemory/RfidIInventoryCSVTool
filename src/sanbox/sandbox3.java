package sanbox;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import rfidInvertoryExcelTool.product;

public class sandbox3 {
	static String workspace = "C:\\Users\\DavidThinkle\\Dropbox\\InformationLab\\RecommendationSystem\\rfidLabInventory\\";
	static String strMasterInventory = workspace + "master inventory.csv";
	
	
	public static void main(String[] args) throws IOException {
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
		
	ArrayList<product> missingList = new ArrayList<product>();
	String filepath1 = strMasterInventory;
	Reader in1 = new FileReader(filepath1);
	Iterable<CSVRecord> records1 = CSVFormat.EXCEL.withHeader().parse(in1);
	for (CSVRecord record : records1) {
		String itemEPC = record.get("EPC Value:");
		if(!retArrayList.contains(itemEPC))
		{
			String missingMasterKey = record.get("Master Key");
			String missingUPC = record.get("UPC");
			//String missingEPC = record.get("EPC Value");
			String missingStyle = record.get("Style");
			String missingColor = record.get("Color");
			String missingSize = record.get("Size");
			String missingMake = record.get("Make");
			/*ArrayList<String> missingItem = new ArrayList<String>();
			missingItem.add(missingMasterKey);
			missingItem.add(missingUPC);
			missingItem.add(itemEPC);
			missingItem.add(missingStyle);
			missingItem.add(missingColor);
			missingItem.add(missingSize);
			missingItem.add(missingMake);
			missingList.add(missingItem);*/
			product misProduct = new product(missingMasterKey,missingUPC,itemEPC,missingStyle,missingColor,missingSize,missingMake);
			missingList.add(misProduct);
		}
	}
	
	
	System.out.println("-MasterInventory-File Readed---"+missingList.size()+"--Missing Items Have Been Detected.");
	}
}

