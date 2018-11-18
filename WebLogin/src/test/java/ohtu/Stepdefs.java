package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Stepdefs {

    String chrome = System.setProperty("webdriver.chrome.driver", "/Users/jaana/Desktop/chromedriver");

    WebDriver driver = new ChromeDriver();
    String baseUrl = "http://localhost:4567";

    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
    }


    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_and_password_are_given(String username, String password) throws Throwable {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }


    @Then("^system will respond \"([^\"]*)\"$")
    public void system_will_respond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }


    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void correct_username_and_incorrect_password_are_given(String username, String password) throws Throwable {
      logInWith(username, password);
  }


    @When("^nonexistent username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void nonexistent_username_and_incorrect_password(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }


    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void correct_username_and_password_are_given(String username, String password) throws Throwable {
      logInWith(username, password);
    }

    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
      pageHasContent("Ohtu Application main page");
    }


    @Given("^command new user is selected$")
    public void command_new_user_is_selected() throws Throwable {
      driver.get(baseUrl);
      WebElement element = driver.findElement(By.linkText("register new user"));
      element.click();

    }

    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void a_valid_username_and_password_and_matching_password_confirmation_are_entered(String username, String password, String passwordConfirmation) throws Throwable {
      WebElement element = driver.findElement(By.name("username"));
      element.sendKeys(username);
      element = driver.findElement(By.name("password"));
      element.sendKeys(password);
      element = driver.findElement(By.name("passwordConfirmation"));
      element.sendKeys(passwordConfirmation);
      element = driver.findElement(By.name("signup"));
      element.submit();
    }

    @Then("^a new user is created$")
    public void a_new_user_is_created() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }

    @When("^valid username \"([^\"]*)\" and too short password \"([^\"]*)\" and matching password confirmation are entered$")
    public void valid_username_and_too_short_password_and_matching_password_confirmation_are_entered(String username, String password, String passwordConfirmation) throws Throwable {
       createUser(username, password, passwordConfirmation);
    }

    @Then("^password in too short$")
    public void password_is_too_short() throws Throwable {
        pageHasContent("invalid username or password");
    }

    //@When("^valid username and password but password confirmation do not match$")
    //public void valid_username_and_password_but_password_confirmation_do_not_match() throws Throwable {
    //  createUser(username, password, passwordConfirmation);
    //  }

    @Then("^password confirmation do not match$")
    public void password_confirmation_do_not_match() throws Throwable {
        pageHasContent("invalid username or password");
    }


    @After
    public void tearDown(){
        driver.quit();
    }

    /* helper methods */

    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    private void createUser(String username, String password, String passwordConfirmation){
      assertTrue(driver.getPageSource().contains("Create username and give password"));
      WebElement element = driver.findElement(By.name("username"));
      element.sendKeys(username);
      element = driver.findElement(By.name("password"));
      element.sendKeys(password);
      element = driver.findElement(By.name("passwordConfirmation"));
      element.sendKeys(passwordConfirmation);
      element = driver.findElement(By.name("signup"));
      element.submit();
    }

}
