# Publishing
The Publish workflow publishes a GitHub release to Maven Central. The release tag must match `lib.version` in `gradle.properties`.

To publish manually, set these Gradle properties as environment variables and run `./gradlew publishToMavenCentral`. Never put them in the project `gradle.properties`.
```shell
export ORG_GRADLE_PROJECT_mavenCentralUsername="<Central Portal user token name>"
export ORG_GRADLE_PROJECT_mavenCentralPassword="<Central Portal user token password>"
export ORG_GRADLE_PROJECT_signingInMemoryKey="$(gpg --export-secret-keys --armor <key id>)"
export ORG_GRADLE_PROJECT_signingInMemoryKeyId="<last 8 characters of the key id>"
export ORG_GRADLE_PROJECT_signingInMemoryKeyPassword="<key password>"
```
Set `ORG_GRADLE_PROJECT_mavenCentralPublishingType=USER_MANAGED` to release the deployment by hand at https://central.sonatype.com/publishing/deployments. Run `./gradlew validateMavenCentralBundle` to check the publications without an upload.
