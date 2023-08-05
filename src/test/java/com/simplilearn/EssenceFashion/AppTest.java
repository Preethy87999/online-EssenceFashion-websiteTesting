package com.simplilearn.EssenceFashion;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.time.Duration;
import java.time.temporal.TemporalAmount;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest 
{
	ChromeDriver driver;
	Actions action  ;
	ExtentSparkReporter htmlReporter;
	 ExtentReports extent;
	 
	@BeforeSuite
	 public void setup() {
	htmlReporter = new ExtentSparkReporter("extentReport.html");
	htmlReporter.config().setReportName("Automation Results");
	htmlReporter.config().setDocumentTitle("Test Results");
	
	  extent = new ExtentReports();
	  extent.attachReporter(htmlReporter);
	  extent.setSystemInfo("Tester", "Preethy");
	  

	 }
	@BeforeTest(alwaysRun = true)
	public void openBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://127.0.0.1:5500/index.html");
		
	}
	@Test(priority=1)
	public void homePage() {
		
		String actaulTitle = driver.getTitle();
		String expectedTitle = "Essence - Fashion Ecommerce Template";
		AssertJUnit.assertEquals(actaulTitle, expectedTitle);
		ExtentTest Hp = extent.createTest( "Test to validate HomePage ");
		Hp.log(Status.INFO, "Starting test case");
		Hp.pass("Home Page");

	}
	
	@Test(priority=2)
	public void contactBox() {
		driver.findElement(By.xpath("//div[@class='classynav']/ul/li[4]")).click();
		String x = driver.findElement(By.cssSelector(".contact-info h2")).getText();
		System.out.println(x);
		AssertJUnit.assertEquals(x,"How to Find Us");
		driver.findElement(By.cssSelector(".contact-address.mt-50")).getText();
		ExtentTest cb= extent.createTest( "Test to validate ContactBox ");
		cb.log(Status.INFO, "Starting test case");
		cb.pass("ContactBox");
	}
	
	@Test(priority=3)
	public void blogs() {
		driver.findElement(By.xpath("//div[@class=\"classynav\"]/ul/li[3]")).click();
		String m = driver.getCurrentUrl();
		String v = "http://127.0.0.1:5500/blog.html";
		AssertJUnit.assertEquals(v,  m);
		System.out.println(driver.findElement(By.cssSelector(".page-title.text-center")).getText());
		int w = driver.findElements(By.className("post-title")).size();
		System.out.println(w);
		WebElement act = driver.findElement(By.xpath("(//div[@class='hover-content'])[2]"));
		action = new Actions(driver);
		action.moveToElement(act).perform();
		ExtentTest blos= extent.createTest( "Test to validate Blogs");
		blos.log(Status.INFO, "Starting test case");
		blos.pass("Blogs");
	}
	
	@Test(priority=5)
	public void shopPage() {
		WebElement shop = driver.findElement(By.className("megamenu-item"));
		action = new Actions(driver);
		action.moveToElement(shop).perform();
		int e =driver.findElements(By.cssSelector(".single-mega.cn-col-4")).size();
		AssertJUnit.assertEquals(e,4);
		ExtentTest sp = extent.createTest( "Test to validate Shop Page ");
		sp.log(Status.INFO, "Starting test case");
		sp.pass("Shop Page");
	}
	
	@Test(priority=4)
	public void pagesBlock() throws InterruptedException {
		WebElement page = driver.findElement(By.cssSelector(".cn-dropdown-item.has-down.pr12"));
		action = new Actions(driver);
		action.moveToElement(page).perform();
		int c = driver.findElements(By.xpath("//ul[@class='dropdown']/li")).size();
		AssertJUnit.assertEquals(c, 8);
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("a[href='single-blog.html']")).click();
		Thread.sleep(1000);
		String t = driver.getCurrentUrl();
		String l = "http://127.0.0.1:5500/single-blog.html";
		AssertJUnit.assertEquals(l, t);
		WebElement page2 = driver.findElement(By.cssSelector(".cn-dropdown-item.has-down.pr12"));
		action = new Actions(driver);
		action.moveToElement(page2).perform();
		driver.findElement(By.cssSelector("a[href='regular-page.html']")).click();
		String y = driver.findElement(By.xpath("//div[@class='regular-page-text']/h2")).getText();
		String g = "Vivamus sed nunc in arcu cursus mollis quis et orci. Interdum et malesuada";
		AssertJUnit.assertEquals( y, g);
		ExtentTest test2 = extent.createTest( "Test to validate PagesBlock ");
		test2.log(Status.INFO, "Starting test case");
		test2.pass("Pages Block");
	}
	
	@Test(priority=6)
	public void purchasePage() throws InterruptedException {
		WebElement purchase = driver.findElement(By.cssSelector(".cn-dropdown-item.has-down.pr12"));
		action = new Actions(driver);
		action.moveToElement(purchase).perform();
		driver.findElement(By.xpath("//ul[@class='dropdown']/li[2]")).click();
		String b = driver.findElement(By.cssSelector(".page-title.text-center")).getText();
		String k = "DRESSES";
		AssertJUnit.assertEquals(b,k);
		Thread.sleep(1000);
		driver.findElement(By.className("current")).click();
		Thread.sleep(1000);
		int u =  driver.findElements(By.xpath("//ul[@class='list']/li")).size();
		 AssertJUnit.assertEquals(u, 4);
		 driver.findElement(By.xpath("//ul[@class='list']/li[2]")).click();
		 driver.findElement(By.className("slider-range")).click();
		 driver.findElement(By.className("range-price")).getText();
		 Thread.sleep(1000);
		System.out.println(driver.findElements(By.className("page-item")).size());
	    	 driver.findElement(By.xpath("(//div[@class='product-description'])[3]")).click();
	    	 driver.findElement(By.cssSelector(".nice-select.mr-5")).click();
	    	 driver.findElement(By.xpath("(//li[normalize-space()='Size: S'])[1]")).click();
	    	 driver.findElement(By.cssSelector("div[class='nice-select']")).click();
	    	 driver.findElement(By.xpath("(//li[normalize-space()='Color: White'])[1]")).click();
	    	 driver.findElement(By.cssSelector(".favme.fa.fa-heart")).click();
	    	 ExtentTest pp = extent.createTest( "Test to validate Purchase Page ");
	 		pp.log(Status.INFO, "Starting test case");
	 		pp.pass("Purchase Page");
	    	 
	}
	
	@Test(priority=7)
	public void checkOut() {
		WebElement check = driver.findElement(By.cssSelector(".cn-dropdown-item.has-down.pr12"));
		action = new Actions(driver);
		action.moveToElement(check).perform();
		driver.findElement(By.xpath("//ul[@class='dropdown']/li[4]")).click();
		driver.findElement(By.id("first_name")).sendKeys("Priya");
		driver.findElement(By.id("last_name")).sendKeys("V S");
		try {
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//span[@class='current']")).click();
		driver.findElement(By.xpath("//div[@class='nice-select w-100 open']/ul/li[5]")).click();
		driver.findElement(By.id("street_address")).sendKeys("Chennai");
		driver.findElement(By.id("postcode")).sendKeys("600005");
		driver.findElement(By.id("city")).sendKeys("xxxx");
		driver.findElement(By.id("state")).sendKeys("YYY");
		driver.findElement(By.id("phone_number")).sendKeys("9999944599");
		driver.findElement(By.id("email_address")).sendKeys("priya@example.com");
		List<WebElement> t = driver.findElements(By.cssSelector(".custom-control-label"));
		for(int i=0;i<t.size();i++)
		{
			 driver.findElements(By.cssSelector(".custom-control-label")).get(i).click();
			 
		}
		WebElement ch=driver.findElement(By.xpath("(//a[normalize-space()='Place Order'])[1]"));
		 
		action = new Actions(driver);
		action.moveToElement(ch).perform();
		driver.findElement(By.id("essenceCartBtn")).click();
		ExtentTest cb = extent.createTest( "Test to validate Cart Box ");
	    cb.log(Status.INFO, "Starting test case");
		cb.pass("Cart Page");
	}
	@AfterTest(alwaysRun = true)
	public void closeBrowser() {
		driver.close();
	}	
	
	@AfterSuite
	 public void tearDown() {
	driver.quit();
	  extent.flush();
	}
}