package resources;

public class Constants {

	private static final String CHROME_PATH = System.getProperty("user.dir") + "/executables/chromedriver.exe";
	private static final String IE_PATH = System.getProperty("user.dir") + "/executables/IEDriverServer.exe";
	private static final String FIREFOX_PATH = System.getProperty("user.dir") + "/executables/geckodriver.exe";

	public static String getChromePath() {
		return CHROME_PATH;
	}

	public static String getFirefoxPath() {
		return FIREFOX_PATH;
	}

	public static String getIEPath() {
		return IE_PATH;
	}

}
