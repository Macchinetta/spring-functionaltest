機能ID命名
----------

- ルール

1. ガイドラインの機能名の英語訳を作成(e.g. Double Submit Protection,Data Access MyBatis2,Internationalization)
2. 単語の最後が数字の場合は残す。（この後のルールで削ることはなく最後まで残す。）
3. 単語から先頭以外の母音を削除(e.g. Dbl Sbmt Prtctn,Dt Accss MyBts2,Intrntnlztn)
4. 複数の単語が残った場合、4以上の最大公約になるように数を減らす。4以上になった場合は最後から消す。(e.g. Db Sb Pr,Dt Ac M2,Intr)
5. 残った単語区切りの後ろから桁を減らす。最終形を作成(e.g. DBSP,DAM2,INTR)


| 機能ID | 機能名(英語) | 機能名(日本語) |
| ------------- |:------------- |:----- |
| AJAX | Ajax | Ajax |
| APLY | Application Layer| アプリケーション層 |
| ATHN | Authentication | 認証 |
| ATHR | Authorization| 認可 |
| BNMP | Bean Mapping | Beanマッピング(MapStruct)|
| CDLS | Code List| コードリスト |
| CMMN | Common | 共通 |
| CSPR | CSRF Protection| CSRF対策 |
| DAM3 | Data Access MyBatis3 | データベースアクセス（Mybatis3編） |
| DBSP | Double Submit Protection | 二重送信防止 |
| DJPA | Data Access JPA | データベースアクセス（JPA編） |
| DMLY | Domain Layer | ドメイン層 |
| DNTA | Date Operation(Date and Time API) | 日付操作(Date and Time API)|
| DTAC | Data Access Common | データベースアクセス（共通編） |
| EMAL | E-mail (SMTP) | E-mail送信(SMTP) |
| ENCR | Encryption | 暗号化 |
| EXCN | Exclusion Control| 排他制御 |
| EXHN | Exception Handling | 例外ハンドリング |
| EXMP | Example| サンプル |
| FLDW | File Download| ファイルダウンロード |
| FLUP | File Upload| ファイルアップロード |
| HLCH | Health Check | ヘルスチェック |
| INLY | Infrastructure Layer | インフラ層 |
| INTR | Internationalization | 国際化 |
| JMSS | Java Message Service | JMS |
| LGGN | Logging| ロギング |
| MSMN | Message Management | メッセージ管理 |
| OTH2 | OAuth2.0 | OAuth2.0 |
| PGNT | Pagination | ページネーション |
| PRMN | Property Management| プロパティ管理 |
| REST | Rest | Rest |
| RSCL | Rest Client | RESTクライアント |
| SOAP | SOAP (Server/Client) | SOAP通信(サーバ/クライアント) |
| SPSC | Spring Security| Spring Security|
| SPSM | Spring Security Session Management | Spring Securityのセッション管理 |
| SSMN | Session Management | セッション管理 |
| STPR | String Processing | 文字列操作|
| SYDT | System Date| システム時刻 |
| THLY | Thymeleaf Layout | Thymeleafにおける画面レイアウト|
| THYM | Thymeleaf | Thymeleaf |
| VLDT | Validation | 入力チェック |
| XSPR | XSS Protection | XSS対策 |

- 共通(Common)は機能としてはないが、試験アプリを作る上で必要なので、機能定義しておく。
- サンプル(Example)はテストに直接関係ないが、一応機能定義しておく。

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

- jp.co.ntt.fw.spring.functionaltest

#### artifactId

| artifactId |説明 |
|:------------- |:----------------- |
| spring-functionaltest-env | 環境定義 |
| spring-functionaltest-initdb | データベースを初期化するためのSQLファイルを管理 |
| spring-functionaltest-selenium | seleniumプロジェクト |
| ajax | Ajax の試験プロジェクト |
| aply | アプリケーション層の実装 の試験プロジェクト |
| athn | 認証 の試験プロジェクト |
| athr | 認可 の試験プロジェクト |
| bnmp | Beanマッピング(MapStruct) の試験プロジェクト |
| cdls | コードリスト の試験プロジェクト |
| cspr | CSRF対策 の試験プロジェクト |
| dam3 | データベースアクセス（MyBatis3編） の試験プロジェクト |
| dbsp | 二重送信防止 の試験プロジェクト |
| djpa | データベースアクセス（JPA編） の試験プロジェクト |
| dmly | ドメイン層 の試験プロジェクト |
| dnta | 日付操作(JSR-310 Date and Time API) の試験プロジェクト |
| dtac | データベースアクセス（共通編） の試験プロジェクト |
| emal | E-mail送信(SMTP) の試験プロジェクト |
| encr | 暗号化 の試験プロジェクト |
| excn | 排他制御 の試験プロジェクト |
| exhn | 例外ハンドリング の試験プロジェクト |
| fldw | ファイルダウンロード の試験プロジェクト |
| flup | ファイルアップロード の試験プロジェクト |
| hlch | ヘルスチェック の試験プロジェクト |
| intr | 国際化 の試験プロジェクト |
| jmss | JMS(Jakarta Messaging) の試験プロジェクト |
| lggn | ロギング の試験プロジェクト |
| msmn | メッセージ管理 の試験プロジェクト |
| oth2-client | OAuth2.0 の試験プロジェクト(クライアントサーバ側) |
| oth2-resource | OAuth2.0 の試験プロジェクト(リソースサーバ側) |
| pgnt | ページネーション の試験プロジェクト |
| prmn | プロパティ管理 の試験プロジェクト |
| rest | RESTful Web Service の試験プロジェクト |
| rscl | RESTクライアント（HTTPクライアント） の試験プロジェクト |
| soap-client | SOAP Web Service（サーバ/クライアント） の試験プロジェクト(クライアント側) |
| soap-client | SOAP Web Service（サーバ/クライアント） の試験プロジェクト(クライアント側) |
| soap-model | SOAP Web Service（サーバ/クライアント） の試験プロジェクト(モデル) |
| soap-webservice | SOAPサーバが提供するWebサービスのインターフェースを格納するプロジェクト |
| spsc | Spring Security概要 の試験プロジェクト |
| spsc-hsts | HSTS（HTTP Strict Transport Security） の試験プロジェクト |
| spsm | Spring Securityのセッション管理 の試験プロジェクト |
| ssmn | セッション管理 の試験プロジェクト |
| stpr | 文字列操作 の試験プロジェクト |
| sydt | システム時刻 の試験プロジェクト |
| thly | Thymeleafにおける画面レイアウト の試験プロジェクト |
| thym | テンプレートエンジン(Thymeleaf) の試験プロジェクト |
| vldt | 入力チェック の試験プロジェクト |
| xspr | XSS対策 の試験プロジェクト

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

- 例)
  {basePackage}/domain/service/flup、{basePackage}/domain/repository/flup

#### アプリケーション層

```
${basePackage}/app/機能ID(小文字)
```

- 例)
  \${basePackage}/app/flup

#### 機能試験(Selenium)のパッケージ {#機能試験Seleniumのパッケージ}

```
${basePackage}/selenium/機能ID(小文字)
```

- 例)
  \${basePackage}/selenium/flup

### クラス規約

#### 基本ポリシー

- Controllerの呼び出しまでは、テストケースとの関連付けを行うために、機械的な命名規約を採用する。

- Controller以降の処理は、試験アプリの題材として採用したユースケースを意識した命名規約を採用する。

- DI対象のフィールドはパッケージプライベートにする。アノテーションは@Injectを使用する。同じ型が複数定義されている場合は@Named("bean名")を追加する

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

- メッセージはapplication-messages.propertiesに定義して、プログラム中ではメッセージキーのみ扱う。

NG

```
throw new BussinessException("This is sample error message!");
```

OK

```
e.sf.em.8001=This is sample error message!
```

```
ResultMessages messages = ResultMessages.error().add("e.sf.em.8001");
thrown new BussinessaException(messages);
```

- 日付の生成はClockFactoryを使用すること。

#### ドメイン層

- モデル(主にEntity)は機械的な名前ではなく、実際にありそうなクラス名(Entity名)とすること。
  - 例) Cart, CartItemやBook, Authorなど・・

- サービスは機械的な名称(例えば、機能ID+Service)ではなく、試験アプリの題材として扱うユースケースを意識した名前にすること。
  - 例) CartServiceやBookManagementServicなど・・

- SharedServiceを作成する場合は複数のServiceから呼び出されることを考慮して作ること。特定のコンテキストに依存してはいけない。外部仕様をJavaDocに明記すること。

#### アプリケーション層

- コントローラは、原則「中項目ID+Controller」とすること。

  ```
  @RequestMapping("/flup/0101")
  @Controller
  public class FLUP0101Controller {
      // 省略...
  }
  ```

- コントローラの処理メソッドは、原則「"handle"+CaseID」とする。テストケースの中で複数の処理メソッドが必要な場合は、「"handle"+CaseID+処理を識別するリクエストパラメータ名」とする。

  ```
  @GetMapping(value = { "001" })
  public String handle001() {
      return "flup/singleFileUploadForm";
  }

  @PostMapping(value = { "001" }, params = "upload")
  public String handle001Upload(FileUploadForm form, Model del) throws UnsupportedEncodingException, IOException {
      fileUploadHelper.bindToModel(form.getMultipartFile, model);
      return "flup/contentFileUploadComplete";
  }
  ```

- 処理メソッドの引数にHttpServletRequestなどガイドライン「アプリケーション層の実装#ハンドラメソッドの引数について」で使用しないよう言及されている型は使用しないこと。どうしても必要な場合は要相談。

- 複数のテストケースで処理メソッド(のロジック)を共有したい場合は、privateメソッドを作って呼び出すこと。コントローラを跨いで共有したい場合は、ヘルパークラスにメソッドを作成すること。

  ```
  @GetMapping(value = "001")
  public String handle001() {
      return form();
  }

  @PostMapping(value = "001", params = "upload")
  public String handle001Upload(ContentUploadForm form, Model model) throws UnsupportedEncodingException, IOException {
      return upload(form, model);
  }

  @GetMapping(value = "003")
  public String handle003() {
      return form();
  }

  @PostMapping(value = "003", params = "upload")
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

- ヘルパークラスは、機械的な名前(例えば、機能ID+Helper)ではなく、役割がわかる名称とすること。
  - 例) FileUploadHelperとか・・



- フォームクラスは、機械的な名称(例えば、機能ID+Form)ではなく、役割がわかる名称とすること。
  - 例) ContentUploadFormとか・・

- フォームオブジェクト &lt;==&gt;
  ドメインオブジェクトの変換にはできるかぎりMapStructを使用すること。

- Controllerから直接Repositoryを呼び出してはいけない。必ずController-&gt;Service-&gt;Repositoryというレイヤにすること

- アプリケーション層のクラス(ControllerやHelper等)で@Transactionalを使用してはいけない

#### テストケース

- Seleniumのテストケースクラスは、機能IDの元となった英語名称+Testとすること。
  ※原則１クラスとするが、必要に応じて細分化してもよい。
  - 例) FileUploadTest

  ```
  public class FileUploadTest extends FunctionTestSupport {
      // 省略
  }
  ```

- テストケースメソッドは、「"test" + テストケースID(中項目ID+CaseID」とする。

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

- 他のテストケースで確認できるものについては、@Ignoreアノテーションを付与して理由を記載する。

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

- Selemiumでの自動化ができないものについては、@Ignoreアノテーションを付与して理由を記載する。＋@Testを付与しない。

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

- サーブレットパス(URL)は、以下のルールで付与すること。

  ```
  機能ID(小文字)/中項目ID(数字部分)/CaseID
  ```

- 例) flup/0101/001
- ひとつのCaseIDに複数のリクエストが含まれる場合は、リクエストパラメータを使って識別すること。
  - 例) flup/0101/001?form, flup/0101/001?upload, flup/0101/001?complete とか・・
- ``@RequestMapping``,``@GetMapping``,``@PostMapping``アノテーションに指定する値となる。

#### JSP

- JSPは、以下のルールで作成すること。

  ```
  ${webapps}/WEB-INF/views/jsp/機能ID(小文字)/XXX.jsp
  ```

- JSP名は機械的な名前(テストケースIDなど)ではなく、試験アプリの題材として扱うユースケースを意識した名前にすること。
  - 例)contentUploadForm, contentUploadCompleteとか・・

- \${webapps}/WEB-INF/views/index.jsp
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

- 機能毎にindex.jspを作成し、テストケース一覧のメニューを作成する。雛形と具体例は以下の通り。
  - \${webapps}/WEB-INF/views/index.jsp

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

- CSSは、以下のルールで作成すること。

  ```
  ${webapps}/resources/app/css/機能ID(小文字).css
  ```

- 例) \${webapps}/resources/app/css/flup.css
- CSSが不要な場合でも、必ず配置してください。
- テストアプリケーション全体に適用するスタイルについては、\${webapps}/resources/app/css/cmmn.css に定義してください。

#### Javascript

- Javascriptは、以下のルールで作成すること。

  ```
  ${webapps}/resources/app/js/機能ID(小文字).js
  ```

- 例) \${webapps}/resources/app/js/flup.js
- Javascriptが不要な場合でも、必ず配置してください。
- テストアプリケーション全体で使用するfunctionについては、\${webapps}/resources/app/js/cmmn.js に定義してください。

### Message ID規約

#### Result Message

- 処理結果メッセージは、以下のルールで作成すること。

  ```
  {メッセージタイプ}.sf.{機能ID(小文字)}.{エラーレベル}{連番(0から開始)}
  ```

- 例) e.sf.fu.8000
- 各項目の説明は、ガイドライン「メッセージ管理#メッセージID体系」を参照。

#### Title Message

- タイトルメッセージは、以下のルールで作成すること。

  ```
  title.{機能ID(小文字)}.{JSP名(拡張子抜き)}
  ```

- 例) title.flup.index

#### Label Message

- ラベルメッセージは、以下のルールで作成すること。

  ```
  label.sf.{機能ID(小文字)}.{物理項目名}
  ```

- 例) label.sf.flup.staffName

### テストデータ規約

#### テーブル

- 表命名規約
  - 接頭語（英小文字1文字＋'\_'）＋エンティティを示す名前（全英小文字＋数字、区切りは'\_'を使用。）
  - 全ての文字数で30文字以内

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

- 結合試験環境のDDL及びDMLは以下のファイルに定義する。
  - 共通的なテーブルについては、機能IDの部分が「cmmn」となっているファイルに定義する。

```
spring-functionaltest-initdb/src/main/sqls/{DB名}/00{連番3桁(0開始)}_schema-{機能ID(小文字)}.sql
spring-functionaltest-initdb/src/main/sqls/{DB名}/01{連番3桁(0開始)}_procedure-{機能ID(小文字)}.sql
spring-functionaltest-initdb/src/main/sqls/{DB名}/02{連番3桁(0開始)}_dataload-{機能ID(小文字)}.sql
```

#### カラム

- カラム命名規約
  - カラム内容を示す名前（全英小文字＋数字、区切りは'\_'を使用。）
  - 全ての文字数で30文字以内

| 例 |
|:--------- |
| category\_id |

#### ファイル(サーバ側)

- ローカル環境用のテスト用ファイルは以下のディレクトリ(配下)に格納する。
  - サブディレクトリは必要に応じて設けてよい。

```
{機能ID(小文字)}/src/main/resources/testdata/{機能ID(小文字)}
```

- 結合試験環境用のテスト用ファイルは以下のディレクトリ(配下)に格納する。
  - サブディレクトリは必要に応じて設けてよい。

```
spring-functionaltest-env/configs/{環境名}/resources/testdata/{機能ID(小文字)}
```

#### ファイル(テストケース側)

- テスト用ファイルは以下のディレクトリ(配下)に格納する。
  - サブディレクトリは必要に応じて設けてよい。

```
spring-functionaltest-selenium/src/test/resources/testdata/{機能ID(小文字)}
```

### サーブレット分割規約

- 試験ごとに設定内容を独立させるため、DispatcherServletを分割して定義する。サーブレットの分割は、原則として中項目ID単位で行う。
  - 状況によって例外は認めるので、相談してください。

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

その他ルール・注意事項
------------
- テスト実装は、Proxyサーバなどのローカルネットワークの環境に依存しないように実装すること。