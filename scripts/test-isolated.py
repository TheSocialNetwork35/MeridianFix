#!/usr/bin/env python3
"""Run fork regression tests against a supplied, locally owned 26.3 server bundle.
MeridianFix addition, 2026-09-25. SPDX-License-Identifier: LGPL-3.0-or-later.
Usage: python3 scripts/test-isolated.py JDK_HOME SERVER_JAR JUNIT_CONSOLE_JAR ANNOTATIONS_JAR
This does not test NeoForge startup or mixin transformation. No game files are distributed.
"""
import hashlib
import os
from pathlib import Path
import subprocess
import sys
import zipfile

if len(sys.argv) != 5:
    raise SystemExit(__doc__)
jdk, server, junit, annotations = map(lambda x: Path(x).resolve(), sys.argv[1:])
root = Path(__file__).resolve().parents[1]
work = root / "build/isolated-regressions"
work.mkdir(parents=True, exist_ok=True)
if hashlib.sha1(server.read_bytes()).hexdigest() != "33680f5f2ac32864d6d7cf5e56a705fdb3e05f4c":
    raise SystemExit("Expected the official Minecraft 26.3 server bundle")
classpath = [junit, annotations]
with zipfile.ZipFile(server) as archive:
    for name in archive.namelist():
        if name.startswith(("META-INF/libraries/", "META-INF/versions/")) and name.endswith(".jar"):
            target = work / Path(name).name
            target.write_bytes(archive.read(name))
            classpath.append(target)
classes = work / "classes"
classes.mkdir(exist_ok=True)
cp = os.pathsep.join(map(str, classpath))
main = root / "src/main/java/org/embeddedt/modernfix"
sources = [main / p for p in ["resources/ZipPackIndex.java", "util/AtomicConfigFile.java", "util/SingleThreadedWorkerService.java"]]
sources += sorted((root / "src/test/java").rglob("*Test.java"))
subprocess.run([str(jdk / "bin/javac"), "-cp", cp, "-d", str(classes), *map(str, sources)], check=True)
subprocess.run([str(jdk / "bin/java"), "-cp", str(classes) + os.pathsep + cp,
                "org.junit.platform.console.ConsoleLauncher", "execute", "--scan-class-path=" + str(classes),
                "--reports-dir=" + str(root / "release/evidence/isolated-tests"), "--disable-ansi-colors"], check=True)
