# spring-kotlin-validation

Some library to release on Maven Central.

## Release

see https://github.com/mbogner/spring-boot-bom for more details.

### Build

Local:
```shell
./gradlew clean signMavenPublication publishToMavenLocal
```

see `~/.m2/repository/dev/mbo/spring-kotlin-validation` for the created content

Upload:
```shell
./gradlew clean signMavenPublication publishToMavenLocal publish
```

### Web Process

https://s01.oss.sonatype.org