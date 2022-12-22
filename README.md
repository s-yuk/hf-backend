## ディレクトリの責務

/controller
- エンドポイントの設定

/service
- serviceクラスのinterfaceを作る

/service/Impl
- serviceの実装
  - ロジックを書く

/model/entity
- テーブルになるクラス

/model/form
- formに入力される値を設定するクラス

/repo
- sqlを発行する
  - ORM

/config
- Spring Securityの設定
- JWTの生成、デコード、フィルター