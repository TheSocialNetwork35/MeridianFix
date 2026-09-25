# Third-party notices

ModernFix and its contributors retain all original rights and credits (see ATTRIBUTION.md).
The configuration system includes historical Sodium-derived code under LGPL-3.0,
as identified by the upstream README: https://github.com/CaffeineMC/sodium.

`org.embeddedt.modernfix.util.TimeFormatter` explicitly derives from Guava's
`Stopwatch` and `Platform` classes. Those sources carry:

- Copyright (C) 2008 The Guava Authors (`Stopwatch`).
- Copyright (C) 2009 The Guava Authors (`Platform`).
- Apache License, Version 2.0; full text supplied in licenses/Apache-2.0.txt.
- Source: https://github.com/google/guava/tree/v21.0/guava/src/com/google/common/base

ModernFix adapted the formatting routines into TimeFormatter. ModernFix Reforged retains
the implementation and adds these explicit notices. This notice does not replace
the LGPL terms of the overall fork or any other existing source-specific rights.

Dependencies provided by Minecraft or the loader are not bundled into this mod.
Only ModernFix's annotations subproject is embedded by this build. The annotation
processor is a build tool and is not embedded into the runtime mod JAR.

`org.embeddedt.modernfix.textures.StbStitcher` explicitly attributes an adaptation
from **lwjgl3ify**, by **eigenraven and GTNewHorizons contributors**, under LGPL v3:
https://github.com/GTNewHorizons/lwjgl3ify/blob/f21364cd3d178aef863458a2faa1f5718a4e350d/src/main/java/me/eigenraven/lwjgl3ify/textures/StbStitcher.java
The linked revision's LICENSE is LGPL v3; that license and its incorporated GPL v3
are supplied in the root LICENSE. The source link is preserved in the class.
The original project's terms remain applicable to this inherited component.
