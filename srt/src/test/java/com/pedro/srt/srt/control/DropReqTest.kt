/*
 * Copyright (C) 2024 pedroSG94.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pedro.srt.srt.control

import com.pedro.srt.Utils
import com.pedro.srt.srt.packets.control.DropReq
import org.junit.Assert.assertArrayEquals
import org.junit.Test
import java.io.ByteArrayInputStream

/**
 * Created by pedro on 1/9/23.
 */
class DropReqTest {

  @Test
  fun `GIVEN a drop req packet WHEN write packet in a buffer THEN get expected buffer`() {
    val expectedData = byteArrayOf(-128, 7, 0, 0, 0, 0, 0, 5, 0, 0, 9, -60, 0, 0, 0, 64, 0, 0, 0, 1, 0, 0, 0, 8)
    val dropReq = DropReq(
      messageNumber = 5,
      firstPacketSequenceNumber = 1,
      lastPacketSequenceNumber = 8
    )
    dropReq.write(2500, 0x40)
    val packetDropReq = dropReq.getData()

    assertArrayEquals(expectedData, packetDropReq)
  }

  @Test
  fun `GIVEN a buffer WHEN read buffer as drop req packet THEN get expected drop req packet`() {
    val buffer = byteArrayOf(-128, 7, 0, 0, 0, 0, 0, 5, 0, 0, 9, -60, 0, 0, 0, 64, 0, 0, 0, 1, 0, 0, 0, 8)
    val expectedPacket = DropReq(
      messageNumber = 5,
      firstPacketSequenceNumber = 1,
      lastPacketSequenceNumber = 8
    )
    val packet = DropReq()
    packet.read(ByteArrayInputStream(buffer))

    Utils.assertObjectEquals(expectedPacket, packet)
  }
}