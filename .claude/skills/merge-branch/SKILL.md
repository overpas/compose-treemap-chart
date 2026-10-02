---
name: merge-branch
description: Merge the current session branch, or a branch that the user names, into master, then delete it, its worktree and other merged branches that the user names. For the session branch, return the session to the main worktree. Use only when the user calls /merge-branch or explicitly asks to merge a branch and delete it.
argument-hint: "[branch to merge] [delete: branch...] [into: base, default master]"
---

# Merge branch

Merge a branch into the base branch, then delete the branch and its worktree. Do all steps
without questions to the user. When a step says "stop", delete nothing and report the reason.

## Terms

- `BASE`: the `into:` branch in the arguments, else `master`.
- `B`: the branch to merge in the arguments, else the current branch of the session.
- `D`: the `delete:` branches in the arguments, else none.
- `M`: the main worktree, the parent directory of
  `git rev-parse --path-format=absolute --git-common-dir`.
- `S`: the worktree that has `B` checked out in `git worktree list --porcelain`. If there is no
  such worktree, `S` is empty.
- `OWN`: `S` is the worktree of this session, `git rev-parse --show-toplevel`, and `S` is not `M`.
- `T`: a temporary worktree that step 2 creates. At the start, `T` is empty.

## Steps

1. **Check.**
   - Stop if `B` is empty (detached HEAD) or `B` is `BASE`.
   - Stop if `S` is `M`. The main checkout must stay on `BASE`.
   - If `OWN`, commit all uncommitted work in `S` first, as the Git rules of the project tell.
   - If not `OWN`, stop if `S` is not empty. A different session has `B` checked out.
   - Stop if `git rev-list --count BASE..B` is `0`: there is nothing to merge.
   - If the session is in a worktree other than `M`, call `ExitWorktree` with `action: "keep"`.
     The session returns to `M`. The worktree guard of the session refuses `git -C M` from a
     worktree. If `ExitWorktree` reports that there is no worktree session, tell the user to
     start the next session in `M`.
2. **Update `B`.** If `git merge-base --is-ancestor BASE B` fails, `BASE` has new commits:
   - If `S` is empty, run `git -C M worktree add M/.claude/worktrees/merge-B B` and use this
     worktree as `S` and `T`. Use absolute paths in `S`, because edits in `M` are blocked.
   - Run `git merge BASE` in `S`.
   - Resolve all conflicts in `S`, keeping the intent of both sides, and commit.
   - Run `./gradlew detekt` and the tests of the modules that the merge touched. The verification
     section of `AGENTS.md` lists the commands. Fix failures in `S` and commit.
3. **Merge in `M`.**
   - Stop if `git -C M status --porcelain` is not empty.
   - Stop if `git worktree list --porcelain` shows `BASE` checked out in a worktree other than
     `M`.
   - Run `git -C M checkout BASE`.
   - If `git merge-base --is-ancestor BASE B` fails again, another session merged meanwhile:
     go back to step 2.
   - Run `git -C M merge --no-ff B -m "Merge branch 'B' into BASE"`.
4. **Remove the worktree of `B`.**
   - If `OWN`, run `git -C M worktree remove S`.
   - If `T` is not empty, run `git -C M worktree remove T`.
   - If `git worktree remove` refuses because the worktree is locked, run
     `git -C M worktree unlock` on it first. Keep a worktree that has uncommitted changes and
     report it.
5. **Delete the branches** from `M`:
   - `git -C M branch -d B`.
   - If `S` was made by `claude -w <name>` or `EnterWorktree` and the branch `worktree-<name>`
     still exists, `git -C M branch -d worktree-<name>`.
   - `git -C M branch -d` for each branch in `D`. Keep a branch in `D` that a worktree has
     checked out.
   - Use `-d` only. If git refuses, keep the branch and report it.
6. **Report** the merge commit (`git -C M log --oneline -1`), the deleted branches, the branches
   kept, and that `M` is on `BASE`. Tell that new edits need `EnterWorktree` first, because edits
   in `M` are blocked.
