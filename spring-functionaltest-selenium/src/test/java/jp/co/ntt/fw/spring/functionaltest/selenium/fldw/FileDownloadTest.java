/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.selenium.fldw;

import static org.hamcrest.Matchers.isOneOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

public class FileDownloadTest extends FunctionTestSupport {

    private static Path downloadTempDirectory;

    private static WebDriver downloadDriver;

    @Value("${selenium.app.download.pdf}")
    private String downloadPdfFileName;

    @Value("${selenium.app.download.xlsx}")
    private String downloadXlsFileName;

    @Value("${selenium.app.download.csv}")
    private String downloadCsvFileName;

    @Value("${selenium.fldw.waitForDownload.offsetSeconds:0}")
    private int offsetSecondsOfWaitForDownload;

    public FileDownloadTest() {
        disableDefaultWebDriver();
    }

    @BeforeClass
    public static void setUpClass() throws IOException {
        downloadTempDirectory = Files.createTempDirectory("springtest-fldw-")
                .toAbsolutePath();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        deleteFilesRecursively(downloadTempDirectory);
    }

    private static void deleteFilesRecursively(Path dir) throws Exception {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    deleteFilesRecursively(entry);
                } else {
                    Files.deleteIfExists(entry);
                }
            }
            Files.deleteIfExists(dir);
        }
    }

    @Before
    public void setUp() throws Exception {
        if (downloadDriver == null) {
            downloadDriver = webDriverCreator
                    .createDownloadableWebDriver(downloadTempDirectory.toFile()
                            .getAbsolutePath());
        }
        setCurrentWebDriver(downloadDriver);
    }

    /**
     * <ul>
     * <li>AbstractPdfViewクラスを利用した場合に、PDFファイルがダウンロードできることを確認
     * </ul>
     */
    @Test
    public void testFLDW0101001() throws Exception {

        // メニュー画面の操作
        webDriverOperations.click(By.id("fldw0101001"));

        // 画面で情報入力
        webDriverOperations.overrideText(By.id("name"), "山田");
        webDriverOperations.overrideText(By.id("address"), "東京");
        webDriverOperations.overrideText(By.id("birthdate"), "19700101");

        // PDFファイルダウンロード
        webDriverOperations.click(By.id("downloadButton"));
        waitForDownloaded();

        // テストデータで用意されたPDFを読み込む
        ClassPathResource expectedPdf = new ClassPathResource("/testdata/fldw/日本語ファイル名iTextAsian1_0.pdf");
        ClassPathResource expectedPdfJava1_8 = new ClassPathResource("/testdata/fldw/日本語ファイル名iTextAsian1_0_jdk1_8.pdf");

        // ダウンロードされたファイルを読み込む
        File file = new File(downloadTempDirectory.toString(), downloadPdfFileName);
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        // ファイルダウンロードの確認
        String actual = readPdfAsStringWithoutRoot(inputStream);
        String expected = readPdfAsStringWithoutRoot(expectedPdf
                .getInputStream());
        String expectedJava1_8 = readPdfAsStringWithoutRoot(expectedPdfJava1_8
                .getInputStream());

        assertThat(actual, isOneOf(expected, expectedJava1_8));

        // ダウンロードファイルを削除
        file.delete();

        // ログの確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertNotContainsWarnAndError(webDriverOperations
                .getXTrack());

    }

    String readPdfAsStringWithoutRoot(InputStream stream) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            for (String line = reader.readLine(); line != null; line = reader
                    .readLine()) {
                if (!line.startsWith("<</Root") && !line.startsWith("<</Info")) {
                    builder.append(line);
                } else {
                    builder.append("xxxxxxxxxx");
                }
                builder.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * <ul>
     * <li>AbstractExcelViewクラスを利用した場合に、Excelファイルがダウンロードできることを確認
     * </ul>
     */
    @Test
    // @Ignore("環境が変わるとファイルの中身が微妙に異なる。現在、assert方法を調査中")
    public void testFLDW0201001() throws Exception {
        // メニュー画面の操作
        webDriverOperations.click(By.id("fldw0201001"));

        // 画面で情報入力
        webDriverOperations.overrideText(By.id("name"), "山田");
        webDriverOperations.overrideText(By.id("address"), "東京");
        webDriverOperations.overrideText(By.id("birthdate"), "19700101");

        // Excelファイルダウンロード
        webDriverOperations.click(By.id("downloadButton"));
        waitForDownloaded();

        // テストデータで用意されたPDFを読み込む
        ClassPathResource expectedExcel = new ClassPathResource("/testdata/fldw/日本語ファイル名.xlsx");

        // ダウンロードされたファイルを読み込む中身の検証
        File file = new File(downloadTempDirectory.toString(), downloadXlsFileName);

        // xlsxファイルを解凍してバイナリで比較
        compareFile(file, expectedExcel.getFile());

        // ダウンロードファイルの削除
        file.delete();

        // ログの確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertNotContainsWarnAndError(webDriverOperations
                .getXTrack());
    }

    /**
     * <ul>
     * <li>JasperReportsを利用した場合に、CSVファイルがダウンロードできることを確認
     * </ul>
     */
    @Test
    public void testFLDW0301001() throws Exception {
        // メニュー画面の操作
        webDriverOperations.click(By.id("fldw0301001"));

        // 画面で情報入力
        webDriverOperations.overrideText(By.id("name"), "山田");
        webDriverOperations.overrideText(By.id("address"), "東京");
        webDriverOperations.overrideText(By.id("birthdate"), "19700101");

        // CSVファイルダウンロード
        webDriverOperations.click(By.id("downloadButton"));
        waitForDownloaded();

        // テストデータで用意されたPDFを読み込む
        ClassPathResource expectedCsv = new ClassPathResource("/testdata/fldw/日本語ファイル名.csv");

        // ダウンロードされたファイルを読み込む
        File file = new File(downloadTempDirectory.toString(), downloadCsvFileName);
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        // ファイルダウンロードの確認
        String actual = readFileAsString(inputStream);
        String expected = readFileAsString(expectedCsv.getInputStream());
        assertThat(actual, is(expected));

        // ダウンロードファイルの削除
        file.delete();

        // ログの確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertNotContainsWarnAndError(webDriverOperations
                .getXTrack());
    }

    String readFileAsString(InputStream stream) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            for (String line = reader.readLine(); line != null; line = reader
                    .readLine()) {
                builder.append(line);
                builder.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private void waitForDownloaded() {
        try {
            TimeUnit.SECONDS.sleep(1 + offsetSecondsOfWaitForDownload);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void compareFile(File actualFile, File expectedFile) throws IOException {

        try (ZipFile zipActual = new ZipFile(actualFile, Charset
                .forName("UTF-8"));
                ZipFile zipExpect = new ZipFile(expectedFile, Charset
                        .forName("UTF-8"));) {

            // ファイルサイズ取得
            int fileSize = getTestDataFileSize(expectedFile);

            // ZIPファイルの中身を比較
            byte[] actual = new byte[fileSize];
            for (Enumeration<? extends ZipEntry> zipEntries = zipActual
                    .entries(); zipEntries.hasMoreElements();) {
                ZipEntry entry = zipEntries.nextElement();
                if (entry.getName().equals("docProps/core.xml")) {
                    // バイナリ値が異なるため比較対象外
                    continue;
                }
                InputStream is = zipActual.getInputStream(entry);
                while (true) {
                    int len = is.read(actual);
                    if (len < 0)
                        break;
                }
            }

            byte[] expected = new byte[fileSize];
            for (Enumeration<? extends ZipEntry> zipEntries = zipExpect
                    .entries(); zipEntries.hasMoreElements();) {
                ZipEntry entry = zipEntries.nextElement();
                if (entry.getName().equals("docProps/core.xml")) {
                    // バイナリ値が異なるため比較対象外
                    continue;
                }
                InputStream is = zipActual.getInputStream(entry);
                while (true) {
                    int len = is.read(expected);
                    if (len < 0)
                        break;
                }
            }
            assertThat(fileSize, is(not(0)));
            assertThat(actual, is(expected));
        }
    }

    private int getTestDataFileSize(File file) throws IOException {

        long size = 0;
        try (ZipFile zipFile = new ZipFile(file, Charset.forName("UTF-8"));) {

            for (Enumeration<? extends ZipEntry> zipEntries = zipFile.entries(); zipEntries
                    .hasMoreElements();) {
                ZipEntry entry = zipEntries.nextElement();
                if (entry.getName().equals("docProps/core.xml")) {
                    // バイナリが異なるファイルはサイズ計算対象外
                    continue;
                }
                size += entry.getSize();
            }
        }
        return (int) size;
    }
}
