# ---------- build stage ----------
FROM maven:3.8-openjdk-17 AS builder
WORKDIR /app

COPY pom.xml .
RUN mvn -q dependency:go-offline

COPY src ./src
# ★ WAR を作る（テストは任意でスキップ可）
RUN mvn -B -DskipTests clean package \
 && ls -l target

# ---------- runtime stage ----------
FROM icr.io/appcafe/open-liberty:kernel-slim-java17-openj9-ubi

# サーバー設定 (server.xml, jvm.options など)
COPY --chown=1001:0 src/main/liberty/config/ /config/

# 必要な Liberty 機能をインストール
RUN features.sh

# アプリケーション WAR を配置
# dropins に置けば server.xml の application 設定は不要
COPY --chown=1001:0 --from=builder /app/target/*.war /config/dropins/

# キャッシュ作成・最終調整
RUN configure.sh
