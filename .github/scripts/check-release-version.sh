#!/usr/bin/env bash
set -euo pipefail

tag="${1:?Usage: check-release-version.sh <release tag>}"
lib_version="$(sed -n 's/^lib\.version=//p' gradle.properties)"
tag_version="${tag#v}"

if [ -z "$lib_version" ]; then
  echo "::error::lib.version is not set in gradle.properties"
  exit 1
fi

if [ "$lib_version" != "$tag_version" ]; then
  echo "::error::Release tag $tag does not match lib.version=$lib_version"
  exit 1
fi

echo "Release tag $tag matches lib.version=$lib_version"
