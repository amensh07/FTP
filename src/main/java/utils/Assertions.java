package utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.testng.Assert;

public class Assertions {
    public static void checkDownloadSpeed(long startTime, String fileName) throws IOException {
        long testTime = 0;
        try {
            testTime = testTime(startTime);
            Assert.assertTrue(testTime <= goalTimeDownload(fileName));
        } catch (AssertionError e) {
            System.err.print("Download time is bigger than goal time, test time is: " + testTime);
        }

    }

    public static void checkUploadSpeed(long startTime, String fileName) throws IOException {
        long testTime = 0;
        try {
            testTime = testTime(startTime);
            Assert.assertTrue(testTime <= goalTimeUpload(fileName));
        } catch (AssertionError e) {
            System.err.print("Upload time is bigger than goal time, test time is: " + testTime);
        }

    }

    private static long goalTimeDownload(String fileName) throws IOException {
        long goalSpeed = PropertyReader.getSpeedDownload();
        long fileSize = Long.parseLong(fileName.replace("B.zip", "").replace("M", "").replace("G", "").replace("K", ""));
        return fileSize / goalSpeed;
    }

    private static long goalTimeUpload(String fileName) throws IOException {
        long goalSpeed = PropertyReader.getSpeedUpload();
        long fileSize = Long.parseLong(fileName.replace("B.mp4", "").replace("B.jpg", "").replace("B.docx", "").replace("M", "").replace("K", ""));
        return fileSize / goalSpeed;
    }

    private static long testTime(long startTime) {
        return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) - TimeUnit.MILLISECONDS.toSeconds(startTime);
    }

    public static void checkDeleteFile(String fileName, FTPClient ftpClient) throws IOException {
        String remotePath = "/upload";
        FTPFile remoteFile = ftpClient.mlistFile(remotePath);
        Assert.assertNotEquals(fileName, remoteFile.getName());
    }
}
