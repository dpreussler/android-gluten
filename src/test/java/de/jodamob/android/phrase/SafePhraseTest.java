package de.jodamob.android.phrase;

import android.content.res.Resources;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    public void should_catch_exception_2() {
        Resources resources = mock(Resources.class);
        when(resources.getText(anyInt())).thenThrow(new IllegalArgumentException());
        assertEquals("",SafePhrase.from(resources, 12).put("will_not_found", "this").format().toString());
    }
}
