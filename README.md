## 環境構築の手順

### 準備

1. vscodeの拡張機能「Remote Development」を入れる
2. Ubuntuにgitが入ってるか確認

```bash
$ git version
# git version 2.38.0
```

### ビルド

1. Docker, Ubuntuを起動する
2. Ubuntu上で下記コマンド実行

```bash
$ git clone https://github.com/s-yuk/hf-backend.git
$ cd hf-backend
$ code .
#vscode起動
```

3. vscodeで｢ctrl+shift+p｣を押し、openと入力→｢Dev Container: Reopen in Container｣を選択

...ビルド中

...二回目から早くなる

vscodeのターミナルでバージョンが出ればOK

```bash
$ java --version
$ gradle -v
```

### Spring Boot ビルド
"/api/src/main/java/com/example/api/"のjavaファイル上で上の「Run Java」ボタンを押す

http://localhost:8000/greeting

でjsonが表示されれば成功