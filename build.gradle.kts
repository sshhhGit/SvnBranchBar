plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.17.3"
}

group = "com.sshhhdev.svnbranchbar"
version = "1.0.0"

repositories {
    mavenCentral()
}

intellij {
    version.set("2023.3")        // 사용하는 IntelliJ 버전에 맞게 설정
    type.set("IC")               // IC: IntelliJ Community Edition
    plugins.set(listOf())        // 필요하면 플러그인 추가
}

tasks {
    patchPluginXml {
        version.set(project.version.toString())
        sinceBuild.set("233")    // 2023.3 버전 빌드 넘버
        untilBuild.set("251.*")  // 2024.1까지 대응되도록
    }

    runIde {
        // ideDirectory.set(file("경로")) ← 별도로 커스텀 경로 지정할 때만
    }
}
