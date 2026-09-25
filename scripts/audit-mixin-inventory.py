#!/usr/bin/env python3
"""MeridianFix addition, 2026-09-25; LGPL-3.0-or-later.
Source inventory only: does not claim runtime Mixin verification.
"""
import json
import re
from pathlib import Path

root = Path(__file__).resolve().parents[1]
entries = []
for path in sorted((root / "src/main/java").rglob("*.java")):
    source = path.read_text()
    match = re.search(r"@Mixin\((.*?)\)\s", source, re.S)
    if match:
        entries.append({"source": str(path.relative_to(root)),
                        "target_declaration": " ".join(match.group(1).split()),
                        "injection_declarations": re.findall(r'method\s*=\s*"([^"]+)"', source),
                        "runtime_status": "NOT_DETERMINED_BY_STATIC_INVENTORY"})
output = root / "release/evidence/mixin-inventory.json"
output.parent.mkdir(parents=True, exist_ok=True)
output.write_text(json.dumps(entries, indent=2) + "\n")
print(f"Inventoried {len(entries)} mixin declarations; see TEST_REPORT.md for the separate runtime audit results.")
