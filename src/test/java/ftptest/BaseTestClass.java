package ftptest;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utils.PropertyReader;

import java.io.IOException;
import java.util.Arrays;

public class BaseTestClass {

    public FTPClient ftpClient;
    private boolean isConnected = false;

    @BeforeTest
    public void setUp() throws IOException {
        ftpClient = new FTPClient();
        ftpClient.connect(PropertyReader.getServerUrl());
        ftpClient.login("anonymous", "anonymous");
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        System.out.print(Arrays.toString(ftpClient.listFiles()));
        isConnected = true;
    }

    @AfterTest
    public void tearDown() throws IOException {
        if(isConnected) {
            ftpClient.disconnect();
        }
    }
}
