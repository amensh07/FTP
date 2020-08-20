package ftptest.downloadtests;

import ftptest.BaseTestClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Assertions;

import java.io.*;

public class DownloadTests extends BaseTestClass {

    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][]{{"1KB.zip"}, {"100KB.zip"}, {"10MB.zip"}, {"100MB.zip"}, {"100GB.zip"}};
    }

    @Test(dataProvider = "data-provider", groups = {"download"})
    public void downloadTest(String fileName) throws IOException {
        long startTime = System.currentTimeMillis();
        File downloadFile = new File("src\\test\\java\\ftptest\\downloadtests\\" + fileName);
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
        boolean success = ftpClient.retrieveFile(fileName, outputStream);
        if (success) {
            System.out.println(fileName + " has been downloaded successfully.");
        } else {
            System.out.print(fileName + "not able for downloading");
        }
        outputStream.close();
        Assertions.checkDownloadSpeed(startTime, fileName);

    }
}


