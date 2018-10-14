//package com.lyl
//
//import com.tsign.cat.api.jvm.JvmDTO
//import io.protostuff.ProtobufIOUtil
//import io.protostuff.runtime.RuntimeSchema
//import kafka.serializer.Decoder
//import kafka.utils.VerifiableProperties
//
//class ProtobufDecoder(props: VerifiableProperties = null) extends Decoder[JvmDTO]{
//
//  def fromBytes(bytes: Array[Byte]): JvmDTO = {
//    val clazz = classOf[JvmDTO];
//    val schema = RuntimeSchema.getSchema(clazz);
//    val jvm = clazz.newInstance();
//    ProtobufIOUtil.mergeFrom(bytes, jvm, schema);
//    jvm
//  }
//}
