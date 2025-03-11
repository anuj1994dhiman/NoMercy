package NoMercy.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import NoMercy.Base.BaseTest;
import NoMercy.Base.MyRetry;
import NoMercy.POM.ProductsPage;

public class LoginTest extends BaseTest {

	// taking data through data provider.

//	@Test(dataProvider = "provideDataToTest")
//	public void test1(String uName, String pass) {
//		ProductsPage productsPage = landingPage.login(uName, pass);
//		Assert.assertEquals(productsPage.getcatPagetext(), "Automation Practice");
//		
//	}

	// taking data through json file.

//	@Test(dataProvider = "provideDataToTest")
//	public void test1(HashMap<String, String> data) {
//		ProductsPage productsPage = landingPage.login(data.get("username"), data.get("password"));
//		Assert.assertEquals(productsPage.getcatPagetext(), "Automation Practice");
//		
//	}
	// taking data through xcel file
	@Test(dataProvider = "provideDataToTest", groups = "smoke")
	public void test1(String uName, String pass) {
		ProductsPage productsPage = landingPage.login(uName, pass);
		Assert.assertEquals(productsPage.getcatPagetext(), "Automation Practice");

	}

	@Test(retryAnalyzer = MyRetry.class, groups ="sanity")
	public void test2() throws InterruptedException {
		landingPage.login("anu@example.com", "bchfnfjc");
		Assert.assertEquals("Incorrect email o password.", landingPage.getErrorMsg());
	}

	@DataProvider
	public Object[][] provideDataToTest() throws IOException {
		Object[][] data = getExcelData();
		return data;
	}

}
