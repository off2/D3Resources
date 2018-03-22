import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.math.BigInteger;
import java.util.List;

public class D3Test{

	static WebDriver driver = new HtmlUnitDriver();
	// Start at the home page for D3 for each test
	@Before
	public void setUp() throws Exception {
		driver.get("https://cs1632ex.herokuapp.com/");
	}




	//Tests welcome message
	//requirement 1

	@Test
	public void testWelcomeMessage(){
		WebElement e = driver.findElement(By.tagName("body"));
		assert(e.getText().contains("Welcome, friend, to a land of pure calculation"));



	}


	//Tests used by message
	//requirement 1

	@Test
	public void testUsedByMessage(){
		WebElement e = driver.findElement(By.tagName("body"));
		assert(e.getText().contains("Used for CS1632 Software Quality Assurance, taught by Bill Laboon"));



	}
	// Given that I am on the main page
	// When I view the header
	// Then I see that it contains "CS1632 D3 Home", "Factorial", "Fibonacci", "Hello", and "Cathedral Pics".
	//Requirement 2
	@Test
	public void testHasCorrectHeaderLinks() {

		try {
			driver.findElement(By.linkText("CS1632 D3 Home"));
			driver.findElement(By.linkText("Factorial"));
			driver.findElement(By.linkText("Fibonacci"));
			driver.findElement(By.linkText("Hello"));
			driver.findElement(By.linkText("Cathedral Pics"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}


	//Tests links on home page lead to correct urls
	//Requirement 2

	@Test
	public void testHomeLink(){
		WebElement e = driver.findElement(By.linkText("CS1632 D3 Home"));
		e.click();
		String s = driver.getCurrentUrl();
		assertEquals("https://cs1632ex.herokuapp.com/",s);

	}
	@Test
	public void testFactorialLink(){
		WebElement e = driver.findElement(By.linkText("Factorial"));
		e.click();
		String s = driver.getCurrentUrl();
		// assertEquals("https://cs1632ex.herokuapp.com/fact",s);

	}
	@Test
	public void testFibonacciLink(){
		WebElement e = driver.findElement(By.linkText("Fibonacci"));
		e.click();
		String s = driver.getCurrentUrl();
		assertEquals("https://cs1632ex.herokuapp.com/fib",s);

	}
	@Test
	public void testHelloLink(){
		WebElement e = driver.findElement(By.linkText("Hello"));
		e.click();
		String s = driver.getCurrentUrl();
		assertEquals("https://cs1632ex.herokuapp.com/hello",s);

	}
	@Test
	public void testCathedralLink(){
		WebElement e = driver.findElement(By.linkText("Cathedral Pics"));
		e.click();
		String s = driver.getCurrentUrl();
		assertEquals("https://cs1632ex.herokuapp.com/cathy",s);

	}

	//tests Factorial fuctionality on 1
	//Requirement 3
	@Test
	public void testFactorial1(){
		driver.findElement(By.linkText("Factorial")).click();
		WebElement e = driver.findElement(By.name("value"));
		e.sendKeys("1");
		try{
			e.submit();
			String r = driver.findElement(By.tagName("h2")).getText();
			assertTrue(r.contains("Factorial of 1 is 1!"));
		}
		catch(Exception ex){
			fail();
		}
	}


	//tests Factorial fuctionality on 100
	//Requirement 3
	@Test
	public void testFactorial100(){
		driver.findElement(By.linkText("Factorial")).click();
		WebElement e = driver.findElement(By.name("value"));
		e.sendKeys("100");
		try{
			e.submit();
			String r = driver.findElement(By.tagName("h2")).getText();
			BigInteger f = factorial(100);
			assertTrue(r.contains("Factorial of 100 is "+f.toString()+"!"));
		}
		catch(Exception ex){
			fail();
		}
	}

	//tests Factorial fuctionality on 50
	//Requirement 3
	@Test
	public void testFactorial50(){
		driver.findElement(By.linkText("Factorial")).click();
	  WebElement e = driver.findElement(By.name("value"));
		e.sendKeys("50");
		try{
			e.submit();
			String r = driver.findElement(By.tagName("h2")).getText();
			BigInteger f = factorial(50);
			assertTrue(r.contains("Factorial of 50 is "+f.toString()+"!"));
		}
		catch(Exception ex){
			fail();
		}
	}

	//helper function for factorials of large numbers
	public BigInteger factorial(int n) {
		BigInteger factorial = BigInteger.valueOf(1);

		for (int i = 1; i <= n; i++) {
			factorial = factorial.multiply(BigInteger.valueOf(i));
		}

		return factorial;
	}

	//Test that the hello page works with no trailing value
	//Requirement 6
	@Test
	public void testHelloEmpty(){
		try{
			driver.navigate().to("https://cs1632ex.herokuapp.com/hello/");
			WebElement e = driver.findElement(By.tagName("h2"));
			String str = e.getText();
			assertTrue(str.contains("Hello CS1632, from Prof. Laboon!"));
		}
		catch(Exception e){
			fail();
		}
	}

	//Test that the cathy page 3 pictures
	//Requirement 8

	@Test
	public void testCathyPage(){
		driver.navigate().to("https://cs1632ex.herokuapp.com/cathy/");
		try{
			List<WebElement> listItems = driver.findElements(By.tagName("img"));
			assertEquals(listItems.size(), 3);
		}
		catch(Exception e){
			fail();
		}


	}


//tests Fibonacci fuctionality on 1
//Requirement 4
@Test
public void testFib1(){
	driver.findElement(By.linkText("Fibonacci")).click();
  WebElement e = driver.findElement(By.id("tb1"));
	e.sendKeys("1");
	try{
		e.submit();
		String r = driver.findElement(By.tagName("h2")).getText();
		assertTrue(r.contains("Fibonacci of 1 is 1!"));
	}
	catch(Exception ex){
		fail();
	}
}


//tests Fibonacci fuctionality on 100
//Requirement 4
@Test
public void testFib100(){
	driver.findElement(By.linkText("Fibonacci")).click();
  WebElement e = driver.findElement(By.id("tb1"));
	e.sendKeys("100");
	try{
		e.submit();
		String r = driver.findElement(By.tagName("h2")).getText();
		assertTrue(r.contains("Fibonacci of 100 is 354224848179261915075!"));
	}
	catch(Exception ex){
		fail();
	}
}

//tests Fibonacci fuctionality on 30
//Requirement 4
@Test
public void testFib30(){
	driver.findElement(By.linkText("Fibonacci")).click();
  WebElement e = driver.findElement(By.id("tb1"));
	e.sendKeys("30");
	try{
		e.submit();
		String r = driver.findElement(By.tagName("h2")).getText();
		assertTrue(r.contains("Fibonacci of 30 is 832040!"));
	}
	catch(Exception ex){
		fail();
	}
}
//tests Fibonacci fuctionality on 31
//Requirement 4
@Test
public void testFib31(){
	driver.findElement(By.linkText("Fibonacci")).click();
  WebElement e = driver.findElement(By.id("tb1"));
	e.sendKeys("31");
	try{
		e.submit();
		String r = driver.findElement(By.tagName("h2")).getText();
		assertTrue(r.contains("Fibonacci of 31 is 1346269!"));
	}
	catch(Exception ex){
		fail();
	}
}

//tests Fibonacci fuctionality on invalid input "-1"
//Requirement 5
@Test
public void testFibBadNeg1(){
	driver.findElement(By.linkText("Fibonacci")).click();
  WebElement e = driver.findElement(By.id("tb1"));
	e.sendKeys("-1");
	try{
		e.submit();
		String r = driver.findElement(By.tagName("h2")).getText();
		assertTrue(r.contains("Fibonacci of -1 is 1!"));
	}
	catch(Exception ex){
		fail();
	}
}

//tests Factorial fuctionality on invalid input "-1"
//Requirement 5
@Test
public void testFacBadNeg1(){
	driver.findElement(By.linkText("Factorial")).click();
  WebElement e = driver.findElement(By.name("value"));
	e.sendKeys("-1");
	try{
		e.submit();
		String r = driver.findElement(By.tagName("h2")).getText();
		assertTrue(r.contains("Factorial of -1 is 1!"));
	}
	catch(Exception ex){
		fail();
	}
}


//tests Fibonacci fuctionality on invalid input "101"
//Requirement 5
@Test
public void testFibBadTooLarge(){
	driver.findElement(By.linkText("Fibonacci")).click();
  WebElement e = driver.findElement(By.id("tb1"));
	e.sendKeys("101");
	try{
		e.submit();
		String r = driver.findElement(By.tagName("h2")).getText();
		assertTrue(r.contains("Fibonacci of 101 is 1!"));
	}
	catch(Exception ex){
		fail();
	}
}

//tests Factorial fuctionality on invalid input "101"
//Requirement 5
@Test
public void testFacBadTooLarge(){
	driver.findElement(By.linkText("Factorial")).click();
  WebElement e = driver.findElement(By.name("value"));
	e.sendKeys("101");
	try{
		e.submit();
		String r = driver.findElement(By.tagName("h2")).getText();
		assertTrue(r.contains("Factorial of 101 is 1!"));
	}
	catch(Exception ex){
		fail();
	}
}


//tests Fibonacci fuctionality on invalid input "hi"
//Requirement 5
@Test
public void testFibBadString(){
	driver.findElement(By.linkText("Fibonacci")).click();
  WebElement e = driver.findElement(By.id("tb1"));
	e.sendKeys("hi");
	try{
		e.submit();
		String r = driver.findElement(By.tagName("h2")).getText();
		assertTrue(r.contains("Fibonacci of hi is 1!"));
	}
	catch(Exception ex){
		fail();
	}
}


//tests Factorial fuctionality on invalid input "hi"
//Requirement 5
@Test
public void testFacBadString(){
	driver.findElement(By.linkText("Factorial")).click();
  WebElement e = driver.findElement(By.name("value"));
	e.sendKeys("hi");
	try{
		e.submit();
		String r = driver.findElement(By.tagName("h2")).getText();
		assertTrue(r.contains("Factorial of hi is 1!"));
	}
	catch(Exception ex){
		fail();
	}
}


//Test that the hello page works when given a trailing "Lenny"
//Requirement 7
@Test
public void testHelloLenny(){
	try{
		driver.navigate().to("https://cs1632ex.herokuapp.com/hello/Lenny");
		WebElement e = driver.findElement(By.tagName("h2"));
		String str = e.getText();
		assertTrue(str.contains("Hello CS1632, from Lenny!"));
	}
	catch(Exception e){
		fail();
	}
}

//Test that the hello page works when given an extremely long trailing value
//Requirement 7
@Test
public void testHelloLongURL(){
	try{
		driver.navigate().to("https://cs1632ex.herokuapp.com/hello/DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
		WebElement e = driver.findElement(By.tagName("h2"));
		String str = e.getText();
		assertTrue(str.contains("Hello CS1632, from Lenny!"));
	}
	catch(Exception e){
		fail();
	}
}

	/*@After
	public void shutDown() throws Exception {
		driver.quit();
	}*/

 }
