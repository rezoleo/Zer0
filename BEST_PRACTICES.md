Best Practices
==============

## Shell scripts

Follow [this guide](https://dev.to/thiht/shell-scripts-matter), in particular:

  - Use [`shellcheck`](https://github.com/koalaman/shellcheck), even better use it as a linter in your editor.
  - Use the "unofficial strict mode" everywhere (more information [here](http://redsymbol.net/articles/unofficial-bash-strict-mode/)).
  ```bash
  #!/bin/bash
  set -euo pipefail
  IFS=$'\n\t'
  ```
  - Cleanup after yourself with a `trap`, that way you can delete temporary directories, restart services or release locks whether the script stops gracefully or on an error.
  ```bash
  cleanup() {
      # ...
  }
  trap cleanup EXIT
  ```
  - Log your scripts with this snippet (use it with `info "Executing this..."`).  
  You can `source Utils/scripts/sources/logger.sh` if you want it included easily.
  ```bash
  readonly LOG_FILE="/tmp/$(basename "$0").log"
  info()    { echo "[INFO]    $@" | tee -a "$LOG_FILE" >&2 ; }
  warning() { echo "[WARNING] $@" | tee -a "$LOG_FILE" >&2 ; }
  error()   { echo "[ERROR]   $@" | tee -a "$LOG_FILE" >&2 ; }
  fatal()   { echo "[FATAL]   $@" | tee -a "$LOG_FILE" >&2 ; exit 1 ; }
  ```
  - If you want to debug, run the script with `bash -x`, or add `set -x` at the beginning of the script; it displays all commands being executed
