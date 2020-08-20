package ftptest.uploadtests;

import ftptest.BaseTestClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Assertions;
import java.io.*;

public class UploadTests extends BaseTestClass {

    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][]{{"2MB.mp4"}, {"33KB.jpg"}, {"43KB.jpg"}, {"691KB.docx"}};
    }

    @Test(dataProvider = "data-provider", groups = {"upload"})
    public void uploadTest(String fileName) throws IOException {

        long startTime = System.currentTimeMillis();

        String filePath = "src\\test\\java\\ftptest\\downloadtests\\" + fileName;
        File uploadFile1 = new File(filePath);
        InputStream inputStream1 = new BufferedInputStream(new FileInputStream(uploadFile1));
        ftpClient.storeFile(filePath, inputStream1);
        inputStream1.close();
        Assertions.checkUploadSpeed(startTime, fileName);
        Assertions.checkDeleteFile(fileName, ftpClient);

    }

}
