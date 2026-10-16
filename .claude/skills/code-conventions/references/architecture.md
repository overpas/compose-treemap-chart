# Architecture

This is a **Kotlin Multiplatform** library with **Compose Multiplatform** UI.

## Modules

- **Core library** — the tree model, its builder DSL and the layout algorithms (measurers) that
  turn weights into rectangles. It has no UI and no Compose dependency, so any UI toolkit can use
  it.
- **Compose library** — the treemap chart composable. The composition is the tree: each direct
  child sets its weight through a parent data modifier, and a nested chart is a group. It uses the
  measurers of the core library, not its tree model.
- **Sample** — a shared Compose UI module and one thin entry-point module or project per platform:
  Android, desktop, iOS, web (JS) and web (Wasm). Platform entry points hold no sample logic.
- **Benchmark** — an Android macrobenchmark that runs against the Android sample in its
  `benchmark` build type.
- **Convention plugins** — an included build with the shared Gradle configuration: publication and
  static analysis.

## Targets

Both library modules build for Android, desktop (JVM), iOS (arm64 and simulator arm64), JS and
Wasm. Put code in `commonMain`. Use a platform source set or `expect`/`actual` only when common
code cannot do the job, and implement it for every target.

## Public API

The library modules are published, so a public declaration is a promise to the users.

- Add a public declaration only when the users need it. Keep the rest `internal` or `private`.
- Do not rename, remove or change the signature of a public declaration unless the task asks for a
  breaking change. Say so in the PR when it does.
- Expose immutable types in the public API.

## Layout algorithms

A layout algorithm implements the shared measurer abstraction of the core library in its own
subpackage. It holds no Compose UI code, so it stays testable in common tests. The Compose
library gets the measurer from a composition local and never checks which algorithm it is.
