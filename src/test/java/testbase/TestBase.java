package testbase;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.zaproxy.clientapi.core.ClientApi;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import Enums.Browsers;
import Enums.OS;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.core.backend.Status;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.rcarz.jiraclient.JiraException;
import scripts.Xlsx_Reader;
import utilities.JiraUtil;
import utilities.ScreenRecorderUtil;

public class TestBase {

	//public static RemoteWebDriver driver;
	public static Properties CONFIG;
	public static Scenario executionScenario;
	public File fs;
	public static Connection connection = null;
	public static Statement statement = null;
	public static ResultSet resultSet = null;
	public static int updateQuery = 0;
	String dbTimeStamp;
	public static final String ZAP_PROXY_ADDRESS = "localhost";
	public static final int ZAP_PROXY_PORT = 8081;
	public static String ZAP_API_KEY = "13ctcr6uvopefqgjmo2sbuia7k";
	public ClientApi zapApi;
	public static int randomNumber;
	public static Xlsx_Reader testDataSheet;
	public String userName;
	public String accessKey;
	public XCUITestOptions options;
	public static Browser browser;
	public static Page page;
	public static BrowserContext context;

	public Page selectBrowser(String testApp) throws MalformedURLException, InterruptedException {
		if (System.getProperty("os.name").toLowerCase().contains(OS.WINDOW.name().toLowerCase())) {
			if (testApp.equalsIgnoreCase(Browsers.CHROME.name())) {
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int width = (int)screenSize.getWidth();
				int height = (int)screenSize.getHeight();
				
				Playwright playwright = Playwright.create();
				browser = playwright.chromium()
						.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1));
				context = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
				context.setDefaultTimeout(15_000);
				context.setDefaultNavigationTimeout(15_000);
				page = context.newPage();
				
			} else if (testApp.equalsIgnoreCase(Browsers.HEADLESS.name())) {
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int width = (int)screenSize.getWidth();
				int height = (int)screenSize.getHeight();
				
				Playwright playwright = Playwright.create();
				browser = playwright.chromium()
						.launch(new BrowserType.LaunchOptions().setHeadless(true).setSlowMo(60));
				BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
				context.setDefaultTimeout(10_000);
				context.setDefaultNavigationTimeout(10_000);
				page = context.newPage();
			} else if (testApp.equalsIgnoreCase(Browsers.FIREFOX.name())) {
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int width = (int)screenSize.getWidth();
				int height = (int)screenSize.getHeight();
				
				Playwright playwright = Playwright.create();
				browser = playwright.firefox()
						.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(60));
				BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height).setIgnoreHTTPSErrors(true));
				context.setDefaultTimeout(10_000);
				context.setDefaultNavigationTimeout(10_000);
				page = context.newPage();
			} else if (testApp.equalsIgnoreCase(Browsers.ANDROID.name())) {
				
				Playwright playwright = Playwright.create();
				browser = playwright.chromium()
						.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(60));
				 // Create a new browser context with mobile emulation settings
	            BrowserContext context = browser.newContext(new Browser.NewContextOptions()
	                    .setViewportSize(375, 812)  // Set viewport size similar to iPhone 11 (375x812)
	                    .setUserAgent(
	                            "Mozilla/5.0 (iPhone; CPU iPhone OS 14_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0 Mobile/15E148 Safari/604.1"));
	            context.setDefaultTimeout(60_000);
				context.setDefaultNavigationTimeout(60_000);
				page = context.newPage();
			} else if (testApp.equalsIgnoreCase(Browsers.ZAPTEST.name())) {
				// System.setProperty("webdriver.chrome.driver",
				// System.getProperty("user.dir") +
				// "/src/test/resources/drivers/chromedriver.exe");
				String proxyServerURL = ZAP_PROXY_ADDRESS + ":" + ZAP_PROXY_PORT;
				Proxy proxy = new Proxy();
				proxy.setHttpProxy(proxyServerURL);
				proxy.setSslProxy(proxyServerURL);

				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int width = (int)screenSize.getWidth();
				int height = (int)screenSize.getHeight();
				
				Playwright playwright = Playwright.create();
				browser = playwright.chromium()
						.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(60).setProxy(proxyServerURL));
				BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
				context.setDefaultTimeout(10_000);
				context.setDefaultNavigationTimeout(10_000);;
				page = context.newPage();

				//WebDriverManager.chromedriver().setup();
				//driver = new ChromeDriver(option);

				zapApi = new ClientApi(ZAP_PROXY_ADDRESS, ZAP_PROXY_PORT, ZAP_API_KEY);
			} else if (testApp.equalsIgnoreCase(Browsers.ACCESSIBILITY.name())) {
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int width = (int)screenSize.getWidth();
				int height = (int)screenSize.getHeight();
				
				Playwright playwright = Playwright.create();
				browser = playwright.chromium()
						.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1));
				context = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
				context.setDefaultTimeout(60_000);
				context.setDefaultNavigationTimeout(60_000);
				page = context.newPage();
			} 
		} else if (System.getProperty("os.name").toLowerCase().contains(OS.MAC.name().toLowerCase())) {
			if (testApp.equalsIgnoreCase(Browsers.CHROME.name())) {
				// System.setProperty("webdriver.chrome.driver",
				// System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver");
				ChromeOptions option = new ChromeOptions();
				option.addArguments("--remote-allow-origins=*");
				option.addArguments("allow-file-access-from-files");
				option.addArguments("use-fake-device-for-media-stream");
				option.addArguments("use-fake-ui-for-media-stream");

				WebDriverManager.chromedriver().setup();
				//driver = new ChromeDriver(option);
			} else if (testApp.equalsIgnoreCase(Browsers.FIREFOX.name())) {
				WebDriverManager.firefoxdriver().setup();
				//driver = new FirefoxDriver();
			} else if (testApp.equalsIgnoreCase(Browsers.HEADLESS.name())) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver");
				ChromeOptions options = new ChromeOptions();
				options.setHeadless(true);
				//driver = new ChromeDriver(options);
			} else if (testApp.equalsIgnoreCase(Browsers.SAFARI.name())) {
				//driver = new SafariDriver();
			} else if (testApp.equalsIgnoreCase("remote-chrome")) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--always-authorize-plugins");
				options.addArguments("enable-automation");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-extensions");
				options.addArguments("--dns-prefetch-disable");
				options.addArguments("--disable-gpu");
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
				//driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
			} else if (testApp.equalsIgnoreCase(Browsers.ANDROID.name())) {
				DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
				desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14");
				desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "RZCW90XJLND");
				desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
				// desiredCapabilities.setCapability(MobileCapabilityType.APP,
				// "/Users/mohansridhar/Downloads/VigoClinic-v2.10-QA.apk");
				// desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME,
				// "chrome");
				desiredCapabilities.setCapability(MobileCapabilityType.APP,
						System.getProperty("user.dir") + "/src/test/resources/mobileApps/app-debug.apk");
				desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
				URL url = new URL("http://127.0.0.1:4723/wd/hub");
				//driver = new AndroidDriver(url, desiredCapabilities);
				// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
				// driver.findElement(By.xpath("(//*[contains(@text,'Username')])[1]")).click();
				// String sessionId = driver.getSessionId().toString();
			} else if (testApp.equalsIgnoreCase(Browsers.BSANDROID.name())) {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("platformName", "android");
				capabilities.setCapability("platformVersion", "9.0");
				capabilities.setCapability("deviceName", "Google Pixel 3");
				capabilities.setCapability("app", "bs://e630f0415c617687b7f5962a8653d755ec71625f");
				//driver = new AndroidDriver(
				//		new URL("https://mohan_hECHmU:7h5KrohhfRTeSDKbmbif@hub.browserstack.com/wd/hub"), capabilities);
				/*
				 * DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
				 * desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,
				 * "14"); desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
				 * "RZCW90XJLND");
				 * desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,
				 * "UiAutomator2");
				 * //desiredCapabilities.setCapability(MobileCapabilityType.APP,
				 * "/Users/mohansridhar/Downloads/VigoClinic-v2.10-QA.apk");
				 * //desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME,
				 * "chrome"); desiredCapabilities.setCapability(MobileCapabilityType.APP,
				 * System.getProperty("user.dir") +
				 * "/src/test/resources/mobileApps/app-debug.apk");
				 * desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,
				 * 300); URL url = new URL("http://127.0.0.1:4723/wd/hub"); driver = new
				 * AndroidDriver(url, desiredCapabilities);
				 */
				// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
				// driver.findElement(By.xpath("(//*[contains(@text,'Username')])[1]")).click();
				// String sessionId = driver.getSessionId().toString();
			} else if (testApp.equalsIgnoreCase(Browsers.IOS.name())) {
				DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
				desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
				desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
				desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 12");
				desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.4");
				// desiredCapabilities.setCapability(MobileCapabilityType.UDID,
				// "00008101-000921342E69001E");
				// desiredCapabilities.setCapability(MobileCapabilityType.APP,
				// "/Users/mohansridhar/Downloads/VigoClinic-v2.10-QA.apk");
				// desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME,
				// "chrome");
				desiredCapabilities.setCapability(MobileCapabilityType.APP,
						System.getProperty("user.dir") + "/src/test/resources/mobileApps/OgMoney.app");
				desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
				// desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);

				URL url = new URL("http://0.0.0.0:4723/wd/hub");
				//driver = new RemoteWebDriver(url, desiredCapabilities);
				// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
				// driver.findElement(By.xpath("(//*[contains(@text,'Username')])[1]")).click();
				// String sessionId = driver.getSessionId().toString();
			} else if (testApp.equalsIgnoreCase(Browsers.BSIOS.name())) {

				options = new XCUITestOptions();
				// userName = System.getenv("BROWSERSTACK_USERNAME") != null ?
				// System.getenv("BROWSERSTACK_USERNAME") : (String)
				// browserStackYamlMap.get("userName");
				// accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY") != null ?
				// System.getenv("BROWSERSTACK_ACCESS_KEY") : (String)
				// browserStackYamlMap.get("accessKey");
				userName = "anilkumararvapal_k9p3jB";
				accessKey = "ApszZDxn2dyjPLJzDLyK";
				options.setCapability("appium:app", "bs://8ec55850bd0e7e0b3ccdea37cd014eae39f11508");
				options.setCapability("appium:deviceName", "iPhone 12");
				options.setCapability("appium:platformVersion", "14");

				//driver = new IOSDriver(
				//		new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", userName, accessKey)),
				//		options);
			} else if (testApp.equalsIgnoreCase(Browsers.ZAPTEST.name())) {
				// System.setProperty("webdriver.chrome.driver",
				// System.getProperty("user.dir") +
				// "/src/test/resources/drivers/chromedriver.exe");
				String proxyServerURL = ZAP_PROXY_ADDRESS + ":" + ZAP_PROXY_PORT;
				Proxy proxy = new Proxy();
				proxy.setHttpProxy(proxyServerURL);
				proxy.setSslProxy(proxyServerURL);

				ChromeOptions option = new ChromeOptions();
				option.addArguments("--remote-allow-origins=*");
				option.addArguments("allow-file-access-from-files");
				option.addArguments("use-fake-device-for-media-stream");
				option.addArguments("use-fake-ui-for-media-stream");
				option.setProxy(proxy);
				option.setAcceptInsecureCerts(true);

				WebDriverManager.chromedriver().setup();
				//driver = new ChromeDriver(option);

				zapApi = new ClientApi(ZAP_PROXY_ADDRESS, ZAP_PROXY_PORT, ZAP_API_KEY);
			}
		} else {
			// System.setProperty("webdriver.chrome.driver",
			// System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver");
			System.out.println("****************************HEADLESS BROWSER EXECUTION STARTED");
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--remote-allow-origins=*");
			option.setHeadless(true);
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(option);
			//System.out.println("Printer Driver=" + driver);
			System.out.println("****************************HEADLESS BROWSER EXECUTION Completed");

			// ChromeOptions options = new ChromeOptions();
			// options.setHeadless(true);
			// driver = new ChromeDriver(options);
		}

		return page;
	}

	public void currentScenario(Scenario scenario) {
		executionScenario = scenario;
	}

	public static void writeResponse(String textToWrite) {
		executionScenario.log(textToWrite);
	}
	
	public static void writeFailedResponse(String textToWrite) {
		executionScenario.log("<p style='color:red'>"+textToWrite+"</p>");
	}

	public void maximizeWindow() {
		//driver.manage().window().maximize();
	}

	public void setImplecitWait() {
		String time = CONFIG.getProperty("IMPLICIT_WAIT");
		// driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		// driver.manage().timeouts().setScriptTimeout(200000, TimeUnit.SECONDS);
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(time)));
	}

	public void setImplecitWait(String waitTime) {
		String time = CONFIG.getProperty(waitTime);
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(time)));
	}

	public int globalMobileCustomWaitTime() {
		return Integer.valueOf(CONFIG.getProperty("MOBILE_CUSTOM_WAIT"));
	}

	public int globalWebExplecitWaitTime() {
		return Integer.valueOf(CONFIG.getProperty("WEB_CUSTOM_WAIT"));
	}

	public int globalImplicitWaitTime() {
		return Integer.valueOf(CONFIG.getProperty("IMPLICIT_WAIT"));
	}

	public void captureScreenShot(Scenario scenario) {
		//JavascriptExecutor jse = (JavascriptExecutor) driver;
		if (scenario.isFailed()) {
			try {
				System.out.println("Capture Screenshot");
				byte[] screenshot = page.screenshot(new Page.ScreenshotOptions()
						  .setPath(Paths.get("screenshot.png"))
						  .setFullPage(true));
				//byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				String testName = scenario.getName();
				scenario.attach(screenshot, "image/png", testName);
			} catch (ClassCastException cce) {
				cce.printStackTrace();
			}
		} else if (System.getProperty("mobile") != null) {
			if (System.getProperty("mobile").equalsIgnoreCase(Browsers.BSANDROID.name())
					|| System.getProperty("mobile").equalsIgnoreCase(Browsers.BSIOS.name())) {
				//jse.executeScript(
				//		"browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");
			}
		}
	}

	public void loadConfigReader(String environment) throws IOException, ConfigurationException {
		// Expecting environment same as config file name. Ex: dev, demo, release
		fs = new File(
				System.getProperty("user.dir") + "/src/test/resources/config/" + environment + "_config.properties");
		CONFIG = new Properties();
		CONFIG.load(new FileReader(fs));
	}

	public void startScenarioVideoRecording(String scenarioName) throws Exception {
		ScreenRecorderUtil.startRecord(scenarioName);
	}

	public void stopScenarioVideoRecording() throws Exception {
		ScreenRecorderUtil.stopRecord();
	}

	public Connection getDBConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = CONFIG.getProperty("db_url");
		String user = CONFIG.getProperty("db_username");
		String password = CONFIG.getProperty("db_password");
		connection = DriverManager.getConnection(url, user, password);
		return connection;
	}

	public void closeDBConnection() throws SQLException {
		if (resultSet != null) {
			resultSet.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (connection != null) {
			connection.close();
		}
	}

	public String getDBTimeStamp() throws SQLException {
		dbTimeStamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
		return dbTimeStamp;
	}

	public static int generateRandomNumber() {
		Random rand = new Random();
		int max = 199999, min = 100000;
		return rand.nextInt(max - min + 1) + min;
	}

	public static void setProperties(String key, String value) throws IOException {
		File appConfig = new File(
				System.getProperty("user.dir") + "/src/test/resources/config/dynamic_config.properties");
		FileInputStream propsInput = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/resources/config/dynamic_config.properties");
		Properties props = new Properties();
		props.load(propsInput);
		try (FileWriter inputStream = new FileWriter(appConfig)) {
			// Setting the properties.
			props.setProperty(key, value);
			// Storing the properties in the file with a heading comment.
			props.store(inputStream, "INFORMATION!!!");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static String setAvailabilityForAllRows() {
		Date date = new Date();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		System.out.println("currentDate=" + currentDate);
		System.out.println("testDataSheet.getRowCount(\"Appointments\")=" + testDataSheet.getRowCount("Appointments"));
		for (int i = 2; i <= testDataSheet.getRowCount("Appointments"); i++) {
			String actDate = testDataSheet.getCellData("Appointments", "Date", i);
			System.out.println("actDate=" + actDate);
			if (currentDate.equals(actDate)) {
				System.out.println("Do Nothing");
			} else {
				testDataSheet.setCellData("Appointments", "Date", i, currentDate);
				testDataSheet.setCellData("Appointments", "Status", i, "Available");
			}
		}
		return "Done";
	}

	public static HashMap<String, String> getAppointmentDetailsByStatus(String appointmentStatus) {
		HashMap<String, String> map = null;
		for (int i = 2; i <= testDataSheet.getRowCount("Appointments"); i++) {
			String status = testDataSheet.getCellData("Appointments", "Status", i);
			if (status.equalsIgnoreCase(appointmentStatus)) {
				map = new HashMap<String, String>();// Creating HashMap
				String mrn = testDataSheet.getCellData("Appointments", "MRN", i);
				String doctorName = testDataSheet.getCellData("Appointments", "Doctor", i);
				String Date = testDataSheet.getCellData("Appointments", "Date", i);
				String Time = testDataSheet.getCellData("Appointments", "Time", i);
				String SlotTime = testDataSheet.getCellData("Appointments", "SlotTime", i);
				String Status = testDataSheet.getCellData("Appointments", "Status", i);
				String ScheduleTime = testDataSheet.getCellData("Appointments", "ScheduleTime", i);
				String PatientName = testDataSheet.getCellData("Appointments", "PatientName", i);
				System.out.println("doctorName=" + doctorName);
				map.put("MRN", mrn);
				map.put("Doctor", doctorName);
				map.put("Date", Date);
				map.put("Time", Time);
				map.put("SlotTime", SlotTime);
				map.put("Status", Status);
				map.put("rowCount", Integer.toString(i));
				map.put("ScheduleTime", ScheduleTime);
				map.put("PatientName", PatientName);
				System.out.println("rowID=" + Integer.toString(i));
				// testDataSheet.setCellData("Appointments", "Status", i, "Blocked");
				// System.out.println("map"+map);
				return map;
			}
		}
		return map;
	}

	public static void setSlotTimeStatus(String rowCount, String status) {
		System.out.println("Sheet Name=" + "Appointments");
		System.out.println("currentRow" + Integer.valueOf(rowCount));
		System.out.println("status=" + status);
		testDataSheet.setCellData("Appointments", "Status", Integer.valueOf(rowCount), status);
	}

	public void createIssueInJira(Scenario scenario) throws JiraException {
		if (scenario.isFailed()) {
			try {
				//File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				//File screen = page.screenshot();
				byte[] screen = page.screenshot(new Page.ScreenshotOptions()
						  .setPath(Paths.get("screenshot.png"))
						  .setFullPage(true));
				
				File screenshot = new File("screenshot.png");
				String issueSummary = "Failed to add " + scenario.getName();
				String Description = "Description " + scenario.getName();
				String createIssueInJira = CONFIG.getProperty("Create_Issue_In_Jira");
				System.out.println("createIssueInJira=" + createIssueInJira);
				String JiraUrl = CONFIG.getProperty("JIRA_URL");
				String JiraEmail = CONFIG.getProperty("JIRA_EMAIL");
				String JiraToken = CONFIG.getProperty("JIRA_TOKEN");
				if (createIssueInJira.equalsIgnoreCase("YES")) {
					JiraUtil jiraUtil = new JiraUtil(JiraUrl, JiraEmail, JiraToken, "CUC");
					// Issue.SearchResult srs = jiraUtil.searchJiraTicket("summary ~"+issueSummary);
					// System.out.println("srs Total=="+srs.total);
					// jiraUtil.createJiraTicket("Bug", issueSummary, Description);
					jiraUtil.createJiraTicket("Bug", issueSummary, Description, screenshot);
				}

			} catch (ClassCastException cce) {
				cce.printStackTrace();
			}
		}
	}

}
