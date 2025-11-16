# 1. ビルドステージ（軽量なJREイメージを使用）
FROM azul/zulu-openjdk:21

# コンテナがリッスンするポートを指定します（Spring Bootのデフォルトは8080）
EXPOSE 8080

# 環境変数 PORT を設定します。
# Cloud Runはコンテナのポートを自動的にこの環境変数で上書きします。
ENV PORT 8080

# 実行可能なJARファイルをコンテナにコピー
COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar

# コンテナ起動時にSpring Bootアプリケーションを実行
ENTRYPOINT ["java","-jar","/app.jar"]