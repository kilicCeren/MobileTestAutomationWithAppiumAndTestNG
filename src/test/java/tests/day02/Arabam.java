package tests.day02;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Arabam {
    AndroidDriver<AndroidElement> driver;

    @BeforeTest
    public void SetUp() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 148");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability("appPackage","com.dogan.arabam");
        caps.setCapability("appActivity","com.dogan.arabam.presentation.feature.home.HomeActivity");


        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void Test1() throws InterruptedException {
        // uygulamanin basarili bir sekilde yuklendigi dogrulanir
        Assert.assertTrue(driver.isAppInstalled("com.dogan.arabam"));

        // uygulamanin basarili bir sekilde acildigi dogrulanir
        Assert.assertTrue(driver.findElementById("com.dogan.arabam:id/ivArabamLogo").isDisplayed());

        // alt menuden ilan ara butonuna tiklanir
        driver.findElementByXPath("//*[@text='İlan Ara']").click();

        // kategori olarak otomobil secilir
        Thread.sleep(2500);
        driver.findElementByXPath("//*[@text='Otomobil']").click();

        // arac olarak Volkswagen secilir
        Thread.sleep(3500);
        TouchAction action=new TouchAction<>(driver);
        action.press(PointOption.point(500,1831)).
                waitAction(WaitOptions.waitOptions(Duration.ofMillis(70))).
                moveTo(PointOption.point(500,409)).release().perform();
        // baslangic noktasiyla bitis noktasi arasindaki gecen sure (wait action)
        // eger sure azalirsa; gidilen yol mesafesi ARTAR. eger sureyi arttirirsan; gidilen yol mesafesi AZALIR !!
        // yani tamamen bir ters oranti vardir. ekranda daha fazla asagi ya da yukari gitmek istiyorsak birim zamanda sureyi azaltmaliyiz

        driver.findElementByXPath("//*[@text='Volkswagen']").click();

        // arac modeli olarak passat secilir
        Thread.sleep(2500);
        driver.findElementByXPath("//*[@text='Passat']").click();

        // 1.4 TSI BlueMotion secilir
        Thread.sleep(2500);
        driver.findElementByXPath("//*[@text='1.4 TSi BlueMotion']").click();

        // Paket secimi comfortline yapilir
        Thread.sleep(2500);
        driver.findElementByXPath("//*[@text='Comfortline']").click();

        // Ucuzdan pahaliya siralama yaparak filtreleme yapilir
        Thread.sleep(2500);
        action.press(PointOption.point(400,400)).release().perform();
        driver.findElementByXPath("//*[@text='Fiyat - Ucuzdan Pahalıya']").click();

        // Gelen en ucuz aracin 500.000 tl den buyuk oldugu dogrulanir

        String enucuzFiyat=driver.findElementByXPath("(//*[@resource-id='com.dogan.arabam:id/tvPrice'])[1]").getText();
        enucuzFiyat=enucuzFiyat.replaceAll("\\D","");
        Assert.assertTrue(Integer.parseInt(enucuzFiyat)>500000);
    }

    @Test
    public void Test2() throws InterruptedException {

        // alt menuden ilan ara butonuna tiklanir
        driver.findElementByXPath("//*[@text='İlan Ara']").click();

        // kategori olarak Kiralık Araçlar secilir
        Thread.sleep(2500);
        driver.findElementByXPath("//*[@text='Kiralık Araçlar']").click();

        // arac olarak Minivan&Panelvan secilir
        Thread.sleep(2500);
        driver.findElementByXPath("//*[@text='Minivan & Panelvan']").click();

        // arac markasi olarak peugeot secilir
        Thread.sleep(2500);
        driver.findElementByXPath("//*[@text='Peugeot']").click();
        //driver.findElementByXPath("(//*[@class='android.widget.TextView'])[16]").click();


        // Bipper secilir
        Thread.sleep(2500);
        driver.findElementByXPath("//*[@text='Bipper']").click();
        // driver.findElementById("com.dogan.arabam:id/textViewBrowseCategory").click();

        // listenin geldigi dogrulanir

        driver.findElementByXPath("(//*[@class='android.widget.TextView'])[9]").isDisplayed();
/*
    String ilanSayisi = driver.findElementById("com.dogan.arabam:id/texViewSubtitle").getText();
    ilanSayisi = ilanSayisi.replaceAll("\\D","");

    Assert.assertTrue(Integer.parseInt(ilanSayisi) > 0);

 */
    }
}