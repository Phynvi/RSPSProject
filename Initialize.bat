@echo Off
title Server
java -XX:+DisableExplicitGC -noverify -cp bin;libs/* com.rs2.Server
pause