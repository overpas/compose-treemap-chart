# Architecture

This is a **Kotlin Multiplatform** library with **Compose Multiplatform** UI.

## Modules

- **Core library** — the tree model, its builder DSL and the layout algorithms (measurers) that
  turn a tree into rectangles. It has no UI and depends only on the Compose runtime.
- **Compose library** — the treemap chart composable. It draws the rectangles of a measurer and
  takes the caller's content for each node. It depends on the core library.
- **Views library** — an Android-only treemap chart for Android Views: a layout that places its
  children by weight with a measurer, and a view that builds such layouts from a tree. The measurer
  is a property of the view, not a composition local. It depends on the core library.
- **Sample** — a shared Compose UI module and one thin entry-point module or project per platform:
  Android, desktop, iOS, web (JS) and web (Wasm). Platform entry points hold no sample logic. A
  separate Android app samples the Views library; its screens copy the Compose sample screens.
- **Benchmark** — an Android macrobenchmark that runs against the Android sample in its
  `benchmark` build type. The sample has a large generated chart for it, and the measured blocks
  hold only chart work: app start and navigation go into the setup block. A second macrobenchmark
  runs the same scenarios against the Views sample, so the results of the two compare.
- **Microbenchmark** — a JVM-only kotlinx-benchmark module for the layout algorithms of the core
  library. It is not part of the build.
- **Convention plugins** — an included build with the shared Gradle configuration: publication and
  static analysis.

## Targets

The core and Compose libraries build for Android, desktop (JVM), iOS (arm64 and simulator arm64),
JS and Wasm. The Views library builds only for Android. Put code in `commonMain`. Use a platform source set or `expect`/`actual` only when common
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
