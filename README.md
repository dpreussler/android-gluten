android-gluten
==============

Gluten is what it all holds together.
This is the glue code between popular android libraries. 


![alt tag](http://jodamob.de/MTC%20Spring%20Speakerbutton%203.jpg)

Gradle
======
```

compile ('de.jodamob.android:android-gluten:0.3-3-g89a07a5') {
    // exclude some dependencies because it was build as java not android project
        exclude group: 'org.robolectric'
}

```
or grab from build folder and put in your libs:

```

compile files('libs/android-gluten-0.2-16-gfbf52e4.jar')

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

They also try to inflate the menu based on same names in menu folders!

SafePhrase
==========
Runtime safe version of the Phrase class

Usage:
Same as Phrase:

'SafePhrase.from(...).put(...).format()'

Will not crash if elements are not found. Will do a wtf log instead (when combined with Crashlytics
logger this would still send the stacktrace so you can fix it easily with next version).


https://github.com/square/phrase


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
                                                new AsyncLogger(
                                                        new FileLogger(context)))))));
``` 





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



Licensed under MIT license
(c) 2014 Danny Preussler

This software binds to other software, please acknowledge those copyrights
or make sure to exclude them at compile time:
Espresso and Android Copyright Google Inc.
OkHTTP, Picasso, Phrase Copyright Square, Inc.
Crashlytics, Copyright Crashlytics
New Relic, Copyright New Relic, Inc.

