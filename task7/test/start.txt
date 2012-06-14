start cmd /C javac Java1.java
PAUSE
start cmd /C java Java1 ^>Java2.java
PAUSE
start cmd /C javac Java2.java
PAUSE
start cmd /C java Java2 ^>Sharp3.cs
PAUSE
start cmd /C csc Sharp3.cs
PAUSE
start cmd /C Sharp3.exe ^>Sharp4.cs
PAUSE
start cmd /C csc Sharp4.cs
PAUSE
start cmd /C Sharp4.exe ^>ruby5.rb
PAUSE
start cmd /C ruby ruby5.rb ^>ruby6.rb
PAUSE
start cmd /C ruby ruby6.rb ^>JavaAnswer.java
