機能ID命名
----------

-   ルール

1.  ガイドラインの機能名の英語訳を作成(e.g. Double Submit
    Protection,Data Access MyBatis2,Internationalization)
2.  単語の最後が数字の場合は残す。（この後のルールで削ることはなく最後まで残す。）
3.  単語から先頭以外の母音を削除(e.g. Dbl Sbmt Prtctn,Dt Accss MyBts2,Intrntnlztn)
4.  複数の単語が残った場合、4以上の最大公約になるように数を減らす。4以上になった場合は最後から消す。(e.g. Db Sb Pr,Dt Ac M2,Intr)
5.  残った単語区切りの後ろから桁を減らす。最終形を作成(e.g. DBSP,DAM2,INTR)


| 機能名(日本語) | 機能名(英語) | 機能ID |
| ------------- |:------------- |:----- |
| ドメイン層 | Domain Layer | DMLY |
| インフラ層 | Infrastructure Layer | INLY |
| アプリケーション層 | Application Layer| APLY |
| データベースアクセス（共通編） | Data Access Common | DTAC |
| データベースアクセス（Mybatis3編） | Data Access MyBatis3 | DAM3 |
| データベースアクセス（JPA編） | Data Access JPA | DJPA |
| 排他制御 | Exclusion Control| EXCN |
| 入力チェック | Validation | VLDT |
| ロギング | Logging| LGGN |
| 例外ハンドリング | Exception Handling | EXHN |
| セッション管理 | Session Management | SSMN |
| メッセージ管理 | Message Management | MSMN |
| プロパティ管理 | Property Management| PRMN |
| ページネーション | Pagination | PGNT |
| 二重送信防止 | Double Submit Protection | DBSP |
| 国際化 | Internationalization | INTR |
| コードリスト | Code List| CDLS |
| Ajax | Ajax | AJAX |
| Rest | Rest | REST |
| RESTクライアント | Rest Client | RSCL |
| SOAP通信(サーバ/クライアント) | SOAP (Server/Client) | SOAP |
| ファイルアップロード | File Upload| FLUP |
| ファイルダウンロード | File Download| FLDW |
| Tilesによる画面レイアウト| Tiles Layout | TLLY |
| Thymeleafにおける画面レイアウト| Thymeleaf Layout | THLY |
| システム時刻 | System Date| SYDT |
| Beanマッピング(Dozer)| Bean Mapping | BNMP |
| 日付操作(Joda Time)| Date Operation | DTOP |
| 日付操作(Date and Time API)| Date Operation(Date and Time API) | DNTA |
| 文字列操作| String Processing | STPR |
| Spring Security| Spring Security| SPSC |
| 認証 | Authentication | ATHN |
| パスワードハッシュ化 | Password Hash| PSHS |
| 認可 | Authorization| ATHR |
| XSS対策| XSS Protection | XSPR |
| CSRF対策 | CSRF Protection| CSPR |
| E-mail送信(SMTP) | E-mail (SMTP) | EMAL |
| JMS | Java Message Service | JMSS |
| ヘルスチェック | Health Check | HLCH |
| OAuth2.0(Spring Security OAuth - depricated) | OAuth2.0 | OTH2 |
| OAuth2.0(Spring Security) | OAuth2.0 | O2SP |
| 暗号化 | Encryption | ENCR |
| Spring Securityのセッション管理 | Spring Security Session Management | SPSM |
| Thymeleaf | Thymeleaf | THYM |
| 共通 | Common | CMMN |
| サンプル | Example| EXMP |

-   共通(Common)は機能としてはないが、試験アプリを作る上で必要なので、機能定義しておく。
-   サンプル(Example)はテストに直接関係ないが、一応機能定義しておく。

試験項目表雛形
--------------

[雛形](./00_雛形)

試験項目表
--------------

-   確認方法について

実行できる方法を選択。

項目作成時には予測。

| 選択 | 意味 |
|:--------- |:------------- |
| ○ | 実施可能 |
| △ | 実施可能方法がわからないがコード記述時に調査予定 |
| × | 実行不可 |
| 手動実行 | 手動実行して確認 |
| 机上 | 机上確認 |

**実行コード作成後に△は存在しなくなること。**

**○以外の確認方法の場合は確認方法の横のセルに理由を記述すること。**


試験アプリ構成
--------------

[構成アプリ](https://github.com/terasolunaorg/spring-functionaltest)

### Maven

#### groupId

-   jp.co.ntt.fw.spring.functionaltest

#### artifactId

| artifactId |説明 |
|:------------- |:----------------- |
| spring-functionaltest | 依存関係とビルドの管理を行うparent POM。|
| spring-functionaltest-domain | |
| spring-functionaltest-domain-oauth2 | OAuth2.0試験用サーバに使用する。|
| spring-functionaltest-env | |
| spring-functionaltest-env-oauth2 | OAuth2.0試験用サーバに使用する。|
| spring-functionaltest-initdb | |
| spring-functionaltest-model | SOAP通信時のみ使用する。公開するModelとなるJavaBeanを配置する。 |
| spring-functionaltest-selenium | |
| spring-functionaltest-web | |
| spring-functionaltest-web-oauth2-component | OAuth2.0試験用サーバの共通モジュール。|
| spring-functionaltest-web-oauth2-db | OAuth2.0試験用サーバ。データベースを用いてアクセストークンを連携する。|
| spring-functionaltest-web-oauth2-memory | OAuth2.0試験用サーバ。インメモリを用いてアクセストークンを連携する。|
| spring-functionaltest-web-oauth2-remote | OAuth2.0試験用サーバ。http通信を用いてアクセストークンを連携する。|
| spring-functionaltest-web-oauth2-resource | OAuth2.0(Spring Security)試験用リソースサーバ。|
| spring-functionaltest-webservice | SOAP通信時のみ使用する。公開するWebserviceのインターフェースを配置する。 |
| spring-functionaltest-web-thymeleaf | spring-functionaltest-webのViewにThymeleafを適用する。 |

### パッケージ規約

#### 基底パッケージ

-   jp.co.ntt.fw.spring.functionaltest

#### ドメイン層

```
${basePackage}/domain
   /model
   /service/機能ID(小文字)
   /repository/機能ID(小文字)
```

-   例)
    {basePackage}/domain/service/flup、{basePackage}/domain/repository/flup

#### アプリケーション層

```
${basePackage}/app/機能ID(小文字)
```

-   例) \${basePackage}/app/flup

#### 機能試験(Selenium)のパッケージ {#機能試験Seleniumのパッケージ}

```
${basePackage}/selenium/機能ID(小文字)
```

-   例) \${basePackage}/selenium/flup

### クラス規約

#### 基本ポリシー

-   Controllerの呼び出しまでは、テストケースとの関連付けを行うために、機械的な命名規約を採用する。

-   Controller以降の処理は、試験アプリの題材として採用したユースケースを意識した命名規約を採用する。

-   DI対象のフィールドはパッケージプライベートにする。アノテーションは@Injectを使用する。同じ型が複数定義されている場合は@Named("bean名")を追加する

```
public class XxxController {

    @Inject
    FooService fooService;

    @Inject
    @Named("bar1")
    Bar bar1;

    @Inject
    @Named("bar2")
    Bar bar2;

}
```

-   メッセージはapplication-messages.propertiesに定義して、プログラム中ではメッセージキーのみ扱う。

NG

```
throw new BussinessException("This is sample error message!");
```

OK

```
e.sf.exmp.8001=This is sample error message!
```

```
ResultMessages messages = ResultMessages.error().add("e.sf.exmp.8001");
thrown new BussinessaException(messages);
```

-   日付の生成はDateFactoryを使用すること。

-   基本的にjava.util.Dateクラスは使用せず、Joda-Timeのorg.joda.time.DateTimeまたはorg.joda.time.LocalDate（年月日のみ）を使用すること。

#### ドメイン層

-   モデル(主にEntity)は機械的な名前ではなく、実際にありそうなクラス名(Entity名)とすること。
    -   例) Cart, CartItemやBook, Authorなど・・

-   サービスは機械的な名称(例えば、機能ID+Service)ではなく、試験アプリの題材として扱うユースケースを意識した名前にすること。
    -   例) CartServiceやBookManagementServicなど・・

-   SharedServiceを作成する場合は複数のServiceから呼び出されることを考慮して作ること。特定のコンテキストに依存してはいけない。外部仕様をJavaDocに明記すること。

#### アプリケーション層

-   コントローラは、原則「中項目ID+Controller」とすること。

    ```
    @RequestMapping("/flup/0101")
    @Controller
    public class FLUP0101Controller {
        // 省略...
    }
    ```

-   コントローラの処理メソッドは、原則「"handle"+CaseID」とする。テストケースの中で複数の処理メソッドが必要な場合は、「"handle"+CaseID+処理を識別するリクエストパラメータ名」とする。

    ```
        @RequestMapping(value = { "001" }, method = RequestMethod.GET)
        public String handle001() {
            return "flup/singleFileUploadForm";
        }

        @RequestMapping(value = { "001" }, method = RequestMethod.POST, params = "upload")
        public String handle001Upload(FileUploadForm form, Model model) throws UnsupportedEncodingException, IOException {
            fileUploadHelper.bindToModel(form.getMultipartFile(), model);
            return "flup/contentFileUploadComplete";
        }
    ```

-   処理メソッドの引数にHttpServletRequestなどガイドライン「アプリケーション層の実装#ハンドラメソッドの引数について」で使用しないよう言及されている型は使用しないこと。どうしても必要な場合は要相談。

-   複数のテストケースで処理メソッド(のロジック)を共有したい場合は、privateメソッドを作って呼び出すこと。コントローラを跨いで共有したい場合は、ヘルパークラスにメソッドを作成すること。

    ```
        @RequestMapping(value = "001", method = RequestMethod.GET)
        public String handle001() {
            return form();
        }

        @RequestMapping(value = "001", method = RequestMethod.POST, params = "upload")
        public String handle001Upload(ContentUploadForm form, Model model) throws UnsupportedEncodingException, IOException {
            return upload(form, model);
        }

        @RequestMapping(value = "003", method = RequestMethod.GET)
        public String handle003() {
            return form();
        }

        @RequestMapping(value = "003", method = RequestMethod.POST, params = "upload")
        public String handle003Upload(ContentUploadForm form, Model model) throws UnsupportedEncodingException, IOException {
            return upload(form, model);
        }

        private String form() {
            return "flup/contentFileUploadForm";
        }

        private String upload(ContentUploadForm form, Model model) throws UnsupportedEncodingException, IOException {
            fileUploadHelper.bindToModel(form.getMultipartFile(), model);
            return "flup/contentFileUploadComplete";
        }
    ```



-   ヘルパークラスは、機械的な名前(例えば、機能ID+Helper)ではなく、役割がわかる名称とすること。
    -   例) FileUploadHelperとか・・



-   フォームクラスは、機械的な名称(例えば、機能ID+Form)ではなく、役割がわかる名称とすること。
    -   例) ContentUploadFormとか・・



-   フォームオブジェクト &lt;==&gt;
    ドメインオブジェクトの変換にはできるかぎりDozerを使用すること。

-   Controllerから直接Repositoryを呼び出してはいけない。必ずController-&gt;Service-&gt;Repositoryというレイヤにすること

-   アプリケーション層のクラス(ControllerやHelper等)で@Transactionalを使用してはいけない

#### テストケース

-   Seleniumのテストケースクラスは、機能IDの元となった英語名称+Testとすること。
    ※原則１クラスとするが、必要に応じて細分化してもよい。
    -   例) FileUploadTest

```
public class FileUploadTest extends FunctionTestSupport {
    // 省略
}
```

-   テストケースメソッドは、「"test" +
    テストケースID(中項目ID+CaseID」とする。

```
    /**
     * <ul>
     * <li>Servlet3.0のアップロード機能とSpringMVCを連携した場合に、ファイルがアップロードできることを確認する。</li>
     * </ul>
     */
    @Test
    public void testFLUP0101001() throws IOException {
        // 省略
    }
```

-   他のテストケースで確認できるものについては、@Ignoreアノテーションを付与して理由を記載する。

    ```
        /**
         * <ul>
         * <li>設定ファイルにアップロードを許可する1ファイルの最大バイト数を指定した場合に、アップロードが行えることを確認する。</li>
         * </ul>
         */
        @Ignore("testFLUP0102001で確認済みのため。")
        @Test
        public void testFLUP0102002() throws IOException {
            testFLUP0102001(); // ★ 関連付けるために、メソッドを呼び出すコードを記載。
        }
    ```

-   Selemiumでの自動化ができないものについては、@Ignoreアノテーションを付与して理由を記載する。＋@Testを付与しない。

    ```
        /**
         * <ul>
         * <li>設定ファイルにアップロードを許可する1ファイルの最大バイト数を指定した場合に、アップロードが行えることを確認する。</li>
         * </ul>
         */
        @Ignore("XXXXができず自動化対象外のため。")
        public void testFLUP0102002() throws IOException {
        }
    ```

### Webリソース規約

#### サーブレットパス(URL)

-   サーブレットパス(URL)は、以下のルールで付与すること。

    ```
    機能ID(小文字)/中項目ID(数字部分)/CaseID
    ```

-   例) flup/0101/001
-   ひとつのCaseIDに複数のリクエストが含まれる場合は、リクエストパラメータを使って識別すること。
    -   例) flup/0101/001?form, flup/0101/001?upload,
        flup/0101/001?complete とか・・
-   @RequestMapping アノテーションに指定する値となる。

#### JSP

-   JSPは、以下のルールで作成すること。

    ```
    ${webapps}/WEB-INF/views/機能ID(小文字)/XXX.jsp
    ```

-   JSP名は機械的な名前(テストケースIDなど)ではなく、試験アプリの題材として扱うユースケースを意識した名前にすること。
    -   例)contentUploadForm, contentUploadCompleteとか・・



-   \${webapps}/WEB-INF/views/welcome/index.jsp
    に機能毎のメニューリンクを追加する。雛形と具体例は以下の通り。

    ```
    <!-- 省略 -->
    <li>
        <a id="{機能ID(小文字)}Link" href="${pageContext.request.contextPath}/{機能ID(小文字)}">
            [{機能ID] {機能名} 機能テスト
        </a>
    </li>
    <!-- 省略 -->
    ```

    ```
    <!-- 省略 -->
    <li>
        <a id="flupLink" href="${pageContext.request.contextPath}/flup">
            [FLUP] ファイルアップロード 機能テスト
        </a>
    </li>
    <!-- 省略 -->
    ```

-   機能毎にindex.jspを作成し、テストケース一覧のメニューを作成する。雛形と具体例は以下の通り。
    -   \${webapps}/WEB-INF/views/機能ID(小文字)/index.jsp

        ```
        <div id="wrapper">

            <h1 id="screenTitle">{機能ID} {機能名}</h1>

            <div>[{大項目ID}] {大項目名}</div>
            <ul>
                <li><a id="{中項目ID(小文字)}{CaseID}"
                    href="${pageContext.request.contextPath}/大項目ID(小文字)/中項目ID(数字部分)/CaseID">{中項目ID(小文字)}{CaseID}</a></li>
                <!-- 省略 -->
            </ul>

            <!-- 省略 -->

        </div>
        ```

        ```
        <div id="wrapper">

            <h1 id="screenTitle">FLUP ファイルアップロード</h1>

            <div>[FLUP01] アプリケーションの設定</div>
            <ul>
                <li><a id="flup0101001"
                    href="${pageContext.request.contextPath}/flup/0101/001">flup0101001</a></li>
                <!-- 省略 -->
            </ul>
            <!-- 省略 -->
        </div>
        ```

#### CSS

-   CSSは、以下のルールで作成すること。

    ```
    ${webapps}/resources/app/css/機能ID(小文字).css
    ```

-   例) \${webapps}/resources/app/css/flup.css
-   CSSが不要な場合でも、必ず配置してください。
-   テストアプリケーション全体に適用するスタイルについては、\${webapps}/resources/app/css/cmmn.css
    に定義してください。

#### Javascript

-   Javascriptは、以下のルールで作成すること。

    ```
    ${webapps}/resources/app/js/機能ID(小文字).js
    ```

-   例) \${webapps}/resources/app/js/flup.js
-   Javascriptが不要な場合でも、必ず配置してください。
-   テストアプリケーション全体で使用するfunctionについては、\${webapps}/resources/app/js/cmmn.jsに定義してください。

### Message ID規約

#### Result Message

-   処理結果メッセージは、以下のルールで作成すること。

    ```
    {メッセージタイプ}.sf.{機能ID(小文字)}.{エラーレベル}{連番(0から開始)}
    ```

-   例) e.sf.flup.8000
-   各項目の説明は、ガイドライン「メッセージ管理#メッセージID体系」を参照。

#### Title Message

-   タイトルメッセージは、以下のルールで作成すること。

    ```
    title.{機能ID(小文字)}.{JSP名(拡張子抜き)}
    ```

-   例) title.flup.index

#### Label Message

-   ラベルメッセージは、以下のルールで作成すること。

    ```
    label.sf.{機能ID(小文字)}.{物理項目名}
    ```

-   例) label.sf.flup.staffName

### テストデータ規約

#### テーブル

-   表命名規約
    -   接頭語（英小文字1文字＋'\_'）＋エンティティを示す名前（全英小文字＋数字、区切りは'\_'を使用。）
    -   全ての文字数で30文字以内

| 種類 | 接頭語 | 例 |
|:------------- |:-----:|:------------- |
| テーブル | t\_ | t\_buy\_history |
| マスタ | m\_ | m\_category |
| ビュー | v\_ | v\_cart\_item |
| シーケンス | s\_ |  s\_item\_id |

-   ローカル環境用のDDLおよびDMLは以下のファイルに定義する。
    -   共通的なテーブルについては、機能IDの部分が「cmmn」となっているファイルに定義する。

```
spring-functionaltest-env/src/main/resources/database/H2-schema-{機能ID(小文字)}.sql
spring-functionaltest-env/src/main/resources/database/H2-procedure-{機能ID(小文字)}.sql
spring-functionaltest-env/src/main/resources/database/H2-dataload-{機能ID(小文字)}.sql
```

-   結合試験環境のDDL及びDMLは以下のファイルに定義する。
    -   共通的なテーブルについては、機能IDの部分が「cmmn」となっているファイルに定義する。

```
spring-functionaltest-initdb/src/main/sqls/{DB名}/00{連番3桁(0開始)}_schema-{機能ID(小文字)}.sql
spring-functionaltest-initdb/src/main/sqls/{DB名}/01{連番3桁(0開始)}_procedure-{機能ID(小文字)}.sql
spring-functionaltest-initdb/src/main/sqls/{DB名}/02{連番3桁(0開始)}_dataload-{機能ID(小文字)}.sql
```

#### カラム

-   カラム命名規約
    -   カラム内容をを示す名前（全英小文字＋数字、区切りは'\_'を使用。）
    -   全ての文字数で30文字以内

| 例 |
|:--------- |
| category\_id |

#### ファイル(サーバ側)

-   ローカル環境用のテスト用ファイルは以下のディレクトリ(配下)に格納する。
    -   サブディレクトリは必要に応じて設けてよい。

```
spring-functionaltest-env/src/main/resources/testdata/{機能ID(小文字)}
```

-   結合試験環境用のテスト用ファイルは以下のディレクトリ(配下)に格納する。
    -   サブディレクトリは必要に応じて設けてよい。

```
spring-functionaltest-env/configs/{環境名}/resources/testdata/{機能ID(小文字)}
```

#### ファイル(テストケース側)

-   テスト用ファイルは以下のディレクトリ(配下)に格納する。
    -   サブディレクトリは必要に応じて設けてよい。

```
spring-functionaltest-selenium/src/test/resources/testdata/{機能ID(小文字)}
```

### サーブレット分割規約

-   試験ごとに設定内容を独立させるため、DispatcherServletを分割して定義する。サーブレットの分割は、原則として中項目ID単位で行う。
    -   状況によって例外は認めるので、相談してください。

#### web.xml

```
    <servlet>
        <servlet-name>{中項目ID(小文字)}AppServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath*:META-INF/spring/spring-mvc-{中項目ID(小文字)}.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>{中項目ID(小文字)}AppServlet</servlet-name>
        <url-pattern>/{大項目ID(小文字)}/{中項目ID(数字部分)}/*</url-pattern>
    </servlet-mapping>
```

#### spring-mvc.xml(通常使用時用のXML)

-   &lt;context:exclude-filter&gt;要素を使用して、サーブレット分割したサーブレットで使用するコントローラーをコンポーネントスキャン対象から除外する。

```
    <context:component-scan base-package="jp.co.ntt.fw.spring.functionaltest.app">
        <context:exclude-filter type="assignable" expression="jp.co.ntt.fw.spring.functionaltest.app.flup.FLUP0102Controller"/>
    </context:component-scan>
```

#### spring-mvc.xml(分割用のXML)

-   共通のXML(spring-mvc-common.xml)を読み込む。
-   必要最低限のコンポーネント(ControllerやHelper)のみbean定義する
-   コンポーネントスキャンしない場合は、&lt;context:annotation-config
    /&gt;が必要。

```
    <import resource="classpath:META-INF/spring/spring-mvc-common.xml"/>

    <context:annotation-config />

    <bean class="jp.co.ntt.fw.spring.functionaltest.app.flup.FLUP0102Controller" />
    <bean class="jp.co.ntt.fw.spring.functionaltest.app.flup.FileUploadHelper" />
```

#### Controller

-   @RequestMappingのvalue属性には、分割したサーブレットに割り振ったサーブレットパスからの相対パス部分のみでよい。

```
//@RequestMapping("flup/0102") ← 分割したサーブレットに割り振ったサーブレットパスは指定不要。（指定すると動かない・・）
@Controller
public class FLUP0102Controller {

    @RequestMapping(value = "001", method = RequestMethod.GET)
    public String handle001() {
        return form();
    }

}
```

その他ルール・注意事項
------------
-   テスト実装は、Proxyサーバなどのローカルネットワークの環境に依存しないように実装すること。