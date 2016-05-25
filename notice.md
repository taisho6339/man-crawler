- IoCコンテナとDIコンテナの違いってなんだろう => 同じ
- ConfigurableApplicationContextとかClassPathXmlApplicationContextの違いってなんやろう
- そもそもContextってなんだよ
- Viewの解決ができない

- RequestMappingされたメソッドでの返り値ってどう使われるのか
 - 定義したControllerによる。RESTならjson変換されるし、ControllerならViewにレンダリングされる

- JPAって何
 - ORMのAPIインターフェース。

- configureって何をしているのか

- ServiceとかComponentとかRepositoryとか一体なに
　- Service=Facadeパターン、ServiceはComponentの一種。Repositoryはデータアクセスをラップする。

- RequestMapしたメソッドの引数ってなに設定してもいいの？どうやってコンテナは振り分けているのか。
 - フォームのパラメータ群をどうやってJavaオブジェクトに変換しているのか
    -> ModelAttributeを使う。リクエストハンドル時にModelを受け取って、これに入れて返す。これにはマッピングするオブジェクトの情報などメタ情報的なものが入っている。

- Hibernateってなんだよ -> ORM
 - JPAとの関係は？？

- まずはURLでメソッドをマッピングして、その後、引数にリクエストボディを当てはめ込んだりする。
- おそらくリクエストについてのメタ情報をModelクラスとして持っている。
  formからのリクエスト時に、このメタ情報を用いてバインドしている。