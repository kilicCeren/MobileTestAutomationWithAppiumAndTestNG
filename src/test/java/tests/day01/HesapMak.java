package tests.day01;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class HesapMak {

    AndroidDriver<AndroidElement> driver;

    // Android driver, Android cihazlar icin uretilmis ve ona gore ozellikleri eklenmis driver
    // AppiumDriver<MobileElement> appiumDriver;
    // oncden tek bir driver vardi. o da appiumDriver di. zamanla appium kendini gelistirdi
    // android icin ayri ios icin ayri ozellikleri bulunan driverlar uretti. IOSDriver<IOSElement> iosDriver;



            // kullanici gerekli kurulumlari yapar
            // uygulamanin yuklendigini dogrular(isInstalled)
            // uygulamanin acildigini dogrular
            // 400 un 3 katininin 1200 oldugunu hesap makinasindan dogrulayalim



    @Test
    public void hesapMak()throws MalformedURLException {
        // kullanici gerekli kurulumlari yapar
        DesiredCapabilities caps =new DesiredCapabilities();
        //caps.setCapability("deviceName","Pixel 148");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel 148");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,"10.0");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        caps.setCapability(MobileCapabilityType.APP,"C:\\Users\\Ceren\\IdeaProjects\\MobileTestAutomationWithAppiumAndTestNG\\Apps\\Calculator_8.4 (503542421)_Apkpure (3).apk");


    //App capability bir uygulama eger yuklu degilse uygulamayi cihaza yuklemek icin kullanilir
    //Eger uygulama yukluyse ve tekrardan test calistirilirsa App capability uygulama yuklu mu diye kontrol eder
    //eger yuklu degilse uygulamayi yukler, eger yukluyse uygulamayi acar


        driver =new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),caps);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // uygulamanin yuklendigini dogrular(isInstalled)
        Assert.assertTrue(driver.isAppInstalled("com.google.android.calculator"));

        // uygulamanin acildigini dogrular
        Assert.assertTrue(driver.findElementById("com.google.android.calculator:id/clr").isDisplayed());

        // 400 un 3 katininin 1200 oldugunu hesap makinasindan dogrulayalim
        driver.findElementByAccessibilityId("4").click();
        driver.findElementByAccessibilityId("0").click();
        driver.findElementByAccessibilityId("0").click();
        driver.findElementByAccessibilityId("multiply").click();
        driver.findElementByAccessibilityId("3").click();

        String islemSonucu=driver.findElementById("com.google.android.calculator:id/result_preview").getText();
        int bekledigimSonuc=1200;

        Assert.assertEquals(Integer.parseInt(islemSonucu),bekledigimSonuc);


    }
}