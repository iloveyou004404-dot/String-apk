package com.example

import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
  @Test
  fun addition_isCorrect() {
    assertEquals(4, 2 + 2)
  }

  @Test
  fun verify_all_letters_A_to_Z_present() {
    val settings = com.example.data.SettingsRepository.getAllSettings()
    val letters = settings.map { it.letter }.toSet()
    for (ch in 'A'..'Z') {
      assertTrue("Missing letter $ch in settings repository", letters.contains(ch))
    }
  }
}
