---
name: update-detekt-rules
description: Update the custom detekt rule set jars (overpas-* rule sets from github.com/overpas/detekt-rules) to a newer release. Use when the user asks to update, upgrade or bump the custom detekt rules or their jars, or names a new detekt-rules release or version.
---

# Update the detekt rules

The custom rule sets come from the GitHub releases of `overpas/detekt-rules`. Each release has one
jar per rule set as an asset. The project commits the jars of one version in `config/detekt/plugins/`
and configures the rules in `config/detekt/detekt.yml` and `config/detekt/detekt-type-resolution.yml`.

## 1. Find the versions

- Current: the version in the jar names in `config/detekt/plugins/`.
- Target: the version that the user names. Else the latest release:
  `gh release list -R overpas/detekt-rules --limit 5`.
- If they are equal, stop and tell the user.

## 2. Check the detekt version

```shell
gh api 'repos/overpas/detekt-rules/contents/gradle/libs.versions.toml?ref=v<target>' --jq .content | base64 -d | grep '^detekt = '
grep '^detekt = ' gradle/libs.versions.toml
```

If the versions are different, stop and tell the user. The jars work only with the detekt version
that they are built against.

## 3. Find the changes

- Read `CHANGELOG.md` at the target tag:
  `gh api 'repos/overpas/detekt-rules/contents/CHANGELOG.md?ref=v<target>' --jq .content | base64 -d`.
- Read the source diff of the rules, because the changelog can be incomplete:
  `gh api repos/overpas/detekt-rules/compare/v<current>...v<target> --jq '.files[] | select(.filename | startswith("rules/")) | "=== \(.filename)\n\(.patch)"'`.
- List each new, renamed or removed rule, rule set id and option, and each changed default. Find the
  defaults in the `@Configuration` properties and the ids in the `RuleSetProvider` classes.

## 4. Replace the jars

```shell
gh release download v<target> -R overpas/detekt-rules -p 'detekt-rules-*.jar' -D config/detekt/plugins
gh release view v<target> -R overpas/detekt-rules --json assets --jq '.assets[] | "\(.digest | sub("sha256:"; ""))  config/detekt/plugins/\(.name)"' > <scratch file>
shasum -a 256 -c <scratch file>
git rm -q config/detekt/plugins/*-<current>.jar
git add config/detekt/plugins
```

If a checksum does not match, delete the downloaded jars and stop. At the end, the folder must hold
one jar per rule set, all of the target version. Two versions on the plugin classpath conflict,
because they have the same rule set ids and classes.

## 5. Update the config

Apply each change from step 3 to `detekt.yml`. A rule that needs type resolution (it implements
`RequiresAnalysisApi`) is configured in `detekt-type-resolution.yml` instead.

- Renamed or removed rule, option or rule set id: rename or remove its entry. Detekt fails on a config
  key that no rule set knows.
- Changed default: if the config sets the option, nothing changes. Else tell the user how the
  behavior changes and ask if the config must keep the old value.
- New rule: ask the user if it must be active, and with which severity.

## 6. Verify

```shell
./gradlew --stop
./gradlew detekt
```

Stop the daemon first, because it keeps the classes of the old jars. If detekt reports new findings,
show them to the user and ask before fixing code.

## 7. Commit

Commit the jars and the config on the session branch with the message
`Update the detekt rules to <target>`. In the body, list the changes from step 3 and what was done
for each of them in the config.
