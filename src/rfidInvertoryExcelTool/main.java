package rfidInvertoryExcelTool;
/*import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
*/
import java.io.*;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

//
		/*
		 *External Libeary: Apache POI 
		 */

		//Logic:
		/*  1.read tagMaster tag ID from scan.xlsx --->List
			2.read master_inventory.xlsx
				if(master.EPC Value in scan.TAG ID) continue;
				else
				 	missingList.add(this--->UPC, EPC Value, Style, Color, Size, Make);
				 	master+ #N/A at last (or can high light...)
				 	
			3.write missingList-->missSheet
				(if needed --> add last updated time<-- current time)
			
			4.Print missing number <-- missingList.size();
			  sysout: end	 	
		 */	



public class main {
	
	
	public static void main(String[] args) throws IOException {
		
		
		
		String workspace =  ".\\"; //".\\.\\file\\";
		String strMasterInventoryFileName = "master inventory.csv";
		String strScanFileName =  "strScan.csv"; 
		String strMissingFileName = "missing.csv";
		
		String strMasterInventory = workspace + strMasterInventoryFileName;
		String strScan = workspace + strScanFileName; 
		String strMissing = workspace + strMissingFileName;
		
		System.out.println("     ------Program Start------\n");
		
		System.out.println("     -----------------*----------------*--------------------");
		System.out.println("Please make sure the input file format are '*.csv'.");
		System.out.println("Please make sure extra information in input file has been deleted.");
		System.out.println("And headers should be in the first line in input file.");
		System.out.println("Please make sure the input master inventory file master key and UPC column formats are [fraction]");
		System.out.println("     -----------------*-----------------*-------------------\n");
		System.out.println("Workspace:  "+workspace);
		System.out.println();
		try {
			ArrayList<String> scanList = readEPCfromScan(strScan,strScanFileName);
			ArrayList<product> missingList = readMissingfromScan(strMasterInventory, scanList,strMasterInventoryFileName);
			Integer numMiss = writeMissing(strMissing,missingList,strMissingFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			System.out.println("\n      ------Program Finish------");
		}
		System.in.read();

	}
	
	
	
	
	//return a scanList contains all tags EPC number from scan
	public static ArrayList<String> readEPCfromScan(String strScan, String strScanFileName) throws IOException
	{
		ArrayList<String> scanList = new ArrayList<String>();
		//int tagStartLine = 7;  //in Scan file, the tag Id info starts from the 7th line.
		//String filepath = strScan;
		Reader in = new FileReader(strScan);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for (CSVRecord record : records) {
			String s = record.get("TAG ID");
			scanList.add(s);
		}
		
		System.out.println("-File--"+strScanFileName+" -Readed---"+scanList.size()+"--Tags Has Been Readed.");
		return scanList;
	}
	
	
	//return a missing List of items which is in MasterInventory but not in scanList(from scan)
	public static ArrayList<product> readMissingfromScan(String strMasterInventory, ArrayList<String> scanList, String MasterInventoryFileName) throws IOException
	{
		ArrayList<product> missingList = new ArrayList<product>();
		String filepath = strMasterInventory;
		Reader in = new FileReader(filepath);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for (CSVRecord record : records) {
			String itemEPC = record.get("EPC Value:");
			if(!scanList.contains(itemEPC))
			{
				String missingMasterKey = record.get("Master Key");
				String missingUPC = record.get("UPC");
				String missingStyle = record.get("Style");
				String missingColor = record.get("Color");
				String missingSize = record.get("Size");
				String missingMake = record.get("Make");
				product misProduct = new product(missingMasterKey,missingUPC,itemEPC,missingStyle,missingColor,missingSize,missingMake);
				missingList.add(misProduct);
			}
		}
		
		
		System.out.println("-File--"+MasterInventoryFileName+" -Readed---"+missingList.size()+"--Missing Items Have Been Detected.");
		return missingList;
	}
	
	
	//return a integer number from missing numbers has been written to strMissing(file);
	public static Integer writeMissing(String strMissing, ArrayList<product> missingList,String strMissingFileName)
	{
		
		//....
		Integer retNum = missingList.size();
		
		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;
	    //Create the CSVFormat object with "\n" as a record delimiter
		CSVFormat csvFileFormat = CSVFormat.EXCEL.withRecordSeparator("\n");
		 
	    try {
	       // String fileName;
			//initialize FileWriter object
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
	      // System.out.println("CSV file was created successfully !!!");
	         
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
		//System.out.println("End");
	    	}
		
		System.out.println("----Miss Items' Information of---"+retNum+"--Have Been Write to File: "+strMissingFileName);
		return retNum;
	}
	

}
