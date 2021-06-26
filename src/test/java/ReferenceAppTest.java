import PageObjects.HomePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import nativeapp.Base;
import org.testng.annotations.Test;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ReferenceAppTest extends Base {

@Test
public void referenceAppTest() throws IOException, InterruptedException
{

	service=startServer();
	
		AndroidDriver<AndroidElement> driver=capabilities("referenceAndroidApp");
     driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

     HomePage h=new HomePage(driver);
     service.stop();
  
     
     
     
    
     
     
	}




}


