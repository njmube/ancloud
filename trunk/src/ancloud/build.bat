call echo %DATE% %TIME%>> mvn.log
call mvn -f %~dp0/pom.xml release:update-versions -DautoVersionSubmodules=true -DdevelopmentVersion=1.0.8-SNAPSHOT >> mvn.log
call mvn -f %~dp0/pom.xml package >> mvn.log