/*
 * Copyright (C) 2023 pedroSG94.
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

package com.pedro.srt

import org.junit.Assert.assertEquals
import org.mockito.MockedStatic

/**
 * Created by pedro on 1/9/23.
 */
object Utils {
  fun assertObjectEquals(actual: Any, expected: Any) {
    assertEquals(actual.toString(), expected.toString())
  }

  fun useStatics(statics: List<MockedStatic<out Any>>, callback: () -> Unit) {
    val list = statics.toMutableList()
    if (list.isEmpty()) callback()
    else if (list.size == 1) {
      list[0].use {
        callback()
      }
    } else {
      val value = list.removeAt(0)
      value.use { useStatics(list, callback) }
    }
  }
}
