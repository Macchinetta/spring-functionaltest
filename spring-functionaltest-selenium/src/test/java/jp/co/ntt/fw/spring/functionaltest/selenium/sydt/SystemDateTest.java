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
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.openqa.selenium.By;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class SystemDateTest extends FunctionTestSupport {

	private static final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");

	/**
	 * <ul>
	 * <li>システム・デフォルトの取得</li>
	 * </ul>
	 */
	@Test
	public void testSYDT0101001() {

		webDriverOperations.click(id("sydt0101001"));

		webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Server Time"));

		DateTime jodaFactory_1 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("jodaFactory_1")));
		DateTime DateTime_1 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("DateTime_1")));
		DateTime jodaFactory_2 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("jodaFactory_2")));
		DateTime DateTime_2 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("DateTime_2")));
		DateTime jodaFactory_3 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("jodaFactory_3")));
		DateTime DateTime_3 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("DateTime_3")));

		// システム・デフォルトを取得しているので差分なし（連続して取得しているが、同一タイミングではないのでナノ秒単位でずれることがある為、ミリ秒のずれは許容する）
		Duration duration = new Duration(jodaFactory_1, DateTime_1);
		long secondsDifference = duration.getStandardSeconds();

		assertThat(secondsDifference, is(0L));

		// tickの確認
		assertTrue(jodaFactory_1.isBefore(jodaFactory_2));
		assertTrue(jodaFactory_2.isBefore(jodaFactory_3));
		assertTrue(DateTime_1.isBefore(DateTime_2));
		assertTrue(DateTime_2.isBefore(DateTime_3));
	}

	/**
	 * <ul>
	 * <li>DBから取得した固定の時刻の取得</li>
	 * </ul>
	 */
	@Test
	public void testSYDT0102001() {

		webDriverOperations.click(id("sydt0102001"));

		webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Server Time"));

		DateTime jodaFactory_1 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("jodaFactory_1")));
		DateTime DateTime_1 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("DateTime_1")));
		DateTime jodaFactory_2 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("jodaFactory_2")));
		DateTime DateTime_2 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("DateTime_2")));
		DateTime jodaFactory_3 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("jodaFactory_3")));
		DateTime DateTime_3 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("DateTime_3")));

		// DB固定値（2013/12/09 13:50:12.100）の為値は異なる
		Duration duration = new Duration(jodaFactory_1, DateTime_1);
		long daysDifference = duration.getStandardDays();

		assertThat(daysDifference, greaterThan(1L));
		assertThat(webDriverOperations.getText(By.id("jodaFactory_1")), is("2013-12-09 13:50:12.100"));

		// tickの確認
		assertTrue(jodaFactory_1.equals(jodaFactory_2));
		assertTrue(jodaFactory_2.equals(jodaFactory_3));
		assertTrue(DateTime_1.isBefore(DateTime_2));
		assertTrue(DateTime_2.isBefore(DateTime_3));
	}

	/**
	 * <ul>
	 * <li>サーバーのシステム時刻にDBに登録した差分値を加算した時刻を取得（キャッシュなし）</li>
	 * </ul>
	 */
	@Test
	public void testSYDT0103001() {

		webDriverOperations.click(id("sydt0103001"));

		webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Server Time"));

		DateTime jodaFactory_1 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("jodaFactory_1")));
		DateTime DateTime_1 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("DateTime_1")));
		DateTime jodaFactory_2 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("jodaFactory_2")));
		DateTime DateTime_2 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("DateTime_2")));
		DateTime jodaFactory_3 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("jodaFactory_3")));
		DateTime DateTime_3 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("DateTime_3")));

		// DB差分（初期値 -1440*1000*60=-24h）の為値は異なる
		Duration duration = new Duration(jodaFactory_1, DateTime_1);
		long daysDifference = duration.getStandardDays();

		assertThat(daysDifference, greaterThanOrEqualTo(1L));
		assertThat(daysDifference, lessThan(2L));

		// tickの確認
		assertTrue(jodaFactory_1.isBefore(jodaFactory_2));
		assertTrue(jodaFactory_2.isBefore(jodaFactory_3));
		assertTrue(DateTime_1.isBefore(DateTime_2));
		assertTrue(DateTime_2.isBefore(DateTime_3));

		try {
			// キャッシュの確認（キャッシュ使用しない）
			updateDiff(0);

			webDriverOperations.click(id("sydt0103001"));

			webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Server Time"));

			jodaFactory_1 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("jodaFactory_1")));
			DateTime_1 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("DateTime_1")));
			jodaFactory_2 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("jodaFactory_2")));
			DateTime_2 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("DateTime_2")));
			jodaFactory_3 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("jodaFactory_3")));
			DateTime_3 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("DateTime_3")));

			// DB差分（0）の為値は同じとなる
			duration = new Duration(jodaFactory_1, DateTime_1);
			long secondsDifference = duration.getStandardSeconds();

			assertThat(secondsDifference, is(0L));
		} finally {
			// 元に戻す
			updateDiff(-1440);
		}
	}
	
	/**
	 * <ul>
	 * <li>サーバーのシステム時刻にDBに登録した差分値を加算した時刻を取得（キャッシュあり）</li>
	 * </ul>
	 */
	@Test
	public void testSYDT0103002() {

		webDriverOperations.click(id("sydt0103001"));

		webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Server Time"));

		DateTime jodaFactory_1 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("jodaFactory_1")));
		DateTime DateTime_1 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("DateTime_1")));
		DateTime jodaFactory_2 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("jodaFactory_2")));
		DateTime DateTime_2 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("DateTime_2")));
		DateTime jodaFactory_3 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("jodaFactory_3")));
		DateTime DateTime_3 = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("DateTime_3")));

		// DB差分（初期値 -1440*1000*60=-24h）の為値は異なる
		Duration duration = new Duration(jodaFactory_1, DateTime_1);
		long daysDifference = duration.getStandardDays();

		assertThat(daysDifference, greaterThanOrEqualTo(1L));
		assertThat(daysDifference, lessThan(2L));

		// tickの確認
		assertTrue(jodaFactory_1.isBefore(jodaFactory_2));
		assertTrue(jodaFactory_2.isBefore(jodaFactory_3));
		assertTrue(DateTime_1.isBefore(DateTime_2));
		assertTrue(DateTime_2.isBefore(DateTime_3));

		try {
			// キャッシュの確認（キャッシュ使用あり）
			updateDiff(0);

			webDriverOperations.click(id("sydt0103002"));

			webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Server Time"));

			DateTime jodaFactory_after = FORMATTER.parseDateTime(webDriverOperations.getText(By.id("jodaFactory_1")));

			// キャッシュされているため変更なし
			duration = new Duration(jodaFactory_1, jodaFactory_after);
			long daysMinutes = duration.getStandardMinutes();

			// 余裕をもって1分以下
			assertThat(daysMinutes, lessThan(1L));
		} finally {
			// 元に戻す
			updateDiff(-1440);
		}
	}

	private void updateDiff(int diff) {
		webDriverOperations.displayPage(getPackageRootUrl());

		webDriverOperations.click(id("register"));
		webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "差分時間更新"));

		webDriverOperations.overrideText(id("diff"), String.valueOf(diff));
		webDriverOperations.click(name("regist"));

		webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "差分時間確認"));

		String dbVal = webDriverOperations.getText(By.id("diff"));

		assert (String.valueOf(diff).equals(dbVal));

		webDriverOperations.displayPage(getPackageRootUrl());
	}

}