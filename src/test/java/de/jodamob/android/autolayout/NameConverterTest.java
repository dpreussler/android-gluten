package de.jodamob.android.autolayout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class NameConverterTest {

    @Test
    public void test() {
       assertEquals("name_converter_test", NameConverter.convertToResourceName(NameConverterTest.this));
    }
}
