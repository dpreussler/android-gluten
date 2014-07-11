package de.jodamob.android.phrase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class SafePhraseTest {

    @Test
    public void should_work() {
        assertEquals("hello world", SafePhrase.from("hello {who}").put("who", "world").format().toString());
    }

    @Test
    public void should_catch_exception() {
        assertEquals("wrong string", SafePhrase.from("wrong string").put("will_not_found", "this").format().toString());
    }

}
