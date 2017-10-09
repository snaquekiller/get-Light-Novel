#!/usr/bin/env bash

declare color_ok="\033[32m"
declare color_info="\033[0;33m"
declare color_error="\033[0;31m"
declare color_reset="\033[0m"

cmd() {
  printf "${color_info} - ${color_reset}$1 "
  _ret=$(eval $2)
  if [ $? -ne 0 ]; then
    printf "${color_error}✗${color_reset}\n"
    echo "$_ret"
    exit 1
  else
    printf "${color_ok}✓${color_reset}\n"
    if [[ $3 == "1" ]]; then
      echo "$_ret"
    fi
  fi
}


