REM set JAVA_HOME=
REM SET ASTROSOFT_HOME=
SET CLASSPATH=
SET CLASSPATH=%ASTROSOFT_HOME%/classes;%ASTROSOFT_HOME%/lib/swisseph.jar;%ASTROSOFT_HOME%/lib/org.jar;
SET CLASSPATH=%CLASSPATH%;%ASTROSOFT_HOME%/lib/suntimes.jar;%ASTROSOFT_HOME%/lib/kunststoff.jar;%ASTROSOFT_HOME%/lib/toniclf.jar;
SET CLASSPATH=%CLASSPATH%;%ASTROSOFT_HOME%/resources;
%JAVA_HOME%\bin\java app.astrosoft.core.Horoscope > testout.txt