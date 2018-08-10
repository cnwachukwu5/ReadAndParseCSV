
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import com.appslabtest.readcsv.ReadAndProcessCSVFile;

public class ReadAndProcessCSVFileTest {
	
	private static ReadAndProcessCSVFile SUT;
	private static File file;
	private static List<CSVRecord> allRows;
	
	@BeforeAll
	public static void init() {
		SUT = new ReadAndProcessCSVFile();
		file = new File("ALF00.csv");
		
		allRows = new ArrayList<>();
		allRows = SUT.parseExcelCSV(file);
	}
	
	@DisplayName("Check if Instance is created")
	@Test
	void validateIntsance() {
		Supplier<String> msg = () -> "Instance created.";
		Assertions.assertNotNull(SUT, msg);
	}
	
	@DisplayName("Check if CSV reads file")
	@Test
	void givenReadAndProcessCSVFile_WhenparseExcelCSV_ThenFileRead() {		
		
		Assertions.assertFalse(allRows.isEmpty());
	}
	
	@DisplayName("Return CSVParser")
	@Test
	void givenReadAndProcessCSVFile_WhengetCSVParser_ThenCSVPArserReturned() {
		Assertions.assertTrue(SUT.getCSVParser(file) instanceof CSVParser);
	}
	
	@DisplayName("Check number of records in file")
	@Test
	void givenReadAndProcessCSVFile_WhengetNumberOfRows_ThenReturnNumberOfRecords() {
		Assertions.assertEquals(126713, SUT.getNumberOfRows(file));
	}
	
	@DisplayName("Check EDW file read")
	@Test
	void givenReadAndProcessCSVFile_Whenpopulate_EDW_Records_ThenEDWListPopulated() {
		SUT.populate_ODW_EDW_Records(file);
		Assertions.assertFalse(SUT.getEdw_Records().isEmpty());
	}
	
	@DisplayName("Search record in list - Binary Search")
	@Test
	void givenReadAndProcessCSVFile_WhenbinarySearch_ThenReturnIndexIfFound() {
		//SUT.SearchRecord(allRows, allRows.get(0));
		
	}
}