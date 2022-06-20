# Second Summary

## git command

### Setup

```bash
$ git config --global user.name "[firstname lastname]"
# set a name that is identifiable for credit when review version history

$ git config --global user.email "[vaild-email]"

$ git config --global color.ui auto
```

### Setup & Init

```bash
$ git init

$ git clone [url]
```

### Stage & Snapshot

```bash
$ git status

$ git add [file]

$ git reset [file]

$ git diff

$ git diff --staged

$ git commit -m "[descriptive message]"
```

### Branch & Merge

```bash
$ git branch

$ git branch [branch-name]

$ git merge [branch]

$ git log
```

### Inspect & Compare

```bash
$ git log

$ git log branchB...branchA
# show the commits on branchA that are not on branchB

$ git log --follow [file]
# show the commits that changed file, even across renames

$ git diff branchB...branchA

git show [SHA]
```

### Tracking path changes

``` bash
$ git rm [file]

$ git mv [existing-path] [new-path]

$ git log --stat -M
```

### Share & Update

```bash
$ git remove add [alias] [url]

$ git fetch [alias]

$ git merge [alias]/[branch]

$ git push [alias] [branch]

$ git pull
```

### Rewrite history

```bash
$ git rebase [branch]

$ git reset --hard [commit]
```

### Temporary commits

```bash
$ git stash

$ git stash list

$ git stash pop

$ git stash drop
```
