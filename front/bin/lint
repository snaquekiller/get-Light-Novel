#!/usr/bin/env bash

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
source $SCRIPT_DIR/utils.sh

cmd \
 "Checking lint" \
 "$SCRIPT_DIR/../node_modules/.bin/eslint --ext .jsx --ext js $SCRIPT_DIR/../src"
