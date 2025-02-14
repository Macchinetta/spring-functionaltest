/*
 * Copyright(c) 2025 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.selenium.sydt;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.Test;
import org.openqa.selenium.By;
import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class SystemDate_JSP_Test extends FunctionTestSupport {

    private static final String VIEW_TYPE = "jsp";

    /**
     * <ul>
     * <li>システム・デフォルトClockの取得</li>
     * </ul>
     */
    @Test
    public void testSYDT0101001() {

        webDriverOperations.click(id("sydt0101001_" + VIEW_TYPE));

        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Clock Display"));

        ClockDisplay display = createClockDisplay();

        // fixedの確認
        assertThat(display.getFixedLocalDateTime1_1(), is(display.getFixedLocalDateTime1_2()));
        assertThat(display.getFixedLocalDateTime1_2(), is(display.getFixedLocalDateTime1_3()));
        assertThat(display.getFixedLocalDateTime2_1(), is(display.getFixedLocalDateTime2_2()));
        assertThat(display.getFixedLocalDateTime2_2(), is(display.getFixedLocalDateTime2_3()));

        // tickの確認
        assertTrue("not tick",
                display.getTickLocalDateTime1_1().isBefore(display.getTickLocalDateTime1_2()));
        assertTrue("not tick",
                display.getTickLocalDateTime1_2().isBefore(display.getTickLocalDateTime1_3()));
        assertTrue("not tick",
                display.getTickLocalDateTime2_1().isBefore(display.getTickLocalDateTime2_2()));
        assertTrue("not tick",
                display.getTickLocalDateTime2_2().isBefore(display.getTickLocalDateTime2_3()));

        // タイムゾーンの確認("Asia/Tokyo" - UTC 9時間差)
        Duration duration = Duration.between(display.getFixedLocalDateTime2_1(),
                display.getTickLocalDateTime2_1());
        assertThat(duration.toHours(), is(-9L));
    }

    /**
     * <ul>
     * <li>指定した固定日時から生成したClockを取得</li>
     * </ul>
     */
    @Test
    public void testSYDT0102001() {

        webDriverOperations.click(id("sydt0102001_" + VIEW_TYPE));

        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Clock Display"));

        ClockDisplay display = createClockDisplay();

        // fixedの確認
        assertThat(display.getFixedLocalDateTime1_1(), is(display.getFixedLocalDateTime1_2()));
        assertThat(display.getFixedLocalDateTime1_2(), is(display.getFixedLocalDateTime1_3()));
        assertThat(display.getFixedLocalDateTime2_1(), is(display.getFixedLocalDateTime2_2()));
        assertThat(display.getFixedLocalDateTime2_2(), is(display.getFixedLocalDateTime2_3()));

        // tickの確認
        assertTrue("not tick",
                display.getTickLocalDateTime1_1().isBefore(display.getTickLocalDateTime1_2()));
        assertTrue("not tick",
                display.getTickLocalDateTime1_2().isBefore(display.getTickLocalDateTime1_3()));
        assertTrue("not tick",
                display.getTickLocalDateTime2_1().isBefore(display.getTickLocalDateTime2_2()));
        assertTrue("not tick",
                display.getTickLocalDateTime2_2().isBefore(display.getTickLocalDateTime2_3()));

        // タイムゾーンの確認(固定値で時間が設定されるため、同じ時間が表示される)
        Duration duration = Duration.between(display.getFixedLocalDateTime2_1(),
                display.getTickLocalDateTime2_1());
        assertThat(duration.toHours(), is(0L));

        // ベースの確認(固定値：2012-09-11T02:25:15)
        assertThat(
                display.getFixedLocalDateTime1_1()
                        .format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")),
                is("2012/09/11 02:25:15"));
    }

    /**
     * <ul>
     * <li>指定した固定日時から生成したClockを取得</li>
     * </ul>
     */
    @Test
    public void testSYDT0102002() {

        webDriverOperations.click(id("sydt0102002_" + VIEW_TYPE));

        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Clock Display"));

        ClockDisplay display = createClockDisplay();

        // fixedの確認
        assertThat(display.getFixedLocalDateTime1_1(), is(display.getFixedLocalDateTime1_2()));
        assertThat(display.getFixedLocalDateTime1_2(), is(display.getFixedLocalDateTime1_3()));
        assertThat(display.getFixedLocalDateTime2_1(), is(display.getFixedLocalDateTime2_2()));
        assertThat(display.getFixedLocalDateTime2_2(), is(display.getFixedLocalDateTime2_3()));

        // tickの確認
        assertTrue("not tick",
                display.getTickLocalDateTime1_1().isBefore(display.getTickLocalDateTime1_2()));
        assertTrue("not tick",
                display.getTickLocalDateTime1_2().isBefore(display.getTickLocalDateTime1_3()));
        assertTrue("not tick",
                display.getTickLocalDateTime2_1().isBefore(display.getTickLocalDateTime2_2()));
        assertTrue("not tick",
                display.getTickLocalDateTime2_2().isBefore(display.getTickLocalDateTime2_3()));

        // タイムゾーンの確認(固定値で時間が設定されるため、同じ時間が表示される)
        Duration duration = Duration.between(display.getFixedLocalDateTime2_1(),
                display.getTickLocalDateTime2_1());
        assertThat(duration.toHours(), is(0L));

        // ベースの確認(固定値：2012-09-11T02:25:15)
        assertThat(
                display.getFixedLocalDateTime1_1()
                        .format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")),
                is("2012/09/11 02:25:15"));
    }

    /**
     * <ul>
     * <li>指定した固定日時から生成したClockを取得</li>
     * </ul>
     */
    @Test
    public void testSYDT0102003() {

        webDriverOperations.click(id("sydt0102003_" + VIEW_TYPE));

        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Clock Display"));

        ClockDisplay display = createClockDisplay();

        // fixedの確認
        assertThat(display.getFixedLocalDateTime1_1(), is(display.getFixedLocalDateTime1_2()));
        assertThat(display.getFixedLocalDateTime1_2(), is(display.getFixedLocalDateTime1_3()));
        assertThat(display.getFixedLocalDateTime2_1(), is(display.getFixedLocalDateTime2_2()));
        assertThat(display.getFixedLocalDateTime2_2(), is(display.getFixedLocalDateTime2_3()));

        // tickの確認
        assertTrue("not tick",
                display.getTickLocalDateTime1_1().isBefore(display.getTickLocalDateTime1_2()));
        assertTrue("not tick",
                display.getTickLocalDateTime1_2().isBefore(display.getTickLocalDateTime1_3()));
        assertTrue("not tick",
                display.getTickLocalDateTime2_1().isBefore(display.getTickLocalDateTime2_2()));
        assertTrue("not tick",
                display.getTickLocalDateTime2_2().isBefore(display.getTickLocalDateTime2_3()));

        // タイムゾーンの確認(固定値で時間が設定されるため、同じ時間が表示される)
        Duration duration = Duration.between(display.getFixedLocalDateTime2_1(),
                display.getTickLocalDateTime2_1());
        assertThat(duration.toHours(), is(0L));

        // ベースの確認(固定値：2012-09-11T02:25:15)
        assertThat(
                display.getFixedLocalDateTime1_1()
                        .format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")),
                is("2012/09/11 02:25:15"));
    }

    /**
     * <ul>
     * <li>システム・デフォルト+差分Clockの取得</li>
     * </ul>
     */
    @Test
    public void testSYDT0103001() {

        webDriverOperations.click(id("sydt0103001_" + VIEW_TYPE));

        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Clock Display"));

        ClockDisplay display = createClockDisplay();

        // fixedの確認
        assertThat(display.getFixedLocalDateTime1_1(), is(display.getFixedLocalDateTime1_2()));
        assertThat(display.getFixedLocalDateTime1_2(), is(display.getFixedLocalDateTime1_3()));
        assertThat(display.getFixedLocalDateTime2_1(), is(display.getFixedLocalDateTime2_2()));
        assertThat(display.getFixedLocalDateTime2_2(), is(display.getFixedLocalDateTime2_3()));

        // tickの確認
        assertTrue("not tick",
                display.getTickLocalDateTime1_1().isBefore(display.getTickLocalDateTime1_2()));
        assertTrue("not tick",
                display.getTickLocalDateTime1_2().isBefore(display.getTickLocalDateTime1_3()));
        assertTrue("not tick",
                display.getTickLocalDateTime2_1().isBefore(display.getTickLocalDateTime2_2()));
        assertTrue("not tick",
                display.getTickLocalDateTime2_2().isBefore(display.getTickLocalDateTime2_3()));

        // タイムゾーンの確認("Asia/Tokyo" - UTC 9時間差)
        Duration duration1 = Duration.between(display.getFixedLocalDateTime2_1(),
                display.getTickLocalDateTime2_1());
        assertThat(duration1.toHours(), is(-9L));

        // ベースの確認(システム・デフォルト + 1日)
        Duration duration2 =
                Duration.between(display.getBaseDateTime(), display.getFixedLocalDateTime1_1());
        assertThat(duration2.toDays(), is(1L));
    }

    /**
     * <ul>
     * <li>システム・デフォルト+差分Clockの取得</li>
     * </ul>
     */
    @Test
    public void testSYDT0103002() {

        webDriverOperations.click(id("sydt0103002_" + VIEW_TYPE));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(".*ThrowableServiceImple",
                "UnsupportedTemporalTypeException:Unit must not have an estimated duration");
    }

    /**
     * <ul>
     * <li>DBに登録した固定の時刻から生成したClockを取得</li>
     * </ul>
     */
    @Test
    public void testSYDT0104001() {

        webDriverOperations.click(id("sydt0104001_" + VIEW_TYPE));

        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Clock Display"));

        ClockDisplay display = createClockDisplay();

        // fixedの確認
        assertThat(display.getFixedLocalDateTime1_1(), is(display.getFixedLocalDateTime1_2()));
        assertThat(display.getFixedLocalDateTime1_2(), is(display.getFixedLocalDateTime1_3()));
        assertThat(display.getFixedLocalDateTime2_1(), is(display.getFixedLocalDateTime2_2()));
        assertThat(display.getFixedLocalDateTime2_2(), is(display.getFixedLocalDateTime2_3()));

        // tickの確認
        assertTrue("not tick",
                display.getTickLocalDateTime1_1().isBefore(display.getTickLocalDateTime1_2()));
        assertTrue("not tick",
                display.getTickLocalDateTime1_2().isBefore(display.getTickLocalDateTime1_3()));
        assertTrue("not tick",
                display.getTickLocalDateTime2_1().isBefore(display.getTickLocalDateTime2_2()));
        assertTrue("not tick",
                display.getTickLocalDateTime2_2().isBefore(display.getTickLocalDateTime2_3()));

        // タイムゾーンの確認(固定値で時間が設定されるため、同じ時間が表示される)
        Duration duration = Duration.between(display.getFixedLocalDateTime2_1(),
                display.getTickLocalDateTime2_1());
        assertThat(duration.toHours(), is(0L));

        // ベースの確認(DB登録値：2013/12/09 13:50:12.100)
        assertThat(
                display.getFixedLocalDateTime1_1()
                        .format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")),
                is("2013/12/09 13:50:12"));
    }

    /**
     * <ul>
     * <li>システム・デフォルト+DB差分から生成したClockを取得</li>
     * </ul>
     */
    @Test
    public void testSYDT0105001() {

        webDriverOperations.click(id("sydt0105001_" + VIEW_TYPE));

        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Clock Display"));

        ClockDisplay display = createClockDisplay();

        // fixedの確認
        assertThat(display.getFixedLocalDateTime1_1(), is(display.getFixedLocalDateTime1_2()));
        assertThat(display.getFixedLocalDateTime1_2(), is(display.getFixedLocalDateTime1_3()));
        assertThat(display.getFixedLocalDateTime2_1(), is(display.getFixedLocalDateTime2_2()));
        assertThat(display.getFixedLocalDateTime2_2(), is(display.getFixedLocalDateTime2_3()));

        // tickの確認
        assertTrue("not tick",
                display.getTickLocalDateTime1_1().isBefore(display.getTickLocalDateTime1_2()));
        assertTrue("not tick",
                display.getTickLocalDateTime1_2().isBefore(display.getTickLocalDateTime1_3()));
        assertTrue("not tick",
                display.getTickLocalDateTime2_1().isBefore(display.getTickLocalDateTime2_2()));
        assertTrue("not tick",
                display.getTickLocalDateTime2_2().isBefore(display.getTickLocalDateTime2_3()));

        // タイムゾーンの確認("Asia/Tokyo" - UTC 9時間差)
        Duration duration1 = Duration.between(display.getFixedLocalDateTime2_1(),
                display.getTickLocalDateTime2_1());
        assertThat(duration1.toHours(), is(-9L));

        // ベースの確認(システム・デフォルト + DB差分(-3600s))
        Duration duration2 =
                Duration.between(display.getBaseDateTime(), display.getFixedLocalDateTime1_1());
        assertThat(duration2.toHours(), is(-1L));
    }

    /**
     * <ul>
     * <li>システム・デフォルト+DB差分から生成したClockを取得</li>
     * </ul>
     */
    @Test
    public void testSYDT0105002() {

        webDriverOperations.click(id("sydt0105002_" + VIEW_TYPE));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(".*ThrowableServiceImple",
                "UnsupportedTemporalTypeException:Unit must not have an estimated duration");
    }

    private ClockDisplay createClockDisplay() {

        ClockDisplay display = new ClockDisplay();

        display.setBaseDateTime(paseLocalDateTime(webDriverOperations.getText(By.id("base"))));
        display.setDatasourceDateTime(
                paseLocalDateTime(webDriverOperations.getText(By.id("database"))));

        display.setFixedLocalDateTime1_1(
                paseLocalDateTime(webDriverOperations.getText(By.id("fixedLocalDateTime1_1"))));
        display.setFixedLocalDateTime2_1(
                paseLocalDateTime(webDriverOperations.getText(By.id("fixedLocalDateTime2_1"))));
        display.setTickLocalDateTime1_1(
                paseLocalDateTime(webDriverOperations.getText(By.id("tickLocalDateTime1_1"))));
        display.setTickLocalDateTime2_1(
                paseLocalDateTime(webDriverOperations.getText(By.id("tickLocalDateTime2_1"))));

        display.setFixedLocalDateTime1_2(
                paseLocalDateTime(webDriverOperations.getText(By.id("fixedLocalDateTime1_2"))));
        display.setFixedLocalDateTime2_2(
                paseLocalDateTime(webDriverOperations.getText(By.id("fixedLocalDateTime2_2"))));
        display.setTickLocalDateTime1_2(
                paseLocalDateTime(webDriverOperations.getText(By.id("tickLocalDateTime1_2"))));
        display.setTickLocalDateTime2_2(
                paseLocalDateTime(webDriverOperations.getText(By.id("tickLocalDateTime2_2"))));

        display.setFixedLocalDateTime1_3(
                paseLocalDateTime(webDriverOperations.getText(By.id("fixedLocalDateTime1_3"))));
        display.setFixedLocalDateTime2_3(
                paseLocalDateTime(webDriverOperations.getText(By.id("fixedLocalDateTime2_3"))));
        display.setTickLocalDateTime1_3(
                paseLocalDateTime(webDriverOperations.getText(By.id("tickLocalDateTime1_3"))));
        display.setTickLocalDateTime2_3(
                paseLocalDateTime(webDriverOperations.getText(By.id("tickLocalDateTime2_3"))));

        return display;
    }

    private LocalDateTime paseLocalDateTime(String strDateTime) {
        return LocalDateTime.parse(strDateTime,
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS"));
    }

    class ClockDisplay {

        private LocalDateTime baseDateTime;

        private LocalDateTime datasourceDateTime;

        private LocalDateTime fixedLocalDateTime1_1;

        private LocalDateTime fixedLocalDateTime2_1;

        private LocalDateTime tickLocalDateTime1_1;

        private LocalDateTime tickLocalDateTime2_1;

        private LocalDateTime fixedLocalDateTime1_2;

        private LocalDateTime fixedLocalDateTime2_2;

        private LocalDateTime tickLocalDateTime1_2;

        private LocalDateTime tickLocalDateTime2_2;

        private LocalDateTime fixedLocalDateTime1_3;

        private LocalDateTime fixedLocalDateTime2_3;

        private LocalDateTime tickLocalDateTime1_3;

        private LocalDateTime tickLocalDateTime2_3;

        public LocalDateTime getBaseDateTime() {
            return baseDateTime;
        }

        public void setBaseDateTime(LocalDateTime baseDateTime) {
            this.baseDateTime = baseDateTime;
        }

        public LocalDateTime getDatasourceDateTime() {
            return datasourceDateTime;
        }

        public void setDatasourceDateTime(LocalDateTime datasourceDateTime) {
            this.datasourceDateTime = datasourceDateTime;
        }

        public LocalDateTime getFixedLocalDateTime1_1() {
            return fixedLocalDateTime1_1;
        }

        public void setFixedLocalDateTime1_1(LocalDateTime fixedLocalDateTime1_1) {
            this.fixedLocalDateTime1_1 = fixedLocalDateTime1_1;
        }

        public LocalDateTime getFixedLocalDateTime2_1() {
            return fixedLocalDateTime2_1;
        }

        public void setFixedLocalDateTime2_1(LocalDateTime fixedLocalDateTime2_1) {
            this.fixedLocalDateTime2_1 = fixedLocalDateTime2_1;
        }

        public LocalDateTime getTickLocalDateTime1_1() {
            return tickLocalDateTime1_1;
        }

        public void setTickLocalDateTime1_1(LocalDateTime tickLocalDateTime1_1) {
            this.tickLocalDateTime1_1 = tickLocalDateTime1_1;
        }

        public LocalDateTime getTickLocalDateTime2_1() {
            return tickLocalDateTime2_1;
        }

        public void setTickLocalDateTime2_1(LocalDateTime tickLocalDateTime2_1) {
            this.tickLocalDateTime2_1 = tickLocalDateTime2_1;
        }

        public LocalDateTime getFixedLocalDateTime1_2() {
            return fixedLocalDateTime1_2;
        }

        public void setFixedLocalDateTime1_2(LocalDateTime fixedLocalDateTime1_2) {
            this.fixedLocalDateTime1_2 = fixedLocalDateTime1_2;
        }

        public LocalDateTime getFixedLocalDateTime2_2() {
            return fixedLocalDateTime2_2;
        }

        public void setFixedLocalDateTime2_2(LocalDateTime fixedLocalDateTime2_2) {
            this.fixedLocalDateTime2_2 = fixedLocalDateTime2_2;
        }

        public LocalDateTime getTickLocalDateTime1_2() {
            return tickLocalDateTime1_2;
        }

        public void setTickLocalDateTime1_2(LocalDateTime tickLocalDateTime1_2) {
            this.tickLocalDateTime1_2 = tickLocalDateTime1_2;
        }

        public LocalDateTime getTickLocalDateTime2_2() {
            return tickLocalDateTime2_2;
        }

        public void setTickLocalDateTime2_2(LocalDateTime tickLocalDateTime2_2) {
            this.tickLocalDateTime2_2 = tickLocalDateTime2_2;
        }

        public LocalDateTime getFixedLocalDateTime1_3() {
            return fixedLocalDateTime1_3;
        }

        public void setFixedLocalDateTime1_3(LocalDateTime fixedLocalDateTime1_3) {
            this.fixedLocalDateTime1_3 = fixedLocalDateTime1_3;
        }

        public LocalDateTime getFixedLocalDateTime2_3() {
            return fixedLocalDateTime2_3;
        }

        public void setFixedLocalDateTime2_3(LocalDateTime fixedLocalDateTime2_3) {
            this.fixedLocalDateTime2_3 = fixedLocalDateTime2_3;
        }

        public LocalDateTime getTickLocalDateTime1_3() {
            return tickLocalDateTime1_3;
        }

        public void setTickLocalDateTime1_3(LocalDateTime tickLocalDateTime1_3) {
            this.tickLocalDateTime1_3 = tickLocalDateTime1_3;
        }

        public LocalDateTime getTickLocalDateTime2_3() {
            return tickLocalDateTime2_3;
        }

        public void setTickLocalDateTime2_3(LocalDateTime tickLocalDateTime2_3) {
            this.tickLocalDateTime2_3 = tickLocalDateTime2_3;
        }
    }
}
