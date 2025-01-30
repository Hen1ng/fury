///*
// * Licensed to the Apache Software Foundation (ASF) under one
// * or more contributor license agreements.  See the NOTICE file
// * distributed with this work for additional information
// * regarding copyright ownership.  The ASF licenses this file
// * to you under the Apache License, Version 2.0 (the
// * "License"); you may not use this file except in compliance
// * with the License.  You may obtain a copy of the License at
// *
// *   http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing,
// * software distributed under the License is distributed on an
// * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// * KIND, either express or implied.  See the License for the
// * specific language governing permissions and limitations
// * under the License.
// */
//
//package org.apache.fury.serializer.collection;
//
//import org.apache.fury.Fury;
//import org.apache.fury.memory.MemoryBuffer;
//import org.apache.fury.resolver.ClassInfo;
//import org.apache.fury.resolver.ClassResolver;
//import org.apache.fury.resolver.RefResolver;
//import org.apache.fury.serializer.Serializer;
//
//import java.util.Iterator;
//import java.util.Map;
//
//import static org.apache.fury.serializer.collection.MapFlags.*;
//import static org.apache.fury.serializer.collection.MapFlags.TRACKING_VALUE_REF;
//
//public class StringMapSerialization {
//
//    public static void main(String[] args) {
//        String s = "bew";
//        final Class<? extends String> aClass = s.getClass();
//        final Class<String> stringClass = String.class;
//        System.out.println(stringClass.equals(aClass));
//    }
//
//    /**
//     * Write string chunk until there isn't any entry left.
//     */
//    public static void writeKeyStringChunks(
//            MemoryBuffer buffer,
//            Map.Entry<Object, Object> entry,
//            Iterator<Map.Entry<Object, Object>> iterator, Fury fury, ClassResolver classResolver) {
//        Object key = entry.getKey();
//        Object value = entry.getValue();
//        Class keyType = key.getClass();
//        Class valueType = value.getClass();
//        // place holder for chunk header and size.
//        buffer.writeInt16((short) -1);
//        int chunkSizeOffset = buffer.writerIndex() - 1;
//        int chunkHeader = 0;
//        Serializer keySerializer = writeStringClass(classResolver, keyType, buffer);
//        Serializer valueSerializer = writeValueClassInfo(classResolver, valueType, buffer);
//        // noinspection Duplicates
//        boolean keyWriteRef = keySerializer.needToWriteRef();
//        boolean valueWriteRef = valueSerializer.needToWriteRef();
//        chunkHeader = updateHeader(chunkHeader, keyWriteRef, valueWriteRef);
//        buffer.putByte(chunkSizeOffset - 1, (byte) chunkHeader);
//        RefResolver refResolver = fury.getRefResolver();
//        // Use int to make chunk size representable for 0~255 instead of 0~127.
//        int chunkSize = 0;
//        while (true) {
//            if (key == null
//                    || value == null
//                    || (key.getClass() != String.class)) {
//                break;
//            }
//            if (!keyWriteRef || !refResolver.writeRefOrNull(buffer, key)) {
//                keySerializer.write(buffer, key);
//            }
//            if (!valueWriteRef || !refResolver.writeRefOrNull(buffer, value)) {
//                valueSerializer.write(buffer, value);
//            }
//            // noinspection Duplicates
//            if (++chunkSize == 255) {
//                break;
//            }
//            if (iterator.hasNext()) {
//                entry = iterator.next();
//                key = entry.getKey();
//                value = entry.getValue();
//            } else {
//                entry = null;
//                break;
//            }
//        }
//        buffer.putByte(chunkSizeOffset, (byte) chunkSize);
////        return entry;
//    }
//
//    private static int updateHeader(int chunkHeader, boolean keyWriteRef, boolean valueWriteRef) {
//        if (keyWriteRef) {
//            chunkHeader |= TRACKING_KEY_REF;
//        }
//        if (valueWriteRef) {
//            chunkHeader |= TRACKING_VALUE_REF;
//        }
//        return chunkHeader;
//    }
//
//    private static Serializer writeValueClassInfo(ClassResolver classResolver, Class keyType, MemoryBuffer buffer) {
//        ClassInfo classInfo = classResolver.getClassInfo(keyType);
//        classResolver.writeClass(buffer, classInfo);
//        return classInfo.getSerializer();
//    }
//
//    private static Serializer writeStringClassInfo(ClassResolver classResolver, Class keyType, MemoryBuffer buffer) {
//        ClassInfo classInfo = classResolver.getClassInfo(keyType);
//        classResolver.writeClass(buffer, classInfo);
//        return classInfo.getSerializer();
//    }
//
//    /**
//     * Write string chunk until there isn't any entry left or chunk size reached max value..
//     */
//    public static Map.Entry<String, String> writeStringChunk(
//            MemoryBuffer buffer,
//            Map.Entry<String, String> entry,
//            Iterator<Map.Entry<String, String>> iterator) {
//        return null;
//    }
//
//    /**
//     * Write string chunk until next entry is not string type.
//     */
//    public static Map.Entry writeChunk(
//            MemoryBuffer buffer,
//            Map.Entry<String, String> entry,
//            Iterator<Map.Entry> iterator) {
//        return null;
//    }
//
//    /**
//     * Read all string kv chunks and put it into map until all chunks are read.
//     */
//    public static void readChunks(
//            MemoryBuffer buffer, Map<String, String> map, long size, int chunkHeader) {
//
//    }
//
//    public static int readChunk(
//            MemoryBuffer buffer, Map<String, String> map, long size, int chunkHeader) {
//        return 0;
//    }
//
//}
