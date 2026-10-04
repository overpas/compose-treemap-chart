# Gradle configs

## Use implementation() by default

Use `implementation()` instead of `api()`. The only exception: a dependency whose types appear in
the public API of a published library module is declared with `api()`.

## Extract common configuration to convention plugins

Each convention plugin should be responsible for one thing, e.g. static analysis, publication, etc.

## Versions

All dependency and plugin versions live in the version catalog. Shared numbers, such as SDK levels
and the JVM version, come from `gradle.properties`. Never write a version in a build script.

## Sorting

Applied plugins: `id()` plugins first, then `alias()` plugins, each group sorted alphabetically.

The source sets' dependencies blocks must be sorted alphabetically by source set name.

The dependencies of one configuration stay together. Inside it, the order is projects, libs,
test-only libs, and each group is sorted alphabetically.
