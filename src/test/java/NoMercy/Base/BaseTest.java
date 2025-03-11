package NoMercy.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import NoMercy.POM.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initDriver() throws IOException {
		FileInputStream broserFile = new FileInputStream(
				"C:\\Users\\ANUJ\\eclipse-workspace\\NoMercy\\src\\main\\java\\NoMercy\\Resources\\Browser.properties");
		Properties prop = new Properties();
		prop.load(broserFile);
		String browser = prop.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}

		else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}

		else {
			System.out.println("incorrect browser name");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchHomePage() throws IOException {
		driver = initDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void terminateBrowser() {
		driver.quit();
	}

	public Object[][] getData() {
		Object[][] data = new Object[2][2];
		data[0][0] = "anu@example.com";
		data[0][1] = "Anu@1234";
		data[1][0] = "anu@example.com";
		data[1][1] = "Anu@1234";
		return data;
	}

	public List<HashMap<String,String>> getJsonData() throws IOException {
		File file = new File(
				"C:\\Users\\ANUJ\\eclipse-workspace\\NoMercy\\src\\main\\java\\NoMercy\\Resources\\data.json");
		String strdata = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> cred = mapper.readValue(strdata,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		
		return cred;
		
	}
	
	public Object[][] getExcelData() throws IOException {
		DataFormatter formatter = new DataFormatter();
		FileInputStream file = new FileInputStream("E:\\Realme 7\\cred.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sh=wb.getSheetAt(0);
		int rowNum = sh.getPhysicalNumberOfRows();
		XSSFRow row = sh.getRow(0);
		int colNum = row.getPhysicalNumberOfCells();
		Object[][] data = new Object [rowNum-1][colNum];
		for(int i=1;i<rowNum;i++) {
			for(int j=0;j<sh.getRow(i).getPhysicalNumberOfCells();j++) {
				data[i-1][j]= formatter.formatCellValue(sh.getRow(i).getCell(j));
			}
		}
		return data;
	}
	
	public String getScreenShot(WebDriver driver, String testName) throws IOException {
		TakesScreenshot sc = (TakesScreenshot)driver;
		File src = sc.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("E:\\Realme 7\\Books\\reports"+testName+".png"));
		return "E:\\Realme 7\\Books\\reports"+testName+".png";
	}
}
