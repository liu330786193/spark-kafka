package com.lyl.trace

@SerialVersionUID(1L)
class DefaultTraceSegment (
                                var traceSegmentId: String,
                                var refs: List[DefaultTraceSegmentRef],
                                var spans: List[DefaultTracingSpan],
                                var applicationId: String,
                                var applicationInstanceId: String,
                                var ip: String,
                                var rgts: List[String],
                                var ignore: Boolean,
                                var isSizeLimited: Boolean,
                                var singleSpanSegment: Boolean
                               ) extends Serializable
