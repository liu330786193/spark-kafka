package com.lyl.trace

@SerialVersionUID(2L)
class DefaultTraceSegmentRef (
                                  var `type`: String,
                                  var traceSegmentId: String,
                                  var spanId: Int,
                                  var peerHost: String,
                                  var entryApplicationInstanceId: String,
                                  var parentApplicationInstanceId: String,
                                  var entryOperationName: String,
                                  var parentOperationName: String
                                  ) extends Serializable
