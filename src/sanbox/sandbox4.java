package sanbox;

import java.awt.List;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import rfidInvertoryExcelTool.product;

public class sandbox4 {
	
	static String workspace = "C:\\Users\\DavidThinkle\\Dropbox\\InformationLab\\RecommendationSystem\\rfidLabInventory\\";
	static String strMasterInventory = workspace + "master inventory.csv";
	
	
	public static void main(String[] args) throws IOException {
		String workspace = "C:\\Users\\DavidThinkle\\Dropbox\\InformationLab\\RecommendationSystem\\rfidLabInventory\\";
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
			String missingMasterKey = record.get(0);
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
	
	
	 FileWriter fileWriter = null;
	 CSVPrinter csvFilePrinter = null;
    //Create the CSVFormat object with "\n" as a record delimiter
	 CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator("\n");
	 String strMissingFileName = "missing1.csv";
	 String strMissing = workspace + strMissingFileName;
    try {
       // String fileName;
		//initialize FileWriter object
    	System.out.println(strMissing);
        fileWriter = new FileWriter(strMissing);
        //initialize CSVPrinter object
        csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
        //Create CSV file header
        csvFilePrinter.printRecord("Master Key", "UPC",	"EPC Value:", "Style", "Color",	"Size",	"Make");
        //Write a new student object list to the CSV file
        for (product misProduct : missingList) {
            ArrayList<String> misProductInfo = new ArrayList<String>();
            misProductInfo.add(misProduct.getMasterKey());
            misProductInfo.add(misProduct.getUPC());
            misProductInfo.add(misProduct.getEPC());
            misProductInfo.add(misProduct.getStyle());
            misProductInfo.add(misProduct.getColor());
            misProductInfo.add(misProduct.getSize());
            misProductInfo.add(misProduct.getMake());
            System.out.println(misProductInfo);
            csvFilePrinter.printRecord(misProductInfo);
        }
       System.out.println("CSV file was created successfully !!!");
         
    } catch (Exception e) {
        System.out.println("Error in CsvFileWriter !!!");
        e.printStackTrace();
    } finally {
        try {
            fileWriter.flush();
            fileWriter.close();
            csvFilePrinter.close();
        } catch (IOException e) {
            System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
                e.printStackTrace();
            }

	
	System.out.println("End");
    	}
		
	}
}
