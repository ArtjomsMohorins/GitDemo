package Academy;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.base;

public class HomePage extends base{
	
	public static Logger log = LogManager.getLogger(base.class.getName());
	
	@BeforeTest
	public void initialize() throws IOException
	{
		driver=initializeDriver();
	}
	
	@Test(dataProvider="getData")
	public void basePageNavigation(String Username, String Password, String text) throws IOException
	{
		driver.get(prop.getProperty("url"));
		LandingPage l=new LandingPage(driver);
		l.getLogin().click();
		LoginPage lp=new LoginPage(driver);
		lp.getEmail().sendKeys(Username);
		lp.getPassword().sendKeys(Password);
		System.out.println(text);
		log.info(text);
		lp.getLogin().click();
	}
		
	@DataProvider
	public Object[][] getData()
	{
		//Row stands for how many different data types test should run
		Object[][] data = new Object[2][3];
		//0th row
		data[0][0] = "non-restricteduser@gocc.com";
		data[0][1] = "12345";
		data[0][2] = "Restricted user";
		//1st raw
		data[1][0] = "restricted@gooc.com";
		data[1][1] = "45678";
		data[1][2] = "Non restricted user";
		
		return data;
	}
	

	@AfterTest
	public void teardown()
	{
		driver.close();
		driver=null;
	}
}


