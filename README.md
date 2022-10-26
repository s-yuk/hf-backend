## 環境構築の手順

### 準備
1. vscodeの拡張機能「Remote Development」を入れる
2. ubuntuにgitが入ってるか確認
``` bash
$ git version #バージョンが出ればいい $は打たん
```
3. 学校でやる場合はDockerとgitのプロキシ設定

### GithubのリポジトリををCloneする
1. Docker, ubuntuを起動する
2. ubuntu上で下記コマンド実行
``` bash
$ git clone https://github.com/s-yuk/hf-backend.git
$ cd hf-backend
$ code . #vscode起動
```
3. vscodeでctrl + shift + pを押し、openと入力→Dev Container: Reopen in Containerを選択


...ビルド中...二回目からは早くなる...ファイルが表示されてきたら成功...

vscodeのターミナルでバージョンが出ればOK
``` bash
$ java --version
$ gradle -v
```
