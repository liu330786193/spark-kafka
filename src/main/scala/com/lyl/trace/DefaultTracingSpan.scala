package com.lyl.trace

@SerialVersionUID(3L)
class DefaultTracingSpan (
                                var spanId: Int,
                                var parentSpanId: Int,
                                var tags: List[KeyValuePair],
                                var operationName: String,
                                var layer: String,
                                var startTime: Long,
                                var endTime: Long,
                                var errorOccurred: Boolean,
                                var componentId: Int,
                                var entry: Boolean,
                                var exit: Boolean,
                                var logs: List[LogDataEntity],
                                var time: Long
                              ) extends Serializable
