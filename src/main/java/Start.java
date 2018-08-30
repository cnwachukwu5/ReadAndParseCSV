
import java.util.List;
import java.io.File;
import java.util.ArrayList;

import org.apache.commons.csv.CSVRecord;
import com.appslabtest.readcsv.ReadAndProcessCSVFile;

public class Start {

	public static void main(String[] args) {
		
		List<CSVRecord> allF00 = new ArrayList<>();
		List<CSVRecord> allF00CONV = new ArrayList<>();
		
		ReadAndProcessCSVFile testInstance = new ReadAndProcessCSVFile();
		
		File file = new File("GAF24.csv");
		File file2 = new File("GALICF24.CONV.sorted.csv");
		
		testInstance.setCurrentFile(file);
		
		long startTime = System.currentTimeMillis();
			allF00 = testInstance.parseExcelCSV(file);
			allF00.remove(0); //Remove the header
			
			allF00CONV = testInstance.parseExcelCSV(file2);
			testInstance.setEdw_Records(allF00);
			testInstance.setOdw_Records(allF00CONV);
		long endTime = System.currentTimeMillis();
		
		System.out.println("Load time: " + (endTime - startTime) / 1000.0);
		
		System.out.println("Completed ...");
		
		long startTime2 = System.currentTimeMillis();
			testInstance.searchAndRemoveRecordsNotInOtherList(testInstance.getEdw_Records(), testInstance.getOdw_Records());
		long endTime2 = System.currentTimeMillis();
		double duration = (endTime2 - startTime2) / 1000.0;
		
		System.out.println("Hashtable load time: " + duration);
		
		System.out.println("EDW Record : " + testInstance.getEdw_Records().size());
		System.out.println("ODW Record : " + testInstance.getOdw_Records().size());
		System.out.println("Records_In_EWD_And_In_ODW : " + testInstance.getRecords_In_EWD_And_In_ODW().size());
		System.out.println("Records_In_EWD_Not_In_ODW : " + testInstance.getRecords_In_EWD_Not_In_ODW().size());
		System.out.println("Records_In_OWD_And_In_EDW : " + testInstance.getRecords_In_OWD_And_In_EDW().size());
		System.out.println("Records_In_OWD_Not_In_EDW : " + testInstance.getRecords_In_OWD_Not_In_EDW().size());
		
		System.out.println("");
		System.out.println("Started sorting time: " );
		
		long startTime3 = System.currentTimeMillis();
			testInstance.mergeSort(testInstance.convertListAndReturnArray(testInstance.getRecords_In_EWD_And_In_ODW()));
		long endTime3 = System.currentTimeMillis();
		
		System.out.println("Sorting time: " + (endTime3 - startTime3) / 1000.0);
		
		testInstance.writeAllRecords(file, "June2018");
	}

}
