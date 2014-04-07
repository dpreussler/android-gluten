android-gluten
==============

Gluten is what it all holds together.
This is the glue code between popular android libraries. 


![alt tag](http://jodamob.de/MTC%20Spring%20Speakerbutton%203.jpg)

Gradle
======
```
dependencies {
	compile 'de.jodamob.android:android-gluten:0.2-11-g787ca31'
}
```


AutoLayout
==========
Why glue together your Activity and Fragments with the xml file manually?
Keep the names in sync and it will be done for you.

Simple extend from one of:
```
AutoLayoutActivity
AutoLayoutFragment
AutoLayoutPreferenceFragment
```

both will do setContentView in onCreate / inflateView in onCreateView based on their name using
lowercase and underscores instead of CamelCase.

So:
`DashboardStatisticsActivity`
must have a layout file
`dashboard_statistics_activity.xml`
it also searches for
`dashboard_statistics.xml`
and
`dashboard.xml`


Espresso:
=========
Espresso is a testing library based on Android Instrumentation tests
https://code.google.com/p/android-test-kit/wiki/Espresso

Before checking assertions Espresso waits to let app be idle. Therefore it checks the mainlooper and AsyncTasks default-executor. But custom threads and threadpools must be registerd as "IdleResource" to be monitored.

IdleResource for Android Priority-JobQueue
------------------------------------------------------------------
https://github.com/path/android-priority-jobqueue

Usage:
`Espresso.registerIdlingResources(new PriorityJobQueueIdleMonitor(new JobManager(context)));`


New Relic Tracer for Retrofit
====================================
New Relic is an app monitoring tool. It can log HTTP results for HttpUrlConnection and Apache and is supposed to support OkHttp
 since release 264 but seems to have some problems. So here are alternative trace implementations


Usage:

set a profiler when building the RestAdapter:

```
return new RestAdapter.Builder()
            .setProfiler(new TracedRetrofitProfiler(new NewRelicTracer()));

```


https://github.com/square/retrofit
https://docs.newrelic.com/docs/mobile-apps/android-api
https://github.com/square/okhttp


Just another logging framework
==============================
multiple combinable logging classes.
Combine local logging with rolling file logging with Crashlytcs with NewRelic...


LogCatLogger : writes to android.util.log

FileLogger : writes rotating log files with java.util.logging (get content written via FileLogger.getLogFileContent() if needed)

SilentLogger: does not log (implements same interface as above, can be used to disable logging)

AsyncLogger : moves all logging into background thread (might be useful in combination with FileLogger)

UncaughtExceptionLogger: registers as exception handler in system and reports those exceptions to logfile

CrashlyticsLogger: sends exceptions that where logged to crashlytics as caught crash)

NewRelicLogger: sends exceptions that where logged to NewRelic as Network errors (there is no better way for now in API)


Log : has the same "interface" as android.util.Log so that only the package has to be changed with one additional initializer method:
Log.initLogger() excepts any of the loggers above


example:

`Log.initLogger(new LogCatLogger())` writes to console (default)

`Log.initLogger(new FileLogger(context, new LogCatLogger()))` writes to files + console

`Log.initLogger(new UncaughtExceptionLogger(new FileLogger(context)))` writes logs and uncaught crashes to file 

`Log.initLogger(new AsyncLogger(new FileLogger(new CrashlyticsLogger(new LogCatLogger()))));` writes async to file and crashlytics and logcat

`Log.initLogger(new SilentLogger())` disables logging

Or simply create the UberLog:

```
	Log.initLogger(
                new NewRelicLogger(
                        new CrashlyticsLogger(
                                new LogCatLogger(
                                        new UncaughtExceptionLogger(
                                                new AsyncLogger(context, 
                                                        new FileLogger(context)))))));
``` 




These source samples are licensed under MIT license
(c) 2014 Danny Preussler
