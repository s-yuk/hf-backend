FROM ubuntu:22.04

ARG USERNAME=vscode
ARG USER_GROUP_NAME=workspace
ARG USER_UID=1000
ARG USER_GID=1000

ARG PKG="git vim curl unzip zip sudo"

SHELL ["/bin/bash", "-c"]

RUN apt-get update \
    && apt-get install -y ${PKG} \
    && groupadd --gid ${USER_GID} ${USER_GROUP_NAME} \
    && useradd --uid ${USER_UID} --shell /bin/bash --gid ${USER_GID} -m ${USERNAME} \
    && echo %${USER_GROUP_NAME} ALL=\(ALL\) NOPASSWD:ALL > /etc/sudoers.d/${USER_GROUP_NAME} \
    && chmod 0440 /etc/sudoers.d/${USER_GROUP_NAME}

ARG JAVA_VERSION=18.0.2-amzn
ARG GRADLE_VERSION=7.5

RUN su ${USERNAME} --command \
    'curl -s "https://get.sdkman.io" | bash \
    && source "${HOME}/.sdkman/bin/sdkman-init.sh" \
    && sdk install java "${JAVA_VERSION}" \
    && sdk install gradle "${GRADLE_VERSION}"'
