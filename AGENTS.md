# AGENTS.md

## General

### Do

- Use ASD-STE100.
- Be concise.
- When presenting any information to the user, when breaking it down, you should use 1-4 base
  elements/points. It pertains to explaining what you did, explaining different options to choose,
  or anything else. Any complex piece of information should consist of 1-4 root level elements,
  which in their turn can be expanded or broken down further, if prompted or required.
- Always start work in a new git branch from `develop`, unless the user names another base branch.
  Obey "Git".
- Don't leave any files unstaged and uncommitted. Either stage and commit or add to `.gitignore` if
  it makes sense.

### Don't

- Write any comments in the code
- Suppress any static analysis findings with `@Suppress` and don't baseline them

## Git

- Work only in a linked worktree under `.claude/worktrees/`, never in the main checkout. Parallel
  sessions in one checkout break each other's branches and uncommitted changes.
- If the session is in the main checkout, call `EnterWorktree` first. If it is already in a
  worktree, stay there and do not create a nested worktree.
- In the worktree, create a new branch from `develop` (or the base branch that the user names):
  `git checkout -b <branch> develop`.
- One branch per session, unless the user deviates from initial topic - in this case ask the user if
  you should continue working in the same branch or create a new one.
- At the end commit all the work. Keep the branch and report its name. Do not merge it, unless the
  user asks for it.
- Push the branch and open a PR into `develop`. Never push to `develop` or `main` directly.
- Fixes, corrections, improvements belong in the same branch.

## Verification

### L1: Build

```shell
./gradlew build
```

The build also runs static analysis, the ABI check and the common tests, including the Compose UI
tests on the desktop, iOS simulator, JS and Wasm targets.

### L2: Public API

```shell
./gradlew updateKotlinAbi
```

Run it only after an intended public API change of a library module and commit the changed ABI
dumps. An unintended change fails the ABI check in L1.

### L3: Coverage

```shell
./gradlew koverHtmlReport
```

### L4: Instrumented tests

```shell
./gradlew connectedCheck connectedAndroidDeviceTest
```

Run them on an emulator or device. The device tests also run the common tests, so a new Compose UI
test belongs in the common test source set, unless it needs an Android API.

## Self-editing

When editing .md files, including this file:

- Use 1-3 sentences per entry: the rule, plus a short reason when the reason is not obvious. When
  a change sets a convention, propose the edit unprompted, in the same turn.
- Never write in the first person. Use "the user", "the workspace" or the imperative.
- Only make additions to the agentic setup docs if: 1) it is required by the current setup
  explicitly; 2) you can provide strong arguments that not having them will hinder or disrupt the
  project in any way.
- Don't describe or reference the concrete modules, files, classes, functions currently present in
  this project. Refer to them as generic abstractions.
