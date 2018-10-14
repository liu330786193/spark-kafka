package com.lyl.trace

@SerialVersionUID(5L)
class LogDataEntity (
                           var timestamp: Long,
                           var logs: List[KeyValuePair]
                         ) extends Serializable
