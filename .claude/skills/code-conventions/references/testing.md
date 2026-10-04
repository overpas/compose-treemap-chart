# Testing

Arrange-Act-Assert pattern. Newlines between the blocks in unit tests.
Use /testing-on-the-toilet skill.
When testing compose code also use /compose-ui-testing-patterns skill.

For the commands that build and test the project, see the verification section of `AGENTS.md`.

## Where a test goes

- `src/commonTest/` of the core library — the tree model, the DSL and the layout algorithms. These
  tests run on every target and need no device.
- `src/androidDeviceTest/` of the Compose library — Compose UI tests of the chart. They run on an
  Android device or emulator.
- `src/androidTest/` of the View library — Espresso tests of the chart view. They run on an Android
  device or emulator.
- The benchmark module — performance of the chart in the Android sample. Add a benchmark only when
  the task is about performance.

Test layout logic in common tests, not through the UI. A UI test checks only what needs Compose:
that the chart and its node content are displayed and react to input.

## Test method naming

Use `` `backticked names with spaces` `` freely — except in `src/androidDeviceTest/` and `src/androidTest/`, where methods
must be camelCase.

## No helper functions in tests

Never define helper functions inside a test class — no builders/factories with default
arguments, no shared setup functions. A test case must be readable top to bottom without jumping to
another function to learn what the data actually is. Verbosity and duplication are the accepted
cost; do not "clean it up" into helpers later.

## Where tests are not needed

- Generated code and other code excluded from test coverage.
- Classes and functions made for preview, usually having "preview" in their name.
- The sample app, unless the task is about it.
- Top level and `object` variables and constants.
- Classes that fulfill the "helper" role for testing purposes.
