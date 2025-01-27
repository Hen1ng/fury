/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.fury.serializer.collection;

import org.apache.fury.memory.MemoryBuffer;

import java.util.Iterator;
import java.util.Map;

public class StringMapSerialization {
    /**
     * Write string chunk until there isn't any entry left.
     */
    public static void writeStringChunks(
            MemoryBuffer buffer,
            Map.Entry<String, String> entry,
            Iterator<Map.Entry<String, String>> iterator) {

    }
    /**
     * Write string chunk until there isn't any entry left or chunk size reached max value..
     */
    public static Map.Entry<String, String> writeStringChunk(
            MemoryBuffer buffer,
            Map.Entry<String, String> entry,
            Iterator<Map.Entry<String, String>> iterator) {

    }

    /**
     * Write string chunk until next entry is not string type.
     */
    public static Map.Entry writeChunk(
            MemoryBuffer buffer,
            Map.Entry<String, String> entry,
            Iterator<Map.Entry> iterator) {
        return null;
    }

    /**
     * Read all string kv chunks and put it into map until all chunks are read.
     */
    public static void readChunks(
            MemoryBuffer buffer, Map<String, String> map, long size, int chunkHeader) {

    }

    public static int readChunk(
            MemoryBuffer buffer, Map<String, String> map, long size, int chunkHeader) {

    }

}
