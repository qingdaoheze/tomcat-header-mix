1. Start the tomcat server through: tomcat-server/src/main/java/org/debug/tomcat/Application.java
2. Invoke it through the client: client/src/main/java/org/debug/client/JettyHttp2Client.java

You will see the exception as bellow:
```java
Exception in thread "http-nio-8080-exec-2" java.lang.NumberFormatException: For input string: "debug"
	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
	at java.lang.Long.parseLong(Long.java:589)
	at java.lang.Long.parseLong(Long.java:631)
	at org.apache.tomcat.util.buf.MessageBytes.getLong(MessageBytes.java:559)
	at org.apache.coyote.Request.getContentLengthLong(Request.java:450)
	at org.apache.coyote.http2.Stream.isContentLengthInconsistent(Stream.java:588)
	at org.apache.coyote.http2.Stream.receivedEndOfStream(Stream.java:574)
	at org.apache.coyote.http2.Http2UpgradeHandler.receivedEndOfStream(Http2UpgradeHandler.java:1591)
	at org.apache.coyote.http2.Http2AsyncUpgradeHandler.receivedEndOfStream(Http2AsyncUpgradeHandler.java:41)
	at org.apache.coyote.http2.Http2Parser.onHeadersComplete(Http2Parser.java:625)
	at org.apache.coyote.http2.Http2Parser.readHeadersFrame(Http2Parser.java:275)
	at org.apache.coyote.http2.Http2AsyncParser$FrameCompletionHandler.completed(Http2AsyncParser.java:251)
	at org.apache.coyote.http2.Http2AsyncParser$FrameCompletionHandler.completed(Http2AsyncParser.java:164)
	at org.apache.tomcat.util.net.SocketWrapperBase$VectoredIOCompletionHandler.completed(SocketWrapperBase.java:1122)
	at org.apache.tomcat.util.net.NioEndpoint$NioSocketWrapper$NioOperationState.run(NioEndpoint.java:1702)
	at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)
	at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:748)
```