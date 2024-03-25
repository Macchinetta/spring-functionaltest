package jp.co.ntt.fw.spring.functionaltest.app.stpr.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HexFormat;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;
import org.terasoluna.gfw.common.codepoints.CodePoints;
import org.terasoluna.gfw.common.codepoints.catalog.ASCIIControlChars;
import org.terasoluna.gfw.common.codepoints.catalog.ASCIIPrintableChars;
import org.terasoluna.gfw.common.codepoints.catalog.CRLF;
import org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0201_Katakana;
import org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0201_LatinLetters;
import org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0208_BoxDrawingChars;
import org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0208_CyrillicLetters;
import org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0208_GreekLetters;
import org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0208_Hiragana;
import org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0208_Kanji;
import org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0208_Katakana;
import org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0208_LatinLetters;
import org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0208_SpecialChars;
import org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0213_Kanji;
import org.terasoluna.gfw.common.fullhalf.DefaultFullHalf;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jp.co.ntt.fw.spring.functionaltest.app.stpr.ConsistOfTestBean;
import jp.co.ntt.fw.spring.functionaltest.app.stpr.CustomFullHalf;
import jp.co.ntt.fw.spring.functionaltest.app.stpr.FullwidthHiraganaKatakana;
import jp.co.ntt.fw.spring.functionaltest.app.stpr.HalfwidthKatakana;
import jp.co.ntt.fw.spring.functionaltest.app.stpr.NumberChars;

public class StringProcessingTest {

    private static Validator validator;

    @BeforeClass
    public static void setUpBeforeClass() {
        ValidatorFactory validatorFactory = Validation
                .buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    /**
     * トリム
     * <p>
     * {@link java.lang.String#strip()}
     */
    @Test
    public void testSTPR0101001() {
        String str = "  Hello World!!";
        assertThat(str.strip(), is("Hello World!!"));
    }

    /**
     * トリム
     * <p>
     * {@link org.springframework.util.StringUtils#trimLeadingCharacter(String, char)}
     */
    @Test
    public void testSTPR0101002() {
        String str = "  Hello World!!";
        assertThat(StringUtils.trimLeadingCharacter(str, ' '), is(
                "Hello World!!"));
    }

    /**
     * トリム
     * <p>
     * {@link org.springframework.util.StringUtils#trimTrailingCharacter(String, char)}
     */
    @Test
    public void testSTPR0101003() {
        String str = "  Hello World!!";
        assertThat(StringUtils.trimTrailingCharacter(str, '!'), is(
                "  Hello World"));
    }

    /**
     * パディング・サプレス
     * <p>
     * {@link java.lang.String#format(String, Object...)}
     */
    @Test
    public void testSTPR0201001() {
        int num = 1;
        assertThat(String.format("%03d", num), is("001"));
    }

    /**
     * パディング・サプレス
     * <p>
     * {@link java.lang.String#replaceFirst(String, String)}
     */
    @Test
    public void testSTPR0201002() {
        String paddingStr = "001";
        assertThat(paddingStr.replaceFirst("^0+(?!$)", ""), is("1"));

        // STPR0201003 別パラメータ
        paddingStr = "000";
        assertThat(paddingStr.replaceFirst("^0+(?!$)", ""), is("0"));
    }

    /**
     * サロゲートペアを考慮した文字列処理
     * <p>
     * 文字列長の取得
     */
    @Test
    public void testSTPR0301001() {
        String str = "𠮷田太郎";
        int lenOfChar = str.length();
        int lenOfCodePoint = str.codePointCount(0, lenOfChar);

        assertThat(lenOfChar, is(5));
        assertThat(lenOfCodePoint, is(4));

        assertThat(getStrLength(str), is(4));
    }

    private int getStrLength(String str) {
        String normalizedStr = Normalizer.normalize(str, Normalizer.Form.NFC);
        int length = normalizedStr.codePointCount(0, normalizedStr.length());

        return length;
    }

    /**
     * サロゲートペアを考慮した文字列処理
     * <p>
     * 指定範囲の文字列取得
     */
    @Test
    public void testSTPR0302001() {
        String str = "𠮷田 太郎";
        int startIndex = 0;
        int endIndex = 2;

        String subStr1 = str.substring(startIndex, endIndex);
        String subStr2 = str.substring(str.offsetByCodePoints(0, startIndex),
                str.offsetByCodePoints(0, endIndex));

        assertThat(subStr1, is("𠮷"));
        assertThat(subStr2, is("𠮷田"));
    }

    /**
     * サロゲートペアを考慮した文字列処理
     * <p>
     * 文字列分割
     */
    @Test
    public void testSTPR0303001() {
        String str = "𠮷田 太郎";
        String[] array = str.split(" ");

        assertThat(array[0], is("𠮷田"));
        assertThat(array[1], is("太郎"));
    }

    /**
     * サロゲートペアを考慮した文字列処理
     * <p>
     * 文字列分割(区切り文字がサロゲートペア)
     */
    @Test
    public void testSTPR0303002() {
        String str = "あいう𠮷えお";
        String[] array = str.split("𠮷");

        assertThat(array[0], is("あいう"));
        assertThat(array[1], is("えお"));
    }

    /**
     * {@link java.lang.String#split(String)}の確認
     */
    @Test
    public void testSTPR0304001() {
        String str = "ABC";
        String[] array = str.split("");

        assertThat(array[0], is("A"));
        assertThat(array[1], is("B"));
        assertThat(array[2], is("C"));
    }

    /**
     * 全角・半角文字列変換
     * <p>
     * 全角文字列に変換 {@link org.terasoluna.gfw.common.fullhalf.FullHalfConverter#toFullwidth(String)}
     */
    @Test
    public void testSTPR0402001() {
        String fullwidth = DefaultFullHalf.INSTANCE.toFullwidth("ｱﾞ!A8ｶﾞザ");
        assertThat(fullwidth, is("ア゛！Ａ８ガザ"));
    }

    /**
     * 全角・半角文字列変換
     * <p>
     * 半角文字列に変換 {@link org.terasoluna.gfw.common.fullhalf.FullHalfConverter#toHalfwidth(String)}
     */
    @Test
    public void testSTPR0403001() {
        String halfwidth = DefaultFullHalf.INSTANCE.toHalfwidth("Ａ！アガｻ");
        assertThat(halfwidth, is("A!ｱｶﾞｻ"));
    }

    /**
     * 全角・半角文字列変換
     * <p>
     * NFC（正準等価性によって分解し、再度合成する）
     */
    @Test
    public void testSTPR0403002() {
        String str1 = Normalizer.normalize("モシ\u3099", Normalizer.Form.NFC);
        String str2 = Normalizer.normalize("ﾓｼﾞ", Normalizer.Form.NFC);

        assertThat(str1, is("モジ"));
        assertThat(str2, is("ﾓｼﾞ"));
    }

    /**
     * 全角・半角文字列変換
     * <p>
     * NFKD（互換等価性によって分解する）
     */
    @Test
    public void testSTPR0403003() {
        String str1 = Normalizer.normalize("モジ", Normalizer.Form.NFKD);
        String str2 = Normalizer.normalize("ﾓｼﾞ", Normalizer.Form.NFKD);

        assertThat(str1, is("モシ\u3099"));
        assertThat(str2, is("モシ\u3099"));
    }

    /**
     * 全角・半角文字列変換
     * <p>
     * NFKC（互換等価性によって分解し、再度合成する）
     */
    @Test
    public void testSTPR0403004() {
        String str1 = Normalizer.normalize("モシ\u3099", Normalizer.Form.NFKC);
        String str2 = Normalizer.normalize("ﾓｼﾞ", Normalizer.Form.NFKC);

        assertThat(str1, is("モジ"));
        assertThat(str2, is("モジ"));
    }

    /**
     * 独自の全角文字と半角文字のペア定義を登録したFullHalfConverterクラスの作成
     */
    @Test
    public void testSTPR0404001() {
        String halfwidth = CustomFullHalf.INSTANCE.toHalfwidth("ハローワールド！");
        assertThat(halfwidth, is("ﾊﾛ-ﾜ-ﾙﾄﾞ!"));
    }

    /**
     * コードポイント集合の作成/コードポイント集合を使った文字列チェック
     * <p>
     * ファクトリメソッドを呼び出してインスタンスを作成する場合
     */
    @Test
    public void testSTPR0502001() {
        // キャッシュあり
        @SuppressWarnings("unused")
        CodePoints codePoints1 = CodePoints.of(ASCIIPrintableChars.class);

        // キャッシュなし
        @SuppressWarnings("unused")
        CodePoints codePoints2 = new ASCIIPrintableChars();

        assertNotNull(codePoints1);
        assertNotNull(codePoints2);
    }

    /**
     * コードポイント集合の作成/コードポイント集合を使った文字列チェック
     * <p>
     * CodePointsのコンストラクタを呼び出してインスタンスを作成する コードポイント(int)を可変長引数で渡す
     */
    @Test
    public void testSTPR0502002() {
        CodePoints codePoints = new CodePoints(0x0061 /* a */, 0x0062 /* b */);

        assertTrue(codePoints.containsAll("ab"));
        assertFalse(codePoints.containsAll("abc"));

        assertThat(codePoints.firstExcludedCodePoint("acbd"), is(0x0063)); // c

        Set<Integer> excludedCodePoints = codePoints.allExcludedCodePoints(
                ("acbd"));
        assertThat(excludedCodePoints.size(), is(2));
        assertTrue(excludedCodePoints.contains(0x0063)); // c
        assertTrue(excludedCodePoints.contains(0x0064)); // d
    }

    /**
     * コードポイント集合の作成/コードポイント集合を使った文字列チェック
     * <p>
     * CodePointsのコンストラクタを呼び出してインスタンスを作成する コードポイント(int)のSetを渡す
     */
    @Test
    public void testSTPR0502003() {
        Set<Integer> set = new HashSet<>(Arrays.asList(0x0061 /* a */,
                0x0062 /* b */));
        CodePoints codePoints = new CodePoints(set);

        assertTrue(codePoints.containsAll("ab"));
        assertFalse(codePoints.containsAll("abc"));

        assertThat(codePoints.firstExcludedCodePoint("acbd"), is(0x0063)); // c

        Set<Integer> excludedCodePoints = codePoints.allExcludedCodePoints(
                ("acbd"));
        assertThat(excludedCodePoints.size(), is(2));
        assertTrue(excludedCodePoints.contains(0x0063)); // c
        assertTrue(excludedCodePoints.contains(0x0064)); // d
    }

    /**
     * コードポイント集合の作成/コードポイント集合を使った文字列チェック
     * <p>
     * CodePointsのコンストラクタを呼び出してインスタンスを作成する コードポイント(int)のSetを渡す
     */
    @Test
    public void testSTPR0502004() {
        // コードポイント集合文字列を渡す
        CodePoints codePoints1 = new CodePoints("ab");

        assertTrue(codePoints1.containsAll("ab"));
        assertFalse(codePoints1.containsAll("abc"));

        assertThat(codePoints1.firstExcludedCodePoint("acbd"), is(0x0063)); // c

        Set<Integer> excludedCodePoints1 = codePoints1.allExcludedCodePoints(
                ("acbd"));
        assertThat(excludedCodePoints1.size(), is(2));
        assertTrue(excludedCodePoints1.contains(0x0063)); // c
        assertTrue(excludedCodePoints1.contains(0x0064)); // d

        // コードポイント集合文字列を複数に分けて渡す
        CodePoints codePoints2 = new CodePoints("a", "b");

        assertTrue(codePoints2.containsAll("ab"));
        assertFalse(codePoints2.containsAll("abc"));

        assertThat(codePoints2.firstExcludedCodePoint("acbd"), is(0x0063)); // c

        Set<Integer> excludedCodePoints2 = codePoints1.allExcludedCodePoints(
                ("acbd"));
        assertThat(excludedCodePoints2.size(), is(2));
        assertTrue(excludedCodePoints2.contains(0x0063)); // c
        assertTrue(excludedCodePoints2.contains(0x0064)); // d
    }

    /**
     * コードポイント集合同士の集合演算/コードポイント集合を使った文字列チェック
     * <p>
     * 和集合メソッドを使用してコードポイント集合のインスタンスを作成する
     */
    @Test
    public void testSTPR0503001() {
        CodePoints abCp = new CodePoints(0x0061 /* a */, 0x0062 /* b */);
        CodePoints cdCp = new CodePoints(0x0063 /* c */, 0x0064 /* d */);

        CodePoints abcdCp = abCp.union(cdCp);

        assertTrue(abcdCp.containsAll("abcd"));
        assertFalse(abcdCp.containsAll("abcde"));

        assertThat(abcdCp.firstExcludedCodePoint("abcde"), is(0x0065)); // e

        Set<Integer> excludedCodePoints = abcdCp.allExcludedCodePoints(
                ("abecdf"));
        assertThat(excludedCodePoints.size(), is(2));
        assertTrue(excludedCodePoints.contains(0x0065)); // e
        assertTrue(excludedCodePoints.contains(0x0066)); // f
    }

    /**
     * コードポイント集合同士の集合演算/コードポイント集合を使った文字列チェック
     * <p>
     * 差集合メソッドを使用してコードポイント集合のインスタンスを作成する
     */
    @Test
    public void testSTPR0503002() {
        CodePoints abcdCp = new CodePoints(0x0061 /* a */, 0x0062 /* b */, 0x0063 /* c */, 0x0064 /* d */);
        CodePoints cdCp = new CodePoints(0x0063 /* c */, 0x0064 /* d */);

        CodePoints abCp = abcdCp.subtract(cdCp);

        assertTrue(abCp.containsAll("ab"));
        assertFalse(abCp.containsAll("abc"));

        assertThat(abCp.firstExcludedCodePoint("acbd"), is(0x0063)); // c

        Set<Integer> excludedCodePoints = abCp.allExcludedCodePoints(("acbd"));
        assertThat(excludedCodePoints.size(), is(2));
        assertTrue(excludedCodePoints.contains(0x0063)); // c
        assertTrue(excludedCodePoints.contains(0x0064)); // d
    }

    /**
     * コードポイント集合同士の集合演算/コードポイント集合を使った文字列チェック
     * <p>
     * 積集合で新規のコードポイント集合のインスタンスを作成する
     */
    @Test
    public void testSTPR0503003() {
        CodePoints abcdCp = new CodePoints(0x0061 /* a */, 0x0062 /* b */, 0x0063 /* c */, 0x0064 /* d */);
        CodePoints cdeCp = new CodePoints(0x0063 /* c */, 0x0064 /* d */, 0x0065 /* e */);

        CodePoints cdCp = abcdCp.intersect(cdeCp);

        assertTrue(cdCp.containsAll("cd"));
        assertFalse(cdCp.containsAll("cde"));

        assertThat(cdCp.firstExcludedCodePoint("cade"), is(0x0061)); // a

        Set<Integer> excludedCodePoints = cdCp.allExcludedCodePoints(("cade"));
        assertThat(excludedCodePoints.size(), is(2));
        assertTrue(excludedCodePoints.contains(0x0061)); // c
        assertTrue(excludedCodePoints.contains(0x0065)); // e
    }

    // STPR0506001 ～ STPR0506003 はこれまでの試験項目で実施済み

    /**
     * Bean Validationと連携した文字列チェック
     */
    @Test
    public void testSTPR0505001() {
        // name1はひらがな、name2はひらがな+カタカナが許容される
        ConsistOfTestBean form1 = new ConsistOfTestBean("あ", "あア");
        Set<ConstraintViolation<ConsistOfTestBean>> violations1 = validator
                .validate(form1);

        assertThat(violations1, is(empty()));

        // name1とname2それぞれについて範囲外を指定する
        ConsistOfTestBean form2 = new ConsistOfTestBean("あア", "あア1");
        Set<ConstraintViolation<ConsistOfTestBean>> violations2 = validator
                .validate(form2);

        List<String> pathArray = violations2.stream().map(t -> t
                .getPropertyPath().toString()).toList();
        assertThat(pathArray.size(), is(2));
        assertTrue(pathArray.contains("name1"));
        assertTrue(pathArray.contains("name2"));
    }

    /**
     * コードポイント集合クラス
     * <p>
     * {@link NumberChars}
     */
    @Test
    public void testSTPR0506001() {
        // 数字のみ
        NumberChars codePoints = new NumberChars();

        assertTrue(codePoints.containsAll("0123456789"));
        assertFalse(codePoints.containsAll("０１２３４５６７８９"));
    }

    /**
     * コードポイント集合クラス
     * <p>
     * {@link FullwidthHiraganaKatakana}
     */
    @Test
    public void testSTPR0506002() {
        // ひらがな」と「カタカナ」からなる和集合
        FullwidthHiraganaKatakana codePoints = new FullwidthHiraganaKatakana();

        assertTrue(codePoints.containsAll("あア"));
        assertFalse(codePoints.containsAll("ｱ"));
    }

    /**
     * コードポイント集合クラス
     * <p>
     * {@link HalfwidthKatakana}
     */
    @Test
    public void testSTPR0506003() {
        // 記号（｡｢｣､･）を除いた半角カタカナ
        HalfwidthKatakana codePoints = new HalfwidthKatakana();

        assertTrue(codePoints.containsAll("ｧｱ"));
        assertFalse(codePoints.containsAll("。"));
    }

    /**
     * 指定したHex範囲をAsciiコードの文字列に変換する
     * @param hexStart 開始
     * @param hexEnd 終了
     * @return Asciiコードで変換した文字列
     */
    private String hexrangeToAscii(int hexStart, int hexEnd) {

        StringBuilder builder = new StringBuilder();

        for (int i = hexStart; i <= hexEnd; i++) {
            String hexStr = Integer.toHexString(i);
            builder.append(hexToAscii(hexStr.length() % 2 != 0 ? "0" + hexStr
                    : hexStr));
        }

        return builder.toString();
    }

    /**
     * Hex表記の文字列をAsciiコードの文字列に変換する
     * @param hexStr Hex文字列
     * @return Asciiコードで変換した文字列
     */
    private String hexToAscii(String hexStr) {
        StringBuilder ascii = new StringBuilder("");

        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            ascii.append((char) Integer.parseInt(str, 16));
        }

        return ascii.toString();
    }

    /**
     * コードポイント集合チェック(文字種チェック)
     * <p>
     * Ascii制御文字の集合(0x0000-0x001F、0x007F) {@link org.terasoluna.gfw.common.codepoints.catalog.ASCIIControlChars}
     */
    @Test
    public void testSTPR0507001() {

        CodePoints codePoints = new ASCIIControlChars();

        String controlStr = hexrangeToAscii(0x0000, 0x001F);
        controlStr = controlStr + hexrangeToAscii(0x007F, 0x007F);

        boolean contains = codePoints.containsAll(controlStr);
        assertTrue(contains);
    }

    /**
     * コードポイント集合チェック(文字種チェック)
     * <p>
     * Ascii印字可能文字の集合(0x0020-0x007E) {@link org.terasoluna.gfw.common.codepoints.catalog.ASCIIPrintableChars}
     */
    @Test
    public void testSTPR0507002() {
        CodePoints codePoints = new ASCIIPrintableChars();

        String controlStr = hexrangeToAscii(0x0020, 0x007E);

        boolean contains = codePoints.containsAll(controlStr);
        assertTrue(contains);
    }

    /**
     * コードポイント集合チェック(文字種チェック)
     * <p>
     * 改行コードの集合(0x000A, 0x000D) {@link org.terasoluna.gfw.common.codepoints.catalog.CRLF}
     */
    @Test
    public void testSTPR0507003() {
        CodePoints codePoints = new CRLF();

        String controlStr = hexrangeToAscii(0x000A, 0x000A);
        controlStr = controlStr + hexrangeToAscii(0x000D, 0x000D);

        boolean contains = codePoints.containsAll(controlStr);
        assertTrue(contains);
    }

    /**
     * CSVの読み込み
     * 
     * @param path csvのパス
     * @return UTF-16をIntegerに変換したリスト 
     */
    private List<Integer> readCsv(String path) {

        List<Integer> hexArray = new ArrayList<>();

        try (BufferedReader buffReader = new BufferedReader(new FileReader(new ClassPathResource(path)
                .getFile()))) {

            // JIS SJIS EUC UTF-8 UTF-16 字
            String line = null;
            while ((line = buffReader.readLine()) != null) {
                String[] arrayStr = line.split("\s");

                // 割り当てが無い箇所は"------"もしくは空となっている
                if (StringUtils.hasLength(arrayStr[4]) && !arrayStr[4]
                        .startsWith("-")) {
                    hexArray.add(HexFormat.fromHexDigits(arrayStr[4]));
                }
            }
        } catch (IOException e) {
            fail("ファイルの読み込みに失敗 : " + path);
        }

        return hexArray;
    }

    /**
     * コードポイント集合チェック(文字種チェック)
     * <p>
     * JIS X 0201 の半角カタカナの集合 {@link org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0201_Katakana}
     */
    @Test
    public void testSTPR0507004() {
        CodePoints codePoints = new JIS_X_0201_Katakana();

        List<Integer> hexArray = readCsv("codepoint/JIS_X_0201_Katakana.csv");

        // @formatter:off
        String target = hexArray.stream() 
                .map(i -> new String(Character.toChars(i)))
                .collect(Collectors.joining());
        // @formatter:on

        boolean contains = codePoints.containsAll(target);
        assertTrue(contains);
    }

    /**
     * コードポイント集合チェック(文字種チェック)
     * <p>
     * JIS X 0201 のLatin文字の集合 {@link org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0201_LatinLetters}
     */
    @Test
    public void testSTPR0507005() {
        CodePoints codePoints = new JIS_X_0201_LatinLetters();

        List<Integer> hexArray = readCsv(
                "codepoint/JIS_X_0201_LatinLetters.csv");

        // @formatter:off
        String target = hexArray.stream() 
                .map(i -> new String(Character.toChars(i)))
                .collect(Collectors.joining());
        // @formatter:on

        boolean contains = codePoints.containsAll(target);
        assertTrue(contains);
    }

    /**
     * コードポイント集合チェック(文字種チェック)
     * <p>
     * JIS X 0208 の1-2区 {@link org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0208_SpecialChars}
     * <br>
     * HORINZONTAL BAR (U+2015)はEM DASH (U+2014)に変更されている。（CSVの中身も変更済み）
     */
    @Test
    public void testSTPR0507006() {
        CodePoints codePoints = new JIS_X_0208_SpecialChars();

        List<Integer> hexArray = readCsv(
                "codepoint/JIS_X_0208_1_2_SpecialChars.csv");

        // @formatter:off
        String target = hexArray.stream() 
                .map(i -> new String(Character.toChars(i)))
                .collect(Collectors.joining());
        // @formatter:on

        boolean contains = codePoints.containsAll(target);
        assertTrue(contains);
    }

    /**
     * コードポイント集合チェック(文字種チェック)
     * <p>
     * JIS X 0208 の3区 {@link org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0208_LatinLetters}
     */
    @Test
    public void testSTPR0507007() {
        CodePoints codePoints = new JIS_X_0208_LatinLetters();

        List<Integer> hexArray = readCsv(
                "codepoint/JIS_X_0208_3_LatinLetters.csv");

        // @formatter:off
        String target = hexArray.stream() 
                .map(i -> new String(Character.toChars(i)))
                .collect(Collectors.joining());
        // @formatter:on

        boolean contains = codePoints.containsAll(target);
        assertTrue(contains);
    }

    /**
     * コードポイント集合チェック(文字種チェック)
     * <p>
     * JIS X 0208 の4区 {@link org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0208_Hiragana}
     */
    @Test
    public void testSTPR0507008() {
        CodePoints codePoints = new JIS_X_0208_Hiragana();

        List<Integer> hexArray = readCsv("codepoint/JIS_X_0208_4_Hiragana.csv");

        // @formatter:off
        String target = hexArray.stream() 
                .map(i -> new String(Character.toChars(i)))
                .collect(Collectors.joining());
        // @formatter:on

        boolean contains = codePoints.containsAll(target);
        assertTrue(contains);
    }

    /**
     * コードポイント集合チェック(文字種チェック)
     * <p>
     * JIS X 0208 の5区 {@link org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0208_Katakana}
     */
    @Test
    public void testSTPR0507009() {
        CodePoints codePoints = new JIS_X_0208_Katakana();

        List<Integer> hexArray = readCsv("codepoint/JIS_X_0208_5_Katakana.csv");

        // @formatter:off
        String target = hexArray.stream() 
                .map(i -> new String(Character.toChars(i)))
                .collect(Collectors.joining());
        // @formatter:on

        boolean contains = codePoints.containsAll(target);
        assertTrue(contains);
    }

    /**
     * コードポイント集合チェック(文字種チェック)
     * <p>
     * JIS X 0208 の6区 {@link org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0208_GreekLetters}
     */
    @Test
    public void testSTPR0507010() {
        CodePoints codePoints = new JIS_X_0208_GreekLetters();

        List<Integer> hexArray = readCsv(
                "codepoint/JIS_X_0208_6_GreekLetters.csv");

        // @formatter:off
        String target = hexArray.stream() 
                .map(i -> new String(Character.toChars(i)))
                .collect(Collectors.joining());
        // @formatter:on

        boolean contains = codePoints.containsAll(target);
        assertTrue(contains);
    }

    /**
     * コードポイント集合チェック(文字種チェック)
     * <p>
     * JIS X 0208 の7区 {@link org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0208_CyrillicLetters}
     */
    @Test
    public void testSTPR0507011() {
        CodePoints codePoints = new JIS_X_0208_CyrillicLetters();

        List<Integer> hexArray = readCsv(
                "codepoint/JIS_X_0208_7_CyrillicLetters.csv");

        // @formatter:off
        String target = hexArray.stream() 
                .map(i -> new String(Character.toChars(i)))
                .collect(Collectors.joining());
        // @formatter:on

        boolean contains = codePoints.containsAll(target);
        assertTrue(contains);
    }

    /**
     * コードポイント集合チェック(文字種チェック)
     * <p>
     * JIS X 0208 の8区 {@link org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0208_BoxDrawingChars}
     */
    @Test
    public void testSTPR0507012() {
        CodePoints codePoints = new JIS_X_0208_BoxDrawingChars();

        List<Integer> hexArray = readCsv(
                "codepoint/JIS_X_0208_8_BoxDrawingChars.csv");

        // @formatter:off
        String target = hexArray.stream() 
                .map(i -> new String(Character.toChars(i)))
                .collect(Collectors.joining());
        // @formatter:on

        boolean contains = codePoints.containsAll(target);
        assertTrue(contains);
    }

    /**
     * コードポイント集合チェック(文字種チェック)
     * <p>
     * JIS X 208で規定される漢字6355字 {@link org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0208_Kanji}
     */
    @Test
    public void testSTPR0507013() {
        CodePoints codePoints = new JIS_X_0208_Kanji();

        List<Integer> hexArray = readCsv("codepoint/JIS_X_0208_Kanji.csv");

        // @formatter:off
        String target = hexArray.stream() 
                .map(i -> new String(Character.toChars(i)))
                .collect(Collectors.joining());
        // @formatter:on

        boolean contains = codePoints.containsAll(target);
        assertTrue(contains);
    }

    /**
     * コードポイント集合チェック(文字種チェック)
     * <p>
     * JIS X 0213:2004で規定される漢字10050字 {@link org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0213_Kanji}
     */
    @Test
    public void testSTPR0507014() {
        CodePoints codePoints = new JIS_X_0213_Kanji();

        List<Integer> hexArray = readCsv("codepoint/JIS_X_0213_2004_Kanji.csv");

        // @formatter:off
        String target = hexArray.stream() 
                .map(i -> new String(Character.toChars(i)))
                .collect(Collectors.joining());
        // @formatter:on

        boolean contains = codePoints.containsAll(target);
        assertTrue(contains);
    }
}
