package de.jodamob.android.utils;

import android.view.View;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class ViewUtilsTest {

    @Test
    public void removeView() throws IOException {
        View mock = mock(View.class);
        ViewUtils.removeView(mock);
        verify(mock).setVisibility(View.GONE);
    }

    @Test
    public void hideView() throws IOException {
        View mock = mock(View.class);
        ViewUtils.hideView(mock);
        verify(mock).setVisibility(View.INVISIBLE);
    }

    @Test
    public void showView() throws IOException {
        View mock = mock(View.class);
        ViewUtils.showView(mock);
        verify(mock).setVisibility(View.VISIBLE);
    }

    @Test
    public void showOrRemoveTrue() throws IOException {
        View mock = mock(View.class);
        ViewUtils.showOrRemove(true, mock);
        verify(mock).setVisibility(View.VISIBLE);
    }

    @Test
    public void showOrRemoveFalse() throws IOException {
        View mock = mock(View.class);
        ViewUtils.showOrRemove(false, mock);
        verify(mock).setVisibility(View.GONE);
    }

    @Test
    public void showOrHideTrue() throws IOException {
        View mock = mock(View.class);
        ViewUtils.showOrHide(true, mock);
        verify(mock).setVisibility(View.VISIBLE);
    }

    @Test
    public void showOrHideFalse() throws IOException {
        View mock = mock(View.class);
        ViewUtils.showOrHide(false, mock);
        verify(mock).setVisibility(View.INVISIBLE);
    }
}
