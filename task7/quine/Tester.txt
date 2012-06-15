start cmd /C javac Java1.java
PAUSE
start cmd /C java Java1 ^>Java2.java
PAUSE
start cmd /C javac Java2.java
PAUSE
start cmd /C java Java2 ^>Sharp1.cs
PAUSE
start cmd /C csc Sharp1.cs
PAUSE 
start cmd /C Sharp1.exe^>Sharp2.cs
PAUSE
start cmd /C csc Sharp2.cs
PAUSE 
start cmd /C Sharp2.exe ^>ruby1.rb
PAUSE 
cmd /C ruby ruby1.rb ^>ruby2.rb
PAUSE
cmd /C ruby ruby2.rb ^>java1.java
PAUSE
