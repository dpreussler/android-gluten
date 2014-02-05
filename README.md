android-gluten
==============

Gluten is what it all holds together.
This is the glue code between popular android libraries. 

Espresso:
=========
Espresso is a testing library based on Android Instrumentation tests
https://code.google.com/p/android-test-kit/wiki/Espresso

Before checking assertions Espresso waits to let app be idle. Therefore it checks the mainlooper and AsyncTasks default-executor. But custom threads and threadpools must be registerd as "IdleResource" to be monitored.

IdleResource for Android Priority-JobQueue
------------------------------------------------------------------
https://github.com/path/android-priority-jobqueue

Usage:
Espresso.registerIdlingResources(new PriorityJobQueueIdleMonitor(new JobManager(context)));



New Relic implementations for OkHttp
====================================
New Relic is an app monitoring tool. It can log HTTP results for HttpUrlConnection and Apache but not OkHttp

https://docs.newrelic.com/docs/mobile-apps/android-api

New Relic Trace for Retrofit
-------------------------------------------

Usage: 
instead of

new OkClient(new OkHttpClient()) 
for the RestAdapter use:

new TracedRetrofitClient(new OkClient(new OkHttpClient()), new NewRelicTracer())



https://github.com/square/retrofit
https://github.com/square/okhttp

TODO: very basic for now, no load tracking

New Relic Trace for Picasso
---------------------------

Usage: 
create your Picasso instance with something like this:

new Picasso.Builder(context).downloader(
	new TracedPicassoDownloader(new NewRelicTracer(), context)).build();



https://github.com/square/picasso

TODO: very basic for now, no load tracking


Just another logging framework
------------------------------
multiple combinable logging classes

LogCatLogger : writes to android.util.log

FileLogger : writes rotating log files with java.util.logging

SilentLogger: does not log (implements same interface as above, can be used to disable logging)

AsyncLogger : moves all logging into background thread (might be useful in combination with FileLogger)

Log : has the same "interface" as android.util.Log so that only the package has to be changed

Log.initLogger() excepts any of the loggers above

example:

Log.initLogger(new LogCatLogger()) writes to console (default)

Log.initLogger(new FileLogger(context, new LogCatLogger(()) writes to files + console

Log.initLogger(new SilentLogger()) disables logging


TODO: will be extended with Crashlytics or New Relic logger to send loggings to remote destinations


These source samples are licensed under MIT license
(c) 2014 Danny Preussler
